package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Oct 17, 2013
 */
public class VoidPrivilegeOrder extends FunctionCase {
	LicenseManager lm = com.activenetwork.qa.awo.keywords.orms.LicenseManager.getInstance();
	LoginInfo login;
	String ordNum;
	Payment pay = new Payment();
	String operateReason = "14 - Other";
	String operateNote = "Automation Test";

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		lm.voidPrivilegeOrderToCart(operateReason, operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		ordNum = (String)param[1];
	}
}
