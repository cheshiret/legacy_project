package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View Order with Replacement Order Item in order history page
 * @Preconditions:
 * @SPEC:
 * Order History - License Order [TC:050344]
 * Order Details - Showing 'Replacement' for replacement order items [TC:050521]
 * @Task#: Auto-1723, Auto-1720
 * 
 * @author Lesley Wang
 * @Date  Jun 3, 2013
 */
public class ViewLicenseOrd_ReplacementOrdItem extends HFSKWebTestCase {
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private String ordDate;
	private boolean isValidDatesHide;
	
	@Override
	public void execute() {
		// Purchase a privilege and then replace it in LM
		lm.loginLicenseManager(loginLM);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cus, privilege);
		cus.orderNum = lm.processOrderCart(pay);
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, cus.orderNum);
	
		lm.replacePrivilegeToCartFromCustomerTopMenuByPrivilegeNums(cus, privilege.privilegeId);
		String replaceOrdNum = lm.processOrderCart(pay);	
		lm.logOutLicenseManager();

		// View the order history and order details in Web
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege); // Original Order
		hf.gotoOrderDetailsPgFromOrdHistPg(privilege.privilegeId, cus.orderNum);
		ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege);
		
		privilege.status = "Replacement";
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, replaceOrdNum, ordDate, privilege); // Replacement Order
		hf.gotoOrderDetailsPgFromOrdHistPg(privilege.privilegeId, replaceOrdNum);
		ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, replaceOrdNum, ordDate, privilege);
		hf.signOut();	
		
		// Clean up 
		lm.loginLicenseManager(loginLM);
		lm.reversePrivilegeOrderToCleanUp(replaceOrdNum, privilege.operateReason, privilege.operateNote, pay);
		lm.reversePrivilegeOrderToCleanUp(cus.orderNum, privilege.operateReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderhistory03@test.com"; //d_web_hf_signupaccount, id=440
		cus.password = "asdfasdf";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "0101";
		cus.residencyStatus = RESID_STATUS_SK + " - RCMP #";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "RCMP004";
		cus.identifier.state = "Ontario";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		TimeZone timezone =  DataBaseFunctions.getContractTimeZone(schema);
		ordDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.code = "SKA";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.validFromDate = ordDate;
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		privilege.operateReason = "14 - Other";
		
		pay.payType = "Cash*";
		
		isValidDatesHide = this.isValidDatesHide(url);
	}

}
