package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddHuntParameterWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrHuntParametersListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Lottery Parameter function
 * 
 * @author Lesley Wang
 * @Date  Mar 25, 2014
 */
public class AddLotteryParameterFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private List<Data<LotteryParameterInfo>> lotteryParams;
	private String contract = "";
	private String location = "";
	private String lotteryCode;
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrHuntParametersListPage paramListPg = LicMgrHuntParametersListPage.getInstance();
	private LicMgrAddHuntParameterWidget addWidget = LicMgrAddHuntParameterWidget.getInstance();

	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		lotteryCode = (String)param[1];
		lotteryParams = (List<Data<LotteryParameterInfo>>)param[2];
		
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
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryCode);
		lm.gotoLotteryProductParametersPg();
		List<String> ids = lm.addLotteryParameters(lotteryParams);
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
