package com.crm.autodesk.genericutility;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

/**
 * it contains WebDriver specific reusable action 
 * @author ASHUTOSH NAYAK
 *
 */

public class WebDriverUtility {
	private static final long PollingTime = 0;
	/**
	 * wait for page to load before identifying any synchronized element in DOM [HTML-document]
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	/**
	 * wait for page to load before identifying ant assychronized[java script actions] element
	 * @param driver
	 */
public void waitForPageToLoadForJSElement(WebDriver driver){
	driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
}
/**
 * used to maximize the browser
 * @param driver
 */
public void maximizeBrowser(WebDriver driver) {
	driver.manage().window().maximize();
}
/**
 *  used to wait for the element to be clickable in GUI and check for specific element for every 500 mili second
 * @param driver
 * @param element
 */
public void waitForElementToBeClickAble(WebDriver driver, WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.elementToBeClickable(element));
}
/**
 * used to wait for the element to be clickable in GUI and check for specific element for every 500 mili second
 * @param driver
 * @param element
 * @throws Throwable
 */
public void waitForElementWithCustomTimeOut(WebDriver driver,WebElement element) throws Throwable  {
	FluentWait wait= new FluentWait(driver);
	wait.pollingEvery(PollingTime,TimeUnit.SECONDS);
	wait.wait(20);
	wait.until(ExpectedConditions.elementToBeClickable(element));
}
/**
 * used to switch to any window based on window title
 * @param driver
 * @param PartialWindowTitle
 */
public void switchToWindow(WebDriver driver ,String PartialWindowTitle) {
	Set<String> set = driver.getWindowHandles();
	Iterator<String> it = set.iterator();
	while(it.hasNext())
	{
		String Wid=it.next();
		driver.switchTo().window(Wid);
		String currentWindowTitle = driver.getTitle();
		if(currentWindowTitle.contains(currentWindowTitle))
		{
			break;
		}
	}
	}
     /**
      * used to Switch to Alert Window & click on OK button
      * @param driver
      */
    public void swithToAlertWindowAndAccpect(WebDriver driver) {
	driver.switchTo().alert().accept();
    }
    /**
     * used to Switch to Alert Window & click on Cancel button
     * @param driver
     */
    public void swithToAlertWindowAndCancel(WebDriver driver) {
	driver.switchTo().alert().dismiss();
   }
    /**
     * used to switch to frame window based on index
     * @param driver
     * @param index
     */
    public void switchToFrame(WebDriver driver, int index)
    {
    	driver.switchTo().frame(index);
    }
    /**
	 * used to Switch to Frame Window based on id or name attribute
	 * @param driver
	 * @param id_name_attribute
	 */
	public void swithToFrame(WebDriver driver , String id_name_attribute) {
		driver.switchTo().frame(id_name_attribute);
	}
	
	/**
	 * used to select the value from the dropDwon  based on index
	 * @param driver
	 * @param index
	 */
	public void select(WebElement element , int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * used to select the value from the dropDwon  based on value / option avlaible in GUI
	 * @param element
	 * @param value
	 */
	public void select(WebElement element , String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	/**
	 * used to place mouse cursor on specified element
	 * @param driver
	 * @param elemnet
	 */
	public void mouseOverOnElement(WebDriver driver , WebElement elemnet)
	{
		Actions act = new Actions(driver);
		act.moveToElement(elemnet).perform();
	}
	
	/**
	 * 	 used to right click  on specified element

	 * @param driver
	 * @param elemnet
	 */
	
	public void rightClickOnElement(WebDriver driver , WebElement elemnet)
	{
		Actions act = new Actions(driver);
		act.contextClick(elemnet).perform();
	}
	
	/**
	 * 
	 * @param driver
	 * @param javaScript
	 */
	public void executeJavaScript(WebDriver driver , String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeAsyncScript(javaScript, null);
	}
	
	   public void waitAndClick(WebElement element) throws InterruptedException
	   {
		   int count = 0;
	       while(count<20) {
		    	   try {
		    	       element.click();
		    	       break;
		    	   }catch(Throwable e){
		    		   Thread.sleep(1000);
		    		   count++;
		    	   }
	       }
	   }
	  
	    
	    /**
	     * pass enter Key appertain in to Browser
	     * @param driver
	     */
	   public void passEnterKey(WebDriver driver) {
		   Actions act = new Actions(driver);
		   act.sendKeys(Keys.ENTER).perform();
	   } 



}



   
