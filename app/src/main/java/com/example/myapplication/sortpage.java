package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;
import com.squareup.picasso.Picasso;

import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;


public class sortpage extends AppCompatActivity {
    private double rate  = 2.0/5;
    private double top=1.0/8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortpage);


       // ImageButton buttonone=findViewById(R.id.Buttonone);


        String[] name=getKinds();
        String[] img =getImage();

        for(int i = 0; i <  3; i++){
            addOneKind(name[i],img[i]);
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

    private String[] getKinds(){
        String[] name = {"fd","fdafa","fdsgsg"};
        return name;
    }
    private String[] getImage(){
        String[] img={"https://i01piccdn.sogoucdn.com/5e58f2161a140620","https://i01piccdn.sogoucdn.com/893c986280c0d7dd","https://i02piccdn.sogoucdn.com/e3fd076be9b8374c"};
        return img;
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
                startActivity(intent);
                sortpage.this.finish();
            }
        });
       // ImageView targetImageView = (ImageView) findViewById(R.id.ImageView);
       // String Url = "http://218.192.170.132/1.jpg";

       // Picasso
           //     .with(this)
         //       .load(Url)
           //     .into(targetImageView);
//        shadow.setImageRadius(20);

//        if(all.getChildCount() > 0){
//            LinearLayout lastRow = (LinearLayout)all.getChildAt(all.getChildCount()-1);
//
//
//            if(lastRow.getChildCount()%2 == 0){
//               // LinearLayout row = new LinearLayout(this);
//              //  row.setOrientation(LinearLayout.HORIZONTAL);
//              //  row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
//               // row.setGravity(Gravity.CENTER);
//              //  row.addView(oneKind);
//              //  all.addView(row);
//            }
//            else{
//                lastRow.addView(oneKind);
//            }
//        }
//        else{
//            LinearLayout row = new LinearLayout(this);
//            row.setGravity(Gravity.);
//           // row.setGravity(Gravity.CENTER);
//            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
//            row.setOrientation(LinearLayout.HORIZONTAL);
//            row.addView(oneKind);
//            all.addView(row);
//        }



        // grid.getChildAt()
    }
}
