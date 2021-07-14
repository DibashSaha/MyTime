package com.mytime.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mytime.pages.AppointmentDetailsPage;
import com.mytime.pages.CommonPage;
import com.mytime.pages.FilterPage;
import com.mytime.pages.HomePage;
import com.mytime.pages.SearchResultsPage;

public class BaseTest {
	
	public WebDriver driver;
	public HomePage homePageObj;
	public SearchResultsPage serachPageObj;
	public CommonPage commonPageObj;
	public FilterPage filterPageObj;
	public AppointmentDetailsPage appointmentDetailsPageObj;
	
	
	public FileInputStream fis = null;
    public Properties prop = null;
	public String env="staging";
	public static String serviceName;
	public static String location;
	public static String businessName;
	
	
	//builds a new report using the html template 
    public ExtentHtmlReporter htmlReporter;
    
    public ExtentReports extent;
    
    //helps to generate the logs in test report.
    public ExtentTest test;
	
   
	
	
	//@Parameters("browser")
	@BeforeTest
	public void setup(){
		String brows="chrome";
		String os="mac";
		//======================PROPERTY FILE READING=====================
		String fileName="";
		try {
			fis = new FileInputStream(System.getProperty("user.dir") +"/src/test/resources/"+env+"/testData.properties");
			 prop = new Properties();
		        try {
					prop.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		//======================REPORTS=====================
		
		 //Date object
		 Date date= new Date();
	         //getTime() returns current time in milliseconds
		 long time = date.getTime();
	         //Passed the milliseconds to constructor of Timestamp class 
		 Timestamp ts = new Timestamp(time);
		
		
		// initialize the HtmlReporter
		 System.out.println(System.getProperty("user.dir"));
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/testReport.html");
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
      
    	
        //======================BROWSER LAUNCH=====================
        if(os.equals("mac")) {
        	driver=new ChromeDriver();
        }else {
        	if(brows.equals("chrome")){
    			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver_new.exe");
    			driver=new ChromeDriver();
    		}else if(brows.equals("firefox")){
    			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
    			driver=new FirefoxDriver();
    		}else if(brows.equals("ie")){
    			System.setProperty("webdriver.ie.driver", "drivers\\IEDriver.exe");
    			driver=new InternetExplorerDriver();
    		}
    	
        }
	
		
		String url=prop.getProperty("URL");
		serviceName=prop.getProperty("SERVICENAME");
		location=prop.getProperty("LOCATION");
		businessName=prop.getProperty("NAME");
		
		//driver.get("https://www.mytime.com/customers");
		driver.get(url);
		driver.manage().window().maximize();
		
		//======================OBJECT CREATION=====================
		homePageObj=new HomePage(driver);
		serachPageObj=new SearchResultsPage(driver);
		commonPageObj=new CommonPage(driver);
		filterPageObj=new FilterPage(driver);
		appointmentDetailsPageObj=new AppointmentDetailsPage(driver);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		extent.flush();
	}
	
	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

}
