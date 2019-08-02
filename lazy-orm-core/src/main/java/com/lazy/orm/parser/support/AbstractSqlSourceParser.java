package com.lazy.orm.parser.support;

import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.parser.SqlSourceParser;

import java.lang.reflect.Method;

/**
 * <p>
 * 抽象Sql解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractSqlSourceParser implements SqlSourceParser {


    @Override
    public SqlSource parserStatic(Method method) {
        return this.doParser(method);
    }

    protected abstract SqlSource doParser(Method method);
}
