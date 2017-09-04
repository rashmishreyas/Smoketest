package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import pages.HomePage;

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
}