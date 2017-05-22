/*
 * Created on Feb 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmMgrRolePage extends AdminManagerPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrRolePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrRolePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrRolePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrRolePage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",new RegularExpression("Add New Role", false));
	}
	
	public void selectRoleName(String roleName){
	    browser.selectDropdownList(".id","SearchBar_SelectIndexFollows_0_like",roleName);
	}
	
	public void setRoleName(String roleName){
	    browser.setTextField(".id","SearchBar_InputIndexFollows_0_like",roleName);
	}
	
	public void clickGo(){
	   browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Go|Search", false));
	}
	
	public void clickAddNewRole(){
	   browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Add New Role", false));
	}
	
	public void gotoRoleDetails(String roleName){
	   browser.clickGuiObject(".class","Html.A",".text",roleName, true);
	}
	
	public void searchRoleName(String roleName){
	   setRoleName(roleName);
	   clickGo();
	   waitLoading();
	}
	
	public boolean checkRoleNameIsExisting(String roleName){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", roleName);
	}
}

