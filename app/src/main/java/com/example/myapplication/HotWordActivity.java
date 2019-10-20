package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.utils.Utils;
import com.nex3z.flowlayout.FlowLayout;
import com.nightonke.boommenu.BoomButtons.HamButton;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotWordActivity extends AppCompatActivity {

    private ImageButton returnButton,writingButton;//返回按钮、添加按钮
    private TextView headLine;//标题
    private com.nex3z.flowlayout.FlowLayout flowLayout;//标题下的标签
    private MeaningLinearLayout meaning;//基本解释
    private MyLinearLayout condition,source,example;//适用情形、来龙去脉、范例
    private RelevantContent relevantContent;//相关槽点
    private String stringFromLastActivity;//从上一个页面传入的字符串

    //onCreate方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_word);

        //基本解释
        this.meaning = new MeaningLinearLayout((LinearLayout)findViewById(R.id.meaning),
                (TextView)findViewById(R.id.meaning_headLine),
                (ImageButton)findViewById(R.id.good_button_for_meaning),
                (TextView)findViewById(R.id.num_of_good_for_meaning),
                (ImageButton)findViewById(R.id.bad_button_for_meaning),
                (TextView)findViewById(R.id.num_of_bad_for_meaning),
                (TextView)findViewById(R.id.meaning_text_part),this);

        //适用情形
        this.condition = new MyLinearLayout((LinearLayout)findViewById(R.id.condition),
                (TextView)findViewById(R.id.condition_head_line),
                (ImageButton)findViewById(R.id.good_button_for_condition),
                (TextView)findViewById(R.id.num_of_good_for_condition),
                (ImageButton)findViewById(R.id.bad_button_for_condition),
                (TextView)findViewById(R.id.num_of_bad_for_condition),
                this,(ViewPager)findViewById(R.id.condition_part));

        //来龙去脉
        this.source = new MyLinearLayout((LinearLayout)findViewById(R.id.source),
                (TextView)findViewById(R.id.source_head_line),
                (ImageButton)findViewById(R.id.good_button_for_source),
                (TextView)findViewById(R.id.num_of_good_for_source),
                (ImageButton)findViewById(R.id.bad_button_for_source),
                (TextView)findViewById(R.id.num_of_bad_for_source),
                this,(ViewPager)findViewById(R.id.source_part));

        //范例
        this.example = new MyLinearLayout((LinearLayout)findViewById(R.id.example),
                (TextView)findViewById(R.id.example_head_line),
                (ImageButton)findViewById(R.id.good_button_for_example),
                (TextView)findViewById(R.id.num_of_good_for_example),
                (ImageButton)findViewById(R.id.bad_button_for_example),
                (TextView)findViewById(R.id.num_of_bad_for_example),
                this,(ViewPager)findViewById(R.id.example_part));

        //相关槽点
        this.relevantContent = new RelevantContent(
                (TextView)findViewById(R.id.relevant_content_head_line),
                (com.nex3z.flowlayout.FlowLayout)findViewById(R.id.relevant_content_part),
                this);

        //从布局文件中设置成员对象
        this.setReturnButton((ImageButton)findViewById(R.id.return_button));
        this.setWritingButton((ImageButton)findViewById(R.id.writing_button));
        this.setHeadLine((TextView)findViewById(R.id.head_line));
        this.setFlowLayout((com.nex3z.flowlayout.FlowLayout)findViewById(R.id.correlation_with_this));

        //设置部分监听器
        this.returnButton.setOnClickListener(
                new ReturnButtonOnClickListener(this,this.returnButton));
        this.writingButton.setOnClickListener(
                new WritingOnClickListener(this,this.writingButton));

        Intent intent = getIntent();//获取前一个页面传来的意图
        Bundle bundle = intent.getExtras();//打开包裹
        this.setStringFromLastActivity(bundle.getString("word"));//修改字符串
        if(bundle.getBoolean("ifFinded"))//判断标志位
            init1();//首页进入
        else
            init2();//详情页或首页标签进入

    }

    //点击了“返回”按钮，调用clickReturnButton()方法
    public void clickReturnButton(){
        //返回前一个页面
    }

    //点击了添加按钮，调用clickWritingButton()方法
    public void clickWritingButton(){
        //实现部分，较为复杂，如果需要动画，可能需要参考其他现成品
    }

    //任何地方点击了“点赞”按钮，调用clickGoodButton()方法
    public void clickGoodButton(GoodOrBadButton goodButton){

    }

    //任何地方点击了“踩”按钮，调用clickBadButton()方法
    public void clickBadButton(GoodOrBadButton badButton){

    }

    //初始化页面(首页搜索进入时使用)
    public void init1(){
        //提示窗口
        LemonBubble.showRight(this,"正在加载数据...");

        //然后进行异步查询，即使再耗时，此时UI线程也不会"卡顿"
        Utils.mysql("SELECT * FROM entry_overview where entry_name like '%" +
                this.getStringFromLastActivity() + "%';",new Handler(){
            //Message传回，触发该回调函数
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results =(JSONArray)msg.obj;
                JSONObject jsonObject = null;


                if(results.length() > 0){

                    //for循环找出最符合搜索的匹配元组
                    for(int i = 0;i < results.length();i++){
                        if(jsonObject == null) {
                            try {
                                jsonObject = results.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            try {
                                if(jsonObject.getInt("hot_name")
                                        <= results.getJSONObject(i).getInt("hot_name"))
                                    jsonObject = results.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    //jsonObject是查询之后得到的最高热度匹配的元组，在这里实现后面的更新

                    //提示窗口
                    LemonBubble.showRight(HotWordActivity.this,"数据加载完成！",1500);

                }

            }

        });
    }

    //初始化页面(详情页点击“相关槽点”或首页点击“热门槽点”时使用)
    public void init2(){
        //提示窗口
        LemonBubble.showRight(this,"正在加载数据...");

        //然后进行异步查询，即使再耗时，此时UI线程也不会"卡顿"
        Utils.mysql("SELECT * FROM entry_overview where entry_name = '" +
                this.getStringFromLastActivity() + "';",new Handler(){

            //Message传回，触发该回调函数
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results =(JSONArray)msg.obj;
                JSONObject jsonObject = null;

                if(results.length() == 1){

                    try {
                        jsonObject = results.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //jsonObject是查询之后得到的最高热度匹配的元组，在这里实现后面的更新

                    //提示窗口
                    LemonBubble.showRight(HotWordActivity.this,"数据加载完成！",1500);

                }
                else
                    LemonBubble.showRight(HotWordActivity.this,"该槽点不存在了~~~",1500);

            }

        });
    }

    //点击了“相关槽点”标签后触发
    public void clickTextView(String string){
        Intent intent = new Intent(this,HotWordActivity.class);//声明一个意图
        Bundle bundle = new Bundle();//包裹
        bundle.putString("word",string);//加入字符串
        bundle.putBoolean("isFinded",false);//搜索进入详情页标志位
        intent.putExtras(bundle);//加入意图
        startActivity(intent);//启动
    }

    //新建一个新的标签
    public void createTextView(String string){
        final TextView textView = new TextView(this);
        textView.setText(string);
        MyStyleTextView myStyleTextView = new MyStyleTextView(textView,this);
        this.getRelevantContent().getFlowLayout().addView(textView);
        //设置监听器
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTextView(textView.getText().toString());
            }
        });
    }


    //set、get
    public void setFlowLayout(FlowLayout flowLayout) {
        this.flowLayout = flowLayout;
    }

    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setCondition(MyLinearLayout condition) {
        this.condition = condition;
    }

    public void setMeaning(MeaningLinearLayout meaning) {
        this.meaning = meaning;
    }

    public void setReturnButton(ImageButton returnButton) {
        this.returnButton = returnButton;
    }

    public void setExample(MyLinearLayout example) {
        this.example = example;
    }

    public void setSource(MyLinearLayout source) {
        this.source = source;
    }

    public void setWritingButton(ImageButton writingButton) {
        this.writingButton = writingButton;
    }

    public void setRelevantContent(RelevantContent relevantContent) {
        this.relevantContent = relevantContent;
    }

    public void setStringFromLastActivity(String stringFromLastActivity) {
        this.stringFromLastActivity = stringFromLastActivity;
    }

    public FlowLayout getFlowLayout() {
        return flowLayout;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public ImageButton getReturnButton() {
        return returnButton;
    }

    public ImageButton getWritingButton() {
        return writingButton;
    }

    public MeaningLinearLayout getMeaning() {
        return meaning;
    }

    public MyLinearLayout getCondition() {
        return condition;
    }

    public MyLinearLayout getExample() {
        return example;
    }

    public MyLinearLayout getSource() {
        return source;
    }

    public RelevantContent getRelevantContent() {
        return relevantContent;
    }

    public String getStringFromLastActivity() {
        return stringFromLastActivity;
    }

}
