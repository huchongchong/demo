package com.demo.administrator.demo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.demo.administrator.demo.R;
import com.demo.administrator.demo.Util.SharedPreferenceUtil;
import com.zxing.activity.CaptureActivity;

/**
 *
 */
public class WelcomActivity extends Activity {
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private TextView contentTV;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        contentTV = (TextView)findViewById(R.id.fullscreen_content);
        mHideHandler.postDelayed(mHideRunnable,AUTO_HIDE_DELAY_MILLIS);
        if(!TextUtils.isEmpty(SharedPreferenceUtil.getSession(this))){
            contentTV.setText("欢迎回来");
            intent = new Intent(WelcomActivity.this,SelectEditActivity.class);
        }else{
            intent = new Intent(WelcomActivity.this,CaptureActivity.class);
        }
    }
    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            //Intent intent = new Intent(WelcomActivity.this,CaptureActivity.class);
            WelcomActivity.this.startActivity(intent);
            WelcomActivity. this.finish();
        }
    };

}
