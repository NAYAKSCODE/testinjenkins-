package Practice;

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

public class CreateOrgTestWithindustryandtype {

	public static void main(String[] args) throws Throwable {
		//get java representation object of physical file
		FileInputStream fis = new FileInputStream("./data/commondata.properties");
			
		//create an object to property class to load all the keys
		Properties pobj = new Properties();
		pobj.load(fis);
		//read the value using getProperty("key")
		String BROWSER = pobj.getProperty("Browser");
		String URL = pobj.getProperty("URL");
	    String USERNAME = pobj.getProperty("username");
	    String PASSWORD = pobj.getProperty("password");
	    //create random number
	    Random rand = new Random();
	     int random1 = rand.nextInt(1000);
	    //read the data from the excel file
	    FileInputStream fis_e = new FileInputStream("./data/testscriptdata.xlsx");
	    Workbook wb = WorkbookFactory.create(fis_e);
	    Sheet sh = wb.getSheet("Sheet1");
	    Row row = sh.getRow(1);
	    String orgName = row.getCell(2).getStringCellValue()+random1;
	    String Industry = row.getCell(4).getStringCellValue();
	    String Type = row.getCell(5).getStringCellValue();
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
        // login to application
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    driver.get(URL);
	    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	    driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	    driver.findElement(By.id("submitButton")).click();
	    // Navigate to organization module
	    driver.findElement(By.linkText("Organizations")).click();
	    driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	    //enter all the details and create new organization
	    driver.findElement(By.name("accountname")).sendKeys(orgName);
	    WebElement object1 = driver.findElement(By.name("industry"));
	     Select sel1 = new Select(object1);
	    sel1.selectByVisibleText(Industry);
	    
	    
	    WebElement object2 = driver.findElement(By.name("accounttype"));
	    Select sel2 = new Select(object2);
	    sel2.selectByVisibleText(Type);
	    
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    //verify organization name in header of the msg
	    String actual_msg = driver.findElement(By.className("dvHeaderText")).getText();
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
       Actions act = new Actions(driver);
       act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
       driver.findElement(By.linkText("Sign Out")).click();
       driver.close();

	}

}
