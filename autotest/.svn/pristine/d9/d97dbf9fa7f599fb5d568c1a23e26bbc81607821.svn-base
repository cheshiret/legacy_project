/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * DEFECT-49038:
 * The privilege with requries hunt allow Multiple sales, so deactivate this test case.
 * @Description: This case is used to verify edit a privilege product for big game
 * @Preconditions:
 * @SPEC: TC046727
 * @Task#: AUTO-1235
 * 
 * @author szhou
 * @Date  Sep 24, 2012
 */
public class EditPrivilegeForBigGame_QualityControl extends LicenseManagerTestCase{
	private PrivilegeInfo editpriv = new PrivilegeInfo();
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private String error;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//add a privilege product as precondition
		if(!lm.verifyProductExistInSys(schema, editpriv.code, editpriv.name)){
			lm.addPrivilegeProduct(editpriv);
		}
		// add quantity control in the priv as precondition
		lm.gotoPrivilegeDetailsPageFromProductListPage(editpriv.code);
		quantityControl.id=lm.addPrivilegeQuantityControl(quantityControl);
		
		// change status and verify error
		this.changeStatus(error);
		
		//clean up
		lm.gotoPrivilegeDetailsPageFromProductListPage(editpriv.code);
		this.gotoQuantityControlPg();
		lm.deactivatePrivilegeQuantityControl(quantityControl.id);
		lm.logOutLicenseManager();
		
	}
	
	private void gotoQuantityControlPg(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage
				.getInstance();

		privilegeDetailsPg.clickQuantityControlTab();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}
	
	private void changeStatus(String message){
		LicMgrPrivilegesListPage privilegePage = LicMgrPrivilegesListPage
				.getInstance();
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage
				.getInstance();
		
		privilegeDetailsPg.selectHuntsRequired(YES_STATUS);
		ajax.waitLoading();
		privilegeDetailsPg.clickApply();
		ajax.waitLoading();
		privilegeDetailsPg.waitLoading();
		
		String error=privilegeDetailsPg.getMessage();
		if(!message.equals(error)){
			throw new ErrorOnPageException("Error Message is not correct. Expect message is "+message+";But Actually is "+error);
		}
		
		
		privilegeDetailsPg.clickCancel();
		privilegePage.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		editpriv.code = "AEB";
		editpriv.name = "AddPriv_Edit_BigGame";
		editpriv.productGroup = "Privileges";
		editpriv.status = "Active";
		editpriv.validDatePrinting = new String[] { "Print Valid From Date" };
		editpriv.customerClasses = new String[] { "Individual" };
		editpriv.authorizationQuantity = "Return as Single Privilege With Quantity";
		editpriv.invType = "Winter Paddlefish Tags";
		editpriv.displayCategory = "Saltwater Fishing";
		editpriv.displaySubCategory = "Applications";
		editpriv.reportCategory = "Non-Resident Licenses";
		editpriv.huntsRequired = "No";
		quantityControl.productCode = editpriv.code;
		quantityControl.status = "Active";
		quantityControl.locationClass = "All";
		quantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		quantityControl.maxQuantityPerTran="5";
		quantityControl.minQuantityPerTran="2";
		quantityControl.maxAllowed="6";
		editpriv.purchasingName = editpriv.code;
		
		error="The indicator Available via Application Only is \"Yes\" and there is at least one Active Privilege Location Class settings that allows multiple quantities. Please change your entry.";
	}

}
