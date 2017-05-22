package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

public class UwpUnifiedSearchViewAsMapPage extends UwpUnifiedSearchResultCommonPage {
	private static UwpUnifiedSearchViewAsMapPage _instance = null;

	protected UwpUnifiedSearchViewAsMapPage() {
	}

	public static UwpUnifiedSearchViewAsMapPage getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedSearchViewAsMapPage();

		return _instance;
	}

	/** Page Property Begin*/
	private RegularExpression typeFilterIdRegx = new RegularExpression("(camping|permit|tour|other)", false);
	private String agencyFilterItemIDReg = "A_(NRSO)?:(-)?\\d+";

	protected Property[] mapBubbleDiv() {
		return Property.concatPropertyArray(this.div(), ".id","mapbubble");
	}
	
	protected Property[] tourTitleDiv() {
		return Property.concatPropertyArray(this.div(), ".className","tour_title");
	}
	
	protected Property[] checkAvailLink() {
		return Property.concatPropertyArray(this.a(), ".className","check_available");
	}

	protected Property[] mapDiv() {
		return Property.concatPropertyArray(this.div(), ".id","search_results_map");
	}
	
	protected Property[] viewHeaderNearDiv() {
		return Property.concatPropertyArray(this.div(), ".className", new RegularExpression("facility_view_header_(near|nearest)", false)); //"facility_view_header_near");
	}
	
	protected Property[] mapPinImg(String contractCode, String parkId) {
		return Property.concatPropertyArray(this.img(), ".id", new RegularExpression("usrpin_"+contractCode.toUpperCase()+parkId+"(_\\d+)?", false));
	}
	
	protected Property[] mapPinLink(String contractCode, String parkId) {
		return Property.concatPropertyArray(a(), ".href", new RegularExpression(".*showFacilityOnMap.*"+contractCode+parkId+".*", false));
	}
	
	protected Property[] mapPinImg(String contractCode) {
		return Property.concatPropertyArray(this.img(), ".id", new RegularExpression("usrpin_"+contractCode.toUpperCase()+"\\d+(_\\d+)?", false));
	}
	
	protected Property[] mapPinLink(String contractCode) {
		return Property.concatPropertyArray(a(), ".href", new RegularExpression(".*showFacilityOnMap.*"+contractCode+".*", false));
	}
	
	protected Property[] viewContentDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "facility_view_content");
	}
	
	protected Property[] mapViewPortDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "mapviewport");
	}
	
	protected Property[] smallTourImg() {
		return Property.concatPropertyArray(this.img(), ".src",  new RegularExpression(".*maps/mm_20_tour\\.png",false));
	}
	
	protected Property[] km(){
		return Property.concatPropertyArray(span(), ".text", new RegularExpression("^\\d+ km", false));
	}
	/** Page Property End*/
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.DIV",".id","map_view_switch",".className","view_switch last selected"));
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className", "usearch_results_label")) && 
				browser.checkHtmlObjectExists(Property.concatPropertyArray(div(),".id","map_view_switch",".className",
						new RegularExpression("(en)?view_switch last selected", false)));
		
	}
	
	public boolean gotoNext(){		
		boolean flag = this.checkNext();
		
		if(flag){
			this.clickNext();
			this.waitLoading();
		}
		return flag;
	}
	
	public boolean gotoPrevious(){		
		boolean flag = this.checkPrevious();
		
		if(flag){
			this.clickPrevious();
			this.waitLoading();
		}
		return flag;
	}
	
	/**Check Previous*/
	public boolean checkPrevious(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(".*Previous",false));
	}
	
	/**Click Previous button*/
	public void clickPrevious(){
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"), new Property(".text", new RegularExpression(".*Previous",false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find Previous button on the page");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	/**Check Next existed*/
	public boolean checkNext(){
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"), new Property(".text", new RegularExpression("Next.*",false))};
		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}
	/**Click Next button*/
	public void clickNext(){
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"), new Property(".text", new RegularExpression("Next.*",false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find Next button on the page");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	/**
	 * Go to first page of Park List
	 */
	public void gotoFirstPage(){
		while(this.checkPrevious()){
			this.clickPrevious();
			this.waitLoading();
		}
	}
	
	/**
	 * Go to last page of Park List
	 */
	public void gotoLastPage(){
		while(this.checkNext()){
			this.clickNext();
			this.waitLoading();
		}
	}
	
	/**
	 * Get search result label like:Search Results: 1-10 of 789
	 * @return
	 */
	public String getSearchResultsLabel(){
		IHtmlObject[] searchResultHeaderTop = browser.getHtmlObject(".class", "Html.DIV", ".id", "search_results_map");
		if(searchResultHeaderTop==null || searchResultHeaderTop.length<1){
			throw new ObjectNotFoundException("Search result map object can't be found.");
		} 
		
		IHtmlObject[] searchResultHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "usearch_results_label",searchResultHeaderTop[0]);
		if(searchResultHeader==null || searchResultHeader.length<1){
			throw new ObjectNotFoundException("Search result label object can't be found.");
		} 
		
		String headerText = searchResultHeader[0].text().trim();
		Browser.unregister(searchResultHeaderTop,searchResultHeader);
		return headerText;
	}
	
	/**Wait for Enter Date pop up*/
	public void mapBubbleWidgetWaitExists() {
		boolean exists=false;
		Timer timer=new Timer();
		
		while(!exists && timer.diff()<30) {
			exists=checkMapBubbleWidgetExist();
		}
		
		if(!exists) {
			throw new ItemNotFoundException("Facility Bubble widget did not display!");
		}
	}
	
	/**
	 * Get first page park site type titles include:
	 * xx matching sites available
	 * First-Come-First-Served.
	 * Reservation via Call center.
	 * 
	 * Camping and Lodging
	 * Permits and Wilderness
	 * Tours and Tickets
	 * 
	 * @return
	 */
	public String getParkSiteTypeTitle(){
		String value = "";
		IHtmlObject[] typTitles = browser.getHtmlObject(".className", new RegularExpression("(site_types_title)|(permit_title)|(tour_title)|(reservation_directive)", false));
	    if(typTitles==null||typTitles.length<1){
	    	value = "null";
	    }else{
		    value=typTitles[0].text();
	    }

	    Browser.unregister(typTitles);
	    return value;
	}
	
	public void verifyParkSiteTypeTitle(String expectTitle){
		String currentTitle = this.getParkSiteTypeTitle();
		
		if(!expectTitle.equalsIgnoreCase(currentTitle)){
			throw new ErrorOnPageException("The site types title verify failed.", expectTitle, currentTitle);
		}
		logger.info("The site types title verify successfully.");
	}
	
	/**
	 * get the  park amenties info in the bubble up widget.
	 * the amentites info is in the format of: Has sites with:  Electric Hook-up(30), Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).
	 * @return
	 */
	public String getParkSitesAmenities(){
		String amenities ="";
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".id",new RegularExpression("sites_amenities_.*",false)),new Property(".className","sites_amenities")};
		IHtmlObject[] amenityObjs = browser.getHtmlObject(p);
		if(amenityObjs==null||amenityObjs.length<1){
			throw new ObjectNotFoundException("Can't find Amenities DIV");
		}
		amenities = amenityObjs[0].text().trim();
		Browser.unregister(amenityObjs);
		return amenities;
	}
	
	public void verifyParkSitesAmenities(String expectSiteAmenities){
		String actualSiteAmenities = this.getParkSitesAmenities();

		if(!actualSiteAmenities.equalsIgnoreCase(expectSiteAmenities)){
			throw new ErrorOnDataException("The expect site amenity is:" + expectSiteAmenities + "; while the current site amenity is:" + actualSiteAmenities);
		}
	}
	
	/**
	 * wait for page loading completed
	 */
	public void waitMapLoadingComplete(){
		Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK); // add by Lesley. A lot of cases are failed due to map not finish loading.
		boolean exists=false;
		int time = SLEEP;
		Timer timer=new Timer();
		
		while(!exists && timer.diff()<time) {
			String loadingMsg="";
			try{
				loadingMsg = this.getMapStatusMessage();
			}catch(ObjectNotFoundException e){
				exists=true;
			    break;
			}
			
			if (loadingMsg == null || !loadingMsg.isEmpty() && loadingMsg.startsWith("loading") ){
				continue;	
			}else{
				exists = true;
			}
		}
		
		if(!exists) {
			throw new ItemNotFoundException("Google map loading didn't finish in " + time + " seconds");
		}
	}
	
	public boolean checkMapBubbleExist(String parkName){
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble"),new Property(".text",new RegularExpression("^[ ]*(?i)"+parkName+".*",false))};
		boolean flag = browser.checkHtmlObjectExists(p);
		return flag;
	}
	
	public void clickParentNameInWidget(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".className", "facility_parent_link")};
	
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("Can't find park name hyperlink in the popup bubble widget.");
			
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void waitForParentNameInWidget(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".className", "facility_parent_link")};
		browser.searchObjectWaitExists(Property.atList(p1,p2));
	}
	
	public void waitForParkNameInWidget(String parkName){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".className", "facility_link"),new Property(".text", new RegularExpression(parkName, false))};
		browser.searchObjectWaitExists(Property.atList(p1,p2));
	}
	
	public void waitForParkNameInWidget(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".className", "facility_link")};
		browser.searchObjectWaitExists(Property.atList(p1,p2));
	}
	
	public void clickParkNameInWidget(String parkName){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".className", "facility_link"),new Property(".text", new RegularExpression(parkName, false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("Can't find park name hyperlink in the popup bubble widget.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void clickParkNameInWidget(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".className", "facility_link")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("Can't find park name hyperlink in the popup bubble widget.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void waitForSmallPicture(String facilityId){
		Property[] p = Property.concatPropertyArray(img(), ".id", new RegularExpression("^bbl_.*" + facilityId +".*", false));
		browser.searchObjectWaitExists(p, SLEEP);
	}
	
	public void waitForMapBubbleWidget(){
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "mapviewport")};
		Property[] p2=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "mapbubble")};
		browser.searchObjectWaitExists(Property.atList(p1, p2));
	}
	
	/**
	 * click park small picture based on picture id in the park.
	 * @param parkPicID
	 * @return
	 */
	public void clickParkSmallPicture(String facilityId){
		Property[] p = Property.concatPropertyArray(img(), ".id", new RegularExpression("^bbl_.*" + facilityId +".*", false));
		browser.clickGuiObject(p, true);
	}
	
	/**
	 * click the park's pin picture, the pin picture displayed as A, B, etc..
	 * @return
	 */
	public void clickMapPin(String contractCode, String parkId) {
		if(contractCode.equalsIgnoreCase("NRRS"))
			contractCode="NRSO";
		if(StringUtil.isEmpty(contractCode))
			contractCode = "NRSO";

		//Get pin objects
		IHtmlObject[] parkPinObjs=null;
		if(StringUtil.isEmpty(parkId)){
			if(browser.checkHtmlObjectExists(mapPinImg(contractCode))){
				parkPinObjs = browser.getHtmlObject(mapPinImg(contractCode));
			}else parkPinObjs = browser.getHtmlObject(mapPinLink(contractCode));
		}else {
			if(browser.checkHtmlObjectExists(mapPinImg(contractCode,parkId))){
				parkPinObjs = browser.getHtmlObject(mapPinImg(contractCode,parkId));
			}else parkPinObjs = browser.getHtmlObject(mapPinLink(contractCode,parkId));
		}
		
		//Click map pin
		if(null!=parkPinObjs && parkPinObjs.length >0){
			if(StringUtil.isEmpty(parkId)){
				parkPinObjs[0].click();	
			}else parkPinObjs[parkPinObjs.length-1].click();
		}else{
			throw new ErrorOnPageException("System can't get the pin Pic for park based on " +contractCode+parkId  );
		}

		Browser.unregister(parkPinObjs);
	}
	
	public boolean checkMapPinDisplay(String src){
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id", "search_results_map");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG",".src", new RegularExpression(src, false));
		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}
	
//	public void waitMapPinMarkerDisplaying(String src){
//		boolean exists=false;
//		int time = LONG_SLEEP;
//		Timer timer=new Timer();
//		
//		while(!exists && timer.diff()<time) {
//			exists = this.checkMapPinDisplay(src);
//		}
//
//		if(!exists) {
//			throw new ItemNotFoundException("map pin loading didn't finish in " + time + " seconds");
//		} else {
//			logger.info("map pin with src=" + src + " display");
//		}
//	}
	
	public void waitMapPinMarkerDisplaying(String contractCode, String parkId){
		boolean exists=false;
		int time = LONG_SLEEP;
		Timer timer=new Timer();
		
		if(StringUtil.isEmpty(contractCode))
			contractCode = "NRSO";
		
		while(!exists && timer.diff()<time) {
			if(StringUtil.isEmpty(parkId)){
				exists = browser.checkHtmlObjectExists(mapPinImg(contractCode)) && browser.checkHtmlObjectExists(mapPinLink(contractCode));
			}else exists = browser.checkHtmlObjectExists(mapPinImg(contractCode, parkId)) && browser.checkHtmlObjectExists(mapPinLink(contractCode, parkId));
		}

		if(!exists) {
			throw new ItemNotFoundException("map pin loading didn't finish in " + time + " seconds");
		} else {
			logger.info("map pin with contractCode=" + contractCode + ", parkid="+parkId+" display");
		}
	}
	
//	public void waitFirstMapPinDisplays(){
//		boolean exists=false;
//		int time = OrmsConstants.SLEEP;
//		Timer timer=new Timer();
//		
//		while(!exists && timer.diff()<time) {
//			exists = this.checkFirstMapPinDisplay();
//		}
//
//		if(!exists) {
//			throw new ItemNotFoundException("First map pin loading didn't finish in " + time + " seconds");
//		}
//		this.waitMapPinDisplays(".*qa\\.reserveamerica\\.com:\\d+/images/maps/markerA(_[A-Z]+)?\\.png");
//		this.waitMapPinMarkerDisplaying(".*images/maps/markerA(_[A-Z]+)?\\.png");
//	}
	
	public void waitFirstMapPinDisplays(String contractCode, String parkId){
		if(StringUtil.isEmpty(contractCode))
			contractCode = "NRSO";
		
		if(StringUtil.notEmpty(parkId)){
			this.waitMapPinMarkerDisplaying(contractCode, parkId);
		}else this.waitMapPinMarkerDisplaying(contractCode, StringUtil.EMPTY);
	}
	
//	public boolean checkFirstMapPinDisplay(){
////		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id", "search_results_map");
////		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG",".src", new RegularExpression(".*qa\\.reserveamerica\\.com:\\d+/images/maps/markerA(_[A-Z]+)?\\.png", false));
////		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
//		return this.checkMapPinDisplay(".*qa\\.reserveamerica\\.com:\\d+/images/maps/markerA(_[A-Z]+)?\\.png");
//	}
	
//	public IHtmlObject[] getFirstMapPinObjs(){
//		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id", "search_results_map");
//		//http://web03.qa.reserveamerica.com:5101/images/maps/markerA.png
//		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG",".src", new RegularExpression(".*images/maps/markerA(_[A-Z]+)?\\.png", false));
//	    IHtmlObject[] parkPinObjs=browser.getHtmlObject(Property.atList(p1,p2));
//	    if(parkPinObjs==null || parkPinObjs.length<1){
//	    	throw new ErrorOnPageException("System can't get first pin Pic.");
//	    }
//	    
//	    return parkPinObjs;
//	}
	public IHtmlObject[] getFirstMapPinObjs(String contractCode){
		 IHtmlObject[] parkPinObjs = null;
		if(browser.checkHtmlObjectExists(mapPinImg(StringUtil.isEmpty(contractCode)?"NRSO":contractCode))){
			parkPinObjs=browser.getHtmlObject(mapPinImg(StringUtil.isEmpty(contractCode)?"NRSO":contractCode));
		}else parkPinObjs=browser.getHtmlObject(mapPinLink(StringUtil.isEmpty(contractCode)?"NRSO":contractCode));
    if(parkPinObjs==null || parkPinObjs.length<1){
    	throw new ErrorOnPageException("System can't get first pin Pic.");
    }
    
    return parkPinObjs;
}
//	public void clicFirstkMapPin() {
//		IHtmlObject[] objs = this.getFirstMapPinObjs();
////		objs[objs.length-1].click(); //Sara [11/4/2013] maybe two objects are found, but can successfully click the first one
//		objs[0].click();
//		Browser.unregister(objs);
//	}
	public void clicFirstkMapPin(String contractCode) {
		IHtmlObject[] objs = this.getFirstMapPinObjs(StringUtil.isEmpty(contractCode)?"NRSO":contractCode);
		objs[0].click();
		Browser.unregister(objs);
	}
	
	/**
	 * check whether the given pin exist on the map page, based on sending Pin Number indicator or Pin Pic Src info.
	 * @param pinValue  Pin number value(for exampe A, B), or Pin Src info
	 * @param isPinValue; true represent Pin Number and false represent Pin Src
	 * @return
	 */
	public boolean checkMapPinExists(String pinValue, boolean isPinValue){
		Boolean exist = false;
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".className", "search_results_map"),new Property(".id", "search_results_map")};
		Property[] p2;
		if("".equals(pinValue)){
			p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", new RegularExpression("gm_marker_\\d+",false))};
		}else if (isPinValue){
			p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".src", new RegularExpression(".*marker"+pinValue.toUpperCase()+"(_)?(No)?(Match)?(NA)?(NI)?.png",false))};
		}else{
			p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".src", RegularExpression.convert(pinValue, false))};
		}
	    IHtmlObject[] mapPinObjs=browser.getHtmlObject(Property.atList(p1,p2));
	    
		if(mapPinObjs != null && mapPinObjs.length > 0){
			exist = true;
		}
		Browser.unregister(mapPinObjs);
		return exist;
	}
	
	/**
	 * check whether the given Pin exist on the map page.
	 * @param pinValue  Pin Number value for example "A","B"
	 * @return
	 */
	public boolean checkMapPinExists(String pinValue){
		return this.checkMapPinExists(pinValue, true);
	}
	
	public boolean checkMapPinExists(String contractCode, String parkId){
		return browser.checkHtmlObjectExists(mapPinImg(contractCode, parkId)) && browser.checkHtmlObjectExists(mapPinLink(contractCode, parkId));
	}
	
	/**
	 * Check map pin exist according to pin value, pin park id
	 * @param pinValue: A, B, C, D
	 * @param contractCode
	 * @param pinParkID
	 * @return
	 */
	public boolean checkMapPinExist(String pinValue, String contractCode, String pinParkID){
		Boolean exist = false;
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), 
				new Property(".src", new RegularExpression(".*marker"+pinValue.toUpperCase()+"(_)?(No)?(Match)?(NA)?(NI)?.png",false)),
				new Property(".id", new RegularExpression("usrpin_"+contractCode+pinParkID, false))};
	    IHtmlObject[] mapPinObjs=browser.getHtmlObject(Property.atList(p1,p2));
	    
		if(mapPinObjs != null && mapPinObjs.length > 0){
			exist = true;
		}
		Browser.unregister(mapPinObjs);
		return exist;
	}
	
	/**
	 * verify the map pin exist or not on the current map page.
	 * @param pinValue
	 * @param isPinValue
	 * @param expectDisplay
	 */
	public void verifyMapPinExists(String pinValue, boolean isPinValue, boolean expectDisplay){
		boolean exist = this.checkMapPinExists(pinValue, isPinValue);
		
		if((expectDisplay && exist) ||  (!expectDisplay && !exist) ){
			logger.info("verify map pin exist or not on map page successfully.");
		}else{
			logger.error("the Pin info used for matching is:" + pinValue);
			throw new ErrorOnPageException("The pin display status should be:" + expectDisplay);
		}
	}
	
	public void verifyMapPinExists(String contractCode, String parkId, boolean expectDisplay){
		boolean exist = this.checkMapPinExists(contractCode, parkId);
		
		if((expectDisplay && exist) ||  (!expectDisplay && !exist) ){
			logger.info("verify map pin exist or not on map page successfully.");
		}else{
			logger.error("the Pin info used for matching is contractCode=" +contractCode+", parkId="+parkId );
			throw new ErrorOnPageException("The pin display status should be:" + expectDisplay);
		}
	}
	
	public boolean checkMapPinMatched(String pinValue){
		Boolean exist = false;
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".src", new RegularExpression(".*marker"+pinValue.toUpperCase()+"\\.png",false))};
	    IHtmlObject[] mapPinObjs=browser.getHtmlObject(Property.atList(p1,p2));
	    
		if(mapPinObjs != null && mapPinObjs.length > 0){
			exist = true;
		}
		Browser.unregister(mapPinObjs);
		return exist;
	}
	
	public boolean checkMapPinNoMatched(String pinValue){
		Boolean exist = false;
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".src", new RegularExpression(".*marker"+pinValue.toUpperCase()+"_(NoMatch)?(NA)?\\.png",false))};
	    IHtmlObject[] mapPinObjs=browser.getHtmlObject(Property.atList(p1,p2));
	    
		if(mapPinObjs != null && mapPinObjs.length > 0){
			exist = true;
		}
		Browser.unregister(mapPinObjs);
		return exist;
	}
	
	public boolean checkMapPinXExists(){
		Boolean exist = false;
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "mapviewport")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".src", new RegularExpression(".*mapmarker_where.png",false))};
	    IHtmlObject[] mapPinObjs=browser.getHtmlObject(Property.atList(p1,p2));
	    
		if(mapPinObjs != null && mapPinObjs.length > 0){
			exist = true;
		}
		Browser.unregister(mapPinObjs);
		return exist;
	}
	
	public boolean checkMapBubbleWidgetExist() {
		IHtmlObject[] objs= getMapBubbleWidget();
		boolean exists=false;
		if(objs.length>0) {
			exists= objs[0].exists();
		} 
		Browser.unregister(objs);
		return exists;
	}
	/**
	 * get park's pin picture src info, the return value look like "images/maps/markerA.png";
	 * @return
	 */
	public String getMapPinMarkerSrc(String contractCode, String parkID) {
		String pinSource = ""; 
		
		IHtmlObject[] parkPinObjs = browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression("^usrpin_"+contractCode+parkID, false));
		if( null !=parkPinObjs && parkPinObjs.length >0){
			pinSource= parkPinObjs[0].getProperty(".src");
		}

		Browser.unregister( parkPinObjs);
		return pinSource;
	}
	
	/**
	 * verify the park pin's src info
	 * @param contractCode
	 * @param parkID
	 * @param expectPinSrc
	 */
	public void verifyMapPinPictureInfo(String contractCode, String parkID, String expectPinSrc){
		String currentPinSrc = this.getMapPinMarkerSrc(contractCode, parkID);
		if(!currentPinSrc.equalsIgnoreCase(expectPinSrc)){
			throw new ErrorOnPageException("verify the pin src info for park:" + parkID + "failed.");
		}
		logger.info("verify the pin src info for park:" + parkID + "successfully.");
	}
	
//	public void waitMapBubbleWidgetDisplays(){
//		browser.searchObjectWaitExists(Property.toPropertyArray(".class","Html.DIV",".className","facility_view_card",".id","mapbubble"), LONG_SLEEP);
//	}
	
	public IHtmlObject[] getMapBubbleWidget() {
		return browser.getHtmlObject(Property.toPropertyArray(".class","Html.DIV",".className","facility_view_card",".id","mapbubble"));
	}
	
	public IHtmlObject[] getBubbleWidgetObjs(){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class","Html.DIV",".id","search_results_map");
		if(null == topObjs || topObjs.length ==0){
			//map bubble widget is the popup widget after we click Pin for park.
			throw new ErrorOnPageException("Can't find map widget..");
		}

		return topObjs;
	}
	
	/**
	 * close the popup map bubble widget.
	 */
	public void closeMapBubbleWidget(){
		IHtmlObject[] topObjs = this.getBubbleWidgetObjs();
		
//		HtmlObject[] bubbleObjs = browser.getHtmlObject(".class","Html.IMG",".src",new RegularExpression(".*/mapfiles/iw_close.gif", false), topObjs[0]);
		IHtmlObject[] bubbleObjs = browser.getHtmlObject(".class","Html.IMG",".src",new RegularExpression(".*maps\\.gstatic\\.com/mapfiles/mv/imgs8.png", false), topObjs[0]);
		if(null != bubbleObjs && bubbleObjs.length >0){
			bubbleObjs[0].click();
		}else{
			//map bubble widget is the popup widget after we click Pin for park.
			throw new ErrorOnPageException("Can't find close object for the pop up map bubble");
		}
	}
	
	public String getParkSiteTypeTitleOnMapBubble(){
		String value = "";
		IHtmlObject[] topObjs = this.getBubbleWidgetObjs();
		Property[] p = Property.toPropertyArray(".className", new RegularExpression("(site_types_title)|(permit_title)|(tour_title)|(reservation_directive)", false));
		IHtmlObject[] typTitles = browser.getHtmlObject(p, topObjs[0]);
	    if(typTitles==null||typTitles.length<1){
	    	value = "null";
	    }else{
		    value=typTitles[0].text();
	    }

	    Browser.unregister(typTitles);
	    return value;
	}
	
	public void verifyParkSiteTypeTitleOnMapBubble(String expectTitle){
		String currentTitle = this.getParkSiteTypeTitleOnMapBubble();
		
		if(!expectTitle.equalsIgnoreCase(currentTitle)){
			throw new ErrorOnPageException("The site types title on map bubble verify failed.", expectTitle, currentTitle);
		}
		logger.info("The site types title on map bubble verify successfully.");
	}
	
	/**
	 * Click on Enter Date/See Details link in pop up window.
	 * or click See Details button in Map Search section.
	 * */
	public void clickBookSitesInWidget() {
		this.clickHyperlinkInWidget("Book \\w+|Check Availability");
	}
	
	/**
	 * Click on "Check Availability" button in the bubble widget.
	 * */
	public void clickCheckAvailabilityInWidget() {
		this.clickHyperlinkInWidget("Check Availability");
	}
	
	/**
	 * Click on Next Available Date button in pop up window.
	 * or click See Details button in Map Search section.
	 * */
	public void clickNextAvailableDateInWidget() {
		this.clickHyperlinkInWidget("Next Available Date");
	}
	
	private void clickHyperlinkInWidget(String displayText){
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".className","facility_view_card",".id","mapbubble");
		Property[] p2=Property.toPropertyArray(".class","Html.A",".text", new RegularExpression(displayText, false));
		browser.clickGuiObject(Property.atList(p1,p2), true,0);
	}
	
	public boolean checkBookSitesButtonExist(){
		return this.checkBookSitesButtonExist("Book (Sites|Now)");
	}
	
	public boolean checkNextAvailableButtonExist(){
		return this.checkBookSitesButtonExist("Next Available Date");
	}
	
	public boolean checkBookSitesButtonExist(String buttonText){
		Boolean flag = false;
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".className","facility_view_card",".id","mapbubble");
		Property[] p2=Property.toPropertyArray(".class","Html.DIV",".id", new RegularExpression("check_avail_panel_.*\\d+", false));
		
		IHtmlObject[] bookSiteObjs = browser.getHtmlObject(Property.atList(p1,p2));
		
		if(null == bookSiteObjs || bookSiteObjs.length ==0){
			//map bubble widget is the popup widget after we click Pin for park.
			throw new ErrorOnPageException("Can't find button with \"Book Sites\" or \"Next Available Date\"..");
		}
		
		if(bookSiteObjs[0].text().trim().equalsIgnoreCase(buttonText)){
			flag = true; 
		}
		Browser.unregister(bookSiteObjs );
		return flag;
	}

	/**
	 * 
	 */
	public String getMapStatusMessage() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", "mapStatusMsg");
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find map status message DIV...");
		}
		String msg=objs[0].text();
		Browser.unregister(objs);
		return msg;
	}
	
	public String getFacilityInfoOnMapBubble() {
		return browser.getObjectText(mapBubbleDiv());
	}
	
	/**
	 * Get The first facility view header info: Facility Name + State + Parent Facility/Region
	 * Format: 'Facility Name', 'State' part of 'Parent Facility/Region'
	 * @return
	 */
	public String getFirstFacilityViewHeader(){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class","Html.DIV",".id","mapbubble");
		if(null == topObjs || topObjs.length ==0){
			//map bubble widget is the popup widget after we click Pin for park.
			throw new ErrorOnPageException("Can't find map bubble widget..");
		}
		IHtmlObject[] facilityViewHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header", topObjs[0]);
		String firstFacilityViewHeaderInfo = facilityViewHeader[0].getProperty(".text");
		
		Browser.unregister(facilityViewHeader,topObjs);
		return firstFacilityViewHeaderInfo;
	}

	/**
	 * Get first park name
	 * @return
	 */
	public String getParkNameFromBubbleWidget(){
		String firstParkName = "";
		IHtmlObject[] topObjs = browser.getHtmlObject(".class","Html.DIV",".id","mapbubble");
		if(null == topObjs || topObjs.length ==0){
			//map bubble widget is the popup widget after we click Pin for park.
			throw new ErrorOnPageException("Can't find map bubble widget..");
		}
		IHtmlObject[] facilityViewHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header", topObjs[0]);
			firstParkName = facilityViewHeader[0].getProperty(".text").split(",")[0].trim();
		
		Browser.unregister(facilityViewHeader,topObjs);
		return firstParkName;
	}
	
	/**
	 * Verify first facility view header format based on given Facility Name, Parent Facility or Region and State, Agency
	 * @param facilityName
	 * @param parentFacilityOrRegion: Sometimes it is all the capital, but sometime first character of each word is capital  
	 * @param state
	 */
	public void verifyFirstFacilityViewHeaderFormat(String facilityName, String parentFacilityOrRegion, String state, String agency){
		String facilityInfo = "";
		if(null == agency){
			facilityInfo = facilityName+" ?, "+state+" part of ?"+parentFacilityOrRegion;
		}else{
			facilityInfo = facilityName+" ?, "+state+" (\\[\\d+\\.\\d+miles\\*\\] )?part of ?"+parentFacilityOrRegion + " ?(, )?" + agency + "( See Details)?";
		}
		String current = this.getFirstFacilityViewHeader();
		if(!current.matches(facilityInfo)){
			throw new ErrorOnDataException("First Facility Name and associated info is incorrect.", facilityInfo, current);
		}
	}
	
	public void verifyFirstRecreationAreaViewHeader(String facilityName, String state, String agency){
		String recreationInfo = facilityName+" ?, "+state+" ?" + agency;
		String current = this.getFirstFacilityViewHeader();
		if(!current.matches(recreationInfo)){
			throw new ErrorOnDataException("First recreation area Name and associated info is incorrect.");
		}
	}
	
	/**
	 * check small park picture is invisible, when it was enlarged.
	 */
	public void verifySmallParkPictureHidden(String facilityID){
		IHtmlObject[] facilityPicture = browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression("^bbl_.*" + facilityID +".*", false));
		String style = facilityPicture[0].style("visibility").trim();
		if (style.equalsIgnoreCase("hidden")){
			logger.info("Verify the initial small park picture hidden successful after click it.");
		}else{
			logger.error("The inital small park picture should be hidden after click it. facility ID:" + facilityID);
			throw new ErrorOnPageException("The inital small park picture should be hidden after click it. facility ID:" + facilityID);
		}
	}
	
	/**
	 * check small park picture is invisible, when it was enlarged.
	 */
	public void verifySmallParkPictureDisplay(String facilityID){
		IHtmlObject[] facilityPicture = browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression("^bbl_.*" + facilityID +".*", false));
		String style = facilityPicture[0].style("visibility");
		if (style.equalsIgnoreCase("hidden")){
			throw new ErrorOnPageException("The inital small park picture should be displayed. facility ID:" + facilityID);

			
		}else{
			logger.info("Verify the initial small park picture display successfully.");
			 }
	}
	
	/**
	 * verify the enlarged picture to be displayed in given seconds.
	 * @param facilityID
	 */
	public void waitEnlargedPictureDisplay(String facilityID){
		boolean flag = false;
		int time = SLEEP*10;
		Timer timer=new Timer();
		
		while(!flag && timer.diffLong()<time) {
			flag = this.checkEnlargedParkPicDisplay(facilityID);
		}
		
		if (!flag){
			throw new ErrorOnPageException("The enlarged picture didn't display in " + (time/1000) + " seconds..");
		}
	}
	
	/**
	 * 
	 * verify the enlarged picture to be hidden in the given seconds.
	 * @param picID
	 * @param seconds
	 */
	public void waitEnlargedPictureHidden(String facilityID){
		boolean flag = true;
		int time = SLEEP * 1000;
		Timer timer=new Timer();
		
		while(flag && timer.diffLong()<time) {
			flag = this.checkEnlargedParkPicDisplay(facilityID);
		}
		
		if (flag){
			throw new ErrorOnPageException("The enlarged picture didn't hiddne in " + time + " seconds..");
		}
	}
	
	public void clickParkEnlargedPicture(String facilityID){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression("^popcopybbl.*" + facilityID +".*", false));
		if(null!= objs && objs.length >0){
			objs[0].click();
		}else{
			throw new ErrorOnPageException("can't find the enlarged Picture");
		}
	}
	
	public boolean checkEnlargedParkPicDisplay(String facilityID){
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".id", new RegularExpression("^popcopybbl.*" + facilityID +".*", false));
	}

	/**
	 * Verify first facility view header format based on given Facility Name, Parent Facility or Region and State
	 * @param facilityName
	 * @param parentFacilityOrRegion: Sometimes it is all the capital, but sometime first character of each word is capital  
	 * @param state
	 */
	public void verifyFirstFacilityViewHeaderFormat(String facilityName, String parentFacilityOrRegion, String state){
		this.verifyFirstFacilityViewHeaderFormat(facilityName, parentFacilityOrRegion, state, null);
	}
	
	public void verifyMapStatusMessage(String expectMsg){
		String currentMsg = this.getMapStatusMessage();
		
		if(!currentMsg.equalsIgnoreCase(expectMsg)){
			logger.error("The expect  map status message is:" + expectMsg);
			logger.error("The current map status message is:" + currentMsg);
			throw new ErrorOnPageException("verify map status message failed.");
		}else{
			logger.info("verify map status message successfully.");
		}
	}
	
	public void clickPanUp(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan up");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("can't find pan up object.");
		}
		
		objs[0].click();
		Browser.unregister(objs);
	}
	public void clickPanDown(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan down");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("can't find Pan down object.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void clickPanRight(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan right");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("can't find Pan right object.");
		}
		
		objs[0].click();
		Browser.unregister(objs);
	}
	public void clickPanLeft(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan left");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("can't find Pan left object.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void clickZoomIn(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Zoom in");
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("can't find zoom in object.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void clickZoomOut(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Zoom out");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("can't find zoom out object.");
		}
		
		objs[0].click();
		Browser.unregister(objs);
	}
	
	public void verifyPanUpText(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan up");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("Verify Pan up indicator display text failed.");
		}else{
			logger.info("Verify Pan Up indicator Mouse Over Text successfully.");
		}
		Browser.unregister(objs);
	}
	public void verifyPanDownText(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan down");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("Verify Pan down indicator display text failed.");
		}else{
			logger.info("Verify Pan down indicator Mouse Over Text successfully.");
		}
		Browser.unregister(objs);
	}
	
	public void verifyPanRightText(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan right");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("Verify Pan right indicator display text failed.");
		}else{
			logger.info("Verify Pan right indicator Mouse Over Text successfully.");
		}
		Browser.unregister(objs);
	}
	public void verifyPanLeftText(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Pan left");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("Verify Pan left indicator display text failed.");
		}else{
			logger.info("Verify Pan left indicator Mouse Over Text successfully.");
		}
		Browser.unregister(objs);
	}
	
	public void verifyZoomInText(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Zoom in");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("Verify Zoom in indicator display text failed.");
		}else{
			logger.info("Verify Zoom in indicator Mouse Over Text successfully.");
		}
		Browser.unregister(objs);
	}
	
	public void verifyZoomOutText(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".title", "Zoom out");
		if(objs==null||objs.length<1){
			throw new ErrorOnPageException("Verify Zoom out indicator display text failed.");
		}else{
			logger.info("Verify Zoom out indicator Mouse Over Text successfully.");
		}
		Browser.unregister(objs);
	}

	/**
	 *  1: Hover(mouse) over the Change Map Style indicator(below the previous/next links)	
	 *  2: verify Change map style displays text displays
	 * @param expectText
	 */
	public void verifyChangeMapStyleDisplayText() {
		boolean flag =browser.checkHtmlObjectExists(".class", "Html.DIV", ".title", "Change map style");
		if(flag){
			logger.info("verify change map style display text successfully.");
		}else{
			throw new  ErrorOnPageException("verify change map style display text failed.");
		}
	}

	/**
	 * Click on Change Map Style indicator drop-down
	 */
	public void clickChangeMapStyleDropdownList() {
		IHtmlObject[] objs =browser.getHtmlObject(".class", "Html.DIV", ".title", "Change map style");
		if(null == objs || objs.length <1){
			throw new  ErrorOnPageException("can't find the change map style drop down list beneath the Previous|Next hyperlinks.");
		}
		
		objs[0].click();
		Browser.unregister(objs);
	}

	/**
	 * verify map items in the "Change map style" dropdown list.
	 * The display text is "map" , the display text when hoverover "map" is "Show street map"
	 * 
	 */
	public void verifyMapItemExistInDDList(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "mapviewport")};
		//PS: text and title are the checkpoints, you must be very carefull when modify those two values.
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "Map"), new Property(".title", "Show street map") };
		
		IHtmlObject[] objs =browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new  ErrorOnPageException("can't find map item in the change map style drop down list.");
		}else{
			logger.info("verify Map items in the Change map style dropdown list successfully");
		}
		Browser.unregister(objs);
	}
	
	/**
	 * verify satellite items in the "Change map style" dropdown list.
	 * The display text is "Satellite" , the display text when hoveover "Satellite" is "Show satellite imagery"
	 * 
	 */
	public void verifySatelliteItemExistInDDList(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "mapviewport")};
		//PS: text and title are the checkpoints, you must be very carefull when modify those two values.
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "Satellite"), new Property(".title", "Show satellite imagery") };
		
		IHtmlObject[] objs =browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new  ErrorOnPageException("can't find Satellite item in the change map style drop down list.");
		}else{
			logger.info("verify Satellite items in the Change map style dropdown list successfully");
		}
		Browser.unregister(objs);
	}
	
	/**
	 * verify Terrain items in the "Change map style" drop down list.
	 * The display text is "Terrain" , the display text when hoverover "Terrian" is "Show street map with terrain"
	 * 
	 */
	public void verifyTerrainCheckBoxExistInDDList(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "mapviewport")};
		//PS: text and title are the checkpoints, you must be very carefull when modify those two values.
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "Terrain"), new Property(".title", "Show street map with terrain") };
		//verify the checkbox for Terrian
		Property[] p3 = new Property[]{new Property(".class", "Html.SPAN")};//""Html.INPUT.checkbox")};
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "gmnoprint",".text"," ?Map ? ?Map ? ? Satellite.*"));
		Boolean flag1 =browser.checkHtmlObjectExists(Property.atList(p1,p2));
		Boolean flag2 =browser.checkHtmlObjectExists(Property.atList(p3));
		if(flag1 && flag2){
			logger.info("verify Terrain items in the Change map style dropdown list successfully");
		}else{
			throw new  ErrorOnPageException("verify Terrian items in the change map style dropdown list failed.");
		}
	}
	
	/**
	 * verify Labels check box in the "Change map style" drop down list.
	 * The display text is "Labels" , the display text when hoverover "Terrian" is "Show street map with terrain"
	 * The default status is checked.
	 * 
	 */
	public void verifyLabelsCheckBoxExistInDDList(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "mapviewport")};
		//PS: text and title are the checkpoints, you must be very carefull when modify those two values.
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "Labels"), new Property(".title", "Show imagery with street names") };
		//verify the checkbox for Terrian
		Property[] p3 = new Property[]{new Property(".class", "Html.SPAN")};//"Html.INPUT.checkbox"
		Property[] p4 = new Property[]{new Property(".class", "Html.DIV")};//"Html.INPUT.checkbox"
		
		Boolean flag1 =browser.checkHtmlObjectExists(Property.atList(p1,p2));
		
		//check the checkbox selected be default.
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p2,p3));
		if(null == objs || objs.length <1){
			throw new  ErrorOnPageException("can't find Labels item in the change map style drop down list.");
		}
		
		IHtmlObject[] objs1 =browser.getHtmlObject(p4, objs[0]);
		if(null == objs1 || objs1.length <1){
			throw new  ErrorOnPageException("can't find check box object under Labels item in the change map style drop down list.");
		}
		
//		Boolean selected=((ICheckBox)objs[0]).isSelected();
		String style = objs1[0].style("Display");
		//Selected: style = "" 
		//Not selected: style = "none";
		if(flag1 && StringUtil.isEmpty(style)){
			logger.info("verify Lables items in the Change map style dropdown list successfully");
		}else{
			throw new  ErrorOnPageException("verify Terrian items in the change map style dropdown list failed.");
		}
		
		Browser.unregister(objs, objs1);
	}
	

	/**
	 * select Satellite from the "Change map style" drop down list.
	 */
	public void selectSatellite() {
		IHtmlObject[] objs =browser.getHtmlObject(".class", "Html.DIV" , ".title", "Show satellite imagery");
		
		if(null == objs || objs.length <1){
			throw new  ErrorOnPageException("can't find Satellite item in the change map style drop down list.");
		}else{
			objs[0].click();
		}
		Browser.unregister(objs);
	}
	
	/**
	 * select "Labels" from Satellite dropdown list.
	 */
	public void checkLabelsForSatellite(){
//		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "Labels"), new Property(".title", "Show imagery with street names") };
//		Property[] p2 = new Property[]{new Property(".class", "Html.INPUT.checkbox")};
//		
//		HtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
//		if(null == objs || objs.length <1){
//			throw new  ErrorOnPageException("can't find Lable item in the change map style drop down list.");
//		}
//		ICheckBox label = (ICheckBox)objs[0];
//		label.select();
		
		this.uncheckLabelsForSatellite();
	}
	
	public void removeFocus(){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "facility_view_header_near");
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(objs!=null && objs.length>1){
			objs[1].click();
		}else{
			throw new ObjectNotFoundException("Facility view header near object can't be found.");
		}
	}
	
	/**
	 * unselect "Labels" from Satellite dropdown list.
	 */
	public void uncheckLabelsForSatellite(){
		Property[] p = new Property[]{new Property(".class", "Html.DIV"), new Property(".title", "Change map style")};
		browser.clickGuiObject(p);
		
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "Labels"), new Property(".title", "Show imagery with street names") };
		Property[] p2 = new Property[]{new Property(".class", "Html.SPAN")};

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new  ErrorOnPageException("can't find Lable item in the change map style drop down list.");
		}
		
		objs[0].click();
        Browser.unregister(objs, objs);		
//		ICheckBox label = (ICheckBox)objs[0];
//		label.deselect();
	}

	/**
	 * verify first come first served text displayed on the map bubble widget.
	 */
	public void verifyFirstComeFirstServedExist() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "First-Come-First-Served.")};
	    Boolean flag = browser.checkHtmlObjectExists(Property.atList(p1,p2));
	    if(flag){
	    	logger.info("verify First Come First Served text displayed on the map bubble successfully ");
	    }else{
	    	throw new ErrorOnPageException("verify First come First served text displayed on the bubble failed.");
	    }
	}
	
	/**
	 * Click on "Availability Details" link in the bubble widget.
	 * */
	public void clickAvailabilityDetailsInWidget() {
		this.clickHyperlinkInWidget("Availability Details");
	}

	/**
	 * verify Availability details link displayed on the map bubble widget.
	 */
	public void verifyAvailabilityDetailsExist() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "mapbubble")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"), new Property(".text", "Availability Details")};
	    Boolean flag = browser.checkHtmlObjectExists(Property.atList(p1,p2));
	    if(flag){
	    	logger.info("verify Availability details link displayed on the map bubble successfully ");
	    }else{
	    	throw new ErrorOnPageException("verify Availability details link displayed on the bubble failed.");
	    }		
	}

	/**
	 * verify the location indicator(always display as a Red cross) always be on the map when the user is paging through the result.
	 */
	public void verifyLocationIndicatorExistCrossPages() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".src", new RegularExpression(".*maps/mapmarker_where\\.png",false))};
		int count = 0;
		do{
			boolean flag = browser.checkHtmlObjectExists(Property.atList(p1,p2));
			if(!flag){
				throw new ErrorOnPageException("location indicator should always be on the map when the user is paging through the result");
			}
			count++;
		}while (this.gotoNext());
		
		if(count ==1){
			throw new ErrorOnDataException("we need at least 2 pages to verify check points. please change your search criteria.");
		}
		logger.info("verify location indicator displays on the map when the user is paging through successfully.");
	}

	/**
	 * The small blue pin represent Camping or Day use facilities.
	 */
	public void clickSmallCampingPin() {
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"),new Property(".src",  new RegularExpression(".*maps/mm_20_camping\\.png",false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find the small blue pin on the map.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}

	/**
	 * The small gree pin represnet Recreation facilities.
	 */
	public void clickSmallRecreationPin() {
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapviewport")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"),new Property(".src",  new RegularExpression(".*maps/mm_20_area\\.png",false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find the small green pin on the map.");
		}
		objs[objs.length-1].click();
		Browser.unregister(objs);
	}
	
	public int getSmallTourPinNum() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.mapViewPortDiv(),this.smallTourImg()));
		return objs.length;
	}
	
	public void clickSmallTourPin(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.mapViewPortDiv(),this.smallTourImg()));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find the small red pin on the map.");
		}
		objs[index].click();
		Browser.unregister(objs);
	}
	
	/**
	 * The small Red pin represnet Tour facilities.
	 */
	public void clickSmallTourPin() {
		String src = ".*maps/mm_20_tour\\.png";
//		this.waitMapPinMarkerDisplaying(src);
//		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "mapviewport")};
//		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"),new Property(".src",  new RegularExpression(src,false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.mapViewPortDiv(),this.smallTourImg()));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find the small red pin on the map.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}
	
	/**
	 * The small Purple pin represnet permit facilities.
	 */
	public void clickSmallPermitPin() {
		String src = ".*maps/mm_20_permit\\.png";
//		this.waitMapPinMarkerDisplaying(src);
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"),new Property(".src",  new RegularExpression(src,false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length <1){
			throw new ErrorOnPageException("can't find the small green pin on the map.");
		}
		objs[0].click();
		Browser.unregister(objs);
	}

	@Override
	public String getWaringMes() {
		return this.getMapStatusMessage();
	}
	
	public boolean isGreenNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_map", ".class", "Html.IMG", ".src", greenNoMapPinSRC));
	}
	
	public boolean isBlueNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_map", ".class", "Html.IMG", ".src", blueNoMapPinSRC));
	}
	
	public boolean isRedNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_map", ".class", "Html.IMG", ".src", redNoMapPinSRC));
	}
	
	public boolean isBrownNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_map"),Property.toPropertyArray(".class", "Html.IMG", ".src", brownNoMapPinSRC)));
	}

	/**
	 * when the 2cm in map represent 500km, it's default map. 
	 *Sara[9/5/2013], can't find 2cm and 500km on map from DEFECT-34732 created at 22/May/2012. So update it using \\d+ km and \\d+ mi because the values will be different based on search result
	 */
	public boolean checkDefautMap() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "mapviewport");
//		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^\\d+ km$", false));
//		Property[] p3 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^\\d+ mi$", false));
//		IHtmlObject[] objs1 = browser.getHtmlObject(Property.atList(p1, p2));
//		IHtmlObject[] objs2 = browser.getHtmlObject(Property.atList(p1, p3));
//		if(objs1.length!=1 || objs2.length!=1){
//			return false;
//		}
//		Browser.unregister(objs1, objs2);
		
		//Sara[20140219] No mi info in map, only have km info.
		IHtmlObject[] objs1 = browser.getHtmlObject(Property.atList(p1, km()));
		if(objs1.length!=1){
			return false;
		}
		Browser.unregister(objs1);
		
//		HtmlObject[] objs=browser.getHtmlObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".id", "mapviewport"), Property.toPropertyArray(".class", "Html.DIV", ".text", "500 km")));
//		if(objs==null || objs.length<1){
//			return false;
//		}
//		Browser.unregister(objs);
		return true;
	}
	/**
	 * get the Scale of current map;
	 * @return
	 */
	public int getScaleOfKm(){
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".id", "mapviewport"), km())); 
		//Sara[20140219], Prd changed, no mi, only have km info in map. Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^\\d+ km$",false))));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("");
		}
		int scale= Integer.parseInt(objs[0].text().split(" ")[0]);
		Browser.unregister(objs);
		return scale;
	}
	
    public boolean checkResultNearExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "facility_view_header_near");
	}
    
	/**
	 * Get the search result header
	 * Such as Results near <facility name> [ * in straight line, not driving distance ]
	 * @return
	 */
	public String getResultNearHeaderText() {
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "search_results_map")};
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), 
				new Property(".className", new RegularExpression("facility_view_header_(near|nearest)", false))};
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("can't find result near header info.");
		}
		String value=objs[0].text();
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * get facility name filters info, the return list including info like below
	  	All
		A
		B
		C
		D
		......
	 * @return
	 */
	public List<String> getAllClickableSearchNameFiltersText(){
		List<String> filters = new ArrayList<String>();
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","letters")};
		Property[] p2 = new Property[]{new Property(".class","Html.A")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1));
		IHtmlObject[] objs1 = browser.getHtmlObject(Property.atList(p1,p2));

		if(null == objs1 || objs1.length < 1){
			return filters;
		}
		for(int i =0; i < objs1.length/objs.length; i ++){
			filters.add(objs1[i].text());
		}
		Browser.unregister(objs, objs1);
		return filters;
	}
	
	/**
	 * click the Search name filters. the Search Type filters in the following format
	   	All
		A
		B
		C
        D
        ......		
	 * @param searchNameText
	 * @return
	 */
	public boolean clickSearchNameFilter(String searchNameText){
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","letters")};
		Property[] p2 = new Property[]{new Property(".class","Html.A"),new Property(".text", searchNameText)};
		
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		
		if(null == objs || objs.length < 4){  //1
			return false;
		}
		
		objs[3].click();  //0
		Browser.unregister(objs);
		return true;
	}
	
	/**
	 * Get total search results
	 * @return
	 */
	public int getTotalSearchResultNum(){
		int result = 0;
		IHtmlObject[] objs = browser.getHtmlObject(".className", "usearch_results_label");
		result = Integer.parseInt(objs[0].text().split("of")[1].trim());
		
		Browser.unregister(objs);
		return result;
	}
	
	/**
	 * Verify Total search result
	 * @param expectResults: The expect total search result
	 */
	public void verifyTotalSearchResults(int expectedTotalSearchResult){
		int actualTotalSearchResult = this.getTotalSearchResultNum();
		if(actualTotalSearchResult!=expectedTotalSearchResult){
			throw new ErrorOnDataException("The total search result is incorrect.", String.valueOf(expectedTotalSearchResult),String.valueOf(actualTotalSearchResult));
		}else{
			logger.info("Successfully verify total search result!");
		}
	}
	
	/**
	 * Verify whether the result near header exist or not
	 * @param existed: ---true: The result near header should be existed
	 *                 ---false: The result near header should not be existed
	 */
	public void verifyResultNearHeaderExist(boolean existed){
		boolean actualResult = this.checkResultNearExist();
		if(existed!=actualResult){
			throw new ObjectNotFoundException("The 'Result Near Header' object should "+(existed?"":"not be ")+"exist.");
		}else{
			logger.info("Successfully verify the 'Result Near Header' object is "+(existed?"":"not")+"existed.");
		}
	}
	
	public boolean checkSearchTypeFilterExist(){
		return browser.checkHtmlObjectExists(".className", "filters", ".id", "filters");
	}
	
	public boolean checkSearchNameFilterExist(){
		return browser.checkHtmlObjectExists(".class","Html.DIV", ".className","letters");
	}
	
	/**
	 * Verify Search result label info
	 * @param expectedSearchResultLabel
	 * @return
	 */
	public void verifySearchResultLabel(String expectedSearchResultLabel){
		logger.info("Start to verify search result label should equal to:"+expectedSearchResultLabel)
;		String actualSearchResultLabel = this.getSearchResultsLabel();
		if(!expectedSearchResultLabel.equals(actualSearchResultLabel)){
			throw new ErrorOnDataException("Search result label is incorrect.", expectedSearchResultLabel, actualSearchResultLabel);
		}else{
			logger.info("Successfully verify search result label equals to:"+expectedSearchResultLabel);
		}
	}

	public boolean checkShowAllResultsLinkExist(){
		return 	browser.checkHtmlObjectExists(".class", "Html.A", ".title", "Show all results.");
	}
	
	public boolean checkShowOnlyAvailableSitesLinkExist(){
		return 	browser.checkHtmlObjectExists(".class", "Html.A", ".title", "Show only sites that have availability for your dates.");
	}

	/**
	 * Get first page park site type titles include:
	 * xx matching sites available
	 * First-Come-First-Served.
	 * Reservation via Call center.
	 * @return
	 */
	public List<String> geParkSiteTypeTitlesInFirstPg(){
		List<String> siteTitles = new ArrayList<String>();

		//Get all site type titles information
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("(site_types_title)|(reservation_directive)", false));

		if(objs.length<=0){
			throw new ObjectNotFoundException("Site types title object can't be found.");
		}else{
			for(int i=0; i<objs.length; i++){
				siteTitles.add(objs[i].text()); 
			}
		}
		Browser.unregister(objs);
		return siteTitles;
	}
	
	/**
	 * Get all park site type titles include:
	 * xx matching sites available
	 * First-Come-First-Served.
	 * Reservation via Call center.
	 * @return
	 */
	public List<String> getAllParkSiteTypeTitles(){
		int nextPageIndex=1;
		List<String> siteTitles = new ArrayList<String>();
		do{
			//Go to next page
			if(this.checkNext() && nextPageIndex!=1){
				this.clickNext();
				this.waitLoading();
			}
			
			//Get all site type titles information
			IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("(site_types_title)|(reservation_directive)", false));
			
			if(objs.length<=0){
				throw new ObjectNotFoundException("Site types title object can't be found.");
			}else{
				for(int i=0; i<objs.length; i++){
					siteTitles.add(objs[i].text()); 
				}
			}
			nextPageIndex++;
			Browser.unregister(objs);
		}while(this.checkNext());

		//Go back to first page
		this.gotoFirstPage();
		
		return siteTitles;
	}

	public String getFacilityAvailableWarningMes(){
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "warning_panel");

		if(objs.length>0){
			warnMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Warning message panel Object can't find.");

		Browser.unregister(objs);
		return warnMes;
	}
	
	public void verifyFacilityAvailableWarningMes(String expectedFacilityAvailableWarningMes){
		String actualWarnMes = this.getFacilityAvailableWarningMes();
		if(!actualWarnMes.equals(expectedFacilityAvailableWarningMes)){
			throw new ErrorOnDataException("The actual warning message: "+actualWarnMes+" " +
					"doesn't equal to the expect one:"+expectedFacilityAvailableWarningMes);
		}
	}
	
	/**
	 * Verify whether Search result label info equal to the expected one or not
	 * @param expectedSearchResultLabel --true: actual one equals to expected one
	 *                                  --false: actual one doesn't equal to expected one
	 * @return
	 */
	public void verifySearchResultLabel(String expectedSearchResultLabel, boolean actualValueEqualsToExpected){
		String actualSearchResultLabel = this.getSearchResultsLabel();
		if(expectedSearchResultLabel.equals(actualSearchResultLabel) != actualValueEqualsToExpected){
			throw new ErrorOnDataException("Search result label "+(actualValueEqualsToExpected?"":"doesn't")+" equal to expected one.", expectedSearchResultLabel, actualSearchResultLabel);
		}else{
			logger.info("Successfully verify search result label "+(actualValueEqualsToExpected?"":"doesn't")+" equal to expected one.");
		}
	}

	public boolean checkFacilityAvailableWarningMesExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "warning_panel");
	}
	
	/**
	 * Get search result label via specific facility type filter and page number
	 * @param facilityTypeFilter
	 * @param pageNum: 1--first page
	 *                 2--second page
	 *                 ... 
	 * @return
	 */
	public String generateSearchResultLabelByFacilityTypeFilter(String facilityTypeFilter, int pageNum){
		logger.info("generate search result label via facility type filter: "+facilityTypeFilter+" for page:"+pageNum);
		String searchResultLabel = "";
		int searchResultNum = Integer.parseInt(facilityTypeFilter.split("\\(")[1].replaceAll("\\)", ""));

		if(searchResultNum>(pageNum-1)*10){
			if(searchResultNum>=pageNum*10){
				searchResultLabel = "Search Results: "+Integer.valueOf((pageNum-1)*10+1)+"-"+pageNum*10+" of "+searchResultNum;
			}else{
				searchResultLabel = "Search Results: "+Integer.valueOf((pageNum-1)*10+1)+"-"+searchResultNum+" of "+searchResultNum;
			}
		}else{
			throw new ErrorOnDataException("Actual search result number is less than the expected.", 
					String.valueOf(pageNum*10), String.valueOf(searchResultNum));
		}

		return searchResultLabel;
	}
	
	/**
	 * generate first page search result label via specific facility type filter
	 * @param facilityTypeFilter
	 * @return
	 */
	public String generateFirstPgSearchResultLabelViaFacilityTypeFilter(String facilityTypeFilter){
		return this.generateSearchResultLabelByFacilityTypeFilter(facilityTypeFilter, 1);
	}
	
	/**
	 * Verify search result label info via specific facility type filter
	 * @param facilityTypeFilter
	 */
	public void verifySearchResultLabelViaFacilityTypeFilter(String facilityTypeFilter){
		logger.info("Verify search result label via facility type filter: "+facilityTypeFilter);
		String searchResultLabel = this.generateFirstPgSearchResultLabelViaFacilityTypeFilter(facilityTypeFilter);
		this.verifySearchResultLabel(searchResultLabel);
	}
	
	/**
	 * Verify whether Search name exist or not 
	 * @param expectedExisted   ---true: the actual one equals to the expected one
	 *                          ---false: the actual one doesn't equal to the expected one
	 */
	public void verifySearchNameFilterExist(boolean expectedExisted){
		boolean actualExisted = this.checkSearchNameFilterExist();
		if(actualExisted!=expectedExisted){
			throw new ErrorOnDataException("Search name filter should "+(expectedExisted?"be":"not be")+" existed.");
		}else{
			logger.info("Successfully verify search name "+(expectedExisted?"":"doesn't")+" exist.");
		}
	}

	/**
	 * @return
	 */
	public boolean checkResultNearHeaderMessageExist() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id","mapviewport")};
		Property[] p2=new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "facility_view_header_near")};
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		
		if(objs==null || objs.length<1){
			return false;
		}
		if(objs[0].text().trim().length()<1){
			return false;
		}
		return true;
	}

	/**
	 * ResultHeader:Search Results: 1-10 of 8
	 * @return
	 */
	public boolean checkResultHeaderExist() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id","mapviewport")};
		Property[] p2=new Property[]{new Property(".class", "Html.DIV"),new Property(".id", "usearch_results_header")};
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		
		if(objs==null || objs.length<1){
			return false;
		}
		
		if(objs[0].text().trim().length()<1){
			return false;
		}
		Browser.unregister(objs);
		return true;
	}

	
	public boolean checkMapPinExist() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id","mapviewport")};
		Property[] p2=new Property[]{new Property(".class", "Html.IMG"),new Property(".src", new RegularExpression(".*/images/maps/.*.png",false))};
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null || objs.length<1){
			return false;
		}
		Browser.unregister(objs);
		return true;
	}

	/**
	 * check large map pin exists
	 * @return
	 */
	public boolean checkLargeMapPinExists() {
		Property[] p1=new Property[]{new Property(".class","Html.DIV"),new Property(".id","mapviewport")};
		Property[] p2=new Property[]{new Property(".class", "Html.IMG"),new Property(".src", ".*/images/maps/.*marker.*\\.png")};
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null || objs.length<1){
			return false;
		}
		Browser.unregister(objs);
		return true;
	}
	
	/**
	 * Return the boolean value that if result filter displays
	 * @return
	 */
	public boolean isResultFiltersDisplaying(){
		return browser.checkHtmlObjectExists(".className", "resultsFilters", ".id", "resultsFilters");
	}
	
	/**
	 * Verify if result filter displays
	 * @param displaying
	 */
	public void verifyResultFiltersDisplaying(boolean displaying){
		boolean result = this.isResultFiltersDisplaying();
		if(result!=displaying){
			throw new ErrorOnPageException("Result filters should "+(displaying?"":"not ")+"display");
		}
		logger.info("Successfully verify Result filters "+(displaying?"display":"does't display"));
	}
	
	/**
	 * Get results filter object in the left of this page
	 * @return
	 */
	public IHtmlObject[] getResultsFiltersObject(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "resultsFilters", ".id", "resultsFilters");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("'resultsFilters' object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get item filter object in the left of this page
	 * @param idValue
	 * @return
	 */
	public IHtmlObject[] getItemFilterObject(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "itemFilter");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("'itemFilter' object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Click specific item filter
	 * @param childObjTitleValue
	 * @param parentObjIdValue
	 */
	public void clickItemFilter(String childObjTitleValue){
//		IHtmlObject[] itemFilterObjs = this.getItemFilterObject();
//		IHtmlObject[] detailItemsObjs = null;
//		boolean noMatchedItem = true;
//		
//		for(int i=0; i<itemFilterObjs.length; i++){
////			detailItemsObjs = browser.getHtmlObject(".class", "Html.A", ".title", childObjTitleValue, this.getItemFilterObject()[i]);//Sara[10/28/2013], can't find using Html.A
//			detailItemsObjs = browser.getHtmlObject(".class", "Html.DIV", ".title", new RegularExpression("^"+childObjTitleValue+"$", false), this.getItemFilterObject()[i]);
//			if(detailItemsObjs!=null && detailItemsObjs.length>0){
//				noMatchedItem = false;
//				detailItemsObjs[0].click();
//				break;
//			}
//		}
//		
//		if(noMatchedItem){
//			throw new ErrorOnPageException("Can't find matched item - "+childObjTitleValue);
//		}
		
		//Sara[11/4/2013]
		Property[] p = Property.concatPropertyArray(div(), ".className", "itemFilter", ".title", new RegularExpression("^"+childObjTitleValue+"$", false));
		browser.clickGuiObject(p);
	}
	
	/**
	 * Make type, agency and letter filter
	 * @param typeItem
	 * @param agencyItem
	 * @param letterItem
	 */
	public void filterResults(String typeItem, String agencyItem, String letterItem){
		if(!StringUtil.isEmpty(typeItem)){
			this.clickItemFilter(typeItem);
			Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
			this.waitLoading();
		}
		
		if(!StringUtil.isEmpty(agencyItem)){
			this.clickItemFilter(agencyItem);
			Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
			this.waitLoading();
		}
		
		if(!StringUtil.isEmpty(letterItem)){
			this.clickItemFilter(letterItem);
			Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
			this.waitLoading();
		}
	}
	
	/**
	 * Get filter category objs
	 * @return
	 */
	public IHtmlObject[] getFilterCategoryObjs(){
		Property[] p1 = Property.toPropertyArray(".id", "resultsFilters", ".className", "resultsFilters");
		Property[] p2 = Property.toPropertyArray(".className", "filterCategory");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'filterCategory' object can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Wait clear filter loading complete
	 */
	public void waitClearFilterLoadingComplete(){
		Property[] p = Property.toPropertyArray(".id", "resultsFilters", ".className", "resultsFilters");
		browser.waitExists(p);
	}
	
	/**
	 * Get result filters content
	 * @return
	 */
	public String getResultFiltersContent(){
		IHtmlObject[] objs = this.getResultsFiltersObject();
		String value = objs[0].text();
		
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Verify result filter content
	 * @param expectedContent
	 */
	public void verifyResultFiltersContent(String expectedContent){
		String actualContent = this.getResultFiltersContent();
		if(!expectedContent.equals(actualContent)){
			throw new ErrorOnPageException("Result filters content is wrong.", expectedContent, actualContent);
		}
		logger.info("Successfully verify result filter content:"+expectedContent);
	}
	
	/**
	 * Ger filter structure
	 * @return
	 */
	public String[] getFilterStructures(){
		IHtmlObject[] objs = this.getFilterCategoryObjs();
		String[] valuew = new String[objs.length];
		for(int i=0; i<objs.length; i++){
			valuew[i] = objs[i].text().trim();
		}
		Browser.unregister(objs);
		return valuew;
	}
	
	/**
	 * Get filter structure(type, agency, first letter of name)
	 * @param expectedFilterCategorys
	 */
	public void verifyFilterStructure(String[] expectedFilterCategorys){
		String[] actualFilterCategorys = this.getFilterStructures();
		
		if(expectedFilterCategorys.length!=actualFilterCategorys.length){
			throw new ErrorOnPageException("Compared to string lists have different length", expectedFilterCategorys, actualFilterCategorys);
		}
		for(int i=0; i<expectedFilterCategorys.length; i++){
			if(!expectedFilterCategorys[i].equals(actualFilterCategorys[i])){
				throw new ErrorOnPageException("Filter structure is wrong because "+actualFilterCategorys[i]+" doesn't equal "+expectedFilterCategorys[i]);
			}
			logger.info("Successfully verify "+actualFilterCategorys[i]+" equals "+expectedFilterCategorys[i]);
		}
	}
	
	/**
	 * Get agency filter objs
	 * @return
	 */
	public IHtmlObject[] getAgencyFitersObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", new RegularExpression(agencyFilterItemIDReg, false)));// RegularExpression("A_\\d+", false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("Agency filter object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get First Letter of Name filters objs
	 * @return
	 */
	public IHtmlObject[] getFirstLetterOfNameFitersObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", new RegularExpression("A_[A-Z]+", false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("First letter of Name filter object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get clear filter objs according to gave filter category
	 * @param filterCategory
	 * @return
	 */
	public IHtmlObject[] getClearFilterObjs(String filterCategory){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".title", "Clear filter", ".text", new RegularExpression("[x]"+filterCategory, false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("clear filter object can't be found for filter category - "+filterCategory);
		}
		return objs;
	}
	
	/**
	 * Click clear filter 
	 * @param filterCategory
	 * Filter Your Results
	 * Type
     * Agency
     * First Letter of Name
	 */
	public void clickClearFilter(String filterCategory){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.DIV", 
				".title", new RegularExpression("Clear (all filters|filter)", false),
				".text", new RegularExpression("\\[x\\]"+filterCategory, false)), true);
	}
	
	/**
	 * Clear filter
	 * @param filterCategory
	 */
	public void clearFilter(String filterCategory){
		this.clickClearFilter(filterCategory);
		this.waitClearFilterLoadingComplete();
	}
	
	/**
	 * Private method to get all agency names and numbers
	 * @return
	 */
	public Map<String, Integer> getAllAgencyFiltersNamesAndNums(){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		String nameAndNum, name = "";
        int num = -1;
		
		IHtmlObject[] objs = this.getAgencyFitersObjs();
		for(int i=0; i<objs.length; i++){
			nameAndNum = objs[i].text().trim(); //(38)Bureau of Reclamation
			name = nameAndNum.split("\\)")[1].trim();
			num = Integer.valueOf(nameAndNum.split("\\)")[0].split("\\(")[1].trim());
			map.put(name, num);
		}
		
		Browser.unregister(objs);
		return map;
	}
	
	public int getAgencyFiltersResultTotalNum(){
		int num = 0;
		Map<String, Integer> namesAndnums = this.getAllAgencyFiltersNamesAndNums();
		for(Object o:namesAndnums.keySet()){
			num += namesAndnums.get(o);
		}
		return num;
	}
	
	public List<Integer> getAgencyResultFilterNums(){
		List<Integer> nums = new ArrayList<Integer>();
		Map<String, Integer> namesAndnums = this.getAllAgencyFiltersNamesAndNums();
		for(Object o:namesAndnums.keySet()){
			nums.add(namesAndnums.get(o));
		}
		return nums;
	}
	
	public List<String> getAgencyResultFilterNames(){
		List<String> names = new ArrayList<String>();
		Map<String, Integer> namesAndnums = this.getAllAgencyFiltersNamesAndNums();
		Set<Map.Entry<String, Integer>> set = namesAndnums.entrySet();
		for (Iterator<Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			names.add(entry.getKey());
		}
		return names;
	}
	
	/**
	 * operate page selector
	 * @param pageNum
	 */
	public void OperatePageSelector(String pageNum){
		this.selectPageSelector(pageNum);
		this.waitLoading();
	}
	
	/**
	 * Select page selector
	 * @param pageNum
	 */
	public void selectPageSelector(String pageNum){
		browser.selectDropdownList(".id", "pageSelector", pageNum);
	}
	
	/**
	 *  Generate search result label according gave total num
     * Search Results: 1-10 of 32
     * Search Results: 1-4 of 4
	 * @param totalNum
	 * @return
	 */
	public String generateSearchResultLabel(int totalNum){
		String searchResultLabel = "";
		if(totalNum>=10){
			searchResultLabel = "Search Results: 1-10 of "+String.valueOf(totalNum);
		}else{
			searchResultLabel = "Search Results: 1-"+totalNum+" of "+String.valueOf(totalNum);
		}
		return searchResultLabel;
	}
	
	/**
	 * Operate paging
	 * @param isNextPage: true: click next button
	 *                    false: click previous button
	 */
	public void OperatePaging(boolean isNextPage){
		if(isNextPage){
			this.clickNext();
		}else this.clickPrevious();
		
		this.waitMapLoadingComplete();
		this.waitLoading();
	}
	
	/**
	 * Return boolean value to identifier if the clearing filter display
	 * @param filterCategory
	 * @return
	 */
	public boolean checkClearingFilterExist(String filterCategory){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", 
				".title", new RegularExpression("Clear (filter|all filters)", false), 
				".text", new RegularExpression("\\[x\\]"+filterCategory, false)));
	}

	/**
	 * Verify if the clearing filter display
	 * @param flag: true: display, false:doesn't display
	 * @param filterCategory
	 */
	public void verifyClearingFilterExist(String filterCategory, boolean flag){
		boolean actualValue  = this.checkClearingFilterExist(filterCategory);
		if(flag!=actualValue){
			throw new ErrorOnPageException("Clear filter:"+filterCategory+" should"+(flag?"":" not")+" display");
		}
		logger.info("Successfully verify clear filter:"+filterCategory+(flag?"":" doesn't")+" display");
	}
	
	public int getFacilityTypeFilterOptionResultNum(String title) {
		IHtmlObject[] objs = this.getFacilityTypeFilterOptionLinks(title);
		IHtmlObject[] divs = browser.getHtmlObject(".class", "Html.DIV", ".classname", "dim", objs[0]);
		if (divs == null || divs.length < 1) {
			throw new ErrorOnPageException(title + " facility type filter num div doesn't exist!");
		}
		String text = RegularExpression.getMatches(divs[0].text(), "\\d+")[0];
		Browser.unregister(objs, divs);
		return Integer.valueOf(text);
	}
	
	private IHtmlObject[] getFacilityTypeFilterOptionLinks(String title) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".title", title);
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException(title + " facility type filter doesn't exist!");
		}
		return objs;
	}

	/**
	 * Verify agency filter result total number
	 * @param expectedNum
	 */
	public void verifyAgencyFilterResultTotalNum(int expectedNum){
		int actualNum = this.getAgencyFiltersResultTotalNum();
		if(expectedNum!=actualNum){
			throw new ErrorOnPageException("Agency filter result total number is wrong!", expectedNum, actualNum);
		}
		logger.info("Successfully verify Agency filter result total number:"+actualNum);
	}
	
	/**
	 * Verify first letter of name filter result total number
	 * @param expectedNum
	 */
	public void verifyFirstLetterOfNameFilterResultTotalNum(int expectedNum){
		int actualNum = this.getFirstLetterOfNameFilterResultTotalNum();
		if(expectedNum!=actualNum){
			throw new ErrorOnPageException("First letter of name filter result total number is wrong!", expectedNum, actualNum);
		}
		logger.info("Successfully verify first letter of name filter result total number:"+actualNum);
	}
	
	/**
	 * Get type filter objs
	 * @return
	 */
	public IHtmlObject[] getTypeFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", typeFilterIdRegx));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("Type filter object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get agency filter objs
	 * @return
	 */
	public IHtmlObject[] getAgencyFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", new RegularExpression(agencyFilterItemIDReg, false)));//new RegularExpression("A_\\d+", false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("Agency filter object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get first letter of name objs
	 * @return
	 */
	public IHtmlObject[] getFirstLetterOfNameFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter",".id", new RegularExpression("^A_([A-Z]|#)$", false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("First letter of Name filter object can't be found.");
		}
		return objs;
	}
	
	/**
	 * This is private method to get all agency filter results names and numbers
	 * @return
	 */
	private List<String> getAllAgencyFiltersNamesAndNums(boolean returnAgencyFilters, String filterType){
		List<String> values = new ArrayList<String>();
		String filterAndNum, filter, num = "";
		IHtmlObject[] objs = null;
		
		if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_TYPE)){
			objs = this.getTypeFilterObjs();
		}else if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_AGENCY)){
			objs = this.getAgencyFilterObjs();
		}else if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME)){
			objs = this.getFirstLetterOfNameFilterObjs();
		}else throw new ErrorOnPageException("No matched fitler type can be found.");
		
		for(int i=0; i<objs.length; i++){
			filterAndNum = objs[i].text().trim();
			filter = filterAndNum.split("\\(\\d+\\)")[1].trim();
			num = filterAndNum.split(filter)[0].replaceAll("\\(", "").replaceAll("\\)", "").trim();
			if(returnAgencyFilters){
				values.add(filter);
			}else values.add(num);
		}

		Browser.unregister(objs);
		return values;
	}
	
	/**
	 * Get agency filter objs according to the gave agency filter option
	 * @param agencyFilterOption
	 * @return
	 */
	public IHtmlObject[] getAgencyFiltersObjs(String agencyFilterOption){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".text", new RegularExpression("\\(\\d+\\) ?"+agencyFilterOption, false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("Agency filter options "+agencyFilterOption+" object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get agency filter option number
	 * @param agencyOption
	 * @return
	 */
	public int getAgencyFilterOptionNum(String agencyOption){
		IHtmlObject[] objs = this.getAgencyFiltersObjs(agencyOption);
		 String agencyFilterAndNum = objs[0].text().trim();
		int num = Integer.valueOf(agencyFilterAndNum.split(agencyOption)[0].replaceAll("\\(", "").replaceAll("\\)", "").trim());

		Browser.unregister(objs);
		return num;
	}
	
	/**
	 * Get facility type filter result total number
	 * @return
	 */
	public int getFacilityTypeFilterResultTotalNum(){
		List<String> nums = this.getAllAgencyFiltersNamesAndNums(false, UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		int num = 0;
		for(int i=0; i<nums.size(); i++){
			num += Integer.valueOf(nums.get(i));
		}
		return num;
	}
	
	/**
	 * Verify facility type filter result total number
	 * @param expectedNum
	 */
	public void verifyFacilityTypeFilterResultTotalNum(int expectedNum){
		int actualNum = this.getFacilityTypeFilterResultTotalNum();
		if(expectedNum!=actualNum){
			throw new ErrorOnPageException("Facility type filter result total number is wrong!", expectedNum, actualNum);
		}
		logger.info("Successfully verify Facility type filter result total number:"+actualNum);
	}
	
	/**
	 * Get first letter of name filter result total number
	 * @return
	 */
	public int getFirstLetterOfNameFilterResultTotalNum(){
		List<String> nums = this.getAllAgencyFiltersNamesAndNums(false, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		int num = 0;
		for(int i=0; i<nums.size(); i++){
			num += Integer.valueOf(nums.get(i));
		}
		return num;
	}
	
	/**
	 * Get first letter of name filter objs according to the gave agency filter option
	 * @param option
	 * @return
	 */
	public IHtmlObject[] getFirstLetterOfNameFiltersObjs(String option){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", new RegularExpression("A_"+option.toUpperCase(), false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("First letter of name filter options "+option+" object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get first letter of name filter option number
	 * @param option
	 * @return
	 */
	public int getFirstLetterOfNameFilterOptionNum(String option){
		IHtmlObject[] objs = this.getFirstLetterOfNameFiltersObjs(option);
		 String filterAndNum = objs[0].text().trim();
		int num = Integer.valueOf(filterAndNum.split(option)[0].replaceAll("\\(", "").replaceAll("\\)", "").trim());

		Browser.unregister(objs);
		return num;
	}
	
	/**
	 * Get only type filter objs
	 * @return
	 */
	public IHtmlObject[] getOnlyTypeFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "defaultItem", ".id", typeFilterIdRegx));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Type filter object can't be found.");
		}
		if(objs.length!=1){
			throw new ObjectNotFoundException("Only one Type filter object should be found.");
		}
		return objs;
	}
	
	/**
	 * Get only type filter option content
	 * @return
	 */
	public String getOnlyTypeFilterName(){
		IHtmlObject[] objs = this.getOnlyTypeFilterObjs();
		String value = objs[0].text().trim();

		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Verify only type filter name
	 * @param expectedName
	 */
	public void verifyOnlyTypeFilterName(String expectedName){
		String actualName = this.getOnlyTypeFilterName();
		if(!expectedName.equals(actualName)){
			throw new ErrorOnPageException("Only type filter name is wrong!", expectedName, actualName);
		}
		logger.info("Successfully verify only type filter name:"+actualName);
	}
	
	/**
	 * Get only agency filter option content
	 * @return
	 */
	public String getOnlyAgencyFilterName(){
		IHtmlObject[] objs = this.getOnlyAgencyFilterObjs();
		String value = objs[0].text().trim();

		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Verify only agency filter name
	 * @param expectedName
	 */
	public void verifyOnlyAgencyFilterName(String expectedName){
		String actualName = this.getOnlyAgencyFilterName();
        if(!expectedName.equals(actualName)){
        	throw new ErrorOnPageException("Only agnecy filter name is wrong!", expectedName, actualName);
        }		
		logger.info("Successfully verify only agency filter name:"+actualName);
	}
	
	/**
	 * Get only agency filter objs
	 * @return
	 */
	public IHtmlObject[] getOnlyAgencyFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "defaultItem", ".id", new RegularExpression(agencyFilterItemIDReg, false))); //new RegularExpression("A_\\d+", false)));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Agency filter object can't be found.");
		}
		if(objs.length!=1){
			throw new ObjectNotFoundException("Only one Agency filter object should be found.");
		}
		return objs;
	}
	
	/**
	 * Go to map bubble widget 
	 * @param contractCode
	 * @param parkId
	 */
	public void gotoMapBubbleWidget(String contractCode, String parkId){
        logger.info("Go to map bubble widget.");
		this.clickMapPin(contractCode, parkId);
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
		this.mapBubbleWidgetWaitExists();
	}
	
	/**
	 * Get park agency objs
	 * @return
	 */
	public IHtmlObject[] getparkAgencyObjsFromMapBubbleWidget(){
		Property[] p = Property.concatPropertyArray(div(), ".id", "mapviewport"); //Sara [11/4/2013] add parent object 
		Property[] p1 = Property.concatPropertyArray(span(), ".className", "agency");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p,p1));
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Patk agency objs can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Get Park agency
	 * @return
	 */
	public String getParkAgencyFromMapBubbleWidget() {
		IHtmlObject[] facilityName =getparkAgencyObjsFromMapBubbleWidget();
		String value = facilityName[0].text().trim().replaceAll(" ?, ?", "");

		Browser.unregister(facilityName);
		return value;
	}
	
	public void verifyShowOnlyAvailableSitesLinkExist(){
		boolean actualResult = this.checkShowOnlyAvailableSitesLinkExist();
		if(!actualResult){
			throw new ErrorOnPageException("'Show only available sites' available filter doesn't display.");
		}
		logger.info("'Show only available sites' available filter successfully display.");
	}
	
	/**
	 * Filter available filter in view as list
	 * @param showAllResults true: click "Show all results" link
	 *                       false: click "Show only available sites" link
	 */
	public void filterAvailabilityFilter(boolean showAllResults){
		if(showAllResults){
			this.clickShowAllResults();
		}else{
			 this.clickShowOnlyAvailableSites();
		}
		this.waitLoading();
	}
	
	public void verifyShowAllResultsLinkExist(){
		boolean actualResult = this.checkShowAllResultsLinkExist();
		if(!actualResult){
			throw new ErrorOnPageException("'Show All Results' available filter doesn't display.");
		}
		logger.info("'Show All Results' available filter successfully display.");
	}
	
	/**
	 * Verify no availability filter displays
	 */
	public void verifyNoAvailabilityFilterDisplay(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "warning_panel");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".title", 
				new RegularExpression("(Show only sites that have availability for your dates.|Show all results.)", false));
		if(browser.checkHtmlObjectExists(Property.atList(p1, p2))){
			throw new ErrorOnPageException("Availability filter should not be display.");
		}
		logger.info("Availability filter doesn't display.");
	}
	
	public void clickMapFooterDiv() {
		browser.clickGuiObject(".class", "Html.DIV", ".id", "usearch_results_map_footer");
	}
	
	public boolean isTourTitleOnMapBubbleExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.mapBubbleDiv(), this.tourTitleDiv()));
	}
	
	public boolean isCheckAvailBtnOnMapBubbleExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.mapBubbleDiv(), this.checkAvailLink()));
	}
	
	public String getFacilityMapViewNearMsg() {
		return browser.getObjectText(Property.atList(this.mapDiv(), this.viewHeaderNearDiv()));
	}
	
	public boolean isMapPinExist(String contractCode, String parkId) {
		return browser.checkHtmlObjectExists(mapPinImg(contractCode, parkId)) && browser.checkHtmlObjectExists(mapPinLink(contractCode, parkId));
	}
	
	public void verifyMapPinExist(String contractCode, String parkId, boolean isExist) {
		boolean actual = this.isMapPinExist(contractCode, parkId);
		String msg = isExist ? "" : "NOT";
		if (isExist != actual) {
			throw new ErrorOnPageException("Map Pin for park id=" + parkId + " should " + msg + " exist!");
		}
		logger.info("Map Pin for park id=" + parkId + " does " + msg + " exist!");
	}
	
	public boolean isFacilityViewContentDisplayed() {
		return browser.checkHtmlObjectDisplayed(Property.atList(this.mapBubbleDiv(), this.viewContentDiv()));
	}
}
