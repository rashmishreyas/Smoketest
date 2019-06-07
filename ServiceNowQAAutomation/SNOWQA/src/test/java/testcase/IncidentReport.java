package testcase;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
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
import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.IncidentReusables;
import com.servicenow.applicationspecificlibraries.IntakeReuasbles;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.CaptureScreenShot;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;
import pages.IncidentPage;
public class IncidentReport {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	static String incNumber=null;
	static String errormessage=null;
	static String incidenttasknum=null;
	static WebElement element = null;
	static String Assignmentgroup1 = null;
	static String modulename=null;
	@BeforeTest
	public void startReport(){
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport11.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Rashmi");
		htmlReporter.config().setDocumentTitle("Incident Requuest");
		htmlReporter.config().setReportName("Incident Request Smoke suite");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	@Test(priority=0,description="Create standalone incident ticket",enabled=true)
	
	public void testCreateStandAloneIncidentTicket() throws Exception{
		
		logger = extent.createTest("Create standalone incident ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		
		WaitUtils.waitForPageToLoad(driver, 10);			
		ServiceNowUtils.navigateToModuleName(driver,"incident");

		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a INC Ticket and searching of updated ticket", ExtentColor.GREEN));
}
	@Test(priority=1,description="Create managed incident ticket",enabled=true)
	
	public void testCreateManagedIncidentTicket() throws Exception{
		
		logger = extent.createTest("Create managed incident ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
				
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createmanagedIncident(driver,2,2);
		IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a managed INC Ticket", ExtentColor.GREEN));
	}
	@Test(priority=2,description="Validate mandatory fields in WIP state",enabled=true)
	public void testverifymandatoryfieldWIP() throws Exception{
		logger = extent.createTest("Validate mandatory fields in WIP stat");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
				
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.Captureerrormessagenew1(driver, errormessage);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is verification of mandatory field in WIP state", ExtentColor.GREEN));
	}
	
	@Test(priority=3,description="Creation of Incident from Intake",enabled=true)
	public void testcreateIncidentfromIntake() throws Exception {
		logger = extent.createTest("Creation of Incident from Intake");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName1(driver,"intake");
		incNumber=IntakeReuasbles.createIncidentfromIntake(driver, 1, 2);
		//ServiceNowUtils.navigateToModuleName1(driver,"incident");	
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of Incident from Intake", ExtentColor.GREEN));
}
	@Test(priority=4,description="Progression of Incident ticket to Resolved state",enabled=true)
	public void testResolveIncidentTicket() throws Exception{
		logger = extent.createTest("Progression of Incident ticket to Resolved state");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		IncidentReusables.resolveIncident(driver);
		//IncidentReusables.verifyStateOfIncidentTicket(driver, "Resolved", incNumber, 1, 2);
		//IncidentReusables.FinalReport(driver, "Resolved", incNumber, 4, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Resolving incident ticket", ExtentColor.GREEN));
}
	@Test(priority=5,description="Cancelling Incident Ticket", enabled=true)
	public void testCancelIncidentTicket() throws Exception{
		logger = extent.createTest("Cancelling Incident Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 4, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		
		IncidentReusables.cancelIncident(driver, incNumber);	
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is cancelling incident ticket", ExtentColor.GREEN));
	}
	@Test(priority=6,description="Create standard change from Incident ",enabled=true)
	public void testCreateStandardChangefromInc() throws Exception{
		logger = extent.createTest("Create standard change from Incident");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		IncidentReusables.createstandardchangefromincident(driver, false, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of standardchange from incident ticket", ExtentColor.GREEN));

	}
	@Test(priority=7,description="Createproblemfromincident",enabled=true)
	public void testcreateProblemfromIncident() throws Exception
	{
		logger = extent.createTest("Createproblemfromincident");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createproblemfromincident(driver, false, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of problem from incident ticket", ExtentColor.GREEN));


	}
	@Test(priority=8,description="Create incident task",enabled=true)
	public void testcreateincidenttask() throws Exception{
		logger = extent.createTest("Create incident task");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		
		incidenttasknum = IncidentReusables.CreateIncidentTask(driver, incNumber);
		Thread.sleep(2000);
		driver.navigate().refresh();
		
		Thread.sleep(2000);
		
		ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
		IncidentReusables.verifyIncidentCreation1(driver, incNumber);
		IncidentReusables.MovetasktoWIP(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of incident task", ExtentColor.GREEN));

		
	}
	@Test(priority=9,description="Create normal change from Incident ",enabled=true)
	public void testCreateNormalChangefromInc() throws Exception{
		logger = extent.createTest("Create normal change from Incident ");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		IncidentReusables.createnormalchangefromincident(driver, false, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of normal change from incident", ExtentColor.GREEN));


	}
	@Test(priority=10,description="Create emergency change from Incident ",enabled=true)
	public void testCreateEmergencyChangefromInc() throws Exception{
		logger = extent.createTest("Create emergency change from Incident ");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		IncidentReusables.createemergencychangefromincident(driver, false, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of emergency change from incident", ExtentColor.GREEN));


	}
	@Test(priority=11,description="Create Child incident from Incident ",enabled=true)
	public void testCreateChildIncfromInc() throws Exception{
		logger = extent.createTest("Create Child incident from Incident ");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		IncidentReusables.creatchildincidentfromincident(driver, false, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of child incident", ExtentColor.GREEN));


	}
	@Test(priority=12,description="Movetasktoclosecomplete",enabled=true)
	public void testmoveIncidentTasktoclosecomplete() throws Exception
	{
		logger = extent.createTest("Movetasktoclosecomplete");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);

	IncidentReusables.CreateIncidentTask(driver, incNumber);
	Thread.sleep(2000);
	driver.navigate().refresh();

	Thread.sleep(2000);

	ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
	IncidentReusables.verifyIncidentCreation1(driver, incNumber);


	IncidentReusables.Movetasktclosecomplete(driver, incNumber);
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is close complete of incident task", ExtentColor.GREEN));


	}
	@Test(priority=13,description="Movetasktocancel",enabled=true)
	public void testmoveIncidentTasktocancel() throws Exception
	{
		logger = extent.createTest("Movetasktocancel");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);

	IncidentReusables.CreateIncidentTask(driver, incNumber);
	Thread.sleep(2000);
	driver.navigate().refresh();

	Thread.sleep(2000);

	ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
	IncidentReusables.verifyIncidentCreation1(driver, incNumber);


	IncidentReusables.Movetasktocancel(driver, incNumber);
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is cancellation incident task", ExtentColor.GREEN));


	}
	@Test(priority=14,description="Create incident alert",enabled=true)
	public void testCreateincidentalert() throws Exception{
		
		logger = extent.createTest("Create incident alert");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);		
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createmanagedIncident(driver,2,2);
		IncidentReusables.verifymanagedIncidentCreation(driver, incNumber);
		IncidentReusables.CreateincidentAlert(driver);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of incident alert", ExtentColor.GREEN));
	}
	@Test(priority=15,description="CreateIncforuserimpact3",enabled=true)
	public void testcreateINCforuserimpact3() throws Exception
	{
		logger = extent.createTest("CreateIncforuserimpact3");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.CreateINCforuserimpact3(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of incident for user impact 3", ExtentColor.GREEN));
	}
	@Test(priority=16,description="CreateIncforuserimpact1",enabled=true)
	public void testcreateINCforuserimpact1() throws Exception
	{
		logger = extent.createTest("CreateIncforuserimpact1");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.CreateINCforuserimpact1(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of incident for user impact 1", ExtentColor.GREEN));

	}
	@Test(priority=17,description="CreateIncforuserimpact2",enabled=true)
	public void testcreateINCforuserimpact2() throws Exception
	{
		logger = extent.createTest("CreateIncforuserimpact2");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.CreateINCforuserimpact2(driver, false, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is creation of incident for user impact 2", ExtentColor.GREEN));


	}
	@Test(priority=18,description="Reassign IM ",groups="Incidents",enabled=true)
	public void testreassignim() throws Exception{
		logger = extent.createTest("Reassign IM");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
		
		IncidentReusables.ReassignIM(driver, incNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is reassigning Incident ", ExtentColor.GREEN));

	}
	@Test(priority=19,description="Copy Incident ",enabled=true)
	public void testcopyincident() throws Exception{
		logger = extent.createTest("Copy Incident");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		IncidentReusables.Copyincident(driver, false, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is copying Incident ", ExtentColor.GREEN));



	}
	@Test(priority=20,description="Active IM task ",enabled=true)
	public void testverifyINCforActiveIMtask() throws Exception
	{
		logger = extent.createTest("Active IM task");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver,"incident");
		incNumber=IncidentReusables.createstandaloneincident1(driver, 1, 2);
		IncidentReusables.verifyIncidentCreation(driver, incNumber);

	IncidentReusables.CreateIncidentTask(driver, incNumber);
	Thread.sleep(2000);
	driver.navigate().refresh();
	Thread.sleep(2000);
	ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "incident");
	IncidentReusables.verifyIncidentCreation1(driver, incNumber);
	IncidentReusables.cancelIncident1(driver, incNumber);
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is verifying active incident task ", ExtentColor.GREEN));
	}
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		WebDriver driver = null;
		//String screenShot = CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName(result));
		if(result.getStatus() == ITestResult.FAILURE){
			
			//logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//logger.fail("Screen Shot : " + logger.addScreenCaptureFromPath(screenShot));
			
		}else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}
	}
@AfterTest
public void endReport(){
	extent.flush();
}
@AfterSuite
public void afterSuite() {
	 final String username = "rp00506732@techmahindra.com";
        final String password ="Megha@123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rp00506732@techmahindra.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("ax00506739@techmahindra.com"));
            message.setSubject("Test");
            message.setText("HI");

            //Transport.send(message);

          //  System.out.println("Done");
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file = "C:\\Users\\UX011974\\Desktop\\ServiceNowQAAutomation\\ServiceNowQAAutomation\\SNOWQA\\test-output\\STMExtentReport11.html";
            String fileName = "STMExtentReport11";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    
}


}
