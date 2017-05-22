package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify whether text display searching function works correctly or not
 * @Preconditions: Need an existing privilege product which code is QQQ
 * @SPEC: <<View Privilege Text Display List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Jan 9, 2012
 */
public class SearchTextDisplay extends LicenseManagerTestCase {
	private PrivilegeTextDisplay textDisplay = new PrivilegeTextDisplay();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(textDisplay.privilegeCode);
		
		//add a privilege text display
		textDisplay.id = lm.addPrivilegeTextDisplay(textDisplay);
		
		//search text display record by all searching criteria
		lm.searchPrivilegeTextDisplayByInfo(textDisplay.status, textDisplay.displayType);
		this.verifyTextDisplayExists(textDisplay);
		
		//search text display record by Status: Active
		lm.searchPrivilegeTextDisplayByInfo(textDisplay.status, "");
		this.verifyTextDisplayExists(textDisplay);
		
		//search text display record by Display Type: Text displayed under privilege prior to selection
		lm.searchPrivilegeTextDisplayByInfo("", textDisplay.displayType);
		this.verifyTextDisplayExists(textDisplay);
		
		//clean up
		lm.deactivatePrivilegeTextDisplay(textDisplay.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		textDisplay.status = OrmsConstants.ACTIVE_STATUS;
		textDisplay.privilegeCode = "QQQ";
		textDisplay.displayType = "Text displayed under product prior to selection";/*"Text displayed under privilege prior to selection";*/
		textDisplay.text = "SearchTextDisplay_Basic - " + System.currentTimeMillis();
		textDisplay.effectiveFromDate = DateFunctions.getToday();
		textDisplay.effectiveToDate = DateFunctions.getDateAfterToday(1);
		textDisplay.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		textDisplay.creationLocation = login.location.split("/")[1];
		textDisplay.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void verifyTextDisplayExists(PrivilegeTextDisplay textInfo) {
		LicMgrPrivilegeTextDisplayPage textDisplayPage = LicMgrPrivilegeTextDisplayPage.getInstance();
		
		logger.info("Verify Text Display record(ID#=" + textInfo.id + ") exists in search result list.");
		boolean result = textDisplayPage.checkTextDisplayRecordExists("ID", textInfo.id);
		if(!result) {
			throw new ErrorOnPageException("Text Display record(ID#=" + textInfo.id + ") should exist in search result list.");
		} else logger.info("Text Display record(ID#=" + textInfo.id + ") really exists in search result list.");
	}
}
