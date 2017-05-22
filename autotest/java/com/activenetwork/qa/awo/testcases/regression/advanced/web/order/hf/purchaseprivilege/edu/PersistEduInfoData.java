package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edu;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFHeaderBar;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify persist data in education info required page after click "Return to product details" link  or other links or tabs, i.e. navigate away from the purchase flow
 * @Preconditions:
 * d_web_hf_signupaccount, id=760, hfmo_00017@webhftest.com, Edu3_FN, Edu3_LN
 * d_hf_add_privilege_prd, id=2050, MOC, HFMO License003
 * @SPEC: Education Information page - Persist Data [TC:047162]
 * @Task#: Auto-1763
 * 
 * @author Swang
 * @Date  Jul 2, 2013
 */
public class PersistEduInfoData extends HFMOWebTestCase {
	private HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();
	private ConfirmDialogPage confimDialog = ConfirmDialogPage.getInstance();
	private Education edu1, edu2, edu3;
//	private String mesOnPopupWindow, mesOnPopupWindowFromUI;
	private PrivilegeInfo priv2 = new PrivilegeInfo();

	public void execute() {
		//Lookup account, make privilege to education information required page
		hf.signIn(url, cus.email, cus.passExpiryDate);
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);

		//Check point 1: Don't enter anything, click "My Account" tab, no pop-up, it goes to account overview page. Select the same product to education info page, all display as initial
		hf.gotoMyAccountPgFromHeaderBar();
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		//Check point 2: Enter education info (12345, Canada, Ontario), click "Cancel" link, has pop-up, click "Leave this page", it goes to product details page. Select the same product to education info page, all the entry data and selections are not retained
		eduInfoRequiredPg.setupEducationInfo(edu2);
//		mesOnPopupWindowFromUI =
				hf.cancelToUpdateEdu(true);
//		verifyMesOnPopupWindow(mesOnPopupWindow, mesOnPopupWindowFromUI);
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1);

		//Check point 3: Enter education info (12345, Canada, Ontario), click "Return to Permit Details" link, no pop-up, it goes to product details page. Select the same product to education info page, all the entry data and selection retained, i.e. it shows education ('12345', 'Canada' and 'Ontario') 
		eduInfoRequiredPg.setupEducationInfo(edu2);
		hf.returnToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1);//Sara[12/13/2013] based on DEFECT-58660, edu1

		//Check point 4: Don't change anything, click on "Home" link, no pop-up, it goes to home page. Select the same product to education info page, all previous entry data and selection retained, i.e. it shows education ('12345', 'Canada' and 'Ontario') 
		hf.gotoHFHomePg();
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1); //edu2

		//Check point 5: Change education info (abcdefg, United States, New York), click "Sign Out" link, has pop-up, click "Stay on this page", stays on Education page with all the data and selection retained
		eduInfoRequiredPg.setupEducationInfo(edu3);
		signOutFunc();
//		verifyMesOnPopupWindow(mesOnPopupWindow, mesOnPopupWindowFromUI);
		eduInfoRequiredPg.verifyEducationInfo(edu3);

		//Check point 6: Click "Items in cart x" link, has pop-up window, click "Leave this page", it goes to shopping cart page. Select the same product to education info page, the new change is lost and all previous retained entry data and selection displayed, i.e. it shows education ("12345", 'Canada' and 'Ontario')
		hf.cancelToUpdateEdu(true);
		hf.makePrivilegeOrderToCart(priv2);
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.setupEducationInfo(edu3);
		itemsInCartLinkFunc();
//		verifyMesOnPopupWindow(mesOnPopupWindow, mesOnPopupWindowFromUI);
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1); //edu2

		//Check point 7: Change education info (abcdefg, United States, New York), click "Return to Product Details" page link, no pop-up, it goes to product details page. Select the same product to education info page, all the new entry data and selection retained, i.e. it shows education ("abcdefg", 'United States' and 'New York')
		eduInfoRequiredPg.setupEducationInfo(edu3);
		hf.returnToProductDetailsPage();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);
		eduInfoRequiredPg.verifyEducationInfo(edu1); //edu3

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfmo_00017@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "Edu3_FN";
		cus.lName = "Edu3_LN";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_CONSERVATION_ID, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		//Privilege parameters
		privilege.name = "HFMO LICENSE003";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "1";

		priv2.name = "HFMO License001";
		priv2.licenseYear = privilege.licenseYear;
		
		//Education info
		edu1 = new Education();
		edu1.educationNum = StringUtil.EMPTY;
		//Sara[12/09/2013] This is a low priority PCR confirmed with Ranjita.
		//This should be the value query "select name from d_ref_country where DSCR = 'United States of America'", not DSCR "United States of America";
		edu1.country = "United States";
		edu1.state = "Missouri";

		edu2 = new Education();
		edu2.educationNum = "12345";
		edu2.country = "Canada";
		edu2.state = "Ontario";

		edu3 = new Education();
		edu3.educationNum = "abcdefg";
		edu3.country = edu1.country;
		edu3.state = "New York";

		//Per James, Windows Internet Explorer dialog message is pretty much static message from Browser and will never change
//		mesOnPopupWindow = "Are you sure you want to navigate away from this page\\? ?Your data will not be saved\\. ?Press OK to continue, or Cancel to stay on the current page\\.";
	}

	/**
	 * Pop-up window displays after click Sign out link, then click Cancel from pop-up to education info required page 
	 * @return the message from pop-up
	 */
	private String signOutFunc(){
		HFHeaderBar header = HFHeaderBar.getInstance();
		header.clickSignOutLink();
		confimDialog.setDismissible(false);
		confimDialog.waitLoading();

		String value = confimDialog.text();
		confimDialog.clickCancel();
		eduInfoRequiredPg.waitLoading();
		return value;
	}

	/**
	 * Pop-up displays after click Items in cart link, then click OK from pop-up to shopping cart page
	 * @return the message from pop-up
	 */
	private String itemsInCartLinkFunc(){
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		eduInfoRequiredPg.clickItemsInCart();
		confimDialog.setDismissible(false);
		confimDialog.waitLoading();

		String value = confimDialog.text();
		confimDialog.clickOK();
		shoppingCartPg.waitLoading();

		logger.info("Confimation message:"+value);
		return value;
	}
//
//	/**
//	 * Verify message on pop-up window
//	 * @param expectedValue
//	 * @param actualValue
//	 */
//	private void verifyMesOnPopupWindow(String expectedValue, String actualValue){
//		if(!actualValue.matches(expectedValue)){
//			throw new ErrorOnPageException("Message on popup window is wrong!", expectedValue, actualValue);
//		}
//		logger.info("Successfully verify message on popup window.");
//	}
}