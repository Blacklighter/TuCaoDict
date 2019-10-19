package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



=======
<<<<<<< HEAD

import android.content.Intent;
<<<<<<< HEAD

import android.media.Image;

import android.app.Activity;

import android.content.Intent;

=======
=======
<<<<<<< HEAD
>>>>>>> 41320e113ce267359428627e3ac5326c1a281517
import android.media.Image;
import android.app.Activity;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 17e59519b70518cc36dfcd8e2648445ce94b7fbf
>>>>>>> d6ac82212445fedb7cfbdd4e42083771932ae317
>>>>>>> 36715cc90a7da4882601e5b1ae91abf25485a500
>>>>>>> 41320e113ce267359428627e3ac5326c1a281517
>>>>>>> 99319a7f94ea2edf8111058bb70d636023717c10
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.myapplication.utils.Utils;
import com.liuguangqiang.ipicker.IPicker;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
>>>>>>> 41320e113ce267359428627e3ac5326c1a281517
>>>>>>> 99319a7f94ea2edf8111058bb70d636023717c10
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    Handler handler = null;

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
