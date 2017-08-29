package com.servicenow.applicationspecificlibraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.servicenow.genericlibraries.ReporterLogs;

public class Utils {
	
	/*
	 * Author : Sathyanarayanan V
	 * Objective : Capture current system time
	 */
	public static String getCurrentDateTime() throws Exception {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
	 //get current date time with Date()
	 Date date = new Date();
	 // Now format the date
	 String date1= dateFormat.format(date);
	 // Print the Date
	 ReporterLogs.log("Current date and time in yyyy-MM-dd HH:mm:ss is "+date1, "info");
	 return date1;			
}

}