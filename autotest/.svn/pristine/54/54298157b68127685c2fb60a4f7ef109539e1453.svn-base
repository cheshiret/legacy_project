package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.tax.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxAssignLocSearchPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is to check the cancel operation when adding a tax schedule for slip product:
 *               1.Make sure the tax which will be assigned the schedule to is already exist
 *               2.Click cancel button in location selection page.
 *               3.Click cancel button in adding Tax Schedule page before set up information.
 *               4.Click cancel button in adding Tax Schedule page after set up information.
 * @Preconditions:
 * @SPEC: TC:049887
 * @Task#: Auto-1434
 * @author Phoebe Chen
 * @Date  Jan 08, 2013
 */
public class Cancel_Add_Slip extends FinanceManagerTestCase{
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	FinMgrTaxAssignLocSearchPage assignLocSrchPg = FinMgrTaxAssignLocSearchPage
			.getInstance();
	FinMgrTaxSchDetailPage schDetailsPg = FinMgrTaxSchDetailPage
			.getInstance();
	FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
	    fnm.gotoTaxMainPage();
	    
	    //Check data prepare and clean the environment   
	    fnm.addNewTax(tax);
		
	    fnm.gotoTaxSchedulePage();
	    
	    //Click cancel button in location selection page.
	    cancelFromLocationSearchPage();
	    
	    //Click cancel button in adding Tax Schedule page before set up information.
	    cancelFromTaxScheduleDetailsPage(false);
	    
	    //Click cancel button in adding Tax Schedule page after set up information.
	    cancelFromTaxScheduleDetailsPage(true);
	    
	    this.verifyScheduleIsNotAdded();
	    
	    fnm.logoutFinanceManager();
	}

	private void verifyScheduleIsNotAdded() {
		taxSchPg.searchSchdByTaxName(tax.taxName);
		if(taxSchPg.getTaxSchSearchResults().size() != 0){
			throw new ErrorOnPageException("A new schedule has been added for tax(" + tax.taxName + "),after click cancel!");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NC Contract";
	  	login.location = "Administrator - Auto/North Carolina State Parks";
	  	
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
	  	
	  	//initialize Tax info
	  	tax.taxName = "cancel_slip" + DateFunctions.getCurrentTime();
	  	tax.taxCode = "CancelSlip";
	  	tax.taxDescription = "add tax schedule Slip and cancel";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Use Fee");
	  	
	  	schedule.location = fnm.getFacilityName("552861", schema);//"South Mountains State Park";
	 	schedule.locationCategory = "Park";
	 	
	 	schedule.taxName = tax.taxName;
	 	schedule.productCategory = "Slip"; //This is the test point, can not be changed
	  	schedule.feeType = "Use Fee"; 
	  	schedule.startDate = "2009-12-1";
	  	schedule.endDate = "2009-12-31";
	  	schedule.marinaRateType = "Lease";
	  	schedule.customerType = "Standard";
	  	schedule.accountCode = "2000.1601.000300000; Default Overpayment Deposit";
	  	schedule.rate = "99.5";
	}
	
	private void cancelFromLocationSearchPage(){
		taxSchPg.clickAddNew();
		assignLocSrchPg.waitLoading();
		
		assignLocSrchPg.searchLocation(schedule.locationCategory, schedule.location);

		assignLocSrchPg.clickCancel();
		//System returns to Tax Schedule list and add Tax Schedule action is cancelled. 
		ajax.waitLoading();
		taxSchPg.waitLoading();
	}
	
	private void cancelFromTaxScheduleDetailsPage(boolean setUpInfo){
		taxSchPg.clickAddNew();
		assignLocSrchPg.waitLoading();
		
		assignLocSrchPg.searchLocation(schedule.locationCategory, schedule.location);

		assignLocSrchPg.selectLocation(schedule.location);
		
		schDetailsPg.waitLoading();
		
		if(setUpInfo){
			schDetailsPg.enterTaxSchDetails(schedule);
			schDetailsPg.clickCancel();
			//System returns to Tax Schedule list and add Tax Schedule action is cancelled.
			ajax.waitLoading();
			taxSchPg.waitLoading();
		}else{
			schDetailsPg.clickCancel();
			//System returns to Tax Schedule list and add Tax Schedule action is cancelled. 
			ajax.waitLoading();
			taxSchPg.waitLoading();
		}
	}
}
