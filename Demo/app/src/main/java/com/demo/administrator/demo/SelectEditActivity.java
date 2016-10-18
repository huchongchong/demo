package com.demo.administrator.demo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

import com.liulin.net.NetCallBack;
import com.liulin.net.TestClient;

import java.util.Calendar;

public class SelectEditActivity extends Activity implements OnClickListener{

    private TextView tv1,tv2,tv3,tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_edit);
        initView();

        //TestClient.instance(null);
    }

    private void initView() {
        tv1 = (TextView)findViewById(R.id.area_tv_1);
        tv2 = (TextView)findViewById(R.id.area_tv_2);
        tv3 = (TextView)findViewById(R.id.area_tv_3);
        tv4 = (TextView)findViewById(R.id.area_tv_4);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);

        // 进行闹铃注册
        Intent intent = new Intent(SelectEditActivity.this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(SelectEditActivity.this, 0, intent, 0);

// 过10s 执行这个闹铃
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 30);

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        switch (v.getId()){
            case R.id.area_tv_1:
                intent.putExtra("number",1);
                break;
            case R.id.area_tv_2:
                intent.putExtra("number",2);
                break;
            case R.id.area_tv_3:
                intent.putExtra("number",3);
                break;
            case R.id.area_tv_4:
                intent.putExtra("number",4);
                break;
        }
        this.startActivity(intent);
    }
}
