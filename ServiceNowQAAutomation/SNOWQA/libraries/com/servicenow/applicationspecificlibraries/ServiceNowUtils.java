package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import pages.HomePage;

import com.servicenow.genericlibraries.TextBoxes;

public class ServiceNowUtils {

	public static void navigateToModuleName(WebDriver driver, String moduleName) throws InterruptedException {
		WaitUtils.waitForIdPresent(driver, "filter");
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching");
		WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
        HomePage.getCreateNewBtn(driver).click();
		Frames.switchToFrameById("gsft_main", driver);	
	}
	
		
	public static void scrollingToElementofAPage(WebElement element, WebDriver driver)
	{
	   try
	      {
	         ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
	         }
	   catch(Exception e){
		  e.getMessage();
	   }
	   
	   }
		   	                                                              
	                                                                
	                                }


