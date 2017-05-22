package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.education.add;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerAdressesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: 1.Verify warning message when no education existed
 *               2.Verify warning message when invalidate fields in add education page
 *               3.Verify customer education list when successfully adding education
 *               4.Verify warning message when the same active education has assign to this customer
 *               5.Verify customer education verify-status form DB
 * @Preconditions: Have business customer with fName: QA-Customer2, mName:QaTest-CusotmerProfile-2,lName:TEST-Profile2 and date of birth is 'Have business customer with fName: QA-Customer4, mName:QaTest-CusotmerProfile-4,lName:TEST-Profile4 and date of birth is 'Jan 14 1977''
 * 		If there are no education type for business customer, check ALL_D_EDUCATION_TYPE and C_CUST_CLASS_EDUCATION_TYPE to configure education type for business customer, check http://wiki.reserveamerica.com/display/qa/Customer+DB+set+up for more detail.---Shane
 * @SPEC: Add Customer Education. UCS
 * @Task#: AUTO_568
 * 
 * @author SWang
 * @Date  May 20, 2011
 */
public class AddCustomerEducationForNoneIndividual extends LicenseManagerTestCase{
	private LicMgrCustomerEducationPage custEducationPage = LicMgrCustomerEducationPage.getInstance();
	private LicMgrAddEducationPage addEducationPage = LicMgrAddEducationPage.getInstance();
	private Education edu_1 = new Education();
	private Education edu_2 = new Education();
	private TimeZone zone;
	private boolean pass = true;

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);

		lm.gotoEducationSubTabFromCustomerDetailsPg();
		//Verify all entries are valid
		lm.switchEducationAndAddEducationPg(custEducationPage);
		//Education#
		this.setupEduInfo("", edu_1.state, edu_1.country);
		this.verifyWarnMess(str1); 
		//State/Province
		this.setupEduInfo(edu_1.educationNum, "", edu_1.country);
		this.verifyWarnMess(str2); 
		//3.05build, if no country select then no state options
//		//Country
//		this.setupEduInfo(edu_1.educationNum, edu_1.state, "");
//		this.verifyWarnMess(str3); 
		lm.switchEducationAndAddEducationPg(addEducationPage);

		//Verify two education add successfully
		lm.manageEducationsForCustomer("Add", null, edu_1);
		edu_1.id = custEducationPage.getEducationID(edu_1.educationType, edu_1.educationNum);
		edu_1.createDate = DateFunctions.getToday("MMM dd,yyyy hh:mm a z", zone);
		custEducationPage.verifyEducatioList(edu_1);

		//Verify warning message when the same active education has assign to this customer
		lm.switchEducationAndAddEducationPg(custEducationPage);
		this.setupEduInfo(edu_2.educationNum, edu_2.state, edu_2.country);
		this.verifyWarnMess(str4);

		//Verify verify-status in DB
		lm.verifyEducationVerifyStatusFromDB(custEducationPage.getEducationID(edu_1.educationType, edu_1.educationNum),"Inactive", "Failed", schema);
		lm.verifyEducationVerifyStatusFromDB(custEducationPage.getEducationID(edu_2.educationType, edu_2.educationNum),"Verified", "Verified", schema);

		//Remove Customer education
		lm.removeAllCustEdus();
		
		//Goto Education page and verify warning message when no education
		this.gotoCustomerDetailsPage();
		this.gotoCustomerEducationTab();
		this.verifyWarnMessWhenNoEducationExist(str0);
				
		//Throw exception if it exists
		if(!pass){
			throw new TestCaseFailedException("Case is running failed.");
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
		zone=DataBaseFunctions.getContractTimeZone("");

		cust.customerClass = "BUSINESS";//COMMERCIAL
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.businessName = "@QaTest-CusotmerProfile-2";
		cust.fName = "QA-Customer2";
		cust.mName = "QaTest-CusotmerProfile-2";
		cust.lName = "TEST-Profile2";
		cust.dateOfBirth = "Jan 14 1977";

		//Educations Info
		edu_1.status = OrmsConstants.ACTIVE_STATUS;
		edu_1.educationType = "HuntEducation";
		edu_1.educationNum = "111111135";
		edu_1.state = "Mississippi";
		edu_1.country = "United States";
		edu_1.createApp = "LicenseManager";
		edu_1.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		
		edu_2.educationType = "HuntEducation";
		edu_2.educationNum = "333608727";//http://wiki.reserveamerica.com/display/qa/Mock+Verifier
		edu_2.state = "Mississippi";
		edu_2.country = "United States";
	}

	private void gotoCustomerDetailsPage() {
		custEducationPage.clickAddressTab();
		ajax.waitLoading();
		LicMgrCustomerAdressesPage.getInstance().waitLoading();
	}
	
	private void gotoCustomerEducationTab() {
		custEducationPage.clickEducationTab();
		ajax.waitLoading();
		custEducationPage.waitLoading();
	}
	
	/**
	 * Verify warning message when no education
	 * @param warnMess
	 */
	private void verifyWarnMessWhenNoEducationExist(String warnMess){
		if(!custEducationPage.getWarnMsg().equals(warnMess)){
			pass &=false;
			logger.error("Warning message is incorrect when no education.");
		}
	}

	/**
	 * Setup Education Information
	 * @param educationNum
	 * @param state
	 * @param country
	 */
	private void setupEduInfo(String educationNum, String state, String country){
		Education edu = new Education();
		edu.educationNum = educationNum;
		edu.state = state;
		edu.country = country;
		addEducationPage.setEducation(edu);
		if(country.trim().length()<1){
			addEducationPage.selectCountry(0);
		}
	}

	/**
	 * Verify warning message when add customer education
	 * @param expectMsg
	 */
	private void verifyWarnMess(String expectMsg){
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		String actualMsg = "";
		addEducationPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialogWidget, addEducationPage);
		if(page == confirmDialogWidget){
			actualMsg = confirmDialogWidget.getMessage();
		}else if(page == addEducationPage  && page != confirmDialogWidget){
			actualMsg = addEducationPage.getWarnMes();
		}
		//Verify warning message
		if(!actualMsg.matches(expectMsg)){
			pass &= false;
			logger.error("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
		//Return add education page
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickOKAndWait();
			custEducationPage.exists();
		}
	}

	private String str0 = "No Educations were found for the selected Customer";
	private String str1 = "Education Number is required. ?Please specify the Education Number.";
	private String str2 = "Education State/Province is required. ?Please specify the Education State/Province.";
	private String str3 = "Education Country is required. ?Please specify the Education Country.";
	private String str4 = "An \"Active\" or \"Manually Verified\" or \"Verified\" Education record with Type \"HuntEducation\" already exists for this Customer and will be set to \"Inactive\" when the Education record is added. Proceed\\?";
}
