package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PriLandownerAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPriLandownerSubPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriLandownerFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private Data<PriLandownerAttr> landowner;
	private LicenseManager lm = LicenseManager.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicMgrPriLandownerSubPage landownerSubPage = LicMgrPriLandownerSubPage.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		landowner = (Data<PriLandownerAttr>)param[2];
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
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		
		contract = login.contract;
		location = login.location;
		
		lm.gotoPrivilegeListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(landowner.stringValue(PriLandownerAttr.privilegeCode));
		lm.gotoPrivilegeSubTabPage(landowner.stringValue(PriLandownerAttr.privilegeCode), "Landowner");
		landownerSubPage.setupPriLandownerInfo(landowner);
		this.verifyResult();
	}
	
	private void verifyResult() {
		String message = landownerSubPage.getMessage();
		
		if(StringUtil.notEmpty(message)){
			throw new ErrorOnPageException("[FAILED]Add landowner for privilege isn't successfully!!");
		}
	}
}
