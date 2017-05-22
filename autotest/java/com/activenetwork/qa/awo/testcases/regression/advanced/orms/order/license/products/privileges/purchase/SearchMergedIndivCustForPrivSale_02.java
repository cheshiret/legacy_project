package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This test case was designed to verify merged individual customer search for purchase privilege.
 * cust1 merged to cust2, cust2 merged to cust3, and search cust1/cust2 for purchase privilege, cust3 will be returned.
 * @Preconditions:
 * update X_PROP set value=True where name='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale'
 * There are three individual customers, two of them status were merged, and one was active
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=3010,3020,3310
 * d_hf_merge_cust:id=40
 * TODO how to setup two merged customers and one active customer
 * @SPEC: 
 * Purchase Privilege - Search Merged Customer [TC:102746]  -- Step6
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 29, 2014
 */
public class SearchMergedIndivCustForPrivSale_02 extends LicenseManagerTestCase {

	private Customer mergedCust01, mergedCust02, activeCust;
	private String alertMsg01, alertMsg02;
	
	@Override
	public void execute() {
		verifyCustInfoForTest();
		lm.loginLicenseManager(login);
		verifyAlertMsgForCustSearchDuringSale(mergedCust01, alertMsg01);
		gotoCustconfirmPgFromConfirmWidget();
		
		lm.gotoHomePage();
		verifyAlertMsgForCustSearchDuringSale(mergedCust02, alertMsg02);
		gotoCustconfirmPgFromConfirmWidget();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		activeCust = new Customer();
		activeCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		activeCust.lName = "TEST-IndivMergeCust02";
		activeCust.fName = "QA-IndivMergeCust02";
		activeCust.dateOfBirth = "19840401";
		activeCust.identifier.identifierType = IDENT_TYPE_HAL;
		activeCust.identifier.identifierNum = lm.getCustomerNumByCustNameStatus(activeCust.lName, activeCust.fName, StringUtil.EMPTY, StringUtil.EMPTY, schema, "1");
		activeCust.residencyStatus = RESID_STATUS_SK;
		
		mergedCust01 =  new Customer();
		mergedCust01.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		mergedCust01.lName = activeCust.lName;
		mergedCust01.fName = activeCust.fName;
		mergedCust01.dateOfBirth = activeCust.dateOfBirth;
		mergedCust01.identifier.identifierType = IDENT_TYPE_HAL;
		mergedCust01.residencyStatus = RESID_STATUS_SK;
		
		mergedCust02 =  new Customer();
		mergedCust02.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		mergedCust02.lName = activeCust.lName;
		mergedCust02.fName = activeCust.fName;
		mergedCust02.dateOfBirth = activeCust.dateOfBirth;
		mergedCust02.identifier.identifierType = IDENT_TYPE_HAL;
		mergedCust02.residencyStatus = RESID_STATUS_SK;
		
		alertMsg01 = ".*HAL ID.*"+mergedCust01.identifier.identifierNum+".*merged with another Customer.*use HAL ID.*"+activeCust.identifier.identifierNum+".*for future transactions.*";
		alertMsg02 = ".*HAL ID.*"+mergedCust02.identifier.identifierNum+".*merged with another Customer.*use HAL ID.*"+activeCust.identifier.identifierNum+".*for future transactions.*";
	}
	
	
	private void verifyCustInfoForTest() {
		logger.info("Verify customer status for test.");
		List<String> custNums = lm.getAllCustNumsByCustNameStatus(activeCust.lName, activeCust.fName, activeCust.mName, activeCust.businessName, schema, "4");
		if(custNums==null || custNums.size()!=2)
			throw new ErrorOnDataException("There should be two merged customers with  last-name:"+activeCust.lName+", first-name"+activeCust.fName+". Please check test case pre-condition.");
		mergedCust01.identifier.identifierNum = custNums.get(0);
		mergedCust02.identifier.identifierNum = custNums.get(1);
		
		logger.info("Merged customer01 num:"+mergedCust01.identifier.identifierNum+"Merged customer02 num:"+mergedCust02.identifier.identifierNum+", Active customer num:"+activeCust.identifier.identifierNum);
	}

	private void verifyAlertMsgForCustSearchDuringSale(Customer cust, String msg) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance(true);
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
		
		homePg.identifyCustomer(cust.customerClass,
				cust.dateOfBirth, cust.identifier.identifierType, cust.identifier.identifierNum,
				cust.identifier.country, cust.identifier.state);
		homePg.clickPurchasePrivilege();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget, confirmPg);
		if(pages!=confirmWidget)
			throw new TestCaseFailedException("Alert widget should be existed for search customer.");
		String content = confirmWidget.getErrorMsg();
		if(!content.matches(msg))
			throw new ErrorOnPageException("Alert widget content displayed un-correctly.", msg, content);
		logger.info("---Verify alert widget and content successfully.");
	}
	
	private void gotoCustconfirmPgFromConfirmWidget() {
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance(true);
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
		
		confirmWidget.clickOK();
		ajax.waitLoading();
		confirmPg.waitLoading();
	}
}

