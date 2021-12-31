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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactsTest {

	public static void main(String[] args) throws Throwable {
	//get java representation of physical file
		FileInputStream fis = new FileInputStream("./data/commondata.properties");
	//create an object of property class to load all keys
		Properties pro = new Properties();
		pro.load(fis);
	//read the value using getProperty("keys")
	String BROWSER = pro.getProperty("Browser");
	String URL = pro.getProperty("URL");
	String USERNAME = pro.getProperty("username");
   String PASSWORD = pro.getProperty("password");
   //get random number
   Random rand = new Random();
  int randomnum = rand.nextInt(5000);
	//	read the value from excel sheet
   FileInputStream fil_e = new FileInputStream("./data/testscriptdata.xlsx");
   Workbook wb = WorkbookFactory.create(fil_e);
 Sheet sh = wb.getSheet("Sheet1");
  Row row = sh.getRow(1);
  String lastname = row.getCell(2).getStringCellValue()+randomnum;
  
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
  Thread.sleep(2000);
  //login
  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
  driver.get(URL);
  Thread.sleep(1000);
  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
  driver.findElement(By.id("submitButton")).click();
  Thread.sleep(1000);
  // navigate to contacts module
  driver.findElement(By.linkText("Contacts")).click();
  Thread.sleep(1000);
  driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
  Thread.sleep(1000);
  driver.findElement(By.name("lastname")).sendKeys(lastname);
  Thread.sleep(1000);
  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
  Thread.sleep(1000);
  //verify contacts 
  String actual_msg = driver.findElement(By.className("dvHeaderText")).getText();
  if(actual_msg.contains(lastname))
  {
	  System.out.println("pass--contact is created");
  }
  else
  {
	  System.out.println("fail--contact not created");
  }
  
  //logout
  Actions act = new Actions(driver);
  act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
  driver.findElement(By.linkText("Sign Out")).click();
  driver.quit();
	}

}
