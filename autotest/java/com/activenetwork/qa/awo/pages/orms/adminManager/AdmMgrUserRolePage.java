/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date Mar 26, 2012
 */
public class AdmMgrUserRolePage extends AdmMgrUserCommonTabPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrUserRolePage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrUserRolePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrUserRolePage getInstance() {
		if (null == _instance) {
			_instance = new AdmMgrUserRolePage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Role");
	}

	public void selectRoleName(String roleName) {
		browser.selectDropdownList(".id", "e_DropDownRoles", roleName);
	}

	public void selectLocationName(String locationName) {
		browser.selectDropdownList(".id", "e_DropDownLocations", locationName);
	}

	public void clickAddRole() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Role");
	}

	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	public String getRoleInfo() {
		String roleInfo = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Role Name.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		// if(table.rowCount()>0){
		// roleInfo = table.getCellValue(1,2)+" "+table.getCellValue(1,3)+
		// " "+table.getCellValue(1,4);
		// }
		roleInfo = table.text();

		Browser.unregister(objs);

		return roleInfo;
	}
	
	public void selectAllRole() {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".name",
				"all_slct");
	}

	public void selectAddedRole() {
		int result = 0;
		String valueString = "";
		int valueInt = 0;

		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"ConfigurableCheckBox_.*", false));
		for (int i = 0; i < objs.length; i++) {
			valueString = objs[i].getProperty(".id").toString().split(
					"ConfigurableCheckBox_")[1];
			valueInt = Integer.parseInt(valueString);
			if (valueInt > result) {
				result = valueInt;
			}
		}
		Browser.unregister(objs);
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id",
				new RegularExpression("ConfigurableCheckBox_" + result, false));
	}
}
