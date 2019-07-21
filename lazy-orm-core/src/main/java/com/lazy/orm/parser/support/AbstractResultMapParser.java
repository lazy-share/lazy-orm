package com.lazy.orm.parser.support;

import com.lazy.orm.mapper.ResultMap;
import com.lazy.orm.parser.ResultMapParser;

import java.lang.reflect.Method;

/**
 * <p>
 * 抽象结果映射解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractResultMapParser implements ResultMapParser {


    @Override
    public ResultMap parser(Method method) {
        return this.doParser(method);
    }

    protected abstract ResultMap doParser(Method method);
}
