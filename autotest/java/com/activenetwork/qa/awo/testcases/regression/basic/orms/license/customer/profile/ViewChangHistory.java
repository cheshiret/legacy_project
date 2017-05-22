/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**  
 * @Description:   Verify view change history successfully
 * @Preconditions:  
 * @SPEC:  View customer profile change history
 * @Task#: Auto-803
 * @author jwang8  
 * @Date  Dec 30, 2011    
 */
public class ViewChangHistory extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrCustomerAdressesPage addrPage = LicMgrCustomerAdressesPage.getInstance();
	private LicMgrCustomerChangeHistoryPage historyPage = LicMgrCustomerChangeHistoryPage.getInstance();
	String location = "";
	private Customer editCust = new Customer();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
	    //Add customer profile
		lm.createNewCustomer(cust);
		
		//Search the new customer and go to the detail page.
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustChangeHistoryPg();
		historyPage.verifyChangeHisSuccessful(cust, "Add", location);	
		
		lm.gotoCustDetailFromChangeHisPg();
       //Edit customer 
		this.swichEditCustParameters();
		lm.addOrEditCustomerInfo(editCust, custDetailPg);
		lm.addOrEditCustomerInfo(editCust, addrPage);
		lm.finishAddOrEditCustomer();
		lm.gotoCustomerDetails(editCust.lName, editCust.fName, editCust.customerClass,editCust.status);	
		
		//View customer profile change history.
		lm.gotoCustChangeHistoryPg();
		historyPage.verifyChangeHisSuccessful(cust, editCust, "Update", location);
		
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		location = login.location.split("/")[1];

		cust.customerClass = "Individual";
		cust.fName = "Jaswang";
		cust.mName ="jas";
		cust.lName = "Zhou"+DataBaseFunctions.getEmailSequence();
		cust.suffix = "JR";
		cust.dateOfBirth = "Dec 24 1985";
		cust.overrideReqId = true;
		cust.overideReason = "Auto-test";
		cust.hPhone = "4088122485";
		cust.bPhone = "4088654789";
		cust.mPhone = "4088000000";
		cust.fax = "02145689";
		cust.email = "li@sina.com";
		cust.custGender = "Female";
		
		cust.ethnicity = "White";
		cust.solicitationIndcator = "No";
		cust.physicalAddr.address ="Xian shanxi";
		cust.physicalAddr.supplementalAddr = "HanZhong";
		cust.physicalAddr.city = "xian";
		cust.physicalAddr.state="New York";
		cust.physicalAddr.county="Schenectady";
		cust.physicalAddr.zip = "12345-0001";
		cust.physicalAddr.country="United States";

		cust.mailAddrAsPhy = true;
		cust.mailingAddr.address ="Xian shanxi";
		cust.mailingAddr.supplementalAddr = "HanZhong";
		cust.mailingAddr.city = "xian";
		cust.mailingAddr.state="New York";
		cust.mailingAddr.county="Schenectady";
		cust.mailingAddr.zip = "12345-0005";
		cust.mailingAddr.country="United States";	
		
		cust.status="Active";
		
		cust.address = "Xian shanxi";
		cust.supplementalAddress = "HanZhong";
		cust.city = "xian";
		cust.state = "New York";
		cust.county ="Schenectady";
		cust.zip = "12345-0005";
		cust.country = "United States";
		cust.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	public void swichEditCustParameters(){
		editCust.status = "Inactive";
		editCust.fName = "Zhang";
		editCust.mName="Nice";
		editCust.lName = "His" + DataBaseFunctions.getEmailSequence();
		editCust.suffix = "SR";
		editCust.dateOfBirth = "Dec 01 2001";
		editCust.overrideReqId = false;
		editCust.overideReason = "";
		editCust.hPhone = "4088333333";
		editCust.bPhone = "4088222222";
		editCust.mPhone = "4088111111";	
		editCust.fax = "02178945";
		editCust.email = "Liu@sina.com";	
		editCust.custGender = "Male";
		editCust.ethnicity = "Black";
		editCust.solicitationIndcator = "Yes";
		
		editCust.physicalAddr.address ="HangZhong";
		editCust.physicalAddr.supplementalAddr = "BeiJing";
		editCust.physicalAddr.city = "ShangHai";
		editCust.physicalAddr.state="New York";
		editCust.physicalAddr.county="Schenectady";
		editCust.physicalAddr.zip = "12345-0005";
		editCust.physicalAddr.country="United States";	
		editCust.physicalAddr.isValidate = true;
		editCust.mailAddrAsPhy=false;
		
		editCust.mailingAddr.address ="JiangSu";
		editCust.mailingAddr.supplementalAddr = "NanJing";
		editCust.mailingAddr.city = "WuZheng";
		editCust.mailingAddr.state="New York";
		editCust.mailingAddr.county="Schenectady";
		editCust.mailingAddr.zip = "12345-0005";
		editCust.mailingAddr.country="United States";	
		editCust.mailingAddr.isValidate = true;
			
		editCust.alternateAddr.address ="ZhengJiang";
		editCust.alternateAddr.supplementalAddr = "XuZhou";
		editCust.alternateAddr.city = "ShangLuo";
		editCust.alternateAddr.state="New York";
		editCust.alternateAddr.county="Schenectady";
		editCust.alternateAddr.zip = "12345-0005";
		editCust.alternateAddr.country="United States";	
		editCust.creationUser=DataBaseFunctions.getLoginUserName(login.userName);
	}
}
