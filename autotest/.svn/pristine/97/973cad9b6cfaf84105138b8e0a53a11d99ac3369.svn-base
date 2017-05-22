package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpTourSearchPanel extends UwpPage {

	private static UwpTourSearchPanel _instance = null;

	public static UwpTourSearchPanel getInstance() {
		if (null == _instance)
			_instance = new UwpTourSearchPanel();

		return _instance;
	}

	public UwpTourSearchPanel() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "recsearch");
	}

	/**
	 * Select tour name, if null, will select 'all tours'.
	 */
	private void selectTour(String tour) {
		if (tour == null || tour.length() < 1) {
			tour = "All Tours";
		}
		browser.selectDropdownList(".id", "tourId", tour);
	}
	
	/**
	 * Fill in tour date, if null, will set to today.
	 * @param date - tour date
	 */
	public void setTourDate(String date) {
		if (date == null || date.length() < 1) {
			date = DateFunctions.getToday();
		}
		browser.setTextField(".id", "tourDate", date);
	}
	
	/**
	 * Fill in number of tickets, if null, will set to 1.
	 * @param num - number of tickets
	 */
	public void setNumOfTickets(String num) {
		if (num == null || num.length() < 1) {
			num = "1";
		}
		browser.setTextField(".id", "noOfTickets", num);
	}
	
	/**
	 * Search an available tour during the given period
	 * @param period - duration of searching window
	 * @return - actual arrival date
	 */
	public String searchTour(int period) {
		this.clickSearchTours();
		this.searchWaitExists();
		String startDate = browser.getTextFieldValue(".id", "tourDate");
		startDate = DateFunctions.formatDate(startDate);
		String arriveDate = startDate;
		int count = 0;
		IHtmlObject[] tours = this.getAvailableTours();
		while (tours.length < 1) {
			Browser.unregister(tours);
			count++;
			if (count > period)
				throw new ItemNotFoundException(
						"There is no tour available in " + period
								+ " days starting from" + startDate);
			
			arriveDate = DateFunctions.getDateAfterGivenDay(arriveDate, 1);
			this.setTourDate(arriveDate);
			this.clickSearchTours();
			this.searchWaitExists();
			tours = this.getAvailableTours();
		}

		tours[0].click();
		Browser.unregister(tours);

		return arriveDate;
	}
	
	public void clickFindNextAvailDate() {
		browser.clickGuiObject(".class","Html.A",".className","book next");
	}
	
	public boolean findNextAvailDateExists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".className","book next");
	}
	
	/**
	 * Fill in tour search criteria.
	 * @param tour - tour info
	 */
	public void setSearchCriteria(TicketInfo tour) {
		this.selectTour(tour.tourName);
		this.setTourDate(tour.tourDate);
		this.setNumOfTickets(tour.ticketNums);
	}
	
	/**
	 * Click on submit button on tour search.
	 */
	public void clickSearchTours() {
		browser.clickGuiObject(".id", "searchcgrounds",  true);
	}
	
	/**
	 * verify whether the facility is available on date selected
	 * @return false - not available, true - available
	 */
	public boolean isDateAvailable() {
		boolean toReturn = false;
		IHtmlObject[] tours = this.getAvailableTours();
		if (tours.length > 0)
			toReturn = true;

		Browser.unregister(tours);
		return toReturn;
	}
	
	/**
	 * Get all available tour objects.
	 * @return
	 */
	public IHtmlObject[] getAvailableTours() {
		return browser.getHtmlObject(".class", "Html.A", ".text", "Book Now");
	}
	
	/**
	 * Click on first available tour in tour search results page.
	 */
	public void clickOnFirstAvailableTour() {
		IHtmlObject[] tours = this.getAvailableTours();
		tours[0].click();
		Browser.unregister(tours);
	}
	
	/**
	 * Wait for Online availability object exists.
	 */
	public void searchWaitExists() {
		browser.searchObjectWaitExists(".class", "Html.A", ".text", "Online** availability");
	}
	
	/**
	 * Click 'Find other Tour Parks' link to back to tour park list page
	 */
	public void clickFindOtherTourParks() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Find other Tour Parks", false));
	}
	
	/**
	 * Click 'Switch to Group Sales' link to switch to group(Organization) tour sales search page
	 */
	public void clickSwitchToGroupSales() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Switch to Group Sales", false), true);
	}
	
	/**
	 * Verify the current tour sales search page whether is Group or not
	 * @return
	 */
	public boolean isInGroupSales() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Switch to Public Sales", false));
	}
	
	/**
	 * Verify the current tour sales search page whether is Public or not
	 * @return
	 */
	public boolean isInPublicSales() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Switch to Group Sales", false));
	}
	
	/**
	 * Click 'Switch to Public Sales' link to switch to public(Individual) tour sales search page
	 */
	public void clickSwitchToPublicSales() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Switch to Public Sales", false), true);
	}
}
