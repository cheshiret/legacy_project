package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
/**
 * 
 * @Description:(P) Verify no Replacement Maximum Allowed in quantity control list, Add and edit quantity control widget when product type as HIP
 * @Preconditions: Have production privilege which product type is "HIP"
 * @SPEC:
 * Add Product Quantity Control Settings- Product Group "Privilege" [TC:099245]
 * Edit Product Quantity Control Settings- Product Group "Privilege" [TC:099248]
 * View Product Quantity Control Settings list- Product Group "Privilege" [TC:099249]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class VerifyRMAWithHIPPrdType extends LicenseManagerTestCase {
	private LicMgrPrivilegeAddQuantityControlWidget addQuantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
	private LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWidget = LicMgrPrivilegeEditQuantityControlWidget.getInstance();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	private String replacementMaximumAllowed;
	private QuantityControlInfo quantityControl = new QuantityControlInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);

		//No Replacement Maximum Allowed
		//Review
		lm.gotoPrivQuantityControlPgFromPrivDetailsPg();
		noReplacementMaximumAllowed(quantityControlPg);

		//Add
		lm.gotoAddPrivQuantityControlWidgetFromPrivQuantityControlList();
		noReplacementMaximumAllowed(addQuantityControlWidget);
		lm.gotoPrivQuantityControlListFromAddPrivQuantityControlWidget();

		//Edit
		List<String> quantityControlIDs = quantityControlPg.getQuantityControlIDs();
		if(quantityControlIDs.size()<1){
			lm.addPrivilegeQuantityControl(quantityControl);
		}
		lm.gotoEditPrivQuantityControlWidgetFromPrivQuantityControlList(quantityControlIDs.get(0));
		noReplacementMaximumAllowed(editQuantityControlWidget);
		lm.gotoPrivQuantityControlListFromEditPrivQuantityControlWidget();

		//Logout
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilege = new PrivilegeInfo();
		privilege.code = "110";
		privilege.name = "HIP";

		replacementMaximumAllowed = "Replacement Maximum Allowed";

		quantityControl.locationClass = "All";
		quantityControl.multiSalesAllowance = "No Multiple Allowed";
	}

	private void noReplacementMaximumAllowed(Page page){
		boolean result = true;

		if(page==addQuantityControlWidget){
			result &= MiscFunctions.compareResult("Replacement Maximum Allowed in add quantity control widget", false, addQuantityControlWidget.isReplacementMaxAllowedExist());
		}else if(page==editQuantityControlWidget){
			result &= MiscFunctions.compareResult("Replacement Maximum Allowed in edit quantity control widget", false, editQuantityControlWidget.isReplacementMaxAllowedExist());
		}else if(page==quantityControlPg){
			result &= MiscFunctions.compareResult("Replacement Maximum Allowed in quantity control list", false, quantityControlPg.getColumnNames().toString().contains(replacementMaximumAllowed));
		}else throw new ErrorOnPageException("Can't find matched page.");

		if(!result){
			throw new ErrorOnPageException("Failed to verify Replacement Maximum Allowed");
		}
		logger.info("Successfully verify Replacement Maximum Allowed");
	}
}
