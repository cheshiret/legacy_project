package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrSelectCustomerClassWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Verify warning message when adding new customer profile and the messages after adding customer successfully
 * @Preconditions: No
 * @SPEC: Add Customer Profile
 * @Task#: 
 *  [Jane] I updated this case about the error message order, it has been passed on QA3(Orms3.02.02)
 * @author SWang
 * @Date  Mar 18, 2011
 */
public class AddNoneIndividalCustomer extends LicenseManagerTestCase{
	LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
	LicMgrCustomerDetailsPage custDetailPage = LicMgrCustomerDetailsPage.getInstance();
	LicMgrCustomersSearchPage customerPg = LicMgrCustomersSearchPage.getInstance();
	List<String> inCorrectDates = new ArrayList<String>();
	private boolean pass = true;

	public void execute() {
		lm.loginLicenseManager(login);

		//1.Cancel in LicMgrSelectCustomerClassWidget page
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		this.searchAInvalidCustomerBeforeAdding();
		this.cancelInCustomerClassWidgetPg();

		//2.Cancel in LicMgrNewCustomerPage
		lm.gotoNewCustomerPage(cust.customerClass);
		this.cancelInNewCustomerPg();

		//3. Verify all entries are valid.
		//Primary Contact
		lm.gotoNewCustomerPage(cust.customerClass);
		this.verifyErrorMessage(str0);//Business Name
		newCustPage.setBusinessName(cust.businessName);
		this.verifyErrorMessage(str1);//Business, Home, or Mobile Phone
		newCustPage.setBusinessPhone("123456");
		this.verifyErrorMessage(str2);//Primary ContactFirst Name
		newCustPage.setFirstName(cust.fName);
		this.verifyErrorMessage(str3);//Primary ContactLast Name
		newCustPage.setLastName(cust.lName);
//		this.verifyErrorMessage(str4);//Primary ContactDate of birth: Not been specified. // because OPTIONAL_DOB_IND is set as TRUE in C_CUST_CLASS, so System will NOT set the 'date of birth' as mandatory for non-individual customer
		
		newCustPage.setDateOfBirth(inCorrectDates.get(0));
		this.verifyErrorMessage(str5);//Primary Contact: Is greater than the Current Date in Contract Time Zone.
		newCustPage.setDateOfBirth(inCorrectDates.get(1));
		this.verifyErrorMessage(str6);//Primary ContactIs 150 or more years in the past.
		newCustPage.setDateOfBirth(cust.dateOfBirth);
		newCustPage.selectPhoneContactPreference("Home Phone");
		this.verifyErrorMessage(str7);//Home Phone & Phone Contact Preference
		newCustPage.setHomePhone(inCorrectDates.get(2));
		this.verifyErrorMessage(str10);//Home Phone 
		newCustPage.setHomePhone("");
		newCustPage.selectPhoneContactPreference("Cell Phone");
		this.verifyErrorMessage(str8);//Cell Phone(Mobile Phone) & Phone Contact Preference
		newCustPage.setMobilePhone(inCorrectDates.get(2));
		this.verifyErrorMessage(str10);//Cell Phone(Mobile Phone)
		newCustPage.setHomePhone("123456");
		newCustPage.setMobilePhone("");
		newCustPage.setBusinessPhone("");
		newCustPage.selectPhoneContactPreference("Work Phone");
		this.verifyErrorMessage(str9);//Work Phone(Business Phone) & Phone Contact Preference
		newCustPage.setBusinessPhone(inCorrectDates.get(2));
		this.verifyErrorMessage(str10);//Work Phone(Business Phone)
		newCustPage.setBusinessPhone("123456");
//		this.verifyErrorMessage(str11);//Preferred Contact Time
//		newCustPage.selectPreferedContactTime("Business Hour - Morning");
		newCustPage.setFax(inCorrectDates.get(2));
		this.verifyErrorMessage(str12);//Fax
		newCustPage.setFax("");
		newCustPage.setEmail(inCorrectDates.get(2));
		this.verifyErrorMessage(str13);//Email
		newCustPage.setEmail("");
		newCustPage.selectOverrideRequiredIdentifiers();
		this.verifyErrorMessage(str14);//overideReason

		//below two methods are used to reset the all default value as null
		newCustPage.selectPhysicalState(0);
		newCustPage.selectPhysicalCountry(0);
		
		//Physical Address
		newCustPage.setOverrideReason("Automation testing");
		this.verifyErrorMessage(str15);//Address
		
		newCustPage.setPhysicalAddress(cust.physicalAddr.address);
		this.verifyErrorMessage(str19);//Country
		newCustPage.selectPhysicalCountry(cust.physicalAddr.country);
		this.verifyErrorMessage(str17);//Zip/Postal
		newCustPage.setPhysicalZipCode(inCorrectDates.get(2));
		this.verifyErrorMessage(str24);//Zip/Postal
		newCustPage.setPhysicalZipCode(cust.physicalAddr.zip);
		this.verifyErrorMessage(str16);//City Town
		newCustPage.setPhysicalCityTown(cust.physicalAddr.city);
		this.verifyErrorMessage(str18);//State/Province
		newCustPage.selectPhysicalState(cust.physicalAddr.state);
		this.verifyErrorMessage(str20);//County

		//Mailling Address
		newCustPage.unselectMailingAddressSameAsPhysicalAddress();
		newCustPage.selectPhysicalCounty(cust.physicalAddr.county);
		this.verifyErrorMessage(str21);//Address
		newCustPage.setMailingAddress(cust.mailingAddr.address);
		this.verifyErrorMessage(str23);//Zip/Postal
		newCustPage.setMailingZipCode(inCorrectDates.get(2));
		this.verifyErrorMessage(str28);//Zip/Postal
		newCustPage.setMailingZipCode(cust.mailingAddr.zip);
		this.verifyErrorMessage(str22);//City Town 
		newCustPage.setMailingCityTown(cust.mailingAddr.city);
		newCustPage.selectMailingState(0); //'Empty'
		this.verifyErrorMessage(str25);//State/Province 
		newCustPage.selectMailingState(cust.mailingAddr.state);
		newCustPage.selectMailingCountry(0);
		this.verifyErrorMessage(str26);//Country
		newCustPage.selectMailingCountry(cust.mailingAddr.country);
		newCustPage.selectMailingState(cust.mailingAddr.state);
		this.verifyErrorMessage(str27);//County

		//Verify after adding customer successfully
		newCustPage.selectMailingState(cust.mailingAddr.state);
		newCustPage.selectMailingCounty(cust.mailingAddr.county);
		newCustPage.clickApply();
		ajax.waitLoading();
		custDetailPage.waitLoading();
		String temp = newCustPage.getCustomerNum();
		if(null==temp|| temp.length()<=0){//customer number
			pass &=false;
			logger.error("Customer number should be generated.");
		}
		temp = newCustPage.getCustomerClass();
		if(null==temp|| !temp.equalsIgnoreCase(cust.customerClass)){//customer class
			pass &=false;
			logger.error("Customer class should be "+cust.customerClass+",but actually it is "+temp);
		}
		temp = newCustPage.getCreationApplication();
		if(null==temp|| 
				!temp.equalsIgnoreCase(cust.creationApplication)){//creation application
			pass &=false;
			logger.error("Customer Creation Application should be "+cust.creationApplication+",but actually it is "+temp);
		}
		temp = newCustPage.getCreationDate();
		if(null==temp|| 
				!temp.equalsIgnoreCase(cust.creationDate)){//creation date
			pass &=false;
			logger.error("Customer Creation Date should be "+cust.creationDate+",but actually it is "+temp);
		}
		temp = newCustPage.getCreationUser().replaceAll(" ", "");
		if(null==temp|| 
				!temp.equalsIgnoreCase(cust.creationUser.replaceAll(" ", ""))){//creation user
			pass &=false;
			logger.error("Customer Creation User should be "+cust.creationUser+",but actually it is "+temp);
		}


		//Throw exception
		if(!pass){
			throw new TestCaseFailedException("Case is running failed.");
		}

		//4.Logout
		lm.gotoHomePage();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//Customer info
		cust.creationApplication = "LicenseManager";
		cust.creationDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(0), "E MMM d yyyy");
		cust.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		//Primary Contact
		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.customerClass = "Business";//COMMERCIAL
		cust.fName = "QA-" + cust.seq;
		cust.lName = "AUTO-" + cust.seq;
		cust.dateOfBirth = "Mar 01 2011";
		cust.businessName = cust.fName+" "+cust.lName;
		//Address info
		cust.physicalAddr.address = "40 South St";
		cust.physicalAddr.city = "Ballston Spa";
		cust.physicalAddr.state = "New York";
		cust.physicalAddr.county = "Saratoga";//Saratoga County
		cust.physicalAddr.zip = "12020-1029";
		cust.physicalAddr.country = "United States";
		cust.mailingAddr.address = cust.physicalAddr.address;
		cust.mailingAddr.city = cust.physicalAddr.city;
		cust.mailingAddr.state = cust.physicalAddr.state;
		cust.mailingAddr.county = "Saratoga";//Saratoga County
		cust.mailingAddr.zip = cust.physicalAddr.zip;
		cust.mailingAddr.country = cust.physicalAddr.country;
		//Incorrect 
		inCorrectDates.add("Jan 01 3000");
		inCorrectDates.add("Jan 01 1800");
		inCorrectDates.add("character");
	}

	private void searchAInvalidCustomerBeforeAdding() {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
		
		custSearchPg.waitLoading();
		custSearchPg.setLicenseNum("invalid123"); // set an invalid number
		custSearchPg.clickSearch();
		ajax.waitLoading();
		custSearchPg.waitLoading();
	}
	
	private void cancelInCustomerClassWidgetPg(){
		LicMgrSelectCustomerClassWidget custClassWgt = LicMgrSelectCustomerClassWidget
		.getInstance();

		customerPg.clickAddCustomer();
		ajax.waitLoading();
		custClassWgt.waitLoading();

		custClassWgt.clickCancel();
		ajax.waitLoading();
		customerPg.waitLoading();
	}

	private void cancelInNewCustomerPg(){
		newCustPage.clickCancel();
		ajax.waitLoading();
		customerPg.waitLoading();
	}

	private void verifyErrorMessage(String expectMsg) {
		newCustPage.clickApply();
		ajax.waitLoading();
		String actualMsg = newCustPage.getErrorMsg();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			pass &=false;
			logger.error("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}

	//Warning message
	String str0 = "Business Name is required. Please specify the Business Name.";
	String str1 = "At least one Phone is required for Contact Type Primary Contact. Please specify the Business, Home, or Mobile Phone.";
	String str2 = "First Name is required. Please specify the First Name.";
	String str3 = "Last Name is required. Please specify the Last Name.";
	String str4 = "Date Of Birth is required. Please specify the Date Of Birth.";
	String str5 = "Date of Birth must be today or in the past. Please re-enter.";
	String str6 = "The Date of Birth must be less than 150 years ago.";
	String str7 = "Home Phone is required. Please specify the Home Phone.";
	String str8 = "Mobile Phone is required. Please specify the Mobile Phone.";
	String str9 = "Business Phone is required. Please specify the Business Phone.";
	String str10 = "Phone (Business, Home, or Mobile) must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please re-enter.";
	String str11 = "Preferred Contact Time is required. Please specify the Preferred Contact Time.";
	String str12 = "Fax must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please re-enter.";
	String str13 = "Email must be a valid email. Please re-enter.";
	String str14 = "Override Reason is required. Please specify the Override Reason.";
	String str15 = "Address is required for Address Type \"Physical Address\". Please specify the Address.";
	String str16 = "City/Town is required for Address Type \"Physical Address\". Please specify the City/Town.";
	String str17 = "Zip/Postal Code is required for Address Type \"Physical Address\". Please specify the Zip/Postal Code.";
	String str18 = "State is required for Address Type \"Physical Address\". Please specify the State.";
	String str19 = "Country is required for Address Type Physical Address. Please specify the Country.";
	String str20 = "County is required for Address Type \"Physical Address\". Please specify the County.";
	String str21 = "Address is required for Address Type \"Mailing Address\". Please specify the Address.";
	String str22 = "City/Town is required for Address Type \"Mailing Address\". Please specify the City/Town.";
	String str23 = "Zip/Postal Code is required for Address Type \"Mailing Address\". Please specify the Zip/Postal Code.";
	String str25 = "State is required for Address Type \"Mailing Address\". Please specify the State.";
	String str26 = "Country is required for Address Type Mailing Address. Please specify the Country.";
	String str27 = "County is required for Address Type \"Mailing Address\". Please specify the County.";
	String str24 = 	"ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space. Please change your entries for Address Type \"Physical Address\".";
	String str28 =  "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space. Please change your entries for Address Type \"Mailing Address\".";
}
