package com.pixtime.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PixtimeUtils {

	public PixtimeUtils() {
		// TODO Auto-generated constructor stub
	}

	static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	public static String getCurrentTimeStamp(){
		
		Date date = new Date();
		return dateFormat.format(date);

	}
}
