package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;

/**
 * @Description: It is the common page for ("Permit Area Details", "Permit Area Map", "Trail List" and "Date Range Availability" 4 sub tabs)
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Oct 31, 2012
 */
public class BwPermitCommonPage extends UwpPage {

	public static BwPermitCommonPage _instance = null;

	public static BwPermitCommonPage getInstance(){
		if (null == _instance){
			_instance = new BwPermitCommonPage();
		}
		return _instance;
	}

	protected BwPermitCommonPage(){

	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".id", "cgroundName");
	}


	/** Click on Permit Area Details tab link.*/
	public void clickPermitAreaDetails() {
		browser.clickGuiObject(".class", "Html.A", ".id", "wildernessAreaFacilityDetails");
	}	

	/** Click on Permit Area Map tab link.*/
	public void clickPermitAreaMap() {
		browser.clickGuiObject(".class", "Html.A", ".id", "permitMap");
	}

	/** Click on Date Range Availability tab link.*/
	public void clickDateRangeAvailability() {
		browser.clickGuiObject(".class", "Html.A", ".id", "permitCalendar");
	}

	/** Click on Trail list tab link.*/
	public void clickTrailList() {
		browser.clickGuiObject(".class", "Html.A", ".id", "entranceSearch");
	}
}
