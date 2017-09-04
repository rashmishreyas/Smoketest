package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangePage {

	private static WebElement element;
	
    public static WebElement getNormalLnk(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[contains(text(),'Normal: Planned')]"));
        return element;
    }	
    
    public static WebElement getStandardChangeLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Standard: Pre-approved')]"));
    	return element;
    }
    
    public static WebElement getEmergencyChangeLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Emergency: Changes')]"));
    	return element;
    }
    
    public static WebElement getConfigurationItemEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.cmdb_ci"));
    	return element;
    }
    
    public static WebElement getAssignmentGrpEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.assignment_group"));
    	return element;
    }
    
    public static WebElement getShortDescriptionEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.short_description"));
    	return element;
    }
    
    public static WebElement getDescriptionEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.description"));
    	return element;
    }
    
    
    public static WebElement getPlanningTab(WebDriver driver){
    	element = driver.findElement(By.xpath(".//*[@id='tabs2_section']/span[2]/span/span[2]"));
    	return element;
    }
    
    public static WebElement getReasonForChangeEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_reason_for_change"));
    	return element;
    }
    
    public static WebElement getChangeNumberEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_readonly.change_request.number"));
    	return element;
    }
     
    public static WebElement getCustomerImpactDuringChangeEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_customer_impact"));
    	return element;
    } 
    
    public static WebElement getImplementationPlanEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.change_plan"));
    	return element;
    } 
    
    public static WebElement getTestPlanEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.test_plan"));
    	return element;
    } 
    
    public static WebElement getBackoutPlanEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.backout_plan"));
    	return element;
    } 
    
    public static WebElement getExpectedServiceImpactDuringTheExecutionofChangeDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_knowledge_of_impacted_config"));
    	return element;
    }
  
    public static WebElement getScheduleTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//*[@id='tabs2_section']/span[4]/span/span[2]"));
    	return element;
    }
    
    public static WebElement getRequestedByDateCalenderBtn(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[@id='change_request.requested_by_date.ui_policy_sensitive']/span[@class='icon icon-calendar']"));
    	return element;
    }
    
    public static WebElement getRequestedByMonthLabel(WebDriver driver){
    	element = driver.findElement(By.id("GwtDateTimePicker_month"));
    	return element;
    }
    
    public static WebElement getRequestedByDateEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.requested_by_date"));
    	return element;
    }
    
    
    public static WebElement getSearchDropDown(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@class='input-group']//select"));
    	return element;
    }
  
    public static WebElement getSearchChangeEdt(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
    	return element;
    }
    
    public static WebElement getcreatedChangeLnk(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+changeNumber+"']"));
    	return element;
    }
    
    public static WebElement getSubmitBtn(WebDriver driver){
    	element = driver.findElement(By.id("sysverb_insert"));
    	return element;
    }
    
    public static WebElement getChangeStatusFromQueue(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+changeNumber+"']/following::td"));
    	return element;
    }
  
    public static WebElement getChangeNumberFromQueue(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+changeNumber+"']"));
    	return element;
    }

    public static WebElement getSubmitForPlanningBtn(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[text()='Submit for Planning']"));
    	return element;
    }
    
    public static WebElement getChangeStateEdtDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.state"));
    	return element;
    }
    
    public static WebElement getChangeCancelBtn(WebDriver driver){
    	element = driver.findElement(By.id("cancel_change"));
    	return element;
    }
  
    public static WebElement getActivityTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[text()='Activity']"));
    	return element;
    }
    
    public static WebElement getReasonForCancellationEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_reason_for_cancellation"));
    	return element;
    }
    
}
