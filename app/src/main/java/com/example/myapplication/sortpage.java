package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


public class sortpage extends AppCompatActivity {
    private double rate  = 2.0/5;
    private double top=1.0/8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortpage);


       // ImageButton buttonone=findViewById(R.id.Buttonone);


        ArrayList<String> names=getKinds();



        ArrayList<String> img =getImgs();

        for(int i = 0; i <  3; i++){
            addOneKind(names.get(i),img.get(i));
        }


//        buttonone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(sortpage.this,MainActivity.class);
//                startActivity(intent);
//                sortpage.this.finish();
//
//            }
//        });
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
    }
    private ArrayList<String> getImgs(){
        JSONArray rows = Utils.mysql("SELECT * FROM entry_kinds");
        ArrayList<String> arrayList = new ArrayList<>();

        for(int i = 0; i < rows.length(); i++){
            try {
                JSONObject row = rows.getJSONObject(i);
                String kind_img = null;
                kind_img = row.getString("kind_img");
                arrayList.add(kind_img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void addOneKind(String name, String img){
        MyApplication application = (MyApplication)getApplication();
        int width = application.getWidth();
        int height = application.getHeight();
        FlowLayout all = (FlowLayout)findViewById(R.id.all);
        RelativeLayout oneKind = (RelativeLayout) getLayoutInflater().inflate(R.layout.kind,null);
        ShapedImageView imageView = (ShapedImageView)oneKind.findViewById(R.id.kindImg);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)imageView.getLayoutParams();
       // Log.e("TeST",""+(float)width+"  "+(float)width*rate+"   "+(int)((float)width*rate));
        int newMargin =  (int)(width*rate*top);
        params.setMargins(newMargin,0,newMargin,0);
        params.height = (int)((float)width*rate);
        params.width = params.height;
        imageView.setLayoutParams(params);
        all.addView(oneKind);
        ImageView kindImg=(ImageView) oneKind.findViewById(R.id.kindImg);
        Picasso.with(this).load(img).into(kindImg);
        TextView kindText=(TextView)oneKind.findViewById(R.id.kindText);
        kindText.setText(name);
        ImageView imageButton=(ImageView)oneKind.findViewById(R.id.kindImg);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sortpage.this,SortPageNext.class);
                Bundle bundle=new Bundle();
                TextView textView=(TextView) (((RelativeLayout)v.getParent()).findViewById(R.id.kindText));
                String classname=(String)textView.getText();
                bundle.putCharSequence("classname",classname);
                intent.putExtras(bundle);
                startActivity(intent);
                sortpage.this.finish();
            }
        });
    }
}
