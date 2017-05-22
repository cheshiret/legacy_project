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
 * @Preconditions: Have Individual customer with fName: QA-Customer3, mName:QaTest-CusotmerProfile-3,lName:TEST-Profile3 and date of birth is 'Aug 03 1988'
 *                 Have identifier: In case:
 *                                  OTHER, 111114(Inactive)(datepool)
 *                                  MS Drivers License,111111118(Inactive), 695073087(Verified)(local cases)
 *                                  Social Security Number, 111111116(Active)(local cases)
 *                                  US Drivers License, 111111117(Active)(datepool)
 *                 Have suspension: Includes verified 'US Driver License' education
 *                 Have Individual customer with fName: QA-Customer5, mName:QaTest-CusotmerProfile-5,lName:TEST-Profile5 and date of birth is 'Aug 03 1988'
 *                 Have identifier: OTHER, 111115(Active)
 * @SPEC: Edit Customer Identifier
 * @Task#: AUTO-540
 * 
 * @author swang5
 * @Date
 */
public class EditIndentifierForIndividualCustomer extends LicenseManagerTestCase{
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
	private LicMgrEditIdentifierPage editIdenPg = LicMgrEditIdentifierPage.getInstance();
	private LicMgrCustomerSuspensionPage suspensionPg = LicMgrCustomerSuspensionPage.getInstance();
	private CustomerIdentifier identifier1 = new CustomerIdentifier();
	private CustomerIdentifier editIdentifier = new CustomerIdentifier();
	private CustomerIdentifier identifier2 = new CustomerIdentifier();
	private CustomerIdentifier identifier3 = new CustomerIdentifier();
	private Suspension suspension = new Suspension();
	private boolean pass = true;
	private Customer otherCust = new Customer();

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Go to identifier list page 
//		cust.licenseNum = lm.getCustomerNum(cust, schema);
		cust.licenseNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum, cust.customerClass);
		lm.gotoIdentifiersFromCustomerDetailsPg();

		//Add identifiers
		//check and remove identifier record - 'OTHER-111115'
		if(custIdenPg.checkIdentifierExistByTypeAndNumber("OTHER", "111115")) {
			lm.removeCustIdentifier("OTHER", "111115");
		}
		lm.addIdentifiersInInditifierPage(cust.identifiers);

		//Get identifier IDs
		String otherIdenID = custIdenPg.getIdentifierID(identifier1.identifierType, identifier1.identifierNum);
		String mSDriversLicIdenID_1 = custIdenPg.getIdentifierID(identifier2.identifierType, identifier2.identifierNum);
		String mSDriversLicIdenID_2 = custIdenPg.getIdentifierID(identifier3.identifierType, identifier3.identifierNum);

		//Add suspension for 'NON-US DL Number' other than 'Social Security Number'
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
		lm.removeCustAllSuspensions();
		lm.addCustomerSuspension(suspension);

		//Verify verify-status in DB
		lm.verifyIdentifierVerifyStatusFromDB(otherIdenID, "Not Applicable", schema);//No State/Province
		lm.verifyIdentifierVerifyStatusFromDB(mSDriversLicIdenID_1, "Failed", schema);//Have State/Province and Verifiable
		lm.verifyIdentifierVerifyStatusFromDB(mSDriversLicIdenID_2, "Verified", schema);

		//Validate field when edit identifier
		lm.switchIdentifiersAndSuspensionsPg(suspensionPg);
		this.validateFieldInWhenEditIden();
		if(editIdenPg.exists()){
			lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);
		}

		//Successfully edit identifier
		//No error message when identifier number with more than 5 numbers and letters combined, or contains a character
		editIdentifier.id = otherIdenID;
		editIdentifier.identifierNum = "111115";
		lm.editIdentifierInfo(editIdentifier);
		editIdentifier.id = custIdenPg.getIdentifierID(editIdentifier.identifierType, editIdentifier.identifierNum);
		custIdenPg.verifyIdentifierList(editIdentifier);

		//Failed edit identifier
		lm.gotoEditIdentifiersFromCustomerDetailsPg(editIdentifier.identifierType, editIdentifier.identifierNum);
		editIdentifier.identifierNum = "111116";
		this.cancelEidtIdentifier();

		//Remove identifier
		cust.identifiers.remove(identifier1);
		editIdentifier.identifierNum = "111115";
		cust.identifiers.add(editIdentifier);
		for(int i=0; i<cust.identifiers.size(); i++){
			lm.removeCustIdentifier(cust.identifiers.get(i).identifierType, cust.identifiers.get(i).identifierNum);
		}

		//Remove suspension
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
		lm.removeCustomerSuspension(suspension);

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

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Customer3";
		cust.mName = "QaTest-CusotmerProfile-3";
		cust.lName = "TEST-Profile3";
		cust.dateOfBirth = "1988-08-03";

		//Identifier info
		identifier1.identifierType = "OTHER";
		identifier1.identifierNum = "111112";
		identifier1.country = "Canada";

		editIdentifier.status = OrmsConstants.ACTIVE_STATUS;
		editIdentifier.identifierType = identifier1.identifierType;
		editIdentifier.identifierNum = "111115";
		editIdentifier.country = "Malta";
		editIdentifier.createDate = DateFunctions.getToday();
		editIdentifier.creationApp = "LicenseManager";
		editIdentifier.createUser = login.userName;

		identifier2.identifierType = "MS Drivers License";
		identifier2.identifierNum = "111111118";
		identifier2.state = "Mississippi";

		identifier3.identifierType = "MS Drivers License";
		identifier3.identifierNum = "695073087";//http://wiki.reserveamerica.com/display/qa/Mock+Verifier
		identifier3.state = "Mississippi";

		cust.identifiers.add(identifier1);
		cust.identifiers.add(identifier2);
		cust.identifiers.add(identifier3);

		//Suspension info
		suspension.beginDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.comment = "EditIndentifierForIndividualCustomer-" + DateFunctions.getCurrentTime();
		suspension.identifiersTypes = new String[]{identifier1.identifierType};
		suspension.identifiersNums = new String[]{identifier1.identifierNum};
		
		otherCust.lName = "TEST-Profile5";
		otherCust.fName = "QA-Customer5";
		otherCust.identifier.identifierType = "MDWFP #";
		otherCust.identifier.identifierNum = lm.getCustomerNumByCustName(otherCust.lName, otherCust.fName, schema);
		
	}

	private void cancelEidtIdentifier(){
		editIdenPg.editIdentifier(editIdentifier);
		editIdenPg.clickCancel();
		ajax.waitLoading();
		custIdenPg.waitLoading();   
		if(custIdenPg.getIdentifierRow("", editIdentifier.identifierType, editIdentifier.identifierNum)!=-1){
			throw new ErrorOnPageException("It should NOT be edited the identifier with " +
					"identifier type("+editIdentifier.identifierType+") and identifier number("+editIdentifier.identifierNum+")");
		}
	}

	private void validateFieldInWhenEditIden(){
		//Edit identifier with type: Social Security Number(No state and country info)
		lm.gotoEditIdentifiersFromIdentifierPg(OrmsConstants.ACTIVE_STATUS, "Social Security Number", "");//111111116
		this.verifyAllEntersValided("", "", "", str1); 
		this.verifyAllEntersValided("0123456789", "", "", str2); 
		this.verifyAllEntersValided("000456789", "", "", str3); 
		this.verifyAllEntersValided("123006789", "", "", str3); 
		this.verifyAllEntersValided("123450000", "", "", str3); 
		lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);

		//Edit identifier with type: US Drivers License()
		lm.gotoEditIdentifiersFromIdentifietPg("US Drivers License", "MLO123456");
		this.verifyAllEntersValided("@*&#", "Missouri", "", str4); 
		this.verifyAllEntersValided("9999999999999", "Missouri", "", str5); 
		this.verifyAllEntersValided("111111117", "", "", str6); 
		//Verify active suspensions are associated to the known Identifier(US Drivers License)
		this.verifyAllEntersValided("111111120", "Missouri", "", str7); 
		lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);

		//Edit identifier with type: MS Drivers License(Has state--Verified)
		lm.gotoEditIdentifiersFromIdentifietPg(identifier3.identifierType, identifier3.identifierNum);
		this.verifyAllEntersValided("123456789", "Mississippi", "", str8); 
		lm.switchIndentifiersAndEditIdentifiersPg(editIdenPg);

		//Edit identifier with type: OTHER(Has country)
		lm.gotoEditIdentifiersFromIdentifietPg(identifier1.identifierType, identifier1.identifierNum);
		this.verifyAllEntersValided("111114", "", "", str9); 
		//Verify identifier(US Drivers License) has added to other customer
		str10 = "An \'Active\' or \'Verified\' Identifier with Type \'OTHER\' and the same Identifier Number, State and Country already exists for another Customer: "+otherCust.fName+" "+otherCust.lName+" \\("+otherCust.identifier.identifierNum+"\\). Do you wish to merge these customers\\?";
		this.verifyAllEntersValided("111119", "", "Canada", str10); //Have added to QaTest_Profile5
	}

	private void verifyAllEntersValided(String identifierNum, String state, String country, String expectMsg){
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		String actualMsg = "";
		//Initialize Identifier
		editIdentifier.identifierNum = identifierNum;
		editIdentifier.state = state;
		editIdentifier.country = country;
		//Edit Identifier
		editIdenPg.editIdentifier(editIdentifier);
		editIdenPg.clickOK();
		ajax.waitLoading();
		//Verify warning message
		Object page = browser.waitExists(confirmDialogWidget, editIdenPg);
		if(page == confirmDialogWidget){
			actualMsg = confirmDialogWidget.getMessage();
		}else if(page == editIdenPg  && page != confirmDialogWidget){
			actualMsg = editIdenPg.getWarnMes();
		}
		if(!actualMsg.matches(expectMsg)){
			pass &=false;
			logger.error("The actual error message is:" + actualMsg);
			logger.error("The expect error message is:" + expectMsg);

		}
		//Return to Edit identifier page from confirm dialog widget 
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickCancel();
			ajax.waitLoading();
			editIdenPg.exists();
		}
	}

	private String str1 = "Identifier Number is required for Identifier Type \"Social Security Number\". ?Please specify the Identifier Number.";
	private String str2 = "The Identifier Number for Identifier Type \"Social Security Number\" must contain exactly 9 digits. ?Please re-enter the Identifier Number.";
	private String str3 = "The Identifier Number for Identifier Type \"Social Security Number\" is invalid. ?Please re-enter.";
	private String str4 = "The Identifier Number for Identifier Type \"US Drivers License\" must only contain numbers and letters. ?Please re-enter.";
	private String str5 = "The Identifier Number for Identifier Type \"US Drivers License\" is invalid. ?Please re-enter.";
	private String str6 = "Identifier State is required for Identifier Type \"US Drivers License\". ?Please specify the Identifier State.";
	private String str7 = "One or more \"Active\" Suspensions may be released when the changes to the Identifier are saved. Proceed with the changes\\?";
	private String str8 = "No changes can be made to the Identifier, other than Status changes, since the Identifier Status is Verified.";
	private String str9 = "Identifier Country is required for Identifier Type \"OTHER\". ?Please specify the Identifier Country.";
	private String str10 = "";
}
