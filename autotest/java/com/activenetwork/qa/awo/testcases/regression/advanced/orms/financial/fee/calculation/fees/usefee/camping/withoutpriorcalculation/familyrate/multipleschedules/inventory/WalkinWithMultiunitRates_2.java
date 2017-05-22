package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.usefee.camping.withoutpriorcalculation.familyrate.multipleschedules.inventory;

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

/**
 * use multiple schedules with multi-unit family rates order use best
 * rates(greater than)
 * 
 * @author Sophia
 * 
 */
public class WalkinWithMultiunitRates_2 extends InventoryManagerTestCase {
	FacilityData facilityData = new FacilityData();
	FeeValidationData feeData = new FeeValidationData();

	@Override
	public void execute() {
		feeData = feeInfo.getFeeScheduleInfo(feeData, "2", TestProperty
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

		res.siteIDs = new String[] { "4851" };
		facilityData.facilityName = "Victoria Springs SRA";
		feeData.productID = "4851";

		feeData.tranType = "walkin";
		feeData.rateType = "family";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Sun May 23 2010";
		feeData.arriveDate = "Sun May 23 2010";
		feeData.departureDate = "Wed Jun 02 2010";
		feeData.nights.add("2");
		feeData.nights.add("1");
		Map<String, String> units = new HashMap<String, String>();
		units.put(RateType.KEY_WEEKRATE, "1");
		feeData.units.add(units);
		feeData.units.add(units);
	}

	public void verifyUseFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> useFee = invFeePg.getFeeBySystemCalculate("Use Fee");

		// calculate rule
		List<BigDecimal> attr = feeCal.calculateBaseFee(feeData, 0, true);
		BigDecimal amount = feeCal.calculateBaseFee(feeData, 1, true).get(0);
		String fee = amount.toString() + feeData.schedules.get(0) + "Weekly";
		String fee1 = attr.get(0) + feeData.schedules.get(0) + "Daily/Nightly";
		String fee2 = attr.get(1) + feeData.schedules.get(1) + "Daily/Nightly";
		String total = amount.add(attr.get(0)).add(attr.get(1)).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);
		compare.add(fee2);

		if (useFee.size() == 0 || useFee.size() < 4) {
			throw new ErrorOnDataException("total use fee display not correct");
		}
		for (int i = 0; i < useFee.size(); i++) {
			String sysAmount = useFee.get(i);
			String compareAmount = compare.get(i);
			if (!sysAmount.equalsIgnoreCase(compareAmount)) {
				throw new ErrorOnDataException(
						"use fee calculation is not correct");
			} else {
				logger.info("use fee calculation is correct");
			}
		}
	}
}
