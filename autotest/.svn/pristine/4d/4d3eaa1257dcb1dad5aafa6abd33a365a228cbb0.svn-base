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
 * [Jane] I updated this case about the error message order, it has been passed on QA3(Orms3.02.02)
 * @author SWang
 * @Date  Mar 18, 2011
 */
public class AddIndividalCustomer extends LicenseManagerTestCase{
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

		//3.Verify all entries are valid.
		//Name/DOB
		lm.gotoNewCustomerPage(cust.customerClass);
		
		//Quentin[20140217]
		newCustPage.selectGender(cust.custGender);
		newCustPage.selectEthnicity(cust.ethnicity);
		
		this.verifyErrorMessage(str0);//First Name
		newCustPage.setFirstName(cust.fName);
		this.verifyErrorMessage(str1);//Last Name
		newCustPage.setLastName(cust.lName);
		this.verifyErrorMessage(str2);//Date of birth: Not been specified.
		newCustPage.setDateOfBirth(inCorrectDates.get(0));
		this.verifyErrorMessage(str3);//Date of birth: Is greater than the Current Date in Contract Time Zone.
		newCustPage.setDateOfBirth(inCorrectDates.get(1));
		this.verifyErrorMessage(str4);//Date of birth: Is 150 or more years in the past.

		//Phone/Email
		newCustPage.setDateOfBirth(cust.dateOfBirth);
		newCustPage.setHomePhone(inCorrectDates.get(2));
		this.verifyErrorMessage(str5);//: Home phone
		newCustPage.setHomePhone("");
		newCustPage.setBusinessPhone(inCorrectDates.get(2));
		this.verifyErrorMessage(str5);//Business phone
		newCustPage.setBusinessPhone("");
		newCustPage.setMobilePhone(inCorrectDates.get(2));
		this.verifyErrorMessage(str5);//Mobile phone
		newCustPage.setMobilePhone("");
		newCustPage.setFax(inCorrectDates.get(2));
		this.verifyErrorMessage(str6);//Fax
		newCustPage.setFax("");
		newCustPage.setEmail(inCorrectDates.get(2));
		this.verifyErrorMessage(str7);//Email
		newCustPage.setEmail("");
		newCustPage.selectPhoneContactPreference("Home Phone");
		this.verifyErrorMessage(str8);//Home Phone & Phone Contact Preference
		newCustPage.selectPhoneContactPreference("Work Phone");
		this.verifyErrorMessage(str9);//Work Phone(Business Phone) & Phone Contact Preference
		newCustPage.selectPhoneContactPreference("Cell Phone");
		this.verifyErrorMessage(str10);//Cell Phone(Mobile Phone) & Phone Contact Preference
		newCustPage.setMobilePhone("123456");
		
		newCustPage.selectEthnicity(0);
		this.verifyErrorMessage(str12);//Ethnicity
		newCustPage.selectEthnicity("white");
	
		newCustPage.selectGender(0);
		this.verifyErrorMessage(str13);//Gender 
		newCustPage.selectGender("Male");

		//Name/DOB
		newCustPage.selectOverrideRequiredIdentifiers();//Override Reason 
		this.verifyErrorMessage(str14);

		//below two methods are used to reset the all default value as null
		newCustPage.selectPhysicalState(0);
		newCustPage.selectPhysicalCountry(0);
		
		//Physical Address
		newCustPage.setOverrideReason("Automation Testing");
		this.verifyErrorMessage(str15);//Address
		
		newCustPage.setPhysicalAddress(cust.physicalAddr.address);
		this.verifyErrorMessage(str19);//Country
		newCustPage.selectPhysicalCountry(cust.physicalAddr.country);
		this.verifyErrorMessage(str17);//Zip/Postal
		newCustPage.setPhysicalZipCode(inCorrectDates.get(2));
		this.verifyErrorMessage(str30);//Zip/Postal
		newCustPage.selectPhysicalCountry("Canada");  //This can not be changed for error message str28, must be "Canada"
		newCustPage.setPhysicalZipCode(inCorrectDates.get(3));
		this.verifyErrorMessage(str28);//Zip/Postal
		newCustPage.selectPhysicalCountry("Albania");  //This must be country other than US or Canada for error message str29
		newCustPage.setPhysicalZipCode(inCorrectDates.get(4));
		this.verifyErrorMessage(str29);//Zip/Postal
		
		newCustPage.selectPhysicalCountry(cust.physicalAddr.country);
		newCustPage.setPhysicalZipCode("12345");
		newCustPage.setPhysicalZipCode(cust.physicalAddr.zip);
		this.verifyErrorMessage(str16);//City Town
		
		newCustPage.setPhysicalCityTown(cust.physicalAddr.city);
		this.verifyErrorMessage(str18);//State/Province (country has been specified)
		newCustPage.selectPhysicalState(cust.physicalAddr.state);
		this.verifyErrorMessage(str20);//County(country and state have been specified)

		//Mailling Address
		newCustPage.selectPhysicalCounty(cust.mailingAddr.county);
		newCustPage.unselectMailingAddressSameAsPhysicalAddress();
		this.verifyErrorMessage(str21);//Address 
		newCustPage.setMailingAddress(cust.mailingAddr.address);
		this.verifyErrorMessage(str23);//Zip/Postal
		newCustPage.setMailingZipCode(inCorrectDates.get(2));
		this.verifyErrorMessage(str24);//Zip/Postal
		newCustPage.setMailingZipCode(cust.mailingAddr.zip);
		this.verifyErrorMessage(str22);//City Town 
		newCustPage.setMailingCityTown(cust.mailingAddr.city);
		newCustPage.selectMailingState(0); //'Empty'
		this.verifyErrorMessage(str25);//State
		newCustPage.selectMailingState(cust.mailingAddr.state);
		newCustPage.selectMailingCountry(0);
		this.verifyErrorMessage(str26);//Country
		newCustPage.selectMailingCountry(cust.mailingAddr.country);
		newCustPage.selectMailingState(cust.mailingAddr.state);
		this.verifyErrorMessage(str27);//County

		//Verify after adding customer successfully
		newCustPage.selectMailingState(cust.mailingAddr.state);
		newCustPage.selectMailingCounty(cust.mailingAddr.county);
		
		//preferred contact time is not required input
//		this.verifyErrorMessage(str11);//Preferred Contact Time
//
//		//Customer Details -- No empty option for "Gender" and "Ethnicity"
//		newCustPage.selectPreferedContactTime("Business Hour - Morning");
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
		//Name/DOB
		cust.seq = DataBaseFunctions.getEmailSequence() + "";
		cust.customerClass = "INDIVIDUAL";
		cust.fName = "QA-" + cust.seq;
		cust.lName = "AUTO-" + cust.seq;
		cust.dateOfBirth = "Mar 01 2011";
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
		cust.mailingAddr.county = cust.physicalAddr.county;
		cust.mailingAddr.zip = cust.physicalAddr.zip;
		cust.mailingAddr.country = cust.physicalAddr.country;
		cust.custGender = "Male";
		cust.ethnicity = "White";
		//Incorrect 
		inCorrectDates.add("Jan 01 3000");
		inCorrectDates.add("Jan 01 1800");
		inCorrectDates.add("character");
		inCorrectDates.add("12");
		inCorrectDates.add("#");
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
	String str0 = "First Name is required. Please specify the First Name.";
	String str1 = "Last Name is required. Please specify the Last Name.";
	String str2 = "Date Of Birth is required. Please specify the Date Of Birth.";
	String str3 = "Date of Birth must be today or in the past. Please re-enter.";
	String str4 = "The Date of Birth must be less than 150 years ago.";
	String str5 = "Phone (Business, Home, or Mobile) must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please re-enter.";
	String str6 = "Fax must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please re-enter.";
	String str7 = "Email must be a valid email. Please re-enter.";
	String str8 = "Home Phone is required. Please specify the Home Phone.";
	String str9 = "Business Phone is required. Please specify the Business Phone.";
	String str10 = "Mobile Phone is required. Please specify the Mobile Phone.";
	String str11 = "Preferred Contact Time is required. Please specify the Preferred Contact Time.";
//	String str12 = "Customer Details - Ethnicity is required. Please specify the Customer Details - Ethnicity.";
	String str12 = "Customer Details - Ethnicity is required. Please re-enter.";
//	String str13 = "Customer Details - Gender is required. Please specify the Customer Details - Gender.";
	String str13 = "Customer Details - Gender is required. Please re-enter.";
	String str14 = "Override Reason is required. Please specify the Override Reason.";
	String str15 = "Address is required for Address Type \"Physical Address\". Please specify the Address.";
	String str16 = "City/Town is required for Address Type \"Physical Address\". Please specify the City/Town.";
	String str17 = "Zip/Postal Code is required for Address Type \"Physical Address\". Please specify the Zip/Postal Code.";
//	String str12 = "ZIP/Postal Code must contain at least 5 numbers and letters combined, and must only contain numbers, letters, a single dash, or a single space." +
//	" Please change your entries for Address Type \"Physical Address\".";  //This is for US
	String str30 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
			" Please change your entries for Address Type \"Physical Address\".";  //This is for US, Updated for DEFECT-49431
	String str18 = "State is required for Address Type \"Physical Address\". Please specify the State.";
	String str19 = "Country is required for Address Type Physical Address. Please specify the Country.";
	String str20 = "County is required for Address Type \"Physical Address\". Please specify the County.";
	String str21 = "Address is required for Address Type \"Mailing Address\". Please specify the Address.";
	String str22 = "City/Town is required for Address Type \"Mailing Address\". Please specify the City/Town.";
	String str23 = "Zip/Postal Code is required for Address Type \"Mailing Address\". Please specify the Zip/Postal Code.";
	String str24 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
			" Please change your entries for Address Type \"Mailing Address\".";  //This is for US, Updated for DEFECT-49431
	String str25 = "State is required for Address Type \"Mailing Address\". Please specify the State.";
	String str26 = "Country is required for Address Type Mailing Address. Please specify the Country.";
	String str27 = "County is required for Address Type \"Mailing Address\". Please specify the County.";
	String str28 = "Postal Code must contain exactly 6 numbers and letters combined, and contain only the following characters: " +
			"number, letter, single dash, single space in one of the following formats: A#A #A# or A#A#A#, or A#A-#A# where A is any alphabetic character and # is a numeric digit from 0 to 9." +
			"Please change your entries for Address Type Physical Address.";  //New added by Phoebe of auto code review, this just for Canada
	String str29 = "ZIP/Postal Code must contain at least 1 number or letter, and must only contain numbers, letters, " +
			"a single dash, or a single space. Please change your entries for Address Type \"Physical Address\".";//New added by Phoebe of auto code review, this just for Country other than US or Canada,
}
