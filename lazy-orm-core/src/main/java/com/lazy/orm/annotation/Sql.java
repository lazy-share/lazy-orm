package com.lazy.orm.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * Sql 注解
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sql {

    String value();

    Class<?> returnType() default Object.class;

}
