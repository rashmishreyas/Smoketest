package pages;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.applicationspecificlibraries.WaitUtils;

public class HomePage {

	private static WebElement element;
	
	public static WebElement gettrLogo(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@id='home_title"));
        return element;
    }  
	
    public static WebElement getUserNameDropDown(WebDriver driver){
       element = driver.findElement(By.xpath("//button[@id='user_info_dropdown']"));
       return element;
    }
    
    public static WebElement getfilterEdt(WebDriver driver){
       element = driver.findElement(By.id("filter"));
       return element;
    }
    //incident creation
    public static WebElement getCreateNewBtn(WebDriver driver){
    // element = driver.findElement(By.xpath("//*[contains(text(),'Create New')]"));
     // problem element = driver.findElement(By.xpath("//*[@id='a1beba50c611227801908558c921ab78']//parent::div[1]"));
    // element = driver.findElement(By.id("0927ed160c1425405ac65c2573079ad0"));
    	//element = driver.findElement(By.cssSelector("#a1beba50c611227801908558c921ab78 > div:nth-child(1) > div:nth-child(1)"));

    	element = driver.findElement(By.xpath("//*[@id='14641d70c611228501114133b3cc88a1']//parent::div[1]"));
    	//element = driver.findElement(By.id("14641d70c611228501114133b3cc88a1"));
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
    	//js.executeScript("arguments[0].scrollIntoView();", element);
    	//WebDriverWait wait = new WebDriverWait(driver, 10);
    	
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
       
    }
    //incident from intake creation
    public static WebElement getCreatenewBtn1(WebDriver driver)
    {
    	element = driver.findElement(By.xpath("//*[@id='0927ed160c1425405ac65c2573079ad0']//parent::div[1]"));
    			WebDriverWait wait = new WebDriverWait(driver, 10);
    	    	wait.until(ExpectedConditions.visibilityOf(element));
    	    	wait.until(ExpectedConditions.elementToBeClickable(element));
    	       return element;
    }
    
    
  
    public static WebElement getAssignedToMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Assigned to my groups']"));
       return element;
    }
    public static WebElement getincidenttaskalllnk1(WebDriver driver) 
	{
		element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[15]/ul/li[2]/div/ul/li[3]/div/a/div/div"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
		
		return element;
	}
    public static WebElement getOpenedByMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Opened by my groups']"));
       return element;
    }
    
    public static WebElement getAssignedToMeLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Assigned to me']"));
       return element;
    }
    
    public static WebElement getOpenedByMeLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Opened by me']"));
       return element;
    }
    
    public static WebElement getAllLnk(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='b55b4ab0c0a80009007a9c0f03fb4da9']//parent::div[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    
        
        return element;
     }
    
    public static WebElement getOverviewLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Overview']"));
       return element;
    }
    
    public static WebElement getIncidentTasksAssignedToMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Incident Tasks']//following-sibling::ul//a[text()='Assigned to my groups']"));
       return element;
    }
    
    public static WebElement getIncidentTasksAssignedToMeLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Incident Tasks']//following-sibling::ul//a[text()='Assigned to me']"));
       return element;
    }
    
    public static WebElement getIncidentTasksAllLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Incident Tasks']//following-sibling::ul//a[text()='All']"));
       return element;
    }
    
    public static WebElement getOpenLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Open']"));
       return element;
    }
    
    public static WebElement getClosedLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Closed']"));
       return element;
    }    
    
    public static WebElement getLogoutBtn(WebDriver driver){
       element = driver.findElement(By.xpath("//button[@id='user_info_dropdown']/following::a[text()='Logout']"));
       return element;
    }
  
    public static WebElement getLoggedInUserInfo(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[@id='user_info_dropdown']//span[2]"));
    	return element;
    }
   
    public static WebElement getChangesAssignedToMeQueue(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Changes Assigned')]"));
    	return element;
    }

}