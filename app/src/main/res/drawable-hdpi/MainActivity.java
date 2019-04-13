package mobilsafe.a520it.www.popuwindow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout root;
    private PopupWindow pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (RelativeLayout)findViewById(R.id.root);
    }

    public  void showPP(View view){
        TextView tv=new TextView(this);
        tv.setText("hah,我是pp");
        tv.setTextColor(Color.RED);
        //显示一个pp
        /**
         * tv 显示内容
         * 200 pp 的宽度
         * 30 pp 高度
         */
        pp = new PopupWindow(tv,200,30);
        pp.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        /**root 挂在的父控件
         * Gravity.LEFT+Gravity.TOP 设置空间左上角对齐
         * 20 离屏幕左边距离
         * 40 离屏幕上面距离
         */
        pp.showAtLocation(root, Gravity.LEFT+Gravity.TOP,50,200);
    }

    @Override
    protected void onDestroy() {
        if(pp!=null) {
            pp.dismiss();
            pp=null;
        }
        super.onDestroy();
    }
}
