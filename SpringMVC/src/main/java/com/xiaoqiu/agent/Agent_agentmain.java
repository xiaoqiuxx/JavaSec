package com.xiaoqiu.agent;

import java.lang.instrument.Instrumentation;

import static java.lang.Thread.sleep;

/**
 * @Description :
 * @Time : 2023/4/15 17:26
 * @Author : xiaoqiuxx
 */
public class Agent_agentmain {
    public static void agentmain(String args, Instrumentation inst) throws InterruptedException {
        while (true){
            System.out.println("调用了agentmain-Agent!");
            sleep(3000);
        }
    }
}
