package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This test case verify add use fee for slip
 *              check point:1. verify Minimum Use Fee Text.
 *                          2. default value should be blank when select different Marina Rate Type
 *                          3. should be enabled when select different Marina Rate Type
 *                          
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Minimum Use Fee for Slip [TC:048074]
 * @Task#: AUTO-1332
 * @author VZhang
 * @Date  Jan 18, 2013
 */

public class VerifyMinimumUseFee extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		//the marina rate type is select as 'Transient'
		//verify minimum use fee default value as blank
		this.verifyMinimumUseFeeDefaultValue();
		//verify minimum use fee text should enabled
		this.verifyMinimumUseFeeIsEnabled();
		
		//select marina rate type as 'Lease'
		schedule.marinaRateType = "Lease";
		this.selectMarinaRateType(schedule.marinaRateType);
		//verify minimum use fee default value as blank
		this.verifyMinimumUseFeeDefaultValue();
		//verify minimum use fee text should enabled
		this.verifyMinimumUseFeeIsEnabled();
		
		//select marina rate type as 'Seasonal'
		schedule.marinaRateType = "Seasonal";
		this.selectMarinaRateType(schedule.marinaRateType);
		//verify minimum use fee default value as blank
		this.verifyMinimumUseFeeDefaultValue();
		//verify minimum use fee text should enabled
		this.verifyMinimumUseFeeIsEnabled();
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
	}
	
	private void verifyMinimumUseFeeDefaultValue(){
		String value = detailsPg.getMinimumUseFee();
		if(!"".equals(value)){
			throw new ErrorOnPageException("Minimum Use Fee default value", "", value);
		}
	}
	
	private void verifyMinimumUseFeeIsEnabled(){
		boolean actIsEnabled = detailsPg.checkMinimumUseFeeIsEnabled();
		if(!actIsEnabled){
			throw new ErrorOnPageException("Minimum Use Fee Editable info", true, actIsEnabled);
		}
	}
	
	private void selectMarinaRateType(String marinaRateType){
		detailsPg.selectMarinaRateType(marinaRateType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}

}
