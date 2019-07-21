package com.lazy.orm.handler.simple;

import com.lazy.orm.handler.support.AbstractParameterValHandler;
import com.lazy.orm.util.ReflectionUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 简单获取参数值处理器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class SimpleParameterValHandler extends AbstractParameterValHandler {

    @Override
    protected Object doGetVal(Object parameter, String fieldName) {
        if (String.class.equals(parameter.getClass())
                || Integer.class.equals(parameter.getClass())
                || Long.class.equals(parameter.getClass())
                || BigDecimal.class.equals(parameter.getClass())
                || Byte.class.equals(parameter.getClass())
                || Time.class.equals(parameter.getClass())
                || Timestamp.class.equals(parameter.getClass())
                || Date.class.equals(parameter.getClass())
                || java.util.Date.class.equals(parameter.getClass())
                || LocalDateTime.class.equals(parameter.getClass())
                || LocalDate.class.equals(parameter.getClass())
        ) {
            return parameter;
        }
        return ReflectionUtil.getValueByFieldName(fieldName, parameter.getClass(), parameter);
    }
}
