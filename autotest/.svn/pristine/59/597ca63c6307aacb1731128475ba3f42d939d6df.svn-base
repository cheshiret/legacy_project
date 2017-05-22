package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View Order with Transfer Order Item in order history page, and the transferred order should not displayed 
 * @Preconditions:
 * @SPEC: 
 * Order History - License Order [TC:050344]
 * Order History - Order List - Order displaying or not [TC:050348]
 * Order Details - Showing 'Transfer' for order item transferred from other customers [TC:052838]
 * @Task#: Auto-1723, Auto-1720
 * 
 * @author Lesley Wang
 * @Date  Jun 3, 2013
 */
public class ViewLicenseOrd_TransferOrdItem extends HFSKWebTestCase {

	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private String ordDate;
	private boolean isValidDatesHide;
	
	@Override
	public void execute() {
		// Make a privilege order in Web
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		cus.orderNum = hf.checkOutCartToConfirmationPage(pay);
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		
		// View order history in Web
		hf.gotoOrdHistPgFromOrdConfirmPg();
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, cus.orderNum, ordDate, privilege);
		hf.signOut();
		
		// Transfer the license to the other customer in LM
		lm.loginLicenseManager(loginLM);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(cus.orderNum, privilege.privilegeId);
		lm.transferPrivToOrderCart(cusNew, null);
		pay.payType = "Cash*";
		cusNew.orderNum = lm.processOrderCart(pay); 
		lm.logOutLicenseManager();
		
		// View order history in Web for the first customer: the order not shown.
		hf.signIn(url, cus.email, cus.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		ordHistPg.verifyOrderExist(cus.orderNum, false);
		hf.signOut();
		
		// View order history in Web for the second customer: new order shown with Transfer status
		hf.signIn(url, cusNew.email, cusNew.password);
		hf.gotoOrderHistoryPgFromMyAcctPanel();
		privilege.status = "Transfer";
		privilege.privilegeId = lm.getPrivilegeNumByOrdNum(schema, cusNew.orderNum);
		ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, cusNew.orderNum, ordDate, privilege);
		
		// View order details page in Web
		hf.gotoOrderDetailsPgFromOrdHistPg(privilege.privilegeId, cusNew.orderNum);
		ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, cusNew.orderNum, ordDate, privilege);
		hf.signOut();
		
		// Clean up
		lm.loginLicenseManager(loginLM);
		lm.gotoPrivilegeOrderDetailPage(cusNew.orderNum);
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "vieworderhistory04@test.com"; //d_web_hf_signupaccount, id=450
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "RCMP005";
		cus.identifier.state = "Ontario";
		
		cusNew.email = "vieworderhistory05@test.com"; //d_web_hf_signupaccount, id=460
		cusNew.password = "asdfasdf";
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"0101";
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "RCMP006";
		cusNew.identifier.state = "Ontario";
		cusNew.residencyStatus = "Saskatchewan Resident - RCMP #";
		
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
		
		isValidDatesHide = this.isValidDatesHide(url);
	}
}
