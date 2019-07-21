package com.lazy.orm.session;

import com.lazy.orm.executor.Executor;
import com.lazy.orm.tx.Transaction;
import com.lazy.orm.tx.TransactionFactory;

/**
 * <p>
 * Default SqlSession Factory
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
       return this.doOpenSession(false);
    }

    private SqlSession doOpenSession(boolean isAutoCommit){
        TransactionFactory transactionFactory = configuration.getTransactionFactory();
        Transaction tx = transactionFactory.newTransaction(configuration.getDataSource(), configuration.getTxLevel(), isAutoCommit);
        final Executor executor = configuration.getExecutor(tx, null);
        return new DefaultSqlSession(configuration, executor);
    }

    @Override
    public SqlSession openSession(boolean isAutoCommit) {
        return this.doOpenSession(isAutoCommit);
    }
}
