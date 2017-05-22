package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.signinsignup;

import com.activenetwork.qa.awo.pages.web.ra.RAPrivacyPolicyPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSendMyPasswordPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Sign In Section on RA new Sign in Sign Up page:
 * 1. page UI when click Sign In or Sign Up link on header bar
 * 2. email and password text fields validation
 * 3. page links: Learn More and Forgot password
 * 4. page UI when make a site order without sign in 
 * @Preconditions:
 * @SPEC: 
 * Existing customer Sign In [TC:070619]
 * Purchase workflow - Sign In [TC:070630]
 * Sign In - Validations [TC:070765]
 * My Account - Learn More link [TC:070762]
 * @Task#: Auto-1778
 * 
 * @author Lesley Wang
 * @Date  July 30, 2013
 */
public class SignInPageValidation_RA extends RATestCase {
	private UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
	private String pageTitle, pageTitleWhenPurchase, existCustSecTitle, privacyStatement, userNmLabel, pwLabel, topMsg, emailMsg, pwMsg;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		web.gotoLogInPage();
		// Verify Sign in Section UI on Sign In Sign Up page: heading, input boxes, Forgot Password link, Sign In Button
		this.verifySignInSectionUI(pageTitle);
		
		// Sign in Validation: empty user name; empty password
		signInUpPg.signInFailed(StringUtil.EMPTY, pw);
		this.verifyExistingCustErrMsgs(topMsg, emailMsg, StringUtil.EMPTY);
		
		signInUpPg.signInFailed(email, StringUtil.EMPTY);
		this.verifyExistingCustErrMsgs(topMsg, StringUtil.EMPTY, pwMsg);
		
		// Verify Sign in Section Learn More and Forgot Password links
		this.verifyExistingCustSecLinks();
		
		// Verify Sign in Section UI on Sign In Sign Up page in purchase work flow: title, header
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		this.verifySignInSectionUI(pageTitleWhenPurchase);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "FL";
		
		// Booking data info
		bd.state = "Florida";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(2);
		bd.lengthOfStay = "2";
		bd.contractCode = "FL";
		bd.parkId = "281060";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"OLENO SP";
		bd.siteID = "1869";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"033";
		
		// page info
		pageTitle = "^My Account.*";
		pageTitleWhenPurchase = "^Checkout.*";
		existCustSecTitle = "^Existing Customer.*";
		privacyStatement = "We take privacy seriously. Learn More";
		userNmLabel = "User Name (email)";
		pwLabel = "Password";
		topMsg = "We need you to correct or provide more information. Please see each marked section.";
		emailMsg = "Please provide your email.";
		pwMsg = "The Password should not be empty.";
	}

	private void verifySignInSectionUI(String pageTitle) {
		boolean result = true;
		result &= MiscFunctions.matchString("Sign In Sign Up page title", signInUpPg.getPageText(), pageTitle);
		result &= MiscFunctions.compareResult("Security icon exist", true, signInUpPg.isSecurityIconExist());
		result &= MiscFunctions.compareString("Privacy Statement", privacyStatement, signInUpPg.getPrivacyStatement());
		result &= MiscFunctions.matchString("Existing Customer section title", signInUpPg.getExistingCustSecText(), existCustSecTitle);
		result &= MiscFunctions.compareString("User Name label", userNmLabel, signInUpPg.getExistingUserNmLabel());
		result &= MiscFunctions.compareResult("User Name text field exist", true, signInUpPg.isExistingUserNmTextFieldExist());
		result &= MiscFunctions.compareString("Password label", pwLabel, signInUpPg.getExistingPasswordLabel());
		result &= MiscFunctions.compareResult("Password text field exist", true, signInUpPg.isExistingPwTextFieldExist());
		result &= MiscFunctions.compareResult("Forgot Password link exist", true, signInUpPg.isForgotPasswordLinkExist());
		result &= MiscFunctions.compareResult("Sign In button exist", true, signInUpPg.isSignInBtnExist());
		
		if(!result) {
			throw new ErrorOnPageException("Sign In Page UI is wrong!");
		}
		logger.info("---Successfully verify Sign In Page UI");
	}
	
	private void verifyExistingCustErrMsgs(String topMsg, String emailMsg, String pwMsg) {
		boolean result = true;
		if (StringUtil.isEmpty(topMsg)) {
			result &= MiscFunctions.compareResult("Top Error Message exist", false, signInUpPg.isExistingCustTopMsgExist());
		} else {
			result &= MiscFunctions.compareString("Top Error Message", topMsg, signInUpPg.getExistingCustTopMsg());
		}
		if (StringUtil.isEmpty(emailMsg)) {
			result &= MiscFunctions.compareResult("Email Error Message exist", false, signInUpPg.isExistingCustEmailMsgExist());
		} else {
			result &= MiscFunctions.compareString("Email Error Message", emailMsg, signInUpPg.getExistingCustEmailErrMsg());
		}
		if (StringUtil.isEmpty(pwMsg)) {
			result &= MiscFunctions.compareResult("Password Error Message exist", false, signInUpPg.isExistingCustPwMsgExist());
		} else {
			result &= MiscFunctions.compareString("Password Error Message", pwMsg, signInUpPg.getExistingCustPwErrMsg());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Existing Customer Section error messages are wrong!");
		}
		logger.info("---Successfully verify existing customer section error messges are correct!");
	}
	
	private void verifyExistingCustSecLinks() {
		// Learn More link. Lesley[20140226]: update due to the link url is changed.
//		signInUpPg.clickPrivacyLearnMoreLink();
//		RAPrivacyPolicyPage.getInstance().waitLoading();
//		web.gotoLogInPage();
		if (!signInUpPg.isPrivacyLearnMoreLinkExist()) {
			throw new ErrorOnPageException("Privacy Policy Learn More link should exist!");
		}
		
		// Forgot Password link
		signInUpPg.clickForgotPasswordLink();
		UwpSendMyPasswordPage.getInstance().waitLoading();
		web.gotoLogInPage();
		
		logger.info("---Successfully verify Learn More and Forgot Password links!");
	}

}
