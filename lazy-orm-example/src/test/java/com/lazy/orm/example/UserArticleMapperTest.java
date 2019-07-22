package com.lazy.orm.example;

import com.alibaba.fastjson.JSON;
import com.lazy.orm.datasource.simple.SimpleDataSourceFactory;
import com.lazy.orm.example.dao.ArticleMapper;
import com.lazy.orm.example.dao.UserMapper;
import com.lazy.orm.example.dto.QueryUserArticleDto;
import com.lazy.orm.io.Resources;
import com.lazy.orm.session.Configuration;
import com.lazy.orm.session.SqlSession;
import com.lazy.orm.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @since 2019/7/22.
 */
public class UserArticleMapperTest {


    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //数据源获取
        Properties props = Resources.getResourceAsProperties("lazyorm.properties");
        DataSource ds = new SimpleDataSourceFactory().setProperties(props).getDataSource();

        //Sql会话
        Configuration configuration = new Configuration()
                //数据源
                .setDataSource(ds)
                //Mapper
                .addMapper(UserMapper.class)
                .addMapper(ArticleMapper.class);

        sqlSession = SqlSessionFactoryBuilder.build(configuration).openSession();
    }


    @Test
    public void test() {
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        List<QueryUserArticleDto> dtos = articleMapper.findUserArticleByCondition(new QueryUserArticleDto()
                .setAge(0)
                .setArticleId(1L)
                .setUserId(1L)
                .setContent("t")
                .setName("lazy"));

        System.out.println(JSON.toJSONString(dtos));


    }

}
