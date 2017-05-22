package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;

/**
 * @Description: Remove customer suspensions in License manager
 *
 */
public class RemoveCustAllSuspensions extends FunctionCase {

	LicenseManager lm = com.activenetwork.qa.awo.keywords.orms.LicenseManager.getInstance();
	LoginInfo login;
	Customer cus;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		lm.removeCustAllSuspensions();
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		cus = (Customer)param[1];
	}

}
