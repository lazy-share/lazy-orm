package com.lazy.orm.handler;

/**
 * <p>
 * 结果集处理器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public interface ResultHandler {

    <T> T handlerResult(ResultSetContext context);

}
