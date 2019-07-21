package com.lazy.orm.session;

/**
 * <p>
 *
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public interface SqlSessionFactory {

    SqlSession openSession();

    SqlSession openSession(boolean isAutoCommit);

}
