package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddTextDisplayWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify whether the error message is displayed correct or not when adding a record which has the same display type and text with existing one
 * @Preconditions: Need an existing privilege product
 * @SPEC: <<Add Privilege Text Display.doc>>
 * @Task#: AUTO-605
 * 
 * @author qchen
 * @Date  Sep 2, 2011
 */
public class Add_ConflictWithTypeAndText extends LicenseManagerTestCase {
	private PrivilegeTextDisplay textDisplayInfo = new PrivilegeTextDisplay();
	private LicMgrPrivilegeAddTextDisplayWidget addTextDisplayWidget = LicMgrPrivilegeAddTextDisplayWidget.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(privilege.code);
		//clean up environment as precondition
		lm.deactivatePrivilegeTextDisplayByText(textDisplayInfo.text);
		
		//adding a existing privilege text display record as precondition
		textDisplayInfo.id = lm.addPrivilegeTextDisplay(textDisplayInfo, true);
		
		//There is already an existing Privilege Text record for the same Privilege that is also "Active" and has the same values as this record for the
		//following: Display Type and Text(using case-insensitive text matching).
		String errorMsg_sameDisplayTypeAndText = "Another active Privilege Text record " + textDisplayInfo.id + " already exists for the same Privilege " +
										"with the same Display Type and Text. Duplicate active records are not allowed.";
		textDisplayInfo.effectiveFromDate = DateFunctions.getDateAfterToday(1);
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(3);
		String actualMsg = lm.addPrivilegeTextDisplay(textDisplayInfo);
		this.verifyErrorMessage(actualMsg, errorMsg_sameDisplayTypeAndText);
		addTextDisplayWidget.clickCancel();
		ajax.waitLoading();
		
		//clean up
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "QQQ";

		textDisplayInfo.displayType = "Alert warning text displayed after product selected";
		textDisplayInfo.text = "Add Privilege Text Display - ConflictWithTypeAndText";
		textDisplayInfo.effectiveFromDate = DateFunctions.getToday();
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(2);
	}

	/**
	 * 
	 * @param actualMsg
	 * @param expectedMsg
	 * @return
	 */
	private boolean verifyErrorMessage(String actualMsg, String expectedMsg) {
		logger.info("Verify whether the actual error message displayed at Add Text Display Widget is correct with expected.");
		if(!actualMsg.equalsIgnoreCase(expectedMsg)) {
			logger.error("Actual error message doesn't match the expected. The expected is: " + expectedMsg + ", but the actual is: " + actualMsg);
			return false;
		} else {
			logger.info("Error message - " + actualMsg + " is displayed correctly.");
			return true;
		}
	}
}
