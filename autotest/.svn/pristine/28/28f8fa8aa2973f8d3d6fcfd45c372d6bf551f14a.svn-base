/*
 * $Id: FldMgrPosSearchPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author jdu
 */
public class OrmsPOSSaleSearchPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrPosSearchPage
	 * Generated     : Feb 8, 2008 10:46:52 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/02/08
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPOSSaleSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPOSSaleSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPOSSaleSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsPOSSaleSearchPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",new RegularExpression("POS Sale (Search|Seach)/List",false));
	}

	/**
	 * Select Order Status
	 * @param status
	 */
	public void selectOrdStatus(String status) {
		browser.selectDropdownList(".id", "POSOrderSearchCriteria.ordStatus",status);
	}

	/**
	 * Input invoice number
	 * @param num
	 */
	public void setInvoiceNum(String num) {
		browser.setTextField(".id","POSOrderSearchCriteria.ordInvcNum:ZERO_TO_NULL", num);
	}

	/**
	 * Input order number
	 * @param num
	 */
	public void setOrdNum(String num) {
		browser.setTextField(".id", "POSOrderSearchCriteria.ordNum", num);
	}

	/**
	 * Input End Date
	 * @param date
	 */
	public void setEndDate(String date) {
		browser.setTextField(".id","POSOrderSearchCriteria.endDate_ForDisplay", date);
	}

	/**
	 * Input oper last name
	 * @param name
	 */
	public void setOperLName(String name) {
		browser.setTextField(".id", "POSOrderSearchCriteria.operatorLastName",name);
	}

	/**
	 * Input oper first name
	 * @param name
	 */
	public void setOperFName(String name) {
		browser.setTextField(".id", "POSOrderSearchCriteria.operatorFirstName",name);
	}

	/**
	 * Select sale location
	 * @param loc
	 */
	public void setSaleLoc(String loc) {
		browser.setTextField(".id", "POSOrderSearchCriteria.saleLoc", loc);
	}

	/**
	 * Input start date
	 * @param date
	 */
	public void setStartDate(String date) {
		browser.setTextField(".id","POSOrderSearchCriteria.startDate_ForDisplay", date);
	}

	/**
	 * Input receipt nubmer
	 * @param num
	 */
	public void setReceiptNum(String num) {
		browser.setTextField(".id","POSOrderSearchCriteria.rcptNum:ZERO_TO_NULL", num);
	}

	/**Check "Include area code"*/
	public void selectIncludeAreaCode() {
		browser.selectCheckBox(".id", "POSOrderSearchCriteria.includeAreaCode");
	}

	/**Unselect Include Area Code*/
	public void deSelectIncludeAreaCode() {
		browser.unSelectCheckBox(".id","POSOrderSearchCriteria.includeAreaCode");
	}

	/**Input customer phone*/
	public void setCustPhone(String phone) {
		browser.setTextField(".id", "POSOrderSearchCriteria.custPhone", phone);
	}

	/**
	 * Input customer last name
	 * @param lname
	 */
	public void setCustLName(String lname) {
		browser.setTextField(".id", "POSOrderSearchCriteria.custLastName",lname);
	}

	/**
	 * input customer first name
	 * @param fname
	 */
	public void setCustFName(String fname) {
		browser.setTextField(".id", "POSOrderSearchCriteria.custFirstName",fname);
	}

	/**
	 * Input customer email
	 * @param email
	 */
	public void setCustEmail(String email) {
		browser.setTextField(".id", "POSOrderSearchCriteria.custEmail", email);
	}

	/**Click Go button*/
	public void clickGo() {
		IHtmlObject[] obj = browser.getHtmlObject(".class","Html.DIV",".id", new RegularExpression("t_tSEARCHBAR_TAG|go",false));
		if(obj.length < 1){
			throw new ErrorOnDataException("Did not for div id as 't_tSEARCHBAR_TAG|go'");
		}
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false),true, 0, obj[0]);
	}

	/**
	 * Set event name
	 * @param event
	 */
	public void setEventName(String event) {
		browser.setTextField(".id", "POSOrderSearchCriteria.chargedEventName",event);
	}

	/**
	 * Input charge reservation number
	 * @param resNum
	 */
	public void setChargeResNum(String resNum) {
		browser.setTextField(".id", "POSOrderSearchCriteria.chargedResNum",resNum);
	}

	/**
	 * Input charge reservation site
	 * @param site
	 */
	public void setChargeResSite(String site) {
		browser.setTextField(".id","POSOrderSearchCriteria.chargedResSiteName", site);
	}

	/**
	 * Input event ID
	 * @param id
	 */
	public void setEventID(String id) {
		browser.setTextField(".id","POSOrderSearchCriteria.chargedEventID:ZERO_TO_NULL", id);
	}

	/**
	 * Search by POS num
	 * @param posNum
	 */
	public void searchByPOSNum(String posNum) {
		this.setOrdNum(posNum);
		this.clickGo();
	}
	
	public void searchByStatus(String status){
		this.selectOrdStatus(status);
		this.setSaleLoc("");
		this.clickGo();
	}

	/**
	 * 
	 * @param posNum
	 * @throws PageNotFoundException
	 */
	public void selectPOSOrder(String posNum) throws PageNotFoundException {
		browser.clickGuiObject(".class", "Html.A", ".text", posNum, true);
	}
	
	public void selectFirstPos()
	{
		browser.clickGuiObject(".class","Html.A",".text","Select");
	}
	
	/**
	 * get the length of chared to event ID
	 * @return
	 */
	public int getLenChargedToEventID(){
		 RegularExpression reg=new RegularExpression("POSOrderLightView.chargedEventID:ZERO_TO_NULL",false);
		 IHtmlObject[] obj=browser.getHtmlObject(".id",reg);
		 
		 return obj.length;
	}
	/**
	 * get the len of charged to res
	 * @return
	 */
	public int getLenChargedToRes(){
		 RegularExpression reg=new RegularExpression("POSOrderLightView.chargedResNum",false);
		 IHtmlObject[] obj=browser.getHtmlObject(".id",reg);
		 
		 return obj.length;
	}
	
	/**
	 * Retrieve all pos info in the pos search page
	 * @return
	 */
	public List<List<String>> retrivePOSinfo() {
		List<List<String>> posinfo = new ArrayList<List<String>>();
		List<String> posinforow = new ArrayList<String>();
		//OrmsPOSSaleSearchPage omPosSearchPage = OrmsPOSSaleSearchPage.getInstance();
		//int colnum;

		do {
			RegularExpression reg = new RegularExpression("^POS Sale # Invoice # Order Status*", false);
			IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);
			IHtmlTable reserveTableGrid = (IHtmlTable) reservetable[0];
			Browser.unregister(reservetable);
			for (int row = 1; row < reserveTableGrid.rowCount(); row++) {
			  posinforow = reserveTableGrid.getRowValues(row);//HTMLTable.getRowData(reserveTableGrid, row);
			  posinfo.add(posinforow);
			}
		} while (gotoNext());

		return posinfo;
	}
	
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			//MiscFunctions.clickObject(objs[0]);
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
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
	
	/**Get specific column's number*/
	public int getColNum(String colname) {
		List<?> reservinforow = (List<?>)this.retriveColumnName().get(0);
		int colcount = getTableColCount();

		for (int col = 0; col < colcount; col++) {
			if (reservinforow.get(col).toString().trim().equalsIgnoreCase(colname)) {
				return col;
			}
		}

		return -1;
	}
	/**Retrive the column information in the reservation search page */
	public List<List<String>> retriveColumnName() {
		List<List<String>> posinforow = new ArrayList<List<String>>();
		//OrmsPOSSaleSearchPage omPosSearchPage = OrmsPOSSaleSearchPage.getInstance();

		RegularExpression reg = new RegularExpression("^Res. # Invoice # Res. Status.*", false);
		IHtmlObject[] postable = browser.getTableTestObject(".text", reg);
		IHtmlTable posTableGrid = (IHtmlTable) postable[0];
		Browser.unregister(postable);
		
		posinforow.add(posTableGrid.getRowValues(0));

		return posinforow;
	}
	
	public void selectInvoiceNum(String InvoiceNum){
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression(InvoiceNum, false), true);
		
	}
	
	/**
	 * click the invoice# based on the given POSID on orms POS sale search page.
	 * @param POSNum
	 * @return Invoice# for the given POSNum
	 */
	public String selectInvoiceNumBasedOnPOSNum(String POSNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "OrderList");
		IHtmlTable table = (IHtmlTable)objs[0];
		if (!table.getCellValue(1, 0).equalsIgnoreCase(POSNum)){
			throw new ErrorOnDataException("Can't find the expect POS#:" + POSNum +"at the first row in POS search result list" );
		}
		String pattern = table.getCellValue(1, 1).trim();		
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression(pattern, false), true);
		return pattern;
	}
	
	public List<String> getPOSSaleNumColumnList(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "OrderList");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<String> posSaleNumList = table.getColumnValues(0);
		posSaleNumList.remove(0);
		
		Browser.unregister(objs);
		return posSaleNumList;
	}
	
}
