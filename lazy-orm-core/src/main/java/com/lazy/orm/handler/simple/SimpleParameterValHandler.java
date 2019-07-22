package com.lazy.orm.handler.simple;

import com.lazy.orm.handler.support.AbstractParameterValHandler;
import com.lazy.orm.util.ReflectionUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

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

        Class<?> theCls = parameter.getClass();
        if (String.class.equals(theCls)
                || Integer.class.equals(theCls)
                || Long.class.equals(theCls)
                || BigDecimal.class.equals(theCls)
                || Byte.class.equals(theCls)
                || Time.class.equals(theCls)
                || Timestamp.class.equals(theCls)
                || Date.class.equals(theCls)
                || java.util.Date.class.equals(theCls)
                || LocalDateTime.class.equals(theCls)
                || LocalDate.class.equals(theCls)
                || ArrayList.class.equals(theCls)
                || (LinkedList.class.equals(theCls))
                || (HashSet.class.equals(theCls))
                || (TreeSet.class.equals(theCls))) {

            return parameter;
        } else {

            return ReflectionUtil.getValueByFieldName(fieldName, theCls, parameter);
        }
    }
}
