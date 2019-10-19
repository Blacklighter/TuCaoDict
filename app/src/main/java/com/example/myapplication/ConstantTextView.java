package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

public class ConstantTextView extends TextView implements View.OnClickListener {

    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public ConstantTextView(Context context) {
        super(context);
        this.context = context;

        //设置背景
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.tag_shape,null);
        this.setBackground(drawable);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
    }

}
