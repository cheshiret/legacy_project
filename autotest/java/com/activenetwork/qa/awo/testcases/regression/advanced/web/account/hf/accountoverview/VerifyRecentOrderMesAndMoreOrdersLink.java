package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.accountoverview;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * 1. Check no any order message and no more orders link when login account without any recent orders
 * 2. Check orders and no more orders link when login account has an Active/Expired/Revoked status order
 * 3. Check no any order message and no more orders link when login account has Invalid/transfered/void/reversed status order 
 * 4. Check orders and no more orders link when login account has 3 orders, each of them has at least one order item in Active/Expried/Revoked status
 * 5. Check orders and more orders link when login account has more than 3 orders, each of them has at least one order item in Active/Expried/Revoked status
 * 
 * @Preconditions:
 * d_web_hf_signupaccount, id=230, hfsk_00004@webhftest.com , 2000-01-06
 *    In support script, the home state should be "Saskatchewan", and hone country is "Canada".
 * d_web_hf_signupaccount, id=220, hfsk_00003@webhftest.com, 2000-01-04 
 * d_hf_add_privilege_prd, id=1870, SKA, HFSK License001
 * d_hf_add_pricing, id=2492, 2602, 2642, Original,  Replacement Permit, Transfer
 * d_hf_assi_pri_to_store, id=1120, 01-Ministry Field Office
 * d_hf_add_qty_control, id=1100, Yes (Within Same Transaction only)
 * d_hf_add_prvi_license_year, id=1590
 * 
 * @SPEC:
 * Account Overview page - Recent Orders displaying message / orders [TC:050359] 
 * Account Overview page - Recent Orders section - 'More Orders' link - displaying / not displaying [TC:050360] 
 * @Task#: AUTO-1640
 * 
 * @author Swang
 * @Date  May 8, 2013
 */
public class VerifyRecentOrderMesAndMoreOrdersLink extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private HFAccountOverviewPage acctOvePg = HFAccountOverviewPage.getInstance();
	private String emailWithoutLic, noAnyOrderMes;
	private String ordNum1, ordNum2, ordNum3, ordNum4, ordNum5;
	private String[] orderNums;

	public void execute() {
		//Precondition
		//* Get all login account related order numbers
		hf.signIn(url, cus.email, cus.password);
		orderNums = hf.getAllLicenceOrders();

		//* Add identifier
		hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);
		hf.addIdentifier(cus.identifier);
		hf.signOut();

		//* Void login account related orders from LM
		if(orderNums.length>0){
			invalidOrdersInLM(orderNums);
		}

		//1. Check no any order message and no more orders link when login account without any recent orders
		hf.signIn(url, emailWithoutLic, cus.password);
		this.verifyRecentOrdersSection(noAnyOrderMes, null, false);
		hf.signOut();

		//2. Check orders and no more orders link when login account has an order, at least one order item in Active/Expired/Revoked status order
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		ordNum1 = hf.checkOutCart(pay);
		hf.gotoMyAccountPgFromHeaderBar();
		verifyRecentOrdersSection(StringUtil.EMPTY, new String[]{ordNum1}, false);
		hf.signOut();

		//3. Check no any order message and no more orders link when login account an order, at least one order item in Invalid/transfered/void/reversed status order 
		invalidOrdersInLM(new String[]{ordNum1});
		hf.signIn(url, cus.email, cus.password);
		verifyRecentOrdersSection(noAnyOrderMes, null, false);

		//4. Check orders and no more orders link when login account has 3 orders, each of them has at least one order item in Active/Expried/Revoked status
		hf.makePrivilegeOrderToCart(cus, privilege);
		ordNum2 = hf.checkOutCart(pay);
		hf.makePrivilegeOrderToCart(cus, privilege);
		ordNum3 = hf.checkOutCart(pay);
		hf.makePrivilegeOrderToCart(cus, privilege);
		ordNum4 = hf.checkOutCart(pay);

		hf.gotoMyAccountPgFromHeaderBar();
		verifyRecentOrdersSection(StringUtil.EMPTY, new String[]{ordNum4, ordNum3, ordNum2}, false);

		//5. Check orders and more orders link when login account has more than 3 orders, each of them has at least one order item in Active/Expried/Revoked status
		hf.makePrivilegeOrderToCart(cus, privilege);
		ordNum5 = hf.checkOutCart(pay);

		hf.gotoMyAccountPgFromHeaderBar();
		verifyRecentOrdersSection(StringUtil.EMPTY, new String[]{ordNum5, ordNum4, ordNum3}, true);
		hf.signOut();

		//Invalid orders in LM
		invalidOrdersInLM(new String[]{ordNum2, ordNum3, ordNum4, ordNum5});
	}

	public void wrapParameters(Object[] param) {
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";

		//Customer info
		emailWithoutLic = "hfsk_00003@webhftest.com";
		cus.email = "hfsk_00004@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4124";
		cus.identifier.state = "Manitoba";

		//Privileges Info
		privilege.name = "HFSK License001";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";

		noAnyOrderMes = "Recent Orders Your orders will appear here.";
	}

	/**
	 * Verify recent orders section
	 * 1. Message should display when login account is without any order or order at least one order item in Invalid/transfered/void/reversed status order 
	 * 2. Order items should be display when login account has 1-3 orders, at least one order item in Active/Expired/Revoked status order
	 * 3. More orders link should be display when login account has more than 3 orders, each of them has at least one order item in Active/Expried/Revoked status
	 * @param noAnyOrderMes
	 * @param orderNums
	 * @param isMoreLinkExists
	 */
	private void verifyRecentOrdersSection(String noAnyOrderMes, String[] orderNums, boolean isMoreLinkExists){
		String[] orderNumsFromUI = null;
		boolean result = true;

		//Check no any order message or orders
		if(StringUtil.notEmpty(noAnyOrderMes)){
			result &= MiscFunctions.compareResult("Recent Order section text when no any order", noAnyOrderMes, acctOvePg.getRecentOrdersSectionText());
		}else {
			orderNumsFromUI = acctOvePg.getAllRecentOrderNums();
			result &= MiscFunctions.compareResult("Length of order numbers", orderNums.length, orderNumsFromUI.length);
			for(int i=0; i<orderNums.length; i++){
				result &= MiscFunctions.compareResult(i+" - Order numbers", orderNums[i], orderNumsFromUI[i]);
			}
		}

		//Check more link
		result &= MiscFunctions.compareResult("Should no more orders link", isMoreLinkExists, acctOvePg.isMoreOrdersLinkExists());

		//Check final result
		if (!result) {
			throw new ErrorOnPageException("Not all check points are correct in 'Recent Orders' section. Please check details from previous logs.");
		}
		logger.info("Successfully verify all check points in 'Recent Orders' section.");
	}

	private void invalidOrdersInLM(String[] orderNums) {
		lm.loginLicenseManager(login);
		for(String orderNum: orderNums){
			logger.info("Invalid Privilege order: " + orderNum);
			lm.gotoOrderPageFromOrdersTopMenu(orderNum);
			lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		}
		lm.logOutLicenseManager();
	}
}
