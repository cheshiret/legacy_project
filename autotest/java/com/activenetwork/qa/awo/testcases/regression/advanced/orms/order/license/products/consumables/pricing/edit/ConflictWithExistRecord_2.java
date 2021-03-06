package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.pricing.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductPricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrEditProductPricingTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify the system displays correct error message when editing a pricing as License/Fiscal Year From is "All" conflict with 
 * an existing Product Pricing record for the same Product that is also "Active" and where the License/Fiscal Year From is not "All" and has 
 * the same values as this record for the following: Location Class and (if applicable) Purchase Type
 * @Preconditions:
 * @SPEC: <<Edit Product Pricing.doc>> and <<Add Product Pricing.doc>>
 * @Task#: AUTO-667
 * 
 * @author qchen
 * @Date  Sep 1, 2011
 */
public class ConflictWithExistRecord_2 extends LicMgrEditProductPricingTestCase {

	@Override
	public void resetPricingInfo() {
		pricing.status = "Active";
		pricing.locationClass = "04 - Commercial Agent";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear());
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getToday();
		pricing.vendorFee = "16";
		pricing.stateTransFee = "8";
		
		pricing.stateFee = "6";
		pricing.stateFee_SplitBy = "Percent";
		pricing.stateFee_SplitInto = "2";
		if(pricing.stateFee_accounts.size() == 0) {
			pricing.stateFee_accounts.add(new String[]{"POS Revenue - General Entrance / Daily Permits - Fisheries", "80"});
			pricing.stateFee_accounts.add(new String[]{"Standard Campsite - Revenue", "20"});
		} else {
			pricing.stateFee_accounts.get(0)[0] = "POS Revenue - General Entrance / Daily Permits - Fisheries";
			pricing.stateFee_accounts.get(0)[1] = "80";
			pricing.stateFee_accounts.get(1)[0] = "Standard Campsite - Revenue";
			pricing.stateFee_accounts.get(1)[1] = "20";
		}
		
		pricing.transFee = "4";
		pricing.transFee_SplitBy = "Percent";
		pricing.transFee_SplitInto = "2";
		if(pricing.transFee_accounts.size() == 0) {
			pricing.transFee_accounts.add(new String[]{"POS Revenue - Apparel", "60"});
			pricing.transFee_accounts.add(new String[]{"Standard Campsite - Revenue", "40"});
		} else {
			pricing.transFee_accounts.get(0)[0] = "POS Revenue - Apparel";
			pricing.transFee_accounts.get(0)[1] = "60";
			pricing.transFee_accounts.get(1)[0] = "Standard Campsite - Revenue";
			pricing.transFee_accounts.get(1)[1] = "40";
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		VERIFY_EDIT_LICYEAR_ALL_CONFLICT_WITH_EXISTING_LICYEAR_NOTALL_ACTIVE_RECORD = true;
		productPricingPage = LicMgrConsumableProductPricingPage.getInstance();
		
		pricing = new PricingInfo();
		resetPricingInfo();
		pricing.prodType = "Consumable";
		pricing.prodCode = "C29";
		pricing.status = "Active";
		
		existingPricing = new PricingInfo();
		existingPricing.prodType = pricing.prodType;
		existingPricing.prodCode = pricing.prodCode;
		existingPricing.status = pricing.status;
		existingPricing.locationClass = pricing.locationClass;
		existingPricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 1);
		existingPricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		existingPricing.effectFrom = DateFunctions.getToday();
		existingPricing.effectTo = DateFunctions.getToday();
		existingPricing.vendorFee = "13";
		existingPricing.stateTransFee = "7";
		
		existingPricing.stateFee = "5";
		existingPricing.stateFee_SplitBy = "Percent";
		existingPricing.stateFee_SplitInto = "2";
		existingPricing.stateFee_accounts.add(new String[]{"POS Revenue - General Entrance / Daily Permits - Fisheries", "80"});
		existingPricing.stateFee_accounts.add(new String[]{"Standard Campsite - Revenue", "20"});
		
		existingPricing.transFee = "3";
		existingPricing.transFee_SplitBy = "Percent";
		existingPricing.transFee_SplitInto = "2";
		existingPricing.transFee_accounts.add(new String[]{"POS Revenue - Apparel", "60"});
		existingPricing.transFee_accounts.add(new String[]{"Standard Campsite - Revenue", "40"});
		
	}
}
