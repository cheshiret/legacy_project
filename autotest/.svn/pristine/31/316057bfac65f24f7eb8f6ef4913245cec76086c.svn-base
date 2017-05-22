package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edu;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) 
 * @Preconditions:
 * d_web_hf_signupaccount, id=770, Edu4_FN, Edu4_FN, Green Card|1R9Y4x4148|Canada| |
 * d_hf_add_privilege_prd, id=2060, MOD, HFMO License004
 * d_hf_add_qty_control, id=1290, MULTISALESALLOWANCE = "No Multiple Allowed"
 * 
 * @SPEC: Education Information page - Remove 'pending' item from shopping cart [TC:047256] 
 * @Task#: Auto-1763
 * 
 * @author Swang
 * @Date  Jul 10, 2013
 */
public class RemovePendingItemFromShoppingCart extends HFMOWebTestCase {
	private HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFEducationInfomationAddedPage eduInfoAddedPg = HFEducationInfomationAddedPage.getInstance();
	private Education edu;

	public void execute() {
		//Lookup account, make privilege to education information required page
		hf.signIn(url, cus.email, cus.password);

		//Check point 1: License Quantity text displays when product set up as No multiple purchases allowed
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);
		verifyQuantityCanNotEdit();
		//A pending item of this product added in the shopping cart, no visible for the user
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduInfoRequiredPg);

		//Check point 2: Don't enter anything, click "My Account" tab, no pop-up, it goes to account overview page. 
		//To product list page, because the pending item is removed from shopping cart, so the same product can be selected
		//To education info page from product details page, a pending item of this product added in the shopping cart, no visible for the user
		hf.gotoMyAccountPgFromHeaderBar();
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);

		//Check point 3: Enter education info (12345, United States, New York), click "Home" tab, has pop-up, click "Leave this page", it goes to home page.
		//To product list page, because the pending item is removed from shopping cart, so the same product can be selected
		//To education info page from product details page, a pending item of this product added in the shopping cart, no visible for the user
		eduInfoRequiredPg.setupEducationInfo(edu);
		hf.gotoHFHomePg(true, eduInfoRequiredPg);
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);

		//Check point 4: Enter education info (12345, United States, New York), click submit to education added page
		//To product list page, because the pending item is removed from shopping cart, so the same product can be selected
		hf.updateEdu(edu);
		eduInfoAddedPg.waitLoading();
		//Go to home page, then page privilege to shopping cart directly without an education page displaying because the education number '12345' already saved in the session
		hf.gotoHFHomePg();
		hf.makePrivilegeOrder(cus, privilege, shoppingCartPg);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfmo_00018@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "Edu4_FN";
		cus.lName = "Edu4_LN";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_CONSERVATION_ID, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		//Privilege parameters
		privilege.name = "HFMO LICENSE004"; //Make sure this product set up as No multiple purchases allowed
	    privilege.licenseYear = hf.getFiscalYear(schema);
	      
		//Education info
		edu = new Education();
		edu.educationNum = "12345";
		//Sara[12/09/2013] This is a low priority PCR confirmed with Ranjita.
		//This should be the value query "select name from d_ref_country where DSCR = 'United States of America'", not DSCR "United States of America";
		edu.country = "United States";
		edu.state = "New York";
	}

	private void verifyQuantityCanNotEdit(){
		HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
		if(productDetailsPg.isLicenseQtyTextDisplayed()){
			logger.info("Successfully verify License Quantity text displays in Product Details page when product set up as No multiple purchases allowed.");
		}else throw new ErrorOnPageException("Failed to verify License Quantity text displays in Product Details page when product set up as No multiple purchases allowed.");
	}
}
