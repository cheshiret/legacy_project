package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify the cancellation and proceeding functionalities work correctly
 * @Preconditions: Need an existing privilege product
 * @SPEC: <<Add Privilege Text Display.doc>>
 * @Task#: AUTO-605
 * 
 * @author qchen
 * @Date  Sep 2, 2011
 */
public class Add_CancelSuccess extends LicenseManagerTestCase {
	private PrivilegeTextDisplay textDisplayInfo = new PrivilegeTextDisplay();
	private LicMgrPrivilegeTextDisplayPage privilegeTextDisplayPg = LicMgrPrivilegeTextDisplayPage.getInstance();
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(textDisplayInfo.privilegeCode);
		lm.deactivatePrivilegeTextDisplayRecords();//added by Peter Zhu
		
		//User confirms their entries and desire to proceed in adding the Privilege Text and cancel
		lm.addPrivilegeTextDisplay(textDisplayInfo, false);
		runningResult &= !privilegeTextDisplayPg.checkTextDisplayRecordExists("Text", textDisplayInfo.text);
		
		//User confirms their entries and desire to proceed in adding the Privilege Text and click OK
		textDisplayInfo.id = lm.addPrivilegeTextDisplay(textDisplayInfo, true);
		runningResult &= privilegeTextDisplayPg.compareTextDisplayRecords(textDisplayInfo);
		
		//clean up
		lm.deactivatePrivilegeTextDisplay(textDisplayInfo.id);
		
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
		
		textDisplayInfo.privilegeCode = "QQQ";
		textDisplayInfo.displayType = "Alert warning text displayed after product selected";

		textDisplayInfo.text = "Add Privilege Text Display - " + System.currentTimeMillis();
		textDisplayInfo.effectiveFromDate = DateFunctions.getToday();
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(2);
	}
}
