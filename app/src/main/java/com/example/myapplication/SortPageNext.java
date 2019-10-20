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

import com.example.myapplication.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        addList(getText(classname));
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
    private void addList(ArrayList<String> name){
        ListView mylistview=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(SortPageNext.this,R.layout.sortpagetext,R.id.textview,name);
        mylistview.setAdapter(myAdapter);
        mylistview.setOnItemClickListener(this);
    }
    private ArrayList<String> getText(String classname){
        JSONArray rows = Utils.mysql("SELECT * FROM entry_overview WHERE entry_kinds like '%"+classname+"%'" );
        ArrayList<String> arrayList = new ArrayList<>();

        for(int i = 0; i < rows.length(); i++){
            try {
                JSONObject row = rows.getJSONObject(i);
                String entry_name = null;
                entry_name = row.getString("entry_name");
                arrayList.add(entry_name);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SortPageNext.this, MainActivity.class);
        Bundle bundle=new Bundle();
        String string;
        TextView textView=(TextView)(view.findViewById(R.id.textview));
        string=(String)textView.getText();
        bundle.putCharSequence("text",string);
        intent.putExtras(bundle);
        startActivity(intent);
        SortPageNext.this.finish();
    }
}

