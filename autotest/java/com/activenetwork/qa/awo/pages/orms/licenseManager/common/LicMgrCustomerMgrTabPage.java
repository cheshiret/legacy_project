package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrSearchTabPage.java
 * @Preconditions: 
 * @SPEC: Search privilege
 * @Task#: AUTO-871
 * @Date:Feb 28, 2012
 * @author jwang8
 */
public class LicMgrCustomerMgrTabPage extends LicMgrCommonTopMenuPage{
    public static LicMgrCustomerMgrTabPage instance = null;
    
    protected LicMgrCustomerMgrTabPage(){};
    
    public static LicMgrCustomerMgrTabPage getInstance(){
    	if(null == instance){
    		instance = new LicMgrCustomerMgrTabPage();
    	}
    	return instance;
    }
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", new RegularExpression("CustomerMgrTabs",false));
	}
   
//	/**
//	 * Click the customer tab.
//	 */
//	public void clickCustomerTab(){
//		browser.clickGuiObject(".class", "Html.A",".text", "Custmers");
//	}
//	
//	 /**
//	  * Click the suspensions tab
//	  */
//	 
//	public void clickSuspensions(){
//	   browser.clickGuiObject(".class", "Html.A", ".text", "SearchSuspension");
//	}
//	
//	 /**
//	  *  Click the privilege table
//	  */
//	 
//	public void clickPrivileges(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "Privileges");
//	}
//	
	/**
	 * Click 'Customers' tab to switch to search customers page
	 */
	public void clickCustomersTab() {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", new RegularExpression("Customers", false));
		property[2] = new Property(".href", new RegularExpression("SearchCustomer$", false));
		browser.clickGuiObject(property);
		ajax.waitLoading();
	}
	
	/**
	 * Click 'Suspensions' tab to switch to search suspensions page
	 */
	public void clickSuspensionsTab() {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", new RegularExpression("Suspensions", false));
		property[2] = new Property(".href", new RegularExpression("SearchSuspension$", false));
		browser.clickGuiObject(property);
		ajax.waitLoading();
	}
	
	/**
	 * Click 'Privileges' tab to switch to search privileges page
	 */
	public void clickPrivilegesTab() {
		//Property property[] = new Property[3];
		//property[0] = new Property(".class", "Html.A");
		//property[1] = new Property(".text", new RegularExpression("Privileges", false));
		//property[2] = new Property(".href", new RegularExpression("SearchPrivilege$", false));
		//browser.clickGuiObject(property);
		//ajax.waitLoading();
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Privileges.*", false));
	}
}
