package com.lazy.orm.example;

import com.alibaba.fastjson.JSON;
import com.lazy.orm.datasource.simple.SimpleDataSourceFactory;
import com.lazy.orm.example.dao.UserMapper;
import com.lazy.orm.example.entity.UserEntity;
import com.lazy.orm.io.Resources;
import com.lazy.orm.session.Configuration;
import com.lazy.orm.session.SqlSession;
import com.lazy.orm.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class SqlSessionTest {


    @Test
    public void test() throws IOException {

        //数据源获取
        Properties props = Resources.getResourceAsProperties("lazyorm.properties");
        DataSource ds = new SimpleDataSourceFactory().setProperties(props).getDataSource();

        //Sql会话
        Configuration configuration = new Configuration()
                //数据源
                .setDataSource(ds)
                //Mapper
                .addMapper(UserMapper.class);

        SqlSession sqlSession = SqlSessionFactoryBuilder.build(configuration).openSession();

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
