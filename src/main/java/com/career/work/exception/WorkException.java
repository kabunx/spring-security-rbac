package com.career.work.exception;

public class WorkException extends Exception {

    private final Integer code;

    private final String msg;

    public WorkException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WorkException(ExceptionEnum e) {
        this(e.getCode(), e.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
