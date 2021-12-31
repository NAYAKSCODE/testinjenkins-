package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {//1-create separate class for every web page
	//2-identify all the element and make as private
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orgHaderText;
    //3-initialized webElement using constructor
	public OrganizationInfoPage(WebDriver driver)
	{
	PageFactory.initElements(driver,this);
	}
	//utilized using getter methods
	public WebElement getOrgHaderText() {
		return orgHaderText;
	}
	//Business library
	public String getOrgInfo()
	{
		return(orgHaderText.getText());
	}
}
