package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.tax.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a tax schedule for transaction fee
 * @LinkSetUp:  d_fin_tax_type:id=300(TAXNAME='TaxForActivity')
 * 				d_hf_add_activity:id=310(ACTIVITY_NAME='Activity For Tax Schedule 03')
 * 				d_hf_add_activity:id=320(ACTIVITY_NAME='Activity For Tax Schedule 04')
 * @SPEC:[TC:110569] Add Tax Shcedule for Transaction Fee  
 *       [TC:110572] Edit Tax Shcedule for Transaction Fee 
 * @Task#: Auto-2137
 * @author Phoebe
 * @Date  May 19, 2014
 */
public class AddEditTaxSchedule_TransactionFee extends LicenseManagerTestCase{
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
	  	schedule.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	schedule.productGroup = "FeeAddEditGroup";
	 	schedule.product = "Activity For Tax Schedule 03";
	 	schedule.effectiveDate = DateFunctions.getDateAfterToday(0);
	 	schedule.effectiveEndDate = DateFunctions.getDateAfterToday(1);	
	  	schedule.tranType = "Register for Activity";
	  	schedule.accountCode = "3461.3010.41540.7000.---.---.--.----.----.--------; Sales Tax";
	  	schedule.rate = "3.24";
	  	
	  	scheduleEdit.taxName = "TaxForActivity";
	  	scheduleEdit.productCategory = "Activity"; //This is the test point, can not be changed
	  	scheduleEdit.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	scheduleEdit.productGroup = "FeeUpdateGroup";
	  	scheduleEdit.product = "Activity For Tax Schedule 04";
	  	scheduleEdit.effectiveDate = DateFunctions.getDateAfterToday(-1);
	  	scheduleEdit.effectiveEndDate = DateFunctions.getDateAfterToday(1);	
	  	scheduleEdit.tranType = "Register for Activity";
	  	scheduleEdit.accountCode = "3461.3010.20000.6000.---.---.--.----.----.--------; Default Refund Account";
	  	scheduleEdit.rate = "1.11";
	}

}

