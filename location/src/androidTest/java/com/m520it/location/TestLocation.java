package com.m520it.location;

import android.test.AndroidTestCase;

import java.io.InputStream;

/**
 * @author 王维波
 * @time 2016/9/10  10:39
 * @desc ${TODD}
 */
public class TestLocation extends AndroidTestCase {

    public void testlocation() throws Exception {

        InputStream in = getContext().getAssets().open("axisoffset.dat");
        ModifyOffset modifyOffset=ModifyOffset.getInstance(in);
        PointDouble pt=new PointDouble(37.422006,-122.084095);
        PointDouble newPoint = modifyOffset.s2c(pt);
        double x = newPoint.x;
        double y = newPoint.y;
        System.out.println("x="+x+"  "+"y="+y);
    }
}
