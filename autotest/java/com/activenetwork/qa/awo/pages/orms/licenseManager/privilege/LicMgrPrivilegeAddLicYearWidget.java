/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrPrivilegeAddLicYearWidget.java
 * @Date:Mar 9, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeAddLicYearWidget extends DialogWidget {
	
	private static LicMgrPrivilegeAddLicYearWidget instance=null;
	
	private LicMgrPrivilegeAddLicYearWidget(){}
	
	public static LicMgrPrivilegeAddLicYearWidget getInstance(){
		if(instance==null){
			instance=new LicMgrPrivilegeAddLicYearWidget();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(".class", "Html.DIV",".text", new RegularExpression("Add (Privilege|Licence) Licen(s|c)e Year(close)?", false)); //"Add Privilege License Yearclose");
		return flag1&&flag2;
	}
	
	/**
	 * @return error message on the top of the widget
	 */
	public String getErrorMsg(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id","NOTSET",this.getWidget()[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cant get Error Message Object");
		}
		String msg=objs[0].text();
		return msg;
	}
	
	/**
	 * 
	 * @return default privilege license year status
	 */
	public String getDefaultStatus(){
		return browser.getDropdownListValue(".id","codebaseDropdown", 0);
	}
	
	/**
	 * 
	 * @return privilege license year status is un-edit or not
	 */
	public boolean checkStatusUnEditable(){
		IBrowser browser = Browser.getInstance();
		boolean ischecked = false;
		IHtmlObject[] object = browser.getHtmlObject(".id","codebaseDropdown");
		String value = object[0].getProperty(".disabled");
		if(value.equals("true")){
			ischecked = true;
		}
		return ischecked;
	}
	
	public void selectLocationClass(String locationClass){
		RegularExpression regx=new RegularExpression("PrivilegeLicenseYearView-\\d+\\.locationClassID",false);
		browser.selectDropdownList(".id", regx, locationClass);
	}
	
	public String getSelectedLocationClass(){
		return browser.getDropdownListValue(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.locationClassID",false), 0);
	}
	
	public List<String> getAllLocationClass(){
		return browser.getDropdownElements(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.locationClassID",false));
	}
	
	public void selectLicenseYear(String year){
		RegularExpression regx=new RegularExpression("PrivilegeLicenseYearView-\\d+\\.licenseYear",false);
	    browser.selectDropdownList(".id", regx, year);
	}
	
	public List<String> getAllLicenseYear(){
		return browser.getDropdownElements(".id",new RegularExpression("PrivilegeLicenseYearView-\\d+\\.licenseYear",false));
	}
	
	public void setSellFromDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
	}

	public void setSellFromTime(String time) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellFromDate_time", false);
		browser.setTextField(".id", regx, time);
	}
	
	public void selectSellFromTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.sellFromDate_ampm",false), amOrPm);
	}

	public void setSellToDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
	}

	public void setSellToTime(String time) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellToDate_time", false);
		browser.setTextField(".id", regx, time);
	}

	public void selectSellToTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.sellToDate_ampm",false), amOrPm);
	}
	
	public boolean isValidFromDateExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay", false));
	}
	
	public boolean isValidToDateExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay", false));
	}
	
	public void setValidFromDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
	}

	public void setValidFromTime(String time) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validFromDate_time", false);
		browser.setTextField(".id", regx, time);
	}

	public boolean isValidFromTimeExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time", false));
	}
	
	public void selectValidFromTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.validFromDate_ampm",false), amOrPm);
	}
	
	public boolean isValidFromAmPmExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.validFromDate_ampm", false));
	}
	
	public void setValidToDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
	}

	public boolean isValidToTimeExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time", false));
	}
	
	public void setVaildToTime(String time) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validToDate_time", false);
		browser.setTextField(".id", regx, time);
	}

	public void selectValidToTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.validToDate_ampm",false), amOrPm);
	}
	
	public boolean isValidToAmPmExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.validToDate_ampm", false));
	}
	
	public void setLicenseYearInfo(LicenseYear ly) {
//		this.selectLocationClass(ly.locationClass);// Lesley[20140319]: select location class finally to close the date picker, otherwise it will affect to click Cancel button
		this.selectLicenseYear(ly.licYear);
		this.setSellFromDate(ly.sellFromDate);
		ajax.waitLoading();
		// those if clause blocks cases of verify error message
//		if(ly.sellFromTime.length() > 0 ) {
			this.setSellFromTime(ly.sellFromTime);
			ajax.waitLoading();
//		}
		this.selectSellFromTimeAmPm(ly.sellFromAmPm);
		ajax.waitLoading();
		this.setSellToDate(ly.sellToDate);
		ajax.waitLoading();
//		if(ly.sellToTime.length() > 0) {
			this.setSellToTime(ly.sellToTime);
			ajax.waitLoading();
//		}
		this.selectSellToTimeAmPm(ly.sellToAmPm);
		ajax.waitLoading();
		if (this.isValidFromDateExsit()) { // Lesley[20130829]: the valid from and to dates fields are not shown when the privilege product group is Inventory
			this.setValidFromDate(ly.validFromDate);
			ajax.waitLoading();
			this.setValidFromTime(ly.validFromTime);
			ajax.waitLoading();
			this.selectValidFromTimeAmPm(ly.validFromAmPm);
			ajax.waitLoading();
			this.setValidToDate(ly.validToDate);
			ajax.waitLoading();
			this.setVaildToTime(ly.validToTime);
			ajax.waitLoading();
			this.selectValidToTimeAmPm(ly.validToAmPm);
			ajax.waitLoading();
		}
		this.selectLocationClass(ly.locationClass); // Lesley[20140319]: select location class finally to close the date picker, otherwise it will affect to click Cancel button
	}
	
	
 
}
