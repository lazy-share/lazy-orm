package com.lazy.orm.datasource.pool;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 连接池状态对象
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class PoolState {


    /**
     * 活动连接队列
     */
    private final Deque<PoolProxyConnection> activeConnections = new LinkedList<>();

    /**
     * 空闲连接集合
     */
    private final List<PoolProxyConnection> idleConnections = new ArrayList<>();

    public Deque<PoolProxyConnection> getActiveConnections() {
        return activeConnections;
    }

    public List<PoolProxyConnection> getIdleConnections() {
        return idleConnections;
    }
}
