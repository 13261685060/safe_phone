package com.m520it.mobilesafe.activity;

import android.app.Activity;
import android.os.Bundle;

import com.m520it.mobilesafe.R;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Setup4Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceStata){
        super.onCreate(savedInstanceStata);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_setup3);
    }
}
