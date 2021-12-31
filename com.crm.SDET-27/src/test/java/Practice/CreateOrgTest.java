package Practice;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {


	public static void main(String[] args) throws IOException, Throwable {
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
    //get random num
    Random ran = new Random();
     int randomnum = ran.nextInt(5000);
    
    //read the data from the excel file
    FileInputStream fis_e = new FileInputStream("./data/testscriptdata.xlsx");
   Workbook wb = WorkbookFactory.create(fis_e);
  Sheet sh = wb.getSheet("Sheet1");
  Row row = sh.getRow(1);
 String orgName = row.getCell(2).getStringCellValue()+ randomnum;
 
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
//login
driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
driver.get(URL);
driver.findElement(By.name("user_name")).sendKeys(USERNAME);
driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
driver.findElement(By.id("submitButton")).click();
// nevigate to organization module
driver.findElement(By.linkText("Organizations")).click();
// click on create organization button
driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
//enter all the details and create new organization
driver.findElement(By.name("accountname")).sendKeys(orgName);
driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//verify organization name
String actual_msg = driver.findElement(By.className("dvHeaderText")).getText();
 if(actual_msg.contains(orgName))
{
	System.out.println("org is actually created");
	}
else
{
	System.out.println("org is not created");
	}

//logout
 
Actions act = new Actions(driver);
act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
driver.findElement(By.linkText("Sign Out")).click();
driver.close();




	}
   

}
