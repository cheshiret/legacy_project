/*
 * Created on Jan 22, 2010
 */
package com.activenetwork.qa.awo.pages.web.uwp;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author vzhao
 */
public class UwpCampgroundMapPage extends UwpCampingPage{
	private static UwpCampgroundMapPage _instance = null;

	public static UwpCampgroundMapPage getInstance() {
		if (null == _instance)
			_instance = new UwpCampgroundMapPage();

		return _instance;
	}

	protected UwpCampgroundMapPage() {
	}

	public boolean exists() {
		Property[] p1=Property.toPropertyArray(".class","Html.TD",".className","slct");
		Property[] p2=Property.toPropertyArray(".class","Html.A",".id","campMap");
		Property[] p3=Property.concatPropertyArray(div(), ".className", "olMap",".id","viewmap");
//		return browser.checkHtmlObjectExists(".id", "fullCamp")||broc.wser.checkHtmlObjectExists(".id", "cgroundmap",".class","Html.DIV");
//		return browser.checkHtmlObjectExists(".id", "campsitesearchform");
		return browser.checkHtmlObjectExists(Property.atList(p1,p2))
				|| browser.checkHtmlObjectExists(p3); //Sara[20140416] ra-redesign
	}

	/** Click on Campground Details tab link.*/
	public void clickCampgroundDetail() {
	  	browser.clickGuiObject(".id", "campDetail");
	}
	
	/** Click on Date Range Availability tab link.*/
	public void clickDateRangeAvailability() {
	  	browser.clickGuiObject(".id", "campCalendar");
	}
	
	/** Click on Date Range Availability tab link.*/
	public void clickCampsiteList() {
	  	browser.clickGuiObject(".id", "campList");
	}
	
	/**
	 * Retrieve the Matching Campsites number in campground map page  
	 * @return siteNum - site numbers list
	 */
	public List<String> getMatchingSiteNums() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("div[0-9]+",false),".className",new RegularExpression("site(ListLabel)?",false));
		List<String> siteNum = new ArrayList<String>();
		
		for(int i=0; i<objs.length; i++) {
			siteNum.add(objs[i].text().trim());
		}
		
		Browser.unregister(objs);
		return siteNum;
	}
	
	public void selectSiteByNum(String siteNum) {
		browser.selectRadioButton(".id", new RegularExpression("div[0-9]+",false), ".text", siteNum);
	}
	
	/** Switch to Enhanced map mode*/
	public void clickSwitchToEnhancedMap() {
		browser.clickGuiObject(".id", "switchMap", ".text", "Switch to Enhanced Map");
		this.waitLoading();
	}
	
	/** Switch to Basic map mode*/
	public void clickSwitchToBasicMap() {
		browser.clickGuiObject(".id", "switchMap", ".text", "Switch to Basic Map");
	}
}
