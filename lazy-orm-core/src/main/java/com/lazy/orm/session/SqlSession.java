package com.lazy.orm.session;

/**
 * <p>
 * 会话类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public interface SqlSession {


    <T> T execute(String mapperKey, Object params);

    void commit();

    void rollback();

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();

}
