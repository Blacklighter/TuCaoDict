package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //从布局文件中获取名为editText的文本输入框,以及搜索按钮button,下方推荐热词布局
        EditText editText = findViewById(R.id.editText);
        ImageButton button = findViewById(R.id.imageButton2);
        com.nex3z.flowlayout.FlowLayout my_flowLayout = findViewById(R.id.my_flowLayout);

        //点击事件
        button.setOnClickListener(this);

        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }//onCreate

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.imageButton2){//如果点击了搜索按钮
            //获取文本输入框并获得其文字
            String string = ((EditText)findViewById(R.id.editText)).getText().toString();
            //新的标签
            TextView t = new TextView(this);
            Drawable drawable = getResources().getDrawable(R.drawable.tag_shape);
            t.setBackground(drawable);
            //t.setHeight(100);
            //t.setWidth(400);
            t.setText(string);
            com.nex3z.flowlayout.FlowLayout flowLayout = findViewById(R.id.my_flowLayout);
            flowLayout.addView(t);
        }//if

    }

    //当用户点击了搜索按钮时触发方法goToSearch
    protected void goToSearch(String s){

    }//goToSearch

    //显示热词方法,传入热词显示的流式布局
    protected void showHotWord(com.nex3z.flowlayout.FlowLayout flowLayout){

    }//showHotWord

}
