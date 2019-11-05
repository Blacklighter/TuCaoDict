package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;

public class MineButtonOnClickListener implements View.OnClickListener {

    private ImageButton mineButton;
    private MainActivity mainActivity;

    public MineButtonOnClickListener(ImageButton mineButton,MainActivity mainActivity){
        this.setMainActivity(mainActivity);
        this.setMineButton(mineButton);
    }

    @Override
    public void onClick(View v) {
        //调用相应页面的方法
        this.mainActivity.clickMineButton();
    }


    //get、set
    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public ImageButton getMineButton() {
        return mineButton;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setMineButton(ImageButton mineButton) {
        this.mineButton = mineButton;
    }

}
