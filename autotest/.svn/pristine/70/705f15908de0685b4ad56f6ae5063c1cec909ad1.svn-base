package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.penalty.marina.add;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: 
 *      1.Keep Effective Date as empty and click OK button then check the msg.
 * 		2.Enter valid Effective Date then Keep Inventory Start Date as Empty and click OK button then check the error msg.
 *      3.Enter valid Effective Date and Inventory Start Date then keep Inventory End Date as empty and click OK button then check the error msg.
 *      4.Enter valid Effective Date then enter Inventory Start Date greater than Inventory End Date and click OK button then check the error msg.
 * 
 * @Preconditions:
 * @SPEC: 
 * TC:049453 -- Add Fee Penalty Scheduel - Effective Date validation
 * TC:049454 -- Add Fee Penalty Scheduel - Inventory Period validation
 * @Task#:AUTO-1431
 * 
 * @author Jasmine
 * @Date Jan 25, 2013(P)
 */
public class VerifyDateMsg extends FinanceManagerTestCase{
	private FeePenaltyData schedule = new FeePenaltyData();
	private FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage.getInstance();
	private FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage.getInstance();
	private final String Empty_EffectiveDate = "An Effective Date for the Fee Penalty Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided.";
	private final String Empty_InvStartDate = "An Inventory Start Date for the Fee Penalty Schedule is required. Please enter the Inventory Start Date using the format Ddd Mmm dd yyyy in the field provided.";
	private final String Empty_InvEndDate = "An Inventory End Date for the Fee Penalty Schedule is required. Please enter the Inventory End Date using the format Ddd Mmm dd yyyy in the field provided.";
	private final String InvStart_GreatThan_InvEnd = "The Inventory Start Date must not be later than the Inventory End Date. Please change your entries.";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		this.setNewFeePenaltyInfo(schedule);
		this.resetDateInfo(schedule);
		this.clickApplyAndGetReturn();
		this.verifyErrorMsgForDate(Empty_EffectiveDate);
		schedule.effectDate= DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.startInv =" ";
		this.resetDateInfo(schedule);
		this.clickApplyAndGetReturn();
		this.verifyErrorMsgForDate(Empty_InvStartDate);
		schedule.startInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");;
		schedule.endInv = "";
		this.resetDateInfo(schedule);
		this.clickApplyAndGetReturn();
		this.verifyErrorMsgForDate(Empty_InvEndDate);
		schedule.endInv =  DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		this.resetDateInfo(schedule);
		this.clickApplyAndGetReturn();
		this.verifyErrorMsgForDate(InvStart_GreatThan_InvEnd);
		
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
		schedule.units = "Nights";
		schedule.value = "5";	
		detailPg.setSlipType(schedule.productCategory);
	}
	
	private void verifyErrorMsgForDate(String exceptedMsg){
		String actualMsg = detailPg.getError();
		if(!exceptedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Message error",exceptedMsg,actualMsg);
		}
	}
	
	private void setNewFeePenaltyInfo(FeePenaltyData fp){
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrFeePenaltyDetailPage detailPg = FinMgrFeePenaltyDetailPage
				.getInstance();

		logger.info("Start to Add New Fee Penalty.");

		mainPg.clickAddNew();
		findLocPg.waitLoading();		
		findLocPg.searchByLocationName(fp.location, fp.locationCategory);
		ajax.waitLoading();
		findLocPg.selectLocation(fp.location);
		detailPg.waitLoading();
		detailPg.fillFeePenalty(fp);
	}
	
	private void resetDateInfo(FeePenaltyData fp){
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		
		detailPg.setEffectiveDate(fp.effectDate);
		browser.waitExists(detailPg, confirmPage);
		detailPg.setInvStartDate(fp.startInv);
		browser.waitExists(detailPg, confirmPage);
		detailPg.setInvEndDate(fp.endInv);
		browser.waitExists(detailPg, confirmPage);
	}
	
	private String clickApplyAndGetReturn(){
		String text = "";
		detailPg.clickApply();
		ajax.waitLoading();
		browser.waitExists();		
		detailPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(detailPg,mainPg);
		if(page == detailPg){// error message existed.
			text = detailPg.getError();
		}
		return text;
	}
	
   public String setupFeePenalty(FeePenaltyData fp) {
	   //Set up schedule detail info.
	   detailPg.fillFeePenalty(fp);
		detailPg.clickApply();
		ajax.waitLoading();
		browser.waitExists();		
		fp.id = detailPg.getFeeSchID();
		detailPg.clickOK();
		return fp.id;

	}

}
