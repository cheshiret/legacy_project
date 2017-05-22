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
 * @Description:This case is used to verify add and edit a transaction fee schedule for activity
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=20,80
 * @SPEC:[TC:109718] Add Transaction Fee Schedule for Activity Product 
 * @Task#: Auto-2135
 * @author Phoebe Chen
 * @Date  April 9, 2014
 */
public class AddEditTransactionFeeScheduleForActivityPrd extends LicenseManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private FeeScheduleData editSchedule = new FeeScheduleData();
	private LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoFeeScheduleListPageFromTopMenu();
		
		//Clear up old data
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
		schedule.feeType = "Transaction Fee";  //Do not change, this is the check point
		
		schedule.productGroup = "FeeAddEditGroup";
		schedule.product = "Activity fee 02";
		schedule.effectDate = DateFunctions.getDateAfterToday(1);
		schedule.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.toLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.salesChannel = "Web";
		schedule.locationClass = "WMA Agent";
		schedule.applicableTaxes.add("Sales Tax");
		schedule.applicableTaxes.add("Restaurant 7%");
		schedule.tranType = "Register for Activity";
		schedule.applyRate = "Per Unit";
		schedule.rateAmount = "4.44";
		schedule.stateTransFee = "3.00";
		schedule.splitRateBy = "Amount";
		schedule.splitIntoNum = "3";
		schedule.accounts.add("3450.2050.41502.0044.130.158.AD.W799.----.--------; NR Saltwater Fishing - 100%");
		schedule.accounts.add("3450.2050.41502.0046.130.168.AD.W799.----.--------; Exempt NR 3 Day Saltwater Fish - 100%");
		schedule.accounts.add("3450.2050.41502.0047.130.169.AD.W799.----.--------; Exempt NR Saltwater Fishing - 100%");
		schedule.percentOrAmountForEachAccount.add("1.12");
		schedule.percentOrAmountForEachAccount.add("2.78");
		schedule.percentOrAmountForEachAccount.add("0.54");
		
		//Set up edit fee schedule information
		editSchedule.productCategory = schedule.productCategory;
		editSchedule.feeType = schedule.feeType;
		editSchedule.productGroup = "FeeUpdateGroup";
		editSchedule.product = "updateActivity02";
		editSchedule.effectDate = DateFunctions.getDateAfterToday(1);
		editSchedule.fromLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(1));
		editSchedule.toLicenseYear = String.valueOf(DateFunctions.getYearAfterCurrentYear(3));
		editSchedule.salesChannel = "CallCenter";
		editSchedule.locationClass = "MDWFP Internet";
		editSchedule.applicableTaxes.add("Sales Tax");
		editSchedule.applicableTaxes.add("Restaurant 7%");
		editSchedule.applicableTaxes.add("Group Meals 7%");
		editSchedule.tranType = "Register for Activity";
		editSchedule.applyRate = "Per Unit";
		editSchedule.rateAmount = "1.12";
		editSchedule.stateTransFee = "3.00";
		editSchedule.splitRateBy = "Amount";
		editSchedule.splitIntoNum = "3";
		editSchedule.accounts.add("3460.1020.41506.0022.170.554.A1.W998.F020.--------; Education Grps Entrance Per Bus Drivers and Chaperons - 100%");
		editSchedule.accounts.add("3460.1020.41506.0021.170.553.A1.W998.F020.--------; Children Under 3 General Entrance - 100%");
		editSchedule.accounts.add("3450.2050.41502.0047.130.169.AD.W799.----.--------; Exempt NR Saltwater Fishing - 100%");
		editSchedule.percentOrAmountForEachAccount.add("0.31");
		editSchedule.percentOrAmountForEachAccount.add("0.22");
		editSchedule.percentOrAmountForEachAccount.add("0.59");
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
