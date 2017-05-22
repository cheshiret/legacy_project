package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Verify the validations and values when adding RA Fee schedule for Marina, including following check points:
 * 					a.The value for marina rate type should be:Transient, Lease, Seasonal, All and All is set as defect
 * 					b.Effective date is empty: System displays an error message on top of page
 * 					c.Effective date is invalid date: System will clear the invalid date automatically 
 * 					d.Account is emtpy:System displays an error message on top of page
 * 					e.Other rate is empty:System displays an error message on top of page 
 * 					f.Other rate is less than 0:System displays an error message on top of page
 * @Preconditions: none
 * @SPEC: Validate Entries-Effective Dates [TC:041560]
 *        Validate Entries-Account [TC:041562]
 *        Validate Entries-Marina Rate Type [TC:041563]
 *        Validate Entries-Other Rate [TC:041564]
 * @Task#: Auto-1460
 * @author Phoebe
 * @Date  Feb 02, 2013
 */
public class VerifyValidationAndValueForMarina extends FinanceManagerTestCase{
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private String effectiveDateEmpty, accountEmpty, otherRateEmpty, otherRateLessThanZero;
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		//Go to ra fee schedule detail page by adding a new one 
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);	
		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		
		//a.Verify the value for marina rate type should be:Transient, Lease, Seasonal, All and All is set as defacult
		this.verifyMarinaRateTypeValue();
		
		//b.Effective date is empty:System displays an error message on top of page
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		detailsPage.setSpecificEffectiveDate(StringUtil.EMPTY);
		clickOkOnDetailPage();
		verifyErrorMessage("Effective date is emtpty", effectiveDateEmpty);

		//c.Effective date is invalid date: System will clear the invalid date automatically 
		detailsPage.setSpecificEffectiveDate("today");
		detailsPage.clickDatesToMoveFocusFromCalendar();
		verifyEffectiveDateIsEmpty();
		
		//d.Account is emtpy:System displays an error message on top of page
		schedule.acctCode = "none";
		this.setUpRAFeeInfoAndClickOk();
		verifyErrorMessage("Account is emtpty", accountEmpty);
		
		//e.Other rate is empty:System displays an error message on top of page
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		schedule.acctCode = "4000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		setUpOtherRateAndClickOk();
		verifyErrorMessage("Other rate is emtpty", otherRateEmpty);
		
		//f.Other rate is less than 0:System displays an error message on top of page 
		schedule.otherRate = "-3";
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		setUpOtherRateAndClickOk();
		verifyErrorMessage("Other rate is less than 0", otherRateLessThanZero);
		
		fnm.logoutFinanceManager();
	}
	
	private void setUpOtherRateAndClickOk() {
		detailsPage.clickAddThresholdRate();
		detailsPage.setBaseRateOfOtherRate(schedule.otherRate,0);
		this.clickOkOnDetailPage();
	}

	private void verifyEffectiveDateIsEmpty() {
		String eDate = detailsPage.getEffectiveDate();
		if(!eDate.equals(StringUtil.EMPTY)){
			throw new ErrorOnPageException("The effective date should be set to blank if it is invalid format!");
		}
		logger.info("It is correct that effective date set to empty when effective date is invalid!");
	}
	
	private void verifyErrorMessage(String msgType, String errorMessage) {
		String errMsg = detailsPage.getError();
		if(!errorMessage.equalsIgnoreCase(errMsg)){
			throw new ErrorOnPageException("The "+ msgType +" is not correct, expect:"+errorMessage+"	But acutral is:" + errMsg);
		}
		logger.info("Error message for " + msgType + " is correct!");
	}
	
	private void setUpRAFeeInfoAndClickOk(){
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		this.clickOkOnDetailPage();
	}
	
	
	private void clickOkOnDetailPage(){
		detailsPage.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	private void verifyMarinaRateTypeValue() {
		boolean passed =true;
		List<String> expectMarinaRTs = new ArrayList<String>();
		expectMarinaRTs.add("All");
		expectMarinaRTs.add("Transient");
		expectMarinaRTs.add("Lease");
		expectMarinaRTs.add("Seasonal");
		//Check the default selected value is "All"
		String defaultSeleted = detailsPage.getMarinaRateTypeValue();
		if(!defaultSeleted.equalsIgnoreCase("All")){
			passed &= false;
			logger.error("'All' is not the default selected value for marina rate type!");
		}
		//Check the content of the marina rate type drop down list
		List<String> marinaRTs = detailsPage.getMarinaRateTypeFromDropDownlist();
		if((!marinaRTs.containsAll(expectMarinaRTs))||(!expectMarinaRTs.containsAll(marinaRTs))){
			passed &= false;
			logger.error("Expect marina rate types:" + this.printMarinaRateType(expectMarinaRTs) + " But actually rate types in list are:" + this.printMarinaRateType(marinaRTs));
		}
		if(!passed){
			throw new ErrorOnPageException("The marina rate type information on the page is not correct, please check the log above!");
		}
		logger.info("Marina rate type values and its default value is correct!");
	}
	
	private String printMarinaRateType(List<String> rateType){
		String rts = "[" ;
		for(String rt:rateType){
			rts += rt + ",";
		}
		rts += "]";
		return rts;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";//Jordan Lake State Rec Area
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip"; //This is the check point, can not be changed
		
		schedule.acctCode = "4000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "15.55";
		
		effectiveDateEmpty = "An Effective Date for the RA Fee Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided."; 
		accountEmpty = "An Account for the RA Fee Schedule is required. Please select the Account from the list provided.";
		otherRateEmpty = "All Rates in the RA Fee Schedule must have a value greater than or equal to $0.00. Please change your entries.";
		otherRateLessThanZero = "All Rates in the RA Fee Schedule must have a value greater than or equal to $0.00. Please change your entries.";
	}
}
