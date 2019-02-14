package testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.ProblemReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExtentReport;

import pages.ChangePage;

public class ChangeReg {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	static String crNumber=null;
	static String prNumber=null;
	@BeforeTest
	public void startReport(){
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport11.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amreen Noor");
		htmlReporter.config().setDocumentTitle("Change Request");
		htmlReporter.config().setReportName("Change Request Smoke suite");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateChnageRequest() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName2(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		//ChangeReusables.FinalReport(driver, "Draft", crNumber, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
	}
	@Test(priority=1,description="Creating change without mandtory field reason for change ",enabled=true)
public void testCreateNormalChangeRequestN() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName2(driver, "change");
		ChangeReusables.createChange1(driver,1,2);
		if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
			}else{
				System.out.println("invisible");
			
			}
	
		
	}
	@Test(priority=1,description="Creating change without mandtory field reason for change ",enabled=true)
	public void testCreateStandardChangeRequest1() throws IOException, InterruptedException{
			
			logger = extent.createTest("Creation of a CR Ticket");
			String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
			System.setProperty("webdriver.gecko.driver",FirefoxDriver);
			WebDriver driver=new FirefoxDriver();
			
		    driver.get("http://thomsonreutersqa.service-now.com");
		    SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName2(driver, "change");
			crNumber=ChangeReusables.createStandardChange(driver,1,2);
			
			driver.findElement(By.linkText(crNumber)).click();
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
			driver.close();
			Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
			
	
	
	}
	
	@Test(priority=1,description="Creating change without mandtory field reason for change ",enabled=true)
	public void testCreateStandardMultiplePhase() throws IOException, InterruptedException{
			
			logger = extent.createTest("Creation of a CR Ticket");
			String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
			System.setProperty("webdriver.gecko.driver",FirefoxDriver);
			WebDriver driver=new FirefoxDriver();
			
		    driver.get("http://thomsonreutersqa.service-now.com");
		    SafeLogin.logInUser(driver);
			WaitUtils.waitForPageToLoad(driver, 10);
			ServiceNowUtils.navigateToModuleName2(driver, "change");
			crNumber=ChangeReusables.createStandardChange(driver,1,2);
			
			driver.findElement(By.linkText(crNumber)).click();
			WaitUtils.waitForPageToLoad(driver, 10);
			ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
			
			ChangePage.getSubmitForPlanningBtn(driver).click();
			Thread.sleep(10000);
			ChangeReusables.moveToAssessmentStatestand(driver);
			ChangeReusables.schedule(driver);
			ChangePage.getUpdateBtn(driver).click();
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
			ChangePage.getRequestImplementationBtn(driver).click();
			ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
			 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		    
		    Thread.sleep(5000);
			WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
			ChangePage.getScheduleTab(driver).click();
			String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
			String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
			ChangePage.getChangeTaskTab(driver).click();
			System.out.println("success");
			ChangePage.getChangeTaskLnk(driver, 1).click();
			ChangePage.getExpectedStartBtn(driver).sendKeys(start);
			ChangePage.getExpectedEndBtn(driver).sendKeys(end);
			Thread.sleep(5000);
			ChangePage.getCompleteImplementationBtn(driver).click();
			ChangeReusables.AssignedTo(driver);
		    WebElement re=ChangePage.getImplementationResult(driver);
			DropDowns.selectDropdownByIndex(re, 1, "result");
			ChangePage.getActualStartBtn(driver).sendKeys(start);
			ChangePage.getActualEndBtn(driver).sendKeys(end);
			Thread.sleep(5000);
			ChangePage.getSaveBtn(driver).click();
			ChangePage.getCloseTaskBtn(driver).click();
			ChangePage.getUpdateBtn(driver).click();
			
			 ChangeReusables.FinalReport(driver, "Closed", crNumber, 7, 2);
			 ChangeReusables.FinalReport(driver, "Closed", crNumber, 8, 2);
				driver.close();	
				 Assert.assertTrue(true);
					logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is moving the change task to different state and validating the closure of change task ", ExtentColor.GREEN));
				
			//ChangeReusables.moveToApprovalState(driver);
			//ChangePage.getUpdateBtn(driver).click();
			//ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			//ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
			//ChangePage.getGroupApprovalTab(driver).click();
			//Thread.sleep(5000);
			//ChangePage.getChangeRequestedLnk(driver).click();
			//Thread.sleep(5000);
			//ChangePage.getApproveBtn(driver).click();
		    //Thread.sleep(5000);
		    ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		 //ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		    // driver.close();
			// Assert.assertTrue(true);
			// logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Approve of change ticket", ExtentColor.GREEN));
			
	
	
	
}
	
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateEmergencyChnageRequest() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName2(driver, "change");
		crNumber = ChangeReusables.createEmerChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		//ChangeReusables.FinalReport(driver, "Draft", crNumber, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
	}



@Test(priority=2,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
public void testMovingtoMultiplePhases() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	crNumber = ChangeReusables.createEmerChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	ChangeReusables.FinalReport(driver, "Approval", crNumber, 2, 2);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangePage.getGroupApprovalTab(driver).click();
	Thread.sleep(5000);
	ChangePage.getChangeRequestedLnk(driver).click();
	Thread.sleep(5000);
	ChangePage.getApproveBtn(driver).click();
	ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
	Thread.sleep(5000);
	WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
	ChangePage.getScheduleTab(driver).click();
	String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
	String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
	ChangePage.getChangeTaskTab(driver).click();
	System.out.println("success");
	ChangePage.getChangeTaskLnk(driver, 1).click();
	ChangePage.getExpectedStartBtn(driver).sendKeys(start);
	ChangePage.getExpectedEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getCompleteImplementationBtn(driver).click();
	ChangeReusables.AssignedTo(driver);
    WebElement re=ChangePage.getImplementationResult(driver);
	DropDowns.selectDropdownByIndex(re, 1, "result");
	ChangePage.getActualStartBtn(driver).sendKeys(start);
	ChangePage.getActualEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getSaveBtn(driver).click();
	ChangePage.getCloseTaskBtn(driver).click();
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
	ChangeReusables.FinalReport(driver, "Closed", crNumber, 2, 2);
	driver.close();	
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Moving the Change to Multiple phases ", ExtentColor.GREEN));
	}

@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
public void testCreateNormalChnageRequestN() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName2(driver, "change");
	ChangeReusables.creatChange2(driver,1,2);
	//ChangeReusables.createChange1(driver,1,2);
	if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
		}

}





}