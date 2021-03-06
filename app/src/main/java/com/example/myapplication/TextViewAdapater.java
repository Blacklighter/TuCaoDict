package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

//文本适配器
public class TextViewAdapater extends PagerAdapter {

    private Context mContext;//声明一个上下文对象

    //声明一个文本视图队列
    private ArrayList<TextView> mTextViewList = new ArrayList<TextView>();
    private ArrayList<String> telNums = new ArrayList<String>();

    public TextViewAdapater(Context context){
        this.setmContext(context);
    }

    //构造函数
    public TextViewAdapater(Context context,ArrayList<TextView> textViews,ArrayList<String> telNums){
        mContext = context;
        mTextViewList = textViews;
        this.telNums = telNums;
    }

    //获取页面项的个数
    public int getCount(){
        return mTextViewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0,Object arg1){
        return arg0 == arg1;
    }

    //从容器中销毁指定位置的页面
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView(mTextViewList.get(position));
    }

    //实例化指定位置的页面，并将其添加到容器中
    public Object instantiateItem(ViewGroup container,int position){
        container.addView(mTextViewList.get(position));
        return mTextViewList.get(position);
    }

    public ArrayList<String> getTelNums() {
        return telNums;
    }

    public ArrayList<TextView> getmTextViewList() {
        return mTextViewList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmTextViewList(ArrayList<TextView> mTextViewList) {
        this.mTextViewList = mTextViewList;
    }

    public void setTelNums(ArrayList<String> telNum) {
        this.telNums = telNum;
    }
}
