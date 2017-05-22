package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify the cancellation and proceeding functionalities work correctly
 * @Preconditions: Need an existing privilege product
 * @SPEC: <<Add Privilege Location Class Settings.doc>>
 * @Task#: AUTO-673
 * 
 * @author qchen
 * @Date  Sep 8, 2011
 */
public class CancelAndSucceed extends LicenseManagerTestCase {
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(quantityControl.productCode);
		
		//user confirms their entries and desire to proceed in adding the Privilege Location Class Settings, user cancels, system should abort the process
		lm.addPrivilegeQuantityControl(quantityControl, false);
		if(quantityControlPg.checkQuantityControlRecordExists(quantityControl)) {
			logger.error("Cancellation action failed.");
			runningResult &= false;
		}
		
		//user confirms their entries and desire to proceed in adding the Privilege Location Class Settings, user proceed, system shoul add a new record
		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);
		quantityControl.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		runningResult &= this.verifyQuantityControlRecordInfo(quantityControl);
		
		//final verification
		if(!runningResult) {
			throw new ErrorOnPageException("Some checkpoints are NOT passed, please refer the log for details info.");
		} else {
			//clean up environment
			lm.deactivatePrivilegeQuantityControl(quantityControl.id);
			logger.info("All checkpoints are PASSED.");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		quantityControl.productCode = "LCS";
		quantityControl.status = "Active";
		quantityControl.locationClass = "03 - Lakes Offices";
		quantityControl.multiSalesAllowance = "No Multiple Allowed";
		quantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.createLocation = login.location.split("/")[1].trim();
	}
	
	/**
	 * Verify whether quantity control detail info matches the expected
	 * @param expectedRecord
	 * @return
	 */
	private boolean verifyQuantityControlRecordInfo(QuantityControlInfo expectedRecord) {
		LicMgrPrivilegeEditQuantityControlWidget editWidget = LicMgrPrivilegeEditQuantityControlWidget.getInstance();
		
		logger.info("Verify whether Quantity Control Record - " + expectedRecord.id + " information are correct with expected.");
		quantityControlPg.clickQuantityControlID(expectedRecord.id);
		editWidget.waitLoading();
		boolean result = editWidget.compareQuantityControlInfo(expectedRecord);
		editWidget.clickCancel();
		if(!result) {
			logger.error("The Quantity Control Record - " + expectedRecord.id + " information are incorrect with expected.");
		}
		
		return result;
	}
}
