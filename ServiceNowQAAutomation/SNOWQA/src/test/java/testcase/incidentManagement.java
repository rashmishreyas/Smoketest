package testcase;

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
import com.servicenow.genericlibraries.Capabilities;
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
			com.servicenow.applicationspecificlibraries.WaitUtils.waitForPageToLoad(driver, 10);			
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createIncident(driver, false);	
			
			}
		catch (Exception e) {
			ReporterLogs.log("Exception "+e.getMessage(), "info");
			}		
		
	}
	

	@Test(priority=1,description="Create managed incident ticket",groups="Incidents")
	public void testCreateManagedIncidentTicket() throws Exception{
		
		//ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"testCreateManagedIncidentTicket","Create Managed Incident");
		
		try {
			SafeLogin.logInUser(driver);			
			com.servicenow.applicationspecificlibraries.WaitUtils.waitForPageToLoad(driver, 10);			
			ServiceNowUtils.navigateToModuleName(driver,"incident");
			IncidentReusables.createIncident(driver, true);	
			
			}
		catch (Exception e) {
			ReporterLogs.log("Exception "+e.getMessage(), "info");
		}		
		
	}
	
	
}
