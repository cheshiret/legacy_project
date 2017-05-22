package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case used to verify cancel edit quantity control action.
 * This case block by DEFECT-30358 
 * @Preconditions:Prepare a privilege which valid to date is not 'Valid To Date of Previous License plus Valid Days/Years'
 * @SPEC:Edit Privilege Location Setting.doc
 * @Task#:Auto-674
 * 
 * @author VZhang
 * @Date  Aug 9, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWiget = 
		LicMgrPrivilegeEditQuantityControlWidget.getInstance();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = 
		LicMgrPrivilegeQuantityControlPage.getInstance();
	private QuantityControlInfo editQuantityControl = new QuantityControlInfo();
	private boolean pass = true;
	
	public void execute() {
		lm.loginLicenseManager(login);	
		//clear up environment
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);		
		lm.deactivatePrivilegeQuantityControlRecords();
		
		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);
		
		editQuantityControl.multiSalesAllowance = "Yes (Within Same License Year only)";
		editQuantityControl.maxQuantityPerTran = "2";
		editQuantityControl.maxAllowed = "2";
		editQuantityControl.replacementMaxAllowed = "1";
		
		lm.gotoEditPrivilegeQuantityControlPg(quantityControl.id);
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		//verify cancel action
		this.verifyCancelAction();
		
		lm.deactivatePrivilegeQuantityControl(quantityControl.id);
		if(!pass){
			throw new ErrorOnPageException("Some check piont is not correct, please check error loger.");
		}
		
		lm.logOutLicenseManager();	
		
	}
	
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "012";
		
		quantityControl.status = "Active";
		quantityControl.locationClass = "03 - Lakes Offices";
		quantityControl.multiSalesAllowance = "No Multiple Allowed";
		quantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.createLocation = login.location.split("/")[1].trim();
		quantityControl.createTime = DateFunctions.getToday("MMM dd yyyy");
		
	}
	
	//verify cancel action
	private void verifyCancelAction(){
		editQuantityControlWiget.clickCancel();
		ajax.waitLoading();
		logger.info("Verify Cancel Action.");
		Object pages = browser.waitExists(editQuantityControlWiget,quantityControlPg);
		if(pages == editQuantityControlWiget){
			pass &= false;
			logger.error("Expect page should be quantity control page.");
		}
		
		quantityControlPg.clickQuantityControlID(quantityControl.id);
		editQuantityControlWiget.waitLoading();
		
		pass &= editQuantityControlWiget.compareQuantityControlInfo(quantityControl);
		editQuantityControlWiget.clickOK();
		quantityControlPg.waitLoading();
	}

}
