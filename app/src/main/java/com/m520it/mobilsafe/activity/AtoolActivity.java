package com.m520it.mobilsafe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.m520it.mobilsafe.R;
import com.m520it.mobilsafe.utils.IntentUtils;

/**
 * @author 王维波
 * @time 2016/9/11  16:06
 * @desc ${TODD}
 */
public class AtoolActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_atool);
    }

    public  void queryAddress(View v){
        IntentUtils.startIntent(AtoolActivity.this,ShowAddressActivity.class);
    }
}
