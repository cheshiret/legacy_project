package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View the order in order history page after checkout, and then view it after void the order
 * @Preconditions:
 * 1. make sure the privilege product "HFSK License006" exists and assigned to Web.
 * 2. make sure the valid from date calculation is "Based on Priv LY Record or Purchase Date (If Greater)" for the product
 * 3. make sure the valid to date calculation is "Valid From Date plus Valid Days/Years" and valid days=200 for the product
 * @SPEC:
 * View Order History immediately after checkout [TC:052341]
 * Order History - License Order [TC:050344]
 * Order History - Order List - Order displaying or not [TC:050348]
 * @Task#: Auto-1723
 * 
 * @author Lesley Wang
 * @Date  Jun 3, 2013
 */
public class ViewLicenseOrd_MultipleQty extends HFSKWebTestCase {
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private String ordDate;
	private PrivilegeInfo privilege2;
	private boolean isValidDatesHide;
	
	@Override
	public void execute() {
		// In Web, make a privilege order with qty > 1 and view the order in order history page
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		this.setPrivilegeNums(cus.orderNum);
		hf.gotoOrdHistPgFromOrdConfirmPg();
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege, privilege2);
		hf.signOut();
	
		// Go to LM to void the order
		lm.loginLicenseManager(loginLM);
//		lm.voidPrivilegeOrderItems(cus.orderNum, Arrays.asList(new String[]{privilege.name, privilege.name}), privilege.operateReason, privilege.operateNote, pay);
		lm.voidPrivilegeOrderToCleanUp(cus.orderNum, privilege.operateReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
		
		// View the voided order not shown in Web
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyOrderExist(cus.orderNum, false);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderhistory@test.com"; //d_web_hf_signupaccount, id=420
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "RCMP002";
		cus.identifier.state = "Ontario";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		TimeZone timezone =  DataBaseFunctions.getContractTimeZone(schema);
		ordDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		
		// Privilege Info
		privilege.name = "HFSK License006";
		privilege.code = "SKF";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.qty = "2";
		privilege.validFromDate = ordDate;
		privilege.validToDate = DateFunctions.getDateAfterGivenDay(ordDate, 200, "E MMM dd yyyy");
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		privilege.operateReason = "14 - Other";
		
		privilege2 = new PrivilegeInfo();
		privilege2.validFromDate = ordDate;
		privilege2.validToDate = privilege.validToDate;
		
		isValidDatesHide = this.isValidDatesHide(url);
	}

	private void setPrivilegeNums(String ordNum) {
		String[] priNums = hf.getPrivilegeNumsByOrdNum(schema, cus.orderNum);
		privilege.privilegeId = priNums[0];
		privilege2.privilegeId = priNums[1];
	}
}
