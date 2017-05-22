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
 * @Description: This case is used to verify adding a privilege text display record successfully or not
 * @Preconditions: Need an existing privilege which code is QQQ
 * @SPEC: <<Add Privilege Text Display.doc>> <<View Privilege Text Display List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 31, 2011
 */
public class AddTextDisplay extends LicenseManagerTestCase {
	private PrivilegeTextDisplay textDisplay = new PrivilegeTextDisplay();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(textDisplay.privilegeCode);
		
		//clean up environment
		this.checkAndDeactivateTextDisplay(textDisplay.displayType, textDisplay.text);
		
		//add a privilege text display
		textDisplay.id = lm.addPrivilegeTextDisplay(textDisplay);
		
		//verify privilege text display list info
		this.verifyTextDisplayListInfo(textDisplay);
		
		//verify privilege text display detail info
		this.verifyTextDisplayDetailInfo(textDisplay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		textDisplay.privilegeCode = "QQQ";
		textDisplay.displayType = "Text displayed when mouse hover over product";
		textDisplay.text = "AddTextDisplay_Basic";
		textDisplay.effectiveFromDate = DateFunctions.getToday();
		textDisplay.effectiveToDate = DateFunctions.getDateAfterToday(2);
		textDisplay.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		textDisplay.creationLocation = login.location.split("/")[1];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		textDisplay.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	/**
	 * check whether an Active and same display type record existing or not, if yes, deactivate it as precondition
	 * @param type
	 * @param text
	 */
	private void checkAndDeactivateTextDisplay(String type, String text) {
		LicMgrPrivilegeTextDisplayPage displayPage = LicMgrPrivilegeTextDisplayPage.getInstance();
		
		logger.info("Check and deactivate existing text display records...");
		if(displayPage.displayTypeOnFilterExists(type)){
			
			displayPage.searchTextDisplay(OrmsConstants.ACTIVE_STATUS, type);
			
			if(displayPage.checkTextDisplayRecordExists("Text", text)) {
				String id = displayPage.getTextInfoByText(text).id;
				
				logger.info("Find an existing text display record - " + id);
				lm.deactivatePrivilegeTextDisplay(id);
			} else {
				logger.info("There is no existing record which the text=" + text);
			}
		}
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
		if(!result) {
			throw new ErrorOnPageException("The Privilege Text Display record - " +  display.id + " detail info doesn't match with expected.");
		} else logger.info("Actual Privilege Text Display detail info matches with expected.");
		editWidget.clickOK();
		ajax.waitLoading();
		textDisplayPage.waitLoading();
	}
}
