package com.demo.administrator.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.demo.administrator.demo.PathModel;
import com.demo.administrator.demo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Created by Administrator on 2016/6/20.
 */
public class TouchImageView extends ImageView {
    private Paint paint;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private float startX;
    private float startY;
    private Path path;
    private int backCount = 0;
    private Vector<PathModel> Vps = new Vector<>();
    private Vector<PathModel> Vps2 = new Vector<>();
    private float curW;
    private int curC;
    private Bitmap bgBitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.gyy);

    public TouchImageView(Context context) {
        super(context);
        init();
    }

    public TouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        curW = 3;
        curC = Color.WHITE;

    }

    public void setPaintWidth(int w) {
        curW = w;
        paint.setStrokeWidth(w);
    }

    public void setPaintColor(int w) {
        curC = w;
        paint.setColor(w);
    }
    public int getPaintColor() {
      return  paint.getColor();
    }

    public void setBaseBitmap(Bitmap bt){
        if(bt==null||bt.getWidth()<=0||bt.getHeight()<=0){
            return;
        }
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)getLayoutParams();
        params.width = bt.getWidth();
        params.height = bt.getHeight();
        setLayoutParams(params);
        postInvalidate();
        setImageBitmap(bt);
        this.bgBitmap= bt;
        baseBitmap = Bitmap.createBitmap(bt.getWidth(),
                bt.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(baseBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawBitmap(bgBitmap, new Matrix(), paint);
        setImageBitmap(baseBitmap);

        Log.e("qqqq", "bt   w: " + bt.getWidth() + "h:  " + bt.getHeight());
        Log.e("qqqq", "view  w: " + this.getWidth() + "h:  " + this.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("qqqq", "  onMeasure  view  w: " + this.getMeasuredWidth() + "h:  " + this.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            // 用户按下动作
            case MotionEvent.ACTION_DOWN:
                Vps2.clear();
                path = new Path();
                path.reset();
                paint.setStrokeWidth(curW);
                paint.setColor(curC);
                // 第一次绘图初始化内存图片，指定背景为白色
//                canvas = new Canvas(baseBitmap);
//                canvas.drawColor(Color.TRANSPARENT);
                // 记录开始触摸的点的坐标
                startX = event.getX();
                startY = event.getY();
                path.moveTo(startX, startY);
                // canvas.drawPoint(startX, startY, paint);
                canvas.drawPath(path, paint);
                this.setImageBitmap(baseBitmap);
                break;
            // 用户手指在屏幕上移动的动作
            case MotionEvent.ACTION_MOVE:
                // 记录移动位置的点的坐标
                float stopX = event.getX();
                float stopY = event.getY();

                final float previousX = startX;
                final float previousY = startY;

                final float dx = Math.abs(stopX - previousX);
                final float dy = Math.abs(stopY - previousY);

                //两点之间的距离大于等于3时，连接连接两点形成直线
                if (dx >= 3 || dy >= 3) {
                    //两点连成直线
                    path.lineTo(stopX, stopY);
                    //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
                    startX = stopX;
                    startY = stopY;
                }

                path.lineTo(stopX, stopY);
                // canvas.drawLine(startX, startY, stopX, stopY, paint);
                // 更新开始点的位置

                canvas.drawPath(path, paint);
                startX = event.getX();
                startY = event.getY();
                // 把图片展示到ImageView中
                this.setImageBitmap(baseBitmap);
                break;
            case MotionEvent.ACTION_UP:
                PathModel p = new PathModel();
                p.p = path;
                p.color = paint.getColor();
                p.w = paint.getStrokeWidth();
                Vps.add(p);
                break;
            default:
                break;
        }
        return true;
    }



    public void back() {
        baseBitmap = Bitmap.createBitmap(this.getWidth(),
                this.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(baseBitmap);
        canvas.drawBitmap(bgBitmap, new Matrix(), paint);
        canvas.drawColor(Color.TRANSPARENT);
        if (Vps.size() > 0) {
            Vps2.add(Vps.remove(Vps.size() - 1));
            for (int i = 0; i < Vps.size(); i++) {
                paint.setColor(Vps.get(i).color);
                paint.setStrokeWidth(Vps.get(i).w);
                canvas.drawPath(Vps.get(i).p, paint);
            }
        }
        this.setImageBitmap(baseBitmap);
    }

    public void go() {
        if (Vps2.size() > 0) {
            baseBitmap = Bitmap.createBitmap(this.getWidth(),
                    this.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawBitmap(bgBitmap, new Matrix(), paint);
            canvas.drawColor(Color.TRANSPARENT);
            Vps.add(Vps2.remove(Vps2.size() - 1));
            if (Vps.size() > 0) {
                for (int i = 0; i < Vps.size(); i++) {
                    paint.setColor(Vps.get(i).color);
                    paint.setStrokeWidth(Vps.get(i).w);
                    canvas.drawPath(Vps.get(i).p, paint);
                }
            }
            this.setImageBitmap(baseBitmap);
        }

    }

    public void reset() {
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(this.getWidth(),
                    this.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawBitmap(bgBitmap, new Matrix(), paint);
            canvas.drawColor(Color.TRANSPARENT);
            this.setImageBitmap(baseBitmap);
        }
        Vps2.clear();
        Vps.clear();
    }

    public String save(int w) throws Exception {
        String sdStatus = Environment.getExternalStorageState();
        String path;
        String picName = System.currentTimeMillis() + ".jpg";
        if (sdStatus.equals(Environment.MEDIA_MOUNTED)) {// 检测sd是否可用
            path = Environment.getExternalStorageDirectory().getPath();
        } else {
            path = Environment.getDataDirectory().getPath();
        }
        File file = new File(path, "com.demo.administrator.demo/pic");
        Log.e("qqqq", " path" + path + "  " + Environment.getExternalStorageDirectory().canWrite());
        Log.e("qqqq", " path  2  " + Environment.getDataDirectory().getPath());
        Log.e("qqqq", " path  3  " + this.getContext().getFilesDir().getAbsolutePath());
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
        this.setDrawingCacheEnabled(true);
        this.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 100, out);
        this.setDrawingCacheEnabled(false);
        out.flush();
        out.close();
        return f.getAbsolutePath();
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
        Log.e("test", "cache width" + cache.getWidth() + "height: " + cache.getHeight());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(cache, new Matrix(), paint);
        if (Vps.size() > 0) {
            for (int i = 0; i < Vps.size(); i++) {
                paint.setColor(Vps.get(i).color);
                paint.setStrokeWidth(Vps.get(i).w);
                canvas.drawPath(Vps.get(i).p, paint);
            }
        }
        //canvas.drawPath(mPath, mGesturePaint);
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        out.flush();
        out.close();
        return f.getAbsolutePath();
    }

}
