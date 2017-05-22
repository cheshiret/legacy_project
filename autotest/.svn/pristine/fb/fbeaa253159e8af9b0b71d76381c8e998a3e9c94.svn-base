package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.suspension;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerSuspensionPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrServiceDaemonsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Notes: Please rerun this case after 02:30:00 PM if it is failed.
 * @Description: This case is used to verify whether the suspension is expired or not when the end date is
 * less than the current date in the time zone of the known contract, and current time is greater than the daemon running time - 2:30 AM
 * Comment: the daemon running time maybe will be changed, can get it from System Manager - Utilities - Envs - Service Daemon(s)
 * @Preconditions: Case will create a suspension to test by itself
 * @SPEC: <<Expire Suspension.UCS>>
 * @Task#: AUTO-756
 * 
 * @author qchen
 * @Date  Dec 5, 2011
 */
public class ExpireSuspension extends LicenseManagerTestCase {
	private SystemManager sm = SystemManager.getInstance();
	private LoginInfo loginSm = new LoginInfo();
	private String daemonName, daemonRunTime, expectedExpireDateTimeInMS;
	private TimeZone timeZone_MS, timeZone_System;
	private Suspension newSuspension = new Suspension();
	private ChangeHistory expectedChangeHistory = new ChangeHistory();
	
	@Override
	public void execute() {
		sm.loginSystemManager(loginSm);
		//go to System Manager to get 'ExpireSuspension' service daemon running time and running status
		this.getDaemonRunningStatusAndTime();
		sm.logoutSystemManager();
		
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerSubTabPage("Suspensions");
		//get suspension info OR create a new suspension then test case fails
		Suspension suspension = this.getSuspensionByComment(newSuspension.comment);
		//verify suspension expired
		this.verifySuspensionExpired(suspension);
		//verify the audit log is correct
		this.verifyAuditLog(suspension);
		
		//remove the expired suspension record
		lm.removeCustomerSuspension(suspension);
		
		//add a new suspension to verify in next time run this case
		lm.addCustomerSuspension(newSuspension);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login info
		loginSm.url = AwoUtil.getOrmsURL(env) + "/SystemMgrLogin.do";
		loginSm.userName = TestProperty.getProperty("orms.adm.user");
		loginSm.password = TestProperty.getProperty("orms.adm.pw");
		loginSm.contract = "MS Contract";
		loginSm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		login.contract = loginSm.contract;
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		daemonName = "com.reserveamerica.licensing.customer.daemon.ExpireSuspensionDaemon";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//customer info
		cust.customerClass = "Individual";
		cust.fName = "QA-ExpireSuspension";
		cust.lName = "Test-ExpireSuspension";
		cust.identifier.identifierType = "Tax Id";
		cust.identifier.identifierNum = "569865304";
		
		//suspension info
		String today = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		newSuspension.type = "Bad Check Suspension";
		newSuspension.beginDate = today;
		newSuspension.endDate = today;
		newSuspension.datePosted = today;
		newSuspension.comment = "CreatedForVerifyExpireCustomerSuspension";
		
		timeZone_System = TimeZone.getTimeZone(DataBaseFunctions.getTimeZoneString(null));
		timeZone_MS = DataBaseFunctions.getContractTimeZone(schema);
		
		//expected change history info, for verifying Audit Log
		expectedChangeHistory.action = "Update";
		expectedChangeHistory.field = "status";
		expectedChangeHistory.oldValue = "Active";
		expectedChangeHistory.newValue = "Expired";
		expectedChangeHistory.user = "Administrator, System";
	}
	
	public void getDaemonRunningStatusAndTime() {
		SysMgrServiceDaemonsPage sysMgrServicePage = SysMgrServiceDaemonsPage.getInstance();
		sm.gotoServiceDaemonsPage();
		String daemonStatus = sysMgrServicePage.getDaemonStatus(daemonName);
		if(!daemonStatus.equals("RUNNING")) {
			throw new ErrorOnPageException("The ExpireCustomerSuspension daemon is NOT running, please contact Administrator to start it.");
		}
		daemonRunTime = sysMgrServicePage.getDaemonRunAt(daemonName).split("at")[1].trim();
	}
	
	/**
	 * Get suspension detail info from customer suspension page identified by unique comment.
	 * If the comment doesn't exists, create a new one and test case fails
	 * @param comment
	 * @return
	 */
	public Suspension getSuspensionByComment(String comment) {
		Suspension suspension = null;
		try{
			//get former created suspension info by an unique comment
			 suspension = lm.getCustomerSuspensionByComment(comment);
		} catch (ErrorOnPageException e) {
			newSuspension.id = lm.addCustomerSuspension(newSuspension);
			String estimateExpireDateTime = DateFunctions.formatDate((DateFunctions.getDateAfterGivenDay(newSuspension.endDate, 1) + " " + daemonRunTime), "MM/dd/yyyy hh:mm");
			throw new TestCaseFailedException("A new suspension(ID#=" + newSuspension.id + ") has been created for testing expiry. Please run this case after " + estimateExpireDateTime + ".");
		}
		
		return suspension;
	}
	
	/**
	 * This method is used to verify whether the suspension is expired or not
	 * @param susp
	 */
	public void verifySuspensionExpired(Suspension susp) {
		logger.info("Verify the suspension(ID#= " + susp.id + ") is expired or not.");
		
		susp.endDate = DateFunctions.formatDate(susp.endDate, "MM/dd/yyyy");
		String expectedExpireDateTimeInSystem = DateFunctions.formatDate((DateFunctions.getDateAfterGivenDay(susp.endDate, 1) + " " + daemonRunTime), "MM/dd/yyyy hh:mm");
		logger.info("----Expected expire Date/Time in System is: " + expectedExpireDateTimeInSystem);
		expectedExpireDateTimeInMS = DateFunctions.changeDateStampTimeZone(expectedExpireDateTimeInSystem, "MM/dd/yyyy hh:mm", timeZone_System, "E MMM d yyyy h:mm zz", timeZone_MS);
		logger.info("----Expected expire Date/Time in MS Contract is: " + expectedExpireDateTimeInMS);
		String currentDateTimeInSystem = DateFunctions.getToday("MM/dd/yyyy hh:mm", timeZone_System);
		
		Date expectedExpireDateTime_Date = DateFunctions.parseDateString(expectedExpireDateTimeInSystem);
		Date currentDateTime_Date = DateFunctions.parseDateString(currentDateTimeInSystem);
		if(DateFunctions.compareDates(expectedExpireDateTime_Date, currentDateTime_Date) == -1) {
			//the customer suspension should be already expired
			if(!susp.status.equals("Expired")) {
				throw new ErrorOnPageException("The suspension(ID#=" + susp.id + ") should be already expired.");
			}
			logger.info("The suspension(ID#=" + susp.id + ") is really expired.");
		} else {
			throw new TestCaseFailedException("The suspension(ID#=" + susp.id + ") hasn't yet expired. Please rerun this case after " + expectedExpireDateTime_Date);
		}
	}
	
	public void verifyAuditLog(Suspension susp) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage.getInstance();
		LicMgrCustomerChangeHistoryPage customerChangeHistoryPage = LicMgrCustomerChangeHistoryPage.getInstance();
		boolean result = true;
		
		logger.info("Verify wether the corresponding audit log is correct or not.");
		suspensionPage.clickChangeHistory();
		customerChangeHistoryPage.waitLoading();
		List<ChangeHistory> histories = customerChangeHistoryPage.getChangeHistoryInfo(susp.id);
		ChangeHistory changeHistory = null;
		for(ChangeHistory history : histories) {
			if(history.field.equals("status") && history.newValue.equals("Expired")) {
				changeHistory = history;
			}
		}
		//ignore the history date change.
		//logger.debug("actual date/time is: " + changeHistory.changeDate + "and expected date/time is: " + expectedExpireDateTimeInMS);
		//if(DateFunctions.compareDates(new Date(changeHistory.changeDate), DateFunctions.parseDateString(expectedExpireDateTimeInMS, "E MMM d yyyy h:mm zz")) != 0) {
			//logger.error("Change History - Date/Time is wrong. Actual value is: " + changeHistory.changeDate + " but expected value is: " + expectedExpireDateTimeInMS);
			//result &= false;
		//}
		expectedChangeHistory.object = "Suspension-" + susp.type + "(" + susp.id + ")";
		if(!changeHistory.object.equals(expectedChangeHistory.object)) {
			logger.error("Change History - Object is wrong. Actual value is: " + changeHistory.object + " but expected value is: " + expectedChangeHistory.object);
			result &= false;
		}
		if(!changeHistory.action.equals(expectedChangeHistory.action)) {
			logger.error("Change History - Action is wrong.");
			result &= false;
		}
		if(!changeHistory.field.equals(expectedChangeHistory.field)) {
			logger.error("Change History - Field is wrong.");
			result &= false;
		}
		if(!changeHistory.oldValue.equals(expectedChangeHistory.oldValue)) {
			logger.error("Change History - Old Value is wrong.");
			result &= false;
		}
		if(!changeHistory.newValue.equals(expectedChangeHistory.newValue)) {
			logger.error("Change History - New Value is wrong.");
			result &= false;
		}
		if(!changeHistory.user.equals(expectedChangeHistory.user)) {
			logger.error("Change History - User is wrong.");
			result &= false;
		}
		if(!changeHistory.location.equals(login.location.split("/")[1])) {
			logger.error("Change History - Location is wrong.");
			result &= false;
		}
		
		if(result) {
			logger.info("Audit Log is correct.");
		} else {
			throw new ErrorOnPageException("Audit Log is incorrect. Please refer the log for detail info.");
		}
		
		customerChangeHistoryPage.clickReturnToCustomerDetail();
		suspensionPage.waitLoading();
	}
}
