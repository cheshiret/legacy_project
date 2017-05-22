/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author szhou
 * @Date  Jul 28, 2011
 */
public class SupportCenterAlertDialog extends SupportCenterPage{
	private static SupportCenterAlertDialog _instance = null;

	public static SupportCenterAlertDialog getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterAlertDialog();
		}
		return _instance;
	}
	
	protected Property[] ok() {
		return Property.toPropertyArray(".class","FlexButton","automationName","OK","className","mx.controls.Button");
	}
	
	protected Property[] flexAlert() {
		return Property.toPropertyArray(".class","FlexAlert","automationClassName","FlexAlert","className","mx.controls.Alert");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(flexAlert());
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
	
	public String getText() {
		return browser.getObjectText(flexAlert());
	}



}
