package com.example.demo.vo;


import com.example.demo.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一返回结果
 *
 * @author stream
 */
@Data
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResponseVo<T> ok() {
        return ResponseVo.ok(null);
    }

    public static <T> ResponseVo<T> ok(T data) {
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
        return new ResponseVo<T>(responseEnum.getCode(), responseEnum.getMsg(), null);
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg) {
        return new ResponseVo<T>(responseEnum.getCode(), msg, null);
    }

}
