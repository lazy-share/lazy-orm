package com.lazy.orm.parser;

import com.lazy.orm.mapper.MappedStatement;

import java.lang.reflect.Method;

/**
 * <p>
 * Sql 解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public interface MappedStatementParser {


    MappedStatement parser(Method method);

}
