package com.lazy.orm.datasource.support;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * <p>
 * 抽象数据源
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public abstract class AbstractDataSource implements DataSource {

    protected ClassLoader driverClassLoader;
    protected Properties driverProperties;
    protected static Map<String, Driver> registeredDrivers = new ConcurrentHashMap<>();

    protected String driver;
    protected String url;
    protected String username;
    protected String password;

    protected Boolean autoCommit;
    protected Integer defaultTransactionIsolationLevel;
    protected Integer defaultNetworkTimeout;

    static {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            registeredDrivers.put(driver.getClass().getName(), driver);
        }
    }

    public ClassLoader getDriverClassLoader() {
        return driverClassLoader;
    }

    public AbstractDataSource setDriverClassLoader(ClassLoader driverClassLoader) {
        this.driverClassLoader = driverClassLoader;
        return this;
    }

    public Properties getDriverProperties() {
        return driverProperties;
    }

    public AbstractDataSource setDriverProperties(Properties driverProperties) {
        this.driverProperties = driverProperties;
        return this;
    }

    public static Map<String, Driver> getRegisteredDrivers() {
        return registeredDrivers;
    }

    public static void setRegisteredDrivers(Map<String, Driver> registeredDrivers) {
        AbstractDataSource.registeredDrivers = registeredDrivers;
    }

    public String getDriver() {
        return driver;
    }

    public AbstractDataSource setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public AbstractDataSource setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AbstractDataSource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AbstractDataSource setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getAutoCommit() {
        return autoCommit;
    }

    public AbstractDataSource setAutoCommit(Boolean autoCommit) {
        this.autoCommit = autoCommit;
        return this;
    }

    public Integer getDefaultTransactionIsolationLevel() {
        return defaultTransactionIsolationLevel;
    }

    public AbstractDataSource setDefaultTransactionIsolationLevel(Integer defaultTransactionIsolationLevel) {
        this.defaultTransactionIsolationLevel = defaultTransactionIsolationLevel;
        return this;
    }

    public Integer getDefaultNetworkTimeout() {
        return defaultNetworkTimeout;
    }

    public AbstractDataSource setDefaultNetworkTimeout(Integer defaultNetworkTimeout) {
        this.defaultNetworkTimeout = defaultNetworkTimeout;
        return this;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

}
