package com.lazy.orm.executor.simple;

import com.lazy.orm.exception.ExecutorException;
import com.lazy.orm.executor.support.AbstractExecutor;
import com.lazy.orm.handler.ResultSetContext;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.tx.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * <p>
 * 简单执行器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class SimpleExecutor extends AbstractExecutor {

    public SimpleExecutor(Transaction tx) {
        this.tx = tx;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T execute(MappedStatement statement, Object[] params) {
        try {

            log.info("sql: " + statement.getSqlSource().getSql());

            Connection connection = tx.getConnection();
            PreparedStatement ps = connection.prepareStatement(statement.getSqlSource().getSql());
            ParameterMap parameterMap = statement.getParameterMap();
            Map<String, ParameterMap.ParameterMeta> parameterMetas = parameterMap.getParameterMetas();
            for (String param : parameterMetas.keySet()) {
                ParameterMap.ParameterMeta meta = parameterMetas.get(param);
                Object parameterVal = parameterValHandler.getVal(params[meta.getParamIdx() - 1], meta.getName());
                if (meta.isLike()) {
                    parameterVal = "%" + parameterVal + "%";
                }
                meta.getTypeHandler().setParameter(ps, meta.getPlaceholderIdx(), parameterVal);
            }
            ps.execute();

            switch (statement.getDmlType()) {
                case SELECT:
                    return selectHandler.handlerResult(new ResultSetContext(ps, statement));
                case UPDATE:
                    return updateHandler.handlerResult(new ResultSetContext(ps, statement));
                case INSERT:
                    return updateHandler.handlerResult(new ResultSetContext(ps, statement));
                case DELETE:
                    return updateHandler.handlerResult(new ResultSetContext(ps, statement));
                default:
                    throw new ExecutorException("找不到对应的DML类型");
            }
        } catch (Exception e) {
            throw new ExecutorException("执行结果异常", e);
        }
    }

    @Override
    public void commit(boolean required) throws SQLException {
        this.tx.commit();
    }

    @Override
    public void rollback(boolean required) throws SQLException {
        this.tx.rollback();
    }

    @Override
    public void close() {
        try {
            tx.close();
        } catch (Exception e) {
            throw new ExecutorException(e);
        }
    }
}
