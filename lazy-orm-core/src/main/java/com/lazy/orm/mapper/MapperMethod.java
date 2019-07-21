package com.lazy.orm.mapper;

import com.lazy.orm.session.SqlSession;

import java.lang.reflect.Method;

/**
 * <p>
 * Mapper方法
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class MapperMethod {


    private final Method method;
    private final Class<?> methodInterface;

    public <T> MapperMethod(Method method, Class<?> methodInterface) {
        this.method = method;
        this.methodInterface = methodInterface;
    }

    public Object execute(SqlSession sqlSession, Object[] args) {

        String key = methodInterface.getName() + "." + method.getName();

        return sqlSession.execute(key, args);
    }
}
