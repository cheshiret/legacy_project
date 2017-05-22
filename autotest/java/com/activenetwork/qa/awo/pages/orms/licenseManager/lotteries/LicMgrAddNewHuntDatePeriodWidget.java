package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the widget that used to add a new hunt quota in license manager, Admin(drop down list)-->Lotteries --- >
 *  Hunts --<click>-->Add Hunt  --<select>-->specie --->CLICK Add New Date Period
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrAddNewHuntDatePeriodWidget extends DialogWidget{
	private static LicMgrAddNewHuntDatePeriodWidget _instance = null;
	
	private String prefix = "DatePeriodView-\\d+\\.";
	protected LicMgrAddNewHuntDatePeriodWidget (){}
	
	public static LicMgrAddNewHuntDatePeriodWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddNewHuntDatePeriodWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"AddDatePeriodTab");
	}
	
	/**
	 * Set up code
	 * @param code
	 */
	public void setCode(String code){
		browser.setTextField(".id", new RegularExpression(prefix+"code", false), code);
	}

	/**
	 * Set description
	 * @param description
	 */
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression(prefix+"description", false), description);
	}
	/**
	 * Select license year from drop down list
	 * @param licenseYear
	 */
	public void selectLicenseYear(String licenseYear){
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.licenseYear", false), licenseYear);
	}

	/**
	 * Set from Date
	 * @param fromDate
	 */
	public void setFromDate(String fromDate, int index){
		browser.setTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.fromDate_ForDisplay", false), fromDate,
				true, index);
	}
	/**
	 * Set to Date
	 * @param toDate
	 */
	public void setToDate(String toDate, int index){
		browser.setTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.toDate_ForDisplay", false), toDate,
				true, index);
	}
	/**
	 * Set category
	 * @param category
	 */
	public void selectCategory(String category, int index){
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.categoryView", false), category, index);
	}
	
	/**
	 * Click 'Add New' link
	 */
	public void clickAddNew(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New", true, index);
	}
	
	/**
	 * Click remove button
	 * @param index
	 */
	public void clickRemove(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, index);
	}
	
	/**
	 * Click Add Date
	 * @param index
	 */
	public void clickAddDate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Dates");
		ajax.waitLoading();
	}
	/**
	 * Click ok button
	 */
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", 1);
	}
	/**
	 * Click cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", 1);
	}
	/**
	 * Set up date period info
	 * @param datePeriod
	 */
	public void setUpDatePeriodInfo(DatePeriodInfo datePeriod){
		if(StringUtil.notEmpty(datePeriod.getCode())){
			this.setCode(datePeriod.getCode());
		}
		if(StringUtil.notEmpty(datePeriod.getDescription())){
			this.setDescription(datePeriod.getDescription());
		}
		if(StringUtil.notEmpty(datePeriod.getLicenseYears().get(0).getLicenseYear())){
			this.selectLicenseYear(datePeriod.getLicenseYears().get(0).getLicenseYear());
		}
		List<Dates> datePeroidList = datePeriod.getLicenseYears().get(0).getDates();
		for(int i=0; i < datePeroidList.size(); i++){
			if(i != 0){
				this.clickAddDate();
			}
			if(StringUtil.notEmpty(datePeroidList.get(i).getFromDate())){
				this.setFromDate(datePeroidList.get(i).getFromDate(), i);
			}
			if(StringUtil.notEmpty(datePeroidList.get(i).getToDate())){
				this.setToDate(datePeroidList.get(i).getToDate(), i);
			}
			if(StringUtil.notEmpty(datePeroidList.get(i).getCategory())){
				this.selectCategory(datePeroidList.get(i).getCategory(), i);
			}
		}
	}
	/**
	 * Add a new date period
	 * @param datePeriod
	 */
	public void addDatePeriod(DatePeriodInfo datePeriod){
		this.setUpDatePeriodInfo(datePeriod);
		this.clickOK();
	}
}
