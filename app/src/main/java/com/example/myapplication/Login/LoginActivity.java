package com.example.myapplication.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.example.myapplication.utils.Utils;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button)findViewById(R.id.loginButton);
        final EditText telephone = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);

        final Account account = Account.getAccount();

        //登录按钮
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LemonBubble.showRoundProgress(LoginActivity.this,"登录中...");
                Utils.mysql("SELECT * FROM users WHERE telephone='"+telephone.getText().toString()+"' AND password='"+password.getText().toString()+"'",new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);

                        JSONArray array = (JSONArray)msg.obj;
                        if(array == null || array.length() == 0){
                                LemonBubble.showError(LoginActivity.this,"账号或密码有误",1500);
                        }
                        else{

                            LemonBubble.showRight(LoginActivity.this,"登录成功",1500);
                            //跳转到首页
                            account.setTelephone(telephone.getText().toString());
                            account.setPassword(password.getText().toString());
                            account.refresh(new Handler(){
                                @Override
                                public void handleMessage(@NonNull Message msg) {
                                    super.handleMessage(msg);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            },1600);
                        }
                    }
                });
            }
        });

        //注册跳转
        TextView textView = (TextView)findViewById(R.id.registerText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Account account = Account.getAccount();
        final EditText telephone = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        if(account.getTelephone() != null && account.getPassword() != null && account.getTelephone().length()>0 && account.getPassword().length() > 0){
            telephone.setText(account.getTelephone());
            password.setText(account.getPassword());
        }
    }
}
