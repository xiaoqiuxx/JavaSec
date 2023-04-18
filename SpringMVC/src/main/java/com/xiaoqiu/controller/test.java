package com.xiaoqiu.controller;

/**
 * @Description :
 * @Time : 2023/4/14 16:10
 * @Author : xiaoqiuxx
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class test {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "Hello";
    }
}