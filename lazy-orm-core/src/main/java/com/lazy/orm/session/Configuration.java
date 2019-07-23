package com.lazy.orm.session;

import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.datasource.DataSourceFactory;
import com.lazy.orm.datasource.DataSourceFactoryBuilder;
import com.lazy.orm.exception.InitException;
import com.lazy.orm.executor.Executor;
import com.lazy.orm.executor.ExecutorType;
import com.lazy.orm.executor.simple.SimpleExecutor;
import com.lazy.orm.io.Resources;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.MapperRegistry;
import com.lazy.orm.scaner.MapperScan;
import com.lazy.orm.tx.JdbcTransactionFactory;
import com.lazy.orm.tx.Transaction;
import com.lazy.orm.tx.TransactionFactory;
import com.lazy.orm.tx.TransactionIsolationLevel;
import com.lazy.orm.util.StringUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 配置类
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public class Configuration {


    private String mapperScanPackage;
    private DataSource dataSource;
    private ExecutorType executorType = ExecutorType.SIMPLE;
    private MapperRegistry mapperRegistry = new MapperRegistry(this);
    private Map<String, MappedStatement> mappedStatements = new ConcurrentHashMap<>();
    private TransactionIsolationLevel txLevel = TransactionIsolationLevel.READ_COMMITTED;
    private Properties properties;

    private static final Log log = LogFactory.getLog(Configuration.class);

    public Configuration() {
        try {
            Properties props = Resources.getResourceAsProperties("lazyorm.properties");
            initConfig(props);
            loadMapper();
        } catch (IOException e) {
            throw new InitException("请在ClassPath下指定【lazyorm.properties】配置文件");
        }

    }

    public Configuration(Properties props) {
        initConfig(props);
        loadMapper();
    }

    private void initConfig(Properties properties) {
        this.properties = properties;

        String txLevelVal = (String) properties.getOrDefault("txLevel", "READ_COMMITTED");
        txLevel = TransactionIsolationLevel.valueOf(txLevelVal.trim().toUpperCase());
        log.info("配置信息:"  + properties.toString());

        String dataSourceType = (String) properties.getOrDefault("dataSourceType", "POOL");
        DataSourceFactory dataSourceFactory = DataSourceFactoryBuilder.of(dataSourceType.trim());
        dataSourceFactory.setProperties(properties);
        dataSource = dataSourceFactory.getDataSource();

        mapperScanPackage = properties.getProperty("mapperScanPackage");
        if (!StringUtil.hasText(mapperScanPackage)) {
            throw new InitException("初始化异常, 请配置[mapperScanPackage]");
        }
        mapperScanPackage = mapperScanPackage.replaceAll("\\.", "/");

    }


    private void loadMapper() {
        MapperScan mapperScan = new MapperScan(this);
        mapperScan.scan();
    }

    public MappedStatement getMappedStatement(String key) {
        return mappedStatements.get(key);
    }

    public void addMappedStatement(String key, MappedStatement statement) {
        mappedStatements.put(key, statement);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    public <T> Configuration addMapper(Class<T> type) {
        this.mapperRegistry.addMapper(type);
        return this;
    }

    public Executor getExecutor(Transaction tx, ExecutorType type) {
        if (type != null) {
            executorType = type;
        }
        if (ExecutorType.SIMPLE == executorType) {
            return new SimpleExecutor(tx);
        }
        return new SimpleExecutor(tx);
    }

    public ExecutorType getExecutorType() {
        return executorType;
    }

    public Configuration setExecutorType(ExecutorType executorType) {
        this.executorType = executorType;
        return this;
    }


    public TransactionIsolationLevel getTxLevel() {
        return txLevel;
    }

    public Configuration setTxLevel(TransactionIsolationLevel txLevel) {
        this.txLevel = txLevel;
        return this;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public TransactionFactory getTransactionFactory() {
        return new JdbcTransactionFactory();
    }


    public Configuration setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getMapperScanPackage() {
        return mapperScanPackage;
    }

    public Configuration setMapperScanPackage(String mapperScanPackage) {
        this.mapperScanPackage = mapperScanPackage;
        return this;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public Configuration setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
        return this;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public Configuration setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
        return this;
    }

    public Properties getProperties() {
        return properties;
    }

    public Configuration setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }
}
