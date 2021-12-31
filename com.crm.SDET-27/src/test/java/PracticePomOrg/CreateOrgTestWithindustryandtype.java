package PracticePomOrg;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateOrgTestWithindustryandtype {

	public static void main(String[] args) throws Throwable {
		/*create object to libraries*/
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		
		//read common data using Properties file
		String BROWSER = fLib.getPropertyKeyValue("Browser");
		String URL = fLib.getPropertyKeyValue("URL");
	    String USERNAME = fLib.getPropertyKeyValue("username");
	    String PASSWORD = fLib.getPropertyKeyValue("password");
	    
	    //get random number
	    int randomNum = jLib.getRanDomNumber();
	 
	    //get the test data from the excel sheet
	    String orgName = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;
	    String Industry = eLib.getDataFromExcel("Sheet1",1,4);
	    String Type = eLib.getDataFromExcel("Sheet1",1,5);
	    
	    // lunch browser
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
	    wLib.waitForPageToLoad(driver);
	    driver.manage().window().maximize();
        // login to application
	    driver.get(URL);
	    LoginPage lp= new LoginPage(driver);
	    lp.logIn(USERNAME, PASSWORD);
	    
	    // Navigate to organization page
	    HomePage hp= new HomePage(driver);
	    hp.clickOnOrganizationsLink();
	    //click on create organization page
	    OrganizationsPage op= new OrganizationsPage(driver);
	    op.clickOnCreateOrganization();
	    
	    //enter all the details and create new organization
        CreateOrganizationPage cop= new CreateOrganizationPage(driver);
	    cop.createOrgWithIndusAndType(orgName, Industry, Type);
	   
	    //verify organization name in header of the msg
	    OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	    String actual_msg = oip.getOrgInfo();
	    if(actual_msg.contains(orgName))
	   {
	   	System.out.println("org is succefully created==> pass");
	   	}
	   else
	   {
	   	System.out.println("org is not created==> fail");
	   	}
       String actual_industry = driver.findElement(By.id("mouseArea_Industry")).getText();
       if(actual_industry.equals(Industry))
       {
    	   System.out.println("industry name is present==> pass");  
       }
       else 
       {
    	   System.out.println("industry name not present==> fail");
       }
       String actual_type = driver.findElement(By.id("mouseArea_Type")).getText();
       if(actual_type.equals(Type))
       {
    	   System.out.println("type is present==> pass");  
       }
       else 
       {
    	   System.out.println("type not present==> fail");
       }
       hp.SignOut(driver);
       driver.close();

	}

}
