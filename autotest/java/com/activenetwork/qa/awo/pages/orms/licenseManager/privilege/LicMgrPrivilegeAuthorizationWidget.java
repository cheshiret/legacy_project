package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Privilege Authorization Widget, displayed when purchase an outfitter privilege. 
 * @author Lesley Wang
 * @Date  Sep 23, 2013
 */
public class LicMgrPrivilegeAuthorizationWidget extends DialogWidget {
	private static LicMgrPrivilegeAuthorizationWidget _instance = null;
	
	protected LicMgrPrivilegeAuthorizationWidget() {
		
	}
	
	public static LicMgrPrivilegeAuthorizationWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeAuthorizationWidget();
		}
		
		return _instance;
	}
	
	/** Page Object Property Definition Begin*/
	protected Property[] topDivProp() {
		return Property.concatPropertyArray(div(), ".id", "hunter");
	}
	
	protected Property[] custNumTextFieldProp() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierNumber", false));
	}
	
	protected Property[] custDOBTextFieldProp() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay", false));
	}
	/** Page Object Property Definition END*/
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(topDivProp());
	}
	
	public void setCustNum(String custNum) {
		browser.setTextField(custNumTextFieldProp(), custNum);
	}
	
	public void setCustDateOfBirth(String dateOfBirth) {
		browser.setTextField(custDOBTextFieldProp(), dateOfBirth);
	}
	
	public void setCustInfo(String custNum, String dateOfBirth) {
		this.setCustNum(custNum);
		this.setCustDateOfBirth(dateOfBirth);
	}
}
