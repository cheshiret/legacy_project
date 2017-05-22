package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInventoryInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * This page is the slip inventory list page, how to go to the page:Select 'Inventory' at the drop down list on the top of facility detail page.
 * @author Phoebe
 * @Date  Feb 25, 2013
 */
public class InvMgrSlipInventoryListPage extends InvMgrCommonTopMenuPage{
	private static InvMgrSlipInventoryListPage _instance = null;
	
	public static InvMgrSlipInventoryListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrSlipInventoryListPage();
		}

		return _instance;
	}

	protected InvMgrSlipInventoryListPage() {
	}
	
	private String prefix = "SlipUsedInventorySearchCriteria.";
	
	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		//use Go button as pageMark
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id","slipUsedInvGrid_LIST");
	}
	
	/***
	 * Input Arrival Date
	 * @param date
	 */
	public void setArrivalDate(String date) {
		browser.setTextField(".id", prefix + "arrivalDate_ForDisplay", date);
	}

	/**Input Slip Number*/
	public void setSlipNumber(String slipNum) {
		browser.setTextField(".id", prefix + "slipNumber", slipNum);
	}

	/**Input Res Number*/
	public void setResNumber(String resNum) {
		browser.setTextField(".id", prefix + "reservationNumber", resNum);
	}

	/**
	 * Input Closure ID
	 * @param id
	 */
	public void setClosureID(String id) {
		browser.setTextField(".id", prefix + "closureID", id);
	}

	/**
	 * Select Inventory Statuss
	 * @param status
	 */
	public void selectInventoryStatus(String status) {
		browser.selectDropdownList(".id", prefix + "inventoryStatus", status);
	}

	/**
	 * Input from Date
	 * @param date
	 */
	public void setFromDate(String date) {
		browser.setCalendarField(".id",prefix + "from_ForDisplay", date);
//		browser.setTextField(".id", prefix + "from_ForDisplay", date);
	}

	/**
	 * Input To Date
	 * @param date
	 */
	public String setToDate(String date) {
		if(StringUtil.notEmpty(date)){
			browser.setCalendarField(".id",prefix + "to_ForDisplay", date);
			new Thread(){//to handle alert dialog popped up for check invalid report date
				ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();
				
				public void run(){
					int i=0;
					alertPg.setDismissible(false);
					while(i<2){
						if(alertPg.exists()){
							TestProperty.putProperty("msg", alertPg.text());
							alertPg.dismiss();
							break;
						}
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							logger.warn(e.getMessage());
						}
						i++;
					}
				};
			}.start();
		}
		browser.waitExists(OrmsConstants.SLEEP_ONE_MINUTE_BEFORE_CHECK);
		String alertMsg = TestProperty.getProperty("msg",null);
		TestProperty.putProperty("msg","");//reset msg to null
		return alertMsg;
	}

	/**
	 * Select Slip Type
	 * @param type
	 */
	public void selectSlipType(String type) {
		browser.selectDropdownList(".id", prefix + "slipType", type);
	}
	
	/**
	 * Select slip relation type
	 * @param type
	 */
	public void selectSlipRelationType(String type){
		browser.selectDropdownList(".id", prefix + "slipRelationType", type);
	}
	
	/**Click GO button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * This method search inventory by reservation number
	 * @param resNum
	 */
	public void searchInventory(String arrDate, String slipCode, String resNum, String closureId, String status, 
			String from,  String to, String slipType, String slipRelationType){
		if(StringUtil.notEmpty(arrDate)){
			this.setArrivalDate(arrDate);
		}else{
			this.setArrivalDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(slipCode)){
			this.setSlipNumber(slipCode);
		}else{
			this.setSlipNumber(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(resNum)){
			this.setResNumber(resNum);
		}else{
			this.setResNumber(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(closureId)){
			this.setClosureID(closureId);
		}else{
			this.setClosureID(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(status)){
			this.selectInventoryStatus(status);
		}else{
			browser.selectDropdownList(".id", prefix + "inventoryStatus", 0);
		}
		
		if(StringUtil.notEmpty(from)){
			this.setFromDate(from);
		}else{
			this.setFromDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(to)){
			this.setToDate(to);
		}else{
			this.setToDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(slipType)){
			this.selectSlipType(slipType);
		}else{
			browser.selectDropdownList(".id", "search_site_type", 0);
		}
		
		if(StringUtil.notEmpty(slipRelationType)){
			this.selectSlipRelationType(slipRelationType);
		}else{
			browser.selectDropdownList(".id", prefix + "slipRelationType", 0);
		}
		
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchSlipInventoryByInvStatus(String status){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY,
				StringUtil.EMPTY, StringUtil.EMPTY, status,  
				StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY);
	}
	
	public void searchSlipInventoryByArrDate(String date){
		this.searchInventory(date, StringUtil.EMPTY,
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY,  
				StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY);
	}
	
	public void searchSlipInventoryBySlipCode(String slipCode){
		this.searchInventory(StringUtil.EMPTY, slipCode,
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY,  
				StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY);
	}
	
	public void searchSlipInventoryBySlipRelationType(String slipRelationType){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY,
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY,  
				StringUtil.EMPTY,  StringUtil.EMPTY,  StringUtil.EMPTY,  slipRelationType);
	}
	
	public void searchSlipInventoryBySlipType(String slipType){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY,
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY,  
				StringUtil.EMPTY,  StringUtil.EMPTY,  slipType,  StringUtil.EMPTY);
	}
	
	public void searchSlipInventoryByResNum(String resNum){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, resNum, 
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
	}
	
	public void searchRevInventoryWithSpecialStatus(String resNum, String status){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, resNum, 
				StringUtil.EMPTY, status, StringUtil.EMPTY, 
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
	}
	
	public void searchSlipInvForSlip(String slipCode, String from, String to){
		this.searchInventory(StringUtil.EMPTY, slipCode, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
	}
	
	public void searchSlipInvForSlipWithStatus(String slipCode, String from, String to, String status){
		this.searchInventory(StringUtil.EMPTY, slipCode, StringUtil.EMPTY, StringUtil.EMPTY, status, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
	}
	
	public void searchSlipInvWithClosureId(String closureId){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, closureId, StringUtil.EMPTY, 
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
	}
	
	private String getStatusForTheFirstInventoryRecord(){
		IHtmlObject[] comboTable = browser.getTableTestObject(".id","slipUsedInvGrid_LIST");
	    IHtmlTable comboTableGrid = (IHtmlTable) comboTable[0];
	    String status = comboTableGrid.getCellValue(1,2);
	    Browser.unregister(comboTable);
	    return status;
	}
	
	/**
	 * This method get the status for a special reservation
	 * @param resNum
	 * @return
	 */
	public String getReservationStatus(String resNum){
		this.searchSlipInventoryByResNum(resNum);
	    return getStatusForTheFirstInventoryRecord();
	}
	
	public String getReservationStatus(String resNum, String from, String to){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, resNum, StringUtil.EMPTY, StringUtil.EMPTY, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
		return getStatusForTheFirstInventoryRecord();
	}
	
	/**
	 * This method get the status for a special reservation
	 * @param resNum
	 * @return
	 */
	public String getInventoryIdByResNum(String resNum){
		this.searchSlipInventoryByResNum(resNum);
	    return this.getFirstInvID();
	}
	
	public String getInventoryIdByResNumForSpecialDatePeriod(String resNum, String from, String to){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, resNum, StringUtil.EMPTY, StringUtil.EMPTY, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
		return this.getFirstInvID();
	}
	
	/**
	 * This method get the status for a special slip in special date period
	 * @param slipCode
	 * @param from
	 * @param to
	 * @return
	 */
	public String getSlipStatusInSpecDateRange(String slipCode, String from, String to){
		this.searchInventory(StringUtil.EMPTY, slipCode, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
		 return getStatusForTheFirstInventoryRecord();
	}
	
	public void verifyNoInventoryForSlip(String slipCode, String from, String to){
		this.searchInventory(StringUtil.EMPTY, slipCode, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> invList = this.getInvID();
		if(invList.size() > 0 ){
			throw new ErrorOnPageException("Slip:" + slipCode  + " has inventory from " + from + " to " + to);
		} 
		logger.info("The related record doesn't exist, there is no inventory for slip:" + slipCode + " from " + from + " to " + to);
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".class", "Html.DIV", ".id", new RegularExpression("NOTSET", false));
	}
	
	public void searchSlipInventory(SlipInventoryInfo slipInvInfo){
		if(null != slipInvInfo.getArrivalDate()){
			this.setArrivalDate(slipInvInfo.getArrivalDate());
		}
		if(null != slipInvInfo.getSlipNum()){
			this.setSlipNumber(slipInvInfo.getSlipNum());
		}
		if(null != slipInvInfo.getResNum()){
			this.setResNumber(slipInvInfo.getResNum());
		}
		if(null != slipInvInfo.getClosureID()){
			this.setClosureID(slipInvInfo.getClosureID());
		}
		if(null != slipInvInfo.getInvStatus()){
			this.selectInventoryStatus(slipInvInfo.getInvStatus());
		}
		if(null != slipInvInfo.getFromDate()){
			this.setFromDate(slipInvInfo.getFromDate());
		}
		if(null != slipInvInfo.getToDate()){
			this.setToDate(slipInvInfo.getToDate());
		}
		if(null != slipInvInfo.getSlipType()){
			this.selectSlipType(slipInvInfo.getSlipType());
		}
		if(null != slipInvInfo.getSlipRelationType()){
			this.selectSlipRelationType(slipInvInfo.getSlipRelationType());
		}

		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getTableObject(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("slipUsedInvGrid_LIST", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find any slip inventory list records.");
		}
		
		return objs;
	}

	private List<String> getColumnValueByName(String columnName){
		IHtmlObject objs[] = this.getTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int col = table.findColumn(0, columnName);
		if(col < 0){
			throw new ItemNotFoundException("Can't find column by given column name:"+columnName);
		}
		List<String> colList = table.getColumnValues(col);
		if(null == colList){
			throw new ItemNotFoundException("There isn't any record for cloumn "+columnName);
		}
		colList.remove(0);// remove title
		Browser.unregister(objs);
		return colList;
	}
	
	private List<String> getFirstInventoryInfo(){
		IHtmlObject objs[] = this.getTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		List<String> info = table.getRowValues(1);
		Browser.unregister(objs);
		return info;
	}
	
	public String getFirstInvID(){
		return this.getColumnValueByName("Inventory ID").get(0);
	}
	
	public List<String> getInvID(){
		return this.getColumnValueByName("Inventory ID");
	}
	
	public List<String> getClosureIDList(){
		return this.getColumnValueByName("Closure ID");
	}
	
	public List<String> getNssParent(){
		return this.getColumnValueByName("NSS Parent");
	}
	
	public List<String> getBookingID(){
		return this.getColumnValueByName("Booking ID");
	}
	
	public List<String> getRecordByInvID(String ID){
		IHtmlObject objs[] = this.getTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int row = table.findRow(0, ID);
		if(row < 0){
			throw new ItemNotFoundException("Can't find record by given inventory ID:"+ID);
		}
		List<String> rowList = table.getRowValues(row);
		Browser.unregister(objs);
		return rowList;
	}
	
	public String getDefaultValueOfInventoryStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression("SlipUsedInventorySearchCriteria.inventoryStatus", false));
	}
	
	public String getDefaultValueOfSlipType(){
		return browser.getDropdownListValue(".id", new RegularExpression("SlipUsedInventorySearchCriteria.slipType", false));
	}
	
	public String getDefaultValueOfSlipRelationType(){
		return browser.getDropdownListValue(".id", new RegularExpression("SlipUsedInventorySearchCriteria.slipRelationType", false));
	}
	
	public List<String> getInventoryStatusDropDownListValue(){
		return browser.getDropdownElements(".id", new RegularExpression("SlipUsedInventorySearchCriteria.inventoryStatus", false));
	}
	
	public List<String> getSlipTypeDropDownListValue(){
		return browser.getDropdownElements(".id", new RegularExpression("SlipUsedInventorySearchCriteria.slipType", false));
	}
	
	public List<String> getSlipRelationTypeDropDownListValue(){
		return browser.getDropdownElements(".id", new RegularExpression("SlipUsedInventorySearchCriteria.slipRelationType", false));
	}

	public void clickInventoryID(String invID){
		browser.clickGuiObject(".class", "Html.A", ".text", invID);
	}
	
	public List<SlipInventoryInfo> getSlipInvList(){
		List<SlipInventoryInfo> slipInvList = new ArrayList<SlipInventoryInfo>();
		SlipInventoryInfo slipInvInfo;
		IHtmlObject objs[] = this.getTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		for(int i=1; i<table.rowCount(); i++){
			slipInvInfo = new SlipInventoryInfo();
			slipInvInfo.setInvID(table.getCellValue(i, 0));
			slipInvInfo.setInvDateTime(table.getCellValue(i, 1));
			slipInvInfo.setInvStatus(table.getCellValue(i, 2));
			slipInvInfo.setSlipNum(table.getCellValue(i, 3));
			slipInvInfo.setSlipName(table.getCellValue(i, 4));
			slipInvInfo.setNssParent(table.getCellValue(i, 5));
			slipInvInfo.setDockArea(table.getCellValue(i, 6));
			slipInvInfo.setSlipType(table.getCellValue(i, 7));
			slipInvInfo.setStartDate(table.getCellValue(i, 8));
			slipInvInfo.setEndDate(table.getCellValue(i, 9));
			slipInvInfo.setBookingID(table.getCellValue(i, 10));
			slipInvInfo.setResNum(table.getCellValue(i, 11));
			slipInvInfo.setArrivalDate(table.getCellValue(i, 12));
			slipInvInfo.setDepartureDate(table.getCellValue(i, 13));
			slipInvInfo.setBoatLength(table.getCellValue(i, 14));
			slipInvInfo.setClosureID(table.getCellValue(i, 15));
			slipInvInfo.setUserName(table.getCellValue(i, 16));
			slipInvInfo.setUserLocation(table.getCellValue(i, 17));
			slipInvInfo.setSalesChannel(table.getCellValue(i, 18));
			slipInvList.add(slipInvInfo);
		}
		Browser.unregister(objs);
		return slipInvList;
	}
	
	public void verifyColumn(String columnName, String expectValue){
		List<String> columnList = this.getColumnValueByName(columnName);
		boolean result = true;
		for(String value:columnList){
			if(!MiscFunctions.compareResult(columnName, expectValue, value)){
				result = false;
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Column "+columnName+" should only contains "+expectValue);
		} else {
			logger.info(columnName+" Column value is correct!");
		}
	}
	
	public void verifySearchResult(HashMap<String, SlipInventoryInfo> exect){
		List<SlipInventoryInfo> actualList = this.getSlipInvList();
		SlipInventoryInfo expectSlipInv = new SlipInventoryInfo();

		if(MiscFunctions.compareResult("Size of search result list", exect.size(), actualList.size())){
			boolean result = true;
			for(int i=0; i<actualList.size(); i++){
				logger.info("---Verify No."+(i+1)+" record.");

				SlipInventoryInfo actualSlipInv = actualList.get(i);
				expectSlipInv = exect.get(actualList.get(i).getInvStatus());

				result &= MiscFunctions.compareResult("Status", expectSlipInv.getInvStatus(), actualSlipInv.getInvStatus());
				result &= MiscFunctions.compareResult("Slip Number", expectSlipInv.getSlipNum(), actualSlipInv.getSlipNum());
				result &= MiscFunctions.compareResult("Slip Name", expectSlipInv.getSlipName(), actualSlipInv.getSlipName());
				result &= MiscFunctions.compareResult("NSS Parent", expectSlipInv.getNssParent(), actualSlipInv.getNssParent());
				result &= MiscFunctions.compareResult("Dock/Area", expectSlipInv.getDockArea(), actualSlipInv.getDockArea());
				result &= MiscFunctions.compareResult("Slip Type", expectSlipInv.getSlipType(), actualSlipInv.getSlipType());
				result &= MiscFunctions.compareResult("Start Date", DateFunctions.formatDate(expectSlipInv.getStartDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getStartDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("End Date", DateFunctions.formatDate(expectSlipInv.getEndDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getEndDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("Booking ID", expectSlipInv.getBookingID(), actualSlipInv.getBookingID());
				result &= MiscFunctions.compareResult("Reservation Number", expectSlipInv.getResNum(), actualSlipInv.getResNum());
				result &= MiscFunctions.compareResult("Arrival Date",DateFunctions.formatDate(expectSlipInv.getArrivalDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getArrivalDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("Departure Date", DateFunctions.formatDate(expectSlipInv.getDepartureDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getDepartureDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("Boat Length", expectSlipInv.getBoatLength(), actualSlipInv.getBoatLength());
				result &= MiscFunctions.compareResult("Closure ID", expectSlipInv.getClosureID(), actualSlipInv.getClosureID());
				result &= MiscFunctions.compareResult("User Name", expectSlipInv.getUserName(), actualSlipInv.getUserName());
				result &= MiscFunctions.compareResult("User Location", expectSlipInv.getUserLocation(), actualSlipInv.getUserLocation());
				result &= MiscFunctions.compareResult("Sales Channel", expectSlipInv.getSalesChannel(), actualSlipInv.getSalesChannel());
				
			}
			if(!result){
				throw new ErrorOnPageException("---Check logs above.");
			}
		} else {
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public void verifySearchResult(List<SlipInventoryInfo> exectList){
		List<SlipInventoryInfo> actualList = this.getSlipInvList();
		
		if(MiscFunctions.compareResult("Size of search result list", exectList.size(), actualList.size())){
			boolean result = true;
			for(int i=0; i<actualList.size(); i++){
				logger.info("---Verify No."+(i+1)+" record.");
				SlipInventoryInfo actualSlipInv = actualList.get(i);
				SlipInventoryInfo expectSlipInv = exectList.get(i);

				result &= MiscFunctions.compareResult("Status", expectSlipInv.getInvStatus(), actualSlipInv.getInvStatus());
				result &= MiscFunctions.compareResult("Slip Number", expectSlipInv.getSlipNum(), actualSlipInv.getSlipNum());
				result &= MiscFunctions.compareResult("Slip Name", expectSlipInv.getSlipName(), actualSlipInv.getSlipName());
				result &= MiscFunctions.compareResult("NSS Parent", expectSlipInv.getNssParent(), actualSlipInv.getNssParent());
				result &= MiscFunctions.compareResult("Dock/Area", expectSlipInv.getDockArea(), actualSlipInv.getDockArea());
				result &= MiscFunctions.compareResult("Slip Type", expectSlipInv.getSlipType(), actualSlipInv.getSlipType());
				result &= MiscFunctions.compareResult("Start Date", DateFunctions.formatDate(expectSlipInv.getStartDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getStartDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("End Date", DateFunctions.formatDate(expectSlipInv.getEndDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getEndDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("Booking ID", expectSlipInv.getBookingID(), actualSlipInv.getBookingID());
				result &= MiscFunctions.compareResult("Reservation Number", expectSlipInv.getResNum(), actualSlipInv.getResNum());
				result &= MiscFunctions.compareResult("Arrival Date",DateFunctions.formatDate(expectSlipInv.getArrivalDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getArrivalDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("Departure Date", DateFunctions.formatDate(expectSlipInv.getDepartureDate(), "yyyy-MM-dd"), DateFunctions.formatDate(actualSlipInv.getDepartureDate(), "yyyy-MM-dd"));
				result &= MiscFunctions.compareResult("Boat Length", expectSlipInv.getBoatLength(), actualSlipInv.getBoatLength());
				result &= MiscFunctions.compareResult("Closure ID", expectSlipInv.getClosureID(), actualSlipInv.getClosureID());
				result &= MiscFunctions.compareResult("User Name", expectSlipInv.getUserName(), actualSlipInv.getUserName());
				result &= MiscFunctions.compareResult("User Location", expectSlipInv.getUserLocation(), actualSlipInv.getUserLocation());
				result &= MiscFunctions.compareResult("Sales Channel", expectSlipInv.getSalesChannel(), actualSlipInv.getSalesChannel());
			}
			if(!result){
				throw new ErrorOnPageException("---Check logs above.");
			}
		} else {
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public void searchSlipInventoryByStatusAndDateRange(String status, String fromDate, String toDate){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY,
				StringUtil.EMPTY, StringUtil.EMPTY, status,  
				fromDate,  toDate,  StringUtil.EMPTY,  StringUtil.EMPTY);
	}
	
	/**
	 * Start~End date range must be among the search criteria Start/End Range from ~ to
	 * @param fromDate
	 * @param toDate
	 */
	public void compareDateRange(String fromDate, String toDate){
		List<String> startList = this.getColumnValueByName("Start Date");
		List<String> endList = this.getColumnValueByName("End Date");
		
		boolean result = true;
		for(int i=0; i<startList.size(); i++){
			if((DateFunctions.compareDates(fromDate, endList.get(i)) > 0 || DateFunctions.compareDates(toDate, startList.get(i)) < 0)){
				logger.info("No. "+(i+1)+" record is not correct. Start/End date range must be among "+fromDate+"~"+toDate);
				result &= false;
			} else {
				result &= true;
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	public void verifyNoInventoryHoldForRev(String resNum){
		this.searchSlipInventoryByResNum(resNum);
		List<String> invList = this.getInvID();
		if(invList.size() > 0 ){
			throw new ErrorOnPageException("The inventory is not released!");
		} 
		logger.info("The related record doesn't exist, the inventory has been released!");
	}

	public void verifyInventoryForRevIsReleasedInSpecialDatePeriod(
			String ordNum, String fromDate, String toDate) {
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, ordNum, StringUtil.EMPTY, StringUtil.EMPTY, 
				fromDate, toDate, StringUtil.EMPTY, StringUtil.EMPTY);
		List<String> invList = this.getInvID();
		if(invList.size() > 0 ){
			throw new ErrorOnPageException("The inventory is not released!");
		} 
		logger.info("The related record doesn't exist, the inventory has been released!");
		
	}
	
//	public void verifySlipIsHold(String resNum, String BoatLength, String startDate, String endDate) {
//		boolean passed = true;
//		this.searchRevInventoryWithSpecialStatus(resNum, "Hold");
//		List<String> info = getFirstInventoryInfo();
//		if(info.size() != 1){
//			throw new ErrorOnPageException("Reservation " + resNum + " is not hold!");
//		}
//		passed &= MiscFunctions.compareResult("Boat Length", BoatLength, info.get(14));
//		passed &= MiscFunctions.compareResult("Start Date", startDate, info.get(8));
//		passed &= MiscFunctions.compareResult("End Date", endDate, info.get(9));
//		if(!passed){
//			throw new ErrorOnPageException("Hold Information for " + resNum + " is not correct! check the log above");
//		}
//		logger.info(resNum + " with correct hold information!" );
//	}
	
	public void verifyNoInventoryCreatedWithStatus(String resNum, String status){
		this.searchRevInventoryWithSpecialStatus(resNum, "Hold");
		List<String> invList = this.getInvID();
		if(invList.size() > 0 ){
			throw new ErrorOnPageException("Reservation:" + resNum + " has " + status + " inventory!");
		} 
		logger.info("The related record doesn't exist, there is no inventory for res:" + resNum + " is " + status);
	}
}
