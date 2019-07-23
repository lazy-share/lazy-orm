package com.lazy.orm.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * <p>
 * 数据源工厂接口
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public interface DataSourceFactory {

    DataSource getDataSource();

    void setProperties(Properties properties);

}
