package com.example.demo.aspect;

import com.example.demo.annnotation.ResponseEncrypt;
import com.example.demo.utils.AesUtil;
import com.example.demo.vo.ResponseVo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 对接口数据进行加密
 *
 * @date 2023-09-07 14:24:27
 */
@RestControllerAdvice
public class ResponseEncryptAdvice implements ResponseBodyAdvice<Object> {

    @Value("${aes.key:cc23d16933a4a4d6c51474e4ea691544}")
    private String key;

    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在写入之前更改body的值
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter returnType,
                                  @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        // 判断是否需要加密
        boolean encrypt = false;
        // 获取类上的注解
        final boolean classPresentAnno = returnType.getContainingClass().isAnnotationPresent(ResponseEncrypt.class);
        if (classPresentAnno) {
            // 类上标注的是否需要加密
            encrypt = returnType.getContainingClass().getAnnotation(ResponseEncrypt.class).value();
            // 类不加密，所有都不加密
            if (!encrypt) {
                return body;
            }
        }
        // 获取方法上的注解
        final boolean methodPresentAnno = Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(ResponseEncrypt.class);
        if (methodPresentAnno) {
            // 方法上标注的是否需要加密
            encrypt = returnType.getMethod().getAnnotation(ResponseEncrypt.class).value();
            if (!encrypt) {
                return body;
            }
        }

        // 如果body是属于ResponseVo类型,只需要对data里面的数据进行加密即可
        if (body instanceof ResponseVo) {
            final ResponseVo ResponseVo = (ResponseVo) body;
            final Object data = ResponseVo.getData();
            if (data == null) {
                return body;
            }
            return ResponseVo.setData(AesUtil.encrypt(data.toString(), key));
        } else {
            return body;
        }
    }

}