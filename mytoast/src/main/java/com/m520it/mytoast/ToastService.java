package com.m520it.toast;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author 王维波
 * @time 2016/9/13  9:48
 * @desc ${TODD}
 */
public class ToastService extends Service {

    private WindowManager mWM;
    private TextView tv;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;


        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        tv = new TextView(getApplicationContext());
        tv.setText("我是自定义的吐司");
        tv.setTextColor(Color.RED);
        mWM.addView(tv, mParams);
    }

    @Override
    public void onDestroy() {
        mWM.removeView(tv);
        super.onDestroy();
    }
}
