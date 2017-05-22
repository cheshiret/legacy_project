/*
 * Created on Jan 28, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager.recipient;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResourceManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrSelectFacilityMainPage extends ResourceManagerPage {

  	/**
	 * A handle to the unique Singleton instance.
	 */
	private ResMgrSelectFacilityMainPage() {
	}

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private static ResMgrSelectFacilityMainPage instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	public static ResMgrSelectFacilityMainPage getInstance() {
		if (null == instance) {
			instance = new ResMgrSelectFacilityMainPage();
		}
		return instance;
	}

	/**
	 * Check whether the specific page mark displayed
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Facility ID Facility Name.*",false));
	}
	
	public void selectSearchType(String searchType)
	{
	  	browser.selectDropdownList(".id","_FRLSearchType",searchType);
	}
	
	public void setSearchValue(String searchValue)
	{
	  	browser.setTextField(".id","_FRLSearchByValue",searchValue);
	}
	
	public void clickSearch()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	/**
	 * Search Facility By facility name
	 * @param name-facility name
	 */
	public void searchByFacilityName(String name)
	{
	  	selectSearchType("Facility Name");
	  	setSearchValue(name);
	  	clickSearch();
	  	waitLoading();
	}
	
	public void selectFirstFacilityRadio()
	{
	  	browser.selectRadioButton(".id","_SchedulerFacilityId");
	}
	
	public void selectFacility(String facilityId)
	{
	  	browser.selectRadioButton(".id","_SchedulerFacilityId",".value",facilityId);
	}
	
	public void gotoNextStep()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Next>>");
	}
}
