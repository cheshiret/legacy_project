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
 * @Date  Aug 26, 2011
 */
public class VerifyUIRequiremeant extends LicMgrAddProductPricingTestCase {

	@Override
	public void execute() {
	    lm.loginLicenseManager(login);
	    lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
	    pricingPage=lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
	    this.VerifyCommonUIRequirement();
	    lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		pricing.prodCode="009";
		pricing.prodType="supply";
	}

}
