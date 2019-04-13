package com.m520it.mobilsafe.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}短信备份
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

public class SmsTools {
    //接口里面的方法都是抽象的
    public interface BackupSms{
        //备份之前要获得总数
        void beforeBackup(int max);
        //备份中参数，需要知道备份到那个位置
        void backuping(int process);
    }
    public static boolean smsBackup(Context context, String filename, BackupSms backup) {
        try {
        //查询别人数据库步骤
        //1获得一个解析器
        ContentResolver resolver = context.getContentResolver();
        //2获得对应查询的URI，就是路径.一定不要忘记查询数据库的步骤
        Uri uri = Uri.parse("content://sms/");

        //保存文件到本地要先有一个文件夹，获得文件夹需要路径和文件夹两个条件
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        //文件写入流
            FileOutputStream fos = new FileOutputStream(file);
        //得到一个xlm序列化器
            XmlSerializer serializer = Xml.newSerializer();
            //设置输出流
            serializer.setOutput(fos,"utf-8");
            serializer.startDocument("utf-8",true);
            serializer.startTag(null,"info");
            Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
             backup.beforeBackup(cursor.getCount());
            int presss = 0;
            while (cursor.moveToNext()) {


                serializer.startTag(null,"sms");
                serializer.startTag(null,"address");
                String address = cursor.getString(0);
                serializer.text(address);
                serializer.endTag(null,"address");

                serializer.startTag(null,"date");
                String date = cursor.getString(1);
                serializer.text(date);
                serializer.endTag(null,"date");

                serializer.endTag(null,"typa");
                String type = cursor.getString(2);
                serializer.text(type);
                serializer.endTag(null,"type");

                serializer.startTag(null,"body");
                String body = cursor.getString(3);
                serializer.text(body);
                serializer.endTag(null,"body");
                serializer.endTag(null,"sms");
                SystemClock.sleep(2000);
                presss++;
                //当前的备份进度
                backup.backuping(presss);
            }
            serializer.endTag(null,"info");
            serializer.endDocument();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
