package com.lazy.orm.annotation;

/**
 * <p>
 * Dml类型
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/21.
 */
public enum DmlType {

    SELECT("select"), UPDATE("update"), DELETE("delete"), INSERT("insert");

    private String val;

    DmlType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public DmlType setVal(String val) {
        this.val = val;
        return this;
    }

    public static DmlType of(String sql) {
        String sqlStr = sql.toLowerCase();
        for (DmlType dmlType : DmlType.values()) {
            if (sqlStr.startsWith(dmlType.getVal())) {
                return dmlType;
            }
        }
        System.out.println("没有找到DmlType: " + sql);
        return null;
    }
}
