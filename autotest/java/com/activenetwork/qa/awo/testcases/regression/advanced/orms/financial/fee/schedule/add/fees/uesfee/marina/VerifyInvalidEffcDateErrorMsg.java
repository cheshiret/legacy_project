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
 *              check point:1.The error message whe effective date was empty.
 *                          
 * @Preconditions:
 * @SPEC: TC:043380
 * @Task#:  Auto-1326
 * @author Jasmine
 * @Date  Jan 09, 2013(p)
 */
public class VerifyInvalidEffcDateErrorMsg extends FinanceManagerTestCase{

	private FeeScheduleData schedule = new FeeScheduleData();
	private final String expectedMsg = "An Effective Date for the Fee Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided.";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.addNewFeeSchedule(schedule);
		this.verifyErrorMsg(expectedMsg);
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
		slipFee1.dailyNightlyFee = "2.0";
		schedule.slipPricingUnit = "Length Range";
		schedule.slipFees.add(slipFee1);
		schedule.startInv = DateFunctions.getToday();
		schedule.endInv = DateFunctions.getToday();
		schedule.minimumUseFee = "2.0";
	}
	/**
	 * verify error message.
	 * @param ExpectedMsg
	 */
	private void verifyErrorMsg(String ExpectedMsg){
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
		logger.info("Go to fee schedule detail page");
		String actualMsg = detailsPg.getErrorMsg();
		if(!ExpectedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Error message",ExpectedMsg,actualMsg);
		}else{
			logger.info("Message was correct");
		}
	}

}
