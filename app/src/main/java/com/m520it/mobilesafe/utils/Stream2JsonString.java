package com.m520it.mobilsafe.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}将字节流转换为json格式的字符串
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Stream2JsonString {
    public static String readStream(InputStream is) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();
        while(line!=null){
            buffer.append(line);
            line = reader.readLine();

        }
        return buffer+"";
    }

}
