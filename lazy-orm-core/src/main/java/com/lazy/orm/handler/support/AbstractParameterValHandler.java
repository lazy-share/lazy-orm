package com.lazy.orm.handler.support;

import com.lazy.orm.handler.ParameterValHandler;

/**
 * <p>
 * 抽象参数值
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public abstract class AbstractParameterValHandler implements ParameterValHandler {


    @Override
    public Object getVal(Object parameter, String fieldName) {
        return this.doGetVal(parameter, fieldName);
    }

    protected abstract Object doGetVal(Object parameter, String fieldName);
}
