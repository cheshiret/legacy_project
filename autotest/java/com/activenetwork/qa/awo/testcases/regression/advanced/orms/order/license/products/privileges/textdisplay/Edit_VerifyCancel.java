/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the steps taken by the User to edit an
 *              existing Privilege Text record for a particular Privilege Product.
 *              check point:verify cancel edit text display action
 * @Preconditions:
 * @SPEC:Use Case Specification: Edit Privilege Text Display
 * @Task#:AUTO-591
 * 
 * @author szhou
 * @Date  Sep 27, 2011
 */
public class Edit_VerifyCancel extends LicenseManagerTestCase{
	private PrivilegeTextDisplay textInfo_edit = new PrivilegeTextDisplay();
	private String privilegeCode;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductTextDisplayPgFromTopMenu(privilegeCode);
		
		lm.deactivatePrivilegeTextDisplayRecords();//added by Peter Zhu

		// add new text (Preconditions)
		textInfo_edit.id = lm.addPrivilegeTextDisplay(textInfo_edit);
		
		// cancel edit text 
		textInfo_edit.text = "edit text cancel qa auto test";
		lm.editTextDisplayForProduct(textInfo_edit, true);
		boolean cancel = this.verifyEditTextDisplayCancel(textInfo_edit);
		
		if (!cancel) {
			throw new ActionFailedException(
					"Failed to cancel edit a text for product.");
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
		
	}
	
	private boolean verifyEditTextDisplayCancel(PrivilegeTextDisplay textInfo) {
		LicMgrPrivilegeTextDisplayPage textPage = LicMgrPrivilegeTextDisplayPage
				.getInstance();
		logger.info("Verify cancel text display success or not ...");

		return !textPage.checkTextDisplayRecordExists("Text",textInfo.text);//updated by Peter Zhu
	}

}
