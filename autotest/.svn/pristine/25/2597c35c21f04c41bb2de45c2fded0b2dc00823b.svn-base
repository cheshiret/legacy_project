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
 * check point: 1.  order lever, transaction type is  "Add New Ticket", per transaction is select
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *                  add threshold rate button should enable, and could add multip
 *              2.  order lever, transaction type is  "Advanced Ticket Purchase",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *                  add threshold rate button should enable, and could add multip
 *              3.  order lever, transaction type is  "Transfer Tickets - Customer Transfer",per transaction is select
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  have from rate text, and to rate text    
 *                  add threshold rate button should enable, and could add multip
 *              4.  order item lever, transaction type is  "Add New Ticket",per transaction is select
 *                  Embed In Ticket check box is disable
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *                  add threshold rate button should enable, and could add multip       
 *              5.  order item lever, transaction type is  "Advanced Ticket Purchase",per transaction is select
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *                  add threshold rate button should enable, and could add multip    
 *              6.  order item lever, transaction type is  "Transfer Tickets - Customer Transfer",per transaction is select
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  have from rate text, and to rate text 
 *                  add threshold rate button should enable, and could add multip     
 * @Preconditions:
 * @SPEC:Add RA Fee Schedule.UCS TC:037420
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 6, 2012
 */

public class VerifyTicketTypeAndRatePerTran extends FinanceManagerTestCase{
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
	    schedule.tranType = "Add New Ticket";
	    schedule.unitOption = "Per Transaction";	
	    this.selectApplyFeeAndFeePerType(schedule.applyRate, schedule.unitOption);
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeAndRateInfo(false);
	    this.verifyAddThresholdRate();
	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeAndRateInfo(false);
	    this.verifyAddThresholdRate();
	    
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeAndRateInfo(true);
	    this.verifyAddThresholdRate();
		
	    /**
	     * Per order Item
	     */
	    schedule.applyRate = "Order Item Level";
	    schedule.tranType = "Add New Ticket";
	    schedule.unitOption = "Per Transaction";
	    this.selectApplyFeeAndFeePerType(schedule.applyRate, schedule.unitOption);
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeAndRateInfo(false);
	    this.verifyAddThresholdRate();
	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeAndRateInfo( false);
	    this.verifyAddThresholdRate();
	    
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeAndRateInfo(true);
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
	    if(perType.equals("Per Transaction")){
	    	detailPg.selectRAFeeUnit("2");
	    	detailPg.waitLoading();
	    }else{
	    	throw new ItemNotFoundException("The per type is not correct.  per type shoulde be Per Transaction");
	    }	    	    
	}
	
	private void verifyTicketTypeAndRateInfo( boolean isHaveToRate){
		detailPg.verifyAddTicketTypeButtonIsEnable(false);
		detailPg.verifyTicketTypeDropDownList(false,false);
		this.verifyRateInfo(isHaveToRate);
	}
	
	private void verifyRateInfo(boolean toRateIsExisting){
		boolean result = true;
		boolean rateIsEnableAct = detailPg.checkRateIsEnable();
		boolean toRateIsExistingAct = detailPg.checkToRateExisting();
		
		result &= MiscFunctions.compareResult("Rate enable info", true, rateIsEnableAct);
		result &= MiscFunctions.compareResult("To Rate existing info", toRateIsExisting, toRateIsExistingAct);
		
		if(!result){
			throw new ErrorOnPageException("The rate info is not correct. please check log.");
		}else {
			logger.info("The rate info is correct.");
		}
	}
	
	private void verifyAddThresholdRate(){
		boolean result = true;

		detailPg.clickAddThresholdRate();
		boolean firstThresholdRateEnable = detailPg.checkThresholdRateTextIsInable(0);		
		
		result &= MiscFunctions.compareResult("First Threshold rate enable info", true, firstThresholdRateEnable);
		boolean addThresholdRateIsExisting = detailPg.checkAddThresholdRateIsExisting();
		result &= MiscFunctions.compareResult("Add Threshold rate is existing after click Add", true, addThresholdRateIsExisting);
		//click Remove
		detailPg.clickRemoveThresholdRate(0);
		firstThresholdRateEnable = detailPg.checkThresholdRateTextIsExisting(0);
		result &= MiscFunctions.compareResult("First Threshold rate enable info after click remove", false, firstThresholdRateEnable);
		
		if(!result){
			throw new ErrorOnPageException("The threshold info is not correct. please check log.");
		}else {
			logger.info("The threshold info is correct.");
		}
	}

}
