/*
 * Created on Mar 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CallMgrTicketSearchPage extends CallManagerPage {
  
  static private CallMgrTicketSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected CallMgrTicketSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public CallMgrTicketSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new CallMgrTicketSearchPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id","TourAvailSearchCriteria.numberOfTickets");
	}
	
	public void selectFacility(String facility){
	    browser.selectDropdownList(".id","TourAvailSearchCriteria.venue",facility);
	}
	
	public void setTour(String tour){
	  browser.setTextField(".id","TourAvailSearchCriteria.name",tour);
	}
	
	public void selectTicketCategory(String category){
	  browser.selectDropdownList(".id","TourAvailSearchCriteria.category",category);
	}
	
	public void setDate(String date){
	  browser.setTextField(".id","TourAvailSearchCriteria.tourDate_ForDisplay",date);
	}
	
	public void setTicketNum(String ticketNum){
	  browser.setTextField(".id","TourAvailSearchCriteria.numberOfTickets",ticketNum);
	}
	
	public void checkShowAvailable(){
	  browser.selectCheckBox(".id","TourAvailSearchCriteria.showAvailableOnly");
	}
	
	public void uncheckShowAvailable(){
	  browser.unSelectCheckBox(".id","TourAvailSearchCriteria.showAvailableOnly");
	}
	
	public void clickSearch(){
		IHtmlObject[] frames=getTransactionFrame();
		Property[] p=new Property[]{new Property(".class","Html.A"),new Property(".text","Search"),new Property(".href",new RegularExpression(".*TourSearch.do.*",false))};
		browser.clickGuiObject(p, false, 0, frames[0]);
		Browser.unregister(frames);
	}
	
	public void clickCheckAvailability(){
	  browser.clickGuiObject(".class","Html.A",".text","Check Availability");
	}
	
	public void searchTicket(TicketInfo ticket){
	  this.searchTicket(ticket, true);
	}
	
	public void searchTicket(TicketInfo ticket, boolean isShowAvailableOnly){
		  selectFacility(ticket.facility);
		  setTour(ticket.tour);
		  selectTicketCategory(ticket.category);
		  setDate(ticket.startDate);
		  setTicketNum(ticket.quantity);
		  if(!isShowAvailableOnly){
			  this.uncheckShowAvailable();
		  }
		  this.clickSearch();
		  this.waitLoading();
	}
	
	public void clickTourLink(String tourName){
		browser.clickGuiObject(".class","Html.A",".text",tourName);
	}
	
	public void selectAll(){
		browser.selectCheckBox(".name","all_slct");
	}
	
}
