package Practice;

import java.io.FileInputStream;
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

public class CreateContactAndSelectOrganisation {

	public static void main(String[] args) throws Throwable {
		//get java representation of physical file 
	FileInputStream fis = new FileInputStream("./data/commondata.properties");
	//create an object of Property class to load all keys 
	Properties prop = new Properties();
	prop.load(fis);
	// read the value using getProperty
	String BROWSER = prop.getProperty("Browser");
	String URL = prop.getProperty("URL");
	String USERNAME = prop.getProperty("username");
	String PASSWORD = prop.getProperty("password");
	//get random number for orgName
	Random random = new Random();
	int random1 = random.nextInt(500);
	
	Random random2 = new Random();
	int random3 = random2.nextInt(1000);
	
	//  read the value using excel 
	FileInputStream file_e = new FileInputStream("./data/testscriptdata.xlsx");
	Workbook wb = WorkbookFactory.create(file_e);
	 Sheet sh = wb.getSheet("Sheet1");
	 Row row = sh.getRow(1);
	 String orgName = row.getCell(2).getStringCellValue()+random1;
	
	FileInputStream fis_e = new FileInputStream("./data/testscriptdata.xlsx");
	Workbook wb1 = WorkbookFactory.create(fis_e);
	Sheet sh1 = wb1.getSheet("Sheet1");
	Row row1 = sh1.getRow(1);
	String lastName = row1.getCell(2).getStringCellValue()+random3;
	
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
	//login
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get(URL);
    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
    driver.findElement(By.id("submitButton")).click();
    //Navigate to organization module 
    driver.findElement(By.linkText("Organizations")).click();
    driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
    driver.findElement(By.name("accountname")).sendKeys(orgName);
    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
    Thread.sleep(3000);
   // Navigate to contact module
    driver.findElement(By.linkText("Contacts")).click();
    driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
    driver.findElement(By.name("lastname")).sendKeys(lastName);
    driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
    String parentwin = driver.getWindowHandle();
    Set<String> childwin = driver.getWindowHandles();
    childwin.remove(parentwin);
    for(String b:childwin)
    {
    	driver.switchTo().window(b);
    }
	driver.findElement(By.linkText(orgName)).click();
	driver.switchTo().window(parentwin);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//logout
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();

	}

}
