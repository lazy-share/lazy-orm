package com.lazy.orm.parser;

import com.lazy.orm.mapper.SqlSource;

import java.lang.reflect.Method;

/**
 * <p>
 * SqlSource对象解析
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public interface SqlSourceParser {

    SqlSource parser(Method method);

}
