package Practicepom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException, Throwable {
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
    
    //read test data from the excel sheet
 String orgName = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;
 
 WebDriver driver;
if(BROWSER.equals("firefox")) {
	driver =new FirefoxDriver();
}else if(BROWSER.equals("chrome"))
{
	driver =new ChromeDriver();
	}
else {
	driver = new ChromeDriver();
}
driver.manage().window().maximize();
//login
wLib.waitForPageToLoad(driver);
driver.get(URL);
/* login to APP */
LoginPage ip= new LoginPage(driver);
ip.logIn(USERNAME, PASSWORD);

/* Navigate to create organization page */
HomePage hp = new HomePage(driver);
hp.clickOnOrganizationsLink();

/* click on create organization button */ 
OrganizationsPage op= new OrganizationsPage(driver);
op.clickOnCreateOrganization();

/*enter all the details and create new organization */
CreateOrganizationPage cop =  new CreateOrganizationPage(driver);
cop.createorg(orgName);

//verify organization name in header of msg
OrganizationInfoPage oip= new OrganizationInfoPage(driver);
String actual_msg = oip.getOrgInfo();
 if(actual_msg.contains(orgName))
{
	System.out.println("org is actually created");
	}
else
{
	System.out.println("org is not created");
	}

//logout
 hp.SignOut(driver);
driver.close();




	}
   

}
