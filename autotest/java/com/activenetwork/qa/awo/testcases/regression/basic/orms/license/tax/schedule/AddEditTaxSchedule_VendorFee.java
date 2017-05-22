package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.tax.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a tax schedule for vendor fee
 * @LinkSetUp:  d_fin_tax_type:id=300(TAXNAME='TaxForActivity')
 * 				d_hf_add_activity:id=330(ACTIVITY_NAME='Activity For Tax Schedule 05')
 * 				d_hf_add_activity:id=340(ACTIVITY_NAME='Activity For Tax Schedule 06')
 * @SPEC:[TC:110570] Add Tax Shcedule for Vendor Fee  
 *       [TC:110573]Edit Tax Shcedule for Vendor Fee 
 * @Task#: Auto-2137
 * @author Phoebe
 * @Date  May 19, 2014
 */
public class AddEditTaxSchedule_VendorFee extends LicenseManagerTestCase{
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
	  	schedule.feeType = "Vendor Fee"; //This is the test point, can not be changed
	  	schedule.productGroup = "FeeAddEditGroup";
	 	schedule.product = "Activity For Tax Schedule 05";
	 	schedule.effectiveDate = DateFunctions.getDateAfterToday(2);
	 	schedule.effectiveEndDate = DateFunctions.getDateAfterToday(3);	
	  	schedule.accountCode = "3461.3010.20000.6200.---.---.--.----.----.--------; Voucher Account";
	  	schedule.rate = "0.12";
	  	
	  	scheduleEdit.taxName = "TaxForActivity";
	  	scheduleEdit.productCategory = "Activity"; //This is the test point, can not be changed
	  	scheduleEdit.feeType = "Vendor Fee"; //This is the test point, can not be changed
	  	scheduleEdit.productGroup = "FeeUpdateGroup";
	  	scheduleEdit.product = "Activity For Tax Schedule 06";
	  	scheduleEdit.effectiveDate = DateFunctions.getDateAfterToday(3);
	  	scheduleEdit.effectiveEndDate = DateFunctions.getDateAfterToday(4);	  	
	  	scheduleEdit.accountCode = "3461.3010.20000.6100.---.---.--.----.----.--------; Accounts Payable - RA Fees";
	  	scheduleEdit.rate = "0.13";
	}

}
