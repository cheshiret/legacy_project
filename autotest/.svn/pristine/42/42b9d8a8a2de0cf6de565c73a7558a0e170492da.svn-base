/*
 * Created on Nov 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InvoiceInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Michael Chen
 */
public class OrmsInvoiceSearchPage extends OrmsPage {

	List<String> invoiceLists;

	static private OrmsInvoiceSearchPage _instance = null;

	protected OrmsInvoiceSearchPage() {
		invoiceLists = new ArrayList<String>();
	}

	static public OrmsInvoiceSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsInvoiceSearchPage();
		}

		return _instance;
	}
	
	/** Determine if the page object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
				"Invoice Created From Date");
	}
	
	/**Input invoice num*/
	public void setInvoiceNum(String invoice) {
		browser.setTextField(".id", "invoiceNumber", invoice);
	}
	
	/**Input order num*/
	public void setOrderNum(String order) {
		browser.setTextField(".id", "orderNum", order);
	}
	
	/**Input receipt num*/
	public void setReceiptNum(String receipt) {
		browser.setTextField(".id", "receiptNum", receipt);
	}

	/**Input event ID*/
	public void setEventID(String eID) {
		browser.setTextField(".id", "eventID", eID);
	}

	/**Input event name*/
	public void setEventName(String eName) {
		browser.setTextField(".id", "eventName", eName);
	}

	/**Input start date*/
	public void setInFromDate(String fromDate) {
		browser.setTextField(".id", "startDate_ForDisplay", fromDate);
	}

	/**Input end num*/
	public void setInToDate(String toDate) {
		browser.setTextField(".id", "endDate_ForDisplay", toDate);
	}

	/**Input customer phone*/
	public void setCustomerPhone(String phone) {
		browser.setTextField(".id", "phone", phone);
	}

	/**Input last name*/
	public void setLastName(String lName) {
		browser.setTextField(".id", "lastName", lName);
	}

	/**Input first num*/
	public void setFirstName(String fName) {
		browser.setTextField(".id", "firstName", fName);
	}

	/**Input email*/
	public void setEmail(String email) {
		browser.setTextField(".id", "email", email);
	}

	/**Select "With amount owing only" check box*/
	public void checkWithAmountOwingOnly() {
		browser.selectCheckBox(".id", "balancedue");
	}

	/**unselect "With amount owing only" check box */
	public void uncheckWithAmountOwingOnly() {
		browser.unSelectCheckBox(".id", "balancedue");
	}
	
	/**
	 * select first check box in invoice search page invoice list.
	 */
	public void selectFirstCheckBox(){
		
		IHtmlObject[] objs = browser.getCheckBox(".id", "order");
		
		if (null == objs || objs.length<1){
			throw new ObjectNotFoundException("there is no outstanding payment in Invoice Search/list page");
		}
		browser.selectCheckBox(".id", "order");
		Browser.unregister(objs);
	}

	/**Select "Include Area Code"*/
	public void checkIncludeAreaCode() {
		browser.selectCheckBox(".id", "includeareacode");
	}

	/**Unselect "Include Area Code"*/
	public void uncheckIncludeAreaCode() {
		browser.unSelectCheckBox(".id", "includeareacode");
	}

	/**Click Go button*/
	public void clickGo() {
		Property[] properties = new Property[3];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".text", "Search");
		properties[2] = new Property(".id", "goAnchor");
		browser.clickGuiObject(properties);
	}

	/**Click make payment button*/
	public void clickMakePayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Make Payment");
	}

	/**
	 * If "Next" button is avaliable, click next button in reservation search page
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			((ILink)objs[0]).click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}

	/**Retrive all reserve info in the reservation search page */
	public List<List<String>> retriveInvoiceInfo() {
		List<List<String>> reserveinfo = new ArrayList<List<String>>();
		List<String> reserveinforow = new ArrayList<String>();

		do {
			RegularExpression reg = new RegularExpression(
					"Invoice # Invoice Created Date & Time Customer Name.*",false);
			IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);
			IHtmlTable reserveTableGrid=(IHtmlTable)reservetable[0];	
			
			int rowSize=reserveTableGrid.rowCount();
			for (int row = 0; row < rowSize; row++) {
				reserveinforow= reserveTableGrid.getRowValues(row);
				reserveinfo.add(reserveinforow);
			}
			Browser.unregister(reservetable);
		} while (gotoNext());

		return reserveinfo;
	}

	/**Get specific column's number*/
	public int getColNum(String colname) {
		List<List<String>> reservinfo= new ArrayList<List<String>>();
		List<String> reservinforow = new ArrayList<String>();
		
		int colcount = getTableColCount();

		reservinfo = retriveInvoiceInfo();
		reservinforow = (List<String>) reservinfo.get(0);
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
		RegularExpression reg = new RegularExpression(
				"Invoice # Invoice Created Date & Time Customer Name.*",false);
		IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);

		IHtmlTable reserveTableGrid = (IHtmlTable) reservetable[0];
		int colcount = reserveTableGrid.columnCount();

		Browser.unregister(reservetable);
		return colcount;
	}

	/**Get table's totla row num*/
	public int getTableRowCount() {
		RegularExpression reg = new RegularExpression(
				"Invoice # Invoice Created Date & Time Customer Name.*",false);
		IHtmlObject[] reservetable = browser.getTableTestObject(".text", reg);

		IHtmlTable reserveTableGrid=(IHtmlTable)reservetable[0];
		
		int rowCount = reserveTableGrid.rowCount();

		Browser.unregister(reservetable);
		return rowCount;
	}

	/** Fill in (and submit) reservation search form with reservation search criteria - as found in ReservationData parameter */
	public void searchInvoice(InvoiceInfo inv) {
		browser.sync();
		if (null != inv.invoiceNum&&inv.invoiceNum.length()>0) {
			this.setInvoiceNum(inv.invoiceNum);
		}
		if (null != inv.orderNum&&inv.orderNum.length()>0) {
			this.setOrderNum(inv.orderNum);
		}
		if (null != inv.invoicePhone&&inv.invoicePhone.length()>0) {
			this.setCustomerPhone(inv.invoicePhone);
		}
		if (null != inv.receiptNum&&inv.receiptNum.length()>0) {
			this.setReceiptNum(inv.receiptNum);
		}
		if (null != inv.eventID&&inv.eventID.length()>0) {
			this.setEventID(inv.eventID);
		}
		if (null != inv.eventName&&inv.eventName.length()>0) {
			this.setEventName(inv.eventName);
		}
		if (null != inv.invoiceFromDate&&inv.invoiceFromDate.length()>0) {
			this.setInFromDate(inv.invoiceFromDate);
		}
		if (null != inv.invoiceToDate&&inv.invoiceToDate.length()>0) {
			this.setInToDate(inv.invoiceToDate);
		}
		if (null != inv.invoicePhone&&inv.invoicePhone.length()>0) {
			this.setCustomerPhone(inv.invoicePhone);
		}
		if (null != inv.firstName&&inv.firstName.length()>0) {
			this.setFirstName(inv.firstName);
		}
		if (null != inv.lastName&&inv.lastName.length()>0) {
			this.setLastName(inv.lastName);
		}
		if (null != inv.email&&inv.email.length()>0) {
			this.setEmail(inv.email);
		}
		if (inv.amountOwing == true) {
			this.checkWithAmountOwingOnly();
		} else {
			this.uncheckWithAmountOwingOnly();
		}

		if (inv.areaCode == true) {
			this.checkIncludeAreaCode();
		} else {
			this.uncheckIncludeAreaCode();
		}
		this.clickGo();
	}


	/**Get cell value in the invoice search page
	 * @param row --The row of the cell
	 * @param col --The col of the cell
	 * */
	public String getSearchResultCellValue(int row, int col) {
		RegularExpression reg = new RegularExpression(
				"^Invoice #.+Invoice Created Date & Time.+Customer Name+.*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", reg);
		String toReturn = ((IHtmlTable)objs[0]).getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Get the one invoice information 
	 * @param invNum---The invoice number
	 * @return---Return the invoice informaiton
	 * @throws PageNotFoundException
	 * @throws ItemNotFoundException
	 */
	public String getInvInfo(String invNum) throws PageNotFoundException,
			ItemNotFoundException {
		String toReturn = "";
		//this.setReservNum(resID);
		this.setInvoiceNum(invNum);
		//this.clickGO();
		this.clickGo();
		//this.searchWaitExists();
		this.waitLoading();

		RegularExpression reg = new RegularExpression(
				"^Invoice #.+Invoice Created Date & Time.+Customer Name+.*",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", reg);
		IHtmlTable searchResult=(IHtmlTable)objs[0];

		if (invoiceLists.size() == 1) {

			SimpleDateFormat df = new SimpleDateFormat("MMM d,yyyy");
			try {
//				Date arriveDate = df.parse(HTMLTable.getCellValue(searchResult,
//						1, 10).toString());
				Date arriveDate = df.parse(searchResult.getCellValue(1, 10));
				Date departDate = df.parse(searchResult.getCellValue(1, 11));
				String nightStay = String.valueOf(DateFunctions.diffBetween(
						departDate, arriveDate));

				toReturn = "parkName->"
						+ searchResult.getCellValue(1, 7)
						+ "|"
						+ "areaName->"
						+ searchResult.getCellValue(1, 8)
						+ "|"
						+ "siteNum->"
						+ SiteInfoData.convertSiteNameNumber(searchResult.getCellValue(1, 9).toString())
						+ "|" + "arriveDate->"
						+ searchResult.getCellValue(1, 10) + "|"
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

	/**
	 * Input the element num
	 * @param elementNum
	 */
	public void setElementNum(String elementNum) {
		if (browser.checkHtmlObjectExists("id", "pagingBarRowsPerPage")) {
			browser.selectDropdownList("id", "pagingBarRowsPerPage",elementNum);
		}
	}

	public void cleanSearchCriteria() {
		setCustomerPhone("");
		setEmail("");
		setFirstName("");
		setLastName("");
		setInvoiceNum("");
		setOrderNum("");
		setReceiptNum("");
		setEventID("");
		setEventName("");
		setInFromDate("");
		setInToDate("");		
	}
	
	public void clickInvoiceNum(String num) {
		browser.clickGuiObject(".class", "Html.A", ".text", num);
	}
}
