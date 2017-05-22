package com.activenetwork.qa.awo.pages.orms.licenseManager.user;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
public class LicMgrUserDetailsPage extends LicMgrTopMenuPage {
	
	private static LicMgrUserDetailsPage _instance = null;
	
	protected LicMgrUserDetailsPage() {}
	
	public static LicMgrUserDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrUserDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "userDetailsFormBar");
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("UserView-\\d+\\.active", false), status);
	}
	
	public void setPassword(String pw) {
		browser.setPasswordField(".id", new RegularExpression("UserView-\\d+\\.password", false), pw);
	}
	
	public void selectUserMustChangePasswordAtNextLoginCheckbox() {
		browser.selectCheckBox(".id", new RegularExpression("UserView-\\d+\\.changePasswordAtNextLogin", false));
	}
	
	public void unselectUserMustChanegPasswordAtNextLoginCheckbox() {
		browser.unSelectCheckBox(".id", new RegularExpression("UserView-\\d+\\.changePasswordAtNextLogin", false));
	}
	
	public void setConfirmPassword(String pw) {
		browser.setPasswordField(".id", new RegularExpression("UserView-\\d+\\.confirmPassword", false), pw);
	}
	
	public void setFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.firstName", false), fName);
	}
	
	public void setLastName(String lName) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.lastName", false), lName);
	}
	
	public void setPhone(String phone) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.phoneNumber", false), phone);
	}
	
	public void setFax(String fax) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.fax", false), fax);
	}
	
	public void setEmail(String email) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.email", false), email);
	}
	
	public void setAddress(String address) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.address", false), address);
	}
	
	public void selectState(String state) {
		browser.selectDropdownList(".id", new RegularExpression("UserView-\\d+\\.stateProvinceID", false), state);
	}
	
	public void setZip(String postal) {
		browser.setTextField(".id", new RegularExpression("UserView-\\d+\\.zip", false), postal);
	}
	
	public void setupUserInfo(User user) {
		selectStatus(user.isActive ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS);
		if(!StringUtil.isEmpty(user.password)) {
			setPassword(user.password);
		}
		if(!StringUtil.isEmpty(user.confirmPassword)) {
			setConfirmPassword(user.confirmPassword);
		}
		if(user.isUserMustChangePasswordAtNextLogin) {
			selectUserMustChangePasswordAtNextLoginCheckbox();
		}
		if(!StringUtil.isEmpty(user.fName)) {
			setFirstName(user.fName);
		}
		if(!StringUtil.isEmpty(user.lName)) {
			setLastName(user.lName);
		}
		if(!StringUtil.isEmpty(user.phone)) {
			setPhone(user.phone);
		}
		if(!StringUtil.isEmpty(user.fax)) {
			setFax(user.fax);
		}
		if(!StringUtil.isEmpty(user.email)) {
			setEmail(user.email);
		}
		if(!StringUtil.isEmpty(user.address)) {
			setAddress(user.address);
		}
		if(!StringUtil.isEmpty(user.state)) {
			selectState(user.state);
		}
		if(!StringUtil.isEmpty(user.zip)) {
			setZip(user.zip);
		}
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickLock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Lock", true);
	}
	
	public void clickUnlock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Unlock", true);
	}
	
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete", true);
	}
	
	public String getUserName() {
		return browser.getObjectText(".id", new RegularExpression("UserView-\\d+\\.name", false)).split("User Name")[1].trim();
	}
	
	public String getStatus() {
		return browser.getDropdownListValue(".id", new RegularExpression("UserView-\\d+\\.active", false));
	}
	
	public String getLockedStatus() {
		return browser.getTextFieldValue(".id", "lockedInput");
	}
	
	public String getPassword() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.password", false));
	}
	
	public String getConfirmPassword() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.password", false));
	}
	
	public boolean isUserMustChangePasswordAtNextLogin() {
		return browser.isCheckBoxSelected(".id", new RegularExpression("UserView-\\d+\\.changePasswordAtNextLogin", false));
	}
	
	public String getFirstName() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.firstName", false));
	}
	
	public String getLastName() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.lastName", false));
	}
	
	public String getPhone() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.phoneNumber", false));
	}
	
	public String getFax() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.fax", false));
	}
	
	public String getEmail() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.email", false));
	}
	
	public String getAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.address", false));
	}
	
	public String getState() {
		return browser.getDropdownListValue(".id", new RegularExpression("UserView-\\d+\\.stateProvinceID", false));
	}
	
	public String getZip() {
		return browser.getTextFieldValue(".id", new RegularExpression("UserView-\\d+\\.zip", false));
	}
	
	public User getUserInfo() {
		User user = new User();
		
		user.userName = getUserName();
		user.isActive = getStatus().equals(OrmsConstants.ACTIVE_STATUS) ? true : false;
		user.isLocked = getLockedStatus().equals(OrmsConstants.YES_STATUS) ? true : false;
		user.isUserMustChangePasswordAtNextLogin = isUserMustChangePasswordAtNextLogin();
		user.fName = getFirstName();
		user.lName = getLastName();
		user.phone = getPhone();
		user.fax = getFax();
		user.email = getEmail();
		user.address = getAddress();
		user.state = getState();
		user.zip = getZip();
		
		return user;
	}
	
	public boolean compareUserInfo(User expected) {
		User actual = getUserInfo();
		boolean result = true;
		
		result &= MiscFunctions.compareResult("User Name", expected.userName, actual.userName);
		result &= MiscFunctions.compareResult("Status", expected.isActive, actual.isActive);
		result &= MiscFunctions.compareResult("Locked", expected.isLocked, actual.isLocked);
		result &= MiscFunctions.compareResult("First Name", expected.fName, actual.fName);
		result &= MiscFunctions.compareResult("Last Name", expected.lName, actual.lName);
		result &= MiscFunctions.compareResult("Phone", expected.phone, actual.phone);
		result &= MiscFunctions.compareResult("Fax", expected.fax, actual.fax);
		result &= MiscFunctions.compareResult("Email", expected.email, actual.email);
		result &= MiscFunctions.compareResult("Address", expected.address, actual.address);
		result &= MiscFunctions.compareResult("State", expected.state, actual.state);
		result &= MiscFunctions.compareResult("Zip/Postal Code", expected.zip, actual.zip);
		
		return result;
	}
}
