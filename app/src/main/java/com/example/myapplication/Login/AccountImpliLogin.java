package com.example.myapplication.Login;

import java.util.ArrayList;

public class AccountImpliLogin implements AccountLogin {

    //创建一个集合，定义为公有的
    static ArrayList<Account> accounts = new ArrayList<Account>();

    //注册方法
    @Override
    public void regist(Account account) {
        account.addAccount(account);
    }

    //登录功能
    @Override
    public boolean isLogin(String telephone, String password) {
        boolean flag = false;
        for(Account account : accounts) {
            if(telephone.equals(account.getTelephone()) && password.equals(account.getPassword()) );
            {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
