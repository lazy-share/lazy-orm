package com.lazy.orm.parser.annotation;

import com.lazy.orm.annotation.Sql;
import com.lazy.orm.exception.ParserException;
import com.lazy.orm.mapper.Placeholder;
import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.parser.support.AbstractSqlSourceParser;
import com.lazy.orm.util.StringUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 注解SqlSource解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class AnnotationSqlSourceParser extends AbstractSqlSourceParser {

    @Override
    protected SqlSource doParser(Method method) {

        Sql sql = method.getAnnotation(Sql.class);
        String sqlStr = sql.value();
        SqlSource sqlSource = new SqlSource();
        sqlSource.setDmlType(sql.dmlType())
                .setSql(sqlStr);

        String openStr = "#{";
        if (!sqlStr.contains(openStr)) {
            return sqlSource;
        }

        int idx = 1;
        int openOffset = sqlStr.indexOf(openStr);
        Map<Integer, String> placeholderParamIdxMap = new HashMap<>();
        StringBuilder finalSql = new StringBuilder(sqlStr.substring(0, openOffset));
        String endStr = "}";
        int endOffset = sqlStr.indexOf(endStr);
        String name = sqlStr.substring(openOffset + 2, endOffset);
        finalSql.append("?");
        int charIdx = finalSql.lastIndexOf("?");
        sqlSource.putPlaceholder(name, new Placeholder()
                .setSymbolIdx(idx++)
                .setCharIdx(charIdx)
                .setName(name));

        placeholderParamIdxMap.put(charIdx, name);
        char[] chars = sqlStr.substring(endOffset + 1).toCharArray();

        boolean find = false;
        StringBuilder paramKey = new StringBuilder();
        for (char c : chars) {
            if (c == '}') {
                find = false;
                finalSql.append("?");
                charIdx = finalSql.lastIndexOf("?");
                placeholderParamIdxMap.put(charIdx, paramKey.toString());
                sqlSource.putPlaceholder(paramKey.toString(), new Placeholder()
                        .setSymbolIdx(idx++)
                        .setCharIdx(charIdx)
                        .setName(paramKey.toString()));
                paramKey.setLength(0);
                continue;
            }
            if (find) {
                paramKey.append(c);
                continue;
            }
            if (c == '{') {
                find = true;
                continue;
            }
            if (c == '#') {
                continue;
            }
            finalSql.append(c);
        }

        //解析like
        this.parserLike(sqlSource, finalSql, placeholderParamIdxMap);

        //解析in
        this.parserIn(sqlSource, finalSql, placeholderParamIdxMap);

        sqlSource.setSql(finalSql.toString());
        return sqlSource;
    }

    private void parserIn(SqlSource sqlSource, StringBuilder finalSql, Map<Integer, String> placeholderParamIdxMap) {

        int nextInIdx = finalSql.indexOf(" in ");
        while (nextInIdx > -1) {
            int placeholderIdx = finalSql.indexOf("?", nextInIdx);
            String paramName = placeholderParamIdxMap.get(placeholderIdx);
            if (!StringUtil.hasText(paramName)) {
                throw new ParserException("解析SQL错误,找不到in对应参数名称");
            }
            Placeholder p = sqlSource.getPlaceholder(paramName);
            if (p == null) {
                throw new ParserException("解析SQL错误,找不到in对应占位符");
            }
            p.setIn(true);
            sqlSource.putPlaceholder(paramName, p);

            nextInIdx = finalSql.indexOf(" in ", nextInIdx + 1);
        }

    }

    private void parserLike(SqlSource sqlSource, StringBuilder finalSql, Map<Integer, String> placeholderParamIdxMap) {

        int nextLikeIdx = finalSql.indexOf("like");
        while (nextLikeIdx > -1) {
            int placeholderIdx = finalSql.indexOf("?", nextLikeIdx);
            String paramName = placeholderParamIdxMap.get(placeholderIdx);
            if (!StringUtil.hasText(paramName)) {
                throw new ParserException("解析SQL错误,找不到like对应参数名称");
            }
            Placeholder p = sqlSource.getPlaceholder(paramName);
            if (p == null) {
                throw new ParserException("解析SQL错误,找不到like对应占位符");
            }
            p.setLike(true);
            sqlSource.putPlaceholder(paramName, p);

            nextLikeIdx = finalSql.indexOf("like", nextLikeIdx + 1);
        }

    }
}
