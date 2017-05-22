package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.discount.shedule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a discount schedule for pos, and discount with percent discount rate type 
 * @LinkSetUp:  d_inv_add_mpos:id=800(PRODUCT='POS_For_Disc_5')
 * 			    d_inv_add_mpos:id=810(PRODUCT='POS_For_Disc_6')
 * 				d_fin_add_discount:id=1530(DISCOUNTNAME='Discount_For_POS_CustomerPass_Percent')
 * @SPEC:[TC:122328] Add Discount Schedule - POS Fee - Customer Pass 
 * 		 [TC:122341] Edit Discount Schedule - POS Fee - Customer Pass 
 * @Task#: Auto-2179
 * @author Phoebe
 * @Date  May 14, 2014
 */
public class PosFee extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private ScheduleData duplicateSchedule = new ScheduleData();
	private DiscountData discount = new DiscountData();
	private FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage.getInstance();
	private FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage.getInstance();
	private String expErrMsg = "";
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
	    //Prepare a active discount schedule
	    fnm.gotoDiscountPage();
	    fnm.gotoDiscountSchedulePg();
	    
	    //Clear data
	    schedulePg.deactiveAllDiscountForProduct(schedule.product);
	    schedulePg.deactiveAllDiscountForProduct(duplicateSchedule.product);
	    
	    fnm.selectLocatinForAddDiscountSchedule(schedule.location, schedule.locationCategory, false);
	    verifyCustomerPass();
		schedule.scheduleId = this.addScheduleFromDetailPg(schedule);
		schedulePg.changeDiscountStatus(schedule.scheduleId, "Active");
		
		duplicateSchedule.scheduleId = fnm.addNewDiscountSchedule(duplicateSchedule, false);
		schedulePg.changeDiscountStatus(duplicateSchedule.scheduleId, "Active");
		expErrMsg = "Discount schedule\\(" +duplicateSchedule.scheduleId+ "\\) has conflict with another active schedule\\(" + schedule.scheduleId +  "\\).";
		
	    //Update the discount schedule same to the first one
	  	schedulePg.searchByScheduleId(duplicateSchedule.scheduleId);
	  	fnm.gotoDiscountScheduleDetailPg(duplicateSchedule.scheduleId);
	  	String errMsg = this.updateScheduleAndClickOk(schedule);
	  	
	  	if(!errMsg.matches(expErrMsg)){
	  		throw new ErrorOnPageException("The error message for duplicate infor is not correct", expErrMsg, errMsg);
	  	}
	  	
	  	fnm.logoutFinanceManager();
	}
	
	private void verifyCustomerPass() {
		List<String> actCustPassType = detailPg.getCustomerPassElement();
		List<String> dbCustPassType = fnm.getCustPassTypeInDB(schema);
		dbCustPassType.add(StringUtil.EMPTY);
		if (actCustPassType.containsAll(dbCustPassType)&&dbCustPassType.containsAll(actCustPassType)) {
			throw new ErrorOnPageException("Product group is not correct", dbCustPassType.toString(),	actCustPassType.toString());
		}
		logger.info("Aviable product group element is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "SC Contract";
	  	login.location = "Administrator/South Carolina State Park Service";
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		
		String facility = fnm.getFacilityName("10137", schema); //CHARLES TOWNE LANDING
		
		//initialize Discount info
	  	discount.name = "Discount_For_POS_CustomerPass_Percent"; //The discount should be added by set up script
	  	discount.description = "This is a percent discount for add edit pos discount with customer pass";
	  	discount.rateType = "Percent";
	  	discount.feeTypes.add("POS Fee");
	  	discount.behaviors.add("Additional Discount");
	  	discount.behaviors.add("Auotomatic Discount");
	  	discount.rateUnit = "Per Stay";
		
	  	//initialize Schedule data
	  	schedule.location = facility;
	  	schedule.locationCategory = "Park";
	  	schedule.discountName = discount.name;
	  	schedule.feeType = "POS Fee";
	  	schedule.productCategory = "POS"; //Do no change
	  	
	  	schedule.product = "POS_For_Disc_6";
	  	schedule.productGroup = "RC SALE OF SERVICES";
		
	  	schedule.effectiveDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
	  	schedule.effectiveEndDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(3), "EEE MMM d yyyy");
	  	schedule.purchaseType = "Original";
		schedule.rate = "5.01";
	  	schedule.salesChannel = "Field";
	  	schedule.customerType = "All";
	  	schedule.custPass = "All";
	  	schedule.accountCode = "0799.3035.4848.63; Day Use - Meeting Room";
	  	
	  	//Set up information for update pos discount schedule
	  	duplicateSchedule.location = facility;
	  	duplicateSchedule.locationCategory = "Park";
	  	duplicateSchedule.discountName = discount.name;
	  	duplicateSchedule.feeType = "POS Fee";
	  	duplicateSchedule.productCategory = "POS"; //Do no change
	  	duplicateSchedule.product = "POS_For_Disc_5";
	  	duplicateSchedule.productGroup = "Admissions";
	  	duplicateSchedule.effectiveDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-2), "EEE MMM d yyyy");
	  	duplicateSchedule.effectiveEndDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");
	  	duplicateSchedule.purchaseType = "Replacement";
	  	duplicateSchedule.rate = "6.23";
	  	duplicateSchedule.salesChannel = "Call Center";
	  	duplicateSchedule.customerType = "Standard";
	  	duplicateSchedule.custPass = "Customer Membership Program";
	  	duplicateSchedule.accountCode = "0799.3035.4828.--; POS - Service - Golf";
	}
	
	private String updateScheduleAndClickOk(ScheduleData schedule) {
		 detailPg.updateDiscountScheduleInfo(schedule);
		 detailPg.clickOK();
		 ajax.waitLoading();
		 detailPg.waitLoading();
		 String err = detailPg.getErrorMessage();
		 return err;
	}
	
	private String addScheduleFromDetailPg(ScheduleData schedule) {
		 String id = detailPg.setupDiscountSchedule(schedule);
		 ajax.waitLoading();
		 schedulePg.waitLoading();
		 return id;
	}

}
