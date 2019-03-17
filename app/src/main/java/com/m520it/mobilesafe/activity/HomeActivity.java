package com.m520it.mobilsafe.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.m520it.mobilesafe.R;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * @author 王维波
 * @time 2016/9/5  11:45
 * @desc ${TODD}
 */
public class HomeActivity extends Activity {
    private ImageView iv_home_logo;
    private GridView gv_home;
    private static final String names[] = {"手机防盗", "通讯卫士", "软件管家", "进程管理", "流量统计", "病毒查杀", "缓存清理", "高级工具"};
    private static final String desc[] = {"手机丢失好找", "防骚扰监听", "方便管理软件", "保存手机畅通", "注意流量超标", "手机安全管家", "手机快步如飞", "特性处理更好"};
    private static final int icons[] = {R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f,
            R.drawable.h, R.drawable.j};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initAnimation();
        initData();
        initEvent();
    }

    //初始化视图和控件
    private void initView() {
        setContentView(R.layout.activity_home);
        //需要设置动画的图片
        iv_home_logo = (ImageView) findViewById(R.id.iv_home_logo);
        //seeting图片
        ImageView iv_home_setting = (ImageView) findViewById(R.id.iv_home_setting);
        //GridView
        gv_home = (GridView) findViewById(R.id.gv_home);

    }

    //初始化动画
    private void initAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_home_logo, "rotationY", 0, 60, 120, 180, 240, 300, 360);

        //每循环一次时间
        objectAnimator.setDuration(2000);
        //无线循环
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        //动画开始
        objectAnimator.start();
    }

    //初始化数据
    private void initData() {
        HomeAdapter adapter=new HomeAdapter();
        gv_home.setAdapter(adapter);
    }

    //初始化事件
    private void initEvent() {

    }

    class HomeAdapter extends BaseAdapter {
        //显示item的个数
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view = View.inflate(getApplicationContext(), R.layout.item_home, null);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_item_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_item_name);
            TextView tv_desc = (TextView) view.findViewById(R.id.tv_item_desc);
            iv_icon.setImageResource(icons[position]);
            tv_name.setText(names[position]);
            tv_desc.setText(desc[position]);

            return view;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }
}
