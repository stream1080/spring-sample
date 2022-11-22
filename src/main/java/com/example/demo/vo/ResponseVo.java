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
public class ResponseVo {

    private Integer code;

    private String msg;

    private Object data;

    public static ResponseVo ok() {
        return ResponseVo.ok(null);
    }

    public static ResponseVo ok(Object data) {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }

    public static ResponseVo error(ResponseEnum responseEnum) {
        return new ResponseVo(responseEnum.getCode(), responseEnum.getMsg(), null);
    }

    public static ResponseVo error(ResponseEnum responseEnum, String msg) {
        return new ResponseVo(responseEnum.getCode(), msg, null);
    }

}
