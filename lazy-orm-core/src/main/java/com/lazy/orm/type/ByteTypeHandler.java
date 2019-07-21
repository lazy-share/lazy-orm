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
public class ByteTypeHandler extends AbstractTypeHandler<Byte> {

    @Override
    public void doSetParameter(PreparedStatement ps, int i, Byte parameter) throws SQLException {
        ps.setByte(i, parameter);
    }

    @Override
    public Byte doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getByte(columnName);
    }
}
