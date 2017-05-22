package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.edit;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 *
 * @Description:This case used to verify edit quantity control error message.
 * This case block by DEFECT-30358
 * @Preconditions:Prepare a privilege which valid to date is not 'Valid To Date of Previous License plus Valid Days/Years'
 * @SPEC:Edit Privilege Location Setting.doc
 * @Task#:Auto-674
 *
 * @author VZhang
 * @Date  Aug 9, 2011
 */
public class VerifyErrorMessage extends LicenseManagerTestCase{
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWiget =
		LicMgrPrivilegeEditQuantityControlWidget.getInstance();
	private LicMgrPrivilegeQuantityControlPage quantityControlPg =
		LicMgrPrivilegeQuantityControlPage.getInstance();
	private QuantityControlInfo editQuantityControl = new QuantityControlInfo();
	private String msg1, msg3, msg4, msg5;
	private LicMgrPrivilegeProductDetailsPage detailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
	
	@SuppressWarnings("unused")
	private String msg2;
	private boolean pass = true;

	public void execute() {
		lm.loginLicenseManager(login);
		//clear up environment
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);
		lm.deactivatePrivilegeQuantityControlRecords();

		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);

		//privilege which valid to date is "Valid To Date of Previous License plus Valid Days/Years", multi-sales allowance option will
		//just have 'No Multiple Allowed' option
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "2011";
		privilege.dateUnitOfValidToDate = "Years";
		privilege.renewalDays = "10";
		this.updatePrivilegeValidToDateCalculationOption();
		lm.gotoEditPrivilegeQuantityControlPg(quantityControl.id);
		this.verifyMutiSalesAllowanceOption(); // Blocked by Defect-42623

		privilege.validToDateCalculation = "Based on Priv License Year Record";
		this.updatePrivilegeValidToDateCalculationOption();
		lm.gotoEditPrivilegeQuantityControlPg(quantityControl.id);

		//verify max quantity control is not specified
		editQuantityControl.multiSalesAllowance = "Yes (Within Same Transaction only)";
		editQuantityControl.maxQuantityPerTran = "";
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		this.verifyErrorMessage(msg1);//spec: message4

		//verify max quantity control is not greater than 0
		editQuantityControl.maxQuantityPerTran = "0";
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		this.verifyErrorMessage(msg1);//spec: message5 //updated by Peter Zhu

		//verify max allowed is not specified
		editQuantityControl.multiSalesAllowance = "Yes (Within Same License Year only)";
		editQuantityControl.maxQuantityPerTran = "2";
		editQuantityControl.maxAllowed = "";
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		this.verifyErrorMessage(msg3);//spec: message6

		//verify max allowed is not greater than 1
		editQuantityControl.maxAllowed = "0";
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		this.verifyErrorMessage(msg3);//spec: message7//updated by Peter Zhu

		//verify max allowed is not greater than 1
		editQuantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		editQuantityControl.maxAllowed = "1";
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		this.verifyErrorMessage(msg4);//spec: message7

		//verify replacement max allowed is not greater than 0
		editQuantityControl.maxAllowed = "2";
		editQuantityControl.replacementMaxAllowed = "0";
		editQuantityControlWiget.setQuantityControlInfo(editQuantityControl);
		this.verifyErrorMessage(msg5);//spec: message8
		editQuantityControlWiget.clickCancel();
		quantityControlPg.waitLoading();

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

		privilege.code = "V90";

		quantityControl.status = "Active";
		quantityControl.locationClass = "05 - Dept of Marine Resources";
		quantityControl.multiSalesAllowance = "No Multiple Allowed";

		msg1 = "The Maximum Quantity Allowed per Transaction is required. Please specify the Maximum Quantity Allowed per Transaction.";
		msg2 = "The Maximum Quantity Allowed per Transaction entered is not valid. Please enter an integer value greater than 0.";
		msg3 = "The Maximum Allowed is required. Please specify the Maximum Allowed.";
		msg4 = "The Maximum Allowed entered is not valid. Please enter an integer value greater than 1.";
		msg5 = "The Replacement Maximum Allowed entered is not valid. Please enter an integer value greater than 0.";
	}

	//change privilege valid to date calculation option
	private void updatePrivilegeValidToDateCalculationOption(){

		quantityControlPg.selectValidToDateCalculation(privilege.validToDateCalculation);
		ajax.waitLoading();
		browser.waitExists();
		if(!quantityControlPg.checkValidDateOrYearsIsDisabled()){
			quantityControlPg.setValidDateOrYears(privilege.validDaysYears);
		}
		if(!quantityControlPg.checkDateUnitOfValidToDateIsDisabled()){
			quantityControlPg.selectDateUnitOfValidToDate(privilege.dateUnitOfValidToDate);
		}
		if(!quantityControlPg.checkRenewalDaysIsDisabled()){
			quantityControlPg.setRenewalDays(privilege.renewalDays);
		}

		quantityControlPg.clickApply();
		ajax.waitLoading();
		detailsPg.waitLoading(); // Change the workflow due to the fix of the defect-40211
		detailsPg.clickQuantityControlTab();
		quantityControlPg.waitLoading();
	}

	//verify mulit-sales allowance option
	private void verifyMutiSalesAllowanceOption(){
		//multi-sales allowance option should just have 'No Multiple Allowed' option
		List<String> mutiSalesAllowanceElementsFromUI = editQuantityControlWiget.getMutiSalesAllowedElements();
		if(mutiSalesAllowanceElementsFromUI.size() != 1){
			pass &= false;
			logger.error("Muti Sales Allowance option should just have one option in this workflow.");
		}
		if(!mutiSalesAllowanceElementsFromUI.get(0).equals("No Multiple Allowed")){
			pass &= false;
			logger.error("Muti Sales Allowance option should be 'Muti Sales Allowance option' in this workflow, " +
					"but acutally is " + mutiSalesAllowanceElementsFromUI.get(0));
		}
		editQuantityControlWiget.clickCancel();
		quantityControlPg.waitLoading();
	}

	//verify error message
	private void verifyErrorMessage(String expectedErrorMsg){
		editQuantityControlWiget.clickOK();

		logger.info("Verify Error Message.");
		Object pages = browser.waitExists(editQuantityControlWiget,quantityControlPg);
		if(pages == quantityControlPg){
			pass &= false;
			logger.error("Expect page is edit quantity control widget page.");
		}

		String actualMsg = editQuantityControlWiget.getErrorMessage();
		if(!actualMsg.equals(expectedErrorMsg)){
			pass &= false;
			logger.error("Expect error message should be " + expectedErrorMsg
					+ ", but actually is " + actualMsg);
		}
	}

}
