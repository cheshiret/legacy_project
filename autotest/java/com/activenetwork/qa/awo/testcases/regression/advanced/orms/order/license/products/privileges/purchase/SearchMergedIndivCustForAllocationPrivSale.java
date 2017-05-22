package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAuthorizationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This test case was designed to verify merged individual customer search for Outfitter business customer purchase Allocation Privilege
 * @Preconditions:
 * update X_PROP set value=True where name='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale'
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=2990,3000
 * d_hf_merge_cust:id=30
 * @SPEC: 
 * Purchase Privilege - Search Merged Customer [TC:102746] -- Step9
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 29, 2014
 */
public class SearchMergedIndivCustForAllocationPrivSale extends
		LicenseManagerTestCase {
	private Customer mergedIndivCust, activeIndivCust, outfitterCust;
	private PrivilegeInfo outfitterPrivilege;
	private String alertMsg;
	
	@Override
	public void execute() {
		verifyCustInfoForTest();
		// Purchase the outfitter privilege by the outfitter customer
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(outfitterCust);
		lm.gotoAddItemPgFromCustDetailPg(outfitterCust.residencyStatus);
		verifyAlertMsgForIndivCustSearch(outfitterPrivilege, mergedIndivCust, alertMsg);
		confirmCustToOrdCartPg();
		lm.goToCart();
		String ordNum = lm.processOrderCart(pay);
		//TODO Purchase the authorized privilege by the individual customer
		lm.reversePrivilegeOrderToCleanUp(ordNum, "14 - Other", "Automation Test", pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Hudson Bay(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		activeIndivCust = new Customer();
		activeIndivCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		activeIndivCust.lName = "TEST-IndivMergeCust01";
		activeIndivCust.fName = "QA-IndivMergeCust01";
		activeIndivCust.dateOfBirth = "Mar 01 1984";
		activeIndivCust.identifier.identifierType = IDENT_TYPE_HAL;
		activeIndivCust.identifier.identifierNum = lm.getCustomerNumByCustNameStatus(activeIndivCust.lName, activeIndivCust.fName, StringUtil.EMPTY, StringUtil.EMPTY, schema, "1");
		activeIndivCust.custNum = activeIndivCust.identifier.identifierNum;
		activeIndivCust.residencyStatus = RESID_STATUS_SK;
		
		mergedIndivCust =  new Customer();
		mergedIndivCust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		mergedIndivCust.lName = activeIndivCust.lName;
		mergedIndivCust.fName = activeIndivCust.fName;
		mergedIndivCust.dateOfBirth = activeIndivCust.dateOfBirth;
		mergedIndivCust.identifier.identifierType = IDENT_TYPE_HAL;
		mergedIndivCust.identifier.identifierNum = lm.getCustomerNumByCustNameStatus(mergedIndivCust.lName, mergedIndivCust.fName, StringUtil.EMPTY, StringUtil.EMPTY, schema, "4");
		mergedIndivCust.custNum = mergedIndivCust.identifier.identifierNum;
		mergedIndivCust.residencyStatus = RESID_STATUS_SK;
		
		outfitterCust = new Customer();
		outfitterCust.customerClass = OrmsConstants.OUTFITTER_CUSTOMER_CLASS;
		outfitterCust.businessName = "Outfitter NameA";
		outfitterCust.businessNum = "SKONUM00001";
		outfitterCust.fName = "OutfitterBusi_FN1";
		outfitterCust.lName = "OutfitterBusi_LN1";
		outfitterCust.dateOfBirth = "1984-01-01";
		outfitterCust.identifier.id = IDEN_HAL_ID;
		outfitterCust.identifier.identifierType = IDENT_TYPE_HAL;
		outfitterCust.identifier.identifierNum = lm.getCustomerNumByCustName(outfitterCust.lName, outfitterCust.fName, StringUtil.EMPTY, outfitterCust.businessName, schema);
		outfitterCust.residencyStatus = RESID_STATUS_SK;
		
		// privilege info - outfitter
		outfitterPrivilege = new PrivilegeInfo();
		outfitterPrivilege.code = "OPA";
		outfitterPrivilege.name = "OutfitterPrivAuth";
		outfitterPrivilege.purchasingName = outfitterPrivilege.code + "-" + outfitterPrivilege.name;
		outfitterPrivilege.licenseYear = lm.getFiscalYear(schema);
		
		alertMsg = ".*HAL ID.*"+mergedIndivCust.identifier.identifierNum+".*merged with another Customer.*use HAL ID.*"+activeIndivCust.identifier.identifierNum+".*for future transactions.*";
	}
	
	private void verifyCustInfoForTest() {
		logger.info("Verify customer status for test.");
		
		if(StringUtil.isEmpty(mergedIndivCust.identifier.identifierNum))
			throw new ItemNotFoundException("Failed to find merged customer for last-name:"+mergedIndivCust.lName+", first-name"+mergedIndivCust.fName);
		if(StringUtil.isEmpty(activeIndivCust.identifier.identifierNum))
			throw new ItemNotFoundException("Failed to find activeCust customer for last-name:"+activeIndivCust.lName+", first-name"+activeIndivCust.fName);
		logger.info("Merged customer num:"+mergedIndivCust.identifier.identifierNum+", Active customer num:"+activeIndivCust.identifier.identifierNum);
	}
	
	private void verifyAlertMsgForIndivCustSearch(PrivilegeInfo privilege, Customer cust, String msg) {
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage	.getInstance();
		LicMgrPrivilegeAuthorizationWidget privAuthorizWidget = LicMgrPrivilegeAuthorizationWidget.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance(true);
		
		logger.info("Add privilege item - " + privilege.purchasingName + ".");
		addItemPg.addProductToCart(privilege.purchasingName, privilege.licenseYear, privilege.qty);
		privAuthorizWidget.waitLoading();
		logger.info("Input the customer info: number=" + cust.custNum + "; date of birth=" + cust.dateOfBirth + " who will be authorized to purchase the privielge.");
		privAuthorizWidget.setCustInfo(cust.custNum, cust.dateOfBirth);
		privAuthorizWidget.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget, confirmCustPg);
		if(pages!=confirmWidget)
			throw new TestCaseFailedException("Alert widget should be existed for search customer.");
		String content = confirmWidget.getErrorMsg();
		if(!content.matches(msg))
			throw new ErrorOnPageException("Alert widget content displayed un-correctly.", msg, content);
		logger.info("---Verify alert widget and content successfully.");
	}

	private void confirmCustToOrdCartPg() {
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance(true);
		LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage	.getInstance();
		//TODO how to identify two widgets without title
		confirmWidget.clickOK();
		ajax.waitLoading();
		confirmPg.waitLoading();
		
		confirmPg.clickOK();
		ajax.waitLoading();
		addItemPg.waitLoading();
	}
}
