package com.lazy.orm.example.dao;

import com.lazy.orm.annotation.Mapper;
import com.lazy.orm.annotation.Param;
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

}
