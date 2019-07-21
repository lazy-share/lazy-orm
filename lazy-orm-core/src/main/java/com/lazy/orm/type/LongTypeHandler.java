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
public class LongTypeHandler extends AbstractTypeHandler<Long> {


    @Override
    public void doSetParameter(PreparedStatement ps, int i, Long parameter) throws SQLException {
        ps.setLong(i, parameter);
    }

    @Override
    public Long doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }
}
