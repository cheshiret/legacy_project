package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.slip;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This test case verify add transaction fee for slip
 *              check point:1.verify drop down list of Marina Rate Type.
 *                          2. Marina Rate Type contain All, Seasonal, Lease, Transient, and All is default value
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Marina Rate Type for Slip [TC:047858]
 * @Task#: AUTO-1419
 * @author VZhang
 * @Date  Jan 09, 2013
 */

public class VerifyMarinaRateType extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> expectValue;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		// verify drop down list of Marina Rate Type
		this.verifyDefaultValueForMarinaRateType();
		this.verifyMarinaRateTypeElements(expectValue);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Transaction Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		expectValue = new ArrayList<String>();
		expectValue.add("All");
		expectValue.add("Seasonal");
		expectValue.add("Lease");
		expectValue.add("Transient");
	}
	
	private void verifyDefaultValueForMarinaRateType() {
		String value = detailsPg.getMarinaRateTypeOfDropDownList();

		if (!"All".equals(value)) {
			throw new ErrorOnPageException("Marina Rate Type default value", "All", value);
		}
	}
	
	private void verifyMarinaRateTypeElements(List<String> expect) {
		List<String> values = detailsPg.getMarinaRateTypeElementsOfDropDownList();

		if (expect.size() != values.size() ) {
			throw new ErrorOnPageException(
					"Marina Rate Type element size is not correct.");
		}
		for (String value : expect) {
			if (!values.contains(value)) {
				throw new ErrorOnPageException(
						"Marina Rate Type should contains value :" + value);
			}
		}
	}

}
