package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 */
public class UwpUpdateEmailPage extends UwpPage {

	private static UwpUpdateEmailPage _instance = null;

	public static UwpUpdateEmailPage getInstance() {
		if (null == _instance)
			_instance = new UwpUpdateEmailPage();

		return _instance;
	}

	public UwpUpdateEmailPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("[updateEmail|Update Email]",false),".className","accountside in");
	}
	
	/**
	 * Fill in new email address.
	 * @param email - email account
	 */
	public void setNewEmail(String email) {
		browser.setTextField(".id", "upemail", email, true);
	}
	
	/**
	 * Fill in password.
	 * @param pwd - password
	 */
	public void setPassword(String pwd) {
		browser.setPasswordField(".id", "oldpass", pwd, true);
	}
	
	/**
	 * Click link to save changes.
	 */
	public void clickSaveChanges() {
		RegularExpression reg = new RegularExpression(".*Save Changes",false);
		browser.clickGuiObject  (".text", reg,".type", "submit",true);
	}
	
	/**
	 * Fill in all fields to update email.
	 * @param newEmail - new email adress
	 * @param pwd - password
	 */
	public void updateEmail(String newEmail, String pwd) {
		this.setNewEmail(newEmail);
		this.setPassword(pwd);
		this.clickSaveChanges();
	}
}
