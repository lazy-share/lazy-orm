package com.lazy.orm.mapper;

/**
 * <p>
 * Mapper语句类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class MappedStatement {

    private SqlSource sqlSource;

    private ParameterMap parameterMap;

    private ResultMap resultMap;

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
