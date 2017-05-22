/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @ScriptName LicMgrPrivilegeEditLicenseYearWidget.java
 * @Date:Mar 9, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeEditLicenseYearWidget extends DialogWidget {

	private static LicMgrPrivilegeEditLicenseYearWidget instance = null;

	private LicMgrPrivilegeEditLicenseYearWidget() {
	}

	public static LicMgrPrivilegeEditLicenseYearWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeEditLicenseYearWidget();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", new RegularExpression("Edit (Privilege|Licence) Licen(s|c)e Yearclose", false));
		return flag1 && flag2;
	}

	public void selectStatus(String status) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.active", false);
		browser.selectDropdownList(".id", regx, status);
	}

	public void setSellFromDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
	}

	public void setSellFromTime(String time) {
		RegularExpression timeRegx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellFromDate_time", false);
		browser.setTextField(".id", timeRegx, time);
	}

	public void setSellToDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date);
	}

	public void setSellToTime(String time) {
		RegularExpression timeRegx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellToDate_time", false);
		browser.setTextField(".id", timeRegx, time);
	}

	public void setValidFromDate(String date) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",
				false);
		browser.setTextField(".id", regx, date,0,IText.Event.LOSEFOCUS);
	}

	public void setValidFromTime(String time) {
		RegularExpression timeRegx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validFromDate_time", false);
		browser.setTextField(".id", timeRegx, time);
	}

	public void setValidToDate(String date) {
		RegularExpression regx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",false);
		browser.setTextField(".id", regx, date,0,IText.Event.LOSEFOCUS);
	}

	public void setValidToTime(String time) {
		RegularExpression timeRegx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.validToDate_time", false);
		browser.setTextField(".id", timeRegx, time);
	}

	public void setLicenseYearInfo(LicenseYear ly) {

		if (ly.status.trim().length() > 0)
			this.selectStatus(ly.status);
//		if (ly.sellFromDate.trim().length() > 0)
			this.setSellFromDate(ly.sellFromDate);
			ajax.waitLoading();
		if (ly.sellFromTime.trim().length() > 0)
			this.setSellFromTime(ly.sellFromTime);		
		    ajax.waitLoading();
			this.selectSellFromTimeAmPm(ly.sellFromAmPm);
			ajax.waitLoading();
//		if (ly.sellToDate.trim().length() > 0)
			this.setSellToDate(ly.sellToDate);
			ajax.waitLoading();
		if (ly.sellToTime.trim().length() > 0)
			this.setSellToTime(ly.sellToTime);
		    ajax.waitLoading();
			this.selectSellToTimeAmPm(ly.sellToAmPm);
			ajax.waitLoading();
//		if (ly.validFromDate.trim().length() > 0)
			this.setValidFromDate(ly.validFromDate);
			ajax.waitLoading();
//		if (ly.validFromTime.trim().length() > 0)
			this.setValidFromTime(ly.validFromTime);
			ajax.waitLoading();
			this.selectValidFromTimeAmPm(ly.validFromAmPm);
			ajax.waitLoading();
//		if (ly.validToDate.trim().length() > 0)
			this.setValidToDate(ly.validToDate);
			ajax.waitLoading();
//		if (ly.validToTime.trim().length() > 0)
			this.setValidToTime(ly.validToTime);
			ajax.waitLoading();
			this.selectValidToTimeAmPm(ly.validToAmPm);
			ajax.waitLoading();
	}
	
	public String getPrivilegeLicenseYearId(){
		RegularExpression idRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.id", false);
		return browser.getTextFieldValue(".id", idRegx);
	}
	
	public String getPrivilegeLicenseYearStatus(){
		RegularExpression statusRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.active", false);
		return browser.getDropdownListValue(".id", statusRegx, 0);
	}
	
	public String getPrivilegeLocationClass(){
		RegularExpression locationRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.locationClassID",false);
		return browser.getDropdownListValue(".id", locationRegx, 0);
	}
	
	public String getLicenseYear(){
		RegularExpression yearRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.licenseYear",false);
		return browser.getDropdownListValue(".id", yearRegx, 0);
	}

	public String getSellFromDate(){
		RegularExpression dateRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay", false);
		return browser.getTextFieldValue(".id", dateRegx);
	}
	
	public String getSellFromTime(){
		RegularExpression timeRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_time", false);
		return browser.getTextFieldValue(".id", timeRegx);
	}
	
	public String getSellFromAmPm(){
		RegularExpression amPmRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellFromDate_ampm", false);
		return browser.getDropdownListValue(".id", amPmRegx, 0);
	}
	
	public String getSellToDate(){
		RegularExpression regx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.sellToDate_date_ForDisplay",false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public String getSellToTime(){
		RegularExpression timeRegx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellToDate_time", false);
		return browser.getTextFieldValue(".id", timeRegx);
	}
	
	public String getSellToAmPm(){
		RegularExpression amPmRegx = new RegularExpression(
				"PrivilegeLicenseYearView-\\d+\\.sellToDate_ampm", false);
		return browser.getDropdownListValue(".id", amPmRegx, 0);
	}
	
	public String getValidFromDate(){
		RegularExpression regx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay",false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public boolean isValidFromDateExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_date_ForDisplay", false));
	}
	
	public String getValidFromTime(){
		RegularExpression timeRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time", false);
		return browser.getTextFieldValue(".id", timeRegx);
	}
	
	public boolean isValidFromTimeExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_time", false));
	}
	
	public String getValidFromAmPm(){
		RegularExpression amPmRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_ampm", false);
		return browser.getDropdownListValue(".id", amPmRegx, 0);
	}
	
	public boolean isValidFromAmPmExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validFromDate_ampm", false));
	}
	
	public String getValidToDate(){
		RegularExpression regx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay",false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public boolean isValidToDateExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_date_ForDisplay", false));
	}
	
	public String getValidToTime(){
		RegularExpression timeRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time", false);
		return browser.getTextFieldValue(".id", timeRegx);
	}
	
	public boolean isValidToTimeExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_time", false));
	}
	
	public String getValidToAmPm(){
		RegularExpression ampmRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm", false);
		return browser.getDropdownListValue(".id", ampmRegx, 0);
	}
	
	public boolean isValidToAmPmExsit() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.validToDate_ampm", false));
	}
	
	public String getCreateUser(){
		RegularExpression userRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.createUser", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", userRegx);
		String user = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return user.split("Creation User")[1].trim();
	}
	
	public String getCreateLocation(){
		RegularExpression locationRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.createLocation", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", locationRegx);
		String user = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return user.split("Creation Location")[1].trim();
	}
	
	public String getCreateTime(){
		RegularExpression timeRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.createTime:LOCAL_TIME", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", timeRegx);
		String user = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return user.split("Creation Date/Time")[1].trim();
	}
	
	public String getLastUpdateUser(){
		RegularExpression lastUpdateUserRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.lastUpdateUser", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", lastUpdateUserRegx);
		String lable = "Last Updated User";
		String userText =  objs[0].getProperty(".text");
		String user = ""; 
		
		if (userText.trim().length() >lable.trim().length()){
			user = userText.split(lable)[1].trim();
		}
		Browser.unregister(objs);
		return user;
	}
	
	public String getLastUpdateLocation(){
		RegularExpression lastUpdateLocationRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.lastUpdateLocation", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", lastUpdateLocationRegx);
		String lable = "Last Updated Location";
		String locText = objs[0].getProperty(".text");
		String loc = "";
		
		if (locText.trim().length() >lable.trim().length()){
			loc = locText.split(lable)[1].trim();
		}
		Browser.unregister(objs);
		return loc;
	}
	
	public String getLastUpdateTime(){
		RegularExpression lastUpdateTimeRegx = new RegularExpression("PrivilegeLicenseYearView-\\d+\\.lastUpdateTime:LOCAL_TIME", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", lastUpdateTimeRegx);
		String lable = "Last Updated Date/Time";
		String updateTimeText = objs[0].getProperty(".text");
		String time = "";
		
		if (updateTimeText.trim().length() >lable.trim().length()){
			time = updateTimeText.split(lable)[1].trim();
		}
		Browser.unregister(objs);
		return time;
	}
	
	/**
	 * this function will verify the license Year details info(except ID) on Edit Privilege License Year page.
	 * @param expectLy
	 * @return
	 */
	public boolean compareLicenseYearRecord(LicenseYear expectedInfo) {
		LicenseYear actualInfo = this.getLicenseYearInfo();
		boolean result = true;
		
		logger.info("start verifing lincese Year info on Edit Privilege License Year Page!");
		if (!expectedInfo.status.equals(actualInfo.status)) {
			logger.error("Expected status is not'" + expectedInfo.status + ".");
			result &= false;
		}
		if (!expectedInfo.locationClass
				.equals(actualInfo.locationClass)) {
			logger.error("Expected locationClass is not'" + expectedInfo.locationClass + ".");
			result &= false;
		}
		if (!expectedInfo.licYear.equals(actualInfo.licYear)) {
			logger.error("Expected licYear is not'" + expectedInfo.licYear + ".");
			result &= false;
		}
		if (DateFunctions.compareDates(expectedInfo.sellFromDate,
				actualInfo.sellFromDate) != 0) {
			logger.error("Expected sellFromDate is not'" + expectedInfo.sellFromDate + ".");
			result &= false;
		}
		if (!(expectedInfo.sellFromTime.equals(actualInfo.sellFromTime))) {
			logger.error("Expected sellFromTime is not'" + expectedInfo.sellFromTime + ".");
			result &= false;
		}
		if (!(expectedInfo.sellFromAmPm.equals(actualInfo.sellFromAmPm))) {
			logger.error("Expected sellFromAmPm is not'" + expectedInfo.sellFromAmPm + ".");
			result &= false;
		}
		if (DateFunctions.compareDates(expectedInfo.sellToDate,
				actualInfo.sellToDate) != 0) {
			logger.error("Expected sellToDate is not'" + expectedInfo.sellToDate + ".");
			result &= false;
		}
		if (!(expectedInfo.sellToTime.equals(actualInfo.sellToTime))) {
			logger.error("Expected sellToTime is not'" + expectedInfo.sellToTime + ".");
			result &= false;
		}
		if (!(expectedInfo.sellToAmPm.equals(actualInfo.sellToAmPm))) {
			logger.error("Expected sellToTime is not'" + expectedInfo.sellToAmPm + ".");
			result &= false;
		}
		
		result &= MiscFunctions.compareResult("Valid From Date", expectedInfo.validFromDate, actualInfo.validFromDate);
		result &= MiscFunctions.compareResult("Valid From Time", expectedInfo.validFromTime, actualInfo.validFromTime);
		result &= MiscFunctions.compareResult("Valid From AmPm", expectedInfo.validFromAmPm, actualInfo.validFromAmPm);
		
		result &= MiscFunctions.compareResult("Valid To Date", expectedInfo.validToDate, actualInfo.validToDate);
		result &= MiscFunctions.compareResult("Valid To Time", expectedInfo.validToTime, actualInfo.validToTime);
		result &= MiscFunctions.compareResult("Valid To AmPm", expectedInfo.validToAmPm, actualInfo.validToAmPm);
		
		if (!(expectedInfo.createUser.replaceAll(" ", StringUtil.EMPTY).equals(actualInfo.createUser.replaceAll(" ", StringUtil.EMPTY)))) {
			logger.error("Expected createUser is not'" + expectedInfo.createUser + ".");
			result &= false;
		}
		
		if (!(expectedInfo.createLocation.equals(actualInfo.createLocation))) {
			logger.error("Expected createLocation is not'" + expectedInfo.createLocation + ".");
			result &= false;
		}
		if (!actualInfo.createTime.startsWith(DateFunctions.formatDate(expectedInfo.createTime, "E MMM dd yyyy"))) {
			logger.error("Expected createTime is not '" + expectedInfo.createTime + "'.");
			result &= false;
		}
		if(expectedInfo.lastUpdatedUser.length() > 0) {
			if (!(expectedInfo.lastUpdatedUser.replaceAll(" ", StringUtil.EMPTY).equals(actualInfo.lastUpdatedUser.replaceAll(" ", StringUtil.EMPTY)))) {
				logger.error("Expected lastUpdateUser is not'" + expectedInfo.lastUpdatedUser + ".");
				result &= false;
			}
			
			if (!(expectedInfo.lastUpdatedLocation.equals(actualInfo.lastUpdatedLocation))) {
				logger.error("Expected lastUpdateLocation is not'" + expectedInfo.lastUpdatedLocation + ".");
				result &= false;
			}
			
			if (!actualInfo.lastUpdatedTime.startsWith(DateFunctions.formatDate(expectedInfo.lastUpdatedTime, "E MMM dd yyyy"))) {
				logger.error("Expected lastUpdateTime is not '" + expectedInfo.lastUpdatedTime + "'.");
				result &= false;
			}
		}
		return result;
	}
	
	//test
	
	public LicenseYear getLicenseYearInfo(){
		LicenseYear ly = new LicenseYear();
		if(this.getPrivilegeLicenseYearId().trim().length()>0){
			ly.id = this.getLicenseYear();
		}
		if(this.getPrivilegeLicenseYearStatus().trim().length()>0){
			ly.status = this.getPrivilegeLicenseYearStatus();
		}
		if(this.getPrivilegeLocationClass().trim().length()>0){
			ly.locationClass = this.getPrivilegeLocationClass();
		}
		if(this.getLicenseYear().trim().length()>0){
			ly.licYear = this.getLicenseYear();
		}
		if(this.getSellFromDate().trim().length()>0){
			ly.sellFromDate = this.getSellFromDate();
		}
		if(this.getSellFromTime().trim().length()>0){
			ly.sellFromTime = this.getSellFromTime();
		}
		if(this.getSellFromAmPm().trim().length()>0){
			ly.sellFromAmPm = this.getSellFromAmPm();
		}
		if(this.getSellToDate().trim().length()>0){
			ly.sellToDate = this.getSellToDate();
		}
		if(this.getSellToTime().trim().length()>0){
			ly.sellToTime = this.getSellToTime();
		}
		if(this.getSellToAmPm().trim().length()>0){
			ly.sellToAmPm = this.getSellToAmPm();
		}
		if(this.getValidFromDate().trim().length()>0){
			ly.validFromDate = this.getValidFromDate();
		}
		if(this.getValidFromTime().trim().length()>0){
			ly.validFromTime = this.getValidFromTime();
		}
		if(this.getValidFromAmPm().trim().length()>0){
			ly.validFromAmPm = this.getValidFromAmPm();
		}
		if(this.getValidToDate().trim().length()>0){
			ly.validToDate = this.getValidToDate();
		}
		if(this.getValidToTime().trim().length()>0){
			ly.validToTime = this.getValidToTime();
		}
		if(this.getValidToAmPm().trim().length()>0){
			ly.validToAmPm = this.getValidToAmPm();
		}
		if(this.getCreateUser().trim().length()>0){
			ly.createUser = this.getCreateUser();
		}
		if(this.getCreateLocation().trim().length()>0){
			ly.createLocation = this.getCreateLocation();
		}
		if(this.getCreateTime().trim().length()>0){
			ly.createTime = this.getCreateTime();
		}
		if(this.getLastUpdateUser().trim().length()>0){
			ly.lastUpdatedUser = this.getLastUpdateUser();
		}
		if(this.getLastUpdateLocation().trim().length()>0) {
			ly.lastUpdatedLocation = this.getLastUpdateLocation();
		}
		if(this.getLastUpdateTime().trim().length()>0){
			ly.lastUpdatedTime = this.getLastUpdateTime();
		}
		return ly;
	}
	
	public void selectSellFromTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.sellFromDate_ampm",false), amOrPm);
	}
	
	public void selectSellToTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.sellToDate_ampm",false), amOrPm);
	}
	
	public void selectValidFromTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.validFromDate_ampm",false), amOrPm);
	}
	
	public void selectValidToTimeAmPm(String amOrPm){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeLicenseYearView-\\d+.validToDate_ampm",false), amOrPm);
	}
	
	public String getWaringMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		String errorMsg = objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return errorMsg;
	}
	
	public void verifyErrorMsg(String expectedValue){
		if(!this.getWaringMsg().equalsIgnoreCase(expectedValue)){
			throw new ErrorOnPageException("The exception message '"+ expectedValue + "' not found ~!");
		}
	}
	/*
	 * click the space.
	 */
	public void clickSpace(){
		browser.clickGuiObject(".class", "Html.DIV", ".id", new RegularExpression("PrivilegeLicenseYearView-\\d+\\.createTime:LOCAL_TIME",false));
	}
}
