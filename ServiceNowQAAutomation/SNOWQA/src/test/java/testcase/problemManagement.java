package testcase;
import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProblemPage;
import pages.ProblemTaskPage;

import com.servicenow.applicationspecificlibraries.ProblemReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;

@Listeners(value=SnowReporter.class)
public class problemManagement extends SuperTestNG{
              
       static String prNumber=null;
       static String prTaskNumber=null;
       static String State=null;
       
       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true, groups="Problems")
       public void testCreateProblemTicket() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName2(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem(driver,1,2);
    	   ProblemReusables.verifyProblemCreation(driver, prNumber); 
    	   driver.close();
       } 
       @Test(priority=1,description="-----update Problem Test Case-----",enabled=true)
       public void testUpdateProblemTicket() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test CreateProblem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName2(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem(driver,1,2);
    	      ProblemReusables.updateProblemTicket(driver, prNumber,2,2);
    	      driver.close();
       } 
       @Test(priority=0,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testDifferentPhasesOfProblemTicket() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName2(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem(driver,1,2);
    	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
    	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
    	   ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
    	   ProblemReusables.closeProblemTask(driver, prNumber);
    	   ProblemReusables.closeProblemTicket(driver, prNumber);
    	   driver.close();
    	  // ProblemReusables.verifyProblemCreation(driver, prNumber);          
       } 
       @Test(priority=0,description="-----deffered state-----",enabled=true, groups="Problems")
       public void testDifferedState() throws Exception{
     	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "DifferedState", "DifferedState");
     	   SafeLogin.logInUser(driver);
     	   WaitUtils.waitForPageToLoad(driver, 10);
     	   ServiceNowUtils.navigateToModuleName2(driver, "Problem");
     	   prNumber=ProblemReusables.createProblem(driver,1,2);
     	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
     	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
     	   ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
     	  //State=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 8);
     	  DropDowns.selectDropdownByVisibleText(ProblemPage.getStateDropdown(driver),"Deferred" , "State");
     	  DropDowns.selectDropdownByVisibleText(ProblemPage.getStateCodeDropdown(driver),"Cost","State code");
     ProblemPage.getSaveBtn(driver).click();
     	  
     	   //ProblemReusables.closeProblemTask(driver, prNumber);
     	  // ProblemReusables.closeProblemTicket(driver, prNumber);
     	 //  driver.close();
     	  // ProblemReusables.verifyProblemCreation(driver, prNumber);          
        } 
       
       
      /* @Test(priority=1,description="-----Update Problem Test Case-----",enabled=false,dependsOnMethods={"testCreateProblemTicket"}, groups="Problems")
       public void testUpdateProblemTicket() throws Exception{
    	   ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Update Problem Ticket", "Update Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Problem");
    	   prNumber=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
    	   ProblemReusables.updateProblemTicket(driver, prNumber,2,2);
    	  }
       
       @Test(priority=2,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testDifferentPhasesOfProblemTicket() throws Exception{
    	   ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Different Phases and Status of Problem Ticket", "Different Phases and Status of Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Problem");
    	   prNumber=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 2);    	   
    	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
    	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
    	   ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
    	   ProblemReusables.closeProblemTask(driver, prNumber);
    	   ProblemReusables.closeProblemTicket(driver, prNumber);
    	   
    	  }
*/
}
