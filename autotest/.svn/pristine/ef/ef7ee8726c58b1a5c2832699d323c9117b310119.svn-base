package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPrivilegeProductFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private PrivilegeInfo privilege = new PrivilegeInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
			.getInstance();
	private boolean loggedin=false, deleteExisting = false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location, schema;
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		privilege = (PrivilegeInfo)param[2];
		deleteExisting = Boolean.parseBoolean((String) param[3]);
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
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
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		if(deleteExisting && !lm.checkPrivilegeExisted(privilege.code+"U", schema)) {
			if(lm.getPrivilegeStatus(privilege.code, schema).equals(String.valueOf(ACTIVE))) 
				lm.changePrivilegeProductStatus(privilege.code, "Inactive");
			privilege.code = privilege.code+"U";
			privilege.name = privilege.name+"U";
			lm.addPrivilegeProduct(privilege);
			this.verifyResult();
		} else if(!lm.checkPrivilegeExisted(privilege.code, schema)) {
			lm.addPrivilegeProduct(privilege);
			this.verifyResult();
		} else logger.info("The Privilege(CD#=" + privilege.code + ") already exists in System. No need to add.");
		newAddValue = privilege.code;
	}

	public void verifyResult() {
		LicMgrCreateNewPriviledgePage createNewPrivilegeProductPage = LicMgrCreateNewPriviledgePage
				.getInstance();
		if (createNewPrivilegeProductPage.exists()) {
			String errMsg = createNewPrivilegeProductPage.getWarningMessage();
			createNewPrivilegeProductPage.clickCancel();
			privilegeListPage.waitLoading();
			throw new ErrorOnPageException("[FAILED]create privilege product failed:code="
					+ privilege.code + ",name=" + privilege.name
					+ ", due to: " + errMsg);
		} else {
			privilege.status = OrmsConstants.ACTIVE_STATUS;
			boolean passed = privilegeListPage.comparePrivilegeInfo(privilege);
			if (!passed) {
				throw new ErrorOnPageException("[FAILED]Create privilege product failed:code="
						+ privilege.code + ",name=" + privilege.name);
			} else {
				logger.info("[PASSED]create privilege product :code="
						+ privilege.code + ",name=" + privilege.name);
			}
		}
	}
}
