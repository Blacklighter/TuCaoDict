package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortPageNext extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_page_next);
        //ArrayList<String> text;
        String text[]={"蔡徐坤","罗志祥","小猪","panyuanm","ssdhd","sidjjjd"};
        ListView mylistview=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(SortPageNext.this,R.layout.sortpagetext,R.id.textview,text);
        mylistview.setAdapter(myAdapter);

    }
    private void addlist(String name){
        LinearLayout textview = (LinearLayout) getLayoutInflater().inflate(R.layout.sortpagetext,null);
        TextView textView=findViewById(R.id.textview);
        textView.setText(name);
        //textView.setTextSize(20);
        ListView listView=findViewById(R.id.listview);
        listView.addView(textView);

    }
}

