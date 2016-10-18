package com.liulin.net;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface NetCallBack  {
    void onSucess(String Msg);
    void onFailure(String Msg);
    void onServerResponse(Object Msg);
    void onDownLoadPic (Bitmap bt);
}
