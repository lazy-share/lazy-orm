package com.lazy.orm.mapper;

import com.lazy.orm.type.TypeHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 参数映射
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class ParameterMap {

    private Map<String, TypeHandler> paramHandler = new HashMap<>();
    private Map<String, Object> paramVal = new HashMap<>();

    public Map<String, TypeHandler> getParamHandler() {
        return paramHandler;
    }

    public ParameterMap setParamHandler(Map<String, TypeHandler> paramHandler) {
        this.paramHandler = paramHandler;
        return this;
    }

    public Map<String, Object> getParamVal() {
        return paramVal;
    }

    public ParameterMap setParamVal(Map<String, Object> paramVal) {
        this.paramVal = paramVal;
        return this;
    }
}
