package com.example.demo.exception;

import com.example.demo.enums.ResponseEnum;
import lombok.Data;

/**
 * 自定义异常
 *
 * @author stream
 * @since 2022-11-22 12:24
 */
@Data
public class ScaffoldException extends RuntimeException {

    private final Integer code;

    private final String msg;

    /**
     * 默认异常使用 SERVER_ERROR 状态码
     *
     * @param message
     */
    public ScaffoldException(String message) {
        super(message);
        this.code = ResponseEnum.SERVER_ERROR.getCode();
        this.msg = ResponseEnum.SERVER_ERROR.getMsg();
    }

    /**
     * 枚举传入异常
     *
     * @param responseEnum
     */
    public ScaffoldException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    /**
     * 枚举 + msg 传入异常
     *
     * @param responseEnum
     * @param message
     */
    public ScaffoldException(ResponseEnum responseEnum, String message) {
        super(message);
        this.msg = message;
        this.code = responseEnum.getCode();
    }

}
