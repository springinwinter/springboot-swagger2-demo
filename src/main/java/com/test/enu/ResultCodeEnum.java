package com.test.enu;

/**
 * @Description: 调用状态枚举
 */
public enum ResultCodeEnum {
    RESULT_CODE_SUCCESS(200,"success"),
    RESULT_CODE_ERROR(500,"fail")
    //参数错误从1033开始--1200结束
    //业务错误 从2033--2200
    ;


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
