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
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;

import pages.HomePage;
import pages.IncidentPage;

public class IncidentReusables {	
	
	static String incidentState = null;
	
	/*
	 * Author- Sathyanarayanan V
	 * Objective: To create Standalone/Managed Incident Ticket
	 * @Param- boolean ManagedIncident: If true, a managed incident ticket is created. Else, Standalone incident ticket is created
	*/
	
	public static void createIncident(WebDriver driver, boolean ManagedIncident) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 10);
		
		//if ManagedIncident is true, creates managed Incident ticket
		if (ManagedIncident) {
			
			ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Managed Incident Ticket","Create Managed Incident");
			ExtentReport.reportLog(LogStatus.INFO, "New Incident Creation page");
			ReporterLogs.log("New Incident Creation page", "info");
			//Reading values from Excel file for Managed Incident Test Case
			String businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 5);
			String assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 6);
			String openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 8);
			String userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 9);
			String shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 10);
			String description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 11);
			String reasonForIncidentManager=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 12);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 10);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			String incNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
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
			if (IncidentPage.getIncidentManagerRequiredChkbox(driver).isEnabled()) {
				IncidentPage.getIncidentManagerRequiredChkbox(driver).sendKeys(Keys.SPACE);
				WaitUtils.waitForIdPresent(driver, "incident.u_reason_for_incident_manager");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getReasonForIncidentManagerDropdown(driver), reasonForIncidentManager, "Reason for Incident Manager");
			}else{
				ReporterLogs.log("Incident Manager Required Checkbox not enabled", "error");
				}	
					
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
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
			 if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				 	Thread.sleep(1000);
			        WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			        TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
			        IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
			            
			        WaitUtils.waitForPageToLoad(driver, 10);            
			        IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
			        WaitUtils.waitForPageToLoad(driver, 10);
			        WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
			        ReporterLogs.log("Title of the page after searching the incident: "+driver.getTitle(), "info");
			        String incMgrReqdValue=IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver).getText();
			        if (incMgrReqdValue.equalsIgnoreCase("true")) {
			            	Assert.assertEquals(incMgrReqdValue, "true");
			            	ReporterLogs.log("Successfully created Managed incident with Id "+incNumber, "info");
			                ExtentReport.reportLog(LogStatus.PASS, "Successfully created Managed incident with Id "+incNumber);
			                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
			                ExtentReport.reportLog(LogStatus.INFO, "Incident Manager Required value for the incident "+ incNumber +" is "+ incMgrReqdValue);
			            	Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver, "Incident Manager Required");
			      }else {
							ReporterLogs.log("Unable to create a Managed incident Ticket. Incident Manager Required value from Notes tab displayed is "+ incMgrReqdValue, "error");
			                ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a managed incident ticket");
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
				   WaitUtils.waitForPageToLoad(driver, 10);            
				   IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
				   WaitUtils.waitForPageToLoad(driver, 10);
				   WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
				   String incMgrReqdValue=IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver).getText();
				   ReporterLogs.log("Incident Manager Required value is: "+incMgrReqdValue, "info");
				            if (incMgrReqdValue.equalsIgnoreCase("true")) {
				            	Assert.assertEquals(incMgrReqdValue, "true");
				            	ReporterLogs.log("Successfully created Managed incident with Id "+incNumber, "info");
				            	ExtentReport.reportLog(LogStatus.PASS, "Successfully created Managed incident with Id "+incNumber);
				            	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
				            	ExtentReport.reportLog(LogStatus.INFO, "Incident Manager Required value for the incident "+ incNumber +" is "+ incMgrReqdValue);
				            	Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver,"Incident Manager Required");
				            }else{
								ReporterLogs.log("Unable to create a Managed incident Ticket. Incident Manager Required value from Notes tab displayed is "+ incMgrReqdValue, "error");
								ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a managed incident ticket");
								ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
								Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver, "Incident Manager Required");
								Assert.assertEquals(incMgrReqdValue, "true");
							}
					  }				
			}
		
		//Creates Standalone Incident Ticket
		else {			
			ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Standalone Incident Ticket","Create Standalone Incident Ticket");
			
			//Reading values from Excel file for Standalone Incident Test Case
			String businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			String assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			String openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			String userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
			String shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			String description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 10);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			String incNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
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
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incNumber);
			
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incNumber);
			
			Thread.sleep(3000);			
			
			//Click on Submit button
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();			
			
			WaitUtils.waitForPageToLoad(driver, 10);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
			
			
			//Search for the created incident id and verify if its status is new
	        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
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
	                Assert.assertEquals(incidentState, "New");
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
			}
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
	
	public static void resolveIncident(WebDriver driver) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 10);
		
		//Search for the Incident Ticket
		String incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
		String assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 7);
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 2, incNumber);
		ReporterLogs.log("Incident Number is "+incNumber, "info");
		ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
		WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
		if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		}
	         ServiceNowUtils.searchTicketFromQueue(driver, incNumber);
	         String incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
	         if (incidentState.equalsIgnoreCase("New")) {
	        	 Thread.sleep(3000);
	        	 IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
	        	 WaitUtils.waitForPageToLoad(driver, 10);
	        	 WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
	        	 ReporterLogs.log("Current State of Incident " + incNumber + "is "+incidentState, "info");
	        	 ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + "is "+incidentState);
	        	 
	        	 //Update the status from New to Work in Progress
	        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
	        	 IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
	        	 IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
	        	 Thread.sleep(2000);
	        	 IncidentPage.getUpdateBtn(driver).click();						
	        	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
	        	 
	        	 //Search for the incident ticket
	        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	        	 ServiceNowUtils.searchTicketFromQueue(driver, incNumber);
	        	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
	        	 String incidentStateWIP=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
	        	 ReporterLogs.log("Current State of Incident " + incNumber + "is "+incidentStateWIP, "info");
	        	 ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + "is "+incidentStateWIP);
	        	 
	        	 //Update the incident ticket from Work in Progress to Resolved State
	        	 if (incidentStateWIP.equalsIgnoreCase("Work in Progress")) {					
					Thread.sleep(3000);
					IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
					WaitUtils.waitForPageToLoad(driver, 10);
					WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
					DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Resolved", "State");					
					
					String configurationItem=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 13);
					String causeCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 14);
					String subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 15);
					String mitigationSolutionSteps=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 16);
					IncidentPage.getConfigurationItemEdt(driver).sendKeys(configurationItem);
					IncidentPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
					
					//Fill closure tab fields
					IncidentPage.getClosureTab(driver).click();
					DropDowns.selectDropdownByVisibleText(IncidentPage.getCauseCodeDropdown(driver), causeCode, "Cause Code");
					DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver), subCauseCode, "Sub Cause Code");
					Thread.sleep(5000);
					TextBoxes.enterTextValue(IncidentPage.getMitigationAndSolutionStepsEdt(driver), mitigationSolutionSteps + incNumber, "Mitigation and Solution Steps");
					
					IncidentPage.getUpdateBtn(driver).click();	
					
					WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	 
		        	 //Search for the incident ticket
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		        	 ServiceNowUtils.searchTicketFromQueue(driver, incNumber);
		        	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	 String incidentStateResolved=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        	 ReporterLogs.log("Current State of Incident " + incNumber + "is "+incidentStateResolved, "info");
		        	 ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + "is "+incidentStateResolved);
		        	 
		        	 //Update the incident ticket from Work in Progress to Resolved State
		        	 if (incidentStateResolved.equalsIgnoreCase("Resolved")) {
		        		 Assert.assertEquals(incidentStateResolved, "Resolved");
		        		 ExtentReport.reportLog(LogStatus.PASS, "Incident ticket " + incNumber +" has been successfully moved to Resolved State");
		        		 ReporterLogs.log("Incident ticket " + incNumber +" has been successfully moved to Resolved State", "pass");
		        		 ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
		        	 }
		        	 else{
		        		 ReporterLogs.log("Incident ticket " + incNumber +" has not been moved to Resolved State", "error");
		        		 ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" has not been moved to Resolved State");
		        		 ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
		        		 Assert.assertEquals(incidentStateResolved, "Resolved");
		        	 }
	        	 	}
	        	 
	        	 else{
	        		 ReporterLogs.log("Incident ticket " + incNumber +" is not Work in Progress", "error");
	        		 ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" is not Work in Progress");
	        		 ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
	        		 Assert.assertEquals(incidentStateWIP, "Work in Progress");
	        	 }
	         	}	
	         
	         else {
	        	 	ReporterLogs.log("Incident ticket " + incNumber +" status is not New", "error");
					ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" status is not New");
					ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
	        		Assert.assertEquals(incidentState, "New");
			}         
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


