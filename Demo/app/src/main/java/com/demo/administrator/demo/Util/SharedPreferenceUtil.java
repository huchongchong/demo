package com.demo.administrator.demo.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferenceUtil {
    public static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences("Token", Context.MODE_PRIVATE);
    }
    public static String getUnionId(Context con) {
        SharedPreferences sp = getSp(con);
        String union_id = sp.getString("union_id", "");
        return union_id;
    }
    public static boolean getCanUseIM(Context con){
        SharedPreferences sp=getSp(con);
        return sp.getBoolean("can_use_im", false);
    }
    public static void setCanUseIM(Context con,boolean canUseIM){
        SharedPreferences sp=getSp(con);
        if(sp==null){
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("can_use_im",canUseIM);
            editor.commit();
        }
    }
    public static void settUnionId(Context con, String union_id) {
        SharedPreferences sp = getSp(con);
        if(sp==null||TextUtils.isEmpty(union_id))
            return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("union_id", union_id);
        editor.commit();
    }
    public static String getToken(Context con) {
        SharedPreferences sp = getSp(con);
        String union_id = sp.getString("Token", "");
        return union_id;
    }
    public static void setToken(Context con, String Token) {
        SharedPreferences sp = getSp(con);
        if(sp==null||TextUtils.isEmpty(Token))
            return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Token", Token);
        editor.commit();
    }
    public static void clearUserData(Context con){
        SharedPreferences sp = getSp(con);
        if(sp==null)
            return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Token","");
        editor.putString("union_id","");
        editor.commit();
    }

    public static String getParkSn(Context con) {
        SharedPreferences sp = getSp(con);
        String union_id = sp.getString("ParkSn", "");
        return union_id;
    }

    public static void setParkSn(Context con, String parkSn) {
        SharedPreferences sp = getSp(con);
        if(sp==null||TextUtils.isEmpty(parkSn))
            return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ParkSn", parkSn);
        editor.commit();
    }

    public static String getParkName(Context con) {
        SharedPreferences sp = getSp(con);
        String union_id = sp.getString("ParkName", "");
        return union_id;
    }

    public static void setParkName(Context con, String parkName) {
        SharedPreferences sp = getSp(con);
        if(sp==null||TextUtils.isEmpty(parkName))
            return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ParkName", parkName);
        editor.commit();
    }


    public static String getSession(Context con) {
        SharedPreferences sp = getSp(con);
        String union_id = sp.getString("Session", "");
        return union_id;
    }
    public static void setSession(Context con, String Token) {
        SharedPreferences sp = getSp(con);
        if(sp==null||TextUtils.isEmpty(Token))
            return;
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Session", Token);
        editor.commit();
    }
}
