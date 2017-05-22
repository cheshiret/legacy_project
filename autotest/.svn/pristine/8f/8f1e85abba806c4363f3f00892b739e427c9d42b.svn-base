package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.calculation.tax.transactionfee.activity;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.ActivityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Tax fee schedule applied on Transaction Fee calculated correctly
 * @Preconditions: d_hf_add_facility=10
 * 							d_hf_add_facility_prd=10
 * 							d_hf_add_instructor=10
 * 							d_hf_add_activity=280
 * 							d_hf_assign_activity_to_store=40
 * @SPEC: Calculate Taxes - Activity Orders [TC:116038] Step 1, 4
 * @Task#: AUTO-2122
 * 
 * @author qchen
 * @Date  May 8, 2014
 */
public class ActivityTaxCalculation extends LicenseManagerTestCase {
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private ScheduleData taxSchdl = new ScheduleData();
	private FeeScheduleData transactionFeeSchdl = new FeeScheduleData();
	private String salesLocation;
	private Data<ActivityAttr> activity = new Data<ActivityAttr>();
	private double taxAmount;
	
	@Override
	public void execute() {
		//1. add a new Tax schedule applied on Activity Fee
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoTaxMainPage();
		taxSchdl.scheduleId = fnm.addNewTaxSchedule(taxSchdl);
		FinMgrTaxSchedulePage.getInstance().changeTaxScheduleStatus(taxSchdl.scheduleId, OrmsConstants.ACTIVE_STATUS);
		fnm.logoutFinanceManager();
		
		lm.loginLicenseManager(login, false);
		//2. add a new Vendor Fee schedule
		lm.gotoFeeScheduleListPageFromTopMenu();
		transactionFeeSchdl.feeSchdId = lm.addNewFeeSchedule(transactionFeeSchdl);
		LicMgrFeeScheduleSearchPage.getInstance().changeScheduleStatus(transactionFeeSchdl.feeSchdId, OrmsConstants.ACTIVE_STATUS);
		
		//3. make an Activity order to order cart
		lm.switchLocationInHomePage(salesLocation);
		lm.makeActivityToCartFromCustomerQuickSearch(cust, activity);
		
		//4. goto fee details page to verify the Tax applied on Vendor Fee calculated correctly
		lm.gotoFeeDetailsPageInCart("New - 1");
		LicMgrOrderFeesDetailsPage.getInstance().verifyFeeInfo(taxSchdl.taxName, taxSchdl.scheduleId, taxAmount, OrmsConstants.TRANNAME_REGISTER_FOR_ACTIVITY);
		lm.cancelFromFeeDetailsPage();
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facility = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/" + facility;
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		
		loginFnm.url = login.url;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginFnm.contract = login.contract;
		loginFnm.location = "Administrator - Auto/" + facility;
		
		//customer info
		cust.lName = "TEST-Advanced4";
		cust.fName = "QA-Advanced4";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "222222";
		cust.residencyStatus = "Non Resident";
				
		//Set up activity info
		activity.put(ActivityAttr.activityName, "Activity Tax Calculation");
		activity.put(ActivityAttr.activityID, lm.getProductID("Product Name", activity.stringValue(ActivityAttr.activityName), null, true, schema, PRDCAT_ACTIVITY));
		
		//tax fee schedule
		taxSchdl.location = facility;
		taxSchdl.locationCategory = "Contract";//IMPORTANT: must be on contract level for LM fee schedules
		taxSchdl.taxName = "Group Meals 7%";//IMPORTANT
		taxSchdl.productCategory = "Activity";
		taxSchdl.feeType = "Transaction Fee";//IMPORTANT
		taxSchdl.productGroup = "ActivityPrdGroup";
		taxSchdl.product = activity.stringValue(ActivityAttr.activityName);//IMPORTANT
		taxSchdl.startDate = DateFunctions.getDateAfterToday(-10);
		taxSchdl.endDate = DateFunctions.getDateAfterToday(30);
		taxSchdl.rate = String.valueOf(NumberUtil.getRandomDouble(10, 30));//Percentage
		
		//activity fee schedule
		transactionFeeSchdl.productCategory = "Activity";
		transactionFeeSchdl.feeType = taxSchdl.feeType;//IMPORTANT
		transactionFeeSchdl.productGroup = taxSchdl.productGroup;
		transactionFeeSchdl.product = taxSchdl.product;//IMPORTANT
		transactionFeeSchdl.effectDate = taxSchdl.startDate;
		transactionFeeSchdl.applicableTaxes.add(taxSchdl.taxName);//IMPORTANT
		transactionFeeSchdl.applyRate = "Per Unit";
		transactionFeeSchdl.rateAmount = String.valueOf(NumberUtil.getRandomDouble(10, 100));
		
		//IMPORTANT: expected tax amount
		taxAmount = new BigDecimal(Double.parseDouble(transactionFeeSchdl.rateAmount) * Double.parseDouble(taxSchdl.rate) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
