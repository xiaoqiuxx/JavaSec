package com.xiaoqiu.agent;

/**
 * @Description :
 * @Time : 2023/4/15 17:25
 * @Author : xiaoqiuxx
 */
import static java.lang.Thread.sleep;

public class Sleep_Hello {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            System.out.println("Hello World!");
            sleep(5000);
        }
    }

    public static void hello(){
        System.out.println("Hello w!");
    }
}