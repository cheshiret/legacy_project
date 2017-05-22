/*
 * Created on Jul 22, 2009
 *
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class UwpRecreationSearchHomePage extends UwpPage {

	private static UwpRecreationSearchHomePage _instance = null;

	public static UwpRecreationSearchHomePage getInstance() {
		if (null == _instance)
			_instance = new UwpRecreationSearchHomePage();

		return _instance;
	}

	public UwpRecreationSearchHomePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "searchcgrounds");
	}
	/**
	 * Fill in recreation area search criteria data
	 * @param bd - booking data
	 */
	public void setupAreaSearchCriteria(BookingData bd) {
	  	if(bd.keyword!=null && bd.keyword.length()>0)
  	  		this.setKeyword(bd.keyword);

		if (bd.state == null || bd.state.length() < 1) {
			this.deselectState();
		}else{
			this.selectState(bd.state);

			if (bd.state.length() > 0) {
				this.waitLoading();//handle landmark drop down sync
				if (bd.landMark == null || bd.landMark.length() < 1)
					this.deselectLandMark();
				else
					this.selectLandMark(bd.landMark);
			}
		}
		if (bd.agency == null || bd.agency.length() < 1)
			this.deselectAgency();
		else
			this.selectAgency(bd.agency);

		if (bd.activity == null || bd.activity.length() < 1)
			this.deselectActivity();
		else
			this.selectActivity(bd.activity);
	}
	
	/**
	 * Fill in Keyword.
	 * @param keyword - keyword
	 */
	public void setKeyword(String keyword) {
		browser.setTextField(".id", "keyword", keyword);
	}
	
	/**
	 * Select state from dropdown list.
	 * @param state
	 */
	public void selectState(String state) {
		browser.selectDropdownList(".id", "pstate", state);
	}
	
	/**
	 * Deselect state.
	 */
	public void deselectState() {
		browser.selectDropdownList(".id", "pstate", 0);
	}
	
	/**
	 * Select land mark.
	 * @param landMark
	 */
	public void selectLandMark(String landMark) {
		browser.selectDropdownList(".id", "landmark", landMark);
	}
	
	/**
	 * Deselect land mark.
	 */
	public void deselectLandMark() {
		browser.selectDropdownList(".id", "landmark", 0);
	}
	
	/**
	 * Select agency from dropdown list.
	 * @param agency
	 */
	public void selectAgency(String agency) {
		browser.selectDropdownList(".id", "agencies", agency);
	}
	
	/**
	 * Deselect agency.
	 */
	public void deselectAgency() {
		browser.selectDropdownList(".id", "agencies", 0);
	}
	
	/**
	 * Select activity.
	 * @param activity
	 */
	public void selectActivity(String activity) {
		browser.selectDropdownList(".id", "activities", activity);
	}
	
	/**
	 * Deselect activity.
	 */
	public void deselectActivity() {
		browser.selectDropdownList(".id", "activities", 0);
	}
	
	/**
	 * Click on recreation area search submit button.
	 */
	public void clickRecreationSearchSubmit() {
		browser.clickGuiObject(".id", "searchcgrounds", ".type", "submit");
	}
	
	/**
	 * Go to first recreation area details page.
	 * @return - selected area name
	 */
	public String gotoFirstRecreationArea() {
		RegularExpression reg = new RegularExpression(".*recAreaDetails.*contractCode=.*", false);
		IHtmlObject[] foundTOs = browser.getHtmlObject(".class", "Html.A",".href", reg);

		String recreationArea = foundTOs[0].getProperty(".text").toString();

		if (foundTOs.length > 0) {
			((ILink)foundTOs[0]).click();//click on first available site
		} else
			throw new ItemNotFoundException("No results match your search!");

		Browser.unregister(foundTOs);
		return recreationArea;
	}
	
	/**
	 * Retrieve recreation area name.
	 * @return - area name
	 */
	public String getAreaName() {
		IHtmlObject[] foundTOs = browser.getHtmlObject(".id", "campnamearea1");
		String areaName = "";
		if (foundTOs.length > 0) {
			areaName = foundTOs[0].getProperty(".text").toString();
		}
		Browser.unregister(foundTOs);

		return areaName;
	}
	
	/**
	 * Go to regional map page by clicking link View Regional Map.
	 */
	public void gotoRegionalMap() {
		browser.clickGuiObject(".class", "Html.A", ".text",	"View Regional Map");
	}
	
	/**
	 * Go to agency's site list page.
	 * @param agencyName
	 */
	public void gotoAgencySite(String agencyName) {
		browser.clickGuiObject(".class", "Html.A", ".text", agencyName);
	}
}
