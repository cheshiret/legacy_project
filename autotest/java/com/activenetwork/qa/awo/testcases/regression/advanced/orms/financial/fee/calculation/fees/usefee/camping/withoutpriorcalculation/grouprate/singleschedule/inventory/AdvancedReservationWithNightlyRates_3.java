package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.usefee.camping.withoutpriorcalculation.grouprate.singleschedule.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AdvancedReservationWithNightlyRates_3 extends
		InventoryManagerTestCase {
	FacilityData facilityData = new FacilityData();
	FeeValidationData feeData = new FeeValidationData();

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
		this.verifyUseFee(feeData);

		// Log out
		im.logoutInvManager();

	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login Inventory manager info
		login.contract = "NE Contract";
		login.location = "Administrator/NGPC";
		login.url = AwoUtil.getOrmsURL(env);

		res.siteIDs=new String[]{"4850"};
		facilityData.facilityName = "Victoria Springs SRA";
		feeData.productID = "4850";

		feeData.tranType = "Reservation";
		feeData.rateType = "group";
		feeData.occuptants = "5";
		feeData.vehicles = "1";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Mon Apr 5 2010";
		feeData.arriveDate = "Thu Apr 08 2010";
		feeData.departureDate = "Tue Apr 13 2010";
		feeData.nights.add("5");
		feeData.time.add("4/08/2010");
		String[] cond = { "occpIncr", "3", "", "5", "4/08/2010" };
		List<String[]> conds = new ArrayList<String[]>();
		conds.add(cond);
		feeData.groupCond.add(conds);
	}

	public void verifyUseFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> useFee = invFeePg.getFeeBySystemCalculate("Use Fee");

		// calculate rule
		BigDecimal amount = feeCal.calculateBaseFee(feeData, 0, false).get(0);
		BigDecimal incramount = feeCal.calculateBaseFee(feeData,
				feeData.groupCond).get(0);
		
		String fee = amount.add(incramount).toString()
				+ feeData.schedules.get(0) + "Daily/Nightly";
		
		String total = amount.add(incramount).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);

		// compare to
		if (useFee.size() == 0 || useFee.size() < 2) {
			throw new ErrorOnDataException("total use fee display not correct");
		}
		for (int i = 0; i < useFee.size(); i++) {
			String sysAmount = useFee.get(i);
			String compareAmount = compare.get(i);
			if (!sysAmount.equals(compareAmount)) {
				throw new ErrorOnDataException(
						"use fee calculation is not correct");
			} else {
				logger.info("use fee calculation is correct");
			}
		}
	}
}
