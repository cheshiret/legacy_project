package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier.add.individual;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerSuspensionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify warning message in adding identifier page
 * @Preconditions: Have Individual customer with fName: QA-Customer1, mName:QaTest-CusotmerProfile-1,lName:TEST-Profile1 and date of birth is '30-Dec-1966'
 * @Note: Split case: AddIndentifierForNoneIndividualCustomer
 * @SPEC: Add Customer Identifier
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 2, 2012
 */
public class WarningMesValidation extends LicenseManagerTestCase{
	private LicMgrAddIdentifiersPage addIdenPg = LicMgrAddIdentifiersPage.getInstance();
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
	private LicMgrCustomerSuspensionPage suspensionPg = LicMgrCustomerSuspensionPage.getInstance();
	private CustomerIdentifier identifier_1 = new CustomerIdentifier();
	private CustomerIdentifier identifier_2 = new CustomerIdentifier();
	private Suspension suspension = new Suspension();
	private boolean pass = true;
	private String identifierType = "NON-US DL Number";

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Go to customer details page
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoIdentifiersFromCustomerDetailsPg();

		//Add two identifiers
		lm.addIdentifiersInInditifierPage(cust.identifiers,schema);

		//Add suspension for 'NON-US DL Number' other than 'Social Security Number'
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
//		lm.manageSuspensions("Add", suspension);
		lm.addCustomerSuspension(suspension);

		//Verify warning message in adding identifier page
		lm.switchIdentifiersAndSuspensionsPg(suspensionPg);
		lm.switchIndentifiersAndAddIdentifiersPg(custIdenPg);
		this.verifyWarningMes();

		//Remove suspension
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
//		lm.manageSuspensions("Remove", suspension);
		lm.removeCustomerSuspension(suspension);

		//Throw exception if it exists
		if(!pass){
			throw new TestCaseFailedException("Case is running failed.");
		}

		//Logout 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";
		cust.fName = "QA-Customer1";
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";

		//Identifier info
		identifier_1.status = "Active";
		identifier_1.identifierType = identifierType;
		identifier_1.identifierNum = "111112";
		identifier_1.state = "";
		identifier_1.country = "Canada";
		identifier_1.creationApp = "LicenseManager";
		identifier_1.createUser = "qa-auto-fm";
		identifier_1.verifyStatus = "Not Applicable";

		identifier_2.status = "Active";
		identifier_2.identifierType = "US Drivers License";
		identifier_2.identifierNum = "ABC123456";
		identifier_2.state = "Missouri";
		identifier_2.country = "";
		identifier_2.creationApp = "LicenseManager";
		identifier_2.createUser = "qa-auto-fm";
		identifier_2.verifyStatus = "Not Applicable";
		cust.identifiers.add(identifier_1);
		cust.identifiers.add(identifier_2);

		//Suspension info
		suspension.beginDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.identifiersTypes = new String[]{"US Drivers License"};  
		suspension.identifiersNums = new String[]{"ABC123456"}; 
		suspension.comment = "suspension"+DataBaseFunctions.getEmailSequence();
	}

	private void verifyWarningMes(){
		//Social Security Number
		this.verifyErrorMessage(str1); //Identifier type
		addIdenPg.selectIdentifierType("Social Security Number");
		this.verifyErrorMessage(str2); //Identifier#
		addIdenPg.setIdentifierNum("1234567890");
		this.verifyErrorMessage(str3); //Identifier# doesn't contain exactly 9 degits
		addIdenPg.setIdentifierNum("000456789");
		this.verifyErrorMessage(str4); //Identifier# first three numbers are all zeros
		addIdenPg.setIdentifierNum("123006789");
		this.verifyErrorMessage(str4); //Identifier# fourth and fifth numbers are both zeros 
		addIdenPg.setIdentifierNum("123450000");
		this.verifyErrorMessage(str4); //Identifier# sixth to ninth numbers are all zeros
		addIdenPg.selectIdentifierType("US Drivers License"); 
		addIdenPg.setIdentifierNum("1234");
		addIdenPg.selectState("New York");
		//US Drivers License
		this.verifyErrorMessage(str5);//Identifier# contains less than 5 numbers and letters combined
		addIdenPg.setIdentifierNum("1a -@");
		this.verifyErrorMessage(str5);//Identifier# contains a character other than a number, a letter, an embedded space, or a dash
		addIdenPg.setIdentifierNum("ABC123456");
		addIdenPg.selectState(0);
		this.verifyErrorMessage(str6);//State
		addIdenPg.selectState("Missouri");//Mississippi
		this.verifyErrorMessage(str7); //'US Drivers License' has added to this customer profile 
		addIdenPg.selectIdentifierType(identifierType);
		addIdenPg.setIdentifierNum("1a -@");
		addIdenPg.selectCountry("United Kingdom");//United States
		//NON-US DL Number
		this.verifyErrorMessage(str8);//Identifier# contains a character other than a number, a letter, an embedded space, or a dash
		addIdenPg.setIdentifierNum("111112");//111111111
		addIdenPg.selectCountry(0);
		this.verifyErrorMessage(str9);//country
		addIdenPg.selectCountry("Canada");//United States
		this.verifyErrorMessage(str10);//The identifier with same type, number and country has added to this customer profile 
		addIdenPg.setIdentifierNum("111113");
		this.verifyErrorMessage(str11);//'NON-US DL Number' has associated active suspension
	}

	private void verifyErrorMessage(String expectMsg) {
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		String actualMsg = "";
		addIdenPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, addIdenPg);
		if(page == confirmDialogWidget){
			actualMsg = confirmDialogWidget.getMessage();
		}
//		if(page == addIdenPg  && page != confirmDialogWidget){
		if(page == addIdenPg){
			actualMsg = addIdenPg.getWarnMes();
		}
		if(!actualMsg.matches(expectMsg)){
			logger.error("The actual error message:"+actualMsg);
			logger.error("The expect error message:"+expectMsg);
			pass &=false;
		}
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickCancel();
			ajax.waitLoading();
			addIdenPg.exists();
		}
	}

	//Warning message
	private String str1 = "Identifier Type is required. ?Please specify the Identifier Type.";
	private String str2 = "Identifier Number is required for Identifier Type \"Social Security Number\". ?Please specify the Identifier Number.";
	private String str3 = "The Identifier Number for Identifier Type \"Social Security Number\" must contain exactly 9 digits. ?Please re-enter the Identifier Number.";
	private String str4 = "The Identifier Number for Identifier Type \"Social Security Number\" is invalid. ?Please re-enter.";
	private String str5 = "The Identifier Number for Identifier Type \"US Drivers License\" must only contain numbers and letters. ?Please re-enter.";
	private String str6 = "Identifier State is required for Identifier Type \"US Drivers License\". ?Please specify the Identifier State.";
	//	private String str7 = "An Identifier with Type \"US Drivers License\" and the same Identifier Number, State/Province and Country already exists for this Customer. Please verify.";//DEFECT-33061(Won't fix)
	private String str7 = "An Identifier with Type US Drivers License and the same Identifier Number, State/Province and Country already exists for this Customer. ?Please verify.";
	private String str8 = "Identifier Number \"1a -@\" is invalid. Identifier Number must contain at least 5 numbers and letters combined, " +
	"and must only contain numbers, letters, embedded spaces or a dash. ?Please re-enter.";
	private String str9 = "Identifier Country is required for Identifier Type \"NON-US DL Number\". ?Please specify the Identifier Country.";
	//	private String str10 = "An Identifier with Type \"NON-US DL Number\" and the same Identifier Number, State/Province and Country already exists for this Customer. Please verify.";//DEFECT-33061(Won't fix) 
	private String str10 = "An Identifier with Type NON-US DL Number and the same Identifier Number, State/Province and Country already exists for this Customer. ?Please verify.";
	private String str11 = "An \"Active\" or \"Verified\" Identifier with Type \"NON-US DL Number\" already exists for this Customer, " +
	"and will be set to \"Inactive\" when the new Identifier is added. As a result, one or more \"Active\" Suspensions may be released. Proceed\\?";

}
