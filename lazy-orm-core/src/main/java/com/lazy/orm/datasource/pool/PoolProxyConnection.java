package com.lazy.orm.datasource.pool;


import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.exception.ConnectionPoolException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * 连接池连接代理对象
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class PoolProxyConnection implements InvocationHandler {


    private final Connection realConnection;
    private final PooledDataSource dataSource;
    private final int hashCode;
    private static final String CLOSE = "close";
    private static final Class<?>[] INTEFACES = new Class<?>[]{Connection.class};
    private final Connection proxyConnection;
    private long checkoutTimestamp;
    private long createdTimestamp;
    private long lastUsedTimestamp;
    protected String pingStatement = "Please Config A Ping Sql Statement";
    private boolean valid;
    private boolean poolPingEnabled = true;
    private long pingIntervalTime = 10000 * 1000;

    private static Log log = LogFactory.getLog(PoolProxyConnection.class);

    public PoolProxyConnection(Connection realConnection, PooledDataSource dataSource) {
        this.realConnection = realConnection;
        this.dataSource = dataSource;
        this.hashCode = realConnection.hashCode();
        this.createdTimestamp = System.currentTimeMillis();
        this.lastUsedTimestamp = System.currentTimeMillis();
        this.valid = true;
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), INTEFACES, this);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }


    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (CLOSE.hashCode() == methodName.hashCode() && CLOSE.equals(methodName)) {
            dataSource.doPushConnection(this);
            return null;
        }
        try {
            if (!Object.class.equals(method.getDeclaringClass())) {
                if (!this.isValid()) {
                    throw new ConnectionPoolException("当前数据库连接已经失效");
                }
            }
            return method.invoke(realConnection, args);
        } catch (Throwable t) {
            throw new ConnectionPoolException(t);
        }
    }

    public boolean isValid() {
        return valid && pingConnection(this);
    }

    public long getTimeForNotUse() {
        return System.currentTimeMillis() - lastUsedTimestamp;
    }

    protected boolean pingConnection(PoolProxyConnection conn) {

        log.info("检查连接是否存活");
        boolean result;

        try {
            result = !conn.getRealConnection().isClosed();
        } catch (SQLException e) {
            result = false;
        }

        if (result) {
            if (poolPingEnabled) {
                if (conn.getTimeForNotUse() > pingIntervalTime) {

                    log.info("检查连接是否存活, ping....");
                    try {
                        Connection realConn = conn.getRealConnection();
                        try (Statement statement = realConn.createStatement()) {
                            statement.executeQuery(pingStatement).close();
                        }
                        if (!realConn.getAutoCommit()) {
                            realConn.rollback();
                        }
                        result = true;

                        log.info("检查连接是否存活, ping ok");
                    } catch (Exception e) {

                        log.warn("连接ping失败 ping sql: " + this.pingStatement);
                        try {
                            conn.getRealConnection().close();
                        } catch (Exception e2) {
                            //ignore
                        }
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    public String getPingStatement() {
        return pingStatement;
    }

    public PoolProxyConnection setPingStatement(String pingStatement) {
        this.pingStatement = pingStatement;
        return this;
    }

    public PoolProxyConnection setValid(boolean valid) {
        this.valid = valid;
        return this;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public long getCheckoutTimestamp() {
        return System.currentTimeMillis() - checkoutTimestamp;
    }

    public PoolProxyConnection setCheckoutTimestamp(long checkoutTimestamp) {
        this.checkoutTimestamp = checkoutTimestamp;
        return this;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public PoolProxyConnection setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    public long getLastUsedTimestamp() {
        return lastUsedTimestamp;
    }

    public PoolProxyConnection setLastUsedTimestamp(long lastUsedTimestamp) {
        this.lastUsedTimestamp = lastUsedTimestamp;
        return this;
    }

    public Connection getRealConnection() {
        return realConnection;
    }
}
