package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Login.Account;
import com.squareup.picasso.Picasso;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ListView listview = (ListView) findViewById(R.id.listview); // 获取列表视图

        int[] imageId = new int[]{R.mipmap.person01, R.mipmap.person02, R.mipmap.person03,
                R.mipmap.person04, R.mipmap.person05, R.mipmap.person06,
                R.mipmap.person07,R.mipmap.person08}; // 定义并初始化保存图片id的数组


        String[] title = new String[]{"我的创作", "我的收藏", "我的关注", "我的足迹", "设置",
                "消息通知", "联系客服","审核"}; // 定义并初始化保存列表项文字的数组

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
        Account account = Account.getAccount();

//        //测试待删
//        account.setHeadImage("http://www.blacklighter.cn/TuCaoDict/imgs/head_imgs/normal.jpg");
//        account.setNickName("网名");
//        account.setIdentity("管理员");

        CircleImageView headImg = (CircleImageView)findViewById(R.id.profile_image);
        TextView nickName = (TextView)findViewById(R.id.nick_name);
        Picasso.with(this).load(account.getHeadImage()).into(headImg);
        nickName.setText(account.getNickName());

        int length = imageId.length;

        if(!Account.getAccount().getIdentity().equalsIgnoreCase("管理员")){
            length = length - 1;
        }

        TextView mineText = (TextView)listview.findViewById(R.id.module);
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < length; i++) {
            Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
            map.put("image", imageId[i]);
            map.put("moudle", title[i]);

            listItems.add(map); // 将map对象添加到List集合中
        }


        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.mine, new String[] {"image" , "moudle"}, new int[] {
                R.id.image, R.id.module }); // 创建SimpleAdapter

        listview.setAdapter(adapter);       // 将适配器与ListView关联


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Map<String, Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);
                  Intent intent=new Intent(MineActivity.this,Review.class);
                  startActivity(intent);
    }
});
    }
}

