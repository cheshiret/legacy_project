package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.discount.shedule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add a discount schedule for discount:
 *               applies to Use Fee and the Rate Type is Flat; Display Discount is unchecked, others are checked and Applies to Per Stay
 * @LinkSetUp:  d_inv_add_dock_area:id=1310(DOCKAREANAME='DockForAddEditDiscountSchedule')
 * 				d_inv_add_slip:id=13960(SLIPCODE='SFAEDS01')
 * 				d_fin_add_discount:id=1300(DISCOUNTNAME='AddSchedule_UseFee_Flat_PerStay_UD')
 * @SPEC:[TC:049998] Add Discount Schedule_Use/Attribute Fee Per Stay_Flow 
 * @Task#: Auto-1949
 * @author Phoebe
 * @Date  Dec 23, 2013
 */
public class UseFee_Flat_PerStay_UD extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private DiscountData discount = new DiscountData();
	private FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage.getInstance();
	private FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
			.getInstance();
	private FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage.getInstance();
	@Override
	public void execute() {
		 //login finance manager, goto discount main page and goto discount schedule list page
	    fnm.loginFinanceManager(login);
	    fnm.gotoDiscountPage();
	    discountPg.gotoDiscountSchPg();
	    
	    schedulePg.searchByDiscountName(schedule.discountName);
	    fnm.selectLocatinForAddDiscountSchedule(schedule.location, schedule.locationCategory, false);
	    //Check dicount details after select discount and fee type
	    detailPg.setupDiscountNameFeeTypeAndProductCategory(schedule);
	    detailPg.verifyDiscountDetailFildNotEditable(schedule.feeType, discount.rateType);
	    detailPg.verifyDiscountDeatilsField(discount.rateType, discount.rateUnit, discount.behaviors);
	    detailPg.verifyFieldDisplayed(discount.rateType, schedule.feeType);
	    
	    //Set up the other information for the new discount, and click 'Cancel', it will go to Discount Schedule List page
	    this.setUpInfoThenClickCancel();
	    verifySearchCriticalRemaining("Discount Name", schedule.discountName, schedule.discountName);
	    
	    this.setUpInfoThenClickApply();
	    detailPg.verifyFieldEditable(discount.rateType, discount.rateUnit,  schedule.feeType);
	    detailPg.verifyScheduleDetail(schedule);
	    
	    fnm.gotoHomePage();
	    
	    fnm.logoutFinanceManager();
	}

	private void setUpInfoThenClickApply() {
		fnm.selectLocatinForAddDiscountSchedule(schedule.location, schedule.locationCategory, false);
		detailPg.setUpDiscountScheduleInfoAndClickApply(schedule);
		schedule.scheduleId = detailPg.getScheduleId();
		if(!schedule.scheduleId.matches("\\d+")){
			throw new ErrorOnPageException("The schedule is not created corrtly!");
		}
		logger.info("Schedule:" + schedule.scheduleId + " is corrected!");
	}

	private void setUpInfoThenClickCancel() {
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		detailPg.setupOtherFields(schedule);
		detailPg.clickCancel();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}
	
	 public void verifySearchCriticalRemaining(String searchType, String searchValue, String discountName){
		 boolean passed = true;
		 passed &= MiscFunctions.compareResult("Search type:", searchType, schedulePg.getSearchType());
		 passed &= MiscFunctions.compareResult("Search value:", searchValue, schedulePg.getSearchValue());
		 List<ScheduleData> record = schedulePg.getAllRecordOnPage();
		 for(int i=0; i<record.size(); i++){
			 passed &= MiscFunctions.compareResult(i+"th discount name:", discountName , record.get(i).discountName);
		 }
		 if(!passed){
			 throw new ErrorOnPageException("The search critical is not maintaining, please check the log above");
		 }
		 logger.info("Search critical is maintaining as previous set, it is correct");
	 }

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		String facility = fnm.getFacilityName("552903", schema);
		
		//initialize Discount info
	  	discount.name = "AddSchedule_UseFee_Flat_PerStay_UD"; //The discount should be added by set up script
	  	discount.rateType = "Flat";
	  	discount.behaviors.add("Additional Discount");
	  	discount.behaviors.add("Automatic Discount");
	  	discount.rateUnit = "Per Stay";
		
	  	//initialize Schedule data
	  	schedule.location = facility;
	  	schedule.locationCategory = "Park";
	  	schedule.discountName = discount.name;
	  	schedule.feeType = "Use Fee";
	  	schedule.productCategory = "Slip";
	  	schedule.loop = "DockForAddEditDiscountSchedule";
	  	schedule.product = "Slip for add edited discount schedule 01";
	  	schedule.productGroup = "Full attributes";
		schedule.effectiveDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.startDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
	  	schedule.endDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(3), "EEE MMM d yyyy");
	  	schedule.marinaRateType = "Transient";
		schedule.rate = "5.0";
	  	schedule.season = "All";
	  	schedule.salesChannel = "Field";
	  	schedule.state = "All";
	  	schedule.customerType = "All";
	  	schedule.custPass = "All";
	  	schedule.member = "All";
	  	schedule.boatCategory = "All";
	  	schedule.accountCode = "3000.1601.000200000; Default Overage/Shortage";
	  	
	}
}
