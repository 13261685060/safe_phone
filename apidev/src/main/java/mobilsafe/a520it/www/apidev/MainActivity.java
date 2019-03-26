package mobilsafe.a520it.www.apidev;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //点我就激活
    public  void startAtive(View view){
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        ComponentName componentNa=new ComponentName(this,DEVReceiver.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentNa);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "点我就激活,不然没法用");
        startActivity(intent);

    }


    //点击就锁屏
    public  void click(View view){
      DevicePolicyManager apm= (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

        ComponentName componentNa=new ComponentName(this,DEVReceiver.class);
        boolean active = apm.isAdminActive(componentNa);
        if(active) {
            //说明已经激活
            //锁屏
            apm.lockNow();
            //清楚数据
            apm.wipeData(DevicePolicyManager.WIPE_RESET_PROTECTION_DATA);
            //设置密码
            apm.resetPassword("123",0);
        }else {
            Toast.makeText(MainActivity.this, "没有激活高级管理权限,不能使用", Toast.LENGTH_SHORT).show();
        }

    }


    public  void unclick(View view){
        DevicePolicyManager apm= (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        ComponentName componentNa=new ComponentName(this,DEVReceiver.class);
        apm.removeActiveAdmin(componentNa);
       /* <action android:name="android.intent.action.VIEW" />
        <action android:name="android.intent.action.DELETE" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="package" />*/
        Intent intent=new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setAction("android.intent.action.DELETE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("package:"+getPackageName()));
        startActivity(intent);
    }
}
