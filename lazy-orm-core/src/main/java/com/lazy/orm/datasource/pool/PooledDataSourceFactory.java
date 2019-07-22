/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
