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
public class StringTypeHandler extends AbstractTypeHandler<String> {

    @Override
    public void doSetParameter(PreparedStatement ps, int i, String parameter) throws SQLException {
        ps.setString(i, parameter);
    }

    @Override
    public String doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }
}
