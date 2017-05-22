/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.pricing.add;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:Add Product Pricing.doc
 * @Task#:AUTO-666
 * @Defects:
 * 
 * @author asun
 * @Date  Aug 3, 2011
 */
public class VerifyUIRequiremeant extends LicMgrAddProductPricingTestCase {

	@Override
	public void execute() {
	    lm.loginLicenseManager(login);
	    LicMgrProductPage prodPage = lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
	    
	    checkAndAddProduct(prodPage);
	    
	    pricingPage=lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
	    this.VerifyCommonUIRequirement();
	    lm.logOutLicenseManager();
	}
	@Override
	public void wrapParameters(Object[] param) {
		pricing.prodCode="P07";
		pricing.prodType="Consumable";
	}

}
