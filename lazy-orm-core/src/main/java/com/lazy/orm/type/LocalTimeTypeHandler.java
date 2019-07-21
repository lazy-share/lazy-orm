package com.lazy.orm.type;

import com.lazy.orm.type.support.AbstractTypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class LocalTimeTypeHandler extends AbstractTypeHandler<LocalDate> {


    @Override
    public void doSetParameter(PreparedStatement ps, int i, LocalDate parameter) throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public LocalDate doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName, LocalDate.class);
    }
}
