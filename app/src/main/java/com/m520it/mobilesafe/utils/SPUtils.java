package com.m520it.mobilsafe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.m520it.mobilsafe.views.Constant;

/**
 * @author Administrator
 * @version $Rev$
 * @des 专门用来管理sp
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SPUtils {
    //得到一个sp
    public static SharedPreferences getSp(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constant.SPNAME,Context.MODE_PRIVATE);
        return sp;
    }
    //得到sp里面保存的boolean
    public static boolean getBoolean(Context context,String key){
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key,false);

    }
    //往sp里面保存一个boolean
    public static void putBoolean(Context context,String key,boolean state){
        SharedPreferences sp = getSp(context);
        sp.edit().putBoolean(key,state).commit();
    }
}
