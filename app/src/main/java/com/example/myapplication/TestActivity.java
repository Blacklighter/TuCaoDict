package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ((ListView)findViewById(R.id.list)).setAdapter(new ArrayAdapter<String>(this,R.layout.card, R.id.card,getResources().getStringArray(R.array.sports)));
    }
}
