package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.quantitycontrol.edit;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:This case is used to verify edit quantity control business rules.
 * This case is block by DEFECT-30400
 * @Preconditions:Prepare a privilege which valid to date is not 'Valid To Date of Previous License plus Valid Days/Years'
 * @SPEC:<<Edit Privilege Location Setting.doc>>
 * @Task#:Auto-674
 * 
 * @author VZhang
 * @Date  Aug 9, 2011
 */
public class VerifyBusinessRules extends LicenseManagerTestCase{
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	private boolean pass = true;
	private List<String> expectMultiSalsAllowance = new ArrayList<String>();
	
	public void execute() {
		lm.loginLicenseManager(login);	
		//clear up environment
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(privilege.code);
		lm.deactivatePrivilegeQuantityControlRecords();
		
		quantityControl.id = lm.addPrivilegeQuantityControl(quantityControl);
		
		lm.gotoEditPrivilegeQuantityControlPg(quantityControl.id);
		//verify business rules: 1. quantity control id should not editable; 2. location class should not editable; 3. multi-slaes allowance option business rule
		this.verifyBusinessRules();
		
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
		
		privilege.code = "V91";
		
		quantityControl.locationClass = "03 - Lakes Offices";
		quantityControl.multiSalesAllowance = "Yes (Regardless of License Year)";
		quantityControl.maxQuantityPerTran = "10";
		quantityControl.maxAllowed = "10";
				
		expectMultiSalsAllowance.add("No Multiple Allowed");
		expectMultiSalsAllowance.add("Yes (Within Same Transaction only)");
		expectMultiSalsAllowance.add("Yes (Within Same License Year only)");
		expectMultiSalsAllowance.add("Yes (Regardless of License Year)");
	}
	
	private void verifyBusinessRules(){
		LicMgrPrivilegeEditQuantityControlWidget editQuantityControlWiget = 
			LicMgrPrivilegeEditQuantityControlWidget.getInstance();
		LicMgrPrivilegeQuantityControlPage quantityControlPg = 
			LicMgrPrivilegeQuantityControlPage.getInstance();
		
		logger.info("Verify Business rule.");
		
		//quantity control id should not editable
		if(!editQuantityControlWiget.checkQuantityControlIDIsDiabled()){
			pass &= false;
			logger.error("Quantity control ID should not be editable.");
		}
		//location class should not editable
		if(!editQuantityControlWiget.checkLocationClassIsDisabled()){
			pass &= false;
			logger.error("Quantity control location class should not be editable.");
		}
		//multi-slaes allowance option business rule
		//verify option elements
		List<String> multiSalesAllowanceElementsFromUI = editQuantityControlWiget.getMutiSalesAllowedElements();
		if(multiSalesAllowanceElementsFromUI.size()!= expectMultiSalsAllowance.size()){
			pass &= false;
			logger.error("Muti sales allowed should have 4 options.");
		}else{
			for(int i=0; i<expectMultiSalsAllowance.size(); i++){
				if(!multiSalesAllowanceElementsFromUI.get(i).equals(expectMultiSalsAllowance.get(i))){
					pass &= false;
					logger.error("This muti sales allowance option should be " + expectMultiSalsAllowance.get(i) 
							+ ", but actually is" + multiSalesAllowanceElementsFromUI.get(i));
				}else {
					logger.info(multiSalesAllowanceElementsFromUI.get(i) + " is expected option.");
				}
			}
		}
		
		//"No Multiple Allowed" is selected, per transaction text and max allowance text will not display
		pass &= editQuantityControlWiget.verifyMultiSalesAllowanceOptionBusinessRule("No Multiple Allowed", false, false);
		
		//"Yes (Within Same License Year only)" is selected, per transaction text and max allowanced text will display
		pass &= editQuantityControlWiget.verifyMultiSalesAllowanceOptionBusinessRule("Yes (Within Same Transaction only)", true, false);
		
		//"Yes (Within Same License Year only)" is selected, per transaction text and max allowanced text will display
		pass &= editQuantityControlWiget.verifyMultiSalesAllowanceOptionBusinessRule("Yes (Within Same License Year only)", true, true);
		
		//"Yes (Regardless of License Year)" is selected, per transaction text and max allowanced text will display
		pass &= editQuantityControlWiget.verifyMultiSalesAllowanceOptionBusinessRule("Yes (Regardless of License Year)", true, true);
		
		editQuantityControlWiget.clickOK();
		ajax.waitLoading();
		quantityControlPg.waitLoading();
	}

}
