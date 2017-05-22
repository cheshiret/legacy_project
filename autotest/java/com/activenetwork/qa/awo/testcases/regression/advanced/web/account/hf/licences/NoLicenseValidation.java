package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licences;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * Message "Your current/expired licences will appear here" will displays when
 * 1. Login account doesn't have any license history;
 * 2. Login account has (Inactive; Transfered; Voided; Reversed) licenses
 * 
 * @Preconditions:
 * d_web_hf_signupaccount
 * id=330, hfsk_00012@webhftest.com, 2000-01-14
 * id=340, hfsk_00013@webhftest.com, 2000-01-15
 * d_hf_add_privilege_prd, id=1870, 1880, 1910 and 1920

 * @SPEC: No license/permit exist on Current/Expired tabs [TC:046331] 
 * @Task#: AUTO-1642
 * 
 * @author SWang
 * @Date  May 28, 2013
 */
public class NoLicenseValidation extends HFSKWebTestCase {
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFExpiredLicensesListPage expiredLicensesListPg = HFExpiredLicensesListPage.getInstance();
	private String orderNum, privilegeNum, noResultMesInCurrent, noResultMesInExpired;
	private PrivilegeInfo privilege2, privilege3, privilege4;

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
		//
		//* Invalid all licenses for 1# account in LM
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, StringUtil.EMPTY, searchStatus, privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();

		//Check point 1: verify no results message in current licence page when the account doesn't have any license history
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		currentLicensePg.verifyNoResultsMes(noResultMesInCurrent);

		//Check point 2: verify no results message in expired licence page when the account doesn't have any license history
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		expiredLicensesListPg.verifyNoResultsMes(noResultMesInExpired);
		hf.signOut();

		//Prepared 4 kinds of licences in LM
		//#1 invalid
		lm.loginLicenseManager(loginLM);
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege);
//		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cus, privilege);
		orderNum = lm.processOrderCart(pay);
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderNum);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(orderNum, privilegeNum);
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateNote);

		//#2 transferred
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege2);
		orderNum = lm.processOrderCart(pay); 
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderNum);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(orderNum, privilegeNum);
		lm.transferPrivToOrderCart(cusNew, null);
		lm.processOrderCart(pay); 

		//#3 reversed
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege3);
		orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(voidReserveReason, privilege.operateNote);
		lm.gotoHomePage();

		//#4 voided
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege4);
		orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(voidReserveReason, privilege.operateNote);
		lm.logOutLicenseManager();

		//Check point 3: verify no results message in current licence page when the account has (Inactive; Transfered; Voided; Reversed) licenses
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		currentLicensePg.verifyNoResultsMes(noResultMesInCurrent);

		//Check point 4: verify no results message in expired licence page when the account has (Inactive; Transfered; Voided; Reversed) licenses
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		expiredLicensesListPg.verifyNoResultsMes(noResultMesInExpired);
		hf.signOut();

		//Invalid customer licenses in LM
		new com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction().execute(loginLM, cus, searchLicYear, searchStatus);
	}

	public void wrapParameters(Object[] param) {
		//License manager parameters
		loginLM.contract = "SK Contract";
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";

		//Customer parameters
		cus.email = "hfsk_00012@webhftest.com";
		cus.dateOfBirth = "2000-01-14";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4131";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		cusNew.email = "hfsk_00013@webhftest.com";
		cusNew.dateOfBirth = "2000-01-15";
		cusNew.residencyStatus = RESID_STATUS_SK;
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "1R9Y4x4132";
		cusNew.identifier.state = "Saskatchewan";
		cusNew.residencyStatus = "Saskatchewan Resident - RCMP #";

		//Privileges parameters
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.purchasingName = "SKA-HFSK License001";
		privilege2 = new PrivilegeInfo();
		privilege2.licenseYear = privilege.licenseYear;
		privilege2.purchasingName = "SKB-HFSK License002";	
		privilege3 = new PrivilegeInfo();
		privilege3.licenseYear = privilege.licenseYear;
		privilege3.purchasingName = "SKE-HFSK License005";
		privilege4 = new PrivilegeInfo();
		privilege4.licenseYear = privilege.licenseYear;
		privilege4.purchasingName = "SKF-HFSK License006";

		searchStatus = new String[]{"Active", "Revoked"};
		pay.payType = Payment.PAY_DEF_CASH;

		noResultMesInCurrent = "Your current licences will appear here\\.";
		noResultMesInExpired = "Your expired licences will appear here\\.";
	}
}
