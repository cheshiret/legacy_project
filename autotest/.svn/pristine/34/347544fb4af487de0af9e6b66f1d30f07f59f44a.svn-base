package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.shoppingcart.timer;

import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (P) H&F private label, doesn't show timer in shopping cart page
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=560
 * d_hf_add_privilege_prd:id=2030
 * @SPEC: Shopping Cart Timer - Add Second Count [TC:068519]
 * @Task#: AUTO-1868
 * 
 * @author SWang
 * @Date  Aug 19, 2013
 */
public class NoTimer extends HFMOWebTestCase {
	private HFShoppingCartPage hfShoppingCartPg = HFShoppingCartPage.getInstance();

	public void execute() {
		//Purchase privilege to shopping cart page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.makePrivilegeOrderToCart(cus, privilege);

		//Verify no timer in shopping cart page
		hfShoppingCartPg.verifyTimerExistingOrNot(false);

		//Sign out
		hf.abandonCart();
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_OTHER_NUM_ID, true, true);
		cus.identifier.identifierNum = "1R9Y4x4140";
		cus.identifier.country = "Canada";
		cus.identifier.state = "Alberta";

		//Privilege Info
		privilege.name = "HFMO License001";
		privilege.code = "MOA";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = Integer.toString(DateFunctions.getCurrentYear());
	}
}
