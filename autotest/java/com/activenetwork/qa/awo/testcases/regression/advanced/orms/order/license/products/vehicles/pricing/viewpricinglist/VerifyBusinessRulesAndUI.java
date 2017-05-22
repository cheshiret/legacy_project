package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.vehicles.pricing.viewpricinglist;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrViewProductPricingListTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify view vehicle pricing list. 
 * Verify business rule and UI requirement and search result.
 * This case blocked by DEFECT-30288 
 * @Preconditions:
 * @SPEC:View Product Pricing List.doc
 * @Task#:Auto-668
 * 
 * @author VZhang
 * @Date  Jul 29, 2011
 */
public class VerifyBusinessRulesAndUI extends LicMgrViewProductPricingListTestCase{

	private PricingInfo[] pricingInfo = new PricingInfo[6];
	private VehicleRTI vehicle = new VehicleRTI();
	
	protected void newVehicleProduct() {
		vehicle.setPrdCode(pricing.prodCode);
		vehicle.setPrdName("Vehicle" + pricing.prodCode);
		vehicle.setPrdGroup("Title");
		vehicle.setVehicleType("Boat");
		
		HashMap<String, Boolean> custClass = new HashMap<String, Boolean>();
		custClass.put("Individual", true);
		
		vehicle.setCustClass(custClass);
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		pricing.prodType = "Vehicle";
		pricing.prodCode = "V13";
		//search critical
		pricing.status = "Active";
		pricing.locationClass = "02 - MDWFP District Office";
		pricing.purchaseType = "Original";
		
//		String str = DataBaseFunctions.getTranslatableLabelValue("translatable.replacement");
		String str = DataBaseFunctions.getTranslations(schema, "translatable.replacement");//Quentin[20131205]

		pricingInfo[0] = new PricingInfo();
		pricingInfo[0].status = "Active";
		pricingInfo[0].prodType = pricing.prodType;
		pricingInfo[0].prodCode = pricing.prodCode;
		pricingInfo[0].locationClass = "All";
		pricingInfo[0].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 4);
		pricingInfo[0].licYearTo = pricingInfo[0].licYearFrom;
		pricingInfo[0].purchaseType = "Transfer";
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
		pricingInfo[2].locationClass = "02 - MDWFP District Office";
		pricingInfo[2].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 2);
		pricingInfo[2].licYearTo = pricingInfo[2].licYearFrom;
		pricingInfo[2].purchaseType = "Original";
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
		pricingInfo[3].locationClass = "02 - MDWFP District Office";
		pricingInfo[3].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 3);
		pricingInfo[3].licYearTo = pricingInfo[3].licYearFrom;
		pricingInfo[3].purchaseType = str;
		pricingInfo[3].effectFrom = DateFunctions.getToday();
		pricingInfo[3].effectTo = DateFunctions.getDateAfterToday(20);
		pricingInfo[3].vendorFee = "16.15";
		pricingInfo[3].stateTransFee = "11.97";
		pricingInfo[3].stateFee = "15.31";
		pricingInfo[3].transFee = "12.34";

		pricingInfo[4] = new PricingInfo();
		pricingInfo[4].status = "Active";
		pricingInfo[4].prodType = pricing.prodType;
		pricingInfo[4].prodCode = pricing.prodCode;
		pricingInfo[4].locationClass = "04 - Commercial Agent";
		pricingInfo[4].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricingInfo[4].licYearTo = pricingInfo[4].licYearFrom;
		pricingInfo[4].purchaseType = str;
		pricingInfo[4].effectFrom = DateFunctions.getDateAfterToday(-10);
		pricingInfo[4].effectTo = DateFunctions.getDateAfterToday(30);
		pricingInfo[4].vendorFee = "14.15";
		pricingInfo[4].stateTransFee = "12.97";
		pricingInfo[4].stateFee = "11.31";
		pricingInfo[4].transFee = "14.34";

		pricingInfo[5] = new PricingInfo();
		pricingInfo[5].status = "Active";
		pricingInfo[5].prodType = pricing.prodType;
		pricingInfo[5].prodCode = pricing.prodCode;
		pricingInfo[5].locationClass = "04 - Commercial Agent";
		pricingInfo[5].licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricingInfo[5].licYearTo = pricingInfo[5].licYearFrom;
		pricingInfo[5].purchaseType = "Original";
		pricingInfo[5].effectFrom = DateFunctions.getToday();
		pricingInfo[5].effectTo = DateFunctions.getDateAfterToday(30);
		pricingInfo[5].vendorFee = "10.15";
		pricingInfo[5].stateTransFee = "13.97";
		pricingInfo[5].stateFee = "12.31";
		pricingInfo[5].transFee = "16.34";
		
		pricingInfos.add(pricingInfo[0]);
		pricingInfos.add(pricingInfo[1]);
		pricingInfos.add(pricingInfo[2]);
		pricingInfos.add(pricingInfo[3]);
		pricingInfos.add(pricingInfo[4]);
		pricingInfos.add(pricingInfo[5]);	
	}
}

