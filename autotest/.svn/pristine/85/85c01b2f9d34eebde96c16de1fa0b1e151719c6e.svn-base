package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerSuspensionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: 1.Verify identifier information after canceling adding customer identifier
 *               2.Verify successfully add identifiers and verify verified-status
 *               3.Verify warning message in adding identifier page
 * @Preconditions: Have business customer with fName: QA-Customer2, mName:QaTest-CusotmerProfile-2,lName:TEST-Profile2 and date of birth is 'Have business customer with fName: QA-Customer4, mName:QaTest-CusotmerProfile-4,lName:TEST-Profile4 and date of birth is 'Jan 14 1977''
 * @SPEC: Add Customer Identifier
 * @Task#:
 * 
 * @author swang5
 * @Date  
 */
public class AddIndentifierForNoneIndividualCustomer extends LicenseManagerTestCase{
	private LicMgrAddIdentifiersPage addIdenPg = LicMgrAddIdentifiersPage.getInstance();
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
	private LicMgrCustomerSuspensionPage suspensionPg = LicMgrCustomerSuspensionPage.getInstance();
	private CustomerIdentifier identifier_1 = new CustomerIdentifier();
	private CustomerIdentifier identifier_2 = new CustomerIdentifier();
	private List<String> createdDates = new ArrayList<String>();
	private Suspension suspension = new Suspension();
	private boolean pass = true;

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);

		//Cancel adding customer identifier
		lm.gotoAddIdentifiersFromCustomerDetailsPg();
		this.cancelAddIndentifier();

		//Add two identifiers
		createdDates = lm.addIdentifiersInInditifierPage(cust.identifiers,schema);

		//Get identifiers IDs and created dates
		identifier_1.createDate = createdDates.get(0);
		identifier_1.id = custIdenPg.getIdentifierID(identifier_1.identifierType, identifier_1.identifierNum);
		identifier_2.createDate = createdDates.get(1);
		identifier_2.id = custIdenPg.getIdentifierID(identifier_2.identifierType, identifier_2.identifierNum);

		//Verify successfully add identifiers and verify verified-status
		custIdenPg.verifyIdentifierList(identifier_1);
		custIdenPg.verifyIdentifierList(identifier_2);

		//Add suspension for 'Employee Federal Identification Number' other than 'Trapper ID'
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
		lm.addCustomerSuspension(suspension);

		//Verify warning message in adding identifier page
		lm.switchIdentifiersAndSuspensionsPg(suspensionPg);
		lm.switchIndentifiersAndAddIdentifiersPg(custIdenPg);
		this.verifyWarMes();

		//Remove identifiers
		for(int i=0; i<cust.identifiers.size(); i++){
			lm.removeCustIdentifier(cust.identifiers.get(i).identifierType, cust.identifiers.get(i).identifierNum);
		}

		//Remove suspension
		lm.switchIdentifiersAndSuspensionsPg(custIdenPg);
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
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = "BUSINESS";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.businessName = "@QaTest-CusotmerProfile-2";
		cust.fName = "QA-Customer2";
		cust.mName = "QaTest-CusotmerProfile-2";
		cust.lName = "TEST-Profile2";
		cust.dateOfBirth = "Jan 14 1977";

		//Identifier info
		identifier_1.status = OrmsConstants.ACTIVE_STATUS;
		identifier_1.identifierType = "Employee Federal Identification Number";
		identifier_1.identifierNum = "111111114";
		identifier_1.state = "";
		identifier_1.country = "";
		identifier_1.createDate = DateFunctions.formatDate(DateFunctions.getToday(), "MM/dd/yyyy");
		identifier_1.creationApp = "LicenseManager";
		identifier_1.createUser = login.userName;
		identifier_1.verifyStatus = "Not Applicable";

		identifier_2.status = OrmsConstants.ACTIVE_STATUS;
		identifier_2.identifierType = "US Drivers License";//Trapper ID
		identifier_2.identifierNum = "111111115";
		identifier_2.state = "Missouri";
		identifier_2.createDate = DateFunctions.formatDate(DateFunctions.getToday(), "MM/dd/yyyy");
		identifier_2.creationApp = "LicenseManager";
		identifier_2.createUser = login.userName;
		identifier_2.verifyStatus = "Failed";

		cust.identifiers.add(identifier_1);
		cust.identifiers.add(identifier_2);

		//Suspension info
		suspension.beginDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.identifiersTypes = new String[]{identifier_2.identifierType};  
		suspension.identifiersNums = new String[]{identifier_2.identifierNum}; 
		suspension.comment = "Remove customer suspension - " + DateFunctions.getCurrentTime();
	}

	public void verifyWarMes(){
		//Employee Federal Identification Number
		this.verifyErrorMessage(str2); //Identifier type
		addIdenPg.selectIdentifierType("Employee Federal Identification Number");
		this.verifyErrorMessage(str3); //Identifier#
		addIdenPg.setIdentifierNum("1234567890");
		this.verifyErrorMessage(str4); //Identifier# doesn't contain exactly 9 degits
		addIdenPg.setIdentifierNum("111111124");//QaTest-CusotmerProfile-6 has this type and num identifier
		cust.licenseNum = lm.getCustomerNumByCustName("TEST-Profile6", "QA-Customer6", schema);
		str5 = "An \"Active\" or \"Verified\" Identifier with Type \"Employee Federal Identification Number\" and the same Identifier Number, " +
		"State/Province and Country already exists for another Customer with Customer Class \"Business\". Please verify and re-enter as required.";
//		this.verifyErrorMessage(str5); //Employee Federal Identification Number assign to another customer profile //TODO
		addIdenPg.setIdentifierNum("111111115");
		this.verifyErrorMessage(str1); //Employee Federal Identification Number has active suspension
		addIdenPg.selectIdentifierType("US Drivers License"); //Trapper ID
		//US Drivers License
		addIdenPg.setIdentifierNum("");
		this.verifyErrorMessage(str6);//Identifier#
		addIdenPg.setIdentifierNum("111111115");
		addIdenPg.selectState(0);
		this.verifyErrorMessage(str8);//State
		addIdenPg.selectState("Missouri");//Mississippi
		this.verifyErrorMessage(str9); //'Trapper ID' has added to this customer profile 
		addIdenPg.setIdentifierNum("1a -@");
		this.verifyErrorMessage(str7);//Identifier# contains a character other than a number, a letter, an embedded space, or a dash
		lm.switchIndentifiersAndAddIdentifiersPg(addIdenPg);
	}

	private void cancelAddIndentifier(){
		addIdenPg.setIdentifier(identifier_1);
		addIdenPg.clickCancel();
		ajax.waitLoading();
		custIdenPg.waitLoading();
		int row = custIdenPg.getIdentifierRow(identifier_1.id, identifier_1.identifierType, identifier_1.identifierNum);
		if(row!=-1){
			throw new ErrorOnDataException("It should not successfully to add the identifier(Type:"+identifier_1.identifierType+"), (Num: "+identifier_1.identifierNum+")");
		}
	}

	private void verifyErrorMessage(String expectMsg) {
		LicMgrAddIdentifiersPage addIdentifierPg = LicMgrAddIdentifiersPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		String actualMsg = "";

		addIdentifierPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, addIdentifierPg);
		if(page == confirmDialogWidget){
			actualMsg = confirmDialogWidget.getMessage();
		}else if(page == addIdentifierPg){
			actualMsg = addIdentifierPg.getWarnMes();
		}
		
		if(!actualMsg.trim().matches(expectMsg.trim())){
			throw new ErrorOnPageException("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}

		if(page == confirmDialogWidget){
			confirmDialogWidget.clickCancel();
			ajax.waitLoading();
			addIdentifierPg.waitLoading();
		}
	}

	//Warning message
	String str1 = "An \"Active\" or \"Verified\" Identifier with Type \"Employee Federal Identification Number\" already exists for this Customer, " +
	"and will be set to \"Inactive\" when the new Identifier is added. As a result, one or more \"Active\" Suspensions may be released. Proceed\\?";
	String str2 = "Identifier Type is required. ?Please specify the Identifier Type.";
	String str3 = "Identifier Number is required for Identifier Type \"Employee Federal Identification Number\". ?Please specify the Identifier Number.";
	String str4 = "The Identifier Number for Identifier Type \"Employee Federal Identification Number\" must contain exactly 9 digits. ?Please re-enter the Identifier Number.";
	String str5 ="";// "An \"Active\" or \"Verified\" Identifier with Type \"Employee Federal Identification Number:+cust.+\" and the same Identifier Number, " +
	//"State/Province and Country already exists for another Customer with Customer Class \"Business\". Please verify and re-enter as required.";
	String str6 = "Identifier Number is required for Identifier Type \"US Drivers License\". ?Please specify the Identifier Number.";
	String str7 = "The Identifier Number for Identifier Type \"US Drivers License\" must only contain numbers and letters. ?Please re-enter.";
	String str8 = "Identifier State is required for Identifier Type \"US Drivers License\". ?Please specify the Identifier State.";
	String str9 = "An Identifier with Type US Drivers License and the same Identifier Number, State/Province and Country already exists for this Customer. ?Please verify.";
}
