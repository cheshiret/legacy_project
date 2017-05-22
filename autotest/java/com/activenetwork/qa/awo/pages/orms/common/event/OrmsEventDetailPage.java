/*
 * $Id: OrmsEventDetailPage.java 7058 2009-01-28 18:59:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsEventDetailPage extends OrmsPage {

	/**
	 * Script Name   : OrmsEventDetailPage
	 * Generated     : Jan 24, 2008 2:26:16 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/24
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsEventDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsEventDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsEventDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsEventDetailPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text","# of Active Event Charges");
	}

	/**Click Void Event*/
	public void clickVoidEvent() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void");
	}
	
	/**Click History*/
	public void clickHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "History");
	}

	/**Click undo close event*/
	public void clickUndoCloseEvent() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Close");
	}

	/**Click reserve Site button*/
	public void clickReserveSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reserve Sites");
	}

	/**Click OK button*/
	public void clickOKButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * This button is available for change even date transactions 2nd screen
	 */
	public void selectAllReservationsToBeAutomaticallyChangedDate() {
		IHtmlObject[] frames=getTransactionFrame();
		browser.selectCheckBox(".name","all_slct",0,frames[0]);
		Browser.unregister(frames);
	}
	
	public void selectAllReservationsToBeAutomaticallyCancelled() {
		IHtmlObject[] frames=getTransactionFrame();
		browser.selectCheckBox(".name","all_slct",0,frames[0]);
		Browser.unregister(frames);
	}
	
	/**
	 * This button is available for change even date transactions 2nd screen
	 */
	public void unSelectAllReservationsToBeAutomaticallyChangedDate() {
		IHtmlObject[] frames=getTransactionFrame();
		browser.unSelectCheckBox(".name","all_slct",0,frames[0]);
		Browser.unregister(frames);
	}

	/**Click Note and Alerts button*/
	public void clickNoteAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	public void clickNegotiatePrice() {
		browser.clickGuiObject(".class", "Html.A", ".href",
				new RegularExpression("\"showEventNegotiatePriceEventDetail\"",false));
	}

	/**Click Event History*/
	public void clickEventHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "History");
	}

	/**Click Event Date Change*/
	public void clickEventDateChange() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Date Change");
	}

	/**Click "CorpBilling"*/
	public void clickCorpBilling() {
		browser.clickGuiObject(".class", "Html.A", ".text","Corporate Billing");
	}

	/**Click Close Button*/
	public void clickCloseButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Close");
	}

	/**Click Eventcharge Pos*/
	public void clickEventChargePOS() {
		browser.clickGuiObject(".class", "Html.A", ".href",
				new RegularExpression("\"chargePOSToEvent\"", false));
	}

	/**Click Cancel Button*/
	public void clickCancelButton() {
//		browser.clickGuiObject(".href", new RegularExpression("\"gotoHome\"",false), ".text", "Cancel");\\Debug case:regression.basic.orms.event.history.VerifyEventHistory_CM 
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel",1);
	}

	/**Click Cancel Event*/
	public void clickCancelEvent() {
		browser.clickGuiObject(".href", new RegularExpression("\"startCancelEvent\"", false), ".text", "Cancel");
	}

	/**Click Apply Button*/
	public void clickApplyButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**Click Add Note And Alert*/
	public void clickAddNoteAlert() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	/**Click Add To Cart*/
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("Add To Cart", false));
	}
	
	/**Click Charge POS in the reservations tab*/
	public void clickResservatiosTabChargePOS() {
		browser.clickGuiObject(".href", new RegularExpression(
				"\"chargePOSToEventRes\"", false), ".text", "Charge POS");
	}

	/**Click Print bill in the reservations tab*/
	public void clickReservationsTabPrintBill() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Bill");
	}

	/**Click Add to Cart in the reservation tab*/
	public void clickReservationsTabAddToCart() {
		browser.clickGuiObject(".href", new RegularExpression("\"addReservationsToCart\"", false), ".text", "Add To Cart");
	}

	/**Click Check Out*/
	public void clickResCheckOut() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Check Out");
	}

	/**Click Reservations Tab*/
	public void clickReservationsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("(Site )?Reservations", false));
	}

	/**Click ResChargeTab*/
	public void clickResChargesTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text","Reservation Charges");
//		ajax.waitLoading();
//		browser.searchObjectWaitExists(".href", new RegularExpression(
//				"\"addReservationChargesToCart\"", false), ".text", new RegularExpression("Add To Cart", false));
//		this.waitLoading();
	}

	/**Click Event Charges Tab*/
	public void clickEventChargesTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Event Charges");
//		browser.searchObjectWaitExists(".href", new RegularExpression(
//				"\"addEventChargesToCart\"", false), ".text", new RegularExpression("Add To Cart", false));
//		this.waitLoading();
	}
	
	/**Click Invoice Number hyper link on Event Charges TAB*/
	public void clickInvoiceNumOnEventChargesTab() {
		browser.clickGuiObject(".class", "Html.A", ".id", "EventOrderView.billInvoiceID");
	}

	public void selectChangeEventReason(String reason) {
		if(StringUtil.isEmpty(reason)) {
			browser.selectDropdownList(".id", new RegularExpression("selectedReasonCodeIDForOrderCategory_\\d+",false), 1);//Shane[20140107],id changed from reasonID to current in 3.05.01 build
		} else {
			browser.selectDropdownList(".id", new RegularExpression("selectedReasonCodeIDForOrderCategory_\\d+",false), reason);
		}
	}

	/***
	 * Input Event Name
	 * @param name
	 */
	public void setEventName(String name) {
	  	RegularExpression pattern=new RegularExpression("EventOrderView(-\\d+)?\\.name",false);
		browser.setTextField(".id", pattern, name,true);
	}

	/**
	 * Input event start date
	 * @param date
	 */
	public void setEventStartDate(String date) {
	  	RegularExpression  pattern=new RegularExpression("EventOrderView(-\\d+)?\\.startDate_ForDisplay",false);
		browser.setTextField(".id", pattern,date,true);
	}

	/**
	 * Input Event End Date
	 * @param date
	 */
	public void setEventEndDate(String date) {
	  	RegularExpression  pattern=new RegularExpression("EventOrderView(-\\d+)?\\.endDate_ForDisplay",false);
		browser.setTextField(".id", pattern, date,true);
	}

	/**
	 * Make sure whether is new event detail page
	 * @return
	 */
	public boolean isNewEventDetailsPage() {
		boolean toReturn = true;
		
		RegularExpression pattern=new RegularExpression("EventOrderView(-\\d+)?\\.name",false);
		toReturn = toReturn&& browser.checkHtmlObjectExists(".class", "Html.INPUT.text",".id", pattern);
		if (toReturn) {
		  	pattern=new RegularExpression("EventOrderView(-\\d+)?\\.startDate_ForDisplay",false);
			toReturn = toReturn&& browser.checkHtmlObjectExists(".class","Html.INPUT.text", ".id",pattern);
		}
		
		if (toReturn){
		  	pattern=new RegularExpression("EventOrderView(-\\d+)?\\.endDate_ForDisplay",false);
			toReturn = toReturn&& browser.checkHtmlObjectExists(".class","Html.INPUT.text", ".id",pattern);
		}
		
		return toReturn;
	}

	/**
	 * create new event
	 * @param event
	 * @throws PageNotFoundException
	 */
	public void createNewEvent(EventInfo event) throws PageNotFoundException {
		if (isNewEventDetailsPage()) {
			this.setEventName(event.eventName);
			this.setEventStartDate(event.eventStart);
			this.setEventEndDate(event.eventEnd);
			this.clickOKButton();
		} else {
			throw new PageNotFoundException(
					"New event detail page is not correct.");
		}
	}

	/**
	 * Get event details table
	 * @return
	 */
	public IHtmlObject[] getEventDetailsTable() {
		return browser.getTableTestObject(".text", new RegularExpression(
				"^Event Actions ", false));
	}

	/**
	 * Verify event status
	 * @param status
	 * @return
	 */
	public boolean verifyEventStatus(String status) {
		boolean result = false;
		IHtmlObject[] objs = this.getEventDetailsTable();
		String text = ((IHtmlTable)objs[0]).getCellValue(1, 1).toString();
		Browser.unregister(objs);
		if(text.length()<1 || !text.matches(".* Status " + status + " ?.*")) {
			//After Orms 3.0, status is in a readOnly text field
			text=browser.getTextFieldValue(".id","EventOrderView.status:CB_TO_NAME"); 
			result=text.equalsIgnoreCase(status);
		} else {
			result = text.matches(".* Status " + status + " ?.*");
		}
		return result;
	}

	/**Get event ID*/
	public String getEventID() {
		String id = "";
		IHtmlObject[] objs = this.getEventDetailsTable();
		String text = ((IHtmlTable)objs[0]).getCellValue(1, 1).toString();
		if (text.matches("ID [0-9]+ "))
			id = RegularExpression.getMatches(text, "[0-9]+")[0];
		Browser.unregister(objs);
		return id;
	}

	/**Get Reservations Table*/
	public IHtmlObject[] getReservationsTable() {
		return browser.getTableTestObject(".id", new RegularExpression(
				"grid_[0-9]+", false), ".text", new RegularExpression(
				"^Reservation # ", false));
	}

	/**
	 * Select Reservations
	 * @param resNum
	 */
	public void selectReservation(String resNum) {
		//boolean result = false;
		IHtmlObject[] objs = this.getReservationsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, resNum);		
		//get the InvoiceNum		
		String invoiceId= table.getCellValue(rowNum, 2).toString();
		
		Browser.unregister(objs);
		browser.selectCheckBox(".id", new RegularExpression("CampingOrderView.id|I_EventReservationOrderView.id", false), ".value", invoiceId);
	}

	/**Charge POS on reservation
	 * @param posResNum
	 * */
	public void chargePOSOnRes(String posResNum) {
		selectReservation(posResNum);
		this.clickResservatiosTabChargePOS();
	}

	/**
	 * get event start date
	 * @param fString
	 * @param lString
	 * @return
	 */
	public String getStartDate(){
		if(checkStartDate()){
			return browser.getTextFieldValue(".id", "EventOrderView.startDate"); //QA4
		}else {
			  return this.getDate("Start Date","End Date");
		}
	}
	public boolean checkStartDate(){
		return browser.checkHtmlObjectExists(".id", "EventOrderView.startDate");
	}
	
	/**
	 * get event end date
	 * @param fString
	 * @param lString
	 * @return
	 */
	public String getEndDate(){
		if(checkEndDate()){
			return browser.getTextFieldValue(".id", "EventOrderView.endDate"); //QA4
		}else{
			  return this.getDate("End Date","");
		}
	}
	public boolean checkEndDate(){
		return browser.checkHtmlObjectExists(".id", "EventOrderView.endDate");
	}
	
	/**
	 * The method get start/End date in the event detail page
	 * @param fString
	 * @param lString
	 * @return
	 */
	public String getDate(String fString, String lString) {
		String theString = getEventDetailsCellValue(2, 1);
		int sIndex = theString.indexOf(fString) + fString.length();
		if ( lString !=null&&lString.length()>0) {
			int lIndex = theString.indexOf(lString);
			return (theString.substring(sIndex, lIndex)).trim();
		} else {
			return (theString.substring(sIndex)).trim();
		}
	}
	
	/**
	 * get event status
	 * @param fString
	 * @param lString
	 * @return
	 */
	public String getStatus() {
		//QA4
		if(checkStatus()){
			return browser.getTextFieldValue(".id", "EventOrderView.status:CB_TO_NAME");
		}else{
			String theString = getEventDetailsCellValue(1, 1);
			String fString="Status";
			int sIndex = theString.indexOf(fString) + fString.length();
			return (theString.substring(sIndex)).trim();	
		}
	}
	
	public boolean checkStatus(){
		return browser.checkHtmlObjectExists(".id", "EventOrderView.status:CB_TO_NAME");
	}
	
	/**
	 * get event details page cell value according row and col
	 * @param row
	 * @param col
	 * @return
	 */
	public String getEventDetailsCellValue(int row, int col) {
	  RegularExpression reg=new RegularExpression("Event Actions.*",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", reg);
		IHtmlTable resDetail = (IHtmlTable) objs[0];
		String toReturn = resDetail.getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * retrieve event charge Pos information table
	 * @return
	 */
	public List<List<String>> retriveEventChargePOSInfo(){
	  List<List<String>> posInfo=new ArrayList<List<String>>();
	  List<String> posInfoRow = new ArrayList<String>();
	  
	  //OrmsEventDetailPage eventDetailPg = OrmsEventDetailPage.getInstance();
	  
	  //int colnum;
	  RegularExpression reg = new RegularExpression("^POS Sale#*", false);
	  IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);
	  IHtmlTable reserveTableGrid=(IHtmlTable)reservetable[0];
	  Browser.unregister(reservetable);
	  for (int row = 1; row < reserveTableGrid.rowCount(); row++) {
		posInfoRow = reserveTableGrid.getRowValues(row);
	    posInfo.add(posInfoRow);
	  }
	  
	  return posInfo;
	}
	
	/**
	 * get current event name in the event detail page
	 * @return
	 */
	public String getEventName(){
	  IHtmlObject[] obj= browser.getHtmlObject(".id","EventOrderView.name");
	  String eventName=obj[0].getProperty(".defaultValue").toString();
	  
	  return eventName;
	}
	
	/**
	 * check if the error message exits when voiding the Event
	 * @return error message
	 */
	public boolean checkErrorMessageExits(){
	    return browser.checkHtmlObjectExists(".id","NOTSET");
	}
	
	/**
	 * Get the error message if the Event could not be void
	 * @return error message
	 */
	public String getErrorMessage(){
	    IHtmlObject[] obj=browser.getHtmlObject(".id","NOTSET");
	    String errorMessage=obj[0].getProperty(".text").toString();
	    Browser.unregister(obj);
	    
	    return errorMessage;
	}

	/** Click Charge POS button on Event Actions field */
	public void clickChargePOS() {
		browser.clickGuiObject(".class", "Html.A", ".href", 
				new RegularExpression("\"SearchPOS.do\", \"POSChargeWorker\", \"chargePOSToEvent", false));
	}
	
}
