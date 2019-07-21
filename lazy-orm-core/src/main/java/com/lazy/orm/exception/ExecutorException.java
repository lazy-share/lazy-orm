package com.lazy.orm.exception;

/**
 * <p>
 * 执行异常
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class ExecutorException extends RuntimeException {

    public ExecutorException() {
    }

    public ExecutorException(String message) {
        super(message);
    }

    public ExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorException(Throwable cause) {
        super(cause);
    }

    public ExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
