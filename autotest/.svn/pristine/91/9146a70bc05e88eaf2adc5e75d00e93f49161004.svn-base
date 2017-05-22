package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddTextDisplayWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Aug 6, 2014
 */
public class AddPrivilegeTextDisplayFunction extends FunctionCase {
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private String contract, location;
	
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicMgrPrivilegeTextDisplayPage priviTextDisplayPg = LicMgrPrivilegeTextDisplayPage.getInstance();
	private LicMgrPrivilegeAddTextDisplayWidget addTextDisplayWidget = LicMgrPrivilegeAddTextDisplayWidget.getInstance();
	
	private PrivilegeTextDisplay textInfo = new PrivilegeTextDisplay();
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		textInfo = (PrivilegeTextDisplay)param[2];
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
		
		lm.gotoPrivilegeTextDisplayPage(textInfo.privilegeCode);
		lm.addPrivilegeTextDisplay(textInfo);
		
		newAddValue = this.verifyResult();
	}
	
	public String verifyResult(){
		String failedMsg = "Add privilege text display failed:" +"privilege code="+textInfo.privilegeCode+".";
		if(addTextDisplayWidget.exists()) {
			 String errorMsg = addTextDisplayWidget.getErrorMessage();
			 addTextDisplayWidget.clickCancel();
			 ajax.waitLoading();
			 priviTextDisplayPg.waitLoading();
			 throw new ErrorOnPageException(failedMsg +	" Due to: " + errorMsg);	 
		 }
		 
		 String id = priviTextDisplayPg.getTextDisplayIdByText(textInfo.text);
		 if (id == null || !id.matches("\\d+")) {
			 throw new ErrorOnPageException(failedMsg);
		 }
		 logger.info("Successfully Add Text Display!");
		 return id;
	}
}
