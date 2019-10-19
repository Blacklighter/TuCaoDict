package com.example.myapplication;

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

public class MainActivity extends AppCompatActivity {

    private TextView headLine;//标题
    private ImageButton cameraButton;//相机按钮
    private EditText findEditText;//输入框
    private ImageButton findButton;//搜索按钮
    private com.nex3z.flowlayout.FlowLayout relevantContent;//热点词条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //从布局文件中获取所有的View
        this.headLine = (TextView)findViewById(R.id.head_line);
        this.cameraButton = (ImageButton)findViewById(R.id.camera_button);
        this.findEditText = (EditText)findViewById(R.id.find_edit);
        this.findButton = (ImageButton)findViewById(R.id.find_button);
        this.relevantContent = (com.nex3z.flowlayout.FlowLayout)findViewById(
                R.id.relevant_content_part);

        //设置相机、搜索点击事件监听器
        this.cameraButton.setOnClickListener(
                new CameraButtonOnClickListener(this.cameraButton,this));
        this.findButton.setOnClickListener(
                new FindButtonOnClickListener(this.findButton,this));

    }//onCreate

    //点击了“相机”按钮
    public void clickCameraButton(){

    }

    //点击了“搜索”按钮
    public void clickFindButton(){

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

}
