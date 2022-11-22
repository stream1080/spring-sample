package com.example.demo.aspect;

import com.example.demo.annnotation.NotControllerResponseAdvice;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.ScaffoldException;
import com.example.demo.vo.ResponseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 封装响应
 *
 * @author stream
 * @since 2022-11-22 12:45
 */
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, @NotNull Class<? extends HttpMessageConverter<?>> aClass) {
        // response 是 ResponseVo 类型，或者注释了 NotControllerResponseAdvice 都不进行包装
        return !methodParameter.getParameterType().isAssignableFrom(ResponseVo.class)
                || methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(ResponseVo.ok(data));
            } catch (JsonProcessingException e) {
                throw new ScaffoldException(ResponseEnum.SERVER_ERROR, e.getMessage());
            }
        }
        // 否则直接包装成ResultVo返回
        return ResponseVo.ok(data);
    }
}
