package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.attributefee.site.withoutpriorcalculation.grouprate.multipleschedules.inventory;

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

public class AdvancedReservationWithNightlyRates_2 extends
		InventoryManagerTestCase {
	FacilityData facilityData = new FacilityData();
	FeeValidationData feeData = new FeeValidationData();

	@Override
	public void execute() {
		feeData = feeInfo.getGroupFeeScheduleInfo(feeData, "12", TestProperty
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
		this.verifyAttributeFee(feeData);

		// Log out
		im.logoutInvManager();

	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login Inventory manager info
		login.contract = "NE Contract";
		login.location = "Administrator/NGPC";
		login.url = AwoUtil.getOrmsURL(env);

		facilityData.facilityName = "Victoria Springs SRA";
		feeData.productID = "4850";
		res.siteIDs = new String[]{"4850"};

		feeData.tranType = "Reservation";
		feeData.rateType = "group";
		feeData.occuptants = "5";
		feeData.vehicles = "6";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Sat Apr 24 2010";
		feeData.arriveDate = "Wed Apr 28 2010";
		feeData.departureDate = "Tue May 04 2010";
		feeData.nights.add("3");
		feeData.nights.add("3");
		feeData.time.add("4/28/2010");
		feeData.time.add("5/01/2010");
		String[] cond1 = { "occpIncr", "3", "", "3", "4/28/2010" };
		String[] cond2 = { "vehIncr", "5", "", "3", "4/28/2010" };
		String[] cond3 = { "occpRange", "1", "", "3", "5/01/2010" };
		String[] cond4 = { "vehRange", "2", "", "3", "5/01/2010" };
		List<String[]> conds1 = new ArrayList<String[]>();
		conds1.add(cond1);
		conds1.add(cond2);
		List<String[]> conds2 = new ArrayList<String[]>();
		conds2.add(cond3);
		conds2.add(cond4);
		feeData.groupCond.add(conds1);
		feeData.groupCond.add(conds2);
	}

	public void verifyAttributeFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> attributeFee = invFeePg
				.getFeeBySystemCalculate("Attribute Fee");

		// calculate rule
		List<BigDecimal> attr = feeCal.calculateBaseFee(feeData, 0, false);
		List<BigDecimal> incr = feeCal.calculateBaseFee(feeData,
				feeData.groupCond);
		String fee = attr.get(0).add(incr.get(0)).toString()
				+ feeData.schedules.get(0) + "Daily/Nightly";
		String fee1 = attr.get(1).add(incr.get(1)).toString()
				+ feeData.schedules.get(1) + "Daily/Nightly";
        
		String total = attr.get(0).add(attr.get(1)).add(incr.get(0)).add(
				incr.get(1)).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);

		// compare to
		if (attributeFee.size() == 0 || attributeFee.size() < 3) {
			throw new ErrorOnDataException(
					"total attribute fee display not correct");
		}
		for (int i = 0; i < attributeFee.size(); i++) {
			String sysAmount = attributeFee.get(i);
			String compareAmount = compare.get(i);
			if (!sysAmount.equals(compareAmount)) {
				throw new ErrorOnDataException(
						"attribute fee calculation is not correct");
			} else {
				logger.info("attribute fee calculation is correct");
			}
		}
	}
}
