package com.example.myapplication.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import androidx.annotation.NonNull;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Utils extends BasicUtils{


    public static void setHeadImg(String imgPath, String telephone, Handler handler){
        Map<String,String> params = new HashMap<>();
        params.put("telephone",telephone);
        uploadFilesWithParams("headImg",new String[]{imgPath},params,handler);
    }

    public static void setHeadImg(String imgPath, String telephone, Handler handler, Context context){
        Map<String,String> params = new HashMap<>();
        params.put("telephone",telephone);
        uploadFilesWithParams("headImg",new String[]{imgPath},params,handler,context,"头像上传中","上传成功");
    }

    public static void setKindImg(String imgPath, String kindName, Handler handler){
        Map<String,String> params = new HashMap<>();
        params.put("kind_name",kindName);
        uploadFilesWithParams("kindImg",new String[]{imgPath},params,handler);
    }

    public static void setKindImg(String imgPath, String kindName, Handler handler, Context context){
        Map<String,String> params = new HashMap<>();
        params.put("kind_name",kindName);
        uploadFilesWithParams("kindImg",new String[]{imgPath},params,handler,context,"图片上传中","上传成功");
    }

    public static void setEntryVideo(String videoPath, String entryName, Handler handler){
        Map<String,String> params = new HashMap<>();
        params.put("entry_name",entryName);
        uploadFilesWithParams("entryVideo",new String[]{videoPath},params,handler);
    }

    public static void setEntryVideo(String videoPath, String entryName, Handler handler, Context context){
        Map<String,String> params = new HashMap<>();
        params.put("entry_name",entryName);
        uploadFilesWithParams("entryVideo",new String[]{videoPath},params,handler,context,"视频上传中","上传成功");
    }

    public static void submitFilesModule(String entryName, final String moduleName, String telephone, final String[] filePaths, final Handler handler){
        final Map<String,String> map = new HashMap<>();
        map.put("entry_name",entryName);
        map.put("module_kind",moduleName);
        map.put("telephone",telephone);

        mysql("SELECT * FROM module_kinds WHERE module_name='"+moduleName+"'",new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray result = (JSONArray) msg.obj;
                if (result.length() == 0) {
                    Log.e("错误", "模块：'" + moduleName + "'不存在");
                    throw new RuntimeException();
                } else {
                    String formats = null;
                    try {
                        formats = result.getJSONObject(0).getString("module_formats");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (!formats.contains("img") && !formats.contains("music") && !formats.contains("video")) {
                        Log.e("错误", "模块格式：" + formats + "有误（文件模块的类型只能为img,music或video");
                        throw new RuntimeException();
                    } else {
                        uploadFilesWithParams("moduleFiles", filePaths, map, handler);
                    }

                }
            }
        });



    }

    public static void submitFilesModule(String entryName, final String moduleName, String telephone, final String[] filePaths, final Handler handler, final Context context){
        final Map<String,String> map = new HashMap<>();
        map.put("entry_name",entryName);
        map.put("module_kind",moduleName);
        map.put("telephone",telephone);

        LemonBubble.showRoundProgress(context,"模块提交中");
        mysql("SELECT * FROM module_kinds WHERE module_name='"+moduleName+"'",new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                JSONArray result = (JSONArray) msg.obj;
                if (result.length() == 0) {
                    Log.e("错误", "模块：'" + moduleName + "'不存在");
                    throw new RuntimeException();
                } else {
                    String formats = null;
                    try {
                        formats = result.getJSONObject(0).getString("module_formats");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (!formats.contains("img") && !formats.contains("music") && !formats.contains("video")) {
                        Log.e("错误", "模块格式：" + formats + "有误（文件模块的类型只能为img,music或video");
                        throw new RuntimeException();
                    } else {
                        uploadFilesWithParams("moduleFiles", filePaths, map, new Handler(){
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                super.handleMessage(msg);
                                handler.sendMessage(Message.obtain(msg));
                                LemonBubble.showRight(context,"模块提交成功",1500);
                            }
                        });
                    }

                }
            }
        });



    }


}
