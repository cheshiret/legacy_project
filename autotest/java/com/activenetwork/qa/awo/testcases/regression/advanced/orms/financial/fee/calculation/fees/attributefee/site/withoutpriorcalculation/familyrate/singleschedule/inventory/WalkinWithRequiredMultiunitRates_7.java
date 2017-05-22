package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.attributefee.site.withoutpriorcalculation.familyrate.singleschedule.inventory;

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
 * use single schedule with multi-unit rate and family type order has required
 * number of nights,if duration rate is not available, use the next duration
 * multi-rate specific(length of stay:38)
 * 
 * @author Sophia
 * 
 */
public class WalkinWithRequiredMultiunitRates_7 extends
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
		feeData.bookDate = "Tue Jan 05 2010";
		feeData.arriveDate = "Tue Jan 05 2010";
		feeData.departureDate = "Fri Feb 12 2010";
		Map<String, String> multi = new HashMap<String, String>();
		multi.put(RateType.KEY_MONTHRATE, "1");
		multi.put(RateType.KEY_WEEKRATE, "1");
		feeData.units.add(multi);
		feeData.nights.add("1");
		feeData.time.add("2/11/2010");
	}

	public void verifyAttributeFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> attributeFee = invFeePg
				.getFeeBySystemCalculate("Attribute Fee");

		// calculate rule
		BigDecimal amount = feeCal.calculateBaseFee(feeData, 2, false).get(0);
		BigDecimal amount1 = feeCal.calculateBaseFee(feeData, 1, false).get(0);
		BigDecimal amount2 = feeCal.calculateBaseFee(feeData, 0, false).get(0);
		String fee = amount.toString() + feeData.schedules.get(0) + "Monthly";
		String fee1 = amount1.toString() + feeData.schedules.get(0) + "Weekly";
		String fee2 = amount2.toString() + feeData.schedules.get(0)
				+ "Daily/Nightly";
		
		List<BigDecimal> attrFees = new ArrayList<BigDecimal>();
		attrFees.add(amount);
		attrFees.add(amount1);
		attrFees.add(amount2);
		String total = feeCal.calculateTotalPrice(attrFees, null, null, null,
				null).get(FeeType.KEY_ATTR_FEES).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);
		compare.add(fee2);

		// compare to
		if (attributeFee.size() == 0 || attributeFee.size() < 4) {
			throw new ErrorOnDataException("attribute fee display not correct");
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
