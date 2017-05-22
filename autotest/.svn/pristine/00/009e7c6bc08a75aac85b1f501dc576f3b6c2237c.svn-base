package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrValidFromDateTime;
import com.activenetwork.qa.awo.supportscripts.function.admin.AssignFeaturesFunction;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Confirm with Li Ning that test Quick Sale flow in MO contract.[2014-4-21] Write this case on QA4(ORMS3.05.01.121)
 * This test case was designed for verify privilege quick sale workflow
 * (1) Verify 'Privilege Quick Sale' button on license manager home page for role's feature;
 * TODO select a identifier type which is not configured for Privilege Quick Sale and verify error message;
 * TODO select a contract which has no configuration of Privilege Quick Sale even role's feature has been assigned;
 * TODO select a customer class which is not configured for Privilege Quick Sale
 * (2) Verify alert for input customer DOB and customer# who has no identifier;
 * (3) Verify alert for input wrong customer DOB;
 * (4) Verify alert for input new customer info;
 * (5) For privilege with Consecutive Days indicator Yes, verify valid from date object number on UI;
 * (6) For privilege with Default to Blank=yes, verify there is no default value on UI;
 * @Preconditions:
 * (1) Configure of 'Require Full Customer Profile For Quick Sale'
 * insert into X_PROP (ID, NAME, NAMESPACE, TYPE, VALUE)
values (CONTRACT_SEQ.nextval, 'IsRequireFullCustomerProfileForQuickSale', 'Contract', 'Boolean', 'True');
 * (2) Configure of 'Customer Class support Quick Sale'
 * insert into X_PROP (ID, NAME, NAMESPACE, TYPE, VALUE)
values (CONTRACT_SEQ.nextval, 'CustomerClassForQuickSale', 'Contract', 'String', '1');
 * (3) Configure Identifier Type for Privilege Quick Sale Indicator
 * update ALL_C_IDENTIFIER_TYPE set quick_sale_ind = 1 where id = 3 and contract='MO';
 * (4) Prompt for Valid From Date, Per Privilege, Valid From Date plus Valid Days/Years, Valid select Days, Consecutive Days Indicator displayed;
 * @LinkSetUp:
 * d_assign_feature:id=5942
 * d_hf_add_privilege_prd:id=2820,2830,2840,2850
 * d_hf_add_pricing:id=4262,4272,4282,4292
 * d_hf_assi_pri_to_store:id=2040,2060,2070,2080
 * d_hf_add_prvi_license_year:id=2950,2960,2970,2980
 * d_hf_add_qty_control:id=2030,2040,2050,2060
 * d_hf_add_cust_profile:id=2960 
 * Note: MO contract, no business class customer
 * 
 * @SPEC: 
 * Purchase Privilege (New & Duplicate) - Privilege Quick Sale [TC:109490] 
 * Purchase Privilege - Privilege Quick Sale [TC:109492] 
 * Get Privilege Product List for Purchase - Privilege Quick Sale [TC:109489] 
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 14, 2014
 */
public class PrivilegeQuickSale extends LicenseManagerTestCase {

	private LoginInfo loginAM;
	private RoleInfo role;
	private String validFromDate;
	private PrivilegeInfo privilege01, privilege02, privilege03, privilege04;
	private String msg01, msg02, msg03;
	private LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage.getInstance();
	
	@Override
	public void execute() {
		//Unassign feature 'PrivilegeQuickSale' to role 'MO Mod 1 - Auto', and verify 'Privilege Quick Sale' button is NOT on LM home page 
		new AssignFeaturesFunction().execute(loginAM, role);
		lm.loginLicenseManager(login);
		verifyPrivilgeQuickSaleBtnOnLMHomePg(false);
		lm.logOutLicenseManager();
		
		//Assign feature 'PrivilegeQuickSale' to role 'MO Mod 1 - Auto', and verify 'Privilege Quick Sale' button is available on LM home page 
		role.unAssignOrNot = false;
		new AssignFeaturesFunction().execute(loginAM, role);
		lm.loginLicenseManager(login);
		verifyPrivilgeQuickSaleBtnOnLMHomePg(true);

		//add customer identifier
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustomerIdentifierSubTab();
		lm.safeAddCustomerIdentifier(cust.identifier);
		lm.gotoHomePage();
		//verify alert for wrong DOB input
		verifyErrorMsgOfWrongDOBForPrivQuickSale();
				
		//remove customer identifier
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustomerIdentifierSubTab();
		lm.removeCustIdentifier(cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.gotoHomePage();
		verifyRequireIdentifierAlertForPrivQuickSale();
		//add customer identifier
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustomerIdentifierSubTab();
		lm.safeAddCustomerIdentifier(cust.identifier);
		lm.gotoHomePage();
		
		verifyNoCustFoundAlertForPrivQuickSale();
		cancelFromAlertWidget();
		
		//Verify all privilege will be listed as normal sale flow
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		addItemPg.verifyPrivExistence(privilege01.code, privilege01.name, privilege01.licenseYear, true);
		addItemPg.verifyPrivExistence(privilege02.code, privilege02.name, privilege02.licenseYear, true);
		addItemPg.verifyPrivExistence(privilege03.code, privilege03.name, privilege03.licenseYear, true);
		addItemPg.verifyPrivExistence(privilege04.code, privilege04.name, privilege04.licenseYear, true);
		
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		addItemPg.verifyPrivExistence(privilege01.code, privilege01.name, privilege01.licenseYear, true);
		addItemPg.verifyPrivExistence(privilege02.code, privilege02.name, privilege02.licenseYear, true);
		addItemPg.verifyPrivExistence(privilege03.code, privilege03.name, privilege03.licenseYear, true);
		addItemPg.verifyPrivExistence(privilege04.code, privilege04.name, privilege04.licenseYear, true);
		
		//Privilege Quick Sale from home page;
		lm.gotoHomePage();
		lm.gotoAddItemPgFromPrivilegeQuickSale(cust);
		verifyPrivilegeValidFromDateTime(privilege01, validFromDate, true);
		verifyPrivilegeValidFromDateTime(privilege02, validFromDate, false);
		verifyDefaultValue(privilege03, "");
		verifyDefaultValue(privilege04, validFromDate);
		lm.goToCart();
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MO Contract";
		login.location = "MO Mod 1/MO Auto Store";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MO";
		
		loginAM = new LoginInfo();
		loginAM.url = login.url;
		loginAM.userName = TestProperty.getProperty("orms.adm.user");
		loginAM.password = TestProperty.getProperty("orms.adm.pw");
		loginAM.contract = login.contract;
		loginAM.location = "MO Admin - Auto/MO Department of Conservation";
		
		role = new RoleInfo();
		role.roleName = "MO Mod 1 - Auto";
		role.feature = "PrivilegeQuickSale";
		role.application = "LicenseManager";
		role.unAssignOrNot = true;
		
		//privilege with Consecutive Days indicator Yes
		privilege01 = new PrivilegeInfo();
		privilege01.code = "QSP";
		privilege01.name = "QuickSalePri01";
		privilege01.purchasingName = "QSP-QuickSalePri01";
		privilege01.licenseYear = lm.getFiscalYear(schema);
		privilege01.qty = "2";//IMPORTANT, do not change
		
		//privilege with Consecutive Days indicator No
		privilege02 = new PrivilegeInfo();
		privilege02.code = "QSN";
		privilege02.name = "QuickSalePri02";
		privilege02.purchasingName = "QSN-QuickSalePri02";
		privilege02.licenseYear = lm.getFiscalYear(schema);
		privilege02.qty = "2";//IMPORTANT, do not change
		
		//privilege with Default to Blank Yes 
		privilege03 = new PrivilegeInfo();
		privilege03.code = "QPY";
		privilege03.name = "QuickSalePri03";
		privilege03.purchasingName = "QPY-QuickSalePri03";
		privilege03.licenseYear = lm.getFiscalYear(schema);
		privilege03.qty = "1";
		
		//privilege with Default to Blank No
		privilege04 = new PrivilegeInfo();
		privilege04.code = "QPN";
		privilege04.name = "QuickSalePri04";
		privilege04.purchasingName = "QPN-QuickSalePri04";
		privilege04.licenseYear = lm.getFiscalYear(schema);
		privilege04.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-QuickSaleIndiv";
		cust.lName = "TEST-QuickSaleIndiv";
		cust.dateOfBirth = "1982-01-01";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "19820101";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		validFromDate = DateFunctions.formatDate(DateFunctions.getCurrentDate(), "EEE MMM dd yyyy");
		
		msg01 = ".*select.*Identifier Types.*Privilege sale via quick flow.*";//TODO select identifier types from DB
		msg02 = ".*No Customer found.*information provided.*re-enter.*";
		msg03 = ".*No customer found.*add a new profile for this customer.*";
		
		pay.payType = Payment.PAY_CASH;
	}
	
	private void verifyPrivilgeQuickSaleBtnOnLMHomePg(boolean existed) {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		logger.info("Verify privilege quick sale button "+(existed == Boolean.TRUE?"displayed":"Not")+" on license manager home page.");
		if(existed && !homePg.checkPrivilegeQuickSaleBtnExisted())
			throw new ErrorOnPageException("'Privilege Quick Sale' button should displayed on home page.");
		else if(!existed && homePg.checkPrivilegeQuickSaleBtnExisted())
			throw new ErrorOnPageException("'Privilege Quick Sale' button should Not displayed on home page.");
		logger.info("---Verify privilege quick sale button "+(existed == Boolean.TRUE?"displayed":"Not")+" successfully on license manager home page.");
	}
	
	private void verifyRequireIdentifierAlertForPrivQuickSale() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		logger.info("Input customer DOB and customer# which has no identifiers.");
		homePg.identifyCustomer(cust.customerClass, cust.dateOfBirth, 
				"Conservation #",
				cust.custNum,
				cust.identifier.country, 
				cust.identifier.state);
		homePg.clickPrivilegeQuickSale();
		ajax.waitLoading();
		homePg.waitLoading();
		String msgUI = homePg.getErrorMessage();
		if(StringUtil.isEmpty(msgUI))
			throw new TestCaseFailedException("Failed to get Require Identifier error message on top of home page.");
		if(!msgUI.matches(msg01))
			throw new ErrorOnPageException("Require Identifier error message displayed un-correctly.", msg01, msgUI);
		logger.info("---Verify Require Identifier error message on top of home page successfully.");
	}
	
	private void verifyErrorMsgOfWrongDOBForPrivQuickSale() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		logger.info("Input wrong customer DOB.");
		homePg.identifyCustomer(cust.customerClass, DateFunctions.getDateAfterGivenDay(cust.dateOfBirth, 10), 
				cust.identifier.identifierType,
				cust.identifier.identifierNum,
				cust.identifier.country, 
				cust.identifier.state);
		homePg.clickPrivilegeQuickSale();
		ajax.waitLoading();
		homePg.waitLoading();
		String msgUI = homePg.getErrorMessage();
		if(StringUtil.isEmpty(msgUI))
			throw new TestCaseFailedException("Failed to get Wrong Input error message on top of home page.");
		if(!msgUI.matches(msg02))
			throw new ErrorOnPageException("Wrong Input error message displayed un-correctly.", msg02, msgUI);
		logger.info("---Verify Wrong Input error message on top of home page successfully.");
	}
	
	private void verifyNoCustFoundAlertForPrivQuickSale() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		
		logger.info("Input new customer info.");
		homePg.identifyCustomer(cust.customerClass, DateFunctions.getDateAfterToday(-30), 
				cust.identifier.identifierType,
				DateFunctions.formatDate(DateFunctions.getDateAfterToday(-30),"yyyyMMdd"),
				cust.identifier.country, 
				cust.identifier.state);
		homePg.clickPrivilegeQuickSale();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget, confirmCustPg);
		if(pages!=confirmWidget)
			throw new TestCaseFailedException("Alert for New Customer should displayed.");
		String content = confirmWidget.getErrorMsg();
		if(!content.matches(msg03))
			throw new ErrorOnPageException("Alert content for New Customer displayed un-correctly.", msg03, content);
		logger.info("---Verify alert content for New Customer successfully.");
	}
	
	private void cancelFromAlertWidget() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance();
		
		confirmWidget.clickCancel();
		ajax.waitLoading();
		homePg.waitLoading();
	}
	
	private void verifyPrivilegeValidFromDateTime(PrivilegeInfo privilege, String validFromDate, boolean indicator) {
		LicMgrOrigPrivSaleAddItemPage addItemPage = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime.getInstance();
		LicMgrPrivilegeInventoryWidget privInvWidget = LicMgrPrivilegeInventoryWidget.getInstance();
		
		addItemPage.addProductToCart(privilege.purchasingName, privilege.licenseYear, privilege.qty);
		Object pages = browser.waitExists(validFromDateTime, addItemPage);
		
		if(pages!=validFromDateTime)
			throw new ErrorOnPageException("Valid from date time widget should exist.");
		
		int validObjUI = validFromDateTime.getVaildDateTimeTextObject();
		if(indicator){
			if(validObjUI!=1)
				throw new ErrorOnPageException("Valid from date time should be ONLY one.");
			String validFromDateUI = validFromDateTime.getValidFromDate();
			if(StringUtil.isEmpty(validFromDateUI) || !validFromDateUI.equals(validFromDate))
				throw new ErrorOnPageException("Valid From Date display un-correctly on page.", validFromDate, validFromDateUI);
		} else if(!indicator) {
			//it is hard code here, for select quantity=2, we verify valid from date object numbers on UI
			if(validObjUI!=2)
				throw new ErrorOnPageException("Valid from date time should be two.");
			String validFromDateUI0 = validFromDateTime.getValidFromDate();
			String validFromDateUI1 = validFromDateTime.getValidFromDate(1);
			if(StringUtil.isEmpty(validFromDateUI0) || !validFromDateUI0.equals(validFromDate))
				throw new ErrorOnPageException("Valid From Date display un-correctly on page.", validFromDate, validFromDateUI0);
			if(StringUtil.isEmpty(validFromDateUI1) || !validFromDateUI0.equals(validFromDate))
				throw new ErrorOnPageException("Valid From Date display un-correctly on page.", validFromDate, validFromDateUI1);
		}
		logger.info("---Verify valid from date time successfully.");
		
		validFromDateTime.setValidFromDateTime(validFromDate);//trigger for hidden date component
		validFromDateTime.clickOK();
		ajax.waitLoading();
		pages = browser.waitExists(privInvWidget, addItemPage);
		if(pages == privInvWidget) {
			//use default inventory
			privInvWidget.clickOK();
			ajax.waitLoading();
			addItemPage.waitLoading();	
		}
	}
	
	private void verifyDefaultValue(PrivilegeInfo privilege, String defaultValue) {
		LicMgrOrigPrivSaleAddItemPage addItemPage = LicMgrOrigPrivSaleAddItemPage.getInstance();
		LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime.getInstance();
		
		addItemPage.addProductToCart(privilege.purchasingName, privilege.licenseYear, privilege.qty);
		validFromDateTime.waitLoading();
		
		String validFromDateUI = validFromDateTime.getValidFromDate();
		if(StringUtil.isEmpty(defaultValue) && !StringUtil.isEmpty(validFromDateUI))
			throw new ErrorOnPageException("Valid From Date default value should be blank.");
		else if(StringUtil.notEmpty(defaultValue) && !validFromDateUI.equals(defaultValue))
			throw new ErrorOnPageException("Valid From Date default value displayed un-correctly on page.", validFromDate, validFromDateUI);
		
		logger.info("---Verify valid from date default value successfully.");
		
		validFromDateTime.setValidFromDateTime(validFromDate);//trigger for hidden date component
		validFromDateTime.clickCancel();
		ajax.waitLoading();
		addItemPage.waitLoading();
	}

}
