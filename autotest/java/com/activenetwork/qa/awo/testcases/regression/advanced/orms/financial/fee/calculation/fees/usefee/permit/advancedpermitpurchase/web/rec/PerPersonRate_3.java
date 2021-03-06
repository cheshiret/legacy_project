package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.fees.usefee.permit.advancedpermitpurchase.web.rec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation.PersonTypeCode;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.bw.BwShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class PerPersonRate_3 extends RecgovTestCase {
	private String email, pw, url;
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private boolean isRecgov = true;
	FeeValidationData feeData = new FeeValidationData();

	public void execute() {
		feeData = feeInfo.getPermitFeeData(feeData, "NRRS", env, "2");

		web.invokeURL(url);
		web.signIn(email, pw);

		bw.makePermitOrderToCart(bd, isRecgov);
		this.verifyUseFee(feeData);
		bw.abandonCart();

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new PermitInfo();
		bd.facility = bw.getFacilityName("72600",TestProperty.getProperty(env + ".db.schema.prefix")+"nrrs");
		bd.permitType = "Overnight Hiking";
		bd.entrance = "76 Big Moose Lake Trail (oh)";
		bd.district = new String[1];
		bd.district[0] = "Gunflint District";
		bd.isRange = false;
		bd.entryDate = DateFunctions.getDateAfterToday(1);
		bd.personTypes = new String[2];
		bd.personTypes[0] = "Adult";
		bd.personTypes[1] = "Interagency Senior Pass";
		bd.personNums = new String[2];
		bd.personNums[0] = "2";
		bd.personNums[1] = "3";
		bd.parkId="72600";
		bd.contractCode="NRSO";
//		bd.isUnifiedSearch=isUnifiedSearch();
		bd.isUnifiedSearch=true;
		
		feeData.productID = "277368";
		feeData.arriveDate = DateFunctions.getDateAfterToday(1);
		feeData.departureDate = DateFunctions.getDateAfterToday(1);
		feeData.updateStartTime.add(DateFunctions.getDateAfterToday(-5));
		feeData.updateEndTime.add(DateFunctions.getDateAfterToday(30));
		feeData.nights.add("1");
		feeData.time.add(feeData.arriveDate);
		Map<String, String> unit = new HashMap<String, String>();
		unit.put(PersonTypeCode.ADULT, "2");
		unit.put(PersonTypeCode.ALL, "3");
		feeData.units.add(unit);
		feeData.personType.add(PersonTypeCode.ADULT);
		feeData.personType.add(PersonTypeCode.ALL);
		feeData.isPersonType = true;
	}

	public void verifyUseFee(FeeValidationData feeData) {
		BwShoppingCartPage bwCart = BwShoppingCartPage.getInstance();
		// get system calculate results from UI
		List<String> useFee = bwCart.getFeeBySystemCalculate(bd.personTypes);

		// calculate rule
		List<BigDecimal> amount = feeCal.calculateBaseFeeInPermit(feeData,
				feeData.personType, false);
		String fee = "Adult" + amount.get(0).toString();
		String fee1 = "Interagency Senior Pass" + amount.get(1).toString();
		List<String> compare = new ArrayList<String>();
		compare.add(fee);
		compare.add(fee1);

		// compare to
		if (useFee.size() == 0) {
			throw new ErrorOnDataException("there is no use fee");
		}
		for (int i = 0; i < useFee.size(); i++) {
			if (!useFee.get(i).equals(compare.get(i))) {
				throw new ErrorOnDataException(
						"use fee calculation is not correct");
			} else {
				logger.info("use fee calculation is correct");
			}
		}
	}
}
