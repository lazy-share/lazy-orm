package com.lazy.orm.parser.support;

import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.parser.SqlSourceParser;

/**
 * <p>
 * 抽象Sql解析器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractSqlSourceParser implements SqlSourceParser {


    @Override
    public SqlSource parser(String sqlStr) {
        return this.doParser(sqlStr);
    }

    protected abstract SqlSource doParser(String sqlStr);
}
