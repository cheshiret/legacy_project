package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.util.Property;
/**
 * @Description: Add Hunt License Year widget
 * @author Lesley Wang
 * @Date  Aug 19, 2013
 */
public class LicMgrAddHuntLicYearWidget extends LicMgrHuntLicYearCommonWidget {
	private static LicMgrAddHuntLicYearWidget _instance = null;
	
	protected LicMgrAddHuntLicYearWidget() {
		super("Add Hunt Licen(s|c)e Year");
	}
	
	public static LicMgrAddHuntLicYearWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddHuntLicYearWidget();
		}
		
		return _instance;
	}
	
	/** Page Object Property Begin */
	protected Property[] newIdTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".text", "New");
	}
	
	protected Property[] statusDropdownListProp() {
		return Property.toPropertyArray(".id", "codebaseDropdown");
	}
	
	
	public boolean isIdTextFieldEditable(){
		return browser.checkHtmlObjectEnabled(this.newIdTextFieldProp());
	}
	
	public boolean isStatusTextFieldEditable(){
		return browser.checkHtmlObjectEnabled(this.statusDropdownListProp());
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(this.statusDropdownListProp(), status);
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(this.statusDropdownListProp(), 0);
	}

}
