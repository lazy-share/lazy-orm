## 自研精简版Mybatis ORM框架

### 使用方式

#### 定义实体类
###### 任何POJO类都可以作为实体类
```$xslt
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

    ...省略getter setter
}

```

#### 定义Mapper接口类

```$xslt
package com.lazy.orm.example.dao;

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
            value = "select * from t_user where 1=1 order by ${order1} ${sort1}, ${order2} ${sort2}",
            itemType = UserEntity.class
    )
    List<UserEntity> selectByOrderBy(@Param("order1") String order1,
                                     @Param("sort1") String sort1,
                                     @Param("order2") String order2,
                                     @Param("sort2") String sort2);


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
                    "#{id}, #{name}, #{age}, #{salary}, #{createTime})"
    )
    int insert(UserEntity entity);

    @Sql(
            value = "delete from t_user"
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
            value = "update t_user set create_time = #{createTime} where id in (#{ids}) and name like #{name} and age < #{age}"
    )
    int updateByDto(QueryUserDto dto);

    @Sql(
            value = "update t_user set create_time = #{createTime} where id in (#{ids}) and name like #{name} and age < #{age}"
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
            value = "update t_user set name = #{name} where id = #{id}"
    )
    int update(UserEntity entity);

    /**
     * 根据ID删除一条数据
     *
     * @param id 主键
     * @return 成功条数
     */
    @Sql(
            value = "delete from t_user where id = #{id}"
    )
    int delete(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param ids 主键，逗号分隔
     * @return 成功条数
     */
    @Sql(
            value = "delete from t_user where id in (#{ids})"
    )
    int batDel(@Param("ids") List<Long> ids);

}



```

#### 编写单元测试类
```$xslt

package com.lazy.orm.example;

import com.alibaba.fastjson.JSON;
import com.lazy.orm.example.dao.UserMapper;
import com.lazy.orm.example.entity.UserEntity;
import com.lazy.orm.io.Resources;
import com.lazy.orm.session.Configuration;
import com.lazy.orm.session.SqlSession;
import com.lazy.orm.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit test for simple App.
 */
public class UserMapperTest {

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //数据源获取
        Properties props = Resources.getResourceAsProperties("lazyorm.properties");

        //Sql会话
        Configuration configuration = new Configuration(props);

        sqlSession = SqlSessionFactoryBuilder.build(configuration).openSession();
    }

    @Test
    public void testOrderBy() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<UserEntity> userEntityList = userMapper.selectByOrderBy(
                "id", "desc", "age", "asc"
        );
        System.out.println(userEntityList.size());
        System.out.println(JSON.toJSONString(userEntityList));
    }

    @Test
    public void testBatDelete() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        int count = userMapper.batDel(ids);

        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testDelete() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.delete(15L);

        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testUpdate() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.update(
                new UserEntity()
                        .setAge(16)
                        .setSalary(new BigDecimal("1002"))
                        .setCreateTime(new Timestamp(System.currentTimeMillis()))
                        .setId(10L)
                        .setName("lisi")
        );

        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testInsert() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int count = userMapper.insert(
                new UserEntity()
                        .setAge(100)
                        .setSalary(new BigDecimal("3"))
                        .setCreateTime(new Timestamp(System.currentTimeMillis()))
                        .setId((long) (Math.random() * 10000000))
                        .setName("wangnwu")
        );

        sqlSession.commit();
        System.out.println(count);

//        UserEntity userEntity = userMapper.selectByPk(10L);
//        System.out.println(JSON.toJSONString(userEntity));
    }

    @Test
    public void testUpdate2() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Long> ids = new ArrayList<>();
        int count = 30000;
        while (count-- > 0) {
            ids.add((long) count);
        }
//        long success = userMapper.updateByDto(
//                new QueryUserDto()
//                .setIds(ids)
//                .setName("lazy")
//                .setAge(30000)
//                .setCreateTime(new Timestamp(System.currentTimeMillis() - 100000))
//        );

        long success = userMapper.updateByParams(
                ids, "lazy", new Timestamp(System.currentTimeMillis() - 1000000000), 30000
        );

        sqlSession.commit();
        System.out.println(success);
    }

    @Test
    public void testSelect2() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Long> ids = new ArrayList<>();
        int count = 300000;
        while (count-- > 0) {
            ids.add((long) count);
        }
//        List<UserEntity> userEntities = userMapper.findByDto(new QueryUserDto()
//                .setAge(100000)
//                .setName("lazy")
//                .setIds(ids)
//        );

        List<UserEntity> userEntities = userMapper.findByParams(ids, "lazy", 3000000);

        System.out.println(JSON.toJSONString(userEntities));
        System.out.println(userEntities.size());
//        System.out.println(JSON.toJSONString(userEntities));
    }


    @Test
    public void testSelect() throws IOException {
        //执行Sql
        UserEntity userEntity = sqlSession.execute("com.lazy.orm.example.dao.UserMapper.selectByPk", 2L);
        System.out.println(JSON.toJSONString(userEntity));

        //注解sql
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<UserEntity> userEntityList = userMapper.selectByCondition("lazy", 20, 0, 10);
//        assert userEntityList.size() > 0;
        System.out.println(JSON.toJSONString(userEntityList));
//
        userEntity = userMapper.selectByPk(1L);
        System.out.println(JSON.toJSONString(userEntity));
    }

    @Test
    public void initTestData() throws InterruptedException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteAll();
        AtomicInteger count = new AtomicInteger(0);
        new Thread(() -> {
            while (count.getAndAdd(1) < 1000000) {
                userMapper.insert(
                        new UserEntity()
                                .setAge(count.get())
                                .setSalary(new BigDecimal(String.valueOf(count.get())))
                                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                .setId((long) count.get())
                                .setName("lazy" + count.get())
                );
            }
            sqlSession.commit();
        }).start();

        new Thread(() -> {
            while (count.getAndAdd(1) < 1000000) {
                userMapper.insert(
                        new UserEntity()
                                .setAge(count.get())
                                .setSalary(new BigDecimal(String.valueOf(count.get())))
                                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                .setId((long) count.get())
                                .setName("lazy" + count.get())
                );
            }
            sqlSession.commit();
        }).start();

        new Thread(() -> {
            while (count.getAndAdd(1) < 1000000) {
                userMapper.insert(
                        new UserEntity()
                                .setAge(count.get())
                                .setSalary(new BigDecimal(String.valueOf(count.get())))
                                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                .setId((long) count.get())
                                .setName("lazy" + count.get())
                );
            }
            sqlSession.commit();
        }).start();

        new Thread(() -> {
            while (count.getAndAdd(1) < 1000000) {
                userMapper.insert(
                        new UserEntity()
                                .setAge(count.get())
                                .setSalary(new BigDecimal(String.valueOf(count.get())))
                                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                .setId((long) count.get())
                                .setName("lazy" + count.get())
                );
            }
            sqlSession.commit();
        }).start();

        new Thread(() -> {
            while (count.getAndAdd(1) < 1000000) {
                userMapper.insert(
                        new UserEntity()
                                .setAge(count.get())
                                .setSalary(new BigDecimal(String.valueOf(count.get())))
                                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                                .setId((long) count.get())
                                .setName("lazy" + count.get())
                );
            }
            sqlSession.commit();
        }).start();


        while (count.get() <= 1000000) {
            Thread.sleep(10000000);
        }
    }
}




```