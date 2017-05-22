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
 * @Date  Sep 27, 2012
 */
public class LicMgrDatePeriodLicenseYearsListPage extends LicMgrDatePeriodsDetailPage {
	
	private static LicMgrDatePeriodLicenseYearsListPage _instance = null;
	
	private LicMgrDatePeriodLicenseYearsListPage() {}
	
	public static LicMgrDatePeriodLicenseYearsListPage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrDatePeriodLicenseYearsListPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "licenseYearList");
	}
	
	private static final String ID_COL = "ID";
	private static final String STATUS_COL = "Status";
	private static final String LICENSE_YEAR_COL = "License Year";
	private static final String DATES_COL = "Dates";
	
	public void clickAddLicenseYear() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add License Year", true);
	}
	
	public boolean isAddLicenseYearButtonExist(){
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", "Add License Year");
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodSearchCriteria-\\d+\\.status", false), status);
	}
	
	public String getSelectStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression("DatePeriodSearchCriteria-\\d+\\.status", false));
	}
	
	public void selectLicenseYear(String licYear) {
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodSearchCriteria-\\d+\\.year", false), licYear);
	}
	
	public List<String> getAllLicenseYears() {
		return browser.getDropdownElements(".id", new RegularExpression("DatePeriodSearchCriteria-\\d+\\.year", false));
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	public boolean isLicenseYearIdEnable(String id){
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", id);
	}
	
	public void setSearchCriteria(String status, String licenseYear) {
		if(!StringUtil.isEmpty(status)) {
			selectStatus(status);
		}
		if(!StringUtil.isEmpty(licenseYear)) {
			selectLicenseYear(licenseYear);
		}
	}
	
	public void searchLicenseYear(String status, String licenseYear) {
		setSearchCriteria(status, licenseYear);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchLicenseYear(String licenseYear) {
		searchLicenseYear(null, licenseYear);
	}
	
	public String getLicenseYearItemInfo(String licenseYear, int infoItemColumNum){
		IHtmlObject objs[] = getLicenseYearTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(2, licenseYear);
		String info = null;
		if(rowIndex != -1) {
			info = table.getCellValue(rowIndex, infoItemColumNum);
		}
		Browser.unregister(objs);
		return info;
	}
	
	public String getLicenseYearID(String licenseYear) {
		return this.getLicenseYearItemInfo(licenseYear, 0);
	}
	
	public String getLicenseYearStatus(String licenseYear){
		return this.getLicenseYearItemInfo(licenseYear, 1);
	}
	
	private IHtmlObject[] getLicenseYearTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "licenseYearList");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Date Period License Year list table object.");
		}
		
		return objs;
	}
	
	/**
	 * get date period license year info identifier/search by license year
	 * @param licenseYear
	 * @return
	 */
	public DatePeriodLicenseYearInfo getDatePeriodLicenseYearInfo(String licenseYear) {
		//searchLicenseYear(licenseYear);
		
		IHtmlObject objs[] = getLicenseYearTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> rowValue = table.getRowValues(1);//it must be row 1
		
		DatePeriodLicenseYearInfo info = new DatePeriodLicenseYearInfo();
		info.setId(rowValue.get(table.findColumn(0, ID_COL)));
		info.setStatus(rowValue.get(table.findColumn(0, STATUS_COL)));
		info.setLicenseYear(rowValue.get(table.findColumn(0, LICENSE_YEAR_COL)));
		String datesText = rowValue.get(table.findColumn(0, DATES_COL));
		String temp[] = RegularExpression.getMatches(datesText, "([a-zA-Z]+\\:\\s)?[a-zA-Z]{3} [0-9]{2} - [a-zA-Z]{3} [0-9]{2}");
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		String dateSuffix = " " + info.getLicenseYear();
		for(int i = 0; i < temp.length; i ++) {
			Dates date = info.new Dates();
			if(temp[i].contains(":")) {
				date.setCategory(temp[i].split(":")[0].trim());
				date.setFromDate(temp[i].split(":")[1].split("-")[0].trim() + dateSuffix);
				date.setToDate(temp[i].split(":")[1].split("-")[1].trim() + dateSuffix);
			} else {
				date.setCategory(StringUtil.EMPTY);
				date.setFromDate(temp[i].split("-")[0].trim() + dateSuffix);
				date.setFromDate(temp[i].split("-")[1].trim() + dateSuffix);
			}
			dates.add(date);
		}
		info.setDates(dates);
		
		Browser.unregister(objs);
		
		return info;
	}
	
	private boolean isDatePeriodLicenseYearExists(String licenseYear) {
		IHtmlObject objs[] = getLicenseYearTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean exists = false;
		if(table.rowCount() == 2) {
			exists = true;
		}
		
		Browser.unregister(objs);
		return exists;
	}
	
	/**
	 * get all date period license years info search by all available license year options of 'License Year' drop down list
	 * @return
	 */
	public List<DatePeriodLicenseYearInfo> getAllDatePeriodLicenseYearInfo() {
		List<String> allLicenseYears = getAllLicenseYears();
		List<DatePeriodLicenseYearInfo> allDatePeriodLicenseYearInfo = new ArrayList<DatePeriodLicenseYearInfo>();
		
		for(String licYear : allLicenseYears) {
			searchLicenseYear(licYear);
			if(isDatePeriodLicenseYearExists(licYear)) {
				allDatePeriodLicenseYearInfo.add(getDatePeriodLicenseYearInfo(licYear));
			}
		}
		
		return allDatePeriodLicenseYearInfo;
	}
	
	public boolean compareDatePeriodLicenseYearInfo(DatePeriodLicenseYearInfo expected) {
		DatePeriodLicenseYearInfo actual = getDatePeriodLicenseYearInfo(expected.getLicenseYear());
		
		boolean result = true;
		result &= MiscFunctions.compareResult("License Year ID", expected.getId(), actual.getId());
		result &= MiscFunctions.compareResult("License Year Status", expected.getStatus(), actual.getStatus());
		result &= MiscFunctions.compareResult("License Year", expected.getLicenseYear(), actual.getLicenseYear());
		
		List<Dates> datesList = expected.getDates();
		for(int i = 0; i < datesList.size(); i ++) {
			result &= datesList.get(i).equals(actual.getDates().get(i));
		}
		
		return result;
	}
	
	public boolean compareAllDatePeriodLicenseYearInfo(List<DatePeriodLicenseYearInfo> expected) {
		boolean result = true;
		for(int i = 0; i < expected.size(); i ++) {
			searchLicenseYear(expected.get(i).getLicenseYear());
			result &= compareDatePeriodLicenseYearInfo(expected.get(i));
		}
		
		return result;
	}
	
	public List<String> getAllLicenseYearIds(){
		IHtmlObject objs[] = getLicenseYearTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> colValue = table.getColumnValues(0);
		colValue.remove(0);
		Browser.unregister(objs);
		return colValue;
	}
}
