package com.xiaoqiu;

/**
 * @Description :
 * @Time : 2023/4/16 18:35
 * @Author : xiaoqiuxx
 */
public class CrackTest {
    public static String username = "admin";
    public static String password = "fakepassword";

    public static boolean checkLogin(){
        if (username == "admin" && password == "admin"){
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) throws Exception{
        while(true){
            if (checkLogin()){
                System.out.println("login success");
            } else {
                System.out.println("login failed");
            }
            Thread.sleep(1000);
        }
    }
}