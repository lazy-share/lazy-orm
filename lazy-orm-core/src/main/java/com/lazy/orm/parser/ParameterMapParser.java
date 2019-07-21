package com.lazy.orm.parser;

import com.lazy.orm.mapper.ParameterMap;

import java.lang.reflect.Method;

/**
 * <p>
 * 参数映射解析器接口
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public interface ParameterMapParser {

    ParameterMap parser(Method method);
}
