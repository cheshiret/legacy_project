package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAssignHuntWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryHuntsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 
 * @author Lesley Wang
 * @Date  Jan 26, 2014
 */
public class AssignHuntsToLotteryFunction extends FunctionCase {
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String schema, loggedInContract, loggedInLocation, lotteryCode;
	private boolean isLoggedIn = false;
	private String[] huntCodes;
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		lotteryCode = (String)param[1];
		huntCodes = (String[])param[2];
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
	}

	@Override
	public void execute() {
		if(isLoggedIn) {
			if(!loggedInContract.equalsIgnoreCase(login.contract)) {
				lm.logOutLicenseManager();
				isLoggedIn = false;
			} else if(!loggedInLocation.equalsIgnoreCase(login.location)) {
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
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryCode);
		lm.assignHuntToLotteryFromLotteryDetailsPg(huntCodes);
		
		newAddValue = verifyResult();
	}
	
	private String verifyResult() {
		LicMgrAssignHuntWidget assignHunt = LicMgrAssignHuntWidget.getInstance();
		LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
		
		if (assignHunt.exists()) {
			String msg = assignHunt.getErrorMsg();
			assignHunt.clickCancel();
			ajax.waitLoading();
			huntsPg.waitLoading();
			throw new ErrorOnPageException("Failed to assign hunts to lottery with code=" + lotteryCode + " due to " + msg);
		}
		String ids = "";
		for (String code : huntCodes) {
			ids += lm.getHuntAssignmentID(schema, code, lotteryCode) + "; ";
		}
		return ids;
	}
}
