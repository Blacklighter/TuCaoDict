package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class MyApplication extends Application {
    private int width;
    private int height;
    

    @Override
    public void onCreate() {
        super.onCreate();
        Point p = new Point();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(p);
        width = p.x;
        height = p.y;//获取屏幕的宽高。
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
