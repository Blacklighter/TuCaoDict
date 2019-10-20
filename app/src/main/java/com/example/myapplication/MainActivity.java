package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.nex3z.flowlayout.FlowLayout;
import android.content.Intent;
import android.media.Image;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.myapplication.utils.Utils;
import com.liuguangqiang.ipicker.IPicker;
import net.lemonsoft.lemonbubble.LemonBubble;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Handler handler = null;

    private TextView headLine;//标题
    private ImageButton cameraButton,mineButton,classificationButton,findButton;//相机、我的、分类、搜索
    private EditText findEditText;//输入框
    private com.nex3z.flowlayout.FlowLayout relevantContent;//热点词条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //从布局文件中获取所有的View
        this.headLine = (TextView)findViewById(R.id.head_line);
        this.cameraButton = (ImageButton)findViewById(R.id.camera_button);
        this.findButton = (ImageButton)findViewById(R.id.find_button);
        this.mineButton = (ImageButton)findViewById(R.id.mine_button);
        this.classificationButton = (ImageButton)findViewById(R.id.classification_button);
        this.findEditText = (EditText)findViewById(R.id.find_edit);
        this.relevantContent = (com.nex3z.flowlayout.FlowLayout)findViewById(
                R.id.relevant_content_part);

        //设置相机、搜索、我的、分类点击事件监听器
        this.cameraButton.setOnClickListener(
                new CameraButtonOnClickListener(this.cameraButton,this));
        this.findButton.setOnClickListener(
                new FindButtonOnClickListener(this.findButton,this));
        this.getClassificationButton().setOnClickListener(
                new ClassificationButtonOnClickListener(this.getClassificationButton(),this));
        this.getMineButton().setOnClickListener(
                new MineButtonOnClickListener(this.getMineButton(),this));

    }//onCreate

    //点击了“相机”按钮
    public void clickCameraButton(){

    }

    //点击了“搜索”按钮或者点击了下方热度高的标签
    public void find(){
        String string = this.getFindEditText().getText().toString();
        if(string.length() != 0){
            doIntent(true,string);//启动意图，新建一个详情页
        }
        else{
            //提示用户不要输入空字符串
        }
    }

    //点击了“我的”按钮，进入我的界面
    public void clickMineButton(){
        Intent intent = new Intent(this,MineActivity.class);//声明一个意图
        startActivity(intent);//启动
    }

    //点击了“分类”按钮
    public void clickClassificationButton(){
        Intent intent = new Intent(this,sortpage.class);//声明一个意图
        startActivity(intent);//启动
    }

    //点击了热门槽点标签
    public void clickTextView(String string){
        if(string.length() != 0){
            doIntent(false,string);//启动意图，新建一个详情页
        }
        else{
            //提示用户不要输入空字符串
        }
    }

    //做一个意图方法并且启动,目标页面是详情页
    public void doIntent(boolean isFinded,String string){
        Intent intent = new Intent(this,HotWordActivity.class);//声明一个意图
        Bundle bundle = new Bundle();//包裹
        bundle.putString("word",string);//加入字符串
        bundle.putBoolean("isFinded",isFinded);//搜索进入详情页标志位
        intent.putExtras(bundle);//加入意图
        startActivity(intent);//启动
    }

    //新建一个新的标签
    public void createTextView(String string){
        final TextView textView = new TextView(this);
        textView.setText(string);
        MyStyleTextView myStyleTextView = new MyStyleTextView(textView,this);
        this.getRelevantContent().addView(textView);
        //设置监听器
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTextView(textView.getText().toString());
            }
        });
    }


    //set、get
    public ImageButton getCameraButton() {
        return cameraButton;
    }

    public TextView getHeadLine() {
        return headLine;
    }

    public EditText getFindEditText() {
        return findEditText;
    }

    public FlowLayout getRelevantContent() {
        return relevantContent;
    }

    public ImageButton getFindButton() {
        return findButton;
    }

    public ImageButton getClassificationButton() {
        return classificationButton;
    }

    public ImageButton getMineButton() {
        return mineButton;
    }

    public void setCameraButton(ImageButton cameraButton) {
        this.cameraButton = cameraButton;
    }

    public void setRelevantContent(FlowLayout relevantContent) {
        this.relevantContent = relevantContent;
    }

    public void setHeadLine(TextView headLine) {
        this.headLine = headLine;
    }

    public void setFindButton(ImageButton findButton) {
        this.findButton = findButton;
    }

    public void setFindEditText(EditText findEditText) {
        this.findEditText = findEditText;
    }

    public void setClassificationButton(ImageButton classificationButton) {
        this.classificationButton = classificationButton;
    }

    public void setMineButton(ImageButton mineButton) {
        this.mineButton = mineButton;
    }

}
