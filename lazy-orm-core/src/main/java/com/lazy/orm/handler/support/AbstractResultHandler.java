package com.lazy.orm.handler.support;

import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.exception.CloseException;
import com.lazy.orm.handler.ResultHandler;
import com.lazy.orm.handler.ResultSetContext;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 抽象结果集处理器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractResultHandler implements ResultHandler {


    protected Log log = LogFactory.getLog(AbstractResultHandler.class);

    @Override
    public <T> T handlerResult(ResultSetContext context) {

        try {

            return this.doHandlerResult(context);
        } finally {

            try {
                if (context != null) {
                    if (context.getStmt() != null) {
                        if (context.getStmt().getResultSet() != null) {
                            context.getStmt().getResultSet().close();
                        }
                        context.getStmt().close();
                    }
                }
            } catch (Exception ex) {

                log.error("关闭资源异常", ex);
            }
        }
    }

    protected abstract <T> T doHandlerResult(ResultSetContext context);


    protected List<String> getColumns(ResultSet rs) throws Exception {

        ResultSetMetaData rsmd = rs.getMetaData();

        int count = rsmd.getColumnCount();

        String[] name = new String[count];

        for (int i = 0; i < count; i++) {

            try {
                name[i] = rsmd.getColumnLabel(i + 1);
            } catch (Exception ex) {
                name[i] = rsmd.getColumnName(i + 1);
            }
        }

        return new ArrayList<>(Arrays.asList(name));
    }

}
