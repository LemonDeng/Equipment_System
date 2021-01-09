package com.ys.exception;

/**
 * 统一异常
 */
public class YsLjException extends RuntimeException{
    private final Integer code;
    private final String message;

    public YsLjException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public YsLjException(YsLjExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
