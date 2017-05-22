/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;

/**
 * @Description: The specified License/Fiscal Year From is not "All" and there
 *               is an existing Product Pricing record for the same Product that
 *               is also "Active" and where the License/Fiscal Year From is
 *               "All" and has the same values as this record for the following:
 *               Location Class and (if applicable) Purchase Type.
 * @Preconditions:
 * @SPEC:Add Product Pricing.DOC
 * @Task#:Auto-666
 * @Defects:
 * 
 * @author asun
 * @Date Jul 28, 2011
 */
public class VerifyConflictWithExistRecord_2
		extends LicMgrAddProductPricingTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		action = LocClass_NotAll_PurTypWithExistRecord_LocClass_All_PurTyp;
		pricing.prodCode = "I05";
		pricing.prodType = "vehicle";
	}

}
