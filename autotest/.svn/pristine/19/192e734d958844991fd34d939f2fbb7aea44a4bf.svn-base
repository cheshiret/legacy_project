package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditTextDisplayWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify editing a privilege text display successfully or not
 * @Preconditions: Need an existing privilege which code is QQQ
 * @SPEC: <<Edit Privilege Text Display.doc>> <<View Privilege Text Display List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 31, 2011
 */
public class EditTextDisplay extends LicenseManagerTestCase {
	private PrivilegeTextDisplay textDisplay = new PrivilegeTextDisplay();
	private LicMgrPrivilegeTextDisplayPage textDisplayPage = LicMgrPrivilegeTextDisplayPage.getInstance();
	private long timestamp = System.currentTimeMillis();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(textDisplay.privilegeCode);
		
		//add a new privilege text display
		textDisplay.id = lm.addPrivilegeTextDisplay(textDisplay);
		String oldTextDisplayID = textDisplay.id;
		
		//edit the new added privilege text display
		this.prepareEditingParameters();
		textDisplay.id = lm.editTextDisplayForProduct(textDisplay);
		
		//verify privilege text display list info
		this.verifyTextDisplayListInfo(textDisplay);
		
		//verify privilege text display detail info
		this.verifyTextDisplayDetailInfo(textDisplay);
		
		//clean up
		lm.deactivatePrivilegeTextDisplay(textDisplay.id);
		
		//verify the old inactive text display record detail info
		textDisplay.id = oldTextDisplayID;
		this.prepareParametersForInactiveRecord();
		this.wrapParameters(null);
		textDisplayPage.searchTextDisplay(textDisplay.status, textDisplay.displayType);
		this.verifyTextDisplayDetailInfo(textDisplay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		textDisplay.privilegeCode = "QQQ";
		textDisplay.displayType = "Text displayed in pop-up when product clicked";/*"Text displayed in pop-up when privilege clicked";*/
		textDisplay.text = "AddTextDisplayForEdit_Basic - " + timestamp;
		textDisplay.effectiveFromDate = DateFunctions.getToday();
		textDisplay.effectiveToDate = DateFunctions.getDateAfterToday(2);
		textDisplay.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		textDisplay.creationLocation = login.location.split("/")[1];
		textDisplay.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void prepareEditingParameters() {
		textDisplay.displayType = "Text displayed under product prior to selection";
		textDisplay.text = "EditTextDisplay_Basic - " + System.currentTimeMillis();
		textDisplay.effectiveFromDate = DateFunctions.getToday();
		textDisplay.effectiveToDate = DateFunctions.getDateAfterToday(2);
	}
	
	private void prepareParametersForInactiveRecord() {
		textDisplay.status = OrmsConstants.INACTIVE_STATUS;
		
		textDisplay.lastUpdatedUser = textDisplay.creationUser;
		textDisplay.lastUpdatedLocation = textDisplay.creationLocation;
		textDisplay.lastUpdatedDateTime = textDisplay.creationDateTime;
	}
	
	private void verifyTextDisplayListInfo(PrivilegeTextDisplay display) {
		LicMgrPrivilegeTextDisplayPage textDisplayPage = LicMgrPrivilegeTextDisplayPage.getInstance();

		logger.info("Verify Privilege Text Display - " + display.id + " list info.");
		boolean result = textDisplayPage.checkTextDisplayRecordExists("ID", display.id);
		if(!result) {
			throw new ErrorOnPageException("The Privilege Text Display record - " + display.id + " doesn't exist.");
		}
		
		result &= textDisplayPage.compareTextDisplayRecords(display);
		if(!result) {
			throw new ErrorOnPageException("The Privilege Text Display record - " + display.id + " list info doesn't match with expected.");
		} else logger.info("Actual Privilege Text Display list info matches with expected");
	}
	
	private void verifyTextDisplayDetailInfo(PrivilegeTextDisplay display) {
		LicMgrPrivilegeTextDisplayPage textDisplayPage = LicMgrPrivilegeTextDisplayPage.getInstance();
		LicMgrPrivilegeEditTextDisplayWidget editWidget = LicMgrPrivilegeEditTextDisplayWidget.getInstance();
		
		logger.info("Verify Privilege Text Display - " + display.id + " detail info.");
		textDisplayPage.clickTextDisplayId(display.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		boolean result = editWidget.compareTextDisplayDetailInfo(display);
		editWidget.clickCancel();
		ajax.waitLoading();
		textDisplayPage.waitLoading();
		if(!result) {
			throw new ErrorOnPageException("The Privilege Text Display record - " +  display.id + " detail info doesn't match with expected.");
		} else logger.info("Actual Privilege Text Display detail info matches with expected.");
	}
}
