/*
 * $Id: OrmsMovePOSPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipReservationInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsMovePOSPage extends OrmsPage {

	/**
	 * Script Name   : OrmsMovePOSPage
	 * Generated     : Feb 1, 2008 7:20:11 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/02/01
	 */
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsMovePOSPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsMovePOSPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsMovePOSPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsMovePOSPage();
		}

		return _instance;
	}
	
	protected Property[] moveSelectionRadioButton() {
		return Property.toPropertyArray(".class","Html.INPUT.radio",".id", "move_type");
	}
	
	protected Property[] moveInEventRadioButton() {
		return Property.concatPropertyArray(moveSelectionRadioButton(), ".value", "23");
	}
	
	protected Property[] moveOutRadioButton() {
		return Property.concatPropertyArray(moveSelectionRadioButton(), ".value", "28");
	}
	

	//added by summer in 10/May/2014 reason: in OM and CM, there exist option of moving to site or moving to slip
	protected Property[] moveInSlipResRadioButton() {
		return Property.concatPropertyArray(moveSelectionRadioButton(), ".value", new RegularExpression("209",false));//Shane[20140610]209 stands for slip reservation
	}

	protected Property[] moveInSiteResRadioButton() {
		return Property.concatPropertyArray(moveSelectionRadioButton(), ".value", new RegularExpression("24",false));
	}
	
	protected Property[] reservationNumber() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id",	"ResToChargeSearchCriteria.reservationNumber");
	}
	protected Property[] movePOSTable() {
		return Property.addToPropertyArray(table(), ".text",new RegularExpression("Where do you want to move each selected POS Sale",false));
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(moveSelectionRadioButton());
	}

	/**Wait the searched event exist*/
	public void schEventWaitExists() throws PageNotFoundException {
		browser.searchObjectWaitExists(".class", "Html.A", ".id",
				"EventOrderLightView.ID");
	}

	/**Wait the searched reservation exist*/
	public void schResWaitExists() throws PageNotFoundException {
		browser.searchObjectWaitExists(".class", "Html.INPUT.radio", ".id",	new RegularExpression("GenericGrid-\\d+\\.selectedItems|selected_ord_id",false));
	}

	/**Click OK*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Input Notes
	 * @param note
	 */
	public void setNotes(String note) {
		browser.setTextArea(".id", "MovePOSView.reason", note,true);
	}

	/**Click Go button*/
	public void clickSearch() {
		//james[20130826]: UI changed to "SEARCH"
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|SEARCH)$",false));
	}

	/**Select Move Out*/
	public void selectMoveOut() {
		browser.selectRadioButton(moveOutRadioButton(),true,0);
	}

	/**Select Move In Event*/
	public void selectMoveInEvent() {
		browser.selectRadioButton(moveInEventRadioButton(),true,0);
	}

	/**Select Move In Res*/
	public void selectMoveInSiteRes() {
		browser.selectRadioButton(moveInSiteResRadioButton(),true,0);
	}
	/**Select Move In site Res*/
	public void selectMoveInSlipRes() {
		browser.selectRadioButton(moveInSlipResRadioButton(),true,0);
	}
	/**
	 * Set Event ID
	 * @param id
	 */
	public void setEventID(String id) {
		browser.setTextField(".id", "EventSearchCriteria.eventId:ZERO_TO_NULL",id);
	}

	/**
	 * Set Reservation Num
	 * @param res
	 */
	public void setResNum(String res) {
		browser.setTextField(".id","ResToChargeSearchCriteria.reservationNumber", res);
	}

	/**
	 * Move Out Event Or reservation
	 * @param notes
	 */
	
	/**
	 * get move pos option label texts
	 * 
	 */

	public List<String> getMoveOptionTexts(){
		List<String> optionTexts=new ArrayList<String>();
		logger.info("Get move option text");
		IHtmlObject[] tops = browser.getTableTestObject(".id", "MovePOSOptions");
		
		if(null==tops||0==tops.length){//if(tops.length<1)
			throw new ErrorOnPageException("HTML Oject move option table is not found");
			
		}
		IHtmlObject[] table=browser.getTableTestObject(movePOSTable(), tops[tops.length-1]);
		IHtmlTable grid = (IHtmlTable)table[table.length-1];
		int rowCount=grid.rowCount();
		int textCol=0;
		for(int i=1;i<=rowCount-2;i++) {
			optionTexts.add(grid.getCellValue(i, textCol));
		}
		//release html objects
		Browser.unregister(tops);
		Browser.unregister(table);

		return optionTexts;
	}
	
	public void moveOutEventOrRes(String notes) {
		this.selectMoveOut();
		this.setNotes(notes);
		this.clickOK();
	}

	/**
	 * Move to Event
	 * @param id
	 * @param notes
	 * @throws PageNotFoundException
	 */
	public void moveToEvent(String id, String notes)
			throws PageNotFoundException {
		this.selectMoveInEvent();
		browser.searchObjectWaitExists(".class", "Html.INPUT.text", ".id",
				"EventSearchCriteria.eventId:ZERO_TO_NULL");
		this.setEventID(id);
		this.setNotes(notes);
		this.clickSearch();
		this.schEventWaitExists();
		selectEvent();
		this.clickOK();
	}

	/**
	 * Select Event
	 * @throws PageNotFoundException
	 */
	public void selectEvent() throws PageNotFoundException {
		IHtmlObject[] radioButtons = browser.getHtmlObject(".class",
				"Html.INPUT.radio", ".id", "selected_event_id");
		if (radioButtons.length > 0)
			((IRadioButton)radioButtons[0]).click();
		else
			throw new PageNotFoundException("There is not event found.");
	}

	/**
	 * Move to reservation
	 * @param res
	 * @param notes
	 * @throws PageNotFoundException
	 */
	public void moveToSiteRes(String res, String notes){
		this.moveToRes(res, notes, "site");
	}
	
	protected void moveToRes(String res, String notes,String target){
		if(target.equalsIgnoreCase("site")){
			this.selectMoveInSiteRes();
		}else if(target.equalsIgnoreCase("slip")){
			selectMoveInSlipRes();
		}else{
			throw new ItemNotFoundException("Unknown target:"+target);
		}
		browser.searchObjectWaitExists(reservationNumber(),OrmsConstants.LONG_SLEEP);
		this.setResNum(res);
		this.setNotes(notes);
		this.clickSearch();
		this.schResWaitExists();

		selectReservation();
		this.clickOK();
	}
	
	public void moveToSlipRes(String res,String notes){
		this.moveToRes(res, notes, "slip");
	}

	/**
	 * Select Reservation
	 * @throws PageNotFoundException
	 */
	public void selectReservation() throws PageNotFoundException {
		RegularExpression pattern=new RegularExpression("GenericGrid-\\d+\\.selectedItems",false);
		browser.clickGuiObject(".id",pattern);

	}
	
	public void setEventName(String name){
		browser.setTextField(".id", new RegularExpression("EventSearchCriteria.eventName",false), name);
	}
	
	public void setLocation(String location){
		browser.setTextField(".id", new RegularExpression("EventSearchCriteria.locationName",false), location);
	}
	
	public void setCustomerLastName(String lastName){
		browser.setTextField(".id", new RegularExpression("EventSearchCriteria.customerLastName",false), lastName);
	}
	
	public void setCustomerPhone(String phone){
		browser.setTextField(".id", new RegularExpression("EventSearchCriteria.customerPhone",false), phone);
	}
	
	public void searchEvent(EventInfo event){
		if(null != event.eventID){
			this.setEventID(event.eventID);
		}
		
		if(null != event.eventName){
			this.setEventName(event.eventName);
		}
		
		if(null != event.location){
			this.setLocation(event.location);
		}
		
		if(null != event.lName){
			this.setCustomerLastName(event.lName);
		}
		
		if(null != event.phone){
			this.setCustomerPhone(event.phone);
		}
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getEventListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "eventListUI_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get event list table object.");
		}
		return objs;
	}
	
	private IHtmlObject[] getSlipResListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+\\_LIST",false),
				".text", new RegularExpression("Slip # \\(Name\\)( )?Slip Reservation#.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get slip Reservation list table object.");
		}
		return objs;
	}
	
	private List<String> getSlipResListTableColumnListValue(String columnName){
		IHtmlObject[] objs = this.getSlipResListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int col = table.findColumn(0, columnName);
		List<String> lists = table.getColumnValues(col);
		lists.remove(0);
		Browser.unregister(objs);
		return lists;
	}
	
	public SlipReservationInfo getSlipResInfoOfSearchResult(String slipResNum){
		IHtmlObject[] objs = this.getSlipResListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.findRow(2, slipResNum);
		if(row<1){
			throw new ItemNotFoundException("Did not get slip reservation info with slip reservation number = " + slipResNum);
		}
		
		SlipReservationInfo slipRes = new SlipReservationInfo();
		slipRes.getSlip().setName(table.getCellValue(row, 1));
		slipRes.reservNum = table.getCellValue(row, 2);
		slipRes.getSlip().setArrivalDate(table.getCellValue(row, 3));
		slipRes.getSlip().setDepartureDate(table.getCellValue(row, 4));
		slipRes.primaryOccupantLastName = table.getCellValue(row, 5).split(",")[0];
		slipRes.primaryOccupantFirstName = table.getCellValue(row, 5).split(",")[1];
		slipRes.orderStatus = table.getCellValue(row, 6);
		slipRes.customer.lName = table.getCellValue(row, 7).split(",")[0];
		slipRes.customer.fName = table.getCellValue(row, 7).split(",")[1];
		slipRes.eventID = table.getCellValue(row, 8);
		slipRes.eventName = table.getCellValue(row, 9);
		slipRes.eventStartDate = table.getCellValue(row, 10);
		slipRes.eventEndDate = table.getCellValue(row, 11);
		Browser.unregister(objs);
		return slipRes;
	}
	
	public List<String> getSlipNumNameListValues(){
		return this.getSlipResListTableColumnListValue("Slip # (Name)");
	}
	
	public List<String> getSlipResNumListValues(){
		return this.getSlipResListTableColumnListValue("Slip Reservation#");
	}
	
	public List<String> getArrivalDateListValues(){
		return this.getSlipResListTableColumnListValue("Arrival Date");
	}
	
	public List<String> getDepartureDateListValues(){
		return this.getSlipResListTableColumnListValue("Depature Date");
	}
	
	public List<String> getPrimaryOccupantListValues(){
		return this.getSlipResListTableColumnListValue("Primary Occupant");
	}
	
	public List<String> getOrderStatusListValues(){
		return this.getSlipResListTableColumnListValue("Order Status");
	}
	
	public List<String> getSlipResCustomerListValues(){
		return this.getSlipResListTableColumnListValue("Billing Customer");
	}
	
	public List<String> getEventIDOfSlipResListValues(){
		return this.getSlipResListTableColumnListValue("Event ID");
	}
	
	public List<String> getEventNameOfSlipResListValues(){
		return this.getSlipResListTableColumnListValue("Event Name");
	}
	
	public List<String> getEventStartDateOfSlipResListValues(){
		return this.getSlipResListTableColumnListValue("Start Date");
	}
	
	public List<String> getEventEndDateOfSlipResListValues(){
		return this.getSlipResListTableColumnListValue("End Date");
	}
	
	private List<String> getEventListTableColumnListValue(String columnName){
		IHtmlObject[] objs = this.getEventListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int col = table.findColumn(0, columnName);
		List<String> lists = table.getColumnValues(col);
		lists.remove(0);
		Browser.unregister(objs);
		return lists;
	}
	
	public List<String> getEventIDColumnListValues(){
		return this.getEventListTableColumnListValue("Event ID");
	}
	
	public List<String> getEventNameColumnListValues(){
		return this.getEventListTableColumnListValue("Event Name");
	}
	
	public List<String> getEventLocationListValues(){
		return this.getEventListTableColumnListValue("Location");
	}
	
	public List<String> getEventCustomerListValues(){
		return this.getEventListTableColumnListValue("Billing Customer");
	}
	
	public EventInfo getEventInfoOfSearchResult(String eventID){
		IHtmlObject[] objs = this.getEventListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.findRow(0, eventID);
		if(row<1){
			throw new ItemNotFoundException("Did not get event info with event id = " + eventID);
		}
		EventInfo event = new EventInfo();
		event.eventName = table.getCellValue(row, 1);
		event.eventStart =  table.getCellValue(row, 2);
		event.eventEnd = table.getCellValue(row, 3);
		event.lName = table.getCellValue(row, 4).split(",")[0];
		event.fName = table.getCellValue(row, 4).split(",")[1];
		event.location = table.getCellValue(row, 5);
		Browser.unregister(objs);
		return event;
	}
	
	public void compareEventInfoOfSearchResult(EventInfo event){
		EventInfo actEvent = this.getEventInfoOfSearchResult(event.eventID);
		
		logger.info("Compare event info of search result.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Event Name", event.eventName, actEvent.eventName);
		result &= MiscFunctions.compareResult("Event Start Date", event.eventStart, actEvent.eventStart);
		result &= MiscFunctions.compareResult("Event End Date", event.eventEnd, actEvent.eventEnd);
		result &= MiscFunctions.compareResult("Customer last name", event.lName, actEvent.lName);
		result &= MiscFunctions.compareResult("Customer first name", event.fName, actEvent.fName);
		result &= MiscFunctions.compareResult("Event location", event.location, actEvent.location);
		if(!result){
			throw new ErrorOnPageException("Event info of search result not correct. Please check log.");
		}logger.info("Event info of search result is correct.");
		
	}
	
	public void verifyEventColumnInfo(String colName,String expInfo){
		logger.info("Verify event column list info.");
		String actColName = colName;
		if(actColName.contains("First Name") || actColName.contains("Last Name")){
			actColName = "Billing Customer";
		}
		List<String> actListValues = this.getEventListTableColumnListValue(actColName);
		
		boolean result = true;
		for(int i=0;i<actListValues.size();i++){
			String act = actListValues.get(i);
			if(colName.contains("First Name")){
				act = act.split(",")[1];
			}else if(colName.contains("Last Name")){
				act = act.split(",")[0];
			}
			result &= MiscFunctions.compareResult(colName + " column list info", expInfo, act);
		}
		
		if(!result){
			throw new ErrorOnPageException("Event column info not correct, please check log.");
		}else logger.info("Event column info is correct.");
	}
	
	public void verifyEventInfoIsExists(String eventID, boolean isExists){
		IHtmlObject[] objs = this.getEventListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.findRow(0, eventID);
		boolean isExists_act = true;
		if(row<1){
			isExists_act = false;
		}else isExists_act = true;
		
		Browser.unregister(objs);
		
		boolean result = MiscFunctions.compareResult("Event Info is Exists", isExists, isExists_act);
		if(!result){
			throw new ErrorOnPageException("Event info exists not correct, please check log.");
		}else logger.info("Event info exists is correct.");
	}
	
	public void setSlipName(String slipName){
		browser.setTextField(".id", "ResToChargeSearchCriteria.siteName", slipName);
	}
	
	public void setSlipResNumber(String slipResNum){
		browser.setTextField(".id", "ResToChargeSearchCriteria.reservationNumber", slipResNum);
	}
	
	public void setMarina(String marina){
		browser.setTextField(".id", "ResToChargeSearchCriteria.locationName", marina);
	}
	
	public void setPrimaryOccuLastName(String lastName){
		browser.setTextField(".id", "ResToChargeSearchCriteria.primaryOccName", lastName);
	}
	
	public void setPrimaryOccuPhone(String phone){
		browser.setTextField(".id", "ResToChargeSearchCriteria.primaryOccPhone", phone);
	}
	
	public void searchSlipReservation(SlipReservationInfo slipRes){
		if(null != slipRes.getSlip().getName()){
			this.setSlipName(slipRes.getSlip().getName());
		}
		
		if(null != slipRes.getSlip().getMarina()){
			this.setMarina(slipRes.getSlip().getMarina());
		}
		
		if(null != slipRes.reservNum){
			this.setSlipResNumber(slipRes.reservNum);
		}
		
		if(null != slipRes.primaryOccupantLastName){
			this.setPrimaryOccuLastName(slipRes.primaryOccupantLastName);
		}
		
		if(null != slipRes.primaryOccupantPhone){
			this.setPrimaryOccuPhone(slipRes.primaryOccupantPhone);
		}
		
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void verifySlipResColumnInfo(String colName,String expInfo){
		logger.info("Verify Slip Reservation column list info.");
		String actColName = colName;
		if(actColName.contains("Primary Occupant First Name") || actColName.contains("Primary Occupant Last Name")){
			actColName = "Primary Occupant";
		}else if(actColName.contains("Customer First Name") || actColName.contains("Customer Last Name")){
			actColName = "Billing Customer";
		}
		List<String> actListValues = this.getSlipResListTableColumnListValue(actColName);
		
		boolean result = true;
		for(int i=0;i<actListValues.size();i++){
			String act = actListValues.get(i);
			if(colName.contains("First Name")){
				act = act.split(",")[1];
			}else if(colName.contains("Last Name")){
				act = act.split(",")[0];
			}
			result &= MiscFunctions.compareResult(colName + " column list info", expInfo, act);
		}
		
		if(!result){
			throw new ErrorOnPageException("Slip Reservation column info not correct, please check log.");
		}else logger.info("Slip Reservation column info is correct.");
	}
	
	public void compareSlipResInfoOfSearchResult(SlipReservationInfo slipRes){
		SlipReservationInfo actSlipRes = this.getSlipResInfoOfSearchResult(slipRes.reservNum);
		
		logger.info("Compare slip reservation info of search result.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Slip # (Name)", slipRes.getSlip().getName(), actSlipRes.getSlip().getName());
		result &= MiscFunctions.compareResult("Slip Reservation Arrival Date", slipRes.getSlip().getArrivalDate(), actSlipRes.getSlip().getArrivalDate());
		result &= MiscFunctions.compareResult("Slip Reservation Departure Date", slipRes.getSlip().getDepartureDate(), actSlipRes.getSlip().getDepartureDate());
		result &= MiscFunctions.compareResult("Primary Occupant First Name", slipRes.primaryOccupantFirstName, actSlipRes.primaryOccupantFirstName);
		result &= MiscFunctions.compareResult("Primary Occupant Last Name", slipRes.primaryOccupantLastName, actSlipRes.primaryOccupantLastName);
		result &= MiscFunctions.compareResult("Order Status", slipRes.orderStatus, actSlipRes.orderStatus);
		result &= MiscFunctions.compareResult("Billing Custoemr First Name", slipRes.customer.fName, actSlipRes.customer.fName);
		result &= MiscFunctions.compareResult("Billing Custoemr Last Name", slipRes.customer.lName, actSlipRes.customer.lName);
		result &= MiscFunctions.compareResult("Event ID", slipRes.eventID, actSlipRes.eventID);
		result &= MiscFunctions.compareResult("Event Name", slipRes.eventName, actSlipRes.eventName);
		result &= MiscFunctions.compareResult("Event Start Date", slipRes.eventStartDate, actSlipRes.eventStartDate);
		result &= MiscFunctions.compareResult("Event End Date", slipRes.eventEndDate, actSlipRes.eventEndDate);
		if(!result){
			throw new ErrorOnPageException("Slip Reservation info of search result not correct. Please check log.");
		}logger.info("Slip Reservation info of search result is correct.");
		
	}
	
	public void verifySlipResInfoIsExists(String slipResNum, boolean isExists){
		IHtmlObject[] objs = this.getSlipResListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.findRow(2, slipResNum);
		boolean isExists_act = true;
		if(row<1){
			isExists_act = false;
		}else isExists_act = true;
		
		Browser.unregister(objs);
		
		boolean result = MiscFunctions.compareResult("Slip Reservation Info is Exists", isExists, isExists_act);
		if(!result){
			throw new ErrorOnPageException("Slip Reservation info exists not correct, please check log.");
		}else logger.info("Slip Reservation info exists is correct.");
	}

}
