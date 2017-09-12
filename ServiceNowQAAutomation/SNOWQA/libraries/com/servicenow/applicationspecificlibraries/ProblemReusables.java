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
                    //Thread.sleep(2000);
                    
                    WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci");
                    TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
                    Thread.sleep(4000);
                    ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
                    ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
                    
                    WaitUtils.waitForIdPresent(driver, "sys_display.problem.assignment_group");
                    TextBoxes.enterTextValue(ProblemPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");                    
                    ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                    ReporterLogs.log("Entering Assignment group field value "+assignmentGroup, "info");
                                        
                    WaitUtils.waitForIdPresent(driver, "problem.short_description");
                    TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
                    
                    WaitUtils.waitForIdPresent(driver, "problem.description");
                    TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
                    Thread.sleep(3000);
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
            {  Assert.assertEquals(problemState, "New");
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
       
       public static void verifyStateOfProblemTicket(WebDriver driver, String expectedStateOfTicket,String crNum,int reqRow, int reqcol) {
   		try{
   				String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ProblemPage.getProblemStateEdtDropDown(driver), "State Drop Down");
   				ReporterLogs.log("State of the Change is :"+actualStateOfTicket);
   				if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
   					Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
   					ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Change Ticket : "+actualStateOfTicket);
   					ReporterLogs.log("Successfully updated Change with Id "+crNum, "info");
   					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
   					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
   					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
   				}else{
   					ExtentReport.reportLog(LogStatus.FAIL, "State of the Change ticket are not same : "+actualStateOfTicket);
   					ReporterLogs.log("Unable to update Change with Id "+crNum, "error");
   					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
   					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
   					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
   					Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
   	}
   		}catch(Exception e){
   			ReporterLogs.log("Exception :"+e.getMessage(),"error");
   		}
   	}
       
}
