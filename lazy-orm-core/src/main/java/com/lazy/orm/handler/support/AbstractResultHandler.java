package com.lazy.orm.handler.support;

import com.lazy.orm.handler.ResultHandler;

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


    protected List<String> getColumns(ResultSet rs) throws Exception {

        ResultSetMetaData rsmd = rs.getMetaData();

        int count = rsmd.getColumnCount();

        String[] name = new String[count];

        for (int i = 0; i < count; i++) {

            name[i] = rsmd.getColumnName(i + 1);
        }

        return new ArrayList<>(Arrays.asList(name));
    }

}
