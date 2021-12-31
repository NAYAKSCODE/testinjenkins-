package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility{//1-create separate class for web page
	//2-identify all the element and make as private
	@FindBy(name="accountname")
	private WebElement organizationNameEdt;
	@FindBy(name="industry")
	private WebElement industrydropdown;
	@FindBy(name="accounttype")
	private WebElement typedropdown;
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
  //3-initialized webElement using constructor
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//utilized using getter method
	public WebElement getOrganizationNameEdt() {
		return organizationNameEdt;
	}
	public WebElement getIndustrydropdown() {
		return industrydropdown;
	}
	public WebElement getTypedropdown() {
		return typedropdown;
	}
	public WebElement getSavebtn() {
		return savebtn;
	}
	//Business library for create organization
	public void createorg(String orgName)
	{
		organizationNameEdt.sendKeys(orgName);
		savebtn.click();
	}
	//business library for create organization with industry drop down
	public void createOrgWithIndustry(String orgName, String industryType)
	{
		organizationNameEdt.sendKeys(orgName);
		select(industrydropdown,industryType);
		savebtn.click();
	}
	//business library for create organization with industry and type
	public void createOrgWithIndusAndType(String orgName, String industry,String Type)
	{
		organizationNameEdt.sendKeys(orgName);
		select(industrydropdown,industry);
		select(typedropdown,Type);
		savebtn.click();
	}
}
