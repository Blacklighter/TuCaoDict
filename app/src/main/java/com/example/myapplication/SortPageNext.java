package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SortPageNext extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_page_next);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String classname=bundle.getString("classname");//是什么分类
        addList(getText(classname));
        Button backbutton=(Button)findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SortPageNext.this, SortPage.class);
                startActivity(intent);
            }
        });

    }
    private void addList(ArrayList<String> name){
        ListView mylistview=(ListView)findViewById(R.id.listview);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(SortPageNext.this,R.layout.card,R.id.card,name);
        mylistview.setAdapter(myAdapter);
        mylistview.setOnItemClickListener(this);
    }
    private ArrayList<String> getText(String classname){
        JSONArray rows = Utils.mysql("SELECT * FROM entry_overview WHERE entry_kinds like '%"+classname+"%'and state like '通过'" );
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
        Intent intent = new Intent(SortPageNext.this, HotWordActivity.class);
        Bundle bundle=new Bundle();
        String string;
        TextView textView=(TextView)(view.findViewById(R.id.card));
        string=(String)textView.getText();
        bundle.putCharSequence("word",string);
        bundle.putBoolean("isFinded",false);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

