/*
 * $Id: VnuMgrTicketAvailSchPage.java 6747 2008-11-26 18:09:36Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 *
 * @author CGuo
 */
public class OrmsTicketAvailabilityPage extends OrmsPage {

	/**
	 * Script Name : VnuMgrTicketAvailSchPage Generated : Feb 9, 2007 4:15:45 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 *
	 * @since 2007/02/09
	 */
	private static OrmsTicketAvailabilityPage _instance = null;

	private OrmsTicketAvailabilityPage() {

		// availTickets = new Vector();
	}

	public static OrmsTicketAvailabilityPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketAvailabilityPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Ticket Availability$", false))&&isToursDDListExist();
	}

	/***
	 * Search availibity ticket
	 *
	 * @param ticket
	 */
	public void searchAvailibility(TicketInfo ticket) {
		this.selectTour(ticket.tour);
		this.selectTourCategory(ticket.category);
		this.setDate(ticket.startDate);
		this.setNumOfTikets(ticket.quantity);
		if (ticket.showAvailableOnly) {
			this.unSelectShowAvailableOnly();
		}
		this.clickGo();
	}

	/**
	 * Search availibility tickey by date
	 *
	 * @param startDate
	 */
	public void searchAvailibilitybyDate(String startDate) {
		if(StringUtil.notEmpty(startDate)) {
			setDate(startDate);
		}
		clickGo();
	}

	/**
	 * Do transfer or change ticket
	 *
	 * @param startDate
	 * @param timeSlot
	 * @param numberOfTicket
	 */
	public void changeTicketTime(String startDate, String timeSlot,String numberOfTicket) {
		searchAvailibilitybyDate(startDate);
		this.waitLoading();
		// browser.sync(1);
		this.selectTicket(timeSlot, numberOfTicket);
	}

	public void changeTicketTime(String startDate, String timeSlot,String numberOfTicket, boolean isUpdateTime) {
		searchAvailibilitybyDate(startDate);
		this.waitLoading();
		this.selectTicket(timeSlot, numberOfTicket, isUpdateTime);
	}

	/**transfer to a known ticket*/
	public void transferOrChangeTicket(TicketInfo ticket, boolean isComboTour) {
		this.searchAvailibility(ticket);
		this.waitLoading();
		if(isComboTour){
			this.selectComboTourTicket(ticket.comboTourName, ticket.comboChildTours);
		}else {
			this.selectTicket(ticket.timeSlot, ticket.ticketNum);
		}
	}

	public void selectTicket(String timeSlot, String numberOfTicket) {
		this.selectTicket(timeSlot, numberOfTicket, false);
	}

	/**
	 * This method will select the ticket with the given timeSlot. If timeSlot
	 * is null or empty string, it will select the first available timeSlot.
	 *
	 * @param timeSlot
	 *            - the time slot in format: HH:mm, it can be null or empty
	 *            string
	 * @param numberOfTicket
	 *            - the number Of Ticket. it null or empty, default value is "1"
	 * @param isUpdateTime
	 * 			   - select second available ticket time when timeslot is not specified
	 */
	public void selectTicket(String timeSlot, String numberOfTicket, boolean isUpdateTime) {
		String temp = "";
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("_[0-9]+_[0-9]+",
				false));
		if (timeSlot == null || timeSlot.length() < 1) {
			p[2] = new Property(".text", new RegularExpression(
							"\\d{1,2}:\\d{2}( [A|P]M)?( - \\d{1,2}:\\d{2}( [A|P]M)?)? (\\d+|\\+)",	false));
		} else {
			p[2] = new Property(".text", new RegularExpression("^" + timeSlot + " ([0-9]+)|\\+", false));
		}
		if (numberOfTicket == null || numberOfTicket.length() < 1) {
			numberOfTicket = "1";
		}

		IHtmlObject[] objs = browser.getHtmlObject(p);
		if (objs.length < 1) {
			throw new ItemNotFoundException("no tickets available for "
					+ (timeSlot.length() > 0 ? "time slot " + timeSlot
							: "current day."));
		} else {
			int index = 0;
			if (timeSlot == null || timeSlot.length() < 1) {
				index = -1;
				for (int i = 0; i < objs.length; i++) {
					temp = objs[i].getProperty(".text").toString();
					String[] tokens = temp.split(" ");
					// String[] tokens = objs[i].getProperty(".text").toString()
					// .split(" ");
					// System.out.println(tokens[tokens.length - 1]);
					if (!tokens[tokens.length - 1].equalsIgnoreCase("+")) {
						int num = Integer.parseInt(tokens[tokens.length - 1]);
						if (num >= Integer.parseInt(numberOfTicket)) {
							if(isUpdateTime) {
								isUpdateTime = false;
								continue;//pass the first one to select second available ticket time
							} else {
								index = i;
								break;
							}
						}
					} else {
						index = i;
						break;
					}
				}
			} else {
				// String[] tokens = objs[0].getProperty(".text").toString()
				// .split(" ");
				temp = objs[0].getProperty(".text").toString();
				String[] tokens = temp.split(" ");
				if(!tokens[tokens.length - 1].equalsIgnoreCase("+")){
					int num = Integer.parseInt(tokens[tokens.length - 1]);
					if (num < Integer.parseInt(numberOfTicket))
						index = -1;
				}
			}

			if (index < 0)
				throw new ItemNotFoundException(
						"No enough tickets available for required "
								+ numberOfTicket);

			// MiscFunctions.clickObject(objs[index]);
			objs[index].click();
		}

		Browser.unregister(objs);
	}

	public void selectLotteryTicket(String timeSlot) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("_[0-9]+_[0-9]+",
				false));
		if (timeSlot == null || timeSlot.length() < 1) {
			p[2] = new Property(".text", new RegularExpression(
							"\\d{1,2}:\\d{2}( [A|P]M)?( - \\d{1,2}:\\d{2}( [A|P]M)?)? \\w+",	false));
		} else {
			p[2] = new Property(".text", new RegularExpression("^" + timeSlot + " \\w+", false));
		}

		IHtmlObject[] objs = browser.getHtmlObject(p);
		if (objs.length < 1) {
			throw new ItemNotFoundException("no tickets available for "
					+ (timeSlot.length() > 0 ? "time slot " + timeSlot
							: "current day."));
		} else {
			
			// MiscFunctions.clickObject(objs[index]);
			objs[0].click();
		}

		Browser.unregister(objs);
	}
	
	public String getMutiDayTicketInventory(int index) {
		Property[] p = new Property[] {
				new Property(".class", "Html.A"),
				new Property(".className", "btn_w"),
				new Property(".text", new RegularExpression(
						"^[a-zA-Z]{3} \\d{1,2} - [a-zA-Z]{3} \\d{1,2}.*", false)) };
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"The inventory button you expect is not found..");
		}

		String buttontext = objs[index].getProperty(".text");
		Browser.unregister(objs);
		return buttontext.split("\\+")[0].trim();

	}

	
	public void selectComboTourTicket(String comboTourName, List<TicketInfo> comboTickets){
		for(int i=0; i<comboTickets.size();i++){
			String tourTimeSlot = comboTickets.get(i).timeSlot;
			String tourName = comboTickets.get(i).tour;

			IHtmlObject[] objs = this.getAvailableTourTimeSoltObject(true, comboTourName, tourName, tourTimeSlot);
			if(objs.length<1){
				throw new ItemNotFoundException("Did not found tour time slot object. Combo Tour Name = " + comboTourName
						+ ", tour name = " + tourName + ", tour time slot = " + tourTimeSlot);
			}

			if(tourTimeSlot.matches("\\d+\\:\\d+ (A|P)M")){
				if(tourTimeSlot.endsWith("PM") && objs.length>1){
					objs[1].click();
				}else {
					objs[0].click();
				}
			} else {
				objs[0].click();
			}

			Browser.unregister(objs);
		}
	}

	/**
	 *
	 *  select a ticket time when there are more than one tour.
	 *
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void selectTicketSameTimeSlot(String timeSlot) {

		IHtmlObject[] ticketObjs = browser.getHtmlObject(".class", "Html.A",
				".text", new RegularExpression("^" + timeSlot + " [0-9]+",
						false));

		for (IHtmlObject o : ticketObjs) {
			o.click();
			browser.waitExists();
		}

		Browser.unregister(ticketObjs);

	}

	/**
	 * Select Tour according to tour name
	 *
	 * @param tour
	 */
	public void selectTour(String tour) {
		browser.selectDropdownList(".id", "TourAvailSearchCriteria.tours", tour);
	}

	/**
	 *
	 *  select tours according to the tours'name
	 *
	 * @param tours
	 * @Return void
	 * @Throws
	 */
	public void selectTours(String[] tours) {
		browser.selectDropdownList(Property.toPropertyArray(".id",
				"TourAvailSearchCriteria.tours"), tours, true, true, null);
	}

	public void selectFirstTour() {
		browser.selectDropdownList(".id", "TourAvailSearchCriteria.tours", 0);
	}

	/**
	 * Select Tour according to the index of the tour
	 *
	 * @param index
	 */
	public void selectTour(int index) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id",
				"TourAvailSearchCriteria.tours", index);
	}

	/**
	 * Input Date
	 *
	 * @param date
	 */
	public void setDate(String date) {
		browser.setCalendarField(".id","TourAvailSearchCriteria.tourDate_ForDisplay", date);
	}

	/**
	 * Input NumofTickets
	 *
	 * @param num
	 */
	public void setNumOfTikets(String num) {
		if (num != null && num.length() > 0)
			browser.setTextField(".id",
					"TourAvailSearchCriteria.numberOfTickets", num);
	}

	/** Select show available onley checkbox */
	public void selectShowAvailableOnlyCheckBox() {
		browser.selectCheckBox(".id",
				"TourAvailSearchCriteria.showAvailableOnly");
	}

	/** Unselect show available onley checkbox */
	public void deSelectShowAvailableOnlyCheckBox() {
		browser.unSelectCheckBox(".id",
				"TourAvailSearchCriteria.showAvailableOnly");
	}

	/** Click Go button */
	public void clickGo() {
		//james[20130827] in 3.05 Go button is changed to SEARCH
		//Shane[20130830] in 3.04.03,transfer ticket flow, href attribute changed
		//Lelsey[20130906] in 3.04.03.34, change ticket time flow, href attribute changed
		Property[] p=Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^(Go|SEARCH)$",false),".href",new RegularExpression("(TourAvailSearch\\.do|transferTourSearch|TourOrderChangeTicketTimeSearch\\.do)",false));
		browser.clickGuiObject(p);
	}

	/** Click Purchase tickets */
	public void clickPurchaseTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Purchase Tickets");
	}
	/**check tours dropdown list exist*/
	public boolean isToursDDListExist(){
		return browser.checkHtmlObjectExists(".id","TourAvailSearchCriteria.tours");
	}

	/** Click Cancel button */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void clickHome(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Home");
	}

	/**
	 * Select tour category
	 *
	 * @param category
	 */
	public void selectTourCategory(String category) {
		if (category != null && category.length() > 0) {
			browser.selectDropdownList(".id",
					"TourAvailSearchCriteria.category", category);
		}
	}

	/** Click transfer ticket */
	public void clickTransferTicket() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Transfer Tickets");
	}

	/** Click change tickety time */
	public void clickChangeTicketTime() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Change Ticket Time");
	}

	/**
	 * @Desc Click the tour name url and go to the tour details page
	 * @param tourname
	 * @Return void
	 * @Throws
	 */
	public void clickTourNameUrl(String tourname) {
		browser.clickGuiObject(".class", "Html.A", ".text", tourname);
	}

	/** Make the checkbox 'Show Available Only'unSelect */
	public void unSelectShowAvailableOnly() {
		// browser.selectCheckBox(".id",
		// "TourAvailSearchCriteria.showAvailableOnly");
		browser.clickGuiObject(".id",
				"TourAvailSearchCriteria.showAvailableOnly");
	}

	/**
	 * Check the tour exist in tour list
	 *
	 * @param tour
	 *            -- the tour will be checked
	 * @return-- exist - true, other - false
	 */
	public boolean checkItemExistInTourList(String tour) {
		boolean exist = false;

		List<String> tours = browser.getDropdownElements(".id",
				"TourAvailSearchCriteria.tours");
		for (int i = 0; i < tours.size(); i++) {
			if (tours.get(i).equals(tour)) {
				exist = true;
				break;
			}
		}

		return exist;
	}

	/**
	 *
	 * Check whether the Tours items are all selected or not in the Tour Selection Box
	 * @return true: all items selected in the selection box.
	 * 		   false: not all items selected in the selection box.
	 */
	public boolean isAllItemsSelectedInTourList(){
		boolean isAllSelected = true;
		ISelect dp = null;

		IHtmlObject[] objs = browser.getDropdownList(".id",
		"TourAvailSearchCriteria.tours");

		if (null == objs || objs.length<1){
			throw new ObjectNotFoundException("can't find Tour selection dropdown list on Ticket Availability Page.");
		}
		dp = (ISelect)objs[0];


		if(null != dp){
			List<String> selectedTours = dp.getSelectedTexts();

			List<String> tours = browser.getDropdownElements(".id",
			"TourAvailSearchCriteria.tours");
			if (tours.size() != selectedTours.size()){
				isAllSelected = false;
			}else {
				for (int j =0; j < tours.size(); j++){
					if (!tours.get(j).equalsIgnoreCase(selectedTours.get(j))){
						isAllSelected = false;
						break;
					}

				}

			}
		} else {
			isAllSelected = false;
		}

		Browser.unregister(objs);
		return isAllSelected;
	}

	/**
	 *
	 * @param tour
	 * @return
	 */
	public boolean checkItemExistInResult(String tour) {
		boolean exist = false;
		// Subitem s1 = Browser.atDescendant(".class", "Html.FRAME",
		// ".id","transaction");
		// Subitem s2 = Browser.atDescendant(".class", "Html.A", ".text",tour);
		Property[] p1 = new Property[2];
		p1[0] = new Property(".class", "Html.FRAME");
		p1[1] = new Property(".id", "transaction");

		Property[] p2 = new Property[2];
		p2[0] = new Property(".class", "Html.A");
		p2[1] = new Property(".text", tour);
		IHtmlObject[] objs = browser.getHtmlObject(Browser.atList(p1, p2));
		if (objs.length > 1) {
			exist = true;
		}
		Browser.unregister(objs);
		return exist;
	}

	public List<String> getErrorMessages() {
		List<String> list = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".id","statusMessages");
		if (objs.length > 0) {
			for (int i = 0; i < objs.length; i++) {
				if(!"".equals(objs[i].getProperty(".text").toString())) {
					list.add(objs[i].getProperty(".text").toString());
				}
		    }
		}
		Browser.unregister(objs);
		return list;
	}

	public String getSearchTourDate() {
		return browser.getTextFieldValue(".id",
				"TourAvailSearchCriteria.tourDate_ForDisplay");
	}

	/**
	 *  get tour inventory of specified time in ticket available.
	 *
	 * @param timeSlot
	 * @param numberOfTicket
	 * @return
	 * @Return int
	 * @Throws
	 */
	public String getSelectTimeTourInventory(String timeSlot) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("_[0-9]+_[0-9]+",
				false));
		if (timeSlot == null || timeSlot.length() < 1){
			p[2] = new Property(".text",new RegularExpression("(\\d{1,2}:\\d{2}( [A|P]M)?( - \\d{1,2}:\\d{2}( [A|P]M)?)? \\d+)|([a-zA-Z]{3} \\d{1,2}-[a-zA-Z]{3} \\d{1,2})",
							false));
		}else{
			p[2] = new Property(".text", new RegularExpression(timeSlot
					+ " ([0-9]+)|\\+", false));
		}
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String inventory = objs[0].getProperty(".text")
				.split(" ")[1];
		Browser.unregister(objs);
		return inventory;
	}

	public String getMultiDaySelectTimeTourInventory(String timeSlot) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("_[0-9]+_[0-9]+",
				false));
		if (timeSlot == null || timeSlot.length() < 1){
			p[2] = new Property(".text",new RegularExpression("([a-zA-Z]{3} \\d{1,2} - [a-zA-Z]{3} \\d{1,2} \\+)",
							false));
		}else{
			p[2] = new Property(".text", new RegularExpression(timeSlot
					+ " ([0-9]+)|\\+", false));
		}
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String inventory = objs[0].getProperty(".text").split("\\+")[0].trim();
		Browser.unregister(objs);
		return inventory;
	}
	/**
	 * Get the ticket grid table object
	 * @return
	 */
	private IHtmlTable getTicketGridTable() {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "items");
		IHtmlTable ticketTable = null;
		if(objs.length > 0) {
			 ticketTable = (IHtmlTable)objs[0];
		}

		return ticketTable;
	}

	/**
	 * Get the tour name
	 * @return
	 */
	public String getTourName() {
		IHtmlTable ticketTable = this.getTicketGridTable();
		String text =  ticketTable.getCellValue(4, 0).trim();
		text = text.split("hr.")[0].trim();
		String tourName = text.substring(0, text.length() - 2).toString();

		return tourName;
	}

	/**
	 * Get a ticket's status of a specific day
	 * @param arrivalDate
	 * @return
	 */
	public String getTicketStatus(String tourName, String arrivalDate) {
		String filledArrivalDate = this.getSearchTourDate();
		String formatedArrivalDate = DateFunctions.formatDate(filledArrivalDate);

		this.selectTour(tourName);
		if(DateFunctions.compareDates(formatedArrivalDate, arrivalDate) != 0) {
			this.setDate(arrivalDate);
		}
		this.setNumOfTikets("1");
		this.deSelectShowAvailableOnlyCheckBox();
		this.clickGo();
		waitLoading();

		IHtmlTable ticketTable = this.getTicketGridTable();

		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < ticketTable.columnCount(); i ++) {
			if(ticketTable.getCellValue(4, i + 1) != null) {
				sb.append(ticketTable.getCellValue(4, i + 1).trim() + " | ");
			}
		}
		return sb.toString();
	}

	public String getMouseMoveOverDisplayCapacity(boolean isComboTour,String comboTourName,String tourName, String timeSlot){
		String mouseOverDisplayCapacity = "";
		IHtmlObject[] timeSoltObjects = this.getAvailableTourTimeSoltObject(isComboTour, comboTourName, tourName, timeSlot);
		if(timeSoltObjects.length<1){
			throw new ObjectNotFoundException("Did not found tour time solt object. Tour name= " + tourName
					+ ", time solt = " +timeSlot);
		}

		if(timeSlot.matches("\\d+\\:\\d+ (A|P)M")){
			if(timeSlot.endsWith("PM") && timeSoltObjects.length>1){
				mouseOverDisplayCapacity = timeSoltObjects[1].getProperty("onmouseover");
			}else {
				mouseOverDisplayCapacity = timeSoltObjects[0].getProperty("onmouseover");
			}
		}else {
			mouseOverDisplayCapacity = timeSoltObjects[0].getProperty("onmouseover");
		}

		if(mouseOverDisplayCapacity.trim().length()>0){
			mouseOverDisplayCapacity = mouseOverDisplayCapacity.split("<b>")[1].split("</b>")[0];
		}

		Browser.unregister(timeSoltObjects);
		return mouseOverDisplayCapacity;
	}

	public IHtmlObject[] getAvailableTourTimeSoltObject(boolean isComboTour,String comboTourName,String tourName, String timeSlot){
		String comboTourObjectSubID = "";
		String tourObjectSubID = "";
		String timeSoltObjectSubID = "";
		if(isComboTour){
			Property[] comboTourProperty = new Property[3];
			comboTourProperty[0] = new Property(".id", new RegularExpression("^Grid_combo_\\d+",false));
			comboTourProperty[1] = new Property(".class", "Html.SPAN");
			comboTourProperty[2] = new Property(".text", comboTourName);
			IHtmlObject[] comboTourObject = browser.getHtmlObject(comboTourProperty);
			if(comboTourObject.length<1){
				throw new ObjectNotFoundException("Did not found combo tour object. Combo tour = " + comboTourName);
			}
			comboTourObjectSubID = comboTourObject[0].id().split("_")[2];
			Browser.unregister(comboTourObject);
		}
		Property[] tourProperty = new Property[3];
		tourProperty[0] = new Property(".id", new RegularExpression("^Grid_tour_\\d+",false));
		tourProperty[1] = new Property(".class", "Html.A");
		tourProperty[2] = new Property(".text", tourName);

		IHtmlObject[] tourObject = browser.getHtmlObject(tourProperty);
		if(tourObject.length<1){
			throw new ObjectNotFoundException("Did not found tour object. Tour name= " + tourName);
		}
		tourObjectSubID = tourObject[0].id().split("_")[2];
		Browser.unregister(tourObject);

		if(isComboTour){
			timeSoltObjectSubID = "_" + comboTourObjectSubID + "_" + tourObjectSubID;
		}else {
			timeSoltObjectSubID = "_" + tourObjectSubID;
		}

		Property[] p = new Property[2];
		p[0] = new Property(".id", new RegularExpression("^" + timeSoltObjectSubID + "_\\d+",false));
		if(timeSlot.matches("\\d+\\:\\d+ (A|P)M")){
			timeSlot = timeSlot.replaceAll("(A|P)M", "").trim();
		}
		p[1] = new Property(".text", new RegularExpression("^" +timeSlot+ ".*",false));

		IHtmlObject[] timeSoltObjects = browser.getHtmlObject(p);

		return timeSoltObjects;
	}

	public IHtmlObject[] getUnavailableTourTimeSoltObject(boolean isComboTour,String comboTourName,String tourName, String timeSlot){
		if(isComboTour){
			//TODO: Finish it
		}

		IHtmlObject[] tourObject = browser.getHtmlObject(".class", "Html.TR",".text",new RegularExpression("^" + tourName + ".*"+ timeSlot + ".*",false));
		if(tourObject.length<1){
			throw new ObjectNotFoundException("Did not found tour object." + tourName);
		}
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.DIV");
		p[1] = new Property(".className", "tourRangeCellDisabled");
		if(timeSlot.matches("\\d+\\:\\d+ (A|P)M")){
			timeSlot = timeSlot.replaceAll("(A|P)M", "").trim();
		}
		p[2] = new Property(".text", new RegularExpression("^" + timeSlot+ ".*",false));
		IHtmlObject[] unavailableTimeSoltObjects = browser.getHtmlObject(p, tourObject[0]);
		Browser.unregister(tourObject);

		return unavailableTimeSoltObjects;
	}

	public String getTourSoltQuantity(IHtmlObject topObject){
		IHtmlObject[] spanObjects = browser.getHtmlObject(".class", "Html.SPAN", topObject);
		if(spanObjects.length<1){
			throw new ObjectNotFoundException("Did not found span object, which top object = " + topObject.text());
		}
		String textValue = spanObjects[0].text();
		Browser.unregister(spanObjects);
		return textValue;
	}

	public boolean checkAvail(String tourName, String arrivalDate) {
		boolean toReturn = false;
		if(this.getTicketStatus(tourName, arrivalDate).contains(":")) {
			toReturn = true;
		}
		return toReturn;
	}

	public boolean checkUnavail(String tourName, String arrivalDate) {
		boolean toReturn = false;

		if(this.getTicketStatus(tourName, arrivalDate).contains("This tour can be booked for dates up to")) {
			toReturn = true;
		}

		return toReturn;
	}
	
	public String getTourDateInvInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Tour Date.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("The span about tour inventory information for tour date is not correct!");
		}
		String content = objs[0].text().replace("Tour Date", "");
		return content;
	}
}
