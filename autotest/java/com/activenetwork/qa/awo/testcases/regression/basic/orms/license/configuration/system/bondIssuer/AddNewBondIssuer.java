/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.bondIssuer;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrBondIssuerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrBondIssuersConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This use case describes the steps taken by the User to add&view a new Bond Issuer for the Contract.
 * @Preconditions:
 * @SPEC:Add Bond Issuer.doc&View Bond Issuer List.doc
 * @Task#:Auto-759
 * 
 * @author nding1
 * @Date  2011-12-8
 */
public class AddNewBondIssuer extends LicenseManagerTestCase {

	protected LicMgrBondIssuerInfo bondIssuerInfo = new LicMgrBondIssuerInfo();
	
	public void execute() {
	
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		
		// view the list of Bond Issuers defined for the Contract
		lm.gotoBondIssuersPg();
		
		// add a bond issuer
		lm.addBondIssuer(bondIssuerInfo);
		verifyNewBondIssuerInList();
		
		// view
		lm.gotoBondIssuerDetailsPage(bondIssuerInfo.businessNm);
		verifyDetailOfNewBondIssuer();
		
		lm.gotoBondIssuersPgFromDetailsPg();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		bondIssuerInfo.businessNm = "AddNewBondIssuer"+DateFunctions.getCurrentTime();
		bondIssuerInfo.cityOrTown = "Montgomery";
		bondIssuerInfo.contactAddress = "P.O. Box 11000";
		bondIssuerInfo.state = "Mississippi";
		bondIssuerInfo.zipCd = "36191";
		bondIssuerInfo.firstName = "QA";
		bondIssuerInfo.country = "United States";
		bondIssuerInfo.lastName = "Tester";
		bondIssuerInfo.phone = "(334) 288-0375";
		bondIssuerInfo.email = "alt_00001@reserveamerica.com";
		bondIssuerInfo.activeBonds = "0";
		bondIssuerInfo.activeAmountHeld = "$0.00";
		bondIssuerInfo.shortforSate = "MS";
		bondIssuerInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		bondIssuerInfo.creationLocation = "MS";
		bondIssuerInfo.creationDateTime = DateFunctions.getToday();
	}
	
	private void verifyNewBondIssuerInList() {

		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();

		logger.info("Verify the new Bond Issuer in the list.");
		bondIssuersPg.verifyBondIssuerInList(bondIssuerInfo);
	}
	
	private void verifyDetailOfNewBondIssuer() {

		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage
				.getInstance();
		
		logger.info("Verify detail of the new Bond Issuer.");
		detailsPg.verifyDetailOfBondIssuer(bondIssuerInfo);
	}
	
}
