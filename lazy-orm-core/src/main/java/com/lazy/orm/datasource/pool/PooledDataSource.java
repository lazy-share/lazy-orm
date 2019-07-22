/**
 * Copyright 2009-2019 the original author or authors.
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


import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.datasource.simple.SimpleDataSource;
import com.lazy.orm.datasource.support.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author laizhiyuan
 * @since 2019-07-22.
 */
public class PooledDataSource extends AbstractDataSource {

    private static final Log log = LogFactory.getLog(PooledDataSource.class);

    private final DataSource dataSource;

    private final PoolState state = new PoolState();
    private final long maxActiveCount = 10;
    private final long maxIdleCount = 5;
    private final long maxWaitTime = 20000;
    private String pingStatement = "Please Config A Ping Sql Statement";


    public PooledDataSource(Properties properties) {
        this.dataSource = new SimpleDataSource(properties);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.doGetConnection().getProxyConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return this.getConnection();
    }

    protected PoolProxyConnection doGetConnection() {

        return null;
    }

    protected void doPushConnection(PoolProxyConnection poolProxyConnection) {

    }
}
