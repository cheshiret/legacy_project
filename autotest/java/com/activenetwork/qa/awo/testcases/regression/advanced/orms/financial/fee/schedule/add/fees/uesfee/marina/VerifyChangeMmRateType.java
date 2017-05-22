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
 * @Description: verify change marina rate type.
 * @Preconditions:
 * @SPEC:  TC:048880
 * @Task#: Auto-1326
 * 
 * @author Jasmine
 * @Date  Jan 7, 2013()
 */
public class VerifyChangeMmRateType extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
    private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
    private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
    private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
			.getInstance();
    private FeeScheduleData.SlipFee slipFee1 =schedule.new SlipFee();
    private final String Expected_WarningMsg = "Switching the Marina Rate Type will automatically result in the removal of existing Fee entries. Are you sure you want to continue?";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		this.gotoFeeScheduleDetailsPageByAddNew(schedule);
		String warningMsg = this.setSlipFeeAndChangeMmRateType(schedule);
		this.verifyMsgForChangeMMRateType(Expected_WarningMsg, warningMsg);
		detailsPg.verifyRaftingNotexist();
		detailsPg.verifyMmRateTypeSelected(schedule.marinaRateType);
		this.updateMranaRateAndFee1();
		warningMsg = this.setSlipFeeAndChangeMmRateType(schedule);
		this.verifyMsgForChangeMMRateType(Expected_WarningMsg, warningMsg);
		detailsPg.verifyMmRateTypeSelected(schedule.marinaRateType);
		this.updateMranaRateAndFee2();
		warningMsg = this.setSlipFeeAndChangeMmRateType(schedule);
		this.verifyMsgForChangeMMRateType(Expected_WarningMsg, warningMsg);
		detailsPg.verifyMmRateTypeSelected(schedule.marinaRateType);
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.location = fnm.getFacilityName("552831", schema);// Eno River State Park";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.marinaRateType = "Seasonal";
		schedule.slipPricingUnit = "Length Range";
		slipFee1.dailyNightlyFee= "2.0";
		schedule.slipFees.add(slipFee1);
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
	
	private String setSlipFeeAndChangeMmRateType(FeeScheduleData schedule){
		detailsPg.setSlipFees(schedule.slipFees,schedule.slipPricingUnit);
		return fnm.changeMarinaFeeScheduleRateType(schedule.marinaRateType, true);
	}
	
	private void updateMranaRateAndFee1(){
		schedule.marinaRateType = "Lease";
		schedule.slipFees.clear();
		slipFee1.perSeasonFee = "2.0";
		schedule.slipFees.add(slipFee1);
	}
	
	private void updateMranaRateAndFee2(){
		schedule.marinaRateType = "Transient";
		schedule.slipFees.clear();
		slipFee1.monthlyFee = "20.0";
		slipFee1.perSeasonFee = "";
		schedule.slipFees.add(slipFee1);
	}
	/**
	 * verify error message.
	 * @param expectedMsg
	 * @param actualMsg
	 */
	private void verifyMsgForChangeMMRateType(String expectedMsg,String actualMsg){
		if(!expectedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Message error",expectedMsg,actualMsg);
		}
	}
	
	

}
