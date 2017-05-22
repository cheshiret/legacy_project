package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LocationSearchPage extends InvMgrCommonTopMenuPage {
	
	private static LocationSearchPage _instance = null;


	public static LocationSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LocationSearchPage();
		}

		return _instance;
	}

	protected LocationSearchPage() {
	}

	public boolean exists() {
	      return browser.checkHtmlObjectExists(".id","LotteryCoverageSearchCriteria.searchFor");
	}
	
	public void selectSearchCriteria(String criteria){
		browser.selectDropdownList(".id", "LotteryCoverageSearchCriteria.searchFor", criteria);
	}
	
	public void setSearchValue(String value){
		browser.setTextField(".id", "LotteryCoverageSearchCriteria.searchValue", value);
	}
	
	public void selectLocationCategory(String location){
		browser.selectDropdownList(".id", "LotteryCoverageSearchCriteria.locationCategory", location);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Go|Search", false));
		waitLoading();
	}
	
	public void clickSelect(){
		browser.clickGuiObject(".class","Html.A",".text","Select");
	}
}
