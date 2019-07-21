package com.lazy.orm.mapper;

import com.lazy.orm.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * <p>
 * Dml代理工厂类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }


    @SuppressWarnings("unchecked")
    protected T newInstance(MapperProxy mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface}, mapperProxy);
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return newInstance(mapperProxy);
    }
}
