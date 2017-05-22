package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licensedetails;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.supportscripts.function.license.RemoveCustAllSuspensions;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify Replace Lost License button on License Details page when:
 * 1. the license with replacement price is active and the button shown.
 * 2. the license with replacement price is revoked and no button shown.
 * 3. the license with replacement price and max replace number is replaced as many times as the max allowed and no button shown 
 * @Preconditions:
 * 1. Make sure HFMO License001 exists, setup replacement price and max replace number =3
 * @SPEC:
 * 	License Detail section - 'Replace Lost License' button [TC:046892]
 * @Task#: Auto-1718
 * 
 * @author Lesley Wang
 * @Date  Jun 14, 2013
 */
public class VerifyReplaceLostLicenseBtn extends HFMOWebTestCase {
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	private int maxReplaceNum;
	
	@Override
	public void execute() {
		// Remove all Suspensions to clean up
		new RemoveCustAllSuspensions().execute(new Object[] {loginLM, cus});
		
		// Invalid license in LM
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
		
		// Make a privilege order in web
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.makePrivilegeOrderToCart(privilege);
		cus.orderNum = hf.checkOutCart(pay);
		privilege.privilegeId = hf.getPrivilegeNumByOrdNum(schema, cus.orderNum);
		// Verify Replace button shown
		hf.gotoLicDetailsPg(privilege.privilegeId, true);
		this.verifyReplaceBtnShown(true);
		hf.signOut();
		
		// Add a suspension in LM
		this.addCustSuspensionInLM();
		
		// View Replace button not shown in web
		this.verifyReplaceBtnInWeb(false, false);
		
		// Reactivate the revoked license in LM 
		this.reactiveLicInLM();
		
		// View Replace button shown in web
		this.verifyReplaceBtnInWeb(true, true);
		
		// Replace the license as many times as max allowed in LM
		pay.payType = "Cash";
		this.replaceLicenseInLM(maxReplaceNum);
		
		// View Replace button not shown in web
		this.verifyReplaceBtnInWeb(true, false);	
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.dateOfBirth = "01/01/1997";// d_web_hf_signupaccount id=520
		cus.lName = "UpdateIdenCO2_LN";
		cus.fName = "UpdateIdenCO2_FN ";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_GREENCARD_NUM_ID, false, true);
		cus.identifier.identifierNum = "1R9Y4x4136";
		cus.identifier.country = "Canada";
		
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";
		
		// Login info for LM
		loginLM.location = "MO Mod 1 - Auto/ACADEMY SPORTS & OUTDOORS(Store Loc)"; // The role name is temporary and need to change after MO release
		
		// Privilege Info
		privilege.name = "HFMO License001"; // d_hf_add_privilege_prd, id=2030
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		privilege.validFromDate = DateFunctions.getToday("E MMM dd yyyy");
		maxReplaceNum = 3; // d_hf_add_qty_control,  id=1260
	}

	private void verifyReplaceBtnShown(boolean exp) {
		if (exp != licDetailsPg.isReplaceLostLicBtnExist()) {
			throw new ErrorOnPageException("Replace lost license button should " + (exp ? " " : " NOT ") + " shown!");
		}
		logger.info("---Verify the display of replace button correctly as " + exp);
	}
	
	private void addCustSuspensionInLM() {
		lm.loginLicenseManager(loginLM);
		lm.addCustSuspensionFromTopMenu(cus, suspension, privilege);
		lm.logOutLicenseManager();		
	}
	
	private void reactiveLicInLM() {
		lm.loginLicenseManager(loginLM);
		lm.gotoPrivilegeDetailPgFromOrdersTopMenu(cus.orderNum, privilege.privilegeId);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.logOutLicenseManager();	
	}
	
	private void replaceLicenseInLM(int replaceNum) {
		lm.loginLicenseManager(loginLM);
		for (int i = 0; i < replaceNum; i++) {
			lm.replacePrivilegeToCartFromCustomerTopMenu(cus, cus.orderNum);
			lm.processOrderCart(pay);
		}
		lm.logOutLicenseManager();
	}
	
	private void verifyReplaceBtnInWeb(boolean isCurrentList, boolean isShown) {
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoLicDetailsPg(privilege.privilegeId, isCurrentList);
		this.verifyReplaceBtnShown(isShown);
		hf.signOut();	
	}
}
