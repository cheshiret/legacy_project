package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description: The page is the one in RA website "Campground by Map" page, click "Map Search" tab in the left of this page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 17, 2013
 */
public class UwpCampgroundsMapSearchPage extends UwpCampingPage{
	private static UwpCampgroundsMapSearchPage _instance = null;

	public static UwpCampgroundsMapSearchPage getInstance() {
		if (null == _instance)
			_instance = new UwpCampgroundsMapSearchPage();

		return _instance;
	}

	protected UwpCampgroundsMapSearchPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".name", "reservationSearchForm");
	}
	
    public void clickMapPin(String contractCode, String faclityID){
    	Property[] p1 = Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("javascript:clickResult\\('"+contractCode+faclityID+"'\\)", false));
     	Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/images/maps/markerA\\.png", false));
     	browser.clickGuiObject(Property.atList(p1, p2), true, 0);
    }
	
    public boolean checkFacilityPhotoDisplays(String contractCode, String facilityName){
    	Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^"+facilityName, false));
//     	Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/webphotos/qa-photos/"+contractCode+"/.*\\.jpg", false));
 	    Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression("/webphotos/"+contractCode+"/.*\\.jpg", false));
 
    return browser.checkHtmlObjectExists(Property.atList(p1, p2));
    }
    
    public void clickMapBrowseTab(){
    	browser.clickGuiObject(".class", "Html.A", ".id", "mapBrowse");
    }
}
