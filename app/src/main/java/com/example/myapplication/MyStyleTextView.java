package com.example.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

public class MyStyleTextView {

    private Drawable myDrawable;
    private AppCompatActivity activity;
    private TextView textView;

    public MyStyleTextView(TextView textView,AppCompatActivity activity) {
        this.setActivity(activity);
        this.setTextView(textView);
        this.setMyDrawable(activity.getResources().getDrawable(R.drawable.tag_shape));
        this.getTextView().setBackground(this.getMyDrawable());
    }


    //get、set
    public AppCompatActivity getActivity() {
        return activity;
    }

    public Drawable getMyDrawable() {
        return myDrawable;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setMyDrawable(Drawable myDrawable) {
        this.myDrawable = myDrawable;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

}
