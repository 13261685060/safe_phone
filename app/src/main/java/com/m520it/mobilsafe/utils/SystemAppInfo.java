package com.m520it.mobilsafe.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.m520it.mobilsafe.bean.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO} 获得所有的安装软件的信息
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SystemAppInfo {
    public static List<AppInfo> getAllAppInfo(Context context){
        List<AppInfo> infos = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        //获得当前安装在该设备上面的所有数据集合
        List<PackageInfo> infoList = pm.getInstalledPackages(0);
        for(PackageInfo info: infoList){
            AppInfo appInfo = new AppInfo();
            String packageName = info.packageName;
            appInfo.setPackname(packageName);
            Drawable icon = info.applicationInfo.loadIcon(pm);
            appInfo.setIcon(icon);
            String name = info.applicationInfo.loadLabel(pm).toString();
            appInfo.setName(name);
            String path = info.applicationInfo.sourceDir;
            File file = new File(path);
            long size = file.length();
            appInfo.setSize(size);
            int flags = info.applicationInfo.flags;
            if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE)==0){
                //说明不是安装在SD卡上
                appInfo.setRom(true);
            }else{
                appInfo.setRom(false);
            }

            if ((flags & ApplicationInfo.FLAG_SYSTEM)==1){
                appInfo.setUser(false);
            }else{
                appInfo.setUser(true);

            }
            infos.add(appInfo);

        }

        return null;
    }
}
