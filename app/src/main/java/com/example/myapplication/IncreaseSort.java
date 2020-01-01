package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.Utils;
import com.liuguangqiang.ipicker.IPicker;
import com.squareup.picasso.Picasso;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.w3c.dom.Text;

import java.util.List;

public class IncreaseSort extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increase_sort);
        Button submit=findViewById(R.id.submit);
        final de.hdodenhof.circleimageview.CircleImageView addrress=(de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.address);
        final String[] imgPath = new String[1];
        IPicker.setOnSelectedListener(new IPicker.OnSelectedListener() {

            @Override
            public void onSelected(List<String> paths) {
                imgPath[0] = paths.get(0);
                Picasso.with(IncreaseSort.this).load("file://" +imgPath[0]).into(addrress);
            }


        });
        addrress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPicker.setLimit(1);
                IPicker.open(IncreaseSort.this);

            }
        });
        Button back=findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IncreaseSort.this,SortPage.class);
                startActivity(intent);
                IncreaseSort.this.finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText sortname=(EditText)findViewById(R.id.sortname);
                //Log.e("text",sortname.getText().toString()+"  "+imgPath[0]);

                Utils.mysql("insert into entry_kinds (kind_name) values ('"+sortname.getText().toString()+"')",new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        LemonBubble.showRoundProgress(IncreaseSort.this,"上传中...");
                        Utils.setKindImg(imgPath[0],sortname.getText().toString(),new Handler(){
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                super.handleMessage(msg);
                                new Handler(){
                                    @Override
                                    public void handleMessage(@NonNull Message msg) {
                                        super.handleMessage(msg);
                                        LemonBubble.forceHide();
                                        IncreaseSort.this.finish();
                                    }
                                }.sendEmptyMessageDelayed(0x11,3000);

                            }
                        },IncreaseSort.this);
                    }
                });

            }
        });
    }
}
