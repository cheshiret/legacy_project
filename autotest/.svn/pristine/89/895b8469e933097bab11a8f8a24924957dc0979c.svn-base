/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.orderhistory;

import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the order history UI when:
 * 1. there is no any order for the account;
 * 2. there is an order with invalid/reversed/voided/transferred status;
 * 3. there are some orders displayed on the page
 * @Preconditions:
 * @SPEC:
 * Order History page - UI [TC:050347]
 * Order History page - blank order list section [TC:055629]
 * @Task#: Auto-1722
 * 
 * @author Lesley Wang
 * @Date  May 27, 2013
 */
public class VerifyOrderHistoryUI extends HFSKWebTestCase {
	private String emailWithoutOrders, pageTitle, textWithoutOrders, textWithOrders, msgWithoutOrders;
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	
	@Override
	public void execute() {
		// Login in with an account without any orders and verify order history UI
		hf.signIn(url, emailWithoutOrders, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		this.verifyOrderHistoryUI(pageTitle, textWithoutOrders, msgWithoutOrders, false, false, false);
		hf.signOut();
		
		// Login in with an account to purchase a license order and verify order history UI
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		hf.checkOutCartToConfirmationPage(pay);
		hf.gotoOrdHistPgFromOrdConfirmPg();
		this.verifyOrderHistoryUI(pageTitle, textWithOrders, StringUtil.EMPTY, true, true, true);
		hf.signOut();
		
		// Invalid the order in LM and verify order history UI in Web
		cus.identifier.identifierType = IDENT_TYPE_NAME_CANDL;
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		this.verifyOrderHistoryUI(pageTitle, textWithoutOrders, msgWithoutOrders, false, false, false);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		emailWithoutOrders = "validateaddress@test.com";
		cus.email = "vieworderhistoryui@test.com";
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_CAN;
		cus.identifier.identifierType = IDENT_TYPE_CANDL;
		cus.identifier.identifierNum = "CANDL00";
		cus.identifier.state = "Ontario";
		
		// Login info for LM
		loginLM.location = "SK Admin/SASK";
			
		// Privilege Info
		privilege.name = "HFSK License001";
		
		// Page info
		pageTitle = "Order History";
		textWithoutOrders = "History of all your purchases.";
		textWithOrders = "History of all your purchases. Change the time period by using the pull-down menu below.";
		msgWithoutOrders = "Your orders will appear here.";
	}

	private void verifyOrderHistoryUI(String title, String headerText, String msg, 
			boolean isDateFliterExist, boolean isPrdTypeFliterExist, boolean isPgControlExist) {
		boolean result = true;
//		result &= MiscFunctions.compareString("Order History Page Title and caption", title+headerText, ordHistPg.getPageTitleAndCaption());
		result &= MiscFunctions.matchString("Order History Page Title and caption", ordHistPg.getPageTitleAndCaption(), title+"( )?"+headerText);
		if (StringUtil.notEmpty(msg)) {
			result &= MiscFunctions.compareResult("Message when no orders", msg, ordHistPg.getNoResultsMsg());
		} else {
			result &= MiscFunctions.compareResult("Message without orders showing", false, ordHistPg.isNoResultsMsgExist());
		}
		result &= MiscFunctions.compareResult("Date Filter List exist", isDateFliterExist, ordHistPg.isDateFilterListExist());
		result &= MiscFunctions.compareResult("Product Type Filter List exist", isPrdTypeFliterExist, ordHistPg.isPrdTypeFilterListExist());
		result &= MiscFunctions.compareResult("Pagination control exist", isPgControlExist, ordHistPg.isPageControlExist());
		
		if (!result) {
			throw new ErrorOnPageException("Order History UI is wrong! Check logger error!");
		}
		logger.info("---Verify Order History UI correctly!");
	}
	
}
