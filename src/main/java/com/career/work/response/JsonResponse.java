package com.career.work.response;

import com.career.work.exception.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResponse<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "success";

    private static final int BAD_REQUEST_CODE = 422000;
    private static final String BAD_REQUEST_MESSAGE = "客户端请求参数错误";

    public JsonResponse(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public JsonResponse(T data) {
        this(true, SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public JsonResponse() {
        this(true, SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <T> JsonResponse<T> success() {
        return new JsonResponse<>();
    }

    public static <T> JsonResponse<T> success(T data) {
        return new JsonResponse<>(data);
    }

    public static <T> JsonResponse<T> error(Integer status, String message) {
        return new JsonResponse<>(false, status, message);
    }

    public static <T> JsonResponse<T> error(ExceptionEnum e) {
        return new JsonResponse<>(false, e.getCode(), e.getMsg());
    }

    public static <T> JsonResponse<T> error(T data) {
        return new JsonResponse<>(false, BAD_REQUEST_CODE, BAD_REQUEST_MESSAGE, data);
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
