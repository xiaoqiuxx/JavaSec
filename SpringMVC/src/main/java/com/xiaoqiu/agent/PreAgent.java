package com.xiaoqiu.agent;

import java.lang.instrument.Instrumentation;

/**
 * @Description :
 * @Time : 2023/4/15 16:23
 * @Author : xiaoqiuxx
 */
public class PreAgent {
    public static void premain(String args, Instrumentation inst) {
        for (int i =0 ; i<10 ; i++){
            System.out.println("调用了premain-Agent！");
        }
    }
}
