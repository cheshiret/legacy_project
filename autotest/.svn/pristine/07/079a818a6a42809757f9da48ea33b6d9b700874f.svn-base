package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.tax.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a tax schedule for activity fee
 * @LinkSetUp:  d_assign_feature:id=6142(FEATURE='%Taxes%')
 * 				d_fin_tax_type:id=300(TAXNAME='TaxForActivity')
 * 				d_hf_add_activity:id=290(ACTIVITY_NAME='Activity For Tax Schedule 01')
 * 				d_hf_add_activity:id=300(ACTIVITY_NAME='Activity For Tax Schedule 02')
 * @SPEC:[TC:110568] Add Tax Shcedule for Activity Fee 
 *       [TC:110571] Edit Tax Shcedule for Activity Fee 
 * @Task#: Auto-2137
 * @author Phoebe
 * @Date  May 19, 2014
 */
public class AddEditTaxSchedule_ActivityFee extends LicenseManagerTestCase{
	private ScheduleData schedule = new ScheduleData();
	private ScheduleData scheduleEdit = new ScheduleData();
	private LicMgrTaxScheduleDetailPage detailPg = LicMgrTaxScheduleDetailPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoTaxScheduelListPage();
		
		schedule.scheduleId = lm.addNewTaxSchedule(schedule);
		lm.gotoTaxScheduleDetailPage(schedule.scheduleId);
		detailPg.verifyScheduleInfo(schedule);
		
		scheduleEdit.scheduleId = lm.editTaxSchedule(scheduleEdit);
		lm.gotoTaxScheduleDetailPage(scheduleEdit.scheduleId);
		detailPg.verifyScheduleInfo(scheduleEdit);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		schedule.taxName = "TaxForActivity";
	 	schedule.productCategory = "Activity"; //This is the test point, can not be changed
	  	schedule.feeType = "Activity Fee"; //This is the test point, can not be changed
	  	schedule.productGroup = "FeeAddEditGroup";
	 	schedule.product = "Activity For Tax Schedule 01";
	 	schedule.effectiveDate = DateFunctions.getDateAfterToday(0);
	 	schedule.effectiveEndDate = DateFunctions.getDateAfterToday(1);	
	  	schedule.accountCode = "3461.3010.20000.6100.---.---.--.----.----.--------; Accounts Payable - RA Fees";
	  	schedule.rate = "99.78";
	  	
	  	scheduleEdit.taxName = "TaxForActivity";
	  	scheduleEdit.productCategory = "Activity"; //This is the test point, can not be changed
	  	scheduleEdit.feeType = "Activity Fee"; //This is the test point, can not be changed
	  	scheduleEdit.productGroup = "FeeUpdateGroup";
	  	scheduleEdit.product = "Activity For Tax Schedule 02";
	  	scheduleEdit.effectiveDate = DateFunctions.getDateAfterToday(0);
	  	scheduleEdit.effectiveEndDate = DateFunctions.getDateAfterToday(2);
	  	scheduleEdit.accountCode = "3461.3010.41540.7100.---.---.--.----.----.--------; Tourism tax";
	  	scheduleEdit.rate = "1.78";
	}

}
