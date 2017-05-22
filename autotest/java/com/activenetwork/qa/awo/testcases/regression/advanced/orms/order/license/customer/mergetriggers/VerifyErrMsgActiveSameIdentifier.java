package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.mergetriggers;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: Verify merge customer active same customer identifier trigger.
 * @Preconditions:
 *	(1)There are two customers which fname,lname and DOB are the same; just different for mid name; 
	(2)The customer should have active identifier of Employee Federal Identification Number; 
	(3)The merged customer should have deactivate Employee Federal Identification Number; 
	(4)Assign 'MergeCustomerProfile' and 'AllowMergeWithCustomerNumber' to HF Administrator role.
 * @SPEC:
 * @Task#: Auto-1156 TC:042734
 * @author Jasmine
 * @Date Jul 31, 2012 
 */
public class VerifyErrMsgActiveSameIdentifier extends LicenseManagerTestCase{
	private LicMgrCustomerIdentifiersPage custIdentifier = LicMgrCustomerIdentifiersPage
			.getInstance();
    private Customer mergedCust = null;
    private String alertMsg, identifierType, identifierNum;
    
	public void execute() {
		lm.loginLicenseManager(login);
		
		//prepare merged customer identifier - Inactive
		lm.gotoCustomerDetailFromCustomersQuickSearch(mergedCust.customerClass, mergedCust.licenseType, mergedCust.licenseNum);
		lm.gotoCustomerIdentifierSubTab();
		mergedCust.identifier.identifierType = identifierType;
		mergedCust.identifier.identifierNum = identifierNum;
		mergedCust.identifier.id = custIdentifier.getIdentifierID(mergedCust.identifier.identifierType, mergedCust.identifier.identifierNum);
	
		alertMsg = this.initExpectedMessage(mergedCust.identifier.id);
		if(null == mergedCust.identifier.id||mergedCust.identifier.id.equalsIgnoreCase("null")){
			lm.addCustomerIdentifier(mergedCust.identifier);
		}else{
			mergedCust.identifier.status = custIdentifier.getIdentifierStatus(mergedCust.identifier.identifierType, mergedCust.identifier.identifierNum);
			if(mergedCust.identifier.status.equals(OrmsConstants.ACTIVE_STATUS)) {
				lm.changeIdentifierStatus(mergedCust.identifier.identifierType,mergedCust.identifier.identifierNum,"Deactivate");
			}
		}
		
		//prepare original customer identifier - Active
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust.customerClass, cust.licenseType, cust.licenseNum);
		lm.gotoCustomerIdentifierSubTab();
		cust.identifier.identifierType = identifierType;
		cust.identifier.identifierNum = identifierNum;
		cust.identifier.status =  custIdentifier.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		if(cust.identifier.status.equals(OrmsConstants.INACTIVE_STATUS)) {
			lm.changeIdentifierStatus(cust.identifier.identifierType,cust.identifier.identifierNum,"Activate");
		}
		
		//goto merged customer identifier page
		mergedCust.identifier.identifierType = "Tax ID";
		mergedCust.identifier.identifierNum = "1452632";
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(mergedCust.customerClass, mergedCust.licenseType, mergedCust.licenseNum);
		lm.gotoCustomerIdentifierSubTab();
		
		//activate the merged customer identifier, and system will pop a waning message
		String actualAlertMsg = lm.getWarnMesWhenChangeIdentifierStatus(mergedCust.identifier.id, "Activate");
		this.verifyAlertMsg(actualAlertMsg, alertMsg);
		lm.logOutLicenseManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.customerClass = "Individual";
		cust.fName = "QA-CustMergeTrigger";
		cust.lName = "TEST-CustMergeTrigger";
		cust.mName ="TriggerCust";
		//cust.licenseType = "Employee Federal Identification Number";
		cust.licenseType = "Tax ID";
		//cust.licenseNum = "415263987";
		cust.licenseNum = "1452631";
		cust.dateOfBirth = "Feb 01 1985";
		cust.identifier.identifierType = cust.licenseType;
		cust.identifier.identifierNum = cust.licenseNum;
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		
		mergedCust = new Customer();
		mergedCust.customerClass = "Individual";
		mergedCust.fName  =  cust.fName;
		mergedCust.lName = cust.lName;
		mergedCust.mName = "MergedTriggerCust";
		mergedCust.licenseType = "Tax ID";
		mergedCust.licenseNum = "1452632";
		mergedCust.identifier.identifierType =mergedCust.licenseType;
		mergedCust.identifier.identifierNum = mergedCust.licenseNum;
		mergedCust.custNum = lm.getCustomerNumByCustName(mergedCust.lName, mergedCust.fName, mergedCust.mName, schema);
		
		identifierType = "Employee Federal Identification Number";
		identifierNum = "415263987";
	}
	
	/**
	 * Initialize the expected message.
	 * @param identifierId
	 * @return
	 */
	private String initExpectedMessage(String identifierId){
		return "Identifier with ID '"+identifierId+"' cannot be activated, since an 'Active' or 'Verified' Identifier with Type '"+identifierType+"' and the same Identifier Number, State and Country already exists for another Customer: "+cust.fName+" "+cust.lName+" ("+cust.custNum+"). Do you wish to merge these customers?";
	}
	
	/**
	 * verify alert message;
	 * @param actualMsg
	 * @param expectedMsg
	 */
	private void verifyAlertMsg(String actualMsg, String expectedMsg){
		if(!MiscFunctions.compareResult("Alert Message", expectedMsg, actualMsg)){
			throw new ErrorOnPageException("Alert Message",expectedMsg,actualMsg);
		}else{
			logger.info("The merge customer alert message correct");
		}
	}
}
