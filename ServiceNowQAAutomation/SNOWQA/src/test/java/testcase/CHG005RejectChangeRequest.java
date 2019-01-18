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
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;

public class CHG005RejectChangeRequest extends SuperTestNG{
static String crNumber=null;
static String plannedStartDate = null;
static String plannedEndtDate=null;
	
	
	
	@Test(priority=3,description="-----Change Ticket Reject-----",enabled=true)
	public void testChangeTicketApproval() throws IOException, InterruptedException{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Reject Change Ticket", "Reject Change Ticket Report");
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
			ChangeReusables.schedule(driver);
			ChangePage.getUpdateBtn(driver).click();
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
			ChangeReusables.moveToApprovalState(driver);
			ChangePage.getUpdateBtn(driver).click();
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
			ChangePage.getGroupApprovalTab(driver).click();
			Thread.sleep(5000);
			ChangePage.getChangeRequestedLnk(driver).click();
			Thread.sleep(5000);
			
					driver.findElement(By.xpath("//button[text()='Reject']")).click();
					driver.findElement(By.id("sysapproval_group.u_reason_for_rejection")).sendKeys("test");
					driver.findElement(By.xpath("//button[text()='Update']")).click();
					 ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
					 ChangeReusables.FinalReport(driver, "Closed", crNumber, 9, 2);
			 
			
}
}
