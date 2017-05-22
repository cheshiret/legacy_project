package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify adding privilege location class settings successfully or not
 * @Preconditions: Need an existing privilege which code is QQQ
 * @SPEC: <<Add Privilege Location Class Settings.doc>> <<View Privilege Location Class Settings List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 31, 2011
 */
public class AddLocationClassSettings extends LicenseManagerTestCase {
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(quantityControl.productCode);
		quantityControl.id = quantityControlPg.getQuantityControlID(quantityControl.locationClass,quantityControl.multiSalesAllowance);
		if(!StringUtil.isEmpty(quantityControl.id)){
			lm.deactivatePrivilegeQuantityControl(quantityControl.id);
		}
		
		//add privilege Location Class Settings
		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);
		
		//verify location class settings list info
		this.verifyQuantityControlListInfo(quantityControl);
		
		//verify location class settings detail info
		this.verifyQuantityControlDetailInfo(quantityControl);
		
		//clean up
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		quantityControl.productCode = "QQQ";
		
		quantityControl.status = OrmsConstants.ACTIVE_STATUS;
		quantityControl.locationClass = "06 - State Parks Agent";
		quantityControl.multiSalesAllowance = "Yes (Within Same License Year only)";
		quantityControl.maxQuantityPerTran = "10";
		quantityControl.maxAllowed = "20";
		quantityControl.replacementMaxAllowed = "1";
		quantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.createLocation = login.location.split("/")[1].trim();
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		quantityControl.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	/**
	 * Verify whether quantity control list info matches with the expected
	 * @param expectedRecord
	 */
	private void verifyQuantityControlListInfo(QuantityControlInfo expectedRecord) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		
		boolean result = quantityControlPg.checkQuantityControlRecordExists(expectedRecord);
		if(!result) {
			throw new ErrorOnPageException("Quantity Control record - " + expectedRecord.id + " doesn't exist.");
		}
		result &= quantityControlPg.compareQuantityControlListInfo(expectedRecord);
		if(!result) {
			throw new ErrorOnPageException("The Quantity Control record - " + expectedRecord.id + " list info doesn't match expected.");
		} else logger.info("Actual quantity control list info matches with expected.");
	}
	
	/**
	 * Verify whether quantity control detail info matches with the expected
	 * @param expectedRecord
	 * @return
	 */
	private void verifyQuantityControlDetailInfo(QuantityControlInfo expectedRecord) {
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
		LicMgrPrivilegeEditQuantityControlWidget editWidget = LicMgrPrivilegeEditQuantityControlWidget.getInstance();
		
		logger.info("Verify whether Quantity Control Record - " + expectedRecord.id + " information are correct with expected.");
		quantityControlPg.clickQuantityControlID(expectedRecord.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		boolean result = editWidget.compareQuantityControlInfo(expectedRecord);
		editWidget.clickCancel();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
		if(!result) {
			throw new ErrorOnPageException("The Quantity Control Record - " + expectedRecord.id + " detail info are incorrect with expected.");
		} else logger.info("Actual quantity control detail info matches with expected.");
	}
}
