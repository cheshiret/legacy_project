package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables.pricing.viewpricinglist;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrViewProductPricingListTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify view consumable pricing list. 
 * Verify business rule and UI requirement and search result.
 * @Preconditions:
 * @SPEC:View Product Pricing List.doc
 * @Task#:Auto-668
 * 
 * @author VZhang
 * @Date  Jul 29, 2011
 */
public class VerifyBusinessRulesAndUI extends LicMgrViewProductPricingListTestCase{

	private PricingInfo[] pricingInfo = new PricingInfo[4];

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		pricing.prodType = "Consumable";
		pricing.prodCode = "d01";
		//search critical
		pricing.status = "Active";
		pricing.locationClass = "All";

		pricingInfo[0] = new PricingInfo();
		pricingInfo[0].status = "Active";
		pricingInfo[0].prodType = pricing.prodType;
		pricingInfo[0].prodCode = pricing.prodCode;
		pricingInfo[0].locationClass = "All";
		pricingInfo[0].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 4);
		pricingInfo[0].licYearTo = pricingInfo[0].licYearFrom;
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
		pricingInfo[1].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 2);
		pricingInfo[1].licYearTo = pricingInfo[1].licYearFrom;
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
		pricingInfo[2].locationClass = "02 - MDWFP District Office";
		pricingInfo[2].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 3);
		pricingInfo[2].licYearTo = pricingInfo[2].licYearFrom;
		pricingInfo[2].effectFrom = DateFunctions.getDateAfterToday(-20);
		pricingInfo[2].effectTo = DateFunctions.getDateAfterToday(30);
		pricingInfo[2].vendorFee = "10.15";
		pricingInfo[2].stateTransFee = "18.97";
		pricingInfo[2].stateFee = "10.31";
		pricingInfo[2].transFee = "10.34";

		pricingInfo[3] = new PricingInfo();
		pricingInfo[3].status = "Active";
		pricingInfo[3].prodType = pricing.prodType;
		pricingInfo[3].prodCode = pricing.prodCode;
		pricingInfo[3].locationClass = "04 - Commercial Agent";
		pricingInfo[3].licYearFrom = "All";
		pricingInfo[3].licYearTo = "";
		pricingInfo[3].effectFrom = DateFunctions.getToday();
		pricingInfo[3].effectTo = DateFunctions.getDateAfterToday(20);
		pricingInfo[3].vendorFee = "16.15";
		pricingInfo[3].stateTransFee = "11.97";
		pricingInfo[3].stateFee = "15.31";
		pricingInfo[3].transFee = "12.34";

		pricingInfos.add(pricingInfo[0]);
		pricingInfos.add(pricingInfo[1]);
		pricingInfos.add(pricingInfo[2]);
		pricingInfos.add(pricingInfo[3]);	
	}

}

