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
 *              check point: verify edit successful
 * @Preconditions:
 * @SPEC:Use Case Specification: Edit Privilege Text Display
 * @Task#:AUTO-591
 * 
 * @author szhou
 * @Date  Sep 27, 2011
 */
public class Edit_VerifyEditSuccess extends LicenseManagerTestCase{
	private PrivilegeTextDisplay textInfo_edit = new PrivilegeTextDisplay();
	private String privilegeCode;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductTextDisplayPgFromTopMenu(privilegeCode);
		lm.deactivatePrivilegeTextDisplayRecords();//added by Peter Zhu
		
		// add new text (Preconditions)
		textInfo_edit.id = lm.addPrivilegeTextDisplay(textInfo_edit);
		
		// edit text successful
		textInfo_edit.displayType = "Text displayed in pop-up when product clicked";
		textInfo_edit.id = lm.editTextDisplayForProduct(textInfo_edit);
		boolean succ = this.verifyEditTextDisplaySuccess(textInfo_edit);
		
		if (!succ) {
			throw new ActionFailedException(
					"Failed to edit a text for product.");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "NL1";

		textInfo_edit.displayType = "Text displayed in pop-up when product clicked";
		textInfo_edit.text = "edit text qa auto test";
		textInfo_edit.effectiveFromDate = DateFunctions.getDateAfterToday(2);
		textInfo_edit.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		textInfo_edit.creationLocation= login.location.split("/")[1];
	}

	private boolean verifyEditTextDisplaySuccess(PrivilegeTextDisplay textInfo) {
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		LicMgrPrivilegeEditTextDisplayWidget edittext = LicMgrPrivilegeEditTextDisplayWidget
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
		edittext.waitLoading();
		if (edittext.compareTextDisplayDetailInfo(textInfo)) {
			logger
					.info("Edit text for product successful on the text detail page.");
		} else {
			logger.info("Failed to edit '" + textInfo.id
					+ " ' for product on the text detail page.");
			return false;
		}
		edittext.clickOK();
		textPage.waitLoading();
     
		return true;
	}
}
