package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.mergetriggers;
/**
 * @Description: Verify merge customer add same customer identifier trigger.
 * @Preconditions:pre-condition: 
	(1)There are two customers which fname,lname and DOB are the same; just different for mid name; 
	(2)The customer should have active identifier of Tax ID; 
	(3)The merged customer should have active identifier of Social Security Number; 
	(4)Assign 'Merge Customer Profile and Allow Merge with Customer#' to HF Administrator role.
 * @SPEC:
 * @Task#: Auto-1156 TC:042734
 * @author Jasmine
 * @Date Jul 30, 2012
 */
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;


public class VerifyErrMsgAddSameIdentifier extends LicenseManagerTestCase{
	private LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
	private String alertMsg = "";
	private Customer mergedCust =null;
	
	public void execute() {
		lm.loginLicenseManager(login);
		//go to merged customer detail page.
		lm.gotoCustomerDetailFromCustomersQuickSearch(mergedCust.customerClass, mergedCust.licenseType,mergedCust.licenseNum);
		lm.gotoCustomerIdentifierSubTab();
		
		//attempt to Add a new identifier - same type/number as customer
		this.gotoAddCustIdentifierConfirmWgt(cust.identifier);
		confirmDialogWidget.verifyErroMsg(alertMsg);
		this.cancelConfirmDialog();
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
	
		//Customer have one identifier which cust.licenseType = "Tax ID" and cust.licenseNum = "1452631";
		cust.customerClass = "Individual";
		cust.fName = "QA-CustMergeTrigger";
		cust.lName = "TEST-CustMergeTrigger";
		cust.mName ="TriggerCust";
		//cust.dateOfBirth = "Aug 12 1984";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "1452631";
	    cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		
		mergedCust = new Customer();
		mergedCust.customerClass = "Individual";
		mergedCust.fName  =  cust.fName;
		mergedCust.lName = cust.lName;
		mergedCust.mName = "MergedTriggerCust";
		mergedCust.licenseType = "Social Security Number";
		mergedCust.licenseNum = "524263102";
		mergedCust.identifier.identifierType = "Tax ID";
		mergedCust.identifier.identifierNum = "1452632";
		
		alertMsg="An 'Active' or 'Verified' Identifier with Type '"+cust.identifier.identifierType+"' and the same Identifier Number, State and Country already exists for another Customer: "+cust.fName+" "+cust.lName+" ("+cust.custNum+"). Do you wish to merge these customers?";
	}
	
	/**
	 * go to the add customer identifier confirm widget confirm page.
	 * from  LicMgrCustomerIdentifiersPage to LicMgrConfirmDialogWidget page
	 */
	private void gotoAddCustIdentifierConfirmWgt(CustomerIdentifier identifier){
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage
				.getInstance();
		LicMgrAddIdentifiersPage addIdentifier = LicMgrAddIdentifiersPage
				.getInstance();
		
		
		logger.info("Add Customer Identifier " + identifier.identifierType
				+ " = " + identifier.identifierNum);
		
		identifierPage.clickAddIdentifier();
		ajax.waitLoading();
		addIdentifier.waitLoading();
		addIdentifier.setIdentifier(identifier);
		addIdentifier.clickOK();
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
