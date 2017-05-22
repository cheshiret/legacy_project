package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddTextDisplayWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify whether the error messages displayed on the widget are correct with expected when the user enter invalid entries
 * @Preconditions: Need an existing privilege product
 * @SPEC: <<Add Privilege Text Display.doc>>
 * @Task#: AUTO-605
 * 
 * @author qchen
 * @Date  Sep 2, 2011
 */
public class Add_ErrorMessage extends LicenseManagerTestCase {
	private PrivilegeTextDisplay textDisplayInfo = new PrivilegeTextDisplay();
	private LicMgrPrivilegeAddTextDisplayWidget addTextDisplayWidget = LicMgrPrivilegeAddTextDisplayWidget.getInstance();
	private String errorMsg_displayTypeIsNull = "The Display Type is required. Please select the Display Type from the list provided.";
	private String errorMsg_effectiveFromDateIsNull = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
	private String errorMsg_effectiveFromDateIsGreaterThanEffectiveToDate = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
	private String errorMsg_textIsNull = "The Text to be displayed is required. Please specify the Text.";
	private String actualErrorMsg;
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(privilege.code);
		
		//1. The Display Type is not specified, i.e. left null/blank
		textDisplayInfo.displayType = "";
		actualErrorMsg = lm.addPrivilegeTextDisplay(textDisplayInfo);
		runningResult &= this.verifyErrorMessage(actualErrorMsg, errorMsg_displayTypeIsNull);
		
		//2. Effective From Date is left blank
		textDisplayInfo.displayType = "Alert warning text displayed after product selected";
		textDisplayInfo.effectiveFromDate = "";
		actualErrorMsg = lm.addPrivilegeTextDisplay(textDisplayInfo);
		runningResult &= this.verifyErrorMessage(actualErrorMsg, errorMsg_effectiveFromDateIsNull);
		
		//3. Effective From Date entry is not a valid date
		textDisplayInfo.effectiveFromDate = "automation";
		addTextDisplayWidget.setupTextDisplayInfo(textDisplayInfo);
		String actualEffectiveFromDate = addTextDisplayWidget.getEffectiveFromDateValue();
		runningResult &= this.verifyErrorMessage(actualEffectiveFromDate, "");
		
		//4. Effective To Date entry is specified but is not a valid date
		textDisplayInfo.effectiveFromDate =  DateFunctions.getToday();
		textDisplayInfo.effectiveToDate = "2012/12/32";
		addTextDisplayWidget.setupTextDisplayInfo(textDisplayInfo);
		String actualEffectiveToDate = addTextDisplayWidget.getEffectiveToDateValue();
		addTextDisplayWidget.clickCancel();
		if(DateFunctions.compareDates("Tue Jan 01 2013", actualEffectiveToDate) != 0){
			logger.error("Expect:Tue Jan 01 2013, but actual is:"+actualEffectiveToDate);
			runningResult &= false;
		}
		
		//5. Effective To Date entry is specified but entry for Effective From Date is greater than the entry for Effective To Date
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(-2);
		actualErrorMsg = lm.addPrivilegeTextDisplay(textDisplayInfo);
		runningResult &= this.verifyErrorMessage(actualErrorMsg, errorMsg_effectiveFromDateIsGreaterThanEffectiveToDate);
		addTextDisplayWidget.clickCancel();
		
		//6. The Text is not specified, i.e. left null/blank
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(2);
		textDisplayInfo.text = "";
		actualErrorMsg = lm.addPrivilegeTextDisplay(textDisplayInfo);
		runningResult &= this.verifyErrorMessage(actualErrorMsg, errorMsg_textIsNull);
		addTextDisplayWidget.clickCancel();
		
		//final verification
		if(!runningResult) {
			throw new ErrorOnPageException("All checkpoints are NOT passed. Please refer the log for details info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "QQQ";

		textDisplayInfo.displayType = "Alert warning text displayed after product selected";
		textDisplayInfo.text = "Add Privilege Text Display - " + System.currentTimeMillis();
		textDisplayInfo.effectiveFromDate = DateFunctions.getToday();
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(2);
		textDisplayInfo.creationLocation = login.location.split("/")[1];
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
