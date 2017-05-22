package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Assign Allocation Type to privilege and assign the allocation privilege to other privileges
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Mar 26, 2014
 */
public class AssignAllocTypeToPrivFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String contract, location, allocationType, allocPrivCode;
	private String[] privCodesWithAllocPriv;
	private boolean loggedin;
	private LicMgrPrivilegeProductDetailsPage privDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
	
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
		
		// Assign allocation type to privilege
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(allocPrivCode);
		privDetailsPg.applyToSelectAllocationType(allocationType);
		this.verifyAssignAllocationType(allocPrivCode);
		newAddValue = "Successfully assign " + allocationType + " to " + allocPrivCode;
		
		// Assign allocation privilege to other privielges
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		for (String code : privCodesWithAllocPriv) {
			if (StringUtil.notEmpty(code)) {
				lm.gotoPrivilegeDetailsPageFromProductListPage(code);
				this.assignAllocationPrivlege(allocPrivCode);
				newAddValue += " Successfully assign the allocation privilege " + allocPrivCode + " to " + code;
			}
		}	
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		allocPrivCode = (String)param[1];
		allocationType = (String)param[2];
		privCodesWithAllocPriv = (String[])param[3];
	}

	private void verifyAssignAllocationType(String code) {
	if (!privDetailsPg.getAllocationType().equalsIgnoreCase(allocationType)) {
		throw new ErrorOnPageException("Fail to assign allocation type to the privilege with code=" + code);
	}
	logger.info("Successfully assign allocation type to the privielge with code=" + code);
	}
	
	private void assignAllocationPrivlege(String allocPriv) {
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage.getInstance();
		logger.info("Assign the allocation privilege " + allocPriv + " to the privilege");
		
		privDetailsPg.selectAllocationPriv(allocPriv);
		privDetailsPg.clickOk();
		ajax.waitLoading();
		privilegePage.waitLoading();
	}
}
