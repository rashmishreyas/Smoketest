package com.servicenow.applicationspecificlibraries;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.ChangePage;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;

import freemarker.core.ReturnInstruction.Return;

public class ChangeReusables {

	public static String createChange(WebDriver driver){
		
		String changeId=null;
		try{
				WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Normal: Planned')]");
				ChangePage.getNormalLnk(driver).click();
				ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				String assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				String configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				String shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				String description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				String reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				String customerImpactDuringChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 10);
				String implementationPlan= ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 11);
				String testPlan = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 12);
				String backoutPlan = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 13);
				changeId = ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 2, changeId);
				ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
				WaitUtils.waitForXpathPresent(driver, ".//*[@id='tabs2_section']/span[2]/span/span[2]");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.u_customer_impact");
				TextBoxes.enterTextValue(ChangePage.getCustomerImpactDuringChangeEdt(driver), customerImpactDuringChange, "Customer Impact During Change");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ customerImpactDuringChange, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.change_plan");
				TextBoxes.enterTextValue(ChangePage.getImplementationPlanEdt(driver), implementationPlan, "Implementation Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ implementationPlan, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.test_plan");
				TextBoxes.enterTextValue(ChangePage.getTestPlanEdt(driver), testPlan, "Test Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ testPlan, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.backout_plan");
				TextBoxes.enterTextValue(ChangePage.getBackoutPlanEdt(driver), backoutPlan, "Backout Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ backoutPlan, "info");
				WaitUtils.waitForXpathPresent(driver, "//*[@id='tabs2_section']/span[4]/span/span[2]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
				}catch(Exception e){
			e.getMessage();
		}
		return changeId;
	}
	
	public static void verifyChangeCreation(WebDriver driver, String crNumber, int snum,int cellnum) throws FileNotFoundException, IOException{
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForTitleToBeDisplayed(driver, "Change Requests | ServiceNow");
		if(ChangePage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), crNumber, "Search Change ");
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			WaitUtils.waitForPageToLoad(driver, 10);
			if(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText().equalsIgnoreCase("Draft")){
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
				ExtentReport.reportLog(LogStatus.PASS, "Successfully created change : "+crNumber);
				ReporterLogs.log("Successfully created Change with Id "+crNumber, "info");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Passed");
			}else{
				ReporterLogs.log("Actual Status displayed is "+ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "error");
				ReporterLogs.log("Unable to create a Change Ticket "+crNumber, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Failed");
				ExtentReport.reportLog(LogStatus.FAIL, "Unable to create change : "+crNumber);
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
			}	
			
		}else{
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			DropDowns.selectDropdownByVisibleText(ChangePage.getSearchDropDown(driver), "Number", "Search Drop Down");
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), crNumber, "Search Change ");
			ReporterLogs.log("Entering Change Id in the Search Text "+crNumber, "info");
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			if(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText().equalsIgnoreCase("Draft")){
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
				ExtentReport.reportLog(LogStatus.PASS, "Successfully created change : "+crNumber);
				ReporterLogs.log("Successfully created Change with Id "+crNumber, "info");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Passed");	
			}else{
				ReporterLogs.log("Actual Status displayed is "+ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "error");
				ReporterLogs.log("Unable to create a Change Ticket "+crNumber, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Failed");
				ExtentReport.reportLog(LogStatus.FAIL, "Unable to create change : "+crNumber);
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
			}		
		}
	}
	
	public static void searchChange(WebDriver driver){
		
	}
	
}
