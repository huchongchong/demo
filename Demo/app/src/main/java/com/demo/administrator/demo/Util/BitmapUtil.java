package com.demo.administrator.demo.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/8/12.
 */
public class BitmapUtil {

    public static Bitmap CompressBitmap(Bitmap source, int width, int height, int targetSize) {
        if (source.getByteCount() > targetSize * 1024) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            source.compress(Bitmap.CompressFormat.JPEG, 100, os);
            if (os.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
                os.reset();//重置baos即清空baos
                source.compress(Bitmap.CompressFormat.JPEG, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
            }
            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeStream(is, null, newOpts);
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            float hh = width;// 设置高度为240f时，可以明显看到图片缩小了
            float ww = height;// 设置宽度为120f，可以明显看到图片缩小了
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0) be = 1;
            newOpts.inSampleSize = be;//设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            is = new ByteArrayInputStream(os.toByteArray());
            bitmap = BitmapFactory.decodeStream(is, null, newOpts);
            //压缩好比例大小后再进行质量压缩
            if(bitmap.getByteCount()>targetSize * 1024){
                Bitmap bit = BitmapCompress(bitmap,targetSize);
                bitmap.recycle();
                return bit;
            }
            return bitmap;
        }
        return source;
    }
    public static Bitmap BitmapCompress(Bitmap source ,int maxSize){
        Log.e("bitmap", "BitmapCompress");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 50;
        // Store the bitmap into output stream(no compress)
        source.compress(Bitmap.CompressFormat.JPEG, options, os);

        // Compress by loop
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        while (os.toByteArray().length / 1024 > maxSize) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            source.compress(Bitmap.CompressFormat.JPEG, options, os);
            Log.e("bitmap", "os" + os.toByteArray().length / 1024 + "kb");

        }
        return BitmapFactory.decodeByteArray(os.toByteArray(), 0, os.toByteArray().length);

    }
    public static String save(Bitmap cache)  {
        try {
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
//        Bitmap bitmap = Bitmap.createBitmap(cache.getWidth(),cache.getHeight(), Bitmap.Config.ARGB_8888);
//        Log.e("test","cache width"+cache.getWidth()+"height: "+cache.getHeight());
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawColor(Color.TRANSPARENT);
//        canvas.drawBitmap(cache, new Matrix(), mGesturePaint);
//        canvas.drawPath(mPath, mGesturePaint);
            if (cache != null) {
                cache.compress(Bitmap.CompressFormat.JPEG, 100, out);
            }
            out.flush();
            out.close();
            return f.getAbsolutePath();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
