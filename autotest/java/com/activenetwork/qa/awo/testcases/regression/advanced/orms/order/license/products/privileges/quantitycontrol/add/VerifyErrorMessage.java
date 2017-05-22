package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddQuantityControlWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify whether the actual error messages displayed are correct or not when enter invalid entries in adding privilege quantity control process
 * @Preconditions: Need an existing privilege product
 * @SPEC: <<Add Privilege Location Class Settings.doc>>
 * @Task#: AUTO-673
 * 
 * @author qchen
 * @Date  Sep 8, 2011
 */
public class VerifyErrorMessage extends LicenseManagerTestCase {
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private QuantityControlInfo existingQuantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeAddQuantityControlWidget addQuantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
	private String errorMsg_maxQuantityAllowedPerTransIsNull = "The Maximum Quantity Allowed per Transaction is required. Please specify the Maximum Quantity Allowed per Transaction.";
	private String errorMsg_maxQuantityAllowedPerTransIsNotGreaterThan0 = "The Maximum Quantity Allowed per Transaction entered is not valid. Please enter an integer value greater than 0.";
	private String errorMsg_maxAllowedPerLicYearIsNull = "The Maximum Allowed is required. Please specify the Maximum Allowed.";
	private String errorMsg_maxAllowedForEachCustIsNull = errorMsg_maxAllowedPerLicYearIsNull;
	private String errorMsg_maxAllowedPerLicYearIsNotGreaterThan1 = "The Maximum Allowed entered is not valid. Please enter an integer value greater than 1.";
	private String errorMsg_maxAllowedForEachCustIsNotGreaterThan1 = errorMsg_maxAllowedPerLicYearIsNotGreaterThan1;
	private String errorMsg_replacementMaxAllowedIsNotGreaterThan0 = "The Replacement Maximum Allowed entered is not valid. Please enter an integer value greater than 0.";
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(quantityControl.productCode);
		
		//1. The Multiple Sales Allowance is not "No Multiple Allowed" and the Maximum Quantity Allowed per Transaction is not specific
		quantityControl.multiSalesAllowance = "Yes (Within Same Transaction Only)";
		quantityControl.maxQuantityPerTran = "";
		String actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxQuantityAllowedPerTransIsNull, actualMsg);
		
		//2. The Maximum Quantity Allowed per Transaction is not an integer value greater than 0
		quantityControl.maxQuantityPerTran = "-1"; //TODO  blocked by DEFECT-30358, the value should be 0
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxQuantityAllowedPerTransIsNotGreaterThan0, actualMsg);
		
		//3. The Multiple Sales Allowance is either "Yes (Within Same License Year only)" or "Yes (Regardless of License Year)",
		//and the Maximum Allowed per License Year/Maximum Allowed for each Customer is not specific
		//a. Multiple Sales Allowance is "Yes (Within Same License Year only)"
		quantityControl.multiSalesAllowance = "Yes (Within Same License Year only)";
		quantityControl.maxQuantityPerTran = "1";
		quantityControl.maxAllowed = "";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxAllowedPerLicYearIsNull, actualMsg);
		
		quantityControl.maxAllowed = "0";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxAllowedPerLicYearIsNull, actualMsg);
		
		//b.Multiple Sales Allowance is "Yes (Regardless of License Year)"
		quantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		quantityControl.maxAllowed = "";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxAllowedForEachCustIsNull, actualMsg);
		
		quantityControl.maxAllowed = "0";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxAllowedForEachCustIsNull, actualMsg);
		
		//4. The Maximum Allowed per License Year/Maximum Allowed for Each Customer is not an integer value greater than 1
		quantityControl.multiSalesAllowance = "Yes (Within Same License Year only)";
		quantityControl.maxQuantityPerTran = "1";
		quantityControl.maxAllowed = "1";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxAllowedPerLicYearIsNotGreaterThan1, actualMsg);
		
		quantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_maxAllowedForEachCustIsNotGreaterThan1, actualMsg);
		
		//5. The Replacement Maximum Allowed is not an integer value greater than 0
		quantityControl.maxAllowed = "2";
		quantityControl.replacementMaxAllowed = "0";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_replacementMaxAllowedIsNotGreaterThan0, actualMsg);
		
		//6. There is already an existing Privilege Location Class Settings record for the same Privilege Product that
		//is also "Active" and that has the same values as this record for the Location Class
		//a. add a quantity control record as precondition
		existingQuantityControl.id = lm.addPrivilegeQuantityControl(existingQuantityControl);
		existingQuantityControl.status = "Active";
		existingQuantityControl.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		//b. add a new quantity control record to verify the error message
		quantityControl.replacementMaxAllowed = "";
		String errorMsg_alreadyExistingRecord = "Another active Product Quantity Control Settings record " + existingQuantityControl.id + " already exists for the same Product with the same Location Class. Duplicate active records are not allowed.";
		actualMsg = lm.addPrivilegeQuantityControl(quantityControl);
		addQuantityControlWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(errorMsg_alreadyExistingRecord, actualMsg);
		
		//clean up environment
		lm.deactivatePrivilegeQuantityControl(existingQuantityControl.id);
		
		//final verification
		if(!runningResult) {
			throw new ErrorOnPageException("Some checkpoints are NOT passed, please refer the log for details info.");
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
		
		quantityControl.productCode = "LCS";
		quantityControl.locationClass = "05 - Dept of Marine Resources";
		quantityControl.multiSalesAllowance = "No Multiple Allowed";
		
		existingQuantityControl.productCode = quantityControl.productCode;
		existingQuantityControl.locationClass = quantityControl.locationClass;
		existingQuantityControl.multiSalesAllowance = "No Multiple Allowed";
		existingQuantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		existingQuantityControl.createLocation = login.location.split("/")[1].trim();
	}
	
	/**
	 * Verify whether the actual message is correct with expected
	 * @param expectedMsg
	 * @param actualMsg
	 */
	private boolean verifyErrorMsg(String expectedMsg, String actualMsg) {
		ajax.waitLoading();//Occurs after click cancel button addQuantityControlWidget
		boolean result = true;
		logger.info("Verify the actual error message displayed correctly.");
		if(!expectedMsg.equalsIgnoreCase(actualMsg)) {
			logger.error("Actual message doesn't match the expected. Actual message is :" + actualMsg + ", but the expected is :" + expectedMsg);
			result &= false;
		} else {
			logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
		}
		
		return result;
	}
}
