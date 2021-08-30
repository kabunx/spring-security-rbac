package com.career.work.exception;

public enum ExceptionEnum {
    SYSTEM_ERROR(100001, "系统异常"),
    LOGIN_REQUIRED(401001, "请登录"),
    USER_REGISTER_ERROR(422110, "用户注册失败"),
    USER_LOGIN_ERROR(422001, "账号或密码错误"),
    USERNAME_OR_PASSWORD_REQUIRED(422100, "用户名或密码不能为空"),
    PASSWORD_TOO_SHORT(422101, "密码长度不够"),
    USERNAME_EXIST(422102, "用户名已存在");


    private final Integer code;

    private final String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
