package com.lazy.orm.parser.annotation;

import com.lazy.orm.annotation.Column;
import com.lazy.orm.annotation.Sql;
import com.lazy.orm.mapper.ResultMap;
import com.lazy.orm.parser.support.AbstractResultMapParser;
import com.lazy.orm.type.TypeHandlerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

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

        ResultMap resultMap = new ResultMap();
        Sql sql = method.getAnnotation(Sql.class);
        Class<?> returnType = sql.itemType().equals(Collection.class) ?
                method.getReturnType() : sql.itemType();
        resultMap.setReturnType(returnType);
        resultMap.setItemType(sql.itemType());
        if (returnType.equals(Void.class)) {
            return resultMap;
        }
        Field[] fields = returnType.getDeclaredFields();

        int idx = 0;
        for (Field field : fields) {
            ResultMap.ResultMeta resultMeta = new ResultMap.ResultMeta();
            String fieldName = field.getName();
            String columnName = fieldName;
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                columnName = column.value();
            }
            resultMeta
                    .setName(fieldName)
                    .setColumn(columnName)
                    .setIdx(idx++)
                    .setTypeHandler(TypeHandlerFactory.of(field.getType()));
            resultMap.addMeta(columnName, resultMeta);
        }

        return resultMap;

    }
}
