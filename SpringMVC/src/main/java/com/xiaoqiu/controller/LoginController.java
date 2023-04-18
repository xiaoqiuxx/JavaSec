package com.xiaoqiu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Time : 2023/4/15 9:29
 * @Author : xiaoqiuxx
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "Success!";
    }

}
