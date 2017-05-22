package com.activenetwork.qa.awo.pages.orms.licenseManager.user;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 25, 2012
 */
public class LicMgrAssignRoleLocationWidget extends DialogWidget {
	
	private static LicMgrAssignRoleLocationWidget _instance = null;
	
	private LicMgrAssignRoleLocationWidget() {
		super("Please select the Role and Location to be assigned to the User");
	}
	
	public static LicMgrAssignRoleLocationWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrAssignRoleLocationWidget();
		}
		
		return _instance;
	}
	
	public void selectRole(String role, int index) {
		browser.selectDropdownList(".id", new RegularExpression("RoleLocView-\\d+\\.roleID", false), role, index);
	}
	
	public void selectLocation(String location, int index) {
		browser.selectDropdownList(".id", new RegularExpression("RoleLocView-\\d+\\.locID", false), location, index);
	}
	
	public void clickRemove(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index - 1);
	}
	
	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true);
	}
	
	/**
	 * select role - location default by index = 0
	 * @param role
	 * @param location
	 */
	public void selectRoleLocation(String role, String location) {
		selectRoleLocation(role, location, 0);
	}
	
	/**
	 * select role - location
	 * @param role
	 * @param location
	 * @param index
	 */
	public void selectRoleLocation(String role, String location, int index) {
		selectRole(role, index);
		selectLocation(location, index);
	}
	
	/**
	 * select multiple role - location
	 * @param roles
	 * @param locations
	 */
	public void selectRoleLocation(String roles[], String locations[]) {
		if(roles.length != locations.length) {
			throw new ErrorOnPageException("Roles size doesn't match locations size, please check it.");
		}
		for(int i = 0; i < roles.length; i ++) {
			if(i > 0) {
				clickAdd();
				ajax.waitLoading();
				waitLoading();
			}
			selectRoleLocation(roles[i], locations[i], i);
		}
	}
}
