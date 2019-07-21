package com.lazy.orm.handler;

/**
 * <p>
 * 参数值处理器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public interface ParameterValHandler {


    Object getVal(Object parameter, String fieldName);

}
