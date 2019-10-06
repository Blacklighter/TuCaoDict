package com.example.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {

    /**ma
     * 功能及参数含义参考第三种重载形式，这里就是默认不打印结果，默认同步
     * @param sql
     * @return
     */
    public static JSONArray mysql(String sql){
        return mysql(sql,false,true);
    }

    /**ma
     * 功能及参数含义参考第三种重载形式，这里就是可打印结果，默认同步
     * @param sql
     * @param doShowResult
     * @return
     */
    public static JSONArray mysql(String sql,final boolean doShowResult){
        return mysql(sql,doShowResult,true);
    }


    /**ma
     * 用于请求后端数据库执行MySQL语句并将结果以json字符串的形式包装为JSONArray返回（如有异常则会抛出并输出原因)
     * @param sql MySQL查询语句
     * @param doShowResult （当没有异常时）是否在控制台打印结果（以json字符串的形式)
     * @param isSynchronous 是否同步（用于扩展，目前来看默认使用同步即可）
     * @return 返回执行MySQL后得到的JSONArray
     */
    public static JSONArray mysql(final String sql, final boolean doShowResult, boolean isSynchronous){
        final JSONArray[] finalResult = {null};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://www.blacklighter.cn:5000/mysql";
                OkHttpClient okHttpClient = new OkHttpClient();

                FormBody formBody = new FormBody.Builder()
                        .add("sql",sql).build();


                Request request = new Request.Builder().url(url).post(formBody).build();
                try{
                    Response response = okHttpClient.newCall(request).execute();
                    String result = response.body().string();
                    if(doShowResult)
                        Log.e("执行的MySQL语句：'", sql+"'\n结果（json字符串）为："+result);
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
        if(isSynchronous) {
            try {
                thread.join();
                return finalResult[0];
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return finalResult[0];
    }
}
