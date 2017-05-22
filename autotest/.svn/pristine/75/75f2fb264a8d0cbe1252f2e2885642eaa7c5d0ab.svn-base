package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC: Search Customer Profile
 * @Task#: Auto-508

 * @author SWang
 * @Date Jun 18, 2011
 */
public class SearchCustomerProfileByRemoveIdentifierOrEducation extends LicenseManagerTestCase{
	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private CustomerIdentifier identifier = new CustomerIdentifier();
	private List<CustomerIdentifier> identifiersList= new ArrayList<CustomerIdentifier>();
	private Customer cust_1 = new Customer();
	private Education edu = new Education();

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);

		//Verify search result with identifier with type other than 'Customer #'
		this.verifySearchResultViaIdentifierTypeOtherThanCustomerNum();

		//Verify search result with education
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		this.verifySearchResultViaEducation();

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		//Customer info
		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";
		cust.fName = "QA-Customer9";
		cust.mName = "QaTest-CusotmerProfile-9";
		cust.lName = "TEST-Profile9";
		cust.dateOfBirth = "Jun 07 1981";

		//Identifier info
		identifier.identifierType = "Passport";
		identifier.identifierNum = "111111160";
		identifier.country = "Canada";
		identifiersList.add(identifier);

		//Education info
		edu.educationType = "HuntEducation";
		edu.educationNum = "111111161";
		edu.state = "Mississippi";
		edu.country = "United States";
	}

	private void initializeLicenseInfo(String licenseType, String licenseNum){
		cust_1.licenseType = licenseType;
		cust_1.licenseNum = licenseNum;
	}

	private void verifySearchResultViaIdentifierTypeOtherThanCustomerNum(){
		//Add and Remove Identifier
		lm.gotoCustomerSubTabPage("Identifiers");
		lm.addIdentifiersInInditifierPage(identifiersList);
		lm.changeIdentifierStatus(identifier.identifierType, identifier.identifierNum, "Remove");
		//Research customer uses the remove identifier
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		this.initializeLicenseInfo(identifier.identifierType, identifier.identifierNum);
		custSearchPg.searchCust(cust_1);
		custSearchPg.verifyKnownCustNumInSearchList(cust.licenseNum,false);
	}

	private void verifySearchResultViaEducation(){
		//Add and Remove Education
		lm.gotoCustomerSubTabPage("Education");
		lm.manageEducationsForCustomer("Add", null, edu);
		lm.manageEducationsForCustomer("Remove", null, edu);
		//Research customer uses the remove education
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		this.initializeLicenseInfo(edu.educationType, edu.educationNum);
		custSearchPg.searchCust(cust_1);
		custSearchPg.verifyKnownCustNumInSearchList(cust.licenseNum,false);
	}

}
