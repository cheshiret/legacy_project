package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:This test case verify add use fee for slip
 *              check point:1.The error message when inventory start date was empty.
 *                          2.The error message when inventory end date was empty.
 *                          3.Enter valid Effective Date  then enter Inventory Start Date greater than Inventory End Date.
 *              
 *                          
 * @Preconditions:
 * @SPEC: TC:043381
 * @Task#:  Auto-1326
 * @author Jasmine
 * @Date  Jan 09, 2013(P)
 */
public class VerifyInvalidIvnDateErrorMsg extends FinanceManagerTestCase{
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private final String Msg_EmptyInvStart = "An Inventory Start Date for the Fee Schedule is required. Please enter the Inventory Start Date using the format Ddd Mmm dd yyyy in the field provided.";
	private final String Msg_EmptyInvEnd = "An Inventory End Date for the Fee Schedule is required. Please enter the Inventory End Date using the format Ddd Mmm dd yyyy in the field provided.";
	private final String Msg_InvStartGreaterInvEnd = "The Inventory Start Date must not be later than the Inventory End Date. Please change your entries.";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.addNewFeeSchedule(schedule);
		this.verifyErrorMsg(Msg_EmptyInvStart);
		schedule.endInv = "";
		schedule.startInv = DateFunctions.getDateAfterToday(1);
		this.setFeeScheduleInvStartAndEndDate();
		this.verifyErrorMsg(Msg_EmptyInvEnd);
		schedule.endInv = DateFunctions.getToday();
		this.setFeeScheduleInvStartAndEndDate();
		this.verifyErrorMsg(Msg_InvStartGreaterInvEnd);
		fnm.logoutFinanceManager();
	}

	@Override
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
		FeeScheduleData.SlipFee slipFee1 =schedule.new SlipFee();
		schedule.slipPricingUnit = "Length Range";
		slipFee1.dailyNightlyFee = "2.0";
		schedule.slipFees.add(slipFee1);
		schedule.minimumUseFee = "2.0";
		schedule.effectDate = DateFunctions.getToday();
		schedule.endInv = DateFunctions.getToday();
	}
	
	/**
	 * verify error message.
	 * @param ExpectedMsg
	 */
	private void verifyErrorMsg(String ExpectedMsg){
		logger.info("Go to fee schedule detail page");
		String actualMsg = detailsPg.getErrorMsg();
		if(!ExpectedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Error message",ExpectedMsg,actualMsg);
		}else{
			logger.info("Message was correct");
		}
	}
	/**
	 * set fee schedule inventory start and end date different in web page.
	 */
	private void setFeeScheduleInvStartAndEndDate(){
		if (null != schedule.startInv) {
			detailsPg.setInvStartDate(schedule.startInv);
		}
		if (null != schedule.endInv) {
			detailsPg.setInvEndDate(schedule.endInv);
		}
		detailsPg.clickApply();
		detailsPg.waitLoading();
	}

}
