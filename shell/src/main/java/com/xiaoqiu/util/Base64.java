package com.xiaoqiu.util;

import java.io.ByteArrayOutputStream;

/**
 * @Description : base64工具类
 * @Time : 2023/4/18 19:09
 * @Author : xiaoqiuxx
 */
public class Base64 {

    public static String base64(ByteArrayOutputStream bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes.toByteArray());
    }

}
