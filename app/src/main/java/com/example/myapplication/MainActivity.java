package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD

=======
<<<<<<< HEAD
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
=======
<<<<<<< HEAD
>>>>>>> 89b0523e72ff4b89816a4ae731550f675710d4d8

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
<<<<<<< HEAD

=======
>>>>>>> 36715cc90a7da4882601e5b1ae91abf25485a500
>>>>>>> 99319a7f94ea2edf8111058bb70d636023717c10
>>>>>>> 89b0523e72ff4b89816a4ae731550f675710d4d8

public class MainActivity extends AppCompatActivity {
    Handler handler = null;

    private TextView headLine;//标题
    private ImageButton cameraButton;//相机按钮
    private EditText findEditText;//输入框
    private ImageButton findButton;//搜索按钮
    private com.nex3z.flowlayout.FlowLayout relevantContent;//热点词条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD

=======
        //从布局文件中获取所有的View
        this.headLine = (TextView)findViewById(R.id.head_line);
        this.cameraButton = (ImageButton)findViewById(R.id.camera_button);
        this.findEditText = (EditText)findViewById(R.id.find_edit);
        this.findButton = (ImageButton)findViewById(R.id.find_button);
        this.relevantContent = (com.nex3z.flowlayout.FlowLayout)findViewById(
                R.id.relevant_content_part);

        //设置相机、搜索点击事件监听器
        this.cameraButton.setOnClickListener(
                new CameraButtonOnClickListener(this.cameraButton,this));
        this.findButton.setOnClickListener(
                new FindButtonOnClickListener(this.findButton,this));

    }//onCreate

    //点击了“相机”按钮
    public void clickCameraButton(){

    }

    //点击了“搜索”按钮
    public void clickFindButton(){

    }


    //set、get
    public ImageButton getCameraButton() {
        return cameraButton;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public EditText getFindEditText() {
        return findEditText;
    }

    public FlowLayout getRelevantContent() {
        return relevantContent;
    }

    public ImageButton getFindButton() {
        return findButton;
    }

    public void setCameraButton(ImageButton cameraButton) {
        this.cameraButton = cameraButton;
    }

    public void setRelevantContent(FlowLayout relevantContent) {
        this.relevantContent = relevantContent;
    }

    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setFindButton(ImageButton findButton) {
        this.findButton = findButton;
    }

    public void setFindEditText(EditText findEditText) {
        this.findEditText = findEditText;
=======
<<<<<<< HEAD

=======
>>>>>>> 89b0523e72ff4b89816a4ae731550f675710d4d8
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
<<<<<<< HEAD
=======
>>>>>>> 36715cc90a7da4882601e5b1ae91abf25485a500
>>>>>>> 99319a7f94ea2edf8111058bb70d636023717c10
>>>>>>> 89b0523e72ff4b89816a4ae731550f675710d4d8
    }

}
