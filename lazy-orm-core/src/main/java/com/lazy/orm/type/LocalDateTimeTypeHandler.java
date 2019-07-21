package com.lazy.orm.type;

import com.lazy.orm.type.support.AbstractTypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class LocalDateTimeTypeHandler extends AbstractTypeHandler<LocalDateTime> {
    @Override
    public void doSetParameter(PreparedStatement ps, int i, LocalDateTime parameter) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public LocalDateTime doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName, LocalDateTime.class);
    }
}
