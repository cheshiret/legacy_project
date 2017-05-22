package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description: The page is the one in RA website "Campground by Map" page, click "Map Browser" tab in the left of this page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Apr 17, 2013
 */
public class UwpCampgroundsMapBrowsePage extends UwpCampingPage{
	private static UwpCampgroundsMapBrowsePage _instance = null;

	public static UwpCampgroundsMapBrowsePage getInstance() {
		if (null == _instance)
			_instance = new UwpCampgroundsMapBrowsePage();

		return _instance;
	}

	protected UwpCampgroundsMapBrowsePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "mapviewcampgroundlist");
	}

	public void searchMapPinDisplays(String contractCode, String faclityID){
		Property[] p1 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("javascript:clickResult\\('"+contractCode+faclityID+"'\\)", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/images/maps/mm_\\d+_state\\.png", false));
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	public void clickMapPin(String contractCode, String faclityID){
		Property[] p1 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("javascript:clickResult\\('"+contractCode+faclityID+"'\\)", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/images/maps/mm_\\d+_state\\.png", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		System.out.println(objs.length);
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	public boolean checkFacilityPhotoDisplays(String contractCode, String facilityName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^"+facilityName, false));
//		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/webphotos/qa-photos/"+contractCode+"/.*\\.jpg", false));
 	    Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/webphotos/"+contractCode+"/.*\\.jpg", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public void clickMapSearchTab(){
		browser.clickGuiObject(".class", "Html.A", ".id", "mapSearch");
	}

}
