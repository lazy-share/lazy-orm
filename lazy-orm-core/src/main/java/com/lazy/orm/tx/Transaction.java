package com.lazy.orm.tx;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <p>
 * 事务接口
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/19.
 */
public interface Transaction {

    /**
     * Retrieve inner database connection.
     *
     * @return DataBase connection
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * Commit inner database connection.
     *
     * @throws SQLException
     */
    void commit() throws SQLException;

    /**
     * Rollback inner database connection.
     *
     * @throws SQLException
     */
    void rollback() throws SQLException;

    /**
     * Close inner database connection.
     *
     * @throws SQLException
     */
    void close() throws SQLException;

    /**
     * Get transaction timeout if set.
     *
     * @throws SQLException
     */
    Integer getTimeout() throws SQLException;

}
