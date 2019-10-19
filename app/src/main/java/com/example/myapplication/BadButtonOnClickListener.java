package com.example.myapplication;

import android.view.View;

public class BadButtonOnClickListener implements View.OnClickListener {

    private HotWordActivity hotWordActivity;
    private GoodOrBadButton badButton;

    public BadButtonOnClickListener(HotWordActivity hotWordActivity,GoodOrBadButton badButton){
        this.setHotWordActivity(hotWordActivity);
        this.setBadButton(badButton);
    }

    @Override
    public void onClick(View v) {
        //调用页面相应的方法
        this.hotWordActivity.clickBadButton(this.badButton);
    }


    //get与set
    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setBadButton(GoodOrBadButton badButton) {
        this.badButton = badButton;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public GoodOrBadButton getBadButton() {
        return badButton;
    }

}
