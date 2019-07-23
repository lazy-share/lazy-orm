package com.lazy.orm;

import com.lazy.orm.example.dao.UserMapper;
import com.lazy.orm.example.entity.UserEntity;
import com.lazy.orm.io.Resources;
import com.lazy.orm.session.Configuration;
import com.lazy.orm.session.SqlSession;
import com.lazy.orm.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 */
public class App {

    private static SqlSession sqlSession;
    static DataSource ds;


    public static void before() throws IOException {
        //数据源获取
        Properties props = Resources.getResourceAsProperties("lazyorm.properties");

        //Sql会话
        Configuration configuration = new Configuration(props);

        sqlSession = SqlSessionFactoryBuilder.build(configuration).openSession();
    }


    public static void initTestData() throws InterruptedException {
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
                sqlSession.commit();
            }
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
                sqlSession.commit();
            }
        }).start();

    }


    public static void testPool() {

        Random ra = new Random();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {

                        int randomCount = ra.nextInt(10000);
                        int randomCount2 = ra.nextInt(10000);
                        Thread.sleep(randomCount);

                        ds.getConnection();

                        Thread.sleep(Math.min(randomCount, randomCount2));

                        ds.getConnection().close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        Thread t5 = new Thread(r);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        before();
        testPool();
//        initTestData();
    }
}
