package com.lazy.orm.type;

import com.lazy.orm.type.support.AbstractTypeHandler;

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
public class ObjectTypeHandler extends AbstractTypeHandler<Object> {
    @Override
    public void doSetParameter(PreparedStatement ps, int i, Object parameter) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public Object doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName);
    }
}
