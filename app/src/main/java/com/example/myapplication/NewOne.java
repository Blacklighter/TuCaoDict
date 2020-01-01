package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.utils.Utils;

import org.json.JSONArray;

import java.util.ArrayList;

public class NewOne extends AppCompatActivity {

    private ArrayList<EditText> texts = new ArrayList<>();
    private EditText headLine,meaning,condition,source,example;//每个具体的组件
    private Button publishButton;//发布按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_one);

        headLine = (EditText)findViewById(R.id.标题);
        meaning = (EditText)findViewById(R.id.基本释义);
        condition = (EditText)findViewById(R.id.适用情形);
        source = (EditText)findViewById(R.id.来龙去脉);
        example = (EditText)findViewById(R.id.范例);
        publishButton = (Button)findViewById(R.id.发布);

        texts.add(headLine);
        texts.add(meaning);
        texts.add(condition);
        texts.add(source);
        texts.add(example);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SpannableString s = new SpannableString("此处不能为空！");
                int n = 0;
                for(int i = 0;i < texts.size();i++)
                    if(texts.get(i).getText().length() == 0){
                        n++;
                        texts.get(i).setHint("此处不能为空！");
                    }

                if(n == 0){

                    Utils.mysql("insert into entry_overview(entry_name,telephone,hot_num,state) " +
                            "values('"+headLine.getText()+"','18379910897',0,'待审核');",new Handler(){
                        //Message传回，触发该回调函数
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                        }
                    });

                    changeSQL("基本释义",texts.get(0).getText().toString());
                    changeSQL("适用情形",texts.get(1).getText().toString());
                    changeSQL("来龙去脉",texts.get(2).getText().toString());
                    changeSQL("范例",texts.get(3).getText().toString());

                    doIntent();

                }

            }

        });

    }

    private void changeSQL(String s,String text){
        Utils.mysql("insert into modules(entry_name,module_kind,telephone,content,like_num,dislike_num) " +
                "values('"+headLine.getText()+"','"+s+"','18379910897','"+text+"',0,0);",new Handler(){
            //Message传回，触发该回调函数
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        });
    }

    private void doIntent(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);//启动
    }



    public void setHeadLine(EditText headLine) {
        this.headLine = headLine;
    }

    public void setMeaning(EditText meaning) {
        this.meaning = meaning;
    }

    public void setCondition(EditText condition) {
        this.condition = condition;
    }


    public void setExample(EditText example) {
        this.example = example;
    }

    public void setSource(EditText source) {
        this.source = source;
    }

    public EditText getHeadLine() {
        return headLine;
    }

    public EditText getSource() {
        return source;
    }

    public EditText getCondition() {
        return condition;
    }

    public EditText getExample() {
        return example;
    }

    public EditText getMeaning() {
        return meaning;
    }

}
