package com.lazy.orm.handler;

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

    public ResultSet getResultSet() {
        return resultSet;
    }

    public ResultSetContext setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
        return this;
    }
}
