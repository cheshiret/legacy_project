/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer.residentstatus;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: View the order details in LM if Select CAN Resident to purchase product 
 * when the account is MOH profile with SK address and override='Canadian Resident'
 * @Preconditions: 
 * @SPEC: (Web) Residency Status Declaration Error page - UI - Caused by Override [TC:062244]
 * @Task#: Auto-1624
 * 
 * @author Lesley Wang
 * @Date  Apr 17, 2013
 */
public class SetResidentStatus_OverrideAsCAN extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	@Override
	public void execute() {
		// Go to LM to override the customer resident
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cus.lName, cus.fName);
		custDetailsPg.editCustResidOverride(cus.residencyOverride);
		lm.logOutLicenseManager();
		
		// Go to HF Web to select Canadian Residency and purchase
		hf.deleteCustIden(schema, cus.identifier.id, cus.email);
		hf.signIn(url, cus.email, cus.password);		
		hf.addIdentifier(cus.identifier); // Additional identifier type must be required per Defect-43130.
		hf.makePrivilegeOrderToCart(cus, privilege);
		String ordNum = hf.checkOutCart(pay);
		hf.signOut();
		
		// Go to License Manager to view the resident status in order details page
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		privOrderDetailsPage.verifyIdentAndResidentInfo(RESID_STATUS_CAN, StringUtil.EMPTY, StringUtil.EMPTY, true);
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "mohsk0001@overridecan.com"; // with SK address
		cus.password = "asdfasdf";
		cus.fName = "ALANNA";
		cus.lName = "ANDREWS";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.residencyOverride = "Country Resident";
		cus.identifier.id = IDEN_RCMP_ID;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identTypeFullNm = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = new DecimalFormat("00000").format(new Random().nextInt(99999));
		cus.identifier.state = "Alberta";
		privilege.purchasingName = "HFSK License001";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";
	}
}
