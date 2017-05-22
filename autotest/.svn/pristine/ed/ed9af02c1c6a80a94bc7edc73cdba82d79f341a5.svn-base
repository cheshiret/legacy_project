package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.mergetriggers;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditIdentifierPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**
 * @Description: Verify merge customer Edit same customer identifier trigger.
 * @Preconditions:
 *  (1)There are two customers which fname,lname and DOB are the same; just different for mid name; 
	(2)The customer should have active identifier of Tax ID; 
	(3)The merged custoemr should have different value of Tax ID.
	(4)Assign 'Merge Customer Profile and Allow Merge with Customer#' to HF Administrator role.
 * @SPEC:
 * @Task#: Auto-1156 TC:042734
 * @author Jasmine
 * @Date Jul 31, 2012
 */
public class VerifyErrMsgEditSameIdentifier extends LicenseManagerTestCase{
	private  LicMgrCustomerIdentifiersPage custIdentifier = LicMgrCustomerIdentifiersPage
			.getInstance();
	private LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
    private Customer mergedCust = null;
    private String alertMsg = "";
	public void execute() {
		lm.loginLicenseManager(login);
		//go to merged customer detail page.
		lm.gotoCustomerDetailFromCustomersQuickSearch(mergedCust.customerClass, mergedCust.licenseType, mergedCust.licenseNum);
		lm.gotoCustomerIdentifierSubTab();
		mergedCust.identifier.id = custIdentifier.getIdentifierID(mergedCust.identifier.identifierType, mergedCust.identifier.identifierNum);
		mergedCust.identifier.identifierNum = cust.identifier.identifierNum;
		this.gotoEditIdentifierConfirmWgt(mergedCust.identifier);
		confirmDialogWidget.verifyErroMsg(alertMsg);
		this.cancelConfirmDialog();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		//cust.customerClass = "Individual";
		cust.fName = "QA-CustMergeTrigger";
		cust.lName = "TEST-CustMergeTrigger";
		cust.mName ="TriggerCust";
		cust.licenseType = "Tax ID";
		cust.identifier.identifierNum = "1452631";
		//cust.dateOfBirth = "Aug 12 1984";
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);//814617502

		mergedCust = new Customer();
		mergedCust.customerClass = "Individual";
		//mergedCust.fName  =  cust.fName;
		//mergedCust.lName = cust.lName;
		mergedCust.licenseType = "Tax ID";
		mergedCust.licenseNum = "1452632";
		mergedCust.identifier.identifierType = mergedCust.licenseType;
		mergedCust.identifier.identifierNum = mergedCust.licenseNum;
		alertMsg="An 'Active' or 'Verified' Identifier with Type '"+cust.licenseType+"' and the same Identifier Number, State and Country already exists for another Customer: "+cust.fName+" "+cust.lName+" ("+cust.custNum+"). Do you wish to merge these customers?";
	}
	/**
	 * go to edit identifier confirm widget.
	 * from  LicMgrCustomerIdentifiersPage to LicMgrConfirmDialogWidget page
	 */
	private void gotoEditIdentifierConfirmWgt(CustomerIdentifier identifier){
		LicMgrEditIdentifierPage editIdPage = LicMgrEditIdentifierPage
				.getInstance();
	    LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget
					.getInstance();

		lm.gotoIdentifierDetailPage(identifier.id);
		logger.info("Edit identifier to " + identifier.identifierType + " = "
				+ identifier.identifierNum);
		editIdPage.editIdentifier(identifier);
		editIdPage.clickOK();
		ajax.waitLoading();
		confirmDialogWidget.waitLoading();
	}
	
	/*
	 * cancel confirm dialog.
	 */
    private void cancelConfirmDialog(){
    	LicMgrCustomerIdentifiersPage custIdentifier = LicMgrCustomerIdentifiersPage
				.getInstance();
    	logger.info("cancel the confirm dialog");
    	confirmDialogWidget.clickCancel();
		ajax.waitLoading();
		custIdentifier.waitLoading();
    }

}
