package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * @Description:This test case is used to verify ticket type and rate text info when select different transaction type and apply lever, 
 * and Select Per Transaction.
 * check point: 
 *              1.  order lever, transaction type is  "Advanced Ticket Purchase",Flat by Range of Ticket Quantity is select
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  have rate text
 *                  have range text, and add range button
 *                  add threshold rate button should not existing   
 * @Preconditions:
 * @SPEC:Add RA Fee Schedule.UCS TC:037420
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 6, 2012
 */


public class VerifyTicketTypeAndRateFlatBy extends FinanceManagerTestCase{
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailPg = FinMgrRaFeeSchDetailPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    fnm.gotoRaFeeSchedulePage();
	    
	    //add new RA Fee Schedule
	    fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
	    this.gotoRaScheduleDetailPgFromSelectLocationPg();
	    
	    /**
	     * Per order
	     */
	    schedule.applyRate = "Order Level";
	    schedule.unitOption = "Flat by Range of Ticket Quantity";	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.selectApplyFeeAndFeePerType(schedule.applyRate, schedule.unitOption);
	    this.verifyTicketTypeInfo();
	    this.verifyRangeAndRateInfo();
	    this.verifyAddThresholdRate();
	    
	    fnm.logoutFinanceManager();			
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	
	    //initialize fee schedule
	  	schedule.productCategory = "Ticket";
		schedule.locationCategory = "Park";
	  	schedule.location = "RYAN PARK";
	}
	
	private void gotoRaScheduleDetailPgFromSelectLocationPg(){
		logger.info("Go to ra schedule detail page from select location page.");
	    detailPg.selectPrdCategory(schedule.productCategory);
		detailPg.waitLoading();
	}
	
	private void selectApplyFeeAndFeePerType(String applyRate, String perType){
		logger.info("Select apply fee is " + applyRate + ", per type is " + perType);
	    detailPg.selectApplyRate(applyRate);
	    if(perType.equals("Flat by Range of Ticket Quantity")){
	    	detailPg.selectRAFeeUnit("3");
	    	detailPg.waitLoading();
	    }else{
	    	throw new ItemNotFoundException("The per type is not correct. per type shoulde be Flat by Range of Ticket Quantity");
	    }	    	    
	}
	
	private void verifyTicketTypeInfo(){
		detailPg.verifyAddTicketTypeButtonIsEnable(false);
		detailPg.verifyTicketTypeDropDownList(false,true);
	}
	
	private void verifyRangeAndRateInfo(){
		logger.info("Verify range and rate info.");
		boolean result = true;
		detailPg.clickAddRange();
		
		boolean firstRangeIsEnable = detailPg.checkRangeIsEnable(0);
		boolean secondRangeIsEnable = detailPg.checkRangeIsEnable(1);
		boolean firstRangeRateIsEnable = detailPg.checkRangeRateIsEnable(0);
		boolean secondRangeRateIsEnable = detailPg.checkRangeRateIsEnable(1);
		boolean toRateIsExisting = detailPg.checkToRateExisting();
		String firstRangeInfo = detailPg.getRange(0);
		
		result &= MiscFunctions.compareResult("First Range info", "1", firstRangeInfo);
		result &= MiscFunctions.compareResult("First Range enable info", false, firstRangeIsEnable);
		result &= MiscFunctions.compareResult("Second Range enable info", true, secondRangeIsEnable);
		result &= MiscFunctions.compareResult("First Range rate enable info", true, firstRangeRateIsEnable);
		result &= MiscFunctions.compareResult("Second Range rate enable info", true, secondRangeRateIsEnable);
		result &= MiscFunctions.compareResult("To rate enable info", false, toRateIsExisting);		
		
		detailPg.clickRemoveRange(0);
		boolean secondRangeIsExisting = detailPg.checkRangeRateIsExisting(1);
		result &= MiscFunctions.compareResult("Second Range existing info", false, secondRangeIsExisting);
		
		if(!result){
			throw new ErrorOnPageException("The range disable info is not correct, please check log.");
		}else{
			logger.info("The range disable info is correct.");
		}
	}
	
	private void verifyAddThresholdRate(){
		boolean result = true;
		
		boolean addThresholdRateIsExisting = detailPg.checkAddThresholdRateIsExisting();
		result &= MiscFunctions.compareResult("Add Threshold rate button", false, addThresholdRateIsExisting);
		
		if(!result){
			throw new ErrorOnPageException("The threshold info is not correct. please check log.");
		}else {
			logger.info("The threshold info is correct.");
		}
	}

}
