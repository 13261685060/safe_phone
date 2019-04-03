package com.m520it.mytoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private ImageView iv_icon;
    private float startX;
    private float startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       iv_icon = (ImageView)findViewById(R.id.iv_icon);
        //监听手指触摸屏幕的方法
        iv_icon.setOnTouchListener(new View.OnTouchListener() {



            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获得手指在屏幕上的不同事件
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN://手指按下去
                        System.out.println("手指放下");
                        //  1 获得开始的坐标
                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE://手指在屏幕上面移动
                        System.out.println("手指移动");
                        // 2 获得移动后的坐标
                        float newX = event.getRawX();
                        float newY = event.getRawY();

                        //3 获得偏移量

                        int  dx= (int) (newX-startX);
                        int dy = (int) (newY - startY);
                        // 4开始位置移动
                        iv_icon.layout(iv_icon.getLeft()+dx,iv_icon.getTop()+dy,iv_icon.getRight()+dx,iv_icon.getBottom()+dy);
                        // 5 初始化 开始坐标

                        startX = event.getRawX();
                        startY = event.getRawY();

                        break;
                    case MotionEvent.ACTION_UP://手指抬起来
                        System.out.println("手指抬起来");

                        break;
                }
                return true;
            }
        });
    }
}
