package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.penalty.marina.add;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 *      1.verify error message with invalid penalty value.
 * 
 * @Preconditions:
 * @SPEC: 
 * TC:049462 -- Add Fee Penalty Scheduel - Penalty Value validation
 * @Task#:AUTO-1431
 * 
 * @author Jasmine
 * @Date Jan 25, 2013
 */
public class VerifyInvalidPenaltyValueMsg extends FinanceManagerTestCase{
	private FeePenaltyData schedule = new FeePenaltyData();
	private FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage.getInstance();
	private final String Empty_Penalty = "The Penalty Value is required. Please enter the Penalty Value in the field provided.";
	private final String Invalid_penalty = "The Penalty Value must have a value greater than or equal to $0.00. Please change your entries.";
	private final String Negtive_Penalty = "The Penalty Value must be between 0% and 100%. Please change your entries.";
	private final String Invalid_DaysNights = "The Penalty Value must either specify \"All\" or an integer value greater than 0. Please change your entries.";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		schedule.id = fnm.addNewFeePenalty(schedule);
		this.verifyErrorMsg(Empty_Penalty);
		schedule.units = "Percent";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Empty_Penalty);
		schedule.units = "Days";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Empty_Penalty);
		schedule.units = "Nights";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Empty_Penalty);
		schedule.units = "Flat";
		schedule.value = "-1";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_penalty);
		schedule.units = "Percent";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Negtive_Penalty);
		schedule.value = "101";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Negtive_Penalty);
		schedule.units = "Days";
		schedule.value = "-1";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		schedule.value = "abc";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		schedule.value = "1.2";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		schedule.value = "0";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		schedule.units = "Nights";
		schedule.value = "-1";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		schedule.value = "abc";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		schedule.value= "1.2";
		this.resetUnitInfo(schedule);
		this.verifyErrorMsg(Invalid_DaysNights);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.location = fnm.getFacilityName("552834", schema);// Medoc Mountain State Park
		schedule.locationCategory = "Park";
		schedule.loop = null;
		schedule.dock = "All";
		schedule.productGroup = "Full attributes";
		schedule.product = "All";
		schedule.effectDate = "";
		schedule.startInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.tranType = "Change Boat";
		schedule.tranOccur = "All";
		schedule.marinaRateType = "Transient";
		schedule.units = "Flat";
		schedule.value = "";	
	}
	
	private void verifyErrorMsg(String exceptedMsg){
		String actualMsg = detailPg.getError();
		if(!exceptedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Message error",exceptedMsg,actualMsg);
		}
	}
	
	/**
	 * reset penalty value.
	 * @param value
	 */
	private void resetUnitInfo(FeePenaltyData fp){
		detailPg.selectUnit(fp.units);
		detailPg.setUnitValue(fp.value);
		detailPg.clickApply();
		ajax.waitLoading();
		detailPg.waitLoading();
	}
}
