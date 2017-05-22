package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.signinsignup;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Sign Up Section on RA new Sign in Sign Up page.
 * @Preconditions:
 * @SPEC: 
 * Sign Up form validation [TC:070760]
 * @Task#: Auto-1778
 * 
 * @author Lesley Wang
 * @Date  July 30, 2013
 */
public class SignUpPageValidation_RA extends RATestCase {
	private UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
	private String newCustSecTitle, topMsg, fNameMsg, lNameMsg, emailMsg, pwMsg, 
		phoneMsg, addrMsg, cityMsg, zipMsg, stateMsg;
	private Customer cust, cust2;
	
	public void execute() {
		web.invokeURL(url);
		web.gotoLogInPage();
		this.verifyNewCustomerSecUI(false);
		
		web.gotoSignUppage();
		this.verifyNewCustomerSecUI(true);
		
		// Name field validation
		cust.fName = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, fNameMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
		cust.fName = cust2.fName;
		
		cust.lName = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, lNameMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
		cust.lName = cust2.lName;
		
		// Email address and password fields validation
		cust.email = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, emailMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
		cust.email = email;
		
		cust.password = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, pwMsg, StringUtil.EMPTY, StringUtil.EMPTY);
		cust.password = pw;
		
		// primary phone
		cust.hPhone = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, phoneMsg, StringUtil.EMPTY);
		cust.hPhone = cust2.hPhone; 
		
		// address
		cust.address = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, addrMsg);
		cust.address = cust2.address;
		
		cust.city = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, cityMsg);
		cust.city = cust2.city;
		
		cust.zip = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, zipMsg);
		cust.zip = "39179";
		
		cust.state = "Select State/Province";
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, stateMsg);
		
		cust.address = StringUtil.EMPTY;
		cust.city = StringUtil.EMPTY;
		cust.zip = StringUtil.EMPTY;
		signInUpPg.createNewMemberAccountFailed(cust);
		this.verifyNewCustErrMsgs(topMsg, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				addrMsg+" "+cityMsg+" "+zipMsg+" "+stateMsg);
		
		// shown password check box validation
		this.verifyShowPwCheckBox();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);
		cust2 = new Customer();
		cust2.setAlterValuesForWeb(email, pw);
		// page info
		newCustSecTitle = "^New Customer.*";	
		topMsg = "We need you to correct or provide more information. Please see each marked section.";
		fNameMsg = "First Name is required."; 
		lNameMsg = "Last Name is required.";
		emailMsg = "Email Address is required.";
		pwMsg = "Password is required.";
		phoneMsg = "Primary Phone # is an invalid phone number.";
		addrMsg = "Address is required.";
		cityMsg = "City is required.";
		zipMsg = "Zip/Postal Code is required.";
		stateMsg = "State/Province is required.";
	}

	private void verifyNewCustomerSecUI(boolean clickContinueBtn) {
		boolean result = true;
		result &= MiscFunctions.matchString("Sign Up Section title", signInUpPg.getNewCustSecText(), newCustSecTitle);	
		result &= MiscFunctions.compareResult("Continue button exist", !clickContinueBtn, signInUpPg.isNewCustContinueBtnExist());
		result &= MiscFunctions.compareResult("New Customer Form exist", clickContinueBtn, signInUpPg.isNewCustFormExist());
		
		if (!result) {
			throw new ErrorOnPageException("New Customer Section UI is wrong!");
		}
		logger.info("---Successfully verify new customer section UI");
	}
	
	private void verifyNewCustErrMsgs(String topMsg, String nameMsg, String emailMsg, String pwMsg, String phoneMsg, String addrMsg) {
		boolean result = true;
		if (StringUtil.isEmpty(topMsg)) {
			result &= MiscFunctions.compareResult("Top Error Message exist", false, signInUpPg.isNewCustTopMsgExist());
		} else {
			result &= MiscFunctions.compareString("Top Error Message", topMsg, signInUpPg.getNewCustTopMsg());
		}
		
		if (StringUtil.isEmpty(nameMsg)) {
			result &= MiscFunctions.compareResult("Name Error Message exist", false, signInUpPg.isNewCustNameMsgExist());
		} else {
			result &= MiscFunctions.compareString("Name Error Message", nameMsg, signInUpPg.getNewCustNameErrMsg());
		}
		
		if (StringUtil.isEmpty(emailMsg)) {
			result &= MiscFunctions.compareResult("Email Error Message exist", false, signInUpPg.isNewCustEmailMsgExist());
		} else {
			result &= MiscFunctions.compareString("Email Error Message", emailMsg, signInUpPg.getNewCustEmailErrMsg());
		}
		
		if (StringUtil.isEmpty(pwMsg)) {
			result &= MiscFunctions.compareResult("Password Error Message exist", false, signInUpPg.isNewCustPwMsgExist());
		} else {
			result &= MiscFunctions.compareString("Password Error Message", pwMsg, signInUpPg.getNewCustPwErrMsg());
		}
		
		if (StringUtil.isEmpty(phoneMsg)) {
			result &= MiscFunctions.compareResult("Phone Error Message exist", false, signInUpPg.isNewCustPhoneMsgExist());
		} else {
			result &= MiscFunctions.compareString("Phone Error Message", phoneMsg, signInUpPg.getNewCustPhoneErrMsg());
		}
		
		result &= MiscFunctions.compareResult("Additional Phone Error Message exist", false, signInUpPg.isNewCustAddiPhoneMsgExist());
		result &= MiscFunctions.compareResult("Organization Error Message exist", false, signInUpPg.isNewCustOrganMsgExist());
		
		if (StringUtil.isEmpty(addrMsg)) {
			result &= MiscFunctions.compareResult("Address Error Message exist", false, signInUpPg.isNewCustAddrMsgExist());
		} else {
			result &= MiscFunctions.compareString("Address Error Message", addrMsg, signInUpPg.getNewCustAddrErrMsg());
		}
		
		if (!result) {
			throw new ErrorOnPageException("New Customer Section error messages are wrong!");
		}
		logger.info("---Successfully verify new customer section error messges are correct!");
	}
	
	private void verifyShowPwCheckBox() {
		boolean result = true;
		signInUpPg.unselectShowPwCheckBox();
		result &= MiscFunctions.compareResult("password shown", false, signInUpPg.isNewCustPasswordShown());
		
		signInUpPg.selectShowPwCheckBox();
		result &= MiscFunctions.compareResult("password shown", true, signInUpPg.isNewCustPasswordShown());
		
		if (!result) {
			throw new ErrorOnPageException("New Customer Section Show Password checkbox is wrong!");
		}
		logger.info("Successfully verify new customer section show password check box!");
	}
}
