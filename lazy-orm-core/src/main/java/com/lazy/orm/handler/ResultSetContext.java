package com.lazy.orm.handler;

import com.lazy.orm.mapper.MappedStatement;

import java.sql.ResultSet;

/**
 * <p>
 * 结果集上下文
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class ResultSetContext {

    private ResultSet resultSet;
    private MappedStatement statement;

    public ResultSetContext(ResultSet resultSet, MappedStatement statement) {
        this.resultSet = resultSet;
        this.statement = statement;
    }

    public MappedStatement getStatement() {
        return statement;
    }

    public ResultSetContext setStatement(MappedStatement statement) {
        this.statement = statement;
        return this;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public ResultSetContext setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
        return this;
    }
}
