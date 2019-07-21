package com.lazy.orm.exception;

/**
 * <p>
 * 单结果异常
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public class SingleRowException extends RuntimeException {

    public SingleRowException() {
    }

    public SingleRowException(String message) {
        super(message);
    }

    public SingleRowException(String message, Throwable cause) {
        super(message, cause);
    }

    public SingleRowException(Throwable cause) {
        super(cause);
    }

    public SingleRowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
