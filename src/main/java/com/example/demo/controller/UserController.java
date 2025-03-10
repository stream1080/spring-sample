package com.example.demo.controller;

import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.ScaffoldException;
import com.example.demo.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author stream
 * @since 2022-11-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public ResponseVo error() {
        throw new ScaffoldException(ResponseEnum.SERVER_ERROR);
    }
}
