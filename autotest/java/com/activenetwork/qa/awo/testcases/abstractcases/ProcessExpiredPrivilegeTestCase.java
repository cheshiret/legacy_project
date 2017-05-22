package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class ProcessExpiredPrivilegeTestCase extends LicenseManagerTestCase {
	protected SystemManager sm = SystemManager.getInstance();
	protected LoginInfo loginSm = new LoginInfo();
	protected String daemonName, daemonRunTime, estimateExpireDateTime;
	protected TimeZone timeZone_MS;
	protected LicMgrPrivilegeItemDetailPage privDetailPage = LicMgrPrivilegeItemDetailPage.getInstance();
	protected boolean isForRevoked = false;
	protected Suspension suspension = new Suspension();
	
	protected void verifyExistingPrivilege(String orderNum, String privilegeNum) {
		logger.info("There is a privilege instance(#" + privilegeNum + ") of an order(#=" + orderNum + ") exists in System.");
		lm.gotoOrderPageFromOrdersQuickSearch(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		String validToDateTime = privDetailPage.getValidToDate();
		String status = privDetailPage.getStatus();
		
		//checkpoint#1
		this.verifyIfPrivilegeExpired(validToDateTime, status);
		
		List<String> history = privDetailPage.getPrivilegeHistory(OrmsConstants.TRANNAME_EXPIRE_PRIVILEGE);
		//checkpoint#2
		this.verifyExpireInfo(history);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		//log info
		loginSm.url = AwoUtil.getOrmsURL(env) + "/SystemMgrLogin.do";
		loginSm.userName = TestProperty.getProperty("orms.adm.user");
		loginSm.password = TestProperty.getProperty("orms.adm.pw");
		loginSm.contract = "MS Contract";
		loginSm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		login.contract = loginSm.contract;
		login.location = "HF HQ Role/WAL-MART";
		
		daemonName = "com.reserveamerica.licensing.order.impl.daemon.ExpirePrivilegeDaemon";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone_MS = DataBaseFunctions.getContractTimeZone(schema);
		
		privilege.code = "PEP";
		privilege.name = "ProcessExpiredPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation test";
		
		if(!isForRevoked) {
			cust.customerClass = "Individual";
			cust.fName = "QA-Basic26";
			cust.lName = "TEST-Basic26";
			cust.dateOfBirth = "19880612";
			cust.identifier.identifierType = "Green Card";
			cust.identifier.identifierNum = "AutoBasic000026";
			cust.identifier.country = "Canada";
			cust.residencyStatus = "Non Resident";
		} else {
			cust.customerClass = "Individual";
			cust.fName = "QA-Dont";
			cust.lName = "TEST-Touchme";
			cust.dateOfBirth = "20120222";
			cust.identifier.identifierType = "Passport";
			cust.identifier.identifierNum = "63667792";
			cust.identifier.country = "Canada";
			cust.residencyStatus = "Non Resident";
			
			suspension.status = OrmsConstants.ACTIVE_STATUS;
			suspension.type = "Bad Check Suspension";
			suspension.beginDate = DateFunctions.getDateAfterToday(-1);
			suspension.endDate = DateFunctions.getDateAfterToday(1);
			suspension.datePosted = suspension.beginDate;
			suspension.comment = "Addiing customer suspension to make privilege as revoked - " + DateFunctions.getCurrentTime();
		}
	}
	
	protected String calculateEstimateExpiredDateTime(String validTo) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateFunctions.parseDateString(validTo));
		calendar.add(Calendar.DATE, 1);
		String expireDate = DateFunctions.formatDate(calendar.getTime(), "EEE MMM dd yyyy");
		String expireDateTimeInSystem = expireDate + " " +  daemonRunTime + " AM";
		//logger.info("----Expected expire Date Time in ORMS System is: " + expireDateTimeInSystem);
		TimeZone systemTimeZone = DataBaseFunctions.getContractTimeZone("");
		estimateExpireDateTime = DateFunctions.changeDateStampTimeZone(expireDateTimeInSystem, "EEE MMM dd yyyy h:mm a", systemTimeZone, "EEE MMM dd yyyy hh:mm a z", timeZone_MS);

		logger.info("----Expected expire Date Time in MS is: " + estimateExpireDateTime);
		
		return estimateExpireDateTime;
	}
	
	protected void verifyIfPrivilegeExpired(String validToDate, String actualStatus) {
		String expectedExpireDateTimeInMS = this.calculateEstimateExpiredDateTime(validToDate);
//		Calendar expectedCalendar = Calendar.getInstance();
//		expectedCalendar.setTime(DateFunctions.parseDateString(expectedExpireDateTimeInMS));
		
		String currentDateTimeInMS = DateFunctions.getToday("EEE MMM d yyyy hh:mm a z", timeZone_MS);
//		Calendar currentCalendar = Calendar.getInstance();
//		currentCalendar.setTime(DateFunctions.parseDateString(currentDateTimeInMS));

		if(DateFunctions.compareExactDates(expectedExpireDateTimeInMS, currentDateTimeInMS) == -1) {
			if(!actualStatus.equals(OrmsConstants.EXPIRED_STATUS)) {
				throw new ErrorOnPageException("The privilege should be already expired at " + expectedExpireDateTimeInMS);
			}
			logger.info("The privilege is really expired at " + expectedExpireDateTimeInMS);
		} else {
			throw new TestCaseFailedException("The privilege hasn't yet expired. Please rerun this case after " + expectedExpireDateTimeInMS);
		}
	}
	
	protected void verifyExpireInfo(List<String> actualHistory) {
		logger.info("Verify the privilege expired history info.");
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Date & Time", estimateExpireDateTime, actualHistory.get(0));
		result &= MiscFunctions.compareResult("Transaction", OrmsConstants.TRANNAME_EXPIRE_PRIVILEGE, actualHistory.get(1));
		result &= MiscFunctions.compareResult("Transaction Location", loginSm.location.split("/")[1], actualHistory.get(3));
		result &= MiscFunctions.compareResult("User", "Administrator,System", actualHistory.get(4).replace(", ", ","));
		
		if(!result) {
			throw new ErrorOnPageException("The privilege history info are incorrect, please refer log for details.");
		} else logger.info("The privilege expired history info are all correct.");
	}
}
