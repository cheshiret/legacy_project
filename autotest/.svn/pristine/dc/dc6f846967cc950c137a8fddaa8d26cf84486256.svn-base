/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It is a new page for RA site, changed from 3.04.01. 
 * After click the link Sign In or Sign Up on header bar, the page will be shown
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  May 24, 2013
 */
public class UwpSignInSignUpPage extends UwpPage {

	private static UwpSignInSignUpPage _instance = null;

	public static UwpSignInSignUpPage getInstance() {
		if (null == _instance)
			_instance = new UwpSignInSignUpPage();

		return _instance;
	}

	protected UwpSignInSignUpPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.concatPropertyArray(form(), ".id", "existing_cust"));
	}

	/** Page Object Property Definition Begin*/
	protected Property[] getSignInWrapperDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "signin_wrapper");
	}
	
	protected Property[] getPrivacyStatementProp() {
		return Property.toPropertyArray(".class", "Html.p", ".className", "grey_txt");
	}
	
	protected Property[] getPrivacyImgProp() {
		return Property.toPropertyArray(".class", "Html.img", ".src", "/images/icon_lock.png");
	}
	
	protected Property[] getPrivacyLearnMoreLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression(".*privacy-policy", false), ".text", "Learn More");
	}
	
	protected Property[] getExistingCustDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "leftcol_wrapper");
	}
	
	protected Property[] getExistingCustEmailDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "emailGroup");
	}
	
	protected Property[] getCustEmailDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "emailAddressGroup");
	}
	
	protected Property[] getExistingCustEmailTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.Input.text", ".id", new RegularExpression("AemailGroup_(-)?\\d+", false));
	}
	
	protected Property[] getExistingPasswordDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "passwrdGroup");
	}
	
	protected Property[] getPasswordDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "passwrdBoxGroup");
	}
	
	protected Property[] getExistingCustPwTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.Input.text", ".id", new RegularExpression("ApasswrdGroup_(-)?\\d+", false));
	}
	
	protected Property[] getExistingCustForgotPasswordLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Forgot Password");
	}
	
	protected Property[] getExistingCustSignInBtnProp() {
		return Property.toPropertyArray(".class", "Html.button", ".id", "submitForm_submitForm", ".text", "Sign In");
	}
	
	protected Property[] getGroupLabelSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "groupLabel");
	}
	
	protected Property[] getTopMsgDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "msg topofpage error");
	}
	
	protected Property[] getItemErrMsg() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "group_errors");
	}
	
	protected Property[] getMesError() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "msg error");
	}
	
	protected Property[] getNewCustDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "rightcol_wrapper");
	}
	
	protected Property[] getNewCustContinueBtnProp() {
		return Property.toPropertyArray(".id", "continue_clicked_button"); //Continue button in sign in button after click Sign in or Sign Up link, not the one in sign up page
	}
	
	protected Property[] getNewCustFormProp() {
		return Property.toPropertyArray(".id", "new_cust");
	}
	
	protected Property[] getNewCustNameDivProp() {
		return Property.toPropertyArray(".id", "nameGrp");
	}
	
	protected Property[] getNewCustPhoneDivProp() {
		return Property.toPropertyArray(".id", "phoneGrp");
	}
	
	protected Property[] getNewCustAddiPhoneDivProp() {
		return Property.toPropertyArray(".id", "additionalPhoneGroup");
	}
	
	protected Property[] getNewCustOrganizDivProp() {
		return Property.toPropertyArray(".id", "ogranizationGrp");
	}
	
	protected Property[] getNewCustAddrDivProp() {
		return Property.toPropertyArray(".id", "addressGrp");
	}
	
	protected Property[] getNewCustPwTxtDivProp() {
		return Property.toPropertyArray(".id", "passwordTxtGrp");
	}
	
	protected Property[] getNewCustPwBoxDivProp() {
		return Property.toPropertyArray(".id", "passwordBoxGrp");
	}
	
	protected Property[] getNewCustShowPwCheckBoxProp() {
		return Property.toPropertyArray(".id", "togglepassword");
	}
	
	protected Property[] getNewCustAdditionalLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".className", "additionalLinkStyle");
	}
	/** Page Object Property Definition End*/
	
	/**
	 * Get content area text
	 */
	public String getPageText() {
		return browser.getObjectText(this.getSignInWrapperDivProp());
	}
	
	public String getPrivacyStatement() {
		return browser.getObjectText(this.getPrivacyStatementProp());
	}
	
	public boolean isSecurityIconExist() {
		return browser.checkHtmlObjectExists(this.getPrivacyImgProp());
	}
	
	public void clickPrivacyLearnMoreLink() {
		browser.clickGuiObject(this.getPrivacyLearnMoreLinkProp());
	}

	public boolean isPrivacyLearnMoreLinkExist() {
		return browser.checkHtmlObjectExists(this.getPrivacyLearnMoreLinkProp());
	}
		
	public String getExistingCustSecText() {
		return browser.getObjectText(this.getExistingCustDivProp());
	}
	
	public String getMesErrorText(){
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), getMesError()));
	}
	
	public void verifyEmailExistError(){
	  	String content = getMesErrorText();
	  	if(content.matches(".*email address has already been registered with another account.*")){
	  	  	logger.info(" --- The account is already in our system.");
	  	}else {
	  	  	throw new ErrorOnPageException("Account does not exist in our system.");
	  	}
	}
	
	public String getExistingUserNmLabel() {
		return browser.getObjectText(Property.atList(this.getExistingCustDivProp(), 
				this.getExistingCustEmailDivProp(), this.getGroupLabelSpanProp()));
	}
	
	public boolean isExistingUserNmTextFieldExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getExistingCustEmailTextFieldProp()));
	}
	
	/**
	 * Fill in user name.
	 * @param user - user account
	 */
	public void setUserName(String user) {
		browser.setTextField(".id", new RegularExpression("AemailGroup_(-)?\\d+", false), user, true);
	}
	
	public String getExistingPasswordLabel() {
		return browser.getObjectText(Property.atList(this.getExistingCustDivProp(), 
				this.getExistingPasswordDivProp(), this.getGroupLabelSpanProp()));
	}
	
	public boolean isExistingPwTextFieldExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getExistingCustPwTextFieldProp()));
	}
	
	/**
	 * Fill in password.
	 * @param pwd - password
	 */
	public void setPassword(String pwd) {
		browser.setPasswordField(".id", new RegularExpression("ApasswrdGroup_(-)?\\d+", false), pwd, true);
	}
	
	public boolean isForgotPasswordLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getExistingCustForgotPasswordLinkProp()));
	}
	
	public void clickForgotPasswordLink() {
		browser.clickGuiObject(Property.atList(this.getExistingCustDivProp(), this.getExistingCustForgotPasswordLinkProp()), true, 0);
	}
	/**
	 * Click on Sign In button.
	 */
	public void clickSignIn() {
		browser.clickGuiObject(".id", "submitForm_submitForm", true);
	}

	public boolean isSignInBtnExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getExistingCustSignInBtnProp()));
	}
	
	/**
	 * Fill in sign in info.
	 * @param user - user name
	 * @param pwd - password
	 */
	public void signIn(String user, String pwd) {
		this.setUserName(user);
		this.setPassword(pwd);
		this.clickSignIn();
	}

	public void signInFailed(String user, String pwd) {
		this.signIn(user, pwd);
		this.waitLoading();
	}
	
	public String getExistingCustTopMsg() {
		return browser.getObjectText(Property.atList(this.getExistingCustDivProp(), this.getTopMsgDivProp()));
	}
	
	public boolean isExistingCustTopMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getTopMsgDivProp()));
	}
	
	public String getExistingCustEmailErrMsg() {
		return browser.getObjectText(Property.atList(this.getExistingCustDivProp(), this.getExistingCustEmailDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isExistingCustEmailMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getExistingCustEmailDivProp(), this.getItemErrMsg()));
	}
	
	public String getExistingCustPwErrMsg() {
		return browser.getObjectText(Property.atList(this.getExistingCustDivProp(), this.getExistingPasswordDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isExistingCustPwMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getExistingCustDivProp(), this.getExistingPasswordDivProp(), this.getItemErrMsg()));
	}

	/**New Customer Section Begin */
	public String getNewCustSecText() {
		return browser.getObjectText(this.getNewCustDivProp());
	}
	
	public boolean isNewCustContinueBtnExist() {
		return browser.checkHtmlObjectExists(this.getNewCustContinueBtnProp());
	}
	
	/** Click Continue button */
	public void clickContinueToSignUp() {
		browser.clickGuiObject(this.getNewCustContinueBtnProp());
	}
	
	public boolean isNewCustFormExist() {
		return browser.checkHtmlObjectExists(this.getNewCustFormProp());
	}
	
	/**
	 * Fill in emial field for new customer
	 * @param email - email account
	 */
	public void setEmailForNewCust(String email) {
//		browser.setTextField(Property.atList(Property.getPropertyArray(".id", "combinedFlowSignUpKit_emailGroup_attrs"), 
//				Property.getPropertyArray(".class", "Html.Input.text", ".id", new RegularExpression("AemailGroup_(-)?\\d+", false))), email);
		browser.setTextField(".id", new RegularExpression("AemailAddressGroup_(-)?\\d+", false), email, 0);
	}
	
	/**
	 * Fill in password
	 * @param pw - password
	 */
	public void setPasswordForNewCust(String pw) {
		browser.setPasswordField(".id", new RegularExpression("ApasswordBoxGrp_\\-\\d+", false), pw);
	}
	
	/**
	 * Fill in first name
	 * @param fName - first name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression("AfirstNameGrp_(-)?\\d+", false), fName);
	}
	/**
	 * Fill in last name
	 * @param lName - last naem
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", new RegularExpression("AlastNameGrp_(-)?\\d+", false), lName);
	}
	/**
	 * Fill in phone number
	 * @param phone - phone number
	 */
	public void setPrimaryPhone(String phone) {
		browser.setTextField(".id", new RegularExpression("AphoneGrp_(-)?\\d+", false), phone);
	}
	
	public void setWorkPhone(String phone) {
		browser.setTextField(".id", new RegularExpression("AadditionalWorkPhoneGroup_(-)?\\d+", false), phone);
	}
	
	public void setWorkExtension(String phone) {
		browser.setTextField(".id", new RegularExpression("AadditionalWorkExtensionPhoneGroup_(-)?\\d+", false), phone);
	}
	
	public void setCellPhone(String phone) {
		browser.setTextField(".id", new RegularExpression("AadditionalCellPhoneGroup_(-)?\\d+", false), phone);
	}
	
	/**
	 * Fill in name of organization
	 * @param orgNm - organization name
	 */
	public void setOrgName(String orgNm) {
		browser.setTextField(".id", new RegularExpression("AogranizationGrp_(-)?\\d+", false), orgNm);
	}
	/**
	 * Fill in address
	 * @param address - address
	 */
	public void setAddress(String address) {
		browser.setTextField(".id", new RegularExpression("AstreetAddressGrp_(-)?\\d+", false), address);
	}
	/**
	 * Fill in user's city
	 * @param city - city
	 */
	public void setCity(String city) {
		browser.setTextField(".id", new RegularExpression("AcityAddressGrp_(-)?\\d+", false), city);
	}
	/**
	 * Fill im user's province
	 * @param province - province
	 */
	public void selectProvince(String province) {
		browser.selectDropdownList(".id", new RegularExpression("AstateAddressGrp_(-)?\\d+", false), province);
	}
	/**
	 * Fill in user city's post code
	 * @param zip - post code
	 */
	public void setPostCode(String zip) {
		browser.setTextField(".id", new RegularExpression("AzipAddressGrp_(-)?\\d+", false), zip);
	}
	/**
	 * Select user's country
	 * @param country - country
	 */
	public void selectCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression("AcountryAddressGrp_(-)?\\d+", false), country);
	}
	/**
	 * Click on 'Continue to Camping Profile' button
	 */
	public void clickContinueToCampingProfileButton() {
		//James [20130703]the button text is changed from "Continue To Camping Profile" to "Continue"
		browser.clickGuiObject(".id", "submitForm_submitForm", ".text", "Continue",true);
	}

	public void clickAddtionalLink() {
		browser.clickGuiObject(this.getNewCustAdditionalLinkProp());
	}
	/**
	 * Create a new customer account.
	 * @param cust - customer details info
	 */
	public void createNewMemberAccount(Customer cust) {
		// Mandatory fields 
		this.setEmailForNewCust(cust.email);
		this.setPasswordForNewCust(cust.password);
		this.setFirstName(cust.fName);
		this.setLastName(cust.lName);
		if (StringUtil.isEmpty(cust.hPhone)) {
			this.setPrimaryPhone("___-___-____"); //Lesley[20131115]: Home page default value is changed in 3.05.00
		} else {
			this.setPrimaryPhone(StringUtil.formatPhoneNumWithDash(cust.hPhone));
		}
		this.selectCountry(cust.country);
		this.waitLoading();
		this.setAddress(cust.address);
		this.setCity(cust.city);
		this.selectProvince(cust.state);
		this.waitLoading();
		this.setPostCode(cust.zip);

		// Optional Fields
		if (StringUtil.notEmpty(cust.bPhone) || StringUtil.notEmpty(cust.workExtension) || StringUtil.notEmpty(cust.mPhone)) {
			this.clickAddtionalLink();
			this.setWorkPhone(StringUtil.formatPhoneNumWithDash(cust.bPhone));
			this.setWorkExtension(cust.workExtension);
			if (StringUtil.notEmpty(cust.mPhone)) {
				this.setCellPhone(StringUtil.formatPhoneNumWithDash(cust.mPhone));
			}
		}
		if (StringUtil.notEmpty(cust.organization)) {
			this.setOrgName(cust.organization);
		}
		this.clickContinueToCampingProfileButton();
	}
	
	public void createNewMemberAccountFailed(Customer cust) {
		this.createNewMemberAccount(cust);
		this.waitLoading();
	}
	
	public boolean isNewCustTopMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getTopMsgDivProp()));
	}
	
	public String getNewCustTopMsg() {
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), this.getTopMsgDivProp()));
	}
	
	public String getNewCustEmailErrMsg() {
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), this.getCustEmailDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustEmailMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getCustEmailDivProp(), this.getItemErrMsg()));
	}
	
	public String getNewCustPwErrMsg() {
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), this.getPasswordDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustPwMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getPasswordDivProp(), this.getItemErrMsg()));
	}

	public String getNewCustNameErrMsg() {
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), this.getNewCustNameDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustNameMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getNewCustNameDivProp(), this.getItemErrMsg()));
	}

	public String getNewCustPhoneErrMsg() {
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), this.getNewCustPhoneDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustPhoneMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getNewCustPhoneDivProp(), this.getItemErrMsg()));
	}

	public boolean isNewCustAddiPhoneMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getNewCustAddiPhoneDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustOrganMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getNewCustOrganizDivProp(), this.getItemErrMsg()));
	}

	public String getNewCustAddrErrMsg() {
		return browser.getObjectText(Property.atList(this.getNewCustDivProp(), this.getNewCustAddrDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustAddrMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getNewCustDivProp(), this.getNewCustAddrDivProp(), this.getItemErrMsg()));
	}
	
	public boolean isNewCustPasswordShown() {
		return browser.checkHtmlObjectExists(Property.addToPropertyArray(this.getNewCustPwTxtDivProp(), ".className", "showOnClick")) &&
				browser.checkHtmlObjectExists(Property.addToPropertyArray(this.getNewCustPwBoxDivProp(), ".className", "hideOnClick"));
	}
	
	public void selectShowPwCheckBox() {
		browser.selectCheckBox(this.getNewCustShowPwCheckBoxProp());
	}
	
	public void unselectShowPwCheckBox() {
		browser.unSelectCheckBox(this.getNewCustShowPwCheckBoxProp(), 0);
	}
}
