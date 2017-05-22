package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.feeschedule;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify edit an active vendor fee schedule which has been used for activity, a new schedule will be corrected,
 *  the original schedule will be inactive and the new created one will be active
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=60
 * @SPEC:[TC:110011] Edit Vendor Fee Schedule for Activity Product 
 * @Task#: Auto-2135
 * @author Phoebe Chen
 * @Date  April 23, 2014
 */
public class EditActiveUsedVendorFeeScheduleForActivityPrd  extends LicenseManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private FeeScheduleData editSchedule = new FeeScheduleData();
	private FeeScheduleData schedule_Activity = new FeeScheduleData();
	private String salesLocation, adminLocation;
	private Data<ActivityAttr> activity = new Data<ActivityAttr>() ;
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	private LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoFeeScheduleListPageFromTopMenu();
		
		//Clear data
		feeScheduleListPg.deactiveSchedulesForPrd(schedule.product, editSchedule.product);
		//Add a new fee schedule for the activity product
		schedule_Activity.feeSchdId = lm.addNewFeeSchedule(schedule_Activity);
		schedule.feeSchdId = lm.addNewFeeSchedule(schedule);
		feeScheduleListPg.activeFeeSchedule(schedule_Activity.feeSchdId, schedule.feeSchdId);
		
		this.makeActivityOrderWithSchedule();
		
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoFeeScheduleListPageFromTopMenu();
		editSchedule.feeSchdId = lm.editFeeSchedule(schedule.feeSchdId, editSchedule);
		
		//Verify fee schedule is new added and the status is correct
		this.verifyScheduleIsNew();
		//The original fee schedule should be inactive
		feeScheduleListPg.verifyScheduleStatus(schedule.feeSchdId, INACTIVE_STATUS);
		//The new created schedule should be active
		feeScheduleListPg.verifyScheduleStatus(editSchedule.feeSchdId, ACTIVE_STATUS);
		
		//Verify edit fee schedule is correct!
		lm.gotoFeeScheduleDetailPgFromListPg(editSchedule.feeSchdId);
		verifyScheduleDetailInfo();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyScheduleIsNew() {
		if(schedule.feeSchdId.equalsIgnoreCase(editSchedule.feeSchdId)){
			throw new ErrorOnPageException("Fee Schedule is not new created!", schedule.feeSchdId ,editSchedule.feeSchdId);
		}
		logger.info("New fee schedule has been created!");
	}

	private void verifyScheduleDetailInfo() {
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		if(!detailPg.compareScheduleInfo(editSchedule)){
			throw new ErrorOnPageException("Fee Schedule info is not correct, please check the log above!");
		}
		logger.info("New added fee scheduel information is correct!");
	}

	private void makeActivityOrderWithSchedule() {
		//make a activity
		lm.switchLocationInHomePage(salesLocation);
		lm.makeActivityToCartFromCustomerQuickSearch(cust, activity);
		this.gotoFeeDetailPage();
		String scheduleId = feepg.getFeeScheduleIdByFeeType(FEETYPE_NAME_VENDORFEE);
		if(!scheduleId.equalsIgnoreCase(schedule.feeSchdId)){
			throw new ErrorOnPageException("The fee schedule is not used for the activity", schedule.feeSchdId, scheduleId);
		}
		lm.cancelFromFeeDetailsPage();
		lm.processOrderCart(pay);
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
		activity.put(ActivityAttr.activityName, "Activity fee 06");
		activity.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		
		//Set up fee schedule information
		schedule.productCategory = "Activity";
		schedule.feeType = "Vendor Fee";  //Do not change, this is the check point
		
		schedule.productGroup = "FeeAddEditGroup";
		schedule.product =  activity.stringValue(ActivityAttr.activityName);
		schedule.effectStartDate = DateFunctions.getDateAfterToday(-1);//"Apr 01 2023";//DateFunctions.getDateAfterToday(1);
		schedule.effectEndDate = lm.getActivitySessionDateById(schema, activity.stringValue(ActivityAttr.activityID));
		schedule.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());//"2023";//String.valueOf(DateFunctions.getCurrentYear());
		schedule.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(9));//"2023";//String.valueOf(DateFunctions.getCurrentYear());
		schedule.salesChannel = "FieldMgr";
		schedule.locationClass = "All";
		schedule.rateAmount = "5.78";
		schedule.stateVendorFee = "1.23";
		schedule.acctCode = "3450.2050.41502.0045.130.159.AD.W799.----.--------; NR 3 Day Saltwater Fishing - 100%";
		
		editSchedule.productCategory = "Activity";
		editSchedule.feeType = "Vendor Fee";
		editSchedule.productGroup = "FeeAddEditGroup";
		editSchedule.product =  activity.stringValue(ActivityAttr.activityName);
		editSchedule.effectStartDate = DateFunctions.getDateAfterToday(4);
		editSchedule.effectEndDate = DateFunctions.getDateAfterToday(7);
		editSchedule.fromLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		editSchedule.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		editSchedule.salesChannel = "Web";
		editSchedule.locationClass = "Dept of Marine Resources";
		editSchedule.rateAmount = "7.18";
		editSchedule.stateVendorFee = "1.03";
		editSchedule.acctCode = "3460.1020.41506.0019.170.551.A1.W998.F020.--------; Senior General Entrance - 100%";
		
		//Fee information for activity fee schedule
		schedule_Activity.productCategory = "Activity";
		schedule_Activity.feeType = "Activity Fee";
		schedule_Activity.productGroup = schedule.productGroup;
		schedule_Activity.product = schedule.product;
		schedule_Activity.scheduleName = "EditUsedActivityFee";
		schedule_Activity.effectStartDate = schedule.effectStartDate;
		schedule_Activity.effectEndDate = schedule.effectEndDate;
		schedule_Activity.fromLicenseYear = schedule.fromLicenseYear;
		schedule_Activity.toLicenseYear = schedule.toLicenseYear;
		schedule_Activity.salesChannel = schedule.salesChannel;
		schedule_Activity.locationClass = schedule.locationClass;
		schedule_Activity.applyRate = "Per Unit";
		schedule_Activity.rateAmount = "10";
		schedule_Activity.splitRateBy = "Amount";
		schedule_Activity.splitIntoNum = "1";
		schedule_Activity.accounts.add("3450.2050.41502.0044.130.158.AD.W799.----.--------; NR Saltwater Fishing - 100%");
		schedule_Activity.percentOrAmountForEachAccount.add("10.00");
		
		//Cust information
		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.residencyStatus="Non Resident";
	}
	
	private void gotoFeeDetailPage() {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		cartPage.selectOrderCheckBox("New - 1", true);
		cartPage.clickFeesButton();
		ajax.waitLoading();
		feepg.waitLoading();
	}

}
