package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.Utils;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;

public class CHG007CreateTask extends SuperTestNG {
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
	
	@Test(priority=0,description="-----Change create task-----",enabled=true)
	public void testChangeCreateTask() throws Exception{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Change Task Ticket", " Change Task Ticket Report");
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver, "change");
			crNumber = ChangeReusables.createChange(driver,1,2);
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangePage.getSubmitForPlanningBtn(driver).click();
			Thread.sleep(10000);
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
			WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
			ChangePage.getScheduleTab(driver).click();
			String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
			String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
			ChangePage.getChangeTaskTab(driver).click();
			ChangePage.getCreateTaskBtn(driver).click();
			ChangeReusables.ConfigItem(driver);
			ChangePage.getExpectedStartBtn(driver).sendKeys(start);
			ChangePage.getExpectedEndBtn(driver).sendKeys(end);
			ChangeReusables.AssignedTo(driver);
			ChangeReusables.AssignmentGroup(driver);
			ChangeReusables.ShortDis(driver);
			ChangeReusables.Dis(driver);
			Thread.sleep(10000);
			ChangePage.getSaveBtn(driver).click();
			ChangePage.getCompleteImplementationBtn(driver).click();
			WebElement re=ChangePage.getImplementationResult(driver);
			DropDowns.selectDropdownByIndex(re, 1, "result");
			ChangePage.getActualStartBtn(driver).sendKeys(start);
			ChangePage.getActualEndBtn(driver).sendKeys(end);
			ChangePage.getSaveBtn(driver).click();
			//ChangePage.getTaskSubmitBtn(driver).click();
			ChangePage.getUpdateBtn(driver).click();
			 ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
			 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 5, 2);
			 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 6, 2);
		
					
			
			
			
			
					
				
					
	}

	
	
	

}




		




