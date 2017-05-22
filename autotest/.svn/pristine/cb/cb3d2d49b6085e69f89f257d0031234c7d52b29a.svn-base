package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.accountoverview;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Check orders' number, privileges' name and licenses' year, orders transaction type, order items text in account overview and order history page
 * 1. license order has active order items
 * 2. license order has Replacement order items
 * 3. license order has transferred order item
 * 4. license order has revoked order items
 * 
 * @Preconditions:
 * d_web_hf_signupaccount, id=240, hfsk_00005@webhftest.com, 2000-01-07
 * d_web_hf_signupaccount, id=260, hfsk_00006@webhftest.com, 2000-01-08
 * d_hf_add_privilege_prd, id=1870, 1880, 1890, 1900
 * d_hf_add_pricing, id=2492, 2602, 2642, 2502, 2612, 2652, 2512, 2622, 2662, 2522, 2632, 2672
 * d_hf_assi_pri_to_store, id=1120, 1130, 1140, 1150
 * d_hf_add_qty_control, id=1100, 1110, 1120, 1130
 * d_hf_add_prvi_license_year, id=1590, 1600, 1610, 1620
 * 
 * @SPEC:  Account Overview page - Recent Orders section (Licese Orders) [TC:050358]
 * 
 * @Task#: AUTO-1640
 * @author Swang
 * @Date  May 10, 2013
 */
public class VerifyRecentOrderNums extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private HFAccountOverviewPage acctOvePg = HFAccountOverviewPage.getInstance();
	private Suspension suspension = new Suspension();
	private String ordNum1, ordNum2, ordNum3, ordNum4, ordNum5, orderNotice1, orderNotice2, licenseYear;
	private PrivilegeInfo privilege2, privilege3;
	private String[] privilegeNames;

	public void execute() {
		//Precondition
		//* Add identifier for 1# account in HFSK
		hf.signIn(url, cus.email, cus.password);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		hf.signOut();

		//* Add identifier for 2# account in HFSK
		hf.signIn(url, cusNew.email, cus.password);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cusNew.email);
		hf.addIdentifier(cusNew.identifier);
		hf.signOut();

		//* Invalid all licenses for 1# account in LM
		lm.loginLicenseManager(login);
		lm.invalidateAllPrivilegesPerCustomer(cus, StringUtil.EMPTY, privilege.searchStatus, privilege.operateReason, privilege.operateNote);
		
		//* Remove added suspension for 1# account in LM
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();	

		//Make 4 order in LM
		//#1 license order has active order item
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cus, privilege);
		ordNum1 = lm.processOrderCart(pay);

		//#2 license order has Replacement order item
		String priNum = lm.getPrivilegeNumByOrdNum(schema, ordNum1);
		lm.replacePrivilegeToCartFromCustomerTopMenuByPrivilegeNums(cus, priNum);
		ordNum2 = lm.processOrderCart(pay); 

		//#3 license order has transferred order item
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cusNew, privilege2);
		ordNum3 = lm.processOrderCart(pay); 
		lm.gotoOrderPageFromOrdersTopMenu(ordNum3);
		String privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum3);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.transferPrivToOrderCart(cus, null);
		ordNum4 = lm.processOrderCart(pay); 

		//#4 license order has revoked order item
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cus, privilege3);
		ordNum5 = lm.processOrderCart(pay);

		privilege3.purchasingName = privilegeNames[0];
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension, privilege3);
		lm.logOutLicenseManager();
		
		//Check orders information in account overview and order history page in HFSK
		hf.signIn(url, cus.email, cus.password);
		String[] orderItemsText = verifyRecentOrdersSectionInAcountPg(new String[]{ordNum5, ordNum4, ordNum2, ordNum1}, privilegeNames, licenseYear); //Original, duplicate, transfer, revoke
		hf.gotoOrerHistoryPgFromAcctOvePg();
		verifyRecentOrdersHistory(new String[]{ordNum5, ordNum4, ordNum2, ordNum1}, privilegeNames, licenseYear, orderItemsText);
		hf.signOut();

		//Invalid customer licenses in LM
		lm.loginLicenseManager(login);
		lm.invalidateAllPrivilegesPerCustomer(cus, StringUtil.EMPTY, privilege.searchStatus, privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
								
		//Customer info
		cus.email = "hfsk_00005@webhftest.com";
		cus.dateOfBirth = "2000-01-07";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4125";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		cusNew.email = "hfsk_00006@webhftest.com";
		cusNew.dateOfBirth = "2000-01-08";
		cusNew.residencyStatus = RESID_STATUS_SK;
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "1R9Y4x4126";
		cusNew.identifier.state = "Saskatchewan";
		cusNew.residencyStatus = "Saskatchewan Resident - RCMP #";

		//Privileges Info
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.purchasingName = "SKA-HFSK License001";
		privilege2 = new PrivilegeInfo();
		privilege2.licenseYear = privilege.licenseYear;
		privilege2.purchasingName = "SKB-HFSK License002";	
		privilege3 = new PrivilegeInfo();
		privilege3.licenseYear = privilege.licenseYear;
		privilege3.purchasingName = "SKD-HFSK License004";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		privilege.searchStatus = new String[] {"Active", "Revoked"};
		
		privilegeNames = new String[4];
		privilegeNames[0] = "HFSK License004";
		privilegeNames[1] = "HFSK License002";
		privilegeNames[2] = "HFSK License001";
		privilegeNames[3] = "HFSK License001";

		orderNotice1 = "Transfer";
		orderNotice2 = "Replacement";
		licenseYear = hf.getFiscalYear(schema);

		//Suspension Info
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = "Angling Suspension";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";

		pay.payType = Payment.PAY_DEF_CASH;
	}

	/**
	 * Verify in account overview page
	 * 1. Order numbers
	 * 2. privilege names and license years
	 * 3. verify order notices
	 * 
	 * @param ordersNums
	 * @param privilegesName
	 * @param privilegeYear
	 * @return
	 */
	private String[] verifyRecentOrdersSectionInAcountPg(String[] ordersNums, String[] privilegesName, String privilegeYear){
		String[] orderNumsFromUI = acctOvePg.getAllRecentOrderNums();
		String[] orderItemsText = acctOvePg.getRecentOrderItemsText(orderNumsFromUI);
		String[] privilegesNameAndLicenseYearFromUI = acctOvePg.getAllRecentOrderPrivilegesNameAndLicenceYear();
		boolean result = true;

		logger.info("Verify order number in 'Account Overview' page");
		for(int i=0; i<orderNumsFromUI.length; i++){
			result &= MiscFunctions.compareResult(i+" - Order numbers", ordersNums[i], orderNumsFromUI[i]);
		}

		logger.info("Verify privilege name and license year in 'Account Overview' page");
		for(int i=0; i<privilegesNameAndLicenseYearFromUI.length; i++){
//			result &= MiscFunctions.compareResult(i+" - Order privilege name and license year", privilegesName[i]+" ("+privilegeYear+")", privilegesNameAndLicenseYearFromUI[i]);
			result &= MiscFunctions.startWithString(i+" - Order privilege name and license year", privilegesNameAndLicenseYearFromUI[i], privilegesName[i]+" ("+privilegeYear+")");
		}

		logger.info("Verify order noticie in 'Account Overview' page");
		result &= MiscFunctions.compareResult("Transfer order notice", orderNotice1, acctOvePg.getRecentOrderItemNotice(orderNumsFromUI[1]));
		result &= MiscFunctions.compareResult("Replacement order notice", orderNotice2, acctOvePg.getRecentOrderItemNotice(orderNumsFromUI[2]));

		if (!result) {
			throw new ErrorOnPageException("Not all check points are correct in 'Account Overview' page. Please check details from previous logs.");
		}
		logger.info("Successfully verify all check points in 'Account Overview' page.");

		return orderItemsText;
	}

	/**
	 * Verify in order history page
	 * 1. order numbers
	 * 2. privilege names and license years
	 * 3. order notices
	 * 4. order items text
	 * 
	 * @param ordersNums
	 * @param privilegesName
	 * @param privilegeYear
	 * @param orderItemsText
	 */
	public void verifyRecentOrdersHistory (String[] ordersNums, String[] privilegesName, String privilegeYear, String[] orderItemsText){
		HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();

		String[] orderNumsFromUI = orderHistoryPg.getAllOrderNums();
		String[] orderItemsTextFromUI = orderHistoryPg.getHistoryOrderItemsText(orderNumsFromUI);
		String[] privilegesNameAndLicenseYearFromUI = orderHistoryPg.getAllLicensesNameAndYear();
		boolean result = true;

		logger.info("Verify order number in 'Order History' page");
		for(int i=0; i<ordersNums.length; i++){
			result &= MiscFunctions.compareResult(i+" - Order numbers", ordersNums[i], orderNumsFromUI[i]);
		}

		logger.info("Verify privilege name and license year in 'Order History' page");
		for(int i=0; i<privilegesNameAndLicenseYearFromUI.length; i++){
//			result &= MiscFunctions.compareResult(i+" - Order privilege name and license year", privilegesName[i]+" ("+privilegeYear+")", privilegesNameAndLicenseYearFromUI[i]);
			result &= MiscFunctions.startWithString(i+" - Order privilege name and license year", privilegesNameAndLicenseYearFromUI[i], privilegesName[i]+" ("+privilegeYear+")");
		}

		logger.info("Verify order noticie in 'Order History' page");
		result &= MiscFunctions.compareResult("Transfer order notice", orderNotice1, orderHistoryPg.getHistoryOrderItemNotice(orderNumsFromUI[1]));
		result &= MiscFunctions.compareResult("Replacement order notice", orderNotice2, orderHistoryPg.getHistoryOrderItemNotice(orderNumsFromUI[2]));

		logger.info("Verify order history items text in 'Order History' page");
		for(int i=0; i<orderItemsText.length; i++){
			result &= MiscFunctions.compareResult(i+" - Order item text", orderItemsText[i], orderItemsTextFromUI[i]);
		}

		if (!result) {
			throw new ErrorOnPageException("Not all check points are correct in 'Order History' page. Please check details from previous logs.");
		}
		logger.info("Successfully verify all check points in 'Order History' page.");
	}
}
