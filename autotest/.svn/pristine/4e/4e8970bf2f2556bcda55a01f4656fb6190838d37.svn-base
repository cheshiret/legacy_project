/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description:  Verify add customer successfully
 * @Preconditions:  
 * @SPEC:  Add customer
 * @Task#: Auto-803
 * @author jwang8  
 * @Date  Dec 29, 2011    
 */
public class AddCustomer extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrCustomerAdressesPage addrPage = LicMgrCustomerAdressesPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
	    //Add customer profile
		lm.createNewCustomer(cust);
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		//verify new added customer info
		this.verifyCustInfoSuccessful(cust);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		cust.customerClass = "Individual";
		cust.fName = "Jaswang";
		cust.mName ="jas";
		cust.lName = "Luo"+DataBaseFunctions.getEmailSequence();
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
		cust.physicalAddr.address ="Xian Shanxi";
		cust.physicalAddr.supplementalAddr = "HanZhong";
		cust.physicalAddr.city = "Schenectady";
		cust.physicalAddr.state="New York";
		cust.physicalAddr.county="Schenectady";
		cust.physicalAddr.zip = "12345-0001";
		cust.physicalAddr.country="United States";
		
		cust.mailAddrAsPhy = true;
		cust.mailingAddr.address ="Xian Shanxi";
		cust.mailingAddr.supplementalAddr = "HanZhong";
		cust.mailingAddr.city = "Schenectady";
		cust.mailingAddr.state="New York";
		cust.mailingAddr.county="Schenectady";
		cust.mailingAddr.zip = "12345-0001";
		cust.mailingAddr.country="United States";	
		
		cust.status="Active";
		
		cust.address = "Xian Shanxi";
		cust.supplementalAddress = "HanZhong";
		cust.city = "xian";
		cust.state = "AA";
		cust.county ="Military AA";
		cust.zip = "12345";
		cust.country = "United States";
		cust.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	/**
	 * Verify the customer profile info and address info.
	 * @param expectCust - the customer info.
	 */
	private void verifyCustInfoSuccessful(Customer expectCust){
		boolean result = custDetailPg.compareCustInfoSuccess(expectCust);
		if(!result){
			throw new ErrorOnPageException("Add/edit customer profile failed.");
		} else {
			logger.info("Customer profile info is correct");
		}
		
		result = addrPage.CompareAddrInfo(expectCust);
    	  if(!result){
          	throw new ErrorOnPageException("Add/eidt customer address failed.");
          }else{
          	logger.info("Save customer address successfull.");
          }
	}
}
