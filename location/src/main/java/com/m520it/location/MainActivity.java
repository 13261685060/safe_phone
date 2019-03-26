package com.m520it.location;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1 定位第一步先获得定位的管理者
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
       /* //2 拿到当前设备所支持的定位方式
        List<String> providers = lm.getAllProviders();
        for (String  provider: providers) {
            System.out.println("定位方式:"+provider);
        }*/

        /**
         * 获得当前的最好的定位提供者
         * critrtia 当前获得最好定位的方式匹配器,里面会封装当前定位的一些要求
         * true 只有获得,瞒住当前要求后,才放回一个定位方式
         */


        Criteria critrtia = new Criteria();
        //定位的精度,一般精度越高越准确,并且耗费电
        critrtia.setAccuracy(Criteria.ACCURACY_FINE);
        //设置高功率
        critrtia.setPowerRequirement(Criteria.POWER_HIGH);
        //获得当前最后的定位方式
        String bestProvider = lm.getBestProvider(critrtia, true);
        System.out.println("目前最好的定位方式:" + bestProvider);
        //通过最好的定位方式获取到经纬度

        lm.requestLocationUpdates(bestProvider, 0, 0, new LocationListener() {

           //位置改变之后的回调方法
            @Override
            public void onLocationChanged(Location location) {
                //经度
                double longitude = location.getLongitude();
                //纬度
                double latitude = location.getLatitude();
                TextView tv=new TextView(getApplicationContext());
                tv.setText("j="+longitude+"  "+"w="+latitude);
                tv.setTextColor(Color.RED);
                setContentView(tv);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


    }
}
