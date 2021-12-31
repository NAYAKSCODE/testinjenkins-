package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class HomePage extends WebDriverUtility {//1- create separate class for webpage
	//2-identify all the element and make as private
	@FindBy(linkText="Leads")
	private WebElement Leadslink;
	
	@FindBy(linkText="Organizations")
	private WebElement Organizationslnk;
	
	@FindBy(linkText="Contacts")
	private WebElement contactslnk;
	
	@FindBy(linkText="Opportunities")
	private WebElement oppertunitieslnk;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorimg;
	@FindBy(linkText="Sign Out")
	private WebElement signoutlnk;
	//3-initialize the element using constructor
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//4-Utilization provide getter methods
	public WebElement getLeadslink() {
		return Leadslink;
	}
	public WebElement getOrganizationslnk() {
		return Organizationslnk;
	}
	public WebElement getContactslnk() {
		return contactslnk;
	}
	public WebElement getOppertunitieslnk() {
		return oppertunitieslnk;
	}
	public WebElement getAdministratorimg() {
		return administratorimg;
	}
	public WebElement getSignoutlnk() {
		return signoutlnk;
	}
	//Business library to click on organizations
	public void clickOnOrganizationsLink()
	{
		Organizationslnk.click();
	}
	//Business library to click on contacts
	public void clickOnContactsLink()
	{
		contactslnk.click();
	}
	//Business library to click on signout
	public void SignOut(WebDriver driver)
	{
		mouseOverOnElement(driver,administratorimg);
		signoutlnk.click();
	}
	
}
