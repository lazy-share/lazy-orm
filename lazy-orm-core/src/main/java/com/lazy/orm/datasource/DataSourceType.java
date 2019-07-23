package com.lazy.orm.datasource;

/**
 * <p>
 *     数据源类型
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/23.
 */
public enum DataSourceType {

    POOL("POOL"), UNPOOL("UNPOOL");

    private String val;

    DataSourceType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public DataSourceType setVal(String val) {
        this.val = val;
        return this;
    }

    public static void main(String[] args) {
        System.out.println(DataSourceType.valueOf("POOL").val);
    }
}
