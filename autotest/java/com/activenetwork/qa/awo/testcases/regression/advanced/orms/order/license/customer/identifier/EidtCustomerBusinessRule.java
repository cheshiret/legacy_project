package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditIdentifierPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Customer need a VERIFIED identifier.
 * DOB:Apr 04 1976
 * (http://wiki.reserveamerica.com/display/qa/Mock+Verifier)
 * Identifier Type:MS Drivers License
 * (select name from ALL_C_IDENTIFIER_TYPE WHERE contract = 'MS' and verifiable_ind = 1;(schema:COMMON))
 * Identifier number:762429124
 * (http://wiki.reserveamerica.com/display/qa/Mock+Verifier)
 * added by Nicole
 *
 */
public class EidtCustomerBusinessRule extends LicenseManagerTestCase{
	private LicMgrEditIdentifierPage editIdenPg = LicMgrEditIdentifierPage.getInstance();
	private CustomerIdentifier identifier = new CustomerIdentifier();
	private boolean pass = true;

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum, cust.customerClass);

		//Add identifier
		lm.gotoIdentifiersFromCustomerDetailsPg();
		identifier.id = lm.addCustomerIdentifier(identifier);

		//Verify un-Edit fields in editing page
		lm.gotoEditIdentifiersFromIdentifietPg(identifier.identifierType, identifier.identifierNum);
		this.verifyUnEditFields();

		//Verify warning message when edit verified identifier
		identifier.identifierNum = "MLO1234567";
		editIdenPg.editIdentifier(identifier);
		this.verifyWarnMess(str);
		lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);

		identifier.identifierNum = "762429124";
		lm.changeIdentifierStatus(identifier.identifierType, identifier.identifierNum, "Remove");

		//Throw exception if it exists
		if(!pass){
			throw new ErrorOnPageException("Case is failed.");
		}

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Customer1";
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";// Don't change!s

		//Identifier info
		identifier.identifierType = "MS Drivers License";// Don't change!
		identifier.identifierNum = "762429124";// Don't change!
		identifier.state = "Mississippi";
		
	}

	private void verifyWarnMess(String expectMsg){
		String actualMsg = "";
		editIdenPg.clickOK();
		ajax.waitLoading();
		actualMsg = editIdenPg.getWarnMes();
		if(!actualMsg.equals(expectMsg)){
			pass &=false;
			logger.error("-----The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}

	private void verifyUnEditFields(){
		editIdenPg.checkIdUnEditable();
		editIdenPg.checkStatusEditable();
		editIdenPg.checkTypeUnEditable();
		editIdenPg.checkCreationApp();
		editIdenPg.checkCreationDate();
		editIdenPg.checkcreationUser();
	}

	private String str = "No changes can be made to the Identifier, other than Status changes, since the Identifier Status is Verified.";
}
