package testcase;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;

import com.servicenow.applicationspecificlibraries.IncidentReusables;
import com.servicenow.applicationspecificlibraries.IntakeReuasbles;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;

@Listeners(value=SnowReporter.class)
public class IntakeManagement extends SuperTestNG {
	static String incNumber=null;
    
    
    @Test(priority=0,description="-----Create intake Test Case-----",enabled=true, groups="intake")
    public void testCreateintaketicket() throws Exception{
 	   ExtentReport.startReport(Capabilities.getPropertyValue("IntakeReports"), "Test Create intake Ticket", "Create Problem Ticket");
 	   SafeLogin.logInUser(driver);
 	   WaitUtils.waitForPageToLoad(driver, 10);
 	   ServiceNowUtils.navigateToModuleName1(driver, "Intake");
 	  incNumber = IntakeReuasbles.createIntake(driver,1,2);
 	driver.findElement(By.xpath("//button[@id='order_now']")).click();
 	 
 	
 	   Thread.sleep(5000);

}}
    
    
   