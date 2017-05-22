package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrBondIssuerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrBondIssuersConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Support scripts for adding bond issuers. 
 * 1. If there is an existing bond issuer with the same business name, the case will go to edit the detail of the bond issuer.
 * 2. The business name should be required. If there are no other info for one record in datapool, the case will use the default values.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Nov 21, 2012
 */
public class AddBondIssuerFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrBondIssuerInfo bondIssuerInfo = new LicMgrBondIssuerInfo();
	private String schema;
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
		bondIssuerInfo = (LicMgrBondIssuerInfo)param[2];
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
		// Go to Bond Issuers Configuration Page
		this.gotoBondIssuersConfigPg();
				
		// Check if there is an existing bond issuer with the same business name. Add if not exist.	
		boolean isExist = this.isBondIssuerExist(bondIssuerInfo.businessNm);
		if (!isExist) {
			lm.addBondIssuer(bondIssuerInfo);	
		}
				
		this.verifyResult(isExist);	
		newAddValue = lm.getBondIssuerIDByName(schema, bondIssuerInfo.businessNm);
	
	}
	
	private void gotoBondIssuersConfigPg() {
		LicMgrSystemConfigurationPage sysConfigPage = LicMgrSystemConfigurationPage.getInstance();
		LicMgrBondIssuersConfigurationPage bondIssuersConfigPage = LicMgrBondIssuersConfigurationPage.getInstance();

		if (!sysConfigPage.exists()) {
			lm.gotoSysConfPgFromTopMenu();
		} 
		if (!bondIssuersConfigPage.exists()) {
			lm.gotoBondIssuersPg();
		}
	}
	
	private boolean isBondIssuerExist(String businessNm) {
		LicMgrBondIssuersConfigurationPage bondIssuersConfigPg = LicMgrBondIssuersConfigurationPage.getInstance();
		return bondIssuersConfigPg.doesTheBondIssuerExist(businessNm);		
	}
	
	public void verifyResult(boolean isExisting){
		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage.getInstance();
		
		if (!isExisting) {
			logger.info("Verify the added bond issuer info....");
			if (!this.isBondIssuerExist(bondIssuerInfo.businessNm)) {
				throw new ErrorOnPageException("Failed to add bond issuer: business name = " + bondIssuerInfo.businessNm);
			}
		} else {
			logger.info("Verify the existing bond issuer info....");
		}
		
		lm.gotoBondIssuerDetailsPage(bondIssuerInfo.businessNm);
		if (!detailsPg.compareBondIssuerInformation(bondIssuerInfo)) {
			throw new ErrorOnPageException("The bond issuer information is wrong! Check logger error!");
		}
		logger.info("Sucessfully add bond issuer: business name = " + bondIssuerInfo.businessNm);
	}
}
