package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntsListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: add hunt in License Manager
 * @author Lesley Wang
 * @Date  Aug 8, 2013
 */
public class AddHuntFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private HuntInfo hunt = new HuntInfo();
	private boolean loggedIn = false;
	private String location = "";
	private String contract = "";
	private LicMgrHuntsListPage huntsListPg = LicMgrHuntsListPage.getInstance();
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		hunt = (HuntInfo)param[1];
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedIn && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedIn= false;
		}
		if(login.contract.equals(contract) && loggedIn && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedIn || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedIn= true;
		}
		contract = login.contract;
		location = login.location;
		
		// Add Hunt 
		lm.gotoHuntsListPg();
		lm.addHuntFromHuntListPage(hunt);
		String huntID = huntsListPg.getHuntId(hunt.getHuntCode());
		newAddValue = huntID;		
	}
}
