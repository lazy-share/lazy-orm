package com.lazy.orm.handler;

import com.lazy.orm.exception.ExecutorException;
import com.lazy.orm.exception.SingleRowException;
import com.lazy.orm.handler.support.AbstractResultHandler;
import com.lazy.orm.mapper.MappedStatement;
import com.lazy.orm.mapper.ResultMap;
import com.lazy.orm.util.ReflectionUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 查询结果集处理器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class SelectResultHandler extends AbstractResultHandler {


    @Override
    @SuppressWarnings({"unchecked"})
    public <T> T handlerResult(ResultSetContext context) {


        try {
            ResultSet rs = context.getStmt().getResultSet();
            MappedStatement statement = context.getStatement();
            ResultMap resultMap = statement.getResultMap();
            Map<String, ResultMap.ResultMeta> resultMetas = resultMap.getResultMetas();

            List<Object> list = new ArrayList<>();
            List<String> columns = this.getColumns(rs);

            Class<?> rowClass = resultMap.getItemType().equals(Collection.class) ?
                    resultMap.getReturnType() : resultMap.getItemType();

            while (rs.next()) {
                Object rowObject = rowClass.newInstance();
                list.add(rowObject);

                for (String column : columns) {
                    ResultMap.ResultMeta meta = resultMetas.get(column);
                    if (meta == null) {
                        continue;
                    }
                    Object val = meta.getTypeHandler().getResult(rs, meta.getColumn());
                    ReflectionUtil.setFieldValue(meta.getName(), val, rowObject);
                }
            }

            if (resultMap.getItemType().equals(Collection.class)) {
                if (list.size() > 1) {
                    throw new SingleRowException("返回结果包含多条数据");
                }
                return (T) list.get(0);
            }
            return (T) list;
        } catch (Exception ex) {
            throw new ExecutorException("处理结果异常", ex);
        }
    }
}
