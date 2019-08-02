package com.lazy.orm.parser.annotation;

import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.mapper.ResultMap;
import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.parser.ParameterMapParser;
import com.lazy.orm.parser.ResultMapParser;
import com.lazy.orm.parser.SqlSourceParser;
import com.lazy.orm.parser.support.AbstractMappedStatementParser;
import com.lazy.orm.session.Configuration;

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
        SqlSourceParser sqlSourceParser = new AnnotationSqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parserStatic(method);

        //解析ParameterMap
        ParameterMapParser parameterMapParser = new AnnotationParameterMapParser(sqlSource);
        ParameterMap parameterMap = parameterMapParser.parser(method);

        //解析ResultMap
        ResultMapParser resultMapParser = new AnnotationResultMapParser();
        ResultMap resultMap = resultMapParser.parser(method);

        MappedStatement mappedStatement = new MappedStatement();
        mappedStatement.setSqlSource(sqlSource)
                .setParameterMap(parameterMap)
                .setDmlType(sqlSource.getDmlType())
                .setResultMap(resultMap);

        return mappedStatement;
    }
}
