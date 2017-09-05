package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    
    public static WebElement getCreateNewBtn(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Create New']"));
       return element;
    }
  
    public static WebElement getAssignedToMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Assigned to my groups']"));
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
       element = driver.findElement(By.xpath("//a[text()='All']"));
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