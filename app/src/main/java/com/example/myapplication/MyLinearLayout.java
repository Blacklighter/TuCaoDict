package com.example.myapplication;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

public class MyLinearLayout {

    private TextView headLine;
    private GoodOrBadButton goodButton,badButton;
    //private TextViewAdapater adapater;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private HotWordActivity hotWordActivity;

    public MyLinearLayout(HotWordActivity hotWordActivity){
        this.setHotWordActivity(hotWordActivity);
        this.goodButton = new GoodOrBadButton(hotWordActivity);
        this.badButton = new GoodOrBadButton(hotWordActivity);
        //点击监听器
        this.goodButton.getImageButton().setOnClickListener(
                new GoodButtonOnClickListener(hotWordActivity,this.goodButton));
        this.badButton.getImageButton().setOnClickListener(
                new BadButtonOnClickListener(hotWordActivity,this.badButton));
    }

    public MyLinearLayout(LinearLayout linearLayout,TextView headLine, ImageButton goodButton,
                          TextView goodNumText, ImageButton badButton, TextView badNumText,
                          HotWordActivity hotWordActivity,ViewPager viewPager){

        this.setHotWordActivity(hotWordActivity);
        this.setLinearLayout(linearLayout);
        //this.setAdapater(adapater);
        this.goodButton = new GoodOrBadButton(goodButton,goodNumText,hotWordActivity);
        this.badButton = new GoodOrBadButton(badButton,badNumText,hotWordActivity);
        this.setHeadLine(headLine);
        this.setViewPager(viewPager);
        //点击监听器
        this.goodButton.getImageButton().setOnClickListener(
                new GoodButtonOnClickListener(hotWordActivity,this.goodButton));
        this.badButton.getImageButton().setOnClickListener(
                new BadButtonOnClickListener(hotWordActivity,this.badButton));
    }


    //set、get
    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setGoodButton(GoodOrBadButton goodButton) {
        this.goodButton = goodButton;
    }

    public void setBadButton(GoodOrBadButton badButton) {
        this.badButton = badButton;
    }

    /*public void setAdapater(TextViewAdapater adapater) {
        this.adapater = adapater;
    }*/

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setAllView(LinearLayout linearLayout, TextView headLine, ImageButton goodButton,
                           TextView goodNumText, ImageButton badButton, TextView badNumText){

        this.setLinearLayout(linearLayout);
        this.goodButton.setAllView(goodButton,goodNumText);
        this.badButton.setAllView(badButton,badNumText);
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public GoodOrBadButton getGoodButton() {
        return goodButton;
    }

    public GoodOrBadButton getBadButton() {
        return badButton;
    }

    /*public TextViewAdapater getAdapater() {
        return adapater;
    }*/

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

}
