package com.servicenow.applicationspecificlibraries;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;
import pages.HomePage;
import pages.IncidentPage;
import pages.IncidentTask;
import pages.ProblemPage;

public class IncidentReusables {
	static String assignmentGroup2 = null;
	static String openedByGrouptaskValue = null;
	static String Assignmentgroup1 =null;
	static String incidentState = null;
	static String date = null;
	static String incidenttaskState = null;
	static String businessServceValue = null;
	static String sourcecivalue = null;
	static String incidentmangroup = null;
	static String assignmentGroupValue = null;
	static String openedByGroupValue = null;
	static String userImpact = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForIncidentManager = null;
	static String incidentNumber = null;
	static String copiedincidentNumber = null;
	static String incMgrReqdValue = null;
	static String incNumber = null;
	static String assignedTo = null;
	static String incidentStateWIP = null;
	static String configurationItem = null;
	static String causeCode = null;
	static String subCauseCode = null;
	static String mitigationSolutionSteps = null;
	static String incidentStateResolved = null;
	static String reasonForCancellation = null;
	static String incidentStateCancelled = null;
	static String worknotes = null;
	static String test = null;
	static String Latestupdate = null;
	static WebElement ATTnumber = null;
	static String incidenttasknum = null;
	private static WebElement element;
	static String problemId=null;
	static String additionalcomments = null;
	static String source=null;
	static String ManTestCase = null;
	static String changeId=null;
	static String reasonForChange = null;
	static String ChildIncnum = null;
	static WebElement incidenttab=null;
	static String SourceCI = null;
	static String Incidentowner = null;
	static String Incidentmanagergroup = null;
	static String Incidentmanager = null;
	static String Accountablebusinessunit = null;
	static String Affectedbusinessunit = null;
	static String incidentalertnumber = null;
	
	
	
	
	
	public static String createIncident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 50);
				
		//if ManagedIncident is true, creates managed Incident ticket
		if (ManagedIncident) {
			
			//ExtentReport.reportLog(LogStatus.INFO, "New Incident Creation page");
			ReporterLogs.log("New Incident Creation page", "info");
			
			//Reading values from Excel file for Managed Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 9);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			reasonForIncidentManager=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 12);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 60);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			
			//Business Service Field
	
			//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
			//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			/*driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
			ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			Thread.sleep(6000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);*/
			
			//Assignment group value
			WebDriverWait wait = new WebDriverWait(driver,20);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='sys_display.incident.u_business_service']")));		
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			
			//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
			//driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
			//driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
			//ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			//Thread.sleep(6000);
			//IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			ReporterLogs.log("Opened by Group field is entered successfully "+ openedByGroupValue, "info");		
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			ReporterLogs.log("User impact field is entered successfully "+ userImpact, "info");	
			//Incident Manager Required Checkbox
			if (IncidentPage.getIncidentManagerRequiredChkbox(driver).isEnabled()) {
				IncidentPage.getIncidentManagerRequiredChkbox(driver).click();
				WaitUtils.waitForIdPresent(driver, "incident.u_reason_for_incident_manager");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getReasonForIncidentManagerDropdown(driver), reasonForIncidentManager, "Reason for Incident Manager");
			}else{
				ReporterLogs.log("Incident Manager Required Checkbox not enabled", "error");
				}	
					
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			Thread.sleep(5000);
			
			//Click on Submit button
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();	
			try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		       // ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }	
			System.out.println(incidentNumber);
		}
		
		else {			
						
			//Reading values from Excel file for Standalone Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 60);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite",1, 2, incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
			
			//Business Service Field
			/*WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
			//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
			ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			Thread.sleep(6000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);*/
			
			
			//Assignment group value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
					
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			ReporterLogs.log("User Impact selected: "+userImpact, "info");
					
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			Thread.sleep(2000);			
			
			//Click on Submit button
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		      //  ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (Exception e) {
		    	System.out.println("e");
		    }
		}
	}catch (UnhandledAlertException f) {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        System.out.println("Alert data: " + alertText);
	        Assert.fail("Unhandled alert");
	        
	        } 
	    catch (NoAlertPresentException e) {
	        e.printStackTrace();
	    }		
	}	
		return incidentNumber;
}
	
			
			
			
	
		public static void verifyManagedIncidentCreation(WebDriver driver, String incNumber) throws Exception
		{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
			 if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				 	Thread.sleep(1000);
			        WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			        TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
			        IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);			            
			        WaitUtils.waitForPageToLoad(driver, 30);            
			        IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
			        WaitUtils.waitForPageToLoad(driver, 30);
			        WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
			        ReporterLogs.log("Title of the page after searching the incident: "+driver.getTitle(), "info");
			        incMgrReqdValue=IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver).getText();
			        if (incMgrReqdValue.equalsIgnoreCase("true")) {
			            	Assert.assertEquals(incMgrReqdValue, "true");
			            	ReporterLogs.log("Successfully created Managed incident with Id "+incNumber, "info");
			               // ExtentReport.reportLog(LogStatus.PASS, "Successfully created Managed incident with Id "+incNumber);
			                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
			               // ExtentReport.reportLog(LogStatus.INFO, "Incident Manager Required value for the incident "+ incNumber +" is "+ incMgrReqdValue);
			            	Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver, "Incident Manager Required");
			      }else {
							ReporterLogs.log("Unable to create a Managed incident Ticket. Incident Manager Required value from Notes tab displayed is "+ incMgrReqdValue, "error");
			               // ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a managed incident ticket");
			                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
			                Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver,"Incident Manager Required");
			                Assert.assertEquals(incMgrReqdValue, "true");
						}
			        }            
			   else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
				   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
				   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
				   WaitUtils.waitForPageToLoad(driver, 30);            
				   IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
				   WaitUtils.waitForPageToLoad(driver, 30);
				   WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
				   incMgrReqdValue=IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver).getText();
				   ReporterLogs.log("Incident Manager Required value is: "+incMgrReqdValue, "info");
				   if (incMgrReqdValue.equalsIgnoreCase("true")) {
					   Assert.assertEquals(incMgrReqdValue, "true");
					   ReporterLogs.log("Successfully created Managed incident with Id "+incNumber, "info");
					//   ExtentReport.reportLog(LogStatus.PASS, "Successfully created Managed incident with Id "+incNumber);
					   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
					  // ExtentReport.reportLog(LogStatus.INFO, "Incident Manager Required value for the incident "+ incNumber +" is "+ incMgrReqdValue);
					   Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver,"Incident Manager Required");
				   }else{
					   ReporterLogs.log("Unable to create a Managed incident Ticket. Incident Manager Required value from Notes tab displayed is "+ incMgrReqdValue, "error");
					//   ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a managed incident ticket");
					   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
					   Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver, "Incident Manager Required");
					   Assert.assertEquals(incMgrReqdValue, "true");
				   }
			   }				
			}
		
		
	
		public static void verifyIncidentCreation(WebDriver driver, String incNumber) throws Exception
		{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
	        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
	        {
	        	Thread.sleep(2000);
	            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
	            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
	            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
	            //Thread.sleep(3000);
	            WaitUtils.waitForPageToLoad(driver, 30);
	            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
	            
	            if(incidentState.equalsIgnoreCase("New"))
	            {
	         	   Assert.assertEquals(incidentState, "New");
	         	   ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
	         	  // ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
	         	  // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	         }else{
	        	   	
	         	   ReporterLogs.log("Unable to create a Standalone incident Ticket. Actual status is "+ incidentState, "error");
	         	   //ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
	         	   //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	         	   Assert.assertEquals(incidentState, "New");
	         	 }  	       
	        }      
	        else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
	        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
	        	   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
	        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
	        	   if(incidentState.equalsIgnoreCase("New"))
	        	   {
		         	Assert.assertEquals(incidentState, "New");
		         	ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
		         	//ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
		            ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
		            //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
		            ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	        	   }else{        	    
				    ReporterLogs.log("Unable to create a Standalone incident Ticket ", "error");
				    //ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
				    ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
				    //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
				    ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
				    Assert.assertEquals(incidentState, "New");
			       }      
		  		}
			}
		
		//verify managed incident creation
		public static void verifymanagedIncidentCreation(WebDriver driver, String incNumber) throws Exception
		{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
	        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
	        {
	        	Thread.sleep(2000);
	            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
	            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
	            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
	            //Thread.sleep(3000);
	            WaitUtils.waitForPageToLoad(driver, 30);
	            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
	            
	            if(incidentState.equalsIgnoreCase("New"))
	            {
	         	   Assert.assertEquals(incidentState, "New");
	         	   ReporterLogs.log("Successfully created managed incident with Id "+incNumber, "info");
	         	  // ExtentReport.reportLog(LogStatus.PASS, "Successfully created managed incident with Id "+incNumber);
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
	         	  // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the managed incident id "+ incNumber +" is "+ incidentState, "info");
	         }else{
	        	   	
	         	   ReporterLogs.log("Unable to create a managed incident Ticket. Actual status is "+ incidentState, "error");
	         	//   ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
	         	  // ExtentReport.reportLog(LogStatus.INFO, "Status of the managed incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the managed incident id "+ incNumber +" is "+ incidentState, "info");
	         	   Assert.assertEquals(incidentState, "New");
	         	 }  	       
	        }      
	        else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
	        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
	        	   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
	        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
	        	   if(incidentState.equalsIgnoreCase("New"))
	        	   {
		         	Assert.assertEquals(incidentState, "New");
		         	ReporterLogs.log("Successfully created managed incident with Id "+incNumber, "info");
		         	//ExtentReport.reportLog(LogStatus.PASS, "Successfully created managed incident with Id "+incNumber);
		            ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed"); 
		            //ExtentReport.reportLog(LogStatus.INFO, "Status of the managed incident id "+ incNumber +" is "+ incidentState);
		            ReporterLogs.log("Status of the managed incident id "+ incNumber +" is "+ incidentState, "info");
	        	   }else{        	    
				    ReporterLogs.log("Unable to create a managed incident Ticket ", "error");
				    //ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
				    ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
				    //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
				    ReporterLogs.log("Status of the managed incident id "+ incNumber +" is "+ incidentState, "info");
				    Assert.assertEquals(incidentState, "New");
			       }      
		  		}
			}
		
		
		//verify incident after moving to in progress state
		
		public static void verifyIncidentCreation1(WebDriver driver, String incNumber) throws Exception
		{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
	        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
	        {
	        	Thread.sleep(2000);
	            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
	            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
	            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
	            //Thread.sleep(3000);
	            WaitUtils.waitForPageToLoad(driver, 30);
	            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
	            
	            if(incidentState.equalsIgnoreCase("Work in Progress"))
	            {
	         	   Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
	         	   ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
	         	  // ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
	         	  // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	         }else{
	        	   	
	         	   ReporterLogs.log("Unable to create a Standalone incident Ticket. Actual status is "+ incidentState, "error");
	         	  // ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
	         	  // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	         	  Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
	         	 }  	       
	        }      
	        else {
	        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
	        	   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
	        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
	        	   if(incidentState.equalsIgnoreCase("Work in Progress"))
	        	   {
	        		Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
		         	ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
		         	//ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
		            ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
		           // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
		            ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	        	   }else{        	    
				    ReporterLogs.log("Unable to create a Standalone incident Ticket ", "error");
				    //ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
				    ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
				   // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
				    ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
				    Assert.assertEquals(IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText(), "Work in Progress");
			       }      
		  		}
			}
		
		//Move Incident to WIP state
		
		public static void MovetoInprogress(WebDriver driver) throws Exception
		{
			
				try{
					WaitUtils.waitForPageToLoad(driver, 30);
					
					//Search for the Incident Ticket
					incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
					assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
					worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
					ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
					ReporterLogs.log("Incident Number is "+incNumber, "info");
					//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
					WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
					if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
						 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
					}
			        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
			        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
			        if (incidentState.equalsIgnoreCase("New")) {
			        	Thread.sleep(3000);
			        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
			        	WaitUtils.waitForPageToLoad(driver, 30);
			        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
			        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
			        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
			        	 
			        	//Update the status from New to Work in Progress
			        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
			        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
			        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
			        	Thread.sleep(2000);
			        	ReporterLogs.log("Assigned to is entered successfully: "+assignedTo, "info");
			        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
						driver.findElement(By.xpath("//button[text()='Save']")).click();
			        	try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        ReporterLogs.log("Alert message: " + alertText, "error");
					      //  ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
					        Assert.fail("Unexpected alert !!!! ");
					        } 
			        	catch(Exception e){
			    			e.getMessage();
					    }
			        	//WaitUtils.waitForTitleIs(driver, "Incident | ServiceNow");
			        }}
		
			        catch (UnhandledAlertException f) {
					    try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        System.out.println("Alert data: " + alertText);
					         Assert.fail("Unhandled alert");
					        
					        } 
					     
					    	 catch(Exception e){
					 			e.getMessage();
					    }	
					    
			        }
			}
					    
		//Keep incident ticket on Hold
		
		public static void OnholdIncident(WebDriver driver) throws Exception
		{
			try
			{
				WaitUtils.waitForPageToLoad(driver, 30);
				Thread.sleep(2000);
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 6, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 6, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("Work in Progress")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        //	ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
				
				DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "On Hold", "State");
				DropDowns.selectDropdownByVisibleText(IncidentPage.onholdtype(driver), "Awaiting Customer", "On hold type");
				//TextBoxes.enterTextValue(IncidentPage.getWorkNotesEdt(driver), test + incNumber, "Work notes");
			    TextBoxes.enterTextValue(IncidentPage.getAdditionalCommentsEdt(driver), test + incNumber, "Additional Comments");
			    ReporterLogs.log("additional comments is added "+additionalcomments, "info");
			    try {
			        Alert alert = driver.switchTo().alert();
			        String alertText = alert.getText();
			        ReporterLogs.log("Alert message: " + alertText, "error");
			        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
			        Assert.fail("Unexpected alert !!!! ");
			        } 
			    catch (NoAlertPresentException e) {
			        e.printStackTrace();
			    }
	        	WaitUtils.waitForTitleIs(driver, "+incNumber+ | ServiceNow");
	        }}

	        catch (UnhandledAlertException f) {
			    try {
			        Alert alert = driver.switchTo().alert();
			        String alertText = alert.getText();
			        System.out.println("Alert data: " + alertText);
			         Assert.fail("Unhandled alert");
			        
			        } 
			    catch (NoAlertPresentException e) {
			        e.printStackTrace();
			    }	
			    
	        }
	}
			    
			
		
					    
	// Resolve the Incident ticket				    
					    
		public static void resolveIncident(WebDriver driver) throws Exception
			{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        System.out.println(incidentState);
		        if (incidentState.equalsIgnoreCase("New")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	//Update the status from New to Work in Progress
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					Thread.sleep(2000);
		        	try {
				        Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				        ReporterLogs.log("Alert message: " + alertText, "error");
				       // ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
				        Assert.fail("Unexpected alert !!!! ");
				        } 
				    catch (NoAlertPresentException e) {
				        e.printStackTrace();
				    }
		        	//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	 
		        	
		        	  
						DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Resolved", "State");					
						configurationItem=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 13);
						//DropDowns.selectDropdownByVisibleText(IncidentPage.ConfigurationItem(driver), "QA Test Runner", "On hold type");
						causeCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 14);
						subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 15);
						mitigationSolutionSteps=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 16);
						//IncidentPage.getConfigurationItemEdt(driver).sendKeys(configurationItem);
						WaitUtils.waitForIdPresent(driver, "sys_display.incident.cmdb_ci");
						driver.findElement(By.id("sys_display.incident.cmdb_ci")).sendKeys(configurationItem);
						Thread.sleep(5000);
						IncidentPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
						ReporterLogs.log("Configuration item field is entered successfully "+ configurationItem, "info");
						
						//Fill closure tab fields
						//WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Closure')");
						
						IncidentPage.getClosureTab(driver).click();
						
						DropDowns.selectDropdownByVisibleText(IncidentPage.getCauseCodeDropdown(driver), causeCode, "Cause Code");
						ReporterLogs.log("Causecode field is entered successfully "+ causeCode, "info");
						DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver), subCauseCode, "Sub Cause Code");
						Thread.sleep(4000);
						ReporterLogs.log("SubCausecode field is entered successfully "+ subCauseCode, "info");
						TextBoxes.enterTextValue(IncidentPage.getMitigationAndSolutionStepsEdt(driver), mitigationSolutionSteps + incNumber, "Mitigation and Solution Steps");
						ReporterLogs.log("Mitigation and solutionsteps field is entered successfully "+ mitigationSolutionSteps, "info");
						WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
						driver.findElement(By.xpath("//button[text()='Save']")).click();
						try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        ReporterLogs.log("Alert message: " + alertText, "error");
					        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
					        Assert.fail("Unexpected alert !!!! ");
					        } 
					    catch (NoAlertPresentException e) {
					        e.printStackTrace();
					    }
						WaitUtils.waitForTitleIs(driver, "incidentNumber|Incidents | ServiceNow");
			        	 
			        	
			        	 
						//Update the incident ticket from Work in Progress to Resolved State
						if (incidentStateResolved.equalsIgnoreCase("Resolved")) {
							Assert.assertEquals(incidentStateResolved, "Resolved");
							//ExtentReport.reportLog(LogStatus.PASS, "Incident ticket " + incNumber +" has been successfully moved to Resolved State");
							ReporterLogs.log("Incident ticket " + incNumber +" has been successfully moved to Resolved State", "pass");
							ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
			        	 }
						else{
			        		ReporterLogs.log("Incident ticket " + incNumber +" has not been moved to Resolved State", "error");
			        		//ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" has not been moved to Resolved State");
			        		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
			        		Assert.assertEquals(incidentStateResolved, "Resolved");
			        	 	}
		        	 	}
		        	 
		        	
		        	 
		         		
		         else {
		        	 	ReporterLogs.log("Incident ticket " + incNumber +" status is not New", "error");
						//ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" status is not New");
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
		        		Assert.assertEquals(incidentState, "New");
				}  }       
					
			catch(Exception e){
				e.getMessage();
			}
			System.out.println(incidentNumber);
			//return incidentNumber;
		}
	
		
		public static void cancelIncident(WebDriver driver, String incNumber) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("New")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	//Update the status from New to Work in Progress
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	ReporterLogs.log("Assigned to field is entered successfully "+ assignedTo, "info");
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
		        
		        	
		        	
		        	
				DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Cancelled", "State");
				 reasonForCancellation=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 4, 17);
		 			subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 15);
		 					
		        	 WaitUtils.waitForXpathPresent(driver, "/html/body/div[2]/form/div[1]/span[4]/span[1]/span[2]");
		        	 IncidentPage.getGovernanceTab(driver).click();
		        	 
		        	 WaitUtils.waitForElementToBeVisible(driver, IncidentPage.getReasonForCancellationEdt(driver));
		        	 WaitUtils.waitForXpathPresent(driver, "//textarea[@id='incident.u_reason_for_cancellation']");
		        	 IncidentPage.getReasonForCancellationEdt(driver).sendKeys(reasonForCancellation);		        	 
		        	 WaitUtils.waitForXpathPresent(driver, "//span[text()='Closure']");
		        	 Thread.sleep(2000);
		        	 IncidentPage.getClosureTab(driver).click();
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver),subCauseCode,"Sub Cause Code");		        	 
		        	 Thread.sleep(2000);
		        	 ReporterLogs.log("Subcausecode field is entered successfully "+ subCauseCode, "info");
		        	 WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
						driver.findElement(By.xpath("//button[text()='Save']")).click();
						try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        ReporterLogs.log("Alert message: " + alertText, "error");
					       // ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
					        Assert.fail("Unexpected alert !!!! ");
					        } 
					    catch (NoAlertPresentException e) {
					        e.printStackTrace();
					    }
			        	//WaitUtils.waitForTitleIs(driver, "+incNumber+ | ServiceNow");
			        }}

			        catch (UnhandledAlertException f) {
					    try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        System.out.println("Alert data: " + alertText);
					         Assert.fail("Unhandled alert");
					        
					        } 
					    catch (NoAlertPresentException e) {
					        e.printStackTrace();
					    }	
					    
			        }
			}
					    
		
		//To validate active IM task
		public static void cancelIncident1(WebDriver driver, String incNumber) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("Work in Progress")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	//Update the status from work in progress to cancel
		        	
		        	
		        	
				DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Cancelled", "State");
				 
		        	 WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
						driver.findElement(By.xpath("//button[text()='Save']")).click();
						Thread.sleep(2000);
						Alert alt = driver.switchTo().alert();
						 
						String alertmessage = driver.switchTo().alert().getText();
						
						 alt.accept();
						 System.out.println(alertmessage);
						 WaitUtils.waitForXpathPresent(driver, "//button[text()='Update']");
							driver.findElement(By.xpath("//button[text()='Update']")).click();
						try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        ReporterLogs.log("Alert message: " + alertText, "error");
					      //  ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
					        Assert.fail("Unexpected alert !!!! ");
					        } 
						catch(Exception e){
							e.getMessage();
					    }
			        	//WaitUtils.waitForTitleIs(driver, "+incNumber+ | ServiceNow");
			        }}

			        catch (UnhandledAlertException f) {
					    try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        System.out.println("Alert data: " + alertText);
					         Assert.fail("Unhandled alert");
					        
					        } 
					    catch(Exception e){
							e.getMessage();
					    }	
					    
			        }
			}
					    
		
		public static void searchIncidentTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
			try{
			Thread.sleep(2000);
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			TextBoxes.enterTextValue(IncidentPage.getIncidentSearchTicketEdt(driver), ticketNumber, "Search Ticket");
			IncidentPage.getIncidentSearchTicketEdt(driver).sendKeys(Keys.ENTER);
			
		}
			
			catch (Exception e) {
				ReporterLogs.log(e.getMessage(), "info");
			}
		
	}
	

//Valdation of mandatory fields
public static void Captureerrormessagenew(WebDriver driver, String errormessage) throws InterruptedException
{
	WaitUtils.waitForPageToLoad(driver, 30);
	Thread.sleep(2000);
	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	WaitUtils.waitForPageToLoad(driver, 30);
	
		
	boolean isdisplayed = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent::div[1]")).isDisplayed();
		
		if(isdisplayed)
		{
			System.out.println("element is displayed");
		}
		else
		{
			System.out.println("element not displayed");
		}	
	
	
}
public static void Captureerrormessagenew1(WebDriver driver, String errormessage) throws Exception
{
	

		WaitUtils.waitForPageToLoad(driver, 30);
		
		//Search for the Incident Ticket
		incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
		assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
		worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
		ReporterLogs.log("Incident Number is "+incNumber, "info");
		//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
		WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
		if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		}
        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
        if (incidentState.equalsIgnoreCase("New")) {
        	Thread.sleep(3000);
        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
        	WaitUtils.waitForPageToLoad(driver, 30);
        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
        	 
        	//Update the status from New to Work in Progress
        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	WaitUtils.waitForPageToLoad(driver, 30);
	
	
		
	boolean isdisplayed = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent::div[1]")).isDisplayed();
		
		if(isdisplayed)
		{
			System.out.println("element is displayed");
		}
		else
		{
			System.out.println("element not displayed");
		}	
	
	
		
}}

//Verify mandatory fields in On hold

public static void Captureerrormessagenew2(WebDriver driver, String errormessage) throws Exception
{
	WaitUtils.waitForPageToLoad(driver, 30);
	incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
	worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 6, 18);
	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 6, 2, incNumber);
	ReporterLogs.log("Incident Number is "+incNumber, "info");
	//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
	if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	}
    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
    if (incidentState.equalsIgnoreCase("New")) {
    	Thread.sleep(3000);
    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
    	WaitUtils.waitForPageToLoad(driver, 30);
    	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
    	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
    	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
    	 
	
	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "On Hold", "State");
	DropDowns.selectDropdownByVisibleText(IncidentPage.onholdtype(driver), "Awaiting Customer", "On hold type");
	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	WaitUtils.waitForPageToLoad(driver, 30);
	
	
		
	boolean isdisplayed = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent::div[1]")).isDisplayed();
		
		if(isdisplayed)
		{
			System.out.println("element is displayed");
		}
		else
		{
			System.out.println("element not displayed");
		}	
    }
}



//verify mandatory field in resolved state
public static void Captureerrormessagenew3(WebDriver driver, String errormessage) throws Exception

{
	WaitUtils.waitForPageToLoad(driver, 30);
	
	//Search for the Incident Ticket
	incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
	assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
	worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
	ReporterLogs.log("Incident Number is "+incNumber, "info");
	ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
	if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	}
	IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
    if (incidentState.equalsIgnoreCase("New")) {
    	Thread.sleep(3000);
    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
    	WaitUtils.waitForPageToLoad(driver, 30);
    	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
    	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
    //	ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
    	 
    	//Update the status from New to Work in Progress
    	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
    	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
    	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
    	Thread.sleep(2000);
    	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
    	try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        ReporterLogs.log("Alert message: " + alertText, "error");
	     //   ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
	        Assert.fail("Unexpected alert !!!! ");
	        } 
	    catch (NoAlertPresentException e) {
	        e.printStackTrace();
	    }

    	
    	
		DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Resolved", "State");					
	configurationItem=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 13);
		//DropDowns.selectDropdownByVisibleText(IncidentPage.ConfigurationItem(driver), "QA Test Runner", "On hold type");
		causeCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 14);
		subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 15);
		mitigationSolutionSteps=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 16);
		IncidentPage.getConfigurationItemEdt(driver).sendKeys(configurationItem);
		IncidentPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
		ReporterLogs.log("Configuration item field is entered successfully "+ configurationItem, "info");
		Thread.sleep(2000);
		
		WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
boolean isdisplayed = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent::div[1]")).isDisplayed();
		
		if(isdisplayed)
		{
			System.out.println("element is displayed");
		}
		else
		{
			System.out.println("element not displayed");
		}	
    }
}

//verify mandatory fields in cancelled state
public static void Captureerrormessagenew4(WebDriver driver, String errormessage) throws Exception
{
	WaitUtils.waitForPageToLoad(driver, 30);
	Thread.sleep(2000);
	incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
	worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 6, 18);
	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 4, 2, incNumber);
	ReporterLogs.log("Incident Number is "+incNumber, "info");
	//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
	if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	}
    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
    if (incidentState.equalsIgnoreCase("New")) {
    	Thread.sleep(3000);
    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
    	WaitUtils.waitForPageToLoad(driver, 30);
    	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
    	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
    	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
    	 
	
	
	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Cancelled", "State");
	 reasonForCancellation=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 4, 17);
			subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 15);
					
    	 WaitUtils.waitForXpathPresent(driver, "/html/body/div[2]/form/div[1]/span[4]/span[1]/span[2]");
    	 IncidentPage.getGovernanceTab(driver).click();
    	 WaitUtils.waitForElementToBeVisible(driver, IncidentPage.getReasonForCancellationEdt(driver));
    	 WaitUtils.waitForXpathPresent(driver, "//textarea[@id='incident.u_reason_for_cancellation']");
    	 IncidentPage.getReasonForCancellationEdt(driver).sendKeys(reasonForCancellation);		        	 
    	 WaitUtils.waitForXpathPresent(driver, "//span[text()='Closure']");
    	 Thread.sleep(2000);
    	 IncidentPage.getClosureTab(driver).click();
    	// DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver),subCauseCode,"Sub Cause Code");		        	 
    	 Thread.sleep(2000);
    	 WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		       // ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
        	//WaitUtils.waitForTitleIs(driver, "+incNumber+ | ServiceNow");
			boolean isdisplayed = driver.findElement(By.xpath("//span[@class='outputmsg_text']//parent::div[1]")).isDisplayed();
			
			if(isdisplayed)
			{
				System.out.println("element is displayed");
			}
			else
			{
				System.out.println("element not displayed");
			}	
    
    }}
	


//creation of ATT tickets
public static String createATTIncident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
{
	
	try{
	WaitUtils.waitForPageToLoad(driver, 30);
			
	
		
		//Reading values from Excel file for Standalone Incident Test Case
		businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 5);
		assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 6);
		openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 8);
		userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 9);
		shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 10);
		description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 11);			
		
		//Store the Incident number in a variable
		WaitUtils.waitForPageToLoad(driver, 50);
		WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
		incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
		ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
		ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
		
		//Business Service Field
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
		ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
		Thread.sleep(5000);
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
		
		//Assignment group value			
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
		IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
		IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
		ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
				
		//OpenedByGroup value			
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
		IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
		IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
		ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
				
		//User Impact Field			
		WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
		DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
		ReporterLogs.log("User Impact selected: "+userImpact, "info");
				
		//Short Description field			
		WaitUtils.waitForIdPresent(driver, "incident.short_description");
		IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
		Thread.sleep(2000);
		//Description field
		WaitUtils.waitForIdPresent(driver, "incident.description");
		IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
		
		
		
		Thread.sleep(2000);	
		//Click on Save button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
		
				WaitUtils.waitForPageToLoad(driver, 50);
				IncidentPage.Clickonexternalreference(driver);
		ATTnumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.u_at_t_ticket_id']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView();", ATTnumber);
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(ATTnumber));
    	wait.until(ExpectedConditions.elementToBeClickable(ATTnumber));
	//	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 7, 2, ATTnumber);
		
		System.out.println(ATTnumber);
		
		
		try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        ReporterLogs.log("Alert message: " + alertText, "error");
	        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
	        Assert.fail("Unexpected alert !!!! ");
	        } 
	    catch (Exception e) {
	    	System.out.println("e");
	    }
	
	}catch (UnhandledAlertException f) {
    try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
        Assert.fail("Unhandled alert");
        
        } 
    catch (NoAlertPresentException e) {
        e.printStackTrace();
    }		
}	
	return incidentNumber;
}


//incident task creation

public static String CreateIncidentTask(WebDriver driver, String incNumber) throws Exception
{
try
	{
	
	WaitUtils.waitForPageToLoad(driver, 30);
	
	//Search for the Incident Ticket
	incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
	assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
	worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
	ReporterLogs.log("Incident Number is "+incNumber, "info");
	//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
	if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	}
    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
    if (incidentState.equalsIgnoreCase("New")) {
    	Thread.sleep(3000);
    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
    	WaitUtils.waitForPageToLoad(driver, 30);
    	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
    	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
    	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
    	 
    	//Update the status from New to Work in Progress
    	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
    	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
    	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
    	Thread.sleep(2000);
    	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
        	 
		
		
		IncidentTask.getroutingsituationbtn(driver).click();
		Thread.sleep(2000);
		driver.switchTo().frame(1);

		
		DropDowns.selectDropdownByVisibleText(IncidentTask.getroutingoption(driver), "Task a Group"," Routing Option");
		Assignmentgroup1=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 6);
		WaitUtils.waitForIdPresent(driver, "sys_display.u_task_route.u_assignment_group");
		Thread.sleep(2000);
		IncidentPage.Assignmentgroupedt(driver).sendKeys(Assignmentgroup1);
		ReporterLogs.log("Assignment group field is entered successfully "+ Assignmentgroup1, "info");
		Thread.sleep(2000);
	    IncidentPage.Assignmentgroupedt(driver).sendKeys(Keys.ENTER);
	    WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		
		driver.switchTo().defaultContent();
		
		//IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		//IncidentPage.getAllIncidentslnk(driver);
		
		//driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]/a")).click();
		//driver.findElement(By.id("close-messages-btn")).click();
		//driver.findElement(By.xpath("/html[1]/body[1]/div[2]/form[1]/span[1]/span[1]/div[2]/button[1]"));
		
		//driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]/a")).click();
		
		//WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
	//if(	driver.findElement(By.xpath("//button[text()='Save']")).isDisplayed())
	//{
		//System.out.println("present");
	//}
		
		//Thread.sleep(2000);
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView();", incidenttab);
		//incidenttab= driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td[3]"));
		//WebDriverWait wait = new WebDriverWait(driver, 10);
    	//wait.until(ExpectedConditions.visibilityOf(element));
    	//wait.until(ExpectedConditions.elementToBeClickable(element));
		
		
		//System.out.println(data);
		//System.out.println(incidenttasknum);		
		
		
		/*WaitUtils.waitForPageToLoad(driver, 30);
		JavascriptExecutor jsx = (JavascriptExecutor)driver;
		jsx.executeScript("window.scrollBy(0,450)", "");*/
		   try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		       // ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
       //	WaitUtils.waitForTitleIs(driver, "+incNumber+ | ServiceNow");
       }}

       catch (UnhandledAlertException f) {
		    try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println("Alert data: " + alertText);
		         Assert.fail("Unhandled alert");
		        
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }	
		    
       }
return incNumber;
}

//2nd incident task
public static String CreateIncidentTask1(WebDriver driver, String incNumber) throws Exception
{
try
	{
	
	WaitUtils.waitForPageToLoad(driver, 30);
	
	//Search for the Incident Ticket
	incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
	assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
	worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
	ReporterLogs.log("Incident Number is "+incNumber, "info");
	//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
	if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	}
    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
    if (incidentState.equalsIgnoreCase("New")) {
    	Thread.sleep(3000);
    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
    	WaitUtils.waitForPageToLoad(driver, 30);
    	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
    	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
    	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
    	 
    	//Update the status from New to Work in Progress
    	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "On Hold", "State");
    	DropDowns.selectDropdownByVisibleText(IncidentPage.onholdtype(driver), "Awaiting Customer", "On hold type");
		//TextBoxes.enterTextValue(IncidentPage.getWorkNotesEdt(driver), test + incNumber, "Work notes");
	    TextBoxes.enterTextValue(IncidentPage.getAdditionalCommentsEdt(driver), test + incNumber, "Additional Comments");
	    ReporterLogs.log("additional comments is added "+additionalcomments, "info");
    	
    	Thread.sleep(2000);
    	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
        	 
		
		
		IncidentTask.getroutingsituationbtn(driver).click();
		Thread.sleep(2000);
		driver.switchTo().frame(1);

		
		DropDowns.selectDropdownByVisibleText(IncidentTask.getroutingoption(driver), "Task a Group"," Routing Option");
		Assignmentgroup1=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 6);
		WaitUtils.waitForIdPresent(driver, "sys_display.u_task_route.u_assignment_group");
		Thread.sleep(2000);
		IncidentPage.Assignmentgroupedt(driver).sendKeys(Assignmentgroup1);
		ReporterLogs.log("Assignment group field is entered successfully "+ Assignmentgroup1, "info");
		Thread.sleep(2000);
	    IncidentPage.Assignmentgroupedt(driver).sendKeys(Keys.ENTER);
	    WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		
		driver.switchTo().defaultContent();
		
		
		   try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		        //ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
      
       }}

       catch (UnhandledAlertException f) {
		    try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println("Alert data: " + alertText);
		         Assert.fail("Unhandled alert");
		        
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }	
		    
       }
return incNumber;
}

//Verification of attachment
public static void Uploadattachment(WebDriver driver) throws Exception
{
	try
	{
		WaitUtils.waitForPageToLoad(driver, 30);
		
		//Search for the Incident Ticket
		incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
		assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
		worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
		ReporterLogs.log("Incident Number is "+incNumber, "info");
		//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
		WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
		if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		}
        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
        if (incidentState.equalsIgnoreCase("New")) {
        	Thread.sleep(3000);
        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
        	WaitUtils.waitForPageToLoad(driver, 30);
        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
        	 
        	//Update the status from New to Work in Progress
        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
        	Thread.sleep(2000);
        	ReporterLogs.log("Assigned to field is entered successfully "+ assignedTo, "info");
        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			 
		WaitUtils.waitForXpathPresent(driver, "//button[@id='header_add_attachment']");
		driver.findElement(By.xpath("//button[@id='header_add_attachment']")).click();
		WaitUtils.waitForXpathPresent(driver, "//input[@id='loadFileXml']");
	driver.findElement(By.xpath("//input[@id='loadFileXml']")).click();
	StringSelection ss= new StringSelection("C:\\Users\\UX011974\\bigfixExportToServiceNow.txt");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);	
	Robot robot = new Robot();

	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	
	 
	
	try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        ReporterLogs.log("Alert message: " + alertText, "error");
	        //ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
	        Assert.fail("Unexpected alert !!!! ");
	        } 
	    catch (Exception e) {
	    	System.out.println("e");
	    }}}
	
catch (UnhandledAlertException f) {
    try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
        Assert.fail("Unhandled alert");
        
        } 
    catch (NoAlertPresentException e) {
        e.printStackTrace();
    }		
}	

}

//Removal of attachment

public static void Removeattachment(WebDriver driver) throws Exception
{
	try
	{
		
		WebElement checkbox = driver.findElement(By.xpath("//*[@id='sys_id_5d66c9511b7b2f08231c43b4bd4bcb97']"));
		boolean checkboxdisplayed = checkbox.isDisplayed();
		System.out.println("checkbox is displayed:"+ checkboxdisplayed);
		boolean checkboxenabled = checkbox.isEnabled();
		System.out.print("checkbox is enabled:"+ checkboxenabled);
		boolean checkboxselected = checkbox.isSelected();
		System.out.println("checkbox is selected:"+ checkboxselected);
	}
		
		catch (UnhandledAlertException f) {
		    try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println("Alert data: " + alertText);
		        Assert.fail("Unhandled alert");
		        
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }		
		
	}
}
public static String ValidateIncidenttask(WebDriver driver, String incNumber) throws Exception
{
try
	{
	
		
		
	WaitUtils.waitForPageToLoad(driver, 30);
	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
    if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
    {
    	Thread.sleep(2000);
        WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
        TextBoxes.enterTextValue(IncidentPage.getSearchIncidenttask(driver),incidenttasknum,"Search Incident task");
        IncidentPage.getSearchIncidenttask(driver).sendKeys(Keys.ENTER);
        //Thread.sleep(3000);
        WaitUtils.waitForPageToLoad(driver, 30);
		
        incidenttaskState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
        
        if(incidenttaskState.equalsIgnoreCase("Open"))
        {
     	   Assert.assertEquals(incidentState, "Open");
     	   ReporterLogs.log("Successfullymoved incident to open "+incNumber, "info");
     	  // ExtentReport.reportLog(LogStatus.PASS, "Successfully moved incident to open "+incidenttasknum);
     	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
     	   //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incidenttasknum +" is "+ incidenttaskState);
     	   ReporterLogs.log("Status of the Standalone incidenttask id "+ incidenttasknum +" is "+ incidenttaskState, "info");
     }else{
    	   	
     	   ReporterLogs.log("Unable to create a Standalone incidenttask Ticket. A	ctual status is "+ incidenttaskState, "error");
     	  // ExtentReport.reportLog(LogStatus.FAIL, "Unable to update the incident task ticket with Status as Work in Progress");
     	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 5, 4, "Failed");
     	   //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incidenttasknum +" is "+ incidenttaskState);
     	   ReporterLogs.log("Status of the Standalone incident id "+ incidenttasknum +" is "+ incidenttaskState, "info");
     	   Assert.assertEquals(incidenttaskState, "Open");
     	 }  	       
    }      
    else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
    	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
    	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
    	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
    	   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
    	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
    	   if(incidentState.equalsIgnoreCase("Work in Progress"))
    	   {
         	Assert.assertEquals(incidentState, "Work in Progress");
         	ReporterLogs.log("Successfully moved incident to in progress "+incNumber, "info");
         //	ExtentReport.reportLog(LogStatus.PASS, "moved incident to in progress "+incNumber);
            ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 5, 4, "Passed"); 
           // ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
            ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
    	   }else{        	    
		    ReporterLogs.log("Unable to move incident to inprogress ", "error");
		    //ExtentReport.reportLog(LogStatus.FAIL, "Unable to update the incident ticket with Status as Work in Progress");
		    ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 5, 4, "Failed");
		    //ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
		    ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
		    Assert.assertEquals(incidentState, "Work in Progress");
	       }      
  		}
    	}
    	catch(Exception e){
			e.getMessage();
    }		

return incidenttasknum ;	

}
public static String CreateINCforuserimpact3(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
{
	
		
		try{
		WaitUtils.waitForPageToLoad(driver, 30);
				
		
			
			//Reading values from Excel file for Standalone Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 9, 9);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 50);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
			
			//Business Service Field
			Thread.sleep(3000);
			//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
			//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
		driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
			ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			Thread.sleep(6000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			
			//Assignment group value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
					
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			ReporterLogs.log("User Impact selected: "+userImpact, "info");
					
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			Thread.sleep(2000);
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			
			
			Thread.sleep(2000);	
			
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		System.out.println(incidentNumber);
		return incidentNumber;
}
public static String CreateINCforuserimpact1(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
{
	
		
		try{
		WaitUtils.waitForPageToLoad(driver, 30);
				
		
			
			//Reading values from Excel file for Standalone Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 11, 9);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 50);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
			
			//Business Service Field
			Thread.sleep(3000);
			//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
			//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
		driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
			ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			Thread.sleep(6000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			
			//Assignment group value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
					
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			ReporterLogs.log("User Impact selected: "+userImpact, "info");
					
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			Thread.sleep(2000);
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			
			
			Thread.sleep(2000);	
			
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
			
		}
		catch(Exception e){
			e.getMessage();
		}
		System.out.println(incidentNumber);
		return incidentNumber;
}
public static String CreateINCforuserimpact2(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
{
	
		
		try{
		WaitUtils.waitForPageToLoad(driver, 30);
				
		
			
			//Reading values from Excel file for Standalone Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 10, 9);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 50);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
			
			//Business Service Field
			Thread.sleep(3000);
			//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
			//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
		driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
			ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			Thread.sleep(6000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			
			//Assignment group value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
					
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			ReporterLogs.log("User Impact selected: "+userImpact, "info");
					
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			Thread.sleep(2000);
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			
			
			Thread.sleep(2000);	
			
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
			
		}
		
		
		
		catch(Exception e){
			e.getMessage();
		}
		System.out.println(incidentNumber);
		return incidentNumber;
}


//create problem from incident
		public static String createproblemfromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);

				//Reading values from Excel file for Standalone Incident Test Case
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
				description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
				
				//Store the Incident number in a variable
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
				ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
				
				//Business Service Field
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				//Assignment group value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
						
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
						
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
						
				//Short Description field			
				WaitUtils.waitForIdPresent(driver, "incident.short_description");
				IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
				
				//Description field
				WaitUtils.waitForIdPresent(driver, "incident.description");
				IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
				
				Thread.sleep(2000);			
				
				//Click on Submit button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
				driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
			
				//click on problem
				WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Problem']");
				driver.findElement(By.xpath("//div[text()='Create Problem']")).click();
				
				//creating problem
				 WaitUtils.waitForPageToLoad(driver, 30);
                 //Thread.sleep(3000);
                 source=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 5);
                 
                 configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 7);
                 shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 10);
                 description=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 11);
                  
                 WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
                 problemId=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
                 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
                 //ExtentReport.reportLog(LogStatus.INFO, "Problem number captured: "+problemId);
                 ReporterLogs.log("Problem number captured: "+problemId, "info");     
                 WaitUtils.waitForIdPresent(driver, "problem.contact_type");
                 DropDowns.selectDropdownByVisibleText(ProblemPage.getSourceDropdown(driver), source, "Source");
                 //Thread.sleep(3000);                                 
                 
                             
                 TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
                 Thread.sleep(2000);
                 TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
                 Thread.sleep(2000);
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
                 TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
                 ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
                 ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
                 Thread.sleep(5000);
                 ProblemPage.getSubmitBtn(driver).click();
			
			
			}
			catch(Exception e){
				e.getMessage();
			}
			System.out.println(incidentNumber);
			System.out.println(problemId);
			return problemId;
		}
		public static String MovetasktoWIP(WebDriver driver, String incNumber) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				openedByGrouptaskValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
			//	ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("Work in Progress")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		      //  	ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					IncidentPage.getIncidentTaskLnk(driver, 1).click();
					DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Work in Progress", "State");
					WaitUtils.waitForPageToLoad(driver, 50);
					WaitUtils.waitForIdPresent(driver, "sys_readonly.u_inc_task.number");
					incidenttasknum=driver.findElement(By.xpath("//input[@id='sys_readonly.u_inc_task.number']")).getAttribute("value");
					//ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
					ReporterLogs.log("Incident tasknumber captured: "+incidenttasknum, "info");
				//	ExtentReport.reportLog(LogStatus.INFO, "Incident tasknumber captured: "+incidenttasknum);	
					//OpenedByGroup value			
					WaitUtils.waitForIdPresent(driver, "sys_display.u_inc_task.u_opened_by_group");
					IncidentPage.getopenbygrouptaskedt(driver).sendKeys(openedByGrouptaskValue);
					IncidentPage.getopenbygrouptaskedt(driver).sendKeys(Keys.ENTER);
					ReporterLogs.log("Opened By Group value selected: "+openedByGrouptaskValue, "info");
					
					//assigned to
					WaitUtils.waitForIdPresent(driver,"sys_display.u_inc_task.assigned_to");
					IncidentPage.getassignedtotaskedt(driver).sendKeys(assignedTo);
		        	IncidentPage.getassignedtotaskedt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	ReporterLogs.log("Assigned to is entered successfully: "+assignedTo, "info");
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					
		        }}
					catch(Exception e){
						e.getMessage();	 
					}
			System.out.println(incidentNumber);
			System.out.println(incidenttasknum);
			return incidentNumber;
		        
}
		
		
		
		//Move task to close complete
		
		public static String Movetasktclosecomplete(WebDriver driver, String incNumber) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				openedByGrouptaskValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("Work in Progress")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					IncidentPage.getIncidentTaskLnk(driver, 1).click();
					DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Work in Progress", "State");
					WaitUtils.waitForPageToLoad(driver, 50);
					WaitUtils.waitForIdPresent(driver, "sys_readonly.u_inc_task.number");
					incidenttasknum=driver.findElement(By.xpath("//input[@id='sys_readonly.u_inc_task.number']")).getAttribute("value");
					//ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
					ReporterLogs.log("Incident tasknumber captured: "+incidenttasknum, "info");
					//ExtentReport.reportLog(LogStatus.INFO, "Incident tasknumber captured: "+incidenttasknum);	
					//OpenedByGroup value			
					WaitUtils.waitForIdPresent(driver, "sys_display.u_inc_task.u_opened_by_group");
					IncidentPage.getopenbygrouptaskedt(driver).sendKeys(openedByGrouptaskValue);
					IncidentPage.getopenbygrouptaskedt(driver).sendKeys(Keys.ENTER);
					ReporterLogs.log("Opened By Group value selected: "+openedByGrouptaskValue, "info");
					
					//assigned to
					WaitUtils.waitForIdPresent(driver,"sys_display.u_inc_task.assigned_to");
					IncidentPage.getassignedtotaskedt(driver).sendKeys(assignedTo);
		        	IncidentPage.getassignedtotaskedt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	ReporterLogs.log("Assigned to is entered successfully: "+assignedTo, "info");
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Closed Complete", "State");
		        	TextBoxes.enterTextValue(IncidentPage.Inctaskworknotes(driver), worknotes + incNumber, "worknotes");
		        	
		        	
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					
		        }}
					catch(Exception e){
						e.getMessage();	 
					}
			System.out.println(incidentNumber);
			System.out.println(incidenttasknum);
			return incidentNumber;
		        
}
		
		
		public static String Movetasktocancel(WebDriver driver, String incNumber) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				openedByGrouptaskValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				//WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("Work in Progress")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					IncidentPage.getIncidentTaskLnk(driver, 1).click();
					DropDowns.selectDropdownByVisibleText(IncidentPage.getincidentaskstate(driver), "Cancelled", "State");
					WaitUtils.waitForPageToLoad(driver, 50);
					WaitUtils.waitForIdPresent(driver, "sys_readonly.u_inc_task.number");
					incidenttasknum=driver.findElement(By.xpath("//input[@id='sys_readonly.u_inc_task.number']")).getAttribute("value");
					//ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
					ReporterLogs.log("Incident tasknumber captured: "+incidenttasknum, "info");
				//	ExtentReport.reportLog(LogStatus.INFO, "Incident tasknumber captured: "+incidenttasknum);	
					//OpenedByGroup value			
					WaitUtils.waitForIdPresent(driver, "sys_display.u_inc_task.u_opened_by_group");
					IncidentPage.getopenbygrouptaskedt(driver).sendKeys(openedByGrouptaskValue);
					IncidentPage.getopenbygrouptaskedt(driver).sendKeys(Keys.ENTER);
					ReporterLogs.log("Opened By Group value selected: "+openedByGrouptaskValue, "info");
					
					//assigned to
					WaitUtils.waitForIdPresent(driver,"sys_display.u_inc_task.assigned_to");
					IncidentPage.getassignedtotaskedt(driver).sendKeys(assignedTo);
		        	IncidentPage.getassignedtotaskedt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	ReporterLogs.log("Assigned to is entered successfully: "+assignedTo, "info");
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					
		        }}
					catch(Exception e){
						e.getMessage();	 
					}
			System.out.println(incidentNumber);
			System.out.println(incidenttasknum);
			return incidentNumber;
		        
}
		
		public static String ReassignIM(WebDriver driver, String incNumber) throws Exception
		{
		try
		{
		WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
			    IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
			    incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
			    if (incidentState.equalsIgnoreCase("New")) {
			    	Thread.sleep(3000);
			    	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
			    	WaitUtils.waitForPageToLoad(driver, 30);
			    	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
			    	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
			    	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
			    	 
			    	//Update the status from New to Work in Progress
			    	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
			    	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
			    	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
			    	Thread.sleep(2000);
			    	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
			        	 
			System.out.println("before reassigning" + assignmentGroupValue);
				
				 
			IncidentTask.getroutingsituationbtn(driver).click();
			Thread.sleep(2000);
			driver.switchTo().frame(1);

			
			DropDowns.selectDropdownByVisibleText(IncidentTask.getroutingoption(driver), "Reassign"," Routing Option");
			Assignmentgroup1=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 9, 6);
			additionalcomments = ExcelUtils.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 19);
			
			WaitUtils.waitForIdPresent(driver, "sys_display.u_task_route.u_assignment_group");
			Thread.sleep(2000);
			IncidentPage.Assignmentgroupedt(driver).sendKeys(Assignmentgroup1);
			Thread.sleep(2000);
		    IncidentPage.Assignmentgroupedt(driver).sendKeys(Keys.ENTER);
		  Alert alert =  driver.switchTo().alert();
		  alert.accept();
		  Thread.sleep(2000);
		  WaitUtils.waitForXpathPresent(driver, "//*[@id='u_task_route.u_additional_comments']");
		  TextBoxes.enterTextValue(IncidentPage.Additionalcommentstask(driver), additionalcomments + incNumber, "Additional comments");
		   
		  
		   Thread.sleep(2000);
		   
		  
		    WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
			driver.findElement(By.xpath("//button[text()='Next']")).click();
			
			
				driver.switchTo().defaultContent();
			}}
		catch(Exception e){
			e.getMessage();
		}
		System.out.println(incidentNumber);
		System.out.println("After reassigning group" + Assignmentgroup1 );
		return incidentNumber;
		}
		public static String Copyincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);

				//Reading values from Excel file for Standalone Incident Test Case
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
				description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
				
				//Store the Incident number in a variable
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
				ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//	ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
				
				//Business Service Field
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				
				//Assignment group value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
						
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
						
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
						
				//Short Description field			
				WaitUtils.waitForIdPresent(driver, "incident.short_description");
				IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
				
				//Description field
				WaitUtils.waitForIdPresent(driver, "incident.description");
				IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
				
				Thread.sleep(2000);			
				
				//Click on Submit button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
				driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
			//copy incident
				WaitUtils.waitForXpathPresent(driver, "//div[text()='Copy Incident']");
				driver.findElement(By.xpath("//div[text()='Copy Incident']")).click();
				Thread.sleep(2000);
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				//Business Service Field
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(5000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 10, 9);
				
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
				Thread.sleep(2000);		
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				copiedincidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, copiedincidentNumber);
				ReporterLogs.log("Incident number captured: "+copiedincidentNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+copiedincidentNumber);
				
			}
			catch(Exception e){
				e.getMessage();
			}
			System.out.println("Original incident number is :" +incidentNumber);
			System.out.println("copied incident number is :" +copiedincidentNumber);
			return copiedincidentNumber;
		}


		//Verify state of Incident ticket
		public static void verifyStateOfIncidentTicket(WebDriver driver, String expectedStateOfTicket,String incNumber,int reqRow, int reqcol) {
			try{
					String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(IncidentPage.getStateDropdown(driver), "State Drop Down");
					System.out.println(actualStateOfTicket);
					ReporterLogs.log("State of the Incident is :"+actualStateOfTicket);
					if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
						Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
					//	ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Incident Ticket : "+actualStateOfTicket);
						ReporterLogs.log("Successfully updated Incident with Id "+incNumber, "info");
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, incNumber);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
					}else{
						//ExtentReport.reportLog(LogStatus.FAIL, "State of the Incident ticket are not same : "+actualStateOfTicket);
						ReporterLogs.log("Unable to update Incident with Id "+incNumber, "error");
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, incNumber);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
						Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
		}
			}catch(Exception e){
				ReporterLogs.log("Exception :"+e.getMessage(),"error");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Final report
		public static void FinalReport(WebDriver driver, String expectedStateOfTicket,String incNumber,int reqRow, int reqcol) throws FileNotFoundException, IOException {
			try{
				String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(IncidentPage.getStateDropdown(driver), "State Drop Down");
				System.out.println(actualStateOfTicket);
				ReporterLogs.log("State of the Incident is :"+actualStateOfTicket);
				 ManTestCase  = ExcelUtils.getData("Incident Smoke TestReport.xlsx", "Incident", 1, 3);
				 if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
						Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
						//ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Incident Ticket : "+actualStateOfTicket);
						ReporterLogs.log("Successfully updated Incident with Id "+incNumber, "info");
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, incNumber);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
		                System.out.println(ManTestCase);
						
						
							ExcelUtils.writeDataIntoCell("Incident Smoke TestReport.xlsx", "Incident", reqRow, 4, "Passed");
				 }else{
					//	ExtentReport.reportLog(LogStatus.FAIL, "State of the Incident ticket are not same : "+actualStateOfTicket);
						ReporterLogs.log("Unable to update incident with Id "+incNumber, "error");
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, incNumber);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
						ExcelUtils.writeDataIntoCell("Incident Smoke TestReport.xlsx", "Incident", reqRow, 4, "Failed");
						Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
			
					}
			}
				catch(Exception e)
				{
					System.out.println("handled");
				}
					
					
		}
	
		public static String createnormalchangefromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);

				//Reading values from Excel file for Standalone Incident Test Case
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
				description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
				
				//Store the Incident number in a variable
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
				ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//	ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
				
				//Business Service Field
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				
				//Assignment group value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
						
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
						
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
						
				//Short Description field			
				WaitUtils.waitForIdPresent(driver, "incident.short_description");
				IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
				
				//Description field
				WaitUtils.waitForIdPresent(driver, "incident.description");
				IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
				
				Thread.sleep(2000);			
				
				//Click on Submit button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
				driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
			
				//click on problem
				WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Emergency Change']");
				driver.findElement(By.xpath("//div[text()='Create Emergency Change']")).click();
				
				//Create Normal change
				assignmentGroup2 = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				//ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				System.out.println(changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup2, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup2, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("configuration item field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("reason for change field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
			}
		
				catch(Exception e){
					e.getMessage();
				}
				System.out.println(incidentNumber);
				System.out.println(changeId);
				return changeId;
			}	
		public static String createemergencychangefromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);

				//Reading values from Excel file for Standalone Incident Test Case
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
				description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
				
				//Store the Incident number in a variable
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
				ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
				
				//Business Service Field
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				
				//Assignment group value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
						
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
						
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
						
				//Short Description field			
				WaitUtils.waitForIdPresent(driver, "incident.short_description");
				IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
				
				//Description field
				WaitUtils.waitForIdPresent(driver, "incident.description");
				IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
				
				Thread.sleep(2000);			
				
				//Click on Submit button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
				driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
			
				//click on emergency change
				WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Emergency Change']");
				driver.findElement(By.xpath("//div[text()='Create Emergency Change']")).click();
				
				//Create emergency change
				assignmentGroup2 = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 11, 13);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				//ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				System.out.println(changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup2, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup2, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), "tara", "Configuration Item");
				ReporterLogs.log("Configuration item field is entered successfully "+"tara", "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("reason for change field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
			}
		
				catch(Exception e){
					e.getMessage();
				}
				System.out.println(incidentNumber);
				System.out.println(changeId);
				return changeId;
			}

		public static String createstandardchangefromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);

				//Reading values from Excel file for Standalone Incident Test Case
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
				description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
				
				//Store the Incident number in a variable
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
				ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			//	ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
				
				//Business Service Field
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				//Assignment group value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
						
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
						
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
						
				//Short Description field			
				WaitUtils.waitForIdPresent(driver, "incident.short_description");
				IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
				
				//Description field
				WaitUtils.waitForIdPresent(driver, "incident.description");
				IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
				
				Thread.sleep(2000);			
				
				//Click on Submit button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
				driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
			
				//click on standard change
				WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Standard Change']");
				driver.findElement(By.xpath("//div[text()='Create Standard Change']")).click();
				
				assignmentGroup2 = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup2, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup2, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
		WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
			}
			
			catch(Exception e){
				e.getMessage();
			}
			System.out.println(incidentNumber);
			System.out.println(changeId);
			return changeId;
		}		
	
		
		public static String creatchildincidentfromincident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
		{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);

				//Reading values from Excel file for Standalone Incident Test Case
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
				description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
				
				//Store the Incident number in a variable
				WaitUtils.waitForPageToLoad(driver, 50);
				WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
				incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
				ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
				
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				
				//Assignment group value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
				IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
						
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
						
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
						
				//Short Description field			
				WaitUtils.waitForIdPresent(driver, "incident.short_description");
				IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
				
				//Description field
				WaitUtils.waitForIdPresent(driver, "incident.description");
				IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
				
				Thread.sleep(2000);			
				
				//Click on Submit button
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				WaitUtils.waitForXpathPresent(driver,"//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']");
				driver.findElement(By.xpath("//button[@class='btn btn-icon icon-menu navbar-btn additional-actions-context-menu-button']")).click();
				WaitUtils.waitForXpathPresent(driver, "//div[text()='Create Child Incident']");
				driver.findElement(By.xpath("//div[text()='Create Child Incident']")).click();
				Thread.sleep(5000);
				businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
				Thread.sleep(3000);
				//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
				//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
				ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
				Thread.sleep(6000);
				IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
				
				openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
				
				//OpenedByGroup value			
				WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
				ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
				
				userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
				//User Impact Field			
				WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				ReporterLogs.log("User Impact selected: "+userImpact, "info");
				ChildIncnum = IncidentPage.getchildIncidentNumber(driver).getAttribute("value");
				WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
				driver.findElement(By.xpath("//button[text()='Save']")).click();
			}
				catch(Exception e){
					e.getMessage();
				}
				System.out.println(incidentNumber);
				System.out.println(ChildIncnum);
				return ChildIncnum;
						
			}	
		
		
		public static String CreateincidentAlert(WebDriver driver) throws Exception
		{
			
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 7);
				worknotes = ExcelUtils.getData("Incident_Management_TestData.xlsx" , "Smoke_Suite", 5, 18);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 5, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");	
				//Store incident alert number in variable
				
				//WaitUtils.waitForIdPresent(driver, "sys_readonly.incident_alert.number");
				//incidentalertnumber = driver.findElement(By.xpath("//input[@id='sys_readonly.incident_alert.number']")).getAttribute("value");
				//ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentalertnumber);
				//ReporterLogs.log("Incident alertnumber captured: "+incidentalertnumber, "info");
				//ExtentReport.reportLog(LogStatus.INFO, "Incident alertnumber captured: "+incidentalertnumber);			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("New")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	//WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	//ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	//Update the status from New to Work in Progress
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	ReporterLogs.log("Assigned to is entered successfully: "+assignedTo, "info");
		        	WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
					driver.findElement(By.xpath("//button[text()='Save']")).click();
					driver.findElement(By.xpath("//*[@id='4f6a779e13987200f05c7e276144b0b1']")).click();
					//reading incident alert values from excel
					SourceCI=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 20);
					Incidentowner=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 21);
					Incidentmanagergroup=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 22);
					Incidentmanager=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 23);
					Accountablebusinessunit=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 24);
					Affectedbusinessunit=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 25);	
					Latestupdate=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 12, 26);
				
										
					//get source CI
					
					IncidentPage.getsourceci(driver).sendKeys(SourceCI);
					ReporterLogs.log("source ci value selected: "+SourceCI, "info");
					Thread.sleep(5000);
					IncidentPage.getsourceci(driver).sendKeys(Keys.ENTER);
					//get incidentmanager value
					WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.assigned_to");
					driver.findElement(By.id("sys_display.incident_alert.assigned_to")).clear();
					IncidentPage.getincidentmanager(driver).sendKeys(Incidentmanager);
					ReporterLogs.log("incident manager  value selected: "+Incidentmanager, "info");
					Thread.sleep(5000);
					IncidentPage.getincidentmanager(driver).sendKeys(Keys.ENTER);
					
					//get incident manager group value
					WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.assignment_group");
					IncidentPage.getincidentmanagergroup(driver).sendKeys(Incidentmanagergroup);
					ReporterLogs.log("incident manager group value selected: "+Incidentmanagergroup, "info");
					Thread.sleep(5000);
					IncidentPage.getincidentmanagergroup(driver).sendKeys(Keys.ENTER);
					Thread.sleep(5000);
					//get incidentowner value
					WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.u_incident_owner");
					driver.findElement(By.id("sys_display.incident_alert.u_incident_owner")).clear();
					IncidentPage.getincidentowner(driver).sendKeys(Incidentowner);
					ReporterLogs.log("incident owner group value selected: "+Incidentowner, "info");
					Thread.sleep(5000);
					IncidentPage.getincidentowner(driver).sendKeys(Keys.ENTER);
					
					//get accountablebu value
					IncidentPage.getaccountabletab(driver).click();
					WaitUtils.waitForIdPresent(driver, "sys_display.incident_alert.u_accountable_bu");
					IncidentPage.getaccountablebu(driver).sendKeys(Accountablebusinessunit);
					ReporterLogs.log("accountable bu  value selected: "+Accountablebusinessunit, "info");
					Thread.sleep(5000);
					IncidentPage.getaccountablebu(driver).sendKeys(Keys.ENTER);
					//get affectedunit value
					WaitUtils.waitForXpathPresent(driver, "//*[@id='incident_alert.u_affected_bu_s_unlock']");
					IncidentPage.getaffectedunit(driver).click();
					IncidentPage.getaffectedunitedt(driver).sendKeys(Affectedbusinessunit);
					IncidentPage.getaffectedlock(driver).click();
					ReporterLogs.log("affected bu  value selected: "+Affectedbusinessunit, "info");
					Thread.sleep(5000);
					//nextupdate date
					//String nextdate = driver.findElement(By.id("incident_alert.u_next_update_due_by.ui_policy_sensitive")).getAttribute("value");
					//IncidentPage.getnextupdate(driver).click();
					String ActuallDate=Utils.getCurrentDateTime();
					
					driver.findElement(By.id("incident_alert.u_next_update_due_by")).sendKeys(ActuallDate);
					System.out.println(ActuallDate);
					//location
					IncidentPage.getimpactedlocation(driver).click();
					//latest update
					IncidentPage.getdetailstab(driver).click();
					WaitUtils.waitForIdPresent(driver, "incident_alert.u_latest_update");
					IncidentPage.getlatestupdate(driver).sendKeys(Latestupdate+ incidentNumber);
					
					Thread.sleep(2000);
					//incident alert number
					String incidentalertnumber = driver.findElement(By.id("sys_readonly.incident_alert.number")).getAttribute("value");
					System.out.println(incidentalertnumber);
					
					/*WaitUtils.waitForXpathPresent(driver, "//button[@id='timeline']");
					driver.findElement(By.xpath( "//button[@id='timeline']")).click();
					Alert alt = driver.switchTo().alert();
					 
					String alertmessage = driver.switchTo().alert().getText();
					
					 alt.accept();*/
					// WaitUtils.waitForXpathPresent(driver, wbXpath);
					WaitUtils.waitForXpathPresent(driver, "//button[@id='sysverb_insert']");
					driver.findElement(By.xpath( "//button[@id='sysverb_insert']")).click();
					try {
				        Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				        ReporterLogs.log("Alert message: " + alertText, "error");
				      //  ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
				        Assert.fail("Unexpected alert !!!! ");
				        } 
		        	catch(Exception e){
		    			e.getMessage();
				    }
		        	
		        }}
	
		        catch (UnhandledAlertException f) {
				    try {
				        Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				        System.out.println("Alert data: " + alertText);
				         Assert.fail("Unhandled alert");
				        
				        } 
				     
				    	 catch(Exception e){
				 			e.getMessage();
				    	 }	
}
			return incidentNumber;
		}

public static String createmanagedIncident(WebDriver driver, int sNum, int cellNum) throws Exception
{
	
	try{
	WaitUtils.waitForPageToLoad(driver, 50);
	ReporterLogs.log("New Incident Creation page", "info");
	
	//Reading values from Excel file for Managed Incident Test Case
	businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
	assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
	openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
	userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 9);
	shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
	description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
	
	reasonForIncidentManager=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 12);			
	
	//Store the Incident number in a variable
	WaitUtils.waitForPageToLoad(driver, 60);
	WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
	incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
	
	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
	//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
	ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
	
	//Business Service Field
Thread.sleep(3000);
	//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
	//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
	ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
	Thread.sleep(6000);
	IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
	
	//Assignment group value
	WebDriverWait wait = new WebDriverWait(driver,20);
	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='sys_display.incident.u_business_service']")));		
	IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
	
	//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
	//driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
	WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
	IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
	ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
	//driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']")).sendKeys(businessServceValue);
	//ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
	//Thread.sleep(6000);
	//IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			
	//OpenedByGroup value			
	WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
	IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
	ReporterLogs.log("Opened by Group field is entered successfully "+ openedByGroupValue, "info");		
	//User Impact Field			
	WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
	DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
	ReporterLogs.log("User impact field is entered successfully "+ userImpact, "info");	
	//Incident Manager Required Checkbox
	if (IncidentPage.getIncidentManagerRequiredChkbox(driver).isEnabled()) {
		IncidentPage.getIncidentManagerRequiredChkbox(driver).click();
		WaitUtils.waitForIdPresent(driver, "incident.u_reason_for_incident_manager");
		DropDowns.selectDropdownByVisibleText(IncidentPage.getReasonForIncidentManagerDropdown(driver), reasonForIncidentManager, "Reason for Incident Manager");
	}else{
		ReporterLogs.log("Incident Manager Required Checkbox not enabled", "error");
		}	
			
	//Short Description field			
	WaitUtils.waitForIdPresent(driver, "incident.short_description");
	IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
	
	//Description field
	WaitUtils.waitForIdPresent(driver, "incident.description");
	IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
	
	Thread.sleep(5000);
	
	//Click on Submit button
	WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();	
	try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        ReporterLogs.log("Alert message: " + alertText, "error");
        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
        Assert.fail("Unexpected alert !!!! ");
        } 
	catch(Exception e){
		e.getMessage();
    }
	
}

catch (UnhandledAlertException f) {
    try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
         Assert.fail("Unhandled alert");
        
        } 
     
    	 catch(Exception e){
 			e.getMessage();
    	 }}	
	return incidentNumber;
	}

//standalone incident

public static String createstandaloneincident(WebDriver driver, int sNum, int cellNum) throws Exception
{
	
	try{
	WaitUtils.waitForPageToLoad(driver, 50);
	ReporterLogs.log("New Incident Creation page", "info");
//Reading values from Excel file for Standalone Incident Test Case
businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			

//Store the Incident number in a variable
WaitUtils.waitForPageToLoad(driver, 60);
WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite",1, 2, incidentNumber);
ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
Thread.sleep(3000);
//Business Service Field
//WaitUtils.waitForXpathPresent(driver, "//input[@id='sys_display.incident.u_business_service']");
//IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys(businessServceValue);
ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
Thread.sleep(6000);
IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);


//Assignment group value			
WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
		
//OpenedByGroup value			
WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
		
//User Impact Field			
WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
ReporterLogs.log("User Impact selected: "+userImpact, "info");
		
//Short Description field			
WaitUtils.waitForIdPresent(driver, "incident.short_description");
IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);

//Description field
WaitUtils.waitForIdPresent(driver, "incident.description");
IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);

Thread.sleep(2000);			

//Click on Submit button
WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
driver.findElement(By.xpath("//button[text()='Submit']")).click();
try {
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    ReporterLogs.log("Alert message: " + alertText, "error");
    ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
    Assert.fail("Unexpected alert !!!! ");
    } 
catch(Exception e){
	e.getMessage();
}

}

catch (UnhandledAlertException f) {
try {
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    System.out.println("Alert data: " + alertText);
     Assert.fail("Unhandled alert");
    
    } 
 
	 catch(Exception e){
			e.getMessage();
	 }}	
return incidentNumber;
}

public static String createstandaloneincident1(WebDriver driver, int sNum, int cellNum) throws Exception
{
	
	
businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
System.out.println(businessServceValue);
assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			

WaitUtils.waitForPageToLoad(driver, 60);
WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite",1, 2, incidentNumber);
ReporterLogs.log("Incident number captured: "+incidentNumber, "info");

Thread.sleep(3000);
driver.findElement(By.id("sys_display.incident.u_business_service")).sendKeys("170 Systems (Markview)");
ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");



//Assignment group value			
WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);

ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
		
//OpenedByGroup value			
WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);

ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
		
//User Impact Field			
WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
ReporterLogs.log("User Impact selected: "+userImpact, "info");
		
//Short Description field			
WaitUtils.waitForIdPresent(driver, "incident.short_description");
IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);

//Description field
WaitUtils.waitForIdPresent(driver, "incident.description");
IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);

Thread.sleep(2000);			

//Click on Submit button
WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
driver.findElement(By.xpath("//button[text()='Submit']")).click();

return incidentNumber;
}
}
