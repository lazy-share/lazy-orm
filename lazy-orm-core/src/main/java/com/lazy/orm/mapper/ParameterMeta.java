package com.lazy.orm.mapper;

import com.lazy.orm.type.TypeHandler;

/**
 * <p>
 *     参数元数据
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class ParameterMeta {

    private String name;
    private TypeHandler typeHandler;
    private Class<?> type;
    private Object val;
    private Integer paramIdx;
    private Placeholder placeholder;

    public Integer getParamIdx() {
        return paramIdx;
    }

    public ParameterMeta setParamIdx(Integer paramIdx) {
        this.paramIdx = paramIdx;
        return this;
    }

    public String getName() {
        return name;
    }

    public ParameterMeta setName(String name) {
        this.name = name;
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

    public Object getVal() {
        return val;
    }

    public ParameterMeta setVal(Object val) {
        this.val = val;
        return this;
    }

    public Placeholder getPlaceholder() {
        return placeholder;
    }

    public ParameterMeta setPlaceholder(Placeholder placeholder) {
        this.placeholder = placeholder;
        return this;
    }
}
