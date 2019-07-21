package com.lazy.orm.type;

import com.lazy.orm.type.support.AbstractTypeHandler;

import java.math.BigDecimal;
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
public class BigDecimalTypeHandler extends AbstractTypeHandler<BigDecimal> {


    @Override
    public void doSetParameter(PreparedStatement ps, int i, BigDecimal parameter) throws SQLException {
        ps.setBigDecimal(i, parameter);
    }

    @Override
    public BigDecimal doGetResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getBigDecimal(columnName);
    }
}
