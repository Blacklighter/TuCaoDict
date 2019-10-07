package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortPageNext extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_page_next);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String classname=bundle.getString("classname");
        addList(getText("classname"));
        Button backbutton=(Button)findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SortPageNext.this,sortpage.class);
                startActivity(intent);
                SortPageNext.this.finish();
            }
        });

    }
    private void addList(String[] name){
        Log.e("TST","dafasfaas");
        ListView mylistview=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(SortPageNext.this,R.layout.sortpagetext,R.id.textview,name);
        mylistview.setAdapter(myAdapter);
        mylistview.setOnItemClickListener(this);
    }
    private String[] getText(String classname){
        String text[]={"蔡徐坤","罗志祥","小猪","panyuanm","ssdhd","sidjjjd"};
        return  text;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SortPageNext.this, MainActivity.class);
        Bundle bundle=new Bundle();
        String string;
        TextView textView=(TextView)(view.findViewById(R.id.textview));
        string=(String)textView.getText();
        Log.e("text",string);
        bundle.putCharSequence("text",string);
        startActivity(intent);
        SortPageNext.this.finish();
    }
}

