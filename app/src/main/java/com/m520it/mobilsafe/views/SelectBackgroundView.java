package com.m520it.mobilsafe.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m520it.mobilsafe.R;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SelectBackgroundView extends RelativeLayout {
    private TextView tv_big;
    private TextView tv_small;

    public SelectBackgroundView(Context context) {
        this(context,null);
    }

    public SelectBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        String bigtitle = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","bigtitle");
        String smalltitle = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","smalla");
        tv_big.setText(bigtitle);
        tv_small.setText(smalltitle);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.view_toast_background,this);
        //大标题
        tv_big = (TextView) findViewById(R.id.tv_bigtitle);
        //小标题
        tv_small = (TextView) findViewById(R.id.tv_small);
    }

    public void setDesc(String text){
        tv_small.setText(text);
    }
}
