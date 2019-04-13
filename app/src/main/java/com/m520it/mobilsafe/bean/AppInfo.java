package com.m520it.mobilsafe.bean;

import android.graphics.drawable.Drawable;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class AppInfo {
    private String name;//软件名称
    private String packname;//软件的包名
    private boolean isRom;//是否安装在手机内存里面
    private long size;
    private Drawable icon;//软件的图片
    private boolean isUser;

    public String getName() {
        return name;
    }

    public AppInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getPackname() {
        return packname;
    }

    public AppInfo setPackname(String packname) {
        this.packname = packname;
        return this;
    }

    public boolean isRom() {
        return isRom;
    }

    public AppInfo setRom(boolean rom) {
        isRom = rom;
        return this;
    }

    public long getSize() {
        return size;
    }

    public AppInfo setSize(long size) {
        this.size = size;
        return this;
    }

    public Drawable getIcon() {
        return icon;
    }

    public AppInfo setIcon(Drawable icon) {
        this.icon = icon;
        return this;
    }

    public boolean isUser() {
        return isUser;
    }

    public AppInfo setUser(boolean user) {
        isUser = user;
        return this;
    }
}
