package com.m520it.rocket;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class RocketService  extends Service {
    private int startX;
    private int startY;
    private WindowManager mWM;
    private ImageView rocketImage;
    private WindowManager.LayoutParams mParams;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
        mWM.updateViewLayout(rocketImage,mParams);
    }
};


    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;

    }
@SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(){
        super.onCreate();

        rocketImage = new ImageView(this);

        rocketImage.setBackgroundResource(R.drawable.rocket);
        AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketAnimation.start();


        mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;

        //指明当前的Windowmanager显示的控件是什么
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        params.setTitle("Toast");
        //必须去掉不能触摸的属性
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        params.gravity = Gravity.LEFT+Gravity.TOP;//设置吐司默认的位置到左上角
        mWM.addView(rocketImage,mParams);

        rocketImage.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        //1得到按下去的坐标

                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        //得到移动后的坐标
                        int newX = (int) event.getRawX();
                        int newY = (int) event.getRawY();

                        //得到便宜量
                        int dx = newX - startX;
                        int dy = newY - startY;

                        //通知移动
                        mWM.updateViewLayout(rocketImage,mParams);

                        //开始移动
                        params.x+=dx;
                        params.y+=dy;

                        if (params.x<0) params.x = 0;
                        if (params.y<0) params.y = 0;
                        if (params.x>mWM.getDefaultDisplay().getWidth()-rocketImage.getWidth()){
                            params.x=mWM.getDefaultDisplay().getWidth()-rocketImage.getWidth();
                        }
                        if (params.y>mWM.getDefaultDisplay().getWidth()-rocketImage.getWidth()){
                            params.y=mWM.getDefaultDisplay().getWidth()-rocketImage.getWidth();
                        }

                        //初始化开始

                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();

                        break;

                    case MotionEvent.ACTION_UP:

                        if (params.x>20&&params.x<300&&params.y>400){
                            new Thread(){
                                public void run(){
                                    for (int i = 0; i < 20; i++) {
                                        SystemClock.sleep(50);
                                        params.y-=5*i;
                                        handler.sendEmptyMessage(0);

                                    }
                                }
                            }.start();

                        }
                        break;
                }
                return true;
            }
        });

    }
}
