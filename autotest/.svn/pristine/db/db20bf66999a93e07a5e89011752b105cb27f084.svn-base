package com.activenetwork.qa.awo.pages.orms.venueManager;


import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang
 * @Date  Jan 6, 2012
 */
public class VnuMgrPrintTicketsPage extends VenueManagerPage {
	private static VnuMgrPrintTicketsPage _instance = null;
	
	public static VnuMgrPrintTicketsPage getInstance() {
		if (null == _instance) {
			_instance = new VnuMgrPrintTicketsPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Print Unsold Tickets$", false));
	}
	
	/**
	 * Select tour by given tourName.
	 * @param tourName
	 */
	public void selectTour(String tourName) {
	  	if(null!=tourName && tourName.length()>0)
	  	  	browser.selectDropdownList(".id",
				"TourEventSearchCriteria.tour", tourName);
	}

	/**
	 * Select tour date by given tourDate.
	 * @param tourName
	 */
	public void selectTourDate(String tourDate) {
	  	if(null!=tourDate && tourDate.length()>0)
	  	  	browser.selectDropdownList(".id",
				"unprintedTicketsDate", tourDate);
	}
	
	/**
	 * Fill in start time.
	 * @param fTime - from time
	 */
	public void setFromTime(String fTime) {
	  	if(null!=fTime && fTime.length()>0)
	  	  	browser.setTextField(".id","time_0", fTime);
	}

	/**
	 * Fill in end time.
	 * @param tTime - end time
	 */
	public void setToTime(String tTime) {
	  	if(null!=tTime && tTime.length()>0)
	  	  browser.setTextField(".id","time_1", tTime);
	}
	
	/**
	 * Fill in start time stamp.
	 * @param stamp - AM or PM
	 */
	public void selectFromTimeStamp(String stamp) {
	  	if(null!=stamp && stamp.length()>0)
	  	  	browser.selectDropdownList(".id", "ampm_0", stamp);
	}

	/**
	 * Fill in end time stamp.
	 * @param stamp - AM or PM
	 */
	public void selectToTimeStamp(String stamp) {
	  	if(null!=stamp && stamp.length()>0)
	  		browser.selectDropdownList(".id", "ampm_1", stamp);
	}
	
	/** Click on Search. */
	public void clickSearch() {
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Search");
//		System.out.println(objs.length);
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", 2);
	}
	
	/**
	 * Search unsold tickets.
	 * @param InventoryInfo tour inventory info
	 */
	public void searchUnsoldTickets(InventoryInfo tourInv) {
		this.selectTour(tourInv.tourValue);
		this.setFromTime(tourInv.firstTime);
		this.selectFromTimeStamp(tourInv.firstTimeStamp);
		this.setToTime(tourInv.lastTime);
		this.selectToTimeStamp(tourInv.lastTimeStamp);
		this.clickSearch();
		this.waitLoading();
	}
	
	public String getErrorMessages() {
		String msg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id","Messages");
		
		if(objs.length < 0){
			throw new ErrorOnPageException("There is no error message on top of Print Unsold Tieckts Page");
		}
		
		if(!"".equals(objs[0].getProperty(".text").toString())) {
			msg = objs[0].getProperty(".text").toString();
		}
		
		Browser.unregister(objs);
		return msg;
	}
	
	public String getUnsoldQty(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("unsoldPrntQty.\\d+",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find any unsold ticket");
		}
		int num = 0;
		for(int i=0;i<objs.length;i++){
			num = num + Integer.parseInt(objs[i].getProperty(".value"));
		}
		Browser.unregister(objs);
		return String.valueOf(num);
	}
}
