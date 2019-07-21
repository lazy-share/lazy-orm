package com.lazy.orm.parser.annotation;

import com.lazy.orm.annotation.Sql;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.mapper.ResultMap;
import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.parser.ParameterMapParser;
import com.lazy.orm.parser.ResultMapParser;
import com.lazy.orm.parser.SqlSourceParser;
import com.lazy.orm.parser.support.AbstractMappedStatementParser;
import com.lazy.orm.session.Configuration;
import org.omg.CORBA.MARSHAL;

import java.lang.reflect.Method;

/**
 * <p>
 * 注解MapppedStatement解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class AnnotationMappedStatementParser extends AbstractMappedStatementParser {

    public AnnotationMappedStatementParser(Configuration configuration, Class<?> type) {
        this.configuration = configuration;
        this.type = type;
    }


    @Override
    protected MappedStatement doParser(Method method) {

        //解析SqlSource
        Sql sql = method.getAnnotation(Sql.class);
        SqlSourceParser sqlSourceParser = new AnnotationSqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parser(sql.value());

        //解析ParameterMap
        ParameterMapParser parameterMapParser = new AnnotationParameterMapParser();
        ParameterMap parameterMap = parameterMapParser.parser(method);

        //解析ResultMap
        ResultMapParser resultMapParser = new AnnotationResultMapParser();
        ResultMap resultMap = resultMapParser.parser(method);

        MappedStatement mappedStatement = new MappedStatement();
        mappedStatement.setSqlSource(sqlSource)
                .setParameterMap(parameterMap)
                .setResultMap(resultMap);

        return mappedStatement;
    }
}
