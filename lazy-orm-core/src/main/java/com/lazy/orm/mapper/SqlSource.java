package com.lazy.orm.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Sql解析源数据
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class SqlSource {

    private String sql;
    private Map<Integer, String> idxName = new HashMap<>();


    public String getSql() {
        return sql;
    }

    public SqlSource setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public Map<Integer, String> getIdxName() {
        return idxName;
    }

    public SqlSource setIdxName(Map<Integer, String> idxName) {
        this.idxName = idxName;
        return this;
    }
}
