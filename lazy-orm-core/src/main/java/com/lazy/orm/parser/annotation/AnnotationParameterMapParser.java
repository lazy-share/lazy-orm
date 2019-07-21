package com.lazy.orm.parser.annotation;

import com.lazy.orm.annotation.Param;
import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.parser.support.AbstractParameterMapParser;
import com.lazy.orm.type.TypeHandlerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * <p>
 * 注解参数映射解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class AnnotationParameterMapParser extends AbstractParameterMapParser {

    public AnnotationParameterMapParser(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    @Override
    protected ParameterMap doParser(Method method) {
        Parameter[] parameters = method.getParameters();
        ParameterMap parameterMap = new ParameterMap();

        int pIdx = 1;
        for (Parameter parameter : parameters) {
            Param param = parameter.getAnnotation(Param.class);
            if (param == null) {
                this.parserObjectParameter(parameterMap, parameter);
                continue;
            }
            String name = param.value();
            SqlSource.Placeholder placeholder = sqlSource.getPlaceholder(name);
            parameterMap.addParameterMeta(name,
                    new ParameterMap.ParameterMeta()
                            .setPlaceholderIdx(placeholder.getIdx())
                            .setParamIdx(pIdx++)
                            .setName(name)
                            .setLike(placeholder.isLike())
                            .setType(parameter.getType())
                            .setTypeHandler(TypeHandlerFactory.of(parameter.getType())));
        }
        return parameterMap;
    }


    private void parserObjectParameter(ParameterMap parameterMap, Parameter parameter) {
        Class<?> pClass = parameter.getClass();
        Field[] fields = pClass.getDeclaredFields();
        for (Field field : fields) {
            SqlSource.Placeholder p = sqlSource.getPlaceholder(field.getName());
            if (p == null) {
                continue;
            }
            parameterMap.addParameterMeta(p.getName(),
                    new ParameterMap.ParameterMeta()
                            .setPlaceholderIdx(p.getIdx())
                            .setParamIdx(1)
                            .setName(p.getName())
                            .setLike(p.isLike())
                            .setType(parameter.getType())
                            .setTypeHandler(TypeHandlerFactory.of(parameter.getType())));
        }

    }

}
