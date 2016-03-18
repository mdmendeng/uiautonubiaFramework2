package com.nubia.uiautotest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	/**
	 * 当前日期时间
	 * @return
	 */
	public static String getDate(){
		 Calendar c=Calendar.getInstance();    
	     SimpleDateFormat f=new SimpleDateFormat("yyyyMMddhhmmss");    
	     return f.format(c.getTime()); 
	}
}
