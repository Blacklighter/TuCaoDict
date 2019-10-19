package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;

public class FindButtonOnClickListener implements View.OnClickListener {

    private ImageButton findButton;
    private MainActivity mainActivity;

    public FindButtonOnClickListener(ImageButton findButton,MainActivity mainActivity){
        this.setFindButton(findButton);
        this.setMainActivity(mainActivity);
    }

    @Override
    public void onClick(View v) {
        //调用页面相应的方法
        this.mainActivity.clickFindButton();
    }


    //get、set
    public ImageButton getFindButton() {
        return findButton;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setFindButton(ImageButton findButton) {
        this.findButton = findButton;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
