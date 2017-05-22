package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.attributefee.site.withoutpriorcalculation.familyrate.multipleschedules.inventory;

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

public class WalkinWithRequiredMultiunitRates_3 extends
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
		feeData.bookDate = "Wed Feb 24 2010";
		feeData.arriveDate = "Wed Feb 24 2010";
		feeData.departureDate = "Sun Mar 07 2010";
		feeData.nights.add("5");
		feeData.nights.add("6");
		feeData.time.add("2/24/2010");
		feeData.time.add("3/01/2010");
	}

	public void verifyAttributeFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		// get system calculate results from UI
		List<String> attrFee = invFeePg
				.getFeeBySystemCalculate("Attribute Fee");

		// calculate rule
		List<BigDecimal> attr = feeCal.calculateBaseFee(feeData, 0, false);
		String fee = attr.get(0) + feeData.schedules.get(0) + "Daily/Nightly";
		String fee1 = attr.get(1) + feeData.schedules.get(1) + "Daily/Nightly";
		
		String total = attr.get(0).add(attr.get(1)).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(total);
		compare.add(fee);
		compare.add(fee1);

		// compare to
		if (attrFee.size() == 0 || attrFee.size() < 3) {
			throw new ErrorOnDataException("total use fee display not correct");
		}
		for (int i = 0; i < attrFee.size(); i++) {
			String sysAmount = attrFee.get(i);
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
