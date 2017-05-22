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
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author swang5
 * @Date
 */
public class EditIndividualCustomer extends LicenseManagerTestCase{
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
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, newCustPage);
		lm.finishAddOrEditCustomer();

		//1.Verify warning message when identifier# assign to other customer when Status is Active
		lm.gotoNewCustomerPage(cust.customerClass);
		lm.addOrEditCustomerInfo(cust, newCustPage);
		this.verifyWarnMesWhenAddNewCustomer(str0);

		//2.Cancel edit customer
		lm.gotoCustomerDetails(cust.lName, cust.fName);
		this.cancelInCustomerDetailsPg();

		//3.Verify fields can't be edit other than Status when Status is Deceased
		lm.gotoCustomerDetails(cust.lName, cust.fName);
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
		lm.gotoIdentifiersFromCustomerDetailsPg();
		lm.changeIdentifierStatus(cust.identifiers.get(0).identifierType, cust.identifiers.get(0).identifierNum, "Remove");

		//5.Verify Alternate Address
		lm.gotoAddressedFromCustomerDetailsPg();
		//New feature need to click enable editing button.
		customerDetailsPg.clickEdit();
		ajax.waitLoading();
		customerAddressPg.setAddr(cust.physicalAddr.address, 2);
		this.verifyErrorMessage(str2);//Alternate address: Country  
		customerAddressPg.selectAddrCountry(cust.physicalAddr.country, 2);
		this.verifyErrorMessage(str3);//Alternate address: Zip/Postal
		
		//Added by Phoebe Country; other than US or Canada,
		customerAddressPg.selectAddrCountry("Albania", 2);
		customerAddressPg.setAddrZip("#", 2);	
		this.verifyErrorMessage(str6);
		
		//This can not be changed for error message str28, must be "Canada"
		customerAddressPg.selectAddrCountry("Canada", 2);
		customerAddressPg.setAddrZip("12", 2);	
		this.verifyErrorMessage(str7);
		
		//This can not be changed for error message str28, must be "United State"
		customerAddressPg.selectAddrCountry("United States", 2);
		customerAddressPg.setAddrZip("23", 2);	
		this.verifyErrorMessage(str8);
		
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

		//6.logs for updates mailing address
		pass &= this.verifyChangeHistory(changeHistoryValues);

		//Throw exception
		if(!pass){
			throw new TestCaseFailedException("Case is running failed.");
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
		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.customerClass = "INDIVIDUAL";
		cust.fName = "QA-" + cust.seq;
		cust.lName = "AUTO-" + cust.seq;
		cust.dateOfBirth = "2000/01/01";
		//Customer attribute info
		cust.custGender = "Male";
		cust.ethnicity = "white";
		//Address info
		cust.physicalAddr.address = "40 South St";
		cust.physicalAddr.city = "Ballston Spa";
		cust.physicalAddr.state = "New York";
		cust.physicalAddr.county = "Saratoga";
		cust.physicalAddr.zip = "12020-1029";
		cust.physicalAddr.country = "United States";
		//Identifier info
		identifier.identifierType = "NON-US DL Number";
		identifier.identifierNum = "A12BC3D45" + cust.seq;
		identifier.country = "United Kingdom";//United States
		cust.identifiers.add(identifier);
		//Change history Value
		changeHistoryValues[0] = DateFunctions.formatDate(DateFunctions.getCurrentDate(), "E MMM d yyyy");
		changeHistoryValues[1] = "Addresses & Contacts-Mailing Address";
		changeHistoryValues[2] = "Update";
		changeHistoryValues[3] = "Address";
		changeHistoryValues[4] = cust.physicalAddr.address;
		changeHistoryValues[5] = "Automation Testing";
		changeHistoryValues[6] = DataBaseFunctions.getLoginUserName(login.userName).replace(",", ", ");
		changeHistoryValues[7] = login.location.split("/")[1];
	}

	private void cancelInCustomerDetailsPg(){
		customerDetailsPg.clickCancel();
		customerSearchPg.waitLoading();
		customerSearchPg.searchCustomer(cust.lName, cust.fName);
		if(customerSearchPg.getCusttableRowNum()!=2){
			pass &=false;
			logger.error("Customer row mumber should be 2.");
		}
	}

	private void verifyCanNotBeEditedFields(RegularExpression idPattern, String expectValue) {
		String disabledValue = customerDetailsPg.getPropertyValue(idPattern, ".disabled");
		if(!disabledValue.equals(expectValue)){
			pass &=false;
			logger.error("For Pattern=" + idPattern + ", the actual property valuse: '" + disabledValue
					+"' is not match the expect value: '" +expectValue+"'");
		}
	}

	private void verifyWarnMesWhenAddNewCustomer(String message){
		newCustPage.clickApply();
		String actualMsg = newCustPage.getErrorMsg();
		if(!actualMsg.equals(message)){
			pass &=false;
			logger.error("Message is incorrect. Actual value is: " + actualMsg + ", but expected is: " + message);
		}
		newCustPage.clickCancel();
		customerSearchPg.waitLoading();
	}

	private void verifyErrorMessage(String expectMsg) {
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
		}
	}

	private boolean verifyChangeHistory(String[] changeHistoryValues){
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
		boolean result = true;
		for(int i=0; i<tableCells.size(); i++){
			if(!tableCells.get(i).contains(changeHistoryValues[i])){
				result &=false;
				logger.error("Actual Value "+tableCells.get(i)+
						" doesn't equal to expect value "+changeHistoryValues[i]);
			}
		}
		custChangeHistoryPg.clickReturnToCustomerDetail();
		customerDetailsPg.waitLoading();
		//test
		if(!result){
			throw new ErrorOnPageException("verifyChangeHistory error");
		}
		//test
		
		return result;
	}

	//warning message
	String str0 = "An \"Active\" or \"Verified\" Identifier with Type \"NON-US DL Number\" and the same Identifier Number, " +
	"State and Country already exists for another Customer with Customer Class \"Individual\". " +
	"Please verify and re-enter as required.";
	String str1 = "City/Town is required for Address Type \"Alternate Address\". Please specify the City/Town.";
	String str2 = "Country is required for Address Type Alternate Address. Please specify the Country.";
	String str3 = "ZIP/Postal Code is required for Address Type \"Alternate Address\". Please specify the Zip/Postal Code.";
	String str4 = "State is required for Address Type \"Alternate Address\". Please specify the State.";
	String str5 = "County is required for Address Type \"Alternate Address\". Please specify the County.";
	
	//Zip error message
	String str6 = "ZIP/Postal Code must contain at least 1 number or letter, and must only contain numbers, letters, " +
			"a single dash, or a single space. Please change your entries for Address Type \"Alternate Address\".";//New added by Phoebe of auto code review, this just for Country other than US or Canada,
	String str7 = "Postal Code must contain exactly 6 numbers and letters combined, and contain only the following characters: " +
			"number, letter, single dash, single space in one of the following formats: A#A #A# or A#A#A#, or A#A-#A# where A is any alphabetic character and # is a numeric digit from 0 to 9." +
			"Please change your entries for Address Type Alternate Address.";  //New added by Phoebe of auto code review, this just for Canada
	String str8 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
			" Please change your entries for Address Type \"Alternate Address\".";  //This is for US, Updated for DEFECT-49431
}
