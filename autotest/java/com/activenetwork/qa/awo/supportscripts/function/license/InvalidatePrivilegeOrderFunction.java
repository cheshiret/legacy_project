package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jul 16, 2014
 */
public class InvalidatePrivilegeOrderFunction extends FunctionCase {
	LicenseManager lm = com.activenetwork.qa.awo.keywords.orms.LicenseManager.getInstance();
	LoginInfo login;
	String ordNum;
	Payment pay = new Payment();
	String operateReason;
	String operateNote = "Automation Test";

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		lm.invalidatePrivilegeOrder(operateReason, operateNote);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		ordNum = (String)param[1];
		operateReason = (String)param[2];
	}
}

