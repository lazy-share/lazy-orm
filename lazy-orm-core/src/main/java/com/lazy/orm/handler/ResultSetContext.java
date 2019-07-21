package com.lazy.orm.handler;

import com.lazy.orm.mapper.MappedStatement;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * <p>
 * 结果集上下文
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class ResultSetContext {

    private Statement stmt;
    private MappedStatement statement;

    public ResultSetContext(Statement stmt, MappedStatement statement) {
        this.stmt = stmt;
        this.statement = statement;
    }

    public MappedStatement getStatement() {
        return statement;
    }

    public ResultSetContext setStatement(MappedStatement statement) {
        this.statement = statement;
        return this;
    }

    public Statement getStmt() {
        return stmt;
    }

    public ResultSetContext setStmt(Statement stmt) {
        this.stmt = stmt;
        return this;
    }
}
