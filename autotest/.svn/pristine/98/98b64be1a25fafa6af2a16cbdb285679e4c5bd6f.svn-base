package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.tax.schedule;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a tax schedule for transaction fee
 * @LinkSetUp:  d_hf_add_activity:id=360(ACTIVITY_NAME='Activity For Tax Schedule 08')
 * @SPEC:[TC:110572] Edit Tax Shcedule for Transaction Fee 
 * @Task#: Auto-2137
 * @author Phoebe
 * @Date  May 19, 2014
 */
public class EditUsedTaxSchedule_TransactionFee  extends LicenseManagerTestCase{
	private ScheduleData schedule = new ScheduleData();
	private ScheduleData scheduleEdit = new ScheduleData();
	private FeeScheduleData schedule_Activity = new FeeScheduleData();
	private FeeScheduleData schedule_Transaction = new FeeScheduleData();
	private String salesLocation, adminLocation;
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrTaxScheduleDetailPage detailPg = LicMgrTaxScheduleDetailPage.getInstance();
	private LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
	private Data<ActivityAttr> activity = new Data<ActivityAttr>() ;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//Set up an active activity fee first
		this.addAnActiveActivityFeeSchedule();
		//Add a new tax schedule for activity fee
		lm.gotoTaxScheduelListPage();
		
		scheduleListPg.deactiveSchedulesForPrd(schedule.product);
		schedule.scheduleId = lm.addNewTaxSchedule(schedule);
		scheduleListPg.activeTaxSchedules(schedule.scheduleId);
		
		this.makeActivityOrderWithSchedule();
		
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoTaxScheduelListPage();
		
		lm.gotoTaxScheduleDetailPage(schedule.scheduleId);
		scheduleEdit.scheduleId = lm.editTaxSchedule(scheduleEdit);
		//Verify fee schedule is new added and the status is correct
		this.verifyScheduleIsNew();
		//The original fee schedule should be inactive
		scheduleListPg.verifyScheduleStatus(schedule.scheduleId, INACTIVE_STATUS);
		//The new created schedule should be active
		scheduleListPg.verifyScheduleStatus(scheduleEdit.scheduleId, ACTIVE_STATUS);
		
		//Verify the tax schedule is updated correctly
		lm.gotoTaxScheduleDetailPage(scheduleEdit.scheduleId);
		detailPg.verifyScheduleInfo(scheduleEdit);
		
		lm.logOutLicenseManager();
	}
	
	private void verifyScheduleIsNew() {
		if(schedule.scheduleId.equalsIgnoreCase(scheduleEdit.scheduleId)){
			throw new ErrorOnPageException("Tax Schedule is not new created!", schedule.scheduleId ,scheduleEdit.scheduleId);
		}
		logger.info("New tax schedule has been created!");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		// Set the role and location for change role in home page
		adminLocation = "HF Administrator - Auto-" + facilityName;
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		
		//Set up activity info
		activity.put(ActivityAttr.activityName, "Activity For Tax Schedule 08");
		activity.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		
		schedule.taxName = "TaxForActivity";
	 	schedule.productCategory = "Activity"; //This is the test point, can not be changed
	  	schedule.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	schedule.productGroup = "FeeAddEditGroup";
	 	schedule.product = activity.stringValue(ActivityAttr.activityName);
	 	schedule.effectiveDate = DateFunctions.getDateAfterToday(-1);
	 	schedule.effectiveEndDate = lm.getActivitySessionDateById(schema, activity.stringValue(ActivityAttr.activityID));
		schedule.tranType = "All";
	  	schedule.accountCode = "3461.3010.20000.6100.---.---.--.----.----.--------; Accounts Payable - RA Fees";
	  	schedule.rate = "20";
	  	
	  	scheduleEdit.taxName = "TaxForActivity";
	  	scheduleEdit.productCategory = "Activity"; //This is the test point, can not be changed
	  	scheduleEdit.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	scheduleEdit.productGroup = schedule.productGroup;
	  	scheduleEdit.product = schedule.product;
	  	scheduleEdit.effectiveDate = DateFunctions.getDateAfterToday(0);
	  	scheduleEdit.effectiveEndDate = DateFunctions.getDateAfterToday(2);		
		schedule.tranType = "Register for Activity";
	  	scheduleEdit.accountCode = "3461.3010.41540.7100.---.---.--.----.----.--------; Tourism tax";
	  	scheduleEdit.rate = "1.78";
	  	
	    //Fee information for activity fee schedule
  		schedule_Activity.productCategory = "Activity";
  		schedule_Activity.feeType = "Activity Fee";
  		schedule_Activity.productGroup = schedule.productGroup;
  		schedule_Activity.product = schedule.product;
  		schedule_Activity.scheduleName = "EditUsedActivityFee";
  		schedule_Activity.effectStartDate = schedule.effectiveDate;
  		schedule_Activity.effectEndDate = schedule.effectiveEndDate;
  		schedule_Activity.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());;
  		schedule_Activity.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(9));
  		schedule_Activity.salesChannel = schedule.salesChannel;
  		schedule_Activity.locationClass = "All";
  		schedule_Activity.applicableTaxes.add(schedule.taxName);
  		schedule_Activity.applyRate = "Per Unit";
  		schedule_Activity.rateAmount = "10";
  		schedule_Activity.splitRateBy = "Amount";
  		schedule_Activity.splitIntoNum = "1";
  		schedule_Activity.accounts.add("3450.2050.41502.0044.130.158.AD.W799.----.--------; NR Saltwater Fishing - 100%");
  		schedule_Activity.percentOrAmountForEachAccount.add("10.00");
		
		//Set up fee schedule information
  		schedule_Transaction.productCategory = "Activity";
  		schedule_Transaction.feeType = "Transaction Fee";  //Do not change, this is the check point
  		schedule_Transaction.productGroup = schedule.productGroup;
  		schedule_Transaction.product =  schedule.product;
  		schedule_Transaction.effectDate = DateFunctions.getDateAfterToday(-1);
  		schedule_Transaction.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule_Transaction.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(9));
		schedule_Transaction.salesChannel = schedule.salesChannel;
		schedule_Transaction.locationClass = "All";
		schedule_Transaction.applicableTaxes.add(schedule.taxName);
		schedule_Transaction.tranType = "Register for Activity";
		schedule_Transaction.applyRate = "Per Unit";
		schedule_Transaction.rateAmount = "6";
		schedule_Transaction.stateTransFee = "4";
		schedule_Transaction.splitRateBy = "Amount";
		schedule_Transaction.splitIntoNum = "1";
		schedule_Transaction.accounts.add("3450.2050.41502.0044.130.158.AD.W799.----.--------; NR Saltwater Fishing - 100%");
  		
		//Cust information
		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.residencyStatus="Non Resident";
	}
	
	private void makeActivityOrderWithSchedule() {
		//make a activity
		lm.switchLocationInHomePage(salesLocation);
		lm.makeActivityToCartFromCustomerQuickSearch(cust, activity);
		this.gotoFeeDetailPage();
		String scheduleId = feepg.getFeeScheduleIdByFeeType(schedule.taxName);
		if(!scheduleId.equalsIgnoreCase(schedule.scheduleId)){
			throw new ErrorOnPageException("The fee schedule is not used for the activity", schedule.scheduleId, scheduleId);
		}
		lm.cancelFromFeeDetailsPage();
		lm.processOrderCart(pay);
	}

	private void addAnActiveActivityFeeSchedule() {
		LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
		lm.gotoFeeScheduleListPageFromTopMenu();
		//Clear data
		feeScheduleListPg.deactiveSchedulesForPrd(schedule_Activity.product);
		//Add a new fee schedule for the activity product
		schedule_Activity.feeSchdId = lm.addNewFeeSchedule(schedule_Activity);
		schedule_Transaction.feeSchdId = lm.addNewFeeSchedule(schedule_Transaction);
		feeScheduleListPg.activeFeeSchedule(schedule_Activity.feeSchdId, schedule_Transaction.feeSchdId);
		lm.gotoHomePage();
	}
	
	private void gotoFeeDetailPage() {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox("New - 1", true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}

}
