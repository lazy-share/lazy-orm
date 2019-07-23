package com.lazy.orm.datasource.simple;

import com.lazy.orm.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 * 简单数据源工厂类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public class SimpleDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        return new SimpleDataSource(properties);
    }
}
