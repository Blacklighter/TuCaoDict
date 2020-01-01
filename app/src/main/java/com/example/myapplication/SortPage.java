package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.utils.Utils;
import com.nex3z.flowlayout.FlowLayout;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


public class SortPage extends AppCompatActivity {
    private double rate  = 2.0/5;//组件占屏幕宽度的比例
    private double top=1.0/8;//组件距离屏幕两侧的边距占屏幕总宽度的比例


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if(((MyApplication)getApplication()).getAccount().identity.equal("普通用户"))
           // findViewById(R.id.increase).setVisibility(View.GONE);

        setContentView(R.layout.activity_sortpage);
        ArrayList<String> names=getKinds();//用来记录从数据库得到的分类名
        ArrayList<String> img =getImgs();//用来记录从数据库得到的分类对应图片的地址
        for(int i = 0; i <  img.size(); i++){
            addOneKind(names.get(i),img.get(i));//将分类名和图片一一对应的加入布局。
        }



    }

    private ArrayList<String> getKinds(){
        JSONArray rows = Utils.mysql("SELECT * FROM entry_kinds");
        ArrayList<String> arrayList = new ArrayList<>();

            for(int i = 0; i < rows.length(); i++){
                try {
                    JSONObject row = rows.getJSONObject(i);
                    String kind_name = null;
                    kind_name = row.getString("kind_name");
                    arrayList.add(kind_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        return arrayList;
    }//从数据库中得到分类名，返回一个ArrayList<String>数组。
    private ArrayList<String> getImgs(){
        JSONArray rows = Utils.mysql("SELECT * FROM entry_kinds");
        ArrayList<String> arrayList = new ArrayList<>();

        for(int i = 0; i < rows.length(); i++){
            try {
                JSONObject row = rows.getJSONObject(i);
                String kind_img = null;
                kind_img = row.getString("kind_img");
                Log.e("name:",""+kind_img);
                arrayList.add(kind_img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }//从数据库中得到图片地址，返回一个ArrayList<String>数组。

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void addOneKind(String name, String img){//将一个对应的图片和类名加入布局中。
        MyApplication application = (MyApplication)getApplication();//实例化MyApplication类
        int width = application.getWidth();//得到屏幕宽度
        int height = application.getHeight();//得到屏幕高度
        FlowLayout all = (FlowLayout)findViewById(R.id.all);//获取id为all的组件
        RelativeLayout oneKind = (RelativeLayout) getLayoutInflater().inflate(R.layout.kind,null);
        ShapedImageView imageView = (ShapedImageView)oneKind.findViewById(R.id.kindImg);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)imageView.getLayoutParams();
        int newMargin =  (int)(width*rate*top);
        params.setMargins(newMargin,0,newMargin,0);
        params.height = (int)((float)width*rate);//设置一个all组件的高度
        params.width = params.height;//设置一个all组件的宽度
        imageView.setLayoutParams(params);
        all.addView(oneKind);//把onekind加入all组件中
        ImageView kindImg=(ImageView) oneKind.findViewById(R.id.kindImg);
        Picasso.with(this).load(img).into(kindImg);//获取网图img为网图地址
        TextView kindText=(TextView)oneKind.findViewById(R.id.kindText);
        kindText.setText(name);//设置类名name
        ImageView imageButton=(ImageView)oneKind.findViewById(R.id.kindImg);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//设置点击每一个类的点击事件，跳转到分类页的下一个页面，分类列表页，并将点击的哪一个类传过去，在数据库中查找，动态创建新的列表页
                Intent intent=new Intent(SortPage.this,SortPageNext.class);
                Bundle bundle=new Bundle();
                TextView textView=(TextView) (((RelativeLayout)v.getParent()).findViewById(R.id.kindText));
                String classname=(String)textView.getText();
                bundle.putCharSequence("classname",classname);
                intent.putExtras(bundle);
                startActivity(intent);
                SortPage.this.finish();
            }
        });


        ImageView incraese=(ImageView)findViewById(R.id.increase);
        incraese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//设置点击每一个类的点击事件，跳转到分类页的下一个页面，分类列表页，并将点击的哪一个类传过去，在数据库中查找，动态创建新的列表页
                Intent intent=new Intent(SortPage.this,IncreaseSort.class);
                startActivity(intent);
                SortPage.this.finish();
            }
        });
    }
}
