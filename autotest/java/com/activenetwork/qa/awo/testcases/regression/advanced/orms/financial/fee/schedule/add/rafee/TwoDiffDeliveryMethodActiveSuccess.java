package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case is used to could add new ra fee schedule which just delivery method is different with system
 * existing transaction fee schedule
 * @Preconditions:
 * @SPEC:Edit RA Fee Schedule.UCS
 * @Task#:AUTO-1132
 * 
 * @author vzhang
 * @Date Aug 9, 2012
 */

public class TwoDiffDeliveryMethodActiveSuccess extends FinanceManagerTestCase{
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private RaFeeScheduleData newSchedule = new RaFeeScheduleData();

	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    fnm.gotoRaFeeSchedulePage();
	    
	    //add new RA Fee Schedule
	    feeMainPg.cleanupFeeSchedule(schedule);
	    schedule.feeId =  fnm.addNewRaFeeSchedule(schedule);
	    feeMainPg.changeScheduleStatus(schedule.feeId, ACTIVE_STATUS);
	    feeMainPg.searchByFeeScheduleId(schedule.feeId);	    
	    boolean isActive = feeMainPg.isFeeScheduleActive(schedule.feeId);
	    if(!isActive){
	    	throw new ErrorOnPageException("Please check the data, should this schedule should be activated. schedule id = " 
	    			+ schedule.feeId);
	    }	
	    
	    newSchedule = schedule;
	    newSchedule.deliverymethod = "All";
	    newSchedule.feeId =  fnm.addNewRaFeeSchedule(newSchedule);
	    //the new ra fee schedule could be active success, because delivery method is different with before
	    feeMainPg.changeScheduleStatus(newSchedule.feeId, ACTIVE_STATUS);
	    feeMainPg.searchByFeeScheduleId(schedule.feeId);	    
	    isActive = feeMainPg.isFeeScheduleActive(schedule.feeId);
	    if(!isActive){
	    	throw new ErrorOnPageException("Please check the data, should this schedule should be activated. schedule id = " 
	    			+ schedule.feeId);
	    }	
	    
	    //clear up
	    feeMainPg.changeScheduleStatus(newSchedule.feeId, INACTIVE_STATUS);
	    feeMainPg.changeScheduleStatus(schedule.feeId, INACTIVE_STATUS);
	    
	    fnm.logoutFinanceManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
	  	//initialize login finance manager loginInfo
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
	  	//initialize schedule data
	  	schedule.productCategory = "Ticket";
		schedule.locationCategory = "Park";
	  	schedule.location = "RYAN PARK";	  	
	  	schedule.loop = "RYAN";
	  	schedule.productGroup = "Boat Tour";
	  	schedule.product = "All";
	  	schedule.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
	  	schedule.salesChannel = "FieldMgr";
	    schedule.applyRate = "Order Level";
	  	
	  	schedule.raFeeOption = "Order Confirmed (will be reversed due to non payment)";
	  	schedule.tranType="Advanced Ticket Purchase";
	  	schedule.tranOccur="14 or more days before tour date";
	  	schedule.acctCode = "USFS.4000.9000.----; Cabin CLIN Fees";
	  	schedule.unitOption = "Per Transaction";
	  	schedule.deliverymethod = "Print at Home";
	  	schedule.personTypes = new String[]{"All"};
	  	schedule.baseRates = new String[]{"9.1"};

	  	String[] thresholdRatesForAll= new String[]{"7.2","3.3","1.6"};
	  	
	  	schedule.thresholdRates = new ArrayList<String[]>();
	  	schedule.thresholdRates.add(thresholdRatesForAll);
	  	
	  	schedule.searchBy = "Product Group";
	  	schedule.searchValue = schedule.productGroup;
	  	schedule.searchDate = "Effective (Start) Date";
	  	schedule.searchDateFrom =  schedule.effectDate;
	  	schedule.searchDateTo = schedule.effectDate;
	  	schedule.searchStatus = ACTIVE_STATUS;
	}

}
