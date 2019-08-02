package com.lazy.orm.parser;

import com.lazy.orm.mapper.ParameterMeta;
import com.lazy.orm.mapper.SqlSource;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>
 * SqlSource对象解析
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public interface SqlSourceParser {

    SqlSource parserStatic(Method method);

    boolean parserIn(SqlSource sqlSource,
                     Map<String, ParameterMeta> parameterMetas,
                     Map<String, ParameterMeta> finalParameterMetas,
                     ParameterMeta meta,
                     Object parameterVal);

    boolean parserDynamic(SqlSource sqlSource,
                          Map<String, ParameterMeta> parameterMetas,
                          ParameterMeta meta,
                          Object parameterVal);

    void parserLike(Object parameterVal, ParameterMeta mete);

}
