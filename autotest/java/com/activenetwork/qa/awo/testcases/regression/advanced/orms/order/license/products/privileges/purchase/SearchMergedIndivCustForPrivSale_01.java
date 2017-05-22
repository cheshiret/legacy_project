package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This test case was designed to verify merged/inactive individual customer search for purchase privilege
 * @Preconditions:
 * update X_PROP set value=True where name='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale'
 * There are two individual customers, merge cust1 to cust2, search cust1 then cust2 will be returned;
 * Change cust2 status to inactive, and search cust2, no customer found
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=2990,3000
 * d_hf_merge_cust:id=30
 * 
 * @SPEC: 
 * Purchase Privilege - Search Merged Customer [TC:102746] -- Step2, Step3, Step5
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 15, 2014
 */
public class SearchMergedIndivCustForPrivSale_01 extends LicenseManagerTestCase {

	private Customer mergedCust, activeCust;
	private String alertMsg01, alertMsg02;
	
	@Override
	public void execute() {
		boolean updated = false;
		try {
			verifyCustInfoForTest();
			lm.loginLicenseManager(login);
			verifyAlertMsgForCustSearchDuringSale(mergedCust, alertMsg01, true);
			gotoCustconfirmPgFromConfirmWidget();
			
			lm.updateCustomerStatus(activeCust, "Active", "Inactive");
			updated = true;
			lm.gotoHomePage();
			verifyAlertMsgForCustSearchDuringSale(activeCust, alertMsg02, false);
			cancelFromConfirmWidget();
			
			lm.logOutLicenseManager();
		} finally {
			if(updated) {
				lm.loginLicenseManager(login);
				lm.updateCustomerStatus(activeCust, "Inactive", "Active");
				lm.logOutLicenseManager();
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		activeCust = new Customer();
		activeCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		activeCust.lName = "TEST-IndivMergeCust01";
		activeCust.fName = "QA-IndivMergeCust01";
		activeCust.dateOfBirth = "Mar 01 1984";
		activeCust.identifier.identifierType = IDENT_TYPE_HAL;
		activeCust.identifier.identifierNum = lm.getCustomerNumByCustNameStatus(activeCust.lName, activeCust.fName, StringUtil.EMPTY, StringUtil.EMPTY, schema, "1");
		activeCust.residencyStatus = RESID_STATUS_SK;
		
		mergedCust =  new Customer();
		mergedCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		mergedCust.lName = activeCust.lName;
		mergedCust.fName = activeCust.fName;
		mergedCust.dateOfBirth = activeCust.dateOfBirth;
		mergedCust.identifier.identifierType = IDENT_TYPE_HAL;
		mergedCust.identifier.identifierNum = lm.getCustomerNumByCustNameStatus(mergedCust.lName, mergedCust.fName, StringUtil.EMPTY, StringUtil.EMPTY, schema, "4");
		mergedCust.residencyStatus = RESID_STATUS_SK;
		
		alertMsg01 = ".*HAL ID.*"+mergedCust.identifier.identifierNum+".*merged with another Customer.*use HAL ID.*"+activeCust.identifier.identifierNum+".*for future transactions.*";
		alertMsg02 = "^No customer found.*";
	}
	
	private void verifyCustInfoForTest() {
		logger.info("Verify customer status for test.");
		
		if(StringUtil.isEmpty(mergedCust.identifier.identifierNum))
			throw new ItemNotFoundException("Failed to find merged customer for last-name:"+mergedCust.lName+", first-name"+mergedCust.fName);
		if(StringUtil.isEmpty(activeCust.identifier.identifierNum))
			throw new ItemNotFoundException("Failed to find activeCust customer for last-name:"+activeCust.lName+", first-name"+activeCust.fName);
		logger.info("Merged customer num:"+mergedCust.identifier.identifierNum+", Active customer num:"+activeCust.identifier.identifierNum);
	}

	private void verifyAlertMsgForCustSearchDuringSale(Customer cust, String msg, boolean noTitle) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance(noTitle);
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
	
	private void cancelFromConfirmWidget() {
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		confirmWidget.clickCancel();
		ajax.waitLoading();
		homePg.waitLoading();
	}
}
