package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.penalty;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case is to verify the setup of Fee Penalty Schedule to include minimum unit of stay, 
 * minimum number of days before arrival date criteria, and Number of Free Transactions for Transaction Fees.
 * For PCR2523.
 * @Preconditions: None
 * @SPEC: Add Fee Penalty Schedule [TC:061820]
 * @Task#: Auto-1578
 * 
 * @author nding1
 * @Date  Apr 18, 2013
 */
public class VerifyErrMsgMinUnitStayAndNumDays extends FinanceManagerTestCase{
	private String minUnitStay, minNumDaysBeforeArrival, numFreeChanges;
	private FeePenaltyData schedule = new FeePenaltyData();
	private FinMgrFeePenaltyDetailPage detailsPg = FinMgrFeePenaltyDetailPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		this.gotoFeePenaltyDetialPg(schedule);

		// verify default value
		if(!MiscFunctions.compareResult("Default value of Minimum Unit of Stay", "0", detailsPg.getMinimumUnitOfStay())){
			throw new ErrorOnPageException("---Check log above.Default value of Minimum Unit of Stay should be 0.");
		}

		if(!MiscFunctions.compareResult("Default value of Minimum Number of Days before Arrival Date", "0", detailsPg.getMinimumNumOfDaysBeforeArrivalDate())){
			throw new ErrorOnPageException("---Check log above.Default value of Minimum Number of Days before Arrival Date should be 0.");
		}
	
		// 1.Minimum Unit of Stay is blank
		detailsPg.setMinumuUnitOfStay(StringUtil.EMPTY);
		this.clickApply();
		this.verifyErrorMessage("Error message when minimum unit of stay is blank", minUnitStay);
		
		// 2.Minimum Unit of Stay is not valid number
		detailsPg.setMinumuUnitOfStay("45hhh645");
		this.clickApply();
		this.verifyErrorMessage("Error message when minimum unit of stay is not valid number", minUnitStay);
		
		// 3.Minimum Number of Days before Arrival Date is blank
		detailsPg.setMinumuUnitOfStay("0");
		detailsPg.setMinimumNumOfDaysBeforArrivalDate(StringUtil.EMPTY);
		this.clickApply();
		this.verifyErrorMessage("Error message when Minimum Number of Days before Arrival Date is blank", minNumDaysBeforeArrival);

		// 4.Minimum Number of Days before Arrival Date is not valid number
		detailsPg.setMinimumNumOfDaysBeforArrivalDate("yr748");
		this.clickApply();
		this.verifyErrorMessage("Error message when Minimum Number of Days before Arrival Date is blank", minNumDaysBeforeArrival);
		
		// 5.Set transaction type as 'Change Dates' and # of Free Change(s) per Reservation is blank
		detailsPg.setMinimumNumOfDaysBeforArrivalDate("1");
		detailsPg.selectTransactionType("Shorten Stay Leave Earlier");
		ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.setNumOfFreeChangesPerTrans("j97");
		this.clickApply();
		this.verifyErrorMessage("Error message when # of Free Change(s) per Reservation is blank", numFreeChanges);
		
		// add successfully
		detailsPg.setNumOfFreeChangesPerTrans(StringUtil.EMPTY);
		this.clickApply();
		schedule.id = detailsPg.getFeeSchID();
		if(StringUtil.isEmpty(schedule.id)){
			throw new ErrorOnPageException("This fee schedule should be added successfully!!");
		}
		fnm.logoutFinanceManager();
	}
	
	private void clickApply(){
		detailsPg.clickApply();
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	private void gotoFeePenaltyDetialPg(FeePenaltyData fp){
		FinMgrFeePenaltyMainPage mainPg = FinMgrFeePenaltyMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
		
		mainPg.clickAddNew();
		findLocPg.waitLoading();		
		findLocPg.searchByLocationName(fp.location, fp.locationCategory);
		ajax.waitLoading();
		findLocPg.selectLocation(fp.location);
		detailsPg.waitLoading();
		detailsPg.selectPrdCategory(fp.productCategory);
		detailsPg.waitLoading();
		detailsPg.setEffectiveDate(schedule.effectDate);
		detailsPg.setInvStartDate(schedule.startInv);
		detailsPg.setInvEndDate(schedule.endInv);
		detailsPg.setUnitValue(schedule.value);
	}
	
	private void verifyErrorMessage(String desc, String expectMsg){
		String actualMsg = detailsPg.getError();
		if(!MiscFunctions.compareResult(desc, expectMsg, actualMsg)){
			throw new ErrorOnPageException("---Check log above.");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		schedule.productCategory = "Site";
		schedule.feeType = "Use Fee";
		schedule.location = fnm.getFacilityName("552818", schema);// Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.effectDate = DateFunctions.getToday();
		schedule.startInv = DateFunctions.getToday();
		schedule.endInv = DateFunctions.getToday();
		schedule.value = "2";

		minUnitStay = "The Minimum Unit of Stay must have a value greater than or equal to 0. Please change your entries.";
		minNumDaysBeforeArrival = "The Minimum Number of Days before Arrival Date must have a value greater than or equal to 0. Please change your entries.";
		numFreeChanges = "The Number of Free Transactions, if specified must have a value greater than or equal to 1. Please change your entries.";
	}
}
