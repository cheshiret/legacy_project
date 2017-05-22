package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.attributefee.site.withoutpriorcalculation.familyrate.multipleschedules.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.FeeType;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * use multiple schedules with nightly family rate (combine week day rate with
 * nightly rate)
 * 
 * @author Sophia
 * 
 */
public class WalkinWithNightlyRates_3 extends InventoryManagerTestCase {
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
		feeData.productID = "4852";
		res.siteIDs = new String[]{"4852"};

		feeData.tranType = "walkin";
		feeData.rateType = "family";
		feeData.salesChannel = "Field";
		feeData.bookDate = "Fri Apr 16 2010";
		feeData.arriveDate = "Fri Apr 16 2010";
		feeData.departureDate = "Tue Apr 27 2010";
		feeData.nights.add("7");
		feeData.nights.add("4");
		feeData.time.add("4/16/2010");
		feeData.time.add("4/23/2010");
	}

	public void verifyAttributeFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> attributeFee = invFeePg
				.getFeeBySystemCalculate("Attribute Fee");

		// calculate rule
		List<BigDecimal> attr = feeCal.calculateBaseFee(feeData, 0, false);
		String fee = attr.get(0).toString() + feeData.schedules.get(0)
				+ "Daily/Nightly";
		String fee1 = attr.get(1).toString() + feeData.schedules.get(1)
				+ "Daily/Nightly";

		String total = feeCal.calculateTotalPrice(attr, null, null, null, null)
				.get(FeeType.KEY_ATTR_FEES).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);

		// compare to
		if (attributeFee.size() == 0 || attributeFee.size() < 3) {
			throw new ErrorOnDataException("attribute fee display not correct");
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
