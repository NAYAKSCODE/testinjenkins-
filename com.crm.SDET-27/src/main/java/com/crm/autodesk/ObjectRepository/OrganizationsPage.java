package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {//1-create separate class for webpage
	//2-identify all the webElement and make as private
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createorglookupimg;
	
	@FindBy(name="search_text")
	private WebElement searchtextEdt;
	
	@FindBy(name="submit")
	private WebElement searchnowbtn;
	
	//3-initialize the by using constructor
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//4-Utilization provide getter methods

	public WebElement getCreateorganization() {
		return createorglookupimg;
	}

	public WebElement getSearchtextEdt() {
		return searchtextEdt;
	}

	public WebElement getSearchnowbtn() {
		return searchnowbtn;
	}
	//Business library
	public void clickOnCreateOrganization()
	{
		createorglookupimg.click();
	}

}
