package com.m520it.mobilsafe.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m520it.mobilsafe.R;
import com.m520it.mobilsafe.conf.Constant;
import com.m520it.mobilsafe.db.dao.ShowAddressDao;
import com.m520it.mobilsafe.utils.SPUtils;

/**
 * @author 王维波
 * @time 2016/9/11  17:10
 * @desc ${TODD}
 */
public class ShowAddresssServer extends Service {

    private TelephonyManager tm;
    private MyListener listener;
    private InnerCallOutReceiver receiver;
    private WindowManager mWM;
    private View view;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //电话打进来的监听
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        listener = new MyListener();
        tm.listen(listener,PhoneStateListener.LISTEN_CALL_STATE);


        receiver = new InnerCallOutReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        registerReceiver(receiver,filter);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        tm.listen(listener,PhoneStateListener.LISTEN_NONE);
        tm=null;
        listener=null;

        unregisterReceiver(receiver);
        receiver=null;
        super.onDestroy();
    }


    class MyListener extends PhoneStateListener{

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state){
                case TelephonyManager.CALL_STATE_RINGING://响铃
                    String address = ShowAddressDao.getAddress(incomingNumber);
                    showToast(address);

                    break;
                case TelephonyManager.CALL_STATE_IDLE://空闲状态
                    if (mWM!=null){
                        mWM.removeView(view);
                    }

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接通状态

                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }


    }

    class InnerCallOutReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //获得打出去的电话
            String phone = getResultData();
            String address = ShowAddressDao.getAddress(phone);
            showToast(address);
        }
    }
    //显示自定义的吐司
    private void showToast(String address) {
        int[] icons = new int[]{R.drawable.call_locate_white,R.drawable.call_locate_orange,R.drawable.call_locate_blue,R.drawable.call_locate_gray,R.drawable.call_locate_green};
        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;


        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        view = View.inflate(getApplicationContext(), R.layout.view_toast,null);
       LinearLayout ll_background = (LinearLayout) view.findViewById(R.id.ll_show);

        int which = SPUtils.getInt(getApplicationContext(), Constant.WHICHBACKGROUND);
       ll_background.setBackgroundResource(icons[which]);
       TextView tv = (TextView) view.findViewById(R.id.tv_location);
       tv.setText(address);
        mWM.addView(view, mParams);


    }
}
