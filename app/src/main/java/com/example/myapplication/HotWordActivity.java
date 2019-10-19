package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.nightonke.boommenu.BoomButtons.HamButton;

import java.util.ArrayList;

public class HotWordActivity extends AppCompatActivity {

    private ImageButton returnButton,writingButton;//返回按钮、添加按钮
    private TextView headLine;//标题
    private com.nex3z.flowlayout.FlowLayout flowLayout;//标题下的标签
    private MeaningLinearLayout meaning;//基本解释
    private MyLinearLayout condition,source,example;//适用情形、来龙去脉、范例
    private RelevantContent relevantContent;//相关槽点

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

}
