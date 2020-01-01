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

public class Review extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        addList(getText());
        Button backbutton=(Button)findViewById(R.id.backbutton2);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review.this.finish();
            }
        });

    }
    private void addList(ArrayList<String> name){
        ListView mylistview=(ListView)findViewById(R.id.listview2);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(Review.this,R.layout.reviewtext,R.id.textViewtwo,name);
        mylistview.setAdapter(myAdapter);
        mylistview.setOnItemClickListener(this);
    }
    private ArrayList<String> getText(){
        JSONArray rows = Utils.mysql("SELECT * FROM entry_overview WHERE state like '待审核'" );
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
        Intent intent = new Intent(Review.this, check.class);
        Bundle bundle=new Bundle();
        String string;
        TextView textView=(TextView)(view.findViewById(R.id.textViewtwo));
        string=(String)textView.getText();
        bundle.putCharSequence("word",string);
        intent.putExtras(bundle);
        startActivity(intent);
        Review.this.finish();
    }
}

