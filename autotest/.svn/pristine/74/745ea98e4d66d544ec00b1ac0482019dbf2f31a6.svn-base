/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.List;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It is for web maintenance application role/location selection page
 * Web maintenance applications include Photo Tool, Marketing Spot Manager, and Admin.do
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 12, 2012
 */
public class WebMaintenanceAppSelectRoleLocPage extends
		WebMaintenanceAppUserPanel {
	public final String errMsg_EmptyRole = "We need you to correct or provide more information. Please see each marked field.";
	public final String roleFieldLabel = "Role/Location";
	public final String roleFieldErrMsg = roleFieldLabel + " is required";
	public final String roleLocText = "Multiple roles/locations are assigned to this account. Please select one to continue";
	public final String defaultRoleLoc = "-- Select Role/Location --";
	
	private static WebMaintenanceAppSelectRoleLocPage _instance = null;

	public static WebMaintenanceAppSelectRoleLocPage getInstance() {
		if (null == _instance)
			_instance = new WebMaintenanceAppSelectRoleLocPage();

		return _instance;
	}
	
	protected WebMaintenanceAppSelectRoleLocPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "rolelocid");
	}
	
	public String getUserName() {
		return browser.getTextFieldValue(".id", "usernameid");
	}
	
	public void selectRoleLocation(String role, String loc) {
		if (StringUtil.isEmpty(role) && StringUtil.isEmpty(loc)) {
			browser.selectDropdownList(".id", "rolelocid", 0);
		} else {
			browser.selectDropdownList(".id", "rolelocid", role + "/" + loc);
		}
	}
	
	public void clickContinue() {
		browser.clickGuiObject(".id", "submit");
	}
	
	/**
	 * Click Continue Button and the page still exists when there are something wrong with the login info
	 */
	public void clickContinueButton() {
		logger.info("Click continue button...");
		this.clickContinue();
		this.waitLoading();
	}
	/**
	 * Get the error message on the top of the page
	 * @return
	 */
	public String getTopErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg error");
	}
	
	/**
	 * Get the error message displayed above user name or password text field
	 * @param fieldLable
	 * @return
	 */
	public String getFieldErrorMessage(String fieldLable) {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".classname", "msg error", 
				".text", new RegularExpression("^" + fieldLable + ".*", false)));
	}
	
	/**
	 * Check if there is an error message displayed above user name or password text field
	 * @return
	 */
	public boolean isFieldErrorMessageExist() {
		return browser.checkHtmlObjectExists(Property.atList(
				Property.toPropertyArray(".id", "loginForm"), 
				Property.toPropertyArray(".class", "Html.DIV", ".classname", "msg error")));
	}
	
	/**
	 * Check if text field is read only
	 * @param id
	 * @return
	 */
	private boolean isTextFieldReadOnly(String id) {
		IHtmlObject[] objs = browser.getTextField(".id", id);
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find text field!");
		}
		boolean isEnabled = ((IText)objs[0]).readOnly();
		Browser.unregister(objs);
		return isEnabled;
	}
	
	/**
	 * Check if User Name text field is read only
	 */
	public boolean isUserNameTextFieldReadOnly() {
		return isTextFieldReadOnly("usernameid");
	}
	
	/**
	 * Check if Password text field is read only
	 * @return
	 */
	public boolean isPasswordTextFieldReadOnly() {
		return isTextFieldReadOnly("passwordid");
	}
	
	public String getRoleLocationListValue() {
		return browser.getDropdownListValue(".id", "rolelocid");
	}
	
	/**
	 * Get the text displayed in the login form
	 * @return
	 */
	public String getLoginFormText() {
		return browser.getObjectText(".id", "loginForm");
	}
	
	/**
	 * Get all role/location selection dropdown list values
	 * @return
	 */
	public List<String> getAllRoleLocValues() {
		return browser.getDropdownElements(".id", "rolelocid");
	}
	
	/**
	 * Verify the role/location selection page UI
	 */
	public void verifyRoleLocSelectionPgUI(String userName, boolean isReadOnly, String text, String defaultValue) {
		boolean result = true;
		// Verify user name and password text fields
		result &= MiscFunctions.compareResult("user name readonly", isReadOnly, this.isUserNameTextFieldReadOnly());
		result &= MiscFunctions.compareResult("password readonly", isReadOnly, this.isPasswordTextFieldReadOnly());
		result &= MiscFunctions.compareString("user name", userName, this.getUserName());
		
		// Verify the text above role/location list
		String allText = this.getLoginFormText();
		if (!allText.contains(text)) {
			result &= false;
			logger.error("Role/Location text is wrong. Expect: " + allText + " should contain " + text);
		} 
		
		// Verify role/location list default value
		result &= MiscFunctions.compareString("role/location list default value", defaultValue, 
				this.getRoleLocationListValue());
		
		// Verify role/location list values sort order
		List<String> values = this.getAllRoleLocValues();
		result &= StringUtil.verifyStringListSortByAlphabetically(values);
		
		if (!result) {
			throw new ErrorOnPageException("role/location selection page UI is wrong! Check logger error!");
		}
		logger.info("Verify role/location selection page UI correctly");
	}	
	
	/**
	 * Compare top error message when select invalid role/location
	 */
	public boolean compareTopErrMessage(String expTopMsg) {
		String actTopMsg = this.getTopErrorMessage();	
		return MiscFunctions.compareString("Top Error Messsage", expTopMsg, actTopMsg);
	}
	
	/**
	 * Compare field error message when not select any role/location
	 */
	public boolean compareFieldErrMessage(String errFieldLabel, String expFieldMsg) {
		String actFieldMsg = this.getFieldErrorMessage(errFieldLabel);
		return MiscFunctions.compareString("Field Error Message", expFieldMsg, actFieldMsg);
	}
	
	/**
	 * Compare field error message exist
	 * @param expected
	 * @return
	 */
	public boolean compareFieldErrMsgExist(boolean expected) {
		String msg = expected ? " " : " not ";
		if (this.isFieldErrorMessageExist() != expected) {
			logger.error("Field Error Message should" + msg + "exist on the page!");
			return false;
		} else {
			logger.info("Field Error Message does" + msg + "exist on the page!");
			return true;
		}
	}
}
