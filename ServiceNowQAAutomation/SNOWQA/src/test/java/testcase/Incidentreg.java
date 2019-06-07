package testcase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.servicenow.applicationspecificlibraries.IncidentReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import pages.IncidentPage;
import pages.Intakepage;


public class Incidentreg extends SuperTestNG
{
	static String incNumber = null;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@Test(priority=0,description="Create standalone incident ticket", enabled=true)
	public void testCreateStandAloneIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Smokeincident", "Create Incident Ticket Report");
		
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 30);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		driver.close();
	}
	


	@Test(priority=1,description="Progression of Incident ticket to Work in Progress state",enabled=true)
	public void testWIPIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Inprogress Incident Ticket", "Progress Incident ticket to In progress state");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 20);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.MovetoInprogress(driver);
		IncidentReusables.verifyStateOfIncidentTicket(driver, "Work in Progress", incNumber, 1, 2);
		IncidentReusables.FinalReport(driver, "Work in Progress", incNumber, 3, 2);
		driver.close();
	
}

	@Test(priority=2,description="Progression of Incident ticket to Resolved state",enabled=true)
	public void testResolveIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Resolve Incident Ticket", "Progress Incident ticket to Resolved state");
		SafeLogin.logInUser(driver);
		
		//ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Incident");
		WaitUtils.waitForPageToLoad(driver, 20);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.resolveIncident(driver);
		IncidentReusables.verifyStateOfIncidentTicket(driver, "Resolved", incNumber, 1, 2);
		IncidentReusables.FinalReport(driver, "Resolved", incNumber, 4, 2);
		driver.close();
		
	}
	

	@Test(priority=3,description="Cancelling Incident Ticket", enabled=true)
	public void testCancelIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Cancel Incident Ticket", "Cancelling the Incident Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 20);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 4, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		
		IncidentReusables.cancelIncident(driver, incNumber);
		IncidentReusables.verifyStateOfIncidentTicket(driver, "Cancelled", incNumber, 1, 2);
		IncidentReusables.FinalReport(driver, "Cancelled", incNumber,5 , 2);
		driver.close();
	}

	
}