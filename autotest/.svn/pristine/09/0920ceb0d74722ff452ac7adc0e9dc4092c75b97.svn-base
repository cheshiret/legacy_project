package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UwpProductListCommonPage extends UwpCampingPage {

	private static UwpProductListCommonPage _instance = null;

	public static UwpProductListCommonPage getInstance() {
		if (null == _instance)
			_instance = new UwpProductListCommonPage();

		return _instance;
	}

	public UwpProductListCommonPage() {
	}

	protected Property[] campsiteListTable() {
		return Property.concatPropertyArray(table(), ".id", "shoppingitems");
	}
	
	protected Property[] siteNumLink(String siteID) {
		return Property.concatPropertyArray(a(), ".href", new RegularExpression("/camping/.*/r/campsiteDetails\\.do.*siteId=" + siteID + ".*", false));
	}
	
	protected Property[] pertsAllowedImg() {
		return Property.concatPropertyArray(img(), ".title", "Pets Allowed");
	}
	
	protected Property[] amenitiesIconsDIV() {
		return Property.concatPropertyArray(div(), ".classname", "amenitiesicons");
	}
	
	protected Property[] pageresults() {
		return Property.concatPropertyArray(span(), ".classname", "pageresults");
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "csiterst");
//		return browser.checkHtmlObjectExists(".id", "campList")&& browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "shoppingitems");
		boolean flag1 = browser.checkHtmlObjectExists(".class","Html.A",".id", "campList");
		boolean flag2 = browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "shoppingitems");
		boolean flag3 = browser.checkHtmlObjectExists(".class","Html.DIV",".className", "msg topofpage");
		boolean flag4 = browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "csiterst"); // Lesley[20130917]: object when no matching sites search 
		return (flag1 && flag2) || (flag1 && flag3) || flag4;

	}
	
	
	/**
	 * get the message on the top of Site List sub page.
	 * such as "No Results found amtching your search".
	 * @return
	 */
	public String getMsgTopOfPage(){
		String msgTop = "";
//		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg topofpage");
		//Jane: Update for warning, for test case ParksNearBy_DayUsePicnicArea
		IHtmlObject[] objs = browser.getHtmlObject(".className", new RegularExpression("msg topofpage|warning", false));
		if(objs != null && objs.length >0){
			msgTop = objs[0].text();
			Browser.unregister(objs);
		}
		
		return msgTop;
	}
	/**
	 * @param expectTopMsg
	 */
	public void verifyMsgTopOfPage(String expectTopMsg){
		String currentTopMsg = this.getMsgTopOfPage();
		if (expectTopMsg.equalsIgnoreCase(currentTopMsg)){
			logger.info("Verification for top page messsage successfully");
		}else{
			throw new ErrorOnPageException("Verification for top page message failed on campsite list page..", expectTopMsg, currentTopMsg);
		}
		
	}
	
	/**
	 * After click 'Search Campsites' button, the site list page will refresh, this method is used to wait this
	 * page full refresh done
	 * @return
	 */
	public void waitPageRefreshDone() {
		Property property[] = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", "(Clear search and show all)");
		
		browser.waitExists(property);
	}
	
	/**
	 * click "Online availability" column header
	 */
	public void clickOnlineAvailabilityColumnHeader(){
		this.clickColumnHeader("Online availability");
	}
	
	/**
	 * click "Site #" column header
	 */
	public void clickSiteNumColumnHeader(){
		this.clickColumnHeader("Site #");
	}
	
	
	/**
	 * click "Facility Area" column header
	 */
	public void clickFacilityAreaColumnHeader(){
		this.clickColumnHeader("Facility Area");
	}
	
	/**
	 *click "Site Type" column header 
	 */
	public void clickSiteTypeColumnHeader(){
		this.clickColumnHeader("Site Type");
	}
	
	
	/**
	 * click the availability table's header name based on given column header info.
	 * such as "Online availability" , "Site #", "Facility Area", "Site Type"
	 */
	public void clickColumnHeader(String columnName){
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression(columnName, false),true);
	}
	
	/**
	 * verify the display order based on given items
	 * @param orderItems
	 * @param isdescedning
	 */
	public void verifySearchResultOrder(String[] orderItems, boolean isdescedning ){
		
//		Arrays.sort(orderItems);
//		for(String o:orderItems){
//			System.out.print(o.replaceAll("Map", "")+",");
//		}
		if(isdescedning){
			for(int i = 0 ; i < orderItems.length-1; i ++ ){
				if(orderItems[i].compareTo(orderItems[i+1]) < 0){
					logger.error("The #" + i + " item is:" + orderItems[i]);
					logger.error("The #" + (i+1) + " item is:" + orderItems[i+1]);
					throw new ErrorOnDataException("The items should display in descending order..");
				}
			}
		}else{
			for(int i = 0 ; i < orderItems.length-1; i ++ ){
				if(orderItems[i].compareTo(orderItems[i+1]) > 0){
					logger.error("The #" + i + " item is:" + orderItems[i]);
					logger.error("The #" + (i+1) + " item is:" + orderItems[i+1]);
					throw new ErrorOnDataException("The items should display in acsending order..");
				}
			}
		}
	}

	protected Property[] dateRangeAvailabilityTab() {
		return Property.concatPropertyArray(this.a(), ".id", "campCalendar");
	}
	
	protected Property[] dateRangeAvailabilityTabByTitle() {
		return Property.concatPropertyArray(this.a(), ".title", "Date Range Availability");
	}
	/** Click on Date Range Availability tab link.*/
	public void clickDateRangeAvailability() {
//	  	browser.clickGuiObject(".id", "campCalendar");
		//Lesley[20140402] update due to  PCR 4463 - Facility Page Re-design
		if (browser.checkHtmlObjectExists(this.dateRangeAvailabilityTab())) {
			browser.clickGuiObject(dateRangeAvailabilityTab());
		} else if (browser.checkHtmlObjectExists(this.dateRangeAvailabilityTabByTitle())) {
			browser.clickGuiObject(dateRangeAvailabilityTabByTitle());
		}
	}
	
	/**
	 * Select site type in site search panel.
	 * @param i - site type item index
	 */
	public void selectSiteType(int i) {
		IHtmlObject[] objs = browser.getDropdownList("id", "siteType");
		ISelect siteType = (ISelect)objs[0];
		try {
			siteType.select(i);
		} catch (Exception e) {
			throw new ItemNotFoundException("Invalid index - Select item error: "+ e.getMessage());
		} finally {
			Browser.unregister(objs);
		}
	}

	/**
	 * Select site type by type name in site search panel.
	 * @param type - site type
	 */
	public void selectSiteType(String type) {
		browser.selectDropdownList("id", "siteType", type);
	}

	/**
	 * Deselect site type.
	 */
	public void deselectSiteType() {
		browser.selectDropdownList("id", "siteType", 0);
	}

	/**
	 * Select camp area by area name in site search panel.
	 * @param area - area name
	 */
	public void selectCampArea(String area) {
		browser.selectDropdownList("id", "loop", area);
	}

	/**
	 * Deselect area name.
	 */
	public void deselectCampArea() {
		browser.selectDropdownList("id", "loop", 0);
	}

	/**
	 * Click on camp site search submit button.
	 * @throws PageNotFoundException
	 */
	public void campsiteSearch() throws PageNotFoundException {
		browser.clickGuiObject(".type", "submit",".id", new RegularExpression("search(_avail)?",false));
		waitLoading();
	}

	/**
	 * Fill in arrival date in site search panel.
	 * @param arrivalDate - arrival date
	 */
	public void setArrivaldate(String arrivalDate) {
		browser.setTextField(".id", "arvdate", arrivalDate);
	}
	
	/**
	 * Close arrival or end date calendar pop up window.
	 */
	public void clickCalendarClose() {
		browser.clickGuiObject(".id", "close");
	}

	/**
	 * Fill in length of stay in site search panel.
	 * @param duration - length of stay
	 */
	public void setLengthOfStay(String duration) {
		browser.setTextField(".id", "lengthOfStay", duration);
	}

	/**
	 * Fill in site number in site search panel.
	 * @param num - site number
	 */
	public void setSiteNumber(String num) {
		browser.setTextField(".id", "csite", num);
	}
	
	/**
	 * Go to site details page by clicking on site number link, if multiple objects found, will go to first one
	 */
	public void clickSiteNum(String siteNum) {
//		browser.clickGuiObject(".class","Html.A",".text", siteNum, true);
		//Quentin[20130905] framework will click the map link above on the corresponding site num link
		//Jane[2014-7-16]:Added process to click Next link to get the site number
		boolean found=false;
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", siteNum);
		if(objs.length<1) {
			while(this.verifyNextClickable()) {
				this.clickNext();
				this.waitLoading();
				objs = browser.getHtmlObject(".class", "Html.A", ".text", siteNum);
				if(objs.length>=1) {
					found = true;
					break;
				}
			}
		} else
			found =true;
		
		if(!found)
			throw new ItemNotFoundException("Cannot find site - " + siteNum);
		String href = objs[0].getProperty(".href");
		Browser.unregister(objs);
		
		//click 'Select' button to avoid directly click site num link, because sometimes it will click the map link above the site num link
		Property selectOrWalkupOnlyProperty[] = Property.toPropertyArray(".className", new RegularExpression("book (now|elsewhere)", false), ".href", href);
		Property findNextAvailProperty[] = Property.toPropertyArray(".className", "book next", ".href", href + "&findavail=next");
		
		if(browser.checkHtmlObjectExists(selectOrWalkupOnlyProperty)) {
			browser.clickGuiObject(selectOrWalkupOnlyProperty);
		} else if(browser.checkHtmlObjectExists(findNextAvailProperty)){
			browser.clickGuiObject(".class","Html.A",".text", siteNum, true);
		} else throw new ItemNotFoundException("Cannot find Select/Walk-up Only/Find Next Available** button/link.");
	}
	
	public void clickSiteNum(int siteID) {
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		IHtmlObject[] site = browser.getHtmlObject(Property.toPropertyArray(".class","Html.A",".className", "book now",".href",href));
		if(site.length<1)
			throw new ItemNotFoundException("Site with id="+ siteID +" not found.");
		site[0].click();
		Browser.unregister(site);
	}
	
	public void clickSiteNumByID(String siteID) {
		browser.clickGuiObject(this.siteNumLink(siteID));
	}
	
	public boolean checkSiteAvailable(String siteNum) {
		return checkSiteAvailable(getSiteID(siteNum));
	}
	
	public boolean checkSiteAvailable(int siteID) {
		RegularExpression href=new RegularExpression(".*siteId="+siteID+".*",false);
		boolean available= browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".text","See Details",".href",href));
		
		int count=0;
		while (!available && hasNextPage() && count<4) {
			clickNext();
			waitLoading();
			available = available && browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".text","See Details",".href",href));
			count++;
		}
		
		return available;
	}
	
	public boolean checkEnterDateForSite(String siteID){
		RegularExpression href=new RegularExpression(".*siteId="+siteID+".*",false);
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".text","Enter Date",".href",href));
	}
	
	public void clickNext() {
		RegularExpression pattern=new RegularExpression("campsitePaging\\.do",false);
		browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".id","resultNext",".href",pattern));
	}
	
	public boolean hasNextPage() {
		RegularExpression pattern=new RegularExpression("campsitePaging\\.do",false);
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".id","resultNext",".href",pattern));
	}
	
	/**
	 * Select first site to go to site details page by site id, even not available.
	 * @param siteID - site id
	 */
	public void selectSiteBySiteID(String siteID) {
		RegularExpression reg = new RegularExpression("book (now|next|elsewhere)", false);
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		if(StringUtil.isEmpty(siteID)) { //click the first one
			browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".className", reg),true);
		} else {
			
			browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".className", reg,".href",href),true);
		}
	}
	
	/**
	 * Go to site details page by clicking see details button for given site id
	 * @param siteID - site id
	 */
	public void clickSeeDetails(int siteID) {
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		RegularExpression text=new RegularExpression("See Details|Select",false);////changed to select from 3.04.01.6 build
		
		browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".href",href,".text",text),true);
	}
	
	public void clickEnterDate(int siteID) {
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".href",href,".text","Enter Date"),true);
	}
	
	/**
	 * Go to site details page by clicking see details button for given site number.
	 * @param siteNum - site number
	 */
	public void clickSeeDetails(String siteNum) {
		clickSeeDetails(Integer.parseInt(getSiteID(siteNum)));
	}
		
	/**Click on first See Details link.*/
	public void clickSeeDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text", "See Details");
	}
	
	/**
	 * Retrieve the site id by given site number.
	 * @param siteNum - site number
	 * @return siteID
	 */
	public String getSiteID(String siteNum) {
		RegularExpression reg=new RegularExpression("siteId=\\d+",false);
		String id="";
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".text",siteNum,".href",reg));
		if(objs.length<1) {
			throw new ItemNotFoundException("Site "+siteNum+" not found.");
		} else {
			String href=objs[0].getProperty(".href");
			String[] tokens=RegularExpression.getMatches(href, "siteId=\\d+");
			id= RegularExpression.getMatches(tokens[0], "\\d+")[0];
		}
		Browser.unregister(objs);
		return id;
	}
	
	/** Click on first available site to go to details page. */
	public String selectFirstAvailSite() {
		Property[] p1=Property.getPropertyArray(".class", "Html.TD", ".className", "sn");
		Property[] p2=Property.toPropertyArray(".class", "Html.DIV",".className","siteListLabel");
		Property[] p3=Property.toPropertyArray(".class", "Html.A");
		// handle ra web site
		IHtmlObject[] sites = browser.getHtmlObject(Property.atList(p1,p2,p3));
	
		if(sites.length<1) {
			// handle plw web site
			Property[] p4=Property.toPropertyArray(".class", "Html.A",".text","See Details");
			sites = browser.getHtmlObject(p4);
		}
		
		String siteID= "";
		if(sites.length<1) {
	  	  	throw new ItemNotFoundException("No available sites.");
	  	} else {
	  		String href = sites[0].getProperty(".href").toString();
			siteID = RegularExpression.getMatches(href, "siteId=\\d+")[0].substring("siteId=".length());
	  	}
	  	
	  	sites[0].click();
	  	Browser.unregister(sites);
	  	return siteID;
	}
	
	/**
	 * Fill in site search panel fields and book site.
	 * @param siteArea - site area
	 * @param siteNum - site number
	 * @param date - arrival date
	 * @param duration - length of stay
	 * @param target - used to control whether filling site search info in first time.
	 * @return - select site id
	 * @throws PageNotFoundException
	 */
	public String bookSite(String siteArea, String siteNum, String date,
			String duration, int target) throws PageNotFoundException {
		//int current = 0;
		//setup the date again
		if (target != 0) {
			this.setArrivaldate(date);
			this.clickCalendarClose();
			this.setLengthOfStay(duration);
		}

		//search for the specific site or sites in a specific area
		if ((siteArea != null && !siteArea.equals(""))
				|| (siteNum != null && siteNum != "")) {
			if (siteArea != null && siteArea != "") {
				this.selectCampArea(siteArea);
			}

			if (siteNum != null && !siteNum .equals("")) {
				this.setSiteNumber(siteNum);
			}
		}

		if (target != 0 || (siteArea != null && siteArea.length()>0)|| (siteNum != null && siteNum.length()>0)) {
			campsiteSearch();
			waitLoading();
		}

		return selectFirstAvailSite();
	}

	/**
	 * Click on Next Results page when it available.
	 * @return true - available; false - not available
	 */
	private boolean nextResult() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "resultNext");
		
		boolean toReturn = false;
		if (objs.length>0&& !(objs[0].getProperty(".className")).equals("disabled")) {
			objs[0].click();
			toReturn = true;
			this.waitLoading();
		}
		
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Verify whether or not any unavailable sites exists.
	 * @return true - found; false - not found
	 */
	public boolean isSiteNotAvailable() {
	  	RegularExpression reg = new RegularExpression("book (elsewhere|next)", false);
	  	IHtmlObject[] objs = browser.getHtmlObject(".className", reg);
	  	boolean toReturn = false;
	  	
	  	if(objs.length > 0)
	  	  	toReturn = true;
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	/**
	 * Click on Previous Results page when it available.
	 * @return true - available; false - not available
	 */
	public boolean previousResult() {
		IHtmlObject[] foundTOs = browser.getHtmlObject(".id", "resultPrevious");
		ILink link_resultPrevious = (ILink) foundTOs[0];
		boolean toReturn = false;

		if (!(link_resultPrevious.getProperty(".className")).equals("disabled")) {
			link_resultPrevious.click();
			toReturn = true;
		}
		Browser.unregister(foundTOs);
		return toReturn;
	}

	/**
	 * Retrieve the number of available sites
	 * @return - number of available sites
	 */
	public int numAvailSites() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "See Details");
		int size = objs.length;
		Browser.unregister(objs);
		return size;
	}

	/**
	 * Retrieve number of all sites from Online availability Table.
	 * @return number of sites, will be int type
	 */
	public int getTotalSiteNumberInAvailTable() {
		int flag = 0;
		int totalNum = 0;
		do{
			if (flag != 0){
				this.clickNextButton();
				this.waitLoading();
			}
			logger.info("count site number on page: " + flag+1);
			IHtmlObject[] foundTOs = browser.getTableTestObject(".id", "shoppingitems");
			IHtmlObject[] mapIconObjs = browser.getHtmlObject(".class", "Html.IMG", ".id", new RegularExpression("^i_\\d+",false), foundTOs[0]);
			totalNum = totalNum + mapIconObjs.length;	
			flag += 1;
			Browser.unregister(foundTOs, mapIconObjs);
		} while (this.verifyNextClickable());
		return totalNum;
	}

	/**
	 * Retrieve number of all sites.
	 * @return number of sites, will be String type
	 */
	public String numSitesS() {
		return getTotalSiteNumberInAvailTable() + "";
	}

	/**
	 * Select Range Date radio button.
	 */
	public void selectRangeRadioButton() {
		browser.selectRadioButton(".id", "rangeyes");
	}

	/**
	 * Fill in start date, if not provided, set today.
	 * @param date - start date
	 */
	public void setStartDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", "arvdate", date);
	}

	/**
	 * Fill in end date, if not provided, set today.
	 * @param date - end date
	 */
	public void setEndDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getToday();
		browser.setTextField(".id", "enddate", date);
	}

	/**
	 * Fil in start and end date for range date search.
	 * @param startDate - start date
	 * @param endDate - end date
	 */
	public void setRangeDate(String startDate, String endDate) {
		this.selectRangeRadioButton();

		if (startDate == null || startDate.length() < 1) {
			startDate = DateFunctions.getDateStamp(false);
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		} else if (endDate == null || endDate.length() < 1) {
			startDate = startDate.replaceAll("-", "/");
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		}

		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	/**
	 * Fill in arrival date for specific date search.
	 * @param arriveDate - arrival date
	 */
	public void setSpecificDate(String arriveDate) {
		browser.selectRadioButton(".id", "rangeno");

		this.setStartDate(arriveDate);
	}

	/**
	 * Check site exists by site number.
	 * @param siteNum - site number
	 * @return
	 */
	public boolean checkSiteExists(String siteNum) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", siteNum);
	}

	/**
	 * Click on Find Other Campgrounds to go to park search results page.
	 */
	public void gotoParkListPage() {
		browser.clickGuiObject(".class","Html.A",".text", "Find other campgrounds");
	}

//	/**
//	 * Click on item in cart.* link to go to shopping cart page.
//	 */
//	public void gotoShoppingCart() {
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart: \\d+", false));
//	}

	/** 
	 * Retrieve the Non-Site Specific available site's number.
	 * @return - number of sites
	 */
	public int getRemainSiteNum() {
		IHtmlObject[] foundTOs = browser
				.getHtmlObject(".class","Html.DIV",".className", "matchSummary");

		String text = foundTOs[0].getProperty(".text").toString();
		int num = Integer.parseInt(text.split(" site\\(s\\)")[0]);
//				.parseInt(text.split(" site(|s)\\)")[0].split("\\(")[1]);

		Browser.unregister(foundTOs);
		return num;
		
	}
	
	/**
	 * Retrieve the site name by given site id, usually this id retrieved by the other methods.
	 * @param siteID - site id
	 * @return site name
	 */
	public String getSiteNameByID(String siteID)	{
	  	RegularExpression reg = new RegularExpression(".*siteId=" + siteID + ".*", false);
	  	IHtmlObject[] sites = browser.getHtmlObject(".class", "Html.A", ".href", reg);

	  	if(sites.length < 1) {
	  	  	throw new ItemNotFoundException("No site found with given ID.");
	  	}
	  	
	  	String siteName = "";
	  	String toReturn = "";
	  	for(int i=0; i < sites.length; i++) {
	  	  	siteName = sites[i].getProperty(".text").toString();
	  	  	if(!siteName.equalsIgnoreCase("See Details")) {
	  	  	  	toReturn = siteName;
	  	  	}
	  	}
	  	
	  	Browser.unregister(sites);
	  	return toReturn;
	}

	/**
	 * Retrieve all available site name in site list page's Online availability table.
	 * @return - name of sites
	 */
	public List<String> getAllAvailableSiteName() {
	  	List<String> sites = new ArrayList<String>();
	  	IHtmlObject[] table = null;
	  	do {
	  	  	table = browser.getTableTestObject(".id", "shoppingitems");
	  	  	IHtmlTable tableGrid = (IHtmlTable) table[0];
	  	  	
	  	  	String siteName = "";
	  	  	String status = "";
	  	  	int statusColIndex = tableGrid.findColumn(1, "Online availability"); //Lesley[20130917]: table column order is changed in RA
	  	  	int siteNumIndex = tableGrid.findColumn(1, "Site#");
	  	  	for(int i=3; i<tableGrid.rowCount(); i++) {
	  	  	status = tableGrid.getCellValue(i,statusColIndex).toString();
	  	  	  	logger.info(status);
	  	  	  	if(status.indexOf("Book Now")!=-1 || status.indexOf("Enter Date")!=-1 || 
	  	  	  			(status.indexOf("Select")!=-1 && !status.contains("by phone only") && !status.contains("walk-up only"))) { //update by Lesley to handle walk-up only sites
	  	  	  	siteName = tableGrid.getCellValue(i,siteNumIndex).toString().replaceAll("Map","").trim();
	  	  	  	  	sites.add(siteName);
	  	  	  	}
	  	  	}
	  	  	
	  	  	if(this.isSiteNotAvailable()) {
	  	  	  	break; // if page has unavailable site, then will not click on next page link
	  	  	}
	  	}while(this.nextResult());
	  	
	  	Browser.unregister(table);
	  	return sites;
	}
	
	/**
	 * verify the each row for the availability column will display in the expect text, such as "Enter Date". 
	 * @param expectText
	 */
	public void verifyOnlineAvailabilityDisplayText(String expectText){
		List<String> onlineText = this.getAllSitesOnlineColumText();
		
		logger.info("start verify online availablity text with the given text value:" +expectText );
		if(onlineText.size() ==0){
			throw new ErrorOnDataException("There should be at least one site displayed on site list page to call this function.");
		}
		
		for(String status: onlineText){
			if(!status.equalsIgnoreCase(expectText)){
				logger.error("The expect Online availability text is:" + expectText);
				logger.error("The current Online availablity text is:" + status);
				throw new ErrorOnDataException("Online Availability verification failed.");
			}
		}
		
		logger.info("verify Online availablity text successfully.");
	}
	
	/**
	 * get the first column display text info, such as "Enter Date", "See details"...
	 * @return
	 */
	public List<String> getAllSitesOnlineColumText(){
		List<String> onlineText = new ArrayList<String>();
		
		IHtmlObject[] table = null;
	  	do {
	  	  	table = browser.getTableTestObject(".id", "shoppingitems");
	  	  	IHtmlTable tableGrid = (IHtmlTable) table[0];
	  	  	
	  	  	String status = "";
	  	  	for(int i=3; i<tableGrid.rowCount(); i++) {
	  	  	  	status = tableGrid.getCellValue(i,0).toString();
	  	  	  	onlineText.add(status);
	  	  	}
	  	  	
	  	}while(this.nextResult());
	  	return onlineText;
	}
	
	
	
	/**
	 * get all site info on the Site list page. ends at the last page of search result.
	 * Including "Online availability", "Site#", "FacilityArea", "SiteType", "Max# of people".
	 * @return
	 */
	public List<SiteInfoData> getAllSiteInfo(){
		List<SiteInfoData> sitesList = new ArrayList<SiteInfoData>(); 
		do{
			IHtmlObject[] sitesTable = browser.getTableTestObject(".id", "shoppingitems");
			IHtmlTable tableGrid = (IHtmlTable)sitesTable[0];

			for(int i=3; i<tableGrid.rowCount(); i++) {
				SiteInfoData site = new SiteInfoData();
				
				site.onlineAvailability = tableGrid.getCellValue(i, 6); //get Online availability
				site.siteNumber = tableGrid.getCellValue(i, 0); //get online site#
				site.area = tableGrid.getCellValue(i, 1); //get online site#
				site.siteType =  tableGrid.getCellValue(i, 2); 
				site.maxNumPeople = tableGrid.getCellValue(i, 3); 
				sitesList.add(site);
	  	  	}
		}while(this.gotoNext());
		
		return sitesList;
	}
	
	
	/**
	 * Retrieve all site type name in site list page's Online availability table.
	 * @return - name of sites
	 */
	public List<String> getAllSiteTypeNames() {
	  	List<String> siteTypes = new ArrayList<String>();
	  	IHtmlObject[] table = null;
	  	do {
	  	  	table = browser.getTableTestObject(".id", "shoppingitems");
	  	  	IHtmlTable tableGrid = (IHtmlTable) table[0];
	  	  	
	  	  	String siteType = "";
	  	  	for(int i=3; i<tableGrid.rowCount(); i++) {
	  	  		siteType = tableGrid.getCellValue(i,2).toString();//3
	  	  		if(siteType.length() >0){
	  	  			siteTypes.add(siteType);
	  	  		}
	  	  	}
	  	  	
	  	}while(this.nextResult());
	  	
	  	Browser.unregister(table);
	  	return siteTypes;
	}
	
	protected int getTotalAvailableSitesNum(IHtmlObject top){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".href",
  				new RegularExpression(".*sitefilter.*", false),top);
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
	
	public int getTotalAvailableSitesNumInMapHeader(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".className","searchSummary");
		int total = this.getTotalAvailableSitesNum(objs[0]);
		
		Browser.unregister(objs);
		return total;
	}
	
	public String getContentAreaTextInMapHeader(){
		return browser.getObjectText(".class","Html.DIV",".className","searchSummary");
	}
	
	/**
	 * Retrieve the total number of available sites from matching and available message by adding clickable link's number together except ALL().
	 * @return - total number
	 */
	public int getTotalAvailableSitesNumInSiteList(){
//		IHtmlObject[] objs = browser.getHtmlObject(".id","sitelistdiv");
		IHtmlObject[] objs = browser.getHtmlObject(Property.concatPropertyArray(div(), ".className","filters site")); //Sara[6/19/2014], top object of different site type filters
		int total = getTotalAvailableSitesNum(objs[0]);
		Browser.unregister(objs);
		return total;
	}
	
	public int getTotalAvailableSiteNumInDateRange(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".id","daterangediv");

		int total = getTotalAvailableSitesNum(objs[0]);
		Browser.unregister(objs);
		return total;
	}
	
	public String getContentAreaTextInDateRangeSecion(){
		return browser.getObjectText(".class","Html.DIV",".id","daterangediv");
	}
	
	/**
	 * Retrieve entire content text.
	 * @return - text
	 */
	public String getContentAreaText(){//all site list content
	  	return browser.getObjectText(".id", "sitelistdiv");
	}
	
	/**
	 * Retrieve all site types  from the section above the matrix table to a vector.
	 * @return - site type vector
	 */
	public List<String> getSiteTypes() {
	  	List<String> types=new ArrayList<String>();
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".href",
										new RegularExpression(".*sitefilter.*", false));
	  	String siteType="";
	  	int index=0;
	  	for(int i=0; i<objs.length; i++) {
	  	  	siteType=objs[i].getProperty(".text").toString();
	  	  	index=siteType.lastIndexOf(" ");
	  	  	siteType=siteType.substring(0,index).trim();
	  	  	if(!siteType.equalsIgnoreCase("All"))
	  	  	  	types.add(siteType);
	  	}
	  	
	  	Browser.unregister(objs);
	  	return types;
	}
	
	/**
	 * Retrieve the specified site type's available site number
	 * @param siteType - given site type
	 * @return available nmuber of sites
	 */
	public int getAvailableSitesByType(String siteType) {
	  	IHtmlObject[] objs=browser.getHtmlObject(".href", new RegularExpression(".*sitefilter="+siteType+".*", false));
	  	
	  	String avaiNum=objs[0].getProperty(".text").toString().split("\\(")[1].split("\\)")[0];;
	  	int num=Integer.parseInt(avaiNum);
	  	
	  	Browser.unregister(objs);
	  	return num;
	}
	
	/**
	 * Click on site type link to go to specified site type list by given type.
	 * @param siteType - site type
	 */
	public void clickSiteTypeLink(String siteType) {
	  	browser.clickGuiObject(".href", new RegularExpression(".*sitefilter="+siteType+".*", false));
	}
	
	/** Click on Campground Map tab link.*/
	public void clickCampgroundMap() {
	  	browser.clickGuiObject(".id", "campMap");
	}
	
	/** Click on Campground Details tab link.*/
	public void clickCampgroundDetail() {
	  	browser.clickGuiObject(".id", "campDetail");
	}
	
	/** Click on Campsite List tab link.*/
	public void clickCampsiteList() {
		browser.clickGuiObject(".id", "campList");
	}
	
	/**
	 * Retrieve the start date.
	 * @return - start date
	 */
	public String getStartDate() {
		return browser.getTextFieldValue(".id", new RegularExpression("arvdate|campingDate", false)); // Lesley[20130916]: update due to RA Unified Search
	}
	
	/**
	 * click Find Next Available Date link on the top of the site List Sub Page.
	 * this link usually display together with "show all", "parks nearby" links on the top of site list table.
	 */
	public void clickFindNextAvailableDateLink(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "Find Next Available Date");
		
		if(objs != null && objs.length >0){
			objs[0].click();
		}else{
			throw new ErrorOnPageException("There is no Find Next Available Date link on the page");
		}
	}
	
	/**
	 * verify there is a link named "Find Next Availalbe Date" 
	 */
	public void verifyFindNextAvailableDateLinkExist(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "Find Next Available Date");
		
		if(objs != null && objs.length >0){
			logger.info("Verify Find Next Available Date link exist successfully.");
		}else{
			throw new ErrorOnPageException("verify \"Find Next Available Date\" link exist on the page failed.");
		}
	}
	
	/**
	 * click show all link on the top of the site List Sub Page.
	 */
	public void clickShowAllLink(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "Show all");
		
		if(objs != null && objs.length >0){
			objs[0].click();
		}else{
			throw new ErrorOnPageException("There is no Show all link on the page");
		}
	}
	
	/**
	 * verify there is a link named "show all" 
	 */
	public void verifyShowAllLinkExist(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "Show all");
		
		if(objs != null && objs.length >0){
			logger.info("Verify Show all link exist successfully.");
		}else{
			throw new ErrorOnPageException("verify there is \"Show all\" link exist on the page failed.");
		}
	}
	
	/**
	 * verify there is a link named "Parks Nearby"
	 */
	public void verifyParksNearbyLinkExist(){
IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "Parks Nearby");
		
		if(objs != null && objs.length >0){
			logger.info("Verify Parks Nearby exist successfully.");
		}else{
			throw new ErrorOnPageException("verify there is \"Parks Nearby\" link exist on the page failed.");
		}
	}
	
	/**Click on first find next available date button.*/
	public void clickFirstFindNextButton() {
	  	IHtmlObject[] objs=browser.getHtmlObject(".className", "book next");
	  	objs[0].click();
	  	Browser.unregister(objs);
	}
	
	/**
	 * Verify whether or not there is any available sites.
	 * @return true - found; false - not found
	 */
	public boolean isOneSiteAvailable() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".className", "book now");
	  	boolean toReturn = false;
	  	
	  	if(objs.length > 0)
	  	  	toReturn = true;
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	/** Click 'Find Next Available Date' below 'No suitable availability shown'*/
	public void clickFindNextAvailableDate() {
	  	browser.clickGuiObject(".class","Html.A",".text", "Find Next Available Date");
	}
	
	/** Click 'Create Availability Notification' below 'No suitable availability shown'*/
	public void clickCreateAvailabilityNotification(){
	  	browser.clickGuiObject(".text", "Create Availability Notification");	
	}
	
	/** Click 'Parks Nearby' below 'No suitable availability shown'*/
	public void clickParksNearbyLink(){
	  	browser.clickGuiObject(".class", "Html.A",".text", "Parks Nearby");
	}
	
	public void clickParksNearbyLink(String contractCode, String parkID){
		browser.clickGuiObject(".text", "Parks Nearby", ".href",
				new RegularExpression(".*parksnearby&parkId="+parkID+"&contractCode="+contractCode+"", false));
	}
	
	/**
	 * Get site status by given site number.
	 * @param siteNum - site number
	 * @return site status
	 */
	public String getSiteStaus(String siteNum){
		IHtmlObject[] table = browser.getTableTestObject(".id","shoppingitems");
		IHtmlTable tableGrid = (IHtmlTable)table[0];
		
		String status = "";
		int siteNumColumn = 0;
		for(int j=0; j<tableGrid.columnCount(); j++) {
			if(tableGrid.getCellValue(1, j).equalsIgnoreCase("Site#")||
					tableGrid.getCellValue(1, j).equalsIgnoreCase("Site #")) {
				siteNumColumn = j;
				break;
			}
		}
		if(siteNumColumn ==0) {
			throw new ErrorOnPageException("Site num column not found, pls check column name.");
		}
		
		for(int i=3; i<tableGrid.rowCount(); i++) {
			if(tableGrid.getCellValue(i, siteNumColumn).indexOf(siteNum) != -1) {
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
	 * Retrieve all site numbers in campsite list page  
	 * @return siteNum - site numbers list
	 */
	public List<String> getAllSiteNums() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems");
		IHtmlTable table = (IHtmlTable) objs[0];
		
		List<String> siteNum = new ArrayList<String>();
		int siteNumCol = table.findColumn(1, new RegularExpression("Site ?#", false)); //Sara[8/28/2013]column name changes to "Site #" from "Site#"
		for(int i=3; i<table.rowCount(); i++) {
//			siteNum.add(table.getCellValue(i, 1).split(" Map")[0].trim());
			siteNum.add(table.getCellValue(i, siteNumCol).replace("Map", "").trim()); // Lesley[08/14/2013]update due to RA UI Redesign;James[20130904] site# is after Map. in case site# is before Map. change to replace Map
		}
		
		Browser.unregister(objs);
		return siteNum;
	}
	
	public boolean verifyPreviousClickable(){
		if (this.verifyBtnEnabled("resultPrevious")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verifyNextClickable(){
		if (this.verifyBtnEnabled("resultNext")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean gotoPrevious(){
		boolean flag = this.verifyPreviousClickable();
		if(flag){
			this.clickPreviousButton();
			this.waitLoading();
		}
		return flag;
	}
	
	
	public void clickNextButton(){
		browser.clickGuiObject(".class","Html.A",".id", "resultNext",true);
	}
	
	public void gotoFirstPage(){
		while(this.verifyPreviousClickable()){
			this.clickPreviousButton();
			this.waitLoading();
		}
	}
	
	public void gotoLastPage(){
		while(this.verifyNextClickable()){
			this.clickNextButton();
			this.waitLoading();
		}
	}
	
	public boolean gotoNext(){
		boolean flag = this.verifyBtnEnabled("resultNext");
		if(flag){
			this.clickNextButton();
			this.waitLoading();
		}
		return flag;
	}
	
	public void clickPreviousButton(){
		browser.clickGuiObject(".class","Html.A",".id", "resultPrevious");
	}
	
	private boolean verifyBtnEnabled(String buttonId){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".id", buttonId);
	
		if (objs[0].getProperty("className").equalsIgnoreCase("disabled")){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * Click on Enter Date link in site list page.
	 * @param siteNum - site number
	 */
	public void clickEnterDate(String siteNum) {
		String siteID = this.getSiteID(siteNum);
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		//james[20130819]: UI changed to "Select"
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".href", href, ".text", new RegularExpression("Enter Date|See Details|Select", false)), true);
	}
	
	/**
	 * Check site type and number info other than the first item: ALL(\\d)
	 * @param siteTypeNum
	 */
	public void checkSiteTypeNum(String[] siteTypeNums){
		List<String> siteTypeNumViaUI = this.getSiteTypeNumText();
		if(siteTypeNumViaUI.size()!=siteTypeNums.length){
			throw new ErrorOnDataException("The actual site type and num length should be equal to the expect one.", siteTypeNums.length+"", siteTypeNumViaUI.size()+"");
		}
		for(int i=0; i<siteTypeNums.length; i++){
			String siteTypeNum = "";
			if(siteTypeNums[i].contains("(") || siteTypeNums[i].contains(")")){
				siteTypeNum = siteTypeNums[i].split("\\(")[0].trim()+" ("+siteTypeNums[i].split("\\(")[1].trim();
			}else{
				siteTypeNum = siteTypeNums[i]+" (0)";
			}
			String fromUI = siteTypeNumViaUI.get(i);
			if(!fromUI.contains(siteTypeNum)){
				throw new ErrorOnDataException("The actual site type and num info is different the expect one.", siteTypeNum, fromUI);
			}
		}
	}
	
	/**
	 * Check site type title info as \\d matching sites
	 * @param siteTypeTitle
	 */
	public void checkSiteTypeTitle(String siteTypeTitle){
		String siteTypeTitleViaUi = this.getMatchAndAvailabilityMsg();
		logger.info("The expected site type title:"+siteTypeTitle);
		logger.info("The actual   site type title:"+siteTypeTitleViaUi);
		
		if(!siteTypeTitleViaUi.contains(siteTypeTitle)){
			throw new ErrorOnDataException("The actual site Type title is incorrect.", siteTypeTitle, siteTypeTitleViaUi);
		}
		logger.info("Successfully verify site type title:"+siteTypeTitle);
	}
	
	/** Get total search result count */
	public String getSearchResultsCount() {
		return browser.getObjectText(".class", "Html.Span", ".id", "resulttotal").trim();
	}
	
	/** Get Site info by site code */
	public SiteInfoData getSiteInfo(String siteCode) {
		SiteInfoData data = null;
		IHtmlObject[] amenitiesIconsDivs = null;
		
		IHtmlObject objs[] = browser.getTableTestObject(".id","shoppingitems");
		IHtmlTable siteTableGrid = (IHtmlTable)objs[0];
		logger.info("Get search result site info for " + siteCode);		

		int loopRowIndex = siteTableGrid.findRow(2, "Loop");
		
		int rowSize = siteTableGrid.rowCount();
		int specificRow = siteTableGrid.findRow(1, new RegularExpression("^" + siteCode + ".*", false)); // site code column index is 1
		if (specificRow > 1 && specificRow < rowSize) {
			// Get the site info when the site code exist in the grid. the online availability column index is 0.
			data = new SiteInfoData();
			data.onlineAvailability = siteTableGrid.getCellValue(specificRow, 0); 
			
			if (data.onlineAvailability.matches(".*\\(\\d+ sites\\).*")) {
				data.nssAvailSitesCount = RegularExpression.getMatches(data.onlineAvailability, "\\d+")[0];
			}
			
			data.siteCode = siteTableGrid.getCellValue(specificRow, 1).replace("Map", "").trim();
			
			if (loopRowIndex > -1) { // The column 2 is for Loop or Area
				data.loopName = siteTableGrid.getCellValue(specificRow, 2);
			} else {
				data.areaName = siteTableGrid.getCellValue(specificRow, 2);
			}
			
			data.siteType = siteTableGrid.getCellValue(specificRow, 3); // The site type column index is 3.
			data.maxNumPeople = siteTableGrid.getCellValue(specificRow, 4); // The max num people column index is 4
			
			String temp = siteTableGrid.getCellValue(specificRow, 5); // The Equip length/ Driveway column index is 5
			if (!StringUtil.isEmpty(temp)) {
				data.maxVehicleLength = temp.split(" ")[0];
				data.drivewayEntry = temp.split(" ")[1];
			}
			
			amenitiesIconsDivs = this.getAmenitiesIconsDivs();
			int headerRowIndex = siteTableGrid.findRow(0, "Online availability");
			int iconsDivIndex = specificRow - headerRowIndex - 2; // the footer row is between the header row and result rows
			data.electricityHookup = this.getElectricityHookup(amenitiesIconsDivs[iconsDivIndex]);
			data.petAllowed = browser.checkHtmlObjectExists(".class", "Html.IMG", 
					".title", "Pets Allowed", amenitiesIconsDivs[iconsDivIndex]);
			data.waterfront = String.valueOf(browser.checkHtmlObjectExists(".class", "Html.IMG", 
					".title", "Near Water", amenitiesIconsDivs[iconsDivIndex]));
		} else {
			logger.info("There is no site " + siteCode + " listed in the page!");
		}
		Browser.unregister(objs, amenitiesIconsDivs);
		return data;
	}
	
	/** Get Amenities Icons DIV elements */
	private IHtmlObject[] getAmenitiesIconsDivs() {
		IHtmlObject[] objs = browser.getHtmlObject(this.amenitiesIconsDIV());
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException("Can't find amenities icons divs on the page!");
		} 
		return objs;
	}
	
	/** Get Electricity Hookup amp value from the hookup image src*/
	private String getElectricityHookup(IHtmlObject top) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG", 
				".title", "Electric Hookup", top);
		String amp = "";
		if (objs == null || objs.length < 1) {
			logger.info("No site electricity image on the page!");
		} else {
			amp = objs[0].getAttributeValue("src");
			amp = RegularExpression.getMatches(amp, "[0-9]+")[0];
		}
		Browser.unregister(objs);
		return amp;
	}

	public String getErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "msg1");
	}
	
	private IHtmlObject getSiteInfoTRBySiteID(String siteID) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(campsiteListTable(), tr()));
		int i = 0;
		for (; i < objs.length; i++) {
			if (browser.checkHtmlObjectExists(siteNumLink(siteID), objs[i])) {
				break;
			}
		}
		if (i == objs.length) {
			throw new ErrorOnPageException("Can't find the table row for the camp site with id=" + siteID);
		}
		IHtmlObject tr = objs[i];
//		Browser.unregister(objs);
		return tr;
	}
	
	public boolean isPetsAllowedIconExist(String siteID) {
		IHtmlObject tr = this.getSiteInfoTRBySiteID(siteID);	
		return browser.checkHtmlObjectExists(this.pertsAllowedImg(), tr);
	}
	
	public void verifyPetsAllowedIconExist(String siteID, boolean exp) {
		String msg = (exp ? " " : " NOT ") + " on shown campsite list page for site ID=" + siteID;
		if (exp != this.isPetsAllowedIconExist(siteID)) {
			throw new ErrorOnPageException("The pets allowed icon should" + msg);
		}
		logger.info("Verify pets allowed icon does" + msg);
	}
	
	public int getAmenityIconsNum(String siteID) {
		IHtmlObject tr = this.getSiteInfoTRBySiteID(siteID);	
		IHtmlObject[] imgDivs = browser.getHtmlObject(this.amenitiesIconsDIV(), tr);
		if (imgDivs.length < 1) {
			throw new ObjectNotFoundException("Can't find the amenities icon div for site with id=" + siteID);
		}
		IHtmlObject[] imgs = browser.getHtmlObject(img(), imgDivs[0]);
		int num = imgs.length;
		Browser.unregister(imgDivs, imgs);
		return num;
	}
	
	public void verifyAmenityIconsNum(String siteID, int expNum) {
		int actualNum = this.getAmenityIconsNum(siteID);
		if (expNum != actualNum) {
			throw new ErrorOnPageException("Amenity icons number is wrong on campsite list page for site with id=" + siteID, expNum, actualNum);
		}
		logger.info("Amenity icons number is correct on campsite list page for site with id=" + siteID + " as " + expNum);
	}
	
	public void clickPageresults(){
		browser.clickGuiObject(pageresults(), 1);
	}
}

