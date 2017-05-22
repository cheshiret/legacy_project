package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class UwpCrossOverToSignInSignUpPage extends UwpPage implements IPopupPage {
	private String attributeName;
	private Object value;
	private static UwpCrossOverToSignInSignUpPage _instance = null;

	public static UwpCrossOverToSignInSignUpPage getInstance() {
		if (null == _instance)
			_instance = new UwpCrossOverToSignInSignUpPage();

		return _instance;
	}

	protected UwpCrossOverToSignInSignUpPage() {
		browser = null;
		attributeName = "url";
		value = new RegularExpression(".*memberSignInSignUp.do",false);
	}

	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		return browser!=null;
	}

	/** Page Object Property Definition Begin*/
	protected Property[] signIn() {
		return Property.toPropertyArray(".id", "submitForm_submitForm");
	}
	
	protected Property[] passWord() {
		return Property.toPropertyArray(".id", new RegularExpression("ApasswrdGroup_(-)?\\d+", false));
	}
	
	protected Property[] userName() {
		return Property.toPropertyArray(".id", new RegularExpression("AemailGroup_(-)?\\d+", false));
	}
	/** Page Object Property Definition End*/
	
	public void setUserName(String user) {
		browser.setTextField(userName(), user);
	}
	
	public void setPassword(String pwd) {
		browser.setPasswordField(passWord(), pwd);
	}
	
	public void clickSignIn() {
		browser.clickGuiObject(signIn());
	}

	public void signIn(String user, String pwd) {
		this.setUserName(user);
		this.setPassword(pwd);
		this.clickSignIn();
	}
}
