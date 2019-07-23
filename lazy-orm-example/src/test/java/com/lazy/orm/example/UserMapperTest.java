package com.lazy.orm.example;

import com.alibaba.fastjson.JSON;
import com.lazy.orm.datasource.pool.PooledDataSourceFactory;
import com.lazy.orm.datasource.simple.SimpleDataSourceFactory;
import com.lazy.orm.example.dao.UserMapper;
import com.lazy.orm.example.entity.UserEntity;
import com.lazy.orm.io.Resources;
import com.lazy.orm.session.Configuration;
import com.lazy.orm.session.SqlSession;
import com.lazy.orm.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
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
        DataSource ds = new PooledDataSourceFactory().setProperties(props).getDataSource();

        //Sql会话
        Configuration configuration = new Configuration()
                //数据源
                .setDataSource(ds)
                //Mapper
                .addMapper(UserMapper.class);

        sqlSession = SqlSessionFactoryBuilder.build(configuration).openSession();
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


        while (count.get() <= 1000000){
            Thread.sleep(10000000);
        }
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
}
