package com.lazy.orm.executor.simple;

import com.lazy.orm.exception.ExecutorException;
import com.lazy.orm.executor.support.AbstractExecutor;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.tx.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * 简单执行器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class SimpleExecutor extends AbstractExecutor {

    public SimpleExecutor(Transaction tx) {
        this.tx = tx;
    }

    @Override
    public <T> T execute(MappedStatement statement, Object params) {
        try {
            Connection connection = tx.getConnection();

        } catch (SQLException e) {
            throw new ExecutorException(e);
        }
        return null;
    }

    @Override
    public void commit(boolean required) throws SQLException {

    }

    @Override
    public void rollback(boolean required) throws SQLException {

    }
}
