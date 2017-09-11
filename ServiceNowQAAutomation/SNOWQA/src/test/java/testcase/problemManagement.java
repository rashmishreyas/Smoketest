package testcase;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.servicenow.applicationspecificlibraries.ProblemReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExtentReport;

@Listeners(value=SnowReporter.class)
public class problemManagement extends SuperTestNG{
       
       
       static String prNumber=null;

       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true)
       public void testCreateProblemTicket() throws Exception{
                     ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
                     SafeLogin.logInUser(driver);
                     WaitUtils.waitForPageToLoad(driver, 10);
                     ServiceNowUtils.navigateToModuleName(driver, "Problem");
                     prNumber=ProblemReusables.createProblem(driver,1,2);
                     ProblemReusables.verifyProblemCreation(driver, prNumber);
                     
       }
}
