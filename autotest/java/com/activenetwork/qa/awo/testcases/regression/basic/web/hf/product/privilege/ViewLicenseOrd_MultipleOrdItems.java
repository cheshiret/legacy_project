package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View License Order in Order History page, which has 4 order items: 1 active, 1 invalid, 1 revoked, 1 transferred
 * @Preconditions:
 * @SPEC:
 * Order History - License Order [TC:050344]
 * Order History - Order List - Order displaying or not [TC:050348]
 * @Task#: Auto-1723
 * 
 * @author Lesley Wang
 * @Date  Jun 3, 2013
 */
public class ViewLicenseOrd_MultipleOrdItems extends HFSKWebTestCase {
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private String ordDate;
	private PrivilegeInfo pri_invalid, pri_revoked, pri_transfer;
	private boolean isValidDatesHide;
	
	@Override
	public void execute() {
		this.removeCustSuspensionsToCleanUp();
		
		// In Web, make a privilege order with multiple order items: different products, different license years
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, pri_revoked);
		hf.makePrivilegeOrderToCart(cus, pri_invalid);
		hf.makePrivilegeOrderToCart(cus, pri_transfer);
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCart(pay);
		this.setPrivilegeNums(cus.orderNum);
		
		// view order in order history page
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege, pri_invalid, pri_revoked, pri_transfer);
		hf.signOut();
		
		this.changeOrdItemStatusInLM();
		
		// In Web, View the order in order history page: invalid and transferred order items not shown, revoked and active ones still shown
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege, pri_revoked);
		hf.signOut();
		
		this.reverseOrdInLM();
		
		// View the order in Web, the order shouldn't be shown
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyOrderExist(cus.orderNum, false);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderhistory02@test.com"; //d_web_hf_signupaccount, id=430
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "RCMP003";
		cus.identifier.state = "Ontario";
		
		cusNew.email = "inputinvaliddentinfo02@test.com";//d_web_hf_signupaccount, id=150
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"0101";
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "RCMPTRA";
		cusNew.identifier.state = "Saskatchewan";
		cusNew.residencyStatus = "Saskatchewan Resident - RCMP #";
		
		//Suspension parameters
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";
		
		// Login info for LM
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		TimeZone timezone =  DataBaseFunctions.getContractTimeZone(schema);
		ordDate = DateFunctions.getToday("E MMM dd yyyy", timezone);
		
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.code = "SKA";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.validFromDate = ordDate;
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		
		pri_invalid = new PrivilegeInfo();
		pri_invalid.name = privilege.name;
		pri_invalid.code = privilege.code;
		pri_invalid.licenseYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(1));
		pri_invalid.validFromDate = ordDate;
		pri_invalid.creationPrice = privilege.creationPrice;
		
		pri_revoked = new PrivilegeInfo();
		pri_revoked.name = "HFSK License002";
		pri_revoked.code = "SKB";
		pri_revoked.licenseYear = privilege.licenseYear;
		pri_revoked.validFromDate = ordDate;
		pri_revoked.creationPrice = hf.getPriCustPrice(schema, pri_revoked.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		
		pri_transfer = new PrivilegeInfo();
		pri_transfer.name = "HFSK License006";
		pri_transfer.code = "SKF";
		pri_transfer.validFromDate = ordDate;
		pri_transfer.validToDate = DateFunctions.getDateAfterGivenDay(ordDate, 200, "E MMM dd yyyy");
		pri_transfer.licenseYear = privilege.licenseYear;
		pri_transfer.creationPrice = hf.getPriCustPrice(schema, pri_transfer.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		
		isValidDatesHide = this.isValidDatesHide(url);
	}

	private void setPrivilegeNums(String ordNum) {
		String[] priNums = hf.getPrivilegeNumsByOrdNum(schema, cus.orderNum);
		pri_revoked.privilegeId = priNums[0];
		pri_invalid.privilegeId = priNums[1];
		pri_transfer.privilegeId = priNums[2];
		privilege.privilegeId = priNums[3];
	}
	
	// Clean up - Remove Suspensions in LM
	private void removeCustSuspensionsToCleanUp() {
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();
		lm.logOutLicenseManager();		
	}
	
	// Change order item status in LM: invalidate one, transfer one and revoke one
	private void changeOrdItemStatusInLM() {
		// In LM, invalidate one item
		lm.loginLicenseManager(loginLM);
		lm.searchAndInvalidatePrivilegeItem(cus.orderNum, pri_invalid.privilegeId, privilege.operateReason, privilege.operateNote);
		lm.gotoHomePage();
		
		// Transfer one item
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(cus.orderNum, pri_transfer.privilegeId);
		lm.transferPrivToOrderCart(cusNew, null);
		pay.payType = "Cash*";
		lm.processOrderCart(pay); 

		// Revoked one item
		lm.addCustSuspensionFromTopMenu(cus, suspension, pri_revoked);
		lm.gotoHomePage();	
		lm.logOutLicenseManager();		
	}
	
	// In LM, invalid the revoked item and then reverse the order
	private void reverseOrdInLM() {
		lm.loginLicenseManager(loginLM);
		lm.searchAndInvalidatePrivilegeItem(cus.orderNum, pri_revoked.privilegeId, privilege.operateReason, privilege.operateNote);
		lm.gotoHomePage();
		
		lm.reversePrivilegeOrderToCleanUp(cus.orderNum, voidReserveReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();				
	}
}
