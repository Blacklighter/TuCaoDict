package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MeaningLinearLayout {

    private TextView headLine;
    private GoodOrBadButton goodButton,badButton;
    private TextView textPart;
    private HotWordActivity hotWordActivity;
    private LinearLayout linearLayout;

    public MeaningLinearLayout(HotWordActivity hotWordActivity){
        this.goodButton = new GoodOrBadButton(hotWordActivity);
        this.badButton = new GoodOrBadButton(hotWordActivity);
        this.setHotWordActivity(hotWordActivity);
        //点击监听器
        this.goodButton.getImageButton().setOnClickListener(
                new GoodButtonOnClickListener(hotWordActivity,this.goodButton));
        this.badButton.getImageButton().setOnClickListener(
                new BadButtonOnClickListener(hotWordActivity,this.badButton));
    }

    public MeaningLinearLayout(LinearLayout linearLayout,TextView headLine, ImageButton goodButton,
                               TextView goodNumText, ImageButton badButton, TextView badNumText,
                               TextView textPart, HotWordActivity hotWordActivity){

        this.setLinearLayout(linearLayout);
        this.setHotWordActivity(hotWordActivity);
        this.goodButton = new GoodOrBadButton(goodButton,goodNumText,hotWordActivity);
        this.badButton = new GoodOrBadButton(badButton,badNumText,hotWordActivity);
        this.setHeadLine(headLine);
        this.setTextPart(textPart);
        //点击监听器
        this.goodButton.getImageButton().setOnClickListener(
                new GoodButtonOnClickListener(hotWordActivity,this.goodButton));
        this.badButton.getImageButton().setOnClickListener(
                new BadButtonOnClickListener(hotWordActivity,this.badButton));
    }


    //get、set
    public void setBadButton(GoodOrBadButton badButton) {
        this.badButton = badButton;
    }

    public void setGoodButton(GoodOrBadButton goodButton) {
        this.goodButton = goodButton;
    }

    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setTextPart(TextView textPart) {
        this.textPart = textPart;
    }

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public void setAllView(LinearLayout linearLayout,TextView headLine, ImageButton goodButton,
                           TextView goodNumText, ImageButton badButton, TextView badNumText,
                           TextView textPart){

        this.setLinearLayout(linearLayout);
        this.goodButton.setAllView(goodButton,goodNumText);
        this.badButton.setAllView(badButton,badNumText);
        this.setHeadLine(headLine);
        this.setTextPart(textPart);
    }

    public GoodOrBadButton getBadButton() {
        return badButton;
    }

    public GoodOrBadButton getGoodButton() {
        return goodButton;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public TextView getTextPart() {
        return textPart;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

}
