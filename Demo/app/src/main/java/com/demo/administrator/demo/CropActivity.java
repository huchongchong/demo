package com.demo.administrator.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.demo.administrator.demo.Util.FileUtil;
import com.demo.administrator.demo.view.CropImageView;
import com.demo.administrator.demo.view.CropImageView3;
import com.demo.administrator.demo.view.CropImageView4;

public class CropActivity extends Activity implements View.OnClickListener{

    private CropImageView mCropImage;
    private ImageView cancel,save;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1) {
                String path = msg.obj.toString();
                Intent intent = new Intent();
                intent.putExtra("path", path);
                CropActivity.this.setResult(2, intent);
                CropActivity.this.finish();
            }else{
                String path = msg.obj.toString();
                Intent intent = new Intent();
                intent.putExtra("path", "");
                CropActivity.this.setResult(1, intent);
                CropActivity.this.finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        mCropImage = (CropImageView) findViewById(R.id.corp);
        String bitmap = getIntent().getStringExtra("bitmap");
        int w = getIntent().getIntExtra("width", 300);
        int h = getIntent().getIntExtra("height",300);

        Log.e("aaaaaaaa","bitmap  path"+bitmap);
        Bitmap b = BitmapFactory.decodeFile(bitmap);
        Log.e("size",w+"       "+h);
       //mCropImage.setDrawable(getResources().getDrawable(R.drawable.gyy),800,500);
        if(bitmap!=null){
            mCropImage.setDrawable(new BitmapDrawable(b),w,h);
        }
        init();
    }
    public  int px2dp(float pxValue){
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
    public  int dip2px(float dipValue){
        final float scale=this.getResources().getDisplayMetrics().density;
        return (int)(dipValue*scale+0.5f);
    }
    private void init() {
        save = (ImageView)findViewById(R.id.save);
        cancel = (ImageView)findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    private void cropImage4() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String s = FileUtil.saveBitmap(CropActivity.this, mCropImage.getCropImage());
                            Log.e("aaaaaa","result path:  "+s);
                            if(!TextUtils.isEmpty(s)){
                                Message m = Message.obtain();
                                m.what = 1;
                                m.obj = s;
                                mHandler.sendMessage(m);
                            }else{
                                Message m = Message.obtain();
                                m.what = 2;
                                mHandler.sendMessage(m);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            Message m = Message.obtain();
                            m.what = 2;
                            mHandler.sendMessage(m);
                        }

                    }
                }.start();
            break;
            case R.id.cancel:
            break;
        }
    }
}
