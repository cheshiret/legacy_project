
package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jchen
 */
public class UwpCurrentResListPage extends UwpPage {
	private static UwpCurrentResListPage _instance = null;

	public static UwpCurrentResListPage getInstance() {
		if (null == _instance)
			_instance = new UwpCurrentResListPage();

		return _instance;
	}

	public UwpCurrentResListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("[currentReservations|Current Reservations]",false),".className","accountside in");
	}

	private Property[] currentReservations(){
		return Property.concatPropertyArray(table(), ".id","Current Reservations");
	}

	private Property[] contentArea(){
		return Property.toPropertyArray(".id", "contentArea");
	}

	private Property[] pastReservation(){
		return Property.concatPropertyArray(a(), ".href", "/reservation.do?mode=past");
	}

	private Property[] marinaLocation(String contractCode, String marinaId){
		return Property.concatPropertyArray(a(), ".href", new RegularExpression(".*/marinaDetails\\.do\\?contractCode="+contractCode+"&parkId="+marinaId, false));
	}

	private Property[] slipAndDock(String contractCode, String slipId, String marinaId){
		return Property.concatPropertyArray(a(), ".href", "/slipSiteDetails.do?contractCode="+contractCode+"&siteId="+slipId+"&parkId="+marinaId);
	}

	private Property[] resultTotal(){
		return Property.concatPropertyArray(span(), ".id", "resulttotal");
	}

	private Property[] pageResults(){
		return Property.concatPropertyArray(span(), ".className", "pageresults");
	}

	private Property[] noRes(){
		return Property.concatPropertyArray(td(), ".text", "There are no reservations.");
	}

	private Property[] nextLink(){
		return Property.concatPropertyArray(a(), ".id", "resultNext", ".href", new RegularExpression("/reservation\\.do\\?mode=current&go=next&page=\\d+", false));
	}

	private Property[] previousLink(){
		return Property.concatPropertyArray(a(), ".id", "resultPrevious", ".href", new RegularExpression("/reservation\\.do\\?mode=current&go=prev&page=\\d+", false));
	}
	
	private Property[] slipResStatus(String orderNum, String contractCode, String resStatus){
		return Property.concatPropertyArray(td(), ".text", new RegularExpression("^"+orderNum+" ?"+contractCode+" ?"+resStatus+".*", false));
	}
	
	private Property[] slipResRecord(String orderNum, String contractCode, String resStatus){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^"+orderNum+" ?"+contractCode+" ?"+resStatus+".*", false));
	}
	
	public Property[] totalFeeBalanceDue(){
		return Property.concatPropertyArray(td(), ".text", new RegularExpression("\\$\\d+\\.\\d+ ?: ?\\$\\d+\\.\\d+", false));
	}

	/**
	 * Go to reservation details page by reservation ID and contract.
	 * @param resv - reservation ID
	 * @param contract - contract
	 */
	public void gotoResDetails(String resv, String contract)
			throws ItemNotFoundException, PageNotFoundException {
		RegularExpression reg = new RegularExpression("(.*reservationNumber="
				+ resv + "&contractCode=" + contract + "(&)?.*)|(.*contractCode=" + contract + "&reservationNumber="
				+ resv + ".*)", false);
		browser.clickGuiObject(".text", "See Detail", ".href", reg, true);
	}

	/**
	 * Go to tour reservation details page by reservation ID and contract.
	 * @param resId - reservation ID
	 * @param contract - contract
	 */
	public void gotoTourOrderDetails(String resId, String contract) {
		RegularExpression reg = new RegularExpression(".*contractCode="
				+ contract + "&reservationNumber=" + resId + ".*", false);
		browser.clickGuiObject(".text", "See Detail", ".href", reg);
	}

	/**
	 * Go to "Facility Details" via clicking Location Name in "Current Reservations" page
	 * @param contractCode
	 * @param parkID
	 */
	public void clickLocationViaContractCodeAndParkID(String contractCode, String parkID){
//		RegularExpression regx = new RegularExpression(".*campgroundDetails\\.do\\?parkId="+parkID+"&contractCode="+contractCode+".*", false);
		RegularExpression regx = new RegularExpression(".*campgroundDetails\\.do\\?contractCode="+contractCode+"&parkId="+parkID+".*", false);//Sara[20140220] the location of contract code and park id are changed 
		browser.clickGuiObject(".class" ,"Html.A", ".href", regx);
	}

	/**
	 * Verify the special reservation exists
	 * @param resv - reservation ID
	 * @param contract - contract
	 */
	public void verifyStatus(String resv, String contract)
			throws ItemNotFoundException {
		verifyStatus(resv, contract, null);
	}

	/**
	 * Verify the special reservation with given status exists.
	 * @param resv - reservation ID
	 * @param contract - contract
	 * @param status - order status
	 */
	public void verifyStatus(String resv, String contract, String status) throws ItemNotFoundException {
		IHtmlObject[] objs = browser.getTableTestObject(currentReservations());

		for (int i = 0; i < ((IHtmlTable)objs[0]).rowCount(); i++) {
			for (int j = 0; j < ((IHtmlTable)objs[0]).columnCount(); j++) {
				if (((IHtmlTable)objs[0]).getCellValue(i, j) != null) {
					String str = ((IHtmlTable)objs[0]).getCellValue(i, j).toUpperCase();
					boolean match=str.indexOf(resv.toUpperCase())>=0 && str.indexOf(contract.toUpperCase())>=0;
					//					System.out.println(str+" matched="+match);

					if (status != null && status.length() > 0) {
						match = match && str.indexOf(status.toUpperCase())>=0;
					} 

					if(match) {
						logger.info("Verify: Current Reservation Page could be access successfully!");
						return;
					}
				}
			}
		}

		Browser.unregister(objs);
		logger.info("Failed to verify the status " + status	+ " for reservation " + resv + " for contract " + contract);
		throw new ItemNotFoundException("Failed to verify the status " + status	+ " for reservation " + resv + " for contract " + contract);
	}
	/**
	 * Go to park list page.
	 */
	public void gotoCampingList() throws PageNotFoundException,
	ItemNotFoundException {
		browser.clickGuiObject(".id","Facility Reservations");
	}
	/**
	 * Go to tour list page.
	 */
	public void gotoTourList() throws PageNotFoundException,
	ItemNotFoundException {
		browser.clickGuiObject(".id", "Tour Reservations");
	}

	protected Property[] currentListTable() {
		return Property.toPropertyArray(".id", "Current Reservations");
	}
	/**
	 * Verify whether or not the order exists in Current Reservations page.
	 * @param resNum - reservation number
	 * @return - true - order exists; false - order not found
	 */
	public boolean verifyOrderexists(String resNum){
		IHtmlObject[] objs = browser.getTableTestObject(currentListTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean toReturn = false;

		String cellValue = "";
		int count = 0;
		for(int i=0; i<table.rowCount(); i++) {
			cellValue = table.getCellValue(i, 0);
			count++;
			if(cellValue.indexOf(resNum)!=-1) {
				toReturn = true;
				break;
			}
		}

		if(count==table.rowCount()+1) {
			throw new ItemNotFoundException("Order "+resNum+" not found!");
		}

		Browser.unregister(objs);
		return toReturn;
	}

	public void verifyOrderExists(String resNum, boolean exp) {
		boolean actual = this.verifyOrderexists(resNum);
		if (exp != actual) {
			throw new ErrorOnPageException("Order with num=" + resNum + " exsitence is wrong on current reservation list page!", exp, actual);
		}
		logger.info("Order with num=" + resNum + " exsitence is correct on current reservation list page as " + exp);
	}
	
	/**
	 * Verify reservation table exist on the page.
	 */
	public void verifyReservationsTableExist(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Current Reservations",".className","items");
		if(objs == null || objs.length < 1){
			throw new ErrorOnPageException("There is no Reservations table on the page!");
		}else{
			logger.info("verify current reservations page display successful.");
		}
	}

	public String getContentArea(){
		return browser.getObjectText(contentArea());
	}

	/**
	 * This link in current reservations page
	 */
	public void clickPastReservationLink(){
		browser.clickGuiObject(pastReservation());
	}

	public List<String[]> getResInfoArray(){
		List<String[]> list = new ArrayList<String[]>();
		IHtmlObject[] objs = browser.getTableTestObject(currentReservations());
		String[] string = null;
		if(objs.length<1){
			throw new ObjectNotFoundException("Current reservations Table not found !");
		}

		IHtmlTable table=(IHtmlTable)objs[0];
		int rowNum=table.rowCount();
		int colNum=table.columnCount();

		for(int i=1;i<rowNum;i++){//i=1:table column names; i>=3:reservation records
			if(i!=2){
				string = new String[colNum];
				for(int j=0; j<colNum; j++){
					string[j] = table.getCellValue(i, j);
				}
				list.add(string);
			}

		}
		Browser.unregister(objs);
		return list;
	}

	public IHtmlObject[] currentResTable(){
		IHtmlObject[] objs = browser.getTableTestObject(currentReservations());
		if(objs.length<1){
			throw new ObjectNotFoundException("Current reservations Table not found !");
		}
		return objs;
	}

	public String getCurrentResTableContent(){
		return browser.getObjectText(currentReservations());
	}

	public List<String> getResInfo(){
		List<String> list = new ArrayList<String>();
		String string = StringUtil.EMPTY;

		IHtmlObject[] objs = currentResTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowNum=table.rowCount();
		int colNum=table.columnCount();

		for(int i=1;i<rowNum;i++){//i=1:table column names; i>=3:reservation records
			if(i!=2){
				for(int j=0; j<colNum; j++){
					string += table.getCellValue(i, j)+(j==colNum-1?"":"#");
				}
				list.add(string);
				string=StringUtil.EMPTY;
			}

		}
		Browser.unregister(objs);
		return list;
	}
	
	private List<String> getAllDepartureDateInFirstPg(){
		IHtmlObject[] objs = currentResTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		List<String> list = new ArrayList<String>();
		for(int i=3;i<table.rowCount();i++){//i=1:table column names; i>=3:reservation records
			list.add(RegularExpression.getMatches(table.getCellValue(i, 3), "[a-zA-Z]{3} [a-zA-Z]{3} \\d+ \\d+")[1]);
		}
		Browser.unregister(objs);
		return list;
	}
	
	public String getTheBigestDepatureDateInFirstPg(){
		List<String> allDepartureDateInFirstPg = getAllDepartureDateInFirstPg();
		String theBigestOne = StringUtil.EMPTY;
		for(int i=0; i<allDepartureDateInFirstPg.size()-1; i++){
			if(DateFunctions.compareDates(allDepartureDateInFirstPg.get(i), allDepartureDateInFirstPg.get(i+1))==1){
				theBigestOne = allDepartureDateInFirstPg.get(i);
			}else theBigestOne = allDepartureDateInFirstPg.get(i+1);
		}
		
		logger.info("The bigest departure date in current reservation list page is:"+theBigestOne);
		return theBigestOne;
	}
	
	public String getTotalFeeBalanceDue(String orderNum, String contractCode, String resStatus){
		return browser.getObjectText(Property.atList(slipResRecord(orderNum, contractCode, resStatus), totalFeeBalanceDue()));
		
	}
	
	public boolean isSlipResExisted(String orderNum, String contractCode, String resStatus){
		return browser.checkHtmlObjectExists(slipResStatus(orderNum, contractCode, resStatus));
	}

	public void clickMarinaLocation(String contractCode, String marinaId){
		browser.clickGuiObject(marinaLocation(contractCode, marinaId));
	}

	public void clickSlipAndDockLink(String contractCode, String slipId, String marinaId){
		browser.clickGuiObject(slipAndDock(contractCode, slipId, marinaId));
	}

	public int getResultTotal(){
		return Integer.parseInt(browser.getObjectText(resultTotal()));
	}

	public String getPageResults(){
		return browser.getObjectText(pageResults());
	}

	public boolean noResExisted(){
		return browser.checkHtmlObjectExists(noRes());
	}

	public boolean noResRecordsExisted(){
		IHtmlObject[] objs = currentResTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		boolean result = table.rowCount()>=3;
		Browser.unregister(objs);
		return result;
	}

	public void verifyNoRes(){
		if(!noResExisted() || !noResRecordsExisted()){
			throw new ErrorOnPageException("Should no reservation.");
		}
		logger.info("Successfully verify no any reservations.");
	}
	
	public boolean isNextDisabled(){
		return !browser.checkHtmlObjectExists(nextLink());
	}
	public void clickNext(){
		browser.clickGuiObject(nextLink());
	}
	public boolean isPreviousDisabled(){
		return !browser.checkHtmlObjectExists(previousLink());
	}
	public void clickPrevious(){
		browser.clickGuiObject(previousLink());
	}

	public void OperatePaging(boolean isNextPage){
		if(isNextPage){
			this.clickNext();
		}else this.clickPrevious();

		this.waitLoading();
	}

	public void gotoLastPage(){
		boolean nextDisabled = isNextDisabled();
		do{
			if(!nextDisabled){
				OperatePaging(true);
				nextDisabled = isNextDisabled();
			}
		}while(!nextDisabled);
	}
	
	public void gotoFirstPage(){
		boolean previousDisabled = isPreviousDisabled();
		do{
			if(!previousDisabled){
				OperatePaging(false);
				previousDisabled = isPreviousDisabled();
			}
		}while(!previousDisabled);
	}
	
	public boolean isLocationLinkExist(String loc) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", loc);
	}
	
	private List<String> getOrderInfo(String ordNum) {
		List<String> list = new ArrayList<String>();
//		String string = StringUtil.EMPTY;

		IHtmlObject[] objs = currentResTable();
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowIndex = table.findRow(0, new RegularExpression("^"+ordNum+".*", false));
		if (rowIndex < 0) {
			throw new ErrorOnPageException("Can't find the order number=" + ordNum);
		}
		for (int i = 0; i < table.columnCount(); i++) { // Reservation Number Status, Type, Location, Details, Total Fee:Balance Due
			list.add(table.getCellValue(rowIndex, i));
		}
		Browser.unregister(objs);
		return list;
	}
	
	/** Verify the ticket lottery order info in application list page. The order of the column should be:
	 * 0 - Application Number Status
	 * 1 - Type
	 * 2 - Location
	 * 3 - Details
	 * 4 - Total Fee:Balance Due
	 */
	public void verifyTicketLotteryOrderInfo(String ordNum, String status, String type, String location, String tour, String tourDate, String fee) {
		boolean result = true;
		List<String> values = this.getOrderInfo(ordNum);
		tourDate = DateFunctions.formatDate(tourDate, "EEE MMM dd yyyy");
		result &= MiscFunctions.containString("Order Status", values.get(0), status);
		result &= MiscFunctions.compareString("Type", type, values.get(1));
		result &= MiscFunctions.compareString("Location", location, values.get(2));
		result &= MiscFunctions.compareResult("Location link exist", false, this.isLocationLinkExist(location));
		result &= MiscFunctions.compareString("Tour and tour date", tour + " " + tourDate, values.get(3));
		result &= MiscFunctions.compareString("Total fee and balance due", fee.replaceAll(" ", ""), values.get(4).replaceAll(" ", ""));
		
		if (!result) {
			throw new ErrorOnPageException("The order " + ordNum + " is displayed wrongly in Current Reservation list page!");
		}
		logger.info("The order " + ordNum + " is displayed correctly in Current Reservation list page!");
	}
}
