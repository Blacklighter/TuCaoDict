package com.example.myapplication.Login;

//该接口用于登录，注册
public interface AccountLogin {
    //注册
    public abstract void regist(Account account);
    //登录
    public abstract boolean isLogin(String telephone, String Password);
}
