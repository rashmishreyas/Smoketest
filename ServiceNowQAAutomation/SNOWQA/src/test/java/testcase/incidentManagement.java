package testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;

import pages.HomePage;
import pages.IncidentPage;
import pages.IncidentTask;

@Listeners(value=SnowReporter.class)
public class incidentManagement extends SuperTestNG {

	static String incNumber=null;
	static String modulename=null;
	static String errormessage=null;
	static String incidenttasknum=null;
	static WebElement element = null;
	static String Assignmentgroup1 = null;
	
	@Test(priority=0,description="Create standalone incident ticket", groups="Incidents",enabled=true)
	public void testCreateStandAloneIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Standalone Incident Ticket","Create Standalone Incident Ticket");
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 30);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");

		incNumber=IncidentReusables.createstandaloneincident(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		//IncidentReusables.verifyStateOfIncidentTicket(driver, "New", incNumber, 1, 2);
		//IncidentReusables.FinalReport(driver, "New", incNumber, 1, 2);
		driver.close();
		
	}
	

	@Test(priority=1,description="Create managed incident ticket",groups="Incidents",enabled=true)
	public void testCreateManagedIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Managed Incident Ticket","Create Managed Incident");		
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 50);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createmanagedIncident(driver,2,2);
		IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
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
		IncidentReusables.verifyStateOfIncidentTicket(driver, "Resolved", incNumber, 1, 2);
		IncidentReusables.FinalReport(driver, "Resolved", incNumber, 4, 2);
		driver.close();
		
	}
	@Test(priority=3,description="Progression of Incident ticket to Work in Progress state",enabled=true,groups="Incidents")
	public void testWIPIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Resolve Incident Ticket", "Progress Incident ticket to Resolved state");
		SafeLogin.logInUser(driver);
		
		//ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Incident");
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.MovetoInprogress(driver);
		IncidentReusables.verifyStateOfIncidentTicket(driver, "Work in Progress", incNumber, 1, 2);
		IncidentReusables.FinalReport(driver, "Work in Progress", incNumber, 3, 2);
		driver.close();
	}
	
	
	@Test(priority=4,description="Cancelling Incident Ticket",groups="Incidents", enabled=true)
	public void testCancelIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Cancel Incident Ticket", "Cancelling the Incident Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 4, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		
		IncidentReusables.cancelIncident(driver, incNumber);
		driver.close();
	}


@Test(priority=5,description="Creation of Incident from Intake",groups="Incidents",enabled=true)
public void testcreateIncidentfromIntake() throws Exception {
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Create Incident Ticket from intake", "Creating the Incident Ticket");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName1(driver,"intake");
	incNumber=IntakeReuasbles.createIncidentfromIntake(driver, 1, 2);

	driver.close();
	
	
}

@Test(priority=6,description="End to end standalone flow",groups="Incidents",enabled=true)
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
	driver.close();
	
}
@Test(priority=7,description="Validate mandatory fields in new state",groups="Incidents",enabled=true)
public void testverifymandatoryfield() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.Captureerrormessagenew(driver, errormessage);
	driver.close();
	
	
}
@Test(priority=8,description="Validate mandatory fields in WIP state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldWIP() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Captureerrormessagenew1(driver, errormessage);
	driver.close();
	
	
}
@Test(priority=9,description="Validate mandatory fields in Onhold state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldOnhold() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Captureerrormessagenew2(driver, errormessage);
	
	driver.close();
	
}
@Test(priority=10,description="Validate mandatory fields in Resolved state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldresolved() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	
	IncidentReusables.Captureerrormessagenew3(driver, errormessage);
	driver.close();
}

@Test(priority=11,description="Validate mandatory fields in Cancelled state",groups="Incidents",enabled=true)
public void testverifymandatoryfieldCancelled() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	
	IncidentReusables.Captureerrormessagenew4(driver, errormessage);
	driver.close();
}
@Test(priority=12,description="Create ATT tickets",groups="Incidents",enabled=true)
public void testcreateATTticket() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "teststandaloneflow", "End to end standalone flow ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createATTIncident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
}
@Test(priority=13,description="Create incident task",groups="Incidents",enabled=true)
public void testcreateincidenttask() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcreateincidenttask", "Create incident task ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	incidenttasknum = IncidentReusables.CreateIncidentTask(driver, incNumber);
	Thread.sleep(2000);
	driver.navigate().refresh();
	
	Thread.sleep(2000);
	
	ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
	IncidentReusables.verifyIncidentCreation1(driver, incNumber);
	IncidentReusables.MovetasktoWIP(driver, incNumber);
	driver.close();
}
@Test(priority=14,description="Upload attachment",groups="Incidents",enabled=true)
public void testUploadattachment() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcreateincidenttask", "Create incident task ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	IncidentReusables.Uploadattachment(driver);
	driver.close();
}
@Test(priority=15,description="Remove attachment",groups="Incidents",enabled=true)
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
	driver.close();
}
@Test(priority=16,description="validate incident task",groups="Incidents",enabled=true)
public void testvalidatetask() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcreateincidenttask", "Create incident task ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//ServiceNowUtils.navigateToModuleName(driver,"incident");
	ServiceNowUtils.navigateToAllQueueForDesiredModule1(driver, "incident");
	IncidentReusables.CreateIncidentTask(driver, incNumber);
	IncidentPage.getSearchIncidenttask(driver);
	//driver.findElement(By.xpath("")).sendKeys(incNumber);
	//incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	
	incidenttasknum = IncidentReusables.ValidateIncidenttask(driver, incNumber);
	driver.close();
}
@Test(priority=17,description="CreateIncforuserimpact3",groups="Incidents",enabled=true)
public void testcreateINCforuserimpact3() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "CreateIncforuserimpact3", "CreateIncforuserimpact3 ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.CreateINCforuserimpact3(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	driver.close();
}
@Test(priority=18,description="CreateIncforuserimpact1",groups="Incidents",enabled=true)
public void testcreateINCforuserimpact1() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "CreateIncforuserimpact1", "CreateIncforuserimpact1 ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.CreateINCforuserimpact1(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	driver.close();

}
@Test(priority=19,description="Createproblemfromincident",groups="Incidents",enabled=true)
public void testcreateProblemfromIncident() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Createproblemfromincident", "Createproblemfromincident ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createproblemfromincident(driver, false, 1, 2);
	//IncidentReusables.verifyIncidentCreation(driver, incNumber);
	driver.close();
}
@Test(priority=20,description="Movetasktoclosecomplete",groups="Incidents",enabled=true)
public void testmoveIncidentTasktoclosecomplete() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Movetasktoclosecomplete", "Movetasktoclosecomplete ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);

IncidentReusables.CreateIncidentTask(driver, incNumber);
Thread.sleep(2000);
driver.navigate().refresh();

Thread.sleep(2000);

ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
IncidentReusables.verifyIncidentCreation1(driver, incNumber);


IncidentReusables.Movetasktclosecomplete(driver, incNumber);
driver.close();
}


@Test(priority=21,description="Movetasktocancel",groups="Incidents",enabled=true)
public void testmoveIncidentTasktocancel() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Movetasktocancel", "Movetasktocancel ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);

IncidentReusables.CreateIncidentTask(driver, incNumber);
Thread.sleep(2000);
driver.navigate().refresh();

Thread.sleep(2000);

ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
IncidentReusables.verifyIncidentCreation1(driver, incNumber);


IncidentReusables.Movetasktocancel(driver, incNumber);
driver.close();
}
@Test(priority=22,description="Active IM task ",groups="Incidents",enabled=true)
public void testverifyINCforActiveIMtask() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testverifyINCforActiveIMtask", "testverifyINCforActiveIMtask");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);

IncidentReusables.CreateIncidentTask(driver, incNumber);
Thread.sleep(2000);
driver.navigate().refresh();
Thread.sleep(2000);
ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
IncidentReusables.verifyIncidentCreation1(driver, incNumber);
IncidentReusables.cancelIncident1(driver, incNumber);
driver.close();



}

@Test(priority=23,description="Reassign IM ",groups="Incidents",enabled=true)
public void testreassignim() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testreassignim", "Reassign incident ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	
	IncidentReusables.ReassignIM(driver, incNumber);
	driver.close();
}
	
@Test(priority=24,description="Copy Incident ",groups="Incidents",enabled=true)
public void testcopyincident() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "testcopyincident", "Copy incident ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.Copyincident(driver, false, 1, 2);
	driver.close();
}
@Test(priority=25,description="Create normal change from Incident ",groups="Incidents",enabled=true)
public void testCreateNormalChangefromInc() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Create normal change from Incident", "Normal change ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.createnormalchangefromincident(driver, false, 1, 2);
	driver.close();

}
@Test(priority=26,description="Create emergency change from Incident ",groups="Incidents",enabled=true)
public void testCreateEmergencyChangefromInc() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Create emergency change from Incident", "Emergency change ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.createemergencychangefromincident(driver, false, 1, 2);

}
@Test(priority=27,description="Create standard change from Incident ",groups="Incidents",enabled=true)
public void testCreateStandardChangefromInc() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Create standard change from Incident", "standard change ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.createstandardchangefromincident(driver, false, 1, 2);

}
@Test(priority=28,description="Create Child incident from Incident ",groups="Incidents",enabled=true)
public void testCreateChildIncfromInc() throws Exception{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Create child incident from Incident", "Child incident ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	IncidentReusables.creatchildincidentfromincident(driver, false, 1, 2);
	driver.close();
}
@Test(priority=1,description="Create incident alert",groups="Incidents",enabled=true)
public void testCreateincidentalert() throws Exception{
	
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create incident alert","Create incident alert");		
	SafeLogin.logInUser(driver);			
	WaitUtils.waitForPageToLoad(driver, 50);			
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.createIncident(driver,true,2,2);
	IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
	IncidentReusables.CreateincidentAlert(driver);
	driver.close();
}
@Test(priority=18,description="CreateIncforuserimpact2",groups="Incidents",enabled=true)
public void testcreateINCforuserimpact2() throws Exception
{
	ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "CreateIncforuserimpact2", "CreateIncforuserimpact2 ");
	SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver,"incident");
	incNumber=IncidentReusables.CreateINCforuserimpact2(driver, false, 1, 2);
	IncidentReusables.verifyIncidentCreation(driver, incNumber);
	driver.close();
}

}