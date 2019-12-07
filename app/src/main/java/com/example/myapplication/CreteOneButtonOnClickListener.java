package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;

public class CreteOneButtonOnClickListener implements View.OnClickListener {

    private ImageButton creteOneButton;
    private MainActivity mainActivity;

    public CreteOneButtonOnClickListener(ImageButton creteOneButton, MainActivity mainActivity){
        this.setCreteOneButton(creteOneButton);
        this.setMainActivity(mainActivity);
    }

    @Override
    public void onClick(View v) {
        //调用页面相应方法
        this.getMainActivity().clickCreteOneButton();
    }


    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public ImageButton getCreteOneButton() {
        return creteOneButton;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setCreteOneButton(ImageButton creteOneButton) {
        this.creteOneButton = creteOneButton;
    }

}
