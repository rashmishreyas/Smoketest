package testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
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

public class CHG003ApproveChangeRequest extends SuperTestNG{
	static String crNumber=null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;
	static String changeId=null;
	static String assignmentGroup = null;
	static String configurationItem = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForChange = null;
	static String customerImpactDuringChange = null;
	static String implementationPlan= null;
	static String testPlan = null;
	static String backoutPlan = null;
	
	@Test(priority=0,description="-----Change Ticket Approval-----",enabled=true)
	public void testChangeTicketApproval() throws Exception{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Approve Change Ticket", "Approve Change Ticket Report");
		
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
			ChangePage.getApproveBtn(driver).click();
		    Thread.sleep(5000);
		  ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		  ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
			/*plannedStartDate = Utils.getDesiredDateAndTime(1);
			plannedEndtDate= Utils.getDesiredDateAndTime(2);
			WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
			ChangePage.getScheduleTab(driver).click();
			WaitUtils.waitForIdPresent(driver, "change_request.start_date");
			TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), plannedStartDate, "Planned Start Date");
			ReporterLogs.log("Requested By Date field is entered successfully "+ plannedStartDate, "info");
			WaitUtils.waitForIdPresent(driver, "change_request.end_date");
			TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), plannedEndtDate, "Planned End Date");
			 driver.findElement(By.xpath("//button[text()='Update']")).click();
			 ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
				ChangeReusables.moveToApprovalState(driver);
				 driver.findElement(By.xpath("//button[text()='Update']")).click();
				 ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
					ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
					ChangePage.getGroupApprovalTab(driver).click();
					Thread.sleep(5000);
					//ChangeReusables.verifyStateOfChangeTicket(driver, "Approval", crNumber,3,2);
					driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[3]/a")).click();
					Thread.sleep(5000);
					driver.findElement(By.xpath("//button[text()='Approve']")).click();*/
			 
					
	}

}
