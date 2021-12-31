package com.crm.autodesk.contact;

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
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.genericutility.BaseClass;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactsTest extends BaseClass {
   @Test(groups= {"smokeTest"})
	public void CreateContactTest() throws Throwable {
		//get random data
		int randomNum = jLib.getRanDomNumber();

		//get the test script data
		String lastname = eLib.getDataFromExcel("Sheet1",1,2)+randomNum;

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
	}

}
