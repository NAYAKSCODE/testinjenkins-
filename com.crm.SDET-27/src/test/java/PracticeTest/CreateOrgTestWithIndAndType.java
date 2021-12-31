package PracticeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateOrgTestWithIndAndType extends BaseClass {
	@Test
	public  void CreateOrgWithind() throws Throwable {
	    //get random number
	    int randomNum = jLib.getRanDomNumber();
	    // get the test script data
	  String orgName = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;
	  String Industry = eLib.getDataFromExcel("Sheet1",1,4);
	  String Type = eLib.getDataFromExcel("Sheet1",1,5);
	    //get the test data from the excel sheet
	   /* String orgName = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;
	    String Industry = eLib.getDataFromExcel("Sheet1",1,4);
	    String Type = eLib.getDataFromExcel("Sheet1",1,5);
	    */
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

	}

}


