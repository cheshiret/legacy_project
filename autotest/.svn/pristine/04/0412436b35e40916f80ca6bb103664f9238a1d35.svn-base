package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddLotteryProductFunction extends FunctionCase {
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private String schema, loggedInContract, loggedInLocation;
	private boolean isLoggedIn = false;
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		lottery = (HFLotteryProduct)param[1];
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
	}

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
		if(!lm.verifyProductExistInSys(schema, lottery.getCode(), lottery.getDescription())) {
			lm.addLotteryProduct(lottery);
			if(!lm.verifyProductExistInSys(schema, lottery.getCode(), lottery.getDescription())) throw new ErrorOnDataException("Create Privilege Lottery product(CD#=" + lottery.getCode() + ") failed.");
		} else logger.info("The Privilege Lottery product(CD#=" + lottery.getCode() + ") already exists in System. No need to add.");
		
		newAddValue = lottery.getCode();
	}
}
