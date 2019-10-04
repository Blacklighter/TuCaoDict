package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        test();
                    }
                }).start();
            }
        });
    }

    private void test(){
        String url = "http://www.blacklighter.cn/test.py";
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder().add("name","haha").build();
        Request request = new Request.Builder().url(url).post(body).build();
        try{
            Response response = okHttpClient.newCall(request).execute();
            Log.e("TEST:", response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
