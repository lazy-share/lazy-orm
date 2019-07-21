package com.lazy.orm.exception;

/**
 * <p>
 * 绑定异常
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class BindingException extends RuntimeException {

    public BindingException() {
    }

    public BindingException(String message) {
        super(message);
    }

    public BindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindingException(Throwable cause) {
        super(cause);
    }

    public BindingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
