package com.lazy.orm.datasource;

import com.lazy.orm.datasource.pool.PooledDataSourceFactory;
import com.lazy.orm.datasource.simple.SimpleDataSourceFactory;

/**
 * <p>
 * 数据源工厂构建者
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/23.
 */
public class DataSourceFactoryBuilder {


    public static DataSourceFactory of(DataSourceType type) {
        if (DataSourceType.UNPOOL.equals(type)) {
            return new SimpleDataSourceFactory();
        }
        return new PooledDataSourceFactory();
    }

    public static DataSourceFactory of(String type) {

        return of(DataSourceType.valueOf(type.toUpperCase()));
    }

}
