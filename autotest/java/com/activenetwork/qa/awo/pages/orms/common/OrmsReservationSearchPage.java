/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jdu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class OrmsReservationSearchPage extends OrmsPage {
	List<String> reservLists;

	/**
	 * A handle to the unique Singleton instance.
	 */
	//static private OrmsReservationSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsReservationSearchPage() {
		reservLists = new ArrayList<String>();
	}

	/**
	 * @return The unique instance of this class.
	 */
//	static public OrmsReservationSearchPage getInstance()
//			throws PageNotFoundException {
//		if (null == _instance) {
//			_instance = new OrmsReservationSearchPage();
//		}
//
//		return _instance;
//	}

	/** Determine if the page object exists */
//	public boolean exists() {
//		//use the GO button as pageMark
//		//.href: javascript:invokeActionTarget(%20"SearchReservations.do",%20"link",%20"PagingWorker",%20"pfTag_doSearch:pfWorker_ReservationSearch:pParamResetPaging_true:pfParam_:pfDriver_SearchReservations.do:",%20"transaction"%20%20)
//		RegularExpression reg = new RegularExpression(
//				"\"SearchReservations\\.do\",.+pfDriver_SearchReservations.do:",
//				false);
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", reg);
//	}

	/**
	 * Select how many rows that displayed in the reservation search page
	 * @param row
	 */
	public void selectRowsPerPage(int row) {
		browser.selectDropdownList(".id", "pagingBarRowsPerPage", row
				+ " rows per page");
	}
	
	private String prefix1 = "MarinaOrderSearchCriteria-\\d+\\.";
	private String prefix = "("+prefix1+")?";
	
	/**
	 * Select order status 
	 * @param item
	 */
	public void selectOrderStatus(String item) {
		RegularExpression reg = new RegularExpression(prefix+"orderstatus", false);
		if (item !=null && item.length()>0) {
			browser.selectDropdownList(".id", reg, item);
		} else {
			browser.selectDropdownList(".id", reg, 0);
		}
	}

	/**
	 * Select process status
	 * @param item
	 */
	public void selectProcessStatus(String item) {
		if (item !=null && item.length()>0) {
			browser.selectDropdownList(".id", "processstatus", item);
		}
	}

	/**Inptu reserve num
	 * @param reservNum
	 */
	public void setReservNum(String reservNum) {
//		if (!StringUtil.isEmpty(reservNum)) {
			browser.setTextField(".id", new RegularExpression("reservationNum|"+prefix1+"orderNumber", false), reservNum);
//		}
	}

	/**
	 * Click "Search For" drop down
	 * @param item
	 */
	public void selectSearchFor(String item) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchFor", false), item);
	}
	
	/**
	 * Check the Search For label is existed or not.
	 * @return if true, existed; if false, non-existed
	 */
	public boolean checkSearchForLabelExist(){
		return browser.checkHtmlObjectExists(".class", "Html.LABEL",".text","Search For:");
	}
	
	/**
	 * Select check box - select all
	 *
	 */
	public void selectAll(){
	    browser.selectCheckBox(".id", "select_all_checkbox");
	}
	/**
	 * Input phone num
	 * @param phone
	 */
	public void setPhone(String phone) {
		browser.setTextField(".id", new RegularExpression(prefix+"phone", false), phone);
	}


	/**Select "Include Area Code" checkbox*/
	public void selectIncludeAreaCode() {
		browser.selectCheckBox(".id", new RegularExpression(prefix+"includeAreaCode", false));
	}

	/**Don't select "Include Area Code" checkbox*/
	public void deSelectIncludeAreaCode() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"includeAreaCode", false));
	}


	/**Input Last Name*/
	public void setLastName(String lname) {
		browser.setTextField(".id", new RegularExpression(prefix+"lastName", false), lname);
	}

	/**Input first name*/
	public void setFirstName(String fname) {
		browser.setTextField(".id", new RegularExpression(prefix+"firstName", false), fname);
	}

	/**Input Email*/
	public void setEmail(String email) {
		browser.setTextField(".id", new RegularExpression(prefix+"email", false), email);
	}

	/**Input Park Name*/
	public void setParkName(String park) {
		browser.setTextField(".id", "park", park);
	}
	
	/**Input Area/Loop*/
	public void setAreaLoop(String loop) {
		browser.setTextField(".id", "loop", loop);
	}

	/**
	 * Input Site Number
	 * @param siteNum
	 */
	public void setSiteNumber(String siteNum) {
		browser.setTextField(".id", "site", siteNum);
	}

	/**
	 * Input Arrival Date
	 * @param date
	 */
	public void setArrivalDate(String date) {
		browser.setTextField(".id", new RegularExpression(prefix+"arrival_ForDisplay", false), date);
	}

	/**Select "IncludelaterArrival" checkbox*/
	public void selectIncludeLaterArrivals() {
		browser.selectCheckBox(".id", new RegularExpression(prefix+"IncludeLaterArrivals", false));
	}

	/**Don't select "IncludelaterArrival" checkbox*/
	public void deSelectIncludeLaterArrivals() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"IncludeLaterArrivals", false));
	}

	/**Input Departure Date*/
	public void setDepartureDate(String date) {
		browser.setTextField(".id", new RegularExpression(prefix+"departure_ForDisplay", false), date);
	}

	/**Select "Include EarlyDeparture" checkbox*/
	public void selectIncludeEarlyDepatures() {
		browser.selectCheckBox(".id", new RegularExpression(prefix+"IncludeEarlierDepartures", false));
	}

	/**Dond't select "IncludeEarlyDepartures" checkbox*/
	public void deSelectIncludeEarlyDepartures() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"IncludeEarlierDepartures", false));
	}

	/**
	 * Input invoice Number
	 * @param invoice
	 */
	public void setInvoiceNumber(String invoice) {
		browser.setTextField(".id", new RegularExpression(prefix+"invoiceNum", false), invoice);
	}

	/**
	 * Input Receipt Number
	 * @param receipt
	 */
	public void setReceiptNumber(String receipt) {
		browser.setTextField(".id", new RegularExpression(prefix+"receiptNum(ber)?", false), receipt);
	}

	/**
	 * Input Event ID
	 * @param eventid
	 */
	public void setEventID(String eventid) {
		browser.setTextField(".id", "eventid", eventid);
	}

	/**
	 * Input Event Name
	 * @param name
	 */
	public void setEventName(String name) {
		browser.setTextField(".id", "eventname", name);
	}

	/**
	 * Select reservation status
	 * @param item
	 */
	public void selectReservationStatus(String item) {
		RegularExpression reg = new RegularExpression("processstatus|"+prefix1+"resStatus", false);
		if(StringUtil.isEmpty(item)){
			browser.selectDropdownList(".id", reg, 0);
		} else 
			browser.selectDropdownList(".id", reg, item);
	}

	/**
	 * Input closure ID
	 * @param closureid
	 */
	public void setClosureID(String closureid) {
		browser.setTextField(".id", new RegularExpression("paramClosureID|"+prefix1+"closureID", false), closureid);
	}


	/**
	 * Input applied discount name
	 * @param discount
	 */
	public void setNameOfDiscountApplied(String discount) {
		browser.setTextField(".id", new RegularExpression("("+prefix1+"nameOfDiscountApplied)|(DiscountName)", false), discount);
	}

	/**
	 * Input other occupant's Name
	 * @param lname
	 */
	public void setOtherOccupantLastName(String lname) {
		browser.setTextField(".id", "occupantlastname", lname);
	}

	/**
	 * Input other occupant's first name
	 * @param fname
	 */
	public void setOtherOccupantFirstName(String fname) {
		browser.setTextField(".id", "occupantfirstname", fname);
	}

	/**
	 * input vehicle plate number
	 * @param plate
	 */
	public void setVehiclePlateNumber(String plate) {
		browser.setTextField(".id", "vehiclelicense", plate);
	}

	/**
	 * select type of vehicle
	 * @param item
	 */
	public void selectVehicleMake(String item) {
		browser.selectDropdownList(".id", "vehiclemake", item);
	}

	/**
	 * input vehicle model
	 * @param model
	 */
	public void setVechicleModel(String model) {
		browser.setTextField(".id", "vehiclemodel", model);
	}

	/**
	 * Select the color of Vehicle
	 * @param color
	 */
	public void selectVehicelColor(String color) {
		browser.selectDropdownList(".id", "vehiclecolor", color);
	}

	/**
	 * Input operator's last name
	 * @param lname
	 */
	public void setOperatorLastName(String lname) {
		browser.setTextField(".id", new RegularExpression(prefix+"operatorlastname", false), lname);
	}

	/**
	 * Input operator's first name
	 * @param fname
	 */
	public void setOperatorFirstName(String fname) {
		browser.setTextField(".id", new RegularExpression(prefix+"operatorfirstname", false), fname);
	}

	/**Click GO Button*/
	public void clickGO() {
		//james[20130801] change pattern from "GO|Search" to "^(GO|Search)$"
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false), true);
	}

	/**Click Next button*/
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next", true);
	}
	
	public boolean hasNext() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next");
	}
	
	/** Click on the link of the reservation specified by the reservation ID parameter */
	public void selectReservation(String resID) {
		browser.clickGuiObject(".text", resID, ".class", "Html.A", true);
	}
	
	/**
	 * Check the corresponding check box of a specified reservation identified by a reservation number
	 * @param resID
	 */
	public void checkReservation(String resID) {
		IHtmlObject objs[] = null;
		IHtmlTable resTable = null;
		int index = -1;
		int counter = 0;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Res. # Invoice #.*",false));
			resTable = (IHtmlTable) objs[0];
			for(int i = 1; i < resTable.rowCount(); i ++) {
				if(resTable.getCellValue(i, 1).trim().equals(resID)) {
					index = i;
					break;
				}
			}
			if(this.hasNext()) {
				this.clickNext();
				this.waitLoading();
			} else {
				break;
			}
			counter ++;
		} while((counter < 10) && (index == -1));
		
		if(index == -1) {
			throw new ErrorOnPageException("Can't find a reservation which number is " + resID);
		}
		IHtmlObject checkboxes[] = browser.getCheckBox(".id", "reservationNumber");
		((ICheckBox)checkboxes[index - 1]).select();
		
		Browser.unregister(objs);
		Browser.unregister(resTable);
		Browser.unregister(checkboxes);
	}
		
	/**Click on the link of the invoice specified by the reservation ID*/
	public void selectInvoice(String invoiceID){
		browser.clickGuiObject(".text", invoiceID,".class","Html.A",true);
	}
	
	
	/**
	 * click the invoice# based on the given ResNum on orms Reservation  search/list page.
	 * @param ResNum
	 * @return invoice# for the given reservation #
	 */
	public String selectInvoiceNumBasedOnResNum(String ResNum){
		String tablePattern = "^Res. #.*";
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression(tablePattern,false));
		IHtmlTable table = (IHtmlTable)objs[0];
		if (!table.getCellValue(1, 0).equalsIgnoreCase(ResNum)){
			throw new ErrorOnDataException("Can't find the expect POS#:" + ResNum +"at the first row in POS search result list" );
		}
		String pattern = table.getCellValue(1, 1).trim();		
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression(pattern, false), true);
		return pattern;
	}

	/**
	 * Retrieve search result cell value
	 * @param row
	 * @param col
	 * @return
	 */
	public String getSearchResultCellValue(int row, int col) {
		RegularExpression reg = new RegularExpression("^Res. # Invoice #.*",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", reg);
		String toReturn = ((IHtmlTable) objs[0]).getCellValue(row, col).toString();
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * After searching of reservation, add them into list(In Reservation List page)
	 * @return
	 *           -Reservation number list
	 */
	public List<Integer> getDefaultResNums(){
		List<Integer> list = new ArrayList<Integer>();
		RegularExpression reg = new RegularExpression("^Res. # Invoice #.*",false);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", reg);
		IHtmlTable table = (IHtmlTable) objs[0];
		for(int i= 1;  i<=50;i++){
			list.add(Integer.parseInt(table.getCellValue(i, 0).toString().split("-")[1].trim()));
		}
		return list;
	}
	
	/**
	 * Get one Reservation Info according reservation ID
	 * @param resID
	 * @return
	 * @throws PageNotFoundException
	 * @throws ItemNotFoundException
	 */
	public String getResInfo(String resID) throws PageNotFoundException,ItemNotFoundException {
		String toReturn = "";
		this.setReservNum(resID);
		this.clickGO();
		this.searchWaitExists();

		RegularExpression reg = new RegularExpression("^Res. # Invoice #.*",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", reg);
		IHtmlTable searchResult = (IHtmlTable) objs[0];

		if (reservLists.size() == 1) {

			SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");
			try {
				Date arriveDate = df.parse(((IHtmlTable)searchResult).getCellValue(1, 10).toString());
				Date departDate = df.parse(((IHtmlTable)searchResult).getCellValue(1, 11).toString());
				String nightStay = String.valueOf(DateFunctions.diffBetween(departDate, arriveDate));

				toReturn = "parkName->"
						+ ((IHtmlTable)searchResult).getCellValue( 1, 7)
						+ "|"
						+ "areaName->"
						+ ((IHtmlTable)searchResult).getCellValue( 1, 8)
						+ "|"
						+ "siteNum->"
						+ SiteInfoData.convertSiteNameNumber(((IHtmlTable)searchResult).getCellValue( 1, 9).toString())
						+ "|" + "arriveDate->"
						+ ((IHtmlTable)searchResult).getCellValue( 1, 10) + "|"
						+ "nightNum->" + nightStay;

			} catch (ParseException e) {
				throw new ItemNotFoundException("Not unique reservation");
			} catch (DataFormatException e) {
				throw new ItemNotFoundException("Not unique reservation");
			}
		} else {
			throw new ItemNotFoundException("Not unique reservation");
		}
		return toReturn;
	}
	
	public void selectResType(String type){
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression("MarinaOrderSearchCriteria-\\d+\\.slipReservationType", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("MarinaOrderSearchCriteria-\\d+\\.slipReservationType", false), type);
		}
	}

	/** Fill in (and submit) reservation search form with reservation search criteria - as found in ReservationData parameter */
	public void searchReservation(ReservationInfo res) {
		browser.sync();
		if(res.customer.hPhone!=null&&res.customer.hPhone.length()>0)
		  this.setPhone(res.customer.hPhone);
		
		if(res.lastName!=null && res.lastName.length()>0)
		  this.setLastName(res.lastName);
		
		if(res.firstName!=null &&res.firstName.length()>0)
		  this.setFirstName(res.firstName);
		
		if(res.customer.email!=null && res.customer.email.length()>0)
		  this.setEmail(res.customer.email);
		
		if(res.site.parkName!=null && res.site.parkName.length()>0)
		  this.setParkName(res.site.parkName);

		if (res.areacode == true) {
			this.deSelectIncludeAreaCode();
		} else {
			this.selectIncludeAreaCode();
		}

		if (res.includelaterarrivedate == true) {
			this.selectIncludeLaterArrivals();
		}
		
		if(res.site.loopName!=null && res.site.loopName.length()>0)
		  this.setAreaLoop(res.site.loopName);
		
		if(res.site.siteNumber!=null && res.site.siteNumber.length()>0)
		  this.setSiteNumber(res.site.siteNumber);
		
		if(res.reservNum!=null && res.reservNum.length()>0)
		  this.setReservNum(res.reservNum);

		if (res.orderStatus != null&&res.orderStatus.length()>0) {
			this.selectOrderStatus(res.orderStatus);
		}

		if (null!=res.reservStatus && res.reservStatus.length()>0) {
			this.selectReservationStatus(res.reservStatus);
		}

		if(res.site.arrivalDate!=null && res.site.arrivalDate.length()>0)
		  this.setArrivalDate(res.site.arrivalDate);
		
		if(res.site.departDate!=null && res.site.departDate.length()>0)
		  this.setDepartureDate(res.site.departDate);
		
		if(res.receiptnum!=null && res.receiptnum.length()>0)
		  this.setReceiptNumber(res.receiptnum);
		
		if(res.invoiceNum!=null && res.invoiceNum.length()>0)
		  this.setInvoiceNumber(res.invoiceNum);
		
		if(res.eventID!=null && res.eventID.length()>0)
		  this.setEventID(res.eventID);
		
		if(res.eventName!=null && res.eventName.length()>0)
		  this.setEventName(res.eventName);
		
		if(res.closureID!=null&&res.closureID.length()>0)
		  this.setClosureID(res.closureID);
		
		if(res.otherOccupantLastName != null && res.eventName.length()>0)
		  this.setOtherOccupantLastName(res.otherOccupantLastName);
		
		clickGO();
		this.waitLoading();
	}

	/**
	 * Fill in (and submit) reservation search form with reservation ID and search for reservations
	 * @param resID
	 */
	public void searchReservation(String resID) {
		this.setReservNum(resID);
		this.clickGO();
	}

	/**wait for the search result exits*/
	public void searchWaitExists() {
//		Browser.sleep(1);
//		RegularExpression reg = new RegularExpression("[0-9]\\-[0-9]+", false);
//		browser.searchObjectWaitExists(".class", "Html.A", ".text", reg);
		browser.searchObjectWaitExists(".class", "Html.Table", ".id", "MarinaOrdersSearchResultList");
	}

	/**Click Map button*/
	public void clickMap() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Map", true);
	}

	/**
	 * Get reserve num
	 * @param row
	 * @return
	 */
	public String getReservNum(int row) {
		return this.getSearchResultCellValue(row, 0);

	}
	
	/**
	 * Get reserve num
	 * @param row
	 * @return
	 */
	public String getReservStatus(int row) {
		return this.getSearchResultCellValue(row, 2);

	}
	
	public List<String> getResNumPreArrival(){
		return this.getAllReservNum(1);
	}
	
	public List<String> getResNumCheckedIn(){
		return this.getAllReservNum(2);
	}
	
	public List<String> getResNumPreArrivalCheckedIn(){
		return this.getAllReservNum(3);
	}
	
	/**
	 * Return all reservation number in reservation researching page met specific status
	 * @param statusIndex:1.Pre Arrival 2.Checked In 3.Pre Arrival || Checked In
	 * @return  reservationNums.size()=0 || reservationNums.size()>0
	 */
	public List<String> getAllReservNum(int statusIndex) {
		List<String> allReservNum = new ArrayList<String>();
		boolean waitExists = false;
		
		do{
			//It's will refresh page after click Next button.
			if(waitExists){
				this.waitLoading();	
			}
			
			RegularExpression reg = new RegularExpression("^Res. # Invoice #.*",false);
			IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST", false),".text", reg);
			IHtmlTable searchResult = (IHtmlTable) objs[0];
			if(searchResult.rowCount()>0){
				for(int i=1; i<searchResult.rowCount(); i++){
					String resStatus = searchResult.getCellValue(i, 2);
					String resNum = searchResult.getCellValue(i, 0);
					if(statusIndex==1){
						if(resStatus.equalsIgnoreCase("Pre Arrival")){
							allReservNum.add(resNum);
						}
					}else if(statusIndex==2){
						if(resStatus.equalsIgnoreCase("Checked In")){
							allReservNum.add(resNum);
						}
					}else if(statusIndex==3){
						if(resStatus.equalsIgnoreCase("Pre Arrival")||resStatus.equalsIgnoreCase("Checked In")){
							allReservNum.add(resNum);
						}
					}
				}
				Browser.unregister(objs);
			}
			waitExists = true;
		}while(gotoNext());
        return allReservNum;
	}
	
	public Property[] checkOut(){
		return Property.toPropertyArray(".class","Html.A",".text", new RegularExpression("Check-(O|o)ut",false));
	}
	/**
	 * "Check Out" button appears only in Field Manager Batch check out reservation search page.
	 *
	 */
	public void clickCheckOut() {
		browser.clickGuiObject(checkOut());
	}
	
	public void clickDock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Dock");
	}

	/**
	 * "Finish" button appears in field Manager Batch NoShow/check out/in reservation search page.
	 *
	 */
	public void clickFinish() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Finish");
	}

	/**
	 * click check out link to make selected reservations check out
	 *
	 */
	public void clickCheckOutRes(){
	    browser.clickGuiObject(".class","Html.A",".text","Check Out");
	}
	/**
	 * "Check In" button appears only in Field Manager Batch check in reservation search page.
	 *
	 */
	public void clickCheckIn() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Check-In", false),true);
	}

	/**
	 * "No Show" button appears only in Field Manager Batch check in reservation search page.
	 *
	 */
	public void clickNoShow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "No-Show");
	}

	/**
	 * If "Next" button is avaliable, click next button in reservation search page.
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}

	/**Retrive all reserve info in the reservation search page */
	public List<List<String>> retriveReserveinfo() {
		List<List<String>> reserveinfo = new ArrayList<List<String>>();
		List<String> reserveinforow = new ArrayList<String>();
		
		RegularExpression reg = new RegularExpression("^Res. # Invoice # Res. Status*", false);
		IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);
		IHtmlTable reserveTableGrid = (IHtmlTable) reservetable[0];
		
		//get column name
		reserveinforow=((IHtmlTable)reserveTableGrid).getRowValues(0);
		reserveinfo.add(reserveinforow);

		do {
			for (int row = 1; row < reserveTableGrid.rowCount(); row++) {
				reserveinforow = ((IHtmlTable)reserveTableGrid).getRowValues(row);
				reserveinfo.add(reserveinforow);
			}
			Browser.unregister(reservetable);
		} while (gotoNext());

		return reserveinfo;
	}

	/**Get specific column's number*/
	public int getColNum(String colname) {
		List<List<String>> reservinfo = retriveReserveinfo();
		List<String> reservinforow = reservinfo.get(0);
		int colcount = getTableColCount();

		for (int col = 0; col < colcount; col++) {
			if (reservinforow.get(col).toString().trim().equalsIgnoreCase(
					colname)) {
				return col;
			}
		}

		return -1;
	}

	/**get table's all columns*/
	public int getTableColCount() {
		RegularExpression reg = new RegularExpression("^Res. # Invoice # Res. Status.*", false);
		IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);
		IHtmlTable reserveTableGrid = (IHtmlTable) reservetable[0];
		int colcount = reserveTableGrid.columnCount();

		Browser.unregister(reservetable);
		return colcount;
	}

	/**
	 * Get Table's all row count
	 * @return
	 */
	public int getTableRowCount() {
		RegularExpression reg = new RegularExpression("^Res. # Invoice # Res. Status.*", false);
		IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);

		IHtmlTable reserveTableGrid = (IHtmlTable) reservetable[0];
		int rowCount = reserveTableGrid.rowCount();

		Browser.unregister(reservetable);
		return rowCount;
	}

	/**
	 * Get Invoice Number
	 * @return
	 */
	public String getInvoiceNum() {

		int col = this.getColNum("Invoice #");
		String invoiceNum = this.getSearchResultCellValue(1, col);
		return invoiceNum;
	}
	
	/**
	 * To get the check out the balance reservation warning message 
	 * @return warning message
	 */
	public String getBanlanceResCheckOutMessage(){
	    IHtmlObject[] objs=browser.getHtmlObject(".id","batch.checkout.balances.due");
	    return objs[0].getProperty(".text").toString();
	}
	
	/**
	 * To get warning message 
	 * @return warning message
	 */
	public List<String> getWarningMessage() {
		List<String> warnMess = new ArrayList<String>();
		
	    IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("NOTSET|marinareservationsearcherrorcode_\\d+", false));
	    if(objs.length>0){
	    	for(int i=0; i<objs.length; i++){
	    		warnMess.add(objs[i].getProperty(".text").toString());
	    	}
	    }else throw new ItemNotFoundException("Object doesn't find.");
	    
	    Browser.unregister(objs);
	    return warnMess;
	}
	
	public String getErrorMessage(){
	    return browser.getObjectText(".class", "Html.DIV", ".id", new RegularExpression("marinareservationsearcherrorcode_\\d+", false)).trim();
	}
	
	/**
	 * To get the non balance reservation check in warning message
	 * @return warning message
	 */
	public List<String> getNonBalanceResCheckInMessage() {
		List<String> warnMess = new ArrayList<String>();
		
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^[0-9]\\-[0-9]+: Check-in process successfully completed$", false));
	    if(objs.length>0) {
	    	for(int i=0; i<objs.length; i++) {
	    		warnMess.add(objs[i].getProperty(".text").toString().trim());
	    	}
	    } else throw new ItemNotFoundException("Object doesn't find.");
	    
	    Browser.unregister(objs);
	    return warnMess;
	}
	
	/**
	 * To get the check in the balance reservation warning message 
	 * @return warning message
	 */
	public List<String> getBalanceResCheckInMessage() {
		List<String> warnMess = new ArrayList<String>();
		
	    IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^[0-9]\\-[0-9]+: Cannot be checked in due to Outstanding Balance$", false));
	    if(objs.length>0) {
	    	for(int i=0; i<objs.length; i++) {
	    		warnMess.add(objs[i].getProperty(".text").toString().trim());
	    	}
	    } else throw new ItemNotFoundException("Object doesn't find.");
	    
	    Browser.unregister(objs);
	    return warnMess;
	}
	
	/* Click Home button */
	public void clickHome() {
		browser.clickGuiObject(".id", "fieldmgr.leftmenu.id.1", ".text", "Home");
	}
	
	//Click Res. #
	public void clickRes(){
		browser.clickGuiObject(".class", "Html.A",".text","Res. #");
	}
	/**
	 * Get Last Name offset
	 * @return
	 */
	public String getLnameScreenLeft(){
		IHtmlObject [] objs  = browser.getHtmlObject(".class", "Html.LABEL",".text","Last Name");
		String str = objs[0].getAttributeValue(".screenLeft");
		
		Browser.unregister(objs);
		return str;
	}
	
	/**
	 * Get First Name offset
	 * @return
	 */
	public String getFnameScreenLeft(){
		IHtmlObject [] objs  = browser.getHtmlObject(".class", "Html.LABEL",".text","First Name");
		String str = objs[0].getAttributeValue(".screenLeft");
		
		Browser.unregister(objs);
		return str;
	}
	
	/**
	 * Get email offset
	 * @return
	 */
	public String getEmailScreenLeft(){
		IHtmlObject [] objs  = browser.getHtmlObject(".class", "Html.LABEL",".text","Email Address");
		String str = objs[0].getAttributeValue(".screenLeft");
		
		Browser.unregister(objs);
		return str;
	}
	
	/**
	 * Get all options of the Vehicle Color drop down list
	 * @return
	 */
	public List<String> getAllOptionsVehicleColor(){
		IHtmlObject [] objs =  browser.getDropdownList(".id", "vehiclecolor");
		ISelect dropDownList =(ISelect)objs[0]; 
		List<String> list = dropDownList.getAllOptions();
		
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * In the Reservation Search page, get the columns info of search list
	 * @return
	 */
	public boolean checkSrchListColumns(){
		String columns = "Res. # Invoice # Res. Status Customer Phone Email # Occ. Park Area/Loop Site# (Name) Arrival Departure Balance";
		IHtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^Res. # invoice #.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		String actual = table.getProperty(".text");
		
		Browser.unregister(objs);
		if(!actual.equals(columns)){
			return false;
		}else{
			return true;
		}
	}
	
	public void clickBalance(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Balance");
	}
	
	public boolean isAdvancedSearchMode() {
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".src", "common/images_skin1/up.png");
	}
	
	public void clickAdvancedSearch(){
		if(!isAdvancedSearchMode()) {
			browser.clickGuiObject(".class", "Html.A", ".text", "Advanced Search");
		}
	}
	
	public void advancedSearch(){
		this.clickAdvancedSearch();
		ajax.waitLoading();
	}
}
