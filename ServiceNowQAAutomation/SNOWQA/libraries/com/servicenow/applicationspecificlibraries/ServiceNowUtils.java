package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import pages.ChangePage;
import pages.HomePage;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

public class ServiceNowUtils {

	public static void navigateToModuleName(WebDriver driver, String moduleName) throws InterruptedException {
		WaitUtils.waitForIdPresent(driver, "filter");
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
		WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getCreateNewBtn(driver).click();
		Frames.switchToFrameById("gsft_main", driver);	
	}
	
	public static void navigateToAllQueueForDesiredModule(WebDriver driver, String moduleName){
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForIdPresent(driver, "filter");
		HomePage.getfilterEdt(driver).sendKeys(moduleName);
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getAllLnk(driver).click();
		Frames.switchToFrameById("gsft_main", driver);
		WaitUtils.waitForPageToLoad(driver, 10);
	}
	
	public static void searchDesiredTicket(WebDriver driver,String ticketNumber,String moduleName){
		if(ChangePage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), ticketNumber, "Searching for "+moduleName);
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			WaitUtils.waitForPageToLoad(driver, 10);
		}else{
				WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				DropDowns.selectDropdownByVisibleText(ChangePage.getSearchDropDown(driver), "Number", "Search Drop Down");
				TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), ticketNumber, "Search Change ");
				ReporterLogs.log("Entering "+moduleName+" in the Search Text "+ticketNumber, "info");
				ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
				WaitUtils.waitForPageToLoad(driver, 10);
		}
}
	
	}
