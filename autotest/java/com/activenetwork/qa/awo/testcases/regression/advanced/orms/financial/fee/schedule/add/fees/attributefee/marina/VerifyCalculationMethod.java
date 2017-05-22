package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This test case verify add attribute fee for slip
 *              check point:1. verify calculation method radio button.
 *                          2. whether enabled when select different Marina Rate type
 *                          3. default value
 *                          4. elements
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Calculation Method for Slip (Use Fee & Attr Fee) [TC:047899]
 * @Task#: AUTO-1332
 * @author VZhang
 * @Date  Jan 17, 2013
 */

public class VerifyCalculationMethod extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> expectValue;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		// the marina rate type is select as 'Transient'
		//verify calculation method is not enabled
		this.verifyCalculationMethodWhetherIsEnabled(false);
		//verify calculation method default value should be 'Daily'
		this.verifyCalculationMethodDefaultValue();
		//verify calculation method elements
		this.verifyCalculationMethodElements(expectValue);
		
		//select marina rate type as 'Lease'
		schedule.marinaRateType = "Lease";
		this.selectMarinaRateType(schedule.marinaRateType);
		//verify calculation method is not enabled
		this.verifyCalculationMethodWhetherIsEnabled(false);
		//verify calculation method default value should be 'Daily'
		this.verifyCalculationMethodDefaultValue();
		//verify calculation method elements
		this.verifyCalculationMethodElements(expectValue);
		
		//select marina rate type as 'Seasonal'
		schedule.marinaRateType = "Seasonal";
		this.selectMarinaRateType(schedule.marinaRateType);
		//verify calculation method is enabled
		this.verifyCalculationMethodWhetherIsEnabled(true);
		//verify calculation method default value should be 'Daily'
		this.verifyCalculationMethodDefaultValue();
		//verify calculation method elements
		this.verifyCalculationMethodElements(expectValue);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Attribute Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		
		expectValue = new ArrayList<String>();
		expectValue.add("Daily");
		expectValue.add("Percentage");
	}
	
	private void verifyCalculationMethodWhetherIsEnabled(boolean expEnabled){
		boolean actEnabled = detailsPg.checkCalculationMethodIsEnabled();
		if(actEnabled != expEnabled){
			throw new ErrorOnPageException(
			"Calculation Method enabled info not correct",expEnabled,actEnabled);
		}
	}
	
	private void verifyCalculationMethodDefaultValue(){
		String value = detailsPg.getCalculationMethod();
		if(!"Daily".equals(value)){
			throw new ErrorOnPageException("Calculation Method default value", "Daily", value);
		}
	}
	
	private void verifyCalculationMethodElements(List<String> expect){
		List<String> values = detailsPg.getCalculationMethodElements();
		if (expect.size() != values.size() ) {
			throw new ErrorOnPageException(
					"Calculation Method elements size is not correct.");
		}
		for (String value : expect) {
			if (!values.contains(value)) {
				throw new ErrorOnPageException(
						"Calculation Method elements should contains value :" + value);
			}
		}
	}
	
	private void selectMarinaRateType(String marinaRateType){
		detailsPg.selectMarinaRateType(marinaRateType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}

}
