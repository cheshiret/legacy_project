/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 13, 2011
 */
public class UwpRecreationAreaDetailsPage extends UwpPage{

	private static UwpRecreationAreaDetailsPage _instance = null;

	public static UwpRecreationAreaDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpRecreationAreaDetailsPage();

		return _instance;
	}

	public UwpRecreationAreaDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","contentArea");
	}

	public String getAgency(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_header_parent");
		String agency = objs[0].text().trim();

		Browser.unregister(objs);
		return agency;
	}
	
	public String getAreaRelatedContent(){
		return browser.getObjectText(".className", new RegularExpression("mastheadLeft|component", false));
	}
	
	/**
	 * Verify area name and related state code
	 * @param areaName
	 * @param stateCode
	 */
	public void verifyAreaNameAndRelatedCode(String areaName, String stateCode){
		String actualValue = this.getAreaRelatedContent();
		String expectedValue = areaName +", ?"+ stateCode +".*";
		if(!actualValue.matches(expectedValue)){
			throw new ErrorOnDataException("Failed to verify Area name and related state code because "+actualValue+" doesn't match "+expectedValue);
		}
		logger.info("Successfully verify area name and related state code - "+actualValue);
	}

	public void gotoAgencySite(String agencyName) {
		browser.clickGuiObject(".class", "Html.A", ".text", agencyName);
	}

	public boolean isFacilityLongDescDetailsDisplay() {
		return browser.checkHtmlObjectExists(".className", "facility_view_desc_detail", ".id", "colbodytoplong");
	}
	
	public boolean isDesMoreLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".id", new RegularExpression("^more_.*", false)); //Sara[9/5/2013], add class attribute 
	}
	
	public void clickFacilityViewDesc(){
		browser.clickGuiObject(".className", "facility_view_description", ".id", "colbodytop");
	}
	
	public void waitForFacilityViewDescDetail(){
		browser.searchObjectWaitExists(".className", "facility_view_desc_detail", ".id", "colbodytoplong");
	}
	
	/** Get facility description which is long and expand after click More*/
	public String getFacilityViewDescDetail(){
		return browser.getObjectText(".className", "facility_view_desc_detail", ".id", "colbodytoplong");
	}
	
	/** Get facility description which is short and fully displayed without expand */
	public String getFacilityViewDescription(){
		return browser.getObjectText(".className", "facility_view_description", ".id", "colbodytop");
	}
	
	public String getOverView(){
//		HtmlObject[] objs = browser.getHtmlObject(".className", "content first");
//		String content = objs[0].text().split("Overview")[1];
//		String overView = "";
//		if(content.contains("Associated Facilities:")){
//			overView = content.split("Associated Facilities:")[0].trim();
//		}else if(content.contains("Make Reservations:")){
//			overView = content.split("Make Reservations:")[0].trim();
//		}else{
//			overView = content.split("Recreational Activities:")[0].trim();
//		}
//
//		Browser.unregister(objs);
//		return overView;
		
		String overView = "";
		if(!isFacilityLongDescDetailsDisplay() && isDesMoreLinkExist()){ // Need to expand if the description is long. Updated by Lesley due to Recreation Details page redesign in 3.04.02
			clickFacilityViewDesc();
			waitForFacilityViewDescDetail();
			overView = getFacilityViewDescDetail();
		}else {
			overView = getFacilityViewDescription();
		}
		
		return overView;
	}

	public void verifyOverView(String expectedOverView){
		String actualOverView = this.getOverView();
		if(!actualOverView.replaceAll("\\s+", "").equals(expectedOverView.replaceAll("\\s+", ""))){
			throw new ErrorOnDataException("Expected overView should be the same as the actual one.", expectedOverView, actualOverView);
		}
	}

	public String getRecreationalActivities(){
//		HtmlObject[] objs = browser.getHtmlObject(".className", "content first");
//		String recreationalActivities = objs[0].text().split("Recreational Activities:")[1].split("Address:")[0].trim();

		IHtmlObject[] objs = browser.getHtmlObject(".id", "activitieslist");
		String recreationalActivities = objs[0].text().split("Activities")[1].trim();
		Browser.unregister(objs);
		return recreationalActivities;
	}

	public void verifyRecreationalActivities(String expectedValue){
		String actualValue = this.getRecreationalActivities();
		if(!actualValue.equals(expectedValue)){
			throw new ErrorOnDataException("Recreational Activities is wrong!", expectedValue, actualValue);
		}
	}
	
	public void clickAssociatedFacility(String contractCode, String parkID){
//		RegularExpression regx = new RegularExpression(".*interface=camping&page=details&contractCode="+contractCode+"&parkId="+parkID, false);
		RegularExpression regx = new RegularExpression("/unifSearchInterface\\.do\\?interface=camping&contractCode="+contractCode+"&parkId="+parkID, false);//Sara[20140220] the href property of park is changed in "MAKE A RESERVATION" section 
		browser.clickGuiObject(".class", "Html.A", ".href", regx);
	}
	
	/**
	 * Click the "Find Other Facilities" hyperlink on left top conner of the page - added when working on Rec.gov.
	 */
	public void clickFindOtherFacilities(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Find Other Facilities");
	}
	
	public void clickBackToSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Back to Search", false));
	}
	
	public boolean checkFindOtherFacilitiesOnMap(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Find other facilities on map");
	}
	/**
	 * Click the "Find Other Facilities on map" hyperlink on left top conner of the page - added when working on Rec.gov.
	 */
	public void clickFindOtherFacilitiesOnMap(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Find other facilities on map");
	}
	
	/**
	 * Click the Google map hyperlink on left top conner of the page - added when working on Rec.gov.
	 */
	public void clickFacilityGoogleMap(){
		browser.clickGuiObject(".className", "facilityGoogleMap");//".class", "Html.IMG", 
	}
}
