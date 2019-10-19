package com.example.myapplication.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class BasicUtils {
    private static final String MY_URL = "http://www.blacklighter.cn:5000/";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final MediaType FILE = MediaType.parse("application/octet-stream");
    private static final MediaType FROM_DATA = MediaType.parse("multipart/form-data");

    /**ma
     * 用于请求后端数据库执行MySQL语句并将结果以json字符串的形式包装为JSONArray返回（如有异常则会抛出并输出原因，默认不打印结果并同步)
     * @param sql MySQL查询语句
     * @return 返回执行MySQL后得到的JSONArray
     */
    public static JSONArray mysql(String sql){
        return mysql(sql,false);
    }

    public static void mysql(String sql, final Handler handler){
        mysql(sql,false,handler);
    }

    // 用于在请求过程中播放等待动画，因为是异步方法，所以返回值和后续逻辑均要利用handler
    public static void mysql(String sql, final Handler handler, final Context context, String loadingText, final String successText){
        mysql(sql,false,handler,context,loadingText,successText);
    }


    /**ma
     * 用于请求后端数据库执行MySQL语句并将结果以json字符串的形式包装为JSONArray返回（如有异常则会抛出并输出原因)
     * @param sql MySQL查询语句
     * @param doShowResult （当没有异常时）是否在控制台打印结果（以json字符串的形式)
     * @return 返回执行MySQL后得到的JSONArray
     */
    public static JSONArray mysql(final String sql, final boolean doShowResult){
        Map map = new HashMap();
        map.put("sql",sql);
        return request("mysql",map,doShowResult);
    }



    // 用于在请求过程中播放等待动画，因为是异步方法，所以返回值和后续逻辑均要利用handler
    public static void mysql(String sql, boolean doShowResult,final Handler handler, final Context context, String loadingText, final String successText){
        LemonBubble.showRoundProgress(context,loadingText);
        Handler handler2 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handler.sendMessage(Message.obtain(msg));
                LemonBubble.showRight(context,successText,1500);
            }
        } ;
        mysql(sql,doShowResult,handler2);
    }


    //对应的异步方法，返回值JSONArray从handler中message.obj中获得，后续逻辑必须放在handler的处理方法里
    public static void mysql(String sql,boolean doShowResult,Handler handler){
        Map map = new HashMap();
        map.put("sql",sql);
        request("mysql",map,handler,doShowResult);
    }



    protected static JSONArray request(final String funName, final Map<String,String> params, final boolean doShowResult){
        final JSONArray[] finalResult = {null};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = MY_URL + funName;
                JSONObject jsonObject = new JSONObject(params);
                RequestBody body = RequestBody.create(JSON,jsonObject.toString());

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).post(body).build();
                try{
                    Response response = okHttpClient.newCall(request).execute();
                    String result = response.body().string();
                    if(doShowResult)
                        Log.e("发送的json字符串", jsonObject.toString()+"\n返回的结果（json字符串）为："+result);
                    //服务器执行给定的mysql语句异常，则打印异常信息
                    if(result.contains("发生异常")){
                        throw new RuntimeException();
                    }
                    else{
                        finalResult[0] = new JSONArray(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();
            return finalResult[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return finalResult[0];
    }

    //专门解决异步问题
    protected static void request(final String funName, final Map<String,String> params, final Handler handler,final boolean doShowResult){
        final JSONArray[] finalResult = {null};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = MY_URL + funName;
                JSONObject jsonObject = new JSONObject(params);
                RequestBody body = RequestBody.create(JSON,jsonObject.toString());

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).post(body).build();
                try{
                    Response response = okHttpClient.newCall(request).execute();
                    String result = response.body().string();
                    if(doShowResult)
                        Log.e("发送的json字符串", jsonObject.toString()+"\n返回的结果（json字符串）为："+result);
                    //服务器执行给定的mysql语句异常，则打印异常信息
                    if(result.contains("发生异常")){
                        throw new RuntimeException();
                    }
                    else{
                        finalResult[0] = new JSONArray(result);
                    }

                   // LemonBubble.forceHide();
                    Message msg = new Message();
                    msg.obj = finalResult[0];
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }



    protected static void uploadFilesWithParams(final String funName, final String[] paths, final Map<String,String> params,Handler handler){
        uploadFilesWithParams(funName,paths,params,handler,false);
    }

    protected static void uploadFilesWithParams(final String funName, final String[] paths, final Map<String,String> params,Handler handler, final boolean doShowResult){

        final JSONArray[] finalResult = {null};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = MY_URL+funName;

                // 创建body
                MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for(int i = 0; i < paths.length; i++){
                    File file = new File(paths[i]);
                    bodyBuilder.addFormDataPart("file"+(i+1),file.getName(),RequestBody.create(FILE,file));
                    for(String key: params.keySet()){
                        bodyBuilder.addFormDataPart(key,params.get(key));
                    }
                }
                RequestBody body = bodyBuilder.build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();


                OkHttpClient client = new OkHttpClient();

                try{
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    if(doShowResult)
                        Log.e("返回的结果（json字符串）为：", result);
                    //服务器执行给定的mysql语句异常，则打印异常信息
                    if(result.contains("发生异常")){
                        throw new RuntimeException();
                    }
                    else{
                        finalResult[0] = new JSONArray(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Message msg = new Message();
        msg.obj = finalResult[0];
        handler.sendMessage(msg);
    }

    protected static void uploadFilesWithParams(final String funName, final String[] paths, final Map<String,String> params, final Handler handler,  final Context context, String loadingText, final String successText) {
        uploadFilesWithParams(funName, paths, params, handler, false,context,loadingText,successText);
    }

    // 用于在请求过程中播放等待动画，因为是异步方法，所以返回值和后续逻辑均要利用handler
    protected static void uploadFilesWithParams(final String funName, final String[] paths, final Map<String,String> params, final Handler handler, final boolean doShowResult, final Context context, String loadingText, final String successText) {
        LemonBubble.showRoundProgress(context, loadingText);
        Handler handler2 = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                handler.sendMessage(Message.obtain(msg));
                LemonBubble.showRight(context, successText, 1500);
            }
        };
        uploadFilesWithParams(funName, paths, params, handler2, doShowResult);
    }
}
