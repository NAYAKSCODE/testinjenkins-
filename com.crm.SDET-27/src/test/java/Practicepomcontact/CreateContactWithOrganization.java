package Practicepomcontact;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactWithOrganization {

	public static void main(String[] args) throws Throwable {
		/*create object to libraries*/
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
      
		//read common data from Properties file
		String BROWSER = fLib.getPropertyKeyValue("Browser");
		String URL = fLib.getPropertyKeyValue("URL");
	    String USERNAME = fLib.getPropertyKeyValue("username");
	    String PASSWORD = fLib.getPropertyKeyValue("password");
	    //get random data
	    int randomNum = jLib.getRanDomNumber();
		int randomNum1 = jLib.getRanDomNumber();
		
		// get the test data excel sheet 
		
		String orgName = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;
		String lastName = eLib.getDataFromExcel("Sheet1",1,3)+randomNum1;
        //lunch the browser
		WebDriver driver;
		if(BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("chrome"))
		{
			driver =new ChromeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		wLib.maximizeBrowser(driver);
		wLib.waitForPageToLoad(driver);
		//login
		driver.get(URL);
	    LoginPage lp= new LoginPage(driver);
	    lp.logIn(USERNAME, PASSWORD);

	  //Navigate to organization module 
	    HomePage hp= new HomePage(driver);
	    hp.clickOnOrganizationsLink();
	    

       /* click on create organization button */ 
        OrganizationsPage cp= new OrganizationsPage(driver);
        cp.clickOnCreateOrganization();

      /*enter all the details and create new organization */
        CreateOrganizationPage cop =  new CreateOrganizationPage(driver);
        cop.createorg(orgName);
	   
	    /*wait for element to active 
	    wLib.waitForElementToBeClickAble(driver,);
	    */
	    //Navigate to contact module
	     driver.findElement(By.linkText("Contacts")).click();
	    //click on cretae contact button
	    driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	
	    driver.findElement(By.name("lastname")).sendKeys(lastName);
	    driver.findElement(By.xpath("//input[@name='account_name']/following-sibiling::img")).click();
	    Set<String> set = driver.getWindowHandles();
	    Iterator<String> it = set.iterator();
	    
	    while(it.hasNext())
	    {
	    	String currentid = it.next();
	    	driver.switchTo().window(currentid);
	    	String currentpagetitle = driver.getTitle();
	    	if(currentpagetitle.contains("Accounts"))
	    		
	    	{
	    		break;
	    	}
	    }
	driver.findElement(By.name("search_text")).sendKeys("orgName");
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("//a[.='"+orgName+"'")).click();
	
	Set<String> set1 = driver.getWindowHandles();
	Iterator<String> it1 = set1.iterator();
	while(it1.hasNext())
	{
		String currentid = it1.next();
		driver.switchTo().window(currentid);
		String cpagetitle = driver.getTitle();
		if(cpagetitle.contains("Contacts"))
		{
			break;
		}
	}
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	System.out.println(orgName+"==Expected organozation name");
	String actual_orgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	System.out.println(actual_orgName+"==actual organization name");
	//verify the conformation msg
	if(actual_orgName.trim().equals(orgName.trim()))
	{
		System.out.println(actual_orgName+"is visible test pass");
	}
	else
	{
		System.out.println(actual_orgName+"is not visible test fail");
	}
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
	
	//logout
	 hp.SignOut(driver);
	  driver.quit();
	

		}

	}


