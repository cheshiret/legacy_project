/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 21, 2012
 */
public class AddDiscountScheduleFunction extends FunctionCase{
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage.getInstance();
	private FinMgrDiscountSchedulePage discountSchPg = FinMgrDiscountSchedulePage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	private String errorMsg,contract;
	private boolean loggedIn = false;

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		schedule = (ScheduleData)param[1];
	}

	public void execute() {
		//Finance Manage can switch
		if (!login.contract.equalsIgnoreCase(contract) && loggedIn && isBrowserOpened) {
			fnm.switchToContract(login.contract, login.location);
		}
		if (!loggedIn || !isBrowserOpened) {
			fnm.loginFinanceManager(login);
			loggedIn = true;
		}
		contract = login.contract;
		if(!homePage.exists()){
			fnm.gotoHomePage();
		}

		fnm.gotoDiscountPage();
		// add discount schedules for the discount just adding.
		discountPg.gotoDiscountSchPg();
		schedule.scheduleId = fnm.addNewDiscountSchedule(schedule, false);
		if(StringUtil.isEmpty(schedule.scheduleId)) {
			fnm.gotoHomePage();
			throw new ErrorOnPageException("Failed to Create Discount Schdeule for discount "+schedule.discountName);
		}
		newAddValue = schedule.scheduleId;
		// activate the schedule you just add and verify status is active
		discountSchPg.changeDiscountStatus(schedule.scheduleId, "Active");
		errorMsg = discountSchPg.getErrorMsg();
		if(!StringUtil.isEmpty(errorMsg)){
			fnm.gotoHomePage();
			throw new ErrorOnPageException("Failed to activate discount:"+schedule.scheduleId);
		}

		// verify current discount schedule's status and put out log information
		boolean result = this.checkSchedule(schedule.scheduleId);
		if(!result) {
			throw new ErrorOnDataException("Failed to Create Discount Schdeule as support data for discount "+schedule.discountName);
		}
	}

	/**
	 * @param scheduleId
	 */
	private boolean checkSchedule(String id) {
		discountSchPg.searchByScheduleId(id);
		ScheduleData record = discountSchPg.getAllRecordOnPage().get(0);
		boolean result = true;

		result &= record.activeStatus.equalsIgnoreCase("Yes");
		result &= StringUtil.isEmpty(schedule.discountName)?true:record.discountName.equalsIgnoreCase(schedule.discountName);
		result &= StringUtil.isEmpty(schedule.location)?true:record.location.equalsIgnoreCase(schedule.location);
		result &= StringUtil.isEmpty(schedule.feeType)?true:record.feeType.equalsIgnoreCase(schedule.feeType);
		result &= StringUtil.isEmpty(schedule.productCategory)?true:record.productCategory.equalsIgnoreCase(schedule.productCategory);
		result &= StringUtil.isEmpty(schedule.rate)?true:(StringUtil.compareNumStrings(record.rate, schedule.rate)==0);
		result &= StringUtil.isEmpty(schedule.effectiveDate)?true:(DateFunctions.compareDates(schedule.effectiveDate, record.effectiveDate)==0);
		//result &= StringUtil.isEmpty(schedule.startDate)?true:(DateFunctions.compareDates(schedule.startDate, record.startDate)==0);
		//result &= StringUtil.isEmpty(schedule.endDate)?true:(DateFunctions.compareDates(schedule.endDate, record.endDate)==0);
		result &= StringUtil.isEmpty(schedule.custPass)?true:record.custPass.equalsIgnoreCase(schedule.custPass);
		return result;
	}
}
