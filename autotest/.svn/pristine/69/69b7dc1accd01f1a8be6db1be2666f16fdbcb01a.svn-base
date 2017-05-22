package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the search page of activity registration orders
 * How to get to this page:Click "Orders" on the top menu and click "Activity Registration Orders" label
 * @author Phoebe
 */
public class LicMgrActivityRegistrationOrdersSearchPage extends LicMgrOrderSearchCommonPage {
	
	private static LicMgrActivityRegistrationOrdersSearchPage _instance = null;
	private LicMgrActivityRegistrationOrdersSearchPage() {}
	public static LicMgrActivityRegistrationOrdersSearchPage getInstance() {
		if(_instance == null) _instance = new LicMgrActivityRegistrationOrdersSearchPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "activityRegistrationOrdersSearchResult");
	}
	@Override
	public void setupOrderSearchCriteria(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	private Property[] searchType() {
		return Property.toPropertyArray(".id", new RegularExpression("ActivityRegistrationOrderSearchCriteria-\\d+\\.searchType", false));
	}
	
	private Property[] searchValue() {
		return Property.toPropertyArray(".id", new RegularExpression("ActivityRegistrationOrderSearchCriteria-\\d+\\.searchValue", false));
	}
	
	public void selectSearchType(String type) {
		browser.selectDropdownList(searchType(), type);
	}
	
	public void setSearchValue(String value) {
		browser.setTextField(searchValue(), value);
	}

	public void searchApplicationOrder(String ordNum) {
		this.selectSearchType("Order #");
		this.setSearchValue(ordNum);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
}
