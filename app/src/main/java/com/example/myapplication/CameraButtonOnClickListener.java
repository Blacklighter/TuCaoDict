package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;

public class CameraButtonOnClickListener implements View.OnClickListener {

    private ImageButton cameraButton;
    private MainActivity mainActivity;

    CameraButtonOnClickListener(ImageButton cameraButton,MainActivity mainActivity){
        this.setCameraButton(cameraButton);
        this.setMainActivity(mainActivity);
    }

    @Override
    public void onClick(View v) {
        //调用页面相应的方法
        this.mainActivity.clickCameraButton();
    }


    //set、get
    public ImageButton getCameraButton() {
        return cameraButton;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setCameraButton(ImageButton cameraButton) {
        this.cameraButton = cameraButton;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
