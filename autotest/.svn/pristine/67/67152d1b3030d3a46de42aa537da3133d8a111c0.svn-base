package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddTextDisplayWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify the business rule of Adding Privilege Text Display
 * @Preconditions: Need an existing privilege product
 * @SPEC: <<Add Privilege Text Display.doc>>
 * @Task#: AUTO-605
 * 
 * @author qchen
 * @Date  Sep 2, 2011
 */
public class Add_BusinessRule extends LicenseManagerTestCase {
	private LicMgrPrivilegeTextDisplayPage textDisplayPage = LicMgrPrivilegeTextDisplayPage.getInstance();
	private LicMgrPrivilegeAddTextDisplayWidget addTextDisplayWidget = LicMgrPrivilegeAddTextDisplayWidget.getInstance();
	private boolean runningResult = true;
	private String expectedOriginalStatus;
	private List<String> expectedDisplayTypeOptions = new ArrayList<String>();
	private TimeZone tz;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeTextDisplayPage(privilege.code);
		this.verifyBusinessRule();
		
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

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		tz = DataBaseFunctions.getContractTimeZone(schema);
		privilege.code = "QQQ";
		
		expectedOriginalStatus = "Active";
		expectedDisplayTypeOptions.add("Alert warning text displayed after (privilege|product) selected");
		expectedDisplayTypeOptions.add("Text displayed in pop-up when (product|privilege) clicked");
		expectedDisplayTypeOptions.add("Text displayed under (product|privilege) prior to selection");
		expectedDisplayTypeOptions.add("Text displayed under (product|privilege) after selection");
		expectedDisplayTypeOptions.add("Text displayed when mouse hover over (product|privilege)");
	}
	
	private void verifyBusinessRule() {
		logger.info("Verify the business rule of Adding Privilege Text Display.");
		textDisplayPage.clickAddTextDisplay();
		addTextDisplayWidget.waitLoading();
		
		boolean isIDEditable = addTextDisplayWidget.checkIDFieldEditable();
		boolean isStatusEditable = addTextDisplayWidget.checkStatusFieldEditable();
		String actualOriginalStatus = addTextDisplayWidget.getStatus();
		List<String> actualDisplayTypeOptions = addTextDisplayWidget.getDisplayTypeElements();
		actualDisplayTypeOptions.remove(0);//the first option is null
		String actualOrginalEffectiveFromDtae = addTextDisplayWidget.getEffectiveFromDateValue();
		addTextDisplayWidget.clickCancel();
		
		if(isIDEditable) {
			logger.error("ID field should be NOT editable.");
			runningResult &= false;
		}
		if(isStatusEditable) {
			logger.error("Status field shoule be NOT editable.");
			runningResult &= false;
		}
		if(!MiscFunctions.compareResult("Status of the Privilege Text", expectedOriginalStatus, actualOriginalStatus)) {
			runningResult &= false;
		}
		for(int i = 0; i < expectedDisplayTypeOptions.size(); i ++) {
			if(!actualDisplayTypeOptions.get(i).matches(expectedDisplayTypeOptions.get(i))) {
				logger.error("The " + (i + 1) + " option of Display Type is not correct. Expect one like:"+expectedDisplayTypeOptions.get(i)+", but actual one is:"+actualDisplayTypeOptions.get(i));
				runningResult &= false;
			} else {
				logger.info("The " + (i + 1) + " option of Display Type is correct.");
			}
		}
		if(DateFunctions.compareDates(DateFunctions.getToday(tz), actualOrginalEffectiveFromDtae) != 0) {
			logger.error("The Effective From Date shold be specified to the Current Date by default.");
			runningResult &= false;
		}
	}
}
