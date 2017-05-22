package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.edit;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case used to verify edit quantity control scuccess.
 * This case block by DEFECT-30358 
 * @Preconditions:Prepare a privilege which valid to date is not 'Valid To Date of Previous License plus Valid Days/Years'
 * @SPEC:Edit Privilege Location Setting.doc
 * @Task#:Auto-674
 * 
 * @author VZhang
 * @Date  Aug 9, 2011
 */
public class VerifyEditSuccess extends LicenseManagerTestCase{
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWiget = 
		LicMgrPrivilegeEditQuantityControlWidget.getInstance();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg = 
		LicMgrPrivilegeQuantityControlPage.getInstance();
	private boolean pass = true;
	private String inactiveID = "";
	
	public void execute() {
		lm.loginLicenseManager(login);	
		//clear up environment
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);
		lm.deactivatePrivilegeQuantityControlRecords();
		
		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);
		
		quantityControl.status = "Active";
		quantityControl.multiSalesAllowance = "Yes (Within Same License Year only)";
		quantityControl.maxQuantityPerTran = "8";
		quantityControl.maxAllowed = "8";
		quantityControl.replacementMaxAllowed = "10";
		quantityControl.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.createLocation = login.location.split("/")[1].trim();
		quantityControl.createTime = DateFunctions.getToday("MMM dd yyyy");
		lm.gotoEditPrivilegeQuantityControlPg(quantityControl.id);
		editQuantityControlWiget.setQuantityControlInfo(quantityControl);
		
		//verify edit success
		//will create a new record, and priori record will be inactive
		this.verifySuccess();
		
		quantityControl.status = "Inactive";
		quantityControl.lastUpdateUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		quantityControl.lastUpdateLocation = login.location.split("/")[1].trim();
		quantityControl.lastUpdateTime = DateFunctions.getToday("MMM dd yyyy");
		lm.deactivatePrivilegeQuantityControl(quantityControl.id);
		//verify inactive success
		//record will be inactive
		this.verifyInactiveSuccess();
	
		quantityControl.status = "Active";
		quantityControl.lastUpdateUser = "";
		quantityControl.lastUpdateLocation = "";
		quantityControl.lastUpdateTime = "";
		lm.activatePrivilegeQuantityControl(quantityControl.id);
		//verify active success
		//will create a new record, and priori record still is inactive
		this.verifyActiveSuccess();
		
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
		
		privilege.code = "mp1";
		
		quantityControl.locationClass = "02 - MDWFP District Office";
		quantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		quantityControl.maxQuantityPerTran = "10";
		quantityControl.maxAllowed = "10";
		
	}
	
	private void verifySuccess(){
		editQuantityControlWiget.clickOK();
		ajax.waitLoading();
		Object pages = browser.waitExists(editQuantityControlWiget,quantityControlPg);
		
		logger.info("Verify edit success.");
		if(pages == editQuantityControlWiget){
			pass &= false;
			logger.error("Expect page should be quanity control page.");
		} else {
			//verify create a new record
			inactiveID = quantityControl.id;
			quantityControl.id = quantityControlPg.getQuantityControlID(quantityControl.locationClass, quantityControl.multiSalesAllowance);		
			quantityControlPg.clickQuantityControlID(quantityControl.id);
			ajax.waitLoading();
			editQuantityControlWiget.waitLoading();
			//compare record info, just have create info
			pass &= editQuantityControlWiget.compareQuantityControlInfo(quantityControl);
			
			editQuantityControlWiget.clickOK();
			ajax.waitLoading();
			quantityControlPg.waitLoading();	
		}
		//verify priori record will be inactive
		lm.searchQuantityControlByCriteria("Inactive", quantityControl.locationClass);
		if(!this.checkQuntityControlRecordIsExists(inactiveID)){
			pass &= false;
			logger.error(inactiveID + " this quantity control record should exists by search.");
		}
		
		quantityControlPg.selectShowCurrentReordsOnly();	
		quantityControlPg.clickGo();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}
	
	private void verifyInactiveSuccess(){		
		logger.info("Verify inactive success.");
		if(this.checkQuntityControlRecordIsExists(quantityControl.id)){
			pass &= false;
			logger.error(quantityControl.id + " this quantity contol record should not exists, due to it is inactive.");
		}
		
		//verify this record will be inactive
		lm.searchQuantityControlByCriteria("Inactive", quantityControl.locationClass);
		if(!this.checkQuntityControlRecordIsExists(quantityControl.id)){
			pass &= false;
			logger.error(quantityControl.id + " this quantity control record should exists by inactive search.");
		}
		//compare record info, have create info and update info
		quantityControlPg.clickQuantityControlID(quantityControl.id);
		ajax.waitLoading();
		editQuantityControlWiget.waitLoading();		
		pass &= editQuantityControlWiget.compareQuantityControlInfo(quantityControl);
		
		editQuantityControlWiget.clickCancel();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}
	
	private void verifyActiveSuccess(){
		logger.info("Verify active success.");
		inactiveID = quantityControl.id;
		
		//verify priori record still inactive
		if(!this.checkQuntityControlRecordIsExists(inactiveID)){
			pass &= false;
			logger.error(inactiveID + " this quantity control record should exist in inactive list.");
		}
		
		//verify crate a new record
		quantityControlPg.selectShowCurrentReordsOnly();		
		quantityControlPg.clickGo();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
		quantityControl.id = quantityControlPg.getQuantityControlID(quantityControl.locationClass, quantityControl.multiSalesAllowance);
		
		quantityControlPg.clickQuantityControlID(quantityControl.id);
		ajax.waitLoading();
		editQuantityControlWiget.waitLoading();	
		//compare record info, just have create info
		pass &= editQuantityControlWiget.compareQuantityControlInfo(quantityControl);
		editQuantityControlWiget.clickOK();
		ajax.waitLoading();
		quantityControlPg.waitLoading();		
	}
	
	private boolean checkQuntityControlRecordIsExists(String quantityControlID){
		List<String> quantityControlIDsFromUI = quantityControlPg.getColumnValues("ID");
		
		if(quantityControlIDsFromUI.contains(inactiveID)){
			return true;
		}else {
			return false;
		}
	}

}
