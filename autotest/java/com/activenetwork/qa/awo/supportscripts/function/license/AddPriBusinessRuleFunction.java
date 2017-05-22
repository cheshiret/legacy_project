package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriBusinessRuleFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode = "";
	private LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	private LicMgrPrivilegeAddBusinessRuleWidget ruleWidget = LicMgrPrivilegeAddBusinessRuleWidget.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		privilegeCode = (String)param[2];
		ruleInfo = (PrivilegeBusinessRule)param[3];
	}

	@Override
	public void execute() {
		if(loggedin&&ruleWidget.exists()){
			ruleWidget.clickCancel();
			ajax.waitLoading();
			rulePage.waitLoading();
		}
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
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		String[] ruleIds = lm.addBusinessRuleForProduct(ruleInfo);
		
		newAddValue = this.verifyResult(ruleIds);
	
	}
	
	private String verifyResult(String[] ids){
		LicMgrPrivilegeAddBusinessRuleWidget addRulePg = LicMgrPrivilegeAddBusinessRuleWidget.getInstance();
		String msg = "Failed to add privilege business rule: Product code="+privilegeCode+",Rule category="+ruleInfo.ruleCategory;
		if (addRulePg.exists()) {
			String errMsg = addRulePg.getErrorMessage();
			addRulePg.clickCancel();
			ajax.waitLoading();
			rulePage.exists();
			throw new ErrorOnPageException(msg + ". Due to: " + errMsg);
		}
		if(!rulePage.exists()){
			throw new ErrorOnPageException(msg);
		}
		String ruleIDs = "";
		for (int i = 0; i < ids.length; i++) {
			String ruleId = ids[i];
			if(StringUtil.isEmpty(ruleId)) {
				throw new ErrorOnPageException("[FAILED]Add business rule to privilege:privilege code ="+privilegeCode+",rule category="+ruleInfo.ruleCategory);
			}else{
				logger.info("[PASSED]Add business rule to privilege:privilege code ="+privilegeCode+",rule category="+ruleInfo.ruleCategory);
		    }
			ruleIDs += ruleId + ", ";
		}
		logger.info("Successfully Add Privilege Business Rule!");
		return ruleIDs.substring(0, ruleIDs.lastIndexOf(", "));
	}
}
