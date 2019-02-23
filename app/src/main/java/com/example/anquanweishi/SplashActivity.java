package com.example.anquanweishi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AlphaAnimation;

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initAnimation();//初始动画
    }

    /**
     * 动画的显示
     */
    private void initAnimation(){
        AlphaAnimation aa = new AlphaAnimation(0.0f,1.0f);
        aa.setDuration(3000);
        aa.setFillAfter(true);
        rl_root.startAnimation(aa);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    }
}
