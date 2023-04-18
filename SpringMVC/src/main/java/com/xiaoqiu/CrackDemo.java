package com.xiaoqiu;

/**
 * @Description :
 * @Time : 2023/4/16 18:35
 * @Author : xiaoqiuxx
 */

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import javassist.*;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.List;

public class CrackDemo {

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        for(Class clazz : inst.getAllLoadedClasses()){ // 先获取到所有已加载的 class
            if (clazz.getName().equals("com.xiaoqiu.CrackTest")){
                inst.addTransformer(new TransformerDemo(), true); // 添加 transformer
                inst.retransformClasses(clazz); // 重加载该 class
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String pid, name;
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(VirtualMachineDescriptor vmd : list){
            pid = vmd.id();
            name = vmd.displayName();
            if (name.equals("com.xiaoqiu.CrackTest")){
                VirtualMachine vm = VirtualMachine.attach(pid);
                String jarName = "out/artifacts/SpringMVC_jar/SpringMVC.jar";
                vm.loadAgent(jarName);
                vm.detach();
                System.out.println("attach ok");
                break;
            }
        }
    }
}

class TransformerDemo implements ClassFileTransformer{
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals("com/xiaoqiu/CrackTest")) { // 因为 transformer 会拦截所有待加载的 class, 所以需要先检查一下 className 是否匹配
            try {
                ClassPool pool = ClassPool.getDefault();
                CtClass clazz = pool.get("com.xiaoqiu.CrackTest");
                CtMethod method = clazz.getDeclaredMethod("checkLogin");
                method.setBody("{System.out.println(\"inject success!!!\"); return true;}"); // 利用 Javaassist 修改指定方法的代码
                byte[] code = clazz.toBytecode();
                clazz.detach();
                return code;
            } catch (Exception e) {
                e.printStackTrace();
                return classfileBuffer;
            }
        } else {
            return classfileBuffer;
        }
    }
}
