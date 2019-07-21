package com.lazy.orm.type.support;

import com.lazy.orm.type.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 *
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public abstract class AbstractTypeHandler<T> implements TypeHandler<T> {


    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter) throws SQLException {
        this.doSetParameter(ps, i, parameter);
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        return this.doGetResult(rs, columnName);
    }

    protected abstract void doSetParameter(PreparedStatement ps, int i, T parameter) throws SQLException;

    protected abstract T doGetResult(ResultSet rs, String columnName) throws SQLException;
}
