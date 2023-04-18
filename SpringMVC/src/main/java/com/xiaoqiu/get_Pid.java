package com.xiaoqiu;


import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * @Description :
 * @Time : 2023/4/15 16:48
 * @Author : xiaoqiuxx
 */
public class get_Pid {
    public static void main(String[] args) {
        //调用VirtualMachine.list()获取正在运行的JVM列表
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(VirtualMachineDescriptor vmd : list){
            //有可能需要加上包名
            //System.out.println(vmd.displayName());

            //遍历每一个正在运行的JVM，如果JVM名称为get_PID则返回其PID
            if(vmd.displayName().equals("com.xiaoqiu.get_Pid"))
                //26560
                System.out.println(vmd.id());
        }
    }

}
