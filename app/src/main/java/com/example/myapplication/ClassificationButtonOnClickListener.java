package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;

public class ClassificationButtonOnClickListener implements View.OnClickListener {

    private ImageButton classificationButton;
    private MainActivity mainActivity;

    public ClassificationButtonOnClickListener(ImageButton classificationButton,MainActivity mainActivity){
        this.setClassificationButton(classificationButton);
        this.setMainActivity(mainActivity);
    }

    @Override
    public void onClick(View v) {
        this.getMainActivity().clickClassificationButton();
    }


    //get、set
    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public ImageButton getClassificationButton() {
        return classificationButton;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setClassificationButton(ImageButton classificationButton) {
        this.classificationButton = classificationButton;
    }

}
