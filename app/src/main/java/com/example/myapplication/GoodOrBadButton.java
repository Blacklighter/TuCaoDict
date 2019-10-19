package com.example.myapplication;

import android.content.Context;
import android.widget.ImageButton;
import android.view.View;
import android.widget.TextView;

public class GoodOrBadButton {

    private int number = 0;
    private ImageButton imageButton;
    private TextView numberText;
    private HotWordActivity hotWordActivity;
    private boolean isClick = false;

    public GoodOrBadButton(HotWordActivity hotWordActivity){
        this.setHotWordActivity(hotWordActivity);
    }

    public GoodOrBadButton(ImageButton imageButton,TextView numberText,
                           HotWordActivity hotWordActivity) {

        this.setImageButton(imageButton);
        this.setNumberText(numberText);
        this.setHotWordActivity(hotWordActivity);
    }

    public GoodOrBadButton(int number,ImageButton imageButton,TextView numberText,
                           HotWordActivity hotWordActivity) {

        this.setNumber(number);
        this.setImageButton(imageButton);
        this.setNumberText(numberText);
        this.setHotWordActivity(hotWordActivity);
    }


    //get与set、is
    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumberText(TextView numberText) {
        this.numberText = numberText;
    }

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public void setIsClick(boolean isClick) {
        isClick = isClick;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setAllView(ImageButton imageButton, TextView numberText){

        this.setImageButton(imageButton);
        this.setNumberText(numberText);
    }

    public int getNumber() {
        return number;
    }

    public TextView getNumberText() {
        return numberText;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

}
