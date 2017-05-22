package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

public class EditNoneIndividualCustomer extends LicenseManagerTestCase{
	LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
	LicMgrCustomerDetailsPage customerDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	LicMgrCustomersSearchPage customerSearchPg = LicMgrCustomersSearchPage.getInstance();
	LicMgrCustomerAdressesPage customerAddressPg = LicMgrCustomerAdressesPage.getInstance();
	CustomerIdentifier identifier = new CustomerIdentifier();
	String[] changeHistoryValues = new String[8];
	String tableValue = "";
	boolean pass = true;

	public void execute() {
		lm.loginLicenseManager(login);

		//Add one customers
		//lm.gotoNewCustomerPage(cust.customerClass);
		//lm.addOrEditCustomerInfo(cust, newCustPage);
		
        cust.custNum = lm.getCustomerNumByCustName("TEST-Certification4", "QA-Certification4", schema);
        String str0 = "An \"Active\" or \"Verified\" Identifier with Type \"Tax ID\" and the same Identifier Number, " +
    	"State and Country already exists for another Customer with Customer Class \"Business\". " +
    	"Please verify and re-enter as required.";
		//1.Verify warning message when identifier# assign to other customer when Status is Active
   
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, newCustPage);
		this.verifyWarnMesWhenAddNewCustomer(str0);
		
		cust.identifiers.clear();
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, newCustPage);
		lm.finishAddOrEditCustomer();

		//2.Cancel edit customer
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		this.cancelInCustomerDetailsPg();

		//3.Verify fields can't be edit other than Status when Status is Deceased
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		customerDetailsPg.editCustomerStatus("Deceased");
		customerDetailsPg.clickEdit();
		ajax.waitLoading();
		this.verifyCanNotBeEditedFields(customerDetailsPg.custStatusIdPattern, "false");
		this.verifyCanNotBeEditedFields(customerDetailsPg.custNumIdPattern, "true");
		this.verifyCanNotBeEditedFields(customerDetailsPg.custClassIdPattern, "true");
		this.verifyCanNotBeEditedFields(customerDetailsPg.creationApplicationIdPattern, "true");
		this.verifyCanNotBeEditedFields(customerDetailsPg.creationDateIdPattern, "true");
		this.verifyCanNotBeEditedFields(customerDetailsPg.creationUserIdPattern, "true");

		//Remove identifier
		customerDetailsPg.editCustomerStatus("Active");
		//5.Verify Alternate Address
		lm.gotoAddressedFromCustomerDetailsPg();
		customerDetailsPg.clickEdit();
		ajax.waitLoading();
		customerAddressPg.setAddr(cust.physicalAddr.address, 2);
		this.verifyErrorMessage(str2);//Alternate address: Country  
		customerAddressPg.selectAddrCountry(cust.physicalAddr.country, 2);
		this.verifyErrorMessage(str3);//Alternate address: Zip/Postal
		customerAddressPg.setAddrZip(cust.physicalAddr.zip, 2);
		this.verifyErrorMessage(str1);//Alternate address: city town
		customerAddressPg.setAddrCity(cust.physicalAddr.city, 2);
		this.verifyErrorMessage(str4);//Alternate address: State/Province.
		customerAddressPg.selectAddrState(cust.physicalAddr.state, 2);
		this.verifyErrorMessage(str5);//Alternate address: County
		
//		this.verifyErrorMessage(str1);//Alternate address: city town
//		customerAddressPg.setAddrCity(cust.physicalAddr.city, 2);	
//		this.verifyErrorMessage(str2);//Alternate address: Country  
//		customerAddressPg.selectAddrCountry(cust.physicalAddr.country, 2);
//		this.verifyErrorMessage(str3);//Alternate address: Zip/Postal
//		customerAddressPg.setAddrZip(cust.physicalAddr.zip, 2);
//		this.verifyErrorMessage(str4);//Alternate address: State/Province.
//		customerAddressPg.selectAddrState(cust.physicalAddr.state, 2);
//		this.verifyErrorMessage(str5);//Alternate address: County

		//6.logs for updates mailling address 
		this.verifyChangeHistory(changeHistoryValues);

		//Throw exception
		if(!pass){
			throw new ErrorOnPageException("Case is running failed.");
		}

		//Logout 
		lm.gotoHomePage();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//Customer profile information
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.customerClass = "Business";//COMMERCIAL
		cust.seq = String.valueOf(DataBaseFunctions.getEmailSequence());
		cust.fName = "QA" + cust.seq;
		cust.lName = "TEST" + cust.seq;
		cust.dateOfBirth = "2000/01/01";
		cust.businessName = "business";
		cust.hPhone = "123456";
		//Customer attribute info
		cust.custGender = "Male";
		cust.ethnicity = "white";
		//Address info
		cust.physicalAddr.address = "40 South St";
		cust.physicalAddr.city = "Ballston Spa";
		cust.physicalAddr.state = "New York";
		cust.physicalAddr.county = "Saratoga";//Saratoga County
		cust.physicalAddr.zip = "12020-1029";
		cust.physicalAddr.country = "United States";
		//Identifier info
		identifier.identifierType = "Tax ID";
		identifier.identifierNum = "888888888";
		cust.identifiers.add(identifier);
		//Change history Value
		changeHistoryValues[0] = DateFunctions.formatDate(DateFunctions.getCurrentDate(), "E MMM d yyyy");
		changeHistoryValues[1] = "Addresses & Contacts-Mailing Address";
		changeHistoryValues[2] = "Update";
		changeHistoryValues[3] = "Address";
		changeHistoryValues[4] = cust.physicalAddr.address;
		changeHistoryValues[5] = "Automation Testing";
		changeHistoryValues[6] = DataBaseFunctions.getLoginUserName(login.userName).replaceAll(",", ", ");
		changeHistoryValues[7] = login.location.split("/")[1];
	}

	public void cancelInCustomerDetailsPg(){
		customerDetailsPg.clickCancel();
		customerSearchPg.waitLoading();
		customerSearchPg.searchCustomer(cust.lName, cust.fName);
		if(customerSearchPg.getCusttableRowNum()!=2){
			pass &=false;
			logger.error("Customer row mumber should be 2.");
		}
	}

	public void verifyCanNotBeEditedFields(RegularExpression idPattern, String expectValue) {
		String disabledValue = customerDetailsPg.getPropertyValue(idPattern, ".disabled");
		if(!disabledValue.equals(expectValue)){
			pass &=false;
			logger.error("The actual property valuse: '" + disabledValue
					+"' is not match the expect value: '" +expectValue+"'");
		}
	}

	public void verifyWarnMesWhenAddNewCustomer(String message){
		newCustPage.clickApply();
		ajax.waitLoading();
		if(!newCustPage.getErrorMsg().equals(message)){
			pass &=false;
			logger.error("Message is incorrect.");
			
			/*//Test
			System.out.println(message);
			System.out.println(newCustPage.getErrorMsg());
			//Test
*/		}
		newCustPage.clickCancel();
		ajax.waitLoading();
		customerSearchPg.waitLoading();
	}

	public void emptyAddress(int index){
		customerAddressPg.setAddr("", index);
		customerAddressPg.setAddrCity("", index);
		customerAddressPg.setAddrZip("", index);
	}

	public void verifyErrorMessage(String expectMsg) {
		ajax.waitLoading();
		customerAddressPg.waitLoading();
		customerAddressPg.clickSave();
		ajax.waitLoading();
		customerAddressPg.waitLoading();
		String actualMsg = customerDetailsPg.getWarnMsg();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			pass &=false;
			logger.error("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
			/*//Test
			throw new ErrorOnPageException("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
			//Test
*/		}
	}

	public void verifyChangeHistory(String[] changeHistoryValues){
		//TODO: Need to split this check-point into another case. (Refer to case: VerifyChangeHistoryForCustEducation) 
		//TODO: Remove the hard-code "custChangeHistoryPg.getTableRowCells(1)" to get the actual value.
		LicMgrCustomerChangeHistoryPage custChangeHistoryPg = LicMgrCustomerChangeHistoryPage.getInstance();

		customerAddressPg.selectAddrCounty(cust.physicalAddr.county, 2);
		ajax.waitLoading();
		customerAddressPg.unSelectMaillAddrSameAsPhy();
		customerAddressPg.setMailAddr("Automation Testing");
		customerAddressPg.clickSave();
		ajax.waitLoading();

		customerDetailsPg.clickChangeHistory();
		ajax.waitLoading();
		custChangeHistoryPg.waitLoading();

		List<String> tableCells = new ArrayList<String>();
//		tableCells = custChangeHistoryPg.getTableRowCells(3);
		tableCells = custChangeHistoryPg.getTableRowCells(changeHistoryValues[5]);
		for(int i=0; i<tableCells.size(); i++){
			if(!tableCells.get(i).contains(changeHistoryValues[i])){
				pass &=false;
				logger.error("Actual Value "+tableCells.get(i)+
						" doesn't equal to expect value "+changeHistoryValues[i]);
				
				/*//Test
				throw new ErrorOnPageException("Actual Value "+tableCells.get(i)+
						" doesn't equal to expect value "+changeHistoryValues[i]);
				//Test
*/			}
		}
		custChangeHistoryPg.clickReturnToCustomerDetail();
		ajax.waitLoading();
		customerDetailsPg.waitLoading();
	}

	//Message
	//String str0 = "An \"Active\" or \"Verified\" Identifier with Type \"Tax ID:"+cust.custNum+"\" and the same Identifier Number, " +
	//"State/Province and Country already exists for another Customer with Customer Class \"Individual\". " +
	//"Please verify and re-enter as required.";
	String str1 = "City/Town is required for Address Type \"Alternate Address\". Please specify the City/Town.";
	String str2 = "Country is required for Address Type Alternate Address. Please specify the Country.";
	String str3 = "Zip/Postal Code is required for Address Type \"Alternate Address\". Please specify the Zip/Postal Code.";
	String str4 = "State is required for Address Type \"Alternate Address\". Please specify the State.";
	String str5 = "County is required for Address Type \"Alternate Address\". Please specify the County.";
}
