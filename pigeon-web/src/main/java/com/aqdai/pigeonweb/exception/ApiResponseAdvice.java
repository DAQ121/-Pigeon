package com.aqdai.pigeonweb.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 检查是否支持拦截器，例如排除掉某些类型（可以根据你的需要进行更具体的过滤）
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        // 如果是 swagger 请求路径，直接返回 body，不进行封装
        String path = request.getURI().getPath();
        if (path.contains("swagger") || path.contains("api-docs")) {
            return body;
        }

        // 如果已经是 ApiResponse 则不再封装
        if (body instanceof ApiResponse) {
            return body;
        }

        // 其他路径正常封装返回体
        return ApiResponse.success(body);
    }

}
