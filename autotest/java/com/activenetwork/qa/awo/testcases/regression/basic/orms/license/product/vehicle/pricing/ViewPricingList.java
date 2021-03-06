/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.pricing;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrViewProductPricingListTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case was designed for verify vehicle pricing list  
 * @Preconditions:
 * @SPEC: View Product Pricing List
 * @Task#: Auto - 874
 * 
 * @author Jane Wang
 * @Date  Mar 13, 2012
 */
public class ViewPricingList extends LicMgrViewProductPricingListTestCase {

	private PricingInfo[] pricingInfo = new PricingInfo[3];
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		pricing.prodType = "Vehicle";
		pricing.prodCode = "Bv6";
		
		//search criteria
		pricing.status = "Active";
		pricing.locationClass = "04 - Commercial Agent";
		pricing.purchaseType = "Duplicate";

		pricingInfo[0] = new PricingInfo();
		pricingInfo[0].status = "Active";
		pricingInfo[0].prodType = pricing.prodType;
		pricingInfo[0].prodCode = pricing.prodCode;
		pricingInfo[0].locationClass = "All";
		pricingInfo[0].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 4);
		pricingInfo[0].licYearTo = pricingInfo[0].licYearFrom;
		pricingInfo[0].purchaseType = "Duplicate";
		pricingInfo[0].effectFrom = DateFunctions.getToday();
		pricingInfo[0].effectTo = DateFunctions.getDateAfterToday(30);
		pricingInfo[0].vendorFee = "24.15";
		pricingInfo[0].stateTransFee = "15.97";
		pricingInfo[0].stateFee = "12.31";
		pricingInfo[0].transFee = "14.34";

		pricingInfo[1] = new PricingInfo();
		pricingInfo[1].status = "Active";
		pricingInfo[1].prodType = pricing.prodType;
		pricingInfo[1].prodCode = pricing.prodCode;
		pricingInfo[1].locationClass = "02 - MDWFP District Office";
		pricingInfo[1].licYearFrom = "All";
		pricingInfo[1].licYearTo = "";
		pricingInfo[1].purchaseType = "Transfer";
		pricingInfo[1].effectFrom = DateFunctions.getDateAfterToday(-30);
		pricingInfo[1].effectTo = DateFunctions.getDateAfterToday(20);
		pricingInfo[1].vendorFee = "12.15";
		pricingInfo[1].stateTransFee = "13.97";
		pricingInfo[1].stateFee = "13.31";
		pricingInfo[1].transFee = "11.34";

		pricingInfo[2] = new PricingInfo();
		pricingInfo[2].status = "Active";
		pricingInfo[2].prodType = pricing.prodType;
		pricingInfo[2].prodCode = pricing.prodCode;
		pricingInfo[2].locationClass = "04 - Commercial Agent";
		pricingInfo[2].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 2);
		pricingInfo[2].licYearTo = pricingInfo[2].licYearFrom;
		pricingInfo[2].purchaseType = "Original";
		pricingInfo[2].effectFrom = DateFunctions.getDateAfterToday(-20);
		pricingInfo[2].effectTo = DateFunctions.getDateAfterToday(30);
		pricingInfo[2].vendorFee = "10.15";
		pricingInfo[2].stateTransFee = "18.97";
		pricingInfo[2].stateFee = "10.31";
		pricingInfo[2].transFee = "10.34";
		
		pricingInfos.add(pricingInfo[0]);
		pricingInfos.add(pricingInfo[1]);
		pricingInfos.add(pricingInfo[2]);

	}

}
