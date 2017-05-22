package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
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
public class SearchCustomerProfileByCustomerNumberAndCustomerClass extends LicenseManagerTestCase{
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
	.getInstance();
	private LicMgrCustomersSearchPage custSearch = LicMgrCustomersSearchPage
	.getInstance();
	private boolean pass = true;
	private String custNum_1, custNum_2 = "";
	private Customer cust = new Customer();
	private Customer cust_1 = new Customer();
	private Customer cust_2 = new Customer();

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Get license number for customer 1 and customer 2
		cust_1.licenseNum = lm.getCustomerNum(cust_1, schema);
		custNum_1 = cust_1.licenseNum;
		cust_2.licenseNum = lm.getCustomerNum(cust_2, schema);

		//Search customer with customer info(Customer #, customer number and customer class)
		lm.gotoCuscomerDetailsFromSearchPg(cust_1.licenseType, cust_1.licenseNum , cust_1.customerClass);
		this.verifyCustomerNumAndClassInCustDetailsPg();

		//customer number ignoring dashes
		cust_1.licenseNum = custNum_1+"-";
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCuscomerDetailsFromSearchPg(cust_1.licenseType, cust_1.licenseNum , cust_1.customerClass);
		this.verifyCustomerNumAndClassInCustDetailsPg();

		//customer number ignoring embedded spaces
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		cust_1.licenseNum = custNum_1.substring(0, 1)+" "+custNum_1.substring(1);
		lm.gotoCuscomerDetailsFromSearchPg(cust_1.licenseType, cust_1.licenseNum , cust_1.customerClass);
		this.verifyCustomerNumAndClassInCustDetailsPg();

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		cust_1.customerClass = "INDIVIDUAL";
		cust_1.licenseType = "MDWFP #";
		cust_1.fName = "QA-Customer1";
		cust_1.mName = "QaTest-CusotmerProfile-1";
		cust_1.lName = "TEST-Profile1";
		cust_1.dateOfBirth = "Apr 04 1976";

		cust_2.customerClass = "Business";//COMMERCIAL
		cust_2.licenseType = "MDWFP #";
		cust_2.businessName = "@QaTest-CusotmerProfile-2";
		cust_2.fName = "QA-Customer2";
		cust_2.mName = "QaTest-CusotmerProfile-2";
		cust_2.lName = "TEST-Profile2";

		cust_2.dateOfBirth = "Jan 14 1977";

		cust.customerClass = cust_1.customerClass;
	}

	private void verifyCustomerNumAndClassInCustDetailsPg(){
		if(!custDetailsPg.getCustomerNumber().equals(cust.custNum)){
			pass &=false;
			logger.error("Customer Number = "+cust.custNum+" is incorrect!");
		}
		if(!custDetailsPg.getCustomerClass().equals(cust.customerClass)){
			pass &=false;
			logger.error("Customer class = "+cust.customerClass+" is incorrect!");
		}
	}
}
