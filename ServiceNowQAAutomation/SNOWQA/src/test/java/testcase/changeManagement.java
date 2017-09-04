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

	@Test(priority=0,description="-----Create Change Test Case-----")
	public void testCreateChangeTicket() throws IOException, InterruptedException{
			ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
			SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName(driver, "change");
			crNumber = ChangeReusables.createChange(driver);
			ChangeReusables.verifyChangeCreation(driver, crNumber,1,4);
	}
	
	@Test(priority=1,description="-----Update Change Test Case-----",enabled=false)
	public void testUpdateChangeTicket() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
		SafeLogin.logInUser(driver);
		crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForIdPresent(driver, "filter");
		HomePage.getfilterEdt(driver).sendKeys("change");
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getAllLnk(driver).click();
		Frames.switchToFrameById("gsft_main", driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		if(ChangePage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), crNumber, "Search Change ");
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangePage.getSubmitForPlanningBtn(driver).click();
			Thread.sleep(10000);
			String stateofChange = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
			ReporterLogs.log("State of the Change is :"+stateofChange);
			if(stateofChange.equalsIgnoreCase("Planning")){
				Assert.assertEquals(stateofChange, "Planning");
				ExtentReport.reportLog(LogStatus.PASS, "Successfully updated change : "+crNumber);
				ReporterLogs.log("Successfully updated Change with Id "+crNumber, "info");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2, crNumber);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
			}else{
				ExtentReport.reportLog(LogStatus.FAIL, "Unable to update change : "+crNumber);
				ReporterLogs.log("Unable to update Change with Id "+crNumber, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2, crNumber);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
				Assert.assertEquals(stateofChange, "Planning");
		}
		}else{
				WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				DropDowns.selectDropdownByVisibleText(ChangePage.getSearchDropDown(driver), "Number", "Search Drop Down");
				TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), crNumber, "Search Change ");
				ReporterLogs.log("Entering Change Id in the Search Text "+crNumber, "info");
				ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
				WaitUtils.waitForPageToLoad(driver, 10);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
				WaitUtils.waitForPageToLoad(driver, 10);
				ChangePage.getSubmitForPlanningBtn(driver).click();
				WaitUtils.waitForPageToLoad(driver, 10);
				ChangePage.getSubmitForPlanningBtn(driver).click();
				Thread.sleep(10000);
				String stateofChange = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
				if(stateofChange.equalsIgnoreCase("Planning")){
					Assert.assertEquals(stateofChange, "Planning");
					ExtentReport.reportLog(LogStatus.PASS, "Successfully updated change : "+crNumber);
					ReporterLogs.log("Successfully updated Change with Id "+crNumber, "info");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2, crNumber);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
				}else{
						ExtentReport.reportLog(LogStatus.FAIL, "Unable to update change : "+crNumber);
						ReporterLogs.log("Unable to update Change with Id "+crNumber, "error");
						ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
						ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2, crNumber);
						Assert.assertEquals(stateofChange, "Planning");
			}
		}
	}
	
	@Test(priority=2,description="-----Cancel Change Test Case-----")
	public void testCancelChangeTicket() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "Test Create Change Ticket", "Create Change Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver);
		ChangeReusables.verifyChangeCreation(driver, crNumber, 3, 4);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangePage.getActivityTab(driver).click();
		ChangePage.getReasonForCancellationEdt(driver).sendKeys("test Cancel");
		ChangePage.getChangeCancelBtn(driver).click();
		WaitUtils.waitForPageToLoad(driver, 20);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		String stateofChange = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
		ReporterLogs.log("State of the Change is :"+stateofChange);
		if(stateofChange.equalsIgnoreCase("Cancelled")){
				Assert.assertEquals(stateofChange, "Cancelled");
				ExtentReport.reportLog(LogStatus.PASS, "Successfully Change change : "+crNumber);
				ReporterLogs.log("Successfully updated Change with Id "+crNumber, "info");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
			}else{
				ExtentReport.reportLog(LogStatus.FAIL, "Unable to Cancel change : "+crNumber);
				ReporterLogs.log("Unable to update Change with Id "+crNumber, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
				Assert.assertEquals(stateofChange, "Planning");
		}
		
		
	}
}