/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:Add Product Pricing
 * @Task#:AUTO-666
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 25, 2011
 */
public class CancelWhenAddingPricing extends LicMgrAddProductPricingTestCase{

	@Override
	public void wrapParameters(Object[] param) {
		action=CANCEL;
		pricing.prodCode="I01";
		pricing.prodType="vehicle";
		pricing.locationClass="All";
		pricing.licYearFrom="All";
		pricing.effectFrom=DateFunctions.getToday();
		pricing.vendorFee="1";
		pricing.stateTransFee="1";
		pricing.stateFee="2";
		pricing.transFee="3";
		pricing.stateFee_SplitBy="Amount";
		pricing.stateFee_SplitInto="1";
		pricing.transFee_SplitBy="Percent";
		pricing.transFee_SplitInto="1";
	}

}