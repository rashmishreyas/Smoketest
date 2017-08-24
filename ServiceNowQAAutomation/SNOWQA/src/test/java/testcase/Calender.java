package testcase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Calender {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "C:\\SnowQA\\SNOWQA\\properties\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://C:\\Users\\u6035997\\Desktop\\MyPage.html");
		System.out.println((driver.findElement(By.id("myList")).getAttribute("value")));
		
		
		

	}

}
