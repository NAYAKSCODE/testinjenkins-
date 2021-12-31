package Practicepom;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
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

import com.crm.autodesk.ObjectRepository.ContactInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactsTest {

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
	    
	   //get the test data from excel sheet
       String lastname = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;
 
        WebDriver driver;
        if(BROWSER.equals("firefox"))
        {
	     driver = new FirefoxDriver();
        }
        else if(BROWSER.equals("chrome"))
        {
	     driver = new ChromeDriver();
        }
       else
       {
	    driver = new ChromeDriver();
       }
        wLib.waitForPageToLoad(driver);
        driver.manage().window().maximize();
       //login
        driver.get(URL);
      LoginPage lp =new LoginPage(driver);
        lp.logIn(USERNAME, PASSWORD);
 
      // navigate to contacts page
       HomePage hp= new HomePage(driver);
       hp.clickOnContactsLink();
       //click on create contact lookup
       ContactsPage cp= new ContactsPage(driver);
       cp.clickOnCreatecontact();
       //enter all the details and create new contacts
       CreateContactPage cop= new CreateContactPage(driver);
       cop.createContact(lastname);
     //verify contacts 
       ContactInfoPage cip =new ContactInfoPage(driver);
       String actual_msg = cip.getContactInfo();
       if(actual_msg.contains(lastname))
      {
	  System.out.println("pass--contact is created");
      }
      else
      {
	  System.out.println("fail--contact not created");
      }
  
  //logout
  hp.SignOut(driver);
  driver.quit();
	}

}
