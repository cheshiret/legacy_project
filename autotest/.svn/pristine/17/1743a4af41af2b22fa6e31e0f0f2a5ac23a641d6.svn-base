package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddTaxAssignment extends SupportCase
{
	/**
	 * Script Name   : <b>AddTaxAssignment</b>
	 * Generated     : <b>Feb 25, 2010 8:03:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2010/02/25
	 * @author Sara Wang
	 */

	private FinMgrTaxMainPage finTaxMainPg = FinMgrTaxMainPage.getInstance();
	private FinanceManager fin = FinanceManager.getInstance();
  	private FinMgrTaxMainPage fintaxMainPg = FinMgrTaxMainPage.getInstance();
    private FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();

	private LoginInfo login = new LoginInfo();
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	private String taxScheduleID = "";
	private boolean loggined = false;

	public void wrapParameters(Object[] param) {

		startpoint=0;   // the start point in the data pool
		endpoint=999;     // the end point in the data pool

		dataSource = casePath + "/" + caseName;

		String env = TestProperty.getProperty("target_env");
	  	login.url = AwoUtil.getOrmsURL(env);
		login.userName = "qa-auto-adm";
		login.password = "auto1234";
		login.envType = "QA";
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

        //log informaiton
		logMsg = new String[8];

		logMsg[0] = "cursor";
		logMsg[1] = "taxName";
		logMsg[2] = "location";
		logMsg[3] = "locationCategory";
		logMsg[4] = "feeType";

		logMsg[5]="taxScheduleID";
		logMsg[6]="activeOrInactive";
	    logMsg[7]="result";

	}

	public void execute() {
        //Login Finance Manager
		if ((!loggined) || (loggined && !finTaxMainPg.exists())) {
			fin.loginFinanceManager(login);
			loggined = true;
			//goto tax main page
			fin.gotoTaxMainPage();
		}

        //search tax by tax name
	    fintaxMainPg.searchByTaxName(tax.taxName);

		//add new tax schedule base on the tax no schedule
		taxScheduleID = fin.addNewTaxSchedule(schedule);

		//Activate tax schedule
		taxSchPg.changeTaxScheduleStatus(taxScheduleID,"Active");

	    //get current taxSchedule's status
	    if( taxSchPg.isTaxScheduleActive(taxScheduleID)&& taxScheduleID.length()>0){
	    	logMsg[5] = taxScheduleID;
	        logMsg[6]="Active";
	        logMsg[7]="Success";

	    } else{
	    	logMsg[5]=taxScheduleID;
	        logMsg[6]="Inactive";
	        logMsg[7]="Fail";
	    }

		//In order to search next tax'name.
		taxSchPg.clickTaxesTab();
		fintaxMainPg.waitLoading();

	}

	public void getNextData() {

		tax.taxName = dpIter.dpString("taxName");
		schedule.taxName = dpIter.dpString("taxScheduleName");

		schedule.location = dpIter.dpString("location");
		schedule.locationCategory = dpIter.dpString("locationCategory");

		schedule.productCategory = dpIter.dpString("productCategory");
		schedule.feeType = dpIter.dpString("feeType");
		schedule.productGroup = dpIter.dpString("productGroup");
		schedule.product = dpIter.dpString("product");
		schedule.startDate = dpIter.dpString("startDate");

		if (!StringUtil.notEmpty(schedule.startDate)) {
			schedule.startDate = DateFunctions.getDateAfterToday(-1);
		}

		if (!StringUtil.notEmpty(schedule.endDate)) {
			schedule.endDate = DateFunctions.getDateAfterToday(-1);
		}

		schedule.endDate = dpIter.dpString("endDate");
		schedule.customerType = dpIter.dpString("customerType");
		schedule.accountCode = dpIter.dpString("accountCode");
		schedule.rate = dpIter.dpString("rate");

		logMsg[0] = cursor+"";
		logMsg[1] = tax.taxName;
		logMsg[2] = schedule.location;
		logMsg[3] = schedule.locationCategory;
		logMsg[4] = schedule.feeType;

		logMsg[5]="Unknown";
		logMsg[6]="Inactive";
	    logMsg[7]="Fail due to error";
	}
}

