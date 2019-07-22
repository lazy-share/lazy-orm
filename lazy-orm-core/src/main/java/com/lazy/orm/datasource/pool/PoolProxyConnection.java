package com.lazy.orm.datasource.pool;


import com.lazy.orm.exception.ConnectionPoolException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

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
    private boolean valid;

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
        return valid;
    }

    public PoolProxyConnection setValid(boolean valid) {
        this.valid = valid;
        return this;
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    public long getCheckoutTimestamp() {
        return checkoutTimestamp;
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
}
