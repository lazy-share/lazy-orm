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

    public static class ParameterMeta {
        private String name;
        private Integer placeholderIdx;
        private Integer paramIdx;
        private TypeHandler typeHandler;
        private Class<?> type;
        private boolean like;

        public boolean isLike() {
            return like;
        }

        public ParameterMeta setLike(boolean like) {
            this.like = like;
            return this;
        }

        public String getName() {
            return name;
        }

        public ParameterMeta setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getPlaceholderIdx() {
            return placeholderIdx;
        }

        public ParameterMeta setPlaceholderIdx(Integer placeholderIdx) {
            this.placeholderIdx = placeholderIdx;
            return this;
        }

        public Integer getParamIdx() {
            return paramIdx;
        }

        public ParameterMeta setParamIdx(Integer paramIdx) {
            this.paramIdx = paramIdx;
            return this;
        }

        public TypeHandler getTypeHandler() {
            return typeHandler;
        }

        public ParameterMeta setTypeHandler(TypeHandler typeHandler) {
            this.typeHandler = typeHandler;
            return this;
        }

        public Class<?> getType() {
            return type;
        }

        public ParameterMeta setType(Class<?> type) {
            this.type = type;
            return this;
        }
    }

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
}
