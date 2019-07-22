package com.lazy.orm.example.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 * 查询用户DTO
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class QueryUserDto {

    private String name;
    private int age;
    private List<Long> ids;
    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public QueryUserDto setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getName() {
        return name;
    }

    public QueryUserDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public QueryUserDto setAge(int age) {
        this.age = age;
        return this;
    }

    public List<Long> getIds() {
        return ids;
    }

    public QueryUserDto setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }
}
