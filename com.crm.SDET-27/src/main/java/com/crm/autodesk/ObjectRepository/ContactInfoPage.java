package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {//1-create separate class for web page
	//2-identify all the WebElement and make as private
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactInfoHeader;
	//3-initialized WebElement using constructor
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//utilized using getter methods
	public WebElement getContactInfoHeader() {
		return contactInfoHeader;
	}
	//Business library
	public String getContactInfo()
	{
		return(contactInfoHeader.getText());
	}
	

}
