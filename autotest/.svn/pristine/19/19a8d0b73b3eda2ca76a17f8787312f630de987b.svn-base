package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.CleanUpSwitch;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: About the include area code share: 1. If the telephone number:7+ if not select 'include area code' check box, can not find result
 *                                                  2. If the telephone number:7|7- do not care 'include area code' check box, always could find result
 *                                                  4. If the telephone number: 7- which don't have area code if unselect 'include area code' check box the result will find.
 * @Preconditions:
 * @SPEC: Search Customer Profile
 * @Task#: Auto-508

 * @author SWang
 * @Date Jun 14, 2011
 */
public class SearchCustomerProfileByPhoneNum extends LicenseManagerTestCase{
	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private CleanUpSwitch cleanUpList = cust.new CleanUpSwitch();
	private Customer cust_1 = new Customer();
	private String msg="";
	private boolean noResultFound = false;
	private boolean businessCustNum = false;
	private boolean justVerifyCustNum = false;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		String custNumForIndividual = lm.getCustomerNum(cust, schema);
		String custNumForBusiness = lm.getCustomerNum(cust_1, schema);

		//Verify Customer Profile Search Execution 
		int cursor = 0;
		int startPoint = 0;
		int endPoint = 999;
		while (!dpIter.dpDone()) {
			if (cursor < startPoint || cursor > endPoint) {
				cursor++;
				dpIter.dpNext();
				continue;
			}

			this.readCustSearchCriteriaFromDP();
			//Search customer
			custSearchPg.clearUpSearchCriteria(cleanUpList);
			custSearchPg.searchCust(cust);

			//Verify warning message when no search result
			if(noResultFound){
				verifyErrorMessage(str);
			}else{
				//Verify Given customer in search result list
				if(businessCustNum){
					custSearchPg.verifyKnownCustNumInSearchList(custNumForBusiness, true);
				}else{
					custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
				}
				if(!justVerifyCustNum){
					//Verify research results list
					this.readEcpectedSearchResultInfoFromDp();
					custSearchPg.verifySearchCustomerProfileResult(cust);
				}
			}

			logger.info("Successfully verify "+cursor+"-"+msg+"...");

			dpIter.dpNext();
			cursor++;
		}

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		dpFileName = AwoUtil.generateDatapoolPath(this.getClass());
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//Customer info
		cust.customerClass = "INDIVIDUAL";
		cust.fName = "QA-CustSearch";
		cust.mName = "QATest-CustSearch";
		cust.lName = "TEST-CustSearch";
		cust.dateOfBirth = "Jun 08 1981";
		cleanUpList.turnOnAllSwitch();
		
		cust_1.customerClass = "BUSINESS";
		cust_1.businessName = "@QaTest-CusotmerProfile-2";
		cust_1.fName = "QA-Customer2";
		cust_1.mName = "QaTest-CusotmerProfile-2";
		cust_1.lName = "TEST-Profile2";
		cust_1.dateOfBirth = "Jan 14 1977";
	}

	public void readCustSearchCriteriaFromDP(){
		msg=dpIter.dpString("comment");
		noResultFound = dpIter.dpBoolean("noResultFound");
		businessCustNum = dpIter.dpBoolean("custNumForCommercial");
		justVerifyCustNum = dpIter.dpBoolean("justVerifyCustNum");
		cust.licenseType = dpIter.dpString("licenseType");
		cust.licenseNum = dpIter.dpString("licenseNum");
		cust.licenseState = dpIter.dpString("licenseState");
		cust.customerClass = dpIter.dpString("customerClass");
		cust.status = dpIter.dpString("status");
		cust.lName = dpIter.dpString("lName");
		cust.fName = dpIter.dpString("fName");
		cust.mName = dpIter.dpString("mName");
		cust.businessName = dpIter.dpString("businessName");
		cust.dateOfBirth = dpIter.dpString("dateOfBirth");
		cust.hPhone = dpIter.dpString("hPhone");
		cust.bPhone = dpIter.dpString("bPhone");
		cust.mPhone = dpIter.dpString("mPhone");
		cust.includeAreaCode = dpIter.dpBoolean("includeAreaCode");
		cust.address = dpIter.dpString("address");
		cust.supplementalAddress = dpIter.dpString("suppAddress");
		cust.city = dpIter.dpString("city");
		cust.county = dpIter.dpString("county");
		cust.state = dpIter.dpString("state");
		cust.zip = dpIter.dpString("zip");
		cust.country = dpIter.dpString("country");
	}

	public void readEcpectedSearchResultInfoFromDp(){
		cust.status = dpIter.dpString("resultStatus");
		cust.lName = dpIter.dpString("resultLName");
		cust.fName = dpIter.dpString("resultFName");
		cust.mName = dpIter.dpString("resultMName");
		cust.businessName = dpIter.dpString("resultBusinessName");
		cust.dateOfBirth = dpIter.dpString("resultDateOfBirth");
		cust.customerClass = dpIter.dpString("resultCustomerClass");
		cust.hPhone = dpIter.dpString("resultPhone");
		cust.address = dpIter.dpString("resultAddress");
		cust.supplementalAddress = dpIter.dpString("resultSuppAddress");
		cust.city = dpIter.dpString("resultCity");
		cust.county = dpIter.dpString("resultCounty");
		cust.state = dpIter.dpString("resultState");
		cust.zip = dpIter.dpString("resultZip");
		cust.country = dpIter.dpString("resultCountry");
	}

	public void verifyErrorMessage(String expectMsg) {
		custSearchPg.clickSearch();
		ajax.waitLoading();
		String actualMsg = custSearchPg.getWarnMes();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			throw new ErrorOnPageException("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}

	//Warning message
	private String str = "No results found matching the search criteria. Please re-enter.";
}
