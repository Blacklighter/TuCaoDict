package com.example.myapplication.Login;

import java.time.LocalDateTime;

import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Account {
    private static Account instance = null;

    private String telephone;           //电话号码
    private String password;            //用户密码
    private String nickName;            //昵称
    private String headImage;            //头像
    private String identity;            //身份(普通用户，管理员)
    private String gender;              //性别(男，女）
    private int likeNum;          //被赞数

    public static Account getAccount() {
        if (Account.instance == null) {
            Account.instance = new Account();
        }
        return Account.instance;
    }

    private Account() {

    }

    //更新当前账户的所有信息
    public void refresh(final Handler handler) {
        refresh(handler,0);
    }

    public void refresh(final Handler handler, final long delay) {
        Utils.mysql("SELECT * FROM users WHERE telephone='" + this.telephone + "'",true,new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results = (JSONArray) msg.obj;
                try {
                    JSONObject result = (JSONObject) results.get(0);

                    password = (String) result.get("password");
                    nickName = (String) result.get("nick_name");
                    headImage = (String) result.get("head_img");
                    identity = (String) result.get("identity");
                    gender = (String) result.get("gender");
                    likeNum = result.getInt("like_num");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessageDelayed(0x11,delay);
            }
        });
    }

    public void printInfo(){
        Log.e("当前账户的所有信息如下：\n",
                "telephone:"+telephone+"\n"+
                "password:"+password+"\n"+
                "nickName:"+nickName+"\n"+
                "headImage:"+headImage+"\n"+
                "identity:"+identity+"\n"+
                "gender:"+gender+"\n"+
                "likeNum:"+likeNum+"\n");
    }

    public String getNickName() {
        return nickName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

}


