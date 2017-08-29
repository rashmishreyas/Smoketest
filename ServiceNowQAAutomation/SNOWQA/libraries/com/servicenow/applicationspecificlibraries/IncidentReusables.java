package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;
import pages.IncidentPage;

public class IncidentReusables {

	
	/*
	 * Creates a new Standalone incident ticket and returns the incident number
	 */
	
	
	public static void createStandaloneIncident(WebDriver driver) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 10);
		ExtentReport.reportLog(LogStatus.INFO, "New Incident Creation page");		
		
		//Reading values from Excel file
		String businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
		String assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
		String openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
		String userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
		String shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
		String description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);
		
		
		//Store the Incident number in a variable
		WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
		String incNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
		System.out.println("Incident number is: "+ incNumber);		
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2, incNumber);
		ReporterLogs.log("Incident number captured: "+incNumber, "info");
		ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incNumber);
		
		
		//Business Service Field
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
		ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
		Thread.sleep(5000);
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
		
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
		IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incNumber);
		
		//Description field
		WaitUtils.waitForIdPresent(driver, "incident.description");
		IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incNumber);
		
		Thread.sleep(3000);
		
		//Click on Submit button
		WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForTitleToBeDisplayed(driver, "Incidents | ServiceNow");
		String incidentState = null;
		
		//Search for the created incident id and verify if its status is new
        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
        	System.out.println("Title is:" +driver.getTitle());
        	Thread.sleep(2000);
            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
            //Thread.sleep(3000);
            WaitUtils.waitForPageToLoad(driver, 10);
            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
            
            if(incidentState.equalsIgnoreCase("New")){
         	   Assert.assertEquals(incidentState, "New");
                ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
                ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
                ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
                ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
         }else{
         	   
         	    ReporterLogs.log("Unable to create a Standalone incident Ticket. Actual status is "+ incidentState, "error");
                ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
                ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
                ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
                Assert.assertEquals(incidentState, "Old");
         }      
       
  }else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
         WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
         TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
         ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
         IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
         
         if(incidentState.equalsIgnoreCase("New")){
         	Assert.assertEquals(incidentState, "New");
         	ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
         	ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
            ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
            ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
            ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
      }else{        	    
     	 	
             ReporterLogs.log("Unable to create a Standalone incident Ticket ", "error");
             ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
             ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
             ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
             ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
             Assert.assertEquals(incidentState, "New");
         }      
  }

        Frames.switchToDefaultContent(driver);
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
	
	
	
	
	public static void createManagedIncident(WebDriver driver) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 10);
		ExtentReport.reportLog(LogStatus.INFO, "New Incident Creation page");
		ReporterLogs.log("New Incident Creation page", "info");
		
		//Reading values from Excel file
		String businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 5);
		String assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 6);
		String openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 8);
		String userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 9);
		String shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 10);
		String description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 11);
		String reasonForIncidentManager=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 12);
		
		
		//Store the Incident number in a variable
		WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
		String incNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
		System.out.println("Incident number is: "+ incNumber);		
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 2, incNumber);
		ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incNumber);
		ReporterLogs.log("Incident number captured: "+incNumber, "info");
		
		//Business Service Field
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
		Thread.sleep(5000);
		IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
		
		//Assignment group value
		
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
		IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
		ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
				
		//OpenedByGroup value		
		
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
		IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
				
		//User Impact Field
		
		WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
		DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
				
		//Incident Manager Required Checkbox
		IncidentPage.getIncidentManagerRequiredChkbox(driver).click();
		WaitUtils.waitForIdPresent(driver, "incident.u_reason_for_incident_manager");
		DropDowns.selectDropdownByVisibleText(IncidentPage.getReasonForIncidentManagerDropdown(driver), reasonForIncidentManager, "Reason for Incident Manager");
				
		//Short Description field
		
		WaitUtils.waitForIdPresent(driver, "incident.short_description");
		IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incNumber);
		
		//Description field
		WaitUtils.waitForIdPresent(driver, "incident.description");
		IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incNumber);
		
		Thread.sleep(3000);
		
		//Click on Submit button
		WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForTitleToBeDisplayed(driver, "Incidents | ServiceNow");
		String incidentState = null;
		
		//Search for the created incident id and verify if its status is new
        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
        	System.out.println("Title is:" +driver.getTitle());
        	Thread.sleep(2000);
            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
            //Thread.sleep(3000);
            WaitUtils.waitForPageToLoad(driver, 10);
            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
            
            if(incidentState.equalsIgnoreCase("Old")){
         	   Assert.assertEquals(incidentState, "New");
                ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
                ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
                ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
         }else{
         	   
         	   ReporterLogs.log("Unable to create a Standalone incident Ticket. Actual status is "+ incidentState, "error");
                ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
                ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
                Assert.assertEquals(incidentState, "Old");
         }      
       
  }else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
         WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
         TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
         ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
         IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
         
         if(incidentState.equalsIgnoreCase("New")){
         	Assert.assertEquals(incidentState, "old");
         	ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
         	ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
             ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
             ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
      }else{        	    
     	 	
             ReporterLogs.log("Unable to create a Standalone incident Ticket ", "error");
             ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
             ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
             ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
             Assert.assertEquals(incidentState, "New");
         }      
  }

        Frames.switchToDefaultContent(driver);
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
	

	
	
}
