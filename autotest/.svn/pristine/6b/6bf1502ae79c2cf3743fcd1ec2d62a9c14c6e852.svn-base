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
 * @Description: This case is used to verify editing privilege location class settings
 * @Preconditions: Need an existing privilege which code is QQQ
 * @SPEC: <<Edit Privilege Location Class Settings.doc>> <<View Privilege Location Class Settings List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 31, 2011
 */
public class EditLocationClassSettings extends LicenseManagerTestCase {
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(quantityControl.productCode);
		quantityControl.id = quantityControlPg.getQuantityControlID(quantityControl.locationClass);
		if(!StringUtil.isEmpty(quantityControl.id)){
			lm.deactivatePrivilegeQuantityControl(quantityControl.id);
		}
		
		//add a new location class settings
		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);
		
		//edit the new added location class settings
		this.prepareEditingParameters();
		quantityControl.id = lm.editPrivilegeQuantityControl(quantityControl);
		
		//verify quantity control list info
		this.verifyQuantityControlListInfo(quantityControl);
		
		//verify quantity control detail info
		this.verifyQuantityControlDetailInfo(quantityControl);
		
		//clean up
//		lm.deactivatePrivilegeQuantityControl(quantityControl.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		quantityControl.productCode = "QQQ";
		
		quantityControl.status = OrmsConstants.ACTIVE_STATUS;
		quantityControl.locationClass = "03 - Lakes Offices";
		quantityControl.multiSalesAllowance = "Yes (Within Same Transaction only)";
		quantityControl.maxQuantityPerTran = "11";
		quantityControl.replacementMaxAllowed = "2";
		quantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.createLocation = login.location.split("/")[1].trim();
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		quantityControl.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void prepareEditingParameters() {
		quantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		quantityControl.maxQuantityPerTran = "15";
		quantityControl.maxAllowed = "30";
		quantityControl.replacementMaxAllowed = "3";
		quantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.createLocation = login.location.split("/")[1].trim();
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		quantityControl.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		quantityControl.lastUpdateUser = quantityControl.createUser;
		quantityControl.lastUpdateLocation = quantityControl.createLocation;
		quantityControl.lastUpdateTime = quantityControl.createTime;
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
			throw new ErrorOnPageException("The Quantity Control record - " + expectedRecord.id + " list info doesn't match expeced.");
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
