package com.lazy.orm.exception;

/**
 * <p>
 * 初始化异常
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/23.
 */
public class InitException extends RuntimeException {


    public InitException() {
    }

    public InitException(String message) {
        super(message);
    }

    public InitException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitException(Throwable cause) {
        super(cause);
    }

    public InitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
