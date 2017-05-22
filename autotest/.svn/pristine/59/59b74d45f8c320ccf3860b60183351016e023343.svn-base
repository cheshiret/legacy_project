package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UwpPastResListPage  extends UwpPage {
	private static UwpPastResListPage _instance = null;

	public static UwpPastResListPage getInstance() {
		if (null == _instance)
			_instance = new UwpPastResListPage();

		return _instance;
	}

	public UwpPastResListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(p|P)astReservations",false),".className",new RegularExpression("items|accountside in", false));//"accountside in");
	}
	
	private Property[] currentReservation(){
		return Property.concatPropertyArray(a(), ".href", "/reservation.do?mode=current");
	}
	
	private Property[] pastReservations(){
		return Property.concatPropertyArray(table(), ".id", "Past Reservations");
	}
	
	private Property[] contentArea(){
		return Property.toPropertyArray(".id", "contentArea");
	}
	
	private Property[] marinaLocation(String contractCode, String marinaId){
		return Property.concatPropertyArray(a(), ".href", new RegularExpression(".*/marinaDetails\\.do\\?contractCode="+contractCode+"&parkId="+marinaId, false));
	}
	
	private Property[] slipAndDock(String contractCode, String slipId, String marinaId){
		return Property.concatPropertyArray(a(), ".href", "/slipSiteDetails.do?contractCode="+contractCode+"&siteId="+slipId+"&parkId="+marinaId);
	}
	
	/**
	 * Go to reservation details page by reservation ID and contract.
	 * @param resv - reservation ID
	 * @param contract - contract
	 */
	public void gotoResDetails(String resv, String contract)
			throws ItemNotFoundException, PageNotFoundException {
		RegularExpression reg = new RegularExpression("(.*reservationNumber="
				+ resv + "&contractCode=" + contract + "(&)?.*)|(.*contractCode="
+ contract + "&reservationNumber=" + resv + ".*)", false);
		browser.clickGuiObject(".text", "See Detail", ".href", reg);
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
		IHtmlObject[] objs = browser.getTableTestObject(".id","Past Reservations");
		
		for (int i = 0; i < ((IHtmlTable)objs[0]).rowCount(); i++) {
			for (int j = 0; j < ((IHtmlTable)objs[0]).columnCount(); j++) {
				if (((IHtmlTable)objs[0]).getCellValue(i, j) != null) {
					String str = ((IHtmlTable)objs[0]).getCellValue(i, j).toUpperCase();
					boolean match=str.indexOf(resv.toUpperCase())>=0 && str.indexOf(contract.toUpperCase())>=0;

					if (status != null && status.length() > 0) {
						match = match && str.indexOf(status.toUpperCase())>=0;
					} 
					
					if(match) {
						return;
					}
				}
			}
		}

		Browser.unregister(objs);
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
	
	protected Property[] resListTable() {
		return Property.toPropertyArray(".id", "Past Reservations");
	}
	/**
	 * Verify whether or not the order exists in Past Reservations page.
	 * @param resNum - reservation number
	 * @return - true - order exists; false - order not found
	 */
	public boolean verifyOrderexists(String resNum){
//		IHtmlObject[] objs = browser.getTableTestObject(".id", "Past Reservations",".className","items");
		IHtmlObject[] objs = browser.getTableTestObject(resListTable());
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
			throw new ErrorOnPageException("Order with num=" + resNum + " exsitence is wrong on past reservation list page!", exp, actual);
		}
		logger.info("Order with num=" + resNum + " exsitence is correct  on past reservation list page as " + exp);
	}
	
	/**
	 * Verify whether or not this is Past Reservations page.
	 * @return true - found page, false - not found
	 */
	public boolean isPastReservationsPg() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "Past Reservations",".className","items");
		Browser.unregister(objs);
		if (objs.length == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * This link in past reservations page
	 */
	public void clickCurrentReservationLink(){
		browser.clickGuiObject(currentReservation());
	}
	
	public String getContentArea(){
		return browser.getObjectText(contentArea());
	}
	
	public List<String[]> getResInfoArray(){
		List<String[]> list = new ArrayList<String[]>();
		IHtmlObject[] objs = browser.getTableTestObject(pastReservations());
		String[] string = null;
		if(objs.length<1){
			throw new ObjectNotFoundException("Past reservations Table not found !");
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
	
	public List<String> getResInfo(){
		List<String> list = new ArrayList<String>();
		String string = StringUtil.EMPTY;
		
		IHtmlObject[] objs = browser.getTableTestObject(pastReservations());
		if(objs.length<1){
			throw new ObjectNotFoundException("Past reservations Table not found !");
		}
		
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
	
	public void clickMarinaLocation(String contractCode, String marinaId){
		browser.clickGuiObject(marinaLocation(contractCode, marinaId));
	}
	
	public void clickSlipAndDockLink(String contractCode, String slipId, String marinaId){
		browser.clickGuiObject(slipAndDock(contractCode, slipId, marinaId));
	}
}
