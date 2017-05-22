package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
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
 * @Date  Sep 28, 2012
 */
public class LicMgrEditDatePeriodLicenseYearWidget extends LicMgrDatePeriodLicenseYearDetailsCommonWidget {
	
	private static LicMgrEditDatePeriodLicenseYearWidget _instance = null;
	
	private LicMgrEditDatePeriodLicenseYearWidget() {}
	
	public static LicMgrEditDatePeriodLicenseYearWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrEditDatePeriodLicenseYearWidget();
		}
		
		return _instance;
	}
	
	
	public String getID() {
		return getAttributeValueByName("ID");
	}
	
	public String getStatus() {           
		return browser.getDropdownListValue(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.status", false));
	}
	
	public boolean isTextFieldEditable(String fieldName){
		IHtmlObject[] topSpans = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^"+fieldName+".*",false)));
		if(topSpans.length < 1){
			throw new ItemNotFoundException("Can't find SPAN object by name - " + fieldName);
		}
		boolean editable = browser.checkHtmlObjectEnabled(Property.toPropertyArray(".class", "Html.INPUT.TEXT"), topSpans[0]);
		Browser.unregister(topSpans);
		return editable;
	}
	
	public boolean isLicenseYearDropDownListEnabled(){
		return browser.checkHtmlObjectEnabled(this.licenseYearDropDownList());
	}
	
	public boolean isDatePeriodLicenseYearIdEditable(){
		return this.isTextFieldEditable("ID");
	}
	
	public boolean isCreationDateTimeEditable(){
		return this.isTextFieldEditable("Creation Date/Time");
	}
	
	public boolean isCreationUserEditable(){
		return this.isTextFieldEditable("Creation User");
	}
	
	public String getLicenseYear() {
		return browser.getDropdownListValue(licenseYearDropDownList(), 0);
	}
	
	public String getFromDate(int objectIndex) {
		return browser.getTextFieldValue(fromDateTextField(), objectIndex);
	}
	
	public String getToDate(int objectIndex) {
		return browser.getTextFieldValue(toDateTextField(), objectIndex);
	}
	
	public String getCategory(int objectIndex) {
		return browser.getDropdownListValue(categoryTextField(), objectIndex);
	}
	
	private List<String> getCategoryEle(){
		return browser.getDropdownElements(this.categoryTextField());
	}
	public boolean isCategoryExist(String category){
		List<String> categories = this.getCategoryEle();
		boolean exist = categories.contains(category);
		return exist;
	}

	public void clickAddNewCategory() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add New", false), true);
	}
			
	private String getAttributeValueByName(String name) {
		IHtmlObject divObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", name), getWidget()[0]);
		if(divObjs.length < 1) {
			throw new ItemNotFoundException("Can't find SPAN object by name - " + name);
		}
		
		String value = browser.getTextFieldValue(Property.toPropertyArray(".className", "readonly"), divObjs[0]);
		Browser.unregister(divObjs);
		
		return value;
	}
	
	public String getCreationDateTime() {
		return getAttributeValueByName("Creation Date/Time");
	}
	
	public String getCreationUser() {
		return getAttributeValueByName("Creation User");//TODO DEFECT-37561 has been fixed
	}
	
	public String getLastModifiedDateTime() {
		return getAttributeValueByName("Last Modified Date/Time");
	}
	
	public String getLastModifiedUser() {
		return getAttributeValueByName("Last Modified User");
	}
	
	public int getDateRangeNum(){
		//get Dates info
		IHtmlObject objs[] = browser.getTableTestObject(".id", "DatePeriodRange_table");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowCount = table.rowCount();
		int num = rowCount -1;
		Browser.unregister(objs);
		return num;
	}
	
	public DatePeriodLicenseYearInfo getLicenseYearInfo() {
		DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
		
		licenseYear.setId(getID());
		licenseYear.setStatus(getStatus());
		licenseYear.setLicenseYear(getLicenseYear());
		
		int dateRangeNum = this.getDateRangeNum();
		List<Dates> datesList = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		for(int i = 0; i < dateRangeNum; i ++) {
			Dates dates = licenseYear.new Dates();
			dates.setFromDate(getFromDate(i));
			dates.setToDate(getToDate(i));
			dates.setCategory(getCategory(i));
			
			datesList.add(dates);
		}
		
		licenseYear.setDates(datesList);
		licenseYear.setCreationDateTime(getCreationDateTime());
		licenseYear.setCreationUser(getCreationUser());
		licenseYear.setLastModifiedDateTime(getLastModifiedDateTime());
		licenseYear.setLastModifiedUser(getLastModifiedUser());
		
		return licenseYear;
	}
	
	public boolean compareLicenseYearDetailsInfo(DatePeriodLicenseYearInfo expected) {
		DatePeriodLicenseYearInfo actual = getLicenseYearInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("License Year Status", expected.getStatus(), actual.getStatus());
		result &= MiscFunctions.compareResult("License Year", expected.getLicenseYear(), actual.getLicenseYear());
		
		List<Dates> datesList = expected.getDates();
		for(int i = 0; i < datesList.size(); i ++) {
			result &= datesList.get(i).equals(actual.getDates().get(i));
		}
		
		result &= MiscFunctions.compareResult("Creation Date/Time", expected.getCreationDateTime(), actual.getCreationDateTime());
		result &= MiscFunctions.compareResult("Creation User", expected.getCreationUser(), actual.getCreationUser().replace(" ", ""));//TODO DEFECT-37561
		if(!StringUtil.isEmpty(expected.getLastModifiedDateTime())) {
			result &= MiscFunctions.compareResult("Last Modified Date/Time", expected.getLastModifiedDateTime(), actual.getLastModifiedDateTime());
		}
		if(!StringUtil.isEmpty(expected.getLastModifiedUser())) {
			result &= MiscFunctions.compareResult("Last Modified User", expected.getLastModifiedUser(), actual.getLastModifiedUser());
		}
		
		return result;
	}
	
}
