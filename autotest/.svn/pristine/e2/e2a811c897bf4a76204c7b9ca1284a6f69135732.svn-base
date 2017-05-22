/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer.residentstatus;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Set resident status when declaration required and 
 * 1. Login in account with SK address and no any override
 * 2. Select Canadian Resident - CAN DL #, make the declaration and purchase a product
 * 3. In License Manager, view the resident status in the order details
 * 
 * @Preconditions: The setup sql "SetupResidentStatusIdentifierQualifier.sql" has been run
 * @SPEC: (Web) Residency Status Declaration page - Qualifer - Declaration required [TC:062195]
 * @Task#: Auto-1623
 * 
 * @author Lesley Wang
 * @Date  Apr 17, 2013
 */
public class SetResidentStatus_DeclarRequired_NoOverride extends
		HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private String maskedNum;
	
	@Override
	public void execute() {
		hf.signIn(url, cus.email, cus.password);		
		
		// Purchase a product with declaring resident status
		hf.gotoResidStatusDeclaPg();
		hf.selectResidStatusToPrdCategoryPg(cus.residencyStatus, cus.identifier);
		hf.gotoLicenseDetailsPgFromLicenseCatListPg(privilege);
		hf.addPrivilegeToCartFromPrdDetailsPg(privilege);
		String ordNum = hf.checkOutCart(pay);
		
		// Check the identifier info added in Update Account page
		hf.gotoMyAccountPgFromHeaderBar();
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updatePg.verifyIdentifierInfo(cus.identifier, maskedNum);
		hf.signOut();

		// Go to License Manager to view the resident status in order details page
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		privOrderDetailsPage.verifyIdentAndResidentInfo(cus.residencyStatus, cus.identifier.identTypeFullNm, cus.identifier.identifierNum, false);
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		
		cus.identifier.identifierType = cus.identifier.identTypeFullNm;
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoCustomerIdentifierSubTab();
		lm.changeIdentifierStatus(cus.identifier.identTypeFullNm, cus.identifier.identifierNum, "Remove");	
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "nonmohsk0003@test.com"; // with SK address
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_CAN;
		cus.identifier.identifierType = IDENT_TYPE_CANDL;
		cus.identifier.identTypeFullNm = IDENT_TYPE_NAME_CANDL;
		cus.identifier.identifierNum = new DecimalFormat("000000").format(new Random().nextInt(999999));
		cus.identifier.state = "Ontario";
		cus.identifier.stateCode = "ON";
		cus.identifier.isDeclarRequired = true;
		
		privilege.name = "HFSK License001";
		privilege.licenseYear = hf.getFiscalYear(schema);
		logger.info(privilege.licenseYear);
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";	
		
		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);
	}
}
