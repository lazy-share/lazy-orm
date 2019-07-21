package com.lazy.orm.parser.support;

import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.parser.ParameterMapParser;

import java.lang.reflect.Method;

/**
 * <p>
 * 抽象参数映射解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractParameterMapParser implements ParameterMapParser {


    @Override
    public ParameterMap parser(Method method) {
        return this.doParser(method);
    }

    protected abstract ParameterMap doParser(Method method);
}
