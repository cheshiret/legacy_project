/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculatevalidtodate;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This script used to testing privilege Valid To Date equal to
 * [Valid From Date & Time MINUS one minute] PLUS the Valid Days specified in the Privilege Product being purchased]
 * @Preconditions: Setup privilege product where Valid To Date Calculation set to "Valid From Date plus Valid Days/Years" and
 * Valid From Date Calculation set to "Prompt for Valid From Date"
 * @SPEC:SpiraTeam[TC:033960]
 * @Task#:Auto-938
 * 
 * @author ssong
 * @Date  Mar 19, 2012
 */
public class FromDatePlusValidDays_DatePrompt extends LicenseManagerTestCase{

	private String sale_location = "HF HQ Role - Auto-RefundTest";
	private String fromDateCal,promptInd,toDateCal,validDaysOrYears,unit;
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		
		lm.loginLicenseManager(login);
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, LicMgrNewCustomerPage.getInstance());
		lm.finishAddOrEditCustomer();
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.checkValidToDateCalculationRule();
		
		//purchase a privilege with valid from date prompt
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String ord_num = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(ord_num);
		String privilege_nums = lm.getPrivilegeNumByOrdNum(schema, ord_num);
		this.verifyPrivilegeValidToDateCalculate(privilege_nums);
		lm.logOutLicenseManager();		
	}
	
	private void verifyPrivilegeValidToDateCalculate(String pri_nums){
		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage.getInstance();
		lm.gotoPrivilegeDetailFromOrderPg(pri_nums.split(",")[0]);
		String fromDate = detailPg.getValidFromDate();
		String toDate = detailPg.getValidToDate();
		//valid from date should equals the 'input date' prompt when purchase privilege
		if(DateFunctions.compareDates(fromDate, privilege.validFromDate)!=0){
			throw new ErrorOnPageException("Valid From Date Not Correct",privilege.validFromDate,fromDate);
		}
		//the difference between two dates should be 23h,59m
		long mins = DateFunctions.diffMinBetween(toDate, fromDate);
		if(DateFunctions.MINUTES_OF_DAY-1!=mins){
			throw new ErrorOnPageException("Valid To Date Calculate Not Correct",DateFunctions.MINUTES_OF_DAY-1+"",String.valueOf(mins));
		}
	}

	private void checkValidToDateCalculationRule(){
		LicMgrPrivilegeProductDetailsPage detailPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		
		detailPg.checkAndPrepareDataForValidToDateCalculate(fromDateCal, promptInd, toDateCal, validDaysOrYears, unit);
		lm.gotoHomePage();
		lm.switchLocationInHomePage(sale_location);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		// initialize login license manager loiginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		//purchase privilege with valid from date widget prompt
		privilege.code = "DP1";
		privilege.name = "CalculateValidToDate_3";
		privilege.purchasingName = "DP1-CalculateValidToDate_3";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getDateAfterToday(2);
		
		//data for check privilege valid to date rule setup
		fromDateCal = "Prompt for Valid From Date";
		promptInd = "Per Privilege";
		toDateCal = "Valid From Date plus Valid Days/Years";
		validDaysOrYears = "1";
		unit = "Days";
		
		cust.setDefaultValuesForLMCust();
	}

}
