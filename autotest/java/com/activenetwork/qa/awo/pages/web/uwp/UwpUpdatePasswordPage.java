package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author jchen
 */
public class UwpUpdatePasswordPage extends UwpPage {
	private static UwpUpdatePasswordPage _instance = null;

	public static UwpUpdatePasswordPage getInstance() {
		if (null == _instance)
			_instance = new UwpUpdatePasswordPage();

		return _instance;
	}

	protected UwpUpdatePasswordPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("Update Password|updatePassword",false),".className","accountside in");
	}
	
	/**
	 * Fill in old password.
	 * @param pwd - password
	 */
	public void setOldPassword(String pwd) {
		browser.setPasswordField(".id", "oldpassword", pwd, true);
	}
	
	/**
	 * Fill in new password.
	 * @param pwd - password
	 */
	public void setNewPassword(String pwd) {
		browser.setPasswordField(".id", "newPassword", pwd, true);
	}
	
	/**
	 * Fill in retype password.
	 * @param pwd - password
	 */
	public void setRetypePassword(String pwd) {
		browser.setPasswordField(".id", "retypePassword", pwd, true);
	}
	
	/**
	 * Click on Save changes link to save your changes.
	 */
	public void clickSaveChanges() {
		RegularExpression reg = new RegularExpression(".*Save Changes",false);
		browser.clickGuiObject  (".text", reg,".type", "submit",true);
	}
	
	/**
	 * Fill all fields when update password.
	 * @param oldPwd - old password
	 * @param newPwd - new password
	 */
	public void updatePassword(String oldPwd, String newPwd) {
		this.setOldPassword(oldPwd);
		this.setNewPassword(newPwd);
		this.setRetypePassword(newPwd);
		this.clickSaveChanges();
	}
}
