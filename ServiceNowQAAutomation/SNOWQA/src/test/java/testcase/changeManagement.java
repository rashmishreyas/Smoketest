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
import pages.HomePage;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.Frames;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
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
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver, "change");
			crNumber = ChangeReusables.createChange(driver);
			ChangeReusables.verifyChangeCreation(driver, crNumber,1,4);
	}
	
	@Test(priority=1,description="-----Update Change Test Case-----",enabled=true)
	public void testChangeTicketUpdation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Update Change Test Case", "Create Change Ticket");
		SafeLogin.logInUser(driver);
		crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
		ServiceNowUtils.searchDesiredTicket(driver, crNumber, "Change");
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber);
	}
	
	@Test(priority=2,description="-----Cancel Change Test Case-----",enabled=false)
	public void testChangeTicketCancellation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Cancel Change Ticket", "Create Change Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver);
		ChangeReusables.verifyChangeCreation(driver, crNumber, 3, 4);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.moveToCancelledState(driver, crNumber);		
		ChangeReusables.verifyStateOfChangeTicket(driver, "Cancelled", crNumber);
		
	}
	
	@Test(priority=3,description="-----Change Ticket Approval-----",enabled=false)
	public void testChangeTicketApproval() throws IOException, InterruptedException{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
			ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
			ServiceNowUtils.searchDesiredTicket(driver, crNumber, "Change");
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangePage.getSubmitForPlanningBtn(driver).click();
			Thread.sleep(10000);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Assessment", crNumber);
			ChangeReusables.moveToApprovalState(driver);
			ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Approval", crNumber);
			
			
			
	}
	
	@Test(priority=4,description="-----Change Ticket Closure-----",enabled=false)
	public void testChangeTicketClosure() throws IOException, InterruptedException{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
			ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
			ServiceNowUtils.searchDesiredTicket(driver, crNumber, "Change");
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			
	}
}