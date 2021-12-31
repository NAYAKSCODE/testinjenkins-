package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {//1-create separate class for web page
//2-identify all the element and make as private
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement Createcontactlookupimg;
	
	@FindBy(name="search_text")
	private WebElement searchtextEdt;
	
	@FindBy(name="submit")
	private WebElement searchnowbtn;
//3-initialized element using constructor
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//4-utilized using getter methods
	public WebElement getCreatecontactlookupimg() {
		return Createcontactlookupimg;
	}
	public WebElement getSearchtextEdt() {
		return searchtextEdt;
	}
	public WebElement getSearchnowbtn() {
		return searchnowbtn;
	}
	//Business library
	public void clickOnCreatecontact()
	{
		Createcontactlookupimg.click();
	}
}
