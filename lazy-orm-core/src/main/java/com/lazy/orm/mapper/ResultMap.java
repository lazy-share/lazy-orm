package com.lazy.orm.mapper;

import com.lazy.orm.type.TypeHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 返回结果映射
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class ResultMap {

    private Map<String, TypeHandler> resultHandler = new HashMap<>();
    private Map<String, Method> resultVal = new HashMap<>();
    private Class<?> returnType;

    public Map<String, TypeHandler> getResultHandler() {
        return resultHandler;
    }

    public ResultMap setResultHandler(Map<String, TypeHandler> resultHandler) {
        this.resultHandler = resultHandler;
        return this;
    }

    public Map<String, Method> getResultVal() {
        return resultVal;
    }

    public ResultMap setResultVal(Map<String, Method> resultVal) {
        this.resultVal = resultVal;
        return this;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public ResultMap setReturnType(Class<?> returnType) {
        this.returnType = returnType;
        return this;
    }
}
