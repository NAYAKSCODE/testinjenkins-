package com.crm.autodesk.org;

import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups= {"smokeTest"})
	public void createOrgTest() throws Throwable
	{
		//get random number
		int randomint = jLib.getRanDomNumber();
		//test script data
		String orgName = eLib.getDataFromExcel("Sheet1",1,2)+randomint;
		//nevigagte to organuzaation
		HomePage hp= new HomePage(driver);
		hp.clickOnOrganizationsLink();
		//nevagate to create organization lookup img
		OrganizationsPage op= new OrganizationsPage(driver);
		op.clickOnCreateOrganization();
		//create organization
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.createorg(orgName);
		//verify the header msg with org name 
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actual_msg = oip.getOrgInfo();
		if(actual_msg.contains(orgName))
		{
			System.out.println(orgName+" ==> create successfully");
		}
		else {
			System.out.println(orgName+" ==> not create successfully");

		}
	}

}
