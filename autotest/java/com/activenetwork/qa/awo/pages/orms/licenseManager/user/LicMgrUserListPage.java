package com.activenetwork.qa.awo.pages.orms.licenseManager.user;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 13, 2012
 */
public class LicMgrUserListPage extends LicMgrTopMenuPage {
	
	private static LicMgrUserListPage _instance = null;
	
	private LicMgrUserListPage() {}
	
	public static LicMgrUserListPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrUserListPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("usermanagementList(_LIST)?", false));//usermanagementList
	}
	
	private static final String USER_NAME_COL = "User Name";
	private static final String STATUS_COL = "Active";
	private static final String LOCKED_COL = "Locked?";
	private static final String FIRST_NAME_COL = "First Name";
	private static final String LAST_NAME_COL = "Last Name";
	private static final String LOCATIONS_COL = "Locations";
	private static final String ROLES_COL = "Roles";
	
	public void clickAddUser() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add User", true);
	}
	
	public void clickUserNameLink(String userName) {
		browser.clickGuiObject(".class", "Html.A", ".text", userName, true);
	}
	
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", new RegularExpression("UserSearchCriteria-\\d+\\.searchType", false), searchType);
	}
	
	public void setSearchValue(String searchValue) {
		browser.setTextField(".id", new RegularExpression("UserSearchCriteria-\\d+\\.searchValue", false), searchValue);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("UserSearchCriteria-\\d+\\.userStatus", false), status);
	}
	
	public void selectStatus(int index) {
		browser.selectDropdownList(".id", new RegularExpression("UserSearchCriteria-\\d+\\.userStatus", false), index);
	}
	
	public void selectAssignedWithRole(String role) {
		browser.selectDropdownList(".id", new RegularExpression("UserSearchCriteria-\\d+\\.role", false), role);
	}
	
	public void selectAssignedWithRole(int index) {
		browser.selectDropdownList(".id", new RegularExpression("UserSearchCriteria-\\d+\\.role", false), index);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void setSearchCriteria(String searchType, String searchValue, String status, String role) {
		if(!StringUtil.isEmpty(searchType)) {
			selectSearchType(searchType);
		}
		if(!StringUtil.isEmpty(searchValue)) {
			setSearchValue(searchValue);
		}
		if(!StringUtil.isEmpty(status)) {
			selectStatus(status);
		}
		if(!StringUtil.isEmpty(role)) {
			selectAssignedWithRole(role);
		}
	}
	
	public void searchUser(String searchType, String searchValue, String status, String role) {
		setSearchCriteria(searchType, searchValue, status, role);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchUser(String searchType, String searchValue) {
		searchUser(searchType, searchValue, null, null);
	}
	
	public void clearSearchCriteria() {
		setSearchValue(StringUtil.EMPTY);
		selectStatus(0);
		selectAssignedWithRole(0);
	}
	
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate", true);
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
	}
	
	public void clickLock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Lock", true);
	}
	
	public void clickUnlock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Unlock", true);
	}
	
	public void selectAllUsers() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	private IHtmlObject[] getUserListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "usermanagementList_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find User list table object.");
		}
		
		return objs;
	}
	
	public List<User> getAllUsers() {
		PagingComponent turningPage = new PagingComponent();
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		
		List<User> users = new ArrayList<User>();
		List<String> rowValues = new ArrayList<String>();
		
		do {
			objs = getUserListTableObject();
			table = (IHtmlTable)objs[0];
			
			for(int i = 1; i < table.rowCount(); i ++) {
				rowValues = table.getRowValues(i);
				User user = new User();
				user.userName = rowValues.get(table.findColumn(0, USER_NAME_COL));
				user.isActive = rowValues.get(table.findColumn(0, STATUS_COL)).equals(OrmsConstants.ACTIVE_STATUS) ? true : false;
				user.isLocked = rowValues.get(table.findColumn(0, LOCKED_COL)).equals(OrmsConstants.YES_STATUS) ? true : false;
				user.fName = rowValues.get(table.findColumn(0, FIRST_NAME_COL));
				user.lName = rowValues.get(table.findColumn(0, LAST_NAME_COL));
				user.numOfLocations = Integer.parseInt(rowValues.get(table.findColumn(0, LOCATIONS_COL)));
				user.numOfRoles = Integer.parseInt(rowValues.get(table.findColumn(0, ROLES_COL)));
				
				users.add(user);
			}
		} while(turningPage.clickNext());
		
		Browser.unregister(objs);
		return users;
	}
	
	public User getUserInfo(String userName) {
		List<User> allUsers = getAllUsers();
		for(User u : allUsers) {
			if(u.userName.equals(userName)) {
				return u;
			}
		}
		
		return null;
	}
	
	public boolean compareUserInfo(User expected) {
		User actual = getUserInfo(expected.userName);
		boolean result = true;
		
		result &= MiscFunctions.compareResult("User Name", expected.userName, actual.userName);
		result &= MiscFunctions.compareResult("Status", expected.isActive, actual.isActive);
		result &= MiscFunctions.compareResult("Locked", expected.isLocked, actual.isLocked);
		result &= MiscFunctions.compareResult("First Name", expected.fName, actual.fName);
		result &= MiscFunctions.compareResult("Last Name", expected.lName, actual.lName);
		result &= MiscFunctions.compareResult("Num of Locations", expected.numOfLocations, actual.numOfLocations);
		result &= MiscFunctions.compareResult("Num of Roles", expected.numOfRoles, actual.numOfRoles);
		
		return result;
	}
	
	/**
	 * verify if the searching result is correct with search value
	 * @param searchBy - 'User Name', 'First Name', 'Last Name', 'Status', and 'Role'
	 * @param searchValue
	 * @return
	 */
	public boolean verifySearchResult(String searchBy, String searchValue) {
		PagingComponent turningPage = new PagingComponent();
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		
		List<String> colValues = new ArrayList<String>();
		
		do {
			objs = getUserListTableObject();
			table = (IHtmlTable)objs[0];
			
			for(int i = 1; i < table.rowCount(); i ++) {
				colValues.addAll(table.getColumnValues(table.findColumn(0, searchBy)));
			}
		} while(turningPage.clickNext());
		
		for(int i = 0; i < colValues.size(); i ++) {
			if(colValues.get(i).equals(searchValue)) {
				return true;
			}
		}
		
		Browser.unregister(objs);
		return false;
	}
}
