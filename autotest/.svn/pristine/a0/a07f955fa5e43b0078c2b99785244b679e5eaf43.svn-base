package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.businessrule.notpurchaseifprdawarded;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoryPrdListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKUnlockedPrivTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the rule "Cannot purchase IF parameter product awarded" with the following setup in HFSK site.
 * Match License Year
 * Display warning 4015
 * @Preconditions:
 * 1. the following hunts exist: 
 * a. "UH2 - Unlock hunt 02" - Limited / No quota added; has two license year records: current and next years
 * b. "LH21 - Leftover sale hunt 21" - Limited / has quota added; has two license year records: current and next years
 * c. the added quota "Leftover sale quota 08" has two license years: current and next years
 * 2. the product "SR6 - HFSK UnlockPrivRule006" exists with the following setup: 
 * a. the above 2 hunts assigned to the product;
 * b. has 2 license year records: current and next years
 * 3. the product "SR3 - HFSK PrivRule003" exists:
 * a. has 2 license year records: current and next years
 * b. has a business rule "Cannot purchase IF parameter product awarded" - Match License Year / Display warning 4015; Parameter product: SR6 - HFSK UnlockPrivRule006 
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2340,2370
 * d_hf_add_pricing:id=3232,3262
 * d_hf_assi_pri_to_store:id=1590,1620
 * d_hf_add_prvi_license_year:id=2400,2410,2460,2470
 * d_hf_add_qty_control:id=1570,1600
 * d_hf_add_pri_business_rule:id=370
 * d_web_hf_signupaccount:id=1090
 * d_hf_add_hunt:id=310,320
 * d_hf_assign_priv_to_hunt:id=310,320
 * d_hf_add_hunt_license_year:id=360,370,380,390
 * d_hf_add_quota_license_year:id=10
 * @SPEC: Rule setup: Match Licence Year / Display Warning 4015 [TC:102414]
 * @Task#: Auto-1866
 * 
 * @author Lesley Wang
 * @Date  Sep 12, 2013
 */
public class MatchLicYear_DisplayMsg extends
		HFSKUnlockedPrivTestCase {
	private PrivilegeInfo priv2, unlockedPriv1, unlockedPriv2, leftOverPriv1, leftOverPriv2;
	private HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage.getInstance();
	private HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	private PrivilegeInfo[] privileges;
	private Boolean[] displayed;
	private String warningMsg;
	
	@Override
	public void execute() {
		// Clean Up - invalid all privileges and deactivate all unlocked privileges
		lm.loginLicenseManager(loginLM);
		lm.invalidateAllPrivilegesPerCustomer(cus, new String[] {privilege.licenseYear, priv2.licenseYear}, searchStatus, privilege.operateReason, privilege.operateNote);
		lm.deactivateAllUnlockedPrivilege(cus);
		lm.logOutLicenseManager();		
		
		// Go to HFSK to make sure the privileges "SR3" and "SR6" with different license years can be added to shopping cart.
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrderToCart(cus, privilege, priv2, leftOverPriv1, leftOverPriv2);
		cartPg.verifyPrivilegesExist(privilege, priv2, leftOverPriv1, leftOverPriv2);
		hf.abandonCart();
		hf.signOut();
		
		// In LM, import the 1 records of the unlocked privileges "SR6" with current license year
		this.prepareUnlockedPriv(fileName, unlockedPriv1, cus.custNum, hunt);
		
		// In Web, verify the 1 awarded unlocked privilege shown and the privilege "SR3" with current license year shown a message
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoLicenseCategoriesListPg(cus.residencyStatus, cus.identifier);
		this.updateExpectedDisplayResult(true, true, true, true, true, false);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(privileges, displayed);
		this.verifyWarningMsgOnPrdDetailsAndListPg(true, false);
		hf.signOut();
		
		// In LM, deactivate the awarded unlocked privilege, and import a record with next license year
		lm.loginLicenseManager(loginLM);
		lm.deactivateAllUnlockedPrivilege(cus);
		lm.logOutLicenseManager();
		
		this.prepareUnlockedPriv(fileName, unlockedPriv2, cus.custNum, hunt);
		
		// In Web, verify only 1 awarded unlocked privilege shown, and the privilege "SR3" with current license year shown, but with next year shown a message
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoLicenseCategoriesListPg(cus.residencyStatus, cus.identifier);
		this.updateExpectedDisplayResult(true, true, true, true, false, true);
		catListPg.filterPrivAndVerifyPrivInCategoryListPg(privileges, displayed);
		this.verifyWarningMsgOnPrdDetailsAndListPg(false, true);

		// Purchase the privilege "SR3" with current license year successfully
		hf.makePrivilegeOrderToCart(cus, privilege);
		hf.checkOutCart(pay);
		
		// Purchase the awarded privilege "SR6" with next license year successfully
		hf.makePrivilegeOrderToCart(cus, unlockedPriv2);
		hf.checkOutCart(pay);
		
		// Verify the privilege "SR3" with next license year shown and purchased successfully
		hf.makePrivilegeOrderToCart(cus, priv2);
		hf.checkOutCart(pay);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer info
		cus.fName = "PurchaseRule03_FN";
		cus.lName = "PurchaseRule03_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16) + "-01-01";
		cus.identifier.id = OrmsConstants.IDEN_RCMP_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, false);
		cus.identifier.identifierNum = "1R9Y4x4164";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("UH2");
		hunt.setDescription("Unlock hunt 02");
				
		// Privilege info
		privilege.code = "SR3";
		privilege.name = "HFSK PrivRule003";
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
		privilege.displayCategory = "Hunting";
		privilege.displaySubCategory = "Big Game";
		
		priv2 = new PrivilegeInfo();
		priv2.name = privilege.name;
		priv2.licenseYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(1));
		priv2.displayCategory = privilege.displayCategory;
		priv2.displaySubCategory = privilege.displaySubCategory;
		
		leftOverPriv1 = new PrivilegeInfo();
		leftOverPriv1.code = "SR6";
		leftOverPriv1.name = "HFSK UnlockPrivRule006";
		leftOverPriv1.licenseYear = privilege.licenseYear;
		leftOverPriv1.displayCategory = privilege.displayCategory;
		leftOverPriv1.displaySubCategory = privilege.displaySubCategory;
		
		leftOverPriv2 = new PrivilegeInfo();
		leftOverPriv2.code = leftOverPriv1.code;
		leftOverPriv2.name = leftOverPriv1.name;
		leftOverPriv2.licenseYear = priv2.licenseYear;
		leftOverPriv2.displayCategory = privilege.displayCategory;
		leftOverPriv2.displaySubCategory = privilege.displaySubCategory;
		
		unlockedPriv1 = new PrivilegeInfo();
		unlockedPriv1.code = leftOverPriv1.code;
		unlockedPriv1.name = leftOverPriv1.name + " - " + hunt.getDescription();
		unlockedPriv1.licenseYear = privilege.licenseYear;
		unlockedPriv1.displayCategory = privilege.displayCategory;
		unlockedPriv1.displaySubCategory = privilege.displaySubCategory;
		
		unlockedPriv2 = new PrivilegeInfo();
		unlockedPriv2.code = unlockedPriv1.code;
		unlockedPriv2.name = unlockedPriv1.name;
		unlockedPriv2.licenseYear = priv2.licenseYear;
		unlockedPriv2.displayCategory = privilege.displayCategory;
		unlockedPriv2.displaySubCategory = privilege.displaySubCategory;
		
		privileges = new PrivilegeInfo[] {privilege, priv2, leftOverPriv1, leftOverPriv2, unlockedPriv1, unlockedPriv2};
		displayed = new Boolean[] {true, true, true, true, false, false};
		
		fileName = "BR_MatchLicYear_Display";
		warningMsg = "This specific licence cannot be purchased at this time. If you need help, Contact us.";
	}

	private void updateExpectedDisplayResult(boolean isPrivExist, boolean isPriv2Exist, boolean isLeftPriv1Exist, 
			boolean isLeftPriv2Exist, boolean isULPriv1Exist, boolean isULPriv2Exist) {
		displayed[0] = isPrivExist;
		displayed[1] = isPriv2Exist;
		displayed[2] = isLeftPriv1Exist;
		displayed[3] = isLeftPriv2Exist;
		displayed[4] = isULPriv1Exist;
		displayed[5] = isULPriv2Exist;
	}
	
	private void verifyWarningMsgOnPrdDetailsAndListPg(boolean isMsgShownForPriv1, boolean isMsgShownForPriv2) {
		hf.gotoPrdDetailsPgFromLicenseCategoryListPg(privilege, null);
		this.verifyRuleMessageOnPrdDetailsPg(isMsgShownForPriv1);
		hf.goBackFromPrdPurchaseDetailsPg();
		hf.gotoPrdDetailsPgFromLicenseCategoryListPg(priv2, null);
		this.verifyRuleMessageOnPrdDetailsPg(isMsgShownForPriv2);
		
		hf.goBackFromPrdPurchaseDetailsPg();
		hf.searchLicensetoLicenseListPg(privilege.displayCategory, StringUtil.EMPTY, StringUtil.EMPTY);
		prdListPg.filterPrivilege(privilege.displaySubCategory, StringUtil.EMPTY, privilege.licenseYear);
		this.verifyRuleMessageOnPrdListPg(isMsgShownForPriv1, privilege.name);
		prdListPg.filterPrivilege(StringUtil.EMPTY, StringUtil.EMPTY, priv2.licenseYear);
		this.verifyRuleMessageOnPrdListPg(isMsgShownForPriv2, priv2.name);				
	}
	
	private void verifyRuleMessageOnPrdDetailsPg(boolean isShown) {
		boolean result = true;
		result &= MiscFunctions.compareResult("The Warning Message shown", isShown, prdDetailsPg.isNotPurchaseInfoExist());
		if (isShown) {
			result &= MiscFunctions.compareString("Warning message", warningMsg, prdDetailsPg.getNotPurchaseInfoMsg());
			result &= MiscFunctions.compareResult("Contact us link", true, prdDetailsPg.isContactUsLinkExistInMsg());
		}
		result &= MiscFunctions.compareResult("Price info shown", !isShown, prdDetailsPg.isPriceInfoExist());
		result &= MiscFunctions.compareResult("Purchase Details info shown", !isShown, prdDetailsPg.isPurchaseDetailsInfoExist());
		result &= MiscFunctions.compareResult("Add to Cart button shown", !isShown, prdDetailsPg.isAddToCartBtnExist());
		
		if (!result) {
			throw new ErrorOnPageException("The product details page is wrong! check logger error!");
		}
		logger.info("Verify product details page correctly!");
	}
	
	private void verifyRuleMessageOnPrdListPg(boolean isShown, String privName) {
		boolean result = true;
		result &= MiscFunctions.compareResult("The Warning Message shown", isShown, prdListPg.isNotPurchaseInfoExist(privName));
		if (isShown) {
			result &= MiscFunctions.compareString("Warning message", warningMsg, prdListPg.getNotPurchaseInfoMsg(privName));
			result &= MiscFunctions.compareResult("Contact us link", true, prdListPg.isContactUsLinkExistInMsg(privName));
		}
		result &= MiscFunctions.compareResult("Buy Licence button shown", !isShown, prdListPg.isBuyPrivilegeLinkExist(privName));
		
		if (!result) {
			throw new ErrorOnPageException("The products list page is wrong! check logger error!");
		}
		logger.info("Verify products list page correctly!");
	}
}
