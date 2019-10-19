package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

public class WritingOnClickListener implements View.OnClickListener {

    private HotWordActivity hotWordActivity;
    private ImageButton writingButton;

    public WritingOnClickListener(HotWordActivity hotWordActivity,ImageButton writingButton){
        this.setHotWordActivity(hotWordActivity);
        this.setWritingButton(writingButton);
    }

    //点击方法
    @Override
    public void onClick(View v) {
        //调用页面的点击方法
        this.hotWordActivity.clickWritingButton();
    }


    //get、set
    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public ImageButton getWritingButton() {
        return writingButton;
    }

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setWritingButton(ImageButton writingButton) {
        this.writingButton = writingButton;
    }

}
