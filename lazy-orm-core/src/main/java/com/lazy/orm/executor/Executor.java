package com.lazy.orm.executor;

import com.lazy.orm.mapper.MappedStatement;

import java.sql.SQLException;

/**
 * <p>
 * 执行器接口定义
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public interface Executor {


    <T> T execute(MappedStatement statement, Object[] params);

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close();

}
