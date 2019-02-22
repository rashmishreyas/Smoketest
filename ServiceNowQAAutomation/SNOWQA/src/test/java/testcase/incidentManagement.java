package testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.Frames;
import com.servicenow.applicationspecificlibraries.IncidentReusables;
import com.servicenow.applicationspecificlibraries.IntakeReuasbles;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;

import pages.HomePage;
import pages.IncidentPage;

@Listeners(value=SnowReporter.class)
public class incidentManagement extends SuperTestNG {

	static String incNumber=null;
	static String modulename=null;
	static String errormessage=null;
	static String incidenttasknum=null;
	
	@Test(priority=0,description="Create standalone incident ticket", groups="Incidents",enabled=true)
	public void testCreateStandAloneIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Standalone Incident Ticket","Create Standalone Incident Ticket");
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 30);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");

		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
	}
	

	@Test(priority=1,description="Create managed incident ticket",groups="Incidents",enabled=true)
	public void testCreateManagedIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Managed Incident Ticket","Create Managed Incident");		
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 50);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, true,2,2);
		IncidentReusables.verifyManagedIncidentCreation(driver, incNumber);
	}
	
	
	@Test(priority=2,description="Progression of Incident ticket to Resolved state",enabled=true,groups="Incidents")
	public void testResolveIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Resolve Incident Ticket", "Progress Incident ticket to Resolved state");
		SafeLogin.logInUser(driver);
		
		//ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Incident");
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.resolveIncident(driver);
		
	}
	
	
	@Test(priority=3,description="Cancelling Incident Ticket",groups="Incidents", enabled=true)
	public void testCancelIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Cancel Incident Ticket", "Cancelling the Incident Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 4, 2);
		//IncidentReusables.verifyIncidentCreation(driver, incNumber);
		
		IncidentReusables.cancelIncident(driver, incNumber);		
	}


@Test(priority=4,description="Creation of Incident from Intake",groups="Incidents",enabled=true)
public void testcreateIncidentfromIntake() throws Exception {
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Create Incident Ticket from intake", "Creating the Incident Ticket");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"intake");
	incNumber=IntakeReuasbles.createIncidentfromIntake(driver, 1, 2);
	ServiceNowUtils.navigateToModuleName1(driver,"incident");

	
	//WaitUtils.waitForPageToLoad(driver, 30);
	// ServiceNowUtils.navigateToModuleName1(driver,"incident");
	
}

@Test(priority=5,description="End to end standalone flow",groups="Incidents",enabled=true)
public void teststandaloneflow() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.MovetoInprogress(driver);
	//IncidentReusables.verifyIncidentCreation1(driver, incNumber);
	IncidentReusables.OnholdIncident(driver);

	
}
@Test(priority=6,description="Validate mandatory fields in new state",groups="Incidents",enabled=true)
public void testverifymandatoryfield() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.Captureerrormessagenew(driver, errormessage);
	
	
	
}
@Test(priority=7,description="Validate mandatory fields in WIP state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldWIP() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Captureerrormessagenew1(driver, errormessage);
	
	
	
}
@Test(priority=8,description="Validate mandatory fields in Onhold state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldOnhold() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Captureerrormessagenew2(driver, errormessage);
	
	
	
}
@Test(priority=9,description="Validate mandatory fields in Resolved state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldresolved() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	
	IncidentReusables.Captureerrormessagenew3(driver, errormessage);
	
}

@Test(priority=10,description="Validate mandatory fields in Cancelled state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldCancelled() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	
	IncidentReusables.Captureerrormessagenew4(driver, errormessage);
	
}
@Test(priority=10,description="Create ATT tickets",groups="Incidents",enabled=true)
public void testcreateATTticket() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createATTIncident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
}
@Test(priority=11,description="Create incident task",groups="Incidents",enabled=true)
public void testcreateincidenttask() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcreateincidenttask", "Create incident task ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	incidenttasknum = IncidentReusables.CreateIncidentTask(driver, incNumber);
}
@Test(priority=12,description="Upload attachment",groups="Incidents",enabled=true)
public void testUploadattachment() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcreateincidenttask", "Create incident task ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Uploadattachment(driver);
}
@Test(priority=13,description="Remove attachment",groups="Incidents",enabled=true)
public void testRemoveattachment()  throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcreateincidenttask", "Create incident task ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Uploadattachment(driver);
	IncidentReusables.Removeattachment(driver);
}}