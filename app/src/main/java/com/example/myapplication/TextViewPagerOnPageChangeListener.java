package com.example.myapplication;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.utils.Utils;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        //初始化按钮图片
        this.getLinearLayout().getGoodButton().getImageButton().setImageResource(R.drawable.good);
        this.getLinearLayout().getBadButton().getImageButton().setImageResource(R.drawable.bad);

        this.setTelNum(this.getLinearLayout().getTextViews().getTelNums().get(position));//当前的号码
        System.out.println(this.getLinearLayout().getTelNum());
        this.getLinearLayout().setJsonObject(this.getLinearLayout().getJsonObjects().get(position));//当前的内容

        try {
            //修改该组件中显示的按钮数值
            this.getLinearLayout().getGoodButton().upDate(
                    this.getLinearLayout().getJsonObject().getInt("like_num"));
            this.getLinearLayout().getBadButton().upDate(
                    this.getLinearLayout().getJsonObject().getInt("dislike_num"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Utils.mysql("SELECT * FROM like_dislike_record where entry_name = '" +
                this.hotWordActivity.getHeadLine().getText().toString() + "'and telephone1 = '15172609837'" +
                "and telephone2 ='"+this.getLinearLayout().getTelNum()+
                "'and module_name = '"+this.getLinearLayout().getModule_name()+"';",new Handler(){
            //Message传回，触发该回调函数

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results =(JSONArray)msg.obj;

                if(results.length() > 0){

                    for(int i = 0;i <= 1;i++){

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = results.getJSONObject(i);
                            if(jsonObject.getString("is_like").equals("true"))
                                getLinearLayout().getGoodButton().getImageButton().setImageResource(R.drawable.isgood);
                            else
                                getLinearLayout().getBadButton().getImageButton().setImageResource(R.drawable.isbad);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }

        });

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
