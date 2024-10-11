package com.aqdai.pigeoncommon.exception;

public class BusinessException extends RuntimeException {

    private int code;  // 错误码

    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
