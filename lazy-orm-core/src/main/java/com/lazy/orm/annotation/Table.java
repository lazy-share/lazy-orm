package com.lazy.orm.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *     表注解
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/23.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    String value();

}
