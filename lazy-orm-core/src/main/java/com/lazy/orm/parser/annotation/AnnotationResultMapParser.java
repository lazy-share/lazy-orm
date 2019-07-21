package com.lazy.orm.parser.annotation;

import com.lazy.orm.mapper.ResultMap;
import com.lazy.orm.parser.support.AbstractResultMapParser;

import java.lang.reflect.Method;

/**
 * <p>
 * 注解结果映射解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class AnnotationResultMapParser extends AbstractResultMapParser {


    @Override
    protected ResultMap doParser(Method method) {
        return null;
    }
}
