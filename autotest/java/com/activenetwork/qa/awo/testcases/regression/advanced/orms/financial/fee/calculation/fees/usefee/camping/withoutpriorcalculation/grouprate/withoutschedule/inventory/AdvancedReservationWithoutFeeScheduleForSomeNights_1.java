package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.usefee.camping.withoutpriorcalculation.grouprate.withoutschedule.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AdvancedReservationWithoutFeeScheduleForSomeNights_1 extends
		InventoryManagerTestCase {
	FacilityData facilityData = new FacilityData();
	FeeValidationData feeData = new FeeValidationData();
	private static final String error = "Error simulating price: Use fee schedule is required(1)!";
	
	@Override
	public void execute() {
		// change site info to group and set site attribute:"Base Number of People","Base Number of Vehicles","Minimum Number of People","Minimum Number of Vehicles","Maximum Number of People","Maximum Number of Vehicles"
		im.changeSiteInfomation(schema, feeData.productID, new String[]{RATE_TYPE,RATE_TYPE_GROUP});
		im.changeSiteAttribute(schema, feeData.productID, BASE_NUMBER_OF_PEOPLE, BASE_NUMBER_OF_PEOPLE_NAME, "2");
		im.changeSiteAttribute(schema, feeData.productID, BASE_NUMBER_OF_VEHICLES, BASE_NUMBER_OF_VEHICLES_NAME, "1");
		im.changeSiteAttribute(schema, feeData.productID, MINIMUM_NUMBER_OF_PEOPLE, MINIMUM_NUMBER_OF_PEOPLE_NAME, "0");
		im.changeSiteAttribute(schema, feeData.productID, MINIMUM_NUMBER_OF_VEHICLES, MINIMUM_NUMBER_OF_VEHICLES_NAME, "0");
		im.changeSiteAttribute(schema, feeData.productID, MAXIMUM_NUMBER_OF_PEOPLE, MAXIMUM_NUMBER_OF_PEOPLE_NAME, "10");
		im.changeSiteAttribute(schema, feeData.productID, MAXIMUM_NUMBER_OF_VEHICLES, MAXIMUM_NUMBER_OF_VEHICLES_NAME, "10");
		
		feeData = feeInfo.getGroupFeeScheduleInfo(feeData, "2", TestProperty
				.getProperty(env + ".db.schema.prefix")
				+ "NE");

		// Login Inventory manager
		im.loginInventoryManager(login);

		// Go to site detail page for validating fee
		im.gotoSiteDetailforCalculation(facilityData.facilityName,
				feeData.productID);

		// Set fee calculation parameter and calculate fee
		im.calculateFee(feeData);

		// verify attribute fee
		this.verifyUseFee(error);

		// Log out
		im.logoutInvManager();

	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login Inventory manager info
		login.contract = "NE Contract";
		login.location = "Administrator/NGPC";
		login.url = AwoUtil.getOrmsURL(env);
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NE";

		res.siteIDs=new String[]{"1804"};
		facilityData.facilityName = "Branched Oak SRA";
		feeData.productID = "1804";

		feeData.tranType = "Reservation";
		feeData.rateType = "group";
		feeData.occuptants = "5";
		feeData.vehicles = "6";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Sat Jul 17 2010";
		feeData.arriveDate = "Wed Jul 21 2010";
		feeData.departureDate = "Wed Jul 28 2010";
	}

	public void verifyUseFee(String error) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		String msg=invFeePg.getUseErrorMsg();
		if (!error.equalsIgnoreCase(msg)) {
			throw new ErrorOnDataException("use fee process error.The error message display not correct.");
		}
	}
}
