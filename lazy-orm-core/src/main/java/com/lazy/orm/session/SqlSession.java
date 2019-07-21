package com.lazy.orm.session;

import java.io.Closeable;
import java.io.IOException;

/**
 * <p>
 * 会话类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public interface SqlSession extends Closeable {


    <T> T execute(String mapperKey, Object... params);

    void commit();

    void rollback();

    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();

    @Override
    void close() throws IOException;
}
