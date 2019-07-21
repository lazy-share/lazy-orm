package com.lazy.orm.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 列注解
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    String value();

}
