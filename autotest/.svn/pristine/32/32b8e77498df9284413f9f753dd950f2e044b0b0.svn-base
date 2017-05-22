/*
 * Created on Dec 22, 2009
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author QA
 * Access this page by doing a range search or clicking Date Range Availability link.
 */
public class UwpDateRangeAvailabilityPage extends UwpCampingPage{
  	private static UwpDateRangeAvailabilityPage _instance = null;

	public static UwpDateRangeAvailabilityPage getInstance() {
		if (null == _instance)
			_instance = new UwpDateRangeAvailabilityPage();

		return _instance;
	}

	public UwpDateRangeAvailabilityPage() {
	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", "calendar");
//		return browser.checkHtmlObjectExists(".id", "csitecalendar");
		return browser.checkHtmlObjectExists(".id", new RegularExpression("csitecalendar|calendar", false)); //Lesley[20140402] update due to PCR 4463 - Facility Page Re-design in RA
	}

	/** Click on Campsite List link. */
	public void gotoSiteList() {
		Property[] p1 = Property.concatPropertyArray(a(), ".href", "javascript:showSiteListView();");
		Property[] p2 = Property.toPropertyArray(".id","campList");
		boolean result = browser.checkHtmlObjectExists(p2);
		if(!result){
			result = browser.checkHtmlObjectExists(p1);
			if(!result){
				throw new ObjectNotFoundException("Can't find site list tab");
			}else browser.clickGuiObject(p1);
		}else browser.clickGuiObject(p2);
	}
	
	/**
	 * Retrieve first site availabilities info, item 0 is site number, item 1 is loop name
	 * @return sites - site info vector
	 */
	public List<String> getSiteDateRangeAvailability() {
	  	IHtmlObject[] table = browser.getTableTestObject(".id","calendar");
	  	
	  	List<String> sites = new ArrayList<String>();
	  	for(int i=0; i<((IHtmlTable)table[0]).columnCount(); i++) {// only get first site
	  	  	if(i==0)
	  	  	  	sites.add(((IHtmlTable)table[0]).getCellValue(5,i).replaceAll(" Map",""));
	  	  	else
	  	  	  	sites.add(((IHtmlTable)table[0]).getCellValue(5,i));
	  	}
	  	
	  	Browser.unregister(table);
	  	return sites;
	}
	
	/** Click on Campground Map tab link.*/
	public void clickCampgroundMap() {
	  	browser.clickGuiObject(".id", "campMap");
	}
	
	/** Click on Campground Details tab link.*/
	public void clickCampgroundDetail() {
	  	browser.clickGuiObject(".id", "campDetail");
	}
	
	/** Click on first available site to go to site details page.*/
	public String selectFirstAvailSite() {
		IHtmlObject[] sites = null;
		String siteID = "";
		sites = browser.getHtmlObject(".className", "avail");
		
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
	
	public boolean checkSiteAvailable(String siteNum) {
		return checkSiteAvailable(getSiteID(siteNum));
	}
	
	public boolean checkSiteAvailable(int siteID) {
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".className","avail",".href",href));
	}
	
	public int getSiteID(String siteNum) {
		RegularExpression hrefPattern=new RegularExpression("siteId=",false);
		IHtmlObject[] site = browser.getHtmlObject(Property.toPropertyArray(".class","Html.A",".text", siteNum,".href",hrefPattern));
		String href=site[0].getProperty(".href");
		
		String siteID=RegularExpression.getMatches(href, "siteId=\\d+")[0].substring("siteId=".length());
		
		return Integer.parseInt(siteID);
	}
	
	/**
	 * Click try other campgrounds link. 
	 */
	public void clickTryOtherCampgrounds() {
		IHtmlObject[] site = browser.getHtmlObject(".class","Html.A",".text", "try other campgrounds");
		if(site==null || site.length<1)
			throw new ItemNotFoundException("There is no \"try other campgrounds\" link on the page.");
		site[0].click();
		Browser.unregister(site);
	}
	
	/**
	 * Go to site details page by clicking on site number link.
	 * @param siteNum - site number
	 */
	public void clickSiteNum(String siteNum) {
		IHtmlObject[] site = browser.getHtmlObject(".class","Html.A",".text", siteNum);
		if(site.length<1)
			throw new ItemNotFoundException("Site "+ siteNum +"not found.");
		site[0].click();
		Browser.unregister(site);
	}
	
	public void clickSiteNum(int siteID) {
		RegularExpression href=new RegularExpression("siteId="+siteID,false);
		IHtmlObject[] site = browser.getHtmlObject(Property.toPropertyArray(".class","Html.A",".href", href,".className",""));
		if(site.length<1)
			throw new ItemNotFoundException("Site link with id= "+ siteID +" not found.");
		site[0].click();
		Browser.unregister(site);
	}
	
	/**
	 * get the start date for the date range matrix in the format of "Jul 1, 2011";
	 * @return
	 */
	public String getStartDateForDateRangeMatrix(){
		IHtmlObject[] table = browser.getTableTestObject(".id","calendar");
	  	IHtmlTable calendarTable = (IHtmlTable)table[0];
	  	
//	  	String month = calendarTable.getCellValue(2, 2).trim().substring(0, 3);
//	  	String year = calendarTable.getCellValue(2, 2).trim().split(" ")[1].trim();
	  	String month = calendarTable.getCellValue(2, 1).trim().substring(0, 3); // Lesley[20130917]: the month year field's index=1
	  	String year = calendarTable.getCellValue(2, 1).trim().split(" ")[1].trim();
		String day = calendarTable.getCellValue(3, 2).trim().split(" ")[0].trim(); //get the start date for date range matrix.
		
	  	return (month + " " + day + ", " + year);
	  	
	}
	
	/**
	 * Go to site details page by click on See Details link A by given site number.
	 * @param siteNum - site number
	 */
	public void selectAvailSiteBySiteNum(String siteNum) {
		IHtmlObject[] site = browser.getHtmlObject(".class","Html.A",".text", siteNum);
		if(site.length<1)
			throw new ItemNotFoundException("Site "+ siteNum +" not found.");
		
		String href = "";
		if(site.length>1) {
			for(int i=0; i<site.length; i++) {
				String aHref=site[i].getProperty(".href");
				if(aHref.length()>0 && !aHref.startsWith("javascript")){
					href = aHref;
					break;
				}
			}
		} else {
			href = site[0].getProperty(".href");
		}
		
		String siteID = RegularExpression.getMatches(href, "siteId=\\d+")[0].substring("siteId=".length());
		Browser.unregister(site);

		RegularExpression reg = new RegularExpression(".*siteId="+siteID+".*", false);
		IHtmlObject[] sites = browser.getHtmlObject(".className", "avail", ".href", reg);
		
	  	sites[0].click();
	  	Browser.unregister(sites);
	}
	
	/**
	 * get the error message displayed on the top of Date range matrix.
	 * @return
	 */
	public String getErrorMessage(){
		String errorMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "msg1");
		
		if(null != objs && objs.length >0){
			errorMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return errorMsg;
	}
	
	/**
	 * verify the error message with the given expect value.
	 * @param expectError
	 */
	public void verifyErrorMessage(String expectError){
		String currentError = this.getErrorMessage();
		if (expectError.equalsIgnoreCase(currentError)){
			logger.info("verify error message successfully on campsite Date Range availability sub pages.");
		}else{
			logger.error("The expect  error is:" + expectError);
			logger.error("The current error is:" + currentError);
			throw new ErrorOnDataException("verify error message on campsite Date Range availability sub page failed.");
		}
	}
	
	public void selectAvailSiteBySiteID(String siteID) {
		RegularExpression hrefPattern=new RegularExpression("siteId="+siteID,false);
		IHtmlObject[] sites = browser.getHtmlObject(Property.toPropertyArray(".class","Html.A",".className", "avail",".href",hrefPattern));
		if(sites.length<1)
			throw new ItemNotFoundException("Site with id="+ siteID +" not found.");
		
			
	  	sites[0].click();
	  	Browser.unregister(sites);
	}
	
	/** Get month and year displayed on the availability calendar */
	public String getCalendarMonthAndYear() {
		return browser.getObjectText(".class", "HTML.TD", ".classname", "weeknav month");
	}
	
	/** Get the first day displayed on the availability calendar.*/
	public String getFirstDateShownInCalendar() {
		return browser.getObjectText(".class", "HTML.DIV", ".id", "day01date");
	}
	
	/** Get First available date */
	public String getFirstAvailDateShownInCalendar() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".id", "avail1");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find the Available Button on the page");
		}
		String text = objs[0].getAttributeValue("href");
		text = StringUtil.getSubString(text, "arvdate=");
		Browser.unregister(objs);
		return text;
	}
	
	public IHtmlObject[] getAvailableInventorys() {
		RegularExpression reg = new RegularExpression("^(A|A [0-9]+)$", false);
		return browser.getHtmlObject(".class", "Html.A", ".text", reg);
	}
	
	public void goNext2Weeks() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next 2 weeks >");
	}
}
