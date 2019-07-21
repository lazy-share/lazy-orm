package com.lazy.orm.session;

import com.lazy.orm.executor.Executor;
import com.lazy.orm.executor.ExecutorType;
import com.lazy.orm.executor.simple.SimpleExecutor;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.MapperRegistry;
import com.lazy.orm.tx.JdbcTransactionFactory;
import com.lazy.orm.tx.Transaction;
import com.lazy.orm.tx.TransactionFactory;
import com.lazy.orm.tx.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.util.Map;
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

    private DataSource dataSource;
    private ExecutorType executorType = ExecutorType.SIMPLE;
    private MapperRegistry mapperRegistry = new MapperRegistry(this);
    private Map<String, MappedStatement> mappedStatements = new ConcurrentHashMap<>();
    private TransactionIsolationLevel txLevel = TransactionIsolationLevel.READ_COMMITTED;

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
}
