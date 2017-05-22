package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This cases is used to verify the business rules when adding privilege quantity control record
 * @Preconditions: Need an existing privilege record
 * @SPEC: <<Add Privilege Location Class Settings.doc>>
 * @Task#: AUTO-673
 * 
 * @author qchen
 * @Date  Sep 8, 2011
 */
public class VerifyBusinessRule extends LicenseManagerTestCase {
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	private LicMgrPrivilegeAddQuantityControlWidget addQuantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(quantityControl.productCode);
		
		quantityControlPg.clickAddQuantityControl();
		addQuantityControlWidget.waitLoading();
		//1. the location classes list shall be displayed with the "All" option as the first option in the list followed by the Location Classes
		//defined for the Contract sorted by the Code in ascending order
		logger.info("Verify the Location Class options are displayed with 'All' as first option and followed by the Location Classes defined for the Contract sorted by the Code in ascending order.");
		try{
			addQuantityControlWidget.verifyLocationClassDropdownList();
			logger.info("Location Class is displayed by Business Rule defination.");
		} catch(Exception e) {
			logger.error("Location Class should be displayed by Business Rule defination.");
			runningResult &= false;
		}
		
		//2. the system shall set the Status of the Privilege Location Class Settings to "Active" and not editable
		logger.info("Verify wether the Status of privilege Location Class Settings is set to 'Active and not editable.'");
		if(!addQuantityControlWidget.checkQuantityControlStatusIsEditable() && addQuantityControlWidget.getInitialStatus().equalsIgnoreCase("Active")) {
			logger.info("Status is not editable and the initial value is 'Active'.");
		} else {
			logger.error("Status is not editable OR the value is not 'Active'.");
			runningResult &= false;
		}
		
		//3.The System shall require the User to specify the Multiple Sales Allowance per Customer in the selected Location Class for the Privilege Product.
		runningResult &= addQuantityControlWidget.verifyMultiSalesAllowanceOptionBusinessRule("No Multiple Allowed", false, false);
		runningResult &= addQuantityControlWidget.verifyMultiSalesAllowanceOptionBusinessRule("Yes (Within Same Transaction only)", true, false);
		runningResult &= addQuantityControlWidget.verifyMultiSalesAllowanceOptionBusinessRule("Yes (Within Same License Year only)", true, true);
		runningResult &= addQuantityControlWidget.verifyMultiSalesAllowanceOptionBusinessRule("Yes (Regardless of License Year)", true, true);
		addQuantityControlWidget.clickCancel();
		
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
		
		quantityControl.productCode = "LCS";
	}
}
