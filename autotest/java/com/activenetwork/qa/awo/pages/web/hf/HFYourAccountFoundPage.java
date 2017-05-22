package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This page will display after lookupped account is found
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 1, 2013
 */
public class HFYourAccountFoundPage extends HFHeaderBar {
	private static HFYourAccountFoundPage _instance = null;

	public static HFYourAccountFoundPage getInstance() {
		if (null == _instance)
			_instance = new HFYourAccountFoundPage();

		return _instance;
	}

	private static String ACCOUNT_INFO_USER = "user";
	private static String ACCOUNT_INFO_CUSTNUMBER = "custNumber";
	private static String ACCOUNT_INFO_CUSTNAME = "custName";
	private static String ACCOUNT_INFO_CUSTIDENTIFIER = "custIdentifier";

	private static String ACCOUNT_INFO_CUSTNUMBER_VALUE = "LookupProfileKit_custNumber_attrs";
	private static String ACCOUNT_INFO_CUSTIDENTIFIER_VALUE = "LookupProfileKit_custIdentifier_attrs";
//	private static String ACCOUNT_INFO_CUSTNAME_VALUE = "LookupProfileKit_custName_attrs";

	private static String CONTACT_INFO_CUSTHOMECITY = "custHomeCity";
	private static String CONTACT_INFO_CUSTHOMEPHONE = "custHomePhone";
	
	protected HFYourAccountFoundPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "lookupCustomerProfileForm");
	}

	/**Page Object Property Definition Begin*/
	protected Property[] getUserEmailLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression(".*userEmail.*", false));
	}
	
	protected Property[] getForgotPwLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression(".*userID.*", false));
	}
	
	protected Property[] getCreateWebAcctBtnProp() {
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Create Web Account");
	}

	protected Property[] getGoToSignInBtnProp() {
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Go to Sign in");
	}
	
	protected Property[] getConfirmAndProceedBtnProp() {
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Confirm and Proceed");
	}
	
	protected Property[] getCancelLinkDivProp() {
//		return Property.toPropertyArray(".class", "Html.DIV", ".className", "submitbtnrighttxt"); Lesley[20140227] object classname changed
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "cancelLink");
	}
	
	protected Property[] getCancelLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("/identifyCustomer\\.do", false));
	}
	
	protected Property[] getAcctInfoDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_accountlabel_attrs");
	}

	protected Property[] getContactInfoDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_contactlabel_attrs");
	}
	
	protected Property[] getAttrFieldDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", new RegularExpression("attributeField( firstLayoutCol)? VIEW", false));
	}
	
	protected Property[] getGroupLabelSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "groupLabel");
	}
	
	protected Property[] getUpdateInfoLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Update information");
	}
	
	protected Property[] getUpdateAddrLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Update Address");
	}
	
	protected Property[] getCustNameDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custName");
	}
	
	protected Property[] getCustNameValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custName_attrs");
	}
	
	protected Property[] getCustNumDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custNumber");
	}

	protected Property[] getCustNumValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custNumber_attrs");
	}
	
	protected Property[] getCustIdentifierDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custIdentifier");
	}

	protected Property[] getCustIdentifierValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custIdentifier_attrs");
	}
	
	protected Property[] getCustHomeAddrDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custHomeAddress");
	}

	protected Property[] getCustHomeAddrValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custHomeAddress_attrs");
	}

	protected Property[] getCustHomeCityDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custHomeCity");
	}

	protected Property[] getCustHomeCityValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custHomeCity_attrs");
	}
	
	protected Property[] getCustHomePhoneDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custHomePhone");
	}

	protected Property[] getCustHomePhoneValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custHomePhone_attrs");
	}
	
	protected Property[] getCustEmailAddrDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "custEmailAddress");
	}

	protected Property[] getCustEmailAddrValueDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "LookupProfileKit_custEmailAddress_attrs");
	}
	
	protected Property[] getEmailAddrTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("AcustEmailAddress_(-)?\\d+", false));
	}
	
	protected Property[] getTopErrorMsgDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "msg topofpage error");
	}
	
	protected Property[] getErrorItemDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "error_item");
	}
	
	protected Property[] mergedCustMsgProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "mergedCustomer");
	}
	/**Page Object Property Definition End*/
	
	
	public String getPageTitle(){
		return browser.getObjectText(".className", "pagetitle").replaceAll("\\s+", "");
	}
	
	public IHtmlObject[] getAccountElementObjs(String elementLable, String parentObjsId, String childObjsId){
		Property[] p1  = null;
		Property[] p2 = null;
		IHtmlObject[] objs = null;
		
		if(StringUtil.isEmpty(elementLable)){
			 p1 = Property.toPropertyArray(".id", parentObjsId);
		}else{
			p1 = Property.toPropertyArray(".id", parentObjsId, ".text", new RegularExpression(elementLable+".*", false));
		}
		p2 = Property.toPropertyArray(".id", childObjsId);

		if(!StringUtil.isEmpty(childObjsId)){
			objs =browser.getHtmlObject(Property.atList(p1, p2));
		}else{
			objs =browser.getHtmlObject(p1);
		}

		if(objs.length<1){
			throw new ObjectNotFoundException("Object idenType:"+elementLable+", id:"+childObjsId+" and parent objs id:"+parentObjsId+" can't be found.");
		}
		return objs;
	}
	
	public String getAccountElementValue(String elementLable, String parentObjsId, String childObjsId){
		IHtmlObject[] objs =this.getAccountElementObjs(elementLable, parentObjsId, childObjsId);
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}

	public boolean checkAccountElementValue(String elementLable, String parentObjsId, String childObjsId, String expectedValue){
		String actualValue = this.getAccountElementValue(elementLable, parentObjsId, childObjsId).replaceAll("\\s+", "");
		logger.info("Actual value:"+actualValue);
		return expectedValue.replaceAll("\\s+", "").equals(actualValue);
	}

	public void verifyAccountElementValue(String elementLable, String parentObjsId, String childObjsId, String expectedValue){
		if(checkAccountElementValue(elementLable, parentObjsId, childObjsId, expectedValue)){
			logger.info("Successfully verify Account element idenType:"+elementLable+", id:"+childObjsId+" and parent objs id:"+parentObjsId);
		} else {
			throw new ErrorOnPageException("Account element idenType:"+elementLable+", id:"+childObjsId+" and parent objs id:"+parentObjsId+" is wrong! Expected value is "+expectedValue);
		}
	}

	public void verifyCustNum(String elementLable, String expectedValue){
		this.verifyAccountElementValue(elementLable, ACCOUNT_INFO_CUSTNUMBER, ACCOUNT_INFO_CUSTNUMBER_VALUE, expectedValue);
	}

	public void verifyIdenRecord(String elementLable, String expectedValue){
		this.verifyAccountElementValue(elementLable, ACCOUNT_INFO_CUSTIDENTIFIER, ACCOUNT_INFO_CUSTIDENTIFIER_VALUE, expectedValue);
	}

	public boolean checkUserSectionContent(String expectedValue){
		return checkAccountElementValue(StringUtil.EMPTY, ACCOUNT_INFO_USER, StringUtil.EMPTY, expectedValue);
	}

	public boolean checkCustNumSectionContent(String expectedValue){
		return checkAccountElementValue(StringUtil.EMPTY, ACCOUNT_INFO_CUSTNUMBER, StringUtil.EMPTY, expectedValue);
	}

	public boolean checkCustNameSectionContent(String expectedValue){
		return checkAccountElementValue(StringUtil.EMPTY, ACCOUNT_INFO_CUSTNAME, StringUtil.EMPTY, expectedValue);
	}

	public boolean checkCustIdenSectionContent(String elementLable, String expectedValue){
		return checkAccountElementValue(elementLable, ACCOUNT_INFO_CUSTIDENTIFIER, StringUtil.EMPTY, expectedValue);
	}

	public boolean checkCustHomeCityContent(String expectedValue){
		return checkAccountElementValue(StringUtil.EMPTY, CONTACT_INFO_CUSTHOMECITY, StringUtil.EMPTY, expectedValue);
	}
	
	public boolean checkCustHomePhoneSectionContent(String expectedValue){
		return checkAccountElementValue(StringUtil.EMPTY, CONTACT_INFO_CUSTHOMEPHONE, StringUtil.EMPTY, expectedValue);
	}
	
	public boolean checkAccountElementExists(String elementLable, String parentObjsId, String childObjsId){
		Property[] p1  = null;
		Property[] p2 = null;
		
		if(StringUtil.isEmpty(elementLable)){
			 p1 = Property.toPropertyArray(".id", parentObjsId);
		}else{
			p1 = Property.toPropertyArray(".id", parentObjsId, ".text", new RegularExpression(elementLable+".*", false));
		}
		p2 = Property.toPropertyArray(".id", childObjsId);

		if(!StringUtil.isEmpty(childObjsId)){
			return browser.checkHtmlObjectExists(Property.atList(p1, p2));
		}else{
			return browser.checkHtmlObjectExists(p1);
		}
	}
	
	public boolean checkCustIdenSectionExits(){
		return checkAccountElementExists(StringUtil.EMPTY, ACCOUNT_INFO_CUSTIDENTIFIER, StringUtil.EMPTY);
	}
	
	public boolean checkUserSectionExits(){
		return checkAccountElementExists(StringUtil.EMPTY, ACCOUNT_INFO_USER, StringUtil.EMPTY);
	}
	
	/** Get Account Information Section Label */
	public String getAcctInfoSecLabel() {
		return browser.getObjectText(Property.atList(this.getAcctInfoDivProp(), this.getAttrFieldDivProp()));
	}
	
	/** Check Update Information Link exist */
	public boolean isUpdateInfoLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getAcctInfoDivProp(), this.getUpdateInfoLinkProp()));
	}
	
	/** Click Update Information Link */
	public void clickUpdateInfo() {
		browser.clickGuiObject(Property.atList(this.getAcctInfoDivProp(), this.getUpdateInfoLinkProp()), true, 0);
	}
	
	/** Get Customer Name label */
	public String getCustNameLabel() {
		return browser.getObjectText(Property.atList(this.getCustNameDivProp(), this.getGroupLabelSpanProp()));
	}
	
	/** Get Customer Name */
	public String getCustName() {
		return browser.getObjectText(Property.atList(this.getCustNameDivProp(), this.getCustNameValueDivProp()));
	}

	/** Get Customer Number label */
	public String getCustNumLabel() {
		return browser.getObjectText(Property.atList(this.getCustNumDivProp(), this.getGroupLabelSpanProp()));
	}
	
	/** Get Customer Name */
	public String getCustNumber() {
		return browser.getObjectText(Property.atList(this.getCustNumDivProp(), this.getCustNumValueDivProp()));
	}
	
	/** Get Contract Information Section label */
	public String getContactInfoSecLabel() {
		return browser.getObjectText(Property.atList(this.getContactInfoDivProp(), this.getAttrFieldDivProp()));
	}
	
	/** Check Update Address Link Exist */
	public boolean isUpdateAddrLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getContactInfoDivProp(), this.getUpdateAddrLinkProp()));
	}
	
	/** Click Update Address Link */
	public void clickUpdateAddress() {
		browser.clickGuiObject(Property.atList(this.getContactInfoDivProp(), this.getUpdateAddrLinkProp()), true, 0);
	}
	
	/** Get Customer Home Address label */
	public String getCustHomeAddrLabel() {
		return browser.getObjectText(Property.atList(this.getCustHomeAddrDivProp(), this.getGroupLabelSpanProp()));
	}
	
	/** Get Customer Home Address */
	public String getCustHomeAddr() {
		return browser.getObjectText(Property.atList(this.getCustHomeAddrDivProp(), this.getCustHomeAddrValueDivProp()));
	}
	
	/** Get Customer Home City label */
	public String getCustHomeCityLabel() {
		return browser.getObjectText(Property.atList(this.getCustHomeCityDivProp(), this.getGroupLabelSpanProp()));
	}
	
	/** Get Customer Home City */
	public String getCustHomeCity() {
		return browser.getObjectText(Property.atList(this.getCustHomeCityDivProp(), this.getCustHomeCityValueDivProp()));
	}

	/** Get Customer Home Phone label */
	public String getCustHomePhoneLabel() {
		return browser.getObjectText(Property.atList(this.getCustHomePhoneDivProp(), this.getGroupLabelSpanProp()));
	}
	
	/** Get Customer Home Phone */
	public String getCustHomePhone() {
		return browser.getObjectText(Property.atList(this.getCustHomePhoneDivProp(), this.getCustHomePhoneValueDivProp()));
	}

	/** Get Customer Email address label */
	public String getCustEmailAddrLabel() {
		return browser.getObjectText(Property.atList(this.getCustEmailAddrDivProp(), this.getGroupLabelSpanProp()));
	}
	
	/** Get Customer Email Address */
	public String getCustEmailAddr() {
		return browser.getObjectText(Property.atList(this.getCustEmailAddrDivProp(), this.getCustEmailAddrValueDivProp()));
	}
	
	/** Check Email Address Text Field exist. Only show in Identifier Mode when lookup existing account without email address */
	public boolean isEmailAddrTextFieldExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getCustEmailAddrValueDivProp(), this.getEmailAddrTextFieldProp()));
	}
	
	/** Get the value of Email Address text field */
	public String getCustEmailAddrFieldValue() {
		return browser.getTextFieldValue(this.getEmailAddrTextFieldProp());
	}
	
	public void setCustEmailAddrFieldValue(String email) {
		browser.setTextField(Property.atList(this.getCustEmailAddrValueDivProp(), this.getEmailAddrTextFieldProp()), email);
	}
	
	/** Click this is not my record link */
	public void clickThisIsNotMyRecordLink(){
		browser.clickGuiObject(Property.atList(this.getCancelLinkDivProp(), this.getCancelLinkProp()), true, 0);
	}

	/** Check this is not my record link exist. */
	public boolean isNotMyRecordLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getCancelLinkDivProp(), this.getCancelLinkProp()));
	}
	
	/** Click Create Web Account button. Only show in Sign in Mode */
	public void clickCreateWebAccount() {
		browser.clickGuiObject(this.getCreateWebAcctBtnProp());
	}
	
	/** Check Create Web Account button exist. Only show in Sign in Mode */
	public boolean isCreateWebAccountExist() {
		return browser.checkHtmlObjectExists(this.getCreateWebAcctBtnProp());
	}
	
	/** Click Go to Sign in button. Only show in Sign in Mode */
	public void clickGoToSignIn() {
		browser.clickGuiObject(this.getGoToSignInBtnProp());
	}
	
	/** Check Go to Sign in button exist. Only show in Sign in Mode */
	public boolean isGoToSignInExist() {
		return browser.checkHtmlObjectExists(this.getGoToSignInBtnProp());
	}
	
	/** Click Confirm And Proceed button */
	public void clickConfirmAndProceed(){
		browser.clickGuiObject(this.getConfirmAndProceedBtnProp());
	}
	
	/** Check Confirm And Proceed button exist*/
	public boolean isConfirmAndProceedExist() {
		return browser.checkHtmlObjectExists(this.getConfirmAndProceedBtnProp());
	}
	
	/** User Email Link. Only show in Sign In Mode */
	public void clickEmailLink(){
		browser.clickGuiObject(this.getUserEmailLinkProp());
	}

	/** Check User Email Link exist. Only show in Sign In Mode */
	public boolean isEmailLinkExist() {
		return browser.checkHtmlObjectExists(this.getUserEmailLinkProp());
	}
	
	/** Forgot password link. Only show in Sign In Mode */
	public void clickForgotPasswordLink(){
		browser.clickGuiObject(this.getForgotPwLinkProp());
	}
	
	/** Check Forgot password link exist. Only show in Sign In Mode */
	public boolean isForgotPwLinkExist() {
		return browser.checkHtmlObjectExists(this.getForgotPwLinkProp());
	}
	
	/** Verify Account Information */
	public void verifyAccountInformation(Customer cus, boolean byIdentifier) {
		boolean result = true;
		String exp = cus.fName + " " + (StringUtil.isEmpty(cus.mName) ? "" : (cus.mName + " ")) + cus.lName;
		if (StringUtil.notEmpty(cus.suffix) && !cus.suffix.matches("(No Suffix)|(Please select)"))
			exp += " " + cus.suffix;
		result &= MiscFunctions.compareString("Customer Name", exp, this.getCustName());
		result &= MiscFunctions.compareString("Customer Number", cus.custNum, this.getCustNumber());
		result &= MiscFunctions.compareResult("Identifier info exist", byIdentifier, this.checkCustIdenSectionExits());
		if (byIdentifier) {
			exp = cus.identifier.identifierType.toUpperCase() + cus.identifier.identifierNum + (StringUtil.isEmpty(cus.identifier.stateCode) ? "" : (", " + cus.identifier.stateCode)) +
					(StringUtil.isEmpty(cus.identifier.country) ? "" : (", " + cus.identifier.country));
			result &= this.checkCustIdenSectionContent(cus.identifier.identifierType.toUpperCase(), exp);
		}
		if (!result) {
			throw new ErrorOnPageException("Account Information is wrong! Check logger error above.");
		}
		logger.info("---Successfully verify Account Information on Customer Identity (Your Account Found) page!");
	}
	
	/** Verify Contact Information */
	public void verifyContactInformation(Customer cus) {
		boolean result = true;
		// home address
		String exp = cus.physicalAddr.address;
		exp = StringUtil.encryptString(exp, exp.indexOf(" "), exp.length(), "X");
		result &= MiscFunctions.compareString("Customer Home Address", exp, this.getCustHomeAddr());
		
		// home city
		exp = cus.physicalAddr.city + (StringUtil.isEmpty(cus.physicalAddr.county) ? "" : ", "+cus.physicalAddr.county) +
				", "+cus.physicalAddr.state + ", "+cus.physicalAddr.country;
		result &= MiscFunctions.compareString("Customer Home City", exp, this.getCustHomeCity());
		
		// home phone
		exp = cus.hPhone;
		if (exp.length()==10) { // 10 digits, display format xxx-xxx-1245
			exp = "XXX-XXX-" + exp.substring(exp.length()-4);
		} else if (exp.length()>10) { // 11 digits, display format xxxx-xxx-xxx-1245
			exp = "XXXX-XXX-XXX-" + exp.substring(exp.length()-4);
		}
		result &= MiscFunctions.compareString("Customer Home Phone", exp, this.getCustHomePhone());
		
		// email address
		result &= MiscFunctions.compareString("Customer Email Address", cus.email, this.getCustEmailAddr());
		
		if (!result) {
			throw new ErrorOnPageException("Contact Information is wrong! Check logger error above.");
		}
		logger.info("---Successfully verify Contact Information on Customer Identity (Your Account Found) page!");
	}
	
	public void verifyEmailAddress(String exp) {
		String actual = "";
		if (this.isEmailAddrTextFieldExist()) {
			actual = this.getCustEmailAddrFieldValue();
		} else {
			actual = this.getCustEmailAddr();
		}
		
		if (!exp.equals(actual)) {
			throw new ErrorOnPageException("Customer Email Address is wrong!", exp, actual);
		}
		logger.info("Customer Email Address is correct as " + exp);
	}
	
	public String getTopErrorMsg() {
		return browser.getObjectText(this.getTopErrorMsgDivProp());
	}
	
	public boolean isTopErrorMsgExist() {
		return browser.checkHtmlObjectExists(this.getTopErrorMsgDivProp());
	}
	
	public String getEmailAddrErrorMsg() {
		return browser.getObjectText(Property.atList(this.getCustEmailAddrValueDivProp(), this.getErrorItemDivProp()));
	}
	
	public boolean isEmailAddrErrorMsgExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getCustEmailAddrValueDivProp(), this.getErrorItemDivProp()));
	}
	
	/** Verify top error message and email address error messages */
	public void verifyErrorMsgs(String expTop, String expEmailError) {
		boolean result = true;
		
		boolean isExist = StringUtil.notEmpty(expTop);
		result &= MiscFunctions.compareResult("Top Error Msg exist", isExist, this.isTopErrorMsgExist());
		if (isExist) {
			result &= MiscFunctions.compareString("Top Error Msg", expTop, this.getTopErrorMsg());
		}
		
		isExist = StringUtil.notEmpty(expEmailError);
		result &= MiscFunctions.compareResult("Top Email Error Msg exist", isExist, this.isEmailAddrErrorMsgExist());
		if (isExist) {
			result &= MiscFunctions.compareString("Top Email Error Msg", expEmailError, this.getEmailAddrErrorMsg());
		}
		
		if(!result) {
			throw new ErrorOnPageException("Error Messages are wrong!");
		}
		logger.info("---Successfully verify Error Messages!");
	}
	
	/** Check if merged customer message exist */
	public boolean isMergedCustMsgExist() {
		return browser.checkHtmlObjectExists(this.mergedCustMsgProp());
	}
	
	public void verifyMergedCustMsgExist(boolean exp) {
		String msg = exp ? " " : " NOT ";
		if (exp != this.isMergedCustMsgExist()) {
			throw new ErrorOnPageException("Merged Customer Message should" + msg + "exist on Account Found page!");
		}
		logger.info("Merged Customer Message does" + msg + "exist on Account Found page!");
	}
	
	/** Get merged customer message */
	public String getMergedCustMsg() {
		return browser.getObjectText(this.mergedCustMsgProp());
	}
	
	public void verifyMergedCustMsg(String expMsg) {
		String actualMsg = this.getMergedCustMsg();
		if (!actualMsg.matches(expMsg)) {
			throw new ErrorOnPageException("Merged customer message is wrong!", expMsg, actualMsg);
		}
		logger.info("Merged customer message is correct as " + actualMsg);
	}
}

