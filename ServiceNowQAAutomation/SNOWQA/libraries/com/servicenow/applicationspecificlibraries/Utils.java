package com.servicenow.applicationspecificlibraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	public static void scrollingToElementofAPage(WebElement element, WebDriver driver,String elementName)
	{
	   try
	      {
		       ReporterLogs.log("Scrolling to element " + elementName, "info");  
			   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
	         }
	   catch(Exception e){
		   ReporterLogs.log("Unable to Scroll to element " + elementName, "error");
		   ReporterLogs.log("Exception " + e.getMessage(), "error");
		  
	   }
}

}