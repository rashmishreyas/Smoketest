package testcase;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

	@Test(priority=0,description="-----Create Change Test Case-----")
	public void testCreateChangeTicket() throws IOException, InterruptedException{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			ServiceNowUtils.navigateToModuleName(driver, "change");
			ChangeReusables.createChange(driver);
	}
	
/*	@Test(priority=1,description="-----Update Change Test Case-----")
	public void testUpdateChangeTicket() throws IOException, InterruptedException{
		String changeId = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
		SafeLogin.logInUser(driver);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		
	}
	*/
	/*@Test(priority=2,description="-----Cancel Change Test Case-----")
	public void testCancelChangeTicket() throws IOException, InterruptedException{
					
	}*/
}