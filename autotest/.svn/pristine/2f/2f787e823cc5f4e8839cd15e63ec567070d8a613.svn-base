package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Based on leftover privilege quantity control, verify
 * 1. Quantity= "Min Quantity Per Transaction" in prd details page at first
 * 2. Quantity = "1" from the second time utill the 3 "Max Quantity per Transaction"
 * 3. Purchased leftover privilege can't displays in privileges list page when all 3 "Max Quantity per Transaction" add into cart
 * 4. Purchased leftover privilege displays in privileges list page after checkout previous order
 * 
 * @Preconditions:
 * The quantity control of the OTC/Leftover privilege
 * Multi-Sales Allowance: Yes (Regardless of License Year)
 * Max Quantity per Transaction: 3
 * Min Quantity Per Transaction: 2
 * 
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1040
 * d_hf_add_privilege_prd:id=2280
 * d_hf_add_pricing:id=3172
 * d_hf_assi_pri_to_store:id=1540
 * d_hf_add_prvi_license_year:id=2330
 * d_hf_add_qty_control:id=1520
 * d_hf_add_hunt:id=150
 * d_hf_assign_priv_to_hunt:id=150
 * d_hf_add_hunt_license_year:id=200
 * d_hf_add_hunt_quota:id=80
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC: TC101733:OTC/Leftover privilege - Quantity on Permit Details page
 * @Task#:Auto-1869
 * 
 * @author Swang
 * @Date  Sep 9, 2013
 */
public class QuantityOnPermitDetailsPg extends HFSKWebTestCase {
	private HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFLicenseCategoriesListPage categoriesPg = HFLicenseCategoriesListPage.getInstance();
	private HuntInfo hunt;
	private String qty1, qty2, cartValue1, cartValue2, voidAction, voidReason, voidNote;

	public void execute() {
		//Make privilege to Licence Details page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);

		//In prd details page, verify quantity is "Min Quantity Per Transaction" as 2 in prd details page when purchase at first time
		productDetailsPg.verifyLilcenseQtyText(qty1);

		//Go to shopping cart page to verify privilege info (2 items for selected hunt) and cart link value (Items:2)
		hf.addPrivilegeToCartFromPrdDetailsPg(privilege);
		shoppingCartPg.verifyPrivOrdItemInfo(privilege, qty1);
		shoppingCartPg.verifyCartLinkValue(cartValue1);

		//Continue shopping to prd details page, verify quantity is 1 from the second time when purchase the same privilege and same hunt
		hf.continueShppingToPrdDetailsPg(privilege);
		productDetailsPg.verifyLilcenseQtyText(qty2);

		//Go to shopping cart page to check privilege info (3 items for selected hunt) and cart link value (Items:3)
		hf.addPrivilegeToCartFromPrdDetailsPg(privilege);
		shoppingCartPg.verifyPrivOrdItemInfo(privilege, String.valueOf(Integer.parseInt(qty1)+Integer.parseInt(qty2)));
		shoppingCartPg.verifyCartLinkValue(cartValue2);

		//Continue shopping to prd list page to check previous purchased privilege can't display
		hf.continueShoppingToLicensePurchaseDetailsPg();
		categoriesPg.verifyPrivilegeExist(privilege.name, false);

		//Check out shopping
		hf.checkoutNow();
		String orderNum = hf.checkOutCart(pay);

		//Purchase same privilege again to prd details page
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);
		productDetailsPg.verifyLilcenseQtyText(qty1);

		//Sign out
		hf.signOut();

		//Void the order in LM
		lm.loginLicenseManager(loginLM);
		lm.voidPrivilegeOrder(orderNum, voidAction, voidReason, voidNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00024@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.fName = "LeftoverPri04_FN";
		cus.lName = "LeftoverPri04_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4159";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.code = "SLD";
		privilege.name = "HFSK LeftoverPriv004";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.validFromDate = DateFunctions.getToday("EEE MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));

		hunt = new HuntInfo();
		hunt.setHuntCode("LH6");
		hunt.setHuntId(hf.getHuntIdByHuntCode(hunt.getHuntCode(), schema));
		hunt.setDescription("Leftover sale hunt 06");
		hunt.setSpecie("Black Bear");
		hunt.setLicYear(privilege.licenseYear);
		privilege.setHuntInfo(hunt);

		qty1 = "2";
		qty2 = "1";
		cartValue1 = "Items:"+qty1;
		cartValue2 = "Items:"+(Integer.parseInt(qty1)+Integer.parseInt(qty2));

		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		voidAction = "Reverse";
		voidReason = "14 - Other";
		voidNote = "QA Test";
	}
}
