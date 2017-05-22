/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.KeyInput;
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
public class LicMgrPrivilegeEditTextDisplayWidget extends DialogWidget {
	private static LicMgrPrivilegeEditTextDisplayWidget instance = null;

	private LicMgrPrivilegeEditTextDisplayWidget() {
		super("Edit Product Text Display");
	}

	public static LicMgrPrivilegeEditTextDisplayWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeEditTextDisplayWidget();
		}
		return instance;
	}

	public String getErrorMessage() {
		String error = browser.getObjectText(".class", "Html.DIV", ".id",
				"NOTSET");
		return error;
	}

	public String getId() {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.id:ZERO_TO_NEW", false);
		String id = browser.getTextFieldValue(".id", regx, 0);
		return id;
	}

	public void selectStatus(String status) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.status", false);
		browser.selectDropdownList(".id", regx, status);
	}

	public String getStatus() {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.status", false);
		String status = browser.getDropdownListValue(".id", regx, 0);
		return status;
	}

	public void selectDisplayType(String type) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.displayType", false);
		browser.selectDropdownList(".id", regx, type);
	}

	public String getDisplayType() {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.displayType", false);
		String type = browser.getDropdownListValue(".id", regx, 0);
		return type;
	}

	public void setText(String text) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeTextDisplayView|ProductTextDisplayView-\\d+\\.text", false);
		browser.setTextArea(".id", regx, text);
	}

	public String getText() {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.text", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", regx);
		String text = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return text;
	}

	public void setEffectiveFromDate(String date) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.effectiveFrom_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
		browser.inputKey(KeyInput.get(KeyInput.TAB));
	}

	public String getEffectiveFromDate() {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.effectiveFrom_ForDisplay",
				false);
		String date = browser.getTextFieldValue(".id", regx);
		return date;
	}

	public void setEffectiveToDate(String date) {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.effectiveTo_ForDisplay", false);
		browser.setTextField(".id", regx, date);
		browser.inputKey(KeyInput.get(KeyInput.TAB));
	}

	public String getEffectiveToDate() {
		RegularExpression regx = new RegularExpression(
				"ProductTextDisplayView-\\d+\\.effectiveTo_ForDisplay", false);
		String date = browser.getTextFieldValue(".id", regx);
		return date;
	}

	public String getCreationUser() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(
						"ProductTextDisplayView-\\d+\\.userCreator.fullName",
						false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Creation User div object.");
		}
		String toReturn = objs[0].getProperty(".text").split("Creation User")[1].trim();

		return toReturn;
	}

	public String getCreationLocation() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id",
				new RegularExpression(
						"ProductTextDisplayView-\\d+\\.locationCreated.name",
						false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Creation Location div object.");
		}
		String toReturn = objs[0].getProperty(".text").split(
				"Creation Location")[1].trim();

		return toReturn;
	}

	public String getCreationDateTime() {
		IHtmlObject objs[] = browser
				.getHtmlObject(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"ProductTextDisplayView-\\d+\\.createDate:LOCAL_TIME",
								false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Creation Date/Time div object.");
		}
		String toReturn = objs[0].getProperty(".text").split(
				"Creation Date/Time")[1].trim();

		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getLastUpdatedUser() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("ProductTextDisplayView-\\d+\\.userLastUpdated.fullName", false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Last Updated User div object.");
		}
		String toReturn = objs[0].getProperty(".text").split("Last Updated User")[1].trim();
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getLastUpdatedLocation() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("ProductTextDisplayView-\\d+\\.locationLastUpdated.name", false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Last Updated Location div object.");
		}
		String toReturn = objs[0].getProperty(".text").split("Last Updated Location")[1].trim();
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getLastUpdatedDateTime() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("ProductTextDisplayView-\\d+\\.lastUpdated:LOCAL_TIME", false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Last Updated Date/Time div object.");
		}
		String toReturn = objs[0].getProperty(".text").split("Last Updated Date/Time")[1].trim();
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public PrivilegeTextDisplay getTextDisplayInfo() {
		PrivilegeTextDisplay info = new PrivilegeTextDisplay();

		info.id = this.getId();
		info.status = this.getStatus();
		info.displayType = this.getDisplayType();
		info.text = this.getText();
		info.effectiveFromDate = this.getEffectiveFromDate();
		info.effectiveToDate = this.getEffectiveToDate();
		info.creationUser = this.getCreationUser();
		info.creationLocation = this.getCreationLocation();
		info.creationDateTime = this.getCreationDateTime();

		return info;
	}
	public boolean compareTextDisplayDetailInfo(PrivilegeTextDisplay info) {
		boolean result = true;
		String temp = this.getId();
		if (!info.id.equals(temp)) {
			result &= false;
			logger.error("Expected ID is: " + info.id + ", but actual ID is: " + temp);
		}
		temp = this.getStatus();
		if (!info.status.equals(temp)) {
			result &= false;
			logger.error("Expected Status is: " + info.status + ", but actual Status is: " + temp);
		}
		temp = this.getDisplayType();
		if (!info.displayType.equals(temp)) {
			result &= false;
			logger.error("Expected Display Type is: " + info.displayType + ", but actual Display Type is: " + temp);
		}
		temp = this.getText();
		if (!info.text.equals(temp)) {
			result &= false;
			logger.error("Expected Display Text is: " + info.text + ", but actual Display Text is: " + temp);
		}
		temp = this.getEffectiveFromDate();
		if (!DateFunctions.formatDate(info.effectiveFromDate, "E MMM d yyyy")
				.equals(temp)) {
			result &= false;
			logger.error("Expected Effective From Date is: " + info.effectiveFromDate + ", but actual Effective From Date is: " + temp);
		}
		temp = this.getEffectiveToDate();
		if(info.effectiveToDate.length() > 0) {
			if (!DateFunctions.formatDate(info.effectiveToDate, "E MMM d yyyy")
					.equals(temp)) {
				result &= false;
				logger.error("Expected Effective To Date is: " + info.effectiveToDate + ", but actual Effective To Date is: " + temp);
			}
		}
		temp = this.getCreationUser().replace(" ", ""); 
		if (!info.creationUser.replace(" ", "").equals(temp)) {
			result &= false;
			logger.error("Expected Creation User is: " + info.creationUser + ", but actual Creation User is: " + temp);
		}
		temp = this.getCreationLocation();
		if (!info.creationLocation.equals(temp)) {
			result &= false;
			logger.error("Expected Creation Location is: " + info.creationLocation + ", but actual Creation Location is: " + temp);
		}
		temp = this.getCreationDateTime();
		if (info.creationDateTime != "") {//added by Peter Zhu, if we do not define the creationDateTime, we won`t compare that.
			if (!temp.contains(DateFunctions.formatDate(info.creationDateTime,
					"E MMM dd yyyy"))) {
				result &= false;
				logger.error("Expected Creation Date Time is: "
						+ info.creationDateTime
						+ ", but actual Creation Date Time is: " + temp);
			}
		}
		
		if(info.status.equals(OrmsConstants.INACTIVE_STATUS)) {
			temp = this.getLastUpdatedUser().replace(" ", "");
			if (!info.lastUpdatedUser.replace(" ", "").equals(temp)) {
				result &= false;
				logger.error("Expected Last Updated User is: " + info.lastUpdatedUser + ", but actual Last Updated User is: " + temp);
			}
			temp = this.getLastUpdatedLocation();
			if (!info.lastUpdatedLocation.equals(temp)) {
				result &= false;
				logger.error("Expected Last Updated Location is: " + info.lastUpdatedLocation + ", but actual Last Updated Location is: " + temp);
			}
			temp = this.getLastUpdatedDateTime();
			
			if (info.lastUpdatedDateTime != "") {//added by Peter Zhu, if we do not define the lastUpdatedDateTime, we won`t compare that.
				if (!temp.contains(DateFunctions.formatDate(
						info.lastUpdatedDateTime, "E MMM dd yyyy"))) {
					result &= false;
					logger
							.error("Expected Last Updated Date Time is: "
									+ info.lastUpdatedDateTime
									+ ", but actual Last Updated Date Time is: "
									+ temp);
				}
			}
		}
		return result;
	}

}
