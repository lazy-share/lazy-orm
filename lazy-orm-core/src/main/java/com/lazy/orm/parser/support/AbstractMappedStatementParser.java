package com.lazy.orm.parser.support;

import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.parser.MappedStatementParser;
import com.lazy.orm.session.Configuration;

import java.lang.reflect.Method;

/**
 * <p>
 * 抽象MapperStatement解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractMappedStatementParser implements MappedStatementParser {

    protected Configuration configuration;
    protected Class<?> type;

    @Override
    public MappedStatement parser(Method method) {
        return this.doParser(method);
    }

    protected abstract MappedStatement doParser(Method method);
}
