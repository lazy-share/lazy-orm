package com.lazy.orm.datasource.pool;


import com.lazy.orm.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Clinton Begin
 */
public class PooledDataSourceFactory implements DataSourceFactory {

    private Properties properties;

    public PooledDataSourceFactory setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public DataSource getDataSource() {
        return new PooledDataSource(properties);
    }
}
