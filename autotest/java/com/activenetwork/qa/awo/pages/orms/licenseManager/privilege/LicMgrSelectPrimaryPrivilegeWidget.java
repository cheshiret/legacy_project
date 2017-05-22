package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  May 28, 2014
 */
public class LicMgrSelectPrimaryPrivilegeWidget extends DialogWidget {
	private static LicMgrSelectPrimaryPrivilegeWidget _instance = null;
	
	private LicMgrSelectPrimaryPrivilegeWidget() {
	}
	
	public static LicMgrSelectPrimaryPrivilegeWidget getInstance() {
		if(_instance == null) _instance = new LicMgrSelectPrimaryPrivilegeWidget();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(privilegeNum());
	}
	
	private Property[] privilegeNum() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeInstanceLightView-\\d+\\.privilegeNumber", false));
	}
	
	private Property[] privilegeName() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeInstanceLightView-\\d+\\.privilegeInfo", false));
	}
	
	private Property[] primaryPrivilegeHolder() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeInstanceLightView-\\d+\\.privilegeHolder", false));
	}
	
	private Property[] searchButton(){
		return Property.addToPropertyArray(a(), ".text", "Search");
	}
	
	private Property[] changeButton(){
		return Property.addToPropertyArray(a(), ".text", "Change");
	}
	
	private Property[] okButton(){
		return Property.addToPropertyArray(a(), ".text", "OK");
	}
	
	public void setPrivilegeNum(String num) {
		browser.setTextField(privilegeNum(), num);
	}
	
	public String getPrivilegeNum() {
		return browser.getTextFieldValue(privilegeNum());
	}
	
	public String getPrivilegeName() {
		return browser.getTextFieldValue(privilegeName());
	}
	
	public String getPrimaryPrivilegeHolder() {
		return browser.getTextFieldValue(primaryPrivilegeHolder());
	}
	
	public void clickSearch() {
		clickButtonByText("Search");
	}
	
	public boolean isSearchButtonExists(){
		return browser.checkHtmlObjectDisplayed(searchButton());
	}
	
	public boolean isChangeButtonExists(){
		return browser.checkHtmlObjectDisplayed(changeButton());
	}
	
	public boolean isOkButtonExists(){
		return browser.checkHtmlObjectDisplayed(okButton());
	}
	
	public void clickChange() {
		clickButtonByText("Change");
	}
	
	public void searchPrivilegeNum(String privilegeNum) {
		this.setPrivilegeNum(privilegeNum);
		this.clickSearch();
		ajax.waitLoading();
	}
}
