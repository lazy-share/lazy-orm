package com.lazy.orm.session;

import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.exception.BindingException;
import com.lazy.orm.executor.Executor;
import com.lazy.orm.mapper.MappedStatement;

import java.sql.SQLException;

/**
 * <p>
 * DefaultSqlSession
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private Log log = LogFactory.getLog(DefaultSqlSession.class);
    private final Executor executor;


    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T execute(String mapperKey, Object params) {

        MappedStatement mappedStatement = configuration.getMappedStatement(mapperKey);
        if (mappedStatement == null) {
            throw new BindingException(String.format("Mapper [%s]没有注册", mapperKey));
        }

        return this.executor.execute(mappedStatement, params);
    }

    @Override
    public void commit() {
        try {
            this.executor.commit(true);
        } catch (SQLException e) {
            log.error("提交事务异常", e);
        }
    }

    @Override
    public void rollback() {
        try {
            this.executor.rollback(true);
        } catch (SQLException e) {
            log.error("回滚事务异常", e);
        }
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
