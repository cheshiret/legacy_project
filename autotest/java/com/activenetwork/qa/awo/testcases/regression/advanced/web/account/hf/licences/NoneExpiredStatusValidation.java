package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licences;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P, 43 minutes 49 seconds)
 * 1. Go to license manager, make the used account has licenses with all kinds of status, Active; Invalid; Reversed; Revoked; Transferred; Voided 
 * 2. Go to HFSK, Only the active licence displays in current licence list page, only revoked licence displays in expired licence list page
 * 3. Go to license manager, inactivate the active licence
 * 4. Go to HFSK, the active licence display in current licence list page
 * 5. Go to license manager, transfer previous active licence
 * 6. Go to HFSK, the transfered licence doesn't display in current list page
 * 7. Go to license manager, undo revered the revered licence
 * 8. Go to HFSK, the undo revered licence display in current list page
 * 9. Go to license manager, undo void the void licence
 * 10. Go to HFSK, the undo void licence displays in current list page
 * 11. Go to license manager, reactive the revoked licence
 * 12. Go to HFSK, the reactive licence displays in current list page, but doesn' display in expired licence list page
 * 
 * @Preconditions:
 * d_web_hf_signupaccount,id=280, 290
 * d_hf_add_privilege_prd, id=1870, 1880, 1890, 1900, 1910, 1920
 * 
 * @SPEC:
 * Verify the status of the license/permit on Current tab [TC:046090] 
 * Verify the status of the license/permit on Expired tab [TC:044618] 
 * @Task#: Auto-1641
 * 
 * @author Swang
 * @Date  May 23, 2013
 */
public class NoneExpiredStatusValidation extends HFSKWebTestCase {
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFExpiredLicensesListPage expiredLicensesListPg = HFExpiredLicensesListPage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private Suspension suspension = new Suspension();
	private String ordNum1, ordNum2, ordNum3, ordNum5, ordNum6, ordNum7, privilegeNum, voidReserveReason, undoVoidReserveReason, searchLicYear;
	private PrivilegeInfo privilege2, privilege3, privilege4, privilege5, privilege6;
	private String[] privilegeNames;
	private String[] searchStatus;
	private List<String> licensesInCurrentLis = new ArrayList<String>();
	private List<String> licensesInExpiredLis = new ArrayList<String>();

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
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, searchLicYear, searchStatus, privilege.operateReason, privilege.operateNote);

		//* Remove added suspension for 1# account in LM
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();

		//* Invalid all licenses for 2# account in LM
		lm.invalidateAllPrivilegesPerCustomer(cusNew, StringUtil.EMPTY, searchStatus, privilege.operateReason, privilege.operateNote);

		//* Remove added suspension for 2# account in LM
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();

		//Make 6 order in LM
		//#1 license order has active order item
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege);
		ordNum1 = lm.processOrderCart(pay);

		//#2 license order has invalid order item
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege2);
		ordNum2 = lm.processOrderCart(pay);
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum2);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(ordNum2, privilegeNum);
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.gotoHomePage();

		//#3 license order has transferred order item
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege3);
		ordNum3 = lm.processOrderCart(pay); 
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum3);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(ordNum3, privilegeNum);
		lm.transferPrivToOrderCart(cusNew, null);
		lm.processOrderCart(pay); 

		//#4 license order has revoked order item
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege4);
		ordNum5 = lm.processOrderCart(pay);

		privilege4.purchasingName = privilegeNames[3];
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension, privilege4);
		lm.gotoHomePage();

		//#5 reversed
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege5);
		ordNum6 = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(ordNum6);
		lm.reversePrivilegeOrdToCart(voidReserveReason, privilege.operateNote);
		lm.gotoHomePage();

		//#6 voided
		lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege6);
		ordNum7 = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(ordNum7);
		lm.voidPrivilegeOrderToCart(voidReserveReason, privilege.operateNote);
		lm.logOutLicenseManager();

		//Check point1: only active license displays in Current list page in HFSK
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicensesDisplay(licensesInCurrentLis, privilegeNames, new boolean[]{true, false, false, false, false, false});

		//Check point2: only revoked license displays in Expired list page in HFSK
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		licensesInExpiredLis = expiredLicensesListPg.getAllLicenses();
		expiredLicensesListPg.verifyLicensesDisplay(licensesInCurrentLis, privilegeNames, new boolean[]{true, false, false, false, false, false});
		hf.signOut();

		//Invalid the active license in license manager
		lm.loginLicenseManager(loginLM);
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum1);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(ordNum1, privilegeNum);
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();

		//Check point 3: the invalid license doesn't display in current list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis.clear();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilegeNames[0], false);
		hf.signOut();

		//Reactive the invalid license in license manager
		lm.loginLicenseManager(loginLM);
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum1);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(ordNum1, privilegeNum);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();

		//Check point 4: the reactive license  display in current list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis.clear();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilegeNames[0], true);
		hf.signOut();

		//Transfer the reactive license in license manager
		lm.loginLicenseManager(loginLM);
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum1);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(ordNum1, privilegeNum);
		lm.transferPrivToOrderCart(cusNew, null);
		lm.processOrderCart(pay); 
		lm.logOutLicenseManager();

		//Check point 5: the reactive license  display in current list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis.clear();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilegeNames[0], false);
		hf.signOut();

		//Undo reserved the reserved license in license manager
		lm.loginLicenseManager(loginLM);
		lm.gotoPrivilegeOrderDetailPage(ordNum6);
		lm.undoReversePrivilegeOrderToCart(undoVoidReserveReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();

		//Check point 6: the undo reserved license display in current list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis.clear();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilegeNames[4], true);
		hf.signOut();

		//Undo void the reserved license in license manager
		lm.loginLicenseManager(loginLM);
		lm.gotoPrivilegeOrderDetailPage(ordNum7);
		lm.undoVoidPrivilegeOrdToCart(undoVoidReserveReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();

		//Check point 7: the undo void license doesn't display in current list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis.clear();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilegeNames[5], true);
		hf.signOut();

		//Active revoked license in license manager
		lm.loginLicenseManager(loginLM);
		privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum5);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(ordNum5, privilegeNum);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();

		//Check point 8: the active license display in current list page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoCurrentLicencesListPgFromMyAcctPanel();
		licensesInCurrentLis.clear();
		licensesInCurrentLis = currentLicensePg.getAllLicenses();
		currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilegeNames[3], true);

		//Check point9: only active license doesn't display in Expired list page in HFSK
		hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
		licensesInExpiredLis.clear();
		licensesInExpiredLis = expiredLicensesListPg.getAllLicenses();
		expiredLicensesListPg.verifyLicenseDisplays(licensesInExpiredLis, privilegeNames[3], false);
		hf.signOut();

		//Invalid customer licenses in LM
		new com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, searchLicYear, searchStatus});
	}

	public void wrapParameters(Object[] param) {
		//License manager parameters
		loginLM.contract = "SK Contract";
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";

		//Customer parameters
		cus.email = "hfsk_00008@webhftest.com";
		cus.dateOfBirth = "2000-01-10";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4128";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		cusNew.email = "hfsk_00009@webhftest.com";
		cusNew.dateOfBirth = "2000-01-11";
		cusNew.residencyStatus = RESID_STATUS_SK;
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "1R9Y4x4129";
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
		privilege3.purchasingName = "SKC-HFSK License003";
		privilege4 = new PrivilegeInfo();
		privilege4.licenseYear = privilege.licenseYear;
		privilege4.purchasingName = "SKD-HFSK License004";
		privilege5 = new PrivilegeInfo();
		privilege5.licenseYear = privilege.licenseYear;
		privilege5.purchasingName = "SKE-HFSK License005";
		privilege6 = new PrivilegeInfo();
		privilege6.licenseYear = privilege.licenseYear;
		privilege6.purchasingName = "SKF-HFSK License006";

		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		voidReserveReason = "14 - Other";
		undoVoidReserveReason = "17 - Other";

		privilegeNames = new String[6];
		privilegeNames[0] = "HFSK License001";
		privilegeNames[1] = "HFSK License002";
		privilegeNames[2] = "HFSK License003";
		privilegeNames[3] = "HFSK License004";
		privilegeNames[4] = "HFSK License005";
		privilegeNames[5] = "HFSK License006";

		//Suspension parameters
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";

		searchLicYear = String.valueOf(DateFunctions.getCurrentYear());
		searchStatus = new String[]{"Active", "Revoked"};
		pay.payType = Payment.PAY_DEF_CASH;
	}
}
