/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;

/**
 * @Description: There is already an existing Product Pricing record for the
 *               same Product that is also "Active" and that has the same values
 *               as this record for the following: Location Class,
 *               License/Fiscal Year From, and (if applicable) Purchase Type.
 * @Preconditions:
 * @SPEC:Add Product Pricing.DOC
 * @Task#:Auto-666
 * @Defects:
 * 
 * @author asun
 * @Date Jul 28, 2011
 */
public class VerifyConflictWithExistRecord_0
		extends LicMgrAddProductPricingTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		action = ExistRecord_Active_LocClass_YearFrom_PurchaseType;
		pricing.prodCode = "A3";
		pricing.prodType = "Privilege";
	}

}
