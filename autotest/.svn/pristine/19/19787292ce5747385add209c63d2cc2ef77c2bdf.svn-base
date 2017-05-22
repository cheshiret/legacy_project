/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.bondIssuer;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrBondIssuerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrBondIssuersChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrBondIssuersConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This use case describes the steps taken by the User to edit a Bond Issuer.
 * And to view change info about the bond issuer.
 * @Preconditions:
 * @SPEC:Edit Bond Issuer.doc&View Bond Issuer Info Change History.doc
 * @Task#:Auto-759
 * 
 * @author nding1
 * @Date  2011-12-8
 */
public class EditBondIssuerAndViewChangeInfo extends LicenseManagerTestCase {

	protected LicMgrBondIssuerInfo bondIssuerInfo = new LicMgrBondIssuerInfo();
	protected LicMgrBondIssuerInfo newBondIssuerInfo = new LicMgrBondIssuerInfo();
	
	public void execute() {
		
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		lm.gotoBondIssuersPg();
		
		// add a new bond issuer
		lm.addBondIssuer(bondIssuerInfo);

		// view
		lm.gotoBondIssuerDetailsPage(bondIssuerInfo.businessNm);
		
		this.changeDetail();

		this.verifyBondIssuerInList();

		// view the new bond issuer
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		this.verifyDetailOfBondIssuer();
		
		// view change log
		lm.viewBondIssuerChangeHistory(newBondIssuerInfo.businessNm);
		this.verifyChangeHistory1();
		
		lm.gotoBondIssuerDetailsPageFromChangeInfoPg();
		lm.logOutLicenseManager();

	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		bondIssuerInfo.businessNm = "AddNewBondIssuer"+DateFunctions.getCurrentTime();
		newBondIssuerInfo.businessNm = "ChangeNewBondIssuer"+DateFunctions.getCurrentTime();
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
		bondIssuerInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		bondIssuerInfo.creationLocation = "MS";
		bondIssuerInfo.shortforSate = "MS";
		bondIssuerInfo.location = login.location.split("/")[1];
		
		newBondIssuerInfo.activeBonds = "0";
		newBondIssuerInfo.activeAmountHeld = "$0.00";
		newBondIssuerInfo.country = "United States";
		newBondIssuerInfo.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		newBondIssuerInfo.creationLocation = "MS";
		newBondIssuerInfo.lastModifiedUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		newBondIssuerInfo.lastModifiedLocation = "MS";
		newBondIssuerInfo.lastModifiedDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		newBondIssuerInfo.shortforSate = "GU";
		newBondIssuerInfo.location = login.location.split("/")[1];
	}
	
	private void changeDetail() {

		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.cityOrTown = "Chicago";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.contactAddress = "2199 Innovation Wy";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.firstName = "QA_Change";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.lastName = "Tester_Change";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.email = "alt_11111@reserveamerica.com";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);
		
		newBondIssuerInfo.phone = "(800)331-6053";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.state = "Guam";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);

		newBondIssuerInfo.zipCd = "60682";
		lm.gotoBondIssuerDetailsPage(newBondIssuerInfo.businessNm);
		lm.changeBondIssuerDetails(newBondIssuerInfo);
	}

	private void verifyBondIssuerInList() {

		LicMgrBondIssuersConfigurationPage bondIssuersPg = LicMgrBondIssuersConfigurationPage
				.getInstance();

		logger.info("Verify Bond Issuer in the list.");
		bondIssuersPg.verifyBondIssuerInList(newBondIssuerInfo);
	}
	
	private void verifyDetailOfBondIssuer() {

		LicMgrBondIssuerDetailsPage detailsPg = LicMgrBondIssuerDetailsPage
				.getInstance();
		
		logger.info("Verify detail of Bond Issuer.");
		detailsPg.verifyDetailOfBondIssuer(newBondIssuerInfo);
	}

	private void verifyChangeHistory1() {

		List<List<String>> bondIssuerInfoList = new ArrayList<List<String>>();
		List<String> record = new ArrayList<String>();
		
		record.add("Bond Issuer");
		record.add("Update");
		record.add("ZIP/Postal Code");
		record.add(bondIssuerInfo.zipCd);
		record.add(newBondIssuerInfo.zipCd);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);
		
		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("State");
		record.add(bondIssuerInfo.state);
		record.add(newBondIssuerInfo.state);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("Contact Phone #");
		record.add(bondIssuerInfo.phone);
		record.add(newBondIssuerInfo.phone);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("Contact Email Address");
		record.add(bondIssuerInfo.email);
		record.add(newBondIssuerInfo.email);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("Contact Last Name");
		record.add(bondIssuerInfo.lastName);
		record.add(newBondIssuerInfo.lastName);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("Contact First Name");
		record.add(bondIssuerInfo.firstName);
		record.add(newBondIssuerInfo.firstName);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("Contact Address");
		record.add(bondIssuerInfo.contactAddress);
		record.add(newBondIssuerInfo.contactAddress);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("City/Town");
		record.add(bondIssuerInfo.cityOrTown);
		record.add(newBondIssuerInfo.cityOrTown);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Update");
		record.add("Business Name");
		record.add(bondIssuerInfo.businessNm);
		record.add(newBondIssuerInfo.businessNm);
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);

		record = new ArrayList<String>();
		record.add("Bond Issuer");
		record.add("Add");
		record.add("");
		record.add("");
		record.add("");
		record.add(bondIssuerInfo.creationUser);
		record.add(bondIssuerInfo.location);
		bondIssuerInfoList.add(record);
		
		LicMgrBondIssuersChangeHistoryPage chageHistoryPg = LicMgrBondIssuersChangeHistoryPage.getInstance();
		chageHistoryPg.verifyChangeHistory1(bondIssuerInfoList);
	}
}
