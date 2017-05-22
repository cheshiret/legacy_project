package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.posfee;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to add pos fee with duplicate information
 * @LinkSetUp:   d_inv_add_mpos:id=590(PRODUCT='POS_For_Fee_1')
 * 			     d_inv_add_mpos:id=630(PRODUCT='POS_For_Fee_5')
 * @SPEC:[TC:109347] Add Fee Schedule -- with Product Category POS and Fee Type "POS Fee" 
 * @Task#: Auto-2121
 * @author Phoebe Chen
 * @Date  April 28, 2014
 */
public class AddPosFeeWithOverlapInfo extends FinanceManagerTestCase {
	private FeeScheduleData schedule_orginal = new FeeScheduleData();
	private FeeScheduleData schedule_New = new FeeScheduleData();
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private String scheduleId_1, scheduleId_2, scheduleId_3, scheduleId_4, scheduleId_5;
	private String expMsg = "";
	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
		
	    //Clear up data:deactive all the pos fee schedule for the two product
	    feeMainPg.deactiveAllTheFeeForProduct(schedule_orginal.product, "POS Fee");
	    feeMainPg.deactiveAllTheFeeForProduct("POS_For_Fee_5", "POS Fee");
	    
	    //Add two pos Fee schedule data, and active them, just with the purchase type is different
	    schedule_New.purchaseType = "Replacement";
	    schedule_orginal.feeSchdId = fnm.addNewFeeSchedule(schedule_orginal);
	    feeMainPg.changeScheduleStatus(schedule_orginal.feeSchdId, ACTIVE_STATUS);
	    
	    //Add a pos fee schedule with the same information as schedule_orginal, and just sale channel is different, the schedule can be active successfully
	    this.setSecondScheduleTheSameAsOrignal();
	    schedule_New.salesChannel = "CallCenter";
	    scheduleId_1 = fnm.addNewFeeSchedule(schedule_New);
	    this.activeSchedule(scheduleId_1, true);
	    
	    //Add a pos fee schedule with the same information as schedule_orginal, and just product group is different, the schedule can be active successfully
	    this.setSecondScheduleTheSameAsOrignal();
	    schedule_New.productGroup = "Admissions";
	    schedule_New.product = "POS_For_Fee_5";
	    scheduleId_2 = fnm.addNewFeeSchedule(schedule_New);
	    this.activeSchedule(scheduleId_2, true);
	    
	    //Add a pos fee schedule with the same information as schedule_orginal, and just effective start date is different, the schedule can be active successfully
	    this.setSecondScheduleTheSameAsOrignal();
	    schedule_New.fromDate = DateFunctions.getDateAfterGivenDay(schedule_orginal.fromDate, 3);
	    schedule_New.toDate = DateFunctions.getDateAfterGivenDay(schedule_orginal.toDate, 3);
	    scheduleId_3 = fnm.addNewFeeSchedule(schedule_New);
	    this.activeSchedule(scheduleId_3, true);
	    
	    //Add a pos fee schedule with the same information as schedule_orginal, and just purchase type is different, the schedule can be active successfully
	    this.setSecondScheduleTheSameAsOrignal();
	    schedule_New.purchaseType = "All";
	    scheduleId_4 = fnm.addNewFeeSchedule(schedule_New);
	    this.activeSchedule(scheduleId_4, true);
	    
	    //Add a pos fee schedule with the same information as schedule_orginal
	    this.setSecondScheduleTheSameAsOrignal();
	    scheduleId_5 = fnm.addNewFeeSchedule(schedule_New);
	    expMsg = "The POS Fee Schedule " + scheduleId_5 + " cannot be activated because another identical active POS Fee Schedule "+ schedule_New.feeSchdId + " exists." ;
	    String actMsg = this.activeSchedule(scheduleId_5, false);
	    this.verifyErrorMsg(actMsg);
	    	    
	    fnm.logoutFinanceManager();
	}
	
	private void verifyErrorMsg(String actMsg) {
		if(actMsg.matches(expMsg)){
			throw new ErrorOnPageException("The error message is not correct for duplicat information for schedule");
		}
		logger.info("The error message is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "SC Contract";
	  	login.location = "Administrator/South Carolina State Park Service";
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
	  	
	    //initialize the first fee schedule data
	  	schedule_orginal.productCategory = "POS";
	  	schedule_orginal.feeType ="POS Fee";
	  	schedule_orginal.locationCategory = "Park";
	  	schedule_orginal.location = fnm.getFacilityName("10137", schema); //CHARLES TOWNE LANDING
	  	schedule_orginal.productGroup = "RC SALE OF SERVICES";
	  	schedule_orginal.product = "POS_For_Fee_1";
	  	schedule_orginal.fromDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-2), "EEE MMM d yyyy");
	  	schedule_orginal.toDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");
	  	schedule_orginal.salesChannel = "FieldMgr";
	  	schedule_orginal.acctCode = "0799.3000.1300.--; Overage/Shortage";
	  	schedule_orginal.purchaseType = "Original";
	  	schedule_orginal.rate = "2.01";
	  	
	  	
	    //initialize the second fee schedule data
	  	this.setSecondScheduleTheSameAsOrignal();
	}
	
	private void setSecondScheduleTheSameAsOrignal(){
	    //initialize attribute fee schedule data
		schedule_New.productCategory = schedule_orginal.productCategory;
		schedule_New.feeType = schedule_orginal.feeType;
		schedule_New.locationCategory = schedule_orginal.locationCategory;
		schedule_New.location = schedule_orginal.location;
		schedule_New.productGroup = schedule_orginal.productGroup;
		schedule_New.product = schedule_orginal.product;
		schedule_New.fromDate = schedule_orginal.fromDate;
		schedule_New.toDate = schedule_orginal.toDate;
		schedule_New.salesChannel = schedule_orginal.salesChannel;
	  	schedule_New.acctCode = schedule_orginal.acctCode;
	  	schedule_New.purchaseType = schedule_orginal.purchaseType;
	  	schedule_New.rate = schedule_orginal.rate;
	}
	
	public String activeSchedule(String feeSchdId, boolean noErr) {
		String errMsg = "";
		feeMainPg.searchByFeeScheduleId(feeSchdId);
		feeMainPg.selectScheduleCheckBox(feeSchdId);
		feeMainPg.clickActive();
		ajax.waitLoading();
		feeMainPg.waitLoading();
		errMsg = feeMainPg.getErrorMsg();
		if(noErr&&StringUtil.notEmpty(errMsg)){
			throw new ErrorOnPageException("There should no error to active the fee scheuld with id:" + feeSchdId);
		}
		if(!noErr&&StringUtil.isEmpty(errMsg)){
			throw new ErrorOnPageException("The fee scheuld with id:" + feeSchdId + " should not be active successfully!");
		}
		return errMsg;
	}
}
