package com.lazy.orm.datasource.pool;


import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.datasource.simple.SimpleDataSource;
import com.lazy.orm.datasource.support.AbstractDataSource;
import com.lazy.orm.exception.ConnectionPoolException;
import com.lazy.orm.util.StringUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author laizhiyuan
 * @since 2019-07-22.
 */
public class PooledDataSource extends AbstractDataSource {

    private static final Log log = LogFactory.getLog(PooledDataSource.class);

    private final DataSource dataSource;

    protected final PoolState state = new PoolState();
    protected long maxActiveCount = 10;
    protected long maxIdleCount = 5;
    protected long maxWaitTime = 20000;
    protected long maxCheckoutTime = 20000;
    protected boolean poolPingEnabled = true;
    protected String pingStatement = "Please Config A Ping Sql Statement";


    public PooledDataSource(Properties properties) {
        this.dataSource = new SimpleDataSource(properties);
        this.pingStatement = properties.getProperty("pingStatement");
        String maxIdleCountConfig = properties.getProperty("maxIdleCount");
        if (StringUtil.hasText(maxIdleCountConfig)){
            this.maxIdleCount = Long.valueOf(maxIdleCountConfig);
        }
        String maxActiveCountConfig = properties.getProperty("maxActiveCount");
        if (StringUtil.hasText(maxActiveCountConfig)){
            this.maxActiveCount = Long.valueOf(maxActiveCountConfig);
        }
        this.poolPingEnabled = Boolean.valueOf(properties.getOrDefault("poolPingEnabled", true).toString());

        String maxWaitTimeConfig = properties.getProperty("maxWaitTime");
        if (StringUtil.hasText(maxWaitTimeConfig)){
            this.maxWaitTime = Long.valueOf(maxWaitTimeConfig);
            this.maxCheckoutTime = Long.valueOf(maxWaitTimeConfig);
        }

        log.info("连接池参数: \n pingStatement: "
                + this.pingStatement
                + "\n maxIdleCount:"
                + this.maxIdleCount
                + "\n maxActiveCount:"
                + this.maxActiveCount
                + "\n maxWaitTime:"
                + this.maxWaitTime
                + "\n maxCheckoutTime:"
                + this.maxCheckoutTime
                + "\n poolPingEnabled:"
                + this.poolPingEnabled
        );

    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.doGetConnection().getProxyConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return this.getConnection();
    }

    protected PoolProxyConnection doGetConnection() throws SQLException {

        PoolProxyConnection connection = null;
        while (connection == null) {
            synchronized (state) {
                //先找空闲的
                if (!state.getIdleConnections().isEmpty()) {
                    connection = state.getIdleConnections().remove(0);

                    log.info("从空闲连接获取一个连接 线程ID:" + Thread.currentThread().getId());
                    //再检查活动的
                } else {

                    //如果没有大于最大活动数则new一个
                    if (state.getActiveConnections().size() < maxActiveCount) {

                        log.info("从活动连接新建一个连接 线程ID:" + Thread.currentThread().getId());
                        connection = new PoolProxyConnection(dataSource.getConnection(), this).setPingStatement(pingStatement);
                    } else {

                        //拿出最早活动的那条链接
                        PoolProxyConnection firstOldConnection = state.getActiveConnections().peek();
                        if (firstOldConnection == null){
                            throw new ConnectionPoolException("从活动获取连接为Null");
                        }
                        //判断如果大于签出时间,则强制失效,回滚连接
                        if (firstOldConnection.getCheckoutTimestamp() > maxCheckoutTime) {

                            log.info("从活动连接强制失效一个大于签出时间的连接 线程ID:" + Thread.currentThread().getId());
                            connection = new PoolProxyConnection(firstOldConnection.getRealConnection(), this);
                            state.getActiveConnections().remove(firstOldConnection);
                            firstOldConnection.setValid(false);

                            if (!connection.getRealConnection().getAutoCommit()) {
                                try {

                                    firstOldConnection.getRealConnection().rollback();
                                } catch (SQLException e) {
                                    log.debug("Bad connection. Could not roll back");
                                }
                            }

                            //没有任何可用连接
                        } else {
                            try {

                                log.info("没有可用连接, 当前线程进入等待 线程ID:" + Thread.currentThread().getId());
                                //释放当前线程state对象锁,等待其它线程唤醒
                                state.wait(maxWaitTime);

                            } catch (InterruptedException e) {

                                log.info("没有可用连接, 当前线程等待超时 线程ID:" + Thread.currentThread().getId());
                                //等待超时
                                break;
                            }
                        }
                    }
                }

                if (connection != null) {
                    if (connection.isValid()) {
                        if (!connection.getRealConnection().getAutoCommit()) {
                            connection.getRealConnection().rollback();
                        }
                        connection.setCheckoutTimestamp(System.currentTimeMillis());
                        connection.setLastUsedTimestamp(System.currentTimeMillis());
                        state.getActiveConnections().push(connection);
                    }
                }else {
                    log.error("获取连接为Null");
                }
            }
        }

        return connection;
    }

    protected void doPushConnection(PoolProxyConnection poolProxyConnection) throws SQLException {
        synchronized (state) {
            log.info("回收一个连接 线程ID:" + Thread.currentThread().getId());
            state.getActiveConnections().remove(poolProxyConnection);
            if (poolProxyConnection.isValid()) {

                //可回收
                if (state.getIdleConnections().size() < maxIdleCount) {

                    log.info("连接可回收 线程ID:" + Thread.currentThread().getId());
                    if (!poolProxyConnection.getRealConnection().getAutoCommit()) {
                        poolProxyConnection.getRealConnection().rollback();
                    }
                    PoolProxyConnection newConn = new PoolProxyConnection(poolProxyConnection.getRealConnection(), this);
                    state.getIdleConnections().add(newConn);
                    log.info("当前空闲连接:" + state.getIdleConnections().size());
                    newConn.setCreatedTimestamp(poolProxyConnection.getCreatedTimestamp());
                    newConn.setLastUsedTimestamp(poolProxyConnection.getLastUsedTimestamp());
                    poolProxyConnection.setValid(false);

                    //回收了一个连接,通知所有等待线程,其它线程将进行一次锁竞争
                    state.notifyAll();

                    //不可回收
                } else {

                    log.info("空闲池已满, 连接不可回收 线程ID:" + Thread.currentThread().getId());
                    if (!poolProxyConnection.getRealConnection().getAutoCommit()) {
                        poolProxyConnection.getRealConnection().rollback();
                    }
                    poolProxyConnection.getRealConnection().close();
                    poolProxyConnection.setValid(false);
                }
            } else {
                log.warn("回收到一个坏的连接 线程ID:" + Thread.currentThread().getId());
            }
        }
    }
}
