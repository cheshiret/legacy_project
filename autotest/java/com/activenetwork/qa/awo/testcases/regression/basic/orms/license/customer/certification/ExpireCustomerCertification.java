/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.certification;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrServiceDaemonsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to verify whether the certification is expired or not when the effective to date is
 * less than the current date.
 * @Preconditions:
 * @SPEC:Expire Customer Certification.doc
 * @Task#:Auto-843
 * 
 * @author nding1
 * @Date  2011-12-26
 */
public class ExpireCustomerCertification extends LicenseManagerTestCase {
	private SystemManager sm = SystemManager.getInstance();
	private LoginInfo loginSm = new LoginInfo();
	private String daemonName, daemonRunTime, expectedExpireDateTimeInMS;
	private Customer customer = new Customer();
	private Certification certification = new Certification();
	private String timeZoneCode_MS, timeZoneCode_System;
	private ChangeHistory expectedChangeHistory = new ChangeHistory();

	@Override
	public void execute() {		
		sm.loginSystemManager(loginSm);
		
		//go to System Manager to get 'ExpireCusomerCertificationDaemon' service daemon running time and running status
		this.getDaemonRunningStatusAndTime();
		sm.logoutSystemManager();
		
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCustomerSubTabPage("Certifications");

		Certification cForRemove = this.getCertificationByNum(certification.num);

		// verify Certification expired or not
		this.verifyCertificationExpired(cForRemove);
		
		// verify the audit log is correct or not
		this.verifyAuditLog(cForRemove);
		
		// remove the expired certification record
		lm.removeCustomerCertification(cForRemove.id, true);
		
		// add a new certification for next time
		lm.addCustomerCertification(certification);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		loginSm.url = AwoUtil.getOrmsURL(env) + "/SystemMgrLogin.do";
		loginSm.userName = TestProperty.getProperty("orms.adm.user");
		loginSm.password = TestProperty.getProperty("orms.adm.pw");
		loginSm.contract = "MS Contract";
		loginSm.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		login.contract = loginSm.contract;
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		daemonName = "com.reserveamerica.licensing.customer.daemon.ExpireCusomerCertificationDaemon";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZoneCode_System = DataBaseFunctions.getTimeZoneString(null);
		timeZoneCode_MS = DataBaseFunctions.getTimeZoneString(schema);
		
		//customer info
		customer.customerClass = "Individual";
		customer.identifier.identifierType = "Green Card";
		customer.identifier.identifierNum = "AutoBasic00001";

		//expected change history info, for verifying Audit Log
		expectedChangeHistory.action = "Update";
		expectedChangeHistory.field = "Status";
		expectedChangeHistory.oldValue = "Active";
		expectedChangeHistory.newValue = "Expired";
		expectedChangeHistory.user = "Administrator, System";
		expectedChangeHistory.location = login.location.split("/")[1];
		
		// new certification info
		certification.type = "Trapper Certification";
		certification.status = OrmsConstants.ACTIVE_STATUS;
		certification.num = "FORE1";
		certification.effectiveFrom = DateFunctions.getToday();
		certification.effectiveTo = DateFunctions.getToday();
	}
	
	/**
	 * Get daemon running status and time in System manager.
	 * 
	 */
	private void getDaemonRunningStatusAndTime() {
		SysMgrServiceDaemonsPage sysMgrServicePage = SysMgrServiceDaemonsPage.getInstance();
		sm.gotoServiceDaemonsPage();
		String daemonStatus = sysMgrServicePage.getDaemonStatus(daemonName);
		if(!daemonStatus.equals("RUNNING")) {
			throw new ErrorOnPageException("The ExpireCustomerSuspension daemon is NOT running, please contact Administrator to start it.");
		}
		daemonRunTime = sysMgrServicePage.getDaemonRunAt(daemonName).split("at")[1].trim();
	}
	
	/**
	 * This method is used to get certification detail info through certification number.
	 * 
	 * @param certificationNum
	 * @return
	 */
	private Certification getCertificationByNum(String certificationNum) {
		Certification c = new Certification();
		
		try{
			// get former created certification info by an unique certification number
			c = lm.getCertificationByNum(certificationNum);
		} catch(Exception e) {
			c.id = lm.addCustomerCertification(certification);
			String estimateExpireDateTime = DateFunctions.formatDate((DateFunctions.getDateAfterGivenDay(c.effectiveTo, 1) + " " + daemonRunTime), "MM/dd/yyyy hh:mm");
			throw new TestCaseFailedException("A new suspension(ID#=" + c.id + ") has been created for testing expiry. Please run this case after " + estimateExpireDateTime + ".");
		}
		
		return c;
	}
	
	/**
	 * This method is used to verify whether the Certification is expired or not.
	 * 
	 * @param certification
	 */
	private void verifyCertificationExpired(Certification certification) {
		logger.info("Verify the Certification(ID#= " + certification.id + ") is expired or not.");

		//End Date is not null/blank and is less than the Current Date in the time zone of the known Contract
		certification.effectiveTo = DateFunctions.formatDate(certification.effectiveTo, "MM/dd/yyyy");
		String expectedExpireDateTimeInSystem = DateFunctions.formatDate((
				DateFunctions.getDateAfterGivenDay(certification.effectiveTo, 1) + " " + daemonRunTime), "MM/dd/yyyy hh:mm");
		expectedExpireDateTimeInMS = DateFunctions.changeDateStampTimeZone(
				expectedExpireDateTimeInSystem, "MM/dd/yyyy hh:mm", TimeZone.getTimeZone(timeZoneCode_System), "E MMM d yyyy h:mm aa", TimeZone.getTimeZone(timeZoneCode_MS));
		String currentDateTimeInSystem = DateFunctions.getToday("MM/dd/yyyy hh:mm", TimeZone.getTimeZone(timeZoneCode_System));
		
		Date expectedExpireDateTime_Date = DateFunctions.parseDateString(expectedExpireDateTimeInSystem);
		Date currentDateTime_Date = DateFunctions.parseDateString(currentDateTimeInSystem);
		
		if(DateFunctions.compareDates(expectedExpireDateTime_Date, currentDateTime_Date) == -1) {			
			//the customer Certification should have been already expired
			if(!certification.status.equals("Expired")) {
				throw new ErrorOnPageException("The Certification(ID#=" + certification.id + ") should have been already expired.");
			}
			
			logger.info("The Certification(ID#=" + certification.id + ") is really expired.");
		} else {
			throw new TestCaseFailedException("The Certification(ID#=" + certification.id + ") hasn't been expired yet. Please rerun this case after " + expectedExpireDateTime_Date);
		}
	}

	
	/**
	 * Verify change log page.
	 * 
	 * @param certification
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLog(Certification certification) {
		LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
		LicMgrCustomerChangeHistoryPage customerChangeHistoryPage = LicMgrCustomerChangeHistoryPage.getInstance();
		boolean result = true;
		
		logger.info("Verify wether the corresponding audit log is correct or not.");
		certificationPage.clickChangeHistory();
		customerChangeHistoryPage.waitLoading();
		
		List<ChangeHistory> histories = customerChangeHistoryPage.getChangeHistoryInfo(certification.id);
		ChangeHistory changeHistory = new ChangeHistory();
		
		if(histories.size() == 0) {
			throw new ErrorOnPageException("There isn't any change history.");
		}
		
		for(ChangeHistory history : histories) {
			if(history.field.equals("Status") && history.newValue.equals("Expired")) {
				changeHistory = history;
			}
		}

		if(DateFunctions.compareDates(new Date(changeHistory.changeDate), new Date(expectedExpireDateTimeInMS)) != 0) {
			logger.error("Change History - Date/Time is wrong. Actual value is: " + changeHistory.changeDate + " but expected value is: " + expectedExpireDateTimeInMS);
			result &= false;
			
			//Thu Mar 29 2012 12:55 AM
		}
		
		expectedChangeHistory.object = "Customer Certification-" + certification.type + "(" + certification.id + ")";
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
		if(!changeHistory.location.equals(expectedChangeHistory.location)) {
			logger.error("Change History - Location is wrong.");
			result &= false;
		}
		
		if(result) {
			logger.info("Audit Log is correct.");
		} else {
			throw new ErrorOnPageException("Audit Log is incorrect.");
		}
		
		customerChangeHistoryPage.clickReturnToCustomerDetail();
		certificationPage.waitLoading();
	}
}
