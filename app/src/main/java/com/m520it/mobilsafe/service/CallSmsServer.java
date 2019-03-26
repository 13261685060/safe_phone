package com.m520it.mobilsafe.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author 王维波
 * @time 2016/9/10  17:27
 * @desc ${TODD}
 */
public class CallSmsServer extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("哥被打开了");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("哥被打关闭了");
        super.onDestroy();
    }
}
