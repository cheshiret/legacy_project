package com.activenetwork.qa.awo.pages.orms.licenseManager.user;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User.UserRoleLocationAssignment;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
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
 * @Date  Sep 14, 2012
 */
public class LicMgrUserRolesLocationsPage extends LicMgrUserDetailsPage {
	
	private static LicMgrUserRolesLocationsPage _instance = null;
	
	private LicMgrUserRolesLocationsPage() {}

	public static LicMgrUserRolesLocationsPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrUserRolesLocationsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "userrolelocationList");
	}
	
	private static final String ROLE_NAME_COL = "Role Name";
	private static final String LOCATION_NAME_COL = "Location Name";
	private static final String LOCATION_LEVEL_COL = "Location Level";
	
	public void clickAssignRoleLocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Role Location", true);
	}
	
	public void clickDelete() {
		IHtmlObject divObjs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "deleteButton");
		if(divObjs.length < 1) {
			throw new ItemNotFoundException("Can't find Delete DIV object.");
		}
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.DIV", ".text", "Delete"), true, 0, divObjs[0]);
	}
	
	public void selectAllCheckboxes() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void selectCheckboxByRowIndex(int index) {
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), index - 1, true);
	}
	
	private IHtmlObject[] getUserRoleLocationTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "userrolelocationList");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find User - Role/Location assignemnt table object.");
		}
		
		return objs;
	}
	
	public List<UserRoleLocationAssignment> getAllUserRoleLocationAssignments() {
		IHtmlObject objs[] = getUserRoleLocationTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<UserRoleLocationAssignment> assignments = new ArrayList<UserRoleLocationAssignment>();
		List<String> rowValues = new ArrayList<String>();
		
		for(int i = 1; i < table.rowCount(); i ++) {
			rowValues = table.getRowValues(i);
			UserRoleLocationAssignment assignment = new User().new UserRoleLocationAssignment();
			assignment.role = rowValues.get(table.findColumn(0, ROLE_NAME_COL));
			assignment.location = rowValues.get(table.findColumn(0, LOCATION_NAME_COL));
			assignment.locationLevel = rowValues.get(table.findColumn(0, LOCATION_LEVEL_COL));
			
			assignments.add(assignment);
		}
		
		Browser.unregister(objs);
		return assignments;
	}
	
	public boolean compareUserRoleLocationAssignment(List<UserRoleLocationAssignment> expected) {
		boolean result = true;
		
		List<UserRoleLocationAssignment> actual = getAllUserRoleLocationAssignments();
		if(actual.size() != expected.size()) {
			throw new ErrorOnPageException("The actual User - Role/Location assignment size doesn't match expected.");
		}
		for(int i = 0 ; i < actual.size(); i ++) {
			result &= MiscFunctions.compareResult("Role Name", expected.get(i).role, actual.get(i).role);
			result &= MiscFunctions.compareResult("Location Name", expected.get(i).location, actual.get(i).location);
			result &= MiscFunctions.compareResult("Location Level", expected.get(i).locationLevel, actual.get(i).locationLevel);
		}
		
		return result;
	}
	
	private int getRowIndexByAssignment(UserRoleLocationAssignment assignment) {
		IHtmlObject objs[] = getUserRoleLocationTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> rowValue = new ArrayList<String>();
		int index = -1;
		for(int i = 1; i < table.rowCount(); i ++) {
			rowValue = table.getRowValues(i);
			if(rowValue.get(1).equals(assignment.role) && rowValue.get(2).equals(assignment.location) && rowValue.get(3).equals(assignment.locationLevel)) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		
		if(index == -1) {
			throw new ItemNotFoundException("Can't find this assignment record.");
		}
		
		return index;
	}
	
	public void deleteUserRoleLocationAssignment(UserRoleLocationAssignment... assignments) {
		for(UserRoleLocationAssignment assignment : assignments) {
			selectCheckboxByRowIndex(getRowIndexByAssignment(assignment));
			clickDelete();
			ajax.waitLoading();
			waitLoading();
		}
	}
	
	public String getWarningMessage() {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".id", "NOTSET", ".className", "message msgsuccess"));
	}
}
