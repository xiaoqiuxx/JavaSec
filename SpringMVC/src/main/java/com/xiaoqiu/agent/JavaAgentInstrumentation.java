package com.xiaoqiu.agent;

import java.lang.instrument.Instrumentation;

/**
 * @Description :
 * @Time : 2023/4/15 18:28
 * @Author : xiaoqiuxx
 */
public class JavaAgentInstrumentation {
    public static void agentmain(String args, Instrumentation inst) throws InterruptedException {
        Class [] classes = inst.getAllLoadedClasses();

        for(Class cls : classes){
            System.out.println("------------------------------------------");
            System.out.println("加载类: "+cls.getName());
            System.out.println("是否可被修改: "+inst.isModifiableClass(cls));
        }
    }
}
