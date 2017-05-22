package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateWebSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (DEFECT-43548 -> P)
 * 1. Create an account without email and home phone in LM
 * 2. Lookup this profile to "Created Web Sign In" page
 * 3. Check page UI, especially email value, home phone label and value
 * 4. Check error message when home phone is blank and invalid
 * 
 * @Preconditions:
 * @SPEC: 
 * Create web account ( no web account; no home phone entered) [TC:054444]  
 * Create web account page - Home Phone Validation [TC:054485] 
 * 
 * @Task#: Auto-1629
 * 
 * @author SWang
 * @Date  May 7, 2013
 */
public class CreateWebSignInNoHPAndEmailEntered extends HFSKWebTestCase {
	private LicMgrCustomerDetailsPage cusDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String createWebSignInPgTitle, emailLabel, emailValue, pwLabel, pwValue, repwLabel, hPhoneLabel, invalidHPone1, invalidHPone2, errorMes1, errorMes2, errorMes3, errorMes4;

	public void execute() {
		//Create an account without email and home phone in LM
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		cus.custNum = lm.createNewCustomerWithoutSearch(cus);
		lm.gotoCustomerDetails(cus.lName, cus.fName);
		lm.addOrEditCustomerInfo(cus, cusDetailsPg);
		lm.logOutLicenseManager();		

		//Lookup a account to create web sign in page in HFSK
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoCreateWebSignInPgFromAcctFoundPg();

		//Check page UI and home phone related error messages 
		verifyCreateWebSignInPgUI();
		verifyErrorMesWithHomePhone();

		//Delete used identifier
		hf.deleteCustIden(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, emailValue);
	}

	public void wrapParameters(Object[] param) {
		//Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";

		//Customer info
		logger.info("cus.email:"+cus.email);
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultCanadaAddress();
		cus.fName = "FN_"+ new DecimalFormat("00000").format(new Random().nextInt(9999));
		cus.lName = "LN_"+ new DecimalFormat("00000").format(new Random().nextInt(9999));
		cus.dateOfBirth = "2000-01-05";
		cus.hPhone = StringUtil.EMPTY;
		cus.bPhone = "8694528962";
		cus.email = StringUtil.EMPTY;

		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(99999));
		logger.info("cus.identifier.identifierNum:"+cus.identifier.identifierNum);
		cus.identifier.country = "Canada";

		//Create Web Sign In page testing parameters
		createWebSignInPgTitle = "Create Web Sign In Create a web sign in for your Hunt and Fish Account.*".replaceAll("\\s+", "");
		emailLabel = "Email Address*";
		pwLabel = "Password*";
		repwLabel = "Retype Password*";
		hPhoneLabel = "Home Phone Number*";

		emailValue = "HFweb" + DateFunctions.getCurrentTime() + "@test.com";
		pwValue = TestProperty.getProperty("web.login.pw");
		invalidHPone1 = "abcderf";
		invalidHPone2 = "1-877-";

		//Home phone related error messages
		errorMes1 = "We need you to correct or provide more information. Please see each marked section.";
		errorMes2 = "Home Phone Number is required.";
		errorMes3 = "Home Phone # is an invalid phone number.";
		errorMes4 = "Home Phone # is invalid. Please enter a Home Phone # between 10 and 11 digits.";
	}

	/**
	 * In "Create Web Sign In" page, verify
	 * 1. Page title
	 * 2. Verify label for Email, password, retype password and home phone, value for Email and Home phone
	 */
	private void verifyCreateWebSignInPgUI(){
		HFCreateWebSignInPage createWebSignInPg = HFCreateWebSignInPage.getInstance();
		List<String> errorLogs = new ArrayList<String>();

		if(!createWebSignInPg.getPageTitle().matches(createWebSignInPgTitle)){
			errorLogs.add("Page title in 'Create Web Sign In' page is wrong! Expected value:"+createWebSignInPgTitle+"\n");
		}
		if(!createWebSignInPg.checkEmailAddressLabel(emailLabel)){
			errorLogs.add("Email label in 'Create Web Sign In' page is wrong! Expected value:"+emailLabel+"\n");
		}
		if(!createWebSignInPg.checkEmailAddressValue(StringUtil.EMPTY)){
			errorLogs.add("Email value in 'Create Web Sign In' page is wrong! Expected value:"+StringUtil.EMPTY+"\n");
		}
		if(!createWebSignInPg.checkPasswordLabel(pwLabel)){
			errorLogs.add("Password label in 'Create Web Sign In' page is wrong! Expected value:"+pwLabel+"\n");
		}
		if(!createWebSignInPg.checkRetypePasswordLabel(repwLabel)){
			errorLogs.add("Repassword label in 'Create Web Sign In' page is wrong! Expected value:"+repwLabel+"\n");
		}
		if(!createWebSignInPg.checkHomePhoneLabel(hPhoneLabel)){
			errorLogs.add("Home Phpne label in 'Create Web Sign In' page is wrong! Expected value:"+hPhoneLabel+"\n");
		}
		if(!createWebSignInPg.checkHomePhoneValue(StringUtil.EMPTY)){
			errorLogs.add("Home Phpne value in 'Create Web Sign In' page is wrong! Expected value:"+StringUtil.EMPTY+"\n");
		}
		if(errorLogs.size()>0){
			throw new ErrorOnPageException("Not all check points are correct in 'Create Web Sign In' page. Please check details logs as below.\n"+errorLogs.toString());
		}
		logger.info("Successfullly verify all check points are correct in 'Create Web Sign In' page.");
	}

	/**
	 * Verify error messages when home phone
	 * 1. Is blank
	 * 2. Is invalid without any number entered
	 * 3. Is invalid with number entered, but not between 20 and 11 digits
	 */
	private void verifyErrorMesWithHomePhone(){
		HFCreateWebSignInPage createWebSignInPg = HFCreateWebSignInPage.getInstance();
		List<String> errorLogs = new ArrayList<String>();

		hf.createWebAccountAfterLookUp(emailValue, pwValue, pwValue, StringUtil.EMPTY);
		if(!createWebSignInPg.isErrorMsgExist(errorMes1)){
			errorLogs.add("Top Error message should displays when home phone is blank.\n"+errorMes1);
		}
		if(!createWebSignInPg.isErrorMsgExist(errorMes2)){
			errorLogs.add("Error message should displays when home phone is blank.\n"+errorMes2);
		}

		hf.createWebAccountAfterLookUp(emailValue, pwValue, pwValue, invalidHPone1);
		if(!createWebSignInPg.isErrorMsgExist(errorMes3)){
			errorLogs.add("Error message should displays when home phone is no any number entered.\n"+errorMes3);
		}

		hf.createWebAccountAfterLookUp(emailValue, pwValue, pwValue, invalidHPone2);
		if(!createWebSignInPg.isErrorMsgExist(errorMes4)){
			errorLogs.add("Error message should displays when home phone is with number entered.\n"+errorMes4);
		}

		if(errorLogs.size()>0){
			throw new ErrorOnPageException("Not all error messages are correct in 'Create Web Sign In' page. Please check details logs as below.\n"+errorLogs.toString());
		}
		logger.info("Successfullly verify all error messages are correct in 'Create Web Sign In' page.");

	}
}


