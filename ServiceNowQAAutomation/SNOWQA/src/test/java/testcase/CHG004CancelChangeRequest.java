package testcase;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.Utils;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;

public class CHG004CancelChangeRequest extends SuperTestNG {
	static String crNumber=null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;

	@Test(priority=4,description="-----Cancel Change Test Case-----",enabled=true)
	public void testChangeTicketCancellation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Cancel Change Ticket", "Cancel Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Planning", crNumber, 4, 2);
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Assessment", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Assessment", crNumber, 4, 2);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangeReusables.moveToCancelledState(driver, crNumber);	
		/*ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	

		*/
		 ChangeReusables.verifyStateOfChangeTicket(driver, "Cancelled", crNumber,1,2);
		 ChangeReusables.FinalReport(driver, "Cancelled", crNumber, 4, 2);
}
}
	

	
	