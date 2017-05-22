/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Create Web Sign In Page. the page is shown after click the button "Create Web Account" on Your Account Found page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 16, 2013
 */
public class HFCreateWebSignInPage extends HFHeaderBar {
	private static HFCreateWebSignInPage _instance = null;

	public static HFCreateWebSignInPage getInstance() {
		if (null == _instance)
			_instance = new HFCreateWebSignInPage();

		return _instance;
	}

	protected HFCreateWebSignInPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "createHFWebAccountForm");
	}

	private String emailAddGroupIDReg = "email";
	private String pwGroupIDReg = "passwd";
	private String repwGroupIDReg = "retypepass";
	private String homePhoneGroupIDReg = "homephone";
	
	private String emailAddIDReg = "Aemail_\\d+";
	private String pwIDReg = "Apasswd_-\\d+";
	private String retypePwIDReg = "Aretypepass_-\\d+";
	private String hPhoneIDReg = "Ahomephone_-\\d+";
	private String labelReg = "groupLabel";
	
	public String getPageTitle(){
		return browser.getObjectText(".id", "contentArea").replaceAll("\\s+", "");
	}
	
	public void setEmailAddress(String email) {
		browser.setTextField(".id", new RegularExpression(emailAddIDReg, false), email);
	}

	public void setPassword(String pw) {
		browser.setTextField(".id", new RegularExpression(pwIDReg, false), pw);
	}

	public void setRetypePassword(String retypePw) {
		browser.setTextField(".id", new RegularExpression(retypePwIDReg, false), retypePw);
	}

	public void setHomePhone(String hPhone) {
		browser.setTextField(".id", new RegularExpression(hPhoneIDReg, false), hPhone);
	}
	
	public void setWebAccountInfo(String email, String pw, String rePw) {
		this.setEmailAddress(email);
		this.setPassword(pw);
		this.setRetypePassword(rePw);
	}
	
	public void setWebAccountInfo(String email, String pw, String rePw, String hPhone) {
		this.setEmailAddress(email);
		this.setPassword(pw);
		this.setRetypePassword(rePw);
		this.setHomePhone(hPhone);
	}
	
	public void clickCreateButton() {
		browser.clickGuiObject(".id", "submitForm_submitForm", ".text", "Accept Privacy Policy and Create");
	}
	
	public IHtmlObject[] getCreatingWebSignInElementObjs(String parentObjsId, String childObjsPropName, String childObjsPropValue){
		Property[] p1  = Property.toPropertyArray(".id", parentObjsId);
		Property[] p2 = Property.toPropertyArray(childObjsPropName, new RegularExpression(childObjsPropValue, false));
		IHtmlObject[] objs = null;

		if(StringUtil.isEmpty(childObjsPropName) || StringUtil.isEmpty(childObjsPropValue)){
			objs = browser.getHtmlObject(p1);

		}else{
			objs = browser.getHtmlObject(Property.atList(p1, p2));
		}

		if(objs.length<1){
			throw new ObjectNotFoundException("Object "+childObjsPropName+":"+childObjsPropValue+" and parent objs id:"+parentObjsId+" can't be found.");
		}
		return objs;
	}

	public String getCreatingWebSignInElementValue(String parentObjsId, String childObjsPropName, String childObjsPropValue){
		IHtmlObject[] objs =this.getCreatingWebSignInElementObjs(parentObjsId, childObjsPropName, childObjsPropValue);
		String value = objs[0].text().trim();
		
		if(StringUtil.isEmpty(value)){
			value = objs[0].getProperty(".value").trim();
		}
		Browser.unregister(objs);
		return value;
	}

	public String getEmailAddress() {
		return getCreatingWebSignInElementValue(emailAddGroupIDReg, ".id", emailAddIDReg);
	}

	public void verifyEmailAddr(String email) {
		String actEmail = this.getEmailAddress();
		if ((StringUtil.isEmpty(email) && StringUtil.notEmpty(actEmail)) || 
				(StringUtil.notEmpty(email) && !email.equals(actEmail))) {
			throw new ErrorOnPageException("Email address in the text field is wrong in Create Web Sign in page!", email, actEmail);
		}
		logger.info("Email address on Web Sign In page correctly as " + email);
	}
	
	public boolean checkCreatingWebSignInElementValue(String parentObjsId, String childObjsPropName, String childObjsPropValue, String expectedValue){
		String actualValue = this.getCreatingWebSignInElementValue(parentObjsId, childObjsPropName, childObjsPropValue).trim();
		logger.info("Actual value:"+actualValue);
		return expectedValue.equals(actualValue);
	}

	public boolean checkEmailAddressLabel(String expectedValue){
		return checkCreatingWebSignInElementValue(emailAddGroupIDReg, ".className", labelReg, expectedValue);
	}
	
	public boolean checkEmailAddressValue(String expectedValue){
		return checkCreatingWebSignInElementValue(emailAddGroupIDReg, ".id", emailAddIDReg, expectedValue);
	}

	public boolean checkPasswordLabel(String expectedValue){
		return checkCreatingWebSignInElementValue(pwGroupIDReg, ".className", labelReg, expectedValue);
	}

	public boolean checkPasswordValue(String expectedValue){
		return checkCreatingWebSignInElementValue(pwGroupIDReg, ".id", pwIDReg, expectedValue);
	}
	
	public boolean checkRetypePasswordLabel(String expectedValue){
		return checkCreatingWebSignInElementValue(repwGroupIDReg, ".className", labelReg, expectedValue);
	}

	public boolean checkRetypePasswordValue(String expectedValue){
		return checkCreatingWebSignInElementValue(repwGroupIDReg, ".id", retypePwIDReg, expectedValue);
	}
	
	public boolean checkHomePhoneLabel(String expectedValue){
		return checkCreatingWebSignInElementValue(homePhoneGroupIDReg, ".className", labelReg, expectedValue);
	}

	public boolean checkHomePhoneValue(String expectedValue){
		return checkCreatingWebSignInElementValue(homePhoneGroupIDReg, ".id", hPhoneIDReg, expectedValue);
	}
	
	public boolean checkCreatingWebSignInElementExists(String parentObjsId, String childObjsId){
		Property[] p1  = Property.toPropertyArray(".id", parentObjsId);
		Property[] p2 =Property.toPropertyArray(".id", childObjsId);

		if(!StringUtil.isEmpty(childObjsId)){
			return browser.checkHtmlObjectExists(Property.atList(p1, p2));
		}else{
			return browser.checkHtmlObjectExists(p1);
		}
	}
	
	public boolean checkHomePhoneSectionExists(){
		return checkCreatingWebSignInElementExists(homePhoneGroupIDReg, StringUtil.EMPTY);
	}
	
	/** Check if the error message exist */
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("error_item|message", false), ".text", new RegularExpression(msg, false));
	}

	/** Verify Error Message exist */
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
}
