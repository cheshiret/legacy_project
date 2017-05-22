/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.CleanUpSwitch;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:  Verify search customer successfully
 * @Preconditions:
 * @SPEC:  Search customer
 * @Task#: Auto-803
 * @author jwang8
 * @Date  Dec 27, 2011
 */
public class SearchCustomer extends LicenseManagerTestCase{

	private String tempLicenseNum = "";


	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//Add a new customer info.
		cust.licenseNum = lm.createNewCustomer(cust);

		//Search customer and verify the result.
		this.verifySearchCust();

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		cust.customerClass = "Individual";
		int sequence = DataBaseFunctions.getEmailSequence();
		cust.fName = "Jas" + sequence;
		cust.mName ="Mid" + sequence;
		cust.lName = "Wang"+ sequence;
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
		cust.physicalAddr.address ="Shanxi";
		cust.physicalAddr.supplementalAddr = "HanZhong";
		cust.physicalAddr.city = "Schenectady";
		cust.physicalAddr.state="New York";
		cust.physicalAddr.county="Schenectady";
		cust.physicalAddr.zip = "12345-0001";
		cust.physicalAddr.country="United States";
		cust.mailAddrAsPhy=true;

		cust.mailingAddr.address ="Shanxi";
		cust.mailingAddr.supplementalAddr = "HanZhong";
		cust.mailingAddr.city = "Schenectady";
		cust.mailingAddr.state="New York";
		cust.mailingAddr.county="Schenectady";
		cust.mailingAddr.zip = "12345-0001";
		cust.mailingAddr.country="United States";
		cust.status="Active";

		cust.address = "Shanxi";
		cust.supplementalAddress = "HanZhong";
		cust.city = "Schenectady";
		cust.state = "New York";
		cust.county ="Schenectady";
		cust.zip = "12345-0001";
		cust.country = "United States";
		cust.licenseType = "MDWFP #";
		cust.includeAreaCode = true;
	}


    private void verifySearchCust(){
    	LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
    	tempLicenseNum = cust.licenseNum;
    	//Search just with full info.
    	
    	cust.licenseNum = "";
        custSearchPg.searchCust(cust);
        cust.licenseNum = tempLicenseNum;
		custSearchPg.verifySearchCustomerProfileResult(cust);
		
		Customer searchCust = new Customer();
		CleanUpSwitch cleanUpList = searchCust.new CleanUpSwitch();
		cleanUpList.isCleanLicenseNumber=true;
    	custSearchPg.clearUpSearchCriteria(cleanUpList);
		//Search just with first name.
		searchCust.fName = cust.fName;
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifySearchCustomerProfileResult(searchCust);

		cleanUpList.isCleanLicenseNumber=false;
		cleanUpList.isCleanFirstName=true;
		custSearchPg.clearUpSearchCriteria(cleanUpList);
		//Search just with last name.
		searchCust.fName ="";
		searchCust.lName = cust.lName;
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifySearchCustomerProfileResult(searchCust);

		cleanUpList.isCleanFirstName=false;
		cleanUpList.isCleanLastName=true;
		custSearchPg.clearUpSearchCriteria(cleanUpList);
		//Search just with middle name.
		searchCust.lName = "";
		searchCust.mName = cust.mName;
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifySearchCustomerProfileResult(searchCust);

		cleanUpList.isCleanLastName=false;
		cleanUpList.isCleanMiddleName=true;
		custSearchPg.clearUpSearchCriteria(cleanUpList);
		//Search just with phone number.
		searchCust.mName="";
		searchCust.hPhone = cust.hPhone;
		searchCust.includeAreaCode = true;
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifySearchCustomerProfileResult(searchCust);

		//Can't as search critira just for customer class because of production channge.
		/*custSearchPg.clearUpSearchCriteria();
		//Search just with customer class.
		searchCust.hPhone = "";
		searchCust.customerClass =cust.customerClass;
		searchCust.includeAreaCode = true;
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifySearchCustomerProfileResult(searchCust);*/

		//Search with licenseType and number.
		cleanUpList.isCleanMiddleName=false;
		cleanUpList.isCleanPhoneNumber=true;
		custSearchPg.clearUpSearchCriteria(cleanUpList);
		searchCust.licenseType = "MDWFP #";
		searchCust.licenseNum = cust.licenseNum;
		lm.searchCustomer(searchCust);
		//custSearchPg.searchCust(searchCust);
		this.verifyCustInfoSuccessful(cust);
    }

    /**
	 * Verify the customer profile info and address info.
	 * @param expectCust - the customer info.
	 */
	private void verifyCustInfoSuccessful(Customer expectCust){

		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerAdressesPage addrPage = LicMgrCustomerAdressesPage.getInstance();
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
