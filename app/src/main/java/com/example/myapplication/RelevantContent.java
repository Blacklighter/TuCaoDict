package com.example.myapplication;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;

public class RelevantContent {

    private TextView headLine;
    private com.nex3z.flowlayout.FlowLayout flowLayout;
    private HotWordActivity hotWordActivity;

    public RelevantContent(HotWordActivity hotWordActivity){
        this.setHotWordActivity(hotWordActivity);
    }

    public RelevantContent(TextView headLine,com.nex3z.flowlayout.FlowLayout flowLayout,
                           HotWordActivity hotWordActivity){

        this.setHotWordActivity(hotWordActivity);
        this.setFlowLayout(flowLayout);
        this.setHeadLine(headLine);
    }


    //set、get
    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setFlowLayout(FlowLayout flowLayout) {
        this.flowLayout = flowLayout;
    }

    public void setHotWordActivity(HotWordActivity hotWordActivity) {
        this.hotWordActivity = hotWordActivity;
    }

    public void setAllView(TextView headLine,com.nex3z.flowlayout.FlowLayout flowLayout){

        this.setFlowLayout(flowLayout);
        this.setHeadLine(headLine);
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public FlowLayout getFlowLayout() {
        return flowLayout;
    }

    public HotWordActivity getHotWordActivity() {
        return hotWordActivity;
    }

}
