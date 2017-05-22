package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class LicMgrCommonTopMenuPage extends LicenseManagerPage {
	
	public void clickHome() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Home");
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.leftmenu\\.id\\.[0-9]+", false));
		browser.clickGuiObject(p);
	}
	
	public void clickVendors() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Vendors");
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.leftmenu\\.id\\.[0-9]+", false));
		browser.clickGuiObject(p);
	}
	
	public void clickCustomers() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Customers");
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.leftmenu\\.id\\.[0-9]+", false));
		browser.clickGuiObject(p);
	}
	
	public void clickVehicles() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("Vehicles", false));
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.leftmenu\\.id\\.[0-9]+", false));
		browser.clickGuiObject(p);
	}
	
	public void clickOrders() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Orders");
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.leftmenu\\.id\\.[0-9]+", false));
		browser.clickGuiObject(p, true);
	}
	
	public boolean checkOrdersExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Orders");
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.leftmenu\\.id\\.[0-9]+", false));
		
		return browser.checkHtmlObjectExists(p);
	}
	
	public void clickAdmin() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Admin:", false));
	}
	
	public void clickCancelCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Cart");
	}
	
	/** Check if the option exists in Admin drop down list */
	public boolean isAdminOptionExist(String option) {
		return browser.dropdownListContains(Property.toPropertyArray(".class", "Html.Select", ".id", "field_search_dropdown"), option);
	}
	
	public boolean isLotteriesOptionExist() {
		return this.isAdminOptionExist("Lotteries");
	}
	
	public void selectAdminOptions(String option) {
		IHtmlObject objs[] = browser.getDropdownList(".id", "field_search_dropdown");
		
		if(((ISelect)objs[0]).getSelectedText().trim().equalsIgnoreCase(option)) {
			clickAdmin();
		} else {
			browser.selectDropdownList(".id","field_search_dropdown", option, true);
		}
		Browser.unregister(objs);
	}
	
	//added by pzhu
	public void selectFinancialsOptions(String option) {
		IHtmlObject objs[] = browser.getDropdownList(".id", "right_field_search_dropdown");
		
		if(((ISelect)objs[0]).getSelectedText().trim().equalsIgnoreCase(option)) {
			clickFinancial();
		} else {
			browser.selectDropdownList(".id","right_field_search_dropdown", option, true);
		}
		Browser.unregister(objs);
	}
	
	public void clickFinancial() {
		Property[] p = new Property[1];
	//	p[0] = new Property(".class", "Html.A");
	//	p[1] = new Property(".text", " Financials: ");
		p[0] = new Property(".id", "Financials");
		browser.clickGuiObject(p);
	}
	
	public void clickReports() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Reports");
		p[2] = new Property(".id", new RegularExpression("licensemanager\\.rightmenu\\.id\\.[0-9]+", false));
		browser.clickGuiObject(p);
	}
	
	public void clickSignOut() {
		browser.clickGuiObject(".class","Html.A",".text","Sign Out");
	}
	
	public String getAdminValue(){
		return browser.getDropdownListValue(".id", "field_search_dropdown", 0);
	}
	
	public void selectLocation(String location){
		RegularExpression regx=new RegularExpression("CurrentRoleLocationUI-\\d+\\.currentRoleLoc",false);
		browser.selectDropdownList(".id", regx, location);
		ajax.waitLoading();
	}
	
	public String getSelectedLocation(){
		RegularExpression regx=new RegularExpression("CurrentRoleLocationUI-\\d+\\.currentRoleLoc",false);
		return browser.getDropdownListValue(".id", regx);
	}

	/**
	 * Click Search link on License Manager top menu in order cart page
	 * 
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search", false));
	}
	
	/**
	 * Select the option in Search dropdown list on top menu in License Manage order cart page
	 * @param option
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public void selectSearchOptions(String option) {
		IHtmlObject objs[] = browser.getDropdownList(".id", "field_search_dropdown");
		
		if(((ISelect)objs[0]).getSelectedText().trim().equalsIgnoreCase(option)) {
			clickSearch();
		} else {
			browser.selectDropdownList(".id","field_search_dropdown", option, true);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Check if the "Cart" menu button exists to identify if there is an existing order cart
	 * @return
	 */
	public boolean isCartExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Cart");
		p[2] = new Property(".id", "licensemanager.cartmenu.id.1");
		
		return browser.checkHtmlObjectExists(p);
	}
	
	public void clickCart() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Cart");
		p[2] = new Property(".id", "licensemanager.cartmenu.id.1");
		
		browser.clickGuiObject(p);
	}
}
