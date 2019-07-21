package com.lazy.orm.mapper;

import com.lazy.orm.annotation.DmlType;

/**
 * <p>
 * Mapper语句类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class MappedStatement {

    private String id;

    private SqlSource sqlSource;

    private ParameterMap parameterMap;

    private ResultMap resultMap;

    private DmlType dmlType;

    public DmlType getDmlType() {
        return dmlType;
    }

    public MappedStatement setDmlType(DmlType dmlType) {
        this.dmlType = dmlType;
        return this;
    }

    public String getId() {
        return id;
    }

    public MappedStatement setId(String id) {
        this.id = id;
        return this;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public MappedStatement setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
        return this;
    }

    public ParameterMap getParameterMap() {
        return parameterMap;
    }

    public MappedStatement setParameterMap(ParameterMap parameterMap) {
        this.parameterMap = parameterMap;
        return this;
    }

    public ResultMap getResultMap() {
        return resultMap;
    }

    public MappedStatement setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
        return this;
    }
}
