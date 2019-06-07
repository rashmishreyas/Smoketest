package testcase;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
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
import pages.IncidentTask;

public class IncidentRemoteSuite extends SuperTestNG
{
	static String incNumber=null;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@Test(priority=0,description="Create standalone incident ticket", groups="Incidents",enabled=true)
	public void testCreateStandAloneIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Standalone Incident Ticket","Create Standalone Incident Ticket");
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 30);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");

		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		//IncidentReusables.verifyStateOfIncidentTicket(driver, "New", incNumber, 1, 2);
		IncidentReusables.FinalReport(driver, "New", incNumber, 1, 2);
		driver.close();
		
	}
	
	@Test(priority=1,description="Create managed incident ticket",groups="Incidents",enabled=true)
	public void testCreateManagedIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Managed Incident Ticket","Create Managed Incident");		
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 30);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver,true,2,2);
		IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
		driver.close();
	}
}