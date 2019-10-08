package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.media.Image;
=======
import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 17e59519b70518cc36dfcd8e2648445ce94b7fbf
>>>>>>> d6ac82212445fedb7cfbdd4e42083771932ae317
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent=getIntent();
//        Bundle bundle=intent.getExtras();
//        String string=bundle.getString("text");
//        Log.e("text",string);//获取点击了哪一个分类列表的词条名。

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }




}
