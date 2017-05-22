package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Cancel the add fee schedule process.
 * @Preconditions:
 * @SPEC:  TC:048879
 * @Task#: Auto-1326
 * 
 * @author Jasmine
 * @Date  Jan 7, 2013(p)
 */
public class VerifyChangeUnitMsg extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
    private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
    private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
    private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
			.getInstance();
    private FeeScheduleData.SlipFee slipFee1 =schedule.new SlipFee();
    private final String Expected_WarningMsg1 = "Changing from Length Range to Length Increments will automatically result in the removal of existing Range entries. Are you sure you want to continue?";
    private final String Expected_WarningMsg2 = "Changing from Length Increments to Length Range will automatically result in the removal of existing Increment entries. Are you sure you want to continue?";
    private String expectedUnit = "";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		this.gotoFeeScheduleDetailsPageByAddNew(schedule);
		String warningMsg = this.setSlipFeeAndChangeUnit(schedule,expectedUnit);
		this.verifyMsgForChangeUnit(Expected_WarningMsg1, warningMsg);
		detailsPg.verifyUnitSelected(expectedUnit);
		
		schedule.slipPricingUnit = "Length Increments";
		expectedUnit = "Length Range";
		warningMsg = setSlipFeeAndCancelChangeUnit(schedule,expectedUnit);
		this.verifyMsgForChangeUnit(Expected_WarningMsg2, warningMsg);
		detailsPg.verifyUnitSelected(schedule.slipPricingUnit);
		
		warningMsg = this.setSlipFeeAndChangeUnit(schedule,expectedUnit);
		this.verifyMsgForChangeUnit(Expected_WarningMsg2, warningMsg);
		detailsPg.verifyUnitSelected(expectedUnit);
		
	
		schedule.slipPricingUnit ="Length Range";
		expectedUnit = "Length Increments";
		warningMsg = this.setSlipFeeAndCancelChangeUnit(schedule,expectedUnit);
		this.verifyMsgForChangeUnit(Expected_WarningMsg1, warningMsg);
		detailsPg.verifyUnitSelected(schedule.slipPricingUnit);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.location = fnm.getFacilityName("552831", schema);// Eno River State Park";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.marinaRateType = "Transient";
		slipFee1.dailyNightlyFee = "2.0";
		schedule.slipFees.add(slipFee1);
		schedule.slipPricingUnit = "Length Range";
		expectedUnit = "Length Increments";
	}
	
	/**
	 * go to fee schedule details page.
	 * @param fd
	 */
	private void gotoFeeScheduleDetailsPageByAddNew(FeeScheduleData fd){
		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(fd.location, fd.locationCategory);
		findLocPg.selectLocation(fd.location);
		ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.selectPrdCategory(fd.productCategory);
		ajax.waitLoading();
		detailsPg.selectFeeType(fd.feeType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	
	private String setSlipFeeAndChangeUnit(FeeScheduleData schedule,String expectedUnit){
		detailsPg.setSlipFees(schedule.slipFees,schedule.slipPricingUnit);
		return fnm.changeMarinaFeeScheduleUnit(expectedUnit);
	}
	
	private String setSlipFeeAndCancelChangeUnit(FeeScheduleData schedule,String expectedUnit){
		detailsPg.setSlipFees(schedule.slipFees,schedule.slipPricingUnit);
		return fnm.cancelChangeMarinaFeeScheduleUnit(expectedUnit);
	}
	
	/**
	 * verify error message.
	 * @param expectedMsg
	 * @param actualMsg
	 */
	private void verifyMsgForChangeUnit(String expectedMsg,String actualMsg){
		if(!expectedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Message error",expectedMsg,actualMsg);
		}
	}
	

}
