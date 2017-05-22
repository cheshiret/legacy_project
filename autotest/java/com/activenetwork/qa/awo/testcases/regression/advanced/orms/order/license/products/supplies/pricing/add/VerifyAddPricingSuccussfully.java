/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  Aug 8, 2011
 */
public class VerifyAddPricingSuccussfully extends
		LicMgrAddProductPricingTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		action=SUCCESS;
		pricing.prodType="supply";
		pricing.prodCode="008";
	}

}
