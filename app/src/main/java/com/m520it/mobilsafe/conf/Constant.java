package com.m520it.mobilsafe.conf;

/**
 * @author 王维波
 * @time 2016/9/5  11:32
 * @desc 常量类,用来保存所有的一些常量信息
 */
public class Constant {
    public  static final String UPDATE="update";
    public  static final String PASSWORD="password";
    public  static final String SPNAME="config";
    public static final String FINISH = "finish";
    public static final String SIMNUMBER = "simnumber";
    public static final String SAFENUMBER = "safenumber";
    public static final String PROTOCOTING = "protocoting";
    public static final String BLACKINTERFACE = "blackinterface";
    public static final String WHICHBACKGROUND = "whichbackground";
    public static String X;
    public static String Y;

    public static class URL{
        public static final String BASEURL="http://192.168.3.45:8080/";
        public  static  final String UPDATEURL=BASEURL+"update.json";

    }
}
