package com.lazy.orm.template;

/**
 * <p>
 * Sql模板
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class SqlTemplate {

    private String symbol;

    public SqlTemplate(String symbol) {
        this.symbol = symbol;
    }

    public String parser(String sqlTemplate, Object params) {
        StringBuilder sql = new StringBuilder();

        return sql.toString();
    }
}
