package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.feeschedule;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrSelectFeeTypePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify add and edit a vendor fee schedule for activity
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=30,90
 * @SPEC:[TC:109715] Add Vendor Fee Schedule for Activity Product 
 * @Task#: Auto-2135
 * @author Phoebe Chen
 * @Date  April 9, 2014
 */
public class AddEditVendorFeeScheduleForActivityPrd extends LicenseManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private FeeScheduleData editSchedule = new FeeScheduleData();
	private LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoFeeScheduleListPageFromTopMenu();
		
		feeScheduleListPg.deactiveSchedulesForPrd(schedule.product, editSchedule.product);
		
		this.setUpAndClickCancel();
		
		schedule.feeSchdId = lm.addNewFeeSchedule(schedule);
		
		//Verify the new added fee schedule is correct!
		lm.gotoFeeScheduleDetailPgFromListPg(schedule.feeSchdId);
		verifyScheduleDetailInfo(schedule);
		
		editSchedule.feeSchdId = lm.editFeeSchedule(schedule.feeSchdId, editSchedule);
		
		//Verify edit fee schedule is correct!
		lm.gotoFeeScheduleDetailPgFromListPg(schedule.feeSchdId);
		verifyScheduleDetailInfo(editSchedule);
		
		lm.logOutLicenseManager();
	}

	private void verifyScheduleDetailInfo(FeeScheduleData schedule) {
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		if(!detailPg.compareScheduleInfo(schedule)){
			throw new ErrorOnPageException("Fee Schedule info is not correct, please check the log above!");
		}
		logger.info("New added fee scheduel information is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//Set up fee schedule information
		schedule.productCategory = "Activity";
		schedule.feeType = "Vendor Fee";  //Do not change, this is the check point
		
		schedule.productGroup = "FeeAddEditGroup";
		schedule.product = "Activity fee 03";
		schedule.effectStartDate = DateFunctions.getDateAfterToday(1);
		schedule.effectEndDate = DateFunctions.getDateAfterToday(3);
		schedule.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		schedule.salesChannel = "FieldMgr";
		schedule.locationClass = "MDWFP District Office";
		schedule.rateAmount = "5.78";
		schedule.stateVendorFee = "1.23";
		schedule.acctCode = "3450.2050.41502.0045.130.159.AD.W799.----.--------; NR 3 Day Saltwater Fishing - 100%";
		
		editSchedule.productCategory = schedule.productCategory;
		editSchedule.feeType = schedule.feeType;
		editSchedule.productGroup = "FeeUpdateGroup";
		editSchedule.product = "updateActivity03";
		editSchedule.effectStartDate = DateFunctions.getDateAfterToday(4);
		editSchedule.effectEndDate = DateFunctions.getDateAfterToday(7);
		editSchedule.fromLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		editSchedule.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		editSchedule.salesChannel = "Web";
		editSchedule.locationClass = "Dept of Marine Resources";
		editSchedule.rateAmount = "7.18";
		editSchedule.stateVendorFee = "1.03";
		editSchedule.acctCode = "3460.1020.41506.0019.170.551.A1.W998.F020.--------; Senior General Entrance - 100%";
		
	}
	
	private void setUpAndClickCancel(){
		LicMgrSelectFeeTypePage selectFeeTypePg = LicMgrSelectFeeTypePage.getInstance();
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		feeScheduleListPg.clickAddNew();
		ajax.waitLoading();
		selectFeeTypePg.waitLoading();
		selectFeeTypePg.setUpProductCatAndFeeType(schedule.productCategory, schedule.feeType);
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.setUpFeeSchesuleInfoAndClickCancel(schedule);
		//It will go back to search page
		ajax.waitLoading();
		feeScheduleListPg.waitLoading();
	}
}
