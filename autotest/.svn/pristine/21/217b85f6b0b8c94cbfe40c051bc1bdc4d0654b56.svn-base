/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.accountoverview;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.supportscripts.function.license.InvalidateCutPrivilegesFunction;
import com.activenetwork.qa.awo.supportscripts.function.license.RemoveCustAllSuspensions;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Current License section on Account Overview page when there are different numbers of license
 * @Preconditions:
 * @SPEC: 
 * Account Overview page - Current Hunt & Fish Licenses section [TC:048784]
 * Account Overview page - Current Licenses section displaying message / licenses [TC:048786]
 *  Account Overview page - Current Licenses section - 'More Licenses' link - displaying / not displaying [TC:048785]
 * @Task#: Auto-1639
 * 
 * @author Lesley Wang
 * @Date  Apr 22, 2013
 */
public class VerifyCurrentLicenseSection extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private HFAccountOverviewPage acctOvePg = HFAccountOverviewPage.getInstance();
	private Suspension suspension = new Suspension();
	private PrivilegeInfo privilege2, privilege3, privilege4;
	private String emailWithoutLic, sectionText, moreLinkText, pageTitle, detailsText;
	private String ordNum1, ordNum2, ordNum3, ordNum4;
	
	@Override
	public void execute() {
		// Verify the UI when login with an account without license
		hf.signIn(url, emailWithoutLic, cus.password);
		this.verifyCurrentLicSectionText(sectionText);
		hf.signOut();
		
		// Clean up customer's privileges and suspensions
		new InvalidateCutPrivilegesFunction().execute(new Object[] {loginLM, cus, privilege.licenseYear, privilege.searchStatus});
		new RemoveCustAllSuspensions().execute(new Object[] {loginLM, cus});
		
		// Verify the UI after add 3 active license
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		ordNum1 = hf.checkOutCart(pay);
		hf.makePrivilegeOrderToCart(cus, privilege2);
		ordNum2 = hf.checkOutCart(pay);
		hf.makePrivilegeOrderToCart(cus, privilege3);
		ordNum3 = hf.checkOutCart(pay);

		hf.gotoMyAccountPgFromHeaderBar();
		this.verifyCurrentLicSectionUI(3, false);
		
		// Verify the UI after add 4 active license
		hf.makePrivilegeOrderToCart(cus, privilege4);
		ordNum4 = hf.checkOutCart(pay);
		
		hf.gotoMyAccountPgFromHeaderBar();
		this.verifyCurrentLicSectionUI(3, true);
		hf.signOut();
		
		// Revoke the first license in License Manager and verify UI: 3 Active, 1 Revoked
		lm.loginLicenseManager(login);
		this.revokePrivilegeInLM(privilege);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		this.verifyCurrentLicSectionUI(3, true);
		hf.signOut();
		
		// Inactive the revoked license and verify UI: 3 Active
		lm.loginLicenseManager(login);
		this.invalidPrivilegeInLM(ordNum1);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		this.verifyCurrentLicSectionUI(3, false);
		hf.signOut();
		
		// Revoke the third license and invalid the second one and verify UI: 1 Active, 1 Revoked 
		lm.loginLicenseManager(login);
		this.invalidPrivilegeInLM(ordNum2);
		this.revokePrivilegeInLM(privilege3);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		this.verifyCurrentLicSectionUI(1, true);
		hf.signOut();
		
		// Invalid the revoked one and verify UI: 1 Active
		lm.loginLicenseManager(login);
		this.invalidPrivilegeInLM(ordNum3);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		this.verifyCurrentLicSectionUI(1, false);
		hf.signOut();
		
		// Revoke the last active license and verify UI: 1 Revoked
		lm.loginLicenseManager(login);
		this.revokePrivilegeInLM(privilege4);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		this.verifyCurrentLicSectionText(sectionText); // Blocked by Defect-43446
		hf.signOut();
		
		// Inactive the last active license and verify UI
		lm.loginLicenseManager(login);
		this.invalidPrivilegeInLM(ordNum4);
		lm.logOutLicenseManager();
		
		hf.signIn(url, cus.email, cus.password);
		this.verifyCurrentLicSectionText(sectionText);
		hf.signOut();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		// Customer info
		emailWithoutLic = "validateaddress@test.com";
		cus.email = "verifyacctoverview@test.com";
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "9555555";
		cus.identifier.state = "Manitoba";
		
		// Privileges Info
		privilege.name = "HFSK License001";
		privilege2 = new PrivilegeInfo();
		privilege2.name = "HFSK License002";
		privilege3 = new PrivilegeInfo();
		privilege3.name = "HFSK License003";
		privilege4 = new PrivilegeInfo();
		privilege4.name = "HFSK License004";
		
		privilege.licenseYear = StringUtil.EMPTY; //hf.getFiscalYear(schema);
		privilege.searchStatus = new String[] {"Active", "Revoked"};
		
		// Page Text
		pageTitle = "Current Licences";
		sectionText = "^" + pageTitle + "\\s*Your current licences will appear here.$";
		moreLinkText = "More licences";
		boolean isHFWebHideValidDates =!WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_UIOptions, UIOptions.HFWebDisplayValidDates, MiscFunctions.getPLNameFromURL(url));
		detailsText = ".*Licence #:\\s*\\d+\\s*"+(isHFWebHideValidDates?"":"Valid( Dates)?:")+".*Type.*";
		
		
		// Suspension Info
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = "Angling Suspension";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";
	}

	/** Verify Current License Text when there is no active license */
	private void verifyCurrentLicSectionText(String expReg) {
		String act = acctOvePg.getCurrentLicSectionText();
		if (!act.matches(expReg)) {
			throw new ErrorOnPageException("Current License Section text is wrong!", expReg, act);
		}
		logger.info("---Verify Current License section correctly!");
	}
	
	/** Verify Current License Section UI when there is at least one license */
	private void verifyCurrentLicSectionUI(int numDisplayed, boolean isMoreLinkExist) {
		HFCurrentLicensesListPage licListPg = HFCurrentLicensesListPage.getInstance();
		String detailsInLicensePage = StringUtil.EMPTY;
		boolean result = true;
		
		result &= MiscFunctions.compareResult("the number of displayed current licenses", numDisplayed, acctOvePg.getNumOfDisplayedLic());
		result &= MiscFunctions.compareResult("more licenses link exist", isMoreLinkExist, acctOvePg.isMoreLicenseLinkExist());
		
		String currentLicText = acctOvePg.getCurrentLicSectionText();
		currentLicText = currentLicText.replace(pageTitle, "").trim();	
		result &= MiscFunctions.matchString("license details", currentLicText, detailsText);
		
		if (isMoreLinkExist) {
			currentLicText = currentLicText.replace(moreLinkText, StringUtil.EMPTY);
			acctOvePg.clickMoreLicenseLink();		
		} else {
			acctOvePg.clickCurrentLicLink();
		}
		licListPg.waitLoading();
		detailsInLicensePage = licListPg.getLicensesDetailsText();
		result &= MiscFunctions.matchString("current license list", detailsInLicensePage.replaceAll(" ", StringUtil.EMPTY), "^" + currentLicText.replaceAll(" ", StringUtil.EMPTY) + ".*");
		
		if (!result) {
			throw new ErrorOnPageException("Current License Section displayed wrongly! Check log error");
		}
		logger.info("---Verify Current License Section UI correctly!");
	}
	
	/** Revoke Privilege in License Manager */
	private void revokePrivilegeInLM(PrivilegeInfo pri) {
		suspension.comment = suspension.comment  + " " +  pri.name + DateFunctions.getCurrentTime();
		logger.info("Revoke Privilege " + pri.name);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.addCustomerSuspension(suspension, pri);
	}
	
	/** Invalid Privilege in License Manager */
	private void invalidPrivilegeInLM(String ordNum) {
		logger.info("Invalid Privilege order: " + ordNum);
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
	}
}
