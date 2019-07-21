package com.lazy.orm.session;

/**
 * <p>
 * SqlSession Factory Builder
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public class SqlSessionFactoryBuilder {


    public static SqlSessionFactory build(Configuration configuration) {

        return new DefaultSqlSessionFactory(configuration);
    }

}
