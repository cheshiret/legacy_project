/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * The Valid To Date Calculation option selected is "Valid To Date of Previous License plus Valid Days/Years", and there is at least one existing Privilege Location Class Settings record for the same Privilege Product that is "Active" and with Multiple Sales Allowance set to a value other than "No Multiple Allowed".
 * System displays an error message (Message 24).
 * System prompts the User to re-enter.
 * @Preconditions:
 * @SPEC:Edit Privilege Product.DOC
 * @Task#:AUTO-670
 * @author asun
 * @Date  Aug 11, 2011
 */
public class Edit_ChangeValidToDateCalculation extends LicenseManagerTestCase {
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private String msg;
	private String targetOption="Valid To Date of Previous License plus Valid Days/Years";
	private String otherOption="Based on Priv License Year Record";
	
	@Override
	public void execute() {
		lm.deleteQuantityControlsFromDB(privilege.code, schema);
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);
		
		//prepare a quantity control record
		privilege.validToDateCalculation=otherOption;
		this.changeValidToDateCulation(privilege);
		lm.addPrivilegeQuantityControl(quantityControl);
		
		//do verification
		privilege.validToDateCalculation=targetOption;
		this.changeValidToDateCulation(privilege);
		this.verifyErrorMessage(msg);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
	 
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		msg="When the Privilege Product's Valid To Date Calculation is set to \"Valid To Date of Previous License plus Valid Days/Years\", the Multiple Sales Allowance must be set to \"No Multiple Allowed\". Please change your entry.";
		
		privilege.code="A12";
		privilege.validToDateCalculation="Expires when the Customer reaches Valid To Age";
		privilege.validDaysYears="1";
		privilege.dateUnitOfValidToDate="Days";
		privilege.renewalDays="1";
		privilege.validToAge="2";
		
		quantityControl.locationClass="All";
		quantityControl.multiSalesAllowance="Yes (Within Same Transaction only)";
		quantityControl.maxQuantityPerTran="3";
	}
	
	/**
	 * change Valid To Date Calculation in privilege details page
	 * @param type
	 */
	public void changeValidToDateCulation(PrivilegeInfo pri){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		logger.info("change Valid To Date Calculation in privilege details page");
		privilegeDetailsPg.setVaildToDateCalculation(pri);
		privilegeDetailsPg.clickApply();
		ajax.waitLoading();
		privilegeDetailsPg.waitLoading();
	}
	
	/**
	 * verify option and message when do confirmation
	 * @param msg
	 * @param isOk
	 */
	public void verifyErrorMessage(String msg){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
//		LicMgrConfirmDialogWidget confirmPg = LicMgrConfirmDialogWidget.getInstance();
		logger.info("verify Error Message....");
//		if(!confirmPg.exists()){
//			throw new PageNotFoundException("Can't find confirm widget..");
//		}
		
//		String msgFromPage=confirmPg.getErrorMsg();
		String msgFromPage=privilegeDetailsPg.getMessage();
		
		if(!MiscFunctions.compareResult("Error message", msg, msgFromPage)){
			throw new ErrorOnPageException("--Check logs above.");
		} else {
//			confirmPg.clickCancel();
//			ajax.waitLoading();
//			privilegeDetailsPg.waitExists();
			logger.info("verify successfully");	
		}
	}
}
