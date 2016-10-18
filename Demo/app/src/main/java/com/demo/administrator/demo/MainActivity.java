package com.demo.administrator.demo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.demo.administrator.demo.Util.BitmapUtil;
import com.demo.administrator.demo.Util.Constant;
import com.demo.administrator.demo.Util.FileUtil;
import com.demo.administrator.demo.view.ComDialog;
import com.demo.administrator.demo.view.DrawingWithoutBezier;
import com.demo.administrator.demo.view.TouchImageView;
import com.demo.administrator.demo.view.colorpickerview.dialog.ColorPickerDialogFragment;
import com.demo.administrator.demo.view.colorpickerview.view.ColorPickerView;
import com.liulin.auto.packet.DrawProto;
import com.liulin.net.NetCallBack;
import com.liulin.net.TestClient;
import com.nineoldandroids.animation.Animator;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity implements ColorPickerView.OnColorChangedListener, ColorPickerDialogFragment.ColorPickerDialogListener, View.OnClickListener {
    private TouchImageView img2;
    private NetCallBack upImgCallBack;
    private View WidthSelectView;
    private View WidthSelectViewL;
    private int lineWidth = 1;
    private String curSelectColor="#ffffffff";
    RelativeLayout.LayoutParams contentParams;
    private int inPx;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
            } else if (msg.what == 2) {
                if (msg.obj != null && msg.obj instanceof Bitmap) {
                    Bitmap bt = (Bitmap) msg.obj;
                    img2.reset();
                    img2.setBaseBitmap(bt);
                    String s = BitmapUtil.save(bt);
                    Log.e("test", (bt.getByteCount() / 1024) + "kb" + bt.getWidth() + "   " + bt.getHeight() + "path;  " + s + "  " + Thread.currentThread().getId());
                } else {
                    Toast.makeText(MainActivity.this, "下载图片出错", Toast.LENGTH_SHORT).show();
                }
            } else if (msg.what == 4) {
                showTv.setText("请滑动选择  当前选择宽度:" + msg.arg1);
                curWidth = msg.arg1;
            } else if (msg.what == 5) {
                if (loading != null && loading.isShowing()) {
                    loading.dismiss();
                }
                if (msg.obj != null && msg.obj instanceof Bitmap) {
                    Bitmap bt = (Bitmap) msg.obj;
                    img2.reset();
                    img2.setBaseBitmap(bt);
                    String s = BitmapUtil.save(bt);
                    Log.e("test", (bt.getByteCount() / 1024) + "kb" + bt.getWidth() + "   " + bt.getHeight() + "path;  " + s + "  " + Thread.currentThread().getId());
                } else {
                    Toast.makeText(MainActivity.this, "下载图片出错", Toast.LENGTH_SHORT).show();
                }
                //img2.setImageBitmap(BitmapFactory.decodeFile("/storage/sdcard1/com.demo.administrator.demo/pic/1471239221033.jpg"));
            } else if (msg.what == 6) {
                if (msg.obj != null) {
                    String px = msg.obj.toString();
                    inPx = Integer.parseInt(px);
                    show_px.setText("当前选择：" + px + "px");
                    LinearLayout.LayoutParams parms = (LinearLayout.LayoutParams) ling_px.getLayoutParams();
                    parms.height = inPx;
                    ling_px.setLayoutParams(parms);
                }
            }else if (msg.what == 7) {
                if (msg.obj != null) {
                    String px = msg.obj.toString();
                    ling_Color.setBackgroundColor(Color.parseColor(px));
                    ling_Color.setTag(px);
                }
            }
        }
    };
    private Uri imageUri;
    private RelativeLayout rl;
    private LinearLayout tool;
    int w = 300;
    int h = 300;
    private ComDialog.Builder d;
    private ComDialog s;
    private SeekBar seekbar;
    private TextView showTv;
    private int curWidth = 3;
    private int location = 1;
    private TestClient Te;
    private ComDialog loading;
    private RelativeLayout dialog;
    private View lindWidthView;
    private View px1, px2, px3, px4, px5, px6, px7, px8, px9;
    private TextView show_px;
    private View ling_px;
    private View lindColorView;
    private View ling_Color;

    private void showImg(String imagePath) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, option);
        img2.setImageBitmap(bitmap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
        location = getIntent().getIntExtra("number", 1);
        Te = TestClient.instance(upImgCallBack);
        Te.downLoadImg(
                location + "");
        this.findViewById(R.id.parent).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (contentParams.width > 0) {
                    YoYo.with(Techniques.FadeOut).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator arg0) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator arg0) {
                        }

                        @Override
                        public void onAnimationEnd(Animator arg0) {
                            contentParams.width = 0;
                            dialog.setLayoutParams(contentParams);
                        }

                        @Override
                        public void onAnimationCancel(Animator arg0) {
                        }
                    }).playOn(dialog);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.PHOTO_REQUEST_TAKEPHOTO && resultCode == Activity.RESULT_OK) {
            //Bitmap bitmap = getBitmapFromUri(imageUri);
            // if (bitmap != null) {
            Intent intent = new Intent(this, CropActivity.class);
            intent.putExtra("bitmap", imageUri.getPath());
            intent.putExtra("width", w);
            intent.putExtra("height", h);
            startActivityForResult(intent, Constant.PHOTO_REQUEST_CROP);
            // }
        } else if (requestCode == Constant.PHOTO_REQUEST_CROP) {
            String path = data.getStringExtra("path");
            Log.e("aaaaaa", "last path :" + path);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            Log.e("test", "corp width:" + bitmap.getWidth() + "height: " + bitmap.getHeight());
            Log.e("test", "img2 width:" + img2.getWidth() + "height: " + img2.getHeight());
            Message message = Message.obtain();
            message.what = 2;
            message.obj = bitmap;
            mHandler.sendMessage(message);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        if(contentParams.width>0){
            YoYo.with(Techniques.FadeOut).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator arg0) {
                }

                @Override
                public void onAnimationRepeat(Animator arg0) {
                }

                @Override
                public void onAnimationEnd(Animator arg0) {
                    WidthSelectView.setPressed(false);
                    WidthSelectView.setSelected(false);
                    contentParams.width = 0;
                    dialog.setLayoutParams(contentParams);
                }

                @Override
                public void onAnimationCancel(Animator arg0) {
                }
            }).playOn(dialog);
        }
        Te.release();
        this.finish();
    }

    protected void getImageFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            imageUri = Uri.fromFile(file);
            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(getImageByCamera, Constant.PHOTO_REQUEST_TAKEPHOTO);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            if (bitmap != null) {
//                String headPath = FileUtil.saveBitmap(this, bitmap, "headImg");
//                MyHttpRequest.getInstance().uploadFile(RequestType.REQEUST_UPLOAD_AVATAR,headPath, "aa", "12345678", this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void init() {
        //img = (TouchImageView)findViewById(R.id.img);
        rl = (RelativeLayout) findViewById(R.id.rl);
        dialog = (RelativeLayout) findViewById(R.id.dialog);
        tool = (LinearLayout) findViewById(R.id.tool);
        img2 = (TouchImageView) findViewById(R.id.img2);
        lindWidthView = LayoutInflater.from(this).inflate(R.layout.loading_item, null);
        lindColorView = LayoutInflater.from(this).inflate(R.layout.loading_item_color, null);
        lindWidthView.setTag("1");
        lindColorView.setTag("2");
        contentParams = (RelativeLayout.LayoutParams) dialog.getLayoutParams();
        // img2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gyy));
        final ViewTreeObserver ob = img2.getViewTreeObserver();
        ob.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //img2.setBaseBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gyy));
                img2.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }

        });
        loading = new ComDialog.Builder(MainActivity.this).setDialogView(R.layout.loading_dialog).setStyle(R.style.ShareDialog).setEndDuration(200).setEndTechnique(Techniques.FadeOutDown).setGravity(Gravity.CENTER).setIsCancelable(false).setStartDuration(200).setStartTechnique(Techniques.BounceIn).build();
        // img2.setImageBitmap(BitmapFactory.decodeFile("/storage/sdcard1/com.demo.administrator.demo/pic/1471239221033.jpg"));
        //  testImg = (ImageView)findViewById(R.id.test);
        loading.show();
        img2.setPaintWidth(lineWidth);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        upImgCallBack = new NetCallBack() {
            @Override
            public void onSucess(String msg) {
                // Toast.makeText(MainActivity.this,"上传图片成功",Toast.LENGTH_SHORT).show();
                Log.e("callBack", msg);
                Message message = Message.obtain();
                message.what = 1;
                message.obj = msg;
                mHandler.sendMessage(message);
            }

            @Override
            public void onFailure(String msg) {
                // Toast.makeText(MainActivity.this,"上传图片失败",Toast.LENGTH_SHORT).show();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = msg;
                mHandler.sendMessage(message);
                Log.e("callBack", msg);
            }

            @Override
            public void onServerResponse(Object Msg) {
                Log.e("callBack", "" + Msg.toString());
                Message message = Message.obtain();
                message.what = 1;
                message.obj = Msg;
                mHandler.sendMessage(message);
            }

            @Override
            public void onDownLoadPic(Bitmap bt) {
                Message message = Message.obtain();
                message.what = 5;
                message.obj = bt;
                mHandler.sendMessage(message);
            }
        };
        px1 = lindWidthView.findViewById(R.id.px_1);
        px2 = lindWidthView.findViewById(R.id.px_2);
        px3 = lindWidthView.findViewById(R.id.px_3);
        px4 = lindWidthView.findViewById(R.id.px_4);
        px5 = lindWidthView.findViewById(R.id.px_5);
        px6 = lindWidthView.findViewById(R.id.px_6);
        px7 = lindWidthView.findViewById(R.id.px_7);
        px8 = lindWidthView.findViewById(R.id.px_8);
        px9 = lindWidthView.findViewById(R.id.px_9);
        show_px = (TextView) lindWidthView.findViewById(R.id.show_px);
        ling_px = (View) lindWidthView.findViewById(R.id.ling_px);
        ling_Color = (View) lindColorView.findViewById(R.id.ling_color);
        ling_Color.setTag("#ffffffff");
        px1.setSelected(true);
        px1.setPressed(true);
        WidthSelectView = px1;
        WidthSelectViewL = px1;
    }

    void showDialog(final View v) {
        dialog.removeAllViews();
        if (contentParams.width == 0) {
            Message message = Message.obtain();
            message.what = 6;
            message.obj = lineWidth + "";
            mHandler.sendMessage(message);
            WidthSelectViewL.setPressed(true);
            WidthSelectViewL.setSelected(true);
            YoYo.with(Techniques.FadeIn).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator arg0) {
                    dialog.addView(v);
                    ling_px.setBackgroundColor(Color.parseColor(curSelectColor));
                    ling_Color.setBackgroundColor(Color.parseColor(curSelectColor));
                    contentParams.width = 300;
                    dialog.setLayoutParams(contentParams);
                }

                @Override
                public void onAnimationRepeat(Animator arg0) {
                }

                @Override
                public void onAnimationEnd(Animator arg0) {

                }

                @Override
                public void onAnimationCancel(Animator arg0) {
                }
            }).playOn(dialog);
        } else {
            YoYo.with(Techniques.FadeOut).duration(200).interpolate(new AccelerateDecelerateInterpolator()).withListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator arg0) {
                }

                @Override
                public void onAnimationRepeat(Animator arg0) {
                }

                @Override
                public void onAnimationEnd(Animator arg0) {
                    WidthSelectView.setPressed(false);
                    WidthSelectView.setSelected(false);
                    contentParams.width = 0;
                    dialog.setLayoutParams(contentParams);
                }

                @Override
                public void onAnimationCancel(Animator arg0) {
                }
            }).playOn(dialog);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.reset:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                img2.reset();
                break;
            case R.id.pic:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                w = rl.getMeasuredWidth();
                h = rl.getMeasuredHeight();
                getImageFromCamera();
                break;
            case R.id.save:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                try {
                    String path = img2.save();
                    TestClient.instance(upImgCallBack).sendImg(path);
                    Toast.makeText(this, "图片保存成功 " + path, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "图片保存失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ddd:
//             s = new ComDialog.Builder(MainActivity.this).setDialogView(R.layout.select_paint_width_dialog).setStyle(R.style.ShareDialog).setEndDuration(200).setEndTechnique(Techniques.SlideOutLeft).setGravity(Gravity.LEFT).setIsCancelable(false).setStartDuration(200).setStartTechnique(Techniques.SlideInLeft).setViewClickLinstener(R.id.ok,this).setViewClickLinstener(R.id.cancel,this).build();
//             s.show();
//              seekbar = (SeekBar) s.findViewById(R.id.seek);
//              seekbar.setProgress(curWidth);
//              Message message = Message.obtain();
//              message.what=4;
//              message.arg1 = curWidth;
//              mHandler.sendMessage(message);
//              showTv = (TextView)s.findViewById(R.id.show_tv);
//              seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                  @Override
//                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                      Message message = Message.obtain();
//                      message.what = 4;
//                      message.arg1 = progress;
//                      mHandler.sendMessage(message);
//                  }
//
//                  @Override
//                  public void onStartTrackingTouch(SeekBar seekBar) {
//
//                  }
//
//                  @Override
//                  public void onStopTrackingTouch(SeekBar seekBar) {
//
//                  }
//              });

                showDialog(lindWidthView);
                break;
            case R.id.ok:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                img2.setPaintWidth(curWidth);
                s.animateDismiss();
                break;
            case R.id.cancel:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                s.animateDismiss();
                break;
            case R.id.back:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                img2.back();
                break;
            case R.id.go:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                img2.go();
                break;
            case R.id.color:
//                onClickColorPickerDialog();

                showDialog(lindColorView);
                break;
            case R.id.px_1:
            case R.id.px_2:
            case R.id.px_3:
            case R.id.px_4:
            case R.id.px_5:
            case R.id.px_6:
            case R.id.px_7:
            case R.id.px_8:
            case R.id.px_9:
                if (v.getId() == WidthSelectView.getId()) {
                    return;
                }
                if (WidthSelectView != null) {
                    WidthSelectView.setSelected(false);
                    WidthSelectView.setPressed(false);
                }
                if (WidthSelectViewL != null) {
                    WidthSelectViewL.setSelected(false);
                    WidthSelectViewL.setPressed(false);
                }
                v.setSelected(true);
                v.setPressed(true);
                WidthSelectView = v;
                Message message = Message.obtain();
                message.what = 6;
                message.obj = v.getTag();
                mHandler.sendMessage(message);
                break;
            case R.id.color_1:
            case R.id.color_2:
            case R.id.color_3:
            case R.id.color_4:
            case R.id.color_5:
            case R.id.color_6:
            case R.id.color_7:
            case R.id.color_8:
            case R.id.color_9:
                WidthSelectView = v;
                Message message1 = Message.obtain();
                message1.what = 7;
                message1.obj = v.getTag();
                mHandler.sendMessage(message1);
                break;
            case R.id.width_cancel:
                WidthSelectView.setPressed(false);
                WidthSelectView.setSelected(false);
                showDialog(lindWidthView);
                break;
            case R.id.width_ok:
                lineWidth = inPx;
                WidthSelectViewL = WidthSelectView;
                img2.setPaintWidth(lineWidth);
                showDialog(lindWidthView);
                break;
            case R.id.color_cancel:
                showDialog(lindColorView);
                break;
            case R.id.color_ok:
                img2.setPaintColor(Color.parseColor(ling_Color.getTag().toString()));
                curSelectColor = ling_Color.getTag().toString();
                showDialog(lindColorView);
                break;
            default:
                if(contentParams.width>0){
                    showDialog(WidthSelectView);
                    return;
                }
                Toast.makeText(this, "暂不支持此功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onClickColorPickerDialog() {
        ColorPickerDialogFragment f = ColorPickerDialogFragment
                .newInstance(1, null, null, img2.getPaintColor(), true);
        f.setListener(this);
        f.setStyle(DialogFragment.STYLE_NORMAL, R.style.LightPickerDialogTheme);
        f.show(getFragmentManager(), "d");

    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        img2.setPaintColor(color);
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }

    @Override
    public void onColorChanged(int newColor) {
        img2.setPaintColor(newColor);
    }
}
