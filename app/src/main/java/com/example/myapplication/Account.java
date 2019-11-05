package com.example.myapplication;

import java.time.LocalDateTime;

import android.media.Image;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;

public class Account extends AppCompatActivity {
    private String accountNum;          //用户账号
    private String telephone;           //电话号码
    private String nickName;            //昵称
    private Image headImage;            //头像
    private String identity;            //身份(普通用户，管理员)
    private String genter;              //性别(男，女）
    public static int likeNum;          //被赞数
    private LocalDateTime loginTime;    //登录时间
    private LocalDateTime signinTime;   //注册时间
    private String browsingHistoy;      //浏览记录
    private String collection;          //收藏


<<<<<<< HEAD
    //从数据库中获取当前用户的用户账号
    public void getAccountNum(final Handler handler) {
        Utils.mysql("selectffasd ",new Handler(){           //在数据库中对“字符串”进行查找，查询结果为msg的成员
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results = (JSONArray) msg.obj;
                try {
                    String result = (String)results.get(0);

                    Message msg2 = new Message();
                    msg2.obj = result;
                    handler.sendMessage(msg2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
=======
//    //从数据库中获取当前用户的用户账号
//    public String getAccountNum(final Handler handler) {
//
//
//        Utils.mysql("selectffasd ",new Handler(){           //在数据库中对“字符串”进行查找，查询结果为msg的成员
//
//        Utils.mysql("selectffasd ",new Handler(){
//
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                JSONArray results = (JSONArray) msg.obj;
//                try {
//                    String result = (String)results.get(0);
//
//                    Message msg2 = new Message();
//                    msg2.obj = result;
//                    handler.sendMessage(msg2);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
>>>>>>> e5ab191e9798d1b2bffccdf6e750b8130373272a

    //从数据库中获取当前用户的Telephone
    public void getTelephone(final Handler handler) {
        Utils.mysql("telephone",new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results = (JSONArray) msg.obj;
                try {
                    String result = (String)results.get(0);
                    Message msg2 = new Message();
                    msg2.obj = result;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Image getHeadImage() {
        return headImage;
    }

    public void setHeadImage(Image headImage) {
        this.headImage = headImage;
    }

    public String getGenter() {
        return genter;
    }

    public void setGenter(String genter) {
        this.genter = genter;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    }

