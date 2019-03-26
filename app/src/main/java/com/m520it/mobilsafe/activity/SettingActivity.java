package com.m520it.mobilsafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.m520it.mobilsafe.conf.Constant;
import com.m520it.mobilsafe.R;
import com.m520it.mobilsafe.service.CallSmsServer;
import com.m520it.mobilsafe.utils.SPUtils;
import com.m520it.mobilsafe.views.SettingItemView;

/**
 * @author 王维波
 * @time 2016/9/7  9:11
 * @desc ${TODD}
 */
public class SettingActivity extends Activity {
    private SettingItemView siv_update;
    private SettingItemView siv_black_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        setContentView(R.layout.activity_setting);
        siv_update = (SettingItemView)findViewById(R.id.siv_update);
        siv_black_interface = (SettingItemView)findViewById(R.id.siv_black_interface);

    }

    private void initData() {

        boolean update = SPUtils.getBoolean(getApplicationContext(), Constant.UPDATE);
        siv_update.setToggle(update);

    }

    private void initEvent() {
        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update = SPUtils.getBoolean(getApplicationContext(), Constant.UPDATE);
                update=!update;
                siv_update.setToggle(update);
                SPUtils.putBoolean(getApplicationContext(),Constant.UPDATE,update);
            }
        });

        siv_black_interface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = SPUtils.getBoolean(getApplicationContext(), Constant.BLACKINTERFACE);
                   if(result) {
                       //设置关闭
                       Intent intnetserver=new Intent(SettingActivity.this, CallSmsServer.class);
                       stopService(intnetserver);
                   }else {
                       //设置为开启
                       Intent intnetserver=new Intent(SettingActivity.this, CallSmsServer.class);
                       startService(intnetserver);
                   }
                    result=!result;
                    siv_black_interface.setToggle(result);
                   SPUtils.putBoolean(getApplicationContext(),Constant.BLACKINTERFACE,result);


            }
        });
    }
}
