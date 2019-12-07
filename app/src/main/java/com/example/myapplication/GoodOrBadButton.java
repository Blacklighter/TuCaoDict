package com.example.myapplication;

import android.content.Context;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
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
        this.getImageButton().setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    public GoodOrBadButton(int number,ImageButton imageButton,TextView numberText,
                           HotWordActivity hotWordActivity) {

        this.setNumber(number);
        this.setImageButton(imageButton);
        this.setNumberText(numberText);
        this.setHotWordActivity(hotWordActivity);
    }

    //更新number并更改textView的值
    public void upDate(int number){
        this.setNumber(number);
        if (this.getNumber() > 100)
            this.getNumberText().setText("99+");
        else if(this.getNumber() == 0)
            this.getNumberText().setText("");
        else
            this.getNumberText().setText("" + this.getNumber());
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
        this.isClick = isClick;
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
