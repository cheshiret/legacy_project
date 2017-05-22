package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description: Under "My account" panel, click "Update Password" link can find this page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 18, 2013
 */
public class HFUpdatePasswordPage extends HFMyAccountPanel {

	private static HFUpdatePasswordPage _instance = null;

	public static HFUpdatePasswordPage getInstance() {
		if (null == _instance)
			_instance = new HFUpdatePasswordPage();

		return _instance;
	}

	protected HFUpdatePasswordPage() {
	}

	public boolean exists() {
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".id", "pagetitle", ".text", new com.activenetwork.qa.testapi.util.RegularExpression("^Update Password.*", false));
		return browser.checkHtmlObjectDisplayed(p);
	}

	public void setOldPassword(String oldPW){
		browser.setTextField(".id", "oldpassword", oldPW);
	}

	public void setNewPassword(String newPW){
		browser.setTextField(".id", "newPassword", newPW);
	}

	public void repeatNewPassword(String repeatedNewPW){
		browser.setTextField(".id", "retypePassword", repeatedNewPW);
	}

	public void clickSaveChangesButton(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "content btn");
		Property[] p2 = Property.toPropertyArray(".text", "Save Changes");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	public boolean topPageErrorMesDisplays(){
		return browser.checkHtmlObjectExists(".className", "msg topofpage error");
	}

	public boolean successfulMesDisplays(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "msg success");
	}
}

