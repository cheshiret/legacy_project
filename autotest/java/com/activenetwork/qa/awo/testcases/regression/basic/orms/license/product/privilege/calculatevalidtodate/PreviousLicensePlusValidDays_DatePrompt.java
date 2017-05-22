package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculatevalidtodate;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This script used to testing privilege Valid To Date equal to
 * [Valid From Date & Time MINUS one minute] PLUS the Valid Days specified in the Privilege Product being purchased]
 * @Preconditions: Setup privilege product where Valid To Date Calculation set to "Valid To Date of Previous License plus Valid Days/Years" and
 * Valid From Date Calculation set to "Prompt for Valid From Date and time"
 * @SPEC:SpiraTeam[TC:036533]
 * @Task#:Auto-938
 * 
 * @author ssong
 * @Date  Apr 05, 2012
 */
public class PreviousLicensePlusValidDays_DatePrompt extends LicenseManagerTestCase{

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

		//purchase a privilege with valid from date promp
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

		//purchase privilege with valid from date&time widget prompt
		privilege.code = "DP3";
		privilege.name = "CalculateValidToDate_6";
		privilege.purchasingName = "DP3-CalculateValidToDate_6";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getDateAfterToday(3);
		privilege.validFromTime = "00:00";
		privilege.validFromAmPm = "AM";
//		privilege.operateReason = "14 - Other";
		
		//data for check privilege valid to date rule setup
		fromDateCal = "Prompt for Valid From Date and Time";
		promptInd = "Per Transaction";
		toDateCal = "Valid To Date of Previous License plus Valid Days/Years";
		validDaysOrYears = "1";
		unit = "Days";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Basic10";
		cust.lName = "TEST-Basic10";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000010";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}

}
