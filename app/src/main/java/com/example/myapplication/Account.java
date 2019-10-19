package com.example.myapplication;

import java.time.LocalDateTime;
import java.util.Scanner;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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



    public String getAccountNum() {
        return accountNum;
    }

    public String getTelephone() {
        return telephone;
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