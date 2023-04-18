package com.xiaoqiu.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Description :
 * @Time : 2023/4/15 21:47
 * @Author : xiaoqiuxx
 */
public class agentmainAgent {
    public static void agentmain(String args, Instrumentation inst) throws InterruptedException, UnmodifiableClassException {
        Class [] classes = inst.getAllLoadedClasses();

        //获取目标JVM加载的全部类
        for(Class cls : classes){
            if (cls.getName().equals("com.xiaoqiu.agent.Sleep_Hello")){
                //添加一个transformer到Instrumentation，并重新触发目标类加载
                inst.addTransformer(new HelloTransform(),true);
                inst.retransformClasses(cls);
            }
        }
    }
}
