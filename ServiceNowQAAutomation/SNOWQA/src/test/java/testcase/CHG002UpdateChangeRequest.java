package testcase;

import java.io.IOException;

import org.openqa.selenium.By;
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

public class CHG002UpdateChangeRequest extends SuperTestNG{
	static String crNumber=null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;
	@Test(priority=0,description="-----Create Change Test Case-----",enabled=true)

	public void testChangeTicketUpdation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Update Change Ticket", "Update Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		//ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber,1,2);
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Assessment", crNumber,1,2);
		plannedStartDate = Utils.getDesiredDateAndTime(1);
		plannedEndtDate= Utils.getDesiredDateAndTime(2);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		WaitUtils.waitForIdPresent(driver, "change_request.start_date");
		TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), plannedStartDate, "Planned Start Date");
		ReporterLogs.log("Requested By Date field is entered successfully "+ plannedStartDate, "info");
		WaitUtils.waitForIdPresent(driver, "change_request.end_date");
		TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), plannedEndtDate, "Planned End Date");
		 driver.findElement(By.xpath("//button[text()='Update']")).click();
		 ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber,1,2);
		 //ChangeReusables.FinalReport(driver, "Draf", crNumber, 1, 2);
		 
	
		
		}
	
	

}
