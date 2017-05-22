package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.Timer;

public class OrmsSiteMapMainPage extends OrmsPage {
	private static OrmsSiteMapMainPage _instance = null;
	
	protected OrmsSiteMapMainPage(){
	}
	
	public static OrmsSiteMapMainPage getInstance(){
		if(_instance == null){
			_instance = new OrmsSiteMapMainPage();
		}
		return _instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] mapAreaDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.mapArea", false));
	}
	
	protected Property[] statusDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.status", false));
	}
	
	protected Property[] siteDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.productId", false));
	}
	
	protected Property[] dateTextField() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.date_ForDisplay", false));
	}
	
	protected Property[] lengthTextField() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.length", false));
	}
	
	protected Property[] occupantsTextField() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.occupants", false));
	}
	
	protected Property[] hookupDropdownList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.hookup", false));
	}
	
	protected Property[] waterHookupCheckBox() {
		return Property.concatPropertyArray(input("checkbox"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.waterHookup", false));
	}
	
	protected Property[] sewerHookupCheckBox() {
		return Property.concatPropertyArray(input("checkbox"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.sewerHookup", false));
	}
	
	protected Property[] pullThroughCheckBox() {
		return Property.concatPropertyArray(input("checkbox"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.pullThrough", false));
	}
	
	protected Property[] accessibleCheckBox() {
		return Property.concatPropertyArray(input("checkbox"), ".id", new RegularExpression("MapSearchCriteria-\\d+\\.accessible", false));
	}
	
	/** Page Object Property Definition END */
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", "mapWrapper");
	}
	
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
	}
	
	public void clickRefreshMap() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Refresh Map");
	}
	
	public void clickClose() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Close");
	}
	
	public String getStatusListValue() {
		return browser.getDropdownListValue(statusDropdownList(), 0);
	}
	
	public List<String> getStatusList() {
		return browser.getDropdownElements(statusDropdownList());
	}
	
	public String getSiteListValue() {
		return browser.getDropdownListValue(siteDropdownList(), 0);
	}
	
	public List<String> getSiteList() {
		return browser.getDropdownElements(siteDropdownList());
	}
	
	public String getDate() {
		return browser.getTextFieldValue(dateTextField());
	}
	
	public void selectArea(String area) {
		browser.selectDropdownList(mapAreaDropdownList(), area);
	}
	
	public void waitForAreaOption(String area) {
		RegularExpression idPattern=new RegularExpression("MapSearchCriteria-\\d+\\.mapArea",false);
		Property[] p1=Property.toPropertyArray(".class","Html.SELECT",".id", idPattern);
		Property[] p2=Property.toPropertyArray(".class","Html.OPTION",".text",area);
		boolean found=false;
		int timeout=LONG_SLEEP;
		Timer timer=new Timer();
		while(!found && timer.diff()<timeout) {
			Timer.sleep(1000);
			found=browser.checkHtmlObjectExists(Property.atList(p1,p2));
		}
		if(!found) {
			throw new ItemNotFoundException("Area option '"+area+"' is not found in "+timeout+" seconds.");
		}
	}
	
	public void setDate(String date) {
		browser.setCalendarField(dateTextField(), date);
	}
	
	public void clickRefreshStatus() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Refresh Map");
	}
	
	public void clickAdvancedSearch() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Advanced Search");
	}
		
	public String getLength() {
		return browser.getTextFieldValue(lengthTextField());
	}
	
	public String getOccupant() {
		return browser.getTextFieldValue(occupantsTextField());
	}
	
	public String getElectricHookupListValue() {
		return browser.getDropdownListValue(hookupDropdownList(), 0);
	}
	
	public List<String> getElectricHookupList() {
		return browser.getDropdownElements(hookupDropdownList());
	}
	
	public boolean isWaterHookupChecked() {
		return browser.isCheckBoxSelected(waterHookupCheckBox());
	}
	
	public boolean isSewerHookupChecked() {
		return browser.isCheckBoxSelected(sewerHookupCheckBox());
	}
	
	public boolean isPullThroughChecked() {
		return browser.isCheckBoxSelected(pullThroughCheckBox());
	}
	
	public boolean isAccessibleChecked() {
		return browser.isCheckBoxSelected(accessibleCheckBox());
	}
}
