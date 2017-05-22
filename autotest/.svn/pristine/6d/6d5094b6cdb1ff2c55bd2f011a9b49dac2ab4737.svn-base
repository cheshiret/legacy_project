package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 *
 * @Description:
 * @Preconditions: Have Individual customer with fName: QA-Customer8, mName:QaTest-CusotmerProfile-8,lName:TEST-Profile8 and date of birth is 'Fri Jul 1 1955'
 *                 Have Identifier:
 *                      US Drivers License, 111111146(Inactive)
 *                      US Drivers License, 111111147(active)
 *                      Employee Federal Identification Number, 111111148(inactive)
 *                      Employee Federal Identification Number, 111111149(active)
 *                      Tax ID, 111111150(inactive)
 *                      Tax ID, 111111151(active)
 *                 Have active suspension for active Tax ID, 111111151
 *                 Have business customer with fName: QA-Customer6, mName:QaTest-CusotmerProfile-6,lName:TEST-Profile6 and date of birth is 'Jul 25 1978'
 *                 Have Identifier:
 *                       US Drivers License, Number 111111146(Active, Added)
 * @SPEC: Active Customer Identifier, Deactivate Customer Identifier and Remove Customer Identifier
 * @Task#: AUTO-552
 *
 * @author swang5
 * @Date
 */
public class ActivateDeactivateRemoveIndentifierForNoneIndividualCustomer extends LicenseManagerTestCase{
	private LicMgrCustomerIdentifiersPage custIdentifierPg = LicMgrCustomerIdentifiersPage.getInstance();
	private CustomerIdentifier identifier_1 = new CustomerIdentifier();
	private CustomerIdentifier identifier_2 = new CustomerIdentifier();
	private List<CustomerIdentifier> identifiers = new ArrayList<CustomerIdentifier>();
	private String idenID_1, idenID_2, idenID_3, idenID_4, idenID_5 = "";
	private String warnMes_1, warnMes_2, warnMes_3, warnMes_4, warnMes_5, warnMes_6;
	private boolean pass = true;
	private Customer otherCust = new Customer();

	public void execute() {
		//Login license manager and goto identifiers page
	
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		//Add identifier for customer2
//		cust1.licenseNum = lm.getCustomerNum(cust1, schema);
//		lm.gotoCuscomerDetailsFromSearchPg(cust1.licenseType, cust1.licenseNum , cust1.customerClass);
//		this.addIdentifiersInInditifierPage(identifier_1);

		//Go to customer1
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoIdentifiersFromCustomerDetailsPg();

		//Get identifier IDs
		idenID_1 = custIdentifierPg.getIdentifierID("US Drivers License", "111111146");
		idenID_2 = custIdentifierPg.getIdentifierID("Employee Federal Identification Number", "111111148");
		idenID_3 = custIdentifierPg.getIdentifierID("Tax ID", "111111150");
		idenID_4 = custIdentifierPg.getIdentifierID("Tax ID", "111111151");
		idenID_5 = custIdentifierPg.getIdentifierID("Employee Federal Identification Number", "111111149");

		lm.getCustomerNumByCustName(otherCust.lName, otherCust.fName, schema);
		this.initializeWarnMess(idenID_1, idenID_2, idenID_3, idenID_4, idenID_5);

		//Try to activate (inactive)identifier that the same identifier have assigned to other customer
	
		String actualMsg = lm.getWarnMesWhenChangeIdentifierStatus(idenID_1,"Activate");
		this.verifyWarnMess(actualMsg, warnMes_1);
		//Try to activate (inactive) identifier that the same identifier type have assigned the same customer
		actualMsg = lm.getWarnMesWhenChangeIdentifierStatus(idenID_2, "Activate");
		this.verifyWarnMess(actualMsg, warnMes_2);
		//Try to activate (inactive) identifier that the same identifier type have assigned the same customer and associated with suspension
		actualMsg = lm.getWarnMesWhenChangeIdentifierStatus(idenID_3, "Activate");
		this.verifyWarnMess(actualMsg, warnMes_3);
		//Try to deactivate (active) identifier which has associated suspension
		actualMsg = lm.getWarnMesWhenChangeIdentifierStatus(idenID_4, "Deactivate");
		this.verifyWarnMess(actualMsg, warnMes_4);
		//Try to remove active identifier
		actualMsg = lm.getWarnMesWhenChangeIdentifierStatus(idenID_5, "Remove");
		this.verifyWarnMess(actualMsg, warnMes_5);
		//Try to remove inactive identifier
		actualMsg = lm.getWarnMesWhenChangeIdentifierStatus(idenID_2, "Remove");
		this.verifyWarnMess(actualMsg, warnMes_6);

		//Add identifier
		lm.addIdentifiersInInditifierPage(identifiers);
		//Inactivate identifier
		lm.changeIdentifierStatus(identifier_2.identifierType, identifier_2.identifierNum, "Deactivate");
		this.verifyIdentifierStatus(identifier_2.identifierType, identifier_2.identifierNum, "Inactive");
		//Activate identifier
		lm.changeIdentifierStatus(identifier_2.identifierType, identifier_2.identifierNum, "Activate");
		this.verifyIdentifierStatus(identifier_2.identifierType, identifier_2.identifierNum, "Active");
		//Remove identifier
		lm.changeIdentifierStatus(identifier_2.identifierType, identifier_2.identifierNum, "Remove");
		//Verify in UI
		this.checkIdentifierExist(identifier_2.identifierType, identifier_2.identifierNum);

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
		cust.businessName = "@QaTest-CusotmerProfile-8";
        cust.fName = "QA-Customer8";
		cust.mName = "QaTest-CusotmerProfile-8";
		cust.lName = "TEST-Profile8";
		cust.dateOfBirth = "Jul 01 1955";

		identifier_1.identifierType = "US Drivers License";
		identifier_1.identifierNum = "111111146";
		identifier_1.state = "Missouri";

		identifier_2.identifierType = "Passport";
		identifier_2.identifierNum = "1q1a5r4w";
		identifier_2.country = "Canada";

		identifiers.add(identifier_2);
		
		otherCust.customerClass = "BUSINESS";
		otherCust.lName = "TEST-Profile6";
		otherCust.fName = "QA-Customer6";
		otherCust.businessName = "@QaTest-CusotmerProfile-6";
		otherCust.dateOfBirth = "Jul 25 1978";
		otherCust.identifier.identifierType = "MDWFP #";
		otherCust.identifier.identifierNum = lm.getCustomerNumByCustName(otherCust.lName, otherCust.fName, schema);
	}

	private void verifyWarnMess(String actualMsg, String expectMsg){
		if(!actualMsg.replaceAll("'", "\"").equals(expectMsg)){
			pass &=false;
			logger.error("-----The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}

	public void initializeWarnMess(String idenID_1, String idenID_2, String idenID_3, String idenID_4, String idenID_5){
		//Warning message
		warnMes_1 = "Identifier with ID \""+idenID_1+"\" cannot be activated, since an \"Active\" or \"Verified\" Identifier with Type \"US Drivers License\" and the same Identifier Number, State and Country already exists for another Customer: "+otherCust.businessName+" ("+otherCust.identifier.identifierNum+"). Do you wish to merge these customers?";
		warnMes_2 = "An \"Active\" or \"Verified\" Identifier with Type \"Employee Federal Identification Number\" already exists for this Customer, and will be set to \"Inactive\" when the Identifier with ID \""+idenID_2+"\" is activated. Proceed?";
		warnMes_3 = "An \"Active\" or \"Verified\" Identifier with Type \"Tax ID\" already exists for this Customer, and will be set to \"Inactive\" when the Identifier with ID \""+idenID_3+"\" is activated. As a result, one or more \"Active\" Suspensions may be released. Proceed?";
		warnMes_4 = "One or more \"Active\" Suspensions may be released when the Identifier with ID \""+idenID_4+"\" is deactivated. Proceed with the deactivate?";
		warnMes_5 = "Once removed, the Identifier with ID \""+idenID_5+"\" will no longer be visible. Proceed with the remove?";
		warnMes_6 = "Once removed, the Identifier with ID \""+idenID_2+"\" will no longer be visible. Proceed with the remove?";
	}

	@SuppressWarnings("unused")
	private void addIdentifiersInInditifierPage(CustomerIdentifier identifier){
		LicMgrAddIdentifiersPage addIden = LicMgrAddIdentifiersPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
		lm.gotoAddIdentifiersFromCustomerDetailsPg();
		addIden.setIdentifier(identifier);
		addIden.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget,addIden, custIdentifierPg);
		if (page == confirmDialogWidget) {
			confirmDialogWidget.clickOK();
		}
		if(page == addIden){
			if(addIden.getWarnMes().contains("-----The same Identifier Number, State/Province and Country already exists for this Customer. Please verify.")){
				addIden.clickCancel();
			}
		}
		custIdentifierPg.waitLoading();
		custIdentifierPg.clickCustomers();
		custSearchPg.waitLoading();
	}

	private void verifyIdentifierStatus(String idenType, String idenNum, String idenStatus){
		if(!custIdentifierPg.getIdentifierStatus(idenType, idenNum).equals(idenStatus)){
			pass &=false;
			logger.error("-----Identifier status should be "+idenStatus);
		}
	}

	private void checkIdentifierExist(String idenType, String idenNum){
		if(!custIdentifierPg.getIdentifierID(idenType, idenNum).equals("null")){
			pass &=false;
			logger.error("-----Identifier with identifier type = "+idenType+ " and identiifier number = "+idenNum+ " should don't display in UI!");
		}
	}
}
