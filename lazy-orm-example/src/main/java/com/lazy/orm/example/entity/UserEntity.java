package com.lazy.orm.example.entity;

import com.lazy.orm.annotation.Column;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>
 * 用户实体
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public class UserEntity {

    @Column("name")
    private String name;
    @Column("salary")
    private BigDecimal salary;
    @Column("age")
    private Integer age;
    @Column("id")
    private Long id;
    @Column("create_time")
    private Timestamp createTime;


    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public UserEntity setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }


    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public UserEntity setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }
}
