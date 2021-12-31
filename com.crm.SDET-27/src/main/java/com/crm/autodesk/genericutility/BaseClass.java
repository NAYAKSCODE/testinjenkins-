package com.crm.autodesk.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;

public class BaseClass {
public	DataBaseUtility dLib=new DataBaseUtility();
public	ExcelUtility eLib=new ExcelUtility();
public	FileUtility fLib=new FileUtility();
public	WebDriverUtility wLib= new WebDriverUtility();
public	JavaUtility jLib=new JavaUtility();
public	WebDriver driver;

@BeforeSuite(groups= {"smokeTest","regressionTest"})
public void dbConnection()
{
	System.out.println("line 1");
	dLib.connectedToDB();
	System.out.println("Database connection succssful ");
	}
@BeforeClass(groups= {"smokeTest","regressionTest"})
public void lunchBrowser() throws Throwable
{
	//read the data
	String BROWSER = fLib.getPropertyKeyValue("Browser");
	String URL = fLib.getPropertyKeyValue("URL");
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		driver=new ChromeDriver();
	}
	else if(BROWSER.equalsIgnoreCase("firefox"))
	{
		driver=new FirefoxDriver();
	}
	else
	{
		System.out.println("Invalid Browser");
	}
    wLib.maximizeBrowser(driver);
	wLib.waitForPageToLoad(driver);
	driver.get(URL);
	System.out.println("Browser lunched");
}
@BeforeMethod(groups= {"smokeTest","regressionTest"})
public void loginToApp() throws Throwable
{
	//read the data
	String USERNAME = fLib.getPropertyKeyValue("username");
	String PASSWORD = fLib.getPropertyKeyValue("password");
	LoginPage lp= new LoginPage(driver);
	lp.logIn(USERNAME, PASSWORD);
	System.out.println("sucessfully login");
}
@AfterMethod(groups= {"smokeTest","regressionTest"})
public void logoutFromApp()
{
	HomePage hp= new HomePage(driver);
	hp.SignOut(driver);
	System.out.println("sucessfully logout");
}
@AfterClass(groups= {"smokeTest","regressionTest"})
public void closeBrowser()
{
	driver.quit();
	System.out.println("Browser closed");
}
@AfterSuite(groups= {"smokeTest","regressionTest"})
public void closeDBConnection()
{
	dLib.closeDb();
	System.out.println("Database connection closed");
}
}