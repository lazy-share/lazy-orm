package com.lazy.orm.parser.annotation;

import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.parser.support.AbstractParameterMapParser;

import java.lang.reflect.Method;

/**
 * <p>
 * 注解参数映射解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class AnnotationParameterMapParser extends AbstractParameterMapParser {


    @Override
    protected ParameterMap doParser(Method method) {
        return null;
    }
}
