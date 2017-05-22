package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LotteryExecutionConfigInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 11, 2013
 */
public class AddLotteryExecutionConfigFunction extends FunctionCase {

	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private LotteryExecutionConfigInfo config = new LotteryExecutionConfigInfo();
	private boolean isLoggedIn = false;
	private String loggedInContract, loggedInLocation;
	
	@Override
	public void execute() {
		if(isLoggedIn) {
			if(!loggedInContract.equalsIgnoreCase(login.contract)) {
				lm.logOutLicenseManager();
				isLoggedIn = false;
			} else if(loggedInLocation.equalsIgnoreCase(login.location)) {
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		
		if(!isLoggedIn) {
			lm.loginLicenseManager(login);
			isLoggedIn = true;
		}
		
		loggedInContract = login.contract;
		loggedInLocation = login.location;
		
		if(!LicenseMgrHomePage.getInstance().exists()) {
			lm.gotoHomePage();
		}
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryExecutionConfigPage();
		String returnValue = lm.addLotteryExecutionConfig(config);
		if(returnValue.matches("\\d+")) {
			newAddValue = returnValue;
		} else throw new ErrorOnPageException(returnValue);
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		config = (LotteryExecutionConfigInfo)param[1];
	}
}
