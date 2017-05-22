package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
/**
 * 
 * @Description: Invalidate customer privileges in License manager
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 8, 2013
 */
public class InvalidateCutPrivilegesFunction extends FunctionCase {
	LicenseManager lm = com.activenetwork.qa.awo.keywords.orms.LicenseManager.getInstance();
	LoginInfo login;
	Customer cus;
	String searchLicYear;
	String[] searchStatus ;
	String operateReason = "01 - Operator Error";
	String operateNote = "Automation-test";

	public void execute() {

		lm.loginLicenseManager(login);
		lm.invalidateAllPrivilegesPerCustomer(cus, searchLicYear, searchStatus, operateReason, operateNote);
		lm.logOutLicenseManager();	

	}

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		cus = (Customer)param[1];
		searchLicYear = (String)param[2];
		searchStatus = (String[])param[3];
	}
}

