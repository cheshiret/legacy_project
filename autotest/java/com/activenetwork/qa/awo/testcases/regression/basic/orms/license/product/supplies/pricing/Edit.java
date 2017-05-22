/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies.pricing;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrEditProductPricingTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed for verify edit a supply pricing   
 * @Preconditions:
 * @SPEC: Edit Product Pricing
 * @Task#: Auto - 874
 * 
 * @author Jane Wang
 * @Date  Mar 13, 2012
 */
public class Edit extends LicMgrEditProductPricingTestCase {

	private boolean result = true;
	private PricingInfo newPricing = new PricingInfo();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		productPricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
		lm.deactivateAllProductPricings(productPricingPage);
		// create a product pricing
		pricing.id = lm.addPricingForProduct(pricing, productPricingPage, true);
		lm.gotoEditProductPricingWidget(pricing.id, productPricingPage);

		// a. update product pricing record 	
		resetPricingInfo();
		newPricing.id = pricing.id;
		newPricing.id = lm.editProductPricing(newPricing, true, productPricingPage);
		
		expectedNewAddTime = String.valueOf(DateFunctions.getCurrentTime());
		expectedUpdateTime = String.valueOf(DateFunctions.getCurrentTime());
		
		// b. verify the old record still exists
		PricingInfo oldPricing = lm.getProductPricingInfo(pricing.id, productPricingPage);
		result &= verifyPricingAddUpdateInfo(oldPricing);
		
		// c. verify system adds a new Product Pricing with information 
		PricingInfo newPricingUI = lm.getProductPricingInfo(newPricing.id, productPricingPage);
		result &= verifyPricingInfo(newPricing, newPricingUI);
	
		if (!result) {
			throw new ErrorOnPageException(
					"The checkpoints are NOT all passed. Please refer log for the detail info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		pricing = new PricingInfo();
		pricing.prodCode = "B11";
		pricing.prodType = "Supply";
		
		pricing.status = OrmsConstants.ACTIVE_STATUS;
		pricing.locationClass = "02 - MDWFP District Office";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear()+1);
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(10);
		pricing.vendorFee = "2.00";
		pricing.stateTransFee = "2.00";
		pricing.stateFee = "2.00";
		pricing.transFee = "3.00";
		pricing.stateFee_SplitBy = "Amount";
		pricing.stateFee_SplitInto = "1";
		pricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"2.0" });
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "1";
		pricing.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.0" });
		pricing.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		pricing.createLocation = login.location.split("/")[1];

	}
	
	public void resetPricingInfo() {
		newPricing.prodCode = "B11";
		newPricing.prodType = "Supply";
		
		newPricing.status = OrmsConstants.ACTIVE_STATUS;
		newPricing.locationClass = "02 - MDWFP District Office";
		newPricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		newPricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear());
		newPricing.effectFrom = DateFunctions.getToday();
		newPricing.effectTo = DateFunctions.getDateAfterToday(15);
		newPricing.vendorFee = "10.00";
		newPricing.stateTransFee = "10.00";
		newPricing.stateFee = "5.00";
		newPricing.transFee = "3.00";
		newPricing.stateFee_SplitBy = "Percent";
		newPricing.stateFee_SplitInto = "2";
		newPricing.stateFee_accounts.add(new String[] {
				"POS Revenue - Conference Center Rental",
				"98.0" });
		newPricing.stateFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"2.0" });
		newPricing.transFee_SplitBy = "Percent";
		newPricing.transFee_SplitInto = "1";
		newPricing.transFee_accounts.add(new String[] {
				"POS Revenue - General Entrance / Daily Permits - Fisheries",
				"100.0" });	
		newPricing.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		newPricing.createLocation = login.location.split("/")[1];
	}
}
