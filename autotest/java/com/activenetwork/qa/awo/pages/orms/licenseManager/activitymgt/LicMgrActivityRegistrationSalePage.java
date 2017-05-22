package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrActivityRegistrationSalePage extends LicMgrCommonTopMenuPage{
private static LicMgrActivityRegistrationSalePage _instance = null;
	
	protected LicMgrActivityRegistrationSalePage() {}
	
	public static LicMgrActivityRegistrationSalePage getInstance() {
		if(_instance == null) _instance = new LicMgrActivityRegistrationSalePage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "ActivityAvailabilitySearchUI");
	}
	
	private String prefix = "ActivityAvailabilitySearchCriteria-\\d+\\.";
	
	/** Page Object Property Definition Begin */
	protected Property[] activityNameTextField(){
		return Property.concatPropertyArray(table(), ".id", new RegularExpression(prefix + "activityName", false));
	}
	
	protected Property[] countyNameDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "county", false));
	}

	protected Property[] cityNameTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "cityName", false));
	}
	
	protected Property[] facilityTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "facilityID:ZERO_TO_NULL", false));
	}

	protected Property[] activityStartDateTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "activityStartDate_ForDisplay", false));
	}
	
	protected Property[] showAvailableRecordsOnlyCheckBox(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix + "showAvailableOnly", false));
	}
	
	protected Property[] searchBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Search", false));
	}
	
	protected Property[] resultTableObject(){
		return Property.toPropertyArray(".id", "ResultGrid");
	}
	
	protected Property[] cancelBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Cancel", false));
	}
	
	public void setActivityName(String activityName){
		browser.setTextField(this.activityNameTextField(), activityName);
	}
	
	public void selectCountyName(String county){
		if(StringUtil.isEmpty(county)){
			browser.selectDropdownList(this.countyNameDropDownList(), 0);
		}else{
			browser.selectDropdownList(this.countyNameDropDownList(), county);
		}
	}
	
	public void setCityName(String cityName){
		browser.setTextField(this.cityNameTextField(), cityName);
	}
	
	public void setFacilityName(String facilityName){
		browser.setTextField(this.cityNameTextField(), facilityName);
	}
	
	public void setActivityStartDate(String startDate){
		browser.setTextField(this.activityStartDateTextField(), startDate);
	}
	
	public void checkShowAvailableRecordsOnly(){
		browser.selectCheckBox(this.showAvailableRecordsOnlyCheckBox());
	}
	
	public void uncheckShowAvailableRecordsOnly(){
		browser.unSelectCheckBox(this.showAvailableRecordsOnlyCheckBox());
	}
	
	public void clickSearch(){
		IHtmlObject[] objs = browser.getHtmlObject(this.searchBtn());
		browser.clickGuiObject(this.searchBtn(), objs.length-1);
		Browser.unregister(objs);
	}

	public void clearSearchCriterial(){
		this.setActivityName(StringUtil.EMPTY);
		this.selectCountyName(StringUtil.EMPTY);
		this.setCityName(StringUtil.EMPTY);
		this.setFacilityName(StringUtil.EMPTY);
		this.setActivityStartDate(StringUtil.EMPTY);
		this.uncheckShowAvailableRecordsOnly();
		this.moveFocusOutOfDateComponent();
	}
	
	public void searchAvailableActivityByName(String activityName){
		clearSearchCriterial();
		this.setActivityName(activityName);
		this.checkShowAvailableRecordsOnly();
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickAddToCart(String activityInfo){
		IHtmlObject[] objs = browser.getTableTestObject(resultTableObject());
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not get search record table for activity!");
		}
		ITable table = (ITable)objs[0];
		
		int index = table.findRow(0, activityInfo) - 1;
		IHtmlObject[] rows = browser.getHtmlObject(".class","Html.TR",".id","ResultGrid_row_"+index);
		if(rows==null||rows.length<1){
			throw new ItemNotFoundException("Failed to found Row for activity:"+activityInfo);
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart",true, 0, rows[0]);
		Browser.unregister(rows);
		ajax.waitLoading();
	}
}
