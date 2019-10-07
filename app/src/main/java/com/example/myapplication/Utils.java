package com.example.myapplication;

import android.util.Log;

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

public class Utils {
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
        return mysql(sql,true);
    }


    public static  JSONArray mysql(String sql,boolean doShowResult){
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
        Map map = new HashMap();
        map.put("sql",sql);
        return request("mysql",map,doShowResult,doShowResult);
    }


    private static JSONArray request(final String funName, final Map<String,String> params, final boolean doShowResult, boolean isSynchronous){
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

    public static JSONArray uploadFilesWithParams(final String funName, final String[] paths, final Map<String,String>[] paramsArray, final boolean doShowResult, boolean isSynchronous){

        final JSONArray[] finalResult = {null};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = MY_URL+funName;

                // 创建body
                MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for(int i = 0; i < paths.length; i++){
                    File file = new File(paths[i]);
                    bodyBuilder.addFormDataPart("file",file.getName(),RequestBody.create(FILE,file));
                    Map<String,String> params = paramsArray[i];
                    for(String key: params.keySet()){
                        bodyBuilder.addFormDataPart(file.getName()+"_"+key,params.get(key));
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
        if(isSynchronous){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return finalResult[0];
    }

}
