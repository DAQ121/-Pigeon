package com.aqdai.pigeonweb.exception;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;      // 响应状态码
    private String message; // 错误或成功消息
    private T data;         // 返回的数据

    // 成功响应
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("请求成功");
        response.setData(data);
        return response;
    }

    // 错误响应
    public static <T> ApiResponse<T> error(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}
