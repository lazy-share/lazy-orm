package com.lazy.orm.handler;

import com.lazy.orm.exception.ExecutorException;
import com.lazy.orm.handler.support.AbstractResultHandler;

/**
 * <p>
 * 更新结果处理器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class UpdateResultHandler extends AbstractResultHandler {


    @Override
    protected <T> T doHandlerResult(ResultSetContext context) {
        try {
            return (T) Integer.valueOf(context.getStmt().getUpdateCount());
        } catch (Exception e) {
            throw new ExecutorException("获取更新条数错误", e);
        }
    }
}
