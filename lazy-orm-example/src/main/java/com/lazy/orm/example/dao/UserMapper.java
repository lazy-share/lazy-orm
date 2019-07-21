package com.lazy.orm.example.dao;

import com.lazy.orm.annotation.Mapper;
import com.lazy.orm.annotation.Sql;
import com.lazy.orm.example.entity.UserEntity;

import java.util.List;

/**
 * <p>
 * 用户Mapper
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
@Mapper
public interface UserMapper {

    /**
     * 根据条件查询数据
     *
     * @param name   名称
     * @param age    年龄
     * @param offset 偏移
     * @param limit  条数
     * @return 结果集合
     */
    @Sql(
            value = "select id, name from user " +
                    "where name like '%${name}%' " +
                    "and age > ${age} " +
                    "order by age desc " +
                    "limit ${offset}, ${limit}",
            returnType = UserEntity.class
    )
    List<UserEntity> selectByCondition(String name, int age, int offset, int limit);


    /**
     * 根据主键查询数据
     *
     * @param pk 主键
     * @return 数据
     */
    @Sql(
            value = "select * from user where id = ':pk'"
    )
    UserEntity selectByPk(String pk);

}
