package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.pricing.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclePricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrEditProductPricingTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify the edit product pricing widget doesn't violate the business rules
 * @Preconditions: Need to create a vehicle product
 * @SPEC: <<Edit Product Pricing.doc>> and <<Add Product Pricing.doc>>
 * @Task#: AUTO-667
 * 
 * @author QA-qchen
 * @Date  Aug 10, 2011
 */
public class VerifyEditingBusinessRule extends LicMgrEditProductPricingTestCase {
	@Override
	public void wrapParameters(Object[] param) {
		VERIFY_BUSINESS_RULE = true;
		productPricingPage = LicMgrVehiclePricingPage.getInstance();
		
		pricing = new PricingInfo();
		pricing.prodType = "Vehicle";
		pricing.prodCode = "V10";
		
		resetPricingInfo();
	}
	
	@Override
	public void resetPricingInfo() {
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = DateFunctions.getToday().split("/")[2];
		pricing.licYearTo = DateFunctions.getToday().split("/")[2];
		pricing.purchaseType = "Original";
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
}
