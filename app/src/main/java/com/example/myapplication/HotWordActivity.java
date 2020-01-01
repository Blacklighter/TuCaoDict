package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.utils.Utils;
import com.nex3z.flowlayout.FlowLayout;
import com.nightonke.boommenu.BoomButtons.HamButton;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotWordActivity extends AppCompatActivity {

    private ImageButton returnButton, writingButton;//返回按钮、添加按钮
    private TextView headLine;//标题
    private com.nex3z.flowlayout.FlowLayout flowLayout;//标题下的标签
    private MeaningLinearLayout meaning;//基本解释
    private MyLinearLayout condition, source, example;//适用情形、来龙去脉、范例
    private RelevantContent relevantContent;//相关槽点
    private String stringFromLastActivity;//从上一个页面传入的字符串
    private JSONArray allJsonObject,allLikeAndDislike;//服务器中得到的全部内容数组
    private JSONObject meaningObject;//基本释义内容

    //onCreate方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_word);

        //基本解释
        this.meaning = new MeaningLinearLayout((LinearLayout) findViewById(R.id.meaning),
                (TextView) findViewById(R.id.meaning_headLine),
                (ImageButton) findViewById(R.id.good_button_for_meaning),
                (TextView) findViewById(R.id.num_of_good_for_meaning),
                (ImageButton) findViewById(R.id.bad_button_for_meaning),
                (TextView) findViewById(R.id.num_of_bad_for_meaning),
                (TextView) findViewById(R.id.meaning_text_part), this);

        //适用情形
        this.condition = new MyLinearLayout((LinearLayout) findViewById(R.id.condition),
                (TextView) findViewById(R.id.condition_head_line),
                (ImageButton) findViewById(R.id.good_button_for_condition),
                (TextView) findViewById(R.id.num_of_good_for_condition),
                (ImageButton) findViewById(R.id.bad_button_for_condition),
                (TextView) findViewById(R.id.num_of_bad_for_condition),
                this, (ViewPager) findViewById(R.id.condition_part),"适用情形");

        //来龙去脉
        this.source = new MyLinearLayout((LinearLayout) findViewById(R.id.source),
                (TextView) findViewById(R.id.source_head_line),
                (ImageButton) findViewById(R.id.good_button_for_source),
                (TextView) findViewById(R.id.num_of_good_for_source),
                (ImageButton) findViewById(R.id.bad_button_for_source),
                (TextView) findViewById(R.id.num_of_bad_for_source),
                this, (ViewPager) findViewById(R.id.source_part),"来龙去脉");

        //范例
        this.example = new MyLinearLayout((LinearLayout) findViewById(R.id.example),
                (TextView) findViewById(R.id.example_head_line),
                (ImageButton) findViewById(R.id.good_button_for_example),
                (TextView) findViewById(R.id.num_of_good_for_example),
                (ImageButton) findViewById(R.id.bad_button_for_example),
                (TextView) findViewById(R.id.num_of_bad_for_example),
                this, (ViewPager) findViewById(R.id.example_part),"范例");

        //相关槽点
        this.relevantContent = new RelevantContent(
                (TextView) findViewById(R.id.relevant_content_head_line),
                (com.nex3z.flowlayout.FlowLayout) findViewById(R.id.relevant_content_part),
                this);

        //从布局文件中设置成员对象
        this.setReturnButton((ImageButton) findViewById(R.id.return_button));
        this.setWritingButton((ImageButton) findViewById(R.id.writing_button));
        this.setHeadLine((TextView) findViewById(R.id.head_line));
        this.setFlowLayout((com.nex3z.flowlayout.FlowLayout) findViewById(R.id.correlation_with_this));

        //设置部分监听器
        this.returnButton.setOnClickListener(
                new ReturnButtonOnClickListener(this, this.returnButton));
        this.writingButton.setOnClickListener(
                new WritingOnClickListener(this, this.writingButton));

        Intent intent = getIntent();//获取前一个页面传来的意图
        Bundle bundle = intent.getExtras();//打开包裹
        this.setStringFromLastActivity(bundle.getString("word"));//修改字符串

        if (bundle.getBoolean("ifFinded"))//判断标志位
            init1();//首页进入
        else
            init2();//详情页或首页标签进入

    }

    //点击了“返回”按钮，调用clickReturnButton()方法
    public void clickReturnButton() {
        //返回前一个页面
    }

    //点击了添加按钮，调用clickWritingButton()方法
    public void clickWritingButton() {
        //实现部分，较为复杂，如果需要动画，可能需要参考其他现成品
    }

    //两个重构方法、用于点击按钮之后改变相应的数据库数值
    //s是表示数据库中的数是增加还是减少，likeOrNo表示是like_num还是dislike_num
    //module_kind是模块名字,tel是对应的电话号码
    private void changeSQL(String s,String likeOrNo){
        Utils.mysql("update modules set "+ likeOrNo +"=" + likeOrNo + s + "1 " +//SQL
                "where entry_name = '" + this.getHeadLine().getText().toString() +"'" +
                "and module_name = '基本释义';",new Handler(){
            //Message传回，触发该回调函数
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        });
            if(s.equals("+"))
                Utils.mysql("insert into like_dislike_record(telephone1,telephone2,entry_name,module_name,islike) " +
                        "values('15172609837','"+getS(this.getMeaningObject())+"'" +
                        ",'"+this.getHeadLine().toString()+"','基本释义','"+getIsLike(s)+"');",new Handler(){
                    //Message传回，触发该回调函数
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }
                });
            else
                Utils.mysql("delete from like_dislike_record " +
                        "where telephone1 = '15172609837' and " +
                        "telephone2 = '" + getS(this.getMeaningObject()) +
                        "' and entry_name = '" + this.getHeadLine().toString() + "'" +
                        "and module_name = '基本释义' and is_like ='" + getIsLike(s) + "';" ,new Handler(){
                    //Message传回，触发该回调函数
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }
                });
    }
    private void changeSQL(MyLinearLayout layout,String s,String likeOrNo,String module_kind,String tel){
        Utils.mysql("update modules set "+ likeOrNo +"=" + likeOrNo + s + "1 " +//SQL
                        "where entry_name = '" + this.getHeadLine().getText().toString() +"'" +
                        "and module_name = '" + module_kind + "' and telephone = '"+tel+"';",
                new Handler(){
                    //Message传回，触发该回调函数
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }
                });
        if(s.equals("+"))
            Utils.mysql("insert into like_dislike_record(telephone1,telephone2,entry_name,module_name,islike) " +
                    "values('15172609837','"+layout.getTelNum()+"'" +
                    ",'"+this.getHeadLine().toString()+"','"+layout.getModule_name()+"','"+getIsLike(s)+"');",new Handler(){
                //Message传回，触发该回调函数
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }
            });
        else
            Utils.mysql("delete from like_dislike_record " +
                    "where telephone1 = '15172609837' and " +
                    "telephone2 = '"+layout.getTelNum()+
                    "' and entry_name = '"+this.getHeadLine().toString()+"'" +
                    "and module_name = '"+layout.getModule_name()+"' and is_like ='"+getIsLike(s)+"';",new Handler(){
                //Message传回，触发该回调函数
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }
            });
    }

    private String getS(JSONObject j){
        String s = null;
        try {
            s = j.getString("telephone");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }
    private String getIsLike(String s){
        return s.equals("like")?"true":"false";
    }

    //任何地方点击了“点赞”按钮，调用clickGoodButton()方法
    public void clickGoodButton(GoodOrBadButton goodButton,View v) {
        //修改图片和标志位、数值
        if(goodButton.isClick()){//从点过赞到未点赞

            //修改服务器中的数据
            if(v == this.getMeaning().getGoodButton().getImageButton()){//点击了“基本释义”一栏的按钮

                changeSQL("-","like_num");

            }
            //缺“相关资料”一栏的按钮的处理
            else if(v == getCondition().getGoodButton().getImageButton()){//点击了“适用情形”一栏的按钮

                changeSQL(this.getCondition(),"-","like_num","适用情形",this.getCondition().getTelNum());

            }
            else if(v == getSource().getGoodButton().getImageButton()){//点击了“来龙去脉”一栏的按钮

                changeSQL(this.getSource(),"-","like_num","来龙去脉",this.getSource().getTelNum());

            }
            else if(v == getExample().getGoodButton().getImageButton()){//点击了“范例”一栏的按钮

                changeSQL(this.getExample(),"-","like_num","范例",this.getExample().getTelNum());

            }

            //修改本地
            goodButton.setIsClick(false);
            goodButton.upDate(goodButton.getNumber() - 1);
            goodButton.getImageButton().setImageResource(R.drawable.good);

        }

        else {//从未点赞到点赞

            //修改服务器中的数据
            if (goodButton.isClick()) {//从点过赞到未点赞

                //修改服务器中的数据
                if (v == this.getMeaning().getGoodButton().getImageButton()) {//点击了“基本释义”一栏的按钮

                    changeSQL("+", "like_num");

                }
                //缺“相关资料”一栏的按钮的处理
                else if (v == getCondition().getGoodButton().getImageButton()) {//点击了“适用情形”一栏的按钮

                    changeSQL(this.getCondition(),"+", "like_num", "适用情形", this.getCondition().getTelNum());

                } else if (v == getSource().getGoodButton().getImageButton()) {//点击了“来龙去脉”一栏的按钮

                    changeSQL(this.getSource(),"+", "like_num", "来龙去脉", this.getSource().getTelNum());

                } else if (v == getExample().getGoodButton().getImageButton()) {//点击了“范例”一栏的按钮

                    changeSQL(this.getExample(),"+", "like_num", "范例", this.getExample().getTelNum());

                }

            }

            goodButton.setIsClick(true);
            goodButton.upDate(goodButton.getNumber() + 1);
            goodButton.getImageButton().setImageResource(R.drawable.isgood);

        }

    }

    //任何地方点击了“踩”按钮，调用clickBadButton()方法
    public void clickBadButton(GoodOrBadButton badButton,View v) {

        //修改图片和标志位、数值
        if(badButton.isClick()){//从踩到不踩

            //修改服务器中的数据
            if(v == this.getMeaning().getBadButton().getImageButton()){//点击了“基本释义”一栏的按钮

                changeSQL("-","dislike_num");

            }
            //缺“相关资料”一栏的按钮的处理
            else if(v == getCondition().getBadButton().getImageButton()){//点击了“适用情形”一栏的按钮

                changeSQL(this.getCondition(),"-","dislike_num","适用情形",this.getCondition().getTelNum());

            }
            else if(v == getSource().getBadButton().getImageButton()){//点击了“来龙去脉”一栏的按钮

                changeSQL(this.getSource(),"-","dislike_num","来龙去脉",this.getSource().getTelNum());

            }
            else if(v == getExample().getBadButton().getImageButton()){//点击了“范例”一栏的按钮

                changeSQL(this.getExample(),"-","dislike_num","范例",this.getExample().getTelNum());

            }

            //修改本地
            badButton.setIsClick(false);
            badButton.upDate(badButton.getNumber() - 1);
            badButton.getImageButton().setImageResource(R.drawable.bad);

        }
        else {//从不踩到踩

            //修改服务器中的数据
            if(v == this.getMeaning().getBadButton().getImageButton()){//点击了“基本释义”一栏的按钮

                changeSQL("+","dislike_num");

            }
            //缺“相关资料”一栏的按钮的处理
            else if(v == getCondition().getBadButton().getImageButton()){//点击了“适用情形”一栏的按钮

                changeSQL(this.getCondition(),"+","dislike_num","适用情形",this.getCondition().getTelNum());

            }
            else if(v == getSource().getBadButton().getImageButton()){//点击了“来龙去脉”一栏的按钮

                changeSQL(this.getSource(),"+","dislike_num","来龙去脉",this.getSource().getTelNum());

            }
            else if(v == getExample().getBadButton().getImageButton()){//点击了“范例”一栏的按钮

                changeSQL(this.getExample(),"+","dislike_num","范例",this.getExample().getTelNum());

            }

            //修改本地
            badButton.setIsClick(true);
            badButton.upDate(badButton.getNumber() + 1);
            badButton.getImageButton().setImageResource(R.drawable.isbad);
        }
    }

    //初始化页面(首页搜索进入时使用)(未写完)
    public void init1() {
        //提示窗口
        LemonBubble.showRight(this, "正在加载数据...");

        //然后进行异步查询，即使再耗时，此时UI线程也不会"卡顿"
        Utils.mysql("SELECT * FROM entry_overview where entry_name like '%" +
                this.getStringFromLastActivity() + "%';", new Handler() {
            //Message传回，触发该回调函数
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray results = (JSONArray) msg.obj;
                JSONObject jsonObject = null;


                if (results.length() > 0) {

                    //for循环找出最符合搜索的匹配元组(修改上面的SQL查询语句以简化这里的代码)
                    for (int i = 0; i < results.length(); i++) {
                        if (jsonObject == null) {
                            try {
                                jsonObject = results.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                if (jsonObject.getInt("hot_name")
                                        <= results.getJSONObject(i).getInt("hot_name"))
                                    jsonObject = results.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    //jsonObject是查询之后得到的最高热度匹配的元组，在这里实现后面的更新
                    //修改大标题
                    try {
                        getHeadLine().setText(jsonObject.getString("entry_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                initAll();//获取数据并初始化全部

                //提示窗口
                LemonBubble.showRight(HotWordActivity.this, "数据加载完成！", 1500);

            }

        });
    }

    //初始化页面(详情页点击“相关槽点”或首页点击“热门槽点”时使用)
    public void init2() {

        LemonBubble.showRoundProgress(HotWordActivity.this,"加载中");

        Utils.mysql("SELECT * FROM entry_overview where entry_name = '" +
                this.getStringFromLastActivity() + "';",true,new Handler(){
            //Message传回，触发该回调函数
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                JSONArray results = (JSONArray) msg.obj;
                JSONObject jsonObject = null;

                if (results.length() == 1) {

                    try {
                        jsonObject = results.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //jsonObject是查询之后得到的最高热度匹配的元组，在这里实现后面的更新
                    //修改大标题
                    try {
                        getHeadLine().setText(jsonObject.getString("entry_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String s = null;
                    try {
                        s = jsonObject.getString("entry_kinds");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String s1 = "";
                    for(int i = 0;i < s.length();i++){
                        if(s.charAt(i) != ' ')
                            s1 = s1 + s.charAt(i);
                        if((s.charAt(i) == ' '|| i == s.length() - 1) && s1.length() != 0){
                            createTextView(s1,getFlowLayout());
                            s1 = "";
                        }
                    }

                }

                initAll();//获取数据并初始化全部

            }

        });

    }

    //设置“相关分类”一栏
    private void initFlowLayout(){

        Utils.mysql("SELECT entry_kinds FROM entry_overview where entry_name = '" +
                getHeadLine().getText().toString() + "';",new Handler(){
            //Message传回，触发该回调函数

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                JSONArray results = (JSONArray)msg.obj;
                if(results.length() == 1){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = results.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String s = null;
                    try {
                        s = jsonObject.getString("entry_kinds");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String s1 = null;
                    for(int i = 0;i < s.length();i++){
                        if(s.charAt(i) != ' ')
                            s1 = s1 + s.charAt(i);
                        if(s.charAt(i) == ' '&& i == s.length() - 1 && s1.length() != 0){
                            createTextView(s1,getFlowLayout());
                            s1 = "";
                        }
                    }
                }

            }

        });

    }

    //设置“基本释义”一栏
    private void initMeaning() {

        if(getMeaningObject() != null)
            try {
                this.getMeaning().getGoodButton().upDate(this.getMeaningObject().getInt("like_num"));//点赞数
                this.getMeaning().getBadButton().upDate(this.getMeaningObject().getInt("dislike_num"));//踩数
                this.getMeaning().getTextPart().setText(this.getMeaningObject().getString("content"));//内容
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }

    //设置“适用情形“一栏
    private void initCondition(){

        //设置内容以及适配器
        changeLinearLayout(this.getCondition().getJsonObjects(),this.getCondition());

        //修改数值
        changeButtonNum(this.getCondition(),this.getCondition().getJsonObjects());

    }

    //设置“来龙去脉”一栏
    private void initSource(){

        //设置内容以及适配器
        changeLinearLayout(this.getSource().getJsonObjects(),this.getSource());

        //修改数值
        changeButtonNum(this.getSource(),this.getSource().getJsonObjects());

    }

    //设置“范例”一栏
    private void initExample(){

        //设置内容以及适配器
        changeLinearLayout(this.getExample().getJsonObjects(),this.getExample());

        //修改数值
        changeButtonNum(this.getExample(),this.getExample().getJsonObjects());

    }

    //设置“相关槽点”一栏
    private void initRelevantContent(){

    }

    //点击了“相关槽点”标签后触发
    public void clickTextView(String string) {
        Intent intent = new Intent(this, HotWordActivity.class);//声明一个意图
        Bundle bundle = new Bundle();//包裹
        bundle.putString("word", string);//加入字符串
        bundle.putBoolean("isFinded", false);//搜索进入详情页标志位
        intent.putExtras(bundle);//加入意图
        startActivity(intent);//启动
    }

    //新建一个新的标签
    private void createTextView(String string,com.nex3z.flowlayout.FlowLayout flowLayout) {
        final TextView textView = new TextView(this);
        textView.setText(string);
        MyStyleTextView myStyleTextView = new MyStyleTextView(textView, this);
        //设置监听器
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTextView(textView.getText().toString());
            }
        });

        flowLayout.addView(textView);

    }

    //从数据库一口气获得所有的内容
    private void initAll(){

        //内容、点赞数、踩数
        Utils.mysql("SELECT * FROM modules where entry_name = '" +
                this.getHeadLine().getText().toString() + "';",new Handler(){
            //Message传回，触发该回调函数

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                setAllJsonObject((JSONArray) msg.obj);//设置AllJsonObject
                handOutObject();//分发数据
                sortAll();//排序
                initMeaning();//“基本释义”
                initCondition();//"适用情形"
                initSource();//"来龙去脉"
                initExample();//"范例"

                //获得对该词条点赞以及未点赞的全部数据
                Utils.mysql("SELECT * FROM like_dislike_record where entry_name = '" +
                        getHeadLine().getText().toString() + "';",new Handler(){
                    //Message传回，触发该回调函数

                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);

                        setAllLikeAndDislike((JSONArray) msg.obj);//设置AllLikeAndDislike
                        changeAllButton();//改变所有的按钮样式

                        LemonBubble.showRight(HotWordActivity.this,"OK",1500);

                    }

                });

            }

        });

    }

    //把所有的数据整理分发
    private void handOutObject() {

        for(int i = 0;i < this.getAllJsonObject().length();i++)
            try {
                switch (this.getAllJsonObject().getJSONObject(i).getString("module_kind")){
                    case "基本释义":
                        this.setMeaningObject(this.getAllJsonObject().getJSONObject(i));
                        break;
                    case "适用情形":
                        this.getCondition().getJsonObjects().add(this.getAllJsonObject().getJSONObject(i));
                        break;
                    case "来龙去脉":
                        this.getSource().getJsonObjects().add(this.getAllJsonObject().getJSONObject(i));
                        break;
                    case "范例":
                        this.getExample().getJsonObjects().add(this.getAllJsonObject().getJSONObject(i));
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }

    //为"适用情形"、"来龙去脉"、"范例"按点赞数排序
    private void sortAll(){
        sortArrayList(this.getCondition().getJsonObjects());//“适用情形”
        sortArrayList(this.getSource().getJsonObjects());//“来龙去脉”
        sortArrayList(this.getExample().getJsonObjects());//“范例”
    }

    //排序算法
    private void sortArrayList(ArrayList<JSONObject> arrayList){

        int n;
        do{
            n = 0;
            for(int i = 0;i < arrayList.size() - 1;i++) {
                try {
                    if(arrayList.get(i).getInt("like_num") <
                            arrayList.get(i + 1).getInt("like_num")){
                        JSONObject jsonObject = arrayList.get(i);
                        arrayList.set(i,arrayList.get(i + 1));
                        arrayList.set(i + 1,jsonObject);
                        n++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }while (n != 0);

    }

    //为“适用情形”“来龙去脉”“范例”设置基本的适配器等内容
    private void changeLinearLayout(ArrayList<JSONObject> arrayList, final MyLinearLayout layout){

        for(int i = 0;i < arrayList.size();i++){

            JSONObject jsonObject = null;
            jsonObject = arrayList.get(i);

            //新建一个TextView
            TextView textView = new TextView(HotWordActivity.this);
            try {
                String string = jsonObject.getString("content");
                textView.setText(string);
                textView.setHeight(50);
                textView.setTextSize(20);
                textView.setMovementMethod(ScrollingMovementMethod.getInstance());//可滚动
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //将TextView加入到数组中
            layout.getTextViews().getmTextViewList().add(i,textView);

            try {
                //将作者的号码逐个加入到电话号码数组中
                layout.getTextViews().getTelNums().add(i,jsonObject.getString("telephone"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//for

        layout.getViewPager().setAdapter(layout.getTextViews());//设置适配器
        layout.getViewPager().setCurrentItem(0);//设置初始页面编号
        layout.setTelNum(layout.getTextViews().getTelNums().get(0));//设置当前页面对应号码
        layout.setJsonObject(arrayList.get(0));
        //为翻页视图添加翻页监听器
        layout.getViewPager().addOnPageChangeListener(
                new TextViewPagerOnPageChangeListener(layout,this));

        //changeButtonNum(layout,moduleName,layout.getTelNum());

    }

    //改变所有的按钮样式
    private void changeAllButton(){

        changeButton(this.getMeaning().getGoodButton(),this.getMeaning().getBadButton());//"基本释义"
        changeButton(this.getCondition().getTelNum(),"适用情形",//"适用情形"
                this.getCondition().getGoodButton(),this.getCondition().getBadButton());
        changeButton(this.getExample().getTelNum(),"范例",//"范例"
                this.getExample().getGoodButton(),this.getExample().getBadButton());
        changeButton(this.getSource().getTelNum(),"来龙去脉",//"来龙去脉"
                this.getSource().getGoodButton(),this.getSource().getBadButton());

    }

    ////修改点赞按钮图片以及点赞数数值以及标志位，tel2是被点赞用户的电话号码，moduleName是模块名
    private void changeButton(final GoodOrBadButton goodButton, final GoodOrBadButton badButton){

        for(int i = 0,j = 0;i < this.getAllLikeAndDislike().length() && j != 2;i++){

            try {

                //“点赞”部分
                if(this.getAllLikeAndDislike().getJSONObject(i).getString("telephone1").equals("15172609837") &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("module_name").equals("基本释义") &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("is_like").equals("true")){

                    goodButton.getImageButton().setImageResource(R.drawable.isgood);
                    goodButton.setIsClick(true);
                    j++;

                }

                //“踩”部分
                if(this.getAllLikeAndDislike().getJSONObject(i).getString("telephone1").equals("15172609837") &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("module_name").equals("基本释义") &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("is_like").equals("false")){

                    badButton.getImageButton().setImageResource(R.drawable.isbad);
                    badButton.setIsClick(true);
                    j++;

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    private void changeButton(String tel2, String moduleName,
                              final GoodOrBadButton goodButton, final GoodOrBadButton badButton){

        for(int i = 0,j = 0;i < this.getAllLikeAndDislike().length() && j != 2;i++){

            try {

                //“点赞”部分
                if(this.getAllLikeAndDislike().getJSONObject(i).getString("telephone1").equals("15172609837") &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("telephone2").equals(tel2) &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("module_name").equals(moduleName) &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("is_like").equals("true")){

                    goodButton.getImageButton().setImageResource(R.drawable.isgood);
                    goodButton.setIsClick(true);
                    j++;

                }

                //“踩”部分
                if(this.getAllLikeAndDislike().getJSONObject(i).getString("telephone1").equals("15172609837") &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("telephone2").equals(tel2) &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("module_name").equals(moduleName) &&
                        this.getAllLikeAndDislike().getJSONObject(i).getString("is_like").equals("false")){

                    goodButton.getImageButton().setImageResource(R.drawable.isgood);
                    goodButton.setIsClick(true);
                    j++;

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    //修改“点赞”“踩”按钮对应的数值
    private void changeButtonNum(final MyLinearLayout layout,ArrayList<JSONObject> arrayList){

        //修改点赞数
        try {
            layout.getGoodButton().upDate(arrayList.get(0).getInt("like_num"));//点赞数
            layout.getBadButton().upDate(arrayList.get(0).getInt("dislike_num"));//踩数
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //set、get
    public void setAllLikeAndDislike(JSONArray allLikeAndDislike) {
        this.allLikeAndDislike = allLikeAndDislike;
    }

    public void setMeaningObject(JSONObject meaningObject) {
        this.meaningObject = meaningObject;
    }

    public void setAllJsonObject(JSONArray allJsonObject) {
        this.allJsonObject = allJsonObject;
    }

    public void setFlowLayout(FlowLayout flowLayout) {
        this.flowLayout = flowLayout;
    }

    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setCondition(MyLinearLayout condition) {
        this.condition = condition;
    }

    public void setMeaning(MeaningLinearLayout meaning) {
        this.meaning = meaning;
    }

    public void setReturnButton(ImageButton returnButton) {
        this.returnButton = returnButton;
    }

    public void setExample(MyLinearLayout example) {
        this.example = example;
    }

    public void setSource(MyLinearLayout source) {
        this.source = source;
    }

    public void setWritingButton(ImageButton writingButton) {
        this.writingButton = writingButton;
    }

    public void setRelevantContent(RelevantContent relevantContent) {
        this.relevantContent = relevantContent;
    }

    public void setStringFromLastActivity(String stringFromLastActivity) {
        this.stringFromLastActivity = stringFromLastActivity;
    }

    public FlowLayout getFlowLayout() {
        return flowLayout;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public ImageButton getReturnButton() {
        return returnButton;
    }

    public ImageButton getWritingButton() {
        return writingButton;
    }

    public MeaningLinearLayout getMeaning() {
        return meaning;
    }

    public MyLinearLayout getCondition() {
        return condition;
    }

    public MyLinearLayout getExample() {
        return example;
    }

    public MyLinearLayout getSource() {
        return source;
    }

    public RelevantContent getRelevantContent() {
        return relevantContent;
    }

    public String getStringFromLastActivity() {
        return stringFromLastActivity;
    }

    public JSONArray getAllJsonObject() {
        return allJsonObject;
    }

    public JSONObject getMeaningObject() {
        return meaningObject;
    }

    public JSONArray getAllLikeAndDislike() {
        return allLikeAndDislike;
    }
}
