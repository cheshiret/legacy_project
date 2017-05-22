/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:Add Product Pricing.DOC
 * @Task#:Auto-666
 * 
 * @author asun
 * @Date  Aug 8, 2011
 */
public class VerifyAddPricingSuccussfully extends
		LicMgrAddProductPricingTestCase {

	@Override
	public void wrapParameters(Object[] param) {
	    action=SUCCESS;
	    pricing.prodType="Vehicle";
	    pricing.prodCode="I8";
	}

}
