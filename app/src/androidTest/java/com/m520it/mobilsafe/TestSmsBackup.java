package com.m520it.mobilsafe;

import android.test.AndroidTestCase;

import com.m520it.mobilsafe.utils.SmsTools;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TestSmsBackup extends AndroidTestCase {
    public void testSmsbackup(){
        boolean result = SmsTools.smsBackup(getContext());
        assertEquals(true,result);
    }
}
