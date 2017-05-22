/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 17, 2012
 */

public class LicMgrAddDatePeriodsPage extends LicMgrLotteriesCommonPage{
	private static LicMgrAddDatePeriodsPage _instance = null;
	
	protected LicMgrAddDatePeriodsPage (){}
	
	public static LicMgrAddDatePeriodsPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddDatePeriodsPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","AddDatePeriodTab");
	}
	
	public void setCode(String code)
	{
		browser.setTextField(".id", new RegularExpression("DatePeriodView-\\d+\\.code", false), code);
	}
	
	public void setDesc(String desc)
	{
		browser.setTextField(".id", new RegularExpression("DatePeriodView-\\d+\\.description", false), desc);
	}	

	public void selectLicenseYear(String year)
	{
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.licenseYear", false), year);
	}
	
	public void selectCategory(String cate, int idx)
	{
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.categoryView", false),cate, idx);
	}	
	
	public List<String> getAllCategory()
	{
		return browser.getDropdownElements(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.categoryView", false));
	}
	
	public void setFromDate(String date, int idx)
	{
		browser.setTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.fromDate_ForDisplay", false), date, idx);
	}	

	public void setEndDate(String date, int idx)
	{
		browser.setTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.toDate_ForDisplay", false), date, idx);
	}
	
	public void clickRemove(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", 0);
	}	
	
	public void clickAddDates(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Dates");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickAddNewCategory(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New", 0);
	}
	
	public String getErrorMsg(){
		String msg = "";
		IHtmlObject[] notSet = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(notSet.length>0)
		{
		 msg = notSet[0].text();
		}
		
		Browser.unregister(notSet);
		return msg; 
	}
	
	public boolean isFromDateTextFieldValid(String input[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.fromDate_ForDisplay", false))[0], input);
	}
	
	public boolean isToDateTextFieldValid(String input[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.toDate_ForDisplay", false))[0], input);
	}
	
	public List<String> getLicenseYearElements() {
		return browser.getDropdownElements(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.licenseYear", false));
	}
	
	public boolean verifyLicenseYearOption() {
		List<String> years = getLicenseYearElements();
		int expected = DateFunctions.getCurrentYear() - 1;//starts from [current year - 1]
		for(int i = 0; i < years.size(); i ++) {
			if(!years.get(i).equals(String.valueOf(expected))) {
				logger.error("License Year drop down list option is incorrect. Expected is: " + expected + ", but actual is: " + years.get(i));
				return false;
			}
			expected++;
		}
		
		logger.info("License Year drop down list option are all correct.");
		return true;
	}
}
