package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: RA Park details page for Campgrounds and Marina, per PCR 4463 - Facility Page Re-design
 * 
 * @author Lesley Wang
 * @Date  Apr 1, 2014
 */
public class RAParkDetailsPageForCampAndMarina extends UwpHeaderBar {
	static class SingletonHolder {
		protected static RAParkDetailsPageForCampAndMarina _instance = new RAParkDetailsPageForCampAndMarina();
	}

	protected RAParkDetailsPageForCampAndMarina() {
	}

	public static RAParkDetailsPageForCampAndMarina getInstance() {
		return SingletonHolder._instance;
	}
	
	/**Page Property Define begin */
	protected Property[] parkIntroDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "facilityintro");
	}
	
	protected Property[] checkAvailLink() {
		return Property.concatPropertyArray(this.a(), ".title", "Check Availability");
	}
	
	protected Property[] checkAvailForMarina() {
		return Property.concatPropertyArray(this.a(), ".title", "Check Availability", ".href", new RegularExpression(".*interface=checkmarina.*", false));
	}
	/**Page Property Define end */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.parkIntroDiv()) && 
				browser.checkHtmlObjectExists(this.checkAvailLink());
	}
	
	public void clickCheckAvailability() {
		browser.clickGuiObject(this.checkAvailLink());
	}
	
	public void clickCheckAvailabilityForMarina() {
		browser.clickGuiObject(this.checkAvailForMarina());
	}
	
//	public boolean isCheckAvailabilityButtonExist(){
//		return browser.checkHtmlObjectExists(checkAvailLink());
//	}
}
