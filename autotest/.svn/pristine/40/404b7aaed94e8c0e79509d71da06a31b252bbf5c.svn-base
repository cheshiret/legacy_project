package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description: Under "My account" panel, click "Update Email" link can find this page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 18, 2013
 */
public class HFUpdateEmailAddressPage extends HFMyAccountPanel {
	
	private static HFUpdateEmailAddressPage _instance = null;

	public static HFUpdateEmailAddressPage getInstance() {
		if (null == _instance)
			_instance = new HFUpdateEmailAddressPage();

		return _instance;
	}
	
	protected HFUpdateEmailAddressPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.TABLE", ".id", "createnew");
	}
	
	public void setPassword(String pw){
		browser.setTextField(".id", "oldpass", pw);
	}
	
	public void setNewEmail(String newEmail){
		browser.setTextField(".id", "upemail", newEmail);
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

