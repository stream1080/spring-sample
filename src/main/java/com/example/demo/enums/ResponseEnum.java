package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一响应码
 *
 * @author stream
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    // 公共类型
    SUCCESS(200, "success"),
    INVALID_PARAM(400, "invalid param"),
    UNAUTHORIZED(401, "no authorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not found"),
    SERVER_ERROR(500, "server error"),
    SERVICE_UNAVAILABLE(503, "service unavailable"),

    // 业务自定义
    PASSWORD_ERROR(5001, "密码错误"),

    ;

    private final Integer code;

    private final String msg;

}
