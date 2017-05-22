package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC: Search Customer Profile
 * @Task#: Auto-508

 * @author QA
 * @Date Jun 15, 2011
 */
public class SearchCustomerProfileByCertifications extends LicenseManagerTestCase{
	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private Certification c = new Certification();
	private Customer cust_1 = new Customer();

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);

		//Add certification
		lm.gotoCustomerSubTabPage("Certifications");
		lm.manageCertificationForCustomer("Add", null, c);

		//Verify search result via certification type and number
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		this.verifySearchResult();
		//case-insensitive match
		cust_1.licenseNum = cust_1.licenseNum.toLowerCase();
		this.verifySearchResult();
		//ignoring dashes
		cust_1.licenseNum = cust_1.licenseNum+"-";
		this.verifySearchResult();
		//ignoring embedded spaces
		cust_1.licenseNum = cust_1.licenseNum.substring(0, 1)+" "+cust_1.licenseNum.substring(1);
		this.verifySearchResult();

		//Remove certification
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.manageCertificationForCustomer("Remove", null, c);

		//Verify search result via certification type and number 
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		cust_1.licenseNum  = c.num;
		this.verifyErrorMessage(str);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//Customer info
		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Customer1";
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";

		//Certification info
		c.status = OrmsConstants.ACTIVE_STATUS;
		c.type = "Trapper Certification";
		c.num = "AUTO0000" + DataBaseFunctions.getEmailSequence();
		c.effectiveFrom = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-100), "E MMM d yyyy");

		cust_1.licenseType = c.type;
		cust_1.licenseNum = c.num;
	}

	private void setCustInfo(){
		cust.status = OrmsConstants.ACTIVE_STATUS;
		cust.hPhone = "128612645";//128612345
		cust.address = "QaTest-CusotmerProfile-1 Street";
		cust.supplementalAddress = "QaTest-CusotmerProfile-1 Supp Street";
		cust.city = "Ballston Spa";//University Hall
		cust.county = "Saratoga";//Marion County
		cust.state = "New York";//Mississippi
		cust.zip = "12020";
		cust.country = "United States";
	}

	private void verifySearchResult(){
		custSearchPg.searchCust(cust_1);
		this.setCustInfo();
		custSearchPg.verifySearchCustomerProfileResult(cust);
	}

	private void verifyErrorMessage(String expectMsg) {
		custSearchPg.searchCust(cust_1);
		String actualMsg = custSearchPg.getWarnMes();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			throw new ErrorOnPageException("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}

	private String str = "No results found matching the search criteria. Please re-enter.";
}
