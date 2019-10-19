package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

public class ReturnButtonOnClickListener implements View.OnClickListener {

    private HotWordActivity hotWordActivity;
    private ImageButton returnButton;

    public ReturnButtonOnClickListener(HotWordActivity hotWordActivity,ImageButton returnButton){
        this.setHotWordActivity(hotWordActivity);
        this.setReturnButton(returnButton);
    }

    @Override
    public void onClick(View v) {
        //调用页面的点击方法
        this.hotWordActivity.clickReturnButton();
    }


    //get、set
    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public ImageButton getReturnButton() {
        return returnButton;
    }

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setReturnButton(ImageButton returnButton) {
        this.returnButton = returnButton;
    }

}
