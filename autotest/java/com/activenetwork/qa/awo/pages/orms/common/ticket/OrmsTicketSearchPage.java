/*
 * $Id: VnuMgrTicketSchPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsTicketSearchPage extends OrmsPage {

	/**
	 * Script Name   : VnuMgrTicketSchPage
	 * Generated     : Feb 19, 2007 1:44:02 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/02/19
	 */
	private static OrmsTicketSearchPage _instance = null;

	private OrmsTicketSearchPage() {
	}

	public static OrmsTicketSearchPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketSearchPage();
		}

		return _instance;
	}
	/** Determine if 
 associated web object exists */
	public boolean exists() {
		//RegularExpression text = new RegularExpression(".*\"TourSearch\\.do\",%20\"TourSearch\",%20\"VenueSearch\".*",false);
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Search");
	    return browser.checkHtmlObjectExists(".id", "TourAvailSearchCriteria.venue");
	}

	/**Click Search button*/
	public void clickSearch() {
		RegularExpression text = new RegularExpression(".*\"TourSearch\\.do\",%20\"TourSearch\",%20\"VenueSearch\".*",false);
		browser.clickGuiObject(".class", "Html.A", ".href", text);
	}

	/**
	 * Input tour name
	 * @param name
	 */
	public void setTourName(String name) {
		browser.setTextField(".id", "TourAvailSearchCriteria.name", name);
	}

	/**
	 * Input qty of tickets
	 * @param num
	 */
	public void setNumberOfTickets(String num) {
		browser.setTextField(".id", "TourAvailSearchCriteria.numberOfTickets",num);
	}

	/**
	 * Set Tour date
	 * @param date
	 */
	public void setTourDate(String date) {
		browser.setTextField(".id","TourAvailSearchCriteria.tourDate_ForDisplay", date);
	}

	/**
	 * Select category
	 * @param category
	 */
	public void selectCategory(String category) {
		browser.selectDropdownList(".id", "TourAvailSearchCriteria.category",category);
	}

	/**
	 * Select Category
	 * @param index
	 */
	public void selectCategory(int index) {
		browser.selectDropdownList(".id", "TourAvailSearchCriteria.category",index);
	}

	/**
	 * Select Facility by facility name
	 * @param facility
	 */
	public void selectFacility(String facility) {
		browser.selectDropdownList(".id", "TourAvailSearchCriteria.venue",facility);
	}

	/**
	 * Select facility by the index of facility
	 * @param index
	 */
	public void selectFacility(int index) {
		browser.selectDropdownList(".id", "TourAvailSearchCriteria.venue",index);
	}

	/**Select show available only checkbox*/
	public void selectShowAvailableOnlyCheckBox() {
		browser.selectCheckBox(".id","TourAvailSearchCriteria.showAvailableOnly");
	}

	/**Unselect show availabel only checkbox*/
	public void deSelectShowAvailableOnlyCheckBox() {
		browser.unSelectCheckBox(".id","TourAvailSearchCriteria.showAvailableOnly");
	}

	/**click "Check availability" */
	public void clickCheckAvailability() {
		browser.clickGuiObject(".class", "Html.A", ".text","Check Availability");
	}

	/**Select all tours checkbox*/
	public void selectAllToursCheckBox() {
		browser.selectCheckBox(".id", "all_slct");
	}

	/**Unselect alltours checkbox*/
	public void deSelectAllToursCheckBox() {
		browser.unSelectCheckBox(".id", "all_slct");
	}

	/**
	 * Select tour checkbox
	 * @param tour
	 */
	public void selectTourCheckBox(String tour) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", tour);
		String href = objs[0].getProperty(".href").toString().replaceAll("%20","");
		String[] tokens = RegularExpression.getMatches(href, "[0-9]+");
		String id = tokens[0];
		Browser.unregister(objs);
		browser.selectCheckBox(".id", "TourAvailSearchCriteria.tours",".value", id);
	}

	/**
	 * Select tour checkbox
	 * @param index
	 */
	public void selectTourCheckBox(int index) {
		browser.selectCheckBox(".id", "TourAvailSearchCriteria.tours", index);
	}
	
	public void searchTicket(TicketInfo ticket){
		  selectFacility(ticket.facility);
		  this.setTourName(ticket.tour);
		  this.selectCategory(ticket.category);
		  this.setTourDate(ticket.startDate);
		  this.setNumberOfTickets(ticket.quantity);
		  
		  this.clickSearch();
		  this.waitLoading();
		}

	/**
	 * Check whether is result empty
	 * @return
	 */
	public boolean isResultEmpty() {
		return browser.checkHtmlObjectExists(".id","TourAvailSearchCriteria.tours");
	}
}
