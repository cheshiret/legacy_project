/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.supplies.pricing.add;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 28, 2011
 */
public class VerifyErrorMessages extends LicMgrAddProductPricingTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		action=ERROR;
		pricing.prodCode="002";
		pricing.prodType="supply";
		pricing.locationClass="All";
		pricing.licYearFrom=Integer.parseInt(DateFunctions.getToday().split("/")[2])+2+"";
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
