package com.lazy.orm.parser;

import com.lazy.orm.mapper.ResultMap;

import java.lang.reflect.Method;

/**
 * <p>
 * 结果映射解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public interface ResultMapParser {

    ResultMap parser(Method method);

}
