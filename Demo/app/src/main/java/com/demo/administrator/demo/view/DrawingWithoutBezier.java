package com.demo.administrator.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Created by Administrator on 2016/6/20.
 */
public class DrawingWithoutBezier extends ImageView {
    private float mX;
    private float mY;

    private final Paint mGesturePaint = new Paint();
    private  Path mPath;
    private int paintWidth = 3;
    private Drawable mDrawable;
    private Canvas canvas;
    private Vector<Path> Vps = new Vector<>();
    public DrawingWithoutBezier(Context context) {
        super(context);
        mPath = new Path();
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(paintWidth);
        mGesturePaint.setColor(Color.WHITE);
    }

    public DrawingWithoutBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(paintWidth);
        mGesturePaint.setColor(Color.WHITE);
    }

    public DrawingWithoutBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(paintWidth);
        mGesturePaint.setColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
        }
        //更新绘制
        if(event.getAction()==MotionEvent.ACTION_UP){
            RectF rect = new RectF(getLeft(),getTop(),getRight(),getBottom());
           // canvas.saveLayer(rect,mGesturePaint);
            Vps.add(mPath);
            mPath = new Path();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if(getDrawable()!= null) {
           // getDrawable().draw(canvas);
            canvas.drawPath(mPath, mGesturePaint);
            canvas.save();
            this.canvas = canvas;
        }else{
           // super.onDraw(canvas);
        }

    }

    //手指点下屏幕时调用
    private void touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;

        //mPath绘制的绘制起点
        mPath.moveTo(x, y);
    }

    //手指在屏幕上滑动时调用
    private void touchMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float previousX = mX;
        final float previousY = mY;

        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);

        //两点之间的距离大于等于3时，连接连接两点形成直线
        if (dx >= 3 || dy >= 3) {
            //两点连成直线
            mPath.lineTo(x, y);
            //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
            mX = x;
            mY = y;
        }
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }
   public void setPaintWidth(int w){
       paintWidth = w;
       mGesturePaint.setStrokeWidth(w);
   }
    public String save() throws Exception {
        String sdStatus = Environment.getExternalStorageState();
        String path;
        String picName = System.currentTimeMillis() + ".jpg";
        if (sdStatus.equals(Environment.MEDIA_MOUNTED)) {// 检测sd是否可用
            path = Environment.getExternalStorageDirectory().getPath();
        } else {
            path = Environment.getDataDirectory().getPath();
        }
        File file = new File(path, "com.demo.administrator.demo/pic");
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Log.e("qqqq", file.getPath() + "   " + b);
        }
        File f = new File(file, picName);
        if (f.exists()) {
            f.delete();
        }
        f.createNewFile();
        FileOutputStream out = new FileOutputStream(f);
        Bitmap cache = ((BitmapDrawable)this.getDrawable()).getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(cache.getWidth(),cache.getHeight(), Bitmap.Config.ARGB_8888);
        Log.e("test","cache width"+cache.getWidth()+"height: "+cache.getHeight());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(cache, new Matrix(), mGesturePaint);
        canvas.drawPath(mPath, mGesturePaint);
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        out.flush();
        out.close();
        return f.getAbsolutePath();
    }

}
