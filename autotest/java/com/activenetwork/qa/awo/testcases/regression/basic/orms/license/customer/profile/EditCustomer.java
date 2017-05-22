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
 * @Description:  Verify edit customer successfully
 * @Preconditions:  
 * @SPEC:  Edit Customer profile
 * @Task#: Auto-803
 * @author jwang8  
 * @Date  Dec 26, 2011    
 */
public class EditCustomer extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrCustomerAdressesPage addrPage = LicMgrCustomerAdressesPage.getInstance();
	private Customer editCust = new Customer();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
	    //Add customer profile
		lm.createNewCustomer(cust);
			
		//Edit customer
		this.swichEditCustParameters();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.addOrEditCustomerInfo(editCust, custDetailPg);
		lm.addOrEditCustomerInfo(editCust, addrPage);
		lm.finishAddOrEditCustomer();
		
		//verify edited customer info
		lm.gotoCustomerDetails(editCust.lName, editCust.fName, editCust.customerClass,editCust.status);	
		this.verifyCustSuccessful(editCust);	
			
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		cust.customerClass = "Individual";
		cust.fName = "Jaswang";
		cust.mName ="jas";
		cust.lName = "Li"+DataBaseFunctions.getEmailSequence();
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
		
		cust.address = "Xian shanxi";
		cust.supplementalAddress = "HanZhong";
		cust.city = "xian";
		cust.state = "AA";
		cust.county ="Military AA";
		cust.zip = "12345";
		cust.country = "United States";
		cust.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	public void swichEditCustParameters(){
		editCust.status = "Inactive";
		editCust.fName = "Zhang";
		editCust.mName="Nice";
		editCust.lName = "Lu" + DataBaseFunctions.getEmailSequence();
		editCust.suffix = "SR";
		editCust.dateOfBirth = "Dec 01 2001";
		editCust.overrideReqId = false;
		editCust.overideReason = "Auto-test";
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
		editCust.physicalAddr.city = "Virginia Beach";
		editCust.physicalAddr.state="Virginia";
		editCust.physicalAddr.county="Virginia Beach city";
		editCust.physicalAddr.zip = "23456";
		editCust.physicalAddr.country="United States";	
		editCust.physicalAddr.isValidate = true;
		editCust.mailAddrAsPhy=false;
		
		editCust.mailingAddr.address ="JiangSu";
		editCust.mailingAddr.supplementalAddr = "NanJing";
		editCust.mailingAddr.city = "Houghton";
		editCust.mailingAddr.state="Iowa";
		editCust.mailingAddr.county="Lee";
		editCust.mailingAddr.zip = "52631";
		editCust.mailingAddr.country="United States";	
		editCust.mailingAddr.isValidate = true;
		
			
		editCust.alternateAddr.address ="Shangxi";
		editCust.alternateAddr.supplementalAddr = "TaiYuan";
		editCust.alternateAddr.city = "Virginia Beach";
		editCust.alternateAddr.state="Virginia";
		editCust.alternateAddr.county="Virginia Beach city";
		editCust.alternateAddr.zip = "23451";
		editCust.alternateAddr.country="United States";	
		editCust.creationUser=DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	/**
	 * Verify the customer profile and address info.
	 * @param expectCust
	 */
	private void verifyCustSuccessful(Customer expectCust){
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
