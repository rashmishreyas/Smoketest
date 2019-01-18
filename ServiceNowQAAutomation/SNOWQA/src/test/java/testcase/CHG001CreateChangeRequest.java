package testcase;

import java.io.IOException;


import org.testng.annotations.Test;

import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExtentReport;

import pages.ChangePage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class CHG001CreateChangeRequest extends SuperTestNG {
	static String crNumber = null;
	

	@Test(priority = 0, description = "-----Create Change Test Case-----", enabled = true)
	public void testCreateChangeTicket() throws IOException, InterruptedException {

		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket","Create Change Ticket Report");
		//logger = extent.createTest("passTest");
		
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver, 1, 2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangeReusables.verifyChangeCreation(driver, crNumber, 1, 4);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber, 1, 2);
		System.out.println("testing1");
		ChangeReusables.FinalReport(driver, "Draf", crNumber, 1, 2);
		//Assert.assertTrue(true);
		//logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));

	}
}