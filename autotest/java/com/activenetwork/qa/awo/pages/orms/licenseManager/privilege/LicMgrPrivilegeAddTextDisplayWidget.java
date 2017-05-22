/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Jun 2, 2011
 */
public class LicMgrPrivilegeAddTextDisplayWidget extends DialogWidget {
	private static LicMgrPrivilegeAddTextDisplayWidget instance = null;

	private LicMgrPrivilegeAddTextDisplayWidget() {
		super("Add Product Text Display");
	}

	public static LicMgrPrivilegeAddTextDisplayWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeAddTextDisplayWidget();
		}
		return instance;
	}

	public void selectDisplayType(String type) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.displayType", false);
		browser.selectDropdownList(".id", regx, type);
	}

	public void setText(String text) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.text", false);
		browser.setTextArea(".id", regx, text);
	}

	public void setEffectiveFromDate(String date) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.effectiveFrom_ForDisplay",
				false);
//		browser.setTextField(".id", regx, date);
		setDateAndGetMessage((IText)browser.getTextField(".id", regx)[0], date);
	}

	public void setEffectiveToDate(String date) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.effectiveTo_ForDisplay", false);
//		browser.setTextField(".id", regx, date);
		setDateAndGetMessage((IText)browser.getTextField(".id", regx)[0], date);
	}

	/**
	 * Check whether the ID text field is editable or not
	 * @return
	 */
	public boolean checkIDFieldEditable() {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.id:ZERO_TO_NEW", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find ID text field object.");
		}
		boolean toReturn = !Boolean.valueOf(objs[0].getProperty("isDisabled").trim());
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Check whether the Status drop down list is editable or not
	 * @return
	 */
	public boolean checkStatusFieldEditable() {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.status", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Status drop down list object.");
		}
		boolean toReturn = !Boolean.valueOf(objs[0].getProperty("isDisabled").trim());
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getStatus() {
		return browser.getDropdownListValue(".id", new RegularExpression("PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.status", false), 0);
	}
	
	/**
	 * Get the all options of display type
	 * @return
	 */
	public List<String> getDisplayTypeElements() {
		return browser.getDropdownElements(".id", new RegularExpression("PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.displayType", false));
	}
	
	/**
	 * Get the the error message
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "_dialog_messages_Dialog001");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find error message table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		String msg = table.getProperty(".text").trim();
		
		return msg;
	}
	
	public String getEffectiveFromDateValue() {
		return browser.getTextFieldValue(".id", new RegularExpression("PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.effectiveFrom_ForDisplay", false));
	}
	
	public String getEffectiveToDateValue() {
		return browser.getTextFieldValue(".id", new RegularExpression("PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.effectiveTo_ForDisplay", false));
	}
	
	/**
	 * Set up detail text display information
	 * @param textDisplay
	 */
	public void setupTextDisplayInfo(PrivilegeTextDisplay textDisplay) {
		this.selectDisplayType(textDisplay.displayType);
		this.setEffectiveFromDate(textDisplay.effectiveFromDate);
		this.setEffectiveToDate(textDisplay.effectiveToDate);
	    this.clickTextArea(); // for close date picker
		this.setText(textDisplay.text);
	}
	private void clickTextArea() {
		RegularExpression regx = new RegularExpression(
				"PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.text", false);
		browser.clickGuiObject(".id", regx);
	}
}
