package com.example.myapplication;

import androidx.viewpager.widget.ViewPager;

import org.json.JSONException;

public class TextViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private MyLinearLayout linearLayout;
    private String telNum = "";
    private HotWordActivity hotWordActivity;

    public TextViewPagerOnPageChangeListener(MyLinearLayout linearLayout, HotWordActivity activity){
        this.setLinearLayout(linearLayout);
        this.setHotWordActivity(activity);
    }

    //过程中触发
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //优化时可以写
    }

    //状态改变时触发，0静止，1正在滑动，2滑动结束
    @Override
    public void onPageScrollStateChanged(int state) {
        //优化可以写
    }

    //结束时触发
    @Override//没有写完
    public void onPageSelected(int position) {

        this.setTelNum(this.getLinearLayout().getTextViews().getTelNums().get(position));//当前的号码
        this.getLinearLayout().setJsonObject(this.getLinearLayout().getJsonObjects().get(position));//当前的内容

        try {
            this.getLinearLayout().getGoodButton().upDate(
                    this.getLinearLayout().getJsonObject().getInt("like_num"));
            this.getLinearLayout().getBadButton().upDate(
                    this.getLinearLayout().getJsonObject().getInt("dislike_num"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public MyLinearLayout getLinearLayout() {
        return linearLayout;
    }

    public String getTelNum() {
        return telNum;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public void setLinearLayout(MyLinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

}
