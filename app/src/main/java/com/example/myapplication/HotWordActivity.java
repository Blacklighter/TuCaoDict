package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.HamButton;

import java.util.ArrayList;

public class HotWordActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<TextView> textViewArrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_word);

        //新建一个新的文本适配器
        TextViewAdapater textViewAdapater1 = new TextViewAdapater(this,textViewArrayList1);

        //从布局文件中获取“适用情形”的翻页视图
        ViewPager textViewPager1 = findViewById(R.id.TextViewPager1);
        //为“适用情形”设置文本适配器
        textViewPager1.setAdapter(textViewAdapater1);
        //设置“适用情形”默认显示第一个页面
        textViewPager1.setCurrentItem(0);
        //给“适用情形”设置页面变化监听器
        textViewPager1.addOnPageChangeListener(this);
    }

    //翻页状态改变时触发
    public void onPageScrollStateChanged(int arg0){

    }

    //翻页过程中触发
    public void onPageScrolled(int arg0,float arg1,int arg2){

    }

    //翻页结束后触发
    public void onPageSelected(int arg0){

    }

}
