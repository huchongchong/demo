package com.demo.administrator.demo.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 具体方法自己添加
 * Created by y66676 on 2015/9/1.
 */
public class MyHttpRequest {
    // private HttpUtils utils;
    private static String className = "MyHttpRequest";
    private static String Token;
    private static MyHttpRequest instance;
    private static OkHttpClient okHttpClient;

    private MyHttpRequest() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(160, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(160, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(160, TimeUnit.SECONDS);
    }

    public static MyHttpRequest getInstance() {
        if (instance == null) {
            synchronized (MyHttpRequest.class) {
                instance = new MyHttpRequest();
            }
        }
        return instance;
    }




    /**
     * 上传文件（头像）
     *
     * @param filePath
     * @param from
     * @param union_id
     * @param mListener
     */
    public void uploadFile(String filePath, String from, String union_id, final MyRequestListener mListener) {
        try {
            /*
            RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
        "http://10.102.104.224:8888/api/upload/file";
             */

            String url = "";
            Log.e("http", url + "   file  path" + filePath);
            File file = new File(filePath);
            RequestBody fileBody = RequestBody.create(MediaType.parse("file"), file);

            RequestBody requestBody = new MultipartBuilder()
                    .addFormDataPart("file", "file", fileBody)
                    .addFormDataPart("form", from)
                    .addFormDataPart("union_id", union_id)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                            //.addHeader("Authorization", "token " + Token)
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request).enqueue(new MyCallBack() {
                @Override
                public void onFailure(Request request, IOException e) {
                    mListener.onFailure(e.toString());
                    Log.e("http  onFailure", " IOException  " + e.toString());
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) {
                    DeliverResponse(response, mListener);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("http  Exception", e.toString());
        }

    }



    /**
     * 网络返回分发
     * @param response
     * @param mListener
     */
    private void DeliverResponse(Response response, MyRequestListener mListener) {
        if (response == null || mListener == null)
            return;
        try {
            if (response.code() == 200) {
                ResponseBody body = response.body();
                if (body != null) {
                    String bodyResult = body.string();
                    Log.e("http", "url : "+response.request().url()+"    bodyResult" + bodyResult);
                    if (!TextUtils.isEmpty(bodyResult)) {
                        mListener.onSuccess(bodyResult);
                    } else {
                        mListener.onFailure(bodyResult);
                    }
                } else {
                    mListener.onFailure("");
                }
            } else if (response.code() >= 400 && response.code() < 500) {
                String body = response.body().string();
                Log.e("http", "url : "+response.request().url()+"    responseCode: "+response.code()+" bodyResult: " + body);
                    mListener.onFailure(response.message());
            } else {
                Log.e("http", "url : " + response.request().url() + "      responseCode: "+response.code());
                mListener.onFailure(response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("http", "url : " + response.request().url() + "Exception " + e.toString());
            mListener.onFailure(response.message());
        }
    }


    public static void loadImage(Context context, final ImageView imageView, String imgUrl){
        try{
            RequestQueue mQueue = Volley.newRequestQueue(context);

            ImageRequest imageRequest = new ImageRequest(
                    imgUrl,
                    new com.android.volley.Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            imageView.setBackground(new BitmapDrawable(response));
                        }
                    }, 0, 0, Bitmap.Config.RGB_565, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            mQueue.add(imageRequest);
        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}
