package com.m520it.mobilesafe.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m520it.mobilesafe.R;
import com.m520it.mobilesafe.utils.SystemInfoUtils;

public class SplashActivity extends Activity {
    private RelativeLayout rl_splash_root;//跟布局
    private TextView tv_splash_version_name;//版本名称
    private TextView tv_splash_version_code;//版本号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //界面初始化
        initView();
        //初始化动画
        initAnimation();
        //初始化数据
        initData();
        //初始化事件
        initEvent();
    }

    //界面初始化
    private void initView() {
        setContentView(R.layout.activity_main);
        //初始化根布局
        rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
        //初始化版本名称
        tv_splash_version_name = (TextView) findViewById(R.id.tv_splash_version_name);
        //版本号
        tv_splash_version_code = (TextView) findViewById(R.id.tv_splash_version_code);

    }

    //初始化动画
    private void initAnimation() {
        /**
         * 0开始旋转的角度
         * 360结束角度
         * Animation.RELATIVE_TO_SELF锚点，相对于自己的某个位置固定
         * 0.5f在X轴方向上相对于自己一半的宽度去旋转
         */
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画的时间
        ra.setDuration(2000);
        //补间动画，又称影子动画
        //固定动画做完的的位置
        ra.setFillAfter(true);
        /**
         * 0.0f在X轴方向从0放大到1
         */
        //比例动画
        ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画的时间
        sa.setDuration(2000);
        //补间动画，又称影子动画
        //固定动画做完的的位置
        sa.setFillAfter(true);
        /**
         * 0.0f完全透明
         * 1.0全不透
         */
        //透明度渐变动画
        AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
        //设置动画的时间
        aa.setDuration(2000);
        //补间动画，又称影子动画
        //固定动画做完的的位置
        aa.setFillAfter(true);
        /**
         * false不共用动画插入器
         */
        //动画容器，用来装载所有的动画，然后统一运行

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(ra);
        animationSet.addAnimation(sa);
        animationSet.addAnimation(aa);
        rl_splash_root.startAnimation(animationSet);

    }

    //初始化数据
    private void initData() {
        try {
            tv_splash_version_code.setText(SystemInfoUtils.getVersionCode(getApplicationContext()) + "");

            tv_splash_version_name.setText(SystemInfoUtils.getVersionCode(getApplicationContext()));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //初始化事件
    private void initEvent() {
    }
}
