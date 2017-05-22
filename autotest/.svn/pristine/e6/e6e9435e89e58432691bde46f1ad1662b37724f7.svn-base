package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case is to verify the setup of RA Fee Schedule to include minimum unit of stay, 
 * minimum number of days before arrival date criteria, and Number of Free Transactions for Transaction Fees.
 * For PCR2523.
 * @Preconditions: None
 * @SPEC: Add RA Fee schedule [TC:061819]
 * @Task#: Auto-1578
 * 
 * @author nding1
 * @Date  Apr 19, 2013
 */
public class VerifyErrMsgMinUnitStayAndNumDays extends FinanceManagerTestCase{
	private String minUnitStay, minNumDaysBeforeArrival, numFreeChanges;
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailsPg = FinMgrRaFeeSchDetailPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		detailsPg.selectPrdCategory(schedule.productCategory);
		detailsPg.waitLoading();

		// verify default value
		if(!MiscFunctions.compareResult("Default value of Minimum Unit of Stay", "0", detailsPg.getMinimumUnitOfStay())){
			throw new ErrorOnPageException("---Check log above.Default value of Minimum Unit of Stay should be 0.");
		}

		if(!MiscFunctions.compareResult("Default value of Minimum Number of Days before Arrival Date", "0", detailsPg.getMinimumNumOfDaysBeforeArrivalDate())){
			throw new ErrorOnPageException("---Check log above.Default value of Minimum Number of Days before Arrival Date should be 0.");
		}
	
		// 1.Minimum Unit of Stay is blank
		detailsPg.setEffectiveDate(schedule.effectDate);
		detailsPg.setBaseRate(schedule.rate);
		detailsPg.selectFirstAccount();
		detailsPg.setMinumuUnitOfStay(StringUtil.EMPTY);
		this.clickApply();
		this.verifyErrorMessage("Error message when minimum unit of stay is blank", minUnitStay);
		
		// 2.Minimum Unit of Stay is not valid number
		detailsPg.setMinumuUnitOfStay("1f2f5f");
		this.clickApply();
		this.verifyErrorMessage("Error message when minimum unit of stay is not valid number", minUnitStay);
		
		// 3.Minimum Number of Days before Arrival Date is blank
		detailsPg.setMinumuUnitOfStay("0");
		detailsPg.setMinimumNumOfDaysBeforArrivalDate(StringUtil.EMPTY);
		this.clickApply();
		this.verifyErrorMessage("Error message when Minimum Number of Days before Arrival Date is blank", minNumDaysBeforeArrival);

		// 4.Minimum Number of Days before Arrival Date is not valid number
		detailsPg.setMinimumNumOfDaysBeforArrivalDate("p098");
		this.clickApply();
		this.verifyErrorMessage("Error message when Minimum Number of Days before Arrival Date is blank", minNumDaysBeforeArrival);
		
		// 5.Set transaction type as 'Change Dates' and # of Free Change(s) per Reservation is blank
		detailsPg.setMinimumNumOfDaysBeforArrivalDate("0");
		detailsPg.selectTransactionType("Extend Stay Arrive Earlier");
		ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.setNumOfFreeChangesPerTrans("u788u");
		this.clickApply();
		this.verifyErrorMessage("Error message when # of Free Change(s) per Reservation is blank", numFreeChanges);
		
		// add successfully
		detailsPg.setNumOfFreeChangesPerTrans(StringUtil.EMPTY);
		this.clickApply();
		schedule.feeSchdId = detailsPg.getFeeSchID();
		if(StringUtil.isEmpty(schedule.feeSchdId)){
			throw new ErrorOnPageException("This fee schedule should be added successfully!!");
		}
		fnm.logoutFinanceManager();
	}
	
	private void clickApply(){
		detailsPg.clickApply();
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	private void verifyErrorMessage(String desc, String expectMsg){
		String actualMsg = detailsPg.getErrorMsg();
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
		schedule.location = fnm.getFacilityName("552818", schema);// Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.effectDate = DateFunctions.getToday();
		schedule.rate = "3.65";

		minUnitStay = "The Minimum Unit of Stay must have a value greater than or equal to 0. Please change your entries.";
		minNumDaysBeforeArrival = "The Minimum Number of Days before Arrival Date must have a value greater than or equal to 0. Please change your entries.";
		numFreeChanges = "The Number of Free Transactions, if specified must have a value greater than or equal to 1. Please change your entries.";
	}
}
