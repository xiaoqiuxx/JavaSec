package com.xiaoqiuxx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Time : 2023/4/14 15:29
 * @Author : xiaoqiuxx
 */

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "<h1>hello world!";
    }
}
