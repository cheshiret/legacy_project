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
 * @Date  Jul 15, 2014
 */
public class ReversePrivilegeOrderFunction extends FunctionCase {
	LicenseManager lm = com.activenetwork.qa.awo.keywords.orms.LicenseManager.getInstance();
	LoginInfo login;
	String ordNum;
	Payment pay = new Payment();
	String operateReason;
	String operateNote = "Automation Test";

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		
		initializeReason();
		lm.reversePrivilegeOrdToCart(operateReason, operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		ordNum = (String)param[1];
	}
	
	public void initializeReason(){
		if(login.location.startsWith("AB")){
			operateReason = "3 - Other";
			pay.payType = "Cash*";
		}else operateReason = "14 - Other";
	}
}

