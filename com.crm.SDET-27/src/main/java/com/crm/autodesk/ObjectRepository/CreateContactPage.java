package com.crm.autodesk.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility {//1-create separate class for web page
	//identify all webElement and make as private
	@FindBy(name="lastname")
	 private WebElement contactlastnameEdt;
	@FindBy(xpath="//input[@name='account_name']/following-sibiling::img")
	private WebElement orgnamelookupimg;
	@FindBy(id="email")
	private WebElement contactemailEdt;
	@FindBy(name="notify_owner")
    private WebElement contactnofifyownercheckbox;
	@FindBy(name="portal")
	private WebElement contactportalusercheckbox;
    @FindBy(xpath="//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;
    //initialized element using constructor
    public CreateContactPage(WebDriver driver)
    {
    	PageFactory.initElements(driver,this);
    }
    //utilized using getter methods
	public WebElement getContactlastnameEdt() {
		return contactlastnameEdt;
	}
	public WebElement getOrgnamelookupimg() {
		return orgnamelookupimg;
	}
	public WebElement getContactemailEdt() {
		return contactemailEdt;
	}
	public WebElement getContactnofifyownercheckbox() {
		return contactnofifyownercheckbox;
	}
	public WebElement getContactportalusercheckbox() {
		return contactportalusercheckbox;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	//business library for create contact
	public void createContact(String lastName)
	{
		contactlastnameEdt.sendKeys(lastName);	
		saveBtn.click();
	}
	//Business library for create contact with Organization
    public void createContactWithOrg(WebDriver driver,String lastName,String orgName, String partialWindowTitle)
    {
    	contactlastnameEdt.sendKeys(lastName);
    	orgnamelookupimg.click();
    	switchToWindow(driver,partialWindowTitle);
    }
	//business library for create contact with nofify owner checkbox
    public void createContactWithNotifyOwner(WebDriver driver,String lastName)
    {
    	contactlastnameEdt.sendKeys(lastName);
        contactnofifyownercheckbox.click();
        saveBtn.click();
    }
    //business library for check weather check box is enable or not
    public void notifyOwnerIsEnable()
    {
    	contactnofifyownercheckbox.isEnabled();
    }
    //business library for create contact with portal user and email
    public void createContactWithUserPortal(String lastName,String Email)
    {
    	contactlastnameEdt.sendKeys(lastName);
    	contactemailEdt.sendKeys(Email);
    	contactportalusercheckbox.click();
    	saveBtn.click();
    }
}
