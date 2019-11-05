package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ListView listview = (ListView) findViewById(R.id.listview); // 获取列表视图

        int[] imageId = new int[]{R.mipmap.person01, R.mipmap.person02, R.mipmap.person03,
                R.mipmap.person04, R.mipmap.person05, R.mipmap.person06,
                R.mipmap.person07}; // 定义并初始化保存图片id的数组

        String[] title = new String[]{"我的创作", "我的收藏", "我的关注", "我的足迹", "设置",
                "消息通知", "联系客服"}; // 定义并初始化保存列表项文字的数组

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
        Account account = new Account();
        onCreate(new Bundle());
        LemonBubble.showRoundProgress(MineActivity.this,"账户名加载中....");

<<<<<<< HEAD
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                String result = (String) msg.obj;

                LemonBubble.showRight(MineActivity.this,"加载完成",1500);

            }
        });
=======
>>>>>>> 1e535d6d0eb4ffe5e6701d68776938a7b6c204a7

        TextView mineText = (TextView)listview.findViewById(R.id.module);
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
            map.put("image", imageId[i]);
            map.put("moudle", title[i]);

            listItems.add(map); // 将map对象添加到List集合中
        }
        for(Map<String, Object> map:listItems){
            for(String key: map.keySet())
            Log.e("TEST",""+map.get(key));
        }

        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.mine, new String[] {"image" , "moudle"}, new int[] {
                R.id.image, R.id.module }); // 创建SimpleAdapter

        listview.setAdapter(adapter);       // 将适配器与ListView关联

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Map<String, Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);
                    //获取选择项的值
                   Toast.makeText(MineActivity.this,map.get("moudle").toString(),Toast.LENGTH_SHORT).show();
    }
});
    }
}

