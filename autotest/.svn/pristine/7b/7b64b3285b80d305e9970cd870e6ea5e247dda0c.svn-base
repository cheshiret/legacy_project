package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description: In sign in page, click "Forget your Password?" link to this page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Mar 19, 2013
 */
public class HFSendMyPasswordPage extends HFHeaderBar {

	private static HFSendMyPasswordPage _instance = null;

	public static HFSendMyPasswordPage getInstance() {
		if (null == _instance)
			_instance = new HFSendMyPasswordPage();

		return _instance;
	}

	protected HFSendMyPasswordPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.FORM", ".id", "memberForgotPasswordForm");
	}

	public void setUserName(String email){
		browser.setTextField(".id", "userid", email);
	}

	public void setLastName(String lastName){
		browser.setTextField(".id", "lname", lastName);
	}

	public void repeatNewPassword(String repeatedNewPW){
		browser.setTextField(".id", "retypePassword", repeatedNewPW);
	}

	public void clickSendMyPasswordButton(){
		Property[] p1= Property.toPropertyArray(".class", "Html.DIV", ".className", "content shop");
		
		//Fixed the issue that the submit button could not be clicked, add ".type", "submit", in the code
		Property[] p2 = Property.toPropertyArray(".type", "submit", ".text", "Send My Password");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);

	}

	public boolean topPageErrorMesDisplays(){
		return browser.checkHtmlObjectExists(".className", "msg topofpage error");
	}

	public boolean successfulMesDisplays(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "msg success");
	}
}

