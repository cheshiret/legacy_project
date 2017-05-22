package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFHeaderBar;
import com.activenetwork.qa.awo.pages.web.hf.HFHomePage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFReplaceLostLicensesPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify page display when view pages required authentication under Identifier Mode
 * 1. Click different tab on header bar and verify account lookup page shown, except Home tab.
 * 2. Lookup account to Account Found page and click the tab again, and verify account lookup page
 * 3. Lookup account and proceed, and verify the specific page and empty shopping cart page is shown
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * @SPEC:
 * Configure to authenticate user by identifiers [TC:067792]
 * HFMO - Identify customer page displays when viewed page requies authentication [TC:067988]
 * @Task#: Auto-1831
 * 
 * @author Lesley Wang
 * @Date  Jul 22, 2013
 */
public class IdentifierMode_ViewPagesWithAuthentication extends HFMOWebTestCase {
	private HFHomePage homePg = HFHomePage.getInstance();
	private HFHeaderBar header = HFHeaderBar.getInstance();
	private HFAccountLookupPage acctLookupPg = HFAccountLookupPage.getInstance();
	private HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage.getInstance();
	private String purchaseTab, myAcctTab, acctOverPgUrl, updateAcctPgUrl, 
		ordHistPgUrl, licHistPgUrl, replactLostLicPgUrl, licCateListPgUrl, licListPgUrl, licDetailsPgUrl;
		
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}
		
		//1. verify home page and no sign in / sign up links
		hf.invokeURL(url);
		this.verifyPageAndSignLinks(homePg);
		
		//2. Click purchase license tab and verify
		this.viewDiffPageAndVerifyPageDisplay(purchaseTab, null, HFLicenseCategoriesListPage.getInstance());
		
		//3. Click My Account tab and verify
		hf.invokeURL(url);
		hf.gotoHFHomePg();
		this.viewDiffPageAndVerifyPageDisplay(myAcctTab, null, HFAccountOverviewPage.getInstance());
		
		//4. Input account overview page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, acctOverPgUrl, HFAccountOverviewPage.getInstance());
		
		//5. Input update account page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, updateAcctPgUrl, HFUpdateAccountPage.getInstance());

		//6. Input order history page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, ordHistPgUrl, HFOrderHistoryPage.getInstance());

		//7. Input license history page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, licHistPgUrl, HFCurrentLicensesListPage.getInstance());

		//8. Input Replace Lost Licenses page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, replactLostLicPgUrl, HFReplaceLostLicensesPage.getInstance());

		//9. Input License Category page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, licCateListPgUrl, HFLicenseCategoriesListPage.getInstance());

		//10. Input Product List page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, licListPgUrl, homePg);

		//11. Input Product Details page URL and verify
		this.viewDiffPageAndVerifyPageDisplay(StringUtil.EMPTY, licDetailsPgUrl, HFLicensePurchaseDetailsPage.getInstance());
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		// customer info
		cus.fName = "IdentMode02_FN"; // d_web_hf_signupaccount, id=860
		cus.lName = "IdentMode02_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; // for login in to add identifier
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		
		// header tab name and page urls
		purchaseTab = "Purchase";
		myAcctTab = "My Account";
		acctOverPgUrl = url + "memberAccountOverview.do"; 
		updateAcctPgUrl = url + "editHFProfile.do";
		ordHistPgUrl = url + "memberOrderHistory.do";
		licHistPgUrl = url + "memberHFLicenses.do";
		replactLostLicPgUrl = url + "duplicatePrivilegesList.do";
		licCateListPgUrl = url + "productCategoriesList.do?prodType=license&topTabIndex=PurchaseLicense";
		licListPgUrl = url + "categoryProductsList.do?catId=" + 
				hf.getDisplayCategoryID(schema, "1", "Fishing"); // Get the product category list page URL for category "Fishing"
		licDetailsPgUrl = url + "privilegePurchaseDetails.do?privId=" + 
				hf.getProductID("Product Name", "HFMO License001", null, true, schema, PRDCAT_PRIVILEGE); // Get the product "HFMO License001" details page URL
	}

	private void viewDiffPageAndVerifyPageDisplay(String tabName, String pageUrl, Page pgAfterLookupAcct) {
		//1. Click tab or invoke page URL before lookup account
		if (StringUtil.notEmpty(tabName)) {
			this.clickHeaderTab(tabName);
		} else if (StringUtil.notEmpty(pageUrl)) {
			hf.invokeURL(url); // Landing on Home page firslty
			hf.invokeURL(pageUrl, false, false);
		}
		acctLookupPg.waitLoading();
		this.verifyPageAndSignLinks(acctLookupPg);
		
		//2. Lookup Account to Account Found page then click Tab again
		if (StringUtil.notEmpty(tabName)) {
			hf.lookupAccountFromAccountLookupPage(cus);
			this.clickHeaderTab(tabName);
			acctLookupPg.waitLoading();
		}
		
		//3. Lookup Account and proceed correctly
		hf.lookupAccountFromAccountLookupPage(cus);
		acctFoundPg.clickConfirmAndProceed();
		pgAfterLookupAcct.waitLoading();
		this.verifyEmptyShopingCart();
		
		hf.signOut();
	}
	
	/** Click header tab per tab name */
	private void clickHeaderTab(String tabName) {
		if (tabName.equals(purchaseTab)) {
			header.clickPurchaseLicenseTab();
		} else if (tabName.equals(myAcctTab)) {
			header.clickMyAccountTab();
		} else {
			throw new ErrorOnPageException(tabName + " Tab Name is wrong!");
		}
	}
	
	/** Verify specific page exists and sign in sign up links don't exist */
	private void verifyPageAndSignLinks(Page pg) {
		boolean result = true;
		String pgNage = pg.getName();
		if (!pg.exists()) {
			result = false;
			logger.info(pgNage + " should exist!");
		}
		if (header.isSignInMode()) {
			result = false;
			logger.error("Sign in Link should not exist!");
		}
		if (header.isSignUpLinkExisting()) {
			result = false;
			logger.error("Sign up Link should not exist!");
		}

		if (!result) {
			throw new ErrorOnPageException(pgNage + " page and Sign in/Sign up links are displayed wrongly!");
		}
		logger.info("---Successfully Verify " + pgNage + " page and Sign in/Sign up links!");
	}

	/** Verify empty shopping cart. */
	private void verifyEmptyShopingCart() {
		String num = header.getNumOfItemsInCart();
		if (!num.equals("0")) {
			throw new ErrorOnPageException("Number of Items in Cart is wrong!", "0", num);
		}
		logger.info("---Successfully verify empty shopping cart!");
	}

}
