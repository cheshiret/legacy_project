package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 28, 2012
 */
public abstract class LicMgrDatePeriodLicenseYearDetailsCommonWidget extends DialogWidget {
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "DateRange");
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.status", false)), status, true, getWidget()[0]);
	}
	
	public int getRemoveCount() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".text", "Remove");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find any 'Remove' button object.");
		}
		int count = objs.length;
		Browser.unregister(objs);
		
		return count;
	}
	
	public void clickAddDates() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Dates", false), true);
	}
	
	protected Property[] fromDateTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.fromDate_ForDisplay", false));
	}
	
	protected Property[] toDateTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.toDate_ForDisplay", false));
	}
	
	protected Property[] categoryTextField(){                       
		return Property.toPropertyArray(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.categoryView", false));
	}
	
	protected Property[] licenseYearDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.licenseYear", false));
	}
	
	/**
	 * 
	 * @param index
	 * @param fromDate
	 */
	public void setFromDate(int index, String fromDate) {
		browser.setTextField(fromDateTextField(), fromDate, true, index);
	}
	
	/**
	 * 
	 * @param index
	 * @param toDate
	 */
	public void setToDate(int index, String toDate) {
		browser.setTextField(toDateTextField(), toDate, index);
	}
	
	/**
	 * 
	 * @param index
	 * @param category
	 */
	public void selectCategory(int index, String category) {
		browser.selectDropdownList(categoryTextField(), category, index);
	}
	
	public boolean isFromDateTextFieldValid(String input[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(fromDateTextField())[0], input);
	}
	
	public boolean isToDateTextFieldValid(String input[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(toDateTextField())[0], input);
	}
	
	/**
	 * 
	 * @param index - object index, starts from 0
	 * @param dates
	 */
	public void setDates(int index, Dates dates) {
		setFromDate(index, dates.getFromDate());
		setToDate(index, dates.getToDate());
		this.moveFocus();
		selectCategory(index, dates.getCategory());
	}
	
	private void moveFocus(){
		browser.clickGuiObject(".class", "Dates", ".text", "Dates");
	}
	
	public void clickRemove(){
		this.clickButtonByText("Remove");
	}
	
	/**
	 * 
	 * @param dates
	 */
	public void setDates(List<Dates> dates) {
		int toRemove = getRemoveCount() - dates.size();
		for(int i=0; (i< toRemove)&&(getRemoveCount()>1); i++){
			this.clickRemove();
			ajax.waitLoading();
			this.waitLoading();
		}
		for(int i = 0; i < dates.size(); i ++) {
			if(getRemoveCount() - 1 < i) {
				clickAddDates();
				ajax.waitLoading();
			}
			setDates(i, dates.get(i));
		}
	}
	
	public String setDatesRangeAndGetErrorMsg(List<Dates> dates){
		this.setDates(dates);
		this.clickOK();
		this.waitLoading();
		String err = this.getErrorMsg();
		return err;
	}
	
	public void updateInfo(String status, List<Dates> dates){
		this.selectStatus(status);
		this.setDates(dates);
	}
	
	public void updateInfoAndClickOK(String status, List<Dates> dates){
		this.updateInfo(status, dates);
		this.clickOK();
	}
	
}
