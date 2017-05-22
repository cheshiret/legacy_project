package com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrFeeFindLocationPage extends LicMgrFeeScheduleCommonPage {
	static private LicMgrFeeFindLocationPage _instance = null;

	protected LicMgrFeeFindLocationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrFeeFindLocationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrFeeFindLocationPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV", ".id", "searchCriteria");
	}
	
	protected Property[] searchTypeDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "location_search");
	}
	
	protected Property[] searchValueTextField(){
		return Property.toPropertyArray(".id", "locationName");
	}
	
	protected Property[] showTypeDropdownList(){
		return Property.toPropertyArray(".id", "status");
	}
	
	protected Property[] locationCategoryDropdownList(){
		return Property.toPropertyArray(".id", "showCategory");
	}
	
	protected Property[] searchBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Search");
	}
	
	public void selectSearchType(String searchType){
		browser.selectDropdownList(this.searchTypeDropDownList(), searchType);
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(this.searchValueTextField(), searchValue);
	}
	
	public void selectShowType(String showType){
		browser.selectDropdownList(this.showTypeDropdownList(), showType);
	}
	
	public void selectLocationCategory(String locCat){
		browser.selectDropdownList(this.locationCategoryDropdownList(), locCat);
	}
	
	public void selectLocation(String location){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression(" ?Location ID.*", false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		int expectRow = -1;
		int empty_row_num = 0;
		for(int i=1;i<grid.rowCount();i++){
			String value = grid.getCellValue(i, 2);
			if(value==null){
				empty_row_num++;
				continue;
			}
			if(value.equalsIgnoreCase(location)){
				expectRow = i;
				break;
			}
			if(location.contains("%")){
				expectRow=i;
				break;
			}
		}
		if(expectRow==-1){
			throw new ErrorOnPageException("Not Found Expected Location: "+location);
		}
		Browser.unregister(objs);
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Select",false),expectRow-1-empty_row_num);
	}
	
	/**
	 * Search location by location name.
	 * @param location
	 * @param locationCategory
	 */
	public void searchByLocationName(String location, String locationCategory) {
	  	this.selectSearchType("Location Name");
	  	if(!StringUtil.isEmpty(location)) {
	  	  setSearchValue(location);
	  	  waitLoading();
		}
	  	this.selectShowType("Show Active Locations");
		if(!StringUtil.isEmpty(locationCategory)) {
		  selectLocationCategory(locationCategory);
		}
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}

}
