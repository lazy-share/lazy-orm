package com.lazy.orm.type;

import com.lazy.orm.type.support.AbstractTypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class TimestampTypeHandler extends AbstractTypeHandler<Timestamp> {
    @Override
    public void doSetParameter(PreparedStatement ps, int i, Timestamp parameter) throws SQLException {
        ps.setTimestamp(i, parameter);
    }

    @Override
    public Timestamp doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getTimestamp(columnName);
    }
}
