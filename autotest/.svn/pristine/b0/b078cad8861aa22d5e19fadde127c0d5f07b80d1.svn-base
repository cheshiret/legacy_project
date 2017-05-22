package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrBondIssuersConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
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
 * @Date  Apr 17, 2012
 */
public class AddBondIssuers extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrBondIssuerInfo bondIssuerInfo = new LicMgrBondIssuerInfo();
	private LicMgrSystemConfigurationPage sysConfigPage = LicMgrSystemConfigurationPage.getInstance();
	
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		
		if (!loggedIn || (loggedIn && !sysConfigPage.exists())) {
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		
		// Go to Bond Issuers Configuration Page
		this.gotoBondIssuersConfigPg();
		
		// Check if there is an existing bond issuer with the same business name	
		if (this.doesTheBondIssuerExist(bondIssuerInfo)) {
			// If true, go to the bond issuer detail page and edit the info
			lm.gotoBondIssuerDetailsPage(bondIssuerInfo.businessNm);
			lm.changeBondIssuerDetails(bondIssuerInfo);
		} else {
			// if false, add a new bond issuer.
			lm.addBondIssuer(bondIssuerInfo);			
		}

		this.verifyResult();	
		contract = login.contract;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 0; // the start point in the data pool
		endpoint = 0; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[3];
		logMsg[0] = "Index";
		logMsg[1] = "Bussiness Name";
		logMsg[2] = "Result";
	}
	
	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		bondIssuerInfo.businessNm = dpIter.dpString("businessName");
		bondIssuerInfo.contactAddress = dpIter.dpString("contactAddress");
		bondIssuerInfo.cityOrTown = dpIter.dpString("cityTown");
		bondIssuerInfo.state = dpIter.dpString("state");
		bondIssuerInfo.zipCd = dpIter.dpString("zip");
		bondIssuerInfo.country = dpIter.dpString("country");
		bondIssuerInfo.firstName = dpIter.dpString("contactFName");
		bondIssuerInfo.lastName = dpIter.dpString("contactLName");
		bondIssuerInfo.phone = dpIter.dpString("contactPhone");
		bondIssuerInfo.email = dpIter.dpString("contactEmail");
		
		// Set the default value for the bond issuer info except the business name
		// ToDo: need a common address 
		if ("".equals(bondIssuerInfo.contactAddress)) {
			bondIssuerInfo.contactAddress = "P.O. Box ";
			logger.info("The contact address is empty. Set the default contact address: " + bondIssuerInfo.contactAddress);	
		}
		if ("".equals(bondIssuerInfo.cityOrTown)) {
			bondIssuerInfo.cityOrTown = "Montgomery";
			logger.info("The city or town is empty. Set the default city or town: " + bondIssuerInfo.cityOrTown);	
		}
		if ("".equals(bondIssuerInfo.state)) {
			bondIssuerInfo.state = "Mississippi";
			logger.info("The state is empty. Set the default state: " + bondIssuerInfo.state);
		}
		if ("".equals(bondIssuerInfo.zipCd)) {
			bondIssuerInfo.zipCd = "10001";
			logger.info("The zip code is empty. Set the default zip code: " + bondIssuerInfo.zipCd);
		}
		if ("".equals(bondIssuerInfo.firstName)) {
			bondIssuerInfo.firstName = "QA";
			logger.info("The contact first name is empty. Set the default first name: " + bondIssuerInfo.firstName);
		}
		if ("".equals(bondIssuerInfo.lastName)) {
			bondIssuerInfo.lastName = "Tester";
			logger.info("The contact last name is empty. Set the default last name: " + bondIssuerInfo.lastName);
		}
		if ("".equals(bondIssuerInfo.phone)) {
			bondIssuerInfo.phone = "9999999999";
			logger.info("The contact phone is empty. Set the default contact phone: " + bondIssuerInfo.phone);
		}
		if ("".equals(bondIssuerInfo.email)) {
			bondIssuerInfo.email = lm.getNextEmail();
			logger.info("The contact email is empty. Set the default contact: " + bondIssuerInfo.email);
		}	
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = bondIssuerInfo.businessNm;
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
	
	private boolean doesTheBondIssuerExist(LicMgrBondIssuerInfo bondIssuerInfo) {
		LicMgrBondIssuersConfigurationPage bondIssuersConfigPg = LicMgrBondIssuersConfigurationPage.getInstance();
		return bondIssuersConfigPg.doesTheBondIssuerExist(bondIssuerInfo.businessNm);		
	}
	
	public void verifyResult(){
		if (!this.doesTheBondIssuerExist(bondIssuerInfo)) {
			logger.error("Add bond issuer failed: business name = " + bondIssuerInfo.businessNm);
			logMsg[2] = "Failed";
		} else {
			logMsg[2] = "Passed";
		}
	}
}
