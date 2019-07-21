package com.lazy.orm.mapper;

import com.lazy.orm.annotation.Sql;
import com.lazy.orm.exception.BindingException;
import com.lazy.orm.parser.MappedStatementParser;
import com.lazy.orm.parser.annotation.AnnotationMappedStatementParser;
import com.lazy.orm.session.Configuration;
import com.lazy.orm.session.SqlSession;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Mapper注册表
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class MapperRegistry {


    private final Configuration configuration;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {

        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);

        if (mapperProxyFactory == null) {

            throw new BindingException("该Mapper没有注册");
        }
        try {

            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void addMapper(Class<T> type) {

        if (!type.isInterface()) {
            return;
        }

        if (hasMapper(type)) {
            return;
        }

        knownMappers.put(type, new MapperProxyFactory(type));

        Method[] methods = type.getMethods();
        for (Method method : methods) {
            Sql sql = method.getAnnotation(Sql.class);
            if (sql == null) {
                continue;
            }
            MappedStatementParser mappedStatementParser = new AnnotationMappedStatementParser(configuration, type);
            MappedStatement mappedStatement = mappedStatementParser.parser(method);

            String key = type.getName() + "." + method.getName();
            mappedStatement.setId(key);
            configuration.addMappedStatement(key, mappedStatement);
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

}
