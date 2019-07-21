package com.lazy.orm.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p>
 * 类型处理器工厂类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class TypeHandlerFactory {


    public static TypeHandler of(Class<?> cls) {

        if (String.class.equals(cls)) {
            return new StringTypeHandler();
        } else if (Integer.class.equals(cls) || int.class.equals(cls)) {
            return new IntegerTypeHandler();
        } else if (BigDecimal.class.equals(cls)) {
            return new BigDecimalTypeHandler();
        } else if (Byte.class.equals(cls) || byte.class.equals(cls)) {
            return new ByteTypeHandler();
        } else if (Character.class.equals(cls) || char.class.equals(cls)) {
            return new CharTypeHandler();
        } else if (Date.class.equals(cls)) {
            return new DateTypeHandler();
        } else if (java.sql.Date.class.equals(cls)) {
            return new SqlDateTypeHandler();
        } else if (LocalDateTime.class.equals(cls)) {
            return new LocalDateTimeTypeHandler();
        } else if (LocalTime.class.equals(cls)) {
            return new LocalTimeTypeHandler();
        } else if (Long.class.equals(cls) || long.class.equals(cls)) {
            return new LongTypeHandler();
        } else if (Timestamp.class.equals(cls)) {
            return new TimestampTypeHandler();
        } else {
            return new ObjectTypeHandler();
        }
    }

}
