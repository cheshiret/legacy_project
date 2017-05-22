package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add hunt parameter function
 * @author Lesley Wang
 * @Date  Aug 19, 2013
 */
public class AddHuntParameterFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private HuntParameterInfo[] huntParams = new HuntParameterInfo[] {};
	private String contract = "";
	private String location = "";
	private String huntCode;
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
	private LicMgrAddHuntParameterWidget addWidget = LicMgrAddHuntParameterWidget.getInstance();

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		huntCode = (String)param[1];
		huntParams = (HuntParameterInfo[])param[2];
		
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
		
		lm.gotoHuntParametersListPg(huntCode);
		List<String> ids = lm.addHuntParameters(huntParams);
		newAddValue = this.verifyResult(ids);
	}

	private String verifyResult(List<String> ids) {
		if (addWidget.exists()) {
			String msg = ids.toString();
			addWidget.clickCancel();
			ajax.waitLoading();
			paramListPg.waitLoading();
			throw new ErrorOnPageException("Failed to add hunt parameter due to " + msg);
		}
		
		return ids.toString();
	}
}
