package com.lazy.orm.parser.annotation;

import com.lazy.orm.annotation.Param;
import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.mapper.ParameterMeta;
import com.lazy.orm.mapper.Placeholder;
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

                this.parserObjectParameter(parameterMap, method.getParameterTypes()[pIdx - 1]);
            } else {

                String name = param.value();
                Placeholder placeholder = sqlSource.getPlaceholder(name);
                parameterMap.addParameterMeta(name,
                        new ParameterMeta()
                                .setName(name)
                                .setType(parameter.getType())
                                .setPlaceholder(placeholder)
                                .setParamIdx(pIdx++)
                                .setTypeHandler(TypeHandlerFactory.of(parameter.getType())));
            }
        }
        return parameterMap;
    }


    private void parserObjectParameter(ParameterMap parameterMap, Class cls) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            Placeholder placeholder = sqlSource.getPlaceholder(field.getName());
            if (placeholder == null) {
                continue;
            }
            parameterMap.addParameterMeta(placeholder.getName(),
                    new ParameterMeta()
                            .setName(placeholder.getName())
                            .setType(cls)
                            .setParamIdx(1)
                            .setPlaceholder(placeholder)
                            .setTypeHandler(TypeHandlerFactory.of(cls)));
        }

    }

}
