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
 * check point: 1.  order lever, transaction type is  "Advanced Ticket Purchase", per unit is select
 *                  ticket type drop down list just have All option, Add Ticket Type button disable
 *                  just have rate text
 *                  add threshold rate button should not existing
 *              2.  Order Item Level, transaction type is  "Add New Ticket",per unit is select
 *                  ticket type drop down list just have multip option, Add Ticket Type button enable
 *                  just have rate text
 *                  add threshold rate button should existing
 *              3.  Order Item Level, transaction type is  "Transfer Tickets - Customer Transfer",per unit is select
 *                  ticket type drop down list just have multip option, Add Ticket Type button enable
 *                  have from rate text, and to rate text    
 *                  add threshold rate button should existing
 *              4.  Order Item Level, transaction type is  "Advanced Ticket Purchase",per unit is select
 *                  ticket type drop down list just have have multip option, Add Ticket Type button enable
 *                  just have rate text
 *                  add threshold rate button should existing  
 * @Preconditions:
 * @SPEC:Add RA Fee Schedule.UCS TC:037420
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 6, 2012
 */

public class VerifyTicketTypeAndRatePerUnit extends FinanceManagerTestCase{
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
	    schedule.tranType = "Advanced Ticket Purchase";
	    schedule.unitOption = "Per Unit";	
	    detailPg.selectTransactionType(schedule.tranType);
	    this.selectApplyFeeAndFeePerType(schedule.applyRate, schedule.unitOption);
	    this.verifyTicketTypeInfo(false, false, false,false);
	    
	    /**
	     * Per order Item
	     */
	    schedule.applyRate = "Order Item Level";
	    schedule.tranType = "Add New Ticket";
	    schedule.unitOption = "Per Unit";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.selectApplyFeeAndFeePerType(schedule.applyRate, schedule.unitOption);
	    this.verifyTicketTypeInfo(true, true, false,true);
		
	    schedule.tranType = "Transfer Tickets - Customer Transfer";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeInfo(true, true, true,true);
	    
	    schedule.tranType = "Advanced Ticket Purchase";
	    detailPg.selectTransactionType(schedule.tranType);
	    this.verifyTicketTypeInfo(true, true, false,true);
	    
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
	    if(perType.equals("Per Unit")){
	    	detailPg.selectRAFeeUnit("1");
	    	detailPg.waitLoading();
	    }else{
	    	throw new ItemNotFoundException("The per type is not correct.  per type shoulde be Per Transaction");
	    }	    	    
	}
	
	private void verifyTicketTypeInfo(boolean addTicketTypeButtonIsEnable, boolean isMultiTicktType, boolean isHaveToRate, boolean thresholdRateIsExisting){
		detailPg.verifyTicketTypeDropDownList(isMultiTicktType,false);
		this.verifyAddTicketTypeButtonAndRateInfo(addTicketTypeButtonIsEnable,isHaveToRate,thresholdRateIsExisting);		
	}
	
	/**
	 * First verify rate info
	 * second verify Add Ticket Type button whether existing
	 * If existing verify second rate info, then click remove, verify second rate info is not existing
	 * @param addTicketTypeButtonIsEnable
	 * @param isHaveToRate
	 */
	private void verifyAddTicketTypeButtonAndRateInfo(boolean addTicketTypeButtonIsEnable,boolean isHaveToRate,boolean thresholdRateIsExisting){
		logger.info("Verify add ticket type button and rate info.");
		boolean result = true;
		detailPg.verifyAddTicketTypeButtonIsEnable(addTicketTypeButtonIsEnable);
		boolean firstRateIsEnable = detailPg.checkRateIsEnable(0);
		result &= MiscFunctions.compareResult("First Rate enable info", true, firstRateIsEnable);
		
		if(isHaveToRate){
			boolean firstToRateIsEnable = detailPg.checkToRateIsEnable(0);
			result &= MiscFunctions.compareResult("First To Rate enable info", true, firstToRateIsEnable);
		}else{
			boolean firstToRateIsExisting = detailPg.checkToRateExisting();
			result &= MiscFunctions.compareResult("First To Rate existing info", false, firstToRateIsExisting);
		}		
		
		if(addTicketTypeButtonIsEnable){
			detailPg.clickAddTicketType();			
			boolean sencondRateIsEnable = detailPg.checkRateIsEnable(1);
			result &= MiscFunctions.compareResult("Second Rate enable info after click add button", true, sencondRateIsEnable);
			if(isHaveToRate){
				boolean sencondToRateIsEnable = detailPg.checkToRateIsEnable(1);
				result &= MiscFunctions.compareResult("Second To Rate enable info after click add button", true, sencondToRateIsEnable);
			}
			
			detailPg.clickRemoveTicketType(0);
			
			boolean sencondRateIsExisting = detailPg.checkRateIsExisting(1);
			boolean secondToRateIsExisting = detailPg.checkToRateIsExisting(1);
			result &= MiscFunctions.compareResult("Second Rate Existing info after click remove button", false, sencondRateIsExisting);
			result &= MiscFunctions.compareResult("Second To Rate Existing info after click remove button", false, secondToRateIsExisting);
		}
		
		boolean actthresholdRateIsExisting = detailPg.checkAddThresholdRateIsExisting();
		result &= MiscFunctions.compareResult("Add Threshold Rate is existing", thresholdRateIsExisting, actthresholdRateIsExisting);
		
		if(!result){
			throw new ErrorOnPageException("The Add Ticket Type and rate info is not correct, please check log.");
		}else{
			logger.info("The Add Ticket Type and rate info is correct.");
		}		
		
	}

}
