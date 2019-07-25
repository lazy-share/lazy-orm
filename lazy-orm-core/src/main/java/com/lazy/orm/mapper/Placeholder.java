package com.lazy.orm.mapper;

/**
 * <p>
 * 占位符对象
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class Placeholder {

    private String name;
    private Integer symbolIdx;
    private boolean dynamic = false;
    private Integer charIdx;
    private boolean like = false;
    private boolean in = false;

    public boolean isDynamic() {
        return dynamic;
    }

    public Placeholder setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
        return this;
    }

    public boolean isIn() {
        return in;
    }

    public Placeholder setIn(boolean in) {
        this.in = in;
        return this;
    }

    public String getName() {
        return name;
    }

    public Placeholder setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSymbolIdx() {
        return symbolIdx;
    }

    public Placeholder setSymbolIdx(Integer symbolIdx) {
        this.symbolIdx = symbolIdx;
        return this;
    }

    public Integer getCharIdx() {
        return charIdx;
    }

    public Placeholder setCharIdx(Integer charIdx) {
        this.charIdx = charIdx;
        return this;
    }

    public boolean isLike() {
        return like;
    }

    public Placeholder setLike(boolean like) {
        this.like = like;
        return this;
    }
}
