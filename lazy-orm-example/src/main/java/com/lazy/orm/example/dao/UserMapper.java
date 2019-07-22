package com.lazy.orm.example.dao;

import com.lazy.orm.annotation.DmlType;
import com.lazy.orm.annotation.Mapper;
import com.lazy.orm.annotation.Param;
import com.lazy.orm.annotation.Sql;
import com.lazy.orm.example.dto.QueryUserDto;
import com.lazy.orm.example.entity.UserEntity;

import java.sql.Timestamp;
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
            value = "select id, name from t_user " +
                    "where name like #{name} " +
                    "and age > #{age} " +
                    "order by age desc " +
                    "limit #{offset}, #{limit}",
            itemType = UserEntity.class
    )
    List<UserEntity> selectByCondition(@Param("name") String name,
                                       @Param("age") int age,
                                       @Param("offset") int offset,
                                       @Param("limit") int limit);


    /**
     * 根据主键查询数据
     *
     * @param pk 主键
     * @return 数据
     */
    @Sql(
            value = "select * from t_user where id = #{pk}"
    )
    UserEntity selectByPk(@Param("pk") Long pk);


    @Sql(
            value = "select count(1) from t_user where name like #{name}"
    )
    long count(@Param("name") String name);

    /**
     * 插入一条数据
     *
     * @param entity 数据实体对象
     * @return 成功条数
     */
    @Sql(
            value = "insert into t_user (id, name, age, salary, create_time) values (" +
                    "#{id}, #{name}, #{age}, #{salary}, #{createTime})",
            dmlType = DmlType.INSERT
    )
    int insert(UserEntity entity);

    @Sql(
            value = "delete from t_user",
            dmlType = DmlType.DELETE
    )
    int deleteAll();


    @Sql(
            value = "select name, create_time, id from t_user where name like #{name} and id in (#{ids}) and age < #{age}",
            itemType = UserEntity.class
    )
    List<UserEntity> findByDto(QueryUserDto dto);

    @Sql(
            value = "select name, create_time, id from t_user where name like #{name} and id in (#{ids}) and age < #{age}",
            itemType = UserEntity.class
    )
    List<UserEntity> findByParams(@Param("ids") List<Long> ids,
                                  @Param("name") String name,
                                  @Param("age") int age);


    @Sql(
            value = "update t_user set create_time = #{createTime} where id in (#{ids}) and name like #{name} and age < #{age}",
            dmlType = DmlType.UPDATE
    )
    int updateByDto(QueryUserDto dto);

    @Sql(
            value = "update t_user set create_time = #{createTime} where id in (#{ids}) and name like #{name} and age < #{age}",
            dmlType = DmlType.UPDATE
    )
    int updateByParams(@Param("ids") List<Long> ids,
                       @Param("name") String name,
                       @Param("createTime") Timestamp createTime,
                       @Param("age") int age);


    /**
     * 更新一条数据
     *
     * @param entity 更新数据实体参数
     * @return 成功条数
     */
    @Sql(
            value = "update t_user set name = #{name} where id = #{id}",
            dmlType = DmlType.UPDATE
    )
    int update(UserEntity entity);

    /**
     * 根据ID删除一条数据
     *
     * @param id 主键
     * @return 成功条数
     */
    @Sql(
            value = "delete from t_user where id = #{id}",
            dmlType = DmlType.DELETE
    )
    int delete(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param ids 主键，逗号分隔
     * @return 成功条数
     */
    @Sql(
            value = "delete from t_user where id in (#{ids})",
            dmlType = DmlType.DELETE
    )
    int batDel(@Param("ids") List<Long> ids);

}
