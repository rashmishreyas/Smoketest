package com.servicenow.applicationspecificlibraries;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.HomePage;
import pages.IncidentPage;
//import pages.IncidentPage;
import pages.Intakepage;
public class IntakeReuasbles{
	static String requestedby = null;
	static String source = null;
	static String location = null;
	static String businessServiceCatalog = null;
	static String situation = null;
	static String openedbygroup = null;
	static String shortDescription = null;
	static String description = null;
	static String incidentNumber = null;
	static String intakeId = null;
	static String modulename = null;
	static String element = null;
	static String assignmentGroupValue = null;
//Create incident from intake *Rashmi*
public static String createIncidentfromIntake(WebDriver driver,int sNum, int cellNum) throws Exception
{
	try
	{
		WaitUtils.waitForPageToLoad(driver, 30);
		//Reading values from excel for creating incident ticket
		
		requestedby=ExcelUtils.getData("Intake_Management_Testdata.xlsx","Smoke_Suite",1,4);
		source=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1, 5);
		location=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1, 7);
		businessServiceCatalog=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1, 8);
		situation=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1,9);
		openedbygroup=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1,11);
		shortDescription=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1,12);
		description=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1,13);
		
		//Store incident number in a variable
		/*WaitUtils.waitForPageToLoad(driver, 30);
		WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
		incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
		ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
		ReporterLogs.log("Incident number captured: "+incidentNumber, "info");*/
		
		//requested by field
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_requested_by");
		Thread.sleep(2000);
		Intakepage.getrequestedbyEdt(driver).sendKeys(requestedby);
		
		//source field
		WaitUtils.waitForIdPresent(driver, "ticket.contact_type");
		DropDowns.selectDropdownByVisibleText(Intakepage.getsourceEdt(driver), source, "Source");
		
		//loctaion field
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.location");
		Intakepage.getlocationEdt(driver).sendKeys(location);
		
		//business service catalog field
		
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_business_service_catalog");
		Intakepage.getbusinessServiceCatalogEdt(driver).sendKeys(businessServiceCatalog);
		
		//Situation field
		
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_symptom_map");
		Thread.sleep(2000);
		//Intakepage.getsituationEdt(driver).sendKeys(situation);
		TextBoxes.enterTextValue(Intakepage.getsituationEdt(driver), situation, "Situation");                    
        Intakepage.getsituationEdt(driver).sendKeys(Keys.ENTER);
        Intakepage.getsituationEdt(driver).sendKeys(Keys.DOWN);
        Intakepage.getsituationEdt(driver).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
		
		//openedby group field
		
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_opened_by_group");
		Intakepage.getopenedbygroup(driver).sendKeys(openedbygroup);
		 TextBoxes.enterTextValue(Intakepage.getshortdescEdt(driver), shortDescription, "Short Description");
         Thread.sleep(2000);
         TextBoxes.enterTextValue(Intakepage.getdescEdt(driver), description, "Description");
         Thread.sleep(2000);
         
		
		//shortdescription field
		
		/*WaitUtils.waitForIdPresent(driver, "ticket.short_description");
		Intakepage.getshortdescEdt(driver).sendKeys(shortDescription);
		
		//description field
		
		WaitUtils.waitForIdPresent(driver, "ticket.description");
		Intakepage.getdescEdt(driver).sendKeys(description);
		
		Thread.sleep(2000);*/
		
		//Click on Next button
	//WaitUtils.waitForXpathPresent(driver, "//button[text()='Next']");
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
		
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		
		//Store the Incident number in a variable
		WaitUtils.waitForPageToLoad(driver, 30);
		WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
		incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
		
		System.out.println(incidentNumber);
		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
		//ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
		ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
		assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
		
		//Assignment group value			
		WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
		IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);

		ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
				
		Thread.sleep(2000);
		WaitUtils.waitForXpathPresent(driver, "//button[text()='Save']");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//element = driver.findElement(By.id("sys_readonly.incident.number")).getAttribute(incidentNumber);
	
		//System.out.println(element);
		
		
		
		//retrieve Incnumber from excel data
		
		
		
		
		
		
		
		try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        ReporterLogs.log("Alert message: " + alertText, "error");
	        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
	        Assert.fail("Unexpected alert !!!! ");
	        } 
		catch (Exception e) {
       	 System.out.println("handled");
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
	return intakeId;
}

public static String createIntake(WebDriver driver,int sNum, int cellNum) throws Exception
{
	try
	{
		WaitUtils.waitForPageToLoad(driver, 30);
		//Reading values from excel for creating incident ticket
		
		requestedby=ExcelUtils.getData("Intake_Management_Testdata.xlsx","Smoke_Suite",1,4);
		source=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1, 5);
		location=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1, 7);
		businessServiceCatalog=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 2, 8);
		situation=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 2,9);
		openedbygroup=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 1,11);
		shortDescription=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 2,12);
		description=ExcelUtils.getData("Intake_Management_Testdata.xlsx", "Smoke_Suite", 2,13);
		
		
		//requested by field
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_requested_by");
		Thread.sleep(2000);
		Intakepage.getrequestedbyEdt(driver).sendKeys(requestedby);
		
		//source field
		WaitUtils.waitForIdPresent(driver, "ticket.contact_type");
		DropDowns.selectDropdownByVisibleText(Intakepage.getsourceEdt(driver), source, "Source");
		
		//loctaion field
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.location");
		Intakepage.getlocationEdt(driver).sendKeys(location);
		
		//business service catalog field
		
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_business_service_catalog");
		Intakepage.getbusinessServiceCatalogEdt(driver).sendKeys(businessServiceCatalog);
		
		//Situation field
		
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_symptom_map");
		Thread.sleep(2000);
		//Intakepage.getsituationEdt(driver).sendKeys(situation);
		TextBoxes.enterTextValue(Intakepage.getsituationEdt(driver), situation, "Situation");                    
        Intakepage.getsituationEdt(driver).sendKeys(Keys.ENTER);
        Intakepage.getsituationEdt(driver).sendKeys(Keys.DOWN);
        Intakepage.getsituationEdt(driver).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
		
		//openedby group field
		
		WaitUtils.waitForIdPresent(driver, "sys_display.ticket.u_opened_by_group");
		Intakepage.getopenedbygroup(driver).sendKeys(openedbygroup);
		 TextBoxes.enterTextValue(Intakepage.getshortdescEdt(driver), shortDescription, "Short Description");
         Thread.sleep(2000);
         TextBoxes.enterTextValue(Intakepage.getdescEdt(driver), description, "Description");
         Thread.sleep(2000);
         
		
		
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Next']")));
		
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		
		
		
		
				
		
		
		
		
		
		try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        ReporterLogs.log("Alert message: " + alertText, "error");
	        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
	        Assert.fail("Unexpected alert !!!! ");
	        } 
		catch (Exception e) {
       	 System.out.println("handled");
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
	return intakeId;
}

}



