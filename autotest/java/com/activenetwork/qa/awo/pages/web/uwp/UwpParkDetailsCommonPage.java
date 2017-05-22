package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UwpParkDetailsCommonPage extends UwpCampingPage {
	RegularExpression sPicReg = new RegularExpression("^p[0-9]?.*@\\d+",false);
	RegularExpression popPicReg = new RegularExpression("^popcopy.*",false);
	RegularExpression bookNowID = new RegularExpression("btn_book_now(_[a-z]+)?_id|btnbookacampsite",false);
	String phoneNumberTitle = "Phone Number:";
	String mailingAddressTitle = "Mailing Address:";
	
	private static UwpParkDetailsCommonPage _instance = null;

	public static UwpParkDetailsCommonPage getInstance() {
		if (null == _instance)
			_instance = new UwpParkDetailsCommonPage();

		return _instance;
	}

	protected UwpParkDetailsCommonPage() {
	}

	public boolean exists() {
		//old id: btnbookacampsite
		//new id: btn_book_now_id
		return browser.checkHtmlObjectExists(".id", bookNowID,".text",new RegularExpression(" ?(Book|Apply) Now",false))
				|| browser.checkHtmlObjectExists(".id", "campDetail");//Quentin[20131217] ELS, Appalachian, PA doesn't have Book|Apply Now button in park details page
	}

	/**
	 * Click on link 'Booking Window'.
	 */
	public void clickBookingWindow() {
		browser.clickGuiObject(".text", "Booking Window");
	}
	
	public void clickFeesAndCancellation(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees and Cancellation");
	}
	
	/**
	 * check whether the overview hyperlink on the right top corner of the page is clickable or not.
	 * @return
	 */
	public boolean checkOverviewClickable(){
		boolean flag = browser.checkHtmlObjectExists( ".class","Html.A", ".text", "Overview");
		return flag;
	}
	
	public void waitFindOtherFacilitiesLinkExist(){
		browser.waitExists(Property.toPropertyArray(".class", "Html.A", ".text", "Find Other Facilities"));
	}
	
	/**
	 * verify the default sub page is the "Overview" 
	 */
	public void verifyDefaultSubPage(){
		boolean flag = this.checkOverviewClickable();
		
		if(flag){
			throw new ErrorOnPageException("the default sub page verify failed, the default sub page shoulld be Overview.");
		}
	}
	
	/**
	 * get the Overview description text.
	 * @return
	 */
	public String getOverviewDescription(){
		String description = "";
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".className", "content first");//Quentin[20140512]
//		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".id", "overviewcontent");
		
		if(null != obj && obj.length >0){
			description = obj[0].text();
		}
		return description;
	}
	
	/**
	 * verify the Overview description text displayed and start with "Overview".
	 */
	public void verifyOverviewDescriptionInfoExist(){
		String description = this.getOverviewDescription();
		if(!description.startsWith("Overview")){
			throw new ErrorOnPageException("verify Overview content failed on Facility details page");
		}
	}
	
	/**
	 * check whether Book Now button exist on the current page or not.
	 * @return
	 */
	public boolean checkBookNowButtonExist(){
		boolean flag = browser.checkHtmlObjectExists(".id", "detailpagenavbar");
		return flag;
	}
	
	/**
	 * verify Book Now button exist on the current page.
	 */
	public void verifyBookNowButtonExist(){
		boolean flag = this.checkBookNowButtonExist();
		
		if(!flag){
			throw new ErrorOnPageException("verify Book Now button exist on the Facility details page failed.");
		}
	}
	
	/**
	 * verify the Overview DIV exist on the right top corner of the page above "Book Now" button.
	 */
	public void verifyOverviewWidgetExist(){
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "detailpagenavbar");
		if(!flag){
			throw new ErrorOnPageException("verify Overview DIV exist on Facility Details page failed.");
		}
	}
	
	/**
	 * verify the amenities and activities info exist on the Overview sub page.
	 */
	public void verifyAmenitiesAndActivitiesInfoExist(){
		String amenities = this.getAmenitiesAndActivities();
		if (amenities.length() < 1){
			throw new ErrorOnPageException("verify amenties and actitives info exist failed on Facility details page");
		}
	}
	
	/**
	 * verify page display with note and alert info, the note start with "Know Before You Go" header.
	 */
	public void verifyNoteAndAlertInfoExist(){
		String note = this.getNoteAndAlertInfo();
		if (note.length() < 1 || !note.startsWith("Know Before You Go")){
			throw new ErrorOnPageException("verify amenties and actitives info exist failed on Facility details page");
		}
	}
	
	/**
	 * get the note and alter info on the page.
	 * @return
	 */
	public String getNoteAndAlertInfo(){
		String note = "";
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Know Before You Go.*", false));
		
		if(null != obj && obj.length >0){
			note = obj[0].text();
		}
		return note;
	}
	/**
	 * verify page display with Getting There info, the Info with "Getting There:" header.
	 */
	public void verifyGettingThereInfoExist(){
		String getthere = this.getGettingThereInfo();
		if (getthere.length() < 1 || !getthere.startsWith("Getting There:")){
			throw new ErrorOnPageException("verify getting there info exist failed on Facility details page");
		}
	}
	
	/**
	 * get the Getting There info on the page.
	 * @return
	 */
	public String getGettingThereInfo(){
		String getthere = "";
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Getting There.*", false));
		
		if(null != obj && obj.length >0){
			getthere = obj[0].text();
		}
		return getthere;
	}
	
	/**
	 * verify page display with phone park for more info, and start with "Phone Park for More Information:" header.
	 */
	public void verifyPhoneParkForMoreInfoExist(){
		String phone = this.getPhoneParkForMoreInfo();
		if (phone.length() < 1 || !phone.startsWith(phoneNumberTitle)){//Phone Park for More Information:
			throw new ErrorOnPageException("verify getting there info exist failed on Facility details page");
		}
	}
	
	public String getParkPhoneNumber(){
		String phoneInfo = this.getPhoneParkForMoreInfo();
		int index = phoneInfo.lastIndexOf("Information:");
		String phone = phoneInfo.substring(index + "Information:".length());
		return phone.trim();
	}
	/**
	 * verify park phone number in the format of (XXX) XXX-XXXX
	 * Information: (334)499-2404
	 */
	public void verifyPhoneNumberFormat(){
		String phone = this.getParkPhoneNumber();
		if(!phone.matches("\\(\\d{3,3}\\)\\d{3,3}-\\d{4,4}")){
			throw new ErrorOnPageException("verify park phone number format failed.Expected Format:"+"\\(\\d{3,3}\\)\\d{3,3}-\\d{4,4}"+" Actual:"+phone);
		}
	}
	/**
	 * get the phone park for more info on the page.
	 * @return
	 */
	public String getPhoneParkForMoreInfo(){
		String phone = "";
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^"+phoneNumberTitle+".*", false));//Phone Park for More Information:
		
		if(null != obj && obj.length >0){
			phone = obj[0].text();
		}
		return phone;
	}

	/**
	 * Click on link 'Season Dates'.
	 */
	public void clickSeasonDates() {
		browser.clickGuiObject(".class","Html.A",".href", new RegularExpression(".*campgroundSeasonDates.do.*", false));
	}
	
	/**
	 * get the season schedule info from campground details page, start from campground details initial page.
	 * @return
	 */
	public List<SeasonData> getSeasonSchedule(){
		List<SeasonData> seasonSch = new ArrayList<SeasonData>();
		this.clickSeasonDates();
		this.waitLoading();
		
		IHtmlObject objs[] = browser.getTableTestObject(".id", "seasonsView");
		if (null != objs && objs.length >0){
			IHtmlTable seasonTab = (IHtmlTable)objs[0];
			logger.info("getting Season Schedule info from Campground details page...");
			for (int i=1; i<seasonTab.rowCount();i++){
				SeasonData temp = new SeasonData();
				temp.m_SeasonType = seasonTab.getCellValue(i, 0);
				temp.m_StartDate = seasonTab.getCellValue(i, 1);
				temp.m_EndDate = seasonTab.getCellValue(i, 2);
				seasonSch.add(temp);
			}
			
		}else{
			throw new ErrorOnPageException("can't find Season Schedule table on Campground details page.");
		}
		
		Browser.unregister(objs);
		return seasonSch;
	}

	/**
	 * Retrieve the park photo name by its SRC property.
	 * @return - image name
	 */
	public String getParkImageName() {
	  	RegularExpression reg=new RegularExpression(".*photos\\/small\\/.*",false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.IMG", ".src", reg);

	  	String imgName=objs[0].getProperty(".src").toString();
	  	int startIndex=imgName.lastIndexOf("/")+1;
	  	
	  	imgName=imgName.substring(startIndex,imgName.length()).split("\\.")[0];
	  	Browser.unregister(objs);
	  	return imgName;
	}
	
	/**
	 * return all images title for the park.
	 * @return
	 */
	public List<String> getAllParkImageTitle(){
		Property[] p1 ={ new Property(".class", "Html.DIV"), new Property(".id", "samplpics")};
		Property[] p2 ={ new Property(".class", "Html.IMG"), new Property(".className", "PopBoxImageSmall")};
		
		List<String> photoTitle = new ArrayList<String>();
		
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		
		if(null == objs || objs.length < 1){
			throw new ErrorOnPageException("There is no small photo image on Facility Details page.");
		}
		for(int i = 0 ; i < objs.length; i ++){
			photoTitle.add(objs[i].getProperty("title"));
		}
		
		return photoTitle;
	}
	
	/**
	 * verify all park's photo title in the format of "Photo: [park name]"
	 * @param parkName
	 */
	public void verifyAllParkImageTitle(String parkName){
		List<String> photoTitle = this.getAllParkImageTitle();
		
		for(int i = 0 ; i < photoTitle.size(); i ++){
			if(!photoTitle.get(i).equalsIgnoreCase("Photo: " + parkName)){
				logger.error("The expect photo title  is:" + "Photo: " + parkName);
				logger.error("The current photo title is:" + photoTitle.get(i));
				throw new ErrorOnPageException("The park name photo's title verify failed.");
			}
		}
	}
	
	/**
	 * Verify whether the park's photo exists by given image name.
	 * @param imageName
	 * @return true - found; false - not found
	 */
	public boolean isImageExists(String imageName) {
	  	boolean toReturn=false;
	  	RegularExpression reg=new RegularExpression(".*photos\\/small\\/"+imageName+".*",false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.IMG", ".src", reg);
	  	
	  	if(objs.length==1) {
	  	  	toReturn=true;
	  	}
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	/**
	 * Retrieve the external site name for park which can reserved from external site.
	 * @return - site name
	 */
	public String getExternalSiteName() {
		RegularExpression reg=new RegularExpression(".*saveCampgroundInfo\\.do.*",false);
		IHtmlObject[] objs=browser.getHtmlObject(".href", reg);
	  	String site=objs[0].getProperty(".text").toString().
	  										replaceAll("Make Reservation at ","");
	  	Browser.unregister(objs);
	  	return site;
	}
	
	/**
	 * Click on the campground pictures by index.
	 * @param index - start from 0
	 * @param conCode - contract code
	 * @param parkID - facility id
	 * @return picture dimensions
	 */
	public String[] clickPictureByIndex(String conCode, String parkID, int index) {
		String dimensions[] = new String[2];
		String lowCaseCode = conCode.toLowerCase();
		String upperCaseCode = conCode.toUpperCase();
		
		RegularExpression reg = new RegularExpression(".*("+lowCaseCode+"|"+upperCaseCode+")(\\/|\\_)(pid)?"+parkID+".*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".src",reg);
		if(index>objs.length-1) {
			throw new ErrorOnDataException("Index out of objects max amount.");
		} else {
			dimensions[0] = objs[index].getProperty(".width");
			dimensions[1] = objs[index].getProperty(".height");
			objs[index].click();//click on the image
		}
		Browser.unregister(objs);
		return dimensions;
	}
	
	/**
	 * Click on the popped up big picture to shrink in, return the pic dimensions from src property
	 * @return dimensions of picture
	 */
	public String[]  clickOnPoppedPicture() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", popPicReg);
		String dimensions[] = new String[2];
		if(obj.length<1) {
			throw new ItemNotFoundException("No big photo popped up!");
		} else {
			dimensions[0] = obj[0].style("width").replace("px", "");
			dimensions[1] = obj[0].style("height").replace("px", "");
			obj[0].click();//click the popped up pic to shrink in
		}
		Browser.unregister(obj);
		
		return dimensions;
	}
	
	/**
	 * Retrieve the number of park photos by given contract code and facility id.
	 * @param conCode - contract code
	 * @param parkID - facility id
	 * @return number of image objects
	 */
	public int numOfParkPics(String conCode, String parkID) {
		String lowCaseCode = conCode.toLowerCase();
		String upperCaseCode = conCode.toUpperCase();
		
		RegularExpression reg = new RegularExpression(".*("+lowCaseCode+"|"+upperCaseCode+")(\\/|\\_)(pid)?"+parkID+".*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".src",reg);
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Park Pictures.");
		}
		int num = objs.length;
		Browser.unregister(objs);
		
		return num;
	}

	/**Verify whether the specified park picture popped up by given park id*/
	public boolean isPopPicExists() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", popPicReg);
		Browser.unregister(obj);
		
		return obj.length==1;
	}
	
	/**Click Book Now to go to site list page*/
	public void clickBookNow() {
		browser.clickGuiObject(".id",bookNowID);
	}
	
	/**
	 * Get the region name
	 * @return
	 */
	public String getParkRegionName() {
		IHtmlObject objs[] = browser.getHtmlObject(".className", "facility_parent_link");
		
		String regionName = "";
		if(objs.length > 0) {
			regionName = objs[0].getProperty(".text").trim();
		}
		
		Browser.unregister(objs);
		return regionName;
	}
	
	/**
	 * get amenities and activities info
	 */
	public String getAmenitiesAndActivities(){
		String amenities = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id", "contenttable");
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i=0; i<table.columnCount(); i++){
			amenities = amenities+" "+table.getCellValue(1, i);
		}
		
		Browser.unregister(objs);
		return amenities.trim();
	}

	public String getAmenitiesAndActivites(String distance) {
		String amenities = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id", "contenttable");
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(0, distance);
		if (rowIndex < 0) {
			throw new ErrorOnPageException("Can't find the row with the text:" + distance);
		}
		for(int i=0; i<table.columnCount(); i++){
			amenities = amenities+" "+table.getCellValue(rowIndex+1, i);
		}
		
		Browser.unregister(objs);
		return amenities.trim();
	}
	
	public void clickMakeResAtAgencyLink() {
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Make Reservation at .*", false));
	}

	
	public void verifyGetDirectionsLinkExist() {
		if(!this.isGetDirectionsLinkExist()){
			throw new ErrorOnPageException("there should be 'Get Diretions' link.");
		}
	}
	
	public boolean isGetDirectionsLinkExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Get Directions");
	}

	/**
	 * "Verify google Map title property "
	 * @param titleOrAltInfo
	 */
	public void verifyGoogleMapTitle(String title) {
		logger.info("Verify google Map title property ");
		   IHtmlObject[] objs=this.getMapImageObject();
		   String titleOnPage=objs[0].getProperty(".title");
		   Browser.unregister(objs);
		   if(!title.equals(titleOnPage)){
		    	throw new ErrorOnPageException("title for google map image is wrong.",title,titleOnPage);
		   }
	}

	/**
	 * "Verify google Map .alt  property."
	 * @param titleOrAltInfo
	 */
	public void verifyGoogleMapAlt(String altInfo) {
		logger.info("Verify google Map .alt  property.");
	    IHtmlObject[] objs=this.getMapImageObject();
	    if(!objs[0].getProperty(".alt").equals(altInfo)){
	    	throw new ErrorOnPageException("title for google map image should be '"+altInfo+"'");
	    }
	}
	
	/**
	 * "Verify "View Regional Map" link text "
	 * @param titleOrAltInfo
	 */
	public void verifyMapLinkText(String text) {
		logger.info("Verify View Regional Map link text...");
		String textOnPage = browser.getObjectText(this.mapLink());
		if(!text.equals(textOnPage)){
		    	throw new ErrorOnPageException("title for google map image is wrong.",text,textOnPage);
		   }
	}
	
	public IHtmlObject[] getMapImageObject(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.IMG", ".className", "facilityGoogleMap");
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Can't find map image object");
		}
		return objs;
	}

	/**
	 * 
	 */
	public void clickMapImage() {
		browser.clickGuiObject(".class", "Html.IMG", ".className", "facilityGoogleMap");
	}
	
	public void clickMapLink() {
		browser.clickGuiObject(this.mapLink());
	}
	/**
	 * Get GPS Info from UI. 
	 * @return latitude and longitude, including decimal and degree format. Such as: 
	 * 34.00444  -119.39861  34\ufffd0'16"N 119\ufffd23'55"W
	 * @author Lesley Wang
	 * @date Oct 31, 2012
	 */
	public String[] getGPSInfo() {
		String gettingThereInfo = this.getGettingThereInfo();
		String[] gpsInfo = StringUtil.getSubString(gettingThereInfo, "(Latitude, Longitude):").split(" ");
		String[] latitudeAndLongitude = new String[4];
		for (int i = 0; i < latitudeAndLongitude.length; i++) {
			latitudeAndLongitude[i] = gpsInfo[i].replace(",", "");
		}
		return latitudeAndLongitude;
	}
	
	
	
	
	
	public String getContactAndMailingAddressInfo() {
		String info = "";
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Contact Information.*", false));
		
		if(null != obj && obj.length >0){
			info = obj[0].text();
		}
		return info;
	}
	
	public String getMailingAddress() {
		String info = this.getContactAndMailingAddressInfo();
		if (info.contains(phoneNumberTitle)) {
			return StringUtil.getSubstring(info, mailingAddressTitle, phoneNumberTitle);
		} else {
			return StringUtil.getSubString(info, mailingAddressTitle);
		}
	}
	
	/**
	 * Get all images
	 * @return
	 * @author Lesley Wang
	 */
	public IHtmlObject[] getCampgroundImages() {
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "samplpics");
//		HtmlObject[] images = null;
//		if (objs == null || objs.length < 1) {
//			logger.warn("No Images are shown!");
//		} else {
//			images = browser.getHtmlObject(".class", "Html.IMG", objs[0]);
//		}
//		
//		Browser.unregister(objs);
//		return images;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "oficial_photos");
		IHtmlObject[] images = browser.getHtmlObject(".class", "Html.IMG", ".className", "PopBoxImageSmall", objs[0]);
		Browser.unregister(objs);
		return images;
	}

	/**
	 * Get all images's info
	 * @return
	 * @author Lesley Wang
	 */
	public List<String> getCampgroundImagesInfo() {
		IHtmlObject[] objs = this.getCampgroundImages();
		List<String> info = new ArrayList<String>(); 
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {			
				info.add(objs[i].getAttributeValue("width") + " " + 
						objs[i].getAttributeValue("height") + " " + 
						objs[i].getAttributeValue("src") + " " + 
						objs[i].getAttributeValue("class") + " " + 
						objs[i].getAttributeValue("pbsrc") + " " +
						objs[i].getAttributeValue("onclick"));
			}
		}
		
		Browser.unregister(objs);
		return info;
	}
	
	public boolean isFacilityLogoExist() {
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".id", new RegularExpression(".*Facility_Logologo", false));
	}
	
	/** Verify facility logo exist or not */
	public void verifyFacilityLogoExist(boolean exp) {
		String msg = exp ? " " : " NOT ";
		if (exp != this.isFacilityLogoExist()) {
			throw new ErrorOnPageException("The facility logo should" + msg + "exist!");
		}
		logger.info("Verify the facility logo does " + msg + "exist!");
	}
	
	private IHtmlObject[] getFacilityLogoImages() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression(".*Facility_Logologo", false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the facility logo image!");
		}
		return objs;
	}
	
	public String getFacilityLogoHeight() {
		IHtmlObject[] objs = this.getFacilityLogoImages();
		String height = objs[0].getAttributeValue("height");
		Browser.unregister(objs);
		return height;
	}
	
	public String getFacilityLogoWidth() {
		IHtmlObject[] objs = this.getFacilityLogoImages();
		String width = objs[0].getAttributeValue("width");
		Browser.unregister(objs);
		return width;
	}
	
	/** Verify facility logo height and width */
	public void verifyFacilityLogoSize(String expH, String expW) {
		boolean result = true;
		result &= MiscFunctions.compareString("Facility Logo Width", expW, this.getFacilityLogoWidth());
		result &= MiscFunctions.compareString("Facility Logo Height", expH, this.getFacilityLogoHeight());
		if (!result) {
			throw new ErrorOnPageException("The facility logo size is wrong. Check logger error!");
		}
		logger.info("The facility logo size is correct!");
	}
	
	public boolean checkFacilityPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".id", "samplpics");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
	
	public String getCampName(){
		return browser.getObjectText(".className", "facility_view_header");
	}
	
	public void verifyCampName(String expectedName){
		String actualName = getCampName();
		if(!MiscFunctions.startWithString("Camp name", actualName, expectedName)){
			 throw new ErrorOnPageException("Camp name is wrong. Please find details from previous log.");
		}
	}
	
	/**
	 * click on 'Make Reservation at' link to book site from external site
	 */
	public void gotoSiteInExternalWebsite() {
		RegularExpression reg = new RegularExpression(".*Make Reservation at.*", false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
}

