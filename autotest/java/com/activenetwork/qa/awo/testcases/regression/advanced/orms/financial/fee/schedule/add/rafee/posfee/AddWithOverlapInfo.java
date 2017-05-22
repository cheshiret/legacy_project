package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.posfee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to add ra fee for pos with duplicate information
 * @LinkSetUp:  d_inv_add_mpos:id=610(PRODUCT='POS_For_Fee_3')
 * 			    d_inv_add_mpos:id=650(PRODUCT='POS_For_Fee_7')
 * @SPEC:[TC:109350] Add RA Fee Schedule -- User selects POS as Product Category and POS Sale as the Product Sub Category 
 * @Task#: Auto-2121
 * @author Phoebe Chen
 * @Date  April 28, 2014
 */
public class AddWithOverlapInfo extends FinanceManagerTestCase {
	private RaFeeScheduleData schedule_orignal = new RaFeeScheduleData();
	private RaFeeScheduleData schedule_new = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage schMainPg = FinMgrRaFeeSchMainPage.getInstance();
	private String scheduleId_1, scheduleId_2, scheduleId_3, scheduleId_4, scheduleId_5, scheduleId_6;
	private String expMsg = "";
	@Override
	public void execute() {
		//login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    fnm.gotoRaFeeSchedulePage();
	    
	   //Clear up data:deactive all the pos fee schedule for the two product
	    schMainPg.deactiveAllTheFeeForProduct(schedule_orignal.product);
	    schMainPg.deactiveAllTheFeeForProduct("POS_For_Fee_7");
	    
	    //add new RA Fee Schedule
	    schedule_orignal.feeId =  fnm.addNewRaFeeSchedule(schedule_orignal);
	    schMainPg.activeRaFeeSchedules(schedule_orignal.feeId);
	    
	    //Add a ra fee schedule with the same information as schedule_orginal, and just license year is different, the schedule can be active successfully
	    this.setNewScheduleInfoTheSameAsOrignal();
	    schedule_new.licenseYearFrom=String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
	    schedule_new.licenseYearTo=String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
	    scheduleId_1 = fnm.addNewRaFeeSchedule(schedule_new);
	    activeSchedule(scheduleId_1, true);
	    
	    //Add a ra fee schedule with the same information as schedule_orginal, and just sale channel is different, the schedule can be active successfully
	    this.setNewScheduleInfoTheSameAsOrignal();
	    schedule_new.salesChannel = "Web";
	    scheduleId_2 = fnm.addNewRaFeeSchedule(schedule_new);
	    activeSchedule(scheduleId_2, true);
	    
	    //Add a ra fee schedule with the same information as schedule_orginal, and just product is different, the schedule can be active successfully
	    this.setNewScheduleInfoTheSameAsOrignal();
	    schedule_new.productGroup = "Admissions";
	    schedule_new.product = "POS_For_Fee_7";
	    scheduleId_3 = fnm.addNewRaFeeSchedule(schedule_new);
	    activeSchedule(scheduleId_3, true);
	    
	    //Add a ra fee schedule with the same information as schedule_orginal, and just effective date is different, the schedule can be active successfully
	    this.setNewScheduleInfoTheSameAsOrignal();
	    schedule_new.effectDate = DateFunctions.getDateAfterGivenDay(schedule_orignal.effectDate, 1);
	    scheduleId_4 = fnm.addNewRaFeeSchedule(schedule_new);
	    activeSchedule(scheduleId_4, true);
	    
	    //Add a ra fee schedule with the same information as schedule_orginal, and just transaction type is different, the schedule can be active successfully
	    this.setNewScheduleInfoTheSameAsOrignal();
	    schedule_new.tranType = "Purchase Consumable";
	    scheduleId_5 = fnm.addNewRaFeeSchedule(schedule_new);
	    activeSchedule(scheduleId_5, true);
	    
	    //Add a duplicate fee schedule
	    this.setNewScheduleInfoTheSameAsOrignal();
	    scheduleId_6 = fnm.addNewRaFeeSchedule(schedule_new);
	    expMsg = "The RA Fee Schedule " + scheduleId_6 + " cannot be activated because another similar active RA Fee Schedule " + schedule_new.feeId+ " already exists with overlapping " + schedule_orignal.licenseYearFrom + 
	    		" From and To entries. Please inactivate that record first or modify the " + schedule_orignal.licenseYearFrom +" Range of that record first to eliminate the overlap.";
	    String actMsg = activeSchedule(scheduleId_6, false);
	    verifyErrorMsg(actMsg);
	    
	    //logout finance manager
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
	  	
	    //initialize schedule data
	  	schedule_orignal.locationCategory = "Park";
	  	schedule_orignal.location = fnm.getFacilityName("10137", schema); //CHARLES TOWNE LANDING
	  	schedule_orignal.productCategory="POS";
	  	schedule_orignal.productSubCategory="POS Sale";
	  	schedule_orignal.productGroup = "RC SALE OF SERVICES";
	  	schedule_orignal.product = "POS_For_Fee_3";
	  	schedule_orignal.effectDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-2), "EEE MMM d yyyy");
	  	schedule_orignal.salesChannel ="CallCenter";
	  	schedule_orignal.licenseYearFrom=String.valueOf(DateFunctions.getCurrentYear());
	  	schedule_orignal.licenseYearTo=String.valueOf(DateFunctions.getCurrentYear());
	  	schedule_orignal.locationClass="All";
	  	schedule_orignal.tranType="Add POS item";
	  	schedule_orignal.acctCode="0799.4035.7255.01; RA Transaction Fee - Campsite";
	  	schedule_orignal.baseRate="13.33";
	  	schedule_orignal.purchaseType= "Original";
	  	
	  	this.setNewScheduleInfoTheSameAsOrignal();
	}
	
	public void setNewScheduleInfoTheSameAsOrignal(){
		schedule_new.locationCategory = schedule_orignal.locationCategory;
		schedule_new.location = schedule_orignal.location;
		schedule_new.productCategory = schedule_orignal.productCategory;
		schedule_new.productSubCategory = schedule_orignal.productSubCategory;
	  	schedule_new.productGroup = schedule_orignal.productGroup;
	  	schedule_new.product = schedule_orignal.product;
	  	schedule_new.effectDate = schedule_orignal.effectDate;
	  	schedule_new.salesChannel = schedule_orignal.salesChannel;
	  	schedule_new.licenseYearFrom = schedule_orignal.licenseYearFrom;
	  	schedule_new.licenseYearTo = schedule_orignal.licenseYearTo;
	  	schedule_new.locationClass = schedule_orignal.locationClass;
	  	schedule_new.tranType = schedule_orignal.tranType;
	  	schedule_new.acctCode = schedule_orignal.acctCode;
	  	schedule_new.baseRate = schedule_orignal.baseRate;
	  	schedule_new.purchaseType = schedule_orignal.purchaseType;
	}
	
	public String activeSchedule(String feeSchdId, boolean noErr) {
		String errMsg = "";
		schMainPg.searchByFeeScheduleId(feeSchdId);
		schMainPg.selectFirstScheduleCheckBox();
		schMainPg.clickActivate();
		ajax.waitLoading();
		schMainPg.waitLoading();
		errMsg = schMainPg.getErrorMsg();
		if(noErr&&StringUtil.notEmpty(errMsg)){
			throw new ErrorOnPageException("There should no error to active the fee scheuld with id:" + feeSchdId);
		}
		if(!noErr&&StringUtil.isEmpty(errMsg)){
			throw new ErrorOnPageException("The fee scheuld with id:" + feeSchdId + " should not be active successfully!");
		}
		return errMsg;
	}
}
