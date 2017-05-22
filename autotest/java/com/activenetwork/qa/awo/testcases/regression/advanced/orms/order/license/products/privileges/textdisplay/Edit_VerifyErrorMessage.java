/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditTextDisplayWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the steps taken by the User to edit an
 *              existing Privilege Text record for a particular Privilege Product.
 *              check point: 1. error message1-8
 *                           2. check change status from active to inactive
 * @Preconditions:
 * @SPEC:Use Case Specification: Edit Privilege Text Display
 * @Task#:AUTO-591
 * 
 * @author szhou
 * @Date  Sep 27, 2011
 */
public class Edit_VerifyErrorMessage extends LicenseManagerTestCase{
	 private PrivilegeTextDisplay textInfo_new = new PrivilegeTextDisplay();
	 private PrivilegeTextDisplay textInfo_edit = new PrivilegeTextDisplay();
	 private LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage.getInstance();
	 private LicMgrPrivilegeEditTextDisplayWidget editWidget = LicMgrPrivilegeEditTextDisplayWidget.getInstance();
	 private String privilegeCode;
	 private String message1, message2, message3, message4, message5;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductTextDisplayPgFromTopMenu(privilegeCode);
		//clean up environment as precondition
		lm.deactivatePrivilegeTextDisplayByText(textInfo_new.text, textInfo_edit.text);
		
		// add two new text (Preconditions)
		textInfo_new.id = lm.addPrivilegeTextDisplay(textInfo_new);
		textInfo_edit.id = lm.addPrivilegeTextDisplay(textInfo_edit);
		
		// Effective From Date is left blank
		textInfo_edit.effectiveFromDate = "";
		lm.editTextDisplayForProduct(textInfo_edit, false);
		boolean errormess = this.verifyEditTextDisplayError(message1);
		if(errormess){
			this.cancelWidgetAndWaitPageExists();
		} else {
			errormess &= false;
		}
		
		// Effective To Date entry is specified but entry for Effective From Date is greater than the entry for Effective To Date.
		textInfo_edit.effectiveFromDate = "2011-6-37";
		textInfo_edit.effectiveToDate = "2011-6-00";
		lm.editTextDisplayForProduct(textInfo_edit, false);
		boolean errFlg = true;
		errFlg &= this.verifyEditTextDisplayError(message2);
		errFlg &= this.verifyEditTextDisplayField("EffectiveFromDate",
				"Thu Jul 7 2011");// Effective From Date entry is not a valid date.
		errFlg &= this.verifyEditTextDisplayField("EffectiveToDate",
				"Tue May 31 2011");// Effective To Date entry is specified but is not a valid date.
		if(errFlg){
			this.cancelWidgetAndWaitPageExists();
		} else {
			errormess &= false;
		}
		
		// The Text is not specified, i.e. left null/blank.
		textInfo_edit.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		textInfo_edit.effectiveToDate = DateFunctions.getDateAfterToday(5);
		textInfo_edit.text = "";
		lm.editTextDisplayForProduct(textInfo_edit, false);
		if(this.verifyEditTextDisplayError(message3)){
			this.cancelWidgetAndWaitPageExists();
		} else {
			errormess &= false;
		}
	
		// There is already an existing Privilege Text record for the same Privilege that is also "Active" and has the same values as this record for the following: Display Type and Text.
		textInfo_edit.displayType = textInfo_new.displayType;
		textInfo_edit.text = textInfo_new.text;
		message4 = "Another active Product Text record "
				+ textInfo_new.id
				+ " already exists for the same Product with the same Display Type and Text. Duplicate active records are not allowed."; // Lesley[20131206]: update Privilege to Product in the message
		lm.editTextDisplayForProduct(textInfo_edit, false);
		if(this.verifyEditTextDisplayError(message4)){
			this.cancelWidgetAndWaitPageExists();
		} else {
			errormess &= false;
		}
		
		// The specified Effective From Date and Effective To Date entries overlap with the Effective From Date and Effective To Date entries in an existing Privilege Text record for the same Privilege that is also "Active" and has the same value as this record for the following: Display Type.
		textInfo_edit.text = "edit text success qa auto test";
		message5 = "Another active Product Text record "
				+ textInfo_new.id
				+ " already exists for the same Product with the same Display Type and with overlapping Effective From and To Dates. Please inactivate that record first or modify the Effective Dates of that record first to eliminate the overlap.";
		lm.editTextDisplayForProduct(textInfo_edit, false);
		if(this.verifyEditTextDisplayError(message5)){
			this.cancelWidgetAndWaitPageExists();
		} else {
			errormess &= false;
		}
		
		// Only the Status was changed to "Inactive"
		lm.deactivatePrivilegeTextDisplay(textInfo_new.id);
		textInfo_new.creationUser = textInfo_edit.creationUser;//added by Peter Zhu
		textInfo_new.lastUpdatedUser = textInfo_edit.creationUser;//added by Peter Zhu
		textInfo_new.lastUpdatedLocation = "Mississippi Department of Wildlife, Fisheries, and Parks";//added by Peter Zhu
		boolean dea = this.verifyDeactiveTextDisplay(textInfo_new);
		
		lm.deactivatePrivilegeTextDisplay(textInfo_edit.id);
		// edit text successful
		textInfo_edit.displayType = "Text displayed in pop-up when product clicked";
		textInfo_edit.text = "edit text qa auto test";
		textInfo_edit.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		textInfo_edit.effectiveToDate = "";
		textInfo_edit.lastUpdatedUser = textInfo_edit.creationUser;//added by Peter Zhu
		textInfo_edit.lastUpdatedLocation = "Mississippi Department of Wildlife, Fisheries, and Parks";//added by Peter Zhu
		dea &= this.verifyDeactiveTextDisplay(textInfo_edit);
		
		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add product text page displayed wrong.");
		}
		if (!dea) {
			throw new ActionFailedException(
					"Failed to deactive a text for product on the privilege detail page.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "NL1";
		textInfo_new.displayType = "Alert warning text displayed after product selected";
		textInfo_new.text = "new text qa auto test";
		textInfo_new.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		textInfo_new.effectiveToDate = DateFunctions.getDateAfterToday(10);
		textInfo_new.creationLocation = login.location.split("/")[1].trim();

		textInfo_edit.displayType = "Text displayed in pop-up when product clicked";
		textInfo_edit.text = "edit text qa auto test";
		textInfo_edit.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		textInfo_edit.creationLocation = login.location.split("/")[1].trim();
		textInfo_edit.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		message1 = "The Effective From Date is required. Please enter the Effective From Date using the format Ddd Mmm dd yyyy in the field provided.";
		message2 = "The Effective From Date must not be later than the Effective To Date. Please change your entries.";
		message3 = "The Text to be displayed is required. Please specify the Text.";
	}
	
	private void cancelWidgetAndWaitPageExists() {
		editWidget.clickCancel();
		ajax.waitLoading();
		textDisplayPg.waitLoading();
	}
	
	private boolean verifyEditTextDisplayError(String error) {
		LicMgrPrivilegeEditTextDisplayWidget editWidget = LicMgrPrivilegeEditTextDisplayWidget
				.getInstance();
		String message = editWidget.getErrorMessage();

		logger.info("Verify edit text display for privilege error message.");
		if (error.equals(message)) {
			logger.info("The error message '" + error
					+ "' on the edit text display page displayed correct.");
		} else {
			logger.error("The error message '" + error
					+ "' on the edit text display page displayed wrong.");
			return false;
		}
		return true;
	}

	private boolean verifyEditTextDisplayField(String field, String value) {
		LicMgrPrivilegeEditTextDisplayWidget textPg = LicMgrPrivilegeEditTextDisplayWidget.getInstance();

		logger.info("Verify edit text display date field is valid. Field is:"+field);
		String fieldValue = "";
		if ("EffectiveFromDate".equals(field)) {
			fieldValue = textPg.getEffectiveFromDate();
		} else if("EffectiveToDate".equals(field)){
			fieldValue = textPg.getEffectiveToDate();
		}
		
		if(DateFunctions.compareDates(value, fieldValue) != 0){
			logger.error("The "+field+" value is not correct. Expect:"+value+", actual:"+fieldValue);
			return false;
		} else {
			logger.info("The "+field+" value is correct.");
			return true;
		}
	}
	
	private boolean verifyDeactiveTextDisplay(PrivilegeTextDisplay textInfo) {
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		logger.info("Verify deactive text display success or not ...");

		// go to inactive page on the text display page
		textInfo.status = "Inactive";
		textPage.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		textPage.selectStatus(textInfo.status);
		textPage.clickGo();
		ajax.waitLoading();
		
		boolean result =  this.verifyEditTextDisplaySuccess(textInfo);
		
//		textPage.unSelectStatus();
//		textPage.clickGo();
		
		return result;
	}
	
	private boolean verifyEditTextDisplaySuccess(PrivilegeTextDisplay textInfo) {
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		LicMgrPrivilegeEditTextDisplayWidget editWidget = LicMgrPrivilegeEditTextDisplayWidget
				.getInstance();

		logger.info("Verify edit text display success or not ...");

		logger.info("View text list ...");
		if (textPage.compareTextDisplayRecords(textInfo)) {
			logger
					.info("Edit text for product successful on the privilege text display page.");
		} else {
			logger.info("Failed to edit '" + textInfo.id
					+ " ' text for product on the privilege detail page.");
			return false;
		}

		logger.info("View text detail information ...");
		textPage.clickTextDisplayId(textInfo.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		if (editWidget.compareTextDisplayDetailInfo(textInfo)) {
			logger
					.info("Edit text for product successful on the text detail page.");
		} else {
			logger.info("Failed to edit '" + textInfo.id
					+ " ' for product on the text detail page.");
			return false;
		}
		editWidget.clickOK();
		ajax.waitLoading();
		textPage.waitLoading();

		return true;
	}

}
