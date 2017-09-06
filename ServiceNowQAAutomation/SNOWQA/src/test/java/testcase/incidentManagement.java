package testcase;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.Frames;
import com.servicenow.applicationspecificlibraries.IncidentReusables;
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

	
	@Test(priority=0,description="Create standalone incident ticket", groups="Incidents")
	public void testCreateStandAloneIncidentTicket() throws Exception{
		
		//ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"testCreateStandAloneIncidentTicket","CreateStandAloneIncident");
		
		try {
			SafeLogin.logInUser(driver);			
			WaitUtils.waitForPageToLoad(driver, 10);			
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createIncident(driver, false);	
			
			}
		catch (Exception e) {
			ReporterLogs.log("Exception "+e.getMessage(), "info");
			}		
		
	}
	

	@Test(priority=1,description="Create managed incident ticket",groups="Incidents",enabled=false)
	public void testCreateManagedIncidentTicket() throws Exception{
		
		//ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"testCreateManagedIncidentTicket","Create Managed Incident");
		
		try {
			SafeLogin.logInUser(driver);			
			WaitUtils.waitForPageToLoad(driver, 10);			
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createIncident(driver, true);	
			
			}
		catch (Exception e) {
			ReporterLogs.log("Exception "+e.getMessage(), "info");
		}		
		
	}
	
	@Test(priority=2,description="Progression of Incident ticket to Resolved state",groups="Incidents",dependsOnMethods={"testCreateStandAloneIncidentTicket"})
	public void testResolveIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Resolve Incident Ticket", "Progress Incident ticket to Resolved state");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Incident");	
		IncidentReusables.resolveIncident(driver);		
	}
	
	
	@Test(priority=3,description="Cancelling Incident Ticket",groups="Incidents")
	public void testCancelIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Cancel Incident Ticket", "Cancelling the Incident Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Incident");	
		IncidentReusables.resolveIncident(driver);		
	}
}
