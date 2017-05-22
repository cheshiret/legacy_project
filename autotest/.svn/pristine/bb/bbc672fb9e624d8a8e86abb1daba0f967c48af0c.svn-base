package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateWebSignInPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * 1. Create a profile with home phone in SK license manager;
 * 2. Lookup this profile in HFSK web site to "Your Account Found" page to verify Page title, Account information and contact information;
 * 3. Verify going to correct page after click "Create Web Account" link in "Your Account Found" page
 * 4. Verify page title email address, password and retype password content, no home phone section
 * 
 * @Preconditions:
 * @SPEC: 
 * Confirm Identity (Found profile has no web account) [TC:044757] 
 * Create web account (Found profile has no web account; Home phone entered) [TC:044862] 
 * 
 * @Task#: Auto-1629
 * 
 * @author SWang
 * @Date  May 2, 2013
 */
public class ConfirmIdenNoWebAccount extends HFSKWebTestCase {
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String custNumContent, custNumLable, custNameContent, homeCityContent, homePhoneContent, yourAccountFoundPgTitle, createWebSignInPgTitle, emailLabel, pwLabel, repwLabel;

	public void execute() {
		//Create an account in LM
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		cus.custNum = lm.createNewCustomerWithoutSearch(cus);
		lm.logOutLicenseManager();		

		//Initialize some parameters
		cus.identifier.identifierType = custNumLable;
		cus.identifier.identifierNum = cus.custNum;
		custNumContent = (custNumLable +"#"+ cus.custNum).replaceAll("\\s+", "");

		//Lookup a account to your account found page in HFSK
		hf.invokeURL(url);
		hf.lookupAccount(cus);

		//Check account and contact information 
		verifyAccountAndContactInfo();

		//Go to create web sign in page to check
		hf.gotoCreateWebSignInPgFromAcctFoundPg();
		verifyCreateWebSignInInfo();
	}

	public void wrapParameters(Object[] param) {
		//Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";

		//Customer info
		cus.email = "HFweb" + DateFunctions.getCurrentTime() + "@test.com";
		logger.info("cus.email:"+cus.email);
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultCanadaAddress(); 
		cus.hPhone = "8694528962";
		cus.fName = "hf_FName";
		cus.lName = "hf_LName";
		cus.suffix = "I";
		cus.dateOfBirth = "2000-01-05";
		cus.physicalAddr.city = "Mississauga";
		cus.physicalAddr.state = "Ontario";
		cus.physicalAddr.country = "Canada";

		//Your account found page testing parameters
		yourAccountFoundPgTitle = "(Your )?Account Found ?(A record is|An account has been) found with the identification provided.*".replaceAll("\\s+", "");
		custNumLable = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		custNameContent = "Name" + cus.fName + cus.lName + cus.suffix;
		homeCityContent = "HomeCity"+cus.physicalAddr.city +","+cus.physicalAddr.state +","+ cus.physicalAddr.country;
		homePhoneContent = "HomePhone#"+"XXX-XXX-8962";

		//Create Web Sign In page testing parameters
		createWebSignInPgTitle = "Create Web Sign In Create a web sign in for your Hunt and Fish Account.*".replaceAll("\\s+", "");
		emailLabel = "Email Address*";
		pwLabel = "Password*";
		repwLabel = "Retype Password*";
	}

	/**
	 * In "Your Account Found Page", verify
	 * 1. Page Title
	 * 2. No user and identifier related section
	 * 3. Account and contact information
	 */
	private void verifyAccountAndContactInfo(){
		List<String> errorLogs = new ArrayList<String>();

		if(!yourAccountFoundPg.getPageTitle().matches(yourAccountFoundPgTitle)){
			errorLogs.add("Page title in 'Your Account Found' page is wrong! Expected value:"+yourAccountFoundPgTitle+"\n");
		}
		if(yourAccountFoundPg.checkUserSectionExits()){
			errorLogs.add("User content secton in 'Your Account Found' page should be existed!\n");
		}
		if(yourAccountFoundPg.checkCustIdenSectionExits()){
			errorLogs.add("Customer identifier section in 'Your Account Found' page should not be existed!\n");
		}
		if(!yourAccountFoundPg.checkCustNumSectionContent(custNumContent)){
			errorLogs.add("Customer number content in 'Your Account Found' page is wrong! Expected value:"+custNumContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustNameSectionContent(custNameContent)){
			errorLogs.add("Customer name content in 'Your Account Found' page is wrong! Expected value:"+custNameContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustHomeCityContent(homeCityContent)){
			errorLogs.add("Home city content in 'Your Account Found' page is wrong! Expected value:"+homeCityContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustHomePhoneSectionContent(homePhoneContent)){
			errorLogs.add("Home phone content in 'Your Account Found' page is wrong! Expected value:"+homePhoneContent+"\n");
		}
		if(errorLogs.size()>0){
			throw new ErrorOnPageException("Not all check point in 'Your Account Found' page are correct. Please check details logs as below.\n"+errorLogs.toString());
		}
		logger.info("Successfullly verify all check point in 'Your Account Found' page are correct.");
	}

	/**
	 * In "Create Web Sign In" page, verify
	 * 1. Page title
	 * 2. The label and value of Email address, password and retype password
	 * 3. Verify no home phone section when created profile with home phone 
	 */
	private void verifyCreateWebSignInInfo (){
		HFCreateWebSignInPage createWebSignInPg = HFCreateWebSignInPage.getInstance();
		List<String> errorLogs = new ArrayList<String>();

		if(!createWebSignInPg.getPageTitle().matches(createWebSignInPgTitle)){
			errorLogs.add("Page title in 'Create Web Sign In' page is wrong! Expected value:"+createWebSignInPgTitle+"\n");
		}
		if(!createWebSignInPg.checkEmailAddressLabel(emailLabel)){
			errorLogs.add("Email label in 'Create Web Sign In' page is wrong! Expected value:"+emailLabel+"\n");
		}
		if(!createWebSignInPg.checkEmailAddressValue(cus.email)){
			errorLogs.add("Email value in 'Create Web Sign In' page is wrong! Expected value:"+cus.email+"\n");
		}
		if(!createWebSignInPg.checkPasswordLabel(pwLabel)){
			errorLogs.add("Password label in 'Create Web Sign In' page is wrong! Expected value:"+pwLabel+"\n");
		}
		if(!createWebSignInPg.checkPasswordValue(StringUtil.EMPTY)){
			errorLogs.add("Password value in 'Create Web Sign In' page is wrong! Expected value:"+StringUtil.EMPTY+"\n");
		}
		if(!createWebSignInPg.checkRetypePasswordLabel(repwLabel)){
			errorLogs.add("Repassword label in 'Create Web Sign In' page is wrong! Expected value:"+repwLabel+"\n");
		}
		if(!createWebSignInPg.checkRetypePasswordValue(StringUtil.EMPTY)){
			errorLogs.add("Repassword value in 'Create Web Sign In' page is wrong! Expected value:"+StringUtil.EMPTY+"\n");
		}
		if(createWebSignInPg.checkHomePhoneSectionExists()){
			errorLogs.add("Home phone section should not exist.\n");
		}
		if(errorLogs.size()>0){
			throw new ErrorOnPageException("Not all check points are correct in 'Create Web Sign In' page. Please check details logs as below.\n"+errorLogs.toString());
		}
		logger.info("Successfullly verify all check points are correct in 'Create Web Sign In' page.");
	}
}

