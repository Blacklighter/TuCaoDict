package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.utils.Utils;
import com.nex3z.flowlayout.FlowLayout;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class check extends AppCompatActivity {

    //从上个页面传来的字符串、基本释义、适用情形、来龙去脉、范例
    private String stringFromLastActivity,meaningText,conditionText,sourceText,exampleText;
    private TextView headLine,meaning,condition,source,example;//每个具体的组件
    private JSONArray allJsonObject;
    private com.nex3z.flowlayout.FlowLayout flowLayout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        //从布局文件中获取对象加载到内部成员中
        this.setHeadLine((TextView)findViewById(R.id.标题内容));
        this.setMeaning((TextView)findViewById(R.id.基本释义));
        this.setCondition((TextView)findViewById(R.id.适用情形));
        this.setSource((TextView)findViewById(R.id.来龙去脉));
        this.setExample((TextView)findViewById(R.id.范例));

        editText = new EditText(check.this);
        editText.setHint("+");
        flowLayout = (com.nex3z.flowlayout.FlowLayout)findViewById(R.id.分类);
        flowLayout.addView(editText);

        Intent intent = getIntent();//获取前一个页面传来的意图
        Bundle bundle = intent.getExtras();//打开包裹
        this.setStringFromLastActivity(bundle.getString("word"));



        findViewById(R.id.审核通过).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.mysql("update entry_overview set entry_kinds = '"+editText.getText().toString()+"',state = '通过' where entry_name = '" +
                        getStringFromLastActivity() + "';",new Handler(){
                    //Message传回，触发该回调函数
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        check.this.finish();
                    }

                });

            }

        });

        findViewById(R.id.审核不通过).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.mysql("update entry_overview set state = '未通过' where entry_name = '" +
                        getStringFromLastActivity() + "';",new Handler(){
                    //Message传回，触发该回调函数
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        check.this.finish();
                    }

                });

            }

        });

        initAll();

    }

    private void initAll(){
        getAllDate();
    }

    private void getAllDate(){

        Utils.mysql("SELECT * FROM modules where entry_name = '" +
                this.getStringFromLastActivity() + "';",new Handler(){
            //Message传回，触发该回调函数

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                setAllJsonObject((JSONArray) msg.obj);//设置AllJsonObject
                handOutObjectAndSet();//分发数据

            }

        },this,"加载中...","加载完成");

    }

    //根据获得的所有对象将它们放在对应的位置
    private void handOutObjectAndSet() {
        headLine.setText(stringFromLastActivity);
        for(int i = 0;i < this.getAllJsonObject().length();i++)
            try {
                JSONObject obj = this.getAllJsonObject().getJSONObject(i);
                switch (obj.getString("module_kind")){
                    case "基本释义":
                        this.setMeaningText(obj.getString("content"));
                        break;
                    case "适用情形":
                        this.setConditionText(obj.getString("content"));
                        break;
                    case "来龙去脉":
                        this.setSourceText(obj.getString("content"));
                        break;
                    case "范例":
                        this.setExampleText(obj.getString("content"));
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }

    //设置标题
    private void initHeadLine(){
        this.getHeadLine().setText(this.getStringFromLastActivity());
    }



    public String getConditionText() {
        return conditionText;
    }

    public String getExampleText() {
        return exampleText;
    }

    public String getMeaning() {
        return meaningText;
    }

    public String getSourceText() {
        return sourceText;
    }

    public String getStringFromLastActivity() {
        return stringFromLastActivity;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
        condition.setText(conditionText);
    }

    public void setExampleText(String exampleText) {
        this.exampleText = exampleText;
        example.setText(exampleText);
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
        source.setText(sourceText);
    }

    public void setStringFromLastActivity(String stringFromLastActivity) {
        this.stringFromLastActivity = stringFromLastActivity;
    }

    public String getMeaningText() {
        return meaningText;
    }

    public TextView getCondition() {
        return condition;
    }

    public TextView getExample() {
        return example;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public TextView getSource() {
        return source;
    }

    public void setMeaning(TextView meaning) {
        this.meaning = meaning;
    }

    public void setCondition(TextView condition) {
        this.condition = condition;
    }

    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setSource(TextView source) {
        this.source = source;
    }

    public void setExample(TextView example) {
        this.example = example;
    }

    public void setMeaningText(String meaningText) {
        this.meaningText = meaningText;
        meaning.setText(meaningText);
    }

    public void setAllJsonObject(JSONArray allJsonObject) {
        this.allJsonObject = allJsonObject;
    }

    public JSONArray getAllJsonObject() {
        return allJsonObject;
    }

}