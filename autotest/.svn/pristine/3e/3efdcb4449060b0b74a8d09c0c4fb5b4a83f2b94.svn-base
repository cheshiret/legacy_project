package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.web.RecFacilityInfo;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UwpParkListCommonPage extends RecgovCommonPage {
	private static UwpParkListCommonPage _instance = null;

	public static UwpParkListCommonPage getInstance() {
		if (null == _instance)
			_instance = new UwpParkListCommonPage();

		return _instance;
	}

	public UwpParkListCommonPage() {
	}

	public boolean exists() {
		boolean flag1 = browser.checkHtmlObjectExists(".className", "msg warning");
		boolean flag2 = browser.checkHtmlObjectExists(".id", new RegularExpression("shoppingitems|unifSearchResultsForm",false));
		return flag1 | flag2;
		//this page mark may has problem, because it is the same as the UwpCampsiteListPage page which is next to this page, the below
		// page mark can unique the page if error happens. May only has problem with WATIJ, will update here after we run regression test
		//		return browser.checkHtmlObjectExists(".className","pageresults",".text", new RegularExpression("^Campground Search Results.*",false));
	}

	/**
	 * Get test object by given name.
	 * @param name - object name you want to retrieve
	 * @return
	 */
	private IHtmlObject getObject(String name) {
		IHtmlObject[] foundTOs = null;
		IHtmlObject toReturn = null;
		if (name.equalsIgnoreCase("table_shoppingitems")) {
			foundTOs = browser.getTableTestObject(".id", "shoppingitems");
			toReturn = (IHtmlTable) foundTOs[0];
		} else if (name.equalsIgnoreCase("link_resultNext")) {
			foundTOs = browser.getHtmlObject(".class","Html.A",".id", "resultNext");
			toReturn = (ILink) foundTOs[0];
		} else if (name.equalsIgnoreCase("link_resultNext")) {
			foundTOs = browser.getHtmlObject(".class","Html.A",".id", "resultPrevious");
			toReturn = (ILink) foundTOs[0];
		} else if (name.equalsIgnoreCase("link_private")) {
			RegularExpression privateRE = new RegularExpression(
					"^Private \\([0-9]+\\)$", false);
			foundTOs = browser.getHtmlObject(".class","Html.A",".text", privateRE);
			if (foundTOs != null && foundTOs.length >= 1)
				toReturn = (ILink) foundTOs[0];

		} else if (name.equalsIgnoreCase("link_state")) {
			RegularExpression stateRE = new RegularExpression(
					"^State \\([0-9]+\\)$", false);
			foundTOs = browser.getHtmlObject(".class","Html.A",".text", stateRE);
			if (foundTOs != null && foundTOs.length >= 1)
				toReturn = (ILink) foundTOs[0];

		} else if (name.equalsIgnoreCase("link_federal")) {
			RegularExpression federalRE = new RegularExpression(
					"^Federal \\([0-9]+\\)$", false);
			foundTOs = browser.getHtmlObject(".class","Html.A",".text", federalRE);
			if (foundTOs != null && foundTOs.length >= 1)
				toReturn = (ILink) foundTOs[0];
		}

		return toReturn;
	}

	/**
	 * Click on contract type link.
	 * @param type - type of contract
	 * @return
	 */
	public boolean clickContractTypeLink(String type) {
		boolean found = false;
		if (type == null || type.length() < 1)
			return false;

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", new RegularExpression(type + " \\(\\d+\\)", false));
		if (objs.length > 0 && ((ILink) objs[0]).isEnabled()) {
			((ILink) objs[0]).click();
			found = true;
		}

		return found;
	}
	
	/**
	 * Get all contract type link
	 * Such as All(XX), federal(XX), state(XX), other(XX), private(XX)
	 * @return
	 */
	public List<String> getAllContractTypeLinks(){
		List<String> contractTypeLinks = new ArrayList<String>();
		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".id","colbody1")};
		Property[] p2 = new Property[]{new Property(".class","Html.A")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1,p2));
		if(null == objs || objs.length < 1){
			return contractTypeLinks;
		}
		for(int i =0; i < objs.length; i ++){
			contractTypeLinks.add(objs[i].text());
		}
		Browser.unregister(objs);
		return contractTypeLinks;
	}

	/**
	 * Click on Federal contract link.
	 * @return
	 */
	public boolean clickFederalLink() {
		return this.clickContractTypeLink("Federal");
	}

	/**
	 * Click on State contract link.
	 * @return
	 */
	public boolean clickStateLink() {
		return this.clickContractTypeLink("State");
	}

	/**
	 * Click on Private contract link.
	 * @return
	 */
	public boolean clickPrivateLink() {
		return this.clickContractTypeLink("Private");
	}

	/**
	 * Go to park's site list page by park name and contract type.
	 * @param parkName - park name
	 * @param contractType - contract type
	 * @return
	 */
	public String chooseSites(String parkName, String contractType) {
		String toReturn = null;
		do {
			this.clickContractTypeLink(contractType);
			this.waitLoading();

			Property[] p = null;
			if (parkName == null || parkName.length() < 1) {
				p = new Property[2];
			} else {
				parkName = parkName.replaceAll(" ", "_");
				p = new Property[3];
				p[2] = new Property(".href", new RegularExpression(parkName,
						false));
			}

			p[0] = new Property(".class", "Html.A");
			p[1] = new Property(".text", "See Details");

			IHtmlObject[] objs = browser.getHtmlObject(p);
			if (objs.length > 0) {
				if (parkName == null || parkName.length() < 1) {
					String href = objs[0].getProperty(".href").toString();
					int start = href.indexOf("/camping/")
					+ "/camping/".length();
					int end = href.indexOf("/r/");
					toReturn = href.substring(start, end).replaceAll("_", " ");
				} else
					toReturn = parkName;

				((ILink)objs[0]).click();
			}

		} while (nextResult());

		return toReturn;
	}

	/**
	 * Go to next result page.
	 * @return
	 */
	public boolean nextResult() {
		ILink resultNext = (ILink)getObject("link_resultNext");
		boolean toReturn = false;
		if (resultNext.exists()
				&& !(resultNext.getProperty(".className"))
				.equals("disabled")) {
			resultNext.click();
			this.waitLoading();
			toReturn = true;
		}
		return toReturn;
	}

	/**
	 * Go to previous result page.
	 * @return
	 */
	public boolean previousResult() {
		ILink resultPrevious = (ILink)getObject("link_resultPrevious");
		boolean toReturn = false;
		if (!(resultPrevious.getProperty(".className")).equals("disabled")) {
			resultPrevious.click();
			toReturn = true;
		}
		return toReturn;
	}

	/**
	 * Get the error message when the search date is less than min window.
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".id", "msg1");

		String msg = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return msg;
	}

	/**
	 * Retrieve the number of available parks
	 * @return - number of available parks
	 */
	public int getAvailableParks(){
		IHtmlObject[] objs = browser.getHtmlObject(".className",new RegularExpression(
				"[B|b]ook [N|n]ow", false));
		int num = objs.length;

		Browser.unregister(objs);
		return num;
	}

	/**
	 * Retrieve all park name object through the page.
	 * @return - TestObject
	 */
	public IHtmlObject[] getParkNameLinkObject() {
		RegularExpression reg = new RegularExpression(".*campgroundDetails\\.do.*", false);

		return browser.getHtmlObject(".class","Html.A", ".href", reg);
	}

	/**
	 * Retrieve all park name and its status to a vector of the entire page.
	 * @return - name of parks, status of park, true - available; false - not
	 */
	public List<String> getAllParkName() {
		List<String> parks = new ArrayList<String>();
		IHtmlObject[] table = null;

		do {
			table = browser.getTableTestObject(".id","shoppingitems");
			IHtmlTable tableGrid = (IHtmlTable) table[0];

			// get park name object here
			RegularExpression reg = new RegularExpression(".*campgroundDetails.do?.*", false);
			IHtmlObject[] parkName = browser.getHtmlObject(".class", "Html.A",".href", reg);

			String status="";
			for(int i=3; i<tableGrid.rowCount(); i++) {
				status = tableGrid.getCellValue(i,0);
				if(status.equalsIgnoreCase("availableSee Details") ||
						status.equalsIgnoreCase("by phone onlySee Details")) {
					parks.add("true");// true - available; false - not
				}else {
					parks.add("false");
				}
				// park name object starts from 0
				parks.add(parkName[i-3].getProperty(".text").toString());
			}
			Browser.unregister(parkName);
		}while(nextResult());

		Browser.unregister(table);
		return parks;
	}

	/**
	 * Get all available park/site with Book Now on them objects.
	 * @return - Test Object[]
	 */
	public IHtmlObject[] getAllAvailableObjects() {
		return browser.getHtmlObject(".className",new RegularExpression(
				"[B|b]ook [N|n]ow", false));
	}

	/**
	 * Get the first park name in park search results page.
	 * @return - park name
	 */
	public String getFirstParkName() {
		RegularExpression reg = new RegularExpression(
				".*campgroundDetails.do?.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".href", reg);

		String parkName = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return parkName;
	}

	/**
	 * Go to site list page by given park name.
	 * @param name - park name
	 */

	public void gotoSiteListByParkName(String name, int index) {
		name = name.replaceAll("( |')","_");
		RegularExpression reg = new RegularExpression(".*"+name+".*campsite(Search|Calendar)\\.do.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".href",reg);

		if(objs.length > 0) {
			((ILink)objs[index]).click();
		}else {
			throw new ItemNotFoundException("Did not find the given park "+name);
		}
		Browser.unregister(objs);
	}

	public void gotoSiteListByParkName(String name) {
		this.gotoSiteListByParkName(name, 0);
	}

	/**
	 * Verify whether or not the park is available for given date.
	 * @return true - available; false - not available
	 */
	public boolean isDateAvailable() {
		IHtmlObject[] objs = browser.getHtmlObject(".className","book next",
				".text", new RegularExpression("Find Next ?Avail.*", false)); //"Find Next Avail. Date*");

		Browser.unregister(objs);
		return objs.length<1;
	}

	/**
	 * Retrieve the park region name by given park name. Till now just found RecGov will show
	 * the region name, UWP will not has region name below the park name.
	 * @param parkName - park name
	 * @return - region name
	 */
	public String getParkRegionName(String parkName) {
		IHtmlObject[] table = browser.getTableTestObject(".id","shoppingitems");
		IHtmlTable tableGrid = (IHtmlTable) table[0];

		String region="";
		int counter=0;
		for(int i=0; i<tableGrid.rowCount(); i++){
			if(tableGrid.getCellValue(i,1)!=null){
				String cellValue=tableGrid.getCellValue(i,1);
				if(cellValue.indexOf(parkName)!=-1){
					region=cellValue.replaceAll(parkName,"").replaceAll("\\(.*\\)","").trim();
					counter++;
					break;
				}
			}
		}
		if(counter==0)
			throw new ItemNotFoundException("Given park not found!");

		Browser.unregister(table);
		return region;
	}

	/**
	 * Verify the Search Nearby link displays for unavailable park, except KOAs.
	 */
	public void verifySearchNearbyLinks(){
		int counter=0;
		String cellValue="";
		String parkName="";
		do{
			IHtmlObject[] table = browser.getTableTestObject(".id","shoppingitems");
			IHtmlTable tableGrid = (IHtmlTable)table[0];

			for(int i=3; i<tableGrid.rowCount(); i++) {
				cellValue=tableGrid.getCellValue(i,0);
				if(cellValue.indexOf("Search Nearby")!=-1) {
					counter++;
					if(cellValue.indexOf("Find Next")==-1) {
						throw new ItemNotFoundException("Site is not Unavailable," +
						" should not have Search Nearby link.");
					}
					parkName=tableGrid.getCellValue(i,1);
					if(parkName.indexOf("KOA")!=-1){// verify KOA, should not have search nearby
						throw new ItemNotFoundException("KOA park should not have Search Nearby link.");
					}
				}
			}
			Browser.unregister(table);
		}while(nextResult());

		if(counter==0) {
			throw new ItemNotFoundException("All park is available for searching date, so No" +
			" 'Search Nearby' link found.");
		}
	}

	/**
	 * search and click the first Search Nearby link and return the park name.
	 * @return - selected park name
	 */
	public String clickFirstSearchNearbyLink(){
		int counter=0;
		String cellValue="";
		String parkName="";
		boolean controller;// control whether or not go to next result page
		do{
			IHtmlObject[] table = browser.getTableTestObject(".id","shoppingitems");
			IHtmlTable tableGrid = (IHtmlTable)table[0];

			for(int i=3; i<tableGrid.rowCount(); i++) {
				cellValue=tableGrid.getCellValue(i,0);
				if(cellValue.indexOf("Search Nearby")!=-1) {
					counter++;
					parkName=tableGrid.getCellValue(i,1);
					break;//break the loop when found first link
				}
			}
			Browser.unregister(table);

			controller=false;// reset the controller
			if(counter==0){// try to find link when did not find in first page
				IHtmlObject[] isNext=browser.getHtmlObject(".id", "resultNext");
				if(!isNext[0].getProperty(".className").equals("disabled")){
					controller=true;
					isNext[0].click();
					this.waitLoading();
				}
				Browser.unregister(isNext);
			}

		}while(controller);

		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text","Search Nearby");
		((ILink)objs[0]).click();
		this.waitLoading();
		Browser.unregister(objs);

		if(counter==0) {
			throw new ItemNotFoundException("All park is available for searching date, so No" +
			" 'Search Nearby' link found.");
		}
		return parkName;
	}

	/**
	 * Retrieve the table content text.
	 * @return - text
	 */
	public String getTableText(){
		IHtmlObject[] objs = browser.getHtmlObject(".id","shoppingitems");
		String text=objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return text;
	}

	/**
	 * Go through all park results page to find and click on first 
	 * 'Find Next Available' date link
	 */
	public void clickFirstFindNext(){
		int counter=0;
		boolean controller=false;// control whether or not go to next result page
		do{
			IHtmlObject[] objs = browser.getHtmlObject(".className","book next");
			if(objs.length>0) {
				counter++;
				controller=false;//reset the while loop controller
				objs[0].click();//add a date range page waitexists?
			}
			Browser.unregister(objs);

			if(counter==0){// try to find link when did not find in first page
				IHtmlObject[] isNext=browser.getHtmlObject(".id", "resultNext");
				if(!isNext[0].getProperty(".className").equals("disabled")){
					controller=true;
					isNext[0].click();//go to next result page
					this.waitLoading();
				}
				Browser.unregister(isNext);
			}

		}while(controller);

		if(counter==0) {
			throw new ItemNotFoundException("All park is available for date searched.");
		}
	}

	/**
	 * Retrieve the park photo name by its SRC property.
	 * @return - image name
	 */
	public String getParkImageName(){
		RegularExpression reg=new RegularExpression(".*photos\\/details\\/.*",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.IMG",".src",reg);

		String imgName=objs[0].getProperty(".src").toString();
		int startIndex=imgName.lastIndexOf("/")+1;

		imgName=imgName.substring(startIndex,imgName.length()).split("\\.")[0];
		Browser.unregister(objs);
		return imgName;
	}

	/**
	 * Click on first park's see details button or link, available or not available
	 * @return - selected park name
	 */
	public String bookFirstPark(){
		IHtmlObject[] objs=browser.getHtmlObject(".className",new RegularExpression(
				"book (now|next|elsewhere)",false));
		if(objs.length<1) {
			throw new ItemNotFoundException("No park found!");
		}

		String href=objs[0].getProperty(".href").toString();
		String parkName=href.split("/", 6)[4].replaceAll("_", " ");
		objs[0].click();

		Browser.unregister(objs);
		return parkName;
	}

	/**
	 * Retrieve the park status by given park name
	 * @param parkName - park name
	 * @return park status
	 */
	public String getParkStatus(String parkName){
		IHtmlObject[] table = browser.getTableTestObject(".id","shoppingitems");
		IHtmlTable tableGrid = (IHtmlTable)table[0];

		String status = "";
		int parkColumn = 0;
		for(int j=0; j<tableGrid.columnCount(); j++) {
			if(tableGrid.getCellValue(1, j).equalsIgnoreCase("Campground")||
					tableGrid.getCellValue(1, j).equalsIgnoreCase("Facility")) {
				parkColumn = j;
				break;
			}
		}
		if(parkColumn ==0) {
			throw new ErrorOnPageException("Park name column not found, pls check column name.");
		}

		for(int i=3; i<tableGrid.rowCount(); i++) {
			if (null != parkName &&tableGrid.getCellValue(i, parkColumn).toLowerCase().startsWith(parkName.toLowerCase()) ==true){
				status = tableGrid.getCellValue(i, 0).split("(Find Next Avail|See Details|Select)")[0].trim();
				break;
			}
		}

		if(status.length() == 0) {
			throw new ErrorOnPageException("Page displays with error, no status found.");
		}

		Browser.unregister(table);
		return status;
	}

	/**
	 * Go to campground details page by given park name.
	 * @param name - park name
	 */

	public void gotoParkDetailsByParkName(String name) {
		//for unified search
		clickParkName(name,null);
	}
	
	
	/**
	 * Go to campground details page by given park name.
	 * @param name - park name
	 */

	public void clickParkName(String name,String contractCode) {
		//for unified search
		RegularExpression reg1 = new RegularExpression("^" + name + ".*",false);
		IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.A",".text",reg1);

		String name1 = name.replaceAll("( |'|-)","_");
		
		String contract="";
		if(contractCode!=null&&contractCode.trim().length()>0){
			contract="contractCode="+contractCode;
		}
		RegularExpression reg = new RegularExpression(".*"+name1+".*campgroundDetails\\.do\\?"+contract+".*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".href",reg);

		if(objs.length > 0) {
			((ILink)objs[0]).click();
		}else if(objs1.length>0){
			((ILink)objs1[0]).click();
		}else{
			throw new ItemNotFoundException("Did not find the given park "+name);
		}

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}

	/**
	 * Go to campground list page by click the given park's enter date button
	 * @param park
	 */
	public void gotoParkListByEnterDateBtn(String name) {
		name = name.replaceAll("( |'|-)","_");
		RegularExpression reg = new RegularExpression(".*"+name+".*campsiteSearch\\.do.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".href",reg);

		if(objs.length > 0) {
			((ILink)objs[0]).click();
		}else {
			throw new ItemNotFoundException("Did not find the given park "+name);
		}
		Browser.unregister(objs);

	}



	/**
	 * Get facility info: Facility Name + State + Parent Facility/Region
	 * Format: 'Facility Name', 'State' part of 'Parent Facility/Region'
	 * @return
	 */
	public String[] getFacilityHeader(){
		String[] facilityHeaderInfos = null;
		IHtmlObject[] facilityViewHeader = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_view_header");
		if(facilityViewHeader.length>0){
			facilityHeaderInfos = new String[facilityViewHeader.length];
			for(int i=0; i<facilityHeaderInfos.length; i++){
				facilityHeaderInfos[i] = facilityViewHeader[i].getProperty(".text");
			}
		}else throw new ObjectNotFoundException("Facility view card object can't find.");

		Browser.unregister(facilityViewHeader);
		return facilityHeaderInfos;
	}

	public String parseShortOfState(String shotOfState){
		String returnString = "";
		if(shotOfState.equalsIgnoreCase("OR")){
			returnString = "Oregon";
		}else if(shotOfState.equalsIgnoreCase("CA")){
			returnString = "California";
		}else if(shotOfState.equalsIgnoreCase("WY")){
			returnString = "Wyoming";
		}else if(shotOfState.equalsIgnoreCase("VA")){
			returnString = "Virginia";
		}else if(shotOfState.equalsIgnoreCase("ID")){
			returnString = "Idaho";
		}else if(shotOfState.equalsIgnoreCase("WA")){
			returnString = "Washington";
		}else if(shotOfState.equalsIgnoreCase("KY")){
			returnString = "Kentucky";
		}else if(shotOfState.equalsIgnoreCase("AR")){
			returnString = "Arkansas";
		}else throw new ErrorOnDataException("No matched regin found!");

		return returnString;
	}

	/**
	 * Verify specific facility info associated with Facility Name, Parent Facility or Region and State
	 * @param facilityName
	 * @param parentFacilityOrRegion: Sometimes it is all the capital, but sometime first character of each word is capital  
	 * @param state
	 */
	public void verifySpecificFacilityHeaderInfo(String facilityName, String parentFacilityOrRegion, String state){
		String facilityInfo = facilityName+", "+this.parseShortOfState(state)+" part of"+ parentFacilityOrRegion;
		if(!this.getFacilityHeader()[0].toUpperCase().contains(facilityInfo.toUpperCase())){
			throw new ErrorOnDataException("Facility Name and associated info is incorrect.");
		}
	}

	public List<RecFacilityInfo> getSearchResultsFacilityInfo(){
		List<RecFacilityInfo> facilityinfo = new ArrayList<RecFacilityInfo>();

		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^# Facility ID.*", false));
		IHtmlTable facilityTableGrid = (IHtmlTable)objs[0];

		logger.info("begin retriving search result facilities info.");		

		int rowSize=facilityTableGrid.rowCount();
		for (int row = 1; row < rowSize; row++) {
			RecFacilityInfo facilityinforow = new RecFacilityInfo();
			facilityinforow.facilityID = facilityTableGrid.getCellValue(row, 1);
			facilityinforow.contractCode =  facilityTableGrid.getCellValue(row, 2);
			facilityinforow.parkName =  facilityTableGrid.getCellValue(row, 3);
			facilityinforow.facilityType = facilityTableGrid.getCellValue(row, 4);
			facilityinforow.latitude = facilityTableGrid.getCellValue(row, 5);
			facilityinforow.longitude = facilityTableGrid.getCellValue(row, 6);
			facilityinforow.distance = facilityTableGrid.getCellValue(row, 7);

			facilityinfo.add(facilityinforow);			
		}
		Browser.unregister(objs);
		return facilityinfo;		
	}

	public void gotoSearchResultListPage(String facilityName){
		if (browser.checkHtmlObjectExists(".text", new RegularExpression("Did you mean.*", false))){
			browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("^" + facilityName + ".*", false),true);
			this.waitLoading();
		}
	}

	/**
	 * Get waring message
	 * @return
	 */
	public String getWarningMes(){
		String errorMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg warning");

		if(objs.length>0){
			errorMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Message warning Object can't find.");

		Browser.unregister(objs);
		return errorMes;
	}

	public void clickFacility(String facilityName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^" + facilityName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)") + ".*", false),true);
	}
	
	public void clickFacility(String name,String contractCode){
		//for unified search
		RegularExpression reg1 = new RegularExpression("^" + name + ".*",false);
		IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.A",".text",reg1);

		String name1 = name.replaceAll("( |'|-)","_");
		
		String contract="";
		if(contractCode!=null&&contractCode.trim().length()>0){
			contract="contractCode="+contractCode;
		}
		RegularExpression reg = new RegularExpression(".*"+name1+".*campgroundDetails\\.do\\?"+contract+".*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".href",reg);

		if(objs.length > 0) {
			((ILink)objs[0]).click();
		}else if(objs1.length>0){
			((ILink)objs1[0]).click();
		}else{
			throw new ItemNotFoundException("Did not find the given park "+name);
		}

		Browser.unregister(objs);
		Browser.unregister(objs1);
	}

	public void gotoMapView(){
		browser.clickGuiObject(".class", "Html.DIV",".id", "map_view_switch",true);
	}

	/**
	 * @return
	 */
	public boolean checkOnlineAvailabilityExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Online Availability");
	}
	
	/**
	 * 
	 * @param searchTypeFilter
	 * All
	 * federal
	 * state
	 * other 
	 * private
	 * @return
	 */
	public int getNumOfSearchTypeFilter(String searchTypeFilter){
		RegularExpression regx = new RegularExpression("^"+searchTypeFilter+".*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".className", "searchTypeFilter", ".text", regx);
		
		String numOfSearchTypeFilter = objs[0].getProperty(".text");
		return Integer.valueOf(numOfSearchTypeFilter.split("\\(")[1].replaceAll("\\)", ""));
	}
	
	/** Get total search result count */
	public String getSearchResultsCount() {
		String searchResultsLabel = browser.getObjectText(".class", "Html.Span", ".classname", "pageresults");
		return StringUtil.getSubString(searchResultsLabel, "of");
	}
	
	/* Get facility info by park name */
	public FacilityData getFacilityInfo(String parkName) {
		FacilityData data = null;
		IHtmlObject[] amenitiesIconsDivs = null;
		
		IHtmlObject objs[] = browser.getTableTestObject(".id","shoppingitems");
		IHtmlTable facilityTableGrid = (IHtmlTable)objs[0];
		logger.info("Get search result facility info for " + parkName);		
		
		int onlineAvaColIndex = facilityTableGrid.findColumn(1, "Online Availability");
		int campgroundColIndex = facilityTableGrid.findColumn(1, "Campground");
		int stateColIndex = facilityTableGrid.findColumn(1, "State");
		int typeColIndex = facilityTableGrid.findColumn(1, "Type");
		
		int rowSize = facilityTableGrid.rowCount();
		int specificRow = facilityTableGrid.findRow(campgroundColIndex, parkName);
		if (specificRow > 1 && specificRow < rowSize) {
			data = new FacilityData();
			data.status = facilityTableGrid.getCellValue(specificRow, onlineAvaColIndex);
			data.facilityName = facilityTableGrid.getCellValue(specificRow, campgroundColIndex);
			data.stateCode = facilityTableGrid.getCellValue(specificRow, stateColIndex).replace("Map", "").trim();
			data.facilityType = facilityTableGrid.getCellValue(specificRow, typeColIndex);
			
			amenitiesIconsDivs = this.getAmenitiesIconsDivs();
			data.hasSitesWithElectircHookup = browser.checkHtmlObjectExists(".class", "Html.IMG", 
					".title", "Electric Hookup", amenitiesIconsDivs[specificRow - 3]);
			data.hasSitesWithFullHookup = browser.checkHtmlObjectExists(".class", "Html.IMG", 
					".title", "Full Hookup", amenitiesIconsDivs[specificRow - 3]);
			data.hasSitesWithPetsAllowed = browser.checkHtmlObjectExists(".class", "Html.IMG", 
					".title", "Pets Allowed", amenitiesIconsDivs[specificRow - 3]);
			data.hasSitesWithWaterFront = browser.checkHtmlObjectExists(".class", "Html.IMG", 
					".title", "Near Water", amenitiesIconsDivs[specificRow - 3]);
			
			data.facilityPhotoSrc = this.getFacilityPhotoSrc(specificRow - 3);
		}	else {
			logger.info("There is no Facility " + parkName + " listed in the page");
		}
		Browser.unregister(objs, amenitiesIconsDivs);
		return data;
	}
	
	/* Get Amenities Icons DIV elements */
	private IHtmlObject[] getAmenitiesIconsDivs() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".classname", "amenitiesicons");
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException("Can't find amenities icons divs on the page!");
		} 
		return objs;
	}
	
	/* Get Facility photo src by it's row index */
	private String getFacilityPhotoSrc(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG", ".title", new RegularExpression("Photo: .*|No Photo", false));
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException("Can't find facility images on the page!");
		} 
		String src = objs[index].getAttributeValue("src");
		Browser.unregister(objs);
		return src;
	}
	
	public boolean checkFacilityPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".id", "shoppingitems");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
}
