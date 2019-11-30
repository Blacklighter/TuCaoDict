package com.example.myapplication;

import android.view.View;

public class GoodButtonOnClickListener implements View.OnClickListener {

    private HotWordActivity hotWordActivity;
    private GoodOrBadButton goodButton;

    public GoodButtonOnClickListener(HotWordActivity hotWordActivity, GoodOrBadButton goodButton){
        this.setHotWordActivity(hotWordActivity);
        this.setGoodButton(goodButton);
    }

    @Override
    public void onClick(View v) {
        //调用页面相应的方法
        this.hotWordActivity.clickGoodButton(this.goodButton,v);
    }


    //get与set
    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setGoodButton(GoodOrBadButton goodButton) {
        this.goodButton = goodButton;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public GoodOrBadButton getGoodButton() {
        return goodButton;
    }

}
