/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.identifier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case is used to verify a basic flow for Edit a customer identifier
 * @Preconditions:Need to prepare a customer at first
 * @SPEC:<Edit Customer Identifier&View Customer Identifier>
 * @Task#:AUTO-804
 * 
 * @author ssong
 * @Date  Dec 28, 2011
 */
public class EditIdentifier extends LicenseManagerTestCase{

	private CustomerIdentifier identifier = new CustomerIdentifier();
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Identifiers");
		
		identifier.id = lm.safeAddCustomerIdentifier(identifier);
		this.changeDataInfo();
		lm.safeEditCustomerIdentifier(identifier);
		custIdenPg.verifyIdentifierList(identifier);
		
		lm.gotoIdentifierDetailPage(identifier.id);
		lm.verifyIdentifierFromDetailPg(identifier);
		
		lm.changeIdentifierStatus(identifier.identifierType, identifier.identifierNum, "Remove");
		lm.logOutLicenseManager();
	}

	private void changeDataInfo(){
		identifier.identifierNum = identifier.identifierNum+"222";
		identifier.country = "Bahamas";
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Identifier1";
		cust.lName = "QA-Identifier1";
		
		identifier.identifierType = "Green Card";
		identifier.identifierNum = "0122354";
		identifier.country = "Canada";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		identifier.createDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));;
		identifier.createUser = login.userName;
		identifier.status = OrmsConstants.ACTIVE_STATUS;
	}
}
