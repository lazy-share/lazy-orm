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
public class IntegerTypeHandler extends AbstractTypeHandler<Integer> {
    @Override
    public void doSetParameter(PreparedStatement ps, int i, Integer parameter) throws SQLException {
        ps.setInt(i, parameter);
    }

    @Override
    public Integer doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName);
    }
}
