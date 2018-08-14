package com.sinohealth.dscp.constants.enumClass;

/**
 * sql类型
 */
public enum SqlType {

    cyclicSql(1,"循环sql"), joinSql(2, "拼接sql");

    private int code;
    private String describe;

    SqlType(int code, String describe){
        this.code = code;
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
