package com.lazy.orm.mapper;

import com.lazy.orm.annotation.DmlType;

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
    private DmlType dmlType;
    private Map<String, Placeholder> placeholder = new HashMap<>();

    public void putPlaceholder(String name, Placeholder p) {
        placeholder.put(name, p);
    }

    public Placeholder getPlaceholder(String name) {
        return placeholder.get(name);
    }

    public DmlType getDmlType() {
        return dmlType;
    }

    public SqlSource setDmlType(DmlType dmlType) {
        this.dmlType = dmlType;
        return this;
    }

    public String getSql() {
        return sql;
    }

    public SqlSource setSql(String sql) {
        this.sql = sql;
        return this;
    }

}
