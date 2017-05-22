package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.attributefee.site.withoutpriorcalculation.familyrate.multipleschedules.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.RateType;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class WalkinWithRequiredMultiunitRates_1 extends
		InventoryManagerTestCase {
	FacilityData facilityData = new FacilityData();
	FeeValidationData feeData = new FeeValidationData();

	@Override
	public void execute() {
		feeData = feeInfo.getFeeScheduleInfo(feeData, "12", TestProperty
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
		feeData.productID = "4851";
		res.siteIDs = new String[]{"4851"};

		feeData.tranType = "walkin";
		feeData.rateType = "family";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Wed Apr 07 2010";
		feeData.arriveDate = "Wed Apr 07 2010";
		feeData.departureDate = "Sun Apr 18 2010";
		Map<String, String> unit = new HashMap<String, String>();
		unit.put(RateType.KEY_WEEKRATE, "1");
		feeData.units.add(unit);
		feeData.units.add(unit);
		feeData.nights.add("2");
		feeData.nights.add("2");
		feeData.time.add("4/14/2010");
		feeData.time.add("4/16/2010");
	}

	public void verifyAttributeFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> attributeFee = invFeePg
				.getFeeBySystemCalculate("Attribute Fee");

		// calculate rule
		BigDecimal amount = feeCal.calculateBaseFee(feeData, 1, false).get(0);
		BigDecimal amount1 = feeCal.calculateBaseFee(feeData, 0, true).get(0);
		BigDecimal amount2 = feeCal.calculateBaseFee(feeData, 0, false).get(1);
		String fee = amount.toString() + feeData.schedules.get(0) + "Weekly";
		String fee1 = amount1.toString() + feeData.schedules.get(0)
				+ "Daily/Nightly";
		String fee2 = amount2.toString() + feeData.schedules.get(1)
				+ "Daily/Nightly";
		
		String total = amount.add(amount1).add(amount2).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);
		compare.add(fee2);

		// compare to
		if (attributeFee.size() == 0 || attributeFee.size() < 4) {
			throw new ErrorOnDataException(
					"total attribute fee display not correct");
		}
		for (int i = 0; i < attributeFee.size(); i++) {
			String sysAmount = attributeFee.get(i);
			String compareAmount = compare.get(i);
			if (!sysAmount.equalsIgnoreCase(compareAmount)) {
				throw new ErrorOnDataException(
						"attribute fee calculation is not correct");
			} else {
				logger.info("attribute fee calculation is correct");
			}
		}
	}
}
