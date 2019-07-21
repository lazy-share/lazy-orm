package com.lazy.orm.type;

import com.lazy.orm.type.support.AbstractTypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class DateTypeHandler extends AbstractTypeHandler<Date> {

    @Override
    public void doSetParameter(PreparedStatement ps, int i, Date parameter) throws SQLException {
        ps.setDate(i, (java.sql.Date) parameter);
    }

    @Override
    public Date doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getDate(columnName);
    }
}
