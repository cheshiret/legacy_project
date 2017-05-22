package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.usefee.camping.withoutpriorcalculation.grouprate.withoutschedule.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AdvancedReservationWithoutFeeScheduleForSomeNights_2 extends
		InventoryManagerTestCase {
	FacilityData facilityData = new FacilityData();
	FeeValidationData feeData = new FeeValidationData();
	private static final String error = "Error simulating price: Use fee schedule is required(1)!";
	
	@Override
	public void execute() {
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

		res.siteIDs=new String[]{"1804"};
		facilityData.facilityName = "Branched Oak SRA";
		feeData.productID = "1804";

		feeData.tranType = "Reservation";
		feeData.rateType = "group";
		feeData.occuptants = "5";
		feeData.vehicles = "6";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Sat Jul 17 2010";
		feeData.arriveDate = "Wed Jul 28 2010";
		feeData.departureDate = "Wed Aug 04 2010";
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
