package com.xiaoqiu.agent;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Description :
 * @Time : 2023/4/15 21:49
 * @Author : xiaoqiuxx
 */
public class HelloTransform implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals("com/xiaoqiu/agent/agentmainAgent"))
            try {

                //获取CtClass 对象的容器 ClassPool
                ClassPool classPool = ClassPool.getDefault();

                //添加额外的类搜索路径
                if (classBeingRedefined != null) {
                    ClassClassPath ccp = new ClassClassPath(classBeingRedefined);
                    classPool.insertClassPath(ccp);
                }

                //获取目标类
                CtClass ctClass = classPool.get("com.xiaoqiu.agent.Sleep_Hello");

                //获取目标方法
                CtMethod ctMethod = ctClass.getDeclaredMethod("hello");

                //设置方法体
                String body = "{System.out.println(\"Hacker!\");}";
                ctMethod.setBody(body);

                //返回目标类字节码
                byte[] bytes = ctClass.toBytecode();
                return bytes;

            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
}
