package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.usefee.camping.withoutpriorcalculation.familyrate.singleschedule.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.RateType;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * use single schedule with multi-unit rate and family type order did not meet
 * customer required number of nights,it use weekly rate and nightly rate(length
 * of stay:11)
 * 
 * @author Sophia
 * 
 */
public class WalkinWithRequiredMultiunitRates_3 extends
		InventoryManagerTestCase {
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

		res.siteIDs=new String[]{"4851"};
		facilityData.facilityName = "Victoria Springs SRA";
		feeData.productID = "4851";

		feeData.tranType = "walkin";
		feeData.rateType = "family";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Wed Mar 03 2010";
		feeData.arriveDate = "Wed Mar 03 2010";
		feeData.departureDate = "Sun Mar 14 2010";
		Map<String, String> multi = new HashMap<String, String>();
		multi.put(RateType.KEY_WEEKRATE, "1");
		feeData.units.add(multi);
		feeData.nights.add("4");
		feeData.time.add("3/10/2010");

	}

	public void verifyUseFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> useFee = invFeePg.getFeeBySystemCalculate("Use Fee");

		// calculate rule
		BigDecimal amount = feeCal.calculateBaseFee(feeData, 1, false).get(0);
		BigDecimal amount1 = feeCal.calculateBaseFee(feeData, 0, false).get(0);
		String fee = amount.toString() + feeData.schedules.get(0) + "Weekly";
		String fee1 = amount1.toString() + feeData.schedules.get(0)
				+ "Daily/Nightly";

		List<BigDecimal> attrFees = new ArrayList<BigDecimal>();
		attrFees.add(amount);
		attrFees.add(amount1);
		String total = feeCal.calculateTotalPrice(attrFees, null, null, null,
				null).get(FeeType.KEY_ATTR_FEES).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);

		// compare to
		if (useFee.size() == 0 || useFee.size() < 3) {
			throw new ErrorOnDataException("use fee display not correct");
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
