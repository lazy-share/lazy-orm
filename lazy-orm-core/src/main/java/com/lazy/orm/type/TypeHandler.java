package com.lazy.orm.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * 类型处理器接口
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public interface TypeHandler<T> {

    @SuppressWarnings("unchecked")
    void setParameter(PreparedStatement ps, int i, T parameter) throws SQLException;

    T getResult(ResultSet rs, String columnName) throws SQLException;

}
