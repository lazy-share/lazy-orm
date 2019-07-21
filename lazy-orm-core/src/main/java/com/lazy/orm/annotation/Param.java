package com.lazy.orm.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 参数注解
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {

    String value();

}
