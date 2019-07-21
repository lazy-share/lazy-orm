package com.lazy.orm.mapper;

import com.lazy.orm.type.TypeHandler;

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

    public static class ResultMeta {
        private String name;
        private String column;
        private TypeHandler typeHandler;
        private Integer idx;

        public Integer getIdx() {
            return idx;
        }

        public ResultMeta setIdx(Integer idx) {
            this.idx = idx;
            return this;
        }

        public String getColumn() {
            return column;
        }

        public ResultMeta setColumn(String column) {
            this.column = column;
            return this;
        }

        public String getName() {
            return name;
        }

        public ResultMeta setName(String name) {
            this.name = name;
            return this;
        }

        public TypeHandler getTypeHandler() {
            return typeHandler;
        }

        public ResultMeta setTypeHandler(TypeHandler typeHandler) {
            this.typeHandler = typeHandler;
            return this;
        }
    }

    private Map<String, ResultMeta> resultMetas = new HashMap<>();

    private Class<?> returnType;

    private Class<?> itemType;

    public Class<?> getItemType() {
        return itemType;
    }

    public ResultMap setItemType(Class<?> itemType) {
        this.itemType = itemType;
        return this;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public ResultMap setReturnType(Class<?> returnType) {
        this.returnType = returnType;
        return this;
    }

    public void addMeta(String name, ResultMeta meta) {
        resultMetas.put(name, meta);
    }

    public ResultMeta getMeta(String name) {
        return resultMetas.get(name);
    }

    public Map<String, ResultMeta> getResultMetas() {
        return resultMetas;
    }
}
