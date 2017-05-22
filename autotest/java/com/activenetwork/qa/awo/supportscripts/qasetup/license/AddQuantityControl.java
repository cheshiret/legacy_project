package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddQuantityControlFunction;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddQuantityControl extends SetupCase{
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	LicMgrPrivilegeAddQuantityControlWidget quantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
	private Object[] args = new Object[3];
	private AddQuantityControlFunction func = new AddQuantityControlFunction();
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_qty_control";
	}
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
		quantityControl.productCode = datasFromDB.get("privilegeCode");
		quantityControl.locationClass = datasFromDB.get("locationClass");
		quantityControl.multiSalesAllowance = datasFromDB.get("multisaleSallowance");
		quantityControl.maxQuantityPerTran = datasFromDB.get("maxQuanPerTrans");
		if(StringUtil.notEmpty(datasFromDB.get("MINQUANPERTRANS")))
		quantityControl.minQuantityPerTran = datasFromDB.get("MINQUANPERTRANS");
		quantityControl.maxAllowed = datasFromDB.get("MaxAllowed");
		quantityControl.replacementMaxAllowed = datasFromDB.get("replacementMaxAllowed");
		
		args[2] = quantityControl;
	}
}
