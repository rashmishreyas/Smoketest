package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.ChangePage;
import pages.ChangeTaskPage;
import pages.HomePage;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.Frames;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.Utils;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

@Listeners(value=SnowReporter.class)
public class changeManagement extends SuperTestNG{
	
	static String crNumber=null;

	@Test(priority=0,description="-----Create Change Test Case-----",enabled=false)
	public void testCreateChangeTicket() throws IOException, InterruptedException{
		try{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver, "change");
			crNumber = ChangeReusables.createChange(driver,1,2);
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangeReusables.verifyChangeCreation(driver, crNumber,1,4);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
			ReporterLogs.log("---Create Change Test Case is Passed---", "info");
	}catch(Exception ex){
		ReporterLogs.log("Exception in closure "+ex, "error");
	}
	}
	@Test(priority=1,description="-----Change Ticket Closure-----",enabled=false)
	public void testChangeTicketClosure() throws IOException, InterruptedException{
		try{
				ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
				SafeLogin.logInUser(driver);
				crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
				ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
				ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
				WaitUtils.waitForPageToLoad(driver, 10);
				ChangePage.getSubmitForPlanningBtn(driver).click();
				Thread.sleep(10000);
				ChangeReusables.moveToAssessmentState(driver);
				ChangeReusables.moveToApprovalState(driver);
				ChangeReusables.moveToImplementationState(driver);
				ChangeReusables.moveToClosedState(driver);
				ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,5,2);		
				ReporterLogs.log("---Closure Change Test Case is Passed---", "info");
		}catch(Exception ex){
			ReporterLogs.log("Exception in closure "+ex, "error");
		}		
	}

	@Test(priority=2,description="-----Update Change Test Case-----",enabled=true)
	public void testChangeTicketUpdation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Update Change Test Case", "Create Change Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,2,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber,2,2);
		ReporterLogs.log("---Update Change Test Case is Passed---", "info");
	}
	
	@Test(priority=3,description="-----Change Ticket Approval-----",enabled=true)
	public void testChangeTicketApproval() throws IOException, InterruptedException{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
			ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangeReusables.moveToAssessmentState(driver);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Assessment", crNumber,3,2);
			ChangeReusables.moveToApprovalState(driver);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Approval", crNumber,3,2);				
	}
	
	@Test(priority=4,description="-----Cancel Change Test Case-----",enabled=false)
	public void testChangeTicketCancellation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Cancel Change Ticket", "Create Change Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,3,2);
		ChangeReusables.verifyChangeCreation(driver, crNumber, 3, 4);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.moveToCancelledState(driver, crNumber);		
		ChangeReusables.verifyStateOfChangeTicket(driver, "Cancelled", crNumber,4,2);
		
	}
}