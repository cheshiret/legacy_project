/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies.pricing;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case was designed for verify add a supply pricing   
 * @Preconditions:
 * @SPEC: Add Product Pricing
 * @Task#: Auto - 874
 * 
 * @author Jane Wang
 * @Date  Mar 13, 2012
 */
public class Add extends LicMgrAddProductPricingTestCase {

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		pricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage);
		pricing.id = lm.addPricingForProduct(pricing, pricingPage, true);

		verifyPricingInfoInPricingWidget(pricingPage, pricing);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {		
		pricing.prodCode = "B10";
		pricing.prodType = "Supply";
		
		pricing.status = "Active";
		pricing.locationClass = "04 - Commercial Agent";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear()+1);
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(10);
		pricing.vendorFee = "20.00";
		pricing.stateTransFee = "15.00";
		pricing.stateFee = "10.00";
		pricing.transFee = "3.00";
		pricing.stateFee_SplitBy = "Amount";
		pricing.stateFee_SplitInto = "2";
		
		pricing.stateFee_accounts.clear();
		pricing.stateFee_accounts.add(new String[]{"POS Revenue - General Entrance / Daily Permits - Fisheries", "8.0"});
		pricing.stateFee_accounts.add(new String[]{"POS Revenue - Conference Center Rental", "2.0"});
		
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.clear();
		pricing.transFee_accounts.add(new String[]{"POS Revenue - General Entrance / Daily Permits - Fisheries", "100.0"}); 
		
		pricing.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		pricing.createLocation = login.location.split("/")[1];
	}
}
