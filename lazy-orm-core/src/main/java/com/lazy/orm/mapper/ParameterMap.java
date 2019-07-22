package com.lazy.orm.mapper;

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

    private Map<String, ParameterMeta> parameterMetas = new HashMap<>();

    public void addParameterMeta(String name, ParameterMeta meta) {
        parameterMetas.put(name, meta);
    }

    public ParameterMeta getParameterMeta(String name) {
        return parameterMetas.get(name);
    }

    public Map<String, ParameterMeta> getParameterMetas() {
        return parameterMetas;
    }

    public ParameterMap setParameterMetas(Map<String, ParameterMeta> parameterMetas) {
        this.parameterMetas = parameterMetas;
        return this;
    }
}
