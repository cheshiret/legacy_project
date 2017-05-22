package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntPermitWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntPermitsListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Assign Privilege to Hunt Function
 * @author Lesley Wang
 * @Date  Aug 18, 2013
 */
public class AssignPrivToHuntFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private List<HuntPermitInfo> huntPermits = new ArrayList<HuntPermitInfo>();
	private String contract = "";
	private String location = "";
	private String huntCode;
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrAddHuntPermitWidget addHuntPermitPg = LicMgrAddHuntPermitWidget.getInstance();
	private LicMgrHuntPermitsListPage huntPermitListPg = LicMgrHuntPermitsListPage.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		huntCode = (String)param[1];
		huntPermits = (List<HuntPermitInfo>)param[2];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		
		contract = login.contract;
		location = login.location;
		
		lm.gotoHuntsListPg();
		lm.gotoHuntPermitListPgFromHuntListPg(huntCode);
		lm.addHuntPermitInfoWithClickOk(huntPermits);
		
		newAddValue = this.verifyResult();
	}

	private String verifyResult() {
		if (addHuntPermitPg.exists()) {
			String msg = addHuntPermitPg.getErrorMessage();
			addHuntPermitPg.clickCancel();
			ajax.waitLoading();
			huntPermitListPg.waitLoading();
			throw new ErrorOnPageException("Failed to assign permit to hunt with code=" + huntCode + " due to " + msg);
		}
		String ids = "";
		for (HuntPermitInfo huntPermit : huntPermits) {
			ids += huntPermit.getHuntPermitID() + "; ";
		}
		return ids;
	}
}
