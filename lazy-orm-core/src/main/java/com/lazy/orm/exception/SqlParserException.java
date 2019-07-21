package com.lazy.orm.exception;

/**
 * <p>
 * Sql解析异常
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public class SqlParserException extends RuntimeException {

    public SqlParserException() {
    }

    public SqlParserException(String message) {
        super(message);
    }

    public SqlParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlParserException(Throwable cause) {
        super(cause);
    }

    public SqlParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
