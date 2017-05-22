/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:Add Product Pricing 
 * @Task#:Auto-666
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 25, 2011
 */
public class VerifyErrorMessages extends LicMgrAddProductPricingTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		action=ERROR;
		pricing.prodCode="A2";
		pricing.prodType="Privilege";
		pricing.locationClass="All";
		pricing.licYearFrom="All";
		pricing.purchaseType="Original";
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