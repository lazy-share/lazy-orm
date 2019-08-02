package com.lazy.orm.executor.simple;

import com.lazy.orm.exception.ExecutorException;
import com.lazy.orm.executor.support.AbstractExecutor;
import com.lazy.orm.handler.ResultSetContext;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.ParameterMap;
import com.lazy.orm.mapper.ParameterMeta;
import com.lazy.orm.mapper.SqlSource;
import com.lazy.orm.tx.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
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

        Connection connection = null;
        try {

            connection = tx.getConnection();
            ParameterMap parameterMap = statement.getParameterMap();
            Map<String, ParameterMeta> parameterMetas = parameterMap.getParameterMetas();
            Map<String, ParameterMeta> finalParameterMetas = new HashMap<>();
            SqlSource sqlSource = statement.getSqlSource();

            for (String pName : parameterMetas.keySet()) {
                ParameterMeta meta = parameterMetas.get(pName);
                Object paramObject = params[meta.getParamIdx() - 1];
                Object parameterVal = parameterValHandler.getVal(paramObject, meta.getName());

                sqlSourceParser.parserLike(parameterVal, meta);

                if (sqlSourceParser.parserDynamic(sqlSource, parameterMetas, meta, parameterVal)) {
                    continue;
                }

                if (sqlSourceParser.parserIn(sqlSource, parameterMetas, finalParameterMetas, meta, parameterVal)) {
                    continue;
                }

                finalParameterMetas.put(pName, meta);

                log.info("parameter: #{" + meta.getName() + "} value:" + parameterVal);
            }

            parameterMap.setParameterMetas(finalParameterMetas);
            log.info("sql: " + sqlSource.getSql());


            PreparedStatement ps = connection.prepareStatement(sqlSource.getSql());
            for (String param : finalParameterMetas.keySet()) {
                ParameterMeta meta = finalParameterMetas.get(param);
                meta.getTypeHandler().setParameter(ps, meta.getPlaceholder().getSymbolIdx(), meta.getVal());
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
        } finally {
            try {
                if (connection != null && connection.getAutoCommit()) {
                    tx.close();
                }
            } catch (SQLException e) {
                log.error("关闭事务异常", e);
            }
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
