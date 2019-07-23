package com.lazy.orm.exception;

/**
 * <p>
 * 关闭操作异常
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/23.
 */
public class CloseException extends RuntimeException {

    public CloseException() {
    }

    public CloseException(String message) {
        super(message);
    }

    public CloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloseException(Throwable cause) {
        super(cause);
    }

    public CloseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
