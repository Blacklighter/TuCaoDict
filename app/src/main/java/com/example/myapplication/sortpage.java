package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yinglan.shadowimageview.ShadowImageView;

public class sortpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortpage);
       // ImageButton buttonone=findViewById(R.id.Buttonone);


        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.all);

        for(int i = 0; i <  20; i++){
            addOneKind("fdaf","");
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
        String[] kinds = {"fd","fdafa","fdsgsg"};

        return kinds;
    }

    private void addOneKind(String name,String img){
        LinearLayout all = (LinearLayout)findViewById(R.id.all);
        LinearLayout oneKind = (LinearLayout) getLayoutInflater().inflate(R.layout.kind,null);
        ShadowImageView shadow = oneKind.findViewById(R.id.shadow);
        shadow.setImageDrawable(getResources().getDrawable(R.mipmap.two));
        shadow.setImageShadowColor(getResources().getColor(R.color.colorPrimary) );
        shadow.setImageRadius(20);

        if(all.getChildCount() > 0){
            LinearLayout lastRow = (LinearLayout)all.getChildAt(all.getChildCount()-1);


            if(lastRow.getChildCount()%2 == 0){
                LinearLayout row = new LinearLayout(this);
                row.setOrientation(LinearLayout.HORIZONTAL);
                row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                row.setGravity(Gravity.CENTER);
                row.addView(oneKind);
                all.addView(row);
            }
            else{
                lastRow.addView(oneKind);
            }
        }
        else{
            LinearLayout row = new LinearLayout(this);
            row.setGravity(Gravity.CENTER);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.addView(oneKind);
            all.addView(row);
        }



        // grid.getChildAt()
    }
}
