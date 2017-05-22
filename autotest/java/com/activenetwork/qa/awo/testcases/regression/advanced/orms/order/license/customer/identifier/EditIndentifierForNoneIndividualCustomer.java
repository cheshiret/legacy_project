package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerSuspensionPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditIdentifierPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 1.Verify verify-status in DB
 *               2.Verify warning message in edit customer identifier page
 *               3.Verify successfully edit identifier
 *               4.Verify failed edit identifier
 * @Preconditions: Have business customer with fName: QA-Customer4, mName:QaTest-CusotmerProfile-4,lName:TEST-Profile4 and date of birth is '22-Feb-1980'
 *                 Have identifier: Employee Federal Identification Number, 111111122(Active)
 *                 Have suspension: Includes verified 'Trapper ID' identifier(local case)
 *                Have business customer with fName: QA-Customer6, mName:QaTest-CusotmerProfile-6,lName:TEST-Profile6 and date of birth is 'Jul 25 1978'
 *                 Have identifier: Employee Federal Identification Number, 111111124(Active)
 * @SPEC: Edit Customer Identifier
 * @Task#: AUTO-540
 * 
 * @author swang5
 * @Date
 */
public class EditIndentifierForNoneIndividualCustomer extends LicenseManagerTestCase{
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
	private LicMgrEditIdentifierPage editIdenPg = LicMgrEditIdentifierPage.getInstance();
	private LicMgrCustomerSuspensionPage suspensionPg = LicMgrCustomerSuspensionPage.getInstance();
	private CustomerIdentifier identifier = new CustomerIdentifier();
	private CustomerIdentifier identifier1 = new CustomerIdentifier();
	private CustomerIdentifier identifier2 = new CustomerIdentifier();
	private CustomerIdentifier identifier3 = new CustomerIdentifier();
	private Suspension suspension = new Suspension();
	private boolean pass = true;
	private Customer otherCust = new Customer();
	
	public void execute() {
		//Login license manager and goto identifiers page
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Add identifier to second customer
		otherCust.licenseNum = lm.getCustomerNum(otherCust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(otherCust.identifier.identifierType, otherCust.identifier.identifierNum, otherCust.customerClass);
		lm.gotoIdentifiersFromCustomerDetailsPg();
		if(!custIdenPg.checkIdentifierExistByTypeAndNumber(otherCust.identifiers.get(0).identifierType, otherCust.identifiers.get(0).identifierNum)){
			lm.addIdentifiersInInditifierPage(otherCust.identifiers);
		}
		
		//Add identifier to first customer
		lm.gotoHomePage();
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoIdentifiersFromCustomerDetailsPg();
		lm.addIdentifiersInInditifierPage(cust.identifiers);

		//Get identifier IDs
		String identifierID = custIdenPg.getIdentifierID(identifier1.identifierType, identifier1.identifierNum);
		identifier.id = custIdenPg.getIdentifierID(identifier.identifierType, identifier.identifierNum);
		
		//Add suspension for 'US Drivers License'
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
		lm.addCustomerSuspension(suspension);

		//Verify identifier status in DB
		//Identifier matches the State/Province, but is not verifiable(US Drivers License)
		lm.verifyIdentifierVerifyStatusFromDB(identifierID, "Not Applicable", schema);

		//Validate field when edit identifier
		lm.switchIdentifiersAndSuspensionsPg(suspensionPg);
		this.validateFieldInWhenEditIden();
//		lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);
		if(editIdenPg.exists()){
			lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);
		}

		//Successfully edit identifier
		identifier.identifierNum = "111111127";
		identifier.state = "";
		lm.editIdentifierInfo(identifier);
		custIdenPg.verifyIdentifierList(identifier);

		//Failed edit identifier
		lm.gotoEditIdentifiersFromCustomerDetailsPg(identifier.identifierType, identifier.identifierNum);
		identifier.identifierNum = "111111126";
		editIdenPg.editIdentifier(identifier);
		this.cancelEidtIdentifier();

		//Remove identifier
		identifier.identifierNum = "111111127";
		for(int i=0; i<cust.identifiers.size(); i++){
			lm.removeCustIdentifier(cust.identifiers.get(i).identifierType, cust.identifiers.get(i).identifierNum);
		}

		//Remove suspension
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
		lm.manageSuspensions("Remove", suspension);

		//Throw exception if it exists
		if(!pass){
			throw new ErrorOnPageException("Case is running failed.");
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

		cust.customerClass = "BUSINESS";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.businessName = "@QaTest-CusotmerProfile-4";
		cust.fName = "QA-Customer4";
		cust.mName = "QaTest-CusotmerProfile-4";
		cust.lName = "TEST-Profile4";
		cust.dateOfBirth = "Aug 11 1979";

		//Identifier info
		identifier.status = OrmsConstants.ACTIVE_STATUS;
		identifier.identifierType = "Tax ID";
		identifier.identifierNum = "111111126";
		identifier.state = "";
		identifier.country = "";
		identifier.createDate = DateFunctions.formatDate(DateFunctions.getToday(), "MM/dd/yyyy");
		identifier.creationApp = "LicenseManager";
		identifier.createUser = login.userName;

		identifier1.identifierType = "US Drivers License";//Trapper ID
		identifier1.identifierNum = "111111123";
		identifier1.state = "Missouri";//Mississippi
		
		identifier3.status = OrmsConstants.ACTIVE_STATUS;
		identifier3.identifierType = "Employee Federal Identification Number";
		identifier3.identifierNum = "111111122";
		identifier3.state = "";
		identifier3.country = "";
		
		cust.identifiers.add(identifier);
		cust.identifiers.add(identifier1);
		cust.identifiers.add(identifier3);
		
		//Suspension info
		suspension.beginDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.identifiersTypes = new String[]{"Employee Federal Identification Number", identifier.identifierType};  
		suspension.identifiersNums = new String[]{"111111122", identifier.identifierNum};
		suspension.comment = "EditIndentifierForIndividualCustomer-" + DateFunctions.getCurrentTime();
		
		otherCust.lName = "TEST-Profile6";
		otherCust.fName = "QA-Customer6";
		otherCust.businessName = "@QaTest-CusotmerProfile-6";
		otherCust.identifier.identifierType = "MDWFP #";
		otherCust.identifier.identifierNum = lm.getCustomerNumByCustName(otherCust.lName, otherCust.fName, schema);
		otherCust.customerClass = "BUSINESS";
		
		identifier2.status = OrmsConstants.ACTIVE_STATUS;
		identifier2.identifierType = "Employee Federal Identification Number";
		identifier2.identifierNum = "111111124";
		identifier2.state = "";
		identifier2.country = "";
		
		otherCust.identifiers.add(identifier2);
	}

	private void cancelEidtIdentifier(){
		editIdenPg.clickCancel();
		ajax.waitLoading();
		custIdenPg.waitLoading();  
		if(custIdenPg.getIdentifierRow("", identifier.identifierType, identifier.identifierNum)!=-1){
			throw new ErrorOnPageException("It should not be successfully edit the customer with " +
					"customer type("+identifier.identifierType+") and customer number("+identifier.identifierNum+")");
		}
	}

	private void validateFieldInWhenEditIden(){
		//Edit identifier with type: Employee Federal Identification Number(No state and country)
		lm.gotoEditIdentifiersFromIdentifietPg("Employee Federal Identification Number", "111111122");
		this.verifyAllEntersValided("", "", str1); 
		this.verifyAllEntersValided("0123456789","", str2); 
//		String customNum = lm.getCustomerNumByCustName("TEST-Profile6", "QA-Customer6", schema);
		str3 = "An 'Active' or 'Verified' Identifier with Type 'Employee Federal Identification Number' and the same Identifier Number, State and Country already exists for another Customer: "+otherCust.businessName+" \\("+otherCust.identifier.identifierNum+"\\). Do you wish to merge these customers\\?";
		this.verifyAllEntersValided("111111124","", str3); 
//		lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);

		//Edit identifier with type: US Drivers LicenseD
		lm.gotoEditIdentifiersFromIdentifietPg("US Drivers License", "111111123");
		this.verifyAllEntersValided("1234", "Missouri", str4); 
		//Verify active suspensions are associated to the known Identifier(Trapper ID)
		this.verifyAllEntersValided("111111125", "Missouri",str5); 
	}

	private void verifyAllEntersValided(String identifierNum, String state, String expectMsg){
		LicMgrEditIdentifierPage editIdentifierPg = LicMgrEditIdentifierPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		String actualMsg = "";
		//Initialize Identifier
		identifier.identifierNum = identifierNum;
		identifier.state = state;
		//Edit Identifier
		editIdentifierPg.editIdentifier(identifier);
		editIdentifierPg.clickOK();
		ajax.waitLoading();
		//Verify warning message
		Object page = browser.waitExists(confirmDialogWidget, editIdentifierPg);
		if(page == confirmDialogWidget){
			actualMsg = confirmDialogWidget.getMessage();
		}else if(page == editIdentifierPg  && page != confirmDialogWidget){
			actualMsg = editIdentifierPg.getWarnMes();
		}
		if(!actualMsg.matches(expectMsg)){
			pass &=false;
			logger.error("The actual error message is:" + actualMsg);
			logger.error("The expect error message is:" + expectMsg);
			
			/*//Test
			throw new ErrorOnPageException("Error");
			//Test
*/		}
		//Return to Edit identifier page from confirm dialog widget 
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickCancel();
			ajax.waitLoading();
			editIdentifierPg.exists();
		}
	}

	private String str1 = "Identifier Number is required for Identifier Type \"Employee Federal Identification Number\". ?Please specify the Identifier Number.";
	private String str2 = "The Identifier Number for Identifier Type \"Employee Federal Identification Number\" must contain exactly 9 digits. ?Please re-enter the Identifier Number.";
	private String str3 = "An 'Active' or 'Verified' Identifier with Type 'Employee Federal Identification Number' and the same Identifier Number, State/Province and Country already exists for another Customer: "+otherCust.businessName+" \\("+otherCust.identifier.identifierNum+"\\)\\. Do you wish to merge these customers\\?";
	private String str4 = "The Identifier Number for Identifier Type \"US Drivers License\" must only contain numbers and letters. ?Please re-enter.";
	private String str5 = "One or more \"Active\" Suspensions may be released when the changes to the Identifier are saved. Proceed with the changes\\?";
}
