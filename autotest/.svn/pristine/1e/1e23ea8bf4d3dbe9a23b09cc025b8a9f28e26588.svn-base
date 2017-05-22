package com.activenetwork.qa.awo.pages.web.uwp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jdu
 * @Date  Sep 22, 2011
 */
public class UwpViewAsListCommonPage extends UwpUnifiedSearchResultCommonPage {

	private String distanceMatch = ".*\\[(|\\d+\\.)\\d+miles\\*\\].*";
	private RegularExpression viewHeaderNearRegx = new RegularExpression("facility_view_header_near(est|)", false);
	private RegularExpression typeFilterIdRegx = new RegularExpression("(camping|permit|tour|other)", false);
	private Property[] searchResultListProp = Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_list"); //In view as list page, it should be "search_results_list", not "facilities_suggestions_list"
//	private Property[] searchResultListProp = Property.toPropertyArray(".class", "Html.DIV", ".id", "facilities_suggestions_list");
	private Property[] facilityViewCardProp = Property.toPropertyArray(".class","Html.DIV",".className", new RegularExpression("(eu)?facility_view_card", false));
	private String parkHasAvailableSiteMes = "1 matching sites available";
    private String agencyFilterItemIDReg = "A_(NRSO)?:(-)?\\d+";
    
	private static UwpViewAsListCommonPage _instance = null;

	protected UwpViewAsListCommonPage() {
	}

	public static UwpViewAsListCommonPage getInstance() {
		if (null == _instance)
			_instance = new UwpViewAsListCommonPage();

		return _instance;
	}

	protected Property[] mapPinImg(String contractCode) {
		return Property.concatPropertyArray(this.img(), ".id", new RegularExpression("usrpin_"+contractCode.toUpperCase()+"\\d+"+"(_\\d+)?", false),
				".title", "View on Map");
	}
	
	protected Property[] mapPinImg(String contractCode, String parkID) {
		return Property.concatPropertyArray(this.img(), ".id", new RegularExpression("usrpin_"+contractCode.toUpperCase()+parkID+"(_\\d+)?", false),
				".title", "View on Map");
	}
	
	protected Property[] marinaCheckAvailability(String parkId, String contractCode){
		return Property.concatPropertyArray(a(), ".className", new RegularExpression("check_available|book_now", false), ".href", new RegularExpression(".*interface=checkmarina&contractCode=" + contractCode + "&parkId=" + parkId + ".*", false));
	}
	
	public boolean exists() {
		//According to URL(.../unifSearchResults.do....), assign this warning message in this page
		boolean exists = browser.checkHtmlObjectExists(".className", "msg warning");

		if(!exists)
//			exists = browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.DIV",".id","search_results_list",".className","search_results_list"));
		    exists = browser.checkHtmlObjectExists(facilityViewCardProp);
		
		return exists;
	}

	public String getFirstParkIdOnMap(String contractCode){
	      IHtmlObject[] objs = browser.getHtmlObject(mapPinImg(contractCode));
	      if(objs.length<1){
	    	  throw new ObjectNotFoundException("Can't find any park on map");
	      }
	      String value = objs[0].getProperty(".id");
	      Browser.unregister(objs);
	      return  RegularExpression.getMatches(value, "\\d+")[0];
	}
	
	public boolean isParkOnMap(String contractCode, String parkID){
		return browser.checkHtmlObjectExists(mapPinImg(contractCode, parkID));
	}
	
	public boolean waitForSpecificParkDisplay(String contractCode, String facilityID){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".className", "facility_view_card", 
				".id", new RegularExpression("facility_view_card_"+contractCode+facilityID+"(|_\\d+)", false)));
	}

	public boolean isGreenNoMapPinExists(String facilityName){
		boolean flag=false;
		IHtmlObject[] objs=browser.getHtmlObject(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facilityName+".*",false));
		flag=browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", this.greenNoMapPinSRC,objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	public boolean isBlueNoMapPinExists(String facilityName){
		boolean flag=false;
		IHtmlObject[] objs=browser.getHtmlObject(".className", "facility_view_card", ".text", new RegularExpression("^"+facilityName+".*",false));
		flag=browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", this.blueNoMapPinSRC,objs[0]);
		Browser.unregister(objs);
		return flag;}

	public boolean isRedNoMapPinExists(String facilityName){
		boolean flag=false;
		IHtmlObject[] objs=browser.getHtmlObject(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facilityName+".*",false));
		flag=browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", this.redNoMapPinSRC,objs[0]);
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean isRedNoMapPinExists(String contractCode, String parkid){
		boolean flag=false;
		IHtmlObject[] objs=browser.getHtmlObject(".className", "facility_view_card", ".id", new RegularExpression("facility_view_card_"+contractCode+parkid+"(_\\d+)?",false));
		flag=browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", this.redNoMapPinSRC,objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	public boolean isBrownNoMapPinExists(String facilityName){
		boolean flag=false;
		IHtmlObject[] objs=browser.getHtmlObject(".className", "facility_view_header", ".text", new RegularExpression(" ?"+facilityName+".*",false));
		flag=browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", this.brownNoMapPinSRC,objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	public boolean isNoMapPinExists(String facilityName){
		boolean flag=false;
		RegularExpression noMapPinSRC=new RegularExpression(".*marker.*NoMap.png",false);
		IHtmlObject[] objs=browser.getHtmlObject(".className", "facility_view_header", ".text", new RegularExpression(" ?"+facilityName+".*",false));
		flag=browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", noMapPinSRC,objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	public void verifyNoMapPinExist(String facilityName){
		logger.info("Verify there is no-map pin for park:"+facilityName);
		if(!isNoMapPinExists(facilityName)){
			throw new ErrorOnPageException("Pin for park "+facilityName+" should be no-map pin");
		}
	}

	public void clickParkName(String facilityName){
		Property[] p = Property.toPropertyArray(
				".class", "Html.A",
				".className", "facility_link",
				".text", new RegularExpression(facilityName.replace("(", "\\(").replace(")", "\\)"), false)
		);
		browser.clickGuiObject(p,true);
	}

	public void clickFirstParkName(){
		browser.clickGuiObject(".class", "Html.A", ".className", "facility_link", true);
	}

	/**
	 * click the parent's facility name based on given child facility's contract code and facilityID info.
	 * @param contractCode
	 * @param facilityID
	 */
	public void clickParentParkName(String contractCode, String facilityID){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV", ".id",new RegularExpression("facility_view_card_" + contractCode + facilityID,false));
		IHtmlObject[] parentObjs = browser.getHtmlObject(".className", "facility_parent_link", topObjs[0]);

		parentObjs[0].click();	
		Browser.unregister(topObjs,parentObjs);
	}

	/**
	 * check the given facility's parent name is a clickable hyperlink or not. return true when it's a hyperlink.
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public boolean checkParentNameClickable(String contractCode, String facilityID){
		boolean flag = false;
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV", ".id",new RegularExpression("facility_view_card_" + contractCode + facilityID,false));
		IHtmlObject[] parentNameLink = browser.getHtmlObject(".class","Html.A",".className", "facility_parent_link", topObjs[0]);
		if(null != parentNameLink && parentNameLink.length >0){
			flag = true;
		}
		return flag;
	}

	/**
	 * get amenities and activities info based on facility's contract code and facilityID info.
	 * if there is no amenities info return "";
	 * @param contractCode
	 * @param facilityID
	 */
	public String getAmenitiesAndActivities(String contractCode, String facilityID){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV", 
				".id",new RegularExpression("^amenities_(activities|services)_" + contractCode + facilityID+"(|_\\d+)",false));
		String amenities = "";                                            
		if(topObjs.length >0){
			IHtmlObject[] amenityObjs = browser.getHtmlObject(".class", "Html.SPAN", topObjs[0]);
			for(int i=0; i<amenityObjs.length; i++){
				amenities = amenities +" "+ amenityObjs[i].text();
			}
			Browser.unregister(amenityObjs);
		}

		Browser.unregister(topObjs);
		return amenities.trim();
	}
	

	/**
	 * Expanding and Contracting of the description will not further impact the display of the Amenities and Activities
	 * Please make sure park with short description firstly, then click it to get the full description
	 * @return activities info after expanding
	 */
	public String verifyImpactingActivitiesExpandingContractingDescription(String contractCode, String parkId){
		String activitiesAndAmenities = this.getAmenitiesAndActivities(contractCode, parkId);

		this.clickParkDescription(contractCode, parkId);
		this.waitParkDescriptionDetail(contractCode, parkId);

		String expandingActivitiesAmenities = this.getAmenitiesAndActivities(contractCode,parkId);

		if(!activitiesAndAmenities.equals(expandingActivitiesAmenities)){
			throw new ErrorOnDataException("Activities and Amenities should be fully displayed.");
		}
		
		return expandingActivitiesAmenities;
	}

	/**
	 * Get top park amenities and activities info
	 * @return
	 */
	public String[] getTopParkActivitiesAndAmenities(){
		String[] amenities =null;
		IHtmlObject[] topObjs = browser.getHtmlObject(".className", "amenities_list");
		IHtmlObject[] amenityObjs = browser.getHtmlObject(".class", "Html.SPAN", topObjs[0]);
		amenities = new String[amenityObjs.length];
		for(int i=0; i<amenityObjs.length; i++){
			amenities[i] = amenityObjs[i].text().trim();
		}
		return amenities;
	}

	/**
	 * get the first park amenties info.
	 * the amentites info is in the format of: Has sites with:  Electric Hook-up(30), Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).
	 * @return
	 */
	public String getFirstParkAmenities(){
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

	/**
	 * Verify all selected activities display in the "Amenity and Activities" of top park
	 * @param amenitiesAndActivities_Expected: the selected activities
	 */
	public void verifyTopParkHasAllSelectedActivities(String[] amenitiesAndActivities_Expected){
		String[] amenitiesAndActivities_Actual = this.getTopParkActivitiesAndAmenities();
		
		for(int i=0; i<amenitiesAndActivities_Expected.length; i++){
			if(!Arrays.asList(amenitiesAndActivities_Actual).contains(amenitiesAndActivities_Expected[i])){
				throw new ErrorOnPageException("The selected activity:"+amenitiesAndActivities_Expected[i]+" doesn't display.");
			}
			logger.info("Successfully verify the selected activity:"+amenitiesAndActivities_Expected[i]+" display.");
		}
		
	
	
	}
	
	public void verifyFirstParkAmenities(String expectSiteAmenities){
		String actualSiteAmenities = this.getFirstParkAmenities();

		if(!actualSiteAmenities.equalsIgnoreCase(expectSiteAmenities)){
			throw new ErrorOnDataException("The expect site amenity is:" + expectSiteAmenities + "; while the current site amenity is:" + actualSiteAmenities);
		}
	}

	public String getParkAmenitiesByParkName(String parkName){
		String amenities ="";
		parkName=parkName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".text", new RegularExpression("^"+parkName+".*",false)),new Property(".className","facility_view_card")};
		IHtmlObject[] facilityCards=browser.getHtmlObject(p);

		if(facilityCards==null||facilityCards.length<1){
			throw new ObjectNotFoundException("Can't find Facility Card DIV by park name "+parkName);
		}
		Property[] p2=new Property[]{new Property(".class", "Html.DIV"),new Property(".id",new RegularExpression("sites_amenities_.*",false)),new Property(".className","sites_amenities")};
		IHtmlObject[] amenityObjs = browser.getHtmlObject(p2,facilityCards[0]);
		if(amenityObjs==null||amenityObjs.length<1){
			throw new ObjectNotFoundException("Can't find Amenities DIV");
		}
		amenities = amenityObjs[0].text().trim();
		Browser.unregister(amenityObjs);
		return amenities;
	}

	public String getParkActivitiesAndAmenities(String contractCode, String facilityId){
		List<Property[]> pList = new ArrayList<Property[]>();
		Property[] p1 = new Property[1];
		p1[0] = new Property(".id", new RegularExpression("amenities_(activities|services)_"+contractCode+""+facilityId+"(|_\\d+)", false));
		Property[] p2 = new Property[2];
		p2[0] = new Property(".class", "Html.DIV");
		p2[1] = new Property(".className", "amenByDist");
		pList.add(p1);
		pList.add(p2);
		IHtmlObject[] amenities = browser.getHtmlObject(pList);
		String value=amenities[0].text();
		Browser.unregister(amenities);

		return value;
	}

	/**
	 * Get all parks amenities and activities info
	 * @return
	 */
	public List<String> getAmenitiesAndActivities(){
		List<String> amenities = new ArrayList<String>();
		do{
			IHtmlObject[] topObjs = browser.getHtmlObject(".className", "amenities_list");
			if(topObjs.length>0){
				for(int i=0; i<topObjs.length; i++){
					IHtmlObject[] amenityObjs = browser.getHtmlObject(".class", "Html.SPAN", topObjs[i]);
					String amenty = "";
					for(int j=0; j<amenityObjs.length; j++){
						amenty = amenty +" "+ amenityObjs[j].text();
					}
					amenities.add(amenty.trim());
				}
			}else throw new ObjectNotFoundException("Object can't be found.");
		}while(this.gotoNext());

		return amenities;
	}

	/**
	 * Get all facility view header info: Facility Name + State + Parent Facility/Region
	 * Format: 'Facility Name', 'State' part of 'Parent Facility/Region'
	 * @return
	 */
	public List<String> getAllFacilityViewHeader(){
		List<String> facilityViewHeaders = new ArrayList<String>();

		do{
			IHtmlObject[] facilityViewHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header");
			if(facilityViewHeader.length>0){
				for(int i=0; i<facilityViewHeader.length; i++){
					facilityViewHeaders.add(facilityViewHeader[i].getProperty(".text"));
				}
			}else throw new ObjectNotFoundException("Facility view card object can't find.");
			Browser.unregister(facilityViewHeader);
		}while(this.gotoNext());

		this.gotoFirstPage();
		return facilityViewHeaders;
	}
	
	public List<String> getFirstPgFacilityViewHeader(){
		List<String> facilityViewHeaders = new ArrayList<String>();

		IHtmlObject[] facilityViewHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header");
		if(facilityViewHeader.length>0){
			for(int i=0; i<facilityViewHeader.length; i++){
				facilityViewHeaders.add(facilityViewHeader[i].getProperty(".text"));
			}
		}else throw new ObjectNotFoundException("Facility view card object can't find.");

		Browser.unregister(facilityViewHeader);
		return facilityViewHeaders;
	}

	public void verifyAllParkWithDistance(){
		List<String> facilityViewHeaders = this.getAllFacilityViewHeader();
		for(int i=0; i<facilityViewHeaders.size(); i++){
			if(!facilityViewHeaders.get(i).matches(distanceMatch)){
				throw new ErrorOnDataException("Facility header info "+facilityViewHeaders.get(i)+" should have distance info.");
			}
		}
	}

	public void verifyAllParkWithDistanceOtherThanTop(){
		List<String> facilityViewHeaders = this.getAllFacilityViewHeader();
		for(int i=1; i<facilityViewHeaders.size(); i++){
			if(!facilityViewHeaders.get(i).matches(distanceMatch)){
				throw new ErrorOnDataException("Facility header info "+facilityViewHeaders.get(i)+" should have distance info.");
			}
		}
	}
	
	public void verifyFirstPgParkWithDistanceOtherThanTop(){
		List<String> facilityViewHeaders = this.getFirstPgFacilityViewHeader();
		for(int i=1; i<facilityViewHeaders.size(); i++){
			if(!facilityViewHeaders.get(i).matches(distanceMatch)){
				throw new ErrorOnDataException("Facility header info "+facilityViewHeaders.get(i)+" should have distance info.");
			}
		}
	}
	
	public boolean isDistanceDisplaysInFirstPg(){
		List<String> facilityViewHeaders = this.getFirstPgFacilityViewHeader();
		boolean result = false;
		
		for(int i=1; i<facilityViewHeaders.size(); i++){
			if(facilityViewHeaders.get(i).matches(distanceMatch)){
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public void verifyFirstPgParksOtherThanTopWithDistance(){
		List<String> facilityViewHeaders = this.getFirstPgFacilityViewHeader();
		for(int i=1; i<facilityViewHeaders.size(); i++){
			if(!facilityViewHeaders.get(i).matches(distanceMatch)){
				throw new ErrorOnDataException("Facility header info "+facilityViewHeaders.get(i)+" should have distance info.");
			}
		}
	}

	public void verifyAllParkWithoutDistance(){
		List<String> facilityViewHeaders = this.getAllFacilityViewHeader();
		for(int i=0; i<facilityViewHeaders.size(); i++){
			if(facilityViewHeaders.get(i).matches(distanceMatch)){
				throw new ErrorOnPageException("Facility header info "+facilityViewHeaders.get(i)+" should not have distance info.");
			}
		}
		logger.info("Successfully verify all facility header is without distance info.");
	}
	
	public void verifyFirstPgParksWithoutDistance(){
		List<String> facilityViewHeaders = this.getFirstPgFacilityViewHeader();
		for(int i=0; i<facilityViewHeaders.size(); i++){
			if(facilityViewHeaders.get(i).matches(distanceMatch)){
				throw new ErrorOnPageException("Facility header info "+facilityViewHeaders.get(i)+" should not have distance info.");
			}
		}
		logger.info("Successfully verify all facility header is without distance info.");
	}

	public void verifyFirstParkWithoutDistance(){
		if(this.getFirstFacilityViewHeader().matches(distanceMatch)){
			throw new ErrorOnDataException("The First park should not contains distance info.");
		}
		logger.info("Successfully verify first facility header is without distance info.");
	}

	public void verifySecondParkWithoutDistance(){
		if(this.getAllFacilityViewHeader().get(1).matches(distanceMatch)){
			throw new ErrorOnDataException("The Second park should don't contains distance info.");
		}
		logger.info("Successfully verify the second facility header is without distance info.");
	}
	
	public void verifySecondParkWithDistance(){
		if(!this.getAllFacilityViewHeader().get(1).matches(distanceMatch)){
			throw new ErrorOnDataException("The Second park should contains distance info.");
		}
		logger.info("Successfully verify the second facility header is with distance info.");
	}

	/**
	 * Get The first facility view header info: Facility Name + State + Parent Facility/Region
	 * Format: 'Facility Name', 'State' part of 'Parent Facility/Region'
	 * @return
	 */
	public String getFirstFacilityViewHeader(){
		IHtmlObject[] facilityViewHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header");
		String firstFacilityViewHeaderInfo =	facilityViewHeader[0].getProperty(".text");

		Browser.unregister(facilityViewHeader);
		return firstFacilityViewHeaderInfo;
	}

	/**
	 * Get view as list tab page search result summary header info, such as "Search Results: 1-10 of 10"
	 * @return
	 */
	public String getSearchResultsLabel(){
		IHtmlObject[] searchResultHeaderTop = browser.getHtmlObject(searchResultListProp);
		if(searchResultHeaderTop!=null & searchResultHeaderTop.length<1){
			throw new ObjectNotFoundException("Search result list object can't be found.");
		} 

		IHtmlObject[] searchResultHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "usearch_results_label",searchResultHeaderTop[0]);
		if(searchResultHeader!=null & searchResultHeader.length<1){
			throw new ObjectNotFoundException("Search result label object can't be found.");
		} 

		String headerText = searchResultHeader[0].text().trim();
		Browser.unregister(searchResultHeaderTop,searchResultHeader);
		return headerText;
	}

	/**
	 * Verify Search result label info equal to the expected one
	 * @param expectedLabel
	 * @return
	 */
	public void verifySearchResultLabelEquals(String expectedLabel){
		String actualLabel = this.getSearchResultsLabel();
		if(!expectedLabel.equals(actualLabel)){
		    throw new ErrorOnDataException("Actual search result label should equal to the expected one.", expectedLabel, actualLabel);
		}
		logger.info("Successfully to verify search result label equals to "+expectedLabel);
	}
	
	/**
	 *  Verify Search result label info doesn't equal to the expected one
	 * @param expectedLabel
	 * @return
	 */
	public void verifySearchResultLabelNotEquals(String expectedLabel){
		String actualLabel = this.getSearchResultsLabel();
		if(expectedLabel.equals(actualLabel)){
		    throw new ErrorOnDataException("Actual search result label should not equal to the expected one.", expectedLabel, actualLabel);
		}
		logger.info("Successfully to verify search result label doesn't equal to "+expectedLabel);
	}

	public boolean checkResultNearExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", viewHeaderNearRegx);
	}

	public boolean checkNearByResultsExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Results near.*");
	}

	public IHtmlObject[] getViewHeaderNearObjs(){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header_nearest");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", viewHeaderNearRegx);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'facility_view_header_near(est)' object can't be found.");
		}
		return objs;
	}

	/**
	 * Get view as list tab page facility near by summary info, such as "Results part of Yosemite National Park"
	 * @return
	 */
	public String getViewHeaderNearValue(){
		IHtmlObject[] viewHeaderNear = this.getViewHeaderNearObjs();
		String headerNearText = viewHeaderNear[0].text().trim();
		Browser.unregister(viewHeaderNear);
		return headerNearText;
	}

	/**
	 * verify the facility view header near message info.
	 * such as 
	 * "Results part of Yosemite National Park"
	 * "Results within MINNESOTA"
	 * @param expectMsg
	 */
	public void verifyViewHeaderNearValue(String expectMsg){
		String currentMsg = this.getViewHeaderNearValue();
		if(currentMsg.equalsIgnoreCase(expectMsg)){
			logger.info("verify facility view header near message successfully.");
		}else{
			throw new ErrorOnPageException("verify facility view header near message failed.", expectMsg, currentMsg);
		}
	}

	public String getFacilityViewHeader(String parkName){
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),
				new Property(".className", "facility_view_header"),
				new Property(".text",new RegularExpression("^"+parkName.toUpperCase()+".*",false))};
		IHtmlObject[] viewHeader = browser.getHtmlObject(p);
		String headerText = viewHeader[0].text().trim();
		Browser.unregister(viewHeader);
		return headerText;
	}

	public String getFacilityViewHeader(String contractCode, String facilityID){
		logger.info("Get facility (ID:"+facilityID+") View Header info...");
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id",new RegularExpression("facility_view_card_"+contractCode+facilityID+"(_\\d+)?", false));
		Property[] p2=Property.toPropertyArray(".class", "Html.DIV",".className", "facility_view_header");

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));

		String headerText = objs[0].text().trim();
		Browser.unregister(objs);
		return headerText;
	}

	/**Get facility View list content*/
	public String getFacilityViewListContent(){
		String content = "";
		IHtmlObject[] objs = browser.getHtmlObject(searchResultListProp);
		content = objs[0].text().toString();

		Browser.unregister(objs);
		return content;
	}

	/**
	 * Get park view card (header and content) info according to contract code and facility id
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public String getParkViewCard(String contractCode, String facilityID){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "facility_view_card", 
				".id", new RegularExpression("facility_view_card_"+contractCode+facilityID+"(|_\\d+)", false));
		String parkViewCard = objs[0].text();

		Browser.unregister(objs);
		return parkViewCard;
	}

	/**
	 * verify the first check availability button display below site types DIV
	 */
	public boolean checkFirstCheckAvailabilityBelowSiteTypes(){

		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_content");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", topObjs[0]);
		boolean flag = true;
		int siteTypeIndex = 0;
		int availabilityIndex = 0;

		for(int i = 0 ; i <objs.length; i ++){
			if(objs[i].getProperty("className").startsWith("summary_sites_list")){
				siteTypeIndex = i;
			}
			if(objs[i].getProperty("className").startsWith("check_avail_panel")){
				availabilityIndex = i;
			}
		}

		if(siteTypeIndex >= availabilityIndex){
			flag = false;
		}

		Browser.unregister(topObjs,objs);
		return flag;
	}

	/**
	 * Get specific park warning message
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public String getWaringMes(String contractCode, String facilityID){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", 
				".id", new RegularExpression("facility_view_card_"+contractCode+"(\\d+_)?"+facilityID, false));
		Property[] p2 = Property.toPropertyArray(".className", new RegularExpression("msg (warning|error)",false));

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		String errorMes ="";
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Error message Object can't find.");
		}else {
			errorMes = objs[0].getProperty(".text");
		}

		Browser.unregister(objs);
		return errorMes;
	}

	/**
	 * Get error message
	 * @return
	 */
	public String getWaringMes(){
		String errorMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("msg (warning|error)",false));

		if(objs.length>0){
			errorMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Error message Object can't find.");

		Browser.unregister(objs);
		return errorMes.trim();
	}

	public void verifyWarningMes(String expectError, String contractCode, String facilityID){
		String actualErrorMes = this.getWaringMes(contractCode, facilityID);
		if(!actualErrorMes.equals(expectError)){
			throw new ErrorOnDataException("Warning message is wrong!", expectError, actualErrorMes);
		}
		logger.info("Successfully verify waring message:"+actualErrorMes);
	}

	public void verifyWarningMes(String expectError){
		String actualErrorMes = this.getWaringMes();
		if(!actualErrorMes.matches(expectError)){
			throw new ErrorOnDataException("Warning message is wrong!", expectError, actualErrorMes);
		}
		logger.info("Successfully verify waring message:"+actualErrorMes);
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
			throw new ErrorOnDataException("Facility abailable warning message is wrong!", expectedFacilityAvailableWarningMes, actualWarnMes);
		}
	}

	public boolean checkShowAllResultsLinkExist(){
		return 	browser.checkHtmlObjectExists(".class", "Html.A", ".title", "Show all results.");
	}
	
	public void verifyShowAllResultsLinkExist(){
		boolean actualResult = this.checkShowAllResultsLinkExist();
		if(!actualResult){
			throw new ErrorOnPageException("'Show All Results' available filter doesn't display.");
		}
		logger.info("'Show All Results' available filter successfully display.");
	}

	public boolean checkShowOnlyAvailableSitesLinkExist(){
		return 	browser.checkHtmlObjectExists(".class", "Html.A", ".title", "Show only sites that have availability for your dates.");
	}
	
	public void verifyShowOnlyAvailableSitesLinkExist(){
		boolean actualResult = this.checkShowOnlyAvailableSitesLinkExist();
		if(!actualResult){
			throw new ErrorOnPageException("'Show only available sites' available filter doesn't display.");
		}
		logger.info("'Show only available sites' available filter successfully display.");
	}

	public boolean checkFacilityAvailableWarningMesExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "warning_panel");
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
	
	public String getErrorMes(String contractCode, String parkID){
		Property[] p1=Property.toPropertyArray(".className","facility_view_card",".id",
				new RegularExpression("^facility_view_card_"+contractCode+parkID+"(_\\d+)?", false));
		Property[] p2=Property.toPropertyArray(".className","msg error");

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		String parkName = objs[0].text();

		Browser.unregister(objs);
		return parkName;
	}

	public void verifyErrorMes(String contractCode, String parkID, String expectError){
		String actualErrorMes = this.getErrorMes(contractCode, parkID);
		if(!actualErrorMes.equals(expectError)){
			throw new ErrorOnDataException("The warning message is wrong!", expectError, actualErrorMes);
		}
	}

	public void verifyRegxErrorMes(String contractCode, String parkID, String expectError){
		String actualErrorMes = this.getErrorMes(contractCode, parkID);
		if(!actualErrorMes.matches(expectError)){
			throw new ErrorOnDataException("The regx warning message is wrong!", expectError, actualErrorMes);
		}
	}
	
	public boolean checkWarningMesExist(){
		return browser.checkHtmlObjectExists(".className", new RegularExpression("msg (warning|error)",false));
	}

	public boolean checkWarningMesExist(String contractCode, String parkID){
		Property[] p1 = Property.toPropertyArray(".className","facility_view_card",".id",
				new RegularExpression("^facility_view_card_"+contractCode+parkID+"(_\\d+)?", false));
		Property[] p2 = Property.toPropertyArray(".className", new RegularExpression("msg (warning|error)", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	public void verifyWarningMesExist(boolean flag){
		boolean actualResult = this.checkWarningMesExist();
		if(actualResult!=flag){
			throw new ErrorOnDataException("Warning message should"+(flag?"":" not ")+" display.");
		}
	}
	public void verifyWarningMesExist(boolean flag, String contractCode, String parkID){
		boolean actualResult = this.checkWarningMesExist(contractCode, parkID);
		if(actualResult!=flag){
			throw new ErrorOnDataException("Contract code:"+contractCode+ " and parkID:"+parkID+" warning message should"+(flag?"":" not ")+" display.");
		}
	}
	public void synFacilities(String preValue){
		logger.info("previousValue:"+preValue);
		String currentValue; 
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;

		boolean isChanged=false;
		Timer time = new Timer();

		do{
			currentValue = this.getFacilityViewListContent();
			isChanged = preValue.equals(currentValue);
			logger.info("currentValue:"+currentValue);

		}while(time.diffLong() < maxWaitTime && isChanged);
		if(isChanged) {
			throw new ItemNotFoundException("Syn facility timed out in "+maxWaitTime+" ms");
		}
	}
	
	public boolean gotoNext(){		
		boolean flag = this.checkNext();
		this.waitLoading();
		String value = this.getFacilityViewListContent();
		if(flag){
			this.clickNext();
			this.waitLoading();
			this.synFacilities(value);
			browser.waitExists();
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

	/**
	 * Get park names from first page
	 * @return
	 */
	public List<String> getFirstPgParkNames(){
		List<String> parkNames = new ArrayList<String>();

		IHtmlObject[] facilityName = browser.getHtmlObject(".className", "facility_link");
		if(facilityName.length>0){
			for(int i=0; i<facilityName.length; i++){
				parkNames.add(facilityName[i].text());
			}
		}else{
			throw new ObjectNotFoundException("Can't find object which has className as 'facility_link'");
		}

		Browser.unregister(facilityName);
		return parkNames;
	}

	/**
	 * Get All Park agency
	 * @return
	 */
	public List<String> getAllParksAgency() {
		List<String> parksAgency = new ArrayList<String>();
		logger.info("Get all the park agencys in view as list page.");
		
		do{
			IHtmlObject[] facilityName = browser.getHtmlObject(".class", "Html.SPAN", ".className", "agency");
			for(int i=0; i<facilityName.length; i++){
				parksAgency.add(facilityName[i].text().trim().replaceAll(" ?, ?", ""));
			}
			Browser.unregister(facilityName);
		}while(this.gotoNext());

		return parksAgency;
	}

	/**
	 * Verify parks agency info
	 * @param parksAgency
	 * @param specificAgency
	 */
	public void verifyParksAgency(List<String> parksAgency, String specificAgency){
		for(int i=0; i<parksAgency.size(); i++){
			if(!parksAgency.get(i).equals(specificAgency)){
				throw new ErrorOnPageException("Agency is wrong!", specificAgency, parksAgency.get(i));
			}
			logger.info("SUccessfully verify park agency-"+parksAgency.get(i));
		}
	}

	/**
	 * Get All Park names
	 * @return
	 */
	public List<String> getAllParkNames() {
		List<String> parkNames = new ArrayList<String>();
		logger.info("Get all park names in view as list page.");
		do{
			IHtmlObject[] facilityName = browser.getHtmlObject(".className", "facility_link");
			for(int i=0; i<facilityName.length; i++){
				parkNames.add(facilityName[i].text().trim());
			}
			Browser.unregister(facilityName);
		}while(this.gotoNext());

		return parkNames;
	}

	/**
	 * This function is used for compare the total result number displayed on the page with the total count number query from the DB.
	 * Get all facility ID, facility Name, facility type[Facility or Recarea]; the facility contain facilities and rec areas.
	 * FacilityData.facilityName is used for storing FacilityName or Recarea name.
	 * FacilityData.facilityID is used for storing Facility's ID or Recrea ID.
	 * FacilityData.productCategory is used for storing the type of the park displayed on Web, "facility" represents facilities, while "recarea" represents recarea. 
	 * @return
	 */
	public List<FacilityData> getAllFacilityInfo() {
		List<FacilityData> allFacilities = new ArrayList<FacilityData>();
		String parkTag = "&parkId=";
		String recAreaStartTag = "&recAreaId=";
		String recAreaEndTag = "&agencyCode=";
		String recFacilityStartTag = "&facilityId=";
		String recFacilityEndTag = "&agencyCode=";

		do{
			IHtmlObject[] facilities = browser.getHtmlObject(".className", "facility_link");
			for(int i=0; i<facilities.length; i++){
				FacilityData tempFacility = new FacilityData();
				tempFacility.facilityName = facilities[i].text();
				String href = facilities[i].getProperty("href").replaceAll(" ", "");

				//if contain facility
				if(href.contains(parkTag)){
					int parkIdStartIndex = href.indexOf(parkTag) + parkTag.length();
					tempFacility.facilityID = href.substring(parkIdStartIndex);
					tempFacility.productCategory = "facility";
				}else if(href.contains(recAreaStartTag) && href.contains(recAreaEndTag)){
					//if contain recare					
					int recAreaIdStartIndex = href.indexOf(recAreaStartTag) + recAreaStartTag.length();
					int recAreaIdEndIndex = href.indexOf(recAreaEndTag);
					tempFacility.facilityID = href.substring(recAreaIdStartIndex, recAreaIdEndIndex);
					tempFacility.productCategory = "recarea";
				}else if(href.contains(recFacilityStartTag) && href.contains(recFacilityEndTag)){
					//if contain rec facility					
					int recFacilityStartIndex = href.indexOf(recFacilityStartTag) + recFacilityStartTag.length();
					int recFacilityEndIndex = href.indexOf(recFacilityEndTag);
					tempFacility.facilityID = href.substring(recFacilityStartIndex, recFacilityEndIndex);
					tempFacility.productCategory = "recfacility";
				}else{
					throw new ErrorOnDataException("The park:" + tempFacility.facilityName +" displayed on the page didn't belong to facility or recarea or recfacility...");
				}
				allFacilities.add(tempFacility);
			}
			Browser.unregister(facilities);
		}while(gotoNext());

		return allFacilities;
	}

	/**
	 * Get top park name
	 * @return
	 */
	public String getTopParkName(){
		IHtmlObject[] facilityName = browser.getHtmlObject(".className", "facility_link");
		String topParkName = facilityName[0].text();

		Browser.unregister(facilityName);
		return topParkName;
	}

	/**
	 * Verify topa park name
	 * @param expectedTopParkName
	 */
	public void verifyTopParkName(String expectedTopParkName){
		String actualTopParkName = this.getTopParkName();
		if(!actualTopParkName.equals(expectedTopParkName)){
			throw new ErrorOnDataException("Top park name is wrong!", expectedTopParkName, actualTopParkName);
		}
		logger.info("Successfully verify top park name - "+actualTopParkName);
	}

	/**
	 * Get All Park's Picture ID. This function will continue click the Next button until loop all park pic's info.
	 * @return
	 */
	public List<String> getAllParkPictureID() {
		List<String> parkPicIDs = new ArrayList<String>();
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id",  new RegularExpression("^p.*@\\d+", false))};

		do{
			IHtmlObject[] facilityPicture = browser.getHtmlObject(Property.atList(searchResultListProp, p2));

			for(int i=0; i<facilityPicture.length; i++){
				parkPicIDs.add(facilityPicture[i].getProperty(".id"));
			}
			Browser.unregister(facilityPicture);
		}while(gotoNext());

		return parkPicIDs;
	}

	/**
	 * Get first page Park's  Map Pin Title.
	 * Not on Map
	 * View on Map
	 * @return
	 */
	public List<String> getFirstPgPinTitles(){
		List<String> pinTitles = new ArrayList<String>();
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id",  new RegularExpression("^usrpin.*", false))};

		IHtmlObject[] pinObjs = browser.getHtmlObject(Property.atList(searchResultListProp,p2));
		for(int i=0; i<pinObjs.length; i++){
			pinTitles.add(pinObjs[i].getProperty(".title"));
		}
		Browser.unregister(pinObjs);

		return pinTitles;
	}

	/**
	 * Get All Park's  Map Pin Title(View on Map). This function will continue click the Next button until loop all park pin info.
	 * @return
	 */
	public List<String> getAllPinTitle() {
		List<String> pinTitles = new ArrayList<String>();
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id",  new RegularExpression("^usrpin.*", false))};

		do{
			IHtmlObject[] pinObjs = browser.getHtmlObject(Property.atList(searchResultListProp,p2));
			for(int i=0; i<pinObjs.length; i++){
				pinTitles.add(pinObjs[i].getProperty(".id"));
			}
			Browser.unregister(pinObjs);
		}while(gotoNext());

		return pinTitles;
	}

	/**
	 * Get all Park's Picture ID in the current page. This function will only get the current page's park picture info.
	 * @return
	 */
	public List<String> getParkPictureID() {
		List<String> parkPicIDs = new ArrayList<String>();
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id", new RegularExpression("^p.*@\\d+", false))};
		IHtmlObject[] facilityPicture = browser.getHtmlObject(Property.atList(searchResultListProp,p2));

		for(int i=0; i<facilityPicture.length; i++){
			parkPicIDs.add(facilityPicture[i].getProperty(".id"));
		}

		Browser.unregister( facilityPicture);
		return parkPicIDs;
	}

	/**
	 * click the park's pin picture, the pin picture displayed as A, B, etc..
	 * @return
	 */
	public void clickMapPin(String contractCode, String parkID) {
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id", new RegularExpression("^usrpin_"+contractCode.toUpperCase()+parkID, false))};
		IHtmlObject[] parkPinObjs = browser.getHtmlObject(Property.atList(searchResultListProp,p2));

		if( null !=parkPinObjs && parkPinObjs.length >0){
			parkPinObjs[0].click();
		}else{
			throw new ErrorOnPageException("System can't get the pin Pic for park:" + parkID);
		}

		Browser.unregister(parkPinObjs);
	}

	/**
	 * check whether the park has corresponding facility map pin picture or not.
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public boolean checkMapPinExists(String contractCode, String parkID){
		Boolean exist = false;
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id", new RegularExpression("^usrpin_"+contractCode.toUpperCase()+parkID, false))};
		IHtmlObject[] parkPinObjs = browser.getHtmlObject(Property.atList(searchResultListProp,p2));

		if(parkPinObjs != null && parkPinObjs.length > 0){
			exist = true;
		}
		Browser.unregister(parkPinObjs);
		return exist;
	}

	/**
	 * check whether the park has corresponding facility map pin picture or not.
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public void verifyMapPinExists(String contractCode, String parkID, boolean display){
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id", new RegularExpression("^usrpin_"+contractCode.toUpperCase()+parkID, false))};
		IHtmlObject[] parkPinObjs = browser.getHtmlObject(Property.atList(searchResultListProp,p2));
		if(display){
			if(parkPinObjs != null && parkPinObjs.length > 0){
				logger.info("verify map pin displayed successfully for park:" + parkID);
			}else{
				throw new ErrorOnPageException("verify map pin displayed failed for park:" + parkID);
			}
		}else{
			if(parkPinObjs != null && parkPinObjs.length > 0){
				throw new ErrorOnPageException("verify map pin displayed failed for park:" + parkID);
			}else{
				logger.info("verify map pin displayed successfully for park:" + parkID);
			}
		}

		Browser.unregister(parkPinObjs);
	}


	/**
	 * get park's pin picture src info, the return value look like "images/maps/markerA.png";
	 * @return
	 */
	public String getMapPinPictureSrc(String contractCode, String parkID) {
		String pinSource = ""; 		
		Property[] p2 = new Property[]{new Property(".class", "Html.IMG"), new Property(".id", new RegularExpression("^usrpin_"+contractCode.toUpperCase()+parkID, false))};
		IHtmlObject[] parkPinObjs = browser.getHtmlObject(Property.atList(searchResultListProp,p2));

		if( null !=parkPinObjs && parkPinObjs.length >0){
			pinSource= parkPinObjs[0].getProperty(".src");
		}

		Browser.unregister(parkPinObjs);
		return pinSource;
	}

	/**
	 * get park's pin text, the return value look like: A, B";
	 * @param contractCode
	 * @param parkID
	 * @Note
        //markerA_NI
		//markerB
		//_NI_NoMap
	 * @return
	 */
	public String getMapPinText(String contractCode, String parkID){
		String mapPinPictureInfo = this.getMapPinPictureSrc(contractCode, parkID);
		String pinText = mapPinPictureInfo.split("marker")[1].split("\\.png")[0].trim();

		if(pinText.contains("_NI")){
			pinText = pinText.split("_NI")[0].trim();
		}else if(pinText.contains("_NI_")){
			pinText = pinText.split("_NI_")[1].trim();
		}
		return pinText;
	}

	/**
	 * click the original page loading small picture, this function will loop all pages and find the park picture id which match
	 * with the given value and then try to click in order to enlarge it. 
	 */
	public void enlargeParkPicture(String parkPicID){
		this.gotoFirstPage();
		boolean flag = false;
		do{
			List<String> picIDs = this.getParkPictureID();
			if (picIDs.toString().contains(parkPicID)){
				flag = true;
				break;
			}
		}while(this.gotoNext());

		if(!flag){
			throw new ErrorOnDataException("There is no park picture's id match with the given value:" + parkPicID);
		}else{
			this.clickParkPicture(parkPicID);
		}
	}

	/**
	 * get the shrinked park picture title info. will loop through all pages.
	 * @param parkPicID
	 * @return
	 */
	public String getParkPictureTitleInfo(String parkPicID){
		return this.getParkPictureTitleInfo(parkPicID, false);
	}

	/**
	 * This function will loop all pages and find the park's picture id which match
	 * with the given value and then try to get the corresponding picture title info. 
	 * the function support both enlarged and shrinked park picture.
	 * @param parkPicID
	 * @param isEnlargedPicture
	 * @return
	 */
	public String getParkPictureTitleInfo(String parkPicID, boolean isEnlargedPicture){
		String parkPictureTitle = "";
		if(!isEnlargedPicture){
			boolean flag = false;

			this.gotoFirstPage();
			do{
				List<String> picIDs = this.getParkPictureID();
				if (picIDs.toString().contains(parkPicID)){
					flag = true;
					break;
				}
			}while(this.gotoNext());

			if(!flag){
				throw new ErrorOnDataException("There is no park picture's id match with the given value:" + parkPicID);
			}
		}

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression(".*" + parkPicID +".*", false));
		parkPictureTitle = objs[0].getProperty("title");

		return parkPictureTitle;
	}

	/**
	 * check small park picture is invisible, when it was enlarged.
	 */
	public boolean checkInitialParkPictureHidden(String parkPicID){
		boolean flag = false;
		String style = "";
		IHtmlObject[] facilityPicture = browser.getHtmlObject(".class", "Html.IMG", ".id", RegularExpression.convert(parkPicID, false));
		style = facilityPicture[0].style("visibility").trim();
		if (style.equalsIgnoreCase("hidden")){
			flag = true;
		}
		return flag;
	}

	/**
	 * verify the enlarged picture to be displayed in given seconds.
	 * @param picID
	 * @param seconds
	 */
	public void verifyEnlargedPictureDisplay(String picID, int seconds){
		boolean flag = false;
		Timer timer=new Timer();

		while(!flag && timer.diffLong()<timeout*1000) {
			flag = this.checkEnlargedParkPicDisplay(picID);
		}

		if (!flag){
			throw new ErrorOnPageException("The enlarged picture didn't display in " + seconds + " seconds..");
		}
	}

	/**
	 * 
	 * verify the enlarged picture to be hidden in the given seconds.
	 * @param picID
	 * @param seconds
	 */
	public void verifyEnlargedPictureHidden(String picID, int seconds){
		boolean flag = true;
		Timer timer=new Timer();

		while(flag && timer.diffLong()<timeout*1000) {
			flag = this.checkEnlargedParkPicDisplay(picID);
		}

		if (flag){
			throw new ErrorOnPageException("The enlarged picture didn't hiddne in " + seconds + " seconds..");
		}
	}

	/**
	 * check whether the enlarged park pic displayed or not.
	 * @param parkPicID
	 * @return
	 */
	public boolean checkEnlargedParkPicDisplay(String parkPicID){
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".id", new RegularExpression(".*" + parkPicID +".*", false));
	}

	/**
	 * click park small picture based on picture id.
	 * @param parkPicID
	 * @return
	 */
	public void clickParkPicture(String parkPicID){
		browser.clickGuiObject(".class", "Html.IMG", ".id", new RegularExpression(".*" + parkPicID +".*", false), true);
	}

	/**
	 * Get park short description info based on contract code, and facilityID
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public String getParkDescription(String contractCode, String facilityID) {
		String description = ""	;
		Property[] hideP = Property.toPropertyArray(".className", "facility_view_desc_detail hide", 
				".id", new RegularExpression("(facility_view_desc_detail_|facility_view_desc_detail_)" +contractCode + facilityID+ "(_\\d+)?", false));
		if(browser.checkHtmlObjectExists(hideP)){
			IHtmlObject objs[] = browser.getHtmlObject(".className", new RegularExpression("^facility_view_description( all)?", false),
					".id", new RegularExpression("facility_view_description_" +contractCode + facilityID+ "(_\\d+)?", false));
			if(objs.length<1){
				throw new ObjectNotFoundException("'facility_view_description' object can't be found.");
			}
			description = objs[0].text();
			logger.info("The short description for:" + contractCode + " contract,facilityid:" + facilityID + " is:" +description);
		}else
			throw new ObjectNotFoundException("'facility_view_desc_detail hide' object can't be found.");

		return description;
	}

	/**
	 * Get park description detail info based on contract code, and facilityID. the description detail info will show after we click "More" hyperlink
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public String getParkDescriptionDetail(String contractCode, String facilityID) {
		String description = ""	;
		Property[] hideP = Property.toPropertyArray(".className", "facility_view_description hide", 
				".id", new RegularExpression("(facility_view_desc_detail_|facility_view_description_)" +contractCode + facilityID+ "(_\\d+)?", false));
		if(browser.checkHtmlObjectExists(hideP)){
			IHtmlObject objs[] = browser.getHtmlObject(".className", "facility_view_desc_detail",
					".id", new RegularExpression("facility_view_desc_detail_" +contractCode + facilityID+ "(_\\d+)?", false));
			if(objs.length<1){
				throw new ObjectNotFoundException("'facility_view_desc_detail_' object can't be found.");
			}
			description = objs[0].text();
			logger.info("The description deail for:" + contractCode + " contract,facilityid:" + facilityID + " is:" +description);
		}else
			throw new ObjectNotFoundException("'facility_view_description hide' object can't be found.");

		return description;
	}

	/**
	 *  Verify description mode
	 * @param isShortenedDescription: true: park is with short description
	 *                                false: park is with full description
	 * @param contractCode
	 * @param parkId
	 */
	public void verifyParkDescriptionMode(boolean isShortenedDescription, String contractCode, String parkId){
		String parkDecription = this.getParkDescription(contractCode, parkId);
		if(isShortenedDescription){
			if(!parkDecription.matches(".*\\[more\\]$")){
				throw new ErrorOnDataException("Park default description should not be details.");
			}
		}else{
			if(parkDecription.matches(".*\\[more\\]$")){
				throw new ErrorOnDataException("Park default description should be details.");
			}
		}
	}
	/**
	 * click description short info DIV based on contract code, and facilityID
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public void clickParkDescription(String contractCode, String facilityID) {
		Property[] p1 = Property.concatPropertyArray(div(), ".id", new RegularExpression("facility_view_description_" +contractCode + facilityID+"(_\\d+)?", false));
		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("more_" +contractCode + facilityID+"(_\\d+)?", false));
		
		if(browser.checkHtmlObjectExists(p1)){
			browser.clickGuiObject(p1);
		}else browser.clickGuiObject(p2);

		//				browser.clickGuiObject(".class", "Html.DIV", 
		//						".id", new RegularExpression("facility_view_description_" +contractCode + facilityID+"(_\\d+)?", false));
		//		browser.clickGuiObject(".id", new RegularExpression("more_" +contractCode + facilityID+"(_\\d+)?", false));
	}

	public void waitParkDescriptionDetail(String contractCode, String facilityID){
		Property[] p = new Property[2];
		p[0] = new Property(".className", "facility_view_description hide");
		p[1] = new Property(".id", new RegularExpression("(facility_view_desc_detail_|facility_view_description_)" +contractCode + facilityID+ "(_\\d+)?", false));
		browser.waitExists(p);
	}

	public void waitParkDescription(String contractCode, String facilityID){
		Property[] p = new Property[2];
		p[0] = new Property(".className", "facility_view_desc_detail hide");
		p[1] = new Property(".id", new RegularExpression("(facility_view_desc_detail_|facility_view_desc_detail_)" +contractCode + facilityID+ "(_\\d+)?", false));
		browser.waitExists(p);
	}

	/**
	 * click description detail info DIV based on contract code, and facilityID
	 * @param contractCode
	 * @param facilityID
	 */
	public void clickParkDescriptionDetail(String contractCode, String facilityID){
		browser.clickGuiObject(".className", "facility_view_desc_detail", ".id", "facility_view_desc_detail_" +contractCode + facilityID);
	}

	/**
	 *Get park name 
	 * @param index
	 * @return
	 */
	public String getParkName(int index){
		String parkName = "";

		IHtmlObject[] facilityName = browser.getHtmlObject(".className", "facility_link");
		parkName = facilityName[index].text();
		
		logger.info("Park name:"+parkName);
		Browser.unregister(facilityName);
		return parkName;
	}
	public String getFirstParkName(){
		return this.getParkName(0);
	}
	public String getSecondParkName(){
		return this.getParkName(1);
	}

	/**
	 * Get first parkName according to contract code and facility id
	 * @param contractCode
	 * @param facilityID
	 * @return
	 */
	public String getParkName(String contractCode, String facilityID){
		Property[] p1=Property.toPropertyArray(".className","facility_view_card",".id",
				new RegularExpression("^facility_view_card_"+contractCode+facilityID+"(_\\d+)?", false));
		Property[] p2=Property.toPropertyArray(".className","facility_link");

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		String parkName = objs[0].text();

		Browser.unregister(objs);
		return parkName;
	}

	/**
	 * Get all park category titles
	 * @return
	 */
	public List<String> getAllParkCategoryTitle(){
		List<String> parkCategoryTitle = new ArrayList<String>();

		do{
			IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("(site_types|permit|tour)_title", false));

			for(int i=0; i<objs.length; i++){//2012/7/18 Sara Wang
				if(!objs[i].text().trim().equals("Tour Tickets sold at Facility only:")){
					parkCategoryTitle.add(objs[i].text().trim());
				}
			}
			Browser.unregister(objs);
		}while(this.gotoNext());

		return parkCategoryTitle;
	}

	public List<String> getFirstPgParkCategoryTitle(){
		List<String> parkCategoryTitle = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("(site_types|permit|tour)_title", false));

		for(int i=0; i<objs.length; i++){//2012/7/18 Sara Wang
			if(!objs[i].text().trim().equals("Tour Tickets sold at Facility only:")){
				parkCategoryTitle.add(objs[i].text().trim());
			}
		}
		Browser.unregister(objs);

		return parkCategoryTitle;
	}
	
	/**
	 * Get First park site title
	 * @return
	 */
	public String getFirstParkSiteTypeTitle(){
		String siteTitle = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "site_types_title");
		siteTitle = objs[0].text();
		Browser.unregister(objs);
		return siteTitle;
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

	public List<String> geParkSiteTypeTitlesInFirstPg(){
		List<String> siteTitles = new ArrayList<String>();

		//Get all site type titles information
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("(site_types_title)|(permit_title)|(tour_title)|(reservation_directive)", false));

		if(objs!=null &&objs.length>0){
			for(int i=0; i<objs.length; i++){
				siteTitles.add(objs[i].text()); 
			}
		}
		Browser.unregister(objs);
		return siteTitles;
	}

	/**
	 * Get 0 from site type title "0 matching sites available"
	 * @return
	 */
	public List<String> getFirstPgAvailableMatchingSitesNum(){
		List<String> siteTitles = new ArrayList<String>();

		//Get all site type titles information
		IHtmlObject[] objs = browser.getHtmlObject(".className", "site_types_title");

		if(objs!=null &&objs.length>0){
			for(int i=0; i<objs.length; i++){
				siteTitles.add(RegularExpression.getMatches(objs[i].text(), "\\d+")[0]); 
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
		List<String> siteTitles = new ArrayList<String>();
		do{
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
		}while(this.gotoNext());

		//Go back to first page
		this.gotoFirstPage();

		return siteTitles;
	}

	public String getParkSiteTypTitleByParkName(String parkName){
		if(parkName!=null){
			parkName=parkName.replaceAll("\\(", "\\\\(");
			parkName=parkName.replaceAll("\\)", "\\\\)");
		}

		Property[] p1 = Property.toPropertyArray(".class","Html.DIV", ".text",new RegularExpression("^"+parkName+".*",false), ".className","facility_view_card");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "site_types_title");
		IHtmlObject[] typTitles=browser.getHtmlObject(Property.atList(p1,p2));
		if(typTitles==null||typTitles.length<1){
			throw new ObjectNotFoundException("Can't find site type title DIV");
		}
		String value=typTitles[0].text();
		Browser.unregister(typTitles);

		return value;
	}

	public String getParkSiteTypeTitleByParkId(String contractCode, String parkID){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id",
				new RegularExpression("facility_view_card_"+contractCode+parkID+"(_\\d+)?",false));
		Property[] p2 = Property.toPropertyArray(".className", "site_types_title");

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		String actualValue =  objs[0].text();

		Browser.unregister(objs);
		return actualValue;
	}
	
	/**
	 * Get 0 from site type title "0 matching sites available"
	 * @return
	 */
	public int getAvailableMatchingSitesNum(String contractCode, String parkID){
       String siteTypeTitle = this.getParkSiteTypeTitleByParkId(contractCode, parkID);
       int availableMatchingSiteNum = Integer.valueOf(RegularExpression.getMatches(siteTypeTitle, "\\d+")[0]);
		return availableMatchingSiteNum;
	}

	public void verifyParkSiteTypeTitle(String contractCode, String facilityID, String expectedValue){
		String actualValue = this.getParkSiteTypeTitleByParkId(contractCode, facilityID);
		if(!actualValue.equals(expectedValue)){
			throw new ErrorOnPageException("The expected value should be: "+expectedValue+", but the actual one is: "+actualValue);
		}else{
			logger.info("...Successfully verify the park site type and title info for the patk with ID: "+facilityID);
		}
	}

	/**
	 * Get park site type items according to contract code and parkID
	 * @return
	 */
	public String[] getParkSiteTypeItems(String contractCode, String parkID){
		String[] siteTypes = null;
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id",
				new RegularExpression("facility_view_card_"+contractCode+parkID+"(_\\d+)?",false));
		Property[] p2 = Property.toPropertyArray(".className", 
				new RegularExpression("site_type_item"+"( next_available)?",false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		siteTypes = new String[objs.length];

		for(int i=0; i<siteTypes.length; i++){
			siteTypes[i] = objs[i].text();
		}
		Browser.unregister(objs);

		return siteTypes;
	}

	public boolean checkParkSummaryListExist(String contractCode, String parkID){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id",
				new RegularExpression("facility_view_card_"+contractCode+parkID+"(_\\d+)?",false));
		Property[] p2 = Property.toPropertyArray(".className", new RegularExpression("summary\\_\\w+\\_list", false));

		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}

	public void verifyParkSummaryListExist(String contractCode, String parkID, boolean flag){
		boolean actualResult = this.checkParkSummaryListExist(contractCode, parkID);
		if(actualResult!=flag){
			throw new ErrorOnDataException("Park Summary Site List should "+(flag?"":"not")+" exsit.");
		}
	}

	public String[] getParkSiteTypesByParkName(String parkName){
		String[] siteTypes = null;
		parkName=parkName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".text", new RegularExpression("^"+parkName+".*",false)),new Property(".className","facility_view_card")};
		IHtmlObject[] facilityCards=browser.getHtmlObject(p);
		if(facilityCards==null||facilityCards.length<1){
			throw new ObjectNotFoundException("Can't find facility view card DIV.");
		}

		IHtmlObject[] summarySiteObj = browser.getHtmlObject(".className", "summary_sites_list",facilityCards[0]);
		IHtmlObject[] siteTypesObj = browser.getHtmlObject(".className", "site_type_item", summarySiteObj[0]);
		siteTypes = new String[siteTypesObj.length];

		for(int i=0; i<siteTypes.length; i++){
			siteTypes[i] = siteTypesObj[i].text();
		}
		Browser.unregister(summarySiteObj, siteTypesObj);

		return siteTypes;
	}

	protected Property[] tourTitleDiv() {
		return Property.toPropertyArray(".id", "summary_tours_list_online");
	}
	
	protected Property[] facilityViewDiv(String contractCode, String parkId) {
		return Property.concatPropertyArray(this.div(), ".className", "facility_view_card", ".id", "facility_view_card_"+contractCode+parkId);
	}
	
	public boolean isParkTourDescriptionExist(String contractCode, String parkId) {
		return browser.checkHtmlObjectExists(Property.atList(this.facilityViewDiv(contractCode, parkId), tourTitleDiv()));
	}
	
	/**
	 * Get first park tour title sold on line
	 * @return
	 */
	public String getFirstParkTourTitle(){
		String soldOnlineTitle = "";
		IHtmlObject[] summaryToursObj = browser.getHtmlObject(tourTitleDiv());
		IHtmlObject[] tourTitleObj = browser.getHtmlObject(".className", "tour_title", summaryToursObj[0]);
		soldOnlineTitle = tourTitleObj[0].text();
		Browser.unregister(summaryToursObj, tourTitleObj);

		return soldOnlineTitle;
	}

	public String[] getFirstParkSoldAtFacilityTourNames(){
		String[] soldAtFacilityOnlyName = null;
		IHtmlObject[] summaryToursObj = browser.getHtmlObject (".id", new RegularExpression("summary_tours_list_park", false));
		IHtmlObject[] tourTypeObj = browser.getHtmlObject(".class", "Html.SPAN", ".className", "tour_item", summaryToursObj[0]);
		soldAtFacilityOnlyName = new String[tourTypeObj.length];
		for(int i=0; i<tourTypeObj.length; i++){
			soldAtFacilityOnlyName[i] = tourTypeObj[i].text();
		}

		Browser.unregister(summaryToursObj, tourTypeObj);
		return soldAtFacilityOnlyName;
	}


	public String[] getFirstParkSoldOnlineTourNames(){
		String[] soldOnlineNames = null;
		IHtmlObject[] summaryToursObj = browser.getHtmlObject (".id", new RegularExpression("summary_tours_list_online", false));
		IHtmlObject[] tourTypeObj = browser.getHtmlObject(".class", "Html.SPAN", ".className", "tour_item", summaryToursObj[0]);
		soldOnlineNames = new String[tourTypeObj.length];
		for(int i=0; i<tourTypeObj.length; i++){
			soldOnlineNames[i] = tourTypeObj[i].text().trim();
		}

		Browser.unregister(summaryToursObj, tourTypeObj);
		return soldOnlineNames;
	}

	public String[] getFirstParkPermitTypes(){
		String[] permitTypes = null;
		IHtmlObject[] summaryPermitObj = browser.getHtmlObject(".className", "summary_permits_list");
		IHtmlObject[] permitTypeObj = browser.getHtmlObject(".class", "Html.SPAN", ".className", "permit_item", summaryPermitObj[0]);
		permitTypes = new String[permitTypeObj.length];
		for(int i=0; i<permitTypeObj.length; i++){
			permitTypes[i] = permitTypeObj[i].text();
		}

		Browser.unregister(summaryPermitObj, permitTypeObj);
		return permitTypes;
	}

	/**
	 * Get first park permit type description
	 * @param permitType
	 * @return
	 */
	public String getFirstParkPermitTypeDescription(String permitType){
		String permitTypesDescription = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "summary_permits_list");
		IHtmlObject[] subObj = browser.getHtmlObject(".class", "Html.A", objs[0]);

		for(int i=0; i<subObj.length; i++){
			if(subObj[i].text().equals(permitType)){
				permitTypesDescription = subObj[i].getProperty(".title");
			}
		}

		Browser.unregister(objs, subObj);
		return permitTypesDescription;
	}

	/**
	 * Get first park permit types description
	 * @param permitType
	 * @return
	 */
	public String[] getFirstParkPermitTypesDescription(String[] permitTypes){
		String[] permitTypesDescription = new String[permitTypes.length];
		for(int i=0; i<permitTypes.length; i++){
			permitTypesDescription[i] = this.getFirstParkPermitTypeDescription(permitTypes[i]);
		}

		return permitTypesDescription;
	}

	public String getFirstParkPermitTitle(){
		String permitTitle = "";
		IHtmlObject[] summaryPermitObj = browser.getHtmlObject(".id", "summary_permits_list");
		IHtmlObject[] permitTitleObj = browser.getHtmlObject(".className", "permit_title", summaryPermitObj[0]);
		permitTitle = permitTitleObj[0].text();
		Browser.unregister(summaryPermitObj, permitTitleObj);

		return permitTitle;
	}

	/**
	 * Get first park tour title sold park
	 * @return
	 */
	public String getFirstParkTourTitleSoldPark(){
		String soldParkTitle = "";
		IHtmlObject[] summaryToursObj = browser.getHtmlObject(".id", "summary_tours_list_park");
		IHtmlObject[] tourTitleObj = browser.getHtmlObject(".className", "tour_title", summaryToursObj[0]);
		soldParkTitle = tourTitleObj[0].text();
		Browser.unregister(summaryToursObj, tourTitleObj);

		return soldParkTitle;
	}

	/**
	 * Get first park tour Names sold park
	 * @return
	 */
	public String[] getFirstParkTourNamesSoldPark(){
		String[] soldParkNames = null;
		IHtmlObject[] summaryToursObj = browser.getHtmlObject(".id", "summary_tours_list_park");
		IHtmlObject[] tourTypeObj = browser.getHtmlObject(".class", "Html.SPAN", ".className", "tour_item", summaryToursObj[0]);
		soldParkNames = new String[tourTypeObj.length];
		for(int i=0; i<tourTypeObj.length; i++){
			soldParkNames[i] = tourTypeObj[i].text();
		}

		Browser.unregister(summaryToursObj, tourTypeObj);
		return soldParkNames;
	}
	/**
	 * Get Last Park name
	 * @return
	 */
	public String getLastParkName(){
		this.gotoLastPage();
		String parkName = "";

		IHtmlObject[] facilityName = browser.getHtmlObject("className", "facility_link");
		parkName = facilityName[facilityName.length -1].text();
		Browser.unregister(facilityName);

		return parkName;
	}

	/**
	 * Get facility view header objects
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public IHtmlObject[] getFacilityViewHeaderObjects(String contractCode, String parkID){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id", "facility_view_card_"+contractCode+parkID);
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "facility_view_header");
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("'facility_view_header' object can't be found, parkID:"+parkID+", contractCode:"+contractCode);
		}
		return objs;
	}

	/**
	 * Is park with matched on
	 * @param contractCode
	 * @param parkID
	 * @param valueMatchedOn: [matched on "XX"]
	 * @return
	 */
	public boolean isParkWithMatchedOn(String contractCode, String parkID, String valueMatchedOn){
		boolean flag=false;
		IHtmlObject[] objs = this.getFacilityViewHeaderObjects(contractCode, parkID);
		flag=browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.SPAN", ".className", "sufix", ".text", "[matched on \""+valueMatchedOn.toUpperCase()+"\"]"));
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * Verify park with matched on specific value
	 * @param contractCode
	 * @param parkID
	 * @param valueMatchedOn
	 * Sample data: [matched on "CG21"]
	 */
	public void verifyParkWithMatchedOn(String contractCode, String parkID, String valueMatchedOn){
		if(!this.isParkWithMatchedOn(contractCode, parkID, valueMatchedOn)){
			throw new ErrorOnPageException("Park:"+parkID+" should be with matched on "+valueMatchedOn);
		}
		logger.info("Park:"+parkID+" is with matched on "+valueMatchedOn);
	}

	/**
	 * Is park with 'part of XXX'
	 * @param contractCode
	 * @param parkID
	 * @param parentName
	 * @return
	 * Sample data: part of parent name 
	 */
	public boolean isParkWithPartOf(String contractCode, String parkID, String parentName){
		boolean flag=false;
		IHtmlObject[] objs = this.getFacilityViewHeaderObjects(contractCode, parkID);
		flag=browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className", "facility_header_parent", ".text", new RegularExpression("part of ?"+parentName.replaceAll(".", "\\.")+".*", false)));
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * Verify park with parent name
	 * @param contractCode
	 * @param parkID
	 * @param valueMatchedOn
	 * Sample data: part of parent name 
	 */
	public void verifyParkWithPartOf(String contractCode, String parkID, String parentName){
		if(!this.isParkWithPartOf(contractCode, parkID, parentName)){
			throw new ErrorOnPageException("Park:"+parkID+" should be with parent "+parentName);
		}
		logger.info("Park:"+parkID+" is with parent "+parentName);
	}

	public double getParkDistance(int index){
		IHtmlObject[] facilityDistance = browser.getHtmlObject(".className", "sufix");
		String distanceString = facilityDistance[index].text().replaceAll("(\\[|miles\\*\\])", "");//[101.72miles*]
		Browser.unregister(facilityDistance);

		return Double.valueOf(distanceString);
	}
	/**
	 * Get first park distance info
	 * @return
	 */
	public double getFirstParkDistance(){
		return getParkDistance(0);
	}

	public BigDecimal getSecondParkDistance(){
		return new BigDecimal(getParkDistance(1));
	}
	
	public BigDecimal getThirdParkDistance(){
		return new BigDecimal(getParkDistance(2));
	}
	
	public void verifyDistanceIsMost(){
		BigDecimal secondParkDistance = getSecondParkDistance();
		BigDecimal thirdParkDistance = getThirdParkDistance();
		if(secondParkDistance.compareTo(thirdParkDistance)!=-1){
			throw new ErrorOnPageException("Second park should have short distance than the thord park", secondParkDistance, thirdParkDistance);
		}
	}
	
	/**
	 * Get last park distance info
	 * @return
	 */
	public double getLastParkDistance(){
		this.gotoLastPage();

		IHtmlObject[] facilityDistance = browser.getHtmlObject(".className", "sufix");
		String distanceString = facilityDistance[facilityDistance.length -1].text().replaceAll("(\\[|miles\\*\\])", "");//[101.72miles*]
		Browser.unregister(facilityDistance);

		return Double.valueOf(distanceString);
	}

	/**
	 * Click specific park site type item via giving index
	 * @param index
	 */
	public void clickParkSiteTypeItem(String contractCode, String parkID, int index){
		IHtmlObject[] summarySiteObj = browser.getHtmlObject(".id", 
				new RegularExpression("^site_types_content_"+contractCode+parkID+"(_\\d+)?", false));
		IHtmlObject[] siteTypesObj = browser.getHtmlObject(".class", "Html.A", summarySiteObj[0]);

		String permitTypeItem = this.getParkSiteTypeItems(contractCode, parkID)[index];

		if(siteTypesObj[index].text().equals(permitTypeItem)){
			siteTypesObj[index].click();
		}else throw new ObjectNotFoundException("The permit type ("+permitTypeItem+") can't be found.");

		Browser.unregister(summarySiteObj,siteTypesObj);
	}

	public void clickFirstParkPermitTypes(int index){
		String permitType = this.getFirstParkPermitTypes()[index].trim();

		IHtmlObject[] summaryPermitObj = browser.getHtmlObject(".className", "summary_permits_list");
		IHtmlObject[] permitTypesObj = browser.getHtmlObject(".class", "Html.A", summaryPermitObj[0]);

		if(permitType.equals(permitTypesObj[index].getProperty(".text"))){
			permitTypesObj[index].click();
		}else{
			throw new ErrorOnDataException("Can't find the expected permit type object with value("+permitType+")");
		}

		Browser.unregister(summaryPermitObj, permitTypesObj);
	}

	public void focusOnFirstParkPermitTypes(int index){
		IHtmlObject[] summaryPermitObj = browser.getHtmlObject(".className", "summary_permits_list");
		IHtmlObject[] permitTypesObj = browser.getHtmlObject(".class", "Html.A", ".text", this.getFirstParkPermitTypes()[index], summaryPermitObj[0]);

		permitTypesObj[index].click();
		Browser.unregister(summaryPermitObj,permitTypesObj);
	}

	public boolean checkFirstParkPermitTypeDescription(String textValue){
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".text", textValue);
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
	 * Get all park distance information
	 * @return
	 */
	public List<String> getAllParkDistance(){
		List<String> parkDistance = new ArrayList<String>();

		do{
			IHtmlObject[] facilityDistance = browser.getHtmlObject(".className", "sufix");

			for(int i=0; i<facilityDistance.length; i++){
				String distanceString = facilityDistance[i].text().replaceAll("(\\[|miles\\*\\])", "");//[101.72miles*]
				parkDistance.add(distanceString.trim());
			}
			Browser.unregister(facilityDistance);
		}while(gotoNext());

		return parkDistance;
	}
	
	public List<String> getFirstPgParkDistance(){
		List<String> parkDistance = new ArrayList<String>();

		IHtmlObject[] facilityDistance = browser.getHtmlObject(".className", "sufix");

		for(int i=0; i<facilityDistance.length; i++){
			String distanceString = facilityDistance[i].text().replaceAll("(\\[|miles\\*\\])", "");//[101.72miles*]
			parkDistance.add(distanceString.trim());
		}

		Browser.unregister(facilityDistance);
		return parkDistance;
	}
	
	/**
	 * Verify all park sort by distance
	 */
	public void verifyParksSortByDistance() {
		int startIndex = 0;

		List<String> parksDistance = new ArrayList<String>();
		parksDistance = this.getFirstPgParkDistance(); //getAllParkDistance();
		
		//If the first result has no distance info, it will display log info
		if(parksDistance.get(0).equals("")){
			logger.info("The first park is without distance info.");
			startIndex = 1;
		}

		//Compare the parks' distance info if they has distance info
		if(parksDistance.size()-startIndex>1){
			for(int i=startIndex; i<parksDistance.size()-1; i++){
				if(Double.parseDouble(parksDistance.get(i))>Double.parseDouble(parksDistance.get(i+1))){
					throw new ErrorOnDataException("The results should sort by distance.", parksDistance.get(i+1), parksDistance.get(i));
				}
			}
		}else logger.info("Only one park has distance info");
	}
	
	public void verifyFirstPgParksSortByDistance() {
		int startIndex = 0;

		List<String> parksDistance = new ArrayList<String>();
		parksDistance = this.getFirstPgParkDistance();

		//If the first result has no distance info, it will display log info
		if(parksDistance.get(0).equals("")){
			logger.info("The first park is without distance info.");
			startIndex = 1;
		}

		//Compare the parks' distance info if they has distance info
		for(int i=startIndex; i<parksDistance.size()-1; i++){
			if(Double.parseDouble(parksDistance.get(i))>Double.parseDouble(parksDistance.get(i+1))){
				throw new ErrorOnDataException("The results should sort by distance.", parksDistance.get(i+1), parksDistance.get(i));
			}
		}
	}

	/**
	 * verify park name
	 * @param parkName
	 * @param index: 0:first park, 1:second park, 2:third park...
	 */
	public void verifyParkName(String parkName, int index){
		String actualFacility = this.getParkName(index);
		if(!actualFacility.equalsIgnoreCase(parkName)){
			throw new ErrorOnDataException("First park name is wrong!", parkName, actualFacility);
		}
	}
	public void verifyFirstParkName(String firstParkName){
		this.verifyParkName(firstParkName, 0);
	}
	public void verifySecondParkName(String secondParkName){
		this.verifyParkName(secondParkName, 1);
	}

	/**
	 * Verify all expected park names display in 'View As List' page
	 * @param actualParkNames
	 * @param expectedParkNames
	 * @param exactMatched  true:all park names are one by one matched those in 'view as list' page
	 *                      false:all expected park display, not one by one
	 */
	public void verifyAllParkNames(List<String> expectedParkNames, List<String> actualParkNames, boolean exactMatched){
		if(exactMatched){
			if(expectedParkNames.size()!=actualParkNames.size()){
				throw new ErrorOnPageException("Size of Park names is different.", expectedParkNames.size(), actualParkNames.size());
			}else logger.info("Size of park names is same.");
		}
		for(int i=0; i<actualParkNames.size(); i++){
			if(!expectedParkNames.toString().contains(actualParkNames.get(i))){
				throw new ErrorOnPageException(actualParkNames.get(i)+" should displays in 'View As List' page.");
			}
		}
	}

	public void verifyAllParkNames(List<String> expectedParkNames, List<String> actualParkNames){
		this.verifyAllParkNames(expectedParkNames, actualParkNames, true);
	}
	
	public void verifyParkDisplays(String  expectedParkName, List<String> actualParkNames, boolean displayed){
			if(displayed!=(actualParkNames.toString().contains(expectedParkName))){
				throw new ErrorOnPageException(expectedParkName+" should "+(displayed?"":"not ")+"display in 'View As List' page.");
			}else logger.info(expectedParkName+(displayed?" displays":" doesn't display")+" in view as list page.");
	}

	/**
	 * Verify park name according to contract code and facility ID
	 * @param contractCode
	 * @param facilityID
	 * @param firstParkName
	 */
	public void verifyParkName(String contractCode, String facilityID, String firstParkName){
		String actualFacility = this.getParkName(contractCode, facilityID);
		if(!actualFacility.equalsIgnoreCase(firstParkName)){
			throw new ErrorOnDataException("The first park name should be "+firstParkName+", but the actual one is "+actualFacility);
		}
	}

	/**
	 * Verify top park category title, such as "0 matching sites available", "First-Come-First-Served."
	 * @param firstParkSiteTitle
	 * @param matched
	 */
	public void verifyTopParkCategoryTitle(String firstParkSiteTitle, String matched){
		String title = "";
		if(matched.equals(UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN)){
			title = this.getFirstParkSiteTypeTitle();
		}else if(matched.equals(UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN)){
			title = this.getFirstParkTourTitle();
		}else throw new ErrorOnDataException("No matched category title.");

		if(!title.equals(firstParkSiteTitle)){
			throw new ErrorOnDataException("The first park site title should be "+firstParkSiteTitle);
		}
	}

	public void verifyParkSiteTypeItems(String contractCode, String parkID, String[] siteTypes){
		String[] siteTypeViaUi = this.getParkSiteTypeItems(contractCode, parkID);
		logger.info(siteTypeViaUi[0]);
		if(siteTypes.length!=siteTypeViaUi.length){
			throw new ErrorOnDataException("The expect site types' length is wrong!", String.valueOf(siteTypes.length), 
					String.valueOf(siteTypeViaUi.length));
		}
		for(int i=0; i<siteTypes.length;i++){
			if(!siteTypes[i].equalsIgnoreCase(siteTypeViaUi[i])){
				throw new ErrorOnDataException("The expect site type "+siteTypes[i]+" " +
						"is defferent from the actual one "+siteTypeViaUi[i]);
			}
		}
	}

	public String[] getFirstParkSiteTypeItems(){
		String[] siteTypes = null;

		IHtmlObject[] summarySiteObj = browser.getHtmlObject(".className", "summary_sites_list");
		IHtmlObject[] siteTypesObj = browser.getHtmlObject(".class","Html.SPAN",".className", new RegularExpression("site_type_item( next_available)?",false), summarySiteObj[0]);
		siteTypes = new String[siteTypesObj.length];

		for(int i=0; i<siteTypes.length; i++){
			siteTypes[i] = siteTypesObj[i].text();
		}
		Browser.unregister(summarySiteObj, siteTypesObj);

		return siteTypes;
	}

	/**
	 * Check product category according to contract code and facility id
	 */
	public boolean checkProductCategory(String contractCode, String facilityID){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id",
				new RegularExpression("facility_view_card_"+contractCode+facilityID+"(_\\d+)?",false));
		Property[] p2 = Property.toPropertyArray(".className", new RegularExpression("summary\\_\\w+\\_list", false));

		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}

	/**
	 * Verify whether product category exist or not according to contract code and facility id
	 * @param contractCode
	 * @param facilityID
	 * @param flag
	 */
	public void verifyProductCategoryExist(String contractCode, String facilityID, boolean flag){
		boolean actualResult = this.checkProductCategory(contractCode, facilityID);
		if(actualResult!=flag){
			throw new ErrorOnDataException("Product Category should"+(flag?"":" not ")+" exist.");
		}
	}

	/**
	 * click the park site type hyperlink based on given parkID and site type name.
	 * @param parkID
	 * @param siteType
	 */
	public void clickSiteType(String parkID, String siteType){
		if(siteType.contains("(") || siteType.contains(")")){
			siteType = siteType.split("\\(")[0].trim().replaceAll(" ", "\\\\+");
		}
		String siteType2 = siteType.replace(" ", "\\+"); 
		RegularExpression regx = new RegularExpression(".*interface=csitetype.*parkId=" + parkID +"&siteType="+siteType2+ ".*", false);

		logger.info("Try to click site type with '.href' value:"+regx);
		///unifSearchInterface.do?interface=csitetype&contractCode=NRSO&parkId=70975&siteType=GROUP+PICNIC+AREA&isDayUse=Y
		browser.clickGuiObject(".class", "Html.A", ".href", regx, true);
		logger.info("Successfully click site type with '.href' value:"+regx);
	}

	/**
	 * Get Site Types info includes Site Types Title and Site Types value
	 * @return
	 */
	public String getSiteTypesInfo(){
		String siteTypesInfo = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "summary_sites_list");

		siteTypesInfo = objs[0].text();

		Browser.unregister(objs);
		return siteTypesInfo;
	}
	
	public String getSiteTypeInfo(String contractCode, String parkID){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id", new RegularExpression("facility_view_card_"+contractCode+parkID, false));
		Property[] p2 = Property.toPropertyArray(".className", "summary_sites_list");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs.length<1){
			throw new ObjectNotFoundException("Summaty site list objects can't be found when contractCode:"+contractCode+" and parkID:"+parkID);
		}
		String value = objs[0].text();

		Browser.unregister(objs);
		return value;
	}
	
	public boolean isParkHavingAvailableSites(String contractCode, String parkID){
		return this.getSiteTypeInfo(contractCode, parkID).startsWith(parkHasAvailableSiteMes);
	}
	
	/**
	 * Check whether the park list in Park List page equals to the expected.
	 * @param parkList: Expected park list values
	 */
	public void verifyParkList(List<String> parkList, boolean isAllPark){
		List<String> facilityListViaUi = this.getAllParkNames();
        boolean result = true;
        
        if(isAllPark){
        	result &= MiscFunctions.compareResult("All Park names size", parkList.size(), facilityListViaUi.size());
        	result &= MiscFunctions.compareResult("All Park Names", parkList.toString(), facilityListViaUi.toString());
        }else for(int i=0; i<parkList.size(); i++){
        	result &= MiscFunctions.containString(i+"-Park", facilityListViaUi.toString(), parkList.get(i));
        }
        //		if(facilityListViaUi.size()==parkList.size()){
//			for(int i=0; i<facilityListViaUi.size(); i++){
//				if(!facilityListViaUi.get(i).equals(parkList.get(i))){
//					throw new ErrorOnDataException("The expect facility name should be "+parkList.get(i)+"," +
//							" but the actual facility name is "+facilityListViaUi.get(i));
//				}
//			}
//		}else throw new ErrorOnDataException("The length of two compared list is different.", parkList.size(), facilityListViaUi.size());
        if(!result){
        	throw new ErrorOnPageException("Not all park name info are correct. Please check the details from previous logs.");
        }else logger.info("Successfully verify park names.");
	}

	public void verifyParkList(List<String> parkList){
		verifyParkList(parkList, true);
	}
	
	/**Click Book Sites button*/
	public void clickBookSites(){
		browser.clickGuiObject(".className", "book_now");
	}

	/**Check Previous*/
	public boolean checkPrevious(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(".*Previous.*",true));
	}

	/**Click Previous button*/
	public void clickPrevious(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(".*Previous.*",true));
	}

	/**Check Next existed*/
	public boolean checkNext(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression(".*UnifSearchEngine.nextPage()",true));
	}

	/**Click Next button*/
	public void clickNext(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Next.*",true));
	}

	/**
	 * Select page selector
	 * @param pageNum
	 */
	public void selectPageSelector(String pageNum){
		browser.selectDropdownList(".id", "pageSelector", pageNum);
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

		this.waitLoading();
	}

	/**
	 * operate page selector
	 * @param pageNum
	 */
	public void OperatePageSelector(String pageNum){
		this.selectPageSelector(pageNum);
		this.waitLoading();
	}

	/**Get Earliest Available Date*/
	public String getEarliestAvailableDate(String contractCode, String parkCode){
		Property[] p1=Property.toPropertyArray(".className","facility_view_card",".id",
				new RegularExpression("^facility_view_card_"+contractCode+parkCode+"(|\\d+)", false));
		Property[] p2=Property.toPropertyArray(".className", "earliest_available");
		IHtmlObject[] objs = browser.getHtmlObject((Property.atList(p1,p2)));

		String earliesAvailableDate = objs[0].text().split("Earliest available date: ")[1].trim();
		Browser.unregister(objs);
		return earliesAvailableDate;

	}

	public void verifyEarliestAvailableDateExist(String contractCode, String parkCode, boolean flag){
		boolean actualResult = this.checkEarliestAvailableDateExist(contractCode, parkCode);
		if(actualResult!=flag){
			throw new ErrorOnDataException("The Earliest Available Date should "+(flag?"":" not")+" be existed.");
		}
	}

	public boolean checkEarliestAvailableDateExist(String contractCode, String parkCode) {
		Property[] p1=Property.toPropertyArray(".className","facility_view_card",".id",
				new RegularExpression("^facility_view_card_"+contractCode+parkCode+"(|\\d+)", false));
		Property[] p2=Property.toPropertyArray(".className", "earliest_available");

		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}

	public void verifyEarliestAvailableDate(String contractCode, String parkID, String expectedEarliestAvailableDate){
		String actualEarliestAvailableDate = this.getEarliestAvailableDate(contractCode, parkID);
		expectedEarliestAvailableDate = DateFunctions.formatDate(expectedEarliestAvailableDate, "MMM d, yyyy");
		if(!actualEarliestAvailableDate.equals(expectedEarliestAvailableDate)){
			throw new ErrorOnDataException("The expected 'Earliest Abailable Date' should be: "+expectedEarliestAvailableDate+", " +
					"but the actual onw is: "+actualEarliestAvailableDate);
		}
	}

	public void clickBookSitesByParkID(int id) {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","check_avail_panel_NRSO"+id);
		Property[] p2=Property.toPropertyArray(".class","Html.A",".className","book_now",".text","Book Sites");

		browser.clickGuiObject(Property.atList(p1,p2),true,0);
	}

	public int getParkID(String parkName) {
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".className", "facility_link", ".text", new RegularExpression("^"+parkName+".*",false)));
		String href=objs[0].getAttributeValue(".href");
		Browser.unregister(objs);
		//		String[] tokens=RegularExpression.getMatches(href, "parkId=\\d+");

		String[] tokens=null;
		int parkID = -1;
		if(href.contains("recAreaId")){
			tokens=RegularExpression.getMatches(href, "recAreaId=\\d+");
			parkID=Integer.parseInt(tokens[0].substring(10));
		}else if(href.contains("parkId")){
			tokens=RegularExpression.getMatches(href, "parkId=\\d+");
			parkID=Integer.parseInt(tokens[0].substring(7));
		}else{
			tokens=RegularExpression.getMatches(href, "facilityId=\\d+");
			parkID=Integer.parseInt(tokens[0].substring(11));
		}
		return parkID;
	}

	/**
	 * Get park ids from first page
	 * @return
	 */
	public List<String> getFirstPgParkIDs(){
		List<String> parkIDs = new ArrayList<String>();
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".className", "facility_link"));
		for(int i=0; i<objs.length; i++){
			String href=objs[i].getAttributeValue(".href");
			Browser.unregister(objs[i]);
			String[] tokens = null;
			if(href.contains("recAreaId")){
				tokens=RegularExpression.getMatches(href, "recAreaId=\\d+");
				parkIDs.add(tokens[0].substring(10));
			}else if(href.contains("parkId")){
				tokens=RegularExpression.getMatches(href, "parkId=\\d+");
				parkIDs.add(tokens[0].substring(7));
			}else{
				tokens=RegularExpression.getMatches(href, "facilityId=\\d+");
				parkIDs.add(tokens[0].substring(11));
			}
		}

		return parkIDs;
	}

	public List<String> getParkIDs(){
		List<String> parkIDs = new ArrayList<String>();
		do{		
			IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".className", "facility_link"));
			for(int i=0; i<objs.length; i++){
				String href=objs[i].getAttributeValue(".href");
				Browser.unregister(objs[i]);
				String[] tokens = null;
				if(href.contains("recAreaId")){
					tokens=RegularExpression.getMatches(href, "recAreaId=\\d+");
					parkIDs.add(tokens[0].substring(10));
				}else if(href.contains("parkId")){
					tokens=RegularExpression.getMatches(href, "parkId=\\d+");
					parkIDs.add(tokens[0].substring(7));
				}else{
					tokens=RegularExpression.getMatches(href, "facilityId=\\d+");
					parkIDs.add(tokens[0].substring(11));
				}
			}

		}while(this.gotoNext());

		return parkIDs;
	}

	public String clickBookSitesForFirstPark() {
		String name=getFirstParkName();
		clickBookSitesByParkname(name);
		return name;
	}

	public void clickBookSitesByParkname(String parkName) {
		int parkId=getParkID(parkName);
		clickBookSitesByParkID(parkId);
	}

	public int getParkID(int index){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".className","facility_link");
		if(objs.length>0) {
			String href=objs[index].getAttributeValue(".href");
			Browser.unregister(objs);
			String[] tokens=RegularExpression.getMatches(href, "parkId=\\d+");
			return Integer.parseInt(tokens[0].substring(7));
		} else {
			return -1;
		}
	}
	
	public int getFirstParkID() {
		return getParkID(0);
	}

	public int getSecondParkID() {
		return getParkID(1);
	}
	
	public void clickMapPin(String parkName) {
		logger.info("Click pin on map for park: "+parkName);
		int id=getParkID(parkName);
//		browser.clickGuiObject(".class","Html.IMG",".id","usrpin_NRSO"+id);
		browser.clickGuiObject(".class","Html.IMG",".id", new RegularExpression("usrpin_.*"+id, false));//Lesley[08/16/2013]Update due to RA Unified Search in QA1/3 in 3.04.03
	}

	/**
	 * Check whether Park Reservation Directive info exist
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public boolean checkParkResDirectiveExist(String contractCode, String parkID){
		RegularExpression res = new RegularExpression("reservation\\_directive\\_" + contractCode + parkID, false);
		return browser.checkHtmlObjectExists(".id", res);
	}

	/**
	 * Verify whether Park Reservation Directive info exist or not
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public void verifyParkResDirectiveExist(String contractCode, String parkID, boolean flag){
		boolean actualResult = this.checkParkResDirectiveExist(contractCode, parkID);
		if(actualResult!=flag){
			throw new ErrorOnDataException("Park Reservation Directive info should "+(flag?"":" not ")+"be existed.");
		}
	}

	/**
	 * Get park Reservation Directive info
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public String getParkResDirectiveText(String contractCode, String parkID){//reservation_directive_NRSO73268
		RegularExpression res = new RegularExpression("reservation\\_directive\\_" + contractCode + parkID, false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", res);

		String resDirective = objs[0].text();

		Browser.unregister(objs);
		return resDirective;
	}

	/**
	 * Verify Park Reservation Directive info
	 * @param resDirective
	 */
	public void verifyParkResDirectiveText(String contractCode, String parkID, String resDirective){
		String currentResDirective = this.getParkResDirectiveText(contractCode, parkID);
		if(!currentResDirective.equals(resDirective)){
			logger.error("The current Reservation Directive is:" + currentResDirective );
			logger.error("The expect  Reservation Directive is:" + resDirective);
			throw new ErrorOnDataException("Verification for the resrevation directive info failed for park:" + parkID);
		}
	}

	/**
	 * Verify whether Availability Details link is existed or not
	 */
	public void verifyAvaiLabilityDetailsLinkExist(String contractCode, String parkID, boolean flag){
		boolean actualResult = this.checkAvaiLabilityDetailsLinkExist(contractCode, parkID);
		if(actualResult!=flag){
			throw new ErrorOnDataException("Availability Details link should "+(flag?"":" not ")+" be existed.");
		}
	}

	/**
	 * Check whether Availability Details link is existed or not
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public boolean checkAvaiLabilityDetailsLinkExist(String contractCode, String parkID){
		Property[] p1=Property.toPropertyArray(".className","facility_view_card",".id",
				new RegularExpression("^facility_view_card_"+contractCode+parkID+"(|_\\d+)", false));
		Property[] p2=Property.toPropertyArray(".class", "Html.A", ".text", "Availability Details");
		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}

	/**
	 * Verify given park's  Availability Details link text match with the given value
	 * @param contractCode
	 * @param parkID
	 * @param expectText
	 */
	public void verifyAvailabilityDetailsText(String contractCode, String parkID, String expectText){

		String currentText = this.getAvailabilityDetailsText(contractCode, parkID);
		if(!currentText.equals(expectText)){
			logger.error("The current availability link's text is:" + currentText);
			logger.error("The expect  availability link's text is:" + expectText);
			throw new ErrorOnDataException("Availability details link text does not match with the given value");
		}
	}

	/**
	 * get the availability details text based on given contract code and parkid.
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public String getAvailabilityDetailsText(String contractCode, String parkID){
		String availablilityText = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", new RegularExpression(".*campavdetails.*" + "contractCode="+contractCode+"&parkId=" +parkID +".*", false));

		if(objs.length >0){
			availablilityText = objs[0].text();
		}
		Browser.unregister(objs);
		return availablilityText;
	}

	/**
	 * Click First Availability Details link
	 */
	public void clickFirstAvailabilityDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Availability Details");
	}


	/**
	 * Click  Availability Details link based on given contract code and parkid
	 * @param contractCode
	 * @param parkID
	 */
	public void clickAvailabilityDetails(String contractCode, String parkID){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*campavdetails.*" + "contractCode="+contractCode+"&parkId=" +parkID +".*", false));
	}

	/**
	 * Click Next Available Date button on view as list page based on contractCode or parkID.
	 * @param contractCode
	 * @param parkID
	 */
	public void clickNextAvailableDate(String contractCode, String parkID){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*nextavcamp.*" + "contractCode="+contractCode+"&parkId=" +parkID +".*", false));
	}


	//	/**
	//	 * Verify Next Available Date 
	//	 */
	//	public void verifyNextAvailableDateExist(){
	//		boolean flag = browser.checkHtmlObjectExists(".class", "Html.A", ".className", "check_next_available");
	//		if(!flag){
	//			throw new ErrorOnDataException("Next Available Date button should be existed.");
	//		}
	//	}

	/**
	 * Verify whether "Next Available Date" exist or not for the given park.
	 * @param contractCode
	 * @param parkID
	 */
	public void verifyNextAvailableDateExist(String contractCode, String parkID, boolean flag){
		boolean actualResult = browser.checkHtmlObjectExists(".class", "Html.A",  ".href", new RegularExpression(".*nextavcamp.*" + "contractCode="+contractCode+"&parkId=" +parkID +".*", false));
		if(actualResult!=flag){
			throw new ErrorOnDataException("Next Available Date button should "+(flag?"":"not ")+"be existed.");
		}
	}

	/**
	 * Get the display Text value for "Next Available Date" button text.
	 * @param contractCode
	 * @param parkID
	 * @return
	 */
	public String getNextAvailableButtonText(String contractCode, String parkID){
		IHtmlObject[] objs =  browser.getHtmlObject(".class", "Html.A",  ".href", new RegularExpression(".*nextavcamp.*" + "contractCode="+contractCode+"&parkId=" +parkID +".*", false));
		String buttonText = "";
		if(objs.length >0){
			buttonText = objs[0].text();
		}

		return buttonText;
	}

	/**
	 * verify the Next available button's text match with the given property.
	 * @param contractCode
	 * @param parkID
	 * @param expectText
	 */
	public void verifyNextAvailableButtonText(String contractCode, String parkID, String expectText){
		String currentText = this.getNextAvailableButtonText(contractCode, parkID);

		if(currentText.equals(expectText)){
			logger.info("verify the text value for Next Availalbe Date button successful.");
		}else{
			logger.error("The expect  button text is:" + expectText);
			logger.error("The current button text is:" + currentText);
			throw new ErrorOnPageException("verification for Next Available Date button failed.");
		}

	}

	/**
	 * Click Next Available Date
	 */
	public void clickFirstNextAvailableDate(){
		Property[] btnProp = Property.toPropertyArray(".class", "Html.A", ".className", "check_next_available"); 
		while (!browser.checkHtmlObjectExists(btnProp) && this.checkNext()) {
			this.clickNext();
			this.waitLoading();
		}
		browser.clickGuiObject(btnProp);
	}

	protected Property[] checkAvailableDiv(String contractCode, String parkId) {
		return Property.concatPropertyArray(this.div(), ".id", "check_avail_panel_" + contractCode + parkId);
	}
	
	public boolean isCheckAvailableButtonExist(String contractCode, String parkId) {
		return browser.checkHtmlObjectExists(checkAvailableDiv(contractCode, parkId));
	}
	
	/**
	 * Click Check Availibility button
	 */
	public void clickFirstCheckAvailability(){
		browser.clickGuiObject(".class", "Html.A", ".className", "check_available");
	}

	/**
	 * click the Check Availability Button based on given parkID and contractCode info.
	 * @param parkId
	 * @param contractCode
	 */
	public void clickCheckAvailability(String parkId, String contractCode){
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".className", new RegularExpression("check_available|book_now", false));//Quentin[20130910] there will be 'Book Sites' button displayed, not only 'Check Availability'
		p[2] = new Property(".href", new RegularExpression(".*contractCode=" + contractCode + "&parkId=" + parkId + ".*", false));
		browser.clickGuiObject(p);
	}

	public void clickMarinaCheckAvailability(String parkId, String contractCode){
		browser.clickGuiObject(marinaCheckAvailability(parkId, contractCode));
	}
	
	public boolean isMarinaCheckAvailabilityExist(String parkId, String contractCode){
	    return browser.checkHtmlObjectExists(marinaCheckAvailability(parkId, contractCode));
	}
	
	/**
	 * verify the first facility's site types title match with the given value
	 * @param expectFacility
	 */
	public void verifyFirstParkSiteTypesTitle(String expectSiteTitle) {
		String actualSiteTitle = this.getFirstParkSiteTypeTitle();

		if(!actualSiteTitle.equalsIgnoreCase(expectSiteTitle)){
			throw new ErrorOnDataException("The expect site types title is:" + expectSiteTitle + "; while the current site types title is:" + actualSiteTitle);
		}
	}


	private IHtmlObject[] getParkAttentionObject(String contractCode, String parkCode){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class","Html.DIV",".id", "facility_view_card_"+contractCode+parkCode);
		IHtmlObject[] content=browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_content",topObjs[0]);
		return browser.getHtmlObject(".className", "special_message", content[0]);
	}

	public boolean checkAttentionExist(String contractCode, String parkCode){
		IHtmlObject[] topObjs = browser.getHtmlObject(".id", "facility_view_card_"+contractCode+parkCode);
		boolean flag = browser.checkHtmlObjectExists(".className", "special_message", topObjs[0]);

		Browser.unregister(topObjs);
		return flag;
	}

	public String getAttention(String contractCode, String parkCode){
		IHtmlObject[] objs = getParkAttentionObject(contractCode, parkCode);

		String attention = objs[0].text();
		Browser.unregister(objs);

		return attention;
	}

	public void verifyAttentionText(String contractCode, String parkCode, String expectText){
		String actualAttention = this.getAttention(contractCode, parkCode);
		if(!actualAttention.equals(expectText)){
			throw new ErrorOnDataException("Attention text value is wrong!", expectText, actualAttention);
		}
	}

	public String getParentNameByParkName(String parkName) {
		Property[] p=new Property[]{
				new Property(".class","Html.DIV"),
				new Property(".className","facility_view_header"),
				new Property(".text",new RegularExpression("^"+parkName.toUpperCase()+".*",false))};

		IHtmlObject[] objs=browser.getHtmlObject(p);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find facility header DIV for park:"+parkName);
		}

		String parentName=objs[0].getProperty(".text");
		parentName=parentName.split("part of")[1].split(",")[0];

		Browser.unregister(objs);
		return parentName;
	}

	/**
	 * Verify the facility near result header and the park on both of this near result header
	 * @param extractParkName
	 * @param firstNearParkName
	 */
	public void verifyParkOnBothOfNearResultHeader(String extractParkName, String firstNearParkName){
		String matchType = ".*Results near "+extractParkName+" \\[ \\* in straight line\\, not driving distance \\] {0,1}.*"+firstNearParkName+".*";
		if(matchType.contains("(")&& matchType.contains(")")){
			matchType = (matchType.replace("(", "\\(")).replace(")", "\\)");
		}
		if(!this.getFacilityViewListContent().matches(matchType)){
			throw new ErrorOnDataException("The park "+firstNearParkName+" doesn't display under Result near "+extractParkName);
		}
	}


	/**
	 * get warning message for park 
	 * @param targetParkName
	 * @return
	 */
	public String getWarningMsgByParkName(String targetParkName) {
		targetParkName=targetParkName.replace("(", "\\(").replace(")", "\\)");
		Property[] p=new Property[]{
				new Property(".class","Html.DIV"),
				new Property(".text",new RegularExpression("^"+targetParkName+".*", false)),
				new Property(".className","facility_view_card")};
		IHtmlObject[] objs=browser.getHtmlObject(p);
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("can't find Facility View Card");
		}

		IHtmlObject[] warningPanel=browser.getHtmlObject(".class","Html.DIV",".className","warning_panel",objs[0]);
		String msg;
		if(warningPanel==null||warningPanel.length<1){
			msg="";
		}else{
			msg=warningPanel[0].getProperty(".text");
		}
		Browser.unregister(warningPanel,objs);
		return msg;
	}

	public String getReservationDiretiveMsg(String targetParkName){
		targetParkName=targetParkName.replace("(", "\\(").replace(")", "\\)");
		Property[] p=new Property[]{
				new Property(".class","Html.DIV"),
				new Property(".text",new RegularExpression("^"+targetParkName+".*", false)),
				new Property(".className","facility_view_card")};
		IHtmlObject[] objs=browser.getHtmlObject(p);
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("can't find Facility View Card");
		}

		IHtmlObject[] warningPanel=browser.getHtmlObject(".class","Html.DIV",".id",new RegularExpression("reservation_directive_.*",false),objs[0]);
		String msg;
		if(warningPanel==null||warningPanel.length<1){
			msg="";
		}else{
			msg=warningPanel[0].getProperty(".text");
		}
		Browser.unregister(warningPanel,objs);
		return msg;
	}

	public void clickPanDown() {
		browser.clickGuiObject(".class", "Html.DIV", ".title", "Pan down");
	}


	/**
	 * 
	 */
	public boolean checkX_MarkExists() {
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", new RegularExpression(".*mapmarker_where\\.png",false));
	}

	/**
	 * Check Associated Facilities DIV exist
	 * @return
	 */
	public boolean checkAssociatedFacilitiesExist(String contractCode, String facilityId){
		Property[] p1 = Property.toPropertyArray("id", new RegularExpression("facility_view_card_"+contractCode+""+facilityId+"_\\d+", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "facility_children");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	/**
	 * Verify no child facilities display for specific facility
	 * @param contractCode
	 * @param parrkId
	 */
	public void verifyNoChildFacilities(String contractCode, String parrkId){
		if(this.checkAssociatedFacilitiesExist(contractCode, parrkId)){
			throw new ErrorOnDataException("Failed to verify no any child facilities listing.");
		}
		logger.info("Successfully verify no any child facilities listing.");
	}

	/**
	 * Check Associated Facilities DIV content
	 * @return
	 */
	public String getAssociatedFacilitiesContent(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_children");
		String associatedFacilitiesContent = objs[index].text();

		Browser.unregister(objs);
		return associatedFacilitiesContent;
	}

	/**
	 * click the first RIDB facility's child facility on search result page
	 */
	public void clickFirstAssociatedFacility(){

		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"),new Property(".className", "facility_children")};
		Property[] p2 = new Property[]{new Property(".class", "Html.A"),new Property(".href", new RegularExpression(".*contractCode.*parkId.*", false))};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null != objs && objs.length >0){
			objs[0].click();
		}else{
			throw new ErrorOnPageException("There is no child facility on the search result page.");
		}
		Browser.unregister(objs);
	}

	/**
	 * Get recreation area associated facilities heading info
	 * @return
	 */
	public String[] getAssociatedFacilitiesHeading(){
		IHtmlObject[] topObj= browser.getHtmlObject(".class", "Html.DIV",".className", "facility_children");
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",".className", "facility_heading", topObj[0]);

		String[] facilityHeading = new String[objs.length];
		for(int i=0; i<objs.length; i++){
			facilityHeading[i] = objs[i].text();
		}

		Browser.unregister(objs);
		return facilityHeading;
	}

	public String[] getAssociatedFacilitiesViaHeading(String facilityHeading){
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^"+facilityHeading+".*", false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "facility_item", topObjs[0]);

		String[] associatedFacilitie = new String[objs.length];
		for(int i=0; i<objs.length; i++){
			associatedFacilitie[i] = objs[i].text();
		}

		return associatedFacilitie;
	}

	/**
	 * Get all recreation area associated facilities heading and items info
	 * @param facilityHeading
	 * @return
	 */
	public List<String[]> getAssociatedFacilitiesAndHeading(String[] facilityHeading){
		List<String[]> associatedFacilities = new ArrayList<String[]>();
		for(int i=0; i<facilityHeading.length; i++){
			IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^"+facilityHeading[i]+".*", false));
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "facility_item", topObjs[0]);

			String[] associatedFacilitie = new String[objs.length+1];
			associatedFacilitie[0] = facilityHeading[i];
			for(int j=0; j<objs.length; j++){
				associatedFacilitie[j+1] = objs[j].text();
			}

			associatedFacilities.add(associatedFacilitie);
		}

		return associatedFacilities;
	}

	/**
	 * 
	 */
	public boolean checkAvaiLabilityButtonExists(String parkName) {
		parkName=parkName.replace("(", "\\(").replace(")", "\\)");
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".text", new RegularExpression("^"+parkName,false)),new Property(".id",new RegularExpression("facility_view_card.*",false))};
		IHtmlObject[] objs=browser.getHtmlObject(p);

		if(objs.length<1){
			throw new ObjectNotFoundException("can't find facility view card DIVs");
		}

		boolean flag=browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Check Availability", objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	public boolean checkAvaiLabilityDetailsLinkExists(String parkName) {
		parkName=parkName.replace("(", "\\(").replace(")", "\\)");
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".text", new RegularExpression("^"+parkName,false)),new Property(".id",new RegularExpression("facility_view_card.*",false))};
		IHtmlObject[] objs=browser.getHtmlObject(p);

		if(objs.length<1){
			throw new ObjectNotFoundException("can't find facility view card DIVs");
		}

		boolean flag=browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Availability Details", objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * @param parkName
	 * @return
	 */
	public boolean checkBookSitesButtonExists(String parkName) {
		parkName=parkName.replace("(", "\\(").replace(")", "\\)");
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".text", new RegularExpression("^"+parkName,false)),new Property(".id",new RegularExpression("facility_view_card.*",false))};
		IHtmlObject[] objs=browser.getHtmlObject(p);

		if(objs.length<1){
			throw new ObjectNotFoundException("can't find facility view card DIVs");
		}

		boolean flag=browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Book Sites", objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * @param parkName
	 * @return
	 */
	public boolean checkNextAvailableDateButtonExists(String parkName) {
		parkName=parkName.replace("(", "\\(").replace(")", "\\)");
		Property[] p=new Property[]{new Property(".class", "Html.DIV"),new Property(".text", new RegularExpression("^"+parkName,false)),new Property(".id",new RegularExpression("facility_view_card.*",false))};
		IHtmlObject[] objs=browser.getHtmlObject(p);

		if(objs.length<1){
			throw new ObjectNotFoundException("can't find facility view card DIVs");
		}

		boolean flag=browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next Available Date", objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	public void clickToursLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Tours\\([0-9]+\\)",false));
	}

	public void clickPermit() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Permit\\([0-9]+\\)",false));
	}
	public boolean isFirstParkPermitPark() {
		IHtmlObject[] topObjs=browser.getHtmlObject(".className","facility_view_card",".class","Html.DIV");
		if(topObjs==null||topObjs.length<1){
			throw new ObjectNotFoundException("can't find facility view card..");
		} 
		boolean flag=browser.checkHtmlObjectExists(".className", "summary_permits_list",".text",new RegularExpression("Permits and Wilderness",false), topObjs[0]);
		Browser.unregister(topObjs);
		return flag;
	}

	public boolean isFirstParkTicketPark(){
		IHtmlObject[] topObjs=browser.getHtmlObject(".className","facility_view_card",".class","Html.DIV");
		if(topObjs==null||topObjs.length<1){
			throw new ObjectNotFoundException("can't find facility view card..");
		} 
		boolean flag=browser.checkHtmlObjectExists(".className", "summary_tours_list",".text",new RegularExpression("Tours and Tickets",false), topObjs[0]);
		Browser.unregister(topObjs);
		return flag;
	}

	/**
	 * get Red/Green/Blue/Brown no map pin tooltip.
	 * @param facility
	 * @return
	 */
	public String getNoMapTooltip(String facility) {
		IHtmlObject[] tops=browser.getHtmlObject(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facility+".*",false));
		IHtmlObject[] noMapObjs=browser.getHtmlObject(".class", "Html.IMG", ".src", new RegularExpression(blueNoMapPinSRC+"|"+redNoMapPinSRC+"|"+greenNoMapPinSRC+"|"+brownNoMapPinSRC,false),tops[0]);
		if(noMapObjs==null || noMapObjs.length<1){
			throw new ObjectNotFoundException("can't find Red/Green/Blue/Brown no map pin tooltip.");
		}
		String tooltip=noMapObjs[0].getProperty(".title");
		Browser.unregister(tops,noMapObjs);
		return tooltip;
	}
	
	public String getNoMapTooltip(String contractCode, String parkID) {
		IHtmlObject[] tops=browser.getHtmlObject(".className", "facility_view_card", ".id", new RegularExpression("facility_view_card_"+contractCode+parkID+"(_\\d+)?",false));
		IHtmlObject[] noMapObjs=browser.getHtmlObject(".class", "Html.IMG", ".src", new RegularExpression(blueNoMapPinSRC+"|"+redNoMapPinSRC+"|"+greenNoMapPinSRC+"|"+brownNoMapPinSRC,false),tops[0]);
		if(noMapObjs==null || noMapObjs.length<1){
			throw new ObjectNotFoundException("can't find Red/Green/Blue/Brown no map pin tooltip.");
		}
		String tooltip=noMapObjs[0].getProperty(".title");
		Browser.unregister(tops,noMapObjs);
		return tooltip;
	}

	public void clickNoMapPin(String facility) {
		browser.clickGuiObject(Property.toPropertyArray(".className", "facility_view_card", ".text", new RegularExpression(" ?"+facility+".*",false), ".class", "Html.IMG", ".src", new RegularExpression(blueNoMapPinSRC+"|"+redNoMapPinSRC+"|"+greenNoMapPinSRC+"|"+brownNoMapPinSRC,false)));
	}

	/**
	 * Get the search result header
	 * Such as Results near <facility name> [ * in straight line, not driving distance ]
	 * @return
	 */
	public String getResultNearHeaderText() {
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), 
				new Property(".className", new RegularExpression("facility_view_header_(near|nearest)", false))};
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(searchResultListProp,p2));
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("can't find result near header info.");
		}
		String value=objs[0].text();
		Browser.unregister(objs);
		return value;
	}

	/**
	 * get Clickable facility name filters info, the return list including info like below
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

	protected Property[] letterFilterDiv(String title) {
		return Property.concatPropertyArray(this.div(), ".title", title);
	}
	
	protected Property[] letterFilterLink(String title) {
		return Property.concatPropertyArray(this.a(), ".title", title);
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
//		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","itemFilter")};
//		Property[] p2 = new Property[]{new Property(".class","Html.A"),new Property(".title",searchNameText)};
//
//		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
//		if(null == objs || objs.length < 1){
//			return false;
//		}
//		objs[0].click();
//		Browser.unregister(objs);
//		return true;
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.letterFilterDiv(searchNameText), this.letterFilterLink(searchNameText)));
		if (objs.length < 1) {
			return false;
		}
		browser.clickGuiObject(this.letterFilterDiv(searchNameText));
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
		if(null == objs || objs.length < 1){
			throw new ObjectNotFoundException("search result label object can't be found.");
		}

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

	/**
	 * Verify whether first park name match the expected one or not
	 * @param expectedFirstParkName: The expected first park name
	 * @param matchExpected  --false: The actual first park name should not match the expected one
	 *                       --true: The actual first park name should match the expected one
	 */
	public void verifyFirstParkName(String expectedFirstParkName, boolean matchExpected){
		String actualFirstParkName = this.getFirstParkName();
		if(actualFirstParkName.equalsIgnoreCase(expectedFirstParkName)!=matchExpected){
			throw new ErrorOnDataException("The actual first park name should "+(matchExpected? "":"not")+" matche with the expected one.", expectedFirstParkName, actualFirstParkName);
		}else{
			logger.info("Successfully verify the first park name which is "+(matchExpected? "":"not")+" matched with the expected one."+expectedFirstParkName);
		}

	}


	public boolean checkSearchTypeFilterExist(){
		return browser.checkHtmlObjectExists(".className", "filters", ".id", "filters");
	}

	public boolean checkSearchNameFilterExist(){
		return browser.checkHtmlObjectExists(".class","Html.DIV", ".className","letters");
	}

	public boolean isGreenNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_list", ".class", "Html.IMG", ".src", greenNoMapPinSRC));
	}

	public boolean isBlueNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_list", ".class", "Html.IMG", ".src", blueNoMapPinSRC));
	}

	public boolean isRedNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_list", ".class", "Html.IMG", ".src", redNoMapPinSRC));
	}

	public boolean isBrownNoMapPinExists( ){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".id", "search_results_list", ".class", "Html.IMG", ".src", brownNoMapPinSRC));
	}

	/**
	 * Get search result label via specific facility type filter and page number
	 * @param facilityTypeFilter
	 * @param pageNum: 1--first page
	 *                 2--second page
	 *                 ... 
	 * @return
	 */
	public String getSearchResultLabelViaFacilityTypeFilter(String facilityTypeFilter, int pageNum){
		logger.info("Get search result label via facility type filter: "+facilityTypeFilter);
		String searchResultLabel = "";
		int searchResultNum = Integer.parseInt(facilityTypeFilter.split("\\(")[1].replaceAll("\\)", ""));

		if(searchResultNum>(pageNum-1)*10){
			if(searchResultNum>=pageNum*10){
				searchResultLabel = "Search Results: "+Integer.valueOf((pageNum-1)*10+1)+"-"+pageNum*10+" of "+searchResultNum;
			}else{
				searchResultLabel = "Search Results: "+Integer.valueOf((pageNum-1)*10+1)+"-"+searchResultNum+" of "+searchResultNum;
			}
		}else{
			throw new ErrorOnDataException("Actual number is less than the expected.", 
					String.valueOf(pageNum*10), String.valueOf(searchResultNum));
		}

		return searchResultLabel;
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
		String num = String.valueOf(totalNum);
		if(totalNum>=10){
			if (num.length() > 3) {
				num = num.substring(0, num.length()-3) + "," + num.substring(num.length()-3);
			}
			searchResultLabel = "Search Results: 1-10 of "+num;
		}else{
			searchResultLabel = "Search Results: 1-"+totalNum+" of "+num;
		}
		return searchResultLabel;
	}

	/**
	 * Get first page search result label via specific facility type filter
	 * @param facilityTypeFilter
	 * @return
	 */
	public String getFirstPgSearchResultLabelViaFacilityTypeFilter(String facilityTypeFilter){
		return this.getSearchResultLabelViaFacilityTypeFilter(facilityTypeFilter, 1);
	}

	/**
	 * Verify first page search result label info via specific facility type filter
	 * @param facilityTypeFilter
	 */
	public void verifyFirstPgSearchResultLabelViaFacilityTypeFilter(String facilityTypeFilter){
		logger.info("Verify search result label via facility type filter: "+facilityTypeFilter);
		String searchResultLabel = this.getFirstPgSearchResultLabelViaFacilityTypeFilter(facilityTypeFilter);
		this.verifySearchResultLabelEquals(searchResultLabel);
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
	 * get last facility name in current page;
	 * @return
	 */
	public String getLastFacilityViewHeader() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header");
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find facility header DIV");
		}
		String header=objs[objs.length-1].text();
		Browser.unregister(objs);
		return header;
	}

	/**
	 * Click park name according to contract code and park id
	 * @param contractCode
	 * @param parkId
	 */
	public void clickParkName(String contractCode, String parkId) {
		//Lesley[20131129]: update from ".*interface=camping&contractCode=" to support event ticket lottery  
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*(interface=camping&)?contractCode="+contractCode+"&parkId="+parkId,false));
	}


	/**
	 * Click facility name
	 * @param facilityName
	 */
	public void clickFacility(String facilityName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^" + facilityName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)") + ".*", false),true);
	}

	/**
	 * Return the boolean value that if the result filters displays
	 * @return
	 */
	public boolean isResultFiltersDisplaying(){
		return browser.checkHtmlObjectExists(".className", "resultsFilters", ".id", "resultsFilters");
	}

	/**
	 * Verify if the result filters displays
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
		//Lesley[20131025] Use DIV instead of A to click the filter because use A will cause an "Not Found" issue.
		//Sara[10/28/2013] add "^" and "$" in regx
//		browser.clickGuiObject(".class", "Html.Div", ".title", new RegularExpression("^"+childObjTitleValue+"$", false));
//		for(int i=0; i<itemFilterObjs.length; i++){
////			detailItemsObjs = browser.getHtmlObject(".class", "Html.A", ".title", childObjTitleValue, this.getItemFilterObject()[i]);
//			detailItemsObjs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression(childObjTitleValue, false), itemFilterObjs[i]);
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
			Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
			this.waitLoading();
		}

		if(!StringUtil.isEmpty(letterItem)){
			this.clickItemFilter(letterItem);
			Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
			this.waitLoading();
		}
	}

	/**
	 * Get filter category objs
	 * @return
	 */
	public IHtmlObject[] getFilterCategoryObjs(){
//		Property[] p1 = Property.toPropertyArray(".id", "resultsFilters", ".className", "resultsFilters");
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "unifSearch");
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
	 * Get result filters content (type, agency and first letter of name)
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
     * Get filter structures
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
	 * Verify filter structure: Type, Agency, First Letter of Name
	 * @param expectedFilterCategorys
	 */
	public void verifyFilterStructure(String[] expectedFilterCategorys){
		String[] actualFilterCategorys = this.getFilterStructures();

		if(expectedFilterCategorys.length!=actualFilterCategorys.length){
			throw new ErrorOnPageException("Compared to string lists have different length", expectedFilterCategorys.length, actualFilterCategorys.length);
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
	public IHtmlObject[] getAgencyFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", new RegularExpression(agencyFilterItemIDReg, false))); // RegularExpression("A_\\d+", false))); Sara, 6/20/2013
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("Agency filter object can't be found.");
		}
		return objs;
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
	 * Get only agency filter objs
	 * @return
	 */
	public IHtmlObject[] getOnlyAgencyFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "defaultItem", ".id", new RegularExpression(agencyFilterItemIDReg, false))); // RegularExpression("A_\\d+", false)));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Agency filter object can't be found.");
		}
		if(objs.length!=1){
			throw new ObjectNotFoundException("Only one Agency filter object should be found.");
		}
		return objs;
	}
	
	/**
	 * Get selected agency filter objs
	 * @return
	 */
	public IHtmlObject[] getSelectedAgencyFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter selected", ".id", new RegularExpression(agencyFilterItemIDReg, false)));// RegularExpression("A_\\d+", false)));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Agency filter object can't be found.");
		}
		if(objs.length!=1){
			throw new ObjectNotFoundException("Selected agency filter object should be found.");
		}
		return objs;
	}
	
	/**
	 * Get selected agency filter option value
	 * @return
	 */
	public String getSelectedAgencyFilterName(){
		IHtmlObject[] objs = this.getSelectedAgencyFilterObjs();
		String value = objs[0].text().trim();
		
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Verify selected agency filter option value
	 * @param expectedValue
	 */
	public void verifySelectedAgencyFilterName(String expectedValue){
		String actualValue = this.getSelectedAgencyFilterName();
		if(actualValue.equals(expectedValue)){
			throw new ErrorOnPageException("Selected agency filter option value is wrong.", expectedValue, actualValue);
		}
		logger.info("Successfully verify selected agency filter option value - "+actualValue);
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
	 * Get first letter of name objs
	 * @return
	 */
	public IHtmlObject[] getFirstLetterOfNameFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter", ".id", new RegularExpression("^A_([A-Z]|#)$", false)));
		if(objs!=null && objs.length<1){
			throw new ObjectNotFoundException("First letter of Name filter object can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get selected agency filter objs
	 * @return
	 */
	public IHtmlObject[] getSelectedFirstLetterOfNameFilterObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "itemFilter selected", ".id", new RegularExpression("A_(#|[A-Z])", false)));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("First letter of Name filter object can't be found.");
		}
		if(objs.length!=1){
			throw new ObjectNotFoundException("Selected first letter of name filter object should be found.");
		}
		return objs;
	}
	
	/**
	 * Get selected first letter of name filter option content
	 * @return
	 */
	public String getSelectedFirstLetterOfNameFilterValue(){
		IHtmlObject[] objs = this.getSelectedFirstLetterOfNameFilterObjs();
		String value = objs[0].text().trim();

		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Verify selected first letter of name filter option value
	 */
	public void verifySelectedFirstLetterOfNameFilterOptionValue(String value){
		String actualValue = this.getSelectedFirstLetterOfNameFilterValue();
		if(!value.equals(actualValue)){
			throw new ErrorOnPageException("Selected first letter of name filter option value is wrong!", value, actualValue);
		}
		logger.info("Successfully verify selected first letter of name filter option value - "+actualValue);
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
	 * Get clear filter objs 
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
	 * Return boolean value to identifier if the clearing filter display
	 * @param filterCategory
	 * @return
	 */
	public boolean checkClearingFilterDisplay(String filterCategory){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", 
				".title", new RegularExpression("Clear (filter|all filters)", false), 
				".text", new RegularExpression("\\[x\\]"+filterCategory, false)));
	}

	/**
	 * Verify if the clearing filter display
	 * @param flag: true: display, false:doesn't display
	 * @param filterCategory
	 */
	public void verifyClearFilterDisplay(boolean flag, String filterCategory){
		boolean actualValue  = this.checkClearingFilterDisplay(filterCategory);
		if(flag!=actualValue){
			throw new ErrorOnPageException("Clear filter:"+filterCategory+" should"+(flag?"":" not")+" display");
		}
		logger.info("Successfully verify clear filter:"+filterCategory+(flag?"":" doesn't")+" display");
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
	 * This is private method to get all filters(type, agency and letter) results names and numbers 
	 * @param filterType: 
	 * UwpUnifiedSearch.FILTERCATEGORY_TYPE
	 * UwpUnifiedSearch.FILTERCATEGORY_AGENCY
	 * UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME
	 * @return
	 */
	public Map<String, Integer> getFiltersNamesAndNums(String filterType){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		String nameAndNum, name = ""; 
		int num = -1;
		IHtmlObject[] objs = null;
		
		if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_TYPE)){
			objs = this.getTypeFilterObjs();
		}else if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_AGENCY)){
			objs = this.getAgencyFilterObjs();
		}else if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME)){
			objs = this.getFirstLetterOfNameFilterObjs();
		}else throw new ErrorOnPageException("No matched fitler type can be found.");
		
		for(int i=0; i<objs.length; i++){
			nameAndNum = objs[i].text().trim(); //(38)Bureau of Reclamation
			name = nameAndNum.split("\\)")[1].trim();
			num = Integer.valueOf(nameAndNum.split("\\)")[0].split("\\(")[1].trim());
			map.put(name, num);
		}

		Browser.unregister(objs);
		return map;
	}
	
	/**
	 * This is private method to get all filters(type, agency and letter) results names
	 * @param filterType: 
	 * UwpUnifiedSearch.FILTERCATEGORY_TYPE
	 * UwpUnifiedSearch.FILTERCATEGORY_AGENCY
	 * UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME
	 * @return
	 */
	public List<String> getFilterNamesFromUI(String filterType){
		List<String> names = new ArrayList<String>();
		String nameAndNum = ""; 
		IHtmlObject[] objs = null;
		
		if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_TYPE)){
			objs = this.getTypeFilterObjs();
		}else if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_AGENCY)){
			objs = this.getAgencyFilterObjs();
		}else if(filterType.equals(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME)){
			objs = this.getFirstLetterOfNameFilterObjs();
		}else throw new ErrorOnPageException("No matched fitler type can be found.");
		
		for(int i=0; i<objs.length; i++){
			nameAndNum = objs[i].text().trim(); //(38)Bureau of Reclamation
			names.add(nameAndNum.split("\\)")[1].trim());
		}

		Browser.unregister(objs);
		return names;
	}

	/**
	 * Get filters(type, agency and letter) result total number
	 * @param filterType: 
	 * UwpUnifiedSearch.FILTERCATEGORY_TYPE
	 * UwpUnifiedSearch.FILTERCATEGORY_AGENCY
	 * UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME
	 * @return
	 */
	public int getFilterResultTotalNum(String filterType){
		int num = 0;
		Map<String, Integer> namesAndnums = this.getFiltersNamesAndNums(filterType);
		for(Object o:namesAndnums.keySet()){
			num += namesAndnums.get(o);
		}
		System.out.println(12);
		return num;
	}
	
	/**
	 * Verify filters((type, agency and letter) ) result total number
	 * @param expectedNum
	 */
	public void verifyFilterResultTotalNum(int expectedNum, String filterType){
		int actualNum = this.getFilterResultTotalNum(filterType);
		if(expectedNum!=actualNum){
			throw new ErrorOnPageException(filterType+" filter result total number is wrong!", expectedNum, actualNum);
		}
		logger.info("Successfully verify "+filterType+" filter result total number:"+actualNum);
	}
	
	/**
	 * Get result filters((type, agency and letter) ) numbers
	 * @param filterType
	 * @return
	 */
	public List<Integer> getFilterNums(String filterType){
		List<Integer> nums = new ArrayList<Integer>();
		Map<String, Integer> namesAndnums = this.getFiltersNamesAndNums(filterType);
		for(Object o:namesAndnums.keySet()){
			nums.add(namesAndnums.get(o));
		}
		return nums;
	}
	
	/**
	 * Verify specific agency filter options number
	 * @param agencyOption
	 * @param expectedNum
	 */
	public void verifyAgencyFilterOptionNum(String agencyOption, int expectedNum){
		int actualNum = this.getAgencyFilterOptionNum(agencyOption);
		if(expectedNum!=actualNum){
			throw new ErrorOnPageException("Agency filter option:"+agencyOption+" number is wrong", expectedNum, actualNum);
		}
		logger.info("Successfully verify agency filter option:"+agencyOption+" number:"+actualNum);
	}

	/**
	 * Get filters((type, agency and letter) ) names
	 * @param filterType
	 * @return
	 */
	public List<String> getFilterNames(String filterType){
		List<String> names = new ArrayList<String>();
		Map<String, Integer> namesAndnums = this.getFiltersNamesAndNums(filterType);
		Set<Map.Entry<String, Integer>> set = namesAndnums.entrySet();
		for (Iterator<Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			names.add(entry.getKey());
		}
		return names;
	}

	public boolean isFacilityTypeFilterOptionExist(String title) {
		boolean result = browser.checkHtmlObjectExists(".class", "Html.A", ".title", title);
		if (!result) {
			result = browser.checkHtmlObjectExists(".class", "Html.DIV", ".title", title); //Lesley[20131101]: handle the scenario that only one option exists.
		}
		return result;
	}
	
	public void verifyFacilityTypeFilterOptionsExist(String[] titles, boolean expect) {
		boolean result = true;
		for (int i = 0; i < titles.length; i++) {
			result &= MiscFunctions.compareResult(titles[i] + " filter type option", expect, isFacilityTypeFilterOptionExist(titles[i]));
		}
		if (!result) {
			throw new ErrorOnPageException("the existence of the filter type options are wrong! Please check logger error!");
		}
		logger.info("the existence of the filter type options are correct!");
	}
	
	public void verifyFacilityTypeFilterOptionsExist(String[] titles, boolean[] expects) {
		boolean result = true;
		for (int i = 0; i < titles.length; i++) {
			result &= MiscFunctions.compareResult(titles[i] + " filter type option", expects[i], isFacilityTypeFilterOptionExist(titles[i]));		
		}
		if (!result) {
			throw new ErrorOnPageException("the existence of the filter type options are wrong! Please check logger error!");
		}
		logger.info("the existence of the filter type options are correct!");
	}
	
	private IHtmlObject[] getFacilityTypeFilterOptionLinks(String title) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".title", title);
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException(title + " facility type filter doesn't exist!");
		}
		return objs;
	}
	
	public String getFacilityTypeFilterOptionName(String title) {
		IHtmlObject[] objs = this.getFacilityTypeFilterOptionLinks(title);
		IHtmlObject[] divs = browser.getHtmlObject(".class", "Html.DIV", ".classname", "item", objs[0]);
		if (divs == null || divs.length < 1) {
			throw new ErrorOnPageException(title + " facility type filter name div doesn't exist!");
		}
		String text = divs[0].text().trim();
		Browser.unregister(objs, divs);
		return text;
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
	
	public int[] getFacilityTypeFilterResultNums(String[] titles) {
		int[] nums  = new int[titles.length];
		for (int i = 0; i < titles.length; i++) {
			nums[i] = this.getFacilityTypeFilterOptionResultNum(titles[i]);
		}
		return nums;
	}
	
	public int getFacilityTypeFiltersResultTotalNum(String[] titles){
		int total = 0;
		int num;
		for(int i=0; i < titles.length; i++){
			num = this.getFacilityTypeFilterOptionResultNum(titles[i]);
			if (num < 1) {
				throw new ErrorOnPageException("The result count shouldn't be equal to 0!");
			}
			total += num;
		}
		return total;
	}
	
	public String getSelectedFacilityTypeFilterName() {
		return browser.getObjectText(Property.toPropertyArray(
				".class", "Html.DIV", ".className", "itemFilter selected", ".id", new RegularExpression("[A-Z]+", false)));
	}
	
	public void verifySelectedFacilityTypeFilterName(String expectName) {
		String actualName = this.getSelectedFacilityTypeFilterName();
		if (!expectName.equals(actualName)) {
			throw new ErrorOnPageException("The selected facility type filter name is wrong!", expectName, actualName);
		}
		logger.info("The selected facility type filter name is correct!");
	}
	
	public void clickClearAllFilters(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.DIV", ".title", "Clear all filters", 
				".text", new RegularExpression("\\[x\\]"+UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, false)), true);
	}

	/**
	 * Check if clearing filter(all, type, agency, first letter of name) exists
	 * @param filterType
	 * @return
	 */
	public boolean isClearingFilterExist(String filterType){
		Property[] p = Property.toPropertyArray(
				".class", "Html.DIV", 
				".title", new RegularExpression("Clear (filter|all filters)", false), 
				".text", new RegularExpression("\\[x\\]"+filterType, false));
		return browser.checkHtmlObjectExists(p);
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
	
	/**
	 * Get first letter of name result filter numbers
	 * @return
	 */
	public List<Integer> getFirstLetterOfNameFilterNums(){
		List<Integer> nums = new ArrayList<Integer>();
		Map<String, Integer> namesAndnums = this.getFiltersNamesAndNums(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		for(Object o:namesAndnums.keySet()){
			nums.add(namesAndnums.get(o));
		}
		return nums;
	}

	/**
	 * Get first letter of name result filter names
	 * @return
	 */
	public List<String> getFirstLetterOfNameFilterNames(){
		List<String> names = new ArrayList<String>();
		Map<String, Integer> namesAndnums = this.getFiltersNamesAndNums(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		Set<Map.Entry<String, Integer>> set = namesAndnums.entrySet();
		for (Iterator<Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			names.add(entry.getKey());
		}
		return names;
	}

	/**
	 * Check if the first letter of name filter option link exist
	 * @param optionName
	 * @return
	 */
	public boolean isFirstLetterOfNameFilterOptionExist(String optionName) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".title", optionName);
	}
	
	public void verifyFirstLetterFilterOptionsExist(List<String> optionsNames, boolean expect) {
		boolean result = true;
		String name;
		for (int i = 0; i < optionsNames.size(); i++) {
			name = optionsNames.get(i);
			if (name == "O") {
				name = "0";
			}
			result &= MiscFunctions.compareResult(name + " letter filter option existence", expect, isFirstLetterOfNameFilterOptionExist(name));
		}
		if (!result) {
			throw new ErrorOnPageException("the existence of the letter filter type options are wrong! Please check logger error!");
		}
		logger.info("the existence of the filter type options are correct!");
	}
	
	public IHtmlObject[] getChildParksObjsBasedOnParkType(String contractCode, String facilityID, String childFacilitiesType){
		Property[] p1 = Property.toPropertyArray(".class","Html.DIV",".id",new RegularExpression("facility_view_card_"+contractCode+facilityID+"(_\\d+)?", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV",".text", new RegularExpression("^"+childFacilitiesType+".*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Facility child facilities DIV objects with type:"+childFacilitiesType+" under parkID:"+facilityID+", and contractCode:"+contractCode+" can't be found");
		}
		return objs;
	}
	
	public IHtmlObject[] getChildParkItemsObjsBasedOnParkType(String contractCode, String facilityID, String childFacilitiesType){
		IHtmlObject[] childFacilitiesDIVObjs = this.getChildParksObjsBasedOnParkType(contractCode, facilityID, childFacilitiesType);
		Property[] p=Property.toPropertyArray(".class", "Html.SPAN",".className", "facility_item");
		IHtmlObject[] objs = browser.getHtmlObject(p, childFacilitiesDIVObjs[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Facility child facilities items objects with type:"+childFacilitiesType+" under parkID:"+facilityID+", and contractCode:"+contractCode+" can't be found");
		}
		Browser.unregister(childFacilitiesDIVObjs);
		return objs;
	}
	
	public IHtmlObject[] getChildParkItemsObjsBasedOnChildParkName(String contractCode, String parentParkID, String childParkName){
		Property[] p1 = Property.toPropertyArray(".class","Html.DIV",".id",new RegularExpression("facility_view_card_"+contractCode+parentParkID+"(_\\d+)?", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.SPAN",".className", "facility_item", ".text", new RegularExpression(childParkName, false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs.length<1){
			throw new ObjectNotFoundException("Child facility item "+childParkName+" objects under parkID:"+parentParkID+", and contractCode:"+contractCode+" can't be found");
		}
		
       return objs;
	}
	
	public List<String> getChildFacilities(String contractCode, String facilityID, String childFacilitiesType){
		IHtmlObject[] objs = this.getChildParkItemsObjsBasedOnParkType(contractCode, facilityID, childFacilitiesType);
				List<String> values = new ArrayList<String>();
		
		for(int i=0; i<objs.length; i++){
			values.add(objs[i].text().trim());
		}
		
		Browser.unregister(objs);
		return values;
	}
	
	public void clickChildPark(String contractCode, String parentParkID, String childParkName){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A"), true, 0, getChildParkItemsObjsBasedOnChildParkName(contractCode, parentParkID, childParkName)[0]);
	}
	
	public boolean checkFacilityPhotoExist(String contractCode, String parkID, String description){
		Property[] p1 = Property.toPropertyArray(".className", "facility_view_card", ".id", "facility_view_card_"+contractCode+parkID);
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
}

