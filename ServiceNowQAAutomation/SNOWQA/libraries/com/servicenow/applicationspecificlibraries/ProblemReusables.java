package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;
import pages.HomePage;
import pages.IncidentPage;
import pages.ProblemPage;

public class ProblemReusables {
       

       static String problemId=null;
       static String problemState=null;
       static String source=null;
       static String impact=null;
       static String complexity=null;
       static String assignmentGroup = null;
       static String configurationItem = null;
       static String shortDescription = null;
       static String description = null;
       static String initialPriority=null;
       static String impactValue=null;
       static String complexityValue=null;
       static String updatedPriority=null;
       static String actualStateOfTicket=null;
       
       public static String createProblem(WebDriver driver, int sNum, int cellNum) throws Exception{
           
           try{
                 WaitUtils.waitForPageToLoad(driver, 30);
                 source=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 5);
                 assignmentGroup=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 6);
                 configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 7);
                 shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 10);
                 description=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 11);
                  
                 WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
                 problemId=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
                 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
                 ExtentReport.reportLog(LogStatus.INFO, "Problem number captured: "+problemId);
                 ReporterLogs.log("Problem number captured: "+problemId, "info");     
                 WaitUtils.waitForIdPresent(driver, "problem.contact_type");
                 DropDowns.selectDropdownByVisibleText(ProblemPage.getSourceDropdown(driver), source, "Source");
                 //Thread.sleep(3000);                                 
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.assignment_group");
                 TextBoxes.enterTextValue(ProblemPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");                    
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                 Thread.sleep(5000);
                 ReporterLogs.log("Entering Assignment group field value "+assignmentGroup, "info");                 
                 TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
                 Thread.sleep(2000);
                 TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
                 Thread.sleep(2000);
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
                 TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
                 ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
                 ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
                 Thread.sleep(5000);
                 ProblemPage.getSubmitBtn(driver).click();
                 try {
                	 Alert alert = driver.switchTo().alert();
                	 String alertText = alert.getText();
                	 ReporterLogs.log("Alert message: " + alertText, "error");
                	 ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
                	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
                	 Assert.fail("Unexpected alert !!!! ");
                    } 
                 catch (NoAlertPresentException e) {
                	 e.printStackTrace();
                 }
           	}
          catch (UnhandledAlertException f) {
             try {
                   Alert alert = driver.switchTo().alert();
                   String alertText = alert.getText();
                   System.out.println("Alert data: " + alertText);
                   Assert.fail("Unhandled alert");
                  } 
               catch (NoAlertPresentException e) {
                   e.printStackTrace();
               }  
           }
                  return problemId;                     
       }


       public static void verifyProblemCreation(WebDriver driver, String problemId) throws Exception
		{
				WaitUtils.waitForPageToLoad(driver, 30);
				WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
		        if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
		        {
		           WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			       DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        
		        ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
		        problemState=ProblemPage.getProblemStatusfromQueue(driver, problemId).getText();
		        ReporterLogs.log("Current state of the problem ticket "+problemId+" is "+problemState, "info");
		        ExtentReport.reportLog(LogStatus.INFO, "Current state of the problem ticket "+problemId+" is "+problemState);
	            
	            if(problemState.equalsIgnoreCase("New"))
	            {  
	               Assert.assertEquals(problemState, "New");
	         	   ReporterLogs.log("Successfully created problem ticket with Id "+problemId, "info");
	         	   ExtentReport.reportLog(LogStatus.PASS, "Successfully created problem ticket with Id "+problemId);
	         	   ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
	         	   ExtentReport.reportLog(LogStatus.INFO, "Status of the problem id "+ problemId +" is "+ problemState);
	         	   ReporterLogs.log("Status of the problem id "+ problemId +" is "+ problemState, "info");
	         }else{        	   	
	         	   ReporterLogs.log("Problem ticket status is not new. Actual status is "+ problemState, "error");
	         	   ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a problem ticket with Status as New");
	         	   ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
	         	   ExtentReport.reportLog(LogStatus.INFO, "Status of the problem id "+ problemId +" is "+ problemState);
	         	   ReporterLogs.log("Status of the problem id "+ problemId +" is "+ problemState, "info");
	         	   Assert.assertEquals(problemState, "New");
	         	 }
		  	}    
       
       public static void searchProblemTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
   			try{
   				   Thread.sleep(2000);
   				   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
   				   TextBoxes.enterTextValue(ProblemPage.getSearchProblemEdt(driver), ticketNumber, "Search Ticket");
   				   ProblemPage.getSearchProblemEdt(driver).sendKeys(Keys.ENTER);
   				}
   			catch (Exception e) {
   				   ReporterLogs.log(e.getMessage(), "info");
   				}
   			}
       
       public static void clickProblemTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
      		try{
      			   Thread.sleep(2000);
      			   ProblemPage.getProblemTicketfromQueue(driver, ticketNumber).click();      		
      		   }
      		catch (Exception e) {
      			   ReporterLogs.log(e.getMessage(), "info");
      			}
      		}
       
       public static void verifyStateOfProblemTicket(WebDriver driver, String expectedStateOfTicket,String prNum,int sNum, int cellNum) {
   			try{
   					actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ProblemPage.getProblemStateEdtDropDown(driver), "State Drop Down");
   					ReporterLogs.log("State of the Problem is :"+actualStateOfTicket);
   					if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket))
   				    {
   						Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
   						ExtentReport.reportLog(LogStatus.PASS, "Successfully updated Problem "+prNum+" with state as "+actualStateOfTicket);
   						ReporterLogs.log("Successfully updated Problem "+prNum+" with state as "+actualStateOfTicket, "info");
   						ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, cellNum, prNum);
   						ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 3, actualStateOfTicket);
   						ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 4, "Passed");
   				    }else{
   				    	ExtentReport.reportLog(LogStatus.FAIL, "State of the Problem ticket is not : "+actualStateOfTicket);
   				    	ReporterLogs.log("Unable to update Problem "+prNum+" with state as "+expectedStateOfTicket, "error");
   				    	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, cellNum, prNum);
   				    	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 3, actualStateOfTicket);
   				    	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 4, "Failed");
   				    	Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
   				    }
   			}catch(Exception e)
   			{
   				ReporterLogs.log("Exception :"+e.getMessage(),"error");
   			}
   		}
       
       
       public static void updateProblemTicket(WebDriver driver, String problemId, int sNum, int cellNum) throws Exception
		{
		   		impact=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 8);
		   		complexity=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 9);
		   		WaitUtils.waitForPageToLoad(driver, 30);
		   		WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
		   		if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
		   		{
		   			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
		   			DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		   		}
		   		ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
		   		ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
		   		initialPriority=ProblemPage.getProblemPriorityfromQueue(driver, problemId).getText();
		   		ReporterLogs.log("Initial Priority value for problem ticket "+problemId+ " is "+ initialPriority, "info");
		   		ExtentReport.reportLog(LogStatus.INFO, "Initial Priority value for problem ticket "+problemId+ " is "+ initialPriority);
		   		ProblemReusables.clickProblemTicketFromQueue(driver, problemId);
		   		WaitUtils.waitForTitleIs(driver, problemId+" | ServiceNow");
		   		DropDowns.selectDropdownByVisibleText(ProblemPage.getImpactDropdown(driver), impact, "Impact");
		   		DropDowns.selectDropdownByVisibleText(ProblemPage.getComplexityDropdown(driver), complexity, "Complexity");
		   		//Thread.sleep(3000);
		   		ProblemPage.getUpdateBtn(driver).click();
	        try {
           	 		Alert alert = driver.switchTo().alert();
           	 		String alertText = alert.getText();
           	 		ReporterLogs.log("Alert message: " + alertText, "error");
           	 		ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
           	 		ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
           	 		Assert.fail("Unexpected alert !!!! ");
               	} 
            catch (NoAlertPresentException e) 
            {
            	e.printStackTrace();
            }	
	           	WaitUtils.waitForPageToLoad(driver, 30);
            	WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
            	updatedPriority=ProblemPage.getProblemPriorityfromQueue(driver, problemId).getText();
            	ReporterLogs.log("Priority value for problem ticket "+problemId+ " after updating is "+ updatedPriority, "info");
            	ExtentReport.reportLog(LogStatus.INFO, "Priority value for problem ticket "+ problemId +" after updating is "+ updatedPriority);
            	if (!updatedPriority.equalsIgnoreCase(initialPriority)) {
				 	Assert.assertTrue(!updatedPriority.equalsIgnoreCase(initialPriority), "Initial Priority and Updated priority are same, Problem ticket has not been updated");
					ReporterLogs.log("Problem ticket "+problemId+ " has been updated.", "pass");
					ReporterLogs.log("Priority of the ticket has been updated from "+initialPriority+ " to "+updatedPriority, "pass");
					ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
					ExtentReport.reportLog(LogStatus.PASS, "Problem ticket "+problemId+ " has been updated. Priority of the ticket has been updated from "+initialPriority+ " to "+updatedPriority );
				} else {
					ReporterLogs.log("Problem ticket "+problemId+ " has not been updated.", "error");
					ReporterLogs.log("Priority of the ticket has not been updated from "+initialPriority+ " to "+updatedPriority, "error");
					ExtentReport.reportLog(LogStatus.FAIL, "Problem ticket "+problemId+ " has not been updated. Priority of the ticket has not been updated from "+initialPriority+ " to "+updatedPriority );
					ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
					Assert.fail("Initial Priority and Updated priority are same, Problem ticket has not been updated");
				}
			}
       
       public static void moveProblemTicketToAcceptedPhase(WebDriver driver, String problemId) throws Exception{
     		try{
     				WaitUtils.waitForPageToLoad(driver, 30);
     				WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
     				if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
     				{
     					WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
     					DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
     				}   
     				ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
     				ProblemReusables.clickProblemTicketFromQueue(driver, problemId);    
     				Frames.switchToDefaultContent(driver);
                    String loggedinUser = HomePage.getLoggedInUserInfo(driver).getText();
                    System.out.println("Logged in user :"+loggedinUser); 
                    Thread.sleep(3000);
                    Frames.switchToFrameById("gsft_main", driver);
                   
                    WaitUtils.waitForPageToLoad(driver, 20);
                    WaitUtils.waitForTitleIs(driver, problemId+ " | ServiceNow");
                    Thread.sleep(3000);
                    ProblemPage.getProblemApproversTab(driver).click();
                    ReporterLogs.log("Clicking on Approvers tab", "info");
                    Thread.sleep(3000);
                    ProblemPage.getProblemPendingApproverLnk(driver, loggedinUser).click();
                    ReporterLogs.log("Clicking on the 'Requested' link for the user "+ loggedinUser, "info");
                    //Thread.sleep(3000);
                    WaitUtils.waitForPageToLoad(driver, 20);
                    WaitUtils.waitForTitleIs(driver, "Approval | ServiceNow");
                    ProblemPage.getProblemApproveBtn(driver).click();
                    ReporterLogs.log("Approving the problem ticket "+ problemId, "info");
                    WaitUtils.waitForPageToLoad(driver, 20);
                    WaitUtils.waitForTitleIs(driver, problemId+ " | ServiceNow");
                    ProblemReusables.verifyStateOfProblemTicket(driver, "Accepted", problemId, 3, 2);
                  }
     		catch (Exception e) {
     			   ReporterLogs.log(e.getMessage(), "info");
     			}
     		}
       
       
}
