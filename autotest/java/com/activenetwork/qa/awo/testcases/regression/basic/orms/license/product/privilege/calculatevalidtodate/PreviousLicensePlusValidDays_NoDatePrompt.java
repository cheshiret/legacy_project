package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculatevalidtodate;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This script used to testing privilege Valid To Date equal to
 * [Valid From Date & Time MINUS one minute] PLUS the Valid Days specified in the Privilege Product being purchased]
 * @Preconditions: Setup privilege product where Valid To Date Calculation set to "Valid To Date of Previous License plus Valid Days/Years" and
 * Valid From Date Calculation set to "Based on Priv LY Record or Purchase Date if Greater"
 * @SPEC:SpiraTeam[TC:036380]
 * @Task#:Auto-938
 * 
 * @author ssong
 * @Date  Apr 05, 2012
 */

public class PreviousLicensePlusValidDays_NoDatePrompt extends LicenseManagerTestCase{

	private String sale_location = "HF HQ Role - Auto-RefundTest";
	private String fromDateCal,promptInd,toDateCal,validDaysOrYears,unit;
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.checkValidToDateCalculationRule();

		// clean up
		lm.invalidatePrivilegePerCustomer(cust, privilege);

		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String ord_num = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(ord_num);
		String privilege_nums = lm.getPrivilegeNumByOrdNum(schema, ord_num);
		this.verifyPrivilegeValidToDateCalculate(privilege_nums);
		this.cleanUp(ord_num);
		lm.logOutLicenseManager();
	}
	
	private void verifyPrivilegeValidToDateCalculate(String pri_nums){
		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage.getInstance();
		lm.gotoPrivilegeDetailFromOrderPg(pri_nums.split(",")[0]);
		String fromDate = detailPg.getValidFromDate();
		String toDate = detailPg.getValidToDate();
		//valid from date should equals the privilege purchase date
		String current_date = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		if(DateFunctions.compareDates(fromDate, current_date)!=0){
			throw new ErrorOnPageException("Valid From Date Not Correct",current_date,fromDate);
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
	
	private void cleanUp(String ordNum){
		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage.getInstance();
		LicMgrPrivilegeOrderDetailsPage ordPg = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		detailPg.clickOrder(ordNum);
		ordPg.waitLoading();
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		if(OrmsOrderCartPage.getInstance().exists()){
			lm.processOrderCart(pay);
		}
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

		privilege.code = "ND3";
		privilege.name = "CalculateValidToDate_5";
		privilege.purchasingName = "ND3-CalculateValidToDate_5";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		//data for check privilege valid to date rule setup
		fromDateCal = "Based on Priv LY Record or Purchase Date (If Greater)";
		promptInd = "Per Transaction";
		toDateCal = "Valid To Date of Previous License plus Valid Days/Years";
		validDaysOrYears = "1";
		unit = "Days";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-ValidDater1";
		cust.lName = "TEST-ValidDater1";
		cust.dateOfBirth = "Jun 08 1988";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "59687561";
		//cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}

}