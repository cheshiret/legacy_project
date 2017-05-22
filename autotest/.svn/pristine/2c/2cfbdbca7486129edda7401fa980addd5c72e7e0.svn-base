package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * 1. Verify account information and contact information in "Your Account Found" page;
 * 2. Verify going to correct page after click email, "Forgot Password", "Go to Sign in" and "This is not my record" link in "Your Account Found" page
 * 
 * @Preconditions:
 * d_web_hf_signupaccount, id=220, hfsk_00003@webhftest.com, 2000-01-04
 * 
 * @SPEC: Confirm Identity (Found profile already has a web account) [TC:044818] 
 * @Task#: Auto-1629
 * 
 * @author SWang
 * @Date  May 2, 2013
 */
public class ConfirmIdenWithWebAccount extends HFSKWebTestCase {
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private String userContent, custNumContent, custNumLable, custNameContent, custIdenContent, homeCityContent, homePhoneContent, pageTitle;

	public void execute() {
		//Delete used identifier
		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.email);

		//Get used customer information from update account page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		getCustInfoFromUpdateAccountPg();
		initializeAccountAndContactParameters();

		//Add a identifier
		hf.addIdentifier(cus.identifier);
		hf.signOut();

		//Lookup a account to your account found page to check account and contact information 
		hf.lookupAccount(cus);
		verifyAccountAndContactInfo();

		//Go to sign in page after click email link
		hf.gotoSignInAfterLookUpViaEmail();

		//Go to send password page after click "Forgot Password" link
		hf.lookupAccount(cus);
		hf.gotoSendPasswordPgAfterLookup();

		//Go to sign in page after click "Go to Sign in" button
		hf.lookupAccount(cus);
		hf.gotoSignInAfterLookUp();

		//Go to account lookup page after click "This is not my record"
		hf.lookupAccount(cus);
		hf.gotoAccountLookupPgAfterLookUp();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);
		cus.email = "hfsk_00003@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.custNum = hf.getCustomerNumByEmail(cus.email, schema);

		custNumLable = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false).replaceAll("\\s+", "") + "#";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_OTHER_NUM_ID, true, true);
		cus.identifier.identifierNum = "1R9Y4x4123";
		cus.identifier.country = "Canada";
		cus.identifier.state = "Alberta";
		cus.identifier.stateCode = "AB";

		pageTitle = "Your Account Found An account was found with the identification provided\\. Please sign in with the User ID\\.".replaceAll("\\s+", "");
	}

	/**
	 * Get used customer information in update account page
	 */
	private void getCustInfoFromUpdateAccountPg(){
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		cus.fName = updateAccountPg.getFirstName(); 
		cus.lName = updateAccountPg.getLastName();
		cus.hPhone = updateAccountPg.getHomePhone();
		cus.city = updateAccountPg.getHomeCity();
		cus.state = updateAccountPg.getHomeState();
		cus.country = updateAccountPg.getHomeCountry();
		cus.dateOfBirth = updateAccountPg.getDateOfBirth();
	}

	/**
	 * Initialize account and contact information parameters
	 */
	private void initializeAccountAndContactParameters(){
		userContent = "UserName(ForSignIn)"  + cus.email + "ForgotPassword";
		custNumContent = custNumLable + cus.custNum;
		custNameContent = "Name" + cus.fName + cus.lName;
		custIdenContent = (cus.identifier.identifierType + cus.identifier.identifierNum.toUpperCase() +","+ cus.identifier.stateCode +","+ cus.identifier.country).replaceAll("\\s+", "");
		homeCityContent = "HomeCity"+cus.city +","+cus.state +","+ cus.country;
		homePhoneContent = "HomePhone#"+"XXX-XXX-"+cus.hPhone.substring(6, 10);
	}

	/**
	 * Verify account and contact information in "Your Account Found" page
	 */
	private void verifyAccountAndContactInfo(){
		List<String> errorLogs = new ArrayList<String>();

		if(!yourAccountFoundPg.getPageTitle().matches(pageTitle)){
			errorLogs.add("Page title is wrong! Expected value:"+pageTitle+"\n");
		}
		if(!yourAccountFoundPg.checkUserSectionContent(userContent)){
			errorLogs.add("User content is wrong! Expected value:"+userContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustNumSectionContent(custNumContent)){
			errorLogs.add("Customer number content is wrong! Expected value:"+custNumContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustNameSectionContent(custNameContent)){
			errorLogs.add("Customer name content is wrong! Expected value:"+custNameContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustIdenSectionContent(cus.identifier.identifierType, custIdenContent)){
			errorLogs.add("Customer identifier content is wrong! Expected value:"+custIdenContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustHomeCityContent(homeCityContent)){
			errorLogs.add("Home city content is wrong! Expected value:"+homeCityContent+"\n");
		}
		if(!yourAccountFoundPg.checkCustHomePhoneSectionContent(homePhoneContent)){
			errorLogs.add("Home phone content is wrong! Expected value:"+homePhoneContent+"\n");
		}
		if(errorLogs.size()>0){
			throw new ErrorOnPageException("Not all account information and contact information related check points are passed. Please check details logs as below.\n"+errorLogs.toString());
		}
		logger.info("Successfullly verify account information and contact information related check points.");
	}
}
