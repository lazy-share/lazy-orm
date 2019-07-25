package com.lazy.orm.executor.simple;

import com.lazy.orm.exception.ExecutorException;
import com.lazy.orm.executor.support.AbstractExecutor;
import com.lazy.orm.handler.ResultSetContext;
import com.lazy.orm.mapper.*;
import com.lazy.orm.tx.Transaction;
import com.lazy.orm.type.TypeHandlerFactory;

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
            String sql = sqlSource.getSql();

            for (String pName : parameterMetas.keySet()) {
                ParameterMeta meta = parameterMetas.get(pName);
                Object paramObject = params[meta.getParamIdx() - 1];
                Object parameterVal = parameterValHandler.getVal(paramObject, meta.getName());
                if (meta.getPlaceholder().isLike()) {
                    parameterVal = "%" + parameterVal + "%";
                }

                log.info("parameter: #{" + meta.getName() + "} value:" + parameterVal);
                meta.setVal(parameterVal);

                if (meta.getPlaceholder().isDynamic()) {
                    sql = sql.substring(0, meta.getPlaceholder().getCharIdx())
                            + parameterVal + sql.substring(meta.getPlaceholder().getCharIdx() + 1);

                    //将对应的后面的符号占位符索引值递增
                    for (String pName2 : parameterMetas.keySet()) {
                        ParameterMeta meta2 = parameterMetas.get(pName2);
                        if (meta2.getPlaceholder().getSymbolIdx() > meta.getPlaceholder().getSymbolIdx()) {
                            meta2.getPlaceholder().setCharIdx(meta2.getPlaceholder().getCharIdx() + parameterVal.toString().length() - 1);
                        }
                    }

                    sqlSource.setSql(sql);
                    continue;
                }

                if (meta.getPlaceholder().isIn()) {
                    if (!(parameterVal instanceof Iterable)) {
                        throw new ExecutorException("in 操作符参数必须实现Iterable接口");
                    }
                    StringBuilder inSqlSymbol = new StringBuilder();
                    Iterable inList = (Iterable) parameterVal;
                    int symbolIdx = meta.getPlaceholder().getSymbolIdx();
                    int nextSymbolIdx = symbolIdx;

                    int count = 0;
                    for (Object inItem : inList) {
                        //创建一个参数符号占位符元数据
                        ParameterMeta parameterMeta = new ParameterMeta()
                                .setTypeHandler(TypeHandlerFactory.of(inItem.getClass()))
                                .setVal(inItem)
                                .setPlaceholder(new Placeholder().setSymbolIdx(nextSymbolIdx++));
                        finalParameterMetas.put(meta.getName() + nextSymbolIdx, parameterMeta);

                        //记录sql符号占位符字符串
                        inSqlSymbol.append("?,");
                        count++;
                    }

                    //将对应的后面的符号占位符索引值 +1
                    for (String pName2 : parameterMetas.keySet()) {
                        ParameterMeta meta2 = parameterMetas.get(pName2);
                        if (meta2.getPlaceholder().getSymbolIdx() > symbolIdx) {
                            meta2.getPlaceholder().setSymbolIdx(meta2.getPlaceholder().getSymbolIdx() + count - 1);
                        }
                    }


                    inSqlSymbol = inSqlSymbol.deleteCharAt(inSqlSymbol.length() - 1);
                    sql = sql.substring(0, meta.getPlaceholder().getCharIdx()) +
                            inSqlSymbol + sql.substring(meta.getPlaceholder().getCharIdx() + 1);

                    sqlSource.setSql(sql);
                    continue;
                }

                finalParameterMetas.put(pName, meta);
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
