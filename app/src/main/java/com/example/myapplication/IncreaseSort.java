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

import org.w3c.dom.Text;

import java.util.List;

public class IncreaseSort extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increase_sort);
        Button submit=findViewById(R.id.submit);
        Button addrress=(Button)findViewById(R.id.address);
        final String[] imgPath = new String[1];
        IPicker.setOnSelectedListener(new IPicker.OnSelectedListener() {

            @Override
            public void onSelected(List<String> paths) {
                imgPath[0] = paths.get(0);
            }


        });
        addrress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPicker.setLimit(1);
                IPicker.open(IncreaseSort.this);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText sortname=(EditText)findViewById(R.id.sortname);
                Log.e("text",sortname.getText().toString()+"  "+imgPath[0]);

                Utils.mysql("insert into entry_kinds (kind_name) values ('"+sortname.getText().toString()+"')",new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);

                        Utils.setKindImg(imgPath[0],sortname.getText().toString(),new Handler(){
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                super.handleMessage(msg);
                            }
                        },IncreaseSort.this);
                    }
                });

            }
        });
    }
}
