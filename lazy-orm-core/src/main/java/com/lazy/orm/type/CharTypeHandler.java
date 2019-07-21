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
public class CharTypeHandler extends AbstractTypeHandler<Character> {

    @Override
    public void doSetParameter(PreparedStatement ps, int i, Character parameter) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public Character doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName) == null ? null : rs.getString(columnName).charAt(0);
    }
}
