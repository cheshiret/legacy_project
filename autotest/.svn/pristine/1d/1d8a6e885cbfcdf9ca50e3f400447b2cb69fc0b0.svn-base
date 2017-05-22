package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * ScriptName: LicMgrAddEditPOSFiscalYearWidget
 * Description:
 * @date: Mar 22, 2011
 * @author qchen
 *
 */
public class LicMgrAddEditPOSFiscalYearWidget extends DialogWidget {
	public static LicMgrAddEditPOSFiscalYearWidget _instance = null;
	
	protected LicMgrAddEditPOSFiscalYearWidget() {}
	
	public static LicMgrAddEditPOSFiscalYearWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrAddEditPOSFiscalYearWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("[Add|Edit] POS Fiscal Year", false));
	}
	
	/**
	 * Get the fiscal year record id
	 * @return
	 */
	public String getFiscalYearID() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.id", false));
	}
	
	/**
	 * Get the status of current fiscal year
	 * @return
	 */
	public String getFiscalYearStatus() {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.active", false));
		
		String status = "";
		if(objs.length > 0) {
			status = ((ISelect)objs[0]).getSelectedText();
		}
		return status;
	}
	
	/**
	 * Select the option of fiscal year status drop down list
	 * @param status
	 */
	public void setFiscalYearStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.active", false), status);
	}
	
	/**
	 * Select the option of fiscal year location class drop down list
	 * @param locationClass
	 */
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.locationClassID", false), locationClass);
	}
	
	/**
	 * Select the option of fiscal year drop down list
	 * @param fiscalYear
	 */
	public void selectFiscalYear(String fiscalYear) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.fiscalYear", false), fiscalYear);
	}
	
	/**
	 * Set the sell from date
	 * @param fromDate
	 */
	public void setSellFromDate(String fromDate) {
		browser.setTextField(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellFromDateDatePart_ForDisplay", false), fromDate);
	}
	
	/**
	 * Set the sell from time
	 * @param fromTime
	 */
	public void setSellFromTime(String fromTime) {
		browser.setTextField(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellFromDateTimePart", false), fromTime);
	}
	
	/**
	 * Select the sell from time format - AM/FM
	 * @param amOrFm
	 */
	public void selectSellFromTimeFormat(String amOrFm) {
		if(null != amOrFm && amOrFm.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellFromDateAMPM", false), amOrFm);
		}
	}
	
	/**
	 * Set the sell to date
	 * @param toDate
	 */
	public void setSellToDate(String toDate) {
		browser.setTextField(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellToDateDatePart_ForDisplay", false), toDate);
	}
	
	/**
	 * Set the sell to time
	 * @param toTime
	 */
	public void setSellToTime(String toTime) {
		browser.setTextField(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellToDateTimePart", false), toTime);
	}
	
	/**
	 * Select the to time format
	 * @param fmOrAm
	 */
	public void selectToTimeFormat(String fmOrAm) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellToDateAMPM", false), fmOrAm, true);
	}
	
	/**
	 * Get the location class value
	 * @return
	 */
	public String getLocationClass() {
		IHtmlObject objs[] =  browser.getDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.locationClassID", false));
		String locationClass = "";
		if(objs.length > 0) {
			locationClass = ((ISelect)objs[0]).getSelectedText();
		}
		
		return locationClass;
	}
	
	/**
	 * Get the fiscal year value
	 * @return
	 */
	public String getFiscalYear() {
		IHtmlObject objs[] =  browser.getDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.fiscalYear", false));
		String fiscalYear = "";
		if(objs.length > 0) {
			fiscalYear = ((ISelect)objs[0]).getSelectedText();
		}
		
		return fiscalYear;
	}
	
	/**
	 * Get sell from date value
	 * @return
	 */
	public String getSellFromDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellFromDateDatePart_ForDisplay", false));
	}
	
	/**
	 * Get sell from time value
	 * @return
	 */
	public String getSellFromTime() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellFromDateTimePart", false));
	}
	
	/**
	 * Get sell from time format - am/pm
	 * @return
	 */
	public String getSellFromTimeFormat() {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellFromDateAMPM", false));
		String format = "";
		if(objs.length > 0) {
			format = ((ISelect)objs[0]).getSelectedText();
		}
		
		return format;
	}
	
	/**
	 * Get sell to date value
	 * @return
	 */
	public String getSellToDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellToDateDatePart_ForDisplay", false));
	}
	
	/**
	 * Get sell to time value
	 * @return
	 */
	public String getSellToTime() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellToDateTimePart", false));
	}
	
	/**
	 * Get sell to time format - am/pm
	 * @return
	 */
	public String getSellToTimeFormat() {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.sellToDateAMPM", false));
		String format = "";
		
		if(objs.length > 0) {
			format = ((ISelect)objs[0]).getSelectedText();
		}
		
		return format;
	}
	
	/**
	 * Get the create user name
	 * @return
	 */
	public String getCreateUser() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.createUser", false));
	}
	
	/**
	 * Get the create location
	 * @return
	 */
	public String getCreateLocation() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.createLocation", false));
	}
	
	/**
	 * Get the create time
	 * @return
	 */
	public String getCreateTime() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.createTime_ForDisplay", false));
	}
	
	/**
	 * Get the last update user name
	 * @return
	 */
	public String getLastUpdateUser() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.lastUpdateUser", false));
	}
	
	/**
	 * Get the last update location
	 * @return
	 */
	public String getLastUpdateLocation() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.lastUpdateLocation", false));
	}
	
	/**
	 * Get the last update time
	 * @return
	 */
	public String getLastUpdateTime() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductFiscalYearView-\\d+\\.lastUpdateTime_ForDisplay", false));
	}
}
