package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case is used to could add new transaction fee schedule which just delivery method is different with system
 * existing transaction fee schedule
 * @Preconditions:
 * @SPEC:Add Fee Schedule.UCS TC:037384
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 1, 2012
 */

public class TwoDiffDeliveryMethodActiveSuccess extends FinanceManagerTestCase{
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private FeeScheduleData newSchedule = new FeeScheduleData();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    //Add a new Fee schedule data
	    feeMainPg.cleanupFeeSchedule(schedule);
	    schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
	    feeMainPg.changeScheduleStatus(schedule.feeSchdId, ACTIVE_STATUS);
	    feeMainPg.searchByFeeScheduleId(schedule.feeSchdId);
	    boolean isActive = feeMainPg.isFeeScheduleActive(schedule.feeSchdId);
	    if(!isActive){
	    	throw new ErrorOnPageException("Please check the data, should this schedule should be activated. schedule id = " 
	    			+ schedule.feeSchdId);
	    }	    
	    
	    newSchedule = schedule;
	    newSchedule.deliveryMethod = "All";
	    newSchedule.feeSchdId = fnm.addNewFeeSchedule(newSchedule);
	    feeMainPg.changeScheduleStatus(newSchedule.feeSchdId, ACTIVE_STATUS);
	    
	    //the new transaction fee schedule should be active success, because delivery method is different with before
	    feeMainPg.searchByFeeScheduleId(newSchedule.feeSchdId);
	    isActive = feeMainPg.isFeeScheduleActive(newSchedule.feeSchdId);
	    if(!isActive){
	    	throw new ErrorOnPageException("This transaction fee schedule should be active success. schedule id = " 
	    			+ schedule.feeSchdId);
	    }
		
	    //clear up
	    feeMainPg.changeScheduleStatus(newSchedule.feeSchdId, INACTIVE_STATUS);
	    feeMainPg.changeScheduleStatus(schedule.feeSchdId, INACTIVE_STATUS);
	    
	    fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	
	    //initialize attribute fee schedule data
	  	schedule.productCategory = "Ticket";
	  	schedule.feeType ="Transaction Fee";
		schedule.locationCategory = "Park";
	  	schedule.location = "RYAN PARK";
	  	schedule.loop = "RYAN";
	  	schedule.productGroup = "Boat Tour";
	  	schedule.product = "All";
	  	schedule.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
	  	schedule.salesChannel = "Web";
	  	schedule.state = "All";
	  	schedule.tranType="Advanced Ticket Purchase";
	  	schedule.tranOccur="14 or more days before tour date";
	  	schedule.acctCode="USFS.3000.8100.1001; Cabin Electric Revenue";
	  	schedule.tranFeeOption="Per Transaction";
	  	schedule.ticketCategory="Individual";
	  	schedule.applyFee="Per Order";
	  	schedule.ticketTypes=new String[]{"All"};
	  	schedule.anyDayRates=new String[]{"6.0"};
	  	schedule.deliveryMethod = "Print at Home";
	  	
	  	schedule.searchType="Product Group";
	  	schedule.searchValue=schedule.productGroup;
	  	schedule.activeStatus="Active";
	  	schedule.dateType = "Effective (Start) Date";
	  	schedule.fromDate = schedule.effectDate;
	  	schedule.toDate = schedule.effectDate;
	}

}
