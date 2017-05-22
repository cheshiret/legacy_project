/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.common.camping;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * This is the master page for campsite search result. it will generate 4 child sub pages(FacilityDetails, FacilityMap,SiteList, Date Range Availability )
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Jul 21, 2011
 */
public class UwpCampingPage extends UwpHeaderBar {

	public static UwpCampingPage _instance = null;
	
	public static UwpCampingPage getInstance(){
		if (null == _instance){
			_instance = new UwpCampingPage();
		}
		return _instance;
	}
	
	protected UwpCampingPage(){
		
	}

	protected Property[] mapLink() {
		return Property.concatPropertyArray(this.a(), ".id", "viewRegionalMap");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".id", "cgroundName");
	}
	
	public boolean verifySiteTypeAndNumInfoExist() {
	  	return browser.checkHtmlObjectExists(".class", "Html.A", ".href",new RegularExpression(".*sitefilter.*", false));
	}
	
	/**
	 * get the park/campground name and related state code which are displayed on the top of the page.
	 * @return
	 * Sample: Desolation Wilderness Permit, CA
	 */
	public String getCampGroundNameAndRelatedStateCode(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", "cgroundName");
	  	String campName = "";
	  	
	  	if(objs != null && objs.length>0){
	  		campName = objs[0].text();
	  	}
	  	
	  	Browser.unregister(objs);
	  	return campName;
	}
	
	/**
	 * verify whether the campground name displayed on the page match with given name or not.
	 * @param expectName
	 */
	public void verifyCampGroundNameAndRelatedStateCode(String name, String stateCode){
		String actualValue = this.getCampGroundNameAndRelatedStateCode();
		String expectedRegx = name +", ?"+ stateCode;
		if(actualValue.matches(expectedRegx)){
			logger.info("verify campground name and state successfully");
		}else{
			throw new ErrorOnDataException("verify for campground name failed because "+actualValue+" doesn't match "+expectedRegx);
		}
	}
	
	/**
	 * @param isDisplay: Expect display statues; True-expect to be displayed, False - expect not to be displayed.
	 * @return
	 */
	public boolean verifySiteTypeAndNumInfoExist(boolean isDisplay){
		boolean flag = true;
		if (this.verifySiteTypeAndNumInfoExist()){
			if (!isDisplay) {
				flag = false;
			}
		} else{
			if (isDisplay){
				flag = false;
			}
		}
		return flag;	
	}
	
	/**
	 * Verify whether the camp map, site list or date range link available.
	 * @param id - object's id value
	 * @return true - available; false - not available
	 */
	public boolean isTabDisable(String id) {
	  	IHtmlObject[] objs=browser.getHtmlObject(".id", new RegularExpression(id, false));
	  	boolean toReturn=false;
	  	
	  	if(objs[0].getProperty(".className").toString().equals("disabled")){
	  	  	toReturn=true;
	  	}
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	public boolean isTabSelected(String id, String text) {
		IHtmlObject[] objs=browser.getHtmlObject(".id", new RegularExpression(id, false), ".text", new RegularExpression(text, false));
	  	boolean toReturn=false;
	  	
	  	if(objs[0].getProperty(".className").toString().endsWith("selected")){
	  	  	toReturn=true;
	  	}
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	/**
	 * Verify whether the site type and number hyperlink's color match with the given color code.
	 * @param text - object's text value
	 * @return true - the actual color match with the given expect color; false - the actual color DIDN't match with the given expect color
	 */
	public boolean verifySiteTypeNumDispInExpectColor(String text,String expectColor) {
	  	String actualColor ="1";
	  	
	  	boolean flag = false; 
	  	
	  	actualColor = this.getSiteTypeNumLinkColor(text);	  	
	  	if(actualColor.equalsIgnoreCase(expectColor)){
	  		flag=true;
	  	}	  	
	  	return flag;
	}
	
	/**
	 * this function used to get the waring message displayed on SiteListTAB.
	 * the warning message triggered by  add invalued value in Find sites panel for more
	 * @return
	 */
	public String getErrorMes(){
		String warnMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV", ".className", new RegularExpression("(msg error|msg topofpage)", false));
		if(objs.length >0){
			warnMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return warnMsg;
	}
	
	/**
	 * @param expectError
	 */
	public void verifyWarningMsg(String expectError){
		String currentError = this.getErrorMes();
		if (expectError.equalsIgnoreCase(currentError)){
			logger.info("Verification for error messsage successfully");
		}else{
			throw new ErrorOnPageException("Verification for error message failed on campsite list page..", expectError, currentError);
		}
	}
	
	/** Get Sub warning message. 
	 *   Note: It is used when the top error message (classname = msg topofpage) 
	 *   and the sub message (classname = msg error) are shown at the same time.
	 */
	public String getSubWarningMes() {
		String warnMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV", ".className", new RegularExpression("msg error", false));
		if(objs.length >0){
			warnMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return warnMsg;
	}
	
	public void verifySubWarningMsg(String expectError){
		String currentError = this.getSubWarningMes();
		if (expectError.equalsIgnoreCase(currentError)){
			logger.info("Verification for error messsage successfully");
		}else{
			throw new ErrorOnPageException("Verification for error message failed on campsite list page..", expectError, currentError);
		}
	}
	
	/**
	 * Verify the message is included in the expected message list
	 * @param String[] expectMes
	 */
	public void verifyWarningMsg(String[] expectMes){
		String currentError = this.getErrorMes();
		boolean passed = true;

		for(int i=0; i<expectMes.length; i++){
			passed |= MiscFunctions.compareResult("Message "+i, expectMes[i], currentError);
		}

		if(!passed){
			throw new ErrorOnPageException("Verification for error message failed on campsite list page. Please check details info as previous logs.");
		}
	}
	
	public boolean verifyCampgroundDetailDisabled(){
		return  isTabDisable("campDetail");
	}
	
	public boolean verifyCampgroundMapDisabled(){
		return  isTabDisable("campMap");
	} 
	
  	public boolean verifyCampSiteListDisabled(){
  		return isTabDisable("campList");
  	} 
  	
	public boolean verifyCampSiteListSelected(){
  		return isTabSelected("list_view_switch", "Campsite List");
  	} 
	
  	public boolean verifyDateRangeAvailabilityDisabled(){
  		return isTabDisable("campCalendar|calendar_view_switch");
  	} 
  	
  	public boolean verifyDateRangeAvailabilitySelected(){
  		return isTabSelected("calendar_view_switch", "Date Range Availability");
  	}
  	
	public boolean verifyDateRangeAvailabilityTabExist(){
  		return browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".id", "calendar_view_switch", ".text", "Date Range Availability"));
  	}
	
  	/** Click on Campground Map tab link.*/
	public void clickCampgroundDetails() {
	  	browser.clickGuiObject(".id", "campDetail");
	}	
	
	/** Click on Campground Map tab link.*/
	public void clickCampgroundMap() {
	  	browser.clickGuiObject(".id", "campMap");
	}
	
	/** Click on Date Range Availability tab link.*/
	public void clickDateRangeAvailability() {
	  	browser.clickGuiObject(".id", "campCalendar");
	}
	
	/** Click on campsite list tab link.*/
	public void clickCampsiteList() {
	  	browser.clickGuiObject(".class", "Html.A", ".id", "campList"); //Lesley[20131115]: update for RA and REC
//		browser.clickGuiObject(Property.concatPropertyArray(span(), ".className", "caption", ".text", "Campsite"));
	}
	
	/**
	 * verify all site type and number text display in expect format.
	 * @return
	 */
	public boolean verifyAllSiteTypeNumDispFormat(){
		List<String> siteTypeNumList = getSiteTypeNumText();
		boolean flag = true;
		
		for (int i = 0; i<siteTypeNumList.size(); i++ ){
			if (!this.verifySiteTypeAndNumFormat(siteTypeNumList.get(i))){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * verify the site type and number hyperlinks display in alpha order
	 * @return
	 */
	public boolean verifySiteTypeNumDispInAlphOrder(){
		List<String> siteTypeNumList = this.getSiteTypeNumText();
		boolean flag = true;
		
		logger.info("verify the sitetype and number display in alphbetically order...");
		
		// the first site type should be ALL()..
//		if (!siteTypeNumList.get(0).equals(new RegularExpression("^ALL \\(\\d+\\)$", true))){
//			return false;
//		}
				
		for (int i = 0; i<siteTypeNumList.size(); i++ ){
			for(int j = i+1; j<siteTypeNumList.size(); j++){
				if (siteTypeNumList.get(i).compareTo(siteTypeNumList.get(j)) > 0){
					flag = false;
					break;
				}
			}
			if (flag == false){
				break;
			}
		}
		return flag;
	}
	

	public boolean verifySiteTypeAndNumFormat(String siteTypeNumInfo){
		String pattern = ".* \\(\\d+\\)$";

		if(siteTypeNumInfo.matches(pattern)){
			return true;
		} 
		else {
			return false;
		}
	}
	
	/**
	 * Get site type and number display Text(including site type and number), return as a String List.
	 * @return  for example: All (16), Environmental Site (10), Group Day Use (5), Group Tent Only (1)
	 */
	public List<String> getSiteTypeNumText(){
		List<String> siteTypeNumInfos = new ArrayList<String>();
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "filters site");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".href",new RegularExpression(".*sitefilter.*", false));
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(p1, p2));
		logger.info("parse and get Site type and site number info..");
		if (null != objs && objs.length >0){
			for(IHtmlObject stInfo: objs ){
				String temp = stInfo.getProperty(".text");
				if(!siteTypeNumInfos.contains(temp)) {
					siteTypeNumInfos.add(temp);
				}
			}
		}
		
		Browser.unregister(objs);
		if (null != siteTypeNumInfos && siteTypeNumInfos.size()>0){
			return siteTypeNumInfos;
		} else{
			throw new ErrorOnDataException("can't get site type and number info from camping page");
		}
	}
	
	/**
	 * Retrieve the total number of available sites from matching and available message by adding clickable link's number together except ALL().
	 * @return - total number
	 */
	public int getTotalAvailableSitesNumInSiteList(){
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".href",
	  	    				new RegularExpression(".*sitefilter.*", false));
		String text="";
		int total=0;
		for(int i=0;i<objs.length;i++) {
			text=objs[i].getProperty(".text").toString().trim();
			if(text.matches("ALL \\(\\d+\\)") || text.matches("All \\(\\d+\\)")) {
			  	continue;
			  	}
		  	text=text.split("\\(")[1].split("\\)")[0];
		  	total = total + Integer.parseInt(text);
		}
		
		Browser.unregister(objs);
		return total;
	}
	
	/**
	 * get the match and availability message displayed between Park name and Site Type Number info.
	 * @return a string like "16 site(s) found";
	 */
	public String getMatchAndAvailabilityMsg(){
		String matchMsg = "";
		IHtmlObject objs[] = browser.getHtmlObject(".className","matchSummary");
		logger.info("get match and availibility message..");
		if (null != objs && objs.length >0){
			matchMsg = objs[0].getProperty(".text");
		} 
		logger.info("Get message:"+matchMsg);
		Browser.unregister(objs);
		return matchMsg.trim(); 
	}
	
	/**
	 * get all site type and number's info and return them as a data collection.
	 * @return data collection of site type and number info.
	 * 		   For example: CampSiteInfo.siteType = "ALL", CampSiteInfo.sitetypeTotalNum = 16;
	 */
	public List<SiteInfoData> parseSiteTypeAndNumInfo(){
		List<SiteInfoData> stInfos = new ArrayList<SiteInfoData>();
		List<String> siteTypeNumList = this.getSiteTypeNumText();
		
		logger.info("beging parse site type and site number info");
		for (int i =0; i<siteTypeNumList.size(); i++ ){
			SiteInfoData temp = new SiteInfoData();
			int index = siteTypeNumList.get(i).lastIndexOf(" ");
			int beginIndex = siteTypeNumList.get(i).lastIndexOf("(");
			int endIndex = siteTypeNumList.get(i).lastIndexOf(")");
			
			temp.siteType = siteTypeNumList.get(i).substring(0,index).trim();			
			temp.siteTypeTotalNum = Integer.parseInt(siteTypeNumList.get(i).substring(beginIndex+1, endIndex));
			
			boolean newSiteType = true;
			for(SiteInfoData sd : stInfos) {
				if(sd.siteType.equalsIgnoreCase(temp.siteType)) {
					newSiteType = false;
					break;
				}
			}
			if(newSiteType) {
				stInfos.add(temp);
			}
		}
		if (null != stInfos && stInfos.size()>0 ){
			return stInfos;
		} else {
			throw new ErrorOnDataException("The camp Site type and number info is empty");
		}
		
	}
	
	/**
	 * verify all site type number links without available search site displayed in the color of "#AAAAAA"
	 * There should be at least one site type available number = 0 when you call this method.
	 * @return
	 */
	public boolean verifyAllSiteTypeNumWithoutAvailDisplayInGivenColor(String colorCode){
		List<SiteInfoData> stInfos = this.parseSiteTypeAndNumInfo();
		int countNum = 0;
		boolean unavailableColor = true;
		
		logger.info("verify the site color display in unavailable color:#AAAAAA");
		for (int i = 1; i<stInfos.size(); i++ ){
			
			if (stInfos.get(i).siteTypeTotalNum == 0){
				if (!this.verifySiteTypeNumDispInExpectColor(stInfos.get(i).siteType, colorCode)){
					unavailableColor = false;
					break;
				}			
				countNum = countNum  +1;
			}
		}
		if (countNum == 0){
			throw new ErrorOnDataException("There should be at least one unavailable sitetype and number hyperlink on the page when you call this method.");
		}
		logger.info("Total " + countNum +"hyperlinks color confirmed in unavailable color");
		return  unavailableColor;
		
	}
	
	/**
	 * click the site type and number link based on given link's display text.
	 * @param text
	 */
	public void clickSiteTpNumLink(String text){
		browser.clickGuiObject(".class", "Html.A",".href",new RegularExpression(".*sitefilter=" + text + ".*", false));
		
	}
	
	/**
	 * verify whether the site type and number text is hyperlink or plain text
	 * @param 
	 * @return true: hyperlink; false: plain text;
	 */
	public boolean verifySiteTypeNumIsLink(String text){
		IHtmlObject topObject[] = browser.getHtmlObject(".class", "Html.Div", ".text", new RegularExpression("^All \\(\\d+\\) .*",false));
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".href",new RegularExpression(".*sitefilter=" + text + ".*", false), topObject[0])){
			return true;
		}else{
			return false;
		}		
	}
	
	/**
	 * get the Site type and number link's color property based on given link's display text value.
	 * @param text
	 * @return color code like "#AAAAAA"
	 */
	public String getSiteTypeNumLinkColor(String text){
		String color = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href",new RegularExpression(".*sitefilter=" + text + ".*", false));
	  	
	  	if (null == objs){
	  		throw new ErrorOnDataException("can't find site type and number hyperlink which display text is:" + text);	
	  	}
	  	color = objs[0].getProperty(".color");
	  	return color;
	}
	
	/**
	 * 
	 * this function will click the "Clear search and show all" hyperlink which beneath the park name, and above the site types info.
	 */
	public void clickClearSearchAndShowAll(){
		browser.clickGuiObject(".class", "Html.A",".text","(Clear search and show all)",true);
	}
	
	/**
	 * verify the page title  match with the given format.
	 * @param facilityName
	 * @param stateCode
	 * @param contractCode
	 */
	public void verifyContractSpecificBanner(String facilityName, String stateCode, String contractCode){
		String expectTitle= ("Facility Details - " + facilityName + ", " + stateCode + " - " + "Recreation.gov.*" );
		String currentTitle = browser.title();
		
		if(currentTitle.matches(expectTitle)){
			logger.info("verify page title successfully.");
		}else{
			logger.error("The expect  page title is:" + "Facility Details - " + facilityName + ", " + stateCode + " - " + "Recreation.gov.*");
			logger.error("The current page title is:" + currentTitle);
			throw new ErrorOnPageException("verify the page title match with the given format failed.");
		}
	}
	
	/**
	 * Click the "Find Other Facilities" hyperlink on left top conner of the page - added when working on Rec.gov.
	 */
	public void clickFindOtherFacilities(){
		Property[] p1=Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression("campgLinks|leftNavPanelLinks",false));
		Property[] p2=Property.toPropertyArray(".class", "Html.DIV", ".className", "facilityNavigation");
		Property[] p3=Property.toPropertyArray(".class", "Html.A", ".text", "Find Other Facilities");
		IHtmlObject[] tops=browser.getHtmlObject(p1);
		if(tops.length<1){
			tops=browser.getHtmlObject(p2);
			if(tops.length<1){
				throw new ObjectNotFoundException("Can't find top DIv of \"Find Other Facilities\"");
			}
		}
		browser.clickGuiObject(p3, false, 0,tops[0]);
		Browser.unregister(tops);
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
	
	/**
	 * verify the "Find other Facilities' hyperlink on left top conner of the page exist.
	 */
	public void verifyFindOtherFacilitiesExist(){
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Find Other Facilities");
		if(!flag){
			throw new ErrorOnPageException("verify \"Find Other Facilities\" hyperlink exist failed.");
		}
	}
	
	public void verifyFindOtherCampgroundsLinkExist(){
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Find other campgrounds");
		if(!flag){
			throw new ErrorOnPageException("verify \"Find other campgrounds\" hyperlink exist failed.");
		}
	}
	
	/**
	 * verify the "GoogleMap' section on left top conner of the page exist.
	 */
	public void verifyGoogleMapExist(){
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.IMG", ".className", "facilityGoogleMap");
		if(!flag){
			throw new ErrorOnPageException("verify \"facilityGoogleMap\" section exist failed.");
		}
	}
	
	/**
	 * verify the "View Regional Map' link on left top conner of the page exist.
	 */
	public void verifyMapLinkExist(){
		boolean flag = browser.checkHtmlObjectExists(this.mapLink());
		if(!flag){
			throw new ErrorOnPageException("verify \"facilicy View Regional Map\" link exist failed.");
		}
	}
	
	/**
	 * verify the search form section on left side of the page exist.
	 */
	public void verifySearchFormExist(){
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.FORM", ".id", "unifSearchForm");
		if(!flag){
			throw new ErrorOnPageException("verify search widget section on left side of the page exist failed.");
		}
	}
	
	/**
	 * verify the advertisement section exist on the left side of the page
	 */
	public void verifyAdvertisementExist(){
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "ad");
		if(!flag){
			throw new ErrorOnPageException("verify advertisement section on the left side of the page failed.");
		}
	}
	
	/** Get the message for availability notice */
	public String getAvailabilityNoticeMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "alternativeSuggestion");
	}
	
	/** Check if the message for availability notice exists */
	public boolean isAvailabilityNoticeMsgExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".classname", "alternativeSuggestion");
	}
	
	/** Verify if the message for availability notice exists */
	public void verifyAvailabilityNoticeMsgExist(boolean isExist) {
		String msg = isExist ? " " : " not ";
		if (isExist != this.isAvailabilityNoticeMsgExist()) {
			throw new ErrorOnPageException("The availability notice message should" + msg + "exist.");
		}
		logger.info("The availability notice message does" + msg + "exist.");
	}
	
	/** Click the link Create Availability Notification in Availability Notice messages */
	public void clickCreateAvailNotifLink() {
		browser.clickGuiObject(".id", "availNotifLink");
	}
	
	/** Check if the Create Available Notification exist */
	public boolean isCreateAvailNotifLinkExist() {
		return browser.checkHtmlObjectExists(".id", "availNotifLink");
	}
	
	/** Click Find Next Available Date in Availability Notice messages */
	public void clickFindNextAvailDateLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".classname", "alternativeSuggestion"), 
				Property.toPropertyArray(".class", "Html.A", ".text", "Find Next Available Date")), true, 0);
	}
	
	/** Click Show all link in Availability Notice messages */
	public void clickShowAllLink() {
		browser.clickGuiObject(".text", "Show all");
	}
	
	/** Click the Number of sites link in the message: We found x Site(s) by searching entire campground */
	public void clickNumOfSitesToSearchEntireLink() {
		browser.clickGuiObject(".href", new RegularExpression(".*mode=decision&relaxSCriteria=1.*", false));
	}
	
	/** Get the Number of sites in the message: We found x Site(s) by searching entire campground */
	public String getNumOfSitesBySearchEntire() {
		String text = browser.getObjectText(".href", new RegularExpression(".*mode=decision&relaxSCriteria=1.*", false));
		return RegularExpression.getMatches(text, "\\d+")[0].trim();
	}
	
	/** Click Number of sites link in the message: We found x Site(s) by also removing your 'Spot with...' preferences */
	public void clickNumOfSitesToSearchWithoutSpotWithLink() {
		browser.clickGuiObject(".href", new RegularExpression(".*mode=decision&relaxSCriteria=2.*", false));
	}
	
	/** Get the Number of sites in the message: We found x Site(s) by also removing your 'Spot with...' preferences */
	public String getNumOfSitesBySearchWithoutSpotWithLink() {
		String text = browser.getObjectText(".href", new RegularExpression(".*mode=decision&relaxSCriteria=2.*", false));
		return RegularExpression.getMatches(text, "\\d+")[0].trim();
	}
	
	/** Verify number of sites in the matched availability message like: 90 site(s) available out of 94 site(s) */
	public void verifyMatchedNumOfSites(String expNum) {
		String matchedMsg = this.getMatchAndAvailabilityMsg();
		if (!matchedMsg.startsWith(expNum)) {
			throw new ErrorOnPageException("The matched num of sites are not correct.", expNum, matchedMsg);
		}
		logger.info("The matched num of sites is correct as " + expNum);
	}
	
	/** Click Parks Nearby link in Availability Notice messages*/
	public void clickParksNearbyLink() {
		browser.clickGuiObject(".href", new RegularExpression(".*(/parksNearbySearch|interface=parksnearby).*", false));
	}
}
